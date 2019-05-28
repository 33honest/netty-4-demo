package cn.lnj.decorator;

/**
 设计模式：装饰（Decorator）模式的适用性:
 想要透明并且动态地给对象增加新的方法而又不会影响其他对象；
 给对象增加的方法，在未来可能会发生改变；
 用子类扩展功能不实际的情况下
 装饰模式的角色：
 抽象构件角色（Component）：给出一个抽象接口，以规范准备接收附加 责任的对象。
 具体构件角色（Concrete Component）：定义一个将要接收附加责任的类。
 装饰角色（Decorator）：持有一个构件（Component）对象的引用，并定义一个与抽象构件接口一致的接口；
 具体装饰角色（Concrete Decorator）：负责给构件对象帖上附加的责任。

 */
public interface Component {

    void doSomething();

}
