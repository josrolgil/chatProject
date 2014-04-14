/*
	File: Status.java
	Author: José María Roldán Gil
	Description:
*/


import java.io.*;

class Status implements Serializable{
	private String partner;
	private Boolean busy;

	Status(){
		partner="nobody";
		busy=false;
	}
	public String getPartner(){
		return partner;
	}
	public Boolean getBusy(){
		return busy;
	}
	public void setPartner(String p){
		partner=p;
	}
	public void setBusy(Boolean b){
		busy=b;
	}

}