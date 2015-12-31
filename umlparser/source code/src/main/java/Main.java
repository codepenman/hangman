import com.github.javaparser.*;
import com.github.javaparser.ast.*;

import java.io.*;

/**
 * Harish Kumar K V
 */
public class Main {

    /*
    * Store all the file names
    * Validate ClassEntity Names with File Names, on mismatch throw the error to user saying invalid
    * Java file name --> Put this point as part of submission as well...
    * With this I can validate weather to create a relation for an entity and its dependencies by checking
    * if there is any file with dependency name.
    * */
    public static void main(String [] args) {
        String directory = args[0];
        String fileName = args[1];

        System.out.println (directory);
        File dirFile = new File(directory);

        FilenameFilter filenameFilter = (file, name) -> name.endsWith (".java");

        /*
        * First I Construct a map of Classes and Interfaces
        * */
        Container container = new Container ();
        SingleClassVisitor classVisitor = new SingleClassVisitor (container);
        CompilationUnit cu = new CompilationUnit ();
        for(File javaFile : dirFile.listFiles (filenameFilter)) {
            try (FileInputStream in = new FileInputStream (javaFile)) {
                // parse the file
                cu = JavaParser.parse (in);
            } catch (IOException | ParseException e) {
                e.printStackTrace ();
            }
            classVisitor.identifyAndStoreDifferentTypes (cu);
        }

        for(File javaFile : dirFile.listFiles (filenameFilter)) {
            try (FileInputStream in = new FileInputStream (javaFile)) {
                // parse the file
                cu = JavaParser.parse (in);
            } catch (IOException | ParseException e) {
                e.printStackTrace ();
            }
            classVisitor.parseCompilationUnit (cu);
        }

        UMLClassDiagram umlClassDiagram = new UMLClassDiagram (container);
        umlClassDiagram.createDiagram (directory, fileName);
    }
}
