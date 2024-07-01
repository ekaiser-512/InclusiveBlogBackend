package org.example.inclusiveblog.controller;

import org.example.inclusiveblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

//CREATE

    //todo create post

    //todo add tag to post

//READ

    //todo getAllPosts

    //todo getAllPostsByTitle

    //todo getAllTagsOnPost

//UPDATE

    //todo update post

//DELETE

    //todo delete post

    //todo delete tag from post

    //todo delete comment from post
}
