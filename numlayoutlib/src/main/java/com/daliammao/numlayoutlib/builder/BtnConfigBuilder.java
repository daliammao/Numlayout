package com.daliammao.numlayoutlib.builder;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

import com.daliammao.numlayoutlib.config.BtnConfig;
import com.daliammao.numlayoutlib.config.inter.IBtn;
import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午10:04
 * @Email: 496946423@qq.com
 * @desc:
 */
public class BtnConfigBuilder extends ViewConfigBuilder<BtnConfigBuilder> {

    private BtnConfig mBtnConfig;

    public static BtnConfigBuilder newBtnConfigBuilder() {
        return new BtnConfigBuilder(new BtnConfig());
    }

    public BtnConfigBuilder(BtnConfig btnConfig) {
        super(btnConfig);
        mBtnConfig = btnConfig;
    }

    public BtnConfigBuilder btnWithAdd() {
        mBtnConfig.setBtnType(IBtn.BtnType.ADD);
        return this;
    }

    public BtnConfigBuilder btnWithLes() {
        mBtnConfig.setBtnType(IBtn.BtnType.LES);
        return this;
    }

    public BtnConfigBuilder setText(CharSequence text) {
        mBtnConfig.setText(text);
        return this;
    }

    public BtnConfigBuilder setTextColor(@ColorInt int color) {
        mBtnConfig.setTextColor(color);
        return this;
    }

    public BtnConfigBuilder setTextColor(ColorStateList colors) {
        mBtnConfig.setTextColor(colors);
        return this;
    }

    public BtnConfigBuilder setTextSize(float size) {
        mBtnConfig.setTextSize(size);
        return this;
    }

    public BtnConfig build() {
        super.build();
        checkBtnType();
        return mBtnConfig;
    }
    protected void checkBtnType() {
        Preconditions.checkNotNull(mBtnConfig.getBtnType(), "Btn type can not null");
    }
}
