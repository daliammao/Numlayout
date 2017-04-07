package com.daliammao.numlayoutlib.builder;

import com.daliammao.numlayoutlib.config.LayoutConfig;
import com.daliammao.numlayoutlib.config.ViewConfig;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午7:35
 * @Email: 496946423@qq.com
 * @desc:
 */
public class LayoutConfigBuilder extends ViewConfigBuilder<LayoutConfigBuilder> {

    private LayoutConfig mLayoutConfig;

    public static LayoutConfigBuilder newLayoutConfigBuilder() {
        return new LayoutConfigBuilder(new LayoutConfig());
    }

    private LayoutConfigBuilder(LayoutConfig layoutConfig) {
        super(layoutConfig);
        mLayoutConfig = layoutConfig;
    }

    public LayoutConfigBuilder addChild(ViewConfig viewConfig) {
        mLayoutConfig.addChild(viewConfig);
        return this;
    }

    public LayoutConfigBuilder setOrientation(@LayoutConfig.Orientation int orientation){
        mLayoutConfig.setOrientation(orientation);
        return this;
    }

    public LayoutConfig build(){
        checkSize();
        return mLayoutConfig;
    }
}
