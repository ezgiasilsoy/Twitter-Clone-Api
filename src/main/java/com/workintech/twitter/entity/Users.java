package com.workintech.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users", schema = "twitter_api")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(min = 8, max = 255)
    @JsonIgnore
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Tweets> tweets;

    @ToString.Exclude
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Comments> comments;

    @ToString.Exclude
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Likes> likes;

    @ToString.Exclude
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Retweets> retweets;
}
