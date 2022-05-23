package il.client;



import il.client.ocsf.AbstractClient;
import il.entities.Flower;
import il.entities.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	private String lastMessage;

	private ParentClass parentClass = null;

	@Override
	protected void handleMessageFromServer(Object msg) throws IOException {
		Message message = (Message) msg;
		System.out.println("get message from server: "+ message.getMessage());

		if(message.getMessage().equals("item catalog list")){
			ParentClass p = new CatalogController();
			System.out.println("Show flower's catalog!");
			p.setFlowerlist(message.getListItem());
		}

		else if(message.getMessage().equals("result login")){
			if(message.getUser()!=null){
				LoginController.setCorrectLogin(true);
			}
			else{
				LoginController.setCorrectLogin(false);
			}
		}

		else if(message.getMessage().equals("result register")){
			if(message.getMessageDescribe().equals("All data is good")) {
				System.out.println("your register was successful");
//				ParentClass p = new MainPageController();
//				MainPageController.LoadLoginPage();
				ParentClass p = new RegisterController();
				p.registerComplit();
			}
			else
				System.out.println("you register was not successful");
			// need to send message describe to controller then show on screen;
		}

		return;
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient(App.ip, App.port);
		}
		return client;
	}

}
