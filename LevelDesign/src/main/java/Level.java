import java.util.ArrayList;
import java.util.List;

public class Level{


    public static class MyObject {

        String Name;
        String Image;
        ObjectSize objectsize;

        MyObject(int anchorx,int anchory,String image,String name){
            objectsize.anchorx = anchorx;
            objectsize.anchory = anchory;
            Image=image;
            Name=name;
        }

        MyObject(){
            objectsize.anchorx = 25;
            objectsize.anchory = 25;
            Image="image/grasst.gif";
            Name="asdf";
        }
    }
    static String Name;
    static String BackgroundImage;
    static String BackgroundName;
    static LevelSize levelsize;
    static List<MyObject> objects;
    static int[][] ObjectMap;

    Level(){
        levelsize.gridsize=27;
        levelsize.width=1;
        levelsize.height=1;
        ObjectMap=new int[1][1];
        ObjectMap[0][0]=0;
        objects= new ArrayList<MyObject>();
        Name="asdf";
        BackgroundName="asdf";
        BackgroundImage="image/background.png";
    }

    public static void main(String[] args) {
        Level samplelevel;
        samplelevel=new Level();
        System.out.println(samplelevel.Name);
        for(int i=0;i<samplelevel.objects.size();i++) {
            System.out.println(samplelevel.objects.get(i).Image);
        }
        MyObject newobj=new MyObject();
        newobj.Image="0だよー";
        samplelevel.objects.add(0,newobj);
        newobj=new MyObject();
        newobj.Image="1だよー";
        samplelevel.objects.add(1,newobj);
        for(int i=0;i<samplelevel.objects.size();i++) {
            System.out.println(samplelevel.objects.get(i).Image);
        }
    }
}