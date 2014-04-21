/*
	File: ChatClient.java
	Author: José María Roldán Gil
	Description:
*/

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ChatClient{
	static public void main (String args[]){
		//////////////////////////////////////////////////////////////////////////
		int flag=0;
		String nn=""; //Nickname
		String friend="";
		String mssg="";
		List<Client> l;
		Client partner;
		//////////////////////////////////////////////////////////////////////////
		if (args.length!=2){
			System.err.println("Use: ChatClient registerHost registerPortNumber");
			return;
		}
		if(System.getSecurityManager()==null)
			System.setSecurityManager(new SecurityManager());
		try{
      ChatService srv = (ChatService) Naming.lookup("//" + args[0] + ":" + args[1] + "/Chat");
			ClientImpl c = new ClientImpl();
			System.out.println("Starting Client.................Ready!");
			System.out.println("'exit' to close");
			Scanner sc = new Scanner(System.in);
			while(flag==0){
				System.out.println("Introduce your nickname:");
				nn=sc.nextLine();
				flag=srv.validateNick(nn);
			}
			c.setNickname(nn);			
			srv.chargeUser(c);
			l=srv.getUsers();
			showUsers(l);
			while(!friend.equals("exit")){
				System.out.println("Introduce the user you want to chat:");
				l=srv.getUsers();
				showUsers(l);
				friend=sc.nextLine();
				System.out.println("Se ha seleccionado: " +friend);
				partner=srv.connectToUser(friend,c);  //¿Cómo hago para llamar a connectToServer solo cuando aqui no se encuentre?
				while(partner!=null  && !mssg.equals("exit") && !friend.equals("exit")){
					mssg=sc.nextLine();
              		partner.sendComment(c.getNickname(),mssg);
              		if(mssg.equals("exit"))
              			srv.disconnect(partner,c);
              	}
            }
			srv.deleteUser(c);
			System.exit(0);
		}catch (RemoteException e) {
            System.err.println("Communication Error: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Exception  in ChatClient:");
            e.printStackTrace();
        }
	}
    public static void showUsers(List<Client> l){
		int index;
		Client u;
		try{
		System.out.println("Online nicknames:");
		for(index=0;index <l.size(); index++){
			u=l.get(index);
			System.out.println(index+"# "+u.getNickname());
		}
		System.out.println("-----------------------------------------");
		}catch (RemoteException e){
			System.err.println("Communication Error: " + e.toString());
		}

	}
}
