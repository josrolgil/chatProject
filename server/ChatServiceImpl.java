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
	//Estado: terminado
	ChatServiceImpl() throws RemoteException{
		l = new LinkedList<Client>();
	}
	//Estado: terminado
	public void chargeUser(Client c) throws RemoteException{
		l.add(c);
	}
	//Estado=terminado
	public void deleteUser(Client c) throws RemoteException{
		l.remove(l.indexOf(c));
	}
	//Estado: terminado
	public List<Client> getUsers() throws RemoteException{
		return l;
	}
	public int validateNick(String n) throws RemoteException{
		int error=1;
		for (Client c:l)
			if(c.getNickname().equals(n))
				error=0;
		return error;	
	}
	//Estado: pendiente
	public Client connectToUser(String nick, Client init) throws RemoteException{
		/*   METODO ANTIGUO
		Client aux = null;
		l.get(indexOf(init)).setBusy(true);
		for (Client c:l)			
			if(c.getNickname().equals(nick) && c.getBusy()==false){
				aux=c;
				System.out.println("Levantamos el candado");
				c.setBusy(true);
				c.message(init.getNickname()+" wants to talk with you!");
				c.message("Please get connect");
				}
		if(aux==null)
			l.get(indexOf(init)).setBusy(false);	
		return aux; */

		Client aux=null;
		System.out.println("Parámetros:" + nick + " "+init.getNickname());
		//l.get(l.indexOf(init)).setFriend(nick);
		init.setFriend(nick);
		for(Client c:l){
			System.out.println("Este cliente es:"+c.getNickname()+" y se busca a "+nick);
			System.out.println("Este cliente está hablandon con:"+c.getFriend());
			if(c.getNickname().equals(nick) && (c.getFriend().equals("") ||c.getFriend().equals(init.getNickname()))){
				System.out.println("Levantamos el candado");
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
				System.out.println("ENVIANDOOO");
				c.message(mssg);
			}
		}
		
	}
}