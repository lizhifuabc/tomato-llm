package com.tomato.llm.server.dao.impl;

import cn.mybatis.mp.core.sql.executor.chain.QueryChain;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.tomato.llm.server.mapper.CustomPromptMapper;
import com.tomato.llm.server.entity.CustomPrompt;
import com.tomato.llm.server.dao.CustomPromptDao;
import cn.mybatis.mp.core.mvc.impl.DaoImpl;

/**
 * <p>
 * 自定义提示词 Dao 实现类
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-19
 */
@Repository
public class CustomPromptDaoImpl extends DaoImpl<CustomPrompt,Long> implements CustomPromptDao {

    @Autowired
    public CustomPromptDaoImpl (CustomPromptMapper customPromptMapper){
        super(customPromptMapper);
    }

    @Override
    protected CustomPromptMapper getMapper(){
        return (CustomPromptMapper) this.mapper;
    }

    @Override
    public CustomPrompt getDefaultPrompt() {
        return QueryChain.of(this.mapper).eq(CustomPrompt::getPromptType, 1).get();
    }
}
