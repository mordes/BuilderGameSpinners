package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Loading.LoadingScreen;
import com.mygdx.game.MyBaseClasses.MyScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class MyGdxGame extends Game {

	//zene?
	//csata


	public final Stack<Class> backButtonStack = new Stack();

	public Label.LabelStyle getLabelStyle() {
		return getLabelStyle(100, Color.YELLOW);
	}

	public Label.LabelStyle getLabelStyle(int size) {
		return getLabelStyle(size, Color.YELLOW);
	}

	public Label.LabelStyle getLabelStyle(int size, Color color) {
		Label.LabelStyle style;
		style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
		style.fontColor = color;
		switch (size){
			case 10:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT10);
				break;
			case 25:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT25);
				break;
			case 50:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT50);
				break;
			case 80:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT80);
				break;
			case 100:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT100);
				break;
			case 140:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT140);
				break;
			default:
				System.out.println("Nem kezelt betűméret!");
		}
		return style;
	}


	public List.ListStyle getListStyle(){
		List.ListStyle l = new List.ListStyle();
		l.selection = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONUP)));
		l.font =  Assets.manager.get(Assets.ACMEREGULAR_FONT50);
		l.fontColorSelected = Color.CYAN;
		l.fontColorUnselected = Color.GOLD;
		//l.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXT_BG)));

		return l;
	}

	public ScrollPane.ScrollPaneStyle getScrollPaneStyle(){
		ScrollPane.ScrollPaneStyle s = new ScrollPane.ScrollPaneStyle();
		s.background = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BATTLE_BG)));
		return s;
	}

	public TextButton.TextButtonStyle getTextButtonStyle(int size) {

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT100);

		/*Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
		p.setColor(0.1f,0.2f,0.2f, 0.5f);
		p.fill();*/
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONUP)));


		/*p.setColor(0.3f,0.5f,0.8f, 0.5f);
		p.fill();*/
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONOVER)));

		/*p.setColor(1f,0.5f,0.8f, 1f);
		p.fill();*/
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONDOWN)));

		switch (size){
			case 10:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT10);
				break;
			case 25:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT25);
				break;
			case 50:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT50);
				break;
			case 80:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT80);
				break;
			case 100:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT100);
				break;
			case 140:
				textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT140);
				break;
			default:
				System.out.println("Nem kezelt betűméret!");
		}

		return textButtonStyle;
	}

	public TextField.TextFieldStyle getTextFieldStyle(){
		TextField.TextFieldStyle style;
		style = new TextField.TextFieldStyle();
		style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT50);

		style.fontColor = Color.BLACK;
		Pixmap p = new Pixmap(1,1, Pixmap.Format.RGBA8888);

		p.setColor(Color.BLACK);
		p.fill();
		style.cursor = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
		style.cursor.setMinWidth(10);

		p.setColor(Color.WHITE);
		p.fill();
		style.background = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

		return style;
	}


	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void setScreen(Screen screen) {
		setScreen(screen,true);
	}

	public void setScreenBackByStackPop(){
		if (backButtonStack.size()>1){
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this),false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else
		{
			Gdx.app.exit();
		}
	}


	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen!=null) {
			if (pushToStack) {backButtonStack.push(prevScreen.getClass());}
			try {
				prevScreen.dispose();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		super.setScreen(screen);
	}

}
