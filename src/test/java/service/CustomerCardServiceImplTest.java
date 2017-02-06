package service;

import com.sport.mvc.dao.CustomerCardDao;
import com.sport.mvc.models.CustomerCard;
import com.sport.mvc.services.impl.CustomerCardServiceImpl;
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
public class CustomerCardServiceImplTest {

    @Mock
    private CustomerCardDao customerCardDao;

    @InjectMocks
    private CustomerCardServiceImpl customerCardService;

    @Spy
    List<CustomerCard> customerCards = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerCards = getCustomerCardList();
    }

    @Test
    public void getAllCustomerCardTest() {
        when(customerCardDao.getAll()).thenReturn(customerCards);
        Assert.assertEquals(customerCardService.getAll(), customerCards);
    }

    @Test
    public void getByIdTest() {
        CustomerCard customerCard = customerCards.get(0);
        when(customerCardDao.getById(anyLong())).thenReturn(customerCard);
        Assert.assertEquals(customerCardService.getCustomerCardt(customerCard.getId()), customerCard);
    }

    @Test
    public void updateCustomerCardTest() {
        doNothing().when(customerCardDao).update(any(CustomerCard.class));
        customerCardService.updateCustomerCard(any(CustomerCard.class));
        verify(customerCardDao, atLeastOnce()).update(any(CustomerCard.class));
    }

    @Test
    public void addCustomerCardTest() {
        doNothing().when(customerCardDao).add(any(CustomerCard.class));
        customerCardService.addCustomerCard(any(CustomerCard.class));
        verify(customerCardDao, atLeastOnce()).add(any(CustomerCard.class));
    }

    @Test
    public void deleteCustomerCardTest() {
        CustomerCard CustomerCard = customerCards.get(0);
        when(customerCardDao.getById(anyLong())).thenReturn(CustomerCard);
        customerCardService.deleteListOfCustomerCards(anyLong());
        verify(customerCardDao, atLeastOnce()).getById(anyLong());
    }

    public List<CustomerCard> getCustomerCardList() {
        CustomerCard customerCard1 = new CustomerCard();
        customerCard1.setId(1L);


        CustomerCard customerCard2 = new CustomerCard();
        customerCard2.setId(2L);


        customerCards.add(customerCard1);
        customerCards.add(customerCard2);

        return customerCards;
    }



}
