package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmTask;
import com.roman.alarm.alarmhub.domain.AlarmTaskExample;
import com.roman.alarm.alarmhub.mapper.AlarmTaskCustomMapper;
import com.roman.alarm.alarmhub.mapper.AlarmTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class AlarmTaskDao {

    @Resource
    private AlarmTaskMapper alarmTaskMapper;
    @Resource
    private AlarmTaskCustomMapper alarmTaskCustomMapper;

    public AlarmTask getById(Long id) {
        return alarmTaskMapper.selectByPrimaryKey(id);
    }

    public AlarmTask getByKey(String taskKey) {
        AlarmTaskExample alarmTaskExample = new AlarmTaskExample();
        alarmTaskExample.createCriteria().andTaskKeyEqualTo(taskKey);
        List<AlarmTask> alarmTasks = alarmTaskMapper.selectByExample(alarmTaskExample);
        return CollectionUtils.isEmpty(alarmTasks) ? null : alarmTasks.get(0);
    }

    public Long getMaxId() {
        return alarmTaskCustomMapper.getMaxId();
    }

    public void updateById(AlarmTask alarmTask) {
        alarmTaskMapper.updateByPrimaryKeySelective(alarmTask);
    }

    public void insert(AlarmTask alarmTask) {
        alarmTaskMapper.insertSelective(alarmTask);
    }

    public AlarmTask getFirstByName(String name) {
        AlarmTaskExample example = new AlarmTaskExample();
        example.createCriteria().andTaskNameEqualTo(name);
        example.setRows(1);
        List<AlarmTask> list = alarmTaskMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public AlarmTask getFirstByKey(String key) {
        AlarmTaskExample example = new AlarmTaskExample();
        example.createCriteria().andTaskKeyEqualTo(key);
        example.setRows(1);
        List<AlarmTask> list = alarmTaskMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
