package com.wl.pluto.module_component.view.progressing.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * @author vondear
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class RectSprite extends ShapeSprite {
    @Override
    public ValueAnimator onCreateAnimation() {
        return null;
    }


    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            canvas.drawRect(getDrawBounds(), paint);
        }
    }
}
