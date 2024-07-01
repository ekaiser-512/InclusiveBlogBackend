package org.example.inclusiveblog.controller;

import org.example.inclusiveblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagController.class)
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

//CREATE

    //todo create tag

//READ

    //todo getTagByName

    //todo getAllTags

//UPDATE

    //todo update tag

//DELETE

    //todo delete tag
}
