package com.cbarklow.froggerclone.sprites;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;

public class NumberSprite extends GameSprite {
	
	private int value;
	private List<TextureRegion> _textures;
	private List<Sprite> _numbers;
	
	public NumberSprite(Game game, float x, float y, String nameRoot){
		super(game, x, y);
		
		skin = null;
		value = 0;
		_textures = new ArrayList<TextureRegion>();
		_numbers = new ArrayList<Sprite>();
		
		Sprite sprite;
		for(int i = 0; i < 10; i++){
			_textures.add(ImageCache.getFrame(nameRoot, i));
			
            //create Sprite with TextureRegion  
            sprite = new Sprite(_textures.get(0));  
            sprite.setPosition(x + i * (sprite.getRegionWidth() + 2), y);  
            _numbers.add(sprite);  
		}
		
		_game.screen.elements.add(this);
	}
	
	public void setValue(int newValue){
		value = newValue;
	}
	
	@Override
	public void draw(){
		String string = value + "";
		int len = string.length();
		if(len > 10) return;
		Sprite sprite;
		for(int i = 0; i < len; i++){
			sprite = _numbers.get(i);
			//this is funky so that it works on html5
			int index = (int)string.charAt(i) - 48;
			sprite.setRegion(_textures.get(index));
			sprite.draw(_game.spriteBatch);
		}
	}

}
