package dao;

import com.sport.mvc.dao.EmailSenderDao;
import com.sport.mvc.models.EmailSender;
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
public class EmailSenderDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("emailSenderDatabaseDao")
    EmailSenderDao emailSenderDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("EmailSender.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(emailSenderDao.getById(1L));
        Assert.assertNull(emailSenderDao.getById(3L));
    }

    @Test
    public void saveEmailSender(){
        emailSenderDao.add(getSimpleEmailSender());
        Assert.assertEquals(emailSenderDao.getAll().size(), 3);
    }

    @Test
    public void findAllEmailSender(){
        Assert.assertEquals(emailSenderDao.getAll().size(), 2);
    }

    @Test
    public void deleteEmailSender(){
        EmailSender emailSender = getSimpleEmailSender();
        emailSenderDao.add(emailSender);
        Assert.assertEquals(emailSenderDao.getAll().size(), 3);
        emailSenderDao.remove(emailSender);
        Assert.assertEquals(emailSenderDao.getAll().size(), 2);
    }

    public EmailSender getSimpleEmailSender(){
        EmailSender EmailSender = new EmailSender();
        return EmailSender;
    }
}

