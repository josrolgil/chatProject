/*
	File: Client.java
	Author: José María Roldán Gil
	Description:
*/

	import java.rmi.*;
	import java.rmi.server.*;

	class ClientImpl extends UnicastRemoteObject implements Client{
		String nickname;
		String friend;
		Boolean busy;
		ClientImpl() throws	 RemoteException{
			super();
			nickname="";
			friend="";
			busy=false;
		}
		public void message(String mssg) throws RemoteException{
			System.out.println("\n"+nickname+": "+mssg);
		}
		public void sendComment(String n,String m) throws RemoteException{
			System.out.println("->"+n+" says: "+m);
		}
		public void setNickname(String n) throws RemoteException{
			nickname=n;
		}
		public String getNickname() throws RemoteException{
			return nickname;
		}
		public void setBusy(Boolean b) throws RemoteException{
			busy=b;
		}
		public Boolean getBusy() throws RemoteException{
			return busy;
		}
		public String getFriend() throws RemoteException{
			return friend;
		}
		public void setFriend(String f) throws RemoteException{
			friend=f;
		}
	}