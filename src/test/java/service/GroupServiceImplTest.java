package service;

import com.sport.mvc.dao.GroupDao;
import com.sport.mvc.models.Group;
import com.sport.mvc.services.impl.GroupServiceImpl;
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
import static org.mockito.Mockito.*;

/**
 * Created by EgorPC on 05.02.2017.
 */
public class GroupServiceImplTest {

    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Spy
    List<Group> groups = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        groups = getGroupList();
    }

    @Test
    public void getAllGroupTest() {
        when(groupDao.getAll()).thenReturn(groups);
        Assert.assertEquals(groupService.getAll(), groups);
    }

    @Test
    public void getByIdTest() {
        Group Group = groups.get(0);
        when(groupDao.getById(anyLong())).thenReturn(Group);
        Assert.assertEquals(groupService.getGroup(Group.getId()), Group);
    }

    @Test
    public void addGroupTest() {
        doNothing().when(groupDao).add(any(Group.class));
        groupService.addGroup(any(Group.class));
        verify(groupDao, atLeastOnce()).add(any(Group.class));
    }

    @Test
    public void deleteGroupTest() {
        Group Group = groups.get(0);
        when(groupDao.getById(anyLong())).thenReturn(Group);
        groupService.deleteListOfGroup(anyLong());
        verify(groupDao, atLeastOnce()).getById(anyLong());
    }

    public List<Group> getGroupList() {
        Group group1 = new Group();
        group1.setId(1L);

        Group group2 = new Group();
        group2.setId(2L);


        groups.add(group1);
        groups.add(group2);

        return groups;
    }


}
