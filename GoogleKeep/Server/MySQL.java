import java.sql.*;
import java.io.*;
import java.net.*;

public class MySQL {
	
	
	public void AddNote(String judul, String isi, String date, int id_user){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/note","root","");
			Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

			int currentID = 1;
			Statement stat = con.createStatement();
			Statement stat2 = con2.createStatement();
			ResultSet rs2 = stat2.executeQuery("SELECT * FROM note.note_created order by id desc Limit 1");
			while(rs2.next()){
				currentID = currentID + rs2.getInt("id");
			}
			String sql = "INSERT INTO `note`.`note_created` (`id`,`judul`, `isi`,`created_at`,`id_user`) VALUES ("+currentID+",'"+judul+"','"+isi+"','"+date+"',"+id_user+")";
			int rs = stat.executeUpdate(sql);
			con.close();
		}catch(Exception e) {
			
		}
		
	}
	
	public void ShowNote(Socket st, int id){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream dout = new DataOutputStream(st.getOutputStream());
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/note","root","");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM `note_created` WHERE `id_user` = "+id);
			String data = "";
			Security s = new Security();
			//System.out.println("Masuk mysql");
			while(rs.next()){
				String kunci = "kepoo";
				String key = "4312567";
				//String kuncipoly = s.UrutanKeyPoly(rs.getString("judul"),kunci);
				//String kuncipoly2 = s.UrutanKeyPoly(rs.getString("isi"),kunci);
				//String dekrip_judul = s.DecryptRT(key, rs.getString("judul"));
	 			//String dekrip_isi = s.DecryptRT(key, rs.getString("isi"));
	 			//String dekrip2_judul = s.DeskripsiPoly(dekrip_judul, kuncipoly);
	 			//String dekrip2_isi = s.DeskripsiPoly(dekrip_isi, kuncipoly2);
	 			
	 			data = data+ rs.getString("judul")+"<kolom>"+rs.getString("isi")+"<kolom>"+rs.getTimestamp("created_at")+"<kolom>"+rs.getInt("id")+"<baris>";
			}
			System.out.println(data);
			System.out.println("Enter this page");
			dout.writeUTF(data);
			con.close();
		}catch(Exception e){
			
		}
	}
	
	
	//Authentication
	
	public void Register(String nama,String email,String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/note","root","");
			Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

			int currentID = 1;
			Statement stat = con.createStatement();
			Statement stat2 = con2.createStatement();
			ResultSet rs2 = stat2.executeQuery("SELECT * FROM note.users order by id desc Limit 1");
			while(rs2.next()){
				currentID = currentID + rs2.getInt("id");
			}
			String sql = "INSERT INTO `note`.`users` (`id`,`nama`, `email`,`password`) VALUES ("+currentID+",'"+nama+"','"+email+"','"+password+"')";
			int rs = stat.executeUpdate(sql);
			System.out.println("User berhasil terdaftar!");
			con.close();
		}catch(Exception e) {
			
		}
		
	}
	
	
	
	
	public void Login(String email,String password, Socket s){
		try {
			//DataInputStream din = new DataInputStream(s.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/note","root","");

			//int currentID = 1;
			//Statement stat = con.createStatement();
			Statement stat2 = con.createStatement();
			ResultSet rs2 = stat2.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND password = '"+password+"'");
			while(rs2.next()){
				if(rs2.getString("email").equals(email) && rs2.getString("password").equals(password)){
					/*ServerThread st = new ServerThread();
					st.LoginSet("<Berhasil>");*/
					
					dout.writeUTF(rs2.getString("email")+"<Berhasil>"+rs2.getString("nama")+"<Berhasil>"+rs2.getInt("id"));
					System.out.println("Berhasil-Server");
				}else{
					dout.writeUTF("<Login_Gagal>");
					System.out.println("Login Gagal");
				}
			}
			
			con.close();
		}catch(Exception e) {
			
		}
	}
	
	
}
