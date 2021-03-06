package ra.jademy.domain;

public class User {
	private String username;
	private String password;
	private String emailAddress;
	private String uuid;
	
	public User (){
		
	}
	public User (String username, String password, String emailAddress){
		this.username=username;
		this.password=password;
		this.emailAddress = emailAddress;
	}
	
	public User (String username, String password, String emailAddress, String uuid){
		this.username=username;
		this.password=password;
		this.emailAddress = emailAddress;
		this.uuid = uuid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getUUID() {
		return uuid;
	}
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

}
