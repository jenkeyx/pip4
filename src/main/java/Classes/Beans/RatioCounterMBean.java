package Classes.Beans;

public interface RatioCounterMBean {

    void calcRatio(int hitCount, int clickCount);

    void setRatio(float ratio);

    float getRatio();
}
