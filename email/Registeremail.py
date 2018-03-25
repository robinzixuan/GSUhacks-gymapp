#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 23 21:29:29 2018

@author: luohaozheng
"""

from Token import Token
import MySQLdb
import tushare as tushare
import pandas as pd
import sqlalchemy 
import create_engine
from datetime import date, datetime, timedelta
from django.core.mail import send_mail
from django import HttpResponse, HttpResponseRedirect
from django.shortcuts import render 
from django.contrib.auth.models import User
from .forms import UserLoginForm, CustomUserCreationForm
db = MySQLdb.connect(host="localhost",user="gtapp",passwd="Git!hacks2018s",database="apphacks")
cur=db.cursor()
sql= 'SELECT SID FROM PASSWORD WHERE NAME=='+ username
try:
    # Execute the SQL command
   cur.execute(sql)
   # Fetch all the rows in a list of lists.
   ls= cur.fetchall()
   for row in ls:
      ID = row[0]
      name = row[1]
      PASSWD = row[2]
      email = row[3]
      uid = row[4]
      SECRET_KEY= row[5]
except:
    print('Error!!!')

token_confirm = Token(SECRET_KEY)

def Register(request):
    global username,password,email
    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)
    if form.is_valid():
        #new_user = form.save()
        username,password,email = form['username'],form['password'],form['email']
        user = User.objects.create(username=username, password=password, email=email, is_active=False)
        user.set_password(password)
        user.save()
        token = token_confirm.generate_validate_token(username)
        #active_key = base64.encodestring(username)
        #send email to the register email
        message = "\n".join([
        u'{0},welcome'.format(username),
        u'',
        '/'.join(['account/activate',token])
        ])
        send_mail(u'information',message, None,[form['email']])
        #user = auth.authenticate(username=username,password=password)
        #auth.login(request,user)
        return HttpResponse(u"login in your email for verfication。")
    else:
        form = CustomUserCreationForm()
    return render(request,'register.html',{'form':form})


def active_user(request,token):
    try:
        username = token_confirm.confirm_validate_token(token)
    except:
        return HttpResponse(u'sorry')
    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        return HttpResponse(u'sorry,retry')
    user.is_active = True
    user.save()
    confirm = u'success!!'
    return HttpResponseRedirect('/account/login',{'confirm':confirm})

