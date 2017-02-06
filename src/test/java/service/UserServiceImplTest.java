package service;

import com.sport.mvc.dao.UserDao;
import com.sport.mvc.models.User;
import com.sport.mvc.services.impl.UserServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by EgorPC on 05.02.2017.
 */
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    List<User> users = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = getUserList();
    }

    @Test
    public void getAllUserTest() {
        when(userDao.getAll()).thenReturn(users);
        Assert.assertEquals(userService.getAll(), users);
    }

    @Test
    public void getByIdTest() {
        User user = users.get(0);
        when(userDao.getById(anyLong())).thenReturn(user);
        Assert.assertEquals(userService.getUserById(user.getId()), user);
    }

    @Test
    public void updateUserTest() {
        doNothing().when(userDao).update(any(User.class));
        userService.updateUser(any(User.class));
        verify(userDao, atLeastOnce()).update(any(User.class));
    }

    @Test
    public void addUserTest() {
       when(userDao.addUser(any(User.class))).thenReturn(true);
        userService.addUser(any(User.class));
        verify(userDao, atLeastOnce()).addUser(any(User.class));
    }

    @Test
    public void deleteUserTest() {
        User user = users.get(0);
        when(userDao.getById(anyLong())).thenReturn(user);
        userService.deleteUser(anyLong());
        verify(userDao, atLeastOnce()).getById(anyLong());
    }

    @Test
    public void userExistTest() {
        when(userDao.userExists(anyString())).thenReturn(true);
        userService.userExists(anyString());
        verify(userDao, atLeastOnce()).userExists(anyString());
    }


    public List<User> getUserList() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Alex");
        user1.setBirthday(new Date(1987L));
        user1.setCity("Kharkov");
        user1.setPhone("12-12-12");
        user1.setCountry("Ukraine");
        user1.setEmail("rrr@mail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Max");
        user2.setBirthday(new Date(1990L));
        user2.setCity("Kharkov");
        user2.setPhone("13-13-13");
        user2.setCountry("Ukraine");
        user2.setEmail("kkkk@mail.com");

        users.add(user1);
        users.add(user2);

        return users;
    }


}
