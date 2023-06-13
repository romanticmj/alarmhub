package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendAckLog;
import com.roman.alarm.alarmhub.mapper.AlarmSendAckLogCustomMapper;
import com.roman.alarm.alarmhub.mapper.AlarmSendAckLogMapper;
import com.roman.alarm.alarmhub.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Slf4j
@Service
public class AlarmSendAckLogDao {

    @Resource
    private AlarmSendAckLogMapper alarmSendAckLogMapper;
    @Resource
    private AlarmSendAckLogCustomMapper alarmSendAckLogCustomMapper;

    public void insert(AlarmSendAckLog alarmSendAckLog) {
        alarmSendAckLogMapper.insert(alarmSendAckLog);
    }

    public Long getMaxIdBeforeDay(Integer alarmLogSaveDays) {
        Date date = DateUtils.addDays(new Date(), -1 * alarmLogSaveDays);
        return alarmSendAckLogCustomMapper.getMaxId(date);
    }

    public void deleteByMaxId(Long maxId) {
        if (maxId == null) {
            return;
        }
        alarmSendAckLogCustomMapper.deleteByMaxId(maxId);
    }
}
