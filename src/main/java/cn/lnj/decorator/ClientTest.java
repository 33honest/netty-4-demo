package cn.lnj.decorator;

public class ClientTest {


    public static void main(String[] args) {


        Component component = new ConcreteDecorate2(new ConcreteDecorate1(new ConcreteDecorate()));
        component.doSomething();

    }

}
