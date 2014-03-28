package com.cbarklow.froggerclone.elements;

import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.sprites.NumberSprite;

public class Score extends NumberSprite {
	
	public Score(Game game, float x, float y, String nameRoot){
		super(game, x, y, nameRoot);
	}
	
	@Override
	public void draw(){
		setValue(_game.gameData.score);
		super.draw();
	}

}
