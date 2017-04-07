package com.daliammao.numlayoutlib.builder;

import android.support.annotation.DrawableRes;

import com.daliammao.numlayoutlib.config.ViewConfig;
import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午11:35
 * @Email: 496946423@qq.com
 * @desc:
 */
public abstract class ViewConfigBuilder<T extends ViewConfigBuilder> {
    private ViewConfig mViewConfig;

    ViewConfigBuilder(ViewConfig viewConfig) {
        mViewConfig = viewConfig;
    }

    /**
     * 设置该控件的唯一标识
     *
     * @param tag
     */
    public T setTag(String tag) {

        mViewConfig.setTag(tag);
        return (T) this;
    }

    /**
     * 设置控件的宽度
     *
     * @param width
     */
    public T setWidth(int width) {
        mViewConfig.setWidth(width);
        return (T) this;
    }

    /**
     * 设置控件的高度
     *
     * @param height
     */
    public T setHeight(int height) {
        mViewConfig.setHeight(height);
        return (T) this;
    }

    /**
     * 设置控件背景
     *
     * @param resid
     */
    public T setBackgroundResource(@DrawableRes int resid) {
        mViewConfig.setBackgroundResource(resid);
        return (T) this;
    }

    /**
     * 设置空间不可用时的背景
     *
     * @param resid
     * @return
     */
    public T setUnEnableBackgroundResource(@DrawableRes int resid) {
        mViewConfig.setUnEnableBackgroundResource(resid);
        return (T) this;
    }

    public T setMargins(int marginLeft, int marginTop, int marginRight, int marginBottom) {
        mViewConfig.setMarginLeft(marginLeft);
        mViewConfig.setMarginTop(marginTop);
        mViewConfig.setMarginRight(marginRight);
        mViewConfig.setMarginBottom(marginBottom);
        return (T) this;
    }

    public T setGravity(int gravity) {
        mViewConfig.setGravity(gravity);
        return (T) this;
    }

    public ViewConfig build() {
        checkTag();
        checkSize();
        return mViewConfig;
    }

    protected void checkTag() {
        Preconditions.checkNotNull(mViewConfig.getTag(), "Tag can not null");
    }

    protected void checkSize() {
        Preconditions.checkArgument(mViewConfig.getHeight() != 0 && mViewConfig.getWidth() != 0, "The value of height or width is zero");

        Preconditions.checkArgument(mViewConfig.getHeight() >= -2 && mViewConfig.getWidth() >= -2, "Unknow the value of height or width");
    }
}