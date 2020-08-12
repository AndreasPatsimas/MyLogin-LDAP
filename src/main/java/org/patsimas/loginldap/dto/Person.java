package org.patsimas.loginldap.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    String fullName;

    String surName;

    String username;

    String password;
}
