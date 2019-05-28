package cn.lnj.decorator;

public class Decorate implements Component {

    private Component component;

    public Decorate(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
