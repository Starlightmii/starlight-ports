package e2;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f1992f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f1993g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final d f1994h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f1995i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final d f1996j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final d f1997k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1998e;

    static {
        int i5 = 1;
        f1992f = new d(i5, 0);
        f1993g = new d(i5, 1);
        f1994h = new d(i5, 2);
        f1995i = new d(i5, 3);
        f1996j = new d(i5, 4);
        f1997k = new d(i5, 5);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i5, int i6) {
        super(i5);
        this.f1998e = i6;
    }

    @Override // a4.c
    public final /* synthetic */ Object c(Object obj) {
        switch (this.f1998e) {
            case 0:
                float[] fArr = ((y0.f0) obj).f6626a;
                break;
            case 1:
                float[] fArr2 = ((y0.f0) obj).f6626a;
                break;
            case 2:
                break;
            case 3:
                int i5 = ((m) obj).f2037a;
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                break;
            default:
                int i6 = ((m) obj).f2037a;
                break;
        }
        return p3.n.f5026a;
    }
}
