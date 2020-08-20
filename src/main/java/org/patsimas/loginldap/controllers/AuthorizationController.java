package org.patsimas.loginldap.controllers;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.loginldap.domain.Member;
import org.patsimas.loginldap.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AuthorizationController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = "/hello")
    public List<Member> all(){

        log.info("page for all");

        return memberRepository.findAll();
    }

    @GetMapping(value = "/user")
    public String users(){

        log.info("page for users");

        return "Aris Thessaloniki Forever!!!";
    }

    @GetMapping(value = "/admin")
    public String admin(){

        log.info("page for admin");

        return "Aris Thessaloniki FC!!!";
    }
}
