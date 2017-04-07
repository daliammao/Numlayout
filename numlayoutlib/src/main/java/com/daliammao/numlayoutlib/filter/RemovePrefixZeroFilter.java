package com.daliammao.numlayoutlib.filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * @author: zhoupengwei
 * @time:16/5/6-下午3:57
 * @Email: 496946423@qq.com
 * @desc:
 */
public class RemovePrefixZeroFilter implements InputFilter {
    private TextView mParentView;
    public boolean isNotFilter = false;

    public RemovePrefixZeroFilter(TextView mParentView) {
        this.mParentView = mParentView;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (isNotFilter == true) {
            isNotFilter = false;
            return null;
        }

        if (TextUtils.isEmpty(source.toString()) || (TextUtils.isEmpty(dest) && TextUtils.equals(source,"0"))) {
            return null;
        }

        if (dest.length() > 0 && dest.charAt(0) == '0' && dstart == 1) {
            //排除特殊情况,比如0,或0.1,在第1加入0是不允许的
            for (int i = start; i < end; i++) {
                char item = source.charAt(i);
                if (item == '0') {
                    continue;
                } else if (item == '.') {
                    return source.subSequence(i, end);
                } else {
                    StringBuilder temp = new StringBuilder(dest);
                    temp.insert(dstart, source);
                    //去除首位的0
                    CharSequence newData = temp.subSequence(1, temp.length());
                    isNotFilter = true;
                    mParentView.setText(newData);
                    isNotFilter = false;
                    return newData;
                }
            }
            return "";
        }

        if (dstart != 0) {
            //已经排除类特殊状况,操作的位子不在第0位,则忽略
            return null;
        }

        StringBuilder newSource = new StringBuilder();

        for (int i = start; i < end; i++) {
            char item = source.charAt(i);
            if (item == '0' || (item == '.' && dest.toString().contains("."))) {
                continue;
            } else {
                if (item == '.' && !dest.toString().contains(".")) {
                    newSource.append('0').append(source.subSequence(i, end));
                } else {
                    newSource.append(source.subSequence(i, end));
                }
                break;
            }
        }

        return newSource.toString();
    }
}
