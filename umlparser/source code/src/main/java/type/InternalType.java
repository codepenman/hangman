package type;

import com.github.javaparser.ast.type.*;

/**
 * Harish Kumar K V
 */
public interface InternalType {
    boolean isReferenceType ();
    String getTypeName ();
    boolean isOfType(Type type);
    boolean isOfType(InternalType internalType);
    boolean isCollectionType();
}
