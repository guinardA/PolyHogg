package com.PolyHogg.model;

import com.PolyHogg.utils.BodyFactory;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

	/*
	 * IDLE : Lorsquil ne bouge ou saute pas ET est envie.
	 * WALKING : Deplacement de gauche ou droite a une vitesse constante.
	 * JUMPING : Deplacement en hauteur de gauche ou droite.
	 * DYING : Invisible et regeneration
	 */
	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}

	private State state = State.IDLE;//etat du personnage
	private boolean facingLeft;//personnage tourne vers la gauche
	float stateTime = 0;

	private Vector2     position = new Vector2(); //position de Bob dans le monde
	private Vector2     acceleration = new Vector2(); //acceleration en XY lorsque le personnage saute
	private Vector2     velocity = new Vector2(); //vitesse de deplacement du personnage
	private Rectangle   bounds = new Rectangle(); //limite du personnage
	
	private int garde = 1;
	private Body player;
	private boolean	life = true;
	private boolean	finish = false;
	private boolean	sprite = true;
	private boolean 	attack =false;
	private long		begin_attack;
	private boolean	slow = false;
	
	private boolean personnageRouge;
	
	private PersonnageAnimation animation;

	public Player(Vector2 position,boolean rouge) {
		this.position = position;
		this.bounds.height = Constants.SIZE_PERSO;
		this.bounds.width = Constants.SIZE_PERSO;
		this.bounds.x = this.position.x;
		this.bounds.y = this.position.y;
		this.facingLeft = rouge;
		this.personnageRouge = rouge;
		this.animation = new PersonnageAnimation(this);
	}
	
	public Body createCorps(World world, int joueur) {
		player = BodyFactory.createPlayer(this, world, joueur);
		return player;
	}
	
	
	public void update(OrthographicCamera camera,float delta){
		if(sprite){
			SpriteBatch batch;
			batch = new SpriteBatch();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(animation.getCurrentFrame(delta),player.getPosition().x, player.getPosition().y, Constants.SIZE_PERSO , Constants.SIZE_PERSO );
			batch.end();
		}
	}
	
	public boolean getSlow(){
		return slow;
	}
	
	public void setSlow(Boolean slow){
		this.slow = slow;
	}
	
	public boolean getAttack(){
		return attack;
	}
	
	public void setAttack(boolean attack){
		this.attack = attack;
		if(attack){
			this.begin_attack = System.currentTimeMillis();
		}
	}
	
	public long getCurrentTime(){
		return begin_attack;
	}
	
	public boolean getFinish(){
		return finish;
	}
	
	public void setFinish(boolean finish){
		this.finish = finish;
	}
	
	public boolean getLife(){
		return life;
	}
	
	public void setLife(boolean life){
		this.life = life;
		if(!life){
			sprite = false;
		}
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
	public void setPlayer(Body player){
		this.player = player;
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


	public boolean isFacingLeft() {
		return facingLeft;
	}

	public State getState() {
		return state;
	}

	public boolean isPersonnageRouge() {
		return personnageRouge;
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
