package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.Jdbcutil;
import to.To;
public class UserService {
	//hi
   public List<String> getcustomers() {
	   Connection con=null;      ResultSet rs=null;
	   int x=1;   PreparedStatement ps=null;
	   List<String> l=new ArrayList<String>();
	   try {
		con=Jdbcutil.getmysqlconnection();
		String sql="select name from customer";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
	    while (rs.next()) {
			l.add(rs.getString(x));
		}
		} catch (Exception e) {e.printStackTrace();}
	    finally {Jdbcutil.cleanup(rs, con, ps);}
	    return l;
        }
    public int registeruser(To uto) {
    	int x=0;  Connection con=null;
    	PreparedStatement ps=null;
    	try {
		con=Jdbcutil.getmysqlconnection();
		String sql="insert into customer values(?,?,?,?,?,?,?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, getnextuserid());
		ps.setString(2, uto.getName());
		ps.setString(3, uto.getAddress());
		ps.setString(4, uto.getEmail());
		ps.setLong(5, uto.getPhone());
		ps.setString(6, uto.getCourse());
		ps.setInt(7, uto.getObtainedmarks());
		ps.setInt(8, uto.getTotalmarks());
		ps.setInt(9, uto.getPercentage());
		ps.setString(10, uto.getCollege());
		x=ps.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
    	finally {Jdbcutil.cleanup(ps, con);}
    	return x;
        } 
    public int updatecustomer(To uto) {
       int rs=0;         Connection con=null;      
 	   PreparedStatement ps=null;
       try {
	   con=Jdbcutil.getmysqlconnection();
	   String sql="update customer set name=? address=? email=? phone=? course=? obtainedmarks=? totalmarks=? percentage=? college=? where id=?";
	   ps=con.prepareStatement(sql);
	   ps.setString(1,uto.getName());
	   ps.setString(2,uto.getAddress());
	   ps.setString(3,uto.getEmail());
	   ps.setLong(4,uto.getPhone());
	   ps.setString(5,uto.getCourse());
	   ps.setInt(6,uto.getObtainedmarks());
	   ps.setInt(7,uto.getTotalmarks());
	   ps.setInt(8,uto.getPercentage());
	   ps.setString(9,uto.getCollege());
	   ps.setInt(10,uto.getId());
	   rs=ps.executeUpdate();
	   } catch (Exception e) {e.printStackTrace();}
       finally {Jdbcutil.cleanup( ps,con);}
       return rs;
       }
     public int getnextuserid() {
       Connection con=null;      ResultSet rs=null;
  	   int x=0;   PreparedStatement ps=null;
  	   try {
	   con=Jdbcutil.getmysqlconnection();
	   String sql="select max(id) from customer";
	   ps=con.prepareStatement(sql);
	   rs=ps.executeQuery();
	   if(rs.next()) {
	    x=rs.getInt(1);
	    x=x+1;
	   }
	   } catch (Exception e) {e.printStackTrace();}
  	   finally {Jdbcutil.cleanup(rs, con, ps);}
       return x;
       }
     public int deletecustomer(int id) {
     	int x=0;  Connection con=null;
     	PreparedStatement ps=null;
     	try {
 		con=Jdbcutil.getmysqlconnection();
 		String sql="delete from customer where id=?";
 		ps=con.prepareStatement(sql);
 		ps.setInt(1,id);
 		x=ps.executeUpdate();
 		} catch (Exception e) {e.printStackTrace();}
     	finally {Jdbcutil.cleanup(ps, con);}
     	return x;
         } 
}
