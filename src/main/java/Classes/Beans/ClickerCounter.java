package Classes.Beans;

public class ClickerCounter implements ClickerCounterMBean{
    private int clickCount;
    private int hitCount;
    @Override
    public void printMessage() {

    }

    @Override
    public void count(Dot dot) {
        if (clickCount%10 == 0)
            System.out.println("Число кликов кратно 10");
        if (dot.isHit)
            hitCount++;
        clickCount++;
    }
}
