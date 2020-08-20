package org.patsimas.loginldap.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    String fullName;

    String surName;

    String username;

    String password;

    private List<GrantedAuthority> authorities;
}
