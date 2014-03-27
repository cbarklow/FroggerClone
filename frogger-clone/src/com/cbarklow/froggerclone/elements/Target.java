package com.cbarklow.froggerclone.elements;

import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.sprites.TierSprite;

public class Target extends TierSprite {

	public static final int TARGET = 0;
	public static final int TOME = 1;
	public static final int GHOST = 2;
	public static final int BONUS_200 = 3;
	public static final int BONUS_400 = 4;

	public int type;

	public Target(Game game, float x, float y, int type) {
		
		super(game, x, y);
		this.type = type;
		
		switch (type) {
			case Target.TARGET:
				setSkin(ImageCache.getTexture("goal"));
				break;
			case Target.TOME:
				setSkin(ImageCache.getTexture("tome"));
				break;
			case Target.GHOST:
				setSkin(ImageCache.getTexture("ghost"));
				break;
			case Target.BONUS_200:
				setSkin(ImageCache.getFrame("label_", 200));
				break;
			case Target.BONUS_400:
				setSkin(ImageCache.getFrame("label_", 400));
				break;
		}
		
		visible = false;
		_game.screen.elements.add(this);
	}
}
