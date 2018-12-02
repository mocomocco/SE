public class MyObject {

    static String Name;
    static String Image;
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