package dao;

import com.sport.mvc.dao.RoleDao;
import com.sport.mvc.models.Role;
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
public class RoleDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("roleDatabaseDao")
    RoleDao roleDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Role.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(roleDao.getById(1L));
        Assert.assertNull(roleDao.getById(3L));
    }

    @Test
    public void saveRole(){
        roleDao.addRole(getSimpleRole());
        Assert.assertEquals(roleDao.getAll().size(), 3);
    }

    @Test
    public void findAllRole(){
        Assert.assertEquals(roleDao.getAll().size(), 2);
    }

    @Test
    public void deleteRole(){
        Role role = getSimpleRole();
        roleDao.addRole(role);
        Assert.assertEquals(roleDao.getAll().size(), 3);
        roleDao.remove(role);
        Assert.assertEquals(roleDao.getAll().size(), 2);
    }

    public Role getSimpleRole(){
        Role role = new Role();
        return role;
    }
}

