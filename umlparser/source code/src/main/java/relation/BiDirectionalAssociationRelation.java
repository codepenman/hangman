package relation;

/**
 * Harish Kumar K V
 */
public class BiDirectionalAssociationRelation extends Relation {
    private final Cardinality leftCardinality;
    private final Cardinality rightCardinality;

    public BiDirectionalAssociationRelation (String fromEntity, String toEntity, Cardinality
            leftCardinality, Cardinality rightCardinality) {
        super (fromEntity, RelationSymbol.BI_DIRECTIONAL_ASSOCIATION_NOTATION, toEntity);
        this.leftCardinality = leftCardinality;
        this.rightCardinality = rightCardinality;
    }

    @Override
    public String toString () {
        String leftOperand = fromEntity + SPACE + leftCardinality;
        String rightOperand = rightCardinality + SPACE + toEntity;
        return leftOperand + SPACE + relation + SPACE + rightOperand;
    }
}
