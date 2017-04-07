package com.daliammao.numlayoutlib.listener;

/**
 * @author: zhoupengwei
 * @time:16/5/4-下午8:06
 * @Email: 496946423@qq.com
 * @desc: 输入的数据有误是会调用该监听获取一个正确的值, 如果返回null则使用原来的值.
 * 转换后的值会再进行一次检错,如果出错由控件自行处理.
 */
public interface OnIntegerErrorListener {
    enum ErrorType {
        LT_MIN,
        GT_MAX,
        LT_ZERO,
        NOT_SATISFY_TOLERANCE
    }

    Integer onIntegerError(ErrorType errorType, int newData, int min, int max, int tolerance);
}
