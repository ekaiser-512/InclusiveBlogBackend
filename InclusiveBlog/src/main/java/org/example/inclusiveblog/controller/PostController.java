package org.example.inclusiveblog.controller;

import org.example.inclusiveblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PostController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PostService postService;

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
