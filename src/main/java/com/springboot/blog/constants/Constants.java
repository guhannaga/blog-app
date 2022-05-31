package com.springboot.blog.constants;

public class Constants {
    public final static String URL_PATH = "/api/v1/posts";
    public static final String DELETE_MESSAGE = "Post Entity deleted successfully";

    public static final String URL_RESOURCE_IDENTIFIER = "/{id}";
    public static final String PAGE_NO = "pageNo";

    public static final String PAGE_SIZE = "pageSize" ;
    public static final String DEFAULT_PAGE_NO = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String BIND_EXCEPTION = "%s not found with %s : %s";
    public static final String COMMENT_TITLE = "comment_title";
    public static final String POST_TITLE = "post_title";
    public static final String POST_DESCRIPTION = "post_description";
    public static final String POST_CONTENT = "post_content";
    public static final String POST = "Post";
    public static final String ID = "id";
    public static final String SORT_BY = "sortBy";
    public static final String DEFAULT_POST_SORT_BY = "postId";
    public static final String SORT_DIRECTION = "sortDir";
    public static final String DEFAULT_SORT_DIRECTION = "asc";
    public static final String COMMENT_BODY = "comment_body";
    public static final String COMMENT_AUTHOR = "comment_author";


    private Constants(){

    }
}
