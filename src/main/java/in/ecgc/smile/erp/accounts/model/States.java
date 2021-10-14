package in.ecgc.smile.erp.accounts.model;

public class States {

	private int stateUtCode;
	private String stateUtName;
	private String StateUtType;
	public int getStateUtCode() {
		return stateUtCode;
	}
	public void setStateUtCode(int stateUtCode) {
		this.stateUtCode = stateUtCode;
	}
	public String getStateUtName() {
		return stateUtName;
	}
	public void setStateUtName(String stateUtName) {
		this.stateUtName = stateUtName;
	}
	public String getStateUtType() {
		return StateUtType;
	}
	public void setStateUtType(String stateUtType) {
		StateUtType = stateUtType;
	}
	@Override
	public String toString() {
		return "States [stateUtCode=" + stateUtCode + ", stateUtName=" + stateUtName + ", StateUtType=" + StateUtType
				+ "]";
	}
	
	public States() {
		super();
	}
	public States(int stateUtCode, String stateUtName, String stateUtType) {
		super();
		this.stateUtCode = stateUtCode;
		this.stateUtName = stateUtName;
		StateUtType = stateUtType;
	}
	
	
	
	
}
