package com.daliammao.numlayoutlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午11:03
 * @Email: 496946423@qq.com
 * @desc:
 */
public class NumButton extends Button {
    public NumButton(Context context) {
        super(context);
        initView();
    }

    public NumButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setPadding(0,0,0,0);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        Preconditions.checkArgument(false, "This operation is not supported");
    }

    public void setSuperOnClickListener(OnClickListener l){
        super.setOnClickListener(l);
    }
}
