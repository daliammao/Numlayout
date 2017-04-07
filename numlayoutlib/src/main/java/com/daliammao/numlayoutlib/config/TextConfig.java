package com.daliammao.numlayoutlib.config;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

import com.daliammao.numlayoutlib.config.inter.IText;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午1:55
 * @Email: 496946423@qq.com
 * @desc:
 */
public class TextConfig extends ViewConfig implements IText {
    protected CharSequence mText;
    protected ColorStateList mTextColor;
    protected CharSequence mHintText;
    protected ColorStateList mHintTextColor;
    protected float mTextSize;
    protected TextType mTextType;
    protected float mMinNum = Integer.MIN_VALUE;
    protected float mMaxNum = Integer.MAX_VALUE;
    // 公差必须大于0 否则默认为1
    protected float mTolerance = 1;
    private boolean mEditNumGtZero = false;

    @Override
    public ViewType getType() {
        return ViewType.TEXT;
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
    public CharSequence getHintText() {
        return mHintText;
    }

    @Override
    public void setHintText(CharSequence hintText) {
        mHintText = hintText;
    }

    @Override
    public ColorStateList getHintTextColor() {
        return mHintTextColor;
    }

    @Override
    public void setHintTextColor(@ColorInt int color) {
        mHintTextColor = ColorStateList.valueOf(color);
    }

    @Override
    public void setHintTextColor(ColorStateList hintTextColor) {
        mHintTextColor = hintTextColor;
    }

    @Override
    public void setTextSize(float size) {
        mTextSize = size;
    }

    @Override
    public float getTextSize() {
        return mTextSize;
    }

    @Override
    public void setTextType(TextType type) {
        mTextType = type;
    }

    @Override
    public TextType getTextType() {
        return mTextType;
    }

    @Override
    public void setMinNum(float min) {
        mMinNum = min;
    }

    @Override
    public void setMinNum(int min) {
        mMinNum = min;
    }

    @Override
    public float getMinNum() {
        return mMinNum;
    }

    @Override
    public void setMaxNum(float max) {
        mMaxNum = max;
    }

    @Override
    public void setMaxNum(int max) {
        mMaxNum = max;
    }

    @Override
    public float getMaxNum() {
        return mMaxNum;
    }

    @Override
    public void setTolerance(float tolerance) {
        if(tolerance<=0){
            mTolerance = 1;
        }else{
            mTolerance = tolerance;
        }
    }

    @Override
    public void setTolerance(int tolerance) {
        if(tolerance<=0){
            mTolerance = 1;
        }else{
            mTolerance = tolerance;
        }
    }

    @Override
    public float getTolerance() {
        return mTolerance;
    }

    public boolean isEditNumGtZero() {
        return mEditNumGtZero;
    }

    public void setEditNumGtZero(boolean editNumGtZero) {
        this.mEditNumGtZero = editNumGtZero;
    }
}
