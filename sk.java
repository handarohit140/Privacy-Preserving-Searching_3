import java.sql.*;

class sk
{
public static void main(String args[])throws Exception
{
String str="";
int S[]=new int[163129];
for(int i=0;i<=163128;i++)
{
S[i]=(int)(Math.random()*2);
str+=S[i];
}

	
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cao","root","");
Statement stmt=con.createStatement();


String query1="insert into `secret_key`(`sr`,`skey`) values('1','"+str+"');";
stmt.executeUpdate(query1);
}
}
