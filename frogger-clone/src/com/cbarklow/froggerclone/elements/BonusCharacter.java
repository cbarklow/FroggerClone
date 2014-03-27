package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.MovingSprite;
import com.cbarklow.froggerclone.sprites.TierSprite;

public class BonusCharacter extends MovingSprite {
	
	public TierSprite floor;
	
	private TextureRegion _stand;	
	private Player _hero;	
	private float _xMove;	
	
	public BonusCharacter(Game game, float x, float y, Player hero) {
		
		super(game, x, y);
		
		_hero = hero;
		_stand = ImageCache.getTexture("dead_kid");
		
		floor = null;
		setSkin (_stand);
		
		visible = false;

		_game.screen.elements.add(this);
		body = new Rectangle (0, 0, width*0.5f, height * 0.5f);
			
	}
	
	@Override 
	public void reset () {
		visible = false;		
	}
	
	@Override
	public void update (float dt) {
		
		if (floor == null) return;
		
		if (visible) {
			if (_hero.hasBonus) {
				nextX = _hero.nextX;
				nextY = _hero.nextY;
			} else {
				nextX = floor.nextX + _xMove;
				nextY = floor.nextY;				
			}
		} else {
			//check if log is out of bounds
			if (floor.left() < 0) {
				nextX = floor.nextX;
				nextY = floor.nextY;
				visible = true;
			}
		}
	}
	
	@Override
	public Rectangle bounds () {
		if (!visible) return null;
		body.x = x + width*0.2f;
		body.y = y + height*0.2f;
		return body;
	}


}
