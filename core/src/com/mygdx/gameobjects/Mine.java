package com.mygdx.gameobjects;

public class Mine extends Building{
	int coinConstant=25;
	public Mine(){
		
	}
	public Mine(float x, float y, int width, int height ,long producedTime,int idGroup, String id){
		super(x,y,width,height,producedTime,idGroup,id);
	}
	public int extract(long time) {
		long timeDifference=time-storedTime;
		if(timeDifference>produceTime){
			storedTime=time;
			return coinConstant;
		}
		return 0;
	}
}
