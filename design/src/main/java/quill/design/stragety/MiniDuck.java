package quill.design.stragety;

/**
 * Created by Quill on 2016/3/29.
 */
public class MiniDuck extends Duck {

    public MiniDuck(){
        flyBe=new FlyStragety();
        quackBe=new QuackStragety();
    }

    @Override
    public void display() {
        System.out.print("I'm MiniDuck!");
    }
}
