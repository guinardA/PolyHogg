package com.PolyHogg.controller;

import com.PolyHogg.model.Player;
import com.PolyHogg.model.Player.State;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener{
	
	private boolean joueurTerre = true;
	private Player player1;
	private Player player2;
	
	public GameContactListener(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	//Si le contact touche un corps ou pas
		@Override
		public void beginContact(Contact contact) {

			Object userData = contact.getFixtureA().getUserData();
			Object userData2 = contact.getFixtureB().getUserData();

			//CONTACT AVEC LE SOL
			if (userData != null && userData == "pied") {
				joueurTerre = true;
			} else {
				
				if (userData2 != null && userData2 == "pied") {
					joueurTerre = true;
				}
			}

			if((userData != null && userData == "porteDroit" && userData2 != null && userData2 == "hitbox") 
				|| (userData2 != null && userData2 == "porteDroit" && userData != null && userData == "hitbox")){
				//player2.setFinish(true);
			}
			
			//CONTACT ENTRE 2 JOUEURS
			if ((userData != null && userData == "hitbox") && (userData2 != null && userData2 == "hitbox")) {
				this.attack();
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

		//Fonction qui permet le saut du personnage pour le personnage 1
		public void jump(){

			if(joueurTerre){
				player1.setState(State.JUMPING); // Passe a ete deplacement
				float impulse = player1.getPlayer().getMass() * 6;//Impulsion au joueur
				 player1.getPlayer().applyLinearImpulse(new Vector2(0, impulse), player1.getPlayer().getWorldCenter(), true);
			}
		}
		
		public void attack(){
			if(player1.getGarde() == player2.getGarde()){
				System.out.println("Match nul");
			}
			else if(player1.getGarde() < player2.getGarde()){
				System.out.println("Joueur 1 gagne");
			}
			else if(player2.getGarde() < player1.getGarde()){
				System.out.println("Joueur 2 gagne");
			}
		}
}
