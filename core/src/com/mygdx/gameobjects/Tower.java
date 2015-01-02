package com.mygdx.gameobjects;

import java.util.List;
import java.util.UUID;


public class Tower extends Building implements iFire{
	public Tower(){
		
	}
	public Tower(float x, float y, int width, int height ,long producedTime,int idGroup, String id){
		super(x,y,width,height, producedTime,idGroup,id,10);
	}
	@Override
	public GameObject fire(double time, List<? extends GameObject> objectList) {
		double timeDifference=time-storedTime;
		GameObject targetBuilding= scanForTarget(objectList);
		if(timeDifference>produceTime&&targetBuilding!=null){			
			storedTime=time;
			return new Bullet(getDimension().position.x, getDimension().position.y, 5, getIdGroup(),UUID.randomUUID().toString(),targetBuilding);
			
		}
		return null;
	}
	@Override
	public GameObject scanForTarget(List<? extends GameObject> objectList) {
		for(GameObject building:objectList){
			if(this.getDimension().getPosition().dst(building.getDimension().getPosition())<150){
				return building;
			}
		}
		return null;
	}
	
}
