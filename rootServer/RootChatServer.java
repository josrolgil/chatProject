/*
	File: RootChatServer.java
	Author: Francisco Ortiz Abril
	Description:  
*/

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class RootChatServer {
    static public void main (String args[]){
       if(args.length != 1){
          System.err.println("Use: RootCharServer RegisterPortNumber");
          return;
       }
       
       if(System.getSecurityManager() == null)
          System.setSecurityManager(new RMISecurityManager());
       try{
          System.out.println("Loading................Ready!");
          RootChatServiceImpl srv = new RootChatServiceImpl();
          Naming.rebind("rmi://localhost:" + args[0] + "/RootChat", srv);
       }catch (RemoteException e){
          System.err.println("Communication Error: " + e.toString());
          System.exit(1);
       }catch (Exception e){
          System.err.println("Exception in RootChatServer: ");
          e.printStackTrace();
          System.exit(1);
       }
    }
}
          
