package service;

import com.sport.mvc.dao.EmailSenderDao;
import com.sport.mvc.models.EmailSender;
import com.sport.mvc.services.impl.EmailSenderServiceImpl;
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
public class EmailSenderServiceImplTest {

    @Mock
    private EmailSenderDao emailSenderDao;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    @Spy
    List<EmailSender> emailSenders = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        emailSenders = getEmailSenderList();
    }

    @Test
    public void getAllEmailSenderTest() {
        when(emailSenderDao.getAll()).thenReturn(emailSenders);
        Assert.assertEquals(emailSenderService.getAll(), emailSenders);
    }

    @Test
    public void getByIdTest() {
        EmailSender EmailSender = emailSenders.get(0);
        when(emailSenderDao.getById(anyLong())).thenReturn(EmailSender);
        Assert.assertEquals(emailSenderService.getEmailSender(EmailSender.getId()), EmailSender);
    }

    @Test
    public void updateEmailSenderTest() {
        doNothing().when(emailSenderDao).update(any(EmailSender.class));
        emailSenderService.updateEmailSender(any(EmailSender.class));
        verify(emailSenderDao, atLeastOnce()).update(any(EmailSender.class));
    }

    @Test
    public void addEmailSenderTest() {
        doNothing().when(emailSenderDao).add(any(EmailSender.class));
        emailSenderService.addEmailSender(any(EmailSender.class));
        verify(emailSenderDao, atLeastOnce()).add(any(EmailSender.class));
    }

    @Test
    public void deleteEmailSenderTest() {
        EmailSender emailSender = emailSenders.get(0);
        when(emailSenderDao.getById(anyLong())).thenReturn(emailSender);
        emailSenderService.deleteListOfEmailSenders(anyLong());
        verify(emailSenderDao, atLeastOnce()).getById(anyLong());
    }


    public List<EmailSender> getEmailSenderList() {
        EmailSender emailSender1 = new EmailSender();
        emailSender1.setId(1L);

        EmailSender emailSender2 = new EmailSender();
        emailSender2.setId(2L);

        emailSenders.add(emailSender1);
        emailSenders.add(emailSender2);

        return emailSenders;
    }


}
