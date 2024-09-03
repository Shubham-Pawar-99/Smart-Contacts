package com.smart_contact.smart_contact.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3,message = "Minimum 3 character is required")
    private String name;
    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Passwaord is required")
    @Size(min = 6,message = "Minimum 6 character required")
    private String password;
    private String about;
    private String phoneNumber;

}

