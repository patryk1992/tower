package com.mygdx.patternobserver;

import java.util.ArrayList;

public interface Observer {
	 //method to update the observer, used by subject
    public void update(float clickX,float clickY);
     
    //attach with subject to observe
    public void setSubject(Subject sub);
}
