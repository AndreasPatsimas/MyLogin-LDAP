package org.patsimas.loginldap.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.loginldap.domain.Member;
import org.patsimas.loginldap.dto.MyUserDetails;
import org.patsimas.loginldap.dto.Person;
import org.patsimas.loginldap.repositories.MemberRepository;
import org.patsimas.loginldap.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Load person process [username: {}] start", username);

        Person person = personRepository.findPersonByUsername(username);

        Optional<Member> member = memberRepository.findMemberByUsername(username);

        if (ObjectUtils.isEmpty(person) || !member.isPresent())
            throw new UsernameNotFoundException("Not found " + username);

        List<GrantedAuthority> authorities = member.get().getAuthorities().stream().map(authority ->
                new SimpleGrantedAuthority(authority.getDescription().name())
        ).collect(Collectors.toList());

        person.setAuthorities(authorities);

        return new MyUserDetails(person);
    }
}
