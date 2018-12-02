//package script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
public class JsonUtil{
    public void Load(Level level,String jsonname){

    }
}
*/
public class JsonUtil {


     public static String readFile(String jsonname){
        try{
            File file = new File(jsonname);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data = "";
            String str = br.readLine();
            while(str != null){
                data += str;
                str = br.readLine();
            }

            br.close();
            return data;
        }catch(FileNotFoundException e){
            System.out.println(e);
            return null;
        }catch(IOException e){
            System.out.println(e);
            return null;
        }
    }


    public static Object get(String json, String code) {
        // Get the JavaScript engine
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String script = "var obj = " + json + ";";
        try {
            engine.eval(script);
            {
                return engine.eval("obj." + code);
            }
        } catch (ScriptException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getstr(String json, String code){
            return(get(json,code).toString());
    }

    public static int getInt(String json, String code){
            return(new Integer( getstr(json,code) ).intValue());
    }


    public static void main(String[] args) {
       String json = readFile("image/sample.json");
        if (json!=null){
        {
            Object value = JsonUtil.get(json, "test");
            System.out.println(value);
        }
        {
            Object value = JsonUtil.get(json, "test2.test3");
            System.out.println(value);
        }

    }}

}
