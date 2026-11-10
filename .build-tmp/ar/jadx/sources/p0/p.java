package p0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p implements Parcelable.ClassLoaderCreator {
    public static q a(Parcel parcel, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = p.class.getClassLoader();
        }
        int i5 = parcel.readInt();
        if (i5 == 0) {
            return new q();
        }
        j0.g gVarE = j0.j.f3207e.e();
        for (int i6 = 0; i6 < i5; i6++) {
            gVarE.add(parcel.readValue(classLoader));
        }
        return new q(gVarE.c());
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return a(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return a(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i5) {
        return new q[i5];
    }
}
