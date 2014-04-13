/*
	File: ChatServer.java
	Author: José María Roldán Gil
	Description:
*/
	import java.rmi.*;
	import java.rmi.server.*;

	class ChatServer {
		static public void main (String args[]){
			if (args.length!=1){
				System.err.println("Use: ChatServer RegisterPortNumber");
				return;
			}
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new RMISecurityManager());
			try	{
				System.out.println("Loading.......................Ready!");
				ChatServiceImpl srv = new ChatServiceImpl();
				Naming.rebind("rmi://localhost:" + args[0] + "/Chat", srv);


			} catch (RemoteException e) {
            	System.err.println("Communication Error: " + e.toString());
            	System.exit(1);
        	}
        		catch (Exception e) {
            	System.err.println("Excepction in ChatServer:");
            	e.printStackTrace();
            	System.exit(1);
        	}		
		}
	}