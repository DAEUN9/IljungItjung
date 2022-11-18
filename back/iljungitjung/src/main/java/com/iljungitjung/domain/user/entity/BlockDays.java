package com.iljungitjung.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BlockDays {

    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;

    public void updateBlockDays(List<Boolean> blockDaysList){
        int dayCount=0;

        this.monday = blockDaysList.get(dayCount++);
        this.tuesday = blockDaysList.get(dayCount++);
        this.wednesday = blockDaysList.get(dayCount++);
        this.thursday = blockDaysList.get(dayCount++);
        this.friday = blockDaysList.get(dayCount++);
        this.saturday = blockDaysList.get(dayCount++);
        this.sunday = blockDaysList.get(dayCount);
    }
    public List<Boolean> getBlockDays(){
        List<Boolean> blockDays = new ArrayList<>();

        blockDays.add(this.monday);
        blockDays.add(this.tuesday);
        blockDays.add(this.wednesday);
        blockDays.add(this.thursday);
        blockDays.add(this.friday);
        blockDays.add(this.saturday);
        blockDays.add(this.sunday);

        return blockDays;
    }
}
