package source;
import source.framework.Engine;
import source.framework.resources.*;

/*|******************************************************************************************
    This is to certify that this project is a colleborative work, based on the collective
    efforts in studying and applying the concepts we have learned in CCPROG3. We have 
    constructed the classes and their respective algorithms and corresponding code as a
    pair. The program was designed, run, tested, and debugged as a collective effort. We
    further certify that we have not copied in part or whole or otherwise plagiarized the
    work of other students and/or persons.

    Full Name: Sebastien Michael V. Dela Cruz
    DLSU ID#: 12112658

    Full Name: Izar Andrei C. Castillo  
    DLSU ID#: 12109933
    
    Course: CCPROG3 - S19
    Submission Date: December 10, 2022

********************************************************************************************/
public class Main {
    
    public static void main( String[] args ) {
        Loader.load();
        Engine.init();
        Engine.start();
    }
}