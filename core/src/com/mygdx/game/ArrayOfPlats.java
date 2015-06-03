package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class ArrayOfPlats extends ApplicationAdapter {
    public static World world;
    Box2DDebugRenderer b2dr;
    Sprite sPlat;
    float fPpm = 100, fRandX, fRandY;
    double dPlatInc=0.8;
    public static final int nWidth = 320, nHeight = 240;
    OrthographicCamera camera;
    Body body;
    Array<Body> arPlats = new Array<Body>();
    public static final float STEP = 1 / 60f;
    Platform platform;

    @Override
    public void create() {
        platform = new Platform();
        for (int i = 0; i < 10; i++) {
            platform = new Platform();
//            body = world.createBody(platform.bdef);
  //          body.createFixture(platform.fdef);
            arPlats.add(platform);
        }
        for (int j = 0; j < arPlats.size; j++) {
            //  System.out.println(arPlats.get(j));
           // arPlats.get(j).createFixture(fdef);
            arPlats.get(j).setUserData(sPlat);
            System.out.println(dPlatInc);
            if (j == 0){
                System.out.println("Plat: "+j+" y: "+8/fPpm);
                arPlats.get(j).setTransform(120 / fPpm, 8 / fPpm, platform.getAngle());
            } else{
                fRandX= MathUtils.random()*(float)1.5;
                arPlats.get(j).setTransform((arPlats.get(0).getPosition().x)+fRandX , arPlats.get(j-1).getPosition().y+(float)dPlatInc, platform.getAngle());
                System.out.println("Plat: " + j + " y: " + (arPlats.get(j - 1).getPosition().y + dPlatInc));
            }
            arPlats.get(j).setUserData(sPlat);
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nWidth / fPpm, nHeight / fPpm);
    }

    @Override
    public void render() {
        camera.position.set(camera.position.x, camera.position.y, 0);
        camera.update();
        world.step(STEP, 6, 2);

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render b2d world
        b2dr.render(world, camera.combined);
        //   batch.setProjectionMatrix(camera.combined);
    }
}
