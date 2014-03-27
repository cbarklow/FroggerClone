package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.TierSprite;

public class Monster extends TierSprite {
	
	private TextureRegion _texture_1;
	private TextureRegion _texture_2;
	
	private float _animationCount;
	
	public Monster(String skinName, Game game, float x, float y){
		super(game, x, y);
		
		this._game = game;
		active = true;
		visible = true;
		
		_animationCount = 0f;
		
		if (_texture_1 == null) {
			_texture_1 = ImageCache.getFrame(skinName, 1);
			_texture_2 = ImageCache.getFrame(skinName, 2);
		}
		
		setSkin(_texture_1);			
		game.screen.elements.add(this);
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
		
		if (_animationCount > 10) {
			skin = _texture_1;			
			_animationCount = 0f;
		} else if (_animationCount > 5) {
			skin = _texture_2;			
		}
		_animationCount += 20 * dt;
	}

}
