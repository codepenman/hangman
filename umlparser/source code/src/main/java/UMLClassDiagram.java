import net.sourceforge.plantuml.*;
import relation.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Harish Kumar K V
 */
public class UMLClassDiagram {

    private static final String SKIN_PARAM = "skinparam";
    private static final String START_UML = "@startuml";
    private static final String END_UML = "@enduml";
    private static final String USER_WORKING_DIRECTORY = System.getProperty ("user.dir");
    private static final String PLANT_UML_CODE_TEMP_FILE = USER_WORKING_DIRECTORY +
            System.getProperty ("file.separator") + "classDiagram.txt";
    private static final String TEXT_EXTENSION = ".txt";
    private static final String ICON_SIZE_ZERO = SKIN_PARAM + " classAttributeIconSize 0";
    private static final String TITLE = "title <b>Class Diagram</b>";
    private static final String HIDE_INTERFACE_CIRCLE = "hide <<interface>> circle";
    private static final String TITLE_FONT_COLOR_BLACK = SKIN_PARAM + " titleFontColor black";
    private static final String TITLE_FONT_SIZE_28 = SKIN_PARAM + " titleFontSize 28";
    private static final String TITLE_FONT_STYLE_ITALIC = SKIN_PARAM + " titleFontStyle italic";
    private static final String CLASS_BACKGROUND_COLOR_DDDDDD = SKIN_PARAM + " classBackgroundColor #DDDDDD";
    private static final String CLASS_BORDER_COLOR_BLACK = SKIN_PARAM + " classBorderColor black";
    private static final String CLASS_FONT_COLOR_BLACK = SKIN_PARAM + " classFontColor black";
    private static final String CLASS_FONT_SIZE_20 = SKIN_PARAM + " classFontSize 20";
    private static final String CLASS_FONT_STYLE_BOLD = SKIN_PARAM + " classFontStyle bold";
    private static final String CLASS_ATTRIBUTE_FONT_SIZE_16 = SKIN_PARAM + " classAttributeFontSize 16";
    private static final String CLASS_ATTRIBUTE_FONT_STYLE_BOLD = SKIN_PARAM + " classAttributeFontStyle bold";
    private static final String ACTIVITY_ARROW_COLOR_BLACK = SKIN_PARAM + " activityArrowColor black";
    private static final String CLASS_ARROW_COLOR_BLACK = SKIN_PARAM + " classArrowColor black";
    private static final String CLASS_ARROW_FONT_SIZE = SKIN_PARAM + " classArrowFontSize 12";
    private static final String STEREOTYPE_CBACKGROUND_COLOR_EEEEEE =
            SKIN_PARAM + " stereotypeCBackgroundColor #EEEEEE";

    private Container container;

    public UMLClassDiagram (Container container) {
        this.container = container;
    }

    public void createDiagram (String directory, String fileName) {
        String temporaryFile = fileName.contains (".") ? fileName.split (Pattern.quote ("."))[0] : fileName;
        File file = new File(temporaryFile + TEXT_EXTENSION);
        System.out.println (temporaryFile);

        generatePlantUMLCode (file);

        try {
            /*
            * Class Diagram image file name will be same as name of text file in which plant uml code is defined*/
            SourceFileReader sourceFileReader =
                    new SourceFileReader (file, new File (directory));
            sourceFileReader.getGeneratedImages ();
            file.delete ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private void generatePlantUMLCode (File temporaryFile) {
        try (PrintWriter writer = new PrintWriter (temporaryFile)) {

            writer.println (START_UML);
            writer.println (TITLE);
            writer.println (TITLE_FONT_COLOR_BLACK);
            writer.println (TITLE_FONT_SIZE_28);
            writer.println (TITLE_FONT_STYLE_ITALIC);
            writer.println (CLASS_BACKGROUND_COLOR_DDDDDD);
            writer.println (CLASS_BORDER_COLOR_BLACK);
            writer.println (CLASS_FONT_COLOR_BLACK);
            writer.println (CLASS_FONT_SIZE_20);
            writer.println (CLASS_FONT_STYLE_BOLD);
            writer.println (CLASS_ATTRIBUTE_FONT_SIZE_16);
            writer.println (CLASS_ATTRIBUTE_FONT_STYLE_BOLD);
            writer.println (ACTIVITY_ARROW_COLOR_BLACK);
            writer.println (CLASS_ARROW_COLOR_BLACK);
            writer.println (CLASS_ARROW_FONT_SIZE);
            writer.println (STEREOTYPE_CBACKGROUND_COLOR_EEEEEE);
            writer.println (ICON_SIZE_ZERO);

            for (ClassEntity classEntity : container.getClassEntityList ()) {
                writer.println (classEntity);
            }

            for (Relation relation : getRelations ()) {
                writer.println (relation);
            }

            writer.println(HIDE_INTERFACE_CIRCLE);
            writer.println (END_UML);
        } catch (FileNotFoundException e) {
            /*
            * throw an exception saying unable to create a writable file by printing directory name
            * */
            e.printStackTrace ();
        }
    }

    public List<Relation> getRelations () {
        List<Relation> relations = container.getRelations ();
        Relation[] relationList = relations.toArray (new Relation[relations.size ()]);
        List<Relation> finalList = new ArrayList<> ();
        List<Integer> unwantedIndexes = new ArrayList<> ();
        for (int i = 0; i < relationList.length; i++) {
            for (int j = i + 1; j < relationList.length; j++) {
                Relation iRelation = relationList[i];
                Relation jRelation = relationList[j];
                if (representsSameRelation (iRelation, jRelation)) {
                    finalList.add (combinedRelation (iRelation, jRelation));
                    unwantedIndexes.add (i);
                    unwantedIndexes.add (j);
                }
            }
        }

        /*...Add all remaining relations other than combined ones...*/
        for(int i = 0; i < relationList.length; i++)   {
            if(!unwantedIndexes.contains (i))   {
                finalList.add (relationList[i]);
            }
        }

        return finalList;
    }

    private Relation combinedRelation (Relation firstRelation, Relation secRelation) {
        RelationSymbol relationSymbol = firstRelation.relation;

        if (relationSymbol == RelationSymbol.ASSOCIATION_NOTATION) {
            AssociationRelation firstRelation_temp = (AssociationRelation)firstRelation;
            AssociationRelation secRelation_temp = (AssociationRelation)secRelation;
            return new BiDirectionalAssociationRelation (firstRelation_temp.fromEntity, firstRelation_temp.toEntity,
                    secRelation_temp.getCardinality(), firstRelation_temp.getCardinality());
        }

        return new Relation (firstRelation.fromEntity,
                RelationSymbol.BI_DIRECTIONAL_DEPENDENCY_NOTATION,
                firstRelation.toEntity);
    }

    /*
    * This method will return true in 2 cases :
    *  Association (A --> B & B --> A) both means same and one relation should be created..
    *   Dependency (A ..> B & B ..> A) both are interdependent and one relation should be created
    *
    * */
    boolean representsSameRelation (Relation relation1, Relation relation2) {
        if (relation1.fromEntity.equals (relation2.toEntity) && relation1.toEntity.equals (relation2.fromEntity)) {
            if (relation1.relation == relation2.relation) {
                return true;
            }
        }
        return false;
    }
}
