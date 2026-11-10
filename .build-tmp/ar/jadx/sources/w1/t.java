package w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a4.e f6365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f6366c;

    public /* synthetic */ t(String str) {
        this(str, p.f6334p);
    }

    public t(String str, a4.e eVar) {
        this.f6364a = str;
        this.f6365b = eVar;
    }

    public t(String str, boolean z2, a4.e eVar) {
        this(str, eVar);
        this.f6366c = z2;
    }

    public final void a(i iVar, Object obj) {
        iVar.c(this, obj);
    }

    public final String toString() {
        return "AccessibilityKey: " + this.f6364a;
    }
}
