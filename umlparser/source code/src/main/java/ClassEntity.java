import java.lang.reflect.*;
import java.util.*;

/**
 * Harish Kumar K V
 */
public class ClassEntity {

    private static final String CLASS = "class";
    private static final String INTERFACE = "interface";
    private static final String OPEN_INTERFACE = "<<";
    private static final String CLOSE_INTERFACE = ">>";
    private static final String SPACE = " ";
    private final Boolean isInterface;
    private final String name;
    private ArrayList<Constructor> constructorsList;
    private ArrayList<Method> methodList;
    private ArrayList<Method> getMethodList;
    private ArrayList<Method> setMethodList;
    private ArrayList<Field> fieldList;

    public ClassEntity (Boolean isInterface, String name) {
        this.isInterface = isInterface;
        this.name = name;
        constructorsList = new ArrayList<> ();
        methodList = new ArrayList<> ();
        getMethodList = new ArrayList<>();
        setMethodList = new ArrayList<>();
        fieldList = new ArrayList<> ();
    }

    public void addConstructor(Constructor constructor) {
        constructorsList.add (constructor);
    }

    public void addMethod(Method method)    {
        methodList.add (method);
    }

    public void addField(Field field)  {
        fieldList.add (field);
    }

    public String getName() {
        return name;
    }

    public Boolean isInterface () {
        return isInterface;
    }

    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder ();

        stringBuilder.append (open ());
        /******ADDING ALL THE FIELDS*********/
        for(Field field : fieldList)    {
            if(field.getAccessModifier () == AccessModifier.PACKAGE ||
                    field.getAccessModifier () == AccessModifier.PROTECTED) {
                continue;
            }
            stringBuilder.append (field);
        }

        /*******ADDING ALL THE CONSTRUCTORS****/
        for(Constructor constructor : constructorsList) {
            stringBuilder.append (constructor);
        }

        /******ADDING ALL THE METHODS*********/
        for(Method method : methodList) {
            if(isSetter(method))    {
                continue;
            }
            if(isGetter (method))   {
                continue;
            }
            stringBuilder.append (method);
        }
        stringBuilder.append (close ());

        return stringBuilder.toString ();
    }

    private String open () {
        if(isInterface ())  {
            return INTERFACE + SPACE + name + SPACE + OPEN_INTERFACE + INTERFACE + CLOSE_INTERFACE + SPACE+ "{" + "\n";
        }
        return CLASS + SPACE + name + SPACE + "{" + "\n";
    }

    private String close()  {
        return "}";
    }

    public void addGetMethod (Method method) {
        getMethodList.add (method);
    }

    public void addSetMethod (Method method) {
        setMethodList.add (method);
    }

    public boolean isSetter (Method method) {
        for(Method setMethod : setMethodList)   {
            if(setMethod.compareTo (method) == 1)
                return true;
        }
        return false;
    }

    public boolean isGetter (Method method) {
        for(Method getMethod : getMethodList)    {
            if(getMethod.compareTo (method) == 1)
                return true;
        }
        return false;
    }
}
