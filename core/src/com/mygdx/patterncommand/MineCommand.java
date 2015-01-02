package com.mygdx.patterncommand;


import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;

public class MineCommand implements Command {
	private Mine mine;
	private double time;
	private Base base;
	public MineCommand(Mine mine,double elapsed,Base base) {
		this.mine = mine;
		this.time=elapsed;
		this.base=base;
	}
	@Override
	public void execute() {
		base.addCoins(mine.extract(time));
	}

}
