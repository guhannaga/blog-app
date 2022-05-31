package com.springboot.blog.entity;


import com.springboot.blog.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(name = Constants.COMMENT_TITLE, nullable = false)
    private String commentTitle;
    @Column(name=Constants.COMMENT_BODY,nullable = false)
    private String commentBody;
    @Column(name=Constants.COMMENT_AUTHOR,nullable = false)
    private String commentAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
