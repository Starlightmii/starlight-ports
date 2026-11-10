package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q1 extends p0.y implements Parcelable, p0.n, i1, g3 {
    public static final Parcelable.Creator<q1> CREATOR = new n1(2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public z2 f2294e;

    public q1(long j5) {
        p0.f fVarJ = p0.m.j();
        z2 z2Var = new z2(fVarJ.g(), j5);
        if (!(fVarJ instanceof p0.a)) {
            z2Var.f4768b = new z2(1, j5);
        }
        this.f2294e = z2Var;
    }

    @Override // p0.x
    public final p0.z a() {
        return this.f2294e;
    }

    @Override // p0.x
    public final p0.z b(p0.z zVar, p0.z zVar2, p0.z zVar3) {
        if (((z2) zVar2).f2442c == ((z2) zVar3).f2442c) {
            return zVar2;
        }
        return null;
    }

    @Override // p0.x
    public final void c(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableLongStateImpl.LongStateStateRecord", zVar);
        this.f2294e = (z2) zVar;
    }

    @Override // p0.n
    public final b3 d() {
        return h.f2189j;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final void g(long j5) {
        p0.f fVarJ;
        z2 z2Var = (z2) p0.m.h(this.f2294e);
        if (z2Var.f2442c != j5) {
            z2 z2Var2 = this.f2294e;
            synchronized (p0.m.f4719c) {
                fVarJ = p0.m.j();
                ((z2) p0.m.o(z2Var2, this, fVarJ, z2Var)).f2442c = j5;
            }
            p0.m.n(fVarJ, this);
        }
    }

    @Override // f0.g3
    public Object getValue() {
        return Long.valueOf(((z2) p0.m.t(this.f2294e, this)).f2442c);
    }

    @Override // f0.i1
    public void setValue(Object obj) {
        g(((Number) obj).longValue());
    }

    public final String toString() {
        return "MutableLongState(value=" + ((z2) p0.m.h(this.f2294e)).f2442c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeLong(((z2) p0.m.t(this.f2294e, this)).f2442c);
    }
}
