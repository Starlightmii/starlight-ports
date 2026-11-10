package y1;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f6743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f6744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f6745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6746d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6748f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k2.b f6749g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final k2.k f6750h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d2.d f6751i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f6752j;

    public d0(f fVar, h0 h0Var, List list, int i5, boolean z2, int i6, k2.b bVar, k2.k kVar, d2.d dVar, long j5) {
        this.f6743a = fVar;
        this.f6744b = h0Var;
        this.f6745c = list;
        this.f6746d = i5;
        this.f6747e = z2;
        this.f6748f = i6;
        this.f6749g = bVar;
        this.f6750h = kVar;
        this.f6751i = dVar;
        this.f6752j = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d0)) {
            return false;
        }
        d0 d0Var = (d0) obj;
        return b4.l.a(this.f6743a, d0Var.f6743a) && b4.l.a(this.f6744b, d0Var.f6744b) && b4.l.a(this.f6745c, d0Var.f6745c) && this.f6746d == d0Var.f6746d && this.f6747e == d0Var.f6747e && this.f6748f == d0Var.f6748f && b4.l.a(this.f6749g, d0Var.f6749g) && this.f6750h == d0Var.f6750h && b4.l.a(this.f6751i, d0Var.f6751i) && k2.a.c(this.f6752j, d0Var.f6752j);
    }

    public final int hashCode() {
        return Long.hashCode(this.f6752j) + ((this.f6751i.hashCode() + ((this.f6750h.hashCode() + ((this.f6749g.hashCode() + m.h.b(this.f6748f, b.b.c((((this.f6745c.hashCode() + ((this.f6744b.hashCode() + (this.f6743a.hashCode() * 31)) * 31)) * 31) + this.f6746d) * 31, 31, this.f6747e), 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("TextLayoutInput(text=");
        sb.append((Object) this.f6743a);
        sb.append(", style=");
        sb.append(this.f6744b);
        sb.append(", placeholders=");
        sb.append(this.f6745c);
        sb.append(", maxLines=");
        sb.append(this.f6746d);
        sb.append(", softWrap=");
        sb.append(this.f6747e);
        sb.append(", overflow=");
        int i5 = this.f6748f;
        if (i5 == 1) {
            str = "Clip";
        } else if (i5 == 2) {
            str = "Ellipsis";
        } else {
            str = i5 == 3 ? "Visible" : "Invalid";
        }
        sb.append((Object) str);
        sb.append(", density=");
        sb.append(this.f6749g);
        sb.append(", layoutDirection=");
        sb.append(this.f6750h);
        sb.append(", fontFamilyResolver=");
        sb.append(this.f6751i);
        sb.append(", constraints=");
        sb.append((Object) k2.a.l(this.f6752j));
        sb.append(')');
        return sb.toString();
    }
}
