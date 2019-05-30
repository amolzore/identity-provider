package com.amolzore.cloud.auth.controller;

public final class ApiPath {
    public static final String
            API = "api",
            V1 = "v1",
            SERVICE = "identity-provider",
            ID = "{id}",
            TOKEN = "token",
            VALIDATE = "validate";

    public static final String
            API_V1_PATH = "/" + API + "/" + V1,
            SERVICE_PATH = "/" + SERVICE,
            TOKEN_PATH = "/" + TOKEN + "/" + ID,
            VALIDATE_PATH = "/" + VALIDATE;

    private ApiPath() {}
}