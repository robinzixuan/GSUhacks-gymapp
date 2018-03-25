#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 23 23:16:34 2018

@author: luohaozheng
"""

import smtplib  
import sys  
import email.mime.text
mail_username='luohaozh@gmail.com'  
mail_password='Luo505505'  
from_addr = mail_username  
to_addrs=('jchen786@gatech.edu')  
HOST = 'smtp.gmail.com'  
PORT = 25  
  
# Create SMTP Object  
smtp = smtplib.SMTP()  
print ('connecting ...')  
  
# show the debug log  
smtp.set_debuglevel(1)  

mail_msg = """
<p>please verify..</p>
<p><a href="http://162.243.172.39:3000/test">This is a Link</a></p>
"""
  
# connet  
try:  
    print (smtp.connect(HOST,PORT)  )
except:  
    print ('CONNECT ERROR ****'  )
# gmail uses ssl  
smtp.starttls()  
# login with username & password  
try:  
    print ('loginning ...' ) 
    smtp.login(mail_username,mail_password)  
except:  
    print ('LOGIN ERROR ****' ) 
# fill content with MIMEText's object   
msg = email.mime.text.MIMEText(mail_msg, 'html', 'utf-8') 
msg['From'] = from_addr  
msg['To'] = ';'.join(to_addrs)  
msg['Subject']='hello , today is a special day'  
print( msg.as_string()  )
smtp.sendmail(from_addr,to_addrs,msg.as_string())  
smtp.quit()   



