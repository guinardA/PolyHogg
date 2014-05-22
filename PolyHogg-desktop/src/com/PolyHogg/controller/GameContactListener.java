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
			if (userData != null && userData == "pied" && userData2 != null && userData2 == "blocStatique") {
				joueurTerre = true;
			} else {
				
				if (userData2 != null && userData2 == "pied" && userData != null && userData == "blocStatique") {
					joueurTerre = true;
				}
			}

			if((userData != null && userData == "porteDroit" && userData2 != null && userData2 == "hitbox2") 
				|| (userData2 != null && userData2 == "porteDroit" && userData != null && userData == "hitbox2")){
				player2.setFinish(true);
			}
			
			if((userData != null && userData == "porteGauche" && userData2 != null && userData2 == "hitbox1") 
					|| (userData2 != null && userData2 == "porteGauche" && userData != null && userData == "hitbox1")){
					player1.setFinish(true);
			}
			
			//CONTACT ENTRE 2 JOUEURS
			if (((userData != null && userData == "hitbox1") && (userData2 != null && userData2 == "hitbox2"))
				|| (userData != null && userData == "hitbox2") && (userData2 != null && userData2 == "hitbox1")) {
				this.attack();
			}
		}

		@Override
		public void endContact(Contact contact) {

			Object userData = contact.getFixtureA().getUserData();
			Object userData2 = contact.getFixtureB().getUserData();

			//On verifie que le personnage ne touche pas le sol pour qu'il ne puisse pas sauter
			if (userData != null && userData == "pied" && userData2 != null && userData2 == "blocStatique") {
				joueurTerre = false;
			} else {
				
				if (userData2 != null && userData2 == "pied" && userData != null && userData == "blocStatique") {
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
				float impulse = player1.getPlayer().getMass() * 10;//Impulsion au joueur
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
