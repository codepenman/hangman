package type;

import com.github.javaparser.ast.type.*;

/**
 * Harish Kumar K V
 */
public class InternalVoidType implements InternalType {
    private final VoidType type;
    private final String TYPE_NAME = "void";

    public InternalVoidType (VoidType type) {
        this.type = type;
    }

    @Override
    public boolean isReferenceType () {
        return false;
    }

    @Override
    public String getTypeName () {
        return TYPE_NAME;
    }

    @Override
    public boolean isOfType (Type type) {
        return false;
    }

    @Override
    public boolean isOfType (InternalType internalType) {
        return getTypeName ().equals (internalType.getTypeName ());
    }

    @Override
    public boolean isCollectionType () {
        return false;
    }

/*
    @Override
    public void setArrayCount (int arrayCount) {
    }
*/

    @Override
    public String toString () {
        return getTypeName ();
    }
}
