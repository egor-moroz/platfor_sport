package dao;

import com.sport.mvc.dao.PriceDao;
import com.sport.mvc.models.Price;
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
public class PriceDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("priceDatabaseDao")
    PriceDao priceDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Price.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(priceDao.getById(1L));
        Assert.assertNull(priceDao.getById(3L));
    }

    @Test
    public void savePrice(){
        priceDao.add(getSimplePrice());
        Assert.assertEquals(priceDao.getAll().size(), 3);
    }

    @Test
    public void findAllPrice(){
        Assert.assertEquals(priceDao.getAll().size(), 2);
    }


    @Test
    public void deletePrice(){
        Price price = getSimplePrice();
        priceDao.add(price);
        Assert.assertEquals(priceDao.getAll().size(), 3);
        priceDao.remove(price);
        Assert.assertEquals(priceDao.getAll().size(), 2);
    }

    public Price getSimplePrice(){
        Price Price = new Price();
        return Price;
    }
}

