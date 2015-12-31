package relation;

import javax.smartcardio.*;

/**
 * Harish Kumar K V
 */
public enum Cardinality {
    ONE_TO_MANY {
        @Override
        String stringRepresentation () {
            return "\"1..*\"";
        }
    },
    ONE_TO_ONE {
        @Override
        String stringRepresentation () {
            return "\"1\"";
        }
    },
    NONE {
        @Override
        String stringRepresentation () {
            return "";
        }
    };


    @Override
    public String toString () {
        return stringRepresentation();
    }

    abstract String stringRepresentation ();
}
