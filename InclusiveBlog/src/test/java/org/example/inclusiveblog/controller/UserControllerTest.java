package org.example.inclusiveblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.inclusiveblog.model.Address;
import org.example.inclusiveblog.model.Post;
import org.example.inclusiveblog.model.User;
import org.example.inclusiveblog.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(UserController.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final User mockUser = createMockUser1();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static String email = "jdoe@example.com";

    private static User createMockUser1() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("Joe Doe");
        user.setEmail(email);
        user.setPassword("Doe123!");
        user.setRegistrationDate(Timestamp.from(Instant.now()));
        user.setProfilePicURL("www.joesprofilepic.com");
        user.setProfileBio("Joe Doe profile bio info");

        Address address = new Address();
        address.setAddressId(1);
        address.setStreet("123 Main St");
        address.setCity("New York City");
        address.setState("NY");
        address.setZipCode("12345");
        address.setCountry("USA");

        return user;
    }

    private String userToJson(User user) {
        // Configure the ObjectMapper to use the same date/time format as the actual response
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return objectMapper.writeValueAsString(user); //try to take object and write it as a string into Json
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); //took a checked exception and turned it into an unchecked exception
        }
    }

    private void compareJsonOuput(ResultActions resultActions) throws Exception {
        resultActions
                .andExpect(jsonPath("$.userId", is(mockUser.getUserId())))
                .andExpect(jsonPath("$.username", is(mockUser.getUsername())))
                .andExpect(jsonPath("$.email", is(mockUser.getEmail())))
                .andExpect(jsonPath("$.password", is(mockUser.getPassword())))
                .andExpect(jsonPath("$.profilePicURL", is(mockUser.getProfilePicURL())))
                .andExpect(jsonPath("$.profileBio", is(mockUser.getProfileBio())));
               //.andExpect(jsonPath("$.registrationDate", is(mockUser.getRegistrationDate()))); struggling with data serialization. (taking object from programing language and turning it into an output string.)
    }

//CREATE

    //todo add User
        //happy path
    @Test
    void testAddUser() throws Exception {
        //setup mock service behavior
        when(userService.addUser(any(User.class))).thenReturn(mockUser);

        //Perform Request
        ResultActions resultActions = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToJson(mockUser)));
        resultActions.andExpect(status().isCreated()); //status 201
        compareJsonOuput(resultActions);

        //Verify
        verify(userService, times(1)).addUser(any(User.class));
    }

        //todo sad path
   @Test
   void testAddUser_UserExists() throws Exception {
        when(userService.addUser(any())).thenThrow(new Exception("user with email " + mockUser.getEmail() + " already exists"));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToJson(mockUser)))
                .andExpect(status().isBadRequest());

        verify(userService).addUser(any());
   }


//READ

    //getUserById
        //happy path
    @Test
    public void testGetUserById() throws Exception {
        //setup mock service behavior
        int userId = 1;
        when(userService.getUserByID(userId)).thenReturn(mockUser);

        //perform request
        ResultActions resultActions = mockMvc.perform(get("/users/{id}", userId));
        resultActions.andExpect(status().isOk()); //status 200
        compareJsonOuput(resultActions);

        //verify
        verify(userService, times(1)).getUserByID(userId);
    }

        //sad path - id not found
    @Test
    public void testGetUserById_IdNotFound() throws Exception {
        when(userService.getUserByID(mockUser.getUserId())).thenThrow(new Exception("User with id " + mockUser.getUserId() + " not found"));

        mockMvc.perform(get("/users/{id}", mockUser.getUserId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService).getUserByID(mockUser.getUserId());
    }

    //getAllUsers
        //happy path
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> mockUsers = Arrays.asList(mockUser, mockUser);
        
        when(userService.getAllUsers()).thenReturn(mockUsers);

        ObjectMapper objectMapper = new ObjectMapper();
        String mockUsersJson = objectMapper.writeValueAsString(mockUsers);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mockUsersJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(mockUsers.size()));

        verify(userService, times(1)).getAllUsers();
    }

        //sad path - no users found
    @Test
    public void testGetAllUsers_NoUsersFound() throws Exception {
        List<User> users =  Arrays.asList();
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users", users)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(users.size()));

        verify(userService).getAllUsers();
    }

    //getUserByEmail
        //happy path
    @Test
    public void testGetUserByEmail() throws Exception {

        when(userService.getUserByEmail(email)).thenReturn(mockUser);

        ResultActions resultActions = mockMvc.perform(get("/users/email/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToJson(mockUser)));
                resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.username", is("Joe Doe")));

        verify(userService).getUserByEmail(email);
    }

        //sad path email does not exist
    @Test
    public void testGetUserByEmail_UserNotFound() throws Exception {

        String badEmail = "notfound@gmail.com";
        when(userService.getUserByEmail(badEmail)).thenThrow(new Exception("User with email " + email + " does not exist"));

        ResultActions resultActions = mockMvc.perform(get("/users/email/{email}", badEmail)
                        .contentType(MediaType.APPLICATION_JSON));
                resultActions.andExpect(status().isNotFound());

        verify(userService).getUserByEmail(badEmail);
    }


    //getAllPostsByUser
        //happy path
    @Test void testGetAllPostsByUserName() throws Exception {

        List<Post> posts = Arrays.asList(new Post(), new Post());
        mockUser.setPosts(posts);
        when(userService.getAllPostsByUserName(mockUser.getUsername())).thenReturn(posts);

        mockMvc.perform(get("/users/{username}/posts", mockUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()));

        verify(userService).getAllPostsByUserName(mockUser.getUsername());
    }

        //sad path user has no posts
    @Test
    void testGetAllPostsByUserName_NoPosts() throws Exception {

        List<Post> posts = Arrays.asList();
        mockUser.setPosts(posts);
        when(userService.getAllPostsByUserName(mockUser.getUsername())).thenReturn(posts);

        mockMvc.perform(get("/users/{username}/posts", mockUser.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()));

        verify(userService).getAllPostsByUserName(mockUser.getUsername());
    }

        //sad path user not found
    @Test
    public void testGetAllPostsByUserName_UsernameNotFound() throws Exception {

        String badUsername = "notRealName";
        when(userService.getAllPostsByUserName(any())).thenThrow(new Exception("User with username " + badUsername + " not found."));

        mockMvc.perform(get("/users/{username}/posts", badUsername)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService).getAllPostsByUserName(badUsername);
    }

//UPDATE

    //updateUser
        //happy path
    @Test
    public void testUpdateUser() throws Exception {
        int userId = 1;
        User updatedUser = new User();
        updatedUser.setUserId(1);
        updatedUser.setUsername("Joey_Doe");
        updatedUser.setEmail("joey_doeghy@gmail.com");

        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(updatedUser);

        ResultActions resultActions = mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToJson(mockUser)));
                resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Joey_Doe")))
                .andExpect(jsonPath("$.email", is("joey_doeghy@gmail.com")));

        verify(userService).updateUser(eq(userId), any(User.class));
    }

        //sad path user id does not exist
    @Test
    public void testUpdateUser_IdDoesNotExist() throws Exception {
        when(userService.getUserByID(mockUser.getUserId())).thenThrow(new Exception("User with id " + mockUser.getUserId() + " not found"));

        mockMvc.perform(get("/users/{id}", mockUser.getUserId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService).getUserByID(mockUser.getUserId());
    }

        //path user is null
    @Test
    public void testUpdateUser_UserNull() throws Exception {
        when(userService.updateUser(anyInt(), any())).thenThrow(new Exception("User cannot be null"));

        mockMvc.perform(put("/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userToJson(mockUser)))
                .andExpect(status().isNotFound());

        verify(userService).updateUser(eq(mockUser.getUserId()), any(User.class));
    }

//DELETE

    //todo deleteUser
        //happy path
    @Test
    public void testDeleteUser() throws Exception {
        //Setup
        int userId = 1;

        doNothing().when(userService).deleteUser(anyInt());

        //Perform
        ResultActions resultActions = mockMvc.perform(delete("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userToJson(mockUser)));
                resultActions.andExpect(status().isOk());

        //Verify
        verify(userService, times(1)).deleteUser(userId);
    }

    //sad path - userId does not exist -- not needed because current happy path accounts for both.

}
