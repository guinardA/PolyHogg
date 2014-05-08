package com.PolyHogg.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Classe qui va charger les différents sprites
 * @author arnaud
 *
 */
public class AfficheSprite {

	/**
	 * Fonction qui va charger les différents sprites
	 * @param world
	 * @param camera
	 */
	public void afficher( OrthographicCamera camera, Body perso){
		SpriteBatch batch;

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		Sprite sprite = (Sprite) perso.getUserData();//On récuèpere le sprite lié au corps

		//On place les sprité au mêmes coordonnée que les block
		Vector2 position = perso.getPosition();
		sprite.setPosition(position.x,position.y);
		sprite.setRotation(perso.getAngle() * MathUtils.radiansToDegrees);

		//On dessine les sprites sur écran
		sprite.draw(batch);

		batch.end();

	}
}
