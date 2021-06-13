import java.io.*;
import java.net.*;
public class ServerThread extends Thread {
	Socket s;
	ServerThread(Socket s){
		this.s = s;
	}
	ServerThread(){
		
	}
	public String login;
	public  void LoginSet(String login){
		this.login = login;
	}
	
	public void run(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//while(br != null){
		try {
			//System.out.println("Koneksi telah tersambung ke client");
            //System.out.println("A new client is connected : " + s); 
			System.out.println("Client " + s.getInetAddress() + " Perform an Action "); 

			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msgin ="", msgout = "";
			while(!msgin.equals("stop")) {
				msgin = din.readUTF();
				//System.out.println("Client(" + s.getInetAddress() +") :"+ msgin);
				if (msgin.equalsIgnoreCase("ping")) {
					msgout = "pong";
					dout.writeUTF(msgout);
					dout.flush();
					
				}else if(msgin.equalsIgnoreCase("Hey Sayang")) {
					msgout = "Hai, Udah makan?";
					dout.writeUTF(msgout);
					dout.flush();
				}else if(msgin.equalsIgnoreCase("udah kok")) {
					msgout = "Love u";
					dout.writeUTF(msgout);
					dout.flush();
				}else if(msgin.contains("<add>")){
					String judul = "";
					String isi ="";
					String date ="";
					int id_user = 0;
					String[] splitmsgin = msgin.split("<add>");
					judul = splitmsgin[0];
					isi = splitmsgin[1];
					date = splitmsgin[2];
					id_user = Integer.parseInt(splitmsgin[3]);
					Security sc = new Security();
					char[] ubah = judul.toCharArray();
					char[] ubah2 = isi.toCharArray();
					String kunci = "kepoo";
					String key = "4312567";
					String kuncipoly = sc.UrutanKeyPoly(judul,kunci);
					String kuncipoly2 = sc.UrutanKeyPoly(isi,kunci);
					String chiperTextPoly = sc.EnkripsiPoly(judul,kuncipoly);
					String chiperTextPoly2 = sc.EnkripsiPoly(isi,kuncipoly2);
					String enkrip_judul = sc.EncryptRT(key,chiperTextPoly);
		 			String enkrip_isi = sc.EncryptRT(key,chiperTextPoly2);
		 		
		 			/*String dekrip_judul = sc.DecryptRT(key, enkrip_judul);
		 			String dekrip_isi = sc.DecryptRT(key, enkrip_isi);
		 			String dekrip2_judul = sc.DeskripsiPoly(dekrip_judul, kuncipoly);
		 			String dekrip2_isi = sc.DeskripsiPoly(dekrip_isi, kuncipoly2);*/
					
					MySQL ms = new MySQL();
					ms.AddNote(enkrip_judul,enkrip_isi,date,id_user);

					System.out.println("Successfully Inserted");
					System.out.println("cek");
					/*System.out.println("Judul : "+judul);
					System.out.println("isi : "+isi);
					System.out.println("Date : "+date);*/
				}else if (msgin.contains("<Register>")){
					
					String nama = "";
					String password = "";
					String email = "";
					String[] splitmsgin = msgin.split("<Register>");
					nama = splitmsgin[0];
					email = splitmsgin[1];
					password = splitmsgin[2];
					MySQL ms = new MySQL();
					ms.Register(nama, email, password);
					
				}else if(msgin.contains("<Login>")){
					String email = "";
					String password = "";
					String[] splitmsgin = msgin.split("<Login>");
					email = splitmsgin[0];
					password = splitmsgin[1];
					MySQL ms = new MySQL();
					ms.Login(email, password,s);
					/*System.out.println(ms.getLogin());
					dout.writeUTF(ms.getLogin());*/

				}else if(msgin.contains("<Show>")){
					String[]split = msgin.split("<Show>");
					int Id = Integer.parseInt(split[0]);
					System.out.println("Enter Show session");
					MySQL ms = new MySQL();
					ms.ShowNote(s,Id);
				}
				else {
					msgout = "";
					dout.writeUTF(msgout);
					dout.flush();
				}
			}
			msgout = "Koneksi telah terputus";
			dout.writeUTF(msgout);
			dout.flush();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	//\}
}
