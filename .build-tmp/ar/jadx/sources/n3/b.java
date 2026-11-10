package n3;

import android.os.Parcel;
import android.util.SparseIntArray;
import j.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f4259d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f4260e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4261f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4262g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f4263h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4264i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4265j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4266k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new f(0), new f(0), new f(0));
    }

    public b(Parcel parcel, int i5, int i6, String str, f fVar, f fVar2, f fVar3) {
        super(fVar, fVar2, fVar3);
        this.f4259d = new SparseIntArray();
        this.f4264i = -1;
        this.f4266k = -1;
        this.f4260e = parcel;
        this.f4261f = i5;
        this.f4262g = i6;
        this.f4265j = i5;
        this.f4263h = str;
    }

    @Override // n3.a
    public final b a() {
        Parcel parcel = this.f4260e;
        int iDataPosition = parcel.dataPosition();
        int i5 = this.f4265j;
        if (i5 == this.f4261f) {
            i5 = this.f4262g;
        }
        return new b(parcel, iDataPosition, i5, this.f4263h + "  ", this.f4256a, this.f4257b, this.f4258c);
    }

    @Override // n3.a
    public final boolean e(int i5) {
        while (this.f4265j < this.f4262g) {
            int i6 = this.f4266k;
            if (i6 == i5) {
                return true;
            }
            if (String.valueOf(i6).compareTo(String.valueOf(i5)) > 0) {
                return false;
            }
            int i7 = this.f4265j;
            Parcel parcel = this.f4260e;
            parcel.setDataPosition(i7);
            int i8 = parcel.readInt();
            this.f4266k = parcel.readInt();
            this.f4265j += i8;
        }
        return this.f4266k == i5;
    }

    @Override // n3.a
    public final void h(int i5) {
        int i6 = this.f4264i;
        SparseIntArray sparseIntArray = this.f4259d;
        Parcel parcel = this.f4260e;
        if (i6 >= 0) {
            int i7 = sparseIntArray.get(i6);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i7);
            parcel.writeInt(iDataPosition - i7);
            parcel.setDataPosition(iDataPosition);
        }
        this.f4264i = i5;
        sparseIntArray.put(i5, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i5);
    }
}
