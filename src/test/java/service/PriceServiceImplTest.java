package service;

import com.sport.mvc.dao.PriceDao;
import com.sport.mvc.models.Price;
import com.sport.mvc.services.impl.PriceServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by EgorPC on 05.02.2017.
 */
public class PriceServiceImplTest {

    @Mock
    private PriceDao priceDao;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Spy
    List<Price> prices = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        prices = getPriceList();
    }

    @Test
    public void getAllpriceTest() {
        when(priceDao.getAll()).thenReturn(prices);
        Assert.assertEquals(priceService.getAll(), prices);
    }

    @Test
    public void getByIdTest() {
        Price price = prices.get(0);
        when(priceDao.getById(anyLong())).thenReturn(price);
        Assert.assertEquals(priceService.getPricece(price.getId()), price);
    }

    @Test
    public void updatepriceTest() {
        doNothing().when(priceDao).update(any(Price.class));
        priceService.updatePrice(any(Price.class));
        verify(priceDao, atLeastOnce()).update(any(Price.class));
    }

    @Test
    public void addPriceTest() {
        doNothing().when(priceDao).add(any(Price.class));
        priceService.addPrice(any(Price.class));
        verify(priceDao, atLeastOnce()).add(any(Price.class));
    }

    @Test
    public void deletePriceTest() {
        Price price = prices.get(0);
        when(priceDao.getById(anyLong())).thenReturn(price);
        priceService.deleteListOfPrice(anyLong());
        verify(priceDao, atLeastOnce()).getById(anyLong());
    }


    public List<Price> getPriceList() {
        Price price1 = new Price();
        price1.setId(1L);

        Price price2 = new Price();
        price2.setId(2L);

        prices.add(price1);
        prices.add(price2);

        return prices;
    }


}
