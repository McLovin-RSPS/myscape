//package com.arlania.vote; // dont forget to change packaging ^-^
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import com.arlania.world.entity.impl.player.PlayerSaving;
//import com.sun.security.ntlm.Client;
//
//
//public class Voting implements Runnable {
//
//	public static final String HOST = "23.227.181.131"; // website ip address
//	public static final String USER = "gdngfysi_voteuser";
//	public static final String PASS = "Azmicohen123";
//	public static final String DATABASE = "gdngfysi_vote";
//
//	private Client player;
//	private Connection conn;
//	private Statement stmt;
//
//	public Voting(Client player) {
//		this.setPlayer(player);
//	}
//
//	@Override
//	public void run() {
//		try {
//			if (!connect(HOST, DATABASE, USER, PASS)) {
//				return;
//			}
//
//			String name = PlayerSaving.getUsername().replace(" ", "_");
//			ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='"+name+"' AND claimed=0 AND voted_on != -1");
//
//			while (rs.next()) {
//				String ipAddress = rs.getString("ip_address");
//				int siteId = rs.getInt("site_id");
//
//
//				// -- ADD CODE HERE TO GIVE TOKENS OR WHATEVER
//
//				System.out.println("[Vote] Vote claimed by "+name+". (sid: "+siteId+", ip: "+ipAddress+")");
//
//				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
//				rs.updateRow();
//			}
//
//			destroy();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	public boolean connect(String host, String database, String user, String pass) {
//		try {
//			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
//			return true;
//		} catch (SQLException e) {
//			System.out.println("Failing connecting to database!");
//			return false;
//		}
//	}
//
//	public void destroy() {
//        try {
//    		conn.close();
//        	conn = null;
//        	if (stmt != null) {
//    			stmt.close();
//        		stmt = null;
//        	}
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//	public int executeUpdate(String query) {
//        try {
//        	this.stmt = this.conn.createStatement(1005, 1008);
//            int results = stmt.executeUpdate(query);
//            return results;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return -1;
//    }
//
//	public ResultSet executeQuery(String query) {
//        try {
//        	this.stmt = this.conn.createStatement(1005, 1008);
//            ResultSet results = stmt.executeQuery(query);
//            return results;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//	public Client getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(Client player) {
//		this.player = player;
//	}
//
//}
