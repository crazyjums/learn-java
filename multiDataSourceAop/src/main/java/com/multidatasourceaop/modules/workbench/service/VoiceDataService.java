package com.multidatasourceaop.modules.workbench.service;

import com.multidatasourceaop.modules.workbench.domain.po.VoiceData;

import java.util.List;

public interface VoiceDataService {

    List<VoiceData> getAllFromMaster();


    List<VoiceData> getAllFromSlave();

}
