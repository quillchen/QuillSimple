package quill.design;

import org.junit.Test;

import quill.design.stragety.FlyOnlyDuck;
import quill.design.stragety.MiniDuck;

/**
 * Created by Quill on 2016/3/29.
 */
public class DuckTest {
    @Test
    public void testD(){
        MiniDuck miniDuck = new MiniDuck();
        miniDuck.display();
        miniDuck.performFly();
        miniDuck.performQuack();
        System.out.println();
        FlyOnlyDuck flyOnlyDuck = new FlyOnlyDuck();
        flyOnlyDuck.display();
        flyOnlyDuck.performFly();

    }
}
