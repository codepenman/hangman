package relation;

/**
 * Harish Kumar K V
 */
/*
* Use these Symbols only if you are working with Plant UML Library for generating UML Diagram
* */
public enum RelationSymbol {
    DEPENDENCY_NOTATION ("..>", 1),
    ASSOCIATION_NOTATION (" --> ", 2),
    COMPOSITION_NOTATION (" *-- ", 3),
    EXTENDS_NOTATION (" --|> ", 4),
    IMPLEMENTS_NOTATION_OLD (" ..|> ", 4),
    UNI_DIRECTIONAL_ASSOCIATION_NOTATION (" --> ", 2), // When u have getter in 'this' type of the type arrow is
    // pointing.
    BI_DIRECTIONAL_ASSOCIATION_NOTATION (" <--> ", 2), // When u have getter API's in both the types.
    BI_DIRECTIONAL_DEPENDENCY_NOTATION (" <..> ", 1);

    private final String symbol;
    private final int priority;

    RelationSymbol (String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    @Override
    public String toString () {
        return symbol;
    }

    public int getPriority()    {
        return priority;
    }
}
