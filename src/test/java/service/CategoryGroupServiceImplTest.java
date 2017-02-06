package service;

import com.sport.mvc.dao.CategoryGroupDao;

import com.sport.mvc.models.CategoryGroup;
import com.sport.mvc.services.impl.CategoryGroupServiceImpl;

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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by EgorPC on 05.02.2017.
 */
public class CategoryGroupServiceImplTest {

    @Mock
    private CategoryGroupDao categoryGroupDao;

    @InjectMocks
    private CategoryGroupServiceImpl categoryGroupService;

    @Spy
    List<CategoryGroup> categoryGroups = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryGroups = getCategoryGroups();
    }

    @Test
    public void getAllCategoryGroupTest() {
        when(categoryGroupDao.getAll()).thenReturn(categoryGroups);
        Assert.assertEquals(categoryGroupService.getAll(), categoryGroups);
    }

    @Test
    public void getByIdTest() {
        CategoryGroup categoryGroup = categoryGroups.get(0);
        when(categoryGroupDao.getById(anyLong())).thenReturn(categoryGroup);
        Assert.assertEquals(categoryGroupService.getCategoryGroup(categoryGroup.getId()), categoryGroup);
    }

    @Test
    public void updateCategoryGroupTest() {
        doNothing().when(categoryGroupDao).update(any(CategoryGroup.class));
        categoryGroupService.updateCategoryGroup(any(CategoryGroup.class));
        verify(categoryGroupDao, atLeastOnce()).update(any(CategoryGroup.class));
    }

    @Test
    public void addCategoryGroupTest() {
        doNothing().when(categoryGroupDao).add(any(CategoryGroup.class));
        categoryGroupService.addCategoryGroup(any(CategoryGroup.class));
        verify(categoryGroupDao, atLeastOnce()).add(any(CategoryGroup.class));
    }

    @Test
    public void deleteCategoryGroupTest() {
        CategoryGroup CategoryGroup = categoryGroups.get(0);
        when(categoryGroupDao.getById(anyLong())).thenReturn(CategoryGroup);
        categoryGroupService.deleteListOfCategoryGroup(anyLong());
        verify(categoryGroupDao, atLeastOnce()).getById(anyLong());
    }




    public List<CategoryGroup> getCategoryGroups () {
        CategoryGroup categoryGroup1 = new CategoryGroup();
        categoryGroup1.setId(1L);
        CategoryGroup categoryGroup2 = new CategoryGroup();
        categoryGroup2.setId(2L);
        categoryGroups.add(categoryGroup1);
        categoryGroups.add(categoryGroup2);

        return categoryGroups;
    }



}
