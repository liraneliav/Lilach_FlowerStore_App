package il.client;

import il.client.events.*;
import il.client.ocsf.AbstractClient;
import il.entities.*;
import org.greenrobot.eventbus.EventBus;


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
			System.out.println("get init data");
			EventBus.getDefault().post(new CatalogItemsEvent(message.getListItem(), message.getListStors()));
		}

		if(message.getMessage().equals("result login")) {
			LoginEvent eventlogIN = null;
			if (!message.isLoginStatus())
				EventBus.getDefault().post(new LoginEvent(false, message.getLoginResult()));
			else {
				if (!message.isWorker()) {//user
					eventlogIN = new LoginEvent(true, message.getUser(), message.getListComplains(), message.getListOrder(), message.getListStors());
					eventlogIN.setId(message.getUser().getId());
				} else {//worker
					switch (message.getPermision()) {
						case 5://system admin
							eventlogIN = new LoginEvent((SystemAdmin) message.getEmployee(), message.getPermision(), message.getListComplains(), message.getListOrder(),
									message.getListStors(), message.getListUsers(), message.getEmployeeslist());
							break;
						case 4://networkmaneger
							eventlogIN = new LoginEvent((NetworkManger) message.getEmployee(), message.getPermision(), message.getListComplains(), message.getListOrder(),
									message.getListStors());
							break;
						case 3://branchManager
							eventlogIN = new LoginEvent((BranchManager) message.getEmployee(), message.getPermision(), message.getListComplains(), message.getListOrder());
							break;
						case 2://CustomerService
							eventlogIN = new LoginEvent((CustomerService) message.getEmployee(), message.getPermision(), message.getListComplains(), message.getListOrder());
							break;
						case 1://StoreEmployee
							eventlogIN = new LoginEvent((StoreEmployee) message.getEmployee(), message.getPermision(), message.getStoreID());
							break;
					}
				}
			}
			EventBus.getDefault().post(eventlogIN);
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
