package q1;

import android.view.MotionEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u f5356d;

    public s(u uVar) {
        this.f5356d = uVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        u uVar = this.f5356d;
        uVar.removeCallbacks(this);
        MotionEvent motionEvent = uVar.f5400p0;
        if (motionEvent != null) {
            boolean z2 = motionEvent.getToolType(0) == 3;
            int actionMasked = motionEvent.getActionMasked();
            if (z2) {
                if (actionMasked == 10 || actionMasked == 1) {
                    return;
                }
            } else if (actionMasked == 1) {
                return;
            }
            int i5 = 7;
            if (actionMasked != 7 && actionMasked != 9) {
                i5 = 2;
            }
            u uVar2 = this.f5356d;
            uVar2.E(motionEvent, i5, uVar2.f5402q0, false);
        }
    }
}
