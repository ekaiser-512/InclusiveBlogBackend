package org.example.inclusiveblog.service;

import org.example.inclusiveblog.repository.ICommentRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CommentServiceTest {

    @MockBean
    private ICommentRepository commentRepository;

    @Autowired
    CommentService commentService;

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
