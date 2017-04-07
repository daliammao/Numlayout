package com.daliammao.numlayoutlib.config;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

import com.daliammao.numlayoutlib.config.inter.IBtn;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午10:09
 * @Email: 496946423@qq.com
 * @desc:
 */
public class BtnConfig extends ViewConfig implements IBtn {
    protected BtnType mBtnType;
    protected CharSequence mText;
    protected ColorStateList mTextColor;
    protected float mTextSize;

    /**
     * @return
     * @see ViewConfig#getType()
     */
    @Override
    public ViewType getType() {
        return ViewType.BTN;
    }

    /**
     * @see IBtn#setBtnType(BtnType)
     */
    @Override
    public void setBtnType(BtnType btnType) {
        mBtnType = btnType;
    }

    /**
     * @see IBtn#getBtnType()
     */
    @Override
    public BtnType getBtnType() {
        return mBtnType;
    }

    @Override
    public void setText(CharSequence text) {
        mText = text;
    }

    @Override
    public CharSequence getText() {
        return mText;
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        mTextColor = ColorStateList.valueOf(color);
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        mTextColor = colors;
    }

    @Override
    public ColorStateList getTextColor() {
        return mTextColor;
    }

    @Override
    public void setTextSize(float size) {
        mTextSize = size;
    }

    @Override
    public float getTextSize() {
        return mTextSize;
    }
}
