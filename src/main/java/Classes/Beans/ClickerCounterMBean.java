package Classes.Beans;

public interface ClickerCounterMBean {

    void printMessage();
    void count(boolean isHit);
    int getHitCount();
    int getClickCount();
    void setHitCount(int hitCount);
    void setClickCount(int clickCount);
}
