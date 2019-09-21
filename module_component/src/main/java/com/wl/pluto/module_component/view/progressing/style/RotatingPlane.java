package com.wl.pluto.module_component.view.progressing.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wl.pluto.module_component.view.progressing.animation.SpriteAnimatorBuilder;
import com.wl.pluto.module_component.view.progressing.sprite.RectSprite;


/**
 * @author vondear
 */
public class RotatingPlane extends RectSprite {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onBoundsChange(Rect bounds) {
        setDrawBounds(clipSquare(bounds));
    }

    @Override
    public ValueAnimator onCreateAnimation() {
        float fractions[] = new float[]{0f, 0.5f, 1f};
        return new SpriteAnimatorBuilder(this).
                rotateX(fractions, 0, -180, -180).
                rotateY(fractions, 0, 0, -180).
                duration(1200).
                easeInOut(fractions)
                .build();
    }
}
