package quill.design.stragety;

/**
 * Created by Quill on 2016/3/29.
 */
public abstract class Duck {
    FlyBe flyBe;
    QuackBe quackBe;

    public Duck(){

    }

    public abstract void display();

    public void performFly(){
        flyBe.fly();
    }

    public void performQuack(){
        quackBe.quack();
    }

    public void swim(){
        System.out.print("All Duck can swim");
    }
}
