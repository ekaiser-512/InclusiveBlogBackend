package org.example.inclusiveblog.service;

import org.example.inclusiveblog.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PostServiceTest {

    @MockBean
    private IPostRepository postRepository;

    @Autowired
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
