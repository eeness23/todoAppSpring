package com.enes.fullstacktodoapp.full_stack_todo_app.Security;

public class SecurityConstants {
    public static final String SING_UP_AND_LOGIN_URL = "/users/**";
    public static final String H2_URL="/h2-console/**";
    public static final String ALL_TASKS_URL="/tasks";
    public static final String SECRET_KEY = "SecretKeyForJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 90_000;
}
