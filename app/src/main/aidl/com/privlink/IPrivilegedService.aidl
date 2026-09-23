package com.privlink;

interface IPrivilegedService {
    String getBackend();
    String getIdentity();
    boolean isPrivileged();
}