package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmLog;
import com.roman.alarm.alarmhub.mapper.AlarmLogCustomMapper;
import com.roman.alarm.alarmhub.mapper.AlarmLogMapper;
import com.roman.alarm.alarmhub.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Slf4j
@Service
public class AlarmLogDao {

    @Resource
    private AlarmLogMapper alarmLogMapper;
    @Resource
    private AlarmLogCustomMapper alarmLogCustomMapper;

    public void insert(AlarmLog alarmSendEvent) {
        alarmLogMapper.insertSelective(alarmSendEvent);
    }

    public Long getMaxIdBeforeDay(Integer alarmLogSaveDays) {
        Date date = DateUtils.addDays(new Date(), -1 * alarmLogSaveDays);
        return alarmLogCustomMapper.getMaxId(date);
    }

    public void deleteByMaxId(Long maxId) {
        if (maxId == null) {
            return;
        }
        alarmLogCustomMapper.deleteByMaxId(maxId);
    }
}
