/*
	File: ChatServiceImpl.java
	Author: Jósé María Roldán Gil
	Description:
*/
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
	List<Client> l;
	String id;
	RootChatService rootService;
	//Estado: terminado
	
	/*ChatServiceImpl() throws RemoteException{
	  id = "";
	}
	*/
	ChatServiceImpl(RootChatService srv) throws RemoteException{
		rootService = srv;
		l = new LinkedList<Client>();
		id = "";
	}
	//Estado: terminado
	public void chargeUser(Client c) throws RemoteException{
	  rootService.addUser(c.getNickname(),id);
		l.add(c);
	}
	//Estado=terminado
	public void deleteUser(Client c) throws RemoteException{
		l.remove(l.indexOf(c));
	}
	//Estado: terminado
	public List<String> getUsers() throws RemoteException{
	  List<String> names = new LinkedList<String>();
	  names = rootService.getNames(); 
		return names;
	}
	
	public void setId(String n) throws RemoteException{
	  id = n;
	}
	
	public String getId() throws RemoteException{
	  return id;
	}
	
	public int validateNick(String n) throws RemoteException{
		int error=1;
		List<String> names = new LinkedList<String>();
		names = rootService.getNames();
		for (String temp:names)
			if(temp.equals(n))
				error=0;
		return error;	
	}
	//Estado: pendiente
	public Client connectToUser(String nick, Client init) throws RemoteException{
		Client aux=null;
		init.setFriend(nick);
		boolean flag = true;
		for(Client c:l){
			if(c.getNickname().equals(nick) && (c.getFriend().equals("") ||c.getFriend().equals(init.getNickname()))){
			  flag = false;
				c.message("!!!!!! "+init.getNickname()+" wants to talk with you!");
				init.message("Waiting for response");
				while(c.getFriend().equals(""));
				if(c.getFriend().equals(init.getNickname())){
					init.message("Response OK!");
					System.out.println("Introduce exit to disconnect.");
					aux=c;
				}else{
					init.setFriend("");
					init.message("There was not a response");
				}
			}
		}
		if (flag)
		{
		  ChatService serverTemp = rootService.getServiceOfClient(nick);
		  aux = serverTemp.connectToUser(nick,init);
		}
		
		return aux;	
	}
	
	public void disconnect(Client friend, Client init) throws RemoteException{
	l.get(l.indexOf(init)).setFriend("");
	friend.message("The partner has finished the session. Introduce 'exit' to finish too.");
	}
	//Estado: incompleto
	public void send(String n, String mssg) throws RemoteException{
		for(Client c:l){
			if(c.getNickname().equals(n)){
				c.message(mssg);
			}
		}
		
	}
}
