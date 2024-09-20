package com.tomato.llm.server.dao.impl;

import cn.mybatis.mp.core.sql.executor.chain.QueryChain;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.tomato.llm.server.mapper.ConnectConfigMapper;
import com.tomato.llm.server.entity.ConnectConfig;
import com.tomato.llm.server.dao.ConnectConfigDao;
import cn.mybatis.mp.core.mvc.impl.DaoImpl;

import java.util.List;

/**
 * <p>
 * 连接配置表 Dao 实现类
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-20
 */
@Repository
public class ConnectConfigDaoImpl extends DaoImpl<ConnectConfig,Long> implements ConnectConfigDao{

    @Autowired
    public ConnectConfigDaoImpl (ConnectConfigMapper connectConfigMapper){
        super(connectConfigMapper);
    }

    @Override
    protected ConnectConfigMapper getMapper(){
        return (ConnectConfigMapper) this.mapper;
    }


    @Override
    public List<ConnectConfig> getAll() {
        return QueryChain.of(mapper).list();
    }
}
