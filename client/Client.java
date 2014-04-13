/*
	File: Client.java	
	Author: José María Roldán Gil
	Description: this is the interface for the client of Server Chat
*/
import java.rmi.*;

interface Client extends Remote{
	//Estado: incompleto
	void message(String mssg) throws RemoteException;
	void sendComment(String n,String m) throws RemoteException;
	String getNickname() throws RemoteException;
	void setNickname(String n) throws RemoteException;
	void setBusy(Boolean b) throws RemoteException;
	Boolean getBusy() throws RemoteException;
	public String getFriend() throws RemoteException;
	public void setFriend(String f) throws RemoteException;
}