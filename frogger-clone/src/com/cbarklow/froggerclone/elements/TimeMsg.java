package com.cbarklow.froggerclone.elements;

import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.GameSprite;
import com.cbarklow.froggerclone.sprites.NumberSprite;

public class TimeMsg extends GameSprite {
	
	public NumberSprite timeLabel;
	
	public TimeMsg(Game game, float x, float y){
		super(game, x, y);
		setSkin(ImageCache.getTexture("timelabel1"));
		
		_game.screen.elements.add(this);
		
		timeLabel = new NumberSprite(_game, x + width * 0.1f, y - height * 0.3f, "redsmall");
		timeLabel.visible = false;;
	}
	
	@Override
	public void show(){
		visible = true;
		timeLabel.visible = true;
	}
	
	@Override
	public void hide(){
		visible = false;
		timeLabel.visible = false;
	}

}
