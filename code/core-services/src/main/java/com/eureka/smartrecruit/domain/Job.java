package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.database.BaseDomainObject;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job extends BaseDomainObject {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToOne(optional = false)
    private Category category;

    @Column(nullable = false)
    private Double remuneration;

    @Column(nullable = false)
    private Integer duration;

    @Column
    private String location;

    @Column
    private Integer numberPositions;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime deadline;

    @Column
    private boolean fixedPrice;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToMany
    @JoinTable(name = "JobSkills")
    private List<Skill> skills;

    @OneToMany(mappedBy = "job")
    private Set<Bid> bids;

    @OneToMany(mappedBy = "job")
    private Set<Application> applications;

    @OneToMany(mappedBy = "job")
    private Set<Feedback> feedback;

    @OneToMany(mappedBy = "job")
    private Set<Dispute> disputes;

    public Set<User> getHired() {
        return applications.stream().filter(i -> i.isAccepted()).map(t -> t.getUser()).collect(Collectors.toSet());
    }
}
