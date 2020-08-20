package org.patsimas.loginldap.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADUSERNAME", unique=true)
    private String username;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "MEMBER_AUTHORITIES", joinColumns =
    @JoinColumn(name = "MEMBER_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID")
    )
    private Set<Authority> authorities;
}
