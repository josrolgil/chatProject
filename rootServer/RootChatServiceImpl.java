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
    
}
