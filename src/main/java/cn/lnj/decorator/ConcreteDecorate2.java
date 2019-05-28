package cn.lnj.decorator;

public class ConcreteDecorate2 extends Decorate {

    public ConcreteDecorate2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doAnotherthing();
    }

    public void doAnotherthing() {
        System.out.println("功能C");
    }

}
