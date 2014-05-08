package com.PolyHogg.model;

import com.PolyHogg.utils.VariableGeneral;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	public static void main(String[] args) {
		
		int hauteur = VariableGeneral.HAUTEUR_FENETRE*VariableGeneral.COTE_BLOCK;
		int largeur = VariableGeneral.LARGEUR_FENETRE*VariableGeneral.COTE_BLOCK;
		
		new LwjglApplication(new GdxGame(),"PolyHogg",largeur,hauteur, true);
	}
}
