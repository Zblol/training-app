package zblol.app.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class SkullMan extends ApplicationAdapter {
	SpriteBatch batch;

	Background background;
	Audio audio;
	Man man;


	Texture gameOver;
	Sound bonesSound;

	//SKULLMAN



	//SKULLS
	ArrayList<Integer> skullXs= new ArrayList<Integer>();
	ArrayList<Integer> skullYs= new ArrayList<Integer>();
	ArrayList<Rectangle> skullRectangles = new ArrayList<Rectangle>();
	Texture skull;
	int skullCount;
	Random random;
	//FIRE
	ArrayList<Integer> fireXs = new ArrayList<Integer>();
	ArrayList<Integer> fireYs = new ArrayList<Integer>();
	ArrayList<Rectangle> fireRectangles = new ArrayList<Rectangle>();
	Texture fire;
	int fireCount;
	//Score
	int score = 0;
	int gameState = 0;
	BitmapFont font ;



	@Override
	public void create () {
		batch = new SpriteBatch();

		background = new Background(batch);
		audio = new Audio();
		man = new Man(batch);

		gameOver = new Texture("gameOver.png");
		bonesSound = Gdx.audio.newSound(Gdx.files.internal("bones1.mp3"));


		///Skulls
		skull = new  Texture("skull.png");
		random = new Random();
		//Fire
		fire = new Texture("fire.png");
		//Score
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(10);

	}

	private void makeFire(){
		fireYs.add(man.getManY());
		fireXs.add(Gdx.graphics.getWidth());
	}

	private void makeSkull(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		skullYs.add((int)height);
		skullXs.add(Gdx.graphics.getWidth());

	}

	/// SETUP methods

	private void drawScore() {
		font.draw(batch,String.valueOf(score),100,200);
	}

	private void drawGameOverBackground(int gameState) {
		if (gameState == 2) {
			batch.draw(gameOver, 200,800, 700,700);
		}
	}

	/// Game engine methods

	private void gameEngine(int gameState) {
		switch (gameState) {
			case 0:
				//Waiting game
				startGame();
				break;
			case 1:
				//Game turnON!
				playGame();
				break;
			case 2:
				//GameOver
				resetGame();
				break;
			default: break;
		}
	}

	private void startGame() {
		man.setManY(-500);
		if(Gdx.input.justTouched()) {
			this.gameState = 1;
		}
	}

	private void resetGame() {
		if (Gdx.input.justTouched()) {
			gameState = 1;
			man.resetMan();
			score =0;
			man.velocity = 0;
			skullXs.clear();
			skullYs.clear();
			skullRectangles.clear();
			skullCount = 0;
			fireXs.clear();
			fireYs.clear();
			fireRectangles.clear();
			fireCount= 0;
		}
	}

	private void playGame() {

		if (fireCount < 250 ) {
			fireCount++;
		} else {
			fireCount = 0;
			makeFire();
		}

		for (int i = 0; i < fireXs.size();i++){
			batch.draw(fire,fireXs.get(i),fireYs.get(i));
			fireXs.set(i,fireXs.get(i) - 10);
			fireRectangles.add(new Rectangle(fireXs.get(i),fireYs.get(i), 200,50));
			if(fireXs.get(i) < 0){
				fireRectangles.remove(i);
				fireYs.clear();
				fireXs.clear();
				fireRectangles.clear();
				Gdx.app.log("FIRE REMOVE"," Fire REMOVE");
			}

		}
//SKULLs
		if (skullCount < 100) {
			skullCount++;
		} else {
			skullCount = 0;
			makeSkull();
		}
		skullRectangles.clear();
		for(int i = 0; i < skullXs.size();i++){
			batch.draw(skull,skullXs.get(i),skullYs.get(i));
			skullXs.set(i,skullXs.get(i)- 4 );
			skullRectangles.add(new Rectangle(skullXs.get(i),skullYs.get(i),skull.getWidth(),skull.getHeight()));
		}
//Touch method
		man.touchMethod();

		for (int i = 0; i < skullRectangles.size();i++) {
			if (Intersector.overlaps(man.skullmanRectangle, skullRectangles.get(i))) {
				bonesSound.play();
				score++;
				skullRectangles.remove(i);
				skullXs.remove(i);
				skullYs.remove(i);
				break;
			}
		}

		for (int i = 0; i < fireRectangles.size();i++) {
			if (Intersector.overlaps(man.skullmanRectangle, fireRectangles.get(i))) {
				Gdx.app.log("FIRE!", "You're die");
				gameState = 2;
			}
		}

	}

	@Override
	public void render () {
		audio.setupAudio(gameState);

		batch.begin();

		background.drawBackground(gameState);
		man.drawMan(gameState);

		drawGameOverBackground(gameState);
		man.drawManRectangle();
		drawScore();

		gameEngine(gameState);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		audio.stop();
		bonesSound.dispose();
	}
}
