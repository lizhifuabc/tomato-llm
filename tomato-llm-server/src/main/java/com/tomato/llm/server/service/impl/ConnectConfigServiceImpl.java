package com.tomato.llm.server.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tomato.llm.server.service.ConnectConfigService;
import com.tomato.llm.server.entity.ConnectConfig;
import com.tomato.llm.server.dao.ConnectConfigDao;

/**
 * <p>
 * 连接配置表 Service 实现类
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-20
 */
@Service
public class ConnectConfigServiceImpl  implements ConnectConfigService{

    @Autowired
    private ConnectConfigDao connectConfigDao;
    
}
