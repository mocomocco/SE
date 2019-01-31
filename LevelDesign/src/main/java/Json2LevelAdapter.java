import java.nio.file.FileSystems;
import java.util.List;

public class Json2LevelAdapter{
    public static void Load(Level level,String jsonname){

        String json = JsonUtil.readFile(jsonname);
        if (json!=null) {
            int objectnum, instancesnum, x, y;
            String attributescode, instancescode;
            if (json != null) {
                level.Name = JsonUtil.getstr(json, "attributes.Name");
                level.levelsize.gridsize = JsonUtil.getInt(json, "attributes.gridsize");
                level.levelsize.width = JsonUtil.getInt(json, "attributes.width");
                level.levelsize.height = JsonUtil.getInt(json, "attributes.height");
                level.ObjectMap = new int[level.levelsize.height][level.levelsize.width];
                level.BackgroundName = JsonUtil.getstr(json, "background.Name");
                level.BackgroundImage = JsonUtil.getstr(json, "background.Image");
                String spa = FileSystems.getDefault().getSeparator();
                objectnum = JsonUtil.getInt(json, "objects.length");


                for (int object = 1; object < objectnum + 1; object++) {
                    attributescode = "objects.object" + String.valueOf(object) + ".attributes.";

                    String name = JsonUtil.getstr(json, attributescode + "Name");
                    String image =  JsonUtil.getstr(json, attributescode + "Image");
                    int anchorx = JsonUtil.getInt(json, attributescode + "anchorx");
                    int anchory = JsonUtil.getInt(json, attributescode + "anchory");

                    level.objects.add(new Level.MyObject(anchorx, anchory, image, name));
                }
                instancescode ="objects.instances.";
                instancesnum = JsonUtil.getInt(json, instancescode+"length");
                for (int instance = 1; instance < instancesnum + 1; instance++) {
                    int object=JsonUtil.getInt(json, instancescode + "instance" + String.valueOf(instance) + ".object");
                    x = JsonUtil.getInt(json, instancescode + "instance" + String.valueOf(instance) + ".x");
                    y = JsonUtil.getInt(json, instancescode + "instance" + String.valueOf(instance) + ".y");
                    level.ObjectMap[y-1][x-1] = object;
                }
            }
        }else{
        }
    }

}
