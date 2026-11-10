package com.oplus.systemui.statusbar.pipeline.wifi.data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.VectorDrawable;
import androidx.appcompat.content.res.AppCompatResources;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiIconSets {
    private static final String TAG = "OplusWifiIconSets";
    public static final OplusWifiIconSets INSTANCE = new OplusWifiIconSets();
    private static final int[] WIFI_SIGNAL_O16 = {2131236921, 2131236923, 2131236925, 2131236927, 2131236929};
    private static final int[] WIFI_SIGNAL_O17 = {2131236922, 2131236924, 2131236926, 2131236928, 2131236930};
    private static final int[] WIFI_ACTIVITY_O16 = {2131236760, 2131236762, 2131236764, 2131236766};
    private static final int[] WIFI_ACTIVITY_O17 = {2131236761, 2131236763, 2131236765, 2131236767};
    private static final int[] WIFI_SMALL_DOUBLE_O16 = {2131236916};
    private static final int[] WIFI_SMALL_DOUBLE_O17 = {2131236917};
    private static final Lazy wifiSmallOs16ToOs17Map$delegate = LazyKt__LazyJVMKt.lazy(new OplusWifiIconSets$$ExternalSyntheticLambda0());

    public static Map $r8$lambda$Xf85lOrMXMw1P0t30h6CfMb9flg() {
        Map mapCreateMapBuilder = MapsKt__MapsJVMKt.createMapBuilder();
        int[] iArr = WIFI_ACTIVITY_O16;
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            mapCreateMapBuilder.put(Integer.valueOf(iArr[i2]), Integer.valueOf(WIFI_ACTIVITY_O17[i3]));
            i2++;
            i3++;
        }
        int[] iArr2 = WIFI_SMALL_DOUBLE_O16;
        int length2 = iArr2.length;
        int i4 = 0;
        while (i < length2) {
            mapCreateMapBuilder.put(Integer.valueOf(iArr2[i]), Integer.valueOf(WIFI_SMALL_DOUBLE_O17[i4]));
            i++;
            i4++;
        }
        return MapsKt__MapsJVMKt.build(mapCreateMapBuilder);
    }

    private final Map<Integer, Integer> getWifiSmallOs16ToOs17Map() {
        return (Map) wifiSmallOs16ToOs17Map$delegate.getValue();
    }

    public static boolean isThemeDrawable(int i, Context context) {
        try {
            return !(AppCompatResources.getDrawable(context, i) instanceof VectorDrawable);
        } catch (Resources.NotFoundException e) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "isThemeDrawable failed, resId=" + i + ", " + e);
            return false;
        }
    }

    public final int[] getWIFI_ACTIVITY_O16() {
        return WIFI_ACTIVITY_O16;
    }

    public final int[] getWIFI_ACTIVITY_O17() {
        return WIFI_ACTIVITY_O17;
    }

    public final int[] getWIFI_SIGNAL_O16() {
        return WIFI_SIGNAL_O16;
    }

    public final int[] getWIFI_SIGNAL_O17() {
        return WIFI_SIGNAL_O17;
    }

    public final int[] getWIFI_SMALL_DOUBLE_O16() {
        return WIFI_SMALL_DOUBLE_O16;
    }

    public final int[] getWIFI_SMALL_DOUBLE_O17() {
        return WIFI_SMALL_DOUBLE_O17;
    }

    public final boolean hasThirdPictureWifiSignalO16(Context context) {
        List listListOf = CollectionsKt__CollectionsKt.listOf(new Integer[]{Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_SIGNAL_O16)), Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_ACTIVITY_O16)), Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_SMALL_DOUBLE_O16))});
        if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
            return false;
        }
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            INSTANCE.getClass();
            if (isThemeDrawable(iIntValue, context)) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasThirdPictureWifiSignalO17(Context context) {
        List listListOf = CollectionsKt__CollectionsKt.listOf(new Integer[]{Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_SIGNAL_O17)), Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_ACTIVITY_O17)), Integer.valueOf(ArraysKt___ArraysKt.first(WIFI_SMALL_DOUBLE_O17))});
        if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
            return false;
        }
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            INSTANCE.getClass();
            if (isThemeDrawable(iIntValue, context)) {
                return true;
            }
        }
        return false;
    }

    public final int mapWifiSmallIconRes(int i, boolean z) {
        Integer num;
        return (z && (num = getWifiSmallOs16ToOs17Map().get(Integer.valueOf(i))) != null) ? num.intValue() : i;
    }
}
