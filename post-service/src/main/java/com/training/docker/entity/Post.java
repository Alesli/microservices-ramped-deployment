package com.training.docker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "posts")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long authorId;

    private String text;

    @Column(name = "posted_at", columnDefinition = "DATE")
    private LocalDate postedAt;

    @PrePersist
    public void prePersist() {
        postedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        postedAt = LocalDate.now();
    }
}
