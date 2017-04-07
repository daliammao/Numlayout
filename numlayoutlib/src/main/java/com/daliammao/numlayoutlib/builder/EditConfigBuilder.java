package com.daliammao.numlayoutlib.builder;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

import com.daliammao.numlayoutlib.config.EditConfig;
import com.daliammao.numlayoutlib.config.inter.IText;
import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午4:39
 * @Email: 496946423@qq.com
 * @desc:
 */
public class EditConfigBuilder extends ViewConfigBuilder<EditConfigBuilder> {

    private EditConfig mEditConfig;

    public static EditConfigBuilder newEditConfigBuilder() {
        return new EditConfigBuilder(new EditConfig());
    }

    private EditConfigBuilder(EditConfig editConfig) {
        super(editConfig);
        mEditConfig = editConfig;
    }

    public EditConfigBuilder textWithNormal() {
        mEditConfig.setTextType(IText.TextType.NORMAL);
        return this;
    }

    public EditConfigBuilder textWithInteger() {
        mEditConfig.setTextType(IText.TextType.INTEGER);
        return this;
    }

    public EditConfigBuilder textWithInteger(int minNum, int maxNum, int tolerance, boolean editNumGtZero) {
        mEditConfig.setTextType(IText.TextType.INTEGER);
        mEditConfig.setMinNum(minNum);
        mEditConfig.setMaxNum(maxNum);
        mEditConfig.setTolerance(tolerance);
        mEditConfig.setEditNumGtZero(editNumGtZero);
        return this;
    }

    public EditConfigBuilder textWithDecimal() {
        mEditConfig.setTextType(IText.TextType.DECIMAL);
        return this;
    }

    public EditConfigBuilder textWithDecimal(float minNum, float maxNum, float tolerance, boolean editNumGtZero) {
        mEditConfig.setTextType(IText.TextType.DECIMAL);
        mEditConfig.setMinNum(minNum);
        mEditConfig.setMaxNum(maxNum);
        mEditConfig.setTolerance(tolerance);
        mEditConfig.setEditNumGtZero(editNumGtZero);
        return this;
    }

    public EditConfigBuilder setText(CharSequence text) {
        mEditConfig.setText(text);
        return this;
    }

    public EditConfigBuilder setTextColor(@ColorInt int color) {
        mEditConfig.setTextColor(color);
        return this;
    }

    public EditConfigBuilder setTextColor(ColorStateList colors) {
        mEditConfig.setTextColor(colors);
        return this;
    }

    public EditConfigBuilder setTextSize(float size) {
        mEditConfig.setTextSize(size);
        return this;
    }

    public EditConfigBuilder setHintText(CharSequence hintText) {
        mEditConfig.setHintText(hintText);
        return this;
    }

    public EditConfigBuilder setHintTextColor(@ColorInt int color) {
        mEditConfig.setHintTextColor(color);
        return this;
    }

    public EditConfigBuilder setHintTextColor(ColorStateList hintTextColor) {
        mEditConfig.setHintTextColor(hintTextColor);
        return this;
    }

    public EditConfig build() {
        super.build();
        checkBtnType();
        checkMinMax();
        return mEditConfig;
    }

    protected void checkBtnType() {
        Preconditions.checkNotNull(mEditConfig.getTextType(), "Text type can not null");
    }

    private void checkMinMax() {
//        switch (mEditConfig.getTextType()) {
//            case INTEGER:
//            case DECIMAL:
//                Preconditions.checkArgument(mEditConfig.getMinNum()>=0,"Min num must great than zero");
//                break;
//        }

        Preconditions.checkArgument(mEditConfig.getMinNum() <= mEditConfig.getMaxNum(), "Min num must less than max num");
    }
}
