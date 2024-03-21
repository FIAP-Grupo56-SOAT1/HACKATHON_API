package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.ports.in.DotMirrorReportInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.SendReportEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.ReportData;
import com.hackathon.fiap.timesheet.application.core.reports.ReportGenerator;
import com.hackathon.fiap.timesheet.application.core.reports.data.DefaultDotMirrorReportData;

public class DotMirrorReportUseCase implements DotMirrorReportInputPort<DefaultDotMirrorReportData, Employee> {
    private final EmployeeOutputPort employeeOutputPort;
    private final SendReportEmailOutputPort sendReportEmailOutputPort;

    public DotMirrorReportUseCase(EmployeeOutputPort employeeOutputPort, SendReportEmailOutputPort sendReportEmailOutputPort) {
        this.employeeOutputPort = employeeOutputPort;
        this.sendReportEmailOutputPort = sendReportEmailOutputPort;
    }

    @Override
    public ReportData<DefaultDotMirrorReportData> generate(Long employeeId, ReportGenerator<DefaultDotMirrorReportData, Employee> reportGenerator) {
        Employee employee = employeeOutputPort.get(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        String email = employee.getEmail();
        reportGenerator.setReportOrigin(employee);
        ReportData<DefaultDotMirrorReportData> reportData = reportGenerator.getReportData();
        sendReportEmailOutputPort.sendEmail(email, reportData);
        return reportData;
    }
}
