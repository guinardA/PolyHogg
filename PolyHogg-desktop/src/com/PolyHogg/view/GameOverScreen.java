package com.PolyHogg.view;



import com.PolyHogg.controller.MenuListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameOverScreen extends PolyMenu{

	private Stage stage;
	private Skin skin;
	private Table table;
	private TextureAtlas atlas;
	private String player;
	public TextButton buttonBack;
	
	public GameOverScreen(String player){
		this.player = player;
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {

		
	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("res/ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("res/ui/menuStyle.json"),atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		buttonBack = new TextButton("Retour", skin);
		buttonBack.pad(10);
		
		buttonBack.addListener(new MenuListener(this));
		
		table.add(player).row();
		table.add("remporte la partie.").spaceBottom(30).row();
		table.add(buttonBack);

		stage.addActor(table);
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}
}
