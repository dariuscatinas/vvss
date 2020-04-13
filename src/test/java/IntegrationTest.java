import app.MainApplication;
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import service.Service;

import java.time.LocalDate;

public class IntegrationTest {

    private Service service;
    private String studentId;
    private String assignmentId;

    public IntegrationTest(){
        service = MainApplication.createService();
    }

    @Test
    public void testAddStudent(){
        studentId = "aaa";
        Student student = new Student(studentId, "Catinas", 1, "cdie2270@scs.ubbcluj.ro");
        try {
            assert service.addStudent(student).getID().equals(studentId);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    public void testAddAssignment(){
        assignmentId = "1";
        Tema tema = new Tema(assignmentId, "o tema", 3, 2);
        try{
            assert service.addTema(tema).getID().equals(assignmentId);
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    public void testAddGrade(){
        String notaId = "1";
        LocalDate received = LocalDate.of(2018, 10, 16);
        Nota nota = new Nota(notaId, "aaa", "1", 9, received);
        try{
            service.addNota(nota, "ok");
            assert true;
        }
        catch (Exception ex){
            assert false;
        }
    }

    @Test
    public void integrate(){
        testAddStudent();
        testAddAssignment();
        testAddGrade();
    }
}
