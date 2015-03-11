package com.nonatomicgames.chinger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MandM on 08.03.2015.
 */
public class SimpleShot implements Shot {

    private static final int VELOCITY = 5;
    private static final float UPDATING_TIME = 0.02f;

    public boolean wasShoted = false;

    public Vector2 position = new Vector2();
    public Vector2 delta = new Vector2();

    private float lifeTime = 0f;


    public SimpleShot() {
        wasShoted = false;
    }


    @Override
    public void shoot(float spawnX, float spawnY) {
        this.position.x = spawnX;
        this.position.y = spawnY;
        this.lifeTime = 0f;
        this.wasShoted = true;
    }

    @Override
    public boolean onScreen() {
        if (wasShoted) {
            boolean onScreen = this.position.x > 0
                    && this.position.y > 0
                    && this.position.x < Constants.WORLD_WIDTH
                    && this.position.y < Constants.WORLD_HEIGHT;
            if (!onScreen) {
                wasShoted = false;
            }
            return onScreen;

        }
        return false;

    }

    @Override
    public void update(float delta) {
        lifeTime += delta;
        if (lifeTime >= UPDATING_TIME) {
            this.position.x += VELOCITY;
            this.lifeTime -= UPDATING_TIME;
        }
    }

    @Override
    public void render(SpriteBatch batcher) {
        batcher.draw(Assets.simpleShotRegion, this.position.x, this.position.y);
    }

    @Override
    public boolean enemyShot() {
        return false;
    }

}
