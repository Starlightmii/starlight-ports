package n;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f4048c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public EdgeEffect f4049d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EdgeEffect f4050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public EdgeEffect f4051f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EdgeEffect f4052g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public EdgeEffect f4053h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public EdgeEffect f4054i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public EdgeEffect f4055j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public EdgeEffect f4056k;

    public z(Context context, int i5) {
        this.f4046a = context;
        this.f4047b = i5;
    }

    public static boolean f(EdgeEffect edgeEffect) {
        if (edgeEffect == null) {
            return false;
        }
        return !edgeEffect.isFinished();
    }

    public static boolean g(EdgeEffect edgeEffect) {
        if (edgeEffect == null) {
            return false;
        }
        return !((Build.VERSION.SDK_INT >= 31 ? h.f3917a.b(edgeEffect) : 0.0f) == 0.0f);
    }

    public final EdgeEffect a() {
        int i5 = Build.VERSION.SDK_INT;
        Context context = this.f4046a;
        EdgeEffect edgeEffectA = i5 >= 31 ? h.f3917a.a(context, null) : new g0(context);
        edgeEffectA.setColor(this.f4047b);
        if (!k2.j.a(this.f4048c, 0L)) {
            long j5 = this.f4048c;
            edgeEffectA.setSize((int) (j5 >> 32), (int) (j5 & 4294967295L));
        }
        return edgeEffectA;
    }

    public final EdgeEffect b() {
        EdgeEffect edgeEffect = this.f4050e;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f4050e = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect c() {
        EdgeEffect edgeEffect = this.f4051f;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f4051f = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect d() {
        EdgeEffect edgeEffect = this.f4052g;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f4052g = edgeEffectA;
        return edgeEffectA;
    }

    public final EdgeEffect e() {
        EdgeEffect edgeEffect = this.f4049d;
        if (edgeEffect != null) {
            return edgeEffect;
        }
        EdgeEffect edgeEffectA = a();
        this.f4049d = edgeEffectA;
        return edgeEffectA;
    }
}
