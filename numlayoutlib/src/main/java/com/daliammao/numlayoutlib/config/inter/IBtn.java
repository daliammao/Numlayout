package com.daliammao.numlayoutlib.config.inter;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午10:20
 * @Email: 496946423@qq.com
 * @desc:
 */
public interface IBtn {

    enum BtnType {
        ADD, //添加型按钮
        LES // 减少型按钮
    }

    /**
     * 设置按钮类型
     *
     * @param btnType
     */
    public void setBtnType(BtnType btnType);

    /**
     * 获取按钮类型
     *
     * @return
     */
    public BtnType getBtnType();

    public void setText(CharSequence text);

    public CharSequence getText();

    /**
     * 设置字体颜色
     */
    public void setTextColor(@ColorInt int color);

    public void setTextColor(ColorStateList colors);

    /**
     * 返回字体颜色
     *
     * @return
     */
    public ColorStateList getTextColor();

    /**
     * 设置字体大小以px为单位
     *
     * @param size
     */
    public void setTextSize(float size);

    /**
     * 返回字体大小
     */
    public float getTextSize();
}
