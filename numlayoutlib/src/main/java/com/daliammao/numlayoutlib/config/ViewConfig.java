package com.daliammao.numlayoutlib.config;

import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.ViewGroup;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午9:49
 * @Email: 496946423@qq.com
 * @desc:
 */
public abstract class ViewConfig {

    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    public enum ViewType {
        LAYOUT,
        BTN, //按钮类型
        TEXT, // 普通文本类型
        EDIT, // 编辑文本类型
        BLOCK // 块类型 就好像一块空白的视图,里面可以放进想要的东西 // TODO: 16/5/6 实现设置自定义view
    }

    protected String mTag;
    protected int mWidth = WRAP_CONTENT;
    protected int mHeight = WRAP_CONTENT;
    protected int mBackgroundResource = 0;
    protected int mUnEnableBackgroundResource = 0;
    protected int mMarginLeft = 0;
    protected int mMarginTop = 0;
    protected int mMarginRight = 0;
    protected int mMarginBottom = 0;
    protected int mGravity = Gravity.CENTER;

    /**
     * 设置该控件的唯一标识
     *
     * @param tag
     */
    public void setTag(String tag) {
        mTag = tag;
    }

    /**
     * 返回该控件的唯一标识
     *
     * @return
     */
    public String getTag() {
        return mTag;
    }

    /**
     * 设置控件的宽度
     *
     * @param width
     */
    public void setWidth(int width) {
        mWidth = width;
    }

    /**
     * 返回控件的宽度
     *
     * @return
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * 设置控件的高度
     *
     * @param height
     */
    public void setHeight(int height) {
        mHeight = height;
    }

    /**
     * 返回控件的高度
     *
     * @return
     */
    public int getHeight() {
        return mHeight;
    }

    /**
     * 设置控件背景
     *
     * @param resid
     */
    public void setBackgroundResource(@DrawableRes int resid) {
        mBackgroundResource = resid;
    }

    /**
     * 返回控件背景
     *
     * @return
     */
    public int getBackgroundResource() {
        return mBackgroundResource;
    }

    /**
     * 设置控件不可用时的背景
     *
     * @param resid
     */
    public void setUnEnableBackgroundResource(@DrawableRes int resid) {
        mUnEnableBackgroundResource = resid;
    }

    public int getUnEnableBackgroundResource() {
        return mUnEnableBackgroundResource;
    }

    public int getMarginLeft() {
        return mMarginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.mMarginLeft = marginLeft;
    }

    public int getMarginTop() {
        return mMarginTop;
    }

    public void setMarginTop(int marginTop) {
        this.mMarginTop = marginTop;
    }

    public int getMarginRight() {
        return mMarginRight;
    }

    public void setMarginRight(int marginRight) {
        this.mMarginRight = marginRight;
    }

    public int getMarginBottom() {
        return mMarginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.mMarginBottom = marginBottom;
    }

    public int getGravity() {
        return mGravity;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    //返回控件的类型
    public abstract ViewType getType();
}
