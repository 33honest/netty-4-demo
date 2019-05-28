package cn.lnj.decorator;

public class ConcreteDecorate implements Component {

    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
