package b;

import android.content.res.Resources;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k0 f348f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k0 f349g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f350e;

    static {
        int i5 = 1;
        f348f = new k0(i5, 0);
        f349g = new k0(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k0(int i5, int i6) {
        super(i5);
        this.f350e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f350e) {
            case 0:
                Resources resources = (Resources) obj;
                b4.l.e("resources", resources);
                return Boolean.valueOf((resources.getConfiguration().uiMode & 48) == 32);
            default:
                View view = (View) obj;
                b4.l.e("it", view);
                Object parent = view.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
        }
    }
}
