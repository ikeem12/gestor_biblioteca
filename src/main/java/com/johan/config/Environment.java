package com.johan.config;

public enum Environment{
    DEVELOPMENT, STAGING, PRODUCTION;

    public static Environment current(){
        String env = System.getenv("APP_ENV");
        
        if(env == null) return DEVELOPMENT;

        try {
            return Environment.valueOf(env.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("APP_ENV inválido: " + env + ". Valores válidos: DEVELOPMENT, STAGING, PRODUCTION");
        }
    }
}