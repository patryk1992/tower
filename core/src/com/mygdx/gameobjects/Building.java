package com.mygdx.gameobjects;


public  class Building extends GameObject{
	
	double storedTime;
	long produceTime;
	int bulletsCanTake;
	public Building(float x, float y, int width, int height ,long producedTime,int idGroup, String id,int bulletsCanTake){
		super(x,y,width,height,idGroup,id);
		this.produceTime=producedTime;
		storedTime=0;
		this.bulletsCanTake=bulletsCanTake;
	}

	public Building() {
		// TODO Auto-generated constructor stub
	}
	public int getBulletsCanTake() {
		return bulletsCanTake;
	}
	public int shooted() {
		return bulletsCanTake--;
	}
	
}
