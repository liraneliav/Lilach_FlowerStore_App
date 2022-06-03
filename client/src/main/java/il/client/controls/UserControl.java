package il.client.controls;

import il.entities.Message;

public class UserControl {
    public void setName(int userID, String newUserName, boolean isWorker){
        Message message = new Message("setUserName");
        message.setUsername(newUserName);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
    public void setPassword(int userID, String newPass, boolean isWorker){
        Message message = new Message("setPassword");
        message.setPass(newPass);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
    public void setCreditCard(int userID, String cc, boolean isWorker){
        Message message = new Message("setCreditCard");
        message.setCredit_card(cc);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
    public void setPhone(int userID, String phone, boolean isWorker){
        Message message = new Message("setPhone");
        message.setPhone(phone);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
    public void setAddress(int userID, String address, boolean isWorker){
        Message message = new Message("setAddress");
        message.setUsername(address);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
    public void setMail(int userID, String mail, boolean isWorker){
        Message message = new Message("setMail");
        message.setUsername(mail);
        message.setWorker(isWorker);
        message.setUserID(userID);

    }
}