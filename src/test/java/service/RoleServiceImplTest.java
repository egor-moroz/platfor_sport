package service;

import com.sport.mvc.dao.RoleDao;
import com.sport.mvc.models.Role;
import com.sport.mvc.services.impl.RoleServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by EgorPC on 05.02.2017.
 */
public class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Spy
    List<Role> roles = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roles = getRoleList();
    }

    @Test
    public void getAllRoleTest() {
        when(roleDao.getAll()).thenReturn(roles);
        Assert.assertEquals(roleService.getAllUsers(), roles);
    }

    @Test
    public void getByIdTest() {
        Role Role = roles.get(0);
        when(roleDao.getById(anyLong())).thenReturn(Role);
        Assert.assertEquals(roleService.getRoleById(Role.getId()), Role);
    }


    @Test
    public void addRoleTest() {
        when(roleDao.addRole(any(Role.class))).thenReturn(true);
        roleService.addRole(any(Role.class));
        verify(roleDao, atLeastOnce()).addRole(any(Role.class));
    }


    @Test
    public void RoleExistTest() {
        when(roleDao.roleExists(anyString())).thenReturn(true);
        roleService.roleExists(anyString());
        verify(roleDao, atLeastOnce()).roleExists(anyString());
    }


    public List<Role> getRoleList() {
        Role role1 = new Role();
        role1.setId(1L);


        Role role2 = new Role();
        role2.setId(2L);


        roles.add(role1);
        roles.add(role2);

        return roles;
    }


}
