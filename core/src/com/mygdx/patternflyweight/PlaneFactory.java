package com.mygdx.patternflyweight;


public class PlaneFactory {
	private static PlaneModel PLANEMODEL;
	
	public static PlaneModel getPlaneModel(){
		if(PLANEMODEL==null){
			PLANEMODEL=new PlaneModel(1,2000);
		}
		return PLANEMODEL;
	}
}
