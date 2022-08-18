package com.sid.secservice.security;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="AbdoHero";
    public static final long EXPIRATION=1*24*3600;
    public static final String HEADER_PREFIX="Bearer ";
    public static final long REFRESH_EXPIRATION = 300 * 24 * 3600;
}