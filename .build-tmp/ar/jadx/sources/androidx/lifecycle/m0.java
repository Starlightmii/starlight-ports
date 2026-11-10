package androidx.lifecycle;

import io.github.andrea_lyz.assistrestore.ui.MainActivity;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m0 implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f266d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ MainActivity f267e;

    public /* synthetic */ m0(MainActivity mainActivity, int i5) {
        this.f266d = i5;
        this.f267e = mainActivity;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f266d) {
            case 0:
                return l0.e(this.f267e);
            default:
                MainActivity mainActivity = this.f267e;
                mainActivity.getLifecycle().a(new j3.b(mainActivity, 0));
                return p3.n.f5026a;
        }
    }
}
