import relation.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class Scope2RelationMap {

    private static final HashMap<Scope, HashMap<Action, RelationSymbol>> map = new HashMap<>();
    static  {
        HashMap<Action, RelationSymbol> classScope = new HashMap<> ();
        classScope.put (Action.EXTENSION, RelationSymbol.EXTENDS_NOTATION);
        classScope.put(Action.IMPLEMENTATION, RelationSymbol.IMPLEMENTS_NOTATION_OLD);
        map.put (Scope.CLASS, classScope);

        HashMap<Action, RelationSymbol> cntrScope = new HashMap<> ();
        cntrScope.put (Action.PARAMETER, RelationSymbol.DEPENDENCY_NOTATION);
        map.put (Scope.CONSTRUCTOR, cntrScope);

        HashMap<Action, RelationSymbol> fieldScope = new HashMap<>();
        fieldScope.put (Action.DECLARATION, RelationSymbol.ASSOCIATION_NOTATION);
        fieldScope.put (Action.INITIALIZATION, RelationSymbol.COMPOSITION_NOTATION);
        map.put(Scope.FIELD, fieldScope);

        HashMap<Action, RelationSymbol> methodScope = new HashMap<>();
        methodScope.put (Action.RETURN, RelationSymbol.DEPENDENCY_NOTATION);
        methodScope.put (Action.PARAMETER, RelationSymbol.DEPENDENCY_NOTATION);
        methodScope.put (Action.INITIALIZATION, RelationSymbol.DEPENDENCY_NOTATION);
        methodScope.put (Action.OBJECT_INITIALIZATION, RelationSymbol.DEPENDENCY_NOTATION);
        map.put (Scope.METHOD, methodScope);
    }

    public RelationSymbol getRelationSym (Scope scope, Action action)    {
        return map.get (scope).get (action);
    }
}
