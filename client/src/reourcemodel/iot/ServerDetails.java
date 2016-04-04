package reourcemodel.iot;

public class ServerDetails {
	
	private int shortServerID;
	
	private int lifetime;
	
	private String notification;
	
	private String binding;
	
	public int getShortServerID() {
		return shortServerID;
	}
	
	public void setShortServerID(int shortServerID) {
		this.shortServerID = shortServerID;
	}
	
	public int getLifetime() {
		return lifetime;
	}
	
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	
	public String getNotification() {
		return notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	public String getBinding() {
		return binding;
	}
	
	public void setBinding(String binding) {
		this.binding = binding;
	}
	
}
