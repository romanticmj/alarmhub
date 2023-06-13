package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendReceiver;
import com.roman.alarm.alarmhub.domain.AlarmSendReceiverExample;
import com.roman.alarm.alarmhub.mapper.AlarmSendReceiverMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmSendReceiverDao {

    @Resource
    private AlarmSendReceiverMapper alarmSendReceiverMapper;

    public List<AlarmSendReceiver> getByGroupId(Long groupId) {
        AlarmSendReceiverExample example = new AlarmSendReceiverExample();
        example.createCriteria().andAlarmSendGroupIdEqualTo(groupId);
        return alarmSendReceiverMapper.selectByExample(example);
    }

    public void insert(AlarmSendReceiver alarmSendReceiver) {
        alarmSendReceiverMapper.insertSelective(alarmSendReceiver);
    }

    public void deleteByGroupId(Long groupId) {
        AlarmSendReceiverExample example = new AlarmSendReceiverExample();
        example.createCriteria().andAlarmSendGroupIdEqualTo(groupId);
        alarmSendReceiverMapper.deleteByExample(example);
    }

    public void insert(List<AlarmSendReceiver> alarmSendChannelTemplateList) {
        alarmSendReceiverMapper.batchInsert(alarmSendChannelTemplateList);
    }
}
