package sml;

//import scala.collection.immutable.List;

import java.lang.reflect.Constructor;

//add: label: String, op: String, val result: Int, val op1: Int, val op2: Int
/**
 * Created by dannymadell on 12/02/2017.
 */
public class JavaReflectionHelper {

    public static Instruction getObject(String str, java.util.List fields) throws Exception {

        Class[] constParams = new Class[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            constParams[i] = fields.get(i).getClass();
            //System.out.println(constParams[i]);

        }

        Constructor constructor = Class.forName(str).getConstructor(constParams);
        return (Instruction) constructor.newInstance(fields);
        //va; Class.forName(str).newInstance(fields)
    }
}
