package type;

import com.github.javaparser.ast.type.PrimitiveType.*;

import java.util.*;

/**
 * Harish Kumar K V
 * Parser Primitive 2 Java Primitive Representation Map
 */
public class PP2JPRepresentaionMap {
    private static final HashMap<Primitive, String> primitiveMap = new HashMap<> ();
    private static PP2JPRepresentaionMap instance;
    static {
        primitiveMap.put (Primitive.Boolean, "boolean");
        primitiveMap.put(Primitive.Byte, "byte");
        primitiveMap.put(Primitive.Char, "char");
        primitiveMap.put(Primitive.Double, "double");
        primitiveMap.put(Primitive.Float, "float");
        primitiveMap.put (Primitive.Int, "int");
        primitiveMap.put(Primitive.Long, "long");
        primitiveMap.put(Primitive.Short, "short");
    }

    private PP2JPRepresentaionMap () {}

    public static PP2JPRepresentaionMap instance() {
        if(instance == null)    {
            instance = new PP2JPRepresentaionMap ();
        }
        return instance;
    }

    public String getJavaPrimitive(Primitive astPrimitive)    {
        return primitiveMap.get (astPrimitive);
    }
}
