package com.ch.demo.model.binding;

import com.ch.demo.model.validator.FieldMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
    first = "password",
    second = "confirmPassword"
)
public class UserRegisterBindingModel {

    @NotEmpty
    @Size(min = 3)
    private String username;
    @NotEmpty
    @Size(min = 3)
    private String fullName;
    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;
    private String confirmPassword;
    @NotEmpty
    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
