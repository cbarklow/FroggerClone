package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.TierSprite;

public class Dragon extends TierSprite {
	

	public static TextureRegion TEXTURE_1;
	public static TextureRegion TEXTURE_2;
	
	private float _animationCnt;
	
	public Dragon(Game game, float x, float y) {
		
		super(game, x, y);
		
		_animationCnt = 0f;
		
		if (Dragon.TEXTURE_1 == null) {
			Dragon.TEXTURE_1 = ImageCache.getFrame("dragon", 1);
			Dragon.TEXTURE_2 = ImageCache.getFrame("dragon", 2);
		}
		
		setSkin(Dragon.TEXTURE_1);
		body = new Rectangle (0, 0, width, height);
		game.screen.elements.add(this);
	}
	
	@Override
	public void update (float dt) {
		
		super.update(dt);
		
		if (_animationCnt > 120) {
			skin = Dragon.TEXTURE_1;
			body.width = width;
			_animationCnt = 0f;
		} else if (_animationCnt > 60) {
			skin = Dragon.TEXTURE_2;
			body.width = width*0.4f;
		}
		_animationCnt += 20 * dt;
		
	}
	
	@Override
	public Rectangle bounds () {
		body.x = x - body.width*0.5f;
		body.y = y - body.height*0.5f;
		return body;
	}
}
