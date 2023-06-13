package com.roman.alarm.alarmhub.dao;

import com.roman.alarm.alarmhub.domain.AlarmSendLog;
import com.roman.alarm.alarmhub.domain.AlarmSendReceiver;
import com.roman.alarm.alarmhub.enums.AlarmAckStateEnum;
import com.roman.alarm.alarmhub.event.AlarmSendEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class AlarmSendLogDao {

    public AlarmSendLog build(AlarmSendEvent alarmSendEvent, List<AlarmSendReceiver> receiverList) {
        AlarmSendLog alarmSendLog = new AlarmSendLog();
        alarmSendLog.setAlarmSendLogKey(alarmSendEvent.getSendLogKey());
        alarmSendLog.setAlarmTaskKey(alarmSendEvent.getAlarmTaskKey());
        alarmSendLog.setAlarmRuleName(alarmSendEvent.getRuleName());
        alarmSendLog.setAlarmMetricName(alarmSendEvent.getMetricName());
        alarmSendLog.setMetricValue(alarmSendEvent.getMetricValue());
        alarmSendLog.setAlarmLevel(alarmSendEvent.getAlarmLevel().getCode());
        alarmSendLog.setAlarmState(alarmSendEvent.getAlarmState().getCode());
        alarmSendLog.setAlarmTime(alarmSendEvent.getAlarmDate());
        alarmSendLog.setAckState(AlarmAckStateEnum.NOT_ACK.getCode());
        alarmSendLog.setReceiverList(receiverList.stream().map(receiver->
                receiver.getReceiverType() + ":" + receiver.getReceiverKey()).collect(Collectors.joining(",")));
        alarmSendLog.setMergedAlarmLogKey(String.join(",", alarmSendEvent.getMergedAlarmKeyList()));
        return alarmSendLog;

    }
}
