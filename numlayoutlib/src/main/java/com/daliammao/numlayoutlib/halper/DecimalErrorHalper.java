package com.daliammao.numlayoutlib.halper;

import com.daliammao.numlayoutlib.listener.OnDecimalErrorListener;
import com.daliammao.numlayoutlib.listener.OnDecimalErrorListener.ErrorType;

/**
 * @author: zhoupengwei
 * @time:16/5/5-上午11:34
 * @Email: 496946423@qq.com
 * @desc:
 */
public class DecimalErrorHalper {

    private float mMinNum = 0;
    private float mMaxNum = Integer.MAX_VALUE;
    // 公差必须大于0 否则默认为1
    private float mTolerance = 1;
    public boolean isNotFilter = false;
    private boolean mEditNumGtZero = false;

    private OnDecimalErrorListener mOnDecimalErrorListener;

    public DecimalErrorHalper(float minNum, float maxNum, float tolerance, boolean editNumGtZero) {
        setMinNum(minNum);
        setMaxNum(maxNum);
        setTolerance(tolerance);
        mEditNumGtZero = editNumGtZero;
    }

    public Float completeFilter(float newData) {
        ErrorType errorType = checkError(newData);
        if (errorType != null) {
            if (mOnDecimalErrorListener != null) {
                Float temp = mOnDecimalErrorListener.onDecimalError(errorType, newData, mMinNum, mMaxNum, mTolerance);
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
                    newData = ((int) (newData - mMinNum) / mTolerance) * mTolerance + mMinNum;
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
    public ErrorType checkError(float data) {
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

    public float getMinNum() {
        return mMinNum;
    }

    public void setMinNum(float minNum) {
        this.mMinNum = minNum;
    }

    public float getMaxNum() {
        return mMaxNum;
    }

    public void setMaxNum(float maxNum) {
        this.mMaxNum = maxNum;
    }

    public float getTolerance() {
        return mTolerance;
    }

    public void setTolerance(float tolerance) {
        this.mTolerance = tolerance;
    }

    public void setOnDecimalErrorListener(OnDecimalErrorListener onDecimalErrorListener) {
        this.mOnDecimalErrorListener = onDecimalErrorListener;
    }

    public OnDecimalErrorListener getOnDecimalErrorListener() {
        return mOnDecimalErrorListener;
    }
}
