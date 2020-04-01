import app.MainApplication;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import service.Service;
import validation.ValidationException;


public class TestAddAssignment {

    private Service service;

    public TestAddAssignment(){
        service = MainApplication.createService();
    }

    @Test
    public void testSuccAddAssignment() {
        Tema tema = new Tema("1", "o tema perfecta", 10, 9);
        Tema tema2 = new Tema("2", "bun", 13, 13);
        try {
            Tema returned = service.addTema(tema);
            assert returned.getID().equals(tema.getID());
            Tema returned2 = service.addTema(tema2);
            assert returned2.getID().equals(tema2.getID());
        }
        catch (ValidationException vex){
            assert false;
        }
    }
    @Before
    public void setup(){
        service = MainApplication.createService();
    }

    @Test
    public void testFailAddAssignment(){
        Tema failed = new Tema("", "ok", 10, 10);
        try{
            service.addTema(failed);
            assert false;
        }
        catch (ValidationException ex){
            assert true;
        }
        try {
            Tema failed2 = new Tema(null, "ok", 10, 10);
            service.addTema(failed2);
            assert false;
        }
        catch (ValidationException vex){
            assert true;
        }
        catch (NullPointerException npx){
            System.out.println("---- nullptrex----");
        }

        try{
            Tema failed3 = new Tema("100", "",10, 10 );
            service.addTema(failed3);
            assert false;
        }
        catch (ValidationException vex){
            assert vex.getMessage().equals("Descriere invalida!");
        }

        try{
            Tema failed3 = new Tema("100", "descriere",-1, 10 );
            service.addTema(failed3);
            assert false;
        }
        catch (ValidationException vex){
            assert vex.getMessage().equals("Deadlineul trebuie sa fie intre 1-14.");
        }

    }
}
