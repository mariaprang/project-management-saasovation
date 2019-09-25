package com.projectmanagement.saasovation.project.domain;

public enum ProjectType {

    Business("Business"),
    Software("Service"),
    ServiceDesk("Service Desk");

    private String message;

    public String getMessage() {
        return message;
    }

    ProjectType(String message) {
        this.message = message;
    }


}
