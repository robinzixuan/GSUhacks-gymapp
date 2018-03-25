import MySQLdb
import pandas as pd
db = MySQLdb.connect(host="localhost",user="gtapp",passwd="Git!hacks2018s",db="apphacks")
cur=db.cursor()

def create():
    sql="""CREATE TABLE GAMEAPP (
             ID  INT NOT NULL AUTO_INCREMENT,
             NAME  CHAR(20),
             AGE INT,  
             GENDER CHAR(20),
             SPORT CHAR(20),
             PHONENUMBER INT,
             EMAIL CHAR(20),
             HOMEX INT,
             HOMEY INT,
             WORKX INT,
             WORKY INT,
             COMMENT CHAR(100)"""
    cur.execute(sql)

def insert(name,age,gender,sport,phone,email,homex,homey, workx,worky, comments):   
    sql = "INSERT INTO GAMEAPP ( 'NAME', 'AGE', 'GENDER',  'SPORT', 'PHONENUMBER,'EMAIL', 'HOMEX','HOMEY','WORKX','WORKY', 'COMMENTS') VALUES('%s','%d','%s','%s','%d','%s','%d','%d','%d','%d','%s')"
    ( name, age, gender, sport, phone,email,homex,homey,workx,worky,comments)
    try:
        cur.execute(sql)
        db.commit()
    except:
       db.rollback()


def delete(name):
    sql = "DELETE FROM GAMEAPP WHERE NAME=="+name
    try:
        cur.execute(sql)
        db.commit()
    except:
       db.rollback()

    
def update(name,age,gender,sport,phone,email,homex,homey,workx,worky, comments):
    sql = "UPDATE GAMEAPP ( 'NAME', 'AGE', 'GENDER',  'SPORT', 'PHONENUMBER,'EMAIL', 'HOMEX','HOMEY','WORKX','WORKY', 'COMMENTS') VALUES('%s','%d','%s','%s','%d','%s','%d','%d','%d','%d','%s')"
    ( name, age, gender, sport, phone,email,homex,homey,workx,worky,comments)
    try:
        cur.execute(sql)
        db.commit()
    except:
       db.rollback()

def connect(file):
    
	return name,age,gender,sport,phone,email,homex,homey,workx,worky,comments

def main():
    file=open()
    (name,age,gender,sport,phone,email,homex,homey,workx,worky,comments)= connect(file)
    sql= 'SELECT * FROM GAMEAPP WHERE Name=='+name
    try:
        # Execute the SQL command
       cur.execute(sql)
       # Fetch all the rows in a list of lists.
       ls= cur.fetchall()
    except:
        print('Error!!!')
    if ls==(): 
    	insert(name,age,gender,sport,phone,email,homex,homey,workx,worky,comments)
    else:
        a= input('whether you should update the value of delete the data? (U/D/N)')
        a=a.upper()
        if a=='U':
            update(name,age,gender,sport,phone,email,homex,homey,workx,worky,comments)
        elif a=='D':
            delete(name)
    df=pd.read_sql('SELECT * FROM GAMEAPP;', con= db)
    db.close()
    
main()