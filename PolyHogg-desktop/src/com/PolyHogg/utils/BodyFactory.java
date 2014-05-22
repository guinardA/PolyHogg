package com.PolyHogg.utils;

import com.PolyHogg.model.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyFactory {
	
	/**
	 * Fonction qui creer une carre primitive avec Box2D
	 * @param largeur
	 * @param hauteur
	 * @param centre
	 * @param angle
	 * @return Objet primitive creer
	 */
	private static Shape createSquare(float largeur, float hauteur, Vector2 centre, float angle) {
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(largeur, hauteur, centre, angle);
		return boxShape;
	}
	
	/**
	 * Fonction qui creer une forme avec des trait primitive avec Box2D
	 * @param largeur
	 */
	private static Shape createLine(Vector2[] contour) {
		ChainShape boxShape = new ChainShape();
		boxShape.createChain(contour);
		return boxShape;
	}
	
	/**
	 * Fonction qui permet de creer les proprietes physique
	 * @param typeObjet - Type de d'objet primitive
	 * @param sensible - Si objet est sensible au contact ou pas
	 * @param densite - Densite de l'objet pour la gravite
	 * @param friction - Si l'objet est contraint a de la friction
	 * @param restitution -  Permet de gerer les rebonds et les collisions compris entre 0 et 1
	 * @return Ensemble des propriete physique
	 */
	private static FixtureDef physicalProperties(Shape typeObjet, boolean sensible, float densite, float friction, float restitution) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = typeObjet;
		fixtureDef.isSensor = sensible;
		fixtureDef.density = densite;
		fixtureDef.friction = friction; 
		fixtureDef.restitution = restitution;
		return fixtureDef;
	}
	
	/**
	 * Fonction qui creer un corps vide avec une position et un etat
	 * @param type - Etat du corps creer dans le monde soit il est statique ou soit il est dynamique
	 * @param position - Position du corps dans le monde
	 * @return Le corps vide creer
	 */
	private static BodyDef createBody(BodyType type, Vector2 position){
		BodyDef bodyDef = new BodyDef(); 
		bodyDef.type = type;// etat statique ou dynamique de l'objet
		bodyDef.position.set(position); //Position de objet dans le monde
		return bodyDef;
	}
	
	/**
	 * Permet de liee le corps a une primitve, au monde et de lie les propriete physique associe
	 * @param world - Monde physique ou on doit ajouter le corps
	 * @param bodyDef - Corps a ajouter au monde
	 * @param fixtureDef - Ensemble des propriete physiques lie au corps
	 * @return Corps ajouter au monde
	 */
	private static Body worldLink(World world, BodyDef bodyDef, FixtureDef fixtureDef){
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
		return body;

	}
	
	/**
	 * Permet de cr��er un block Normal avec toutes les bonne propri��t��
	 * @param v - Tableau de cordonn��e pour cr��er la primitive
	 * @param position - Position de la primitive dans le monde
	 * @param world - Monde ou il faut li�� la primitive
	 * @param type - Type de block
	 * @return Un corps de bloc
	 */
	public static Body createBlock(Vector2[] v, Vector2 position, World world, String type){
		Shape primitive =  createLine(v);
		FixtureDef physique = physicalProperties(primitive, false, 1, Constants.SIZE_BLOCK/2, 0);
		BodyDef corps =  createBody(BodyType.StaticBody, position);
		Body body =  worldLink(world, corps, physique);
		
		primitive =  BodyFactory.createSquare(Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2,new Vector2(0,0), 0);
		physique = BodyFactory.physicalProperties(primitive, true, 0, 0, 0);
		
		
		
		if(type.equals("statique")){
			body.setUserData("blocStatique");
		}
		else if(type.equals("ralentisseur")){
			body.setUserData("blocRalentisseur");
		}
		else if(type.equals("pd")){
			//body.setUserData("porteDroit");
			body.createFixture(physique).setUserData("porteDroit");//Permet de savoir si le personange touche le sol ou pas
			body.setFixedRotation(true);
		}
		else if(type.equals("pg")){
			body.setUserData("porteGauche");
		}
		
		return body;
	}
	
	public static Body createPlayer(Player perso, World world){
		
		//Corps
		Shape primitive =  createSquare(perso.getBounds().width/2, perso.getBounds().height/2, new Vector2(perso.getBounds().width/2,perso.getBounds().height/2), 0);
		FixtureDef physique = physicalProperties(primitive, false, 1, 0, 0);
		BodyDef corps =  createBody(BodyType.DynamicBody, perso.getPosition());
		Body body =  worldLink(world, corps, physique);

		//Pied
		primitive =  BodyFactory.createSquare(0.1f, 0.1f,new Vector2(perso.getBounds().width/2,0), 0);
		physique = BodyFactory.physicalProperties(primitive, true, 0, 0, 0);
		body.createFixture(physique).setUserData("pied");//Permet de savoir si le personange touche le sol ou pas
		body.setFixedRotation(true); //Empeche le carre de glisser et de tomber
		
		//HitBox
		primitive =  BodyFactory.createSquare(perso.getBounds().width/2, perso.getBounds().height/2,new Vector2(perso.getBounds().width/2,perso.getBounds().height/2), 0);
		physique = BodyFactory.physicalProperties(primitive, true, 0, 0, 0);
		body.createFixture(physique).setUserData("hitbox");//Permet de savoir si le personange touche le sol ou pas
		body.setFixedRotation(true); //Empeche le carre de glisser et de tomber
		
		body.setUserData("joueur");
		
		return body;
	}

}
