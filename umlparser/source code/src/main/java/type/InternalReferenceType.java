package type;

import com.github.javaparser.ast.type.*;

/**
 * Harish Kumar K V
 */
public class InternalReferenceType implements InternalType {

    private final InternalType type;
    private final int arrayCount;

    public InternalReferenceType (final InternalType type) {
        this.type = type;
        arrayCount = 0;
    }

    public InternalReferenceType (final InternalType type, int arrayCount) {
        this.type = type;
        this.arrayCount = arrayCount;
    }

    @Override
    public boolean isReferenceType () {
        return true;
    }

    @Override
    public String getTypeName () {
        return type.getTypeName ();
    }

    @Override
    public boolean isOfType (Type type) {
        return false;
    }

    @Override
    public boolean isOfType (InternalType internalType) {
        return false;
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
        return type.toString ();
    }
}
