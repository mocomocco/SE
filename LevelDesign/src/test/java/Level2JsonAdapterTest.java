import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by HINARI on 2018/12/13.
 */
public class Level2JsonAdapterTest {
    @Test
    public void WriteAndReadJson() throws Exception {
        Level samplelevel=new Level();
        Json2LevelAdapter.Load(samplelevel,"./json/level1.json");
        Level2JsonAdapter.WriteJson(samplelevel,"sample");
        Json2LevelAdapter.Load(samplelevel,"./json/sample.json");
        String actualName=samplelevel.objects.get(0).Image;
        String expectedName="./image/tree3_1.gif";
        assertThat(actualName,is(expectedName));
    }
    @Test
    public void writeJson() throws Exception {
        Level samplelevel=new Level();
        Level2JsonAdapter.WriteJson(samplelevel,"sample");
        Json2LevelAdapter.Load(samplelevel,"./json/sample.json");
        String actualName=samplelevel.Name;
        String expectedName="asdf";
        assertThat(actualName,is(expectedName));
    }
}