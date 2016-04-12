package reourcemodel.iot;

public class ClientMain {

	private ID _id;
	private String serialID;
	private String bootstrapURL;
	public ID get_id() {
		return _id;
	}
	public void set_id(ID _id) {
		this._id = _id;
	}
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public String getBootstrapURL() {
		return bootstrapURL;
	}
	public void setBootstrapURL(String bootstrapURL) {
		this.bootstrapURL = bootstrapURL;
	}

}
