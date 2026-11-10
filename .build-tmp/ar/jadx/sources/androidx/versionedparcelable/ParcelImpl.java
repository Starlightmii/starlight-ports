package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import e.a;
import n3.b;
import n3.c;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new a(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f307d;

    public ParcelImpl(Parcel parcel) {
        this.f307d = new b(parcel).g();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        new b(parcel).i(this.f307d);
    }
}
