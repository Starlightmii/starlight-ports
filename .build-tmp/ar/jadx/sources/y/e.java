package y;

import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f6600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f6601c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f6602d = null;

    public e(String str, String str2) {
        this.f6599a = str;
        this.f6600b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return l.a(this.f6599a, eVar.f6599a) && l.a(this.f6600b, eVar.f6600b) && this.f6601c == eVar.f6601c && l.a(this.f6602d, eVar.f6602d);
    }

    public final int hashCode() {
        int iC = b.b.c((this.f6600b.hashCode() + (this.f6599a.hashCode() * 31)) * 31, 31, this.f6601c);
        d dVar = this.f6602d;
        return iC + (dVar == null ? 0 : dVar.hashCode());
    }

    public final String toString() {
        return "TextSubstitution(layoutCache=" + this.f6602d + ", isShowingSubstitution=" + this.f6601c + ')';
    }
}
