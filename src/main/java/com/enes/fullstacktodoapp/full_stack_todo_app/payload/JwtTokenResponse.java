package com.enes.fullstacktodoapp.full_stack_todo_app.payload;

public class JwtTokenResponse {
    private boolean valid;
    private String token;

    public JwtTokenResponse(boolean valid, String token) {
        this.valid = valid;
        this.token = token;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
