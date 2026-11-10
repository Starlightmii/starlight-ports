package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p1 extends p0.y implements Parcelable, h1, p0.n {
    public static final Parcelable.Creator<p1> CREATOR = new n1(1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y2 f2287e;

    public p1(int i5) {
        p0.f fVarJ = p0.m.j();
        y2 y2Var = new y2(fVarJ.g(), i5);
        if (!(fVarJ instanceof p0.a)) {
            y2Var.f4768b = new y2(1, i5);
        }
        this.f2287e = y2Var;
    }

    @Override // p0.x
    public final p0.z a() {
        return this.f2287e;
    }

    @Override // p0.x
    public final p0.z b(p0.z zVar, p0.z zVar2, p0.z zVar3) {
        if (((y2) zVar2).f2428c == ((y2) zVar3).f2428c) {
            return zVar2;
        }
        return null;
    }

    @Override // p0.x
    public final void c(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord", zVar);
        this.f2287e = (y2) zVar;
    }

    @Override // p0.n
    public final b3 d() {
        return h.f2189j;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final int g() {
        return ((y2) p0.m.t(this.f2287e, this)).f2428c;
    }

    public final void h(int i5) {
        p0.f fVarJ;
        y2 y2Var = (y2) p0.m.h(this.f2287e);
        if (y2Var.f2428c != i5) {
            y2 y2Var2 = this.f2287e;
            synchronized (p0.m.f4719c) {
                fVarJ = p0.m.j();
                ((y2) p0.m.o(y2Var2, this, fVarJ, y2Var)).f2428c = i5;
            }
            p0.m.n(fVarJ, this);
        }
    }

    public final String toString() {
        return "MutableIntState(value=" + ((y2) p0.m.h(this.f2287e)).f2428c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(g());
    }
}
