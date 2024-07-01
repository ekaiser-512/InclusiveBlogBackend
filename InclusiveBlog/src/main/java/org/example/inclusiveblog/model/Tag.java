package org.example.inclusiveblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tagId;
    private String tagName;
    private String tagDescription;

//Creating M:N relationship between posts and tags
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new ArrayList<>();
}
