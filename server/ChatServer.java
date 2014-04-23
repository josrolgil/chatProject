/*
	File: ChatServer.java
	Author: José María Roldán Gil
	Description:
*/
	import java.rmi.*;
	import java.rmi.server.*;
	import java.util.*;
	
	class ChatServer {
		static public void main (String args[]){
		  List<String> names;
		  List<Client> lista;
		  String id = "";
			if (args.length!=3){
				System.err.println("Use: ChatServer RegisterHost RegisterPortNumber1 RegisterPortNumber2");
				return;
			}
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new RMISecurityManager());
			try	{
				System.out.println("Loading.......................Ready!");
				//Cliente del servicio RootChat
				RootChatService srv = (RootChatService) Naming.lookup("//" + args[0] + ":" + args[1] + "/RootChat");
				
				ChatServiceImpl c = new ChatServiceImpl(srv);
				//Servidor del servicio Chat
				Naming.rebind("rmi://localhost:" + args[2] + "/Chat", c);
				Scanner sc = new Scanner(System.in);
				//int flag = 0;
				//while (flag == 0){
				  System.out.println("Introduce server id: ");
				  id = sc.nextLine();
				  //flag = srv.validateId(id);
				//}
				c.setId(id); 
				srv.chargeServer(c,id);
        names = srv.getServers();
        showServers(names);
    
        //srv.deleteServer(c);
                
			} catch (RemoteException e) {
            	System.err.println("Communication Error: " + e.toString());
            	System.exit(1);
        	}
        		catch (Exception e) {
            	System.err.println("Exception in ChatServer:");
            	e.printStackTrace();
            	System.exit(1);
        	}		
		}
		
		public static void showServers(List<String> l){
		int index;
		String u;
		System.out.println("Online servers:");
		for(index=0;index <l.size(); index++){
			u=l.get(index);
			System.out.println(index+"# "+ u);
		}
		System.out.println("-----------------------------------------");
		
		}

	}
	
