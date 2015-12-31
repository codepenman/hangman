/**
 * Harish Kumar K V
 */
public enum AccessModifier {
    PUBLIC {
        @Override
        String plantUmlForm () {
            return "+";
        }
    },
    PRIVATE {
        @Override
        String plantUmlForm () {
            return "-";
        }
    },
    PACKAGE {
        @Override
        String plantUmlForm () {
            return "~";
        }
    },
    PROTECTED {
        @Override
        String plantUmlForm () {
            return "#";
        }
    };

    public boolean isPrivate()  {
        return this == AccessModifier.PRIVATE;
    }

    public boolean isPublic()  {
        return this == AccessModifier.PUBLIC;
    }

    public boolean isProtected()  {
        return this == AccessModifier.PROTECTED;
    }

    public boolean isPackage()  {
        return this == AccessModifier.PACKAGE;
    }


    @Override
    public String toString () {
        return plantUmlForm ();
    }

    abstract String plantUmlForm ();
}
