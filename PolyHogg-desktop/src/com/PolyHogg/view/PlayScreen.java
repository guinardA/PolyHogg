package com.PolyHogg.view;

import com.PolyHogg.controller.MenuListener;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class PlayScreen extends PolyMenu{

	private Stage stage;
	private Skin skin;
	private Table table;	//pour placer les éléments sur l'écran
	public TextButton buttonQuick, buttonCustom, buttonTourn, buttonBack;
	private Label heading;
	private TextureAtlas atlas;
	
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
		
	}

	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = new TextureAtlas("res/ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("res/ui/menuStyle.json"), atlas);
		table = new Table(skin);
		table.setBounds(0, 0, Constants.WINDOWS_WIDTH*Constants.COTE_BLOCK, Constants.WINDOWS_HEIGHT*Constants.COTE_BLOCK);

		buttonQuick = new TextButton("Rapide", skin);
		buttonCustom = new TextButton("Personnalisé", skin);
		buttonTourn = new TextButton("Tournoi", skin);
		buttonBack = new TextButton("Retour", skin);
		
		buttonQuick.pad(5, 0, 5, 0);
		buttonCustom.pad(5, 2, 5, 1);
		buttonTourn.pad(5, 0, 5, 0);
		buttonBack.pad(5, 0, 5, 0);
		
		buttonQuick.addListener(new MenuListener(this));
		buttonCustom.addListener(new MenuListener(this));
		buttonBack.addListener(new MenuListener(this));
		buttonTourn.addListener(new MenuListener(this));
		
		heading = new Label("Type de Jeu", skin);
		heading.setAlignment(Align.center);
		
		table.add().width(table.getWidth()/3);
		table.add(heading).width(table.getWidth()/3).center();
		table.add().width(table.getWidth()/3).row();
		table.getCell(heading).spaceBottom(50);
		table.add();
		table.add(buttonQuick).width(buttonCustom.getWidth());
		table.add().row();
		table.add();
		table.add(buttonCustom);
		table.add().row();
		table.add();
		table.add(buttonTourn).width(buttonCustom.getWidth());
		table.add().row();
		table.add();
		table.add(buttonBack).width(buttonCustom.getWidth());
		table.add();
		
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
