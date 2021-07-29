package com.huseyin.youcontribute.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Integer id;

    private Long githubIssueId;

    private Integer githubIssueNumber;

    private String title;

    @Column(columnDefinition = "text")
    private String body;

    private String url;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Repository repository;

    @OneToOne(mappedBy = "issue", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private IssueChallenge issueChallenge;

}
