import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.*;
import type.*;

/**
 * Harish Kumar K V
 */
public class GetSetMethodVisitor {

    private boolean hasGetter;
    private boolean hasSetter;
    private String fieldName;
    private InternalType fieldType;
    private Container container;

    public GetSetMethodVisitor (String fieldName, InternalType fieldType, Container container) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.container = container;
    }

    public void visit (Node node) {
        GetMethodVisitor getMethodVisitor = new GetMethodVisitor ();
        node.accept (getMethodVisitor, null);
        hasGetter = getMethodVisitor.hasGetMethod ();
        if (hasGetter) {
            container.addGetMethod (getMethodVisitor.getMethod ());
        }

        SetMethodVisitor setMethodVisitor = new SetMethodVisitor ();
        node.accept (setMethodVisitor, null);
        hasSetter = setMethodVisitor.hasSetMethod ();
        if (hasSetter) {
            container.addSetMethod (setMethodVisitor.getMethod ());
        }
    }

    public boolean hasGetterSetter () {
        return hasGetter && hasSetter;
    }

    private Method createMethod (MethodDeclaration methodDeclaration) {
        //Creating a method which should be finally added into the Current ClassEntity
        Method method = new Method (methodDeclaration.getName ());

        //Logic to understand Access Modifier of the Method
        AccessModifier accessModifier =
                P2PlantUMLRepresentationAMMap.instance ().getUMLRepresentation (methodDeclaration.getModifiers ());
        method.setAccessModifier (accessModifier);

        //Logic to understand Method Return Type
        Type returnType = methodDeclaration.getType ();

        InternalType internalType = Parser2InternalTypeConverterHelper.getInternalType (returnType);

        method.setInternalReturnType (internalType);

        //Logic to Understand Type and Name of Method Parameters
        if (methodDeclaration.getParameters () != null) {
            for (final Parameter p : methodDeclaration.getParameters ()) {
                InternalType paramType = Parser2InternalTypeConverterHelper.getInternalType (p.getType ());
                InternalParameter param = new InternalParameter (paramType, p.getId ().getName ());
                method.addParameter (param);
            }
        }
        return method;
    }

    private class GetMethodVisitor extends VoidVisitorAdapter<Object> {

        private boolean hasSameReturnType = false;
        private boolean hasSameFieldName = false;
        private Method method;

        public boolean hasGetMethod () {
            return (hasSameReturnType && hasSameFieldName);
        }

        public Method getMethod () {
            return method;
        }

        @Override
        public void visit (MethodDeclaration n, Object arg) {
            if (hasGetMethod ()) {
                return;
            }

            AccessModifier accessModifier = P2PlantUMLRepresentationAMMap.instance ().
                    getUMLRepresentation (n.getModifiers ());

            if (accessModifier.isPrivate ()) {
                return;
            }
            method = createMethod (n);

            StringBuilder stringBuilder = new StringBuilder ();
            stringBuilder.append ("get");
            stringBuilder.append (Character.toUpperCase (fieldName.charAt (0)));
            stringBuilder.append (fieldName.substring (1));
            if(stringBuilder.toString ().equals (n.getName ())) {
                hasSameFieldName = true;
                hasSameReturnType = true;
            }

/*
            Type returnType = n.getType ();
            hasSameReturnType = fieldType.isOfType (returnType);
            if (n.getBody () != null) {
                n.getBody ().accept (this, arg);
            }
*/
        }



        @Override
        public void visit (ReturnStmt n, Object arg) {
            if (hasGetMethod ()) {
                return;
            }

            if (n.getExpr () instanceof NameExpr) {
                String retVarName = ((NameExpr) n.getExpr ()).getName ();
                hasSameFieldName = retVarName.equals (fieldName);
            }

            if (n.getExpr () instanceof FieldAccessExpr) {
                String retVarName = ((FieldAccessExpr) n.getExpr ()).getFieldExpr ().getName ();
                hasSameFieldName = retVarName.equals (fieldName);
            }
        }
    }

    private class SetMethodVisitor extends VoidVisitorAdapter<Object> {

        private boolean hasSameParamType = false;
        private boolean hasFieldInitStmt = false;
        private String methodParamName;
        private Method method;

        @Override
        public void visit (AssignExpr n, Object arg) {
            if (hasSetMethod ()) {
                return;
            }

/*

            boolean leftOp = false, rightOp = false;
            if (n.getTarget () instanceof FieldAccessExpr) {
                leftOp = ((FieldAccessExpr) n.getTarget ()).getFieldExpr ().getName ().equals (fieldName);
            }

            if (n.getTarget () instanceof NameExpr) {
                leftOp = ((NameExpr) n.getTarget ()).getName ().equals (fieldName);
            }

            if (n.getValue () instanceof NameExpr) {
                rightOp = ((NameExpr) n.getValue ()).getName ().equals (methodParamName);
            }
            hasFieldInitStmt = leftOp && rightOp;
*/
        }

        @Override
        public void visit (MethodDeclaration n, Object arg) {
            if (hasSetMethod ()) {
                return;
            }

            AccessModifier accessModifier = P2PlantUMLRepresentationAMMap.instance ().
                    getUMLRepresentation (n.getModifiers ());

            if (accessModifier.isPrivate ()) {
                return;
            }

            method = createMethod (n);
            StringBuilder stringBuilder = new StringBuilder ();
            stringBuilder.append ("set");
            stringBuilder.append (Character.toUpperCase (fieldName.charAt (0)));
            stringBuilder.append (fieldName.substring (1));
            if(stringBuilder.toString ().equals (n.getName ())) {
                hasSameParamType = true;
                hasFieldInitStmt = true;
            }
/*
            if (n.getParameters () != null) {
                if (n.getParameters ().isEmpty ()) {
                    hasSameParamType = false;
                    return;
                } else {
                    Parameter parameter = n.getParameters ().get (0);
                    InternalType paramType = Parser2InternalTypeConverterHelper.getInternalType (parameter.getType ());
                    hasSameParamType = paramType.isOfType (fieldType);
                    methodParamName = parameter.getId ().getName ();
                }
            }

            if (n.getBody () != null) {
                n.getBody ().accept (this, arg);
            }*/
        }

        public boolean hasSetMethod () {
            return hasSameParamType && hasFieldInitStmt;
        }

        public Method getMethod () {
            return method;
        }
    }
}
