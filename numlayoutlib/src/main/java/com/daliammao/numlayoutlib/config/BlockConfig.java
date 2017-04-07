package com.daliammao.numlayoutlib.config;

import com.daliammao.numlayoutlib.config.inter.IBlock;

/**
 * @author: zhoupengwei
 * @time:16/5/6-上午11:46
 * @Email: 496946423@qq.com
 * @desc:
 */
public class BlockConfig extends ViewConfig implements IBlock {
    private BlockType mBlockType = BlockType.NORMAL;

    @Override
    public ViewType getType() {
        return ViewType.BLOCK;
    }

    public BlockType getBlockType() {
        return mBlockType;
    }

    public void setBlockType(BlockType blockType) {
        this.mBlockType = blockType;
    }
}
