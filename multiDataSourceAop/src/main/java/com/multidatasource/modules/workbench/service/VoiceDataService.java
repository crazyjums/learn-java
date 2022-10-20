package com.multidatasource.modules.workbench.service;

import com.multidatasource.modules.workbench.domain.po.VoiceData;

import java.util.List;

public interface VoiceDataService {

    List<VoiceData> getAllFromMaster();


    List<VoiceData> getAllFromSlave();

}
