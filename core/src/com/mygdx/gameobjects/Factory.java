package com.mygdx.gameobjects;

import java.util.ArrayList;

public class Factory extends Building   {
	int tankNumber;	
	long maxTank;
	public Factory(){
		
	}
	
	public Factory(float x, float y, int width, int height ,int idGroup, String id,long produceTime,long maxTank){
		super(x,y,width,height,produceTime,idGroup,id,10);
		tankNumber=0;
		this.maxTank=maxTank;
	}
	public void produce(double time){
		double timeDifference=time-storedTime;
		if(timeDifference>produceTime&&tankNumber<maxTank){
			tankNumber++;
			storedTime=time;
		}
		
	}
	public int getTankNumber() {
		return tankNumber;
	}
	public void setTankNumber(int tankNumber) {
		this.tankNumber = tankNumber;
	}
	
	public void setStoredTime(double d) {
		this.storedTime = d;
	}
	
}
