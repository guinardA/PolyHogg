package com.PolyHogg.model;

import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe qui definie les propriete d'un block classique
 * On ne peut pas traverser ce type de block
 * @author arnaud
 *
 */
public class BlockStatic extends Block{
 
    public BlockStatic(Vector2 pos, String too) {
    	super(pos);
    }

	public Rectangle getBounds() {
		return bounds;
	}
    
}
