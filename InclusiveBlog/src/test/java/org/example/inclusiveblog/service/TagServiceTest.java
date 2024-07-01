package org.example.inclusiveblog.service;

import org.example.inclusiveblog.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TagServiceTest {

    @MockBean
    private ITagRepository tagRepository;

    @Autowired
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
