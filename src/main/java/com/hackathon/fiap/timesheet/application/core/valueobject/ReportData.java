package com.hackathon.fiap.timesheet.application.core.valueobject;

import java.util.List;

public abstract class ReportData<T> {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
