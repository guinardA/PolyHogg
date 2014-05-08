package com.PolyHogg.model;

import com.PolyHogg.ressource.Perso;
import com.PolyHogg.utils.Box2Dfactory;
import com.PolyHogg.utils.VariableGeneral;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Classe qui a pour role de charger un niveau au monde 
 * @author arnaud
 */
public class ChargementNiveau {

	/**
	 * Fonction statique qui charge ensemble du decors du niveau
	 * @param world
	 * @param niveau
	 */
	public static OrthogonalTiledMapRenderer chargerDecor(World world) {


		TiledMap tileMap;
		float tileSize;
		OrthogonalTiledMapRenderer tmr;

		//BLOCK BASE

		//Chargement du niveau dessine
		tileMap = new TmxMapLoader().load("res/map/map.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);

		//Recuperation du layer correspondant a tout les blocs de la map
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("statique");
		tileSize = layer.getTileWidth();

		for( int row=0;row<layer.getHeight();row++){
			
			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col , row);
				
				//Création des différentes primitive
				if( cell != null && cell.getTile() != null ){
					Vector2[] v = new Vector2[5];
					v[0] = new Vector2( -VariableGeneral.SIZE_BLOCK/2, -VariableGeneral.SIZE_BLOCK/2);//BG
					v[1] = new Vector2( -VariableGeneral.SIZE_BLOCK/2, VariableGeneral.SIZE_BLOCK/2);//HG
					v[2] = new Vector2( VariableGeneral.SIZE_BLOCK/2, VariableGeneral.SIZE_BLOCK/2);//HD
					v[3] = new Vector2( VariableGeneral.SIZE_BLOCK/2, -VariableGeneral.SIZE_BLOCK/2);//BD
					v[4] = new Vector2( -VariableGeneral.SIZE_BLOCK/2, -VariableGeneral.SIZE_BLOCK/2);//BG

					Vector2 position = new Vector2((col + VariableGeneral.SIZE_BLOCK/2) , (row + VariableGeneral.SIZE_BLOCK/2));
					
					//Factory qui créer un corps bloc
					Box2Dfactory.BlockNormal(v, position, world);
					
				}
			}
		}
		
		return tmr;
	}

	/**
	 * Fonction statique qui charge le personnage au monde (si i == 1 alors perso 1 et si i == 2 perso 2)
	 * @param world
	 * @param niveau
	 * @param i
	 * @return retourne le corps personnage
	 */
	public static Body chargerPersonnage(World world, ChargementPersonnage ChargPerso, int i) {

		Body player;
		Sprite sprite;

		//Chargement du sprite perosnnage
		Perso perso = ChargPerso.getPerso();
		sprite = new Sprite(new Texture("images/bob_01.png"));
		sprite.setSize(perso.getBounds().width, perso.getBounds().height);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		
		player = Box2Dfactory.Personnage(sprite, perso, world);

		return player;

	}
}
