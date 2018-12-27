import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by HINARI on 2018/12/13.
 */
public class Json2LevelAdapterTest {
    @Test
    public void load() throws Exception {
        Level samplelevel;
        samplelevel=new Level();
        Json2LevelAdapter.Load(samplelevel,"./json/level1.json");
        String actualName=samplelevel.Name;
        String expectedName="level1";
        assertThat(actualName,is(expectedName));
    }


}