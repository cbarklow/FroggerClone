package com.cbarklow.froggerclone.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.cbarklow.froggerclone.Game;
import com.cbarklow.froggerclone.data.GameData;
import com.cbarklow.froggerclone.data.ImageCache;
import com.cbarklow.froggerclone.data.Sounds;
import com.cbarklow.froggerclone.screens.GameScreen;
import com.cbarklow.froggerclone.sprites.Animation;
import com.cbarklow.froggerclone.sprites.Animation.AnimationEvent;
import com.cbarklow.froggerclone.sprites.Animation.AnimationEventListener;
import com.cbarklow.froggerclone.sprites.MovingSprite;

public class Player extends MovingSprite implements AnimationEventListener {


	public static final int MOVE_TOP = 0;
	public static final int MOVE_DOWN = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_RIGHT = 3;
	
	
	public boolean hasBonus = false;
	public float tierSpeed = 0;
	public boolean dead = false;
	public boolean moving = false;
	
	public int tierIndex = 0;
	
	private TextureRegion _front;
	private TextureRegion _side;
	private TextureRegion _frontJump;
	private TextureRegion _sideJump;
	private TextureRegion _down;
	private TextureRegion _downJump;
	private TextureRegion _restFrame;

	private Animation _deathAnimation;
	private float _animationTime;
	
	private int _sideStep = 22;
	private Vector3 _startPoint;
	private Sprite _sprite;
	private float _moveCnt = 0.0f;
	private int _moveInterval = 6;
	
	public Player(Game game, float x, float y) {
		
		super(game, x, y);
		
		tierSpeed = 0.0f;
		
		//store textures for player
		_front = ImageCache.getTexture("hero_front");
		_frontJump = ImageCache.getTexture("hero_front_jump");
		_side = ImageCache.getTexture("hero_side");
		_sideJump = ImageCache.getTexture("hero_side_jump");
		_down = ImageCache.getTexture("hero_down");
		_downJump = ImageCache.getTexture("hero_down_jump");
		_restFrame = _front;
		
		TextureRegion[] death = {ImageCache.getTexture("hero_down"),
				ImageCache.getFrame("death_", 2),
				ImageCache.getFrame("death_", 3),
				ImageCache.getFrame("death_", 4),
				ImageCache.getFrame("death_", 4),
				ImageCache.getFrame("death_", 4),
				ImageCache.getFrame("death_", 4),
				_front};
		
		_deathAnimation = new Animation (0.1f, death);				
		
		_animationTime = 0f;
		_deathAnimation.addEventListener(this);
		
		setSkin(_front);
		
		//null skin so we draw sprite instead
		skin = null;
		_sprite = new Sprite (_front);
		_sprite.setPosition(x, y);
		
		_startPoint = new Vector3(x - width*0.5f, y - height*0.5f, 0f);
		
		_game.screen.elements.add(this);
		
	}
	
	@Override 
	public void reset () {
		visible = true;
		dead = false;
		_animationTime = 0f;
		
		x = nextX = _startPoint.x;
		y = nextY = _startPoint.y;
		_sprite.setPosition(x, y);	
		_sprite.setRegion(_front);
		_restFrame = _front;
		
		tierIndex = 0;
		active = true;
		hasBonus = false;
		moving = false;
		
	}
	
	public void movePlayerUp () {
		if (!moving) {
			moving = true;
			tierIndex++;
			if (tierIndex >= Tier.TIER_Y.length) tierIndex = Tier.TIER_Y.length - 1;
			nextY = _game.screenHeight - Tier.TIER_Y[tierIndex] - height;
			_game.gameData.score += GameData.POINTS_JUMP;		
			Sounds.play(Sounds.jump);
			showMoveFrame(MovingSprite.UP);		
		}
	}
	
	
	public void movePlayerDown () {
		if (!moving) {
			moving = true;
			tierIndex--;
			if (tierIndex < 0) tierIndex = 0;
			nextY = _game.screenHeight - Tier.TIER_Y[tierIndex] - height;
			_game.gameData.score +=  GameData.POINTS_JUMP;
			Sounds.play(Sounds.jump);
			showMoveFrame(MovingSprite.DOWN);
		}
	}
	
	public void movePlayerLeft ()  {
		if (!moving) {
			moving = true;
			nextX -= _sideStep;
			Sounds.play(Sounds.jump);
			showMoveFrame(MovingSprite.LEFT);
		}
	}
	
	public void movePlayerRight () {
		if (!moving) {
			moving = true;
			nextX += _sideStep;
			Sounds.play(Sounds.jump);
			showMoveFrame(MovingSprite.RIGHT);
		}
	}
	
	@Override 
	public void update (float dt) {
		
		if (dead) {
			_animationTime += dt;
	        return;
	    }
	    if (moving) {
	        if (_moveCnt > _moveInterval) {
	            moving = false;
	            _sprite.setRegion(_restFrame);
	            _moveCnt = 0.0f;
	        }
	        _moveCnt += 20*dt;
	    }
	    //add tier speed if player is on top of a moving object
	    nextX += tierSpeed * dt;
	}
	
	@Override 
	public void place () {
		//limit movement if player is not on water Tiers so frog does not leave the screen
		if (tierIndex < 7) {	
			if (nextX < 0) 
				nextX = 0;
			if (nextX > _game.screenWidth - width)
				nextX = _game.screenWidth - width;
		} else {
			//make player go back to start if frog leaves screen on water Tiers
			if (nextX < 0 || nextX > _game.screenWidth - width) {
				Sounds.play(Sounds.outofbounds);
				reset();
			}
		}
		super.place();
		_sprite.setPosition(x, y);
	}
	
	public void kill () {
		tierSpeed = 0;
		_game.gameData.gameMode = Game.GAME_STATE_ANIMATE;
		active = false; 
		dead = true;
	}
	
	private void showMoveFrame (int dir) {
		switch (dir) {
			case MovingSprite.LEFT:
				_sprite.setScale(1, 1);
				_sprite.setRegion(_sideJump);
				_restFrame = _side;
				break;
			case MovingSprite.RIGHT:
				_sprite.setScale(-1, 1);
				_sprite.setRegion(_sideJump);
				_restFrame = _side;
				break;
			case MovingSprite.UP:				
				_sprite.setRegion(_frontJump);
				_restFrame = _front;
				break;
			case MovingSprite.DOWN:				
				_sprite.setRegion(_downJump);
				_restFrame = _down;
				break;
		}
		
	}
	
	@Override
	public void draw () {
		if (dead) {
			//draw frame from death animation
			_sprite.setRegion(_deathAnimation.getKeyFrame(_animationTime, false));
		}
		_sprite.draw(_game.spriteBatch);
	}
	
	public void onAnimationEnded(AnimationEvent e) {
		visible = false;
		dead = false;
		
	    if (_game.gameData.lives >= 0) {
	    	reset();
			_game.gameData.gameMode = Game.GAME_STATE_PLAY;
	    } else {
	    	GameScreen screen = (GameScreen) _game.screen;
	    	screen.gameOver();
	    }
	}


}
