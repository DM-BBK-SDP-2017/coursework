package sml;

//import scala.collection.immutable.List;

import java.lang.reflect.Constructor;
import java.util.HashMap;

//add: label: String, op: String, val result: Int, val op1: Int, val op2: Int
/**
 * Created by dannymadell on 12/02/2017.
 */
public class JavaReflectionHelper {

    public static Instruction getObject(String str, java.util.ArrayList fields) throws Exception {

        //ArrayList<Object> fields = Arrays.asList(list.get(0));
        HashMap<String, String > mappings = new HashMap<>();
        mappings.put("class java.lang.String", "String");
        mappings.put("class java.lang.Integer", "scala.Int");
        System.out.println("fields size = " + fields.size());



        Class[] constParams = new Class[fields.size()];
        //constParams[0] = Class.forName("scala.String");
        //constParams[1] = Class.forName("scala.String");



        for (int i = 2; i < fields.size(); i++) {
            System.out.println( fields.get(i).getClass());

            constParams[i] = Class.forName(
                    mappings.get(fields.get(i).
                            getClass().
                            toString()));
            //System.out.println(constParams[i]);

        }

        Constructor constructor = Class.forName(str).getConstructor(constParams);
        return (Instruction) constructor.newInstance(fields);
        //va; Class.forName(str).newInstance(fields)
    }
}