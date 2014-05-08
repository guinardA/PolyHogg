package com.PolyHogg.ressource;

import com.PolyHogg.utils.VariableGeneral;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe qui definie les propriete d'un block classique
 * On ne peut pas traverser ce type de block
 * @author arnaud
 *
 */
public class Block {
	
    static Rectangle   bounds = new Rectangle(); //Limite d'un block
 
    public Block(Vector2 pos) {
         this.bounds.width = VariableGeneral.SIZE_BLOCK;
         this.bounds.height = VariableGeneral.SIZE_BLOCK;
    }

	public static Rectangle getBounds() {
		return bounds;
	}
    
}
