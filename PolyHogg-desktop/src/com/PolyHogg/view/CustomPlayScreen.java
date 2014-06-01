package com.PolyHogg.view;

import com.PolyHogg.controller.MenuListener;
import com.PolyHogg.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class CustomPlayScreen extends PolyMenu{

	private Stage stage;
	private Skin skin;
	private Table table;
	private Label gravityText, grenadesText, durationText, scoreText, difficutlyText;
	public Label heading;
	private TextureAtlas atlas;
	public TextButton buttonOk, buttonBack;
	public TextField gravityField, grenadesField, durationField, scoreField, difficultyField;
	
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

		heading = new Label("Partie personnalisee", skin);
		
		gravityText = new Label("Gravite", skin);
		grenadesText = new Label("Grenades", skin);
		grenadesText.setColor(Color.GRAY);
		durationText = new Label("Duree", skin);
		scoreText = new Label("ScoreMax", skin);
		difficutlyText = new Label("Difficulte", skin);
		
		TextFieldStyle fieldStyle = new TextFieldStyle();
		fieldStyle.font = new BitmapFont(Gdx.files.internal("res/font/white.fnt"));
		fieldStyle.fontColor = new Color(1, 1, 1, 1);
		
		TextFieldStyle fieldStyle2 = new TextFieldStyle();
		fieldStyle2.font = new BitmapFont(Gdx.files.internal("res/font/white.fnt"));
		fieldStyle2.fontColor = Color.GRAY;
		
		gravityField = new TextField(Float.toString(Constants.GRAVITY), fieldStyle);
		grenadesField = new TextField(Integer.toString(Constants.NB_GRENADES), fieldStyle2);
		grenadesField.setDisabled(true);
		durationField = new TextField(Integer.toString(Constants.GAME_DURATION), fieldStyle);
		scoreField = new TextField(Integer.toString(Constants.MAX_SCORE), fieldStyle);
		difficultyField = new TextField(Integer.toString(Constants.DIFFICULTY), fieldStyle);
		
		buttonOk = new TextButton("Valider", skin);
		buttonBack = new TextButton("Retour", skin);
		buttonOk.pad(10);
		buttonBack.pad(10);
		
		buttonOk.addListener(new MenuListener(this));
		buttonBack.addListener(new MenuListener(this));
		
		table.add(heading).colspan(2).center().row();
		table.getCell(heading).spaceBottom(10);
		table.add(gravityText);
		table.add(gravityField).center().fill().row();
		table.add(grenadesText);
		table.add(grenadesField).center().row();
		table.add(durationText);
		table.add(durationField).center().row();
		table.add(scoreText);
		table.add(scoreField).center().row();
		table.add(difficutlyText);
		table.add(difficultyField).center().row();
		table.add(buttonOk);
		table.add(buttonBack);
		
		//table.debug();
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
	
	public boolean isEmpty(){
		boolean ret = false;
		if((gravityField.getText().equals("")) && (grenadesField.getText().equals(""))&& (durationField.getText().equals(""))&& (scoreField.getText().equals(""))&& (difficultyField.getText().equals(""))){
			ret = true;
		}
		
		return ret;
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}
}
