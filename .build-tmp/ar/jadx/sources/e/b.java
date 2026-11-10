package e;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a(0);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1641d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Intent f1642e;

    public b(Intent intent, int i5) {
        this.f1641d = i5;
        this.f1642e = intent;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        String strValueOf;
        StringBuilder sb = new StringBuilder("ActivityResult{resultCode=");
        int i5 = this.f1641d;
        if (i5 != -1) {
            strValueOf = i5 != 0 ? String.valueOf(i5) : "RESULT_CANCELED";
        } else {
            strValueOf = "RESULT_OK";
        }
        sb.append(strValueOf);
        sb.append(", data=");
        sb.append(this.f1642e);
        sb.append('}');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        l.e("dest", parcel);
        parcel.writeInt(this.f1641d);
        Intent intent = this.f1642e;
        parcel.writeInt(intent == null ? 0 : 1);
        if (intent != null) {
            intent.writeToParcel(parcel, i5);
        }
    }
}
