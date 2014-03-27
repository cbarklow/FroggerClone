package com.cbarklow.froggerclone;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Spooky Tower";
		cfg.width = 320;
		cfg.height = 480;
		
		new LwjglApplication(new FroggerCloneGame(), cfg);
	}
}
