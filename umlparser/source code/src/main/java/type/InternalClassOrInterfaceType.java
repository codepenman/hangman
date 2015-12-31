package type;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.type.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class InternalClassOrInterfaceType implements InternalType{

    private static final String SPACE = " ";
    private static final String TYPE_ARG_START_BRACKET = "<";
    private static final String TYPE_ARG_END_BRACKET = ">";
    private final ClassOrInterfaceType type;
    private int arrayCount;
    private static final List<String> collectionTypes;
    static  {
        collectionTypes = new ArrayList<> ();
        collectionTypes.add ("Collection");
        collectionTypes.add ("List");
        collectionTypes.add ("ArrayList");
    }

    public InternalClassOrInterfaceType(final ClassOrInterfaceType type, final int arrayCount)  {
        this.type = type; // Wrapper should not be created in this way, Learn how to create wrappers for complex
        // objects...
        this.arrayCount = arrayCount;
    }

    @Override
    public boolean isReferenceType () {
        return true;
    }

    @Override
    public String getTypeName () {
        return type.getName ();
    }

    @Override
    public boolean isOfType (Type type) {
        if(type instanceof ReferenceType)   {
            ReferenceType refType = (ReferenceType)type;
            return refType.getType () instanceof ClassOrInterfaceType && ((ClassOrInterfaceType) refType.getType ()).getName ()
                    .equals (getTypeName ());
        }
        return false;
    }

    @Override
    public boolean isOfType (InternalType internalType) {
        return internalType instanceof InternalClassOrInterfaceType &&
                internalType.getTypeName ().equals (getTypeName ());
    }

    @Override
    public boolean isCollectionType () {
        Node node = type.getParentNode ().getParentNode ();
        if(node instanceof ClassOrInterfaceType)    {
            ClassOrInterfaceType classOrInterfaceType = ((ClassOrInterfaceType) node);
            if(collectionTypes.contains (classOrInterfaceType.getName ()))  {
                return true;
            }else   {
                new InternalClassOrInterfaceType (classOrInterfaceType, 0).isCollectionType ();
            }
        }
        return arrayCount == 1;
    }
/*

    @Override
    public void setArrayCount (int arrayCount) {
        this.arrayCount = arrayCount;
    }
*/

    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (type);
/*
        List<InternalType> internalTypeArgs = getInternalTypeArgs ();
        if(internalTypeArgs.isEmpty ())    {
            return stringBuilder.toString ();
        }
        stringBuilder.append (SPACE).append (TYPE_ARG_START_BRACKET);
        int numberOfTypeArgs = 1;
        for (InternalType internalTypeArg : internalTypeArgs) {
            if (numberOfTypeArgs < internalTypeArgs.size () - 1) {
                stringBuilder.append (internalTypeArg.getTypeName ()).append (", ");
            } else {
                stringBuilder.append (internalTypeArg.getTypeName ());
            }
        }
        stringBuilder.append (TYPE_ARG_END_BRACKET);
*/
        return stringBuilder.toString ();
    }

    public List<InternalType> getInternalTypeArgs () {
        List<InternalType> internalTypeArgs = new ArrayList<> ();
        for(Type curType : type.getTypeArgs ()) {
            InternalType internalTypeArg = new UnknownInternalType();
            if (curType instanceof ReferenceType) {

                Type referenceType = ((ReferenceType) curType).getType ();
                int arrayCount = ((ReferenceType) curType).getArrayCount ();

                if (referenceType instanceof ClassOrInterfaceType) {
                    internalTypeArg = new InternalClassOrInterfaceType ((ClassOrInterfaceType)referenceType, arrayCount);
                }
                /*
                * This condition is for the case in which field types are of primitive array's  */
                if(referenceType instanceof PrimitiveType){
                    internalTypeArg =  new InternalPrimitiveType ((PrimitiveType)referenceType, true);
                }
            }else if(curType instanceof PrimitiveType)   {
                internalTypeArg = new InternalPrimitiveType ((PrimitiveType)curType, false);
            }
            internalTypeArgs.add (internalTypeArg);
        }
        return internalTypeArgs;
    }
}
