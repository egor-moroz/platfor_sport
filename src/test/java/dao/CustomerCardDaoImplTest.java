package dao;

import com.sport.mvc.dao.CustomerCardDao;
import com.sport.mvc.models.CustomerCard;
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
public class CustomerCardDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("customerCardDatabaseDao")
    CustomerCardDao customerCardDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("CustomerCard.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(customerCardDao.getById(1L));
        Assert.assertNull(customerCardDao.getById(3L));
    }

    @Test
    public void saveCustomerCard(){
        customerCardDao.add(getSimpleCustomerCard());
        Assert.assertEquals(customerCardDao.getAll().size(), 3);
    }

    @Test
    public void findAllCustomerCard(){
        Assert.assertEquals(customerCardDao.getAll().size(), 2);
    }



    @Test
    public void deleteCustomerCard(){
        CustomerCard customerCard = getSimpleCustomerCard();
        customerCardDao.add(customerCard);
        Assert.assertEquals(customerCardDao.getAll().size(), 3);
        customerCardDao.remove(customerCard);
        Assert.assertEquals(customerCardDao.getAll().size(), 2);
    }

    public CustomerCard getSimpleCustomerCard(){
        CustomerCard customerCard = new CustomerCard();
        return customerCard;
    }
}

