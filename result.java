import java.sql.*;

class result
{
public sttaic void main(String args[])throws Exception
{
long startTime=0, endTime=0,x=0;
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cao","root","");
Statement stmt=con.createStatement();
Statement stmt1=con.createStatement();
Statement stmt2=con.createStatement();
Statement stmt3=con.createStatement();

String query1="select * from `index_query`"
ResultSet rs=stmt.executeQuery(query1);


while(rs.next())
{
	int sr=rs.getInt("sr");
	String index1=rs.getString("index1");
	String index2=rs.getString("index2");

	String arr1[]=index1.split(",");
	String arr2[]=index2.split(",");

	for(int m=0;m<arr1.lenght;m++)
	{
		double xx=Double.parseDouble(arr1[m]);
		double yy=Double.parseDouble(arr2[m]);


	}


}



}

}