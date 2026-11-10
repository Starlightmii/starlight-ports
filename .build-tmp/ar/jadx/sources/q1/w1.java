package q1;

import android.view.MotionEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w1 f5442a = new w1();

    public final boolean a(MotionEvent motionEvent, int i5) {
        float rawX = motionEvent.getRawX(i5);
        if (Float.isInfinite(rawX) || Float.isNaN(rawX)) {
            return false;
        }
        float rawY = motionEvent.getRawY(i5);
        return (Float.isInfinite(rawY) || Float.isNaN(rawY)) ? false : true;
    }
}
