import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.PrimitiveType.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class P2PlantUMLRepresentationAMMap {
    private static final HashMap<Integer, AccessModifier> accessModifierMap = new HashMap<> ();
    private static P2PlantUMLRepresentationAMMap instance;

    static {
        accessModifierMap.put (1, AccessModifier.PUBLIC);
        accessModifierMap.put (2, AccessModifier.PRIVATE);
        accessModifierMap.put (4, AccessModifier.PROTECTED);
    }

    private P2PlantUMLRepresentationAMMap () {}

    public static P2PlantUMLRepresentationAMMap instance() {
        if(instance == null)    {
            instance = new P2PlantUMLRepresentationAMMap ();
        }
        return instance;
    }

    public AccessModifier getUMLRepresentation (Integer parserRepresentation)    {
        if(accessModifierMap.containsKey (parserRepresentation))    {
            return accessModifierMap.get (parserRepresentation);
        }else if(ModifierSet.hasModifier (parserRepresentation, ModifierSet.PUBLIC))  {
            return AccessModifier.PUBLIC;
        }else if(ModifierSet.hasModifier (parserRepresentation, ModifierSet.PRIVATE))   {
            return AccessModifier.PRIVATE;
        }else if(ModifierSet.hasModifier (parserRepresentation, ModifierSet.PROTECTED))   {
            return AccessModifier.PROTECTED;
        }else   {
            return AccessModifier.PACKAGE;
        }
    }
}
