package com.springboot.blog.payload;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class PostDto {
 private Long postId;
 @NotEmpty
 @Size(min=2,message="post title should have at least 2 characters")
 private String title;
 @NotEmpty
 private String description;
 @NotEmpty
 private String content;
 private Set<CommentDto> comments;
}
