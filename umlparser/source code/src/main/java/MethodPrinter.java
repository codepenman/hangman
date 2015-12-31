import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.*;

import java.io.*;
import java.util.*;

public class MethodPrinter {

    private MethodPrinter mp;
    private String a, b[], c;
    private String xyz;
    private Collection<String> col;
    private ArrayList<String> strCol;

    public Object instantiate(String className) throws Exception {
        a = className;
        mp = new MethodPrinter ();
        Type type = new ClassOrInterfaceType ();
        this.xyz = "";
        String str = a;
        // Load the class.
        return this.a;
    }

    public static void main (String[] args) throws Exception {
/*
        new MethodPrinter ().instantiate ("java.util.Collection");

        String path = System.getProperty ("user.dir") + File.separator + "foStream.txt";
        FileOutputStream outputStream = new FileOutputStream (path);
        outputStream.write (path.getBytes ());

        path = System.getProperty ("user.dir") + File.separator + "classDiagram.txt";
        System.out.println (path);
        System.out.println (System.getProperty ("file.separator"));

        SourceFileReader sourceFileReader =
                new SourceFileReader (new File (path), new File(System.getProperty ("user.dir")));

        sourceFileReader.getGeneratedImages ();*/
/*
        File imageFile = generatedImageList.get (0).getPngFile ();
        System.out.println (imageFile.getAbsolutePath ());
*/

        // creates an input stream for the file to be parsed
        FileInputStream in =
                new FileInputStream (
                        "W:\\IntelliJ Projects\\umlparser\\src\\main\\java\\MethodPrinter.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse (in);
        } finally {
            in.close ();
        }

        // visit and print the methods names
        new MethodVisitor ().visit (cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<MethodPrinter> {

        @Override
        public void visit(ClassOrInterfaceDeclaration n, MethodPrinter arg)    {
            System.out.println(n.getName ());
            super.visit (n, arg);
        }

        @Override
        public void visit (FieldDeclaration n, MethodPrinter arg) {
            super.visit (n, arg);
        }

        @Override
        public void visit (ClassOrInterfaceType n, MethodPrinter arg) {
            super.visit (n, arg);
        }

        @Override
        public void visit (MethodDeclaration n, MethodPrinter arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods
            super.visit (n, arg);
        }

        @Override
        public void visit (AssignExpr n, MethodPrinter arg) {
            super.visit (n, arg);
        }

        @Override
        public void visit (ArrayCreationExpr n, MethodPrinter arg) {
            super.visit (n, arg);
        }

        @Override
        public void visit (FieldAccessExpr n, MethodPrinter arg) {
            super.visit (n, arg);
        }

        @Override
        public void visit (ReturnStmt n, MethodPrinter arg) {
            super.visit (n, arg);
        }


        @Override
        public void visit (ObjectCreationExpr n, MethodPrinter arg) {
            super.visit (n, arg);
        }
    }
}