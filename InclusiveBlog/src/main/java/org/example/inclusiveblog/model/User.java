package org.example.inclusiveblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private Timestamp registrationDate;
    private String profilePicURL;
    private String profileBio;

//Creating 1:N relationship between User and Post
    @OneToMany (mappedBy = "user")
    private List<Post> posts;

//Creating 1:1 relationship between User and Address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

}
