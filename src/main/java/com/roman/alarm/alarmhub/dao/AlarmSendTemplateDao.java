package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendTemplate;
import com.roman.alarm.alarmhub.domain.AlarmSendTemplateExample;
import com.roman.alarm.alarmhub.mapper.AlarmSendTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmSendTemplateDao {

    @Resource
    private AlarmSendTemplateMapper alarmSendTemplateMapper;

    public AlarmSendTemplate getById(Long id) {
        return alarmSendTemplateMapper.selectByPrimaryKey(id);
    }

    public AlarmSendTemplate queryDefaultTemplateBySource(String source) {
        AlarmSendTemplateExample example = new AlarmSendTemplateExample();
        example.createCriteria().andDefaultSourceEqualTo(source);
        List<AlarmSendTemplate> result = alarmSendTemplateMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

    public void updateById(AlarmSendTemplate alarmSendTemplate) {
        alarmSendTemplate.setCreateTime(null);
        alarmSendTemplate.setCreateTime(null);
        alarmSendTemplateMapper.updateByPrimaryKeySelective(alarmSendTemplate);
    }

    public void insert(AlarmSendTemplate alarmSendTemplate) {
        alarmSendTemplateMapper.insertSelective(alarmSendTemplate);
    }

    public Long countByName(String name) {
        AlarmSendTemplateExample example = new AlarmSendTemplateExample();
        example.createCriteria().andTemplateNameEqualTo(name);
        return alarmSendTemplateMapper.countByExample(example);
    }

    public AlarmSendTemplate getFirstByName(String name) {
        AlarmSendTemplateExample example = new AlarmSendTemplateExample();
        example.createCriteria().andTemplateNameEqualTo(name);
        example.setRows(1);
        List<AlarmSendTemplate> list = alarmSendTemplateMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
