package com.workintech.twitter.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments", schema = "twitter_api")
@EqualsAndHashCode(of="id")
@ToString
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String text;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    @ToString.Exclude
    @JsonIgnore

    private Tweets tweets;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore

    private Users users;
}
