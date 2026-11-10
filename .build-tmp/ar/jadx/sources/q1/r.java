package q1;

import android.os.SystemClock;
import android.view.MotionEvent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5330e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u f5331f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(u uVar, int i5) {
        super(0);
        this.f5330e = i5;
        this.f5331f = uVar;
    }

    @Override // a4.a
    public final Object a() {
        int actionMasked;
        switch (this.f5330e) {
            case 0:
                u uVar = this.f5331f;
                MotionEvent motionEvent = uVar.f5400p0;
                if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                    uVar.f5402q0 = SystemClock.uptimeMillis();
                    uVar.post(uVar.f5408t0);
                }
                return p3.n.f5026a;
            default:
                return this.f5331f.get_viewTreeOwners();
        }
    }
}
