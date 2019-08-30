package com.tw.tw.dao;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = User.USER_NAME_COLUMN)})
@Entity
public class User {
    public static final String USER_NAME_COLUMN = "userName";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    @OneToMany
    @JoinColumn
    private Collection<User> following;
    @NotNull
    @Column(name = USER_NAME_COLUMN)
    private String userName;
}
