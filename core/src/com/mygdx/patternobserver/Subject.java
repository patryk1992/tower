package com.mygdx.patternobserver;

import java.util.ArrayList;

public interface Subject {	
    public void register(Observer obj);
    public void unregister(Observer obj);    
    public void notifyObservers(int screenX, int screenY);  
}
