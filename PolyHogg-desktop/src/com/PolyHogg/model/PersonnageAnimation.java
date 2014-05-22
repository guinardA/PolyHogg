package com.PolyHogg.model;

import com.PolyHogg.model.Player.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class PersonnageAnimation {

	/** Textures **/
	private TextureRegion idleLeft;
	private TextureRegion idleRight;
	private TextureRegion frame;
	
	/** Animations **/
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

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
		idleLeft = atlas.findRegion(couleur+"-01");
		idleRight = new TextureRegion(idleLeft);
		idleRight.flip(true, false);
		TextureRegion[] walkLeftFrames = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			walkLeftFrames[i] = atlas.findRegion(couleur+"-0" + (i + 2));
		}
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[5];

		for (int i = 0; i < 5; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
	}
	
	public TextureRegion getCurrentFrame(float delta){
		stateTime += delta;
		frame = player.isFacingLeft() ? idleLeft : idleRight;
		if(player.getState().equals(State.WALKING)) {
			frame = player.isFacingLeft() ? walkLeftAnimation.getKeyFrame(stateTime, true) : walkRightAnimation.getKeyFrame(stateTime, true);
		}
		return frame;
	}
	
	private void drawBob() {
		
	}

}
