package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendChannelTemplateExample;
import com.roman.alarm.alarmhub.domain.AlarmSendChannelTemplateWithBLOBs;
import com.roman.alarm.alarmhub.mapper.AlarmSendChannelTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmSendChannelTemplateDao {

    @Resource
    private AlarmSendChannelTemplateMapper alarmSendChannelTemplateMapper;

    public List<AlarmSendChannelTemplateWithBLOBs> getByTemplateId(Long alarmSendTemplateId) {
        AlarmSendChannelTemplateExample example = new AlarmSendChannelTemplateExample();
        example.createCriteria().andTemplateIdEqualTo(alarmSendTemplateId);
        return alarmSendChannelTemplateMapper.selectByExampleWithBLOBs(example);
    }

    public AlarmSendChannelTemplateWithBLOBs getByTemplateIdAndChannelType(Long templateId, Integer channelType) {
        AlarmSendChannelTemplateExample example = new AlarmSendChannelTemplateExample();
        example.createCriteria().andTemplateIdEqualTo(templateId).andChannelTypeEqualTo(channelType);
        List<AlarmSendChannelTemplateWithBLOBs> templateWithBLOBs = alarmSendChannelTemplateMapper.selectByExampleWithBLOBs(example);
        return CollectionUtils.isEmpty(templateWithBLOBs) ? null : templateWithBLOBs.get(0);
    }

    public void deleteByTemplateId(Long templateId) {
        AlarmSendChannelTemplateExample example = new AlarmSendChannelTemplateExample();
        example.createCriteria().andTemplateIdEqualTo(templateId);
        alarmSendChannelTemplateMapper.deleteByExample(example);
    }

    public void insert(List<AlarmSendChannelTemplateWithBLOBs> alarmSendChannelTemplateList) {
        if (CollectionUtils.isEmpty(alarmSendChannelTemplateList)) {
            return;
        }
        alarmSendChannelTemplateMapper.batchInsert(alarmSendChannelTemplateList);
    }
}
