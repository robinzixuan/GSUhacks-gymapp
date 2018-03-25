#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 24 05:41:07 2018

@author: luohaozheng
"""
def cal_time(x,y):
    time1=list()
    list1=x.split(':')
    list2=y.split(':')
    t1=int(list1[0])
    t2=int(list2[0])
    for i in range(t1,t2+1):
        time1.append(i+t1)
    return time1

def time_list():
    (var,tp,timestart,timeend)=open()
    gym=dict()
    l1=["leg","chest","back","shoulder","belly"]
    l2=["0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
    time1=cal_time(timestart,timeend)
    if var  not in gym:
        gym[var]=dict()
        for i in l1:
            gym[var][i]=dict()
            for p in l2:
                gym[var][i][p]=0
        for q in time1:
            gym[var][tp][q]=1
    else:
        for q in time1:
            gym[var][tp][q]+=1
    
    return gym       