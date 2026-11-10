package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import io.github.libxposed.service.IXposedService;
import java.lang.reflect.InvocationTargetException;
import s2.a;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f219k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f221b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f229j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f220a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f222c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f223d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f224e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f225f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f226g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f227h = f219k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f228i = null;

    public final String toString() {
        String str;
        int iIntValue;
        if (this.f220a == -1) {
            return String.valueOf(this.f221b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f220a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f220a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f221b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f221b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f229j);
                sb.append(" id=");
                int i5 = this.f220a;
                if (i5 == -1) {
                    Object obj = this.f221b;
                    if (Build.VERSION.SDK_INT >= 28) {
                        iIntValue = a.b(obj);
                    } else {
                        try {
                            iIntValue = ((Integer) obj.getClass().getMethod("getResId", null).invoke(obj, null)).intValue();
                        } catch (IllegalAccessException e5) {
                            Log.e("IconCompat", "Unable to get icon resource", e5);
                            iIntValue = 0;
                        } catch (NoSuchMethodException e6) {
                            Log.e("IconCompat", "Unable to get icon resource", e6);
                            iIntValue = 0;
                        } catch (InvocationTargetException e7) {
                            Log.e("IconCompat", "Unable to get icon resource", e7);
                            iIntValue = 0;
                        }
                    }
                } else {
                    if (i5 != 2) {
                        throw new IllegalStateException("called getResId() on " + this);
                    }
                    iIntValue = this.f224e;
                }
                sb.append(String.format("0x%08x", Integer.valueOf(iIntValue)));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f224e);
                if (this.f225f != 0) {
                    sb.append(" off=");
                    sb.append(this.f225f);
                }
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
            case 6:
                sb.append(" uri=");
                sb.append(this.f221b);
                break;
        }
        if (this.f226g != null) {
            sb.append(" tint=");
            sb.append(this.f226g);
        }
        if (this.f227h != f219k) {
            sb.append(" mode=");
            sb.append(this.f227h);
        }
        sb.append(")");
        return sb.toString();
    }
}
