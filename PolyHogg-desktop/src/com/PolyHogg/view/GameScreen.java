package com.PolyHogg.view;

import com.PolyHogg.controller.GameContactListener;
import com.PolyHogg.controller.PersonnageListener;
import com.PolyHogg.manager.LevelManager;
import com.PolyHogg.model.Player;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;


/**
 * Classe qui d��finie ��cran de jeu (Existe plusieurs types ��cran intro et game over)
 * @author arnaud
 */
public class GameScreen extends PolyHogScreen{
		//Sans Box2D
		private World world;
		private Box2DDebugRenderer debugRenderer;
		private Player player1;
		private Player player2;
		private OrthographicCamera camera;
		private PersonnageListener persoController;
		private GameContactListener gameController;
		private OrthogonalTiledMapRenderer mondeSprite;
		private LevelManager levelManager;

		//++++++++++++++++++++++++++++++++++++++++++++Fonction li��e a Screen
		@Override
		public void resize(int width, int height) {
		}

		@Override
		public void hide() {
			//Quand la f��netre est cach�� on d��sactive l'��coute
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
			//Quand la fenetre est en arri��re plan, on met le jeu en pause
			debugRenderer.dispose();
			world.dispose();
			Gdx.input.setInputProcessor(null);
			mondeSprite.dispose();
		}

		//Fonction lanc�� pour affich�� ��cran et ensuite lance le render
		@Override
		public void show() {
			
			debugRenderer = new Box2DDebugRenderer(); //Cr��ation du mode debug
			
			//Cr��ation de la cam��ra
			this.camera = new OrthographicCamera(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);//Monde de 10*8 blocks
			this.camera.position.set(Constants.WINDOWS_WIDTH/2, Constants.WINDOWS_HEIGHT/2, 0);//Centre de la cam��ra au mileu
			this.camera.update();
			
			//Chargement du niveau dans le world
			world = new World(new Vector2(0, -19.81f), true);//Cr��ation d'un monde avec un gravit�� a 9.81
			
			//Chargement du level
			levelManager = new LevelManager(world, "map");
			mondeSprite = levelManager.createWorld();
			this.loadWorld();	 
			
		}

		//Lanc�� directement apr��s la fonction show, cette fonction doit ��tre unique 
		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			//SPRITE
			final InterLevelScreen ils = new InterLevelScreen("player");
			OrthographicCamera camera2 = new OrthographicCamera();
			
			
			int largeur = Constants.WINDOWS_WIDTH*Constants.COTE_BLOCK;
			int hauteur = Constants.WINDOWS_HEIGHT*Constants.COTE_BLOCK;
			
			camera2.setToOrtho(false, largeur,hauteur);
			
			mondeSprite.setView(camera2);
			mondeSprite.render();

			if(player1.getFinish()){
				player1.setFinish(false);
				levelManager.setLevel(levelManager.getLevel()+1);
				world.setContactListener(null);
				levelManager.clearWorld();
				mondeSprite = levelManager.createWorld();
				this.loadWorld();
			}
			
			else if(player2.getFinish()){
				player2.setFinish(false);
				levelManager.setLevel(levelManager.getLevel()-1);
				world.setContactListener(null);
				levelManager.clearWorld();
				mondeSprite = levelManager.createWorld();
				this.loadWorld();
			}
			
			//Cas ou les 2 joueurs tombe dans le vide
			if(player1.getPlayer().getPosition().y < 0 && player2.getPlayer().getPosition().y <0){
				
				world.setContactListener(null);
				levelManager.clearWorld();
				mondeSprite = levelManager.createWorld();
				this.loadWorld();
			}

			
			if(!player1.getLife()){
				//Suppresion du personnage du decor mais erreur
				//world.destroyBody(player1.getPlayer());
				//player1.setLife(true);
				world.setContactListener(null);
				levelManager.setLevel(levelManager.getLevel()-1);
				levelManager.clearWorld();
				mondeSprite = levelManager.createWorld();
				this.loadWorld();
			}
			
			else if(!player2.getLife()){
				//Suppresion du personnage du decor mais erreur
				//world.destroyBody(player1.getPlayer());
				//player1.setLife(true);
				world.setContactListener(null);
				levelManager.setLevel(levelManager.getLevel()+1);
				levelManager.clearWorld();
				mondeSprite = levelManager.createWorld();
				this.loadWorld();
			}
			
			//A CHANGER AVEC ANIMATION
			player1.update(camera,delta);
			player2.update(camera,delta);
			
		    ///Affiche le mode debug
			debugRenderer.render(world, camera.combined);
			
			/* Step the simulation with a fixed time step of 1/60 of a second */
			world.step(1 / 60f, 6, 2);
			
		}
		
		private void loadWorld(){
			player1 = levelManager.getPlayer1();
			player2 = levelManager.getPlayer2();
			//Liste des ��couteurs a charger
			gameController = new GameContactListener(player1, player2);
			persoController = new PersonnageListener(player1, player2, gameController);
			world.setContactListener(gameController); 
			Gdx.input.setInputProcessor(persoController); //Ecouteur sur les diff��rents clique
		}

}
