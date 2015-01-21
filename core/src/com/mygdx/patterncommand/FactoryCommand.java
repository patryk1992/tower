package com.mygdx.patterncommand;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;

public class FactoryCommand implements Command{
	private Factory factory;
	private double time;
	public FactoryCommand(Factory factory,double elapsed) {
		this.factory = factory;
		this.time=elapsed;
	}
	@Override
	public void execute() {		
		factory.produce(time);
		
	}
}
