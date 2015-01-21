package com.mygdx.patternflyweight;

import com.badlogic.gdx.math.Vector2;

public class PlaneModel {
	private float velocityConstant;
	private long produceTime;
	public PlaneModel(float velocityConstant, long produceTime) {
		this.velocityConstant = velocityConstant;
		this.produceTime = produceTime;
	}
	public PlaneModel(){		
	}
	public float getVelocityConstant() {
		return velocityConstant;
	}
	public long getProduceTime() {
		return produceTime;
	}
}
