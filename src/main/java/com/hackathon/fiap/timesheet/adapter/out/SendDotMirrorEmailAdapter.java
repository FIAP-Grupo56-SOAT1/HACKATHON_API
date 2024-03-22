package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.ports.out.SendDotMirrorEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendDotMirrorEmailAdapter implements SendDotMirrorEmailOutputPort {

    @Override
    public void send(String email, DefaultDotMirrorReportData reportData) {

    }
}
