package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n1 implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2280a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f2280a) {
            case 0:
                return new o1(parcel.readFloat());
            case 1:
                return new p1(parcel.readInt());
            default:
                return new q1(parcel.readLong());
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i5) {
        switch (this.f2280a) {
            case 0:
                return new o1[i5];
            case 1:
                return new p1[i5];
            default:
                return new q1[i5];
        }
    }
}
