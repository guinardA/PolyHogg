package com.PolyHogg.controller;

import com.PolyHogg.utils.Constants;
import com.PolyHogg.view.CustomPlayScreen;
import com.PolyHogg.view.GameScreen;
import com.PolyHogg.view.MenuScreen;
import com.PolyHogg.view.ParametreScreen;
import com.PolyHogg.view.PlayScreen;
import com.PolyHogg.view.PolyMenu;
import com.PolyHogg.view.TournoiScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuListener extends ClickListener {

	private PolyMenu polymenu;

	public MenuListener(PolyMenu pm) {
		this.polymenu = pm;
	}

	public void clicked(InputEvent event, float x, float y) {
		if (polymenu instanceof MenuScreen) {	//MENUSCREEN
			if (event.getListenerActor() == ((MenuScreen) polymenu).buttonExit) {
				Gdx.app.exit();
			}

			if (event.getListenerActor() == ((MenuScreen) polymenu).buttonOption) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new ParametreScreen());
			}

			if (event.getListenerActor() == ((MenuScreen) polymenu).buttonPlay) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new PlayScreen());
			}
		}

		if (polymenu instanceof PlayScreen) {	//PLAYSCREEN
			if (event.getListenerActor() == ((PlayScreen) polymenu).buttonQuick) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new GameScreen());
			}

			if (event.getListenerActor() == ((PlayScreen) polymenu).buttonBack) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new MenuScreen());
			}

			if (event.getListenerActor() == ((PlayScreen) polymenu).buttonCustom) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new CustomPlayScreen());
			}

			if (event.getListenerActor() == ((PlayScreen) polymenu).buttonTourn) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new TournoiScreen());
			}
		}

		if (polymenu instanceof ParametreScreen) {	//PARAMETRESCREEN
			if (event.getListenerActor() == ((ParametreScreen) polymenu).buttonOk) {
				Constants.GRAVITY = Float
						.parseFloat(((ParametreScreen) polymenu).gravityField
								.getText());
				Constants.NB_GRENADES = Integer
						.parseInt(((ParametreScreen) polymenu).grenadesField
								.getText());
				Constants.MAX_SCORE = Integer
						.parseInt(((ParametreScreen) polymenu).scoreField
								.getText());
				Constants.GAME_DURATION = Integer
						.parseInt(((ParametreScreen) polymenu).durationField
								.getText());
				Constants.DIFFICULTY = Integer
						.parseInt(((ParametreScreen) polymenu).difficultyField
								.getText());
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new MenuScreen());

			}

			if (event.getListenerActor() == ((ParametreScreen) polymenu).buttonCancel) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new MenuScreen());
			}
		}

		if (polymenu instanceof CustomPlayScreen) {	//CUSTOMPLAYSCREEN
			if (event.getListenerActor() == ((CustomPlayScreen) polymenu).buttonOk) {
				if((Float.parseFloat(((CustomPlayScreen) polymenu).gravityField.getText()) < 0)){
					Constants.GRAVITY = Float
							.parseFloat(((CustomPlayScreen) polymenu).gravityField
									.getText());
					Constants.NB_GRENADES = Integer
							.parseInt(((CustomPlayScreen) polymenu).grenadesField
									.getText());
					Constants.MAX_SCORE = Integer
							.parseInt(((CustomPlayScreen) polymenu).scoreField
									.getText());
					Constants.GAME_DURATION = Integer
							.parseInt(((CustomPlayScreen) polymenu).durationField
									.getText());
					Constants.DIFFICULTY = Integer
							.parseInt(((CustomPlayScreen) polymenu).difficultyField
									.getText());
					((Game) Gdx.app.getApplicationListener())
							.setScreen(new GameScreen());
				}
				else{
					((CustomPlayScreen) polymenu).heading.setText("Probleme");
				}
		}

			if (event.getListenerActor() == ((CustomPlayScreen) polymenu).buttonBack) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new PlayScreen());
				
			}
		}
		
		if (polymenu instanceof TournoiScreen) {	//TOURNOISCREEN
			if (event.getListenerActor() == ((TournoiScreen) polymenu).buttonOk) {

			}

			if (event.getListenerActor() == ((TournoiScreen) polymenu).buttonBack) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new PlayScreen());
			}
		}
	}
}
