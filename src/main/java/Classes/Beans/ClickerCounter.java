package Classes.Beans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class ClickerCounter extends NotificationBroadcasterSupport implements ClickerCounterMBean{
    private int clickCount;
    private int hitCount;

    @Override
    public void count(boolean isHit) {
        if (clickCount%10 == 0)
            sendNotification(new Notification("Число кликов кратно 10", this,System.currentTimeMillis()));
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
}
