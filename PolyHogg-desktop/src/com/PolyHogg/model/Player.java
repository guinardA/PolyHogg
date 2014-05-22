package com.PolyHogg.model;

import com.PolyHogg.utils.BodyFactory;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Classe qui contient les propriete specifique a un personnage
 * @author arnaud
 *
 */
public class Player {
	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	/*
	 * IDLE : Lorsqu’il ne bouge ou saute pas ET est envie.
	 * WALKING : Deplacement de gauche ou droite a une vitesse constante.
	 * JUMPING : Deplacement en hauteur de gauche ou droite.
	 * DYING : Invisible et regeneration
	 */

	private Vector2     position = new Vector2(); //position de Bob dans le monde
	private Vector2     acceleration = new Vector2(); //acceleration en XY lorsque le personnage saute
	private Vector2     velocity = new Vector2(); //vitesse de deplacement du personnage
	private Rectangle   bounds = new Rectangle(); //limite du personnage
	private State       state = State.IDLE;//etat du personnage
	private boolean    facingLeft = true;//personnage tourne vers la gauche
	private int 		garde = 1;
	private Body 		player;
	private boolean	life = true;
	private boolean	finish = false;

	public Player(Vector2 position) {
		this.position = position;
		this.bounds.height = Constants.SIZE_PERSO;
		this.bounds.width = Constants.SIZE_PERSO;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
	}
	
	//MÉTHODE A CHANGER POUR ANIMATIONS
	public Body createCorps(World world) {
		Sprite sprite;

		//Chargement du sprite perosnnage
		sprite = new Sprite(new Texture("images/bob_01.png"));
		sprite.setSize(bounds.width, bounds.height);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

		player = BodyFactory.createPlayer(sprite, this, world);
		
		return player;

	}
	//MÉTHODE A CHANGER POUR ANIMATIONS
	public void afficherSprite( OrthographicCamera camera){
		SpriteBatch batch;

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();

		Sprite sprite = (Sprite) this.getPlayer().getUserData();//On récuèpere le sprite lié au corps

		//On place les sprité au mêmes coordonnée que les block
		
		Vector2 position = player.getPosition();
		sprite.setPosition(position.x,position.y);
		sprite.setRotation(this.getPlayer().getAngle() * MathUtils.radiansToDegrees);

		//On dessine les sprites sur écran
		sprite.draw(batch);
		batch.end();
		

	}
	
	public boolean getFinish(){
		return finish;
	}
	
	public void setFinish(boolean finish){
		this.finish = finish;
	}
	
	public void setLife(boolean life){
		this.life = life;
	}
	
	public int getGarde(){
		return garde;
	}
	
	public void upGarde(){
		if(garde < 3){
			garde ++;
		}
	}
	
	public void downGarde(){
		if(garde > 0){
			garde --;
		}
	}
	
	public Body getPlayer(){
		return player;
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