package quill.design;

import org.junit.Test;

import quill.design.builder.Builder;
import quill.design.builder.Director;
import quill.design.builder.MacBuilder;

/**
 * Created by Quill on 2016/3/24.
 */
public class BuilderTest {
    @Test
    public void testBuilder(){
        Builder macBuilder = new MacBuilder();
        Director director = new Director(macBuilder);
        director.buildComputer("主机","显示器");
        System.out.print(macBuilder.create().toString());
    }
}
