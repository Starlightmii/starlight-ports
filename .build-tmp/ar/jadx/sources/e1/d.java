package e1;

import java.util.ArrayList;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f1892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f1894h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c f1895i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1896j;

    public d(String str) {
        long j5 = y0.u.f6694g;
        this.f1887a = str;
        this.f1888b = 24.0f;
        this.f1889c = 24.0f;
        this.f1890d = 24.0f;
        this.f1891e = 24.0f;
        this.f1892f = j5;
        this.f1893g = 5;
        ArrayList arrayList = new ArrayList();
        this.f1894h = arrayList;
        int i5 = a0.f1864a;
        ArrayList arrayList2 = new ArrayList();
        c cVar = new c();
        cVar.f1878a = q3.s.f5477d;
        cVar.f1879b = arrayList2;
        this.f1895i = cVar;
        arrayList.add(cVar);
    }

    public static void a(d dVar, ArrayList arrayList, s0 s0Var) {
        if (dVar.f1896j) {
            d4.a.L("ImageVector.Builder is single use, create a new instance to create a new ImageVector");
            throw null;
        }
        ArrayList arrayList2 = dVar.f1894h;
        ((c) arrayList2.get(arrayList2.size() - 1)).f1879b.add(new d0(arrayList, s0Var));
    }

    public final e b() {
        if (this.f1896j) {
            d4.a.L("ImageVector.Builder is single use, create a new instance to create a new ImageVector");
            throw null;
        }
        while (true) {
            ArrayList arrayList = this.f1894h;
            if (arrayList.size() <= 1) {
                c cVar = this.f1895i;
                cVar.getClass();
                e eVar = new e(this.f1887a, this.f1888b, this.f1889c, this.f1890d, this.f1891e, new z(cVar.f1878a, cVar.f1879b), this.f1892f, this.f1893g);
                this.f1896j = true;
                return eVar;
            }
            if (this.f1896j) {
                d4.a.L("ImageVector.Builder is single use, create a new instance to create a new ImageVector");
                throw null;
            }
            c cVar2 = (c) arrayList.remove(arrayList.size() - 1);
            ArrayList arrayList2 = ((c) arrayList.get(arrayList.size() - 1)).f1879b;
            cVar2.getClass();
            arrayList2.add(new z(cVar2.f1878a, cVar2.f1879b));
        }
    }
}
