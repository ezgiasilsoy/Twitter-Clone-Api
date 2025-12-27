package com.workintech.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweets",schema = "twitter_api")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString

public class Tweets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String text;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore

    private Users users;

    @OneToMany(mappedBy = "tweets")
    @ToString.Exclude
    @JsonIgnore

    private List<Comments> comments;

    @ToString.Exclude
    @OneToMany(mappedBy = "tweets")
    @JsonIgnore

    private List<Likes> likes;

    @ToString.Exclude
    @OneToMany(mappedBy = "tweets")
    @JsonIgnore

    private List<Retweets> retweets;



}
