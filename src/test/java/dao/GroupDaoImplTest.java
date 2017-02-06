package dao;

import com.sport.mvc.dao.GroupDao;
import com.sport.mvc.models.Group;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by EgorPC on 06.02.2017.
 */
public class GroupDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("groupDatabaseDao")
    GroupDao groupDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Group.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(groupDao.getById(1L));
        Assert.assertNull(groupDao.getById(3L));
    }

    @Test
    public void saveGroup(){
        groupDao.add(getSimpleGroup());
        Assert.assertEquals(groupDao.getAll().size(), 3);
    }

    @Test
    public void findAllGroup(){
        Assert.assertEquals(groupDao.getAll().size(), 2);
    }

    @Test
    public void deleteGroup(){
        Group group = getSimpleGroup();
        groupDao.add(group);
        Assert.assertEquals(groupDao.getAll().size(), 3);
        groupDao.remove(group);
        Assert.assertEquals(groupDao.getAll().size(), 2);
    }

    public Group getSimpleGroup(){
        Group Group = new Group();
        return Group;
    }
}

