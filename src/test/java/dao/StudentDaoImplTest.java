package dao;

import com.sport.mvc.dao.StudentDao;
import com.sport.mvc.models.Student;
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
public class StudentDaoImplTest  extends EntityDaoImplTest{

    @Autowired
    @Qualifier("studentDatabaseDao")
    StudentDao studentDao;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Student.xml"));
        return dataSet;
    }

    @Test
    public void findById(){
        Assert.assertNotNull(studentDao.getById(1L));
        Assert.assertNull(studentDao.getById(3L));
    }

    @Test
    public void saveStudent(){
        studentDao.add(getSimpleStudent());
        Assert.assertEquals(studentDao.getAll().size(), 3);
    }

    @Test
    public void findAllStudent(){
        Assert.assertEquals(studentDao.getAll().size(), 2);
    }

    @Test
    public void deleteStudent(){
        Student student = getSimpleStudent();
        studentDao.add(student);
        Assert.assertEquals(studentDao.getAll().size(), 3);
        studentDao.remove(student);
        Assert.assertEquals(studentDao.getAll().size(), 2);
    }

    public Student getSimpleStudent(){
        Student Student = new Student();
        return Student;
    }
}

