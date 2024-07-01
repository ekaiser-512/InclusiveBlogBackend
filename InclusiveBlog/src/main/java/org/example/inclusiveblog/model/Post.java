package org.example.inclusiveblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    private String postTitle;
    private String postContent;
    private Integer postLikes;
    private String postURL;
    private Timestamp creationDate;

//Creating 1:N relationship between Post and Comment
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

//Creating 1:N relationship with User
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//Creating M:N relationship with tag
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    List<Tag> tags = new ArrayList<>();
}

