package com.PolyHogg.model;
 
import com.PolyHogg.view.GameScreen;
import com.badlogic.gdx.Game;
 
public class GdxGame extends Game {
 
	/*
	 * Charge les differents ressources utile a l'application
	 */
    @Override
    public void create() {
    	setScreen(new GameScreen());//Implemente interface ecran de jeu (show() et hide())
    }
 
}