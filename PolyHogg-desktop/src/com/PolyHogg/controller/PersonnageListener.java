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
	private boolean attackPressedJ1;
	private boolean attackPressedJ2;
	

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
			if(!player1.getSlow()){
				player1.getPlayer().setLinearVelocity(-4,0);
			}
			else{
				player1.getPlayer().setLinearVelocity(-2,0);
			}
			player1.setState(State.WALKING);
			player1.setFacingLeft(true);
		}

		if (keycode == Keys.RIGHT){
			if(!player1.getSlow()){
				player1.getPlayer().setLinearVelocity(4,0);
			}
			else{
				player1.getPlayer().setLinearVelocity(2,0);
			}
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
			contact.jump(1);
		}
		
		if(keycode == Keys.CONTROL_RIGHT){
			if(attackPressedJ1){
				player1.setAttack(true);
				attackPressedJ1 = false;
			}
		}
		
		//Touche du joueur 2
		if (keycode == Keys.Q){
			if(!player2.getSlow()){
				player2.getPlayer().setLinearVelocity(-4,0);
			}
			else{
				player2.getPlayer().setLinearVelocity(-2,0);
			}
			player2.setState(State.WALKING);
			player2.setFacingLeft(true);
		}
		if (keycode == Keys.D){
			if(!player2.getSlow()){
				player2.getPlayer().setLinearVelocity(4,0);
			}
			else{
				player2.getPlayer().setLinearVelocity(2,0);
			}
			player2.setState(State.WALKING);
			player2.setFacingLeft(false);
		}
		if (keycode == Keys.Z){
			player2.upGarde();
		}
		if (keycode == Keys.S){
			player2.downGarde();
		}
		if (keycode == Keys.SPACE){
			contact.jump(2);
		}
		if(keycode == Keys.SHIFT_LEFT){
			if(attackPressedJ2){
				player2.setAttack(true);
				attackPressedJ2 = false;
			}
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
		if(keycode == Keys.CONTROL_RIGHT){
				attackPressedJ1 = true;
		}
		if(keycode == Keys.SHIFT_LEFT){
			attackPressedJ2 = true;
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
