package com.daliammao.numlayoutlib.halper;

import com.daliammao.numlayoutlib.listener.OnIntegerErrorListener;
import com.daliammao.numlayoutlib.listener.OnIntegerErrorListener.ErrorType;


/**
 * @author: zhoupengwei
 * @time:16/5/5-上午11:43
 * @Email: 496946423@qq.com
 * @desc:
 */
public class IntegerErrorHalper {

    private int mMinNum = 0;
    private int mMaxNum = Integer.MAX_VALUE;
    // 公差必须大于0 否则默认为1
    private int mTolerance = 1;
    public boolean isNotFilter = false;
    private boolean mEditNumGtZero = false;

    private OnIntegerErrorListener mOnIntegerErrorListener;

    public IntegerErrorHalper(int minNum, int maxNum, int tolerance, boolean editNumGtZero) {
        setMinNum(minNum);
        setMaxNum(maxNum);
        setTolerance(tolerance);
        mEditNumGtZero = editNumGtZero;
    }

    public Integer completeFilter(int newData) {
        ErrorType errorType = checkError(newData);
        if (errorType != null) {
            if (mOnIntegerErrorListener != null) {
                Integer temp = mOnIntegerErrorListener.onIntegerError(errorType, newData, mMinNum, mMaxNum, mTolerance);
                newData = temp == null ? newData : temp;
            }
        } else {
            return null;
        }

        //错误转换后的新值在进行一次检错.如果还是错误数据,根据错误改变数值
        errorType = checkError(newData);
        if (errorType != null) {
            switch (errorType) {
                case LT_MIN:
                    newData = mMinNum;
                    break;
                case GT_MAX:
                    newData = mMaxNum;
                    break;
                case NOT_SATISFY_TOLERANCE:
                    newData = (newData-mMinNum) / mTolerance * mTolerance+mMinNum;
                    newData = newData < mMinNum ? mMinNum : newData;
                    break;
                case LT_ZERO:
                    newData = 0;
                    break;
            }
        }
        return newData;
    }

    /**
     * 返回错误类型,如果没有错误返回null.
     *
     * @param data
     * @return
     */
    public ErrorType checkError(int data) {
        if (mEditNumGtZero && data < 0) {
            return ErrorType.LT_ZERO;
        }
        if (data < mMinNum) {
            return ErrorType.LT_MIN;
        }
        if (data > mMaxNum) {
            return ErrorType.GT_MAX;
        }
        if ((data - mMinNum) % mTolerance > 0) {
            return ErrorType.NOT_SATISFY_TOLERANCE;
        }
        return null;
    }

    public int getMinNum() {
        return mMinNum;
    }

    public void setMinNum(int mMinNum) {
        this.mMinNum = mMinNum;
    }

    public int getMaxNum() {
        return mMaxNum;
    }

    public void setMaxNum(int mMaxNum) {
        this.mMaxNum = mMaxNum;
    }

    public int getTolerance() {
        return mTolerance;
    }

    public void setTolerance(int mTolerance) {
        this.mTolerance = mTolerance;
    }

    public void setOnIntegerErrorListener(OnIntegerErrorListener onIntegerErrorListener) {
        this.mOnIntegerErrorListener = onIntegerErrorListener;
    }

    public OnIntegerErrorListener getOnIntegerErrorListener() {
        return mOnIntegerErrorListener;
    }
}
