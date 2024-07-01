package org.example.inclusiveblog.controller;

import org.example.inclusiveblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

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
