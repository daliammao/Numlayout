package com.daliammao.numlayoutlib.config;

import com.daliammao.numlayoutlib.config.inter.IEdit;

/**
 * @author: zhoupengwei
 * @time:16/4/28-下午4:27
 * @Email: 496946423@qq.com
 * @desc:
 */
public class EditConfig extends TextConfig implements IEdit {

    @Override
    public ViewType getType() {
        return ViewType.EDIT;
    }
}
