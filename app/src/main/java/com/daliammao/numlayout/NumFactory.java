package com.daliammao.numlayout;

import android.content.Context;
import android.view.Gravity;

import com.daliammao.numlayoutlib.builder.BtnConfigBuilder;
import com.daliammao.numlayoutlib.builder.EditConfigBuilder;
import com.daliammao.numlayoutlib.builder.LayoutConfigBuilder;
import com.daliammao.numlayoutlib.config.LayoutConfig;
import com.daliammao.numlayoutlib.config.ViewConfig;

/**
 * @author: zhoupengwei
 * @time:16/5/27-上午11:01
 * @Email: 496946423@qq.com
 * @desc:
 */
public class NumFactory {
    //获取默认的单个数字加减layout
    public static LayoutConfig getDefaultSingel(Context context, int min, int max, int tolerance) {
        return LayoutConfigBuilder.newLayoutConfigBuilder()
                .setOrientation(LayoutConfig.HORIZONTAL)
                .setBackgroundResource(R.drawable.shape_cart_stroke)
                .setHeight(ViewConfig.WRAP_CONTENT)
                .setWidth(ViewConfig.WRAP_CONTENT)
                .addChild(BtnConfigBuilder.newBtnConfigBuilder()
                        .setTag("Les")
                        .setWidth(UIUtils.dip2px(context, 30))
                        .setHeight(ViewConfig.MATCH_PARENT)
                        .setText("-")
                        .btnWithLes()
                        .build())
                .addChild(EditConfigBuilder.newEditConfigBuilder()
                        .setTag("Text")
                        .textWithInteger(min, max, tolerance, true)
                        .setHeight(ViewConfig.MATCH_PARENT)
                        .setWidth(UIUtils.dip2px(context, 35))
                        .setText(String.valueOf(min))
                        .setGravity(Gravity.CENTER)
                        .setBackgroundResource(R.color.trans)
                        .setTextSize(13)
                        .build())
                .addChild(BtnConfigBuilder.newBtnConfigBuilder()
                        .setTag("Add")
                        .setWidth(ViewConfig.WRAP_CONTENT)
                        .setWidth(UIUtils.dip2px(context, 30))
                        .setHeight(ViewConfig.MATCH_PARENT)
                        .btnWithAdd()
                        .setText("+")
                        .build())
                .build();
    }
}
