package b;

import android.window.BackEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f308a = new a();

    public final BackEvent a(float f5, float f6, float f7, int i5) {
        return new BackEvent(f5, f6, f7, i5);
    }

    public final float b(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        return backEvent.getProgress();
    }

    public final int c(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        return backEvent.getSwipeEdge();
    }

    public final float d(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        return backEvent.getTouchX();
    }

    public final float e(BackEvent backEvent) {
        b4.l.e("backEvent", backEvent);
        return backEvent.getTouchY();
    }
}
