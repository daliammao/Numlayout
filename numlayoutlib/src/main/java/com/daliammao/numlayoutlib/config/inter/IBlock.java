package com.daliammao.numlayoutlib.config.inter;

/**
 * @author: zhoupengwei
 * @time:16/5/6-上午11:49
 * @Email: 496946423@qq.com
 * @desc:
 */
public interface IBlock {
    enum BlockType {
        NORMAL, //普通类型,可以设置自定义view,也可以设置一个空视图分开控件给定一个距离
        LINE // 线类型,不能设置自定义view,但可以根据NumLayout的Orientation自动转换高宽
    }


}
