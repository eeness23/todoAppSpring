package com.enes.fullstacktodoapp.full_stack_todo_app.exception;

public class TaskExceptionResponse {

    private String response;

    public TaskExceptionResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
