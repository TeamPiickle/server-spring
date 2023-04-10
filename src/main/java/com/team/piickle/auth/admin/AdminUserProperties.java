package com.team.piickle.auth.admin;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Setter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "admin")
public class AdminUserProperties {
    private String id;
    private String password;

    public boolean isMatched(String id, String password) {
        return id.equals(this.id) && password.equals(this.password);
    }
}
