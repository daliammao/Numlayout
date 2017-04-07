package com.daliammao.numlayoutlib.config.inter;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午12:43
 * @Email: 496946423@qq.com
 * @desc:
 */
public interface IText {
    enum TextType{
        NORMAL, //任何字符
        INTEGER, //正整数
        DECIMAL, //浮点数
    }

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

    public CharSequence getHintText();

    public void setHintText(CharSequence hintText);

    public ColorStateList getHintTextColor();

    public void setHintTextColor(@ColorInt int color);

    public void setHintTextColor(ColorStateList hintTextColor);

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

    /**
     * 设置控件类型
     *
     * @param type
     */
    public void setTextType(TextType type);

    /**
     * 返回控件类型
     *
     * @return
     */
    public TextType getTextType();

    /**
     * 当type为INTEGER或DECIMAL时 这些值才会使用
     * 当type为INTEGER时该值会直接取整
     *
     * @param min
     */
    public void setMinNum(float min);
    public void setMinNum(int min);
    public float getMinNum();

    public void setMaxNum(float max);
    public void setMaxNum(int max);
    public float getMaxNum();

    // 公差必须大于0 否则默认为1,设置了公差之后,在text里确定的数据必须为tolerance的倍数.
    public void setTolerance(float tolerance);
    public void setTolerance(int tolerance);
    public float getTolerance();
}
