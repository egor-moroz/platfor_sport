package service;

import com.sport.mvc.dao.StudentDao;
import com.sport.mvc.models.Student;
import com.sport.mvc.services.impl.StudentServiceImpl;
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
public class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Spy
    List<Student> students = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        students = getStudentList();
    }

    @Test
    public void getAllStudentTest() {
        when(studentDao.getAll()).thenReturn(students);
        Assert.assertEquals(studentService.getAll(), students);
    }

    @Test
    public void getByIdTest() {
        Student Student = students.get(0);
        when(studentDao.getById(anyLong())).thenReturn(Student);
        Assert.assertEquals(studentService.getStudent(Student.getId()), Student);
    }

    @Test
    public void updateStudentTest() {
        doNothing().when(studentDao).update(any(Student.class));
        studentService.updateStudent(any(Student.class));
        verify(studentDao, atLeastOnce()).update(any(Student.class));
    }

    @Test
    public void addStudentTest() {
        doNothing().when(studentDao).add(any(Student.class));
        studentService.addStudent(any(Student.class));
        verify(studentDao, atLeastOnce()).add(any(Student.class));
    }

    @Test
    public void deleteStudentTest() {
        Student Student = students.get(0);
        when(studentDao.getById(anyLong())).thenReturn(Student);
        studentService.deleteListOfStudents(anyLong());
        verify(studentDao, atLeastOnce()).getById(anyLong());
    }

    public List<Student> getStudentList() {
        Student student1 = new Student();
        student1.setId(1L);

        Student student2 = new Student();
        student2.setId(2L);

        students.add(student1);
        students.add(student2);

        return students;
    }


}
