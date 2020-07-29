package zblol.app.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by macbook on 29.07.2020.
 */

public class Audio {
    Music backMusic;
    Music fireSound;

    Audio() {
        this.backMusic = Gdx.audio.newMusic(Gdx.files.internal("Theme.mp3"));
        this.fireSound = Gdx.audio.newMusic(Gdx.files.internal("fire1.mp3"));
        backMusic.setLooping(true);
    }

    void setupAudio(int gameState) {
        backMusic.play();
        switch (gameState) {
            case 1:
                fireSound.stop();
                break;
            case 2:
                backMusic.stop();
                fireSound.play();
                break;
            default: break;
        }
    }

    void stop() {
        backMusic.dispose();
        fireSound.dispose();
    }
}

