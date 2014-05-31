package com.PolyHogg.controller;

import com.PolyHogg.model.Player;
import com.PolyHogg.model.Player.State;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener{
	
	private boolean joueur1Terre = true;
	private boolean joueur2Terre = true;
	
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
			if (userData != null && userData == "pied1" && userData2 != null && userData2 == "blocStatique") {
				joueur1Terre = true;
				player1.setSlow(false);
			} else {
				
				if (userData2 != null && userData2 == "pied1" && userData != null && userData == "blocStatique") {
					joueur1Terre = true;
					player1.setSlow(false);
				}
			}
			
			if (userData != null && userData == "pied2" && userData2 != null && userData2 == "blocStatique") {
				joueur2Terre = true;
				player2.setSlow(false);
			} else {
				
				if (userData2 != null && userData2 == "pied2" && userData != null && userData == "blocStatique") {
					joueur2Terre = true;
					player2.setSlow(false);
				}
			}
			
			//CONTACT AVEC UN BLOCK Ralentisseur
			if (userData != null && userData == "pied1" && userData2 != null && userData2 == "blocRalentisseur") {
				joueur1Terre = true;
				player1.setSlow(true);
			} else {
				
				if (userData2 != null && userData2 == "pied1" && userData != null && userData == "blocRalentisseur") {
					joueur1Terre = true;
					player1.setSlow(true);
				}
			}
			
			if (userData != null && userData == "pied2" && userData2 != null && userData2 == "blocRalentisseur") {
				joueur2Terre = true;
				player2.setSlow(true);
			} else {
				
				if (userData2 != null && userData2 == "pied2" && userData != null && userData == "blocRalentisseur") {
					joueur2Terre = true;
					player2.setSlow(true);
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
			if (userData != null && userData == "pied1" && userData2 != null && userData2 == "blocStatique") {
				joueur1Terre = false;
			} else {
				
				if (userData2 != null && userData2 == "pied1" && userData != null && userData == "blocStatique") {
					joueur1Terre = false;
				}
			}
			
			if (userData != null && userData == "pied2" && userData2 != null && userData2 == "blocStatique") {
				joueur2Terre = false;
			} else {
				
				if (userData2 != null && userData2 == "pied2" && userData != null && userData == "blocStatique") {
					joueur2Terre = false;
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
		public void jump(int joueur){

			if(joueur == 1){
				if(joueur1Terre){
					player1.setState(State.JUMPING); // Passe a ete deplacement
					float impulse;
					if(!player1.getSlow()){
						impulse = player1.getPlayer().getMass() * 10;//Impulsion au joueur
					}
					else{
						impulse = player1.getPlayer().getMass() * 5;//Impulsion au joueur
					}
					 player1.getPlayer().applyLinearImpulse(new Vector2(0, impulse), player1.getPlayer().getWorldCenter(), true);
				}
			}
			else{
				if(joueur2Terre){
					player2.setState(State.JUMPING); // Passe a ete deplacement
					float impulse;
					if(!player1.getSlow()){
						impulse = player2.getPlayer().getMass() * 10;//Impulsion au joueur
					}
					else{
						impulse = player2.getPlayer().getMass() * 5;//Impulsion au joueur
					}
					player2.getPlayer().applyLinearImpulse(new Vector2(0, impulse), player2.getPlayer().getWorldCenter(), true);
				}
			}
		}
		
		public void attack(){
			
			//Cas ou l'un des joueurs est en attack

			boolean attack1 = player1.getAttack();
			boolean attack2 = player2.getAttack();
			
			if(attack1 == true && attack2 == false && player1.getGarde() != player2.getGarde()){
				player2.setLife(false);
				System.out.println("Joueur 1 gagne");
			}
			
			else if(attack1 == false && attack2 == true && player1.getGarde() != player2.getGarde()){
				player1.setLife(false);
				System.out.println("Joueur 2 gagne");
			}
			
			else if(player1.getGarde() == player2.getGarde()){
				System.out.println("Match nul");	
			}
			else if(player1.getGarde() < player2.getGarde()){
				player2.setLife(false);
				System.out.println("Joueur 1 gagne");
			}
			else if(player2.getGarde() < player1.getGarde()){
				player1.setLife(false);
				System.out.println("Joueur 2 gagne");
			}
		}
}
