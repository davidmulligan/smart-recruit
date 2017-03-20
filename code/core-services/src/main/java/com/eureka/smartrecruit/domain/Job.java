package com.eureka.smartrecruit.domain;

import com.eureka.smartrecruit.domain.enumeration.BidStatus;
import com.eureka.smartrecruit.domain.enumeration.JobStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.lambda.Seq;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.CascadeType;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job extends DomainObject {

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
    private boolean fixed;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToMany
    @JoinTable(name = "JobSkills")
    private List<Skill> skills;

    @OneToMany(mappedBy = "job")
    private Set<Feedback> feedback;

    @OneToMany(mappedBy = "job")
    private Set<Bid> bids;

    @OneToMany(mappedBy = "job")
    private Set<Dispute> disputes;

    @OneToOne(mappedBy = "job", cascade = CascadeType.ALL)
    private Workroom workroom;

    public Set<User> getFreelancers() {
        return Seq.seq(bids).filter(i -> i.getStatus().equals(BidStatus.ACCEPTED)).map(Bid::getCreatedBy).toSet();
    }
}
