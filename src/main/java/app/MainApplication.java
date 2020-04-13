package app;


import repository.NotaFileRepository;
import repository.StudentFileRepository;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import repository.NotaXMLRepo;
import repository.TemaFileRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

import java.io.Serializable;


public class MainApplication {



    public static Service createService(String filenameStudent, String filenameTema, String filenameNota){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        return service;
    }

    public static Service createService(){
        return createService("fisiere/Studenti.xml", "fisiere/Teme.xml", "fisiere/Note.xml");
    }

    public static Service createTestService(){
        return createService("fisiere/TestStudenti.xml", "fisiere/TestTeme.xml", "fisiere/TestNote.xml");
    }

    public static UI createUI(Service service){

        UI ui = new UI(service);
        return ui;
    }

    public static void main(String[] args) {
        UI ui = createUI(createService());
        ui.run();
    }

}
