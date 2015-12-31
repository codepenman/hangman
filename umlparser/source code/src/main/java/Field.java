import type.InternalType;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class Field {

    private static final String SPACE = " ";
    public static final String COLON = ":";
    private final AccessModifier accessModifier;
    private final InternalType fieldInternalType;
    private final ArrayList<String> fieldNames;

    public Field (AccessModifier accessModifier, InternalType fieldInternalType, ArrayList<String> fieldNames)  {
        this.accessModifier = accessModifier;
        this.fieldInternalType = fieldInternalType;
        this.fieldNames = new ArrayList<> (fieldNames);
    }

    public AccessModifier getAccessModifier () {
        return accessModifier;
    }

    public InternalType getFieldInternalType () {
        return fieldInternalType;
    }

    public List<String> getFieldNames () {
        return fieldNames;
    }

    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (accessModifier);

        int numberOfFields = 1;
        for(String fieldName : fieldNames)  {
            if(numberOfFields < fieldNames.size ()-1)    {
                numberOfFields++;
                stringBuilder.append (fieldName).append (", ");
            }else   {
                stringBuilder.append (fieldName);
            }
        }
        stringBuilder.append (SPACE).append (COLON).append (SPACE);
        stringBuilder.append (fieldInternalType);
        stringBuilder.append ("\n");

        return stringBuilder.toString ();
    }
}
