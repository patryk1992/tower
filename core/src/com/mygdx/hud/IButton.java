package com.mygdx.hud;

import java.util.ArrayList;

import com.mygdx.helpers.Input;
import com.mygdx.patternobserver.Observer;
import com.mygdx.patternobserver.Subject;
import com.mygdx.simpleobjects.MyRectangle;

public class IButton extends MyRectangle implements Observer{
	Boolean pressed=false;
	private Subject topic;
	public IButton(float x, float y, int width, int height){
		super(x,y,width,height);
	}
	@Override
	public void update(float clickX,float clickY) {
		if (collides((int)clickX,(int) clickY)) {
			Boolean tmpPressedressed=pressed;
			for(Observer observer:((Input)topic).getObservers()){
				((IButton)observer).pressed=false;
			}
			if (tmpPressedressed == true) {
				pressed=false;
			} else {
				pressed=true;
			}		
		}
	}
	@Override
	public void setSubject(Subject sub) {
		topic=sub;
	}
	public Boolean getPressed() {
		return pressed;
	}
	public void setPressed(Boolean pressed) {
		this.pressed = pressed;
	}
}
