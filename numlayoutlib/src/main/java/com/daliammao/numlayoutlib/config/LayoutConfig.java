package com.daliammao.numlayoutlib.config;

import android.support.annotation.IntDef;
import android.widget.LinearLayout;

import com.daliammao.numlayoutlib.config.inter.ILayout;
import com.daliammao.numlayoutlib.utils.Preconditions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午7:23
 * @Email: 496946423@qq.com
 * @desc:
 */
public class LayoutConfig extends ViewConfig implements ILayout {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {}

    @Orientation
    private int mOrientation = HORIZONTAL;

    Map<String, ViewConfig> mChildrenView = new LinkedHashMap<String, ViewConfig>();

    @Override
    public ViewType getType() {
        return ViewType.LAYOUT;
    }

    @Override
    public ViewConfig addChild(ViewConfig viewConfig) {
        Preconditions.checkArgument(!mChildrenView.keySet().contains(viewConfig.getTag()), "The tag already exists");
        return mChildrenView.put(viewConfig.getTag(), viewConfig);
    }

    public Map<String, ViewConfig> getChildrenView() {
        return Collections.unmodifiableMap(mChildrenView);
    }

    @Override
    public void setOrientation(@Orientation int orientation) {
        Preconditions.checkArgument(orientation == HORIZONTAL || orientation == VERTICAL);

        mOrientation = orientation;
    }

    @Orientation
    public int getOrientation() {
        return mOrientation;
    }
}
