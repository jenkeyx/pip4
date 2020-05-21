package Classes.Beans;

public class RatioCounter implements RatioCounterMBean {
    float ratio;


    @Override
    public void calcRatio(int hitCount, int clickCount) {
        ratio = (float) hitCount / clickCount;
    }

    @Override
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    @Override
    public float getRatio() {
        return ratio;
    }

}
