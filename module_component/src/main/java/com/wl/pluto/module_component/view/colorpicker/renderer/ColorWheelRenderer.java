package com.wl.pluto.module_component.view.colorpicker.renderer;


import com.wl.pluto.module_component.view.colorpicker.ColorCircle;

import java.util.List;

/**
 * @author vondear
 * @date 2018/6/11 11:36:40 整合修改
 */
public interface ColorWheelRenderer {
    float GAP_PERCENTAGE = 0.025f;

    void draw();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);

    List<ColorCircle> getColorCircleList();
}
