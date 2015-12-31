import relation.*;
import type.*;

import java.util.*;

/**
 * Harish Kumar K V
 */
public class Container {
    private List<ClassEntity> classEntityList;
    private RelationsSet relationsSet;
    private ClassEntity currEntity;
    private HashMap<String, TypeEnum> typeIdentifierMap;
    private static final Scope2RelationMap scope2RelationMap = new Scope2RelationMap ();
    private Scope scope;

    public Container () {
        classEntityList = new ArrayList<> ();
        relationsSet = new RelationsSet ();
        currEntity = null;
        typeIdentifierMap = new HashMap<> ();
    }

    public void initializeClass (Boolean isInterface, String name) {
        currEntity = new ClassEntity (isInterface, name);
    }

    public List<ClassEntity> getClassEntityList () {
        return classEntityList;
    }

    /*
    * Class OR Interface as part of Field Declaration(Not Initialization) should come under ASSOCIATION_NOTATION
    * Class OR Interface as part of Field Declaration(Initialization) should come under COMPOSITION_NOTATION
    * Class OR Interface as part of Method Declaration(Parameter, Return Type & Initialization in method body)
     * should come under DEPENDENCY_NOTATION
    * */
    public void addRelation (Action action, InternalClassOrInterfaceType classOrInterfaceType) {
        /*If this type is not available in class form then I just return and don't add the relation*/

        List<InternalType> typeArgs = classOrInterfaceType.getInternalTypeArgs ();
        if(typeArgs.isEmpty ()) {
            add(action, classOrInterfaceType);
        }else   {
            addTypeArgsRelation (action, classOrInterfaceType);
        }
    }

    private void addTypeArgsRelation (Action action, InternalClassOrInterfaceType classOrInterfaceType) {

        List<InternalType> typeArgs = classOrInterfaceType.getInternalTypeArgs ();
        if(typeArgs.isEmpty ()) {
            add(action, classOrInterfaceType);
        }else   {
            for(InternalType type : typeArgs)   {
                if(type instanceof InternalClassOrInterfaceType)    {
                    if(type.isCollectionType ())    {
                        addTypeArgsRelation (action, (InternalClassOrInterfaceType) type);
                    }else   {
                        addTypeArgsRelation (action, (InternalClassOrInterfaceType) type);
                    }
                }
            }
        }
    }

    private void add(Action action, InternalClassOrInterfaceType classOrInterfaceType)  {

        /*It wont add relations with those types which don't have declarations*/
        if(!typeIdentifierMap.containsKey (classOrInterfaceType.getTypeName ()))    {
            return;
        }

        RelationSymbol relationSymbol = scope2RelationMap.getRelationSym (scope, action);

        TypeEnum currType = typeIdentifierMap.get (currEntity.getName ());
        TypeEnum reqType = typeIdentifierMap.get (classOrInterfaceType.getTypeName ());

        /*.....No relation between interfaces other than Parent/Child relation....*/
        if(currType.isInterface () && reqType.isInterface () && relationSymbol != RelationSymbol.EXTENDS_NOTATION)    {
            return;
        }

        /*...No uses/dependency relation on classes....*/
        if(reqType.isClass () && relationSymbol == RelationSymbol.DEPENDENCY_NOTATION)  {
            return;
        }

/*        *//*.....No Association relation between interface and class...*//*
        if(((!currType.isInterface () && reqType.isInterface ()) || (currType.isInterface () && !reqType.isInterface
                ())) && relationSymbol == RelationSymbol.ASSOCIATION_NOTATION)   {
            return;
        }*/

        /*....Check for weaker relations and override them with stronger relations..*/
/*        Relation existingRelation = relationsSet.getExistingRelation (currEntity.getName (),
                classOrInterfaceType.getTypeName ());

        if(existingRelation != null)  {
            *//*If I have a relation with greater priority then i will keep the existing relation*//*
            if(existingRelation.relation.getPriority () > relationSymbol.getPriority ())   {
                return;
            }

            *//*If i have a relation with lesser priority i remove it and add a new relation*//*
            if(existingRelation.relation.getPriority () < relationSymbol.getPriority ())   {
                relationsSet.removeRelation (existingRelation);
                //continue by adding new relation after existing relation is removed...
            }
        }*/

        Cardinality cardinality = Cardinality.ONE_TO_ONE;

        /*Create relation.Relation and update the Relations Set*/
        switch (relationSymbol) {
            case ASSOCIATION_NOTATION: {

                if(classOrInterfaceType.isCollectionType ())    {
                    cardinality = Cardinality.ONE_TO_MANY;
                }
                Relation relation =
                        new AssociationRelation (currEntity.getName (), classOrInterfaceType.getTypeName (),
                                cardinality);
                relationsSet.addRelation (relation);
                break;
            }
            case COMPOSITION_NOTATION: {
                Relation relation = new Relation(currEntity.getName (), relationSymbol, classOrInterfaceType
                        .getTypeName ());
                relationsSet.addRelation (relation);
                break;
            }
            case DEPENDENCY_NOTATION: {
                Relation relation =
                        new Relation (currEntity.getName (), relationSymbol, classOrInterfaceType.getTypeName ());
                relationsSet.addRelation (relation);
                break;
            }
            case EXTENDS_NOTATION: {
                Relation relation =
                        new Relation (currEntity.getName (), relationSymbol, classOrInterfaceType.getTypeName ());
                relationsSet.addRelation (relation);
                break;
            }
            case IMPLEMENTS_NOTATION_OLD:   {
                Relation relation =
                        new Relation (currEntity.getName (), RelationSymbol.IMPLEMENTS_NOTATION_OLD,
                                classOrInterfaceType.getTypeName ());
                relationsSet.addRelation (relation);
                break;
            }
        }
    }

    public List<Relation> getRelations () {
        return relationsSet.getRelationsSet ();
    }

    public HashMap<String, TypeEnum> getTypeIdentifierMap () {
        return typeIdentifierMap;
    }

    public void addToTypeIdentifierMap (String className, TypeEnum type) {
        typeIdentifierMap.put (className, type);
    }

    public void addField (Field field) {
        currEntity.addField (field);
    }

    public void addMethod (Method method) {
        currEntity.addMethod (method);
    }

    public void update () {
        classEntityList.add (currEntity);
        currEntity = null;
    }

    public void addGetMethod (Method method) {
        currEntity.addGetMethod (method);
    }

    public void addSetMethod (Method method) {
        currEntity.addSetMethod (method);
    }

    public void setScope (Scope scope) {
        this.scope = scope;
    }

    public void addConstructor (Constructor constructor) {
        currEntity.addConstructor (constructor);
    }

    public boolean isInterfaceMethod () {
        return getTypeIdentifierMap ().get (currEntity.getName ()).isInterface ();
    }

    private class RelationsSet {
        HashSet<Relation> relationsSet;

        public RelationsSet () {
            relationsSet = new HashSet<> ();
        }

        public void addRelation (Relation relation) {
            relationsSet.add (relation);
        }

        public List<Relation> getRelationsSet () {
            return new ArrayList<> (relationsSet);
        }

        public boolean removeRelation (Relation relation){
            return relationsSet.remove (relation);
        }

        public Relation getExistingRelation (String entityA, String entityB)   {
            for(Relation relation : relationsSet)   {
                if(relation.fromEntity.equals (entityA) && relation.toEntity.equals (entityB) ||
                        relation.fromEntity.equals (entityB) && relation.toEntity.equals (entityA))  {
                    return relation;
                }
            }
            return null;
        }
    }
}
