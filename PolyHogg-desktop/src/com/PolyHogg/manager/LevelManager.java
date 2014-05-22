package com.PolyHogg.manager;

import com.PolyHogg.model.Player;
import com.PolyHogg.utils.BodyFactory;
import com.PolyHogg.utils.Constants;
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
public class LevelManager {

	private World world;
	private int level;
	private String dossier;
	private Player player1;
	private Player player2;

	//Variable pour le monde
	TiledMap tileMap;
	float tileSize;
	OrthogonalTiledMapRenderer tmr;
	TiledMapTileLayer layer;

	public LevelManager(World world, String dossier){
		this.world = world;
		level = 5; //Les niveaux de vont de 1 à 9 et de gauche a droite
		this.dossier = dossier;
	}


	public OrthogonalTiledMapRenderer createWorld(){

		//Chargement du level
		String url = "res/"+dossier+"/level"+String.valueOf(level)+".tmx";
		tileMap = new TmxMapLoader().load(url);
		tmr = new OrthogonalTiledMapRenderer(tileMap);

		//Mise en place des différents block


		//Block statique
		layer = (TiledMapTileLayer) tileMap.getLayers().get("statique");
		tileSize = layer.getTileWidth();

		for( int row=0;row<layer.getHeight();row++){

			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col , row);

				//Création des différentes primitive
				if( cell != null && cell.getTile() != null ){
					Vector2[] v = new Vector2[5];
					v[0] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG
					v[1] = new Vector2( -Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HG
					v[2] = new Vector2( Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HD
					v[3] = new Vector2( Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BD
					v[4] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG

					Vector2 position = new Vector2((col + Constants.SIZE_BLOCK/2) , (row + Constants.SIZE_BLOCK/2));

					//Factory qui créer un corps bloc

					BodyFactory.createBlock(v, position, world, "statique");
				}
			}
		}

		//Block Dynamique
		layer = (TiledMapTileLayer) tileMap.getLayers().get("ralentisseur");
		tileSize = layer.getTileWidth();

		for( int row=0;row<layer.getHeight();row++){

			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col , row);

				//Création des différentes primitive
				if( cell != null && cell.getTile() != null ){
					Vector2[] v = new Vector2[5];
					v[0] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG
					v[1] = new Vector2( -Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HG
					v[2] = new Vector2( Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HD
					v[3] = new Vector2( Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BD
					v[4] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG

					Vector2 position = new Vector2((col + Constants.SIZE_BLOCK/2) , (row + Constants.SIZE_BLOCK/2));

					//Factory qui créer un corps bloc
					BodyFactory.createBlock(v, position, world, "ralentisseur");
				}
			}
		}

		//Porte droite
		layer = (TiledMapTileLayer) tileMap.getLayers().get("pd");
		tileSize = layer.getTileWidth();

		for( int row=0;row<layer.getHeight();row++){

			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col , row);

				//Création des différentes primitive
				if( cell != null && cell.getTile() != null ){
					Vector2[] v = new Vector2[5];
					v[0] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG
					v[1] = new Vector2( -Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HG
					v[2] = new Vector2( Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HD
					v[3] = new Vector2( Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BD
					v[4] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG

					Vector2 position = new Vector2((col + Constants.SIZE_BLOCK/2) , (row + Constants.SIZE_BLOCK/2));

					//Factory qui créer un corps bloc
					BodyFactory.createBlock(v, position, world, "pd");
				}
			}
		}

		//Porte Gauche
		layer = (TiledMapTileLayer) tileMap.getLayers().get("pg");
		tileSize = layer.getTileWidth();

		for( int row=0;row<layer.getHeight();row++){

			for(int col=0;col<layer.getWidth();col++){
				Cell cell = layer.getCell(col , row);

				//Création des différentes primitive
				if( cell != null && cell.getTile() != null ){
					Vector2[] v = new Vector2[5];
					v[0] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG
					v[1] = new Vector2( -Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HG
					v[2] = new Vector2( Constants.SIZE_BLOCK/2, Constants.SIZE_BLOCK/2);//HD
					v[3] = new Vector2( Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BD
					v[4] = new Vector2( -Constants.SIZE_BLOCK/2, -Constants.SIZE_BLOCK/2);//BG

					Vector2 position = new Vector2((col + Constants.SIZE_BLOCK/2) , (row + Constants.SIZE_BLOCK/2));

					//Factory qui créer un corps bloc
					BodyFactory.createBlock(v, position, world, "pg");
				}
			}
		}
		
		//Mise en place des players
		player1 = new Player(new Vector2(24, 2)); //Position de départ du personnage
		player1.createCorps(world);
		
		player2 = new Player(new Vector2(1, 2)); //Position de départ du personnage
		player2.createCorps(world);
		
		return tmr;
	}
	
	public Player getPlayer1(){
		return player1;
	}
	
	public Player getPlayer2(){
		return player2;
	}
	
	public void setLevel(int i){
		this.level = i;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setTextureWorld(){
	}
}
