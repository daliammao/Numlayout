package com.daliammao.numlayoutlib.config.inter;

import com.daliammao.numlayoutlib.config.ViewConfig;

import java.util.Map;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午7:35
 * @Email: 496946423@qq.com
 * @desc:
 */
public interface ILayout {

    /**
     * 添加子View
     *
     * @param viewConfig
     * @return
     */
    public ViewConfig addChild(ViewConfig viewConfig);

    /**
     * 返回所有子view
     *
     * @return
     */
    public Map getChildrenView();

    /**
     * 设置横向还是纵向
     *
     * @param orientation
     */
    public void setOrientation(int orientation);

    public int getOrientation();
}
