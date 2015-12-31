package relation;

/**
 * Harish Kumar K V
 */
public class Relation {

    protected static final String SPACE = " ";
    protected static final String COLON = ":";
    public final String fromEntity;
    public final RelationSymbol relation;
    public final String toEntity;

    public Relation(String fromEntity, RelationSymbol relation, String toEntity)   {
        this.fromEntity = fromEntity;
        this.relation = relation;
        this.toEntity = toEntity;
    }

    @Override
    public boolean equals (Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof Relation))  {
            return false;
        }

        Relation otherRelation = (Relation) obj;

        if(fromEntity.equals (otherRelation.fromEntity) && toEntity.equals
                (otherRelation.toEntity))  {
            if(relation.toString ().equals (otherRelation.relation.toString ()))    {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode () {
        return (fromEntity + relation.toString () + toEntity).hashCode ();
    }

    @Override
    public String toString () {
        return fromEntity + SPACE + relation + SPACE + toEntity;
    }

}
