package com.oplus.systemui.separate;

import android.content.Context;
import android.util.Log;
import androidx.collection.FloatList$;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightLiveAlertCard;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ColumnWidthUtil {
    public static final ColumnWidthUtil INSTANCE = new ColumnWidthUtil();
    public static final String TAG = "ColumnWidthUtil";

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScreenType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ ScreenType[] $VALUES;
        public static final ScreenType LARGE;
        public static final ScreenType MIDDLE;
        public static final ScreenType SMALL;

        static {
            ScreenType screenType = new ScreenType(FlashlightLiveAlertCard.SIZE_SMALL, 0);
            SMALL = screenType;
            ScreenType screenType2 = new ScreenType("MIDDLE", 1);
            MIDDLE = screenType2;
            ScreenType screenType3 = new ScreenType("LARGE", 2);
            LARGE = screenType3;
            ScreenType[] screenTypeArr = {screenType, screenType2, screenType3};
            $VALUES = screenTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(screenTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static ScreenType valueOf(String str) {
            return (ScreenType) Enum.valueOf(ScreenType.class, str);
        }

        public static ScreenType[] values() {
            return (ScreenType[]) $VALUES.clone();
        }
    }

    public static final int dp2px(Context context, float f) {
        float f2 = context.getResources().getDisplayMetrics().density;
        int i = (int) ((f * f2) + 0.5f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ColumnWidthUtil-->".concat("dp2px, scale: " + f2 + " px: " + i));
        }
        return i;
    }

    public static final int getPanelWidth(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131170750);
        if (DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context)) || dimensionPixelSize == -1) {
            int i = context.getResources().getDisplayMetrics().widthPixels;
            int iPx2dp = px2dp(context, i);
            ScreenType screenType = getScreenType(iPx2dp);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("isPortraitInSmallScreen, screenWidth: ", i, iPx2dp, ", ,screenWidthDp: ", ", screenType: ");
                sbM.append(screenType);
                sbM.append(", dimenPanelWidth: ");
                sbM.append(dimensionPixelSize);
                Log.d("SystemUi--Notification", "ColumnWidthUtil-->".concat(sbM.toString()));
            }
            if (screenType == ScreenType.SMALL) {
                return i - (context.getResources().getDimensionPixelSize(2131172246) << 1);
            }
        }
        return dimensionPixelSize - (context.getResources().getDimensionPixelSize(2131170784) << 1);
    }

    public static final ScreenType getScreenType(int i) {
        if (i < 600) {
            return ScreenType.SMALL;
        }
        return i < 840 ? ScreenType.MIDDLE : ScreenType.LARGE;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0047  */
    public static final int getTipMaxWidthPx(Context context) {
        float f;
        float f2;
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int iPx2dp = px2dp(context, i);
        ScreenType screenType = getScreenType(iPx2dp);
        int iOrdinal = screenType.ordinal();
        if (iOrdinal != 0) {
            f = iOrdinal != 1 ? (iPx2dp - 184) / 12.0f : (iPx2dp - 104) / 8.0f;
        } else {
            f = (iPx2dp - 56) / 4.0f;
        }
        int iOrdinal2 = screenType.ordinal();
        if (iOrdinal2 == 0) {
            f2 = (4.0f * f) + 24.0f;
        } else if (iOrdinal2 != 1) {
            f2 = (8.0f * f) + 56.0f;
        } else if (getScreenType(i) == ScreenType.SMALL) {
            f2 = (4.0f * f) + 24.0f;
        } else {
            f2 = (6.0f * f) + 40.0f;
        }
        return dp2px(context, f2);
    }

    public static final int px2dp(Context context, float f) {
        float f2 = context.getResources().getDisplayMetrics().density;
        int i = (int) ((f / f2) + 0.5f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ColumnWidthUtil-->".concat("px2dp, scale: " + f2 + " dp: " + i));
        }
        return i;
    }
}
