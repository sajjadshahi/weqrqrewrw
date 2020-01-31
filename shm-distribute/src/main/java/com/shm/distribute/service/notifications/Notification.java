package com.shm.distribute.service.notifications;

import com.shm.common.dto.base.ResultDto;
import com.shm.distribute.service.distributable.IDistributable;

public abstract class Notification implements IDistributable {

    private String title;
    private String picture;
    private String description;
    private String typeString;
    private int severity;

    @Override
    public ResultDto distribute() {
        String log = "Notification ---" + this.title + "--- of Type: " + this.typeString + " Distribute Call!";
        System.out.println(log);
        return new ResultDto(true, this.continueDistribute());

    }

    @Override
    public ResultDto cancel() {
        String log = "Notification ---" + this.title + "--- of Type: " + this.typeString + " Cancel Call!";
        System.out.println(log);
        return new ResultDto(true, this.continueCancel());
    }


    public String continueDistribute(){
        return "DISTRIBUTE";
    }

    public String continueCancel(){
        return "CANCEL";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
