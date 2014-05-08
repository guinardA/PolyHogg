package com.PolyHogg.utils;

import com.PolyHogg.ressource.Perso;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2Dfactory {

	/**
	 * Fonction qui creer une carre primitive avec Box2D
	 * @param largeur
	 * @param hauteur
	 * @param centre
	 * @param angle
	 * @return Objet primitive creer
	 */
	public static Shape creationCarre(float largeur, float hauteur, Vector2 centre, float angle) {
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(largeur, hauteur, centre, angle);
		return boxShape;
	}

	/**
	 * Fonction qui creer une forme avec des trait primitive avec Box2D
	 * @param largeur
	 */
	public static Shape creationTrait(Vector2[] contour) {
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
	public static FixtureDef ProprietePhysique(Shape typeObjet, boolean sensible, float densite, float friction, float restitution) {
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
	public static  BodyDef Corps(BodyType type, Vector2 position){
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
	public static Body liaisonMonde(World world, BodyDef bodyDef, FixtureDef fixtureDef){
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		fixtureDef.shape.dispose();
		return body;

	}
	
	/**
	 * Permet de créer un block Normal avec toutes les bonne propriété
	 * @param v - Tableau de cordonnée pour créer la primitive
	 * @param position - Position de la primitive dans le monde
	 * @param world - Monde ou il faut lié la primitive
	 * @return Un corps de bloc
	 */
	public static Body BlockNormal(Vector2[] v, Vector2 position, World world){
		Shape primitive =  creationTrait(v);
		FixtureDef physique = ProprietePhysique(primitive, false, 1, VariableGeneral.SIZE_BLOCK/2, 0);
		BodyDef corps =  Corps(BodyType.StaticBody, position);
		Body body =  liaisonMonde(world, corps, physique);
		body.setUserData("bloc");
		
		return body;
	}
	
	public static Body Personnage(Sprite sprite, Perso perso, World world){
		
		//Corps
		Shape primitive =  creationCarre(perso.getBounds().width/2, perso.getBounds().height/2, new Vector2(perso.getBounds().width/2,perso.getBounds().height/2), 0);
		FixtureDef physique = ProprietePhysique(primitive, false, 1, 0, 0);
		BodyDef corps =  Corps(BodyType.DynamicBody, perso.getPosition());
		Body body =  liaisonMonde(world, corps, physique);

		//Pied
		primitive =  Box2Dfactory.creationCarre(0.1f, 0.1f,new Vector2(perso.getBounds().width/2,0), 0);
		physique = Box2Dfactory.ProprietePhysique(primitive, true, 0, 0, 0);
		body.createFixture(physique).setUserData("pied");//Permet de savoir si le personange touche le sol ou pas
		body.setFixedRotation(true); //Empeche le carre de glisser et de tomber

		//On lie le sprite au personnage
		body.setUserData(sprite);
		
		return body;
	}


}
