package com.daliammao.numlayoutlib.filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * @author: zhoupengwei
 * @time:16/5/26-下午4:17
 * @Email: 496946423@qq.com
 * @desc:
 */
public class DecimalValidFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (TextUtils.isEmpty(source.toString())) {
            return null;
        }
        try {
            StringBuilder temp = new StringBuilder(dest);
            temp.insert(dstart, source);
            if (temp.length() >= 10) {
                Float.valueOf(temp.toString());
            }
        } catch (NumberFormatException e) {
            return "";
        }
        return null;
    }
}
