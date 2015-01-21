package com.mygdx.patternobserver;

import java.util.ArrayList;

public interface Observer {
    public void update(float clickX,float clickY);
    public void setSubject(Subject sub);
}
