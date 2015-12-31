import type.*;

/**
 * Harish Kumar K V
 */
public class InternalParameter {
    private static final String SPACE = " ";
    public static final String COLON = ":";
    private final InternalType internalType;
    private final String paramName;

    public InternalParameter (InternalType internalType, String paramName)  {
        this.internalType = internalType;
        this.paramName = paramName;
    }

    public InternalType getInternalType () {
        return internalType;
    }

    public String getParamName () {
        return paramName;
    }

    @Override
    public String toString () {
        return paramName + SPACE + COLON + SPACE + internalType;
    }

    public boolean isEqualTo (InternalParameter internalParameter) {
        return internalType.isOfType (internalParameter.getInternalType ());
    }
}
