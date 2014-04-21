/*
	File: RootChatService.java
	Author: Francisco Ortiz Abril
	Description: this interface defines the methods that
	are needed in the root chat service. 

*/

import java.rmi.*;
import java.util.*;

interface RootChatService extends Remote {
    void chargeServer (ChatService s) throws RemoteException;
    void deleteServer (ChatService s) throws RemoteException;
    List<ChatService> getServers() throws RemoteException;
}
