package com.PolyHogg.controller;

import com.PolyHogg.model.Player;
import com.PolyHogg.model.Player.State;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Classe qui fait les reaction avec les differents input possible
 * @author arnaud
 *
 */
public class PersonnageListener implements  InputProcessor{

	private Player player1;
	private Player player2;
	private GameContactListener contact;
	

	public PersonnageListener(Player player1, Player player2, GameContactListener contact) {
		this.player1 = player1;
		this.player2 = player2;
		this.contact = contact;
	}

	//Quand une touche est enfonce
	@Override
	public boolean keyDown(int keycode) {
		//Touche du joueur 1
		if (keycode == Keys.LEFT){
			player1.getPlayer().setLinearVelocity(-4,0);
			player1.setState(State.WALKING);
			player1.setFacingLeft(true);
		}

		if (keycode == Keys.RIGHT){
			player1.getPlayer().setLinearVelocity(4,0);
			player1.setState(State.WALKING);
			player1.setFacingLeft(false);
		}
		
		if (keycode == Keys.UP){
			player1.upGarde();
		}
		if (keycode == Keys.DOWN){
			player1.downGarde();
		}

		if (keycode == Keys.SHIFT_RIGHT){
			contact.jump();
		}
		
		//Touche du joueur 2
		if (keycode == Keys.Q){
			player2.getPlayer().setLinearVelocity(-4,0);
			player2.setState(State.WALKING);
			player2.setFacingLeft(true);
		}
		if (keycode == Keys.D){
			player2.getPlayer().setLinearVelocity(4,0);
			player2.setState(State.WALKING);
			player2.setFacingLeft(false);
		}
		if (keycode == Keys.Z){
			player2.upGarde();
		}
		if (keycode == Keys.S){
			player2.downGarde();
		}
		return true;
	}

	//Quand une touche est relache
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT){
			player1.getPlayer().setLinearVelocity(0,0);
			player1.setState(State.IDLE);
		}

		if (keycode == Keys.RIGHT){
			player1.getPlayer().setLinearVelocity(0,0);
			player1.setState(State.IDLE);
		}
		if (keycode == Keys.Q){
			player2.getPlayer().setLinearVelocity(0,0);
			player2.setState(State.IDLE);
		}
		if (keycode == Keys.D){
			player2.getPlayer().setLinearVelocity(0,0);
			player2.setState(State.IDLE);
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
}
