package com.imaginchallenge.cookiefetch.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by Jose on 02/06/2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currFrameTime;
    private int numFrames;
    private int currFrame;

    public Animation(TextureRegion texturesTmp, int numFrames, float cycleTime) {
        this.frames = new Array<TextureRegion>();
        this.numFrames = numFrames;
        int frameWidth = texturesTmp.getRegionWidth() / numFrames;
        for (int i = 0; i < numFrames; i++) {
            this.frames.add(new TextureRegion(texturesTmp, i * frameWidth, 0, frameWidth, texturesTmp.getRegionHeight()));
        }

        this.maxFrameTime = cycleTime / numFrames;
        this.currFrame = 0;
    }

    public void update(float delta) {
        currFrameTime += delta;
        if (currFrameTime > maxFrameTime) {
            currFrame++;
            currFrameTime = 0;
        }
        if (currFrame >= numFrames) {
            currFrame = 0;
        }
    }

    public TextureRegion getCurrFrame() {
        return frames.get(currFrame);
    }

    public void dispose() {

    }

}