package com.PolyHogg.model;

import com.PolyHogg.ressource.Perso;
import com.badlogic.gdx.math.Vector2;

public class ChargementPersonnage {

	Perso perso;
	
	public ChargementPersonnage(int numero) {
		if(numero == 1 ){
			niveauTeste();
		}
	}
	
	private void niveauTeste() {

		perso = new Perso(new Vector2(7, 3)); //Position de départ du personnage
		
	}
	public void setPerso(Perso perso) {
		this.perso = perso;
	}
	public Perso getPerso() {
		return perso;
	}
}
