import java.io.IOException;
import java.util.Scanner;
public class main {
	
	public static void Register() {
		System.out.println("Halaman Register");
		Scanner input = new Scanner(System.in);
		System.out.print("Nama :");
		String nama = input.nextLine();
		System.out.print("Email :");
		String email = input.nextLine();
		System.out.println("Password :");
		String password = input.nextLine();
		SocketClient sc = new SocketClient();
		sc.Register(nama, email, password);
		System.out.println("input Berhasil");
		SessionUser su = new SessionUser();
		su.SetId(1);
		System.out.println(su.GetId());
	}
	
	public static void Login() {
		System.out.println("Halaman Login");
		Scanner input = new Scanner(System.in);
		System.out.print("Email :");
		String email = input.nextLine();
		System.out.println("Password :");
		String password = input.nextLine();
		SocketClient sc = new SocketClient();
		sc.Login(email, password);
	}
	
	public static void menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Pilih Menu : ");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.print("Jawab : ");
		int jawabmenu =  input.nextInt();
		if(jawabmenu == 1) {
			Login();
		}else if(jawabmenu == 2) {
			Register();
		}else {
			System.out.println("Input salah");
			menu();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		menu();
		//GUI g = new GUI();
		//g.mainMenu();
	}

}
