package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import io.github.libxposed.service.IXposedService;
import java.nio.charset.Charset;
import n3.a;
import n3.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(a aVar) {
        IconCompat iconCompat = new IconCompat();
        int i5 = iconCompat.f220a;
        if (aVar.e(1)) {
            i5 = ((b) aVar).f4260e.readInt();
        }
        iconCompat.f220a = i5;
        byte[] bArr = iconCompat.f222c;
        if (aVar.e(2)) {
            Parcel parcel = ((b) aVar).f4260e;
            int i6 = parcel.readInt();
            if (i6 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i6];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f222c = bArr;
        iconCompat.f223d = aVar.f(iconCompat.f223d, 3);
        int i7 = iconCompat.f224e;
        if (aVar.e(4)) {
            i7 = ((b) aVar).f4260e.readInt();
        }
        iconCompat.f224e = i7;
        int i8 = iconCompat.f225f;
        if (aVar.e(5)) {
            i8 = ((b) aVar).f4260e.readInt();
        }
        iconCompat.f225f = i8;
        iconCompat.f226g = (ColorStateList) aVar.f(iconCompat.f226g, 6);
        String string = iconCompat.f228i;
        if (aVar.e(7)) {
            string = ((b) aVar).f4260e.readString();
        }
        iconCompat.f228i = string;
        String string2 = iconCompat.f229j;
        if (aVar.e(8)) {
            string2 = ((b) aVar).f4260e.readString();
        }
        iconCompat.f229j = string2;
        iconCompat.f227h = PorterDuff.Mode.valueOf(iconCompat.f228i);
        switch (iconCompat.f220a) {
            case -1:
                Parcelable parcelable = iconCompat.f223d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f221b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.f223d;
                if (parcelable2 != null) {
                    iconCompat.f221b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.f222c;
                iconCompat.f221b = bArr3;
                iconCompat.f220a = 3;
                iconCompat.f224e = 0;
                iconCompat.f225f = bArr3.length;
                return iconCompat;
            case 2:
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
            case 6:
                String str = new String(iconCompat.f222c, Charset.forName("UTF-16"));
                iconCompat.f221b = str;
                if (iconCompat.f220a == 2 && iconCompat.f229j == null) {
                    iconCompat.f229j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f221b = iconCompat.f222c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.getClass();
        iconCompat.f228i = iconCompat.f227h.name();
        switch (iconCompat.f220a) {
            case -1:
                iconCompat.f223d = (Parcelable) iconCompat.f221b;
                break;
            case 1:
            case 5:
                iconCompat.f223d = (Parcelable) iconCompat.f221b;
                break;
            case 2:
                iconCompat.f222c = ((String) iconCompat.f221b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f222c = (byte[]) iconCompat.f221b;
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
            case 6:
                iconCompat.f222c = iconCompat.f221b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i5 = iconCompat.f220a;
        if (-1 != i5) {
            aVar.h(1);
            ((b) aVar).f4260e.writeInt(i5);
        }
        byte[] bArr = iconCompat.f222c;
        if (bArr != null) {
            aVar.h(2);
            Parcel parcel = ((b) aVar).f4260e;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f223d;
        if (parcelable != null) {
            aVar.h(3);
            ((b) aVar).f4260e.writeParcelable(parcelable, 0);
        }
        int i6 = iconCompat.f224e;
        if (i6 != 0) {
            aVar.h(4);
            ((b) aVar).f4260e.writeInt(i6);
        }
        int i7 = iconCompat.f225f;
        if (i7 != 0) {
            aVar.h(5);
            ((b) aVar).f4260e.writeInt(i7);
        }
        ColorStateList colorStateList = iconCompat.f226g;
        if (colorStateList != null) {
            aVar.h(6);
            ((b) aVar).f4260e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.f228i;
        if (str != null) {
            aVar.h(7);
            ((b) aVar).f4260e.writeString(str);
        }
        String str2 = iconCompat.f229j;
        if (str2 != null) {
            aVar.h(8);
            ((b) aVar).f4260e.writeString(str2);
        }
    }
}
