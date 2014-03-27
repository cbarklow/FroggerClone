package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.sprites.GameSprite;

public class Controls extends GameSprite {
	
	private Vector3 _center;
	
	public Controls(Game game, float x, float y){
		super(game, x, y);
		setSkin("control");
		
		_game.screen.elements.add(this);
		_center = new Vector3(x, y, 0);
	}
	
	public int getDirection(Vector3 p){
		double diffX = p.x - _center.x;
		double diffY = p.y - _center.y;
		
		double rad = Math.atan2(diffY, diffX);
		
		int angle = (int)(180 * rad/ Math.PI);

		//wut..?
		if(angle < 360) angle +=  360;
		if(angle > 360) angle -= 360;
		
		if(angle > 315 || angle < 45){
			Gdx.app.log("CONTROL CLICK:", " RIGHT");
			return Player.MOVE_RIGHT;
		} else if(angle >= 45 && angle <= 135){
			Gdx.app.log("CONTROL CLICK:", " TOP");
			return Player.MOVE_TOP;
		} else if(angle > 135 && angle < 225){
			Gdx.app.log("CONTROL CLICK:", " LEFT");
			return Player.MOVE_LEFT;
		} else {
			Gdx.app.log("CONTROL CLICK:", " DOWN");
			return Player.MOVE_DOWN;
		}		
	}
	
	public Rectangle bounds(){
		return new Rectangle(x, y, width, height);
	}

}
