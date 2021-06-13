import java.io.*;
import java.net.*;
import java.util.Date;
import java.sql.Timestamp;

public class SocketClient {
	// ip thetering 192.168.43.162
	//ip wifi id 10.212.2.231
	//ip wifi UISI 10.1.25.15
	//ip wifi SC 172.16.1.158
	String ipAddress = "192.168.137.13";
	int port = 5000;
	public static SessionUser user;
	//static String savedNote;
	public void SessionUserSet(SessionUser user) {
		this.user = user;
	}
	public String SavedNote(){
		try{
			Socket s = new Socket(ipAddress,port);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//String msgin = din.readUTF();
			//savedNote = msgin;
			dout.writeUTF(user.GetId()+"<Show>");
			String msgin = "";
            msgin = din.readUTF();
			if(msgin.contains("<kolom>")) {
				//System.out.println(msgin);
                return msgin;
			}
			return "Tidak ada data";
		}catch(Exception e){
			
		}
		return "Tidak ada Data";
	}
	
	/*public String getNote() {
		return this.savedNote;
	}*/
	public void AddNote(String title,String inputText){
		try{
			Socket s = new Socket(ipAddress,port);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//String msgin ="", msgout = "";
			//Date d = new Date();
			//long time = d.getDate();
			Security sc = new Security();
			Date ts = new Timestamp(System.currentTimeMillis());
			//key sementara
			
			
			//SessionUser su = new SessionUser();
			dout.writeUTF(title+"<add>"+inputText+"<add>"+ts+"<add>"+user.GetId());
			System.out.println(title+"<add>"+inputText+"<add>"+ts+"<add>"+user.GetId());
			/*while(!msgin.equals("stop")) {
				System.out.print("Client : ");
				msgout = br.readLine();
				dout.writeUTF("pong");
				msgin =din.readUTF();
				System.out.print("Server : ");
				System.out.println(msgin);
			}
			msgin = din.readUTF();
			System.out.println(msgin);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Register(String nama,String email,String password){
		try{
			Socket s = new Socket(ipAddress,port);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//String msgin ="", msgout = "";
			//Date d = new Date();
			//long time = d.getDate();
			Security sc = new Security();
			Date ts = new Timestamp(System.currentTimeMillis());
			//key sementara

			SessionUser su = new SessionUser();
			dout.writeUTF(nama+"<Register>"+email+"<Register>"+password);
			main m = new main();
			m.Login();
			/*while(!msgin.equals("stop")) {
				System.out.print("Client : ");
				msgout = br.readLine();
				dout.writeUTF("pong");
				msgin =din.readUTF();
				System.out.print("Server : ");
				System.out.println(msgin);
			}
			msgin = din.readUTF();
			System.out.println(msgin);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Login(String email,String password) {
		try{
			Socket s = new Socket(ipAddress,port);
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			//SessionUser su = new SessionUser();
			dout.writeUTF(email+"<Login>"+password);
			String msgin = din.readUTF();

			if(msgin.contains("<Berhasil>")) {
				System.out.println("Berhasil-Client");

				String[] splitmsgin = msgin.split("<Berhasil>");
				String emailin = splitmsgin[0];
				String namain = splitmsgin[1];
				int idin = Integer.parseInt(splitmsgin[2]);
				
				user = new SessionUser();
				
				user.SetEmail(emailin);
				user.SetId(idin);
				//SocketClient sc = new SocketClient();
				//sc.SessionUserSet(user);
				//this.su = user;
				//System.out.println(emailin);
				GUI g = new GUI();
				g.mainMenu();
				
				
			}else{
				System.out.println("Login Not Processed By Client");
			}
			/*while(!msgin.equals("stop")) {
				System.out.print("Client : ");
				msgout = br.readLine();
				dout.writeUTF("pong");
				msgin =din.readUTF();
				System.out.print("Server : ");
				System.out.println(msgin);
			}
			msgin = din.readUTF();
			System.out.println(msgin);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void DeleteNote(){
		
	}
	
	public Socket getIP() throws UnknownHostException, IOException {
		Socket s ;
			s = new Socket(ipAddress,port);
			return s;
	}
	
	/*public InetAddress getClientIP() throws UnknownHostException, IOException {
		Socket s ;
			s = new Socket(ipAddress,port);
			return s.getLocalAddress();
	}*/
}
