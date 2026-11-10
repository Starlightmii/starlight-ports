package com.oplusos.systemui.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import androidx.collection.FloatList$;
import com.android.systemui.qs.external.CustomTile;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IconDrawableUtil {
    public static volatile Field sIconObj1Field;
    public static volatile boolean sIconObj1FieldResolved;
    public static IconDrawableUtil sInstance;
    public ArrayList mResIconDrawables;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ResIconDrawable {
        public WeakReference mHost;
        public Drawable mIconResDrawable;
        public int mIconResId;
        public String mIconResPkg;
        public int mSkipTimes;
        public /* synthetic */ IconDrawableUtil this$0;

        public final String toString() {
            String str;
            if (this.mIconResDrawable == null) {
                return "resIconDrawable_" + hashCode() + "_{No Drawable}";
            }
            StringBuilder sb = new StringBuilder("resIconDrawable_");
            sb.append(hashCode());
            sb.append("_{mIconResId=");
            sb.append(this.mIconResId);
            sb.append(", mIconResPkg=");
            sb.append(this.mIconResPkg);
            sb.append(", mHost=");
            IconDrawableUtil iconDrawableUtil = this.this$0;
            Object obj = this.mHost.get();
            iconDrawableUtil.getClass();
            if (obj == null) {
                str = "Null";
            } else {
                str = obj.getClass().getCanonicalName() + "_" + obj.hashCode();
            }
            sb.append(str);
            sb.append(", mSkipTimes=");
            return FloatList$.ExternalSyntheticOutline0.m(sb, this.mSkipTimes, '}');
        }
    }

    public static void cleanPkgResource(Icon icon) {
        getInstance().getClass();
        cleanPkgResourceForIcon(icon);
    }

    public static void cleanPkgResourceForIcon(Icon icon) {
        if (icon == null || icon.getType() != 2) {
            return;
        }
        try {
            Field iconObj1Field = getIconObj1Field(icon.getClass());
            if (iconObj1Field != null) {
                iconObj1Field.set(icon, null);
            } else {
                LogUtil.w("IconDrawableUtil", "cleanPkgResourceForIcon skip: mObj1 field unavailable");
            }
        } catch (Exception unused) {
        }
    }

    public static Drawable getDrawalbe(Icon icon, Context context, CustomTile customTile) {
        int i;
        Drawable drawable;
        IconDrawableUtil iconDrawableUtil = getInstance();
        iconDrawableUtil.getClass();
        if (icon == null) {
            return null;
        }
        if (icon.getType() != 2) {
            Drawable drawableLoadDrawable = icon.loadDrawable(context);
            cleanPkgResourceForIcon(icon);
            return drawableLoadDrawable;
        }
        ArrayList arrayList = new ArrayList();
        synchronized (iconDrawableUtil.mResIconDrawables) {
            try {
                ArrayList arrayList2 = iconDrawableUtil.mResIconDrawables;
                int size = arrayList2.size();
                i = 0;
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList2.get(i2);
                    i2++;
                    ResIconDrawable resIconDrawable = (ResIconDrawable) obj;
                    if (resIconDrawable.mHost.get() == null) {
                        arrayList.add(resIconDrawable);
                    }
                }
                iconDrawableUtil.mResIconDrawables.removeAll(arrayList);
            } catch (Throwable th) {
                throw th;
            }
        }
        int resId = icon.getResId();
        String resPackage = icon.getResPackage();
        synchronized (iconDrawableUtil.mResIconDrawables) {
            try {
                ArrayList arrayList3 = iconDrawableUtil.mResIconDrawables;
                int size2 = arrayList3.size();
                while (true) {
                    if (i >= size2) {
                        drawable = null;
                        break;
                    }
                    Object obj2 = arrayList3.get(i);
                    i++;
                    ResIconDrawable resIconDrawable2 = (ResIconDrawable) obj2;
                    if (resIconDrawable2.mIconResPkg.equals(icon.getResPackage()) && resIconDrawable2.mIconResId == icon.getResId() && resIconDrawable2.mHost.get() == customTile) {
                        drawable = resIconDrawable2.mIconResDrawable;
                        resIconDrawable2.mSkipTimes++;
                        break;
                    }
                }
                if (drawable == null) {
                    ResIconDrawable resIconDrawable3 = new ResIconDrawable();
                    resIconDrawable3.this$0 = iconDrawableUtil;
                    resIconDrawable3.mHost = new WeakReference(null);
                    VarHandle.storeStoreFence();
                    resIconDrawable3.mIconResId = resId;
                    resIconDrawable3.mIconResPkg = resPackage;
                    resIconDrawable3.mHost = new WeakReference(customTile);
                    Drawable drawableLoadDrawable2 = icon.loadDrawable(context);
                    cleanPkgResourceForIcon(icon);
                    resIconDrawable3.mIconResDrawable = drawableLoadDrawable2;
                    iconDrawableUtil.mResIconDrawables.add(resIconDrawable3);
                    drawable = drawableLoadDrawable2;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return drawable;
    }

    public static Field getIconObj1Field(Class cls) {
        if (sIconObj1Field == null && !sIconObj1FieldResolved) {
            try {
                try {
                    Field declaredField = cls.getDeclaredField("mObj1");
                    declaredField.setAccessible(true);
                    sIconObj1Field = declaredField;
                } catch (NoSuchFieldException unused) {
                    LogUtil.w("IconDrawableUtil", "getIconObj1Field mObj1 not found for ".concat(cls.getName()));
                }
            } finally {
                sIconObj1FieldResolved = true;
            }
        }
        return sIconObj1Field;
    }

    private static synchronized IconDrawableUtil getInstance() {
        try {
            if (sInstance == null) {
                IconDrawableUtil iconDrawableUtil = new IconDrawableUtil();
                iconDrawableUtil.mResIconDrawables = new ArrayList();
                sInstance = iconDrawableUtil;
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }
}
