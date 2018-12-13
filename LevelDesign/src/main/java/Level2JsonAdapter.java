import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Level2JsonAdapter {
    public static void WriteJson(Level level,String jsonname){
        int objectnum, instancesnum, x, y;
        String attributescode, instancescode;
/*

  "objects": {
    "length": 2,
    "object1": {
      "attributes": {
        "Name": "tree",
        "Image": "tree3_1.gif",
        "anchorx": 25,
        "anchory": 25
      },
      "instances": {
        "length":2,
        "instance1": {
          "x": 2,
          "y": 3
        },
        "instance2": {
          "x": 6,
          "y": 1
        }
      }
    },
    "object2": {
      "attributes": {
        "Name": "grass",
        "Image": "grasst.gif",
        "anchorx": 25,
        "anchory": 25
      },
      "instances": {
        "length":1,
        "instance1": {
          "x": 5,
          "y": 5
        }
      }
    }
  }
}




*/
        try {
            File file = new File("json/"+jsonname+".json");

            FileWriter filewriter = new FileWriter(file);

            filewriter.write("{");
                filewriter.write("\"attributes\":{");
                    filewriter.write("\"Names\":\""+level.Name+"\",");
                    filewriter.write("\"gridsize\":"+ level.levelsize.gridsize+",");
                    filewriter.write("\"width\":"+level.levelsize.width+",");
                    filewriter.write("\"height\":"+level.levelsize.height);
                filewriter.write("},");

                filewriter.write("\"background\":{");
                    filewriter.write("\"Names\":\""+level.BackgroundName+"\",");
                    filewriter.write("\"image\":\""+ level.BackgroundImage+"\"");
                filewriter.write("},");

/*
*     "object1": {
      "attributes": {
        "Name": "tree",
        "Image": "tree3_1.gif",
        "anchorx": 25,
        "anchory": 25
      },
      "instances": {
        "length":2,
        "instance1": {
          "x": 2,
          "y": 3
        },
        "instance2": {
          "x": 6,
          "y": 1
        }
      }
    },
*/
                objectnum=level.objects.size();
                int[][] instancenum=new int[objectnum][level.levelsize.width*level.levelsize.height];
                for(int instancey=0;instancey<level.levelsize.height;instancey++){
                    for (int instancex=0;instancex<level.levelsize.width;instancex++){
                        int target =level.ObjectMap[instancey][instancex];
                        if(target!=0){

                        }
                    }
                }

                filewriter.write("\"objects\":{");
                    filewriter.write("\"length\":\""+objectnum+"\",");
                    for(int object=0;object<objectnum;object++) {
                        filewriter.write("\"object"+(object+1)+"\":\"{");
                        filewriter.write("\"attributes\":\"{");
                            filewriter.write("\"Name\":\""+  level.objects.get(object).Name+"\",");
                            filewriter.write("\"Image\":\""+  level.objects.get(object).Image+"\",");
                            filewriter.write("\"anchorx\":\""+  level.objects.get(object).objectsize.anchorx+"\",");
                            filewriter.write("\"anchory\":\""+  level.objects.get(object).objectsize.anchory+"\",");
                        filewriter.write("},");
                    }
                filewriter.write("},");

            filewriter.write("}");

            filewriter.close();
        } catch(IOException e) {
            System.out.println(e);
        }
/*

                level.ObjectMap = new int[level.levelsize.height][level.levelsize.width];
                objectnum = JsonUtil.getInt(json, "objects.length");


                for (int object = 1; object < objectnum + 1; object++) {
                    Level.MyObject newobj = new Level.MyObject();
                    attributescode = "objects.object" + String.valueOf(object) + ".attributes.";
                    newobj.Name = JsonUtil.getstr(json, attributescode + "Name");
                    newobj.Image = "image/" + JsonUtil.getstr(json, attributescode + "Image");
                    newobj.objectsize.anchorx = JsonUtil.getInt(json, attributescode + "anchorx");
                    newobj.objectsize.anchory = JsonUtil.getInt(json, attributescode + "anchory");

                    String name = JsonUtil.getstr(json, attributescode + "Name");
                    String image = "image/" + JsonUtil.getstr(json, attributescode + "Image");
                    int anchorx = JsonUtil.getInt(json, attributescode + "anchorx");
                    int anchory = JsonUtil.getInt(json, attributescode + "anchory");

                    level.objects.add(new Level.MyObject(anchorx, anchory, image, name));
                    instancescode = "objects.object" + String.valueOf(object) + ".instances.";
                    instancesnum = JsonUtil.getInt(json, instancescode + "length");
                    for (int instance = 1; instance < instancesnum + 1; instance++) {
                        //System.out.println(instancescode+ "  "+object);
                        x = JsonUtil.getInt(json, instancescode + "instance" + String.valueOf(instance) + ".x");
                        y = JsonUtil.getInt(json, instancescode + "instance" + String.valueOf(instance) + ".y");
                        level.ObjectMap[y - 1][x - 1] = object;
                    }
            }*/

    }

    public static void main(String[] args) {
        Level samplelevel;
        samplelevel=new Level();
        WriteJson(samplelevel,"level3");
    }
}
