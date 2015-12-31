package type;

import com.github.javaparser.ast.type.*;

/**
 * Harish Kumar K V
 */
public class InternalPrimitiveType implements InternalType {

    private static final String ARRAY_BLOCK = "[]";
    private final PrimitiveType type;
    private final Boolean isArray;

    public InternalPrimitiveType (PrimitiveType type, Boolean isArray) {
        this.type = type;
        this.isArray = isArray;
    }

    @Override
    public boolean isReferenceType () {
        return true;
    }

    @Override
    public String getTypeName () {
        return PP2JPRepresentaionMap.instance ().getJavaPrimitive (type.getType ());
    }

    @Override
    public boolean isOfType (Type type) {
        if(type instanceof PrimitiveType)   {
            String typeName = PP2JPRepresentaionMap.instance ().getJavaPrimitive (((PrimitiveType) type).getType ());
            return typeName.equals (getTypeName ());
        }
        return false;
    }

    @Override
    public boolean isOfType (InternalType internalType) {
        return getTypeName ().equals (internalType.getTypeName ());
    }

    @Override
    public boolean isCollectionType () {
        return isArray;
    }

/*
    @Override
    public void setArrayCount (int arrayCount) {
    }
*/

    @Override
    public String toString () {
        if(isArray) {
            return getTypeName () + ARRAY_BLOCK;
        }else   {
            return getTypeName ();
        }
    }
}
