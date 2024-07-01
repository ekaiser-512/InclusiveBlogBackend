package org.example.inclusiveblog.controller;

import org.example.inclusiveblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

//CREATE

    //todo create comment on post

//READ

    //todo getAllCommentsByUser

    //todo getAllCommentsOnPost

    //todo getAllLikesOnComment

//UPDATE

    //todo updateComment

//DELETE

    //todo delete commentById
}
