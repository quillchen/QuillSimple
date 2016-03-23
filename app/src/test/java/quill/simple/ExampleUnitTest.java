package quill.simple;

import org.junit.Test;

import quill.simple.builder.Director;
import quill.simple.builder.MacBuilder;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test1 Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testBuilder() throws Exception{
        MacBuilder macBuilder = new MacBuilder();
		Director director = new Director(macBuilder);
		director.buildComputer("Intel", " Retina");
		System.out.println(macBuilder.create().toString());
    }
}