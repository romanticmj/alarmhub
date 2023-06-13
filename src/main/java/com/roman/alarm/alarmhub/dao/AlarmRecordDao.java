package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmRecord;
import com.roman.alarm.alarmhub.domain.AlarmRecordExample;
import com.roman.alarm.alarmhub.mapper.AlarmRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmRecordDao {


    @Resource
    private AlarmRecordMapper alarmRecordMapper;

    public void insert(AlarmRecord alarmRecord) {
        alarmRecordMapper.insert(alarmRecord);
    }

    public void update(AlarmRecord alarmRecord) {
        alarmRecordMapper.updateByPrimaryKeySelective(alarmRecord);
    }

    public List<AlarmRecord> query(String alarmTask, String ruleName, String metricName) {
        AlarmRecordExample example = new AlarmRecordExample();
        AlarmRecordExample.Criteria criteria = example.createCriteria();
        criteria.andAlarmTaskKeyEqualTo(alarmTask);
        if (StringUtils.isNotBlank(ruleName)) {
            criteria.andAlarmRuleNameEqualTo(ruleName);
        }
        if (StringUtils.isNotBlank(metricName)) {
            criteria.andAlarmMetricNameEqualTo(metricName);
        }
        return alarmRecordMapper.selectByExample(example);
    }

    public void updateByIdList(List<Long> idList, AlarmRecord alarmRecord) {
        AlarmRecordExample example = new AlarmRecordExample();
        example.createCriteria().andIdIn(idList);
        alarmRecordMapper.updateByExampleSelective(alarmRecord, example);
    }
}
