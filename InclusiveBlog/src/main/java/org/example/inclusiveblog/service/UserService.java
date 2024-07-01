package org.example.inclusiveblog.service;

import org.example.inclusiveblog.model.Comment;
import org.example.inclusiveblog.model.Post;
import org.example.inclusiveblog.model.User;
import org.example.inclusiveblog.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

//CREATE

    //add User
    public User addUser(User user) throws Exception {
        boolean userExists = userRepository.existsByEmail(user.getEmail());

        if(userExists) {
            throw new Exception("user with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

//READ

    //getUserById
    public User getUserByID(Integer id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
    }

    //getAllUsers
    public List<User> getAllUsers() {
    return userRepository.findAll();
    }

    //getUserByEmail
    public User getUserByEmail(String email) throws Exception {
        return (User) userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with email " + email + " does not exist"));
    }


    //getAllPostsByUserName
    public List<Post> getAllPostsByUserName(String username) throws Exception {
        User user = (User) userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User with username " + username + " not found."));
        return new ArrayList<>(user.getPosts());
    }
    //todo

//UPDATE

    // updateUser
    public User updateUser(int id, User user) throws Exception {
        User oldUser = userRepository.findById(id).orElse(null);

        if (oldUser == null) {
            throw new Exception ("User with id " + id + " not found");

        }
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRegistrationDate(user.getRegistrationDate());
        oldUser.setProfilePicURL(user.getProfilePicURL());
        oldUser.setProfileBio(user.getProfileBio());

        return userRepository.save(oldUser);
    }

//DELETE

    // deleteUser
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
}
