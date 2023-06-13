package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendGroup;
import com.roman.alarm.alarmhub.domain.AlarmSendGroupExample;
import com.roman.alarm.alarmhub.mapper.AlarmSendGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmSendGroupDao {

    @Resource
    private AlarmSendGroupMapper alarmSendGroupMapper;

    public AlarmSendGroup getById(Long groupId) {
        return alarmSendGroupMapper.selectByPrimaryKey(groupId);
    }

    public void insert(AlarmSendGroup alarmSendGroup) {
        alarmSendGroupMapper.insertSelective(alarmSendGroup);
    }

    public void updateById(AlarmSendGroup alarmSendGroup) {
        alarmSendGroup.setCreateTime(null);
        alarmSendGroup.setCreateUser(null);
        alarmSendGroupMapper.updateByPrimaryKeySelective(alarmSendGroup);
    }

    public AlarmSendGroup getFirstByName(String name) {
        AlarmSendGroupExample example = new AlarmSendGroupExample();
        example.createCriteria().andGroupNameEqualTo(name);
        example.setRows(1);
        List<AlarmSendGroup> list = alarmSendGroupMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
