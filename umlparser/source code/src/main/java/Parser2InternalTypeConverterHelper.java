import com.github.javaparser.ast.type.*;
import type.*;

/**
 * Harish Kumar K V
 */
public class Parser2InternalTypeConverterHelper {

    public static InternalType getInternalType (Type type) {
        InternalType internalType = new UnknownInternalType ();

        if(type instanceof ReferenceType) {
            Type referenceType = ((ReferenceType) type).getType ();
            int arrayCount = ((ReferenceType) type).getArrayCount ();
            if (referenceType instanceof ClassOrInterfaceType) {
                internalType = new InternalClassOrInterfaceType ((ClassOrInterfaceType)referenceType, arrayCount);
            }

                /*
                * This condition is for the case in which field types are of primitive array's  */
            if(referenceType instanceof PrimitiveType){
                internalType =  new InternalPrimitiveType ((PrimitiveType)referenceType, true);
            }
        }

        if(type instanceof PrimitiveType)  {
            internalType = new InternalPrimitiveType ((PrimitiveType)type, false);
        }

        if(type instanceof VoidType)  {
            internalType = new InternalVoidType ((VoidType)type);
        }

        return internalType;
    }
}
