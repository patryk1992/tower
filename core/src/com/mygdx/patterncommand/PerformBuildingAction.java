package com.mygdx.patterncommand;
/*
 * Invoker class
 */
public class PerformBuildingAction {
	Command cmd;
	public void Execute(Command cmd) {	      
	      cmd.execute();
	   }
}
