package com.springboot.blog.entity;


import com.springboot.blog.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"post_title"})})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = Constants.POST_TITLE, nullable = false)
    private String title;
    @Column(name = Constants.POST_DESCRIPTION, nullable = false)
    private String description;
    @Column(name = Constants.POST_CONTENT)
    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
