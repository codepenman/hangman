package type;

import com.github.javaparser.ast.type.*;

/**
 * Harish Kumar K V
 */
public class UnknownInternalType implements InternalType {
    public static final String UNKNOWN_TYPE = "UNKNOWN TYPE";
    private final UnknownType type;

    public UnknownInternalType()    {
        this.type = null;
    }

    public UnknownInternalType (UnknownType type) {
        this.type = type;
    }

    @Override
    public boolean isReferenceType () {
        return false;
    }

    @Override
    public String getTypeName () {
        if(type == null)    {
            //TODO If logger is implemented write something to log
        }
        return UNKNOWN_TYPE;
    }

    @Override
    public String toString () {
        return UNKNOWN_TYPE;
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

/*    @Override
    public void setArrayCount (int arrayCount) {
    }*/
}
