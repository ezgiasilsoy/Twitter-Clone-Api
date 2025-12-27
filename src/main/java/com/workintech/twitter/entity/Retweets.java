package com.workintech.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "retweets", schema = "twitter_api")
@EqualsAndHashCode(of="id")
@ToString

public class Retweets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore

    private Users users;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    @ToString.Exclude
    @JsonIgnore

    private Tweets tweets;
}
