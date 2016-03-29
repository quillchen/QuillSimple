package quill.design.stragety;

/**
 * Created by Quill on 2016/3/29.
 */
public class FlyOnlyDuck extends Duck {
    public FlyOnlyDuck(){
        flyBe=new FlyStragety();
    }

    @Override
    public void display() {
        System.out.print("a fly-only duck");
    }
}
