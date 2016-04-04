package resourcemodel.iot;

public class ServerDetails {

	private ID _id;
	
	private String shortServer;
	
	private String lifeTime;
	
	private String minPeriod;
	
	private String maxPeriod;
	
	private String disable;
	
	private String disableTimeout;
	
	private String notification;
	
	private String binding;
	
	private String registration;

	public String getShortServer() {
		return shortServer;
	}

	public void setShortServer(String shortServer) {
		this.shortServer = shortServer;
	}

	public String getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

	public String getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(String minPeriod) {
		this.minPeriod = minPeriod;
	}

	public String getMaxPeriod() {
		return maxPeriod;
	}

	public void setMaxPeriod(String maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getDisableTimeout() {
		return disableTimeout;
	}

	public void setDisableTimeout(String disableTimeout) {
		this.disableTimeout = disableTimeout;
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

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public ID get_id() {
		return _id;
	}

	public void set_id(ID _id) {
		this._id = _id;
	}
	
}
