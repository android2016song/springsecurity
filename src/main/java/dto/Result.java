package dto;

public class Result<T> {
	private boolean success;
	private T data;
	private String error;
	public Result() {
		
	}
	public Result(boolean sucess,T data) {
		this.data=data;
		this.success=sucess;
	}
	public Result(boolean sucess,String error) {
		this.error=error;
		this.success=sucess;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
	
	
}
