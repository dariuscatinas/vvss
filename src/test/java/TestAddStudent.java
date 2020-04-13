import app.MainApplication;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.Service;
import validation.ValidationException;

import java.util.*;

@RunWith(Parameterized.class)

public class TestAddStudent {

    private Service service;
    private Student student;
    private boolean expectedToFail;
    private boolean emptyBefore;

//    public TestAddStudent(){
//        service = MainApplication.createService();
//    }
    public TestAddStudent(Student student, boolean expected, boolean emptyBefore){

        this.student = student;
        this.expectedToFail = expected;
        this.service = MainApplication.createTestService();
        this.emptyBefore = emptyBefore;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Student> students = getTestedStudents();
        Object[][] data = new Object[][] {
                { students.get(0) , false, true },
                {students.get(1), false, true},
                {students.get(2), true, true },
                {students.get(3), true, true},
                {students.get(4),  true, true},
                {students.get(5), true, true},
                {students.get(6), true, true}
        };

        List<Object[]> emptyBefore = new ArrayList<>();
        for(Object[] specificCase : data){
            Object[] toDeleteBefore = specificCase.clone();
            toDeleteBefore[2] = false;
            emptyBefore.add(toDeleteBefore);
        }
        emptyBefore.addAll(Arrays.asList(data));
        return emptyBefore;

    }

    public static List<Student> getTestedStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("1234", "Cati", 1, "cdie2270@yahoo.com"));
        students.add(new Student("1111", "Cati", 2, "acoe@gmail.com"));
        students.add(new Student("", "Cati", 1, "cdie22@yahoo.com"));
        students.add(new Student(null, "Cati", 1, "cdie2270@yahoo.com"));
        students.add(new Student("1111", "Geo", 2, "geo@yahoo.com"));
        students.add(new Student("12345", "Co9@", 2, "coco@yahho.com"));
        students.add(new Student("123456", "Cov", 2, "hhh"));
        return students;
    }

    @Before
    public void cleanBefore(){
        if (!emptyBefore){
            return;
        }
        clean();

    }

    private void clean(){
        List<String> ids = new ArrayList<>();
        for(Student student : service.getAllStudenti()){
            ids.add(student.getID());
        }
        ids.forEach(id -> service.deleteStudent(id));
    }

    @Test
    public void testAddSpecificStudent(){
        try{
            Student returned = service.addStudent(student);
            if(returned.getID().equals(student.getID())){
                System.out.println("Successful addition of " + student.toString() + " with emptyBefore = "  + emptyBefore );
                assert !expectedToFail;
                return;
            }
            assert expectedToFail;
        }
        catch (ValidationException vex){
            System.out.println("Validation exception for " +  student.toString() + " with emptyBefore = "  + emptyBefore + "\n" + vex.toString() );
            assert expectedToFail;
        }
        catch (NullPointerException nex){
            System.out.println("Null pointer exception for " +  student.toString() +" with emptyBefore = "  + emptyBefore  + "\n" + nex.toString() );
            assert expectedToFail;
        }
        catch (Exception ex){
            System.out.println("Unknown exception for " + student.toString() + " with emptyBefore = "  + emptyBefore );
            assert expectedToFail;
        }
    }

//    @After
//    public void cleanAfter(){
//        clean();
//    }




}
