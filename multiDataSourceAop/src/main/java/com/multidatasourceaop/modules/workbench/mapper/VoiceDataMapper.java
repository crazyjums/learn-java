package com.multidatasourceaop.modules.workbench.mapper;

import com.multidatasourceaop.modules.workbench.domain.po.VoiceData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VoiceDataMapper {

    @Select("select * from voice_data; ")
    List<VoiceData> getAll();


}
