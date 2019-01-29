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
            objectsize.anchorx = 0;
            objectsize.anchory = 0;
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

    Level(String name,int x ,int y){
        levelsize.gridsize=27;
        levelsize.width=x;
        levelsize.height=y;
        ObjectMap=new int[y][x];
        objects= new ArrayList<MyObject>();
        Name=name;
        BackgroundName="asdf";
        BackgroundImage="image/background.png";
    }

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
}