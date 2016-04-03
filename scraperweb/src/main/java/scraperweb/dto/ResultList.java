package scraperweb.dto;

import java.io.Serializable;

public class ResultList implements Serializable{

	private static final long serialVersionUID = -1215399993017262426L;

	String result;
	String msg;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
