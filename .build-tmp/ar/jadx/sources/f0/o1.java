package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o1 extends p0.y implements Parcelable, p0.n, i1, g3 {
    public static final Parcelable.Creator<o1> CREATOR = new n1(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public x2 f2284e;

    public o1(float f5) {
        p0.f fVarJ = p0.m.j();
        x2 x2Var = new x2(f5, fVarJ.g());
        if (!(fVarJ instanceof p0.a)) {
            x2Var.f4768b = new x2(f5, 1);
        }
        this.f2284e = x2Var;
    }

    @Override // p0.x
    public final p0.z a() {
        return this.f2284e;
    }

    @Override // p0.x
    public final p0.z b(p0.z zVar, p0.z zVar2, p0.z zVar3) {
        if (((x2) zVar2).f2419c == ((x2) zVar3).f2419c) {
            return zVar2;
        }
        return null;
    }

    @Override // p0.x
    public final void c(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableFloatStateImpl.FloatStateStateRecord", zVar);
        this.f2284e = (x2) zVar;
    }

    @Override // p0.n
    public final b3 d() {
        return h.f2189j;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final float g() {
        return ((x2) p0.m.t(this.f2284e, this)).f2419c;
    }

    @Override // f0.g3
    public Object getValue() {
        return Float.valueOf(g());
    }

    public final void h(float f5) {
        p0.f fVarJ;
        x2 x2Var = (x2) p0.m.h(this.f2284e);
        if (x2Var.f2419c == f5) {
            return;
        }
        x2 x2Var2 = this.f2284e;
        synchronized (p0.m.f4719c) {
            fVarJ = p0.m.j();
            ((x2) p0.m.o(x2Var2, this, fVarJ, x2Var)).f2419c = f5;
        }
        p0.m.n(fVarJ, this);
    }

    @Override // f0.i1
    public void setValue(Object obj) {
        h(((Number) obj).floatValue());
    }

    public final String toString() {
        return "MutableFloatState(value=" + ((x2) p0.m.h(this.f2284e)).f2419c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeFloat(g());
    }
}
