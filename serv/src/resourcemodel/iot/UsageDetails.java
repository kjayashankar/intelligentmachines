package resourcemodel.iot;

public class UsageDetails {

	private String clientID;
	
	private int requests;
	
	private int responses;
	
	private boolean hasUpgraded;

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public int getResponses() {
		return responses;
	}

	public void setResponses(int responses) {
		this.responses = responses;
	}

	public boolean isHasUpgraded() {
		return hasUpgraded;
	}

	public void setHasUpgraded(boolean hasUpgraded) {
		this.hasUpgraded = hasUpgraded;
	}
	
	
}
