#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 24 06:15:26 2018

@author: luohaozheng
"""

import numpy as np  
import pandas as pd  
from sklearn import ensemble, cross_validation  
import MySQLdb
db = MySQLdb.connect(host="localhost",user="gtapp",passwd="Git!hacks2018s",db="apphacks")
cur=db.cursor()


def cal_time(x,y):
    time1=list()
    list1=x.split(':')
    list2=y.split(':')
    t1=int(list1[0])
    t2=int(list2[0])
    for i in range(t1,t2+1):
        time1.append(i+t1)
    return time1

def time_list(var,tp,timestart,timeend):
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

def rmspe(zip_list,count):  
    # w = ToWeight(y)  
    # rmspe = np.sqrt(np.mean((y - yhat) ** 2))  
    sum_value=0.0  
    # count=len(zip_list)  
    for real,predict in zip_list:  
        v1=(real-predict)**2  
        sum_value += v1  
    v2=sum_value / count  
    v3=np.sqrt(v2)  
    return v3  


def get_features_target(data):  
    data_array=pd.np.array(data)#传入dataframe，为了遍历，先转为array  
    features_list=[]  
    target_list=[]  
    for line in data_array:  
        temp_list=[]  
        for i in range(0,384):#一共有384个特征  
            if i == 360 :#index=360对应的特征是flow  
                target_temp=int(line[i])  
            else:  
                temp_list.append(int(line[i]))  
        features_list.append(temp_list)  
        target_list.append(target_temp)  
    return features_list, target_list  

def run_demo():  
    #df=pd.read_sql('SELECT * FROM GAMEAPP;', con= db)
    (var,tp,timestart,timeend)=open()
    gym= time_list(var,tp,timestart,timeend)
    a= input('type:')
    a= a.lower()
    anly_dict=dict()
    for k  in  gym:
        anly_dict[k]=gym[k][a]
    data= pd.from_dict(anly_dict, orient='columns', dtype=None)
    data_other,data=cross_validation.train_test_split(data,test_size=0.001,random_state=10)#为了减少代码运行时间，方便测试  
    train_and_valid, test = cross_validation.train_test_split(data, test_size=0.2, random_state=10)  
    train, valid = cross_validation.train_test_split(train_and_valid, test_size=0.01, random_state=10)  
    train_feature, train_target = get_features_target(train)  
    test_feature, test_target = get_features_target(test)  
    valid_feature, valid_target = get_features_target(valid)  
  
    params = {'n_estimators': 500, 'max_depth': 4, 'min_samples_split': 2,  
              'learning_rate': 0.01, 'loss': 'ls'}  
    clf = ensemble.GradientBoostingRegressor(**params)  
  
    clf.fit(train_feature, train_target) #训练  
    # mse = mean_squared_error(test_target, clf.predict(test_feature)) #预测并且计算MSE  
    # print(mse)  
    pre=clf.predict(test_feature)  
    pre_list=list(pre)  
    real_pre_zip=zip(test_target,pre_list)  
  
    count=len(pre_list)  
    error=rmspe(real_pre_zip,count)  
    print(error)  
  
run_demo()  


