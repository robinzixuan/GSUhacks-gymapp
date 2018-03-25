import MySQLdb
db = MySQLdb.connect(host="localhost",user="gtapp",passwd="Git!hacks2018s",db="apphacks")
cur=db.cursor()

def create():
    sql="""CREATE TABLE PASSWORD (
             ID  INT NOT NULL AUTO_INCREMENT,
             NETID CHAR(20),
             PASSWD  CHAR(20),
             EMAIL CHAR(20),  
             UID CHAR(60),
             SID CHAR(60)
             """
    cur.execute(sql)

def insert(name,passwd,email, uid, sid):   
    sql = "INSERT INTO PASSWORD ( 'NETID', 'PASSWD', 'EMAIL','UID','SID') VALUES(%s',%s',%s',‘%s','%s')"
    ( name,passwd,email,uid,sid)
    try:
        cur.execute(sql)
        db.commit()
    except:
       db.rollback()



    
def update(name,passwd, email,uid,sid):
    sql = "UPDATE PASSWORD  ( 'NETID', 'PASSWD', 'EMAIL'，'UID','SID') VALUES(%s',%s',%s','%s','%s')"
    ( name,passwd,email,uid,sid)
    try:
        cur.execute(sql)
        db.commit()
    except:
       db.rollback()



def connect(file):
    
    return name,passwd,email,uid,sid

def main():
    file=open()
    (name,passwd,email,uid,sid)= connect(file);
    sql= 'SELECT * FROM PASSWORD WHERE NAME'+ name
    try:
        # Execute the SQL command
       cur.execute(sql)
       # Fetch all the rows in a list of lists.
       ls= cur.fetchall()
    except:
        print('Error!!!')
    if ls==(): 
        insert(name,passwd,email,uid,sid)
    else:
        update(name,passwd,email,uid,sid)
    db.close()

main()
