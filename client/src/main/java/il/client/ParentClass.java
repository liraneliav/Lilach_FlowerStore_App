package il.client;

import il.client.DiffClasses.Priority;
import il.entities.Flower;

import java.io.IOException;
import java.util.List;

public abstract  class ParentClass {
    static Priority priority;
    ParentClass(){
    }
    void init_priority(){
        priority = new Priority();
        priority.setPriority_level(1);
    }
    protected void setFlowerlist(List<Flower> flowerlist1) throws IOException {}

    protected void registerComplit() throws IOException {}

    public void LoadLoginPage() throws IOException {}
}