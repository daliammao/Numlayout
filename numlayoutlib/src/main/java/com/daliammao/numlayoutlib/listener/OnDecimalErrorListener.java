package com.daliammao.numlayoutlib.listener;

/**
 * @author: zhoupengwei
 * @time:16/5/4-下午7:54
 * @Email: 496946423@qq.com
 * @desc:
 */
public interface OnDecimalErrorListener {
    enum ErrorType {
        LT_MIN,
        GT_MAX,
        LT_ZERO,
        NOT_SATISFY_TOLERANCE
    }

    Float onDecimalError(ErrorType errorType, float newData, float min, float max, float tolerance);
}
