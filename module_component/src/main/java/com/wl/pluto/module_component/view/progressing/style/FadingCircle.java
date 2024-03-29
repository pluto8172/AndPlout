package com.wl.pluto.module_component.view.progressing.style;

import android.animation.ValueAnimator;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wl.pluto.module_component.view.progressing.animation.SpriteAnimatorBuilder;
import com.wl.pluto.module_component.view.progressing.sprite.CircleLayoutContainer;
import com.wl.pluto.module_component.view.progressing.sprite.CircleSprite;
import com.wl.pluto.module_component.view.progressing.sprite.Sprite;

/**
 * @author vondear
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class FadingCircle extends CircleLayoutContainer {

    @Override
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            dots[i].setAnimationDelay(100 * i + -1200);
        }
        return dots;
    }

    private class Dot extends CircleSprite {

        Dot() {
            setAlpha(0);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.39f, 0.4f, 1f};
            return new SpriteAnimatorBuilder(this).
                    alpha(fractions, 0, 0, 255, 0).
                    duration(1200).
                    easeInOut(fractions).build();
        }
    }
}
