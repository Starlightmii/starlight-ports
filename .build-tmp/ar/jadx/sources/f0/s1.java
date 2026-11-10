package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s1 extends p0.y implements Parcelable, p0.n {
    public static final Parcelable.Creator<s1> CREATOR = new r1();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b3 f2303e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a3 f2304f;

    public s1(Object obj, b3 b3Var) {
        this.f2303e = b3Var;
        p0.f fVarJ = p0.m.j();
        a3 a3Var = new a3(fVarJ.g(), obj);
        if (!(fVarJ instanceof p0.a)) {
            a3Var.f4768b = new a3(1, obj);
        }
        this.f2304f = a3Var;
    }

    @Override // p0.x
    public final p0.z a() {
        return this.f2304f;
    }

    @Override // p0.x
    public final p0.z b(p0.z zVar, p0.z zVar2, p0.z zVar3) {
        if (this.f2303e.a(((a3) zVar2).f2090c, ((a3) zVar3).f2090c)) {
            return zVar2;
        }
        return null;
    }

    @Override // p0.x
    public final void c(p0.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutableStateImpl.StateStateRecord<T of androidx.compose.runtime.SnapshotMutableStateImpl>", zVar);
        this.f2304f = (a3) zVar;
    }

    @Override // p0.n
    public final b3 d() {
        return this.f2303e;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // f0.g3
    public final Object getValue() {
        return ((a3) p0.m.t(this.f2304f, this)).f2090c;
    }

    @Override // f0.i1
    public final void setValue(Object obj) {
        p0.f fVarJ;
        a3 a3Var = (a3) p0.m.h(this.f2304f);
        if (this.f2303e.a(a3Var.f2090c, obj)) {
            return;
        }
        a3 a3Var2 = this.f2304f;
        synchronized (p0.m.f4719c) {
            fVarJ = p0.m.j();
            ((a3) p0.m.o(a3Var2, this, fVarJ, a3Var)).f2090c = obj;
        }
        p0.m.n(fVarJ, this);
    }

    public final String toString() {
        return "MutableState(value=" + ((a3) p0.m.h(this.f2304f)).f2090c + ")@" + hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        int i6;
        parcel.writeValue(getValue());
        h hVar = h.f2186g;
        b3 b3Var = this.f2303e;
        if (b4.l.a(b3Var, hVar)) {
            i6 = 0;
        } else if (b4.l.a(b3Var, h.f2189j)) {
            i6 = 1;
        } else {
            if (!b4.l.a(b3Var, h.f2187h)) {
                throw new IllegalStateException("Only known types of MutableState's SnapshotMutationPolicy are supported");
            }
            i6 = 2;
        }
        parcel.writeInt(i6);
    }
}
