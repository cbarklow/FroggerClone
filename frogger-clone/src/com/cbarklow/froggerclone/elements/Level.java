package com.cbarklow.froggerclone.elements;

import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.sprites.NumberSprite;

public class Level extends NumberSprite {
	
	public Level(Game game, float x, float y, String nameRoot){
		super(game, x, y, nameRoot);
	}
	
	@Override
	public void draw(){
		value = _game.gameData.level;
		super.draw();
	}

}
