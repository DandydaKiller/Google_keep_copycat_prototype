
public class SessionUser {
	 String email;
	 String password;
	 String name;
	 int id;
	
	public  void SetPassword(String password) {
		this.password = password;
	}
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public String GetEmail() {
		return this.email;
	}
	
	public int GetId() {
		return this.id;
	}
	
	
}
