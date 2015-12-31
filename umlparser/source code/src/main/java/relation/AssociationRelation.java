package relation;

/**
 * Harish Kumar K V
 */
public class AssociationRelation extends Relation{
    private final Cardinality cardinality;

    public AssociationRelation (String entityA, String entityB, Cardinality cardinality) {
        super (entityA, RelationSymbol.ASSOCIATION_NOTATION, entityB);
        this.cardinality = cardinality;
    }

    public Cardinality getCardinality () {
        return cardinality;
    }

    @Override
    public String toString () {
        return fromEntity + SPACE + relation + SPACE + cardinality + SPACE + toEntity;
    }
}
