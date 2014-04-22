/*
	File: RootChatServiceImpl.java
	Author: Francisco Ortiz Abril
	Description: this file implements the methods that
	are needed in the chat service. 

*/
import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

class RootChatServiceImpl extends UnicastRemoteObject implements RootChatService {
    List<ChatService> l;
    RootChatServiceImpl() throws RemoteException{
       l = new LinkedList<ChatService>();
    }
    
    public void chargeServer(ChatService s) throws RemoteException{
       l.add(s);
    }
    
    public void deleteServer(ChatService s) throws RemoteException{
       l.remove(l.indexOf(s));
    }
    
    public List<ChatService> getServers() throws RemoteException{
       return l;
    }
    
    //Estado: devolverá un 1 si un cliente se encuentra en la lista de otro Server. Recorrerá todas las listas de los 
    //servidores parándose en la primera coincidencia.
    public int connectToServer() throws RemoteException{
      int resul = 0;  
      
      return resul;
    }
    
    public int validateId(String n) throws RemoteException{
      int error = 1;
      for (ChatService s:l)
         if(s.getId().equals(n))
            error = 0;
      return error;
    }
    
}
