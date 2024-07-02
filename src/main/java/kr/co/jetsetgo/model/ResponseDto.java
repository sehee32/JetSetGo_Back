package kr.co.jetsetgo.model;

public class ResponseDto<T> {

	private String code		= "S";
	private String msg;
	private String detailMsg;
	private T data;
	
	public ResponseDto() {
		
	}

	public ResponseDto(T o) {
		this.data	= o;
	}
	
	public ResponseDto(Exception e) {

	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDetailMsg() {
		return detailMsg;
	}
	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}