package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import n3.a;
import n3.b;
import n3.c;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        c cVarG = remoteActionCompat.f213a;
        boolean z2 = true;
        if (aVar.e(1)) {
            cVarG = aVar.g();
        }
        remoteActionCompat.f213a = (IconCompat) cVarG;
        CharSequence charSequence = remoteActionCompat.f214b;
        if (aVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f4260e);
        }
        remoteActionCompat.f214b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.f215c;
        if (aVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((b) aVar).f4260e);
        }
        remoteActionCompat.f215c = charSequence2;
        remoteActionCompat.f216d = (PendingIntent) aVar.f(remoteActionCompat.f216d, 4);
        boolean z3 = remoteActionCompat.f217e;
        if (aVar.e(5)) {
            z3 = ((b) aVar).f4260e.readInt() != 0;
        }
        remoteActionCompat.f217e = z3;
        boolean z4 = remoteActionCompat.f218f;
        if (!aVar.e(6)) {
            z2 = z4;
        } else if (((b) aVar).f4260e.readInt() == 0) {
            z2 = false;
        }
        remoteActionCompat.f218f = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, a aVar) {
        aVar.getClass();
        IconCompat iconCompat = remoteActionCompat.f213a;
        aVar.h(1);
        aVar.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.f214b;
        aVar.h(2);
        Parcel parcel = ((b) aVar).f4260e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.f215c;
        aVar.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.f216d;
        aVar.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z2 = remoteActionCompat.f217e;
        aVar.h(5);
        parcel.writeInt(z2 ? 1 : 0);
        boolean z3 = remoteActionCompat.f218f;
        aVar.h(6);
        parcel.writeInt(z3 ? 1 : 0);
    }
}
