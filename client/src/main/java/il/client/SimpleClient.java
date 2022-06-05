package il.client;

import il.client.events.*;
import il.client.ocsf.AbstractClient;
import il.entities.Product;
import il.entities.Message;
import org.greenrobot.eventbus.EventBus;

import java.util.*;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg){
		Message message = (Message) msg;
		System.out.println("get message from server: "+ message.getMessage());


		if(message.getMessage().equals("item catalog list")){
			System.out.println("get Flower object!");
			List<Product> items = message.getListItem();
			EventBus.getDefault().post(new CatalogItemsEvent(items));
		}

		if(message.getMessage().equals("AllOpenComplaints")){
			System.out.println("All Open Complaints are here!");
			EventBus.getDefault().post(new ComplaintEvent(message.getListComplains()));
		}

		if(message.getMessage().equals("AllOpenComplaintsForUser")){
			System.out.println("All Open Complaints For User are here!");
			EventBus.getDefault().post(new ComplaintEvent(message.getListComplains()));
		}

		if(message.getMessage().equals("reportData")){
			System.out.println("report data for store"+message.getStoreID()+" are here!");
			EventBus.getDefault().post(new ReportEvent(message.getListComplains(), message.getListOrder()));
		}

		if(message.getMessage().equals("result login")){
			LoginEvent eventlogIN = null;
			if(!message.isLoginStatus())
				EventBus.getDefault().post(new LoginEvent(false, message.getLoginResult()));
			else{
				if(!message.isWorker()){//user
					eventlogIN = new LoginEvent(true, message.getUser(), message.getListComplains(), message.getListOrder(), message.getListStors());
					eventlogIN.setId(message.getUser().getId());
					if(message.getPermision()==3 || message.getPermision()==5)
						eventlogIN.setStoreId(message.getStoreID());
				}
				else{//worker
					eventlogIN = new LoginEvent(message.getUsername(), message.getPermision());
					eventlogIN.setId(message.getIddatabase());
//					eventlogIN.setOrderList(message.getListOrder());
//					eventlogIN.setComplainList(message.getListComplains());
				}
				EventBus.getDefault().post(eventlogIN);
			}
		}

		if(message.getMessage().equals("result register")){
			EventBus.getDefault().post(new RegisterEvent(message.isRegisterStatus(), message.getRegisterResult()));
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient(App.ip, App.port);
		}
		return client;
	}

}
