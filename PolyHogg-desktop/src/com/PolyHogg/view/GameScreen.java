package com.PolyHogg.view;

import com.PolyHogg.controller.GameContactListener;
import com.PolyHogg.controller.PersonnageListener;
import com.PolyHogg.manager.ChargementPersonnage;
import com.PolyHogg.manager.LevelManager;
import com.PolyHogg.temporaire.AfficheSprite;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Classe qui définie écran de jeu (Existe plusieurs types écran intro et game over)
 * @author arnaud
 */
public class GameScreen extends PolyHogScreen{
		//Sans Box2D
		private ChargementPersonnage ChargPerso;
		private ChargementPersonnage ChargPerso2;
		private World world;
		private Box2DDebugRenderer debugRenderer;
		private Body player;//Personnage du monde
		private Body player2;//Personnage du monde
		private OrthographicCamera camera;
		private AfficheSprite afficheSprite;
		private PersonnageListener persoController;
		private GameContactListener gameController;
		private OrthogonalTiledMapRenderer mondeSprite;

		//++++++++++++++++++++++++++++++++++++++++++++Fonction liée a Screen
		@Override
		public void resize(int width, int height) {
		}

		@Override
		public void hide() {
			//Quand la fênetre est caché on désactive l'écoute
			Gdx.input.setInputProcessor(null);
		}

		@Override
		public void pause() {
		}

		@Override
		public void resume() {	
		}

		@Override
		public void dispose() {
			//Quand la fenetre est en arrière plan, on met le jeu en pause
			debugRenderer.dispose();
			world.dispose();
			Gdx.input.setInputProcessor(null);	
		}

		//Fonction lancé pour affiché écran et ensuite lance le render
		@Override
		public void show() {
			
			debugRenderer = new Box2DDebugRenderer(); //Création du mode debug
			
			//Création de la caméra
			this.camera = new OrthographicCamera(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);//Monde de 10*8 blocks
			this.camera.position.set(Constants.WINDOWS_WIDTH/2, Constants.WINDOWS_HEIGHT/2, 0);//Centre de la caméra au mileu
			this.camera.update();
			
			//Chargement du niveau dans le world
			world = new World(new Vector2(0, -19.81f), true);//Création d'un monde avec un gravité a 9.81
			
			ChargPerso = new ChargementPersonnage(1,1);//Chargement du niveau 1
			ChargPerso2 = new ChargementPersonnage(1,2);//Chargement du niveau 1
			
			mondeSprite = LevelManager.chargerDecor(world); //Chargement du decor
			
			player = LevelManager.chargerPersonnage(world, ChargPerso, 1);//Chargement du personnage
			
			player2 = LevelManager.chargerPersonnage(world, ChargPerso2, 1);//Chargement du personnage
			
			//Liste des écouteurs a charger
			gameController = new GameContactListener(player, ChargPerso);
			persoController = new PersonnageListener(player, player2, gameController);
			world.setContactListener(gameController); 
			Gdx.input.setInputProcessor(persoController); //Ecouteur sur les différents clique
				 
			
		}

		//Lancé directement après la fonction show, cette fonction doit être unique 
		@Override
		public void render(float delta) {
			
			Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			//SPRITE
			
			OrthographicCamera camera2 = new OrthographicCamera();
			
			int largeur = Constants.WINDOWS_WIDTH*Constants.COTE_BLOCK;
			int hauteur = Constants.WINDOWS_HEIGHT*Constants.COTE_BLOCK;
			
			camera2.setToOrtho(false, largeur,hauteur);
			mondeSprite.setView(camera2);
			mondeSprite.render();
			
			afficheSprite = new AfficheSprite();
			afficheSprite.afficher(camera, player);
			
		    ///Affiché le mode debug
			debugRenderer.render(world, camera.combined);
			
			/* Step the simulation with a fixed time step of 1/60 of a second */
			world.step(1 / 40f, 6, 2);
			
		}
}
