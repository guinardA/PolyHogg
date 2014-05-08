package com.PolyHogg.view;

import com.PolyHogg.controller.PersoController;
import com.PolyHogg.model.ChargementNiveau;
import com.PolyHogg.model.ChargementPersonnage;
import com.PolyHogg.utils.VariableGeneral;
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
public class GameScreen implements Screen{
		//Sans Box2D
		private ChargementPersonnage ChargPerso;
		private World world;
		private Box2DDebugRenderer debugRenderer;
		private Body player;//Personnage du monde
		private OrthographicCamera camera;
		private AfficheSprite afficheSprite;
		private PersoController persoController;
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
			this.camera = new OrthographicCamera(VariableGeneral.LARGEUR_FENETRE, VariableGeneral.HAUTEUR_FENETRE);//Monde de 10*8 blocks
			this.camera.position.set(VariableGeneral.LARGEUR_FENETRE/2, VariableGeneral.HAUTEUR_FENETRE/2, 0);//Centre de la caméra au mileu
			this.camera.update();
			
			//Chargement du niveau dans le world
			world = new World(new Vector2(0, -9.81f), true);//Création d'un monde avec un gravité a 9.81
			ChargPerso = new ChargementPersonnage(1);//Chargement du niveau 1
			mondeSprite = ChargementNiveau.chargerDecor(world); //Chargement du decor
			player = ChargementNiveau.chargerPersonnage(world, ChargPerso, 1);//Chargement du personnage
			
			//Liste des écouteurs a charger
			persoController = new PersoController(player, ChargPerso);
			world.setContactListener(persoController); 
			Gdx.input.setInputProcessor(persoController); //Ecouteur sur les différents clique
				 
			
		}

		//Lancé directement après la fonction show, cette fonction doit être unique 
		@Override
		public void render(float delta) {
			
			Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			//SPRITE
			
			OrthographicCamera camera2 = new OrthographicCamera();
			
			int largeur = VariableGeneral.LARGEUR_FENETRE*VariableGeneral.COTE_BLOCK;
			int hauteur = VariableGeneral.HAUTEUR_FENETRE*VariableGeneral.COTE_BLOCK;
			
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
