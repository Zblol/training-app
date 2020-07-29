package zblol.app.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by macbook on 29.07.2020.
 */

public class Man {
    Texture man[];
    SpriteBatch batch;
    int pause = 0;
    float gravity = 0.2f;
    float velocity = 0;

    public int getManY() {
        return manY;
    }

    public void setManY(int manY) {
        this.manY = manY;
    }

    private int manY;
    int manState;
    Rectangle skullmanRectangle;

    Man(SpriteBatch batch) {
        this.batch = batch;
        this.manY = 0;
        this.manState = 0;
        //SkullMan
        this.man = new Texture[5];
        this.man[0] = new Texture("startrun.png");
        this.man[1] = new Texture("secondrun.png");
        this.man[2] = new Texture("threerun.png");
        this.man[3] = new Texture("Rip.png");
        this.man[4] = new Texture("jump.png");

        this.manY = Gdx.graphics.getHeight() - man[manState].getHeight();
    }

    void drawMan(int gameState) {
        switch (gameState) {
            case 2:
                batch.draw(man[3],Gdx.graphics.getWidth()/3- man[manState].getWidth()/2, manY);
                break;
            default:
                batch.draw(man[manState], Gdx.graphics.getWidth()/3  - man[manState].getWidth()/2, manY);
                break;
        }
    }

    void resetMan() {
        this.manY = Gdx.graphics.getHeight()/3 - man[manState].getHeight();
    }

    void drawManRectangle() {
        this.skullmanRectangle = new Rectangle(Gdx.graphics.getWidth()/3 - man[manState].getWidth()/2,manY,200,250);
    }


    void touchMethod(){
        if (Gdx.input.justTouched()) {
            velocity = -10;

        } else if(getManY() >= 1400 ) {
            velocity = 0;
        }
        if (pause < 6 ) {
            pause++;
        } else {
            pause = 0;
            if (manState < 2 ){
                manState++;
            } else {
                manState = 0;
            }
        }
        velocity += gravity;
        manY -= velocity;
        if( manY <= 0){
            manY = 0;
        }
    }
}


