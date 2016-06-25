package com.nbos.capi.modules.identity.v0;

import java.util.List;

public class MemberApiModel {
    private Long id;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getUuid() {
        return uuid;
    }

    public List<SocialAccountApiModel> getSocialAccounts() {
        return socialAccounts;
    }

    public List<EmailConnectApiModel> getEmailConnects() {
        return emailConnects;
    }

    String firstName;
    String lastName;
    String phone;
    String description;
    String uuid;

    List<SocialAccountApiModel> socialAccounts;
    List<EmailConnectApiModel> emailConnects;
}
