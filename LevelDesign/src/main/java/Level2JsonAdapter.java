import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level2JsonAdapter {

    private static void addinstance(List<Instance> instances,Instance instance){
        instances.add(instance);
    }
    public static void WriteJson(Level level,String jsonname){
        int objectnum, instancesnum, x, y;
        String attributescode, instancescode;
        List<List<Instance>> instances;

        try {
            File file = new File("json/"+jsonname+".json");

            FileWriter filewriter = new FileWriter(file);

            filewriter.write("{");
                filewriter.write("\"attributes\":{");
                    filewriter.write("\"Name\":\""+level.Name+"\",");
                    filewriter.write("\"gridsize\":"+ level.levelsize.gridsize+",");
                    filewriter.write("\"width\":"+level.levelsize.width+",");
                    filewriter.write("\"height\":"+level.levelsize.height);
                filewriter.write("},");

                filewriter.write("\"background\":{");
                    filewriter.write("\"Name\":\""+level.BackgroundName+"\",");
                    filewriter.write("\"Image\":\""+ level.BackgroundImage+"\"");
                filewriter.write("}");

                objectnum=level.objects.size();
                filewriter.write(",");
                instances=new ArrayList<List<Instance>>();
                for(int object=0;object<objectnum;object++){
                    List<Instance> ainstance=new ArrayList();
                    instances.add(ainstance);
                }
                filewriter.write("\"objects\":{");
                filewriter.write("\"length\":"+objectnum);
                if(objectnum!=0){
                    filewriter.write(",");
                    int instancesize=0;
                    for(int object=0;object<objectnum;object++) {
                        if(object!=0)filewriter.write(",");
                        filewriter.write("\"object"+(object+1)+"\":{");
                        filewriter.write("\"attributes\":{");
                        filewriter.write("\"Name\":\""+  level.objects.get(object).Name+"\",");
                        filewriter.write("\"Image\":\""+  level.objects.get(object).Image+"\",");
                        filewriter.write("\"anchorx\":"+  level.objects.get(object).objectsize.anchorx+",");
                        filewriter.write("\"anchory\":"+  level.objects.get(object).objectsize.anchory);
                        filewriter.write("}");//attribute
                        filewriter.write("}");
                    }

                    filewriter.write(",\"instances\":{");

                        for(int instancey=0;instancey<level.levelsize.height;instancey++) {
                            for (int instancex = 0; instancex < level.levelsize.width; instancex++) {
                                int target = level.ObjectMap[instancey][instancex];
                                if (target != 0) {
                                    filewriter.write("\"instance" + (instancesize + 1) + "\":{");
                                    filewriter.write("\"object\":" + target + ",");
                                    filewriter.write("\"x\":" + (instancex+1) + ",");
                                    filewriter.write("\"y\":" + (instancey+1));
                                    filewriter.write("},");//instance
                                    instancesize += 1;
                                }
                            }
                        }
                    filewriter.write("\"length\":"+  instancesize);


                    filewriter.write("}");//instances
                }
            filewriter.write("}");
            filewriter.write("}");

            filewriter.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

}
