package zblol.app.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by macbook on 29.07.2020.
 */

public class Background {
    SpriteBatch batch;
    Texture dieBackground;
    Texture start;
    Texture background;

    Background(SpriteBatch batch) {
        this.dieBackground = new Texture("backgroundFire.png");
        this.start = new Texture("start.png");
        this.background = new Texture("background.png");
        this.batch = batch;
    }

    void drawBackground(int gameState) {
        switch (gameState) {
            case 0:
                batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                batch.draw(start,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
                break;
            case 2:
                batch.draw(dieBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
                break;
            default:
                batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                break;
        }
    }
}