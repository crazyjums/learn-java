package com.multidatasource.modules.workbench.service.impl;

import com.multidatasource.annotaion.TargetDataSource;
import com.multidatasource.enums.DBType;
import com.multidatasource.modules.workbench.domain.po.VoiceData;
import com.multidatasource.modules.workbench.mapper.VoiceDataMapper;
import com.multidatasource.modules.workbench.service.VoiceDataService;
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
