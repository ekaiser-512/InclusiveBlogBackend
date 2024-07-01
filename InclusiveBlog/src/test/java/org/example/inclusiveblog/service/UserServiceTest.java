package org.example.inclusiveblog.service;

import org.example.inclusiveblog.model.Address;
import org.example.inclusiveblog.model.Post;
import org.example.inclusiveblog.model.User;
import org.example.inclusiveblog.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private IUserRepository userRepository;

    @Autowired
    private UserService userService;

    private final User mockUser = createMockUser();

    private static User createMockUser() {
         User user = new User();
         user.setUserId(1);
         user.setUsername("Joe Doe");
         user.setEmail("jdoe@example.com");
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


//CREATE

    //todo add User

        //happy path
    @Test
    public void testAddUser() throws Exception {
         //arrange
        when(userRepository.save(eq(mockUser))).thenReturn(mockUser);

        //act
        User result = userService.addUser(mockUser);

        //assert
        assertEquals(mockUser, result);
        verify(userRepository, times(1)).save(mockUser);
    }

        //sad path
    @Test
    public void testAddUser_AlreadyExists() throws Exception { //sad path
        //arrange
        when(userRepository.existsByEmail(mockUser.getEmail())).thenReturn(true);
        when(userRepository.save(mockUser)).thenReturn(null);

        //act
        Exception exception = assertThrows(Exception.class, () -> {
            userService.addUser(mockUser);
        });

        verify(userRepository, times(0)).save(mockUser);
    }

        //sad path
    @Test
    public void testAddUserMissingInfo() { //sad path
        //todo
    }


//READ

    //todo getUserById

        //happy path
    @Test
    public void testGetUserById () throws Exception {
        //arrange
        //when(userRepository.findById(mockUser.getUserId())).thenReturn(Optional.of(mockUser)); //behavior interception - more specific/more narrow mock
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser)); //better set up in most cases because its broader

        //act
        User result = userService.getUserByID(mockUser.getUserId());

        //assert
        assertEquals(mockUser, result); //testing for correct behavior
        verify(userRepository, times(1)).findById(anyInt());//testing that correct calls were made.
    }
        //sad path user id does not exists
    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        when(userRepository.findById(mockUser.getUserId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            userService.getUserByID(mockUser.getUserId());
        });
    }

    //getAllUsers

        //happy path
    @Test
    public void testGetAllUsers() {
        //arrange
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User());
        mockUsers.add(new User());
        mockUsers.add(new User());

        when(userRepository.findAll()).thenReturn(mockUsers);

        //act
        List<User> allUsers = userService.getAllUsers();

        //assert
        assertEquals(3, allUsers.size());
        verify(userRepository, times(1)).findAll();
    }

        //sad path
    @Test
    public void testGetAllUsers_Failure() {
        List<User> mockList = Arrays.asList(mockUser, mockUser);

        when(userRepository.findAll()).thenReturn(mockList);
        List<User> resultList = userService.getAllUsers();

        assertNotEquals(5, resultList.size(), "The total number of users should not be 5");
        verify(userRepository).findAll();
    }

    //todo getUserByEmail

        //happy path
    @Test
    public void testGetUserByEmail() throws Exception {
        //arrange
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        //act
        User result = userService.getUserByEmail(mockUser.getEmail());

        //assert
        assertEquals(mockUser, result);
        verify(userRepository, times(1)).findByEmail(anyString());
    }
        //sad path email not found

    //todo getAllPostsByUser
        //happy path
    @Test
    public void testGetAllPostsByUserName() throws Exception {
        //arrange
        List<Post> posts = Arrays.asList(new Post(), new Post());
        mockUser.setPosts(posts);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));

        //act
        List<Post> retrievedPosts = userService.getAllPostsByUserName(mockUser.getUsername());

        //assert
        assertNotNull(retrievedPosts);
        assertEquals(2,retrievedPosts.size());
        verify(userRepository, times(1)).findByUsername(mockUser.getUsername());
    }


        //sad path user has no posts yet

//UPDATE

    //todo updateUser
        //happy path
    @Test
    public void testUpdateUser() throws Exception {
        //arrange
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        //act
        User updatedUser = userService.updateUser(1, mockUser);

        //assert
        assertNotNull(updatedUser);
        assertEquals(mockUser, updatedUser);
        verify(userRepository, times(1)).findById(1);
    }

        //sad path


//DELETE

    //todo deleteUser
        //happy path
    @Test
    public void testDeleteUser() {

        //arrange/act
        userService.deleteUser(mockUser.getUserId());

        //assert
        verify(userRepository, times(1)).deleteById(mockUser.getUserId());
    }

        //sad path user id doesn't exist

}
