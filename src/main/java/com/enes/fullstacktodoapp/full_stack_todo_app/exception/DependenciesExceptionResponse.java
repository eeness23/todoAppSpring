package com.enes.fullstacktodoapp.full_stack_todo_app.exception;

public class DependenciesExceptionResponse {
    private String dependencies;

    public DependenciesExceptionResponse(String dependencies) {
        this.dependencies = dependencies;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }
}
