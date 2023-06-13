package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendLog;
import com.roman.alarm.alarmhub.domain.AlarmSendLogExample;
import com.roman.alarm.alarmhub.mapper.AlarmSendLogCustomeMapper;
import com.roman.alarm.alarmhub.mapper.AlarmSendLogMapper;
import com.roman.alarm.alarmhub.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class AlarmSendLogService {

    @Resource
    private AlarmSendLogMapper alarmSendLogMapper;
    @Resource
    private AlarmSendLogCustomeMapper alarmSendLogCustomeMapper;

    public void insert(AlarmSendLog alarmSendLog) {
        alarmSendLogMapper.insert(alarmSendLog);
    }

    public AlarmSendLog getByKey(String alarmSendLogKey) {
        AlarmSendLogExample example = new AlarmSendLogExample();
        example.createCriteria().andAlarmSendLogKeyEqualTo(alarmSendLogKey);
        List<AlarmSendLog> alarmSendLogList = alarmSendLogMapper.selectByExample(example);
        return CollectionUtils.isEmpty(alarmSendLogList) ? null : alarmSendLogList.get(0);
    }

    public void update(AlarmSendLog alarmSendLog) {
        alarmSendLogMapper.updateByPrimaryKey(alarmSendLog);
    }

    public Long getMaxIdBeforeDay(Integer alarmLogSaveDays) {
        Date date = DateUtils.addDays(new Date(), -1 * alarmLogSaveDays);
        return alarmSendLogCustomeMapper.getMaxId(date);
    }

    public void deleteByMaxId(Long maxId) {
        if (maxId == null) {
            return;
        }
        alarmSendLogCustomeMapper.deleteByMaxId(maxId);
    }
}
