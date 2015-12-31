import java.util.*;

/**
 * Harish Kumar K V
 */
public class Constructor {

    public static final String COLON = ":";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String SPACE = " ";
    private final String name;
    private final AccessModifier accessModifier;
    private List<InternalParameter> parameterList;

    public Constructor(String name, AccessModifier accessModifier)    {
        this.name = name;
        this.accessModifier = accessModifier;
        parameterList = new ArrayList<> ();
    }

    public void addParameter(InternalParameter internalParameter){
        parameterList.add (internalParameter);
    }

    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder ();
        stringBuilder.append (accessModifier);
        //This space is just for readability of file for debugging purpose, it will work even with out space....
        stringBuilder.append (SPACE);
        stringBuilder.append (name).append (OPEN_BRACKET).append (SPACE);
        int numberOfFields = 1;
        for (InternalParameter parameter : parameterList) {
            if (numberOfFields < parameterList.size () - 1) {
                numberOfFields++;
                stringBuilder.append (parameter).append (", ");
            } else {
                stringBuilder.append (parameter);
            }
        }
        stringBuilder.append (SPACE).append (CLOSE_BRACKET);
        stringBuilder.append ("\n");

        return stringBuilder.toString ();
    }
}
