package com.PolyHogg.model;

import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Block {
	
	protected Rectangle bounds; //Limite d'un block
	
	public Block(Vector2 pos) {
   	 	bounds = new Rectangle();
        this.bounds.width = Constants.SIZE_BLOCK;
        this.bounds.height = Constants.SIZE_BLOCK;
   }
	
	protected abstract Rectangle getBounds();
}
