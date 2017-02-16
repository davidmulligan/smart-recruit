package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.domain.enumeration.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.lambda.Seq;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends DomainObject implements UserDetails {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String companyName;

    @Column
    private String securityQuestion;

    @Column
    private String securityAnswer;

    @Column(unique = true)
    private String activationCode;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime activationExpiry;

    @Column
    private String profile;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole")
    private Set<Role> roles;

    @OneToMany(mappedBy = "createdBy")
    @OrderBy("name")
    private Set<Job> jobs;

    @OneToMany(mappedBy = "user")
    @OrderBy("createdOn")
    private Set<Feedback> feedback;

    @OneToOne
    private Membership membership;

    @ManyToMany
    @JoinTable(name = "UserSkills")
    private List<Skill> skills;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Seq.seq(roles).map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Transient
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
