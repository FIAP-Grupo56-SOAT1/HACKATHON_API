package com.hackathon.fiap.timesheet.application.core.ports.out;

public interface GetSubjectOutputPort {
    String getSubject(String tokenJWT);
}
