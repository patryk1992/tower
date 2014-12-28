package com.mygdx.patterncommand;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;

public class FactoryCommand implements Command{
	private Factory factory;
	private long time;
	
	public FactoryCommand(Factory factory,long time) {
		this.factory = factory;
		this.time=time;
	}

	@Override
	public void execute() {		
		factory.produce(time);
		
	}

	
	
}
