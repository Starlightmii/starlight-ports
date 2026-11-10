package f0;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r1 implements Parcelable.ClassLoaderCreator {
    public static s1 a(Parcel parcel, ClassLoader classLoader) {
        h hVar;
        if (classLoader == null) {
            classLoader = r1.class.getClassLoader();
        }
        Object value = parcel.readValue(classLoader);
        int i5 = parcel.readInt();
        if (i5 == 0) {
            hVar = h.f2186g;
        } else if (i5 == 1) {
            hVar = h.f2189j;
        } else {
            if (i5 != 2) {
                throw new IllegalStateException("Unsupported MutableState policy " + i5 + " was restored");
            }
            hVar = h.f2187h;
        }
        return new s1(value, hVar);
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
        return new s1[i5];
    }
}
