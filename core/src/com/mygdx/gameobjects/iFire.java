package com.mygdx.gameobjects;

import java.util.List;
import java.util.UUID;

public interface iFire {
	public GameObject fire(double time,List<? extends GameObject> objectList);		
	public GameObject scanForTarget(List<? extends GameObject> objectList);	
}
