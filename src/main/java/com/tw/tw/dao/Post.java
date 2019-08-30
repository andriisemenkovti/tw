package com.tw.tw.dao;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;

import static java.time.LocalDateTime.now;

@Data
@Table
@Entity
public class Post {
    private final Timestamp createdAt = Timestamp.valueOf(now());
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long postId;
    @Length(max = 140, message = "You may not post more than 140 characters.")
    private String content;
    private Long userId;
}
