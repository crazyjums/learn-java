package com.multidatasourceaop.modules.workbench.service.impl;

import com.multidatasourceaop.annotaion.TargetDataSource;
import com.multidatasourceaop.enums.DBType;
import com.multidatasourceaop.modules.workbench.domain.po.VoiceData;
import com.multidatasourceaop.modules.workbench.mapper.VoiceDataMapper;
import com.multidatasourceaop.modules.workbench.service.VoiceDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoiceDataServiceImpl implements VoiceDataService {

    @Resource
    VoiceDataMapper voiceDataMapper;

    @TargetDataSource(targetDB = DBType.MASTER)
    @Override
    public List<VoiceData> getAllFromMaster() {
        List<VoiceData> list = voiceDataMapper.getAll();
        return list;
    }

    @TargetDataSource(targetDB = DBType.SLAVE)  //显示的控制走从库
    @Override
    public List<VoiceData> getAllFromSlave() {
        List<VoiceData> list = voiceDataMapper.getAll();
        return list;
    }
}
