package com.iljungitjung.domain.notification.entity;

import com.iljungitjung.domain.notification.exception.phone.NoMatchAutoScheduleException;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public enum Auto {

    ACCEPT(Type.ACCEPT, null,"일정있정에서 안내드립니다.\n[%s]\n%s\n%s - %s\n[%s]예약이 승인되었습니다."),
    CANCEL(Type.CANCEL, "사용자","일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n[%s]님의 예약 신청이 취소되었습니다.\n"),
    REFUSE(Type.CANCEL, "제공자","일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n [%s] 예약 신청이 거절되었습니다.\n"),
    DELETE(Type.DELETE,null,"일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]의 [%s]예약이 취소되었습니다.\n"),
    REQUEST(Type.REQUEST,null, "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]님이 [%s]예약을 신청 하셨습니다.\n홈페이지에서 확인해주세요.");

    private final Type scheduleType;
    private final String canceler;
    private final String base;

    private Auto(Type scheduleType, String canceler, String base) {
        this.scheduleType = scheduleType;
        this.canceler = canceler;
        this.base = base;
    }

    public static Auto getMatchAuto(Type scheduleType, String canceler) {
        return Arrays.stream(Auto.values())
                .filter(prize -> prize.isMatch(scheduleType, canceler))
                .findAny()
                .orElseThrow(NoMatchAutoScheduleException::new);
    }

    public boolean isMatch(Type scheduleType, String canceler) {
        return scheduleType.equals(this.scheduleType) && (canceler == this.canceler);
    }

    public String getPhonenum(Schedule schedule) {
        if (this.equals(CANCEL) || this.equals(REQUEST)) {
            return schedule.getUserTo().getPhonenum();
        }
        return schedule.getPhonenum();
    }

    public String makeContent(Schedule schedule) {
        String date = makeDateFormat(schedule.getStartDate());
        String startTime = makeTimeFormat(schedule.getStartDate());
        String endTime = makeTimeFormat(schedule.getEndDate());

        if (this.equals(ACCEPT)) {
            return String.format(base, schedule.getUserTo().getNickname(), date, startTime, endTime, schedule.getCategoryName());
        }
        if (this.equals(CANCEL)) {
            return String.format(base, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getUserTo().getNickname());
        }
        if (this.equals(REFUSE)) {
            return String.format(base, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
        }
        if (this.equals(REQUEST)) {
            return String.format(base, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
        }
        if (this.equals(DELETE)) {
            return String.format(base, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
        }
        throw new NoMatchAutoScheduleException();
    }

    private String makeDateFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("yyyy-MM-dd");
        return base.format(date);
    }

    private String makeTimeFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("HH:mm");
        return base.format(date);
    }
}
