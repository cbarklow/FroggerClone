package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.TierSprite;

public class MysteryTile extends TierSprite {
	
	public static TextureRegion TEXTURE_1;
	public static TextureRegion TEXTURE_2;
	public static TextureRegion TEXTURE_3;
	
	private boolean _animated;
	private float _animationCount;
	
	public MysteryTile(Game game, float x, float y, boolean animated){
		super(game, x, y);
		_animated = animated;
		
		if (MysteryTile.TEXTURE_1 == null) {
			MysteryTile.TEXTURE_1 = ImageCache.getFrame("mysterytile", 1);
			MysteryTile.TEXTURE_2 = ImageCache.getFrame("mysterytile", 2);
			MysteryTile.TEXTURE_3 = ImageCache.getFrame("mysterytile", 3);
		}
		
		setSkin(MysteryTile.TEXTURE_1);
		game.screen.elements.add(this);
		_animationCount = 0f;
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
		
		if(_animated){
			if (_animationCount > 220) {
				skin = MysteryTile.TEXTURE_1;
				_animationCount = 0f;				
			} else if (_animationCount > 180) {
				visible = true;
			} else if (_animationCount > 130) {
				visible = false;
				skin = MysteryTile.TEXTURE_2;
			} else if (_animationCount > 105) {					
				skin = MysteryTile.TEXTURE_3;
			} else if (_animationCount > 80) {
				skin = MysteryTile.TEXTURE_2;
			}
			
			_animationCount += 50 * dt;
		}
	}
	
	@Override
	public Rectangle bounds(){
		if(!visible || skin == MysteryTile.TEXTURE_3)
			return null;
		return super.bounds();
	}
}
