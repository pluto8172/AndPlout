package com.wl.pluto.module_component.view.progressing.style;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.wl.pluto.module_component.view.progressing.sprite.Sprite;
import com.wl.pluto.module_component.view.progressing.sprite.SpriteContainer;

/**
 * @author vondear
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class MultiplePulse extends SpriteContainer {
    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new Pulse(),
                new Pulse(),
                new Pulse(),
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].setAnimationDelay(200 * (i + 1));
        }
    }
}
