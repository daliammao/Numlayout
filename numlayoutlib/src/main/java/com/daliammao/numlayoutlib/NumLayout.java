package com.daliammao.numlayoutlib;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daliammao.numlayoutlib.config.BlockConfig;
import com.daliammao.numlayoutlib.config.BtnConfig;
import com.daliammao.numlayoutlib.config.EditConfig;
import com.daliammao.numlayoutlib.config.LayoutConfig;
import com.daliammao.numlayoutlib.config.TextConfig;
import com.daliammao.numlayoutlib.config.ViewConfig;
import com.daliammao.numlayoutlib.config.inter.IBlock;
import com.daliammao.numlayoutlib.config.inter.IText;
import com.daliammao.numlayoutlib.filter.DecimalValidFilter;
import com.daliammao.numlayoutlib.filter.IntegerValidFilter;
import com.daliammao.numlayoutlib.filter.RemovePrefixZeroFilter;
import com.daliammao.numlayoutlib.halper.DecimalErrorHalper;
import com.daliammao.numlayoutlib.halper.IntegerErrorHalper;
import com.daliammao.numlayoutlib.listener.OnDecimalErrorListener;
import com.daliammao.numlayoutlib.listener.OnIntegerErrorListener;
import com.daliammao.numlayoutlib.utils.Preconditions;
import com.daliammao.numlayoutlib.widget.NumButton;
import com.daliammao.numlayoutlib.widget.NumEditText;
import com.daliammao.numlayoutlib.widget.NumTextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhoupengwei
 * @time:16/4/28-上午9:42
 * @Email: 496946423@qq.com
 * @desc: 控件的主要实现
 */
public class NumLayout extends LinearLayout implements View.OnClickListener {
    private Map<String, View> mChildrenView = new HashMap();
    private Map<String, ViewConfig> mChildrenConfig = new HashMap();
    private Map<String, OnIntegerErrorListener> mIntegerErrorListeners = new HashMap();
    private Map<String, OnDecimalErrorListener> mDecimalErrorListeners = new HashMap();

    private OnItemClickListener mOnItemClickListener;
    private OnInputListener mOnInputListener;
    private OnNumComputeHandler mOnNumComputeHandler;

    public NumLayout(Context context) {
        this(context, null);
    }

    public NumLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 通过设置{@link LayoutConfig}来初始化全部ui
     *
     * @param layoutConfig
     */
    public void setConfig(LayoutConfig layoutConfig) {
        removeAllViews();
        mChildrenView.clear();
        mChildrenConfig.clear();

        setViewData(this, layoutConfig);
        setOrientation(layoutConfig.getOrientation());

        for (ViewConfig config : layoutConfig.getChildrenView().values()) {
            addView(config);
        }
    }

    @Override
    public void setOrientation(int orientation) {
        if (getOrientation() == orientation) {
            return;
        }
        for (Map.Entry item : mChildrenConfig.entrySet()) {
            if (item.getValue() instanceof BlockConfig && ((BlockConfig) item.getValue()).getBlockType() == IBlock.BlockType.LINE) {
                View child = mChildrenView.get(item.getKey());
                if (child != null) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
                    int childHeight = layoutParams.height;
                    layoutParams.height = layoutParams.width;
                    layoutParams.width = childHeight;
                    layoutParams.setMargins(layoutParams.bottomMargin, layoutParams.leftMargin,
                            layoutParams.topMargin, layoutParams.rightMargin);
                    child.setLayoutParams(layoutParams);
                }
            }
        }
        super.setOrientation(orientation);
    }

    /**
     * 该view的子view进行了包装,无法设置onClickListener,为的是统一点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            String clickTag = (String) v.getTag(R.id.view_tag);
            ViewConfig config = mChildrenConfig.get(clickTag);
            if (config == null) {
                return;
            }
            mOnItemClickListener.onItemClick(NumLayout.this, v,
                    config.getType(), clickTag);
        }
    }

    private NumButton initButton(final BtnConfig config) {
        final NumButton btnView = new NumButton(getContext());
        setViewData(btnView, config);
        if (!TextUtils.isEmpty(config.getText())) {
            btnView.setText(config.getText());
        }
        if (config.getTextColor() != null) {
            btnView.setTextColor(config.getTextColor());
        }
        if (config.getTextSize() != 0) {
            btnView.setTextSize(config.getTextSize());
        }

        btnView.setGravity(config.getGravity());

        btnView.setSuperOnClickListener(this);
        return btnView;
    }

    private NumTextView initTextView(final TextConfig config) {
        NumTextView textView = new NumTextView(getContext());
        setViewData(textView, config);
        setTextData(textView, config);
        textView.setSuperOnClickListener(this);
        return textView;
    }

    private NumEditText initEditText(final EditConfig config) {
        NumEditText editText = new NumEditText(getContext());
        setViewData(editText, config);
        setTextData(editText, config);
        editText.setSuperOnClickListener(this);
        return editText;
    }

    private View initBlock(final BlockConfig config) {
        View blockView = new View(getContext());
        setViewData(blockView, config);
        return blockView;
    }

    private void setViewData(View view, final ViewConfig config) {
        if (config instanceof LayoutConfig) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = config.getWidth() != 0 ? config.getWidth() : layoutParams.width;
            layoutParams.height = config.getHeight() != 0 ? config.getHeight() : layoutParams.height;
            view.setLayoutParams(layoutParams);
        } else {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    config.getWidth() != 0 ? config.getWidth() : ViewConfig.WRAP_CONTENT,
                    config.getHeight() != 0 ? config.getHeight() : ViewConfig.WRAP_CONTENT);
            layoutParams.setMargins(config.getMarginLeft(), config.getMarginTop(), config.getMarginRight(),
                    config.getMarginBottom());
            view.setLayoutParams(layoutParams);
        }

        if (config.getBackgroundResource() != 0) {
            view.setBackgroundResource(config.getBackgroundResource());
        }

        view.setTag(R.id.view_tag, config.getTag());
    }

    private void setTextData(TextView viewData, TextConfig config) {
        if (!TextUtils.isEmpty(config.getText())) {
            viewData.setText(config.getText());
        }
        if (config.getTextColor() != null) {
            viewData.setTextColor(config.getTextColor());
        }
        if (config.getTextColor() != null) {
            viewData.setHintTextColor(config.getTextColor());
        }
        if (!TextUtils.isEmpty(config.getHintText())) {
            viewData.setHint(config.getHintText());
        }
        if (config.getTextSize() != 0) {
            viewData.setTextSize(config.getTextSize());
        }

        if(config.getTextType() == IText.TextType.INTEGER){
            viewData.setFilters(new InputFilter[]{new RemovePrefixZeroFilter(viewData),new IntegerValidFilter()});
        }else if(config.getTextType() == IText.TextType.DECIMAL){
            viewData.setFilters(new InputFilter[]{new RemovePrefixZeroFilter(viewData),new DecimalValidFilter()});
        }

        viewData.setGravity(config.getGravity());

        switch (config.getTextType()) {
            case NORMAL:
                viewData.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                break;
            case INTEGER:
                viewData.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;
            case DECIMAL:
                viewData.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
        }
    }

    public void addView(ViewConfig config) {
        View childView = null;
        switch (config.getType()) {
            case BTN:
                childView = initButton((BtnConfig) config);
                break;
            case TEXT:
                childView = initTextView((TextConfig) config);
                break;
            case EDIT:
                childView = initEditText((EditConfig) config);
                break;
            case BLOCK:
                childView = initBlock((BlockConfig) config);
                break;
            default:
                Preconditions.checkArgument(false, "Unknow view type");
        }
        addView(childView);
        mChildrenView.put(config.getTag(), childView);
        mChildrenConfig.put(config.getTag(), config);
    }

    public View getViewWithTag(String tag) {
        return mChildrenView.get(tag);
    }

    public void removeView(String tag) {
        removeView(mChildrenView.remove(tag));
        mChildrenConfig.remove(tag);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnInputListener(OnInputListener onInputListener) {
        this.mOnInputListener = onInputListener;
    }

    public void setOnNumComputeHandler(OnNumComputeHandler onNumComputeHandler) {
        this.mOnNumComputeHandler = onNumComputeHandler;
    }

    public void setOnIntegerErrorListenerWithTag(String tag, OnIntegerErrorListener listener) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");
        Preconditions.checkArgument(config instanceof TextConfig, "The view is not supported this operation");

        TextConfig childConfig = (TextConfig) config;
        Preconditions.checkArgument(childConfig.getTextType() == IText.TextType.INTEGER, "The view is not supported this operation");

        mIntegerErrorListeners.put(tag, listener);
    }

    public void setOnDecimalErrorListenerWithTag(String tag, OnDecimalErrorListener listener) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");
        Preconditions.checkArgument(config instanceof TextConfig, "The view is not supported this operation");

        TextConfig childConfig = (TextConfig) config;
        Preconditions.checkArgument(childConfig.getTextType() == IText.TextType.DECIMAL, "The view is not supported this operation");

        mDecimalErrorListeners.put(tag, listener);
    }

    public void addNumWithTag(String tag) {
        operationNumWithTag(tag, Operation.ADD);
    }

    public void lesNumWithTag(String tag) {
        operationNumWithTag(tag, Operation.LES);
    }

    /**
     * 对指定tag对view,直接设定值.
     * 因为该值是由程序直接设定的,所以不需要再调用OnNumComputeHandler让程序重新计算一遍
     *
     * @param tag
     * @param values
     */
    public void setNumWithTag(String tag, String values) {
        View view = mChildrenView.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkArgument(view instanceof TextView, "The view is not supported this operation");

        TextView childView = (TextView) view;
        childView.setText(values);

        confirmEditDataWithTag(tag);
    }

    /**
     * 由于EditText是可以点击编辑的,所以什么时候对输入对数据进行检查是一个问题.
     * 对数据对检查时机根据业务会有很大对不同,可能是点击类一个确定按钮,或者是键盘回收时.
     * 所以暴露这个函数,由用户确定检查数据对时机.
     * 这个函数检查数据对最小值和公差,
     */
    public void confirmEditDataWithTag(String tag) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");

        if (!(config instanceof TextConfig)) {
            return;
        }

        TextView childView = (TextView) view;
        TextConfig childConfig = (TextConfig) config;

        switch (childConfig.getTextType()) {
            case INTEGER:
                OnIntegerErrorListener integerlistener = mIntegerErrorListeners.get(tag);
                IntegerErrorHalper integerhalper = new IntegerErrorHalper(
                        (int) childConfig.getMinNum(),
                        (int) childConfig.getMaxNum(),
                        (int) childConfig.getTolerance(),
                        childConfig.isEditNumGtZero());
                integerhalper.setOnIntegerErrorListener(integerlistener);

                String integerValues = childView.getText().toString();
                if(TextUtils.isEmpty(integerValues)){
                    integerValues = "0";
                }
                Integer integerResult = integerhalper.completeFilter(Integer.valueOf(integerValues));
                if (integerResult != null) {
                    childView.setText(String.valueOf(integerResult));
                }
                break;
            case DECIMAL:
                OnDecimalErrorListener decimallistener = mDecimalErrorListeners.get(tag);
                DecimalErrorHalper decimalhalper = new DecimalErrorHalper(
                        childConfig.getMinNum(),
                        childConfig.getMaxNum(),
                        childConfig.getTolerance(),
                        childConfig.isEditNumGtZero());
                decimalhalper.setOnDecimalErrorListener(decimallistener);

                String decimalValues = childView.getText().toString();
                if(TextUtils.isEmpty(decimalValues)){
                    decimalValues = "0";
                }
                Float decimalResult = decimalhalper.completeFilter(Float.valueOf(decimalValues));
                if (decimalResult != null) {
                    childView.setText(String.valueOf(decimalResult));
                }
                break;
            case NORMAL:
                break;
        }

        if (mOnInputListener != null) {
            mOnInputListener.onInputComplete(this,
                    tag,
                    childView.getText().toString(),
                    childConfig.getMinNum(),
                    childConfig.getMaxNum(),
                    childConfig.getTolerance());
        }
    }

    /**
     * 根据tag寻找view 并设置按钮的可用性
     *
     * @param tag       指定的view的tag
     * @param clickable 是否能够点击,开启的话只是ui层面的置灰
     */
    public void setViewEnableWithTag(String tag, boolean enable, boolean clickable) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");

        if (enable) {
            view.setEnabled(true);
            view.setClickable(clickable);
            if (config.getBackgroundResource() != 0) {
                view.setBackgroundResource(config.getBackgroundResource());
            }
        } else {
            if (clickable) {
                view.setEnabled(true);
                view.setClickable(true);
            } else {
                view.setEnabled(false);
                view.setClickable(false);
            }
            if (config.getUnEnableBackgroundResource() != 0) {
                view.setBackgroundResource(config.getUnEnableBackgroundResource());
            }
        }
    }

    public void operationNumWithTag(String tag, Operation antion) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");
        Preconditions.checkArgument(config instanceof TextConfig, "The view is not supported this operation");

        TextView childView = (TextView) view;
        TextConfig childConfig = (TextConfig) config;
        switch (childConfig.getTextType()) {
            case NORMAL:
                String newText = null;
                if (mOnNumComputeHandler != null) {
                    newText = mOnNumComputeHandler.onNormalTextComputeHandler(
                            tag,
                            childView.getText(),
                            antion,
                            childConfig.getMinNum(),
                            childConfig.getMaxNum(),
                            childConfig.getTolerance());
                }
                if (newText != null) {
                    childView.setText(newText);
                    return;
                }
                break;
            case INTEGER:
                int oldIntegerNum;
                try {
                    oldIntegerNum = Integer.valueOf(childView.getText().toString());
                } catch (NumberFormatException e) {
                    oldIntegerNum = 0;
                }
                Integer newInteger = null;
                if (mOnNumComputeHandler != null) {
                    newInteger = mOnNumComputeHandler.onIntegerNumComputeHandler(
                            tag,
                            oldIntegerNum,
                            antion,
                            (int) childConfig.getMinNum(),
                            (int) childConfig.getMaxNum(),
                            (int) childConfig.getTolerance());
                }
                if (newInteger != null) {
                    childView.setText(String.valueOf(newInteger));
                    return;
                } else {
                    int result = 0;
                    if (antion == Operation.ADD) {
                        result = oldIntegerNum + (int) childConfig.getTolerance();
                    } else if (antion == Operation.LES) {
                        result = oldIntegerNum - (int) childConfig.getTolerance();
                    }
                    childView.setText(String.valueOf(result));
                }
                break;
            case DECIMAL:
                float oldFloatNum;
                try {
                    oldFloatNum = Float.valueOf(childView.getText().toString());
                } catch (NumberFormatException e) {
                    oldFloatNum = 0;
                }
                Float newFloat = null;
                if (mOnNumComputeHandler != null) {
                    newFloat = mOnNumComputeHandler.onDecimalNumComputeHandler(
                            tag,
                            oldFloatNum,
                            antion,
                            childConfig.getMinNum(),
                            childConfig.getMaxNum(),
                            childConfig.getTolerance());
                }
                if (newFloat != null) {
                    childView.setText(String.valueOf(newFloat));
                    return;
                } else {
                    float result = 0;
                    if (antion == Operation.ADD) {
                        result = oldFloatNum + childConfig.getTolerance();
                    } else if (antion == Operation.LES) {
                        result = oldFloatNum - childConfig.getTolerance();
                    }
                    childView.setText(String.valueOf(result));
                }
                break;
        }

        confirmEditDataWithTag(tag);
    }

    public String getNumStrWithTag(String tag) {
        View view = mChildrenView.get(tag);
        ViewConfig config = mChildrenConfig.get(tag);
        Preconditions.checkNotNull(view, "Can not find the view with designation tag");
        Preconditions.checkNotNull(config, "Can not find the view with designation tag");
        Preconditions.checkArgument(config instanceof TextConfig, "The view is not supported this operation");

        TextView childView = (TextView) view;
        return childView.getText().toString();
    }

    /**
     * item点击监听
     */
    public interface OnItemClickListener {
        void onItemClick(NumLayout parent, View view, ViewConfig.ViewType type, String tag);
    }

    /**
     * 输入监听
     */
    public interface OnInputListener {
        /**
         * 输入完成监听
         *
         * @param parent
         * @param tag
         * @param text
         */
        void onInputComplete(NumLayout parent, String tag, String text, float min, float max, float tolerance);
    }

    /**
     * 如果对计算有特殊对处理,可以设置该监听
     */
    public interface OnNumComputeHandler {

        /**
         * 指定tag的View{@link com.daliammao.numlayoutlib.config.inter.IText.TextType}值为NORMAL,
         * 被操作时触发.
         *
         * @param tag
         * @param oldText
         * @param action
         * @param minNum
         * @param maxNum
         * @param tolerance
         * @return
         */
        String onNormalTextComputeHandler(String tag, CharSequence oldText, Operation action, float minNum, float maxNum, float tolerance);

        /**
         * 指定tag的View{@link com.daliammao.numlayoutlib.config.inter.IText.TextType}INTEGER,
         * 被操作时触发.
         *
         * @param tag
         * @param oldNum
         * @param action
         * @param minNum
         * @param maxNum
         * @param tolerance
         * @return
         */
        Integer onIntegerNumComputeHandler(String tag, int oldNum, Operation action, int minNum, int maxNum, int tolerance);

        /**
         * 指定tag的View{@link com.daliammao.numlayoutlib.config.inter.IText.TextType}DECIMAL,
         * 被操作时触发.
         *
         * @param tag
         * @param oldNum
         * @param action
         * @param minNum
         * @param maxNum
         * @param tolerance
         * @return
         */
        Float onDecimalNumComputeHandler(String tag, float oldNum, Operation action, float minNum, float maxNum, float tolerance);
    }

    /**
     * 操作的枚举
     */
    public enum Operation {
        ADD,
        LES
    }
}
