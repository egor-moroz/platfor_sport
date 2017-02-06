package dao;

import com.sport.mvc.dao.CategoryGroupDao;
import com.sport.mvc.models.CategoryGroup;
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
public class CategoryGroupDaoImplTest extends EntityDaoImplTest {

    @Autowired
    @Qualifier("categoryGroupDatabaseDao")
    CategoryGroupDao categoryGroupDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("CategoryGroup.xml"));
        return dataSet;
    }


    @Test
    public void saveCategoryGroup() {
        categoryGroupDao.add(getSimpleCategoryGroup());
        Assert.assertEquals(categoryGroupDao.getAll().size(), 3);
    }

    @Test
    public void findAllCategoryGroup() {
        Assert.assertEquals(categoryGroupDao.getAll().size(), 2);
    }


    @Test
    public void deleteCategoryGroup() {
        CategoryGroup categoryGroup = getSimpleCategoryGroup();
        categoryGroupDao.add(categoryGroup);
        Assert.assertEquals(categoryGroupDao.getAll().size(), 3);
        categoryGroupDao.remove(categoryGroup);
        Assert.assertEquals(categoryGroupDao.getAll().size(), 2);
    }

    public CategoryGroup getSimpleCategoryGroup() {
        CategoryGroup categoryGroup = new CategoryGroup();

        return categoryGroup;
    }
}

