package com.haocai.module.system.vo;

public class Data extends UserData{
    private String password;
    private String username;
    private String authorities;
    private String accountNonExpired;
    private String accountNonLocked;
    private String credentialsNonExpired;
    private String enabled;
    private String type;
    private Info info;

    @Override
    public String toString() {
        return "Data{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", authorities='" + authorities + '\'' +
                ", accountNonExpired='" + accountNonExpired + '\'' +
                ", accountNonLocked='" + accountNonLocked + '\'' +
                ", credentialsNonExpired='" + credentialsNonExpired + '\'' +
                ", enabled='" + enabled + '\'' +
                ", type='" + type + '\'' +
                ", info=" + info +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(String accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(String accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(String credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
