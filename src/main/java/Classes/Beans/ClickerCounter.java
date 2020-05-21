package Classes.Beans;

public class ClickerCounter implements ClickerCounterMBean{
    private int clickCount;
    private int hitCount;
    @Override
    public void printMessage() {

    }

    @Override
    public void count(boolean isHit) {
        if (clickCount%10 == 0)
            System.out.println("Число кликов кратно 10");
        if (isHit)
            hitCount++;
        clickCount++;
    }

    @Override
    public int getHitCount() {
        return hitCount;
    }

    @Override
    public int getClickCount() {
        return clickCount;
    }

    @Override
    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
