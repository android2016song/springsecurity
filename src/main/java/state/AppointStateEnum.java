package state;

public enum AppointStateEnum {
	SUCESS(1, "预约成功"), NO_NUMBER(0, "库存不足"),RE_PEAT(-1,"重复预约"),INNER_ERROR(-2,"内部异常");

	AppointStateEnum(int arg0, String arg1) {
		 
		this.state =arg0;
		this.stateInfo =arg1;
	}
	private int state;
	private String stateInfo;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	public static AppointStateEnum stateof(int index) {
		for(AppointStateEnum state:  values()) {
			if(state.getState()==index) {
				return state;
			}
			 
		}
		return null;
	}
	
}
