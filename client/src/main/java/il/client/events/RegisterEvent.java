package il.client.events;

import il.entities.Message;
import il.entities.Store;

import java.util.LinkedList;

public class RegisterEvent {
    private boolean statusRegister;
    private String result;
    private LinkedList<Store> storesList;

    public boolean isStatusRegister() {
        return statusRegister;
    }

    public void setStatusRegister(boolean statusRegister) {
        this.statusRegister = statusRegister;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LinkedList<Store> getStoresList() {
        return storesList;
    }

    public void setStoresList(LinkedList<Store> storesList) {
        this.storesList = storesList;
    }

    public RegisterEvent(boolean statusRegister, String result) {
        this.statusRegister = statusRegister;
        this.result = result;
    }

    public RegisterEvent(LinkedList<Store> storesList) {
        this.storesList=storesList;
    }

}
