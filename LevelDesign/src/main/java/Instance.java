/**
 * Created by HINARI on 2018/12/13.
 */
public class Instance {
    static int x;
    static int y;
    Instance(int inputx,int inputy){
        x=inputx;
        y=inputy;
    }
    Instance(){
        x=0;
        y=0;
    }
    public void set(int inputx,int inputy){
        x=inputx;
        y=inputy;
    }
}
