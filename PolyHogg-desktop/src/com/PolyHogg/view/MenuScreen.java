package com.PolyHogg.view;

import com.PolyHogg.controller.MenuListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends PolyMenu{

	private Stage stage;
	private Skin skin;
	private Table table;	//pour placer les éléments sur l'écran
	public TextButton buttonPlay, buttonOption, buttonExit;
	private Label heading;
	private TextureAtlas atlas;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuStyle.json"),atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		heading = new Label("PolyHogg", skin);
		heading.setFontScale((float) 1.5);
		
		buttonPlay = new TextButton("Jouer", skin);
		buttonOption = new TextButton("Options", skin);
		buttonExit = new TextButton("Quitter", skin);
		
		buttonPlay.pad(20);
		buttonOption.pad(20);
		buttonExit.pad(20);
		
		MenuListener menuLis = new MenuListener(this);
		
		buttonPlay.addListener(menuLis);
		buttonOption.addListener(menuLis);
		buttonExit.addListener(menuLis);
		
		table.add(heading);
		table.getCell(heading).spaceBottom(50);
		table.row();
		table.add(buttonPlay).width(299); //solution temporaire pour le probleme de taille du bouton Play
		table.row();
		table.add(buttonOption);
		table.row();
		table.add(buttonExit);
		
		table.debug();
		stage.addActor(table);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
