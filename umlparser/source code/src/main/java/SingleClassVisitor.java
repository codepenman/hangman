import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.*;
import type.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class SingleClassVisitor {

    private Container container;

    public SingleClassVisitor (Container container) {
        this.container = container;
    }

    public void parseCompilationUnit (CompilationUnit compilationUnit) {
        Visitor cuVisitor = new Visitor ();
        cuVisitor.visit (compilationUnit, false);
    }

    public void identifyAndStoreDifferentTypes (CompilationUnit compilationUnit) {
        Visitor cuVisitor = new Visitor ();
        cuVisitor.visit (compilationUnit, true);
    }

    private class Visitor extends VoidVisitorAdapter<Boolean> {

        @Override
        public void visit (ClassOrInterfaceDeclaration n, Boolean isTypeIdentification) {
            if(isTypeIdentification)    {
                container.addToTypeIdentifierMap (n.getName (), getType (n.isInterface ()));
                return;
            }

            //Define ClassEntity Name -> ClassEntity Name
            container.initializeClass (n.isInterface (), n.getName ());
            container.setScope (Scope.CLASS);

            if (n.getExtends () != null) {
                for (ClassOrInterfaceType classOrInterfaceType : n.getExtends ()) {
                    container.addRelation (Action.EXTENSION, new InternalClassOrInterfaceType
                            (classOrInterfaceType, 0));
                }
            }

            if (n.getImplements () != null) {
                for (ClassOrInterfaceType classOrInterfaceType : n.getImplements ()) {
                    /*By default I use Old Notation, Container will decide later based on Options to be Old Notation
                    or New Notation....*/
                    container.addRelation (Action.IMPLEMENTATION, new InternalClassOrInterfaceType
                            (classOrInterfaceType, 0));
                }
            }

            /*
            * I perform this loop, to visit my other elements of ClassEntity like Fields, Methods*/
            if (n.getMembers () != null) {
                for (final BodyDeclaration member : n.getMembers ()) {
                    member.accept (this, isTypeIdentification);
                }
            }

            container.update();
        }

        private TypeEnum getType (boolean anInterface) {
            if(anInterface) {
                return TypeEnum.INTERFACE;
            }else   {
                return TypeEnum.CLASS;
            }
        }

        @Override
        public void visit (ConstructorDeclaration n, Boolean isTypeIdentification) {
            if(isTypeIdentification)    {
                return;
            }

            container.setScope (Scope.CONSTRUCTOR);
            AccessModifier accessModifier = P2PlantUMLRepresentationAMMap.instance ().getUMLRepresentation (n
                    .getModifiers ());
            String name = n.getName ();
            Constructor constructor = new Constructor (name, accessModifier);
            if(n.getParameters () != null)  {
                for(Parameter parameter : n.getParameters ())   {
                    InternalType paramType = Parser2InternalTypeConverterHelper.getInternalType (parameter.getType ());
                    addRelationConditionally (paramType, Action.PARAMETER);
                    InternalParameter param = new InternalParameter (paramType, parameter.getId ().getName ());
                    constructor.addParameter (param);
                }
            }
            container.addConstructor (constructor);
        }

        @Override
        public void visit (FieldDeclaration n, Boolean isTypeIdentification) {
            if(isTypeIdentification)    {
                return;
            }

            AccessModifier accessModifier =
                    P2PlantUMLRepresentationAMMap.instance ().getUMLRepresentation (n.getModifiers ());
            Type fieldType = n.getType ();

            container.setScope (Scope.FIELD);
            InternalType internalType = Parser2InternalTypeConverterHelper.getInternalType (fieldType);

            ArrayList<String> fieldNames = new ArrayList<> ();
            ArrayList<String> convertedFieldNames = new ArrayList<> ();

            for (final VariableDeclarator var : n.getVariables ()) {
                /*
                * var.getId ().getArrayCount ()
                * In order to get the multiplicty u have to check it as part of ArrayCreationExpr
                * use to Identify weather it is a collection or not*/
                if(accessModifier.isPrivate ()) {
                    GetSetMethodVisitor getSetMethodVisitor = new GetSetMethodVisitor (var.getId ().getName (),
                            internalType, container);
                    getSetMethodVisitor.visit (n.getParentNode ());
                    boolean hasGetterSetter = getSetMethodVisitor.hasGetterSetter();
                    if(hasGetterSetter) {
                        convertedFieldNames.add (var.getId ().getName ());
                    }else   {
                        fieldNames.add (var.getId ().getName ());
                    }
                }else   {
                    fieldNames.add (var.getId ().getName ());
                }
            }
            addRelationConditionally (internalType, Action.DECLARATION);
            if(!convertedFieldNames.isEmpty ()) {
                Field field = new Field (AccessModifier.PUBLIC, internalType, convertedFieldNames);
                container.addField (field);
            }
            if(!fieldNames.isEmpty ())  {
                Field field = new Field (accessModifier, internalType, fieldNames);
                container.addField (field);
            }
        }

        @Override
        public void visit (MethodDeclaration n, Boolean isTypeIdentification) {
            if(isTypeIdentification)    {
                return;
            }

            container.setScope (Scope.METHOD);
            //Creating a method which should be finally added into the Current ClassEntity
            Method method = new Method(n.getName ());

            //Logic to understand Access Modifier of the Method
            int modifier = n.getModifiers ();

            boolean isInterfaceMethod = container.isInterfaceMethod ();;

            AccessModifier accessModifier;

            if(isInterfaceMethod && modifier == 0)   {
                 accessModifier = AccessModifier.PUBLIC;
            }else   {
                accessModifier = P2PlantUMLRepresentationAMMap.instance ().getUMLRepresentation (modifier);
            }

            method.setAccessModifier (accessModifier);
            //Logic to understand Method Return Type
            Type returnType = n.getType ();

            InternalType internalType = Parser2InternalTypeConverterHelper.getInternalType (returnType);

            addRelationConditionally (internalType, Action.RETURN);

            method.setInternalReturnType (internalType);

            //Logic to Understand Type and Name of Method Parameters
            if (n.getParameters() != null) {
                for (final Parameter p : n.getParameters()) {
                    InternalType paramType = Parser2InternalTypeConverterHelper.getInternalType (p.getType ());
                    addRelationConditionally (paramType, Action.PARAMETER);
                    InternalParameter param = new InternalParameter (paramType, p.getId ().getName ());
                    method.addParameter (param);
                }
            }

            if(n.getBody () != null)    {
                n.getBody ().accept (this, false);
            }

            container.addMethod (method);
        }

        private void addRelationConditionally (InternalType internalType, Action action) {
            if (internalType instanceof InternalClassOrInterfaceType) {
                container.addRelation (action, (InternalClassOrInterfaceType)internalType);
            }
        }

        @Override
        public void visit (ObjectCreationExpr n, Boolean isTypeIdentification) {
            if(isTypeIdentification)    {
                return;
            }

            Node node = n.getParentNode ().getParentNode ();
            if(node instanceof VariableDeclarationExpr)  {
                Type type = ((VariableDeclarationExpr) node).getType ();
                InternalType internalType = Parser2InternalTypeConverterHelper.getInternalType (type);
                addRelationConditionally (internalType, Action.OBJECT_INITIALIZATION);
            }
        }
    }
}

