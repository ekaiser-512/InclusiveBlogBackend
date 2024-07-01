package org.example.inclusiveblog.service;

import org.example.inclusiveblog.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    ICommentRepository commentRepository;

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
