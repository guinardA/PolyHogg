package com.PolyHogg.controller;

import com.PolyHogg.model.ChargementPersonnage;
import com.PolyHogg.ressource.Perso;
import com.PolyHogg.ressource.Perso.State;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Classe qui fait les reaction avec les differents input possible
 * @author arnaud
 *
 */
public class PersoController implements  InputProcessor, ContactListener{

	private Body persoCorps;
	private Perso perso;
	private boolean joueurTerre = true;

	public PersoController(Body perso, ChargementPersonnage ChargPerso) {
		this.persoCorps = perso;
		this.perso = ChargPerso.getPerso();
	}

	//Quand une touche est enfonce
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT){
			persoCorps.setLinearVelocity(-4,0);
		}

		if (keycode == Keys.RIGHT){
			persoCorps.setLinearVelocity(4,0);
		}

		if (keycode == Keys.SPACE){
			jump();
		}
		return true;
	}

	//Quand une touche est relache
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT){
			persoCorps.setLinearVelocity(0,0);
		}

		if (keycode == Keys.RIGHT){
			persoCorps.setLinearVelocity(0,0);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	//Pour les ecrans tactile quand on appuie sur ecran
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		// TODO Auto-generated method stub
		return false;
	}

	//Pour les ecrans tactile quand on relache l'ecran
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	//Si le contact touche un corps ou pas
	@Override
	public void beginContact(Contact contact) {

		Object userData = contact.getFixtureA().getUserData();

		//On verifie que une des zone de contact correspond au personnage, si oui on signale que le joueur peut sauter
		if (userData != null && userData == "pied") {
			joueurTerre = true;
		} else {
			userData = contact.getFixtureB().getUserData();
			if (userData != null && userData == "pied") {
				joueurTerre = true;
			}
		}
	}

	@Override
	public void endContact(Contact contact) {

		Object userData = contact.getFixtureA().getUserData();

		//On verifie que le personnage ne touche pas le sol pour qu'il ne puisse pas sauter
		if (userData != null && userData == "pied") {
			joueurTerre = false;
		} else {
			userData = contact.getFixtureB().getUserData();
			if (userData != null && userData == "pied") {
				joueurTerre = false;
			}
		}
	}


	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

	//Fonction qui permet le saut du personnage
	private void jump(){

		if(joueurTerre){
			perso.setState(State.JUMPING); // Passe a ete deplacement
			float impulse = persoCorps.getMass() * 6;//Impulsion au joueur
			persoCorps.applyLinearImpulse(new Vector2(0, impulse),persoCorps.getWorldCenter(), true);
		}
	}
}
