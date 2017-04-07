package com.daliammao.numlayoutlib.builder;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

import com.daliammao.numlayoutlib.config.TextConfig;
import com.daliammao.numlayoutlib.config.inter.IText;
import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午2:14
 * @Email: 496946423@qq.com
 * @desc:
 */
public class TextConfigBuilder extends ViewConfigBuilder<TextConfigBuilder> {

    private TextConfig mTextConfig;

    public static TextConfigBuilder newTextConfigBuilder() {
        return new TextConfigBuilder(new TextConfig());
    }

    protected TextConfigBuilder(TextConfig textConfig) {
        super(textConfig);
        mTextConfig = textConfig;
    }

    public TextConfigBuilder textWithNormal() {
        mTextConfig.setTextType(IText.TextType.NORMAL);
        return this;
    }

    public TextConfigBuilder textWithInteger() {
        mTextConfig.setTextType(IText.TextType.INTEGER);
        return this;
    }

    public TextConfigBuilder textWithInteger(int minNum, int maxNum, int tolerance,boolean editNumGtZero) {
        mTextConfig.setTextType(IText.TextType.INTEGER);
        mTextConfig.setMinNum(minNum);
        mTextConfig.setMaxNum(maxNum);
        mTextConfig.setTolerance(tolerance);
        mTextConfig.setEditNumGtZero(editNumGtZero);
        return this;
    }

    public TextConfigBuilder textWithDecimal() {
        mTextConfig.setTextType(IText.TextType.DECIMAL);
        return this;
    }

    public TextConfigBuilder textWithDecimal(float minNum, float maxNum, float tolerance,boolean editNumGtZero) {
        mTextConfig.setTextType(IText.TextType.DECIMAL);
        mTextConfig.setMinNum(minNum);
        mTextConfig.setMaxNum(maxNum);
        mTextConfig.setTolerance(tolerance);
        mTextConfig.setEditNumGtZero(editNumGtZero);
        return this;
    }

    public TextConfigBuilder setText(CharSequence text) {
        mTextConfig.setText(text);
        return this;
    }

    public TextConfigBuilder setTextColor(@ColorInt int color) {
        mTextConfig.setTextColor(color);
        return this;
    }

    public TextConfigBuilder setTextColor(ColorStateList colors) {
        mTextConfig.setTextColor(colors);
        return this;
    }

    public TextConfigBuilder setTextSize(float size) {
        mTextConfig.setTextSize(size);
        return this;
    }

    public TextConfigBuilder setHintText(CharSequence hintText) {
        mTextConfig.setHintText(hintText);
        return this;
    }

    public TextConfigBuilder setHintTextColor(@ColorInt int color) {
        mTextConfig.setHintTextColor(color);
        return this;
    }

    public TextConfigBuilder setHintTextColor(ColorStateList hintTextColor) {
        mTextConfig.setHintTextColor(hintTextColor);
        return this;
    }

    @Override
    public TextConfig build() {
        super.build();
        checkBtnType();
        checkMinMax();
        return mTextConfig;
    }

    protected void checkBtnType() {
        Preconditions.checkNotNull(mTextConfig.getTextType(), "Text type can not null");
    }

    private void checkMinMax() {
//        switch (mTextConfig.getTextType()) {
//            case INTEGER:
//            case DECIMAL:
//                Preconditions.checkArgument(mTextConfig.getMinNum()>=0,"Min num must great than zero");
//                break;
//        }

        Preconditions.checkArgument(mTextConfig.getMinNum()<=mTextConfig.getMaxNum(),"Min num must less than max num");
    }
}
