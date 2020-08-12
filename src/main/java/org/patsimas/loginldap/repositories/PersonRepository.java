package org.patsimas.loginldap.repositories;

import org.patsimas.loginldap.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.security.ldap.LdapUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class PersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    public Person findPersonByUsername(String username) {

        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(5000)
                .countLimit(10)
                .attributes("cn", "sn", "uid", "userPassword")
                //.base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("uid").is(username)
                .and("uid").isPresent();

        return ldapTemplate.search(query, new PersonAttributesMapper()).get(0);
    }

    private class PersonAttributesMapper implements AttributesMapper<Person> {
        public Person mapFromAttributes(Attributes attrs) throws NamingException {

            String password = null;

            Object passwordValue = attrs.get("userPassword").get();

            if (!ObjectUtils.isEmpty(passwordValue))
                password = LdapUtils.convertPasswordToString(passwordValue);

            return Person.builder()
                    .fullName((String)attrs.get("cn").get())
                    .surName((String)attrs.get("sn").get())
                    .username((String)attrs.get("uid").get())
                    .password(password)
                    .build();
        }
    }
}
