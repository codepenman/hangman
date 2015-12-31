import type.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class Method implements Comparable<Method> {

    public static final String COLON = ":";
    private static final String OPEN_METHOD_BRACKET = "(";
    private static final String CLOE_METHOD_BRACKET = ")";
    private static final String SPACE = " ";
    private final String methodName;
    private AccessModifier accessModifier;
    private InternalType returnInternalType;
    private List<InternalParameter> parameterList;

    public Method (String methodName) {
        this.methodName = methodName;
        parameterList = new ArrayList<> ();
    }

    public void setInternalReturnType (InternalType returnInternalType) {
        this.returnInternalType = returnInternalType;
    }

    public InternalType getReturnInternalType () {
        return returnInternalType;
    }

    public AccessModifier getAccessModifier () {
        return accessModifier;
    }

    public void setAccessModifier (AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getMethodName () {
        return methodName;
    }

    public void addParameter (InternalParameter internalParameter) {
        parameterList.add (internalParameter);
    }

    public List<InternalParameter> getParameterList () {
        return parameterList;
    }

    @Override
    public String toString () {
        if(!accessModifier.isPublic ()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (accessModifier);
        //This space is just for readability of file for debugging purpose, it will work even with out space....
        stringBuilder.append (SPACE);
        stringBuilder.append (methodName).append (OPEN_METHOD_BRACKET).append (SPACE);
        int numberOfFields = 1;
        for (InternalParameter parameter : parameterList) {
            if (numberOfFields < parameterList.size () - 1) {
                numberOfFields++;
                stringBuilder.append (parameter).append (", ");
            } else {
                stringBuilder.append (parameter);
            }
        }
        stringBuilder.append (SPACE).append (CLOE_METHOD_BRACKET);
        stringBuilder.append (SPACE).append (COLON).append (SPACE);
        stringBuilder.append (returnInternalType);
        stringBuilder.append ("\n");

        return stringBuilder.toString ();
    }

    @Override
    public int compareTo (Method otherMethod) {
        if(otherMethod == null) {
            return 0;
        }
        if (methodName.equals (otherMethod.getMethodName ()) &&
                returnInternalType.isOfType (otherMethod.returnInternalType)) {
                if(parameterList.isEmpty ())    {
                    return 1;
                }else   {
                    InternalParameter parameter = parameterList.get (0);
                    if(parameter.isEqualTo (otherMethod.getParameterList ().get (0)))   {
                        return 1;
                    }
                }
        }
        return 0;
    }
}
