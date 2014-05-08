package com.PolyHogg.ressource;

import com.PolyHogg.utils.VariableGeneral;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe qui contient les propriete specifique a un personnage
 * @author arnaud
 *
 */
public class Perso {
	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	/*
	 * IDLE : Lorsqu’il ne bouge ou saute pas ET est envie.
	 * WALKING : Deplacement de gauche ou droite a une vitesse constante.
	 * JUMPING : Deplacement en hauteur de gauche ou droite.
	 * DYING : Invisible et regeneration
	 */

	Vector2     position = new Vector2(); //position de Bob dans le monde
	Vector2     acceleration = new Vector2(); //acceleration en XY lorsque le personnage saute
	Vector2     velocity = new Vector2(); //vitesse de deplacement du personnage
	Rectangle   bounds = new Rectangle(); //limite du personnage
	State       state = State.IDLE;//etat du personnage
	boolean    facingLeft = true;//personnage tourne vers la gauche

	public Perso(Vector2 position) {
		this.position = position;
		this.bounds.height = VariableGeneral.SIZE_PERSO;
		this.bounds.width = VariableGeneral.SIZE_PERSO;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public void setFacingLeft(boolean face){
		facingLeft = face;
	}
	public void setState(State etat){
		state = etat;
	}
	public Vector2 getVelocity(){
		return velocity;
	}
	public Vector2 getAcceleration(){
		return acceleration;
	}
}