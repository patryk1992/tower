package com.mygdx.gameobjects;

import java.util.ArrayList;

public class Factory extends Building {
	int tankNumber;
	long storedTime;
	long produceTime;
	long maxTank;
	public Factory(){
		
	}
	public Factory(float x, float y, int width, int height ,int idGroup, String id,long produceTime,long maxTank){
		super(x,y,width,height,idGroup,id);
		tankNumber=0;
		storedTime=0;
		this.produceTime=produceTime;
		this.maxTank=maxTank;
	}
	public void produce(long time){
		long timeDifference=time-storedTime;
		if(timeDifference>produceTime&&tankNumber<maxTank){
			tankNumber++;
			storedTime=time;
		}
		
	}
	public int getTankNumber() {
		return tankNumber;
	}
	
}