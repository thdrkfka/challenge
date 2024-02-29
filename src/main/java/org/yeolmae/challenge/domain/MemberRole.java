package org.yeolmae.challenge.domain;

public enum MemberRole {
    USER("USER"), ADMIN("ADMIN");

    String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
