package com.PolyHogg.model;

import com.PolyHogg.model.Player.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PersonnageAnimation {

	/** Textures **/
	//Statique
	private TextureRegion idleLeftH;
	private TextureRegion idleLeftM;
	private TextureRegion idleLeftB;
	private TextureRegion idleRightH;
	private TextureRegion idleRightM;
	private TextureRegion idleRightB;
	
	//Saut
	private TextureRegion jumpLeft;
	private TextureRegion fallLeft;
	private TextureRegion jumpRight;
	private TextureRegion fallRight;
	
	//attaque
	private TextureRegion attaqueLeftH;
	private TextureRegion attaqueLeftM;
	private TextureRegion attaqueLeftB;
	private TextureRegion attaqueRightH;
	private TextureRegion attaqueRightM;
	private TextureRegion attaqueRightB;
	
	private TextureRegion frame;
	
	/** Animations **/
	private Animation walkLeftHAnimation;
	private Animation walkLeftMAnimation;
	private Animation walkLeftBAnimation;
	private Animation walkRightHAnimation;
	private Animation walkRightMAnimation;
	private Animation walkRightBAnimation;

	private static final float RUNNING_FRAME_DURATION = 0.06f;
	float stateTime = 0;

	private Player player;
	
	public PersonnageAnimation(Player p){
		player = p;
		loadTextures();
	}
	
	private void loadTextures() {
		String couleur = player.isPersonnageRouge() ? "rouge" : "vert";
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures.pack"));
		
		initPositionHauteFrames(atlas,couleur);
		initPositionMilieuFrames(atlas,couleur);
		initPositionBasseFrames(atlas,couleur);

		//saut 
		jumpLeft = atlas.findRegion(couleur+"-up");
		jumpRight = new TextureRegion(jumpLeft);
		jumpRight.flip(true, false);
		fallLeft = atlas.findRegion(couleur+"-down");
		fallRight = new TextureRegion(fallLeft);
		fallRight.flip(true, false);
	}
	
	public TextureRegion getCurrentFrame(float delta){
		stateTime += delta;
		int garde = player.getGarde();
		if( garde == 0 )
			frame = player.isFacingLeft() ? idleLeftB : idleRightB;
		else if(garde == 1)
			frame = player.isFacingLeft() ? idleLeftM : idleRightM;
		else 
			frame = player.isFacingLeft() ? idleLeftH : idleRightH;

		if( player.getAttack() ){
			if( garde == 0 )
				frame = player.isFacingLeft() ? attaqueLeftB : attaqueRightB ;
			else if( garde == 1 )
				frame = player.isFacingLeft() ? attaqueLeftM : attaqueRightM ;
			else
				frame = player.isFacingLeft() ? attaqueLeftH : attaqueRightH ;
		}
		
		if(player.getState().equals(State.WALKING)) {
			if( garde == 0 )
				frame = player.isFacingLeft() ? walkLeftBAnimation.getKeyFrame(stateTime, true) : walkRightBAnimation.getKeyFrame(stateTime, true);
			else if(garde == 1)
				frame = player.isFacingLeft() ? walkLeftMAnimation.getKeyFrame(stateTime, true) : walkRightMAnimation.getKeyFrame(stateTime, true);
			else
				frame = player.isFacingLeft() ? walkLeftHAnimation.getKeyFrame(stateTime, true) : walkRightHAnimation.getKeyFrame(stateTime, true);
		}
		else if (player.getState().equals(State.JUMPING)) {
			if( player.getPlayer().getLinearVelocity().y == 0)
				player.setState(State.IDLE);
			if (player.getPlayer().getLinearVelocity().y > 0) {
				frame = player.isFacingLeft() ? jumpLeft : jumpRight;
			} else {
				frame = player.isFacingLeft() ? fallLeft : fallRight;
			}
		}

		return frame;
	}
	
	private void initPositionHauteFrames(TextureAtlas atlas,String couleur){
		//Etat statique
		idleLeftH = atlas.findRegion(couleur+"-01H");
		idleRightH = new TextureRegion(idleLeftH);
		idleRightH.flip(true, false);
		
		//animation marche gauche Haut
		TextureRegion[] walkLeftFramesH = new TextureRegion[5];
		walkLeftFramesH[0] = atlas.findRegion(couleur+"-02H");
		walkLeftFramesH[1] = atlas.findRegion(couleur+"-03");
		walkLeftFramesH[2] = atlas.findRegion(couleur+"-04");
		walkLeftFramesH[3] = atlas.findRegion(couleur+"-05H");
		walkLeftFramesH[4] = atlas.findRegion(couleur+"-06H");
		walkLeftHAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFramesH);

		//animation marche droite Haut
		TextureRegion[] walkRightFramesH = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkRightFramesH[i] = new TextureRegion(walkLeftFramesH[i]);
			walkRightFramesH[i].flip(true, false);
		}
		walkRightHAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFramesH);
		
		//attaque gauche haut
		attaqueLeftH = atlas.findRegion(couleur+"-atckH");
		attaqueRightH = new TextureRegion(attaqueLeftH);
		attaqueRightH.flip(true, false);
	}

	private void initPositionMilieuFrames(TextureAtlas atlas,String couleur){
		//Etat statique
		idleLeftM = atlas.findRegion(couleur+"-01M");
		idleRightM = new TextureRegion(idleLeftM);
		idleRightM.flip(true, false);
		
		//animation marche gauche Haut
		TextureRegion[] walkLeftFramesM = new TextureRegion[5];
		walkLeftFramesM[0] = atlas.findRegion(couleur+"-02M");
		walkLeftFramesM[1] = atlas.findRegion(couleur+"-03");
		walkLeftFramesM[2] = atlas.findRegion(couleur+"-04");
		walkLeftFramesM[3] = atlas.findRegion(couleur+"-05M");
		walkLeftFramesM[4] = atlas.findRegion(couleur+"-06M");
		walkLeftMAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFramesM);

		//animation marche droite Haut
		TextureRegion[] walkRightFramesM = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkRightFramesM[i] = new TextureRegion(walkLeftFramesM[i]);
			walkRightFramesM[i].flip(true, false);
		}
		walkRightMAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFramesM);
		
		//attaque gauche milieu
		attaqueLeftM = atlas.findRegion(couleur+"-atckM");
		attaqueRightM = new TextureRegion(attaqueLeftM);
		attaqueRightM.flip(true, false);
	}

	private void initPositionBasseFrames(TextureAtlas atlas,String couleur){
		//Etat statique
		idleLeftB = atlas.findRegion(couleur+"-01B");
		idleRightB = new TextureRegion(idleLeftB);
		idleRightB.flip(true, false);
		
		//animation marche gauche Haut
		TextureRegion[] walkLeftFramesB = new TextureRegion[5];
		walkLeftFramesB[0] = atlas.findRegion(couleur+"-02B");
		walkLeftFramesB[1] = atlas.findRegion(couleur+"-03");
		walkLeftFramesB[2] = atlas.findRegion(couleur+"-04");
		walkLeftFramesB[3] = atlas.findRegion(couleur+"-05B");
		walkLeftFramesB[4] = atlas.findRegion(couleur+"-06B");
		walkLeftBAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFramesB);

		//animation marche droite Haut
		TextureRegion[] walkRightFramesB = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkRightFramesB[i] = new TextureRegion(walkLeftFramesB[i]);
			walkRightFramesB[i].flip(true, false);
		}
		walkRightBAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFramesB);
		
		//attaque gauche haut
		attaqueLeftB = atlas.findRegion(couleur+"-atckB");
		attaqueRightB = new TextureRegion(attaqueLeftB);
		attaqueRightB.flip(true, false);
	}
}
