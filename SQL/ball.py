import MySQLdb
import pandas as pd
db = MySQLdb.connect("localhost","gtapp","Git!hacks2018s","apphacks")
cursor=db.execute()
ls1=['LEG','CHESK','BACK','SHOULDER','BELLY']
def create():
    for i in ls1:
        sql="CREATE TABLE"+ i + """(
                 ID  INT NOT NULL AUTO_INCREMENT,
                 NAME CHAR(20),
                 ONE INT,
                 TWO INT,  
                 THREE INT,
                 FOUR INT,
                 FIVE INT,
                 SIX INT,
                 SEVEN INT,
                 EIGHT INT,
                 NINE INT,
                 TEN INT,
                 ELEVEN INT,
                 TWELVE INT,
                 THIRTEEN INT,
                 FOURTEEN INT,
                 FIFTEEN INT,
                 SIXTEEN INT,
                 SEVENTEEN INT,
                 EIGHTEEN INT,
                 NINETEEN INT,
                 TWENTY INT,
                 TWENTYONE INT,
                 TWENTYTWO INT,
                 TWENTYTHREE INT,
                 ZERO INT,
                 COMMENTS CHAR(100)
                 """
        cursor.execute(sql)

def insert(tp,name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments):  
    sql = "INSERT INTO"+ tp +" ( 'NAME', 'ONE','TWO', 'FOUR', 'FIVE', 'SIX',  'SEVEN','EIGHT', 'NINE','TEN','ELEVEN','TWELVE','THIRTEEN','FOURTEEN','FIVETEEN','SIXTEEN','SEVENTEEN','EIGHTEEN','NINETEEN','TWENTY','TWENTYONE','TWENTYTWO','TWENTYTHREE', 'ZERO','COMMENTS') VALUES('%s','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%s')"
    (name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments)
    try:
        cursor.execute(sql)
        db.commit()
    except:
       db.rollback()


def delete(tp,name):
    sql = "DELETE FROM"+tp+ "WHERE NAME=="+name
    try:
        cursor.execute(sql)
        db.commit()
    except:
       db.rollback()

    
def update(tp,name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments):
    sql = "UPDATE"+ tp +"( 'NAME', 'ONE','TWO', 'FOUR', 'FIVE', 'SIX',  'SEVEN','EIGHT', 'NINE','TEN','ELEVEN','TWELVE','THIRTEEN','FOURTEEN','FIVETEEN','SIXTEEN','SEVENTEEN','EIGHTEEN','NINETEEN','TWENTY','TWENTYONE','TWENTYTWO','TWENTYTHREE', 'ZERO','COMMENTS') VALUES('%s','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%s')"
    (name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments)
    try:
        cursor.execute(sql)
        db.commit()
    except:
       db.rollback()

def connect(file):

	return tp, name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments

def main():
    file=open()
    (tp, name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments)= connect(file);
    sql = "SELECT * FROM GYM WHERE NAME=="+name
    try:
        # Execute the SQL command
       cursor.execute(sql)
       # Fetch all the rows in a list of lists.
       ls=cursor.fetchall()
    except:
        print('Error!!!')
    if ls==(): 
        insert(tp, name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments)
    else:
        a=input('whether you should update the value of delete the data? (U/D/N)')
        a= a.upper()
        if a=='U':
            	update(tp, name,one,two,three,four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty,twentyone,twentytwo,twentythree,zero, comments)
        elif a=='D':
            delete(tp,name)
    df=db.read_sql('SELECT * FROM'+tp, con= db)
    #give the value ans sort with it
    df.sort(columns='B')
    db.close()
main()
