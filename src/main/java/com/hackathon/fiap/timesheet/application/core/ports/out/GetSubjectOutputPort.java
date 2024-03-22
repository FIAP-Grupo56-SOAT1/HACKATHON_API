package com.hackathon.fiap.timesheet.application.core.ports.out;

public interface GetSubjectOutputPort {
    String GetSubject(String tokenJWT);
}
