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
public class CircleSprite extends ShapeSprite {

    @Override
    public ValueAnimator onCreateAnimation() {
        return null;
    }


    @Override
    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            int radius = Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2;
            canvas.drawCircle(getDrawBounds().centerX(),
                    getDrawBounds().centerY(),
                    radius, paint);
        }
    }
}
