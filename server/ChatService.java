/*
	File: ChatService.java
	Author: José María Roldán Gil
	Description: this interface defines the methods that
	are needed in the chat service. 

*/
import java.rmi.*;
import java.util.*;

interface ChatService extends Remote {
		void chargeUser (Client c) throws RemoteException;
		void deleteUser (Client c) throws RemoteException;
		List<Client> getUsers() throws RemoteException;
		int validateNick(String n) throws RemoteException;
		Client connectToUser(String nick, Client init) throws RemoteException;
		void disconnect(Client friend, Client init) throws RemoteException;
		void send (String c, String mssg) throws RemoteException;
		void setId(String n) throws RemoteException;
		public String getId() throws RemoteException;
}
