package dao;

import com.sport.mvc.dao.UserDao;
import com.sport.mvc.models.User;
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
public class UserDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("userDatabaseDao")
    UserDao userDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(userDao.getById(1L));
        Assert.assertNull(userDao.getById(3L));
    }

    @Test
    public void saveUser(){
        userDao.addUser(getSimpleUser());
        Assert.assertEquals(userDao.getAll().size(), 3);
    }

    @Test
    public void findAllUser(){
        Assert.assertEquals(userDao.getAll().size(), 2);
    }

    @Test
    public void findUserByName(){
        Assert.assertNotNull(userDao.getUserByUsername("SAMY"));
        Assert.assertNull(userDao.getUserByUsername("KET"));
    }

    @Test
    public void deleteUser(){
        User user = getSimpleUser();
        userDao.addUser(user);
        Assert.assertEquals(userDao.getAll().size(), 3);
        userDao.remove(user);
        Assert.assertEquals(userDao.getAll().size(), 2);
    }

    public User getSimpleUser(){
        User user = new User();
        user.setName("Tom");
        return user;
    }
}
