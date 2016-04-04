package reourcemodel.iot;

public class TokenStore {
	
	private String clientID;
	
	private String token;
	
	private String serverURL;
	
	
	public String getServerURL() {
		return serverURL;
	}
	
	public String getClientID() {
		return clientID;
	}
	
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
}
