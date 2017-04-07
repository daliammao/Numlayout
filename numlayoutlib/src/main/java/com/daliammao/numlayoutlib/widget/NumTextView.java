package com.daliammao.numlayoutlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.daliammao.numlayoutlib.utils.Preconditions;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午11:07
 * @Email: 496946423@qq.com
 * @desc:
 */
@SuppressLint("AppCompatCustomView")
public class NumTextView extends TextView {

    public NumTextView(Context context) {
        super(context);
        initView();
    }

    public NumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
