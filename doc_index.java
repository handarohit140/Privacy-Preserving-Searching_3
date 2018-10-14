import java.sql.*;

class doc_index
{

public static void main(String args[])throws Exception
{
long startTime=0, endTime=0,x=0;
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cao","root","");
Statement stmt=con.createStatement();
Statement stmt1=con.createStatement();
Statement stmt2=con.createStatement();
Statement stmt3=con.createStatement();

String query="select * from `doc_level`";
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{
	int d_arr[]=new int[163129];

	for(int k=0;k<163129;k++)
	d_arr[k]=0;

	int sr=rs.getInt("Doc_ID");
	String did=rs.getString("L1");
	String did_array[]=did.split(",");

	startTime = System.currentTimeMillis();
	for(int k=0;k<did_array.length;k++)
	{
		String query2="select `sr` from `keyword_doc_count` where `keyword`='"+did_array[k]+"'";
		ResultSet rs1=stmt1.executeQuery(query2);
		rs1.next();

		int sr1=rs1.getInt("Sr");
		d_arr[sr1]=1;
	}
	String dindex="";
	for(int k=0;k<163129;k++)
	{
		dindex+=d_arr[k];
	}
	endTime = System.currentTimeMillis();	
	x=endTime-startTime;

	int M1[][]=new int[163129][163129];
	int M2[][]=new int[163129][163129];
	for(int m=0;m<=163129;m++)
	{
		for(int n=0;n<=163129;n++)
		{
			if(m==n)
				M2[m][n]=M1[m][n]=1;
			else
				M2[m][n]=M1[m][n]=0;
		}
	}

	
		String query4="select `skey` from `secret_key`";
		ResultSet rs3=stmt3.executeQuery(query4);
		rs3.next();

	String strr=rs3.getString("skey");

	double d1[]=new double[163129];
	double d2[]=new double[163129];

	startTime = System.currentTimeMillis();
	for(int m=0;m<strr.length();m++)
	{
		if(strr.charAt(m)=='0')
		{
			d1[m]=d2[m]=d_arr[m];
		}
		else
		{
			float xx=(float)(Math.random());
			xx=(float)(Math.floor(xx * 100)) / 100;
			d1[m]=xx;
			d2[m]=d_arr[m]-d1[m];
		}
	}

	String index1="";
	String index2="";

	float sum1=0,sum2=0;
	for(int m=0;m<=163129;m++)
	{
		sum1=0;sum2=0;
		for(int n=0;n<163129;n++)
		{
			sum1+=M1[m][n]*d1[m];
			sum2+=M2[m][n]*d2[m];			
		}
	index1+=sum1+",";
	index2+=sum2+",";
	}
	endTime = System.currentTimeMillis();	
	x+=endTime-startTime;

	String query3="insert into `index_doc`(`Dindex1`,`Dindex2`,`time`) values('"+index1+"','"+index2+"','"+x+"');";
	stmt2.executeUpdate(query3);
}
}
}
