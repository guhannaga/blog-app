package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long commentId;
    private String commentTitle;
    private String commentBody;
    private String commentAuthor;
}
