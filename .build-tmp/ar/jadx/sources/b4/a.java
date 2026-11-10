package b4;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements i, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f669d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Class f670e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f671f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f672g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f674i;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f673h = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f675j = 4;

    public a(int i5, Class cls, Object obj, String str, String str2) {
        this.f669d = obj;
        this.f670e = cls;
        this.f671f = str;
        this.f672g = str2;
        this.f674i = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f673h == aVar.f673h && this.f674i == aVar.f674i && this.f675j == aVar.f675j && this.f669d.equals(aVar.f669d) && this.f670e.equals(aVar.f670e) && this.f671f.equals(aVar.f671f) && this.f672g.equals(aVar.f672g);
    }

    @Override // b4.i
    public final int getArity() {
        return this.f674i;
    }

    public final int hashCode() {
        return ((((((this.f672g.hashCode() + ((this.f671f.hashCode() + ((this.f670e.hashCode() + (this.f669d.hashCode() * 31)) * 31)) * 31)) * 31) + (this.f673h ? 1231 : 1237)) * 31) + this.f674i) * 31) + this.f675j;
    }

    public final String toString() {
        x.f698a.getClass();
        return y.a(this);
    }
}
