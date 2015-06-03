package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Justin on 2015-06-02.
 */
public class Platform extends Body {
    Vector2 vPlat1, vPlat2;
    int w, h;
    Box2DDebugRenderer b2dr;
    Sprite sPlat;
    float fPpm = 100;
    public static final short shGround = 2, shPlayer = 4;
    BodyDef bdef;
    FixtureDef fdef;
    PolygonShape shape;

    public Platform() {
        super(ArrayOfPlats.world, 100);
        vPlat1 = new Vector2(-15, 0);
        vPlat2 = new Vector2(15, 0);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        b2dr = new Box2DDebugRenderer();
        sPlat = new Sprite(new Texture("platform.png"));
        sPlat.setSize((28 / fPpm) * 5, (30 / fPpm) * 4);
        sPlat.setPosition(160 / fPpm, 10 / fPpm);
        bdef = new BodyDef();
        bdef.position.set(sPlat.getX(), sPlat.getY());
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(120 / fPpm, 8 / fPpm);
        shape = new PolygonShape();
        shape.setAsBox(50 / fPpm, 5 / fPpm);
        fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = shGround;
        fdef.filter.maskBits = shPlayer;
        ArrayOfPlats.world.createBody(bdef);
        this.createFixture(fdef);
    }
}