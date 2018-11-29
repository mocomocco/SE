public class Level{
    private class Size{
        int gridsize;
        int width;
        int height;
    }
    String Name;
    String BackgroundName;
    Size size;
    Object[] objects;

    Level(){
        size.gridsize=0;
        size.width=0;
        size.height=0;
        Name="asdf";
        BackgroundName="NoImage";
    }
}