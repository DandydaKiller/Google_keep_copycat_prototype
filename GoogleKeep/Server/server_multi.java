import java.io.*;
import java.net.*;

public class server_multi {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(5000);
		System.out.print("Server Starting ");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.println("Ok!");

		while(true){
		
		try{
			Socket s = ss.accept();
			System.out.println("Connection Successfull");
			System.out.println("Ip Server : "+s.getLocalAddress());
			ServerThread st = new ServerThread(s);
            st.start();

			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	}

}
