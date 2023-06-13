package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.MetricRule;
import com.roman.alarm.alarmhub.domain.MetricRuleExample;
import com.roman.alarm.alarmhub.mapper.MetricRuleCustomMapper;
import com.roman.alarm.alarmhub.mapper.MetricRuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class MetricRuleDao {

    @Resource
    private MetricRuleMapper metricRuleMapper;
    @Resource
    private MetricRuleCustomMapper metricRuleCustomMapper;

    public List<MetricRule> getByTaskId(Long id) {
        MetricRuleExample metricRuleExample = new MetricRuleExample();
        metricRuleExample.createCriteria().andTaskIdEqualTo(id);
        return metricRuleMapper.selectByExample(metricRuleExample);
    }

    public void deleteByTaskId(Long taskId) {
        metricRuleCustomMapper.deleteByTaskId(taskId);
    }

    public void batchInsert(List<MetricRule> metricRuleList) {
        metricRuleMapper.batchInsert(metricRuleList);
    }
}
