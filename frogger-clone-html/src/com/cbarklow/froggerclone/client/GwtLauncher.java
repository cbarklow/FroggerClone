package com.cbarklow.froggerclone.client;

import com.cbarklow.froggerclone.FroggerCloneGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(320, 480);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new FroggerCloneGame();
	}
}