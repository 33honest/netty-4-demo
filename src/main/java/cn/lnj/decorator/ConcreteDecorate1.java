package cn.lnj.decorator;

public class ConcreteDecorate1 extends Decorate {

    public ConcreteDecorate1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doAnotherthing();
    }

    public void doAnotherthing() {
        System.out.println("功能B");
    }
}
