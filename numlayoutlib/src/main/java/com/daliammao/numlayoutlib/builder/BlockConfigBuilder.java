package com.daliammao.numlayoutlib.builder;

import com.daliammao.numlayoutlib.config.BlockConfig;
import com.daliammao.numlayoutlib.config.inter.IBlock;

/**
 * @author: zhoupengwei
 * @time:16/5/6-上午11:56
 * @Email: 496946423@qq.com
 * @desc:
 */
public class BlockConfigBuilder extends ViewConfigBuilder<BlockConfigBuilder> {
private BlockConfig mBlockConfig;

    public static BlockConfigBuilder newBlockConfigBuilder() {
        return new BlockConfigBuilder(new BlockConfig());
    }

    BlockConfigBuilder(BlockConfig blockConfig) {
        super(blockConfig);
        mBlockConfig = blockConfig;
    }

    public BlockConfigBuilder blockWithLine() {
        mBlockConfig.setBlockType(IBlock.BlockType.LINE);
        return this;
    }

    public BlockConfigBuilder blockWithNormal() {
        mBlockConfig.setBlockType(IBlock.BlockType.NORMAL);
        return this;
    }
}
