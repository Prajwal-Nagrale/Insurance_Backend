package com.cg.users.register;

import com.cg.users.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
