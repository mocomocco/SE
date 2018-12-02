import java.util.List;

public class Json2LevelAdapter{
    public static void Load(Level level,String jsonname){
        String json = JsonUtil.readFile(jsonname);
        int objectnum,instancesnum,x,y;
        String attributescode,instancescode;
        if (json!=null){
            level.Name = JsonUtil.getstr(json, "attributes.Name");
            level.levelsize.gridsize=JsonUtil.getInt(json,"attributes.gridsize");
            level.levelsize.width=JsonUtil.getInt(json,"attributes.width");
            level.levelsize.height=JsonUtil.getInt(json,"attributes.height");
            level.ObjectMap = new int[level.levelsize.height][level.levelsize.width];
            level.BackgroundName=JsonUtil.getstr(json,"background.Name");
            level.BackgroundImage="image/"+JsonUtil.getstr(json,"background.Image");
            objectnum=JsonUtil.getInt(json,"objects.length");


            for(int object=1;object<objectnum+1;object++){
                Level.MyObject newobj=new Level.MyObject();
                attributescode="objects.object"+String.valueOf(object)+".attributes.";
                newobj.Name=JsonUtil.getstr(json, attributescode+"Name");
                newobj.Image="image/"+JsonUtil.getstr(json, attributescode+"Image");
                newobj.objectsize.anchorx=JsonUtil.getInt(json,attributescode+"anchorx");
                newobj.objectsize.anchory=JsonUtil.getInt(json,attributescode+"anchory");
                String name=JsonUtil.getstr(json, attributescode+"Name");
                String image="image/"+JsonUtil.getstr(json, attributescode+"Image");
                int anchorx=JsonUtil.getInt(json,attributescode+"anchorx");
                int anchory=JsonUtil.getInt(json,attributescode+"anchory");
                level.objects.add(new Level.MyObject(anchorx,anchory,image,name));
                System.out.println(" after0"+ level.objects.get(0).Name);
                System.out.println(" after"+ level.objects.get(level.objects.size()-1).Name);
                instancescode="objects.object"+String.valueOf(object)+".instances.";
                instancesnum=JsonUtil.getInt(json,instancescode+"length");
                for(int instance=1;instance<instancesnum+1;instance++) {
                    //System.out.println(instancescode+ "  "+object);
                    x=JsonUtil.getInt(json,instancescode+"instance"+String.valueOf(instance)+".x");
                    y=JsonUtil.getInt(json,instancescode+"instance"+String.valueOf(instance)+".y");
                    level.ObjectMap[y-1][x-1]=object;
                }
            }
        }
    }

    public static void main(String[] args) {
        Level samplelevel;
        samplelevel=new Level();
        System.out.println(samplelevel.Name);
        Load(samplelevel,"image/level1.json");
        /*for(int x=0;x<samplelevel.levelsize.height;x++) {
            for (int y=0;y<samplelevel.levelsize.width;y++) {
                System.out.print(samplelevel.ObjectMap[x][y]);
            }
            System.out.println(" ");
        }*/
        for(int i=0;i<samplelevel.objects.size();i++) {
            System.out.println(samplelevel.objects.get(i).Image);
        }

    }
}
