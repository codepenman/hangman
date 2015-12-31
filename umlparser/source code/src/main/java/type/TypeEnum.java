package type;

/**
 * Harish Kumar K V
 */
public enum TypeEnum {
    CLASS("class"),
    INTERFACE("interface");

    private final String type;

    TypeEnum (String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public boolean isInterface()    {
        return (TypeEnum.INTERFACE == this);
    }

    public boolean isClass()    {
        return (TypeEnum.CLASS == this);
    }
}
