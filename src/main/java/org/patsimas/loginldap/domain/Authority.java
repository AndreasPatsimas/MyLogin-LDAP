package org.patsimas.loginldap.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.patsimas.loginldap.AuthorityDesc;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORITIES")
public class Authority {

    @Id
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "DESCRIPTION")
    private AuthorityDesc description;
}
