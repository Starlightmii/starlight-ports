package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.navigationbar.NavigationBarGestureGearEx;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.observer.NavGestureProcessObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarGestureGearExImpl extends NavigationBarGestureGearEx {
    public static final Companion Companion = new Companion();
    private static final int[] DEFAULT_GESTURE_SIDE_WIDTH = {12, 19, 24, 32, 40};
    private static final int[] DEFAULT_GESTURE_SIDE_WIDTH_GEAR = {2, 2, 2, 2};
    private static final String DEFAULT_GESTURE_SIDE_WIDTH_GEAR_STR = "2,2,2,2";
    public static final String NAV_PROGRESS_DIRECTION_IN_LEFT = "inLeft";
    public static final String NAV_PROGRESS_DIRECTION_IN_RIGHT = "inRight";
    public static final String NAV_PROGRESS_DIRECTION_OUT_LEFT = "outLeft";
    public static final String NAV_PROGRESS_DIRECTION_OUT_RIGHT = "outRight";
    private static final String NAV_PROGRESS_GESTURE_GEAR_DEFAULT_VALUE = "12,19,24,32,40";
    private static final String OPLUS_NAV_BAR_GESTURE_GEAR = "oplus_nav_bar_gesture_gear";
    private static final String OPLUS_NAV_BAR_GESTURE_GEAR_VALUE = "oplus_nav_bar_gesture_gear_value";
    private static final String SEPARATE = ",";
    private static final String TAG = "NavigationBarGestureGearExImpl";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private static /* synthetic */ void getDEFAULT_GESTURE_SIDE_WIDTH$annotations() {
        }

        private static /* synthetic */ void getDEFAULT_GESTURE_SIDE_WIDTH_GEAR$annotations() {
        }
    }

    public NavigationBarGestureGearExImpl(Context context) {
        super(context);
        this.context = context;
    }

    public static boolean gearIsLegal(String str) {
        List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{","}, 0, 6);
        if (listSplit$default.size() == 4) {
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                try {
                    int i = Integer.parseInt((String) it.next());
                    if (i < 0 || i >= 5) {
                    }
                } catch (NumberFormatException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public static int[] getNavGestureProgressValueArray(Context context, String str) {
        String[] stringArray;
        String str2;
        int[] iArr = null;
        try {
            stringArray = context.getResources().getStringArray(context.getResources().getIdentifier(OPLUS_NAV_BAR_GESTURE_GEAR_VALUE, "array", "oplus"));
        } catch (Resources.NotFoundException unused) {
            stringArray = null;
        }
        int i = 0;
        if (stringArray == null || stringArray.length == 0 || stringArray.length < 4) {
            stringArray = null;
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            String str3 = stringArray[0];
            String str4 = stringArray[1];
            String str5 = stringArray[2];
            String str6 = stringArray[3];
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("gearValueStrFromConfig =  ", str3, " ,", str4, " ,");
            sbM.append(str5);
            sbM.append(" , ");
            sbM.append(str6);
            Log.d("SystemUi--NavBar", "NavigationBarGestureGearExImpl-->".concat(sbM.toString()));
        }
        String str7 = NAV_PROGRESS_GESTURE_GEAR_DEFAULT_VALUE;
        if (stringArray != null && stringArray.length != 0) {
            switch (str) {
                case "inLeft":
                    str2 = stringArray[2];
                    break;
                case "outLeft":
                    str2 = stringArray[0];
                    break;
                case "outRight":
                    str2 = stringArray[1];
                    break;
                case "inRight":
                    str2 = stringArray[3];
                    break;
                default:
                    str2 = NAV_PROGRESS_GESTURE_GEAR_DEFAULT_VALUE;
                    break;
            }
        } else {
            str2 = null;
        }
        if (str2 != null && str2.length() != 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("featureGearValue = ", str2, "NavigationBarGestureGearExImpl-->", "SystemUi--NavBar");
            }
            str7 = str2;
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "NavigationBarGestureGearExImpl-->getNavGestureProgressValueString = 12,19,24,32,40");
        }
        if (!TextUtils.isEmpty(str7)) {
            String[] strArr = (String[]) StringsKt__StringsKt.split$default(str7, new String[]{","}, 0, 6).toArray(new String[0]);
            int[] iArr2 = new int[5];
            if (strArr.length == 5) {
                int length = strArr.length;
                int i2 = -1;
                while (i < length) {
                    try {
                        int i3 = Integer.parseInt(strArr[i]);
                        if (i3 >= i2 && i3 >= 1 && i3 <= 40) {
                            iArr2[i] = i3;
                            i++;
                            i2 = i3;
                        }
                    } catch (NumberFormatException unused2) {
                    }
                }
                iArr = iArr2;
            }
        }
        return iArr == null ? DEFAULT_GESTURE_SIDE_WIDTH : iArr;
    }

    public static int[] parseGearStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArr = (String[]) StringsKt__StringsKt.split$default(str, new String[]{","}, 0, 6).toArray(new String[0]);
        int[] iArr = new int[4];
        if (strArr.length != 4) {
            return null;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            try {
                int i2 = Integer.parseInt(strArr[i]);
                if (i2 >= 0 && i2 < 5) {
                    iArr[i] = i2;
                }
                return null;
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return iArr;
    }

    public final Context getContext() {
        return this.context;
    }

    public String getNavGestureGear() {
        String string;
        String navGestureProgressGearUserState = NavBarSettingsValueProxy.Companion.getNavGestureProgressGearUserState(this.context);
        if (navGestureProgressGearUserState != null && gearIsLegal(navGestureProgressGearUserState)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("userGear =  ", navGestureProgressGearUserState, "NavigationBarGestureGearExImpl-->", "SystemUi--NavBar");
            }
            return navGestureProgressGearUserState;
        }
        Context context = this.context;
        try {
            string = context.getResources().getString(context.getResources().getIdentifier(OPLUS_NAV_BAR_GESTURE_GEAR, "string", "oplus"));
        } catch (Resources.NotFoundException unused) {
            string = DEFAULT_GESTURE_SIDE_WIDTH_GEAR_STR;
        }
        string.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("strFromConfig =  ", string, "NavigationBarGestureGearExImpl-->", "SystemUi--NavBar");
        }
        if (gearIsLegal(string)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("config Str =  ", string, "NavigationBarGestureGearExImpl-->", "SystemUi--NavBar");
            }
            return string;
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            return DEFAULT_GESTURE_SIDE_WIDTH_GEAR_STR;
        }
        Log.d("SystemUi--NavBar", "NavigationBarGestureGearExImpl-->default Str =  2,2,2,2");
        return DEFAULT_GESTURE_SIDE_WIDTH_GEAR_STR;
    }

    public int getNavGestureLeftGearValue() {
        int[] gearStr = parseGearStr(NavGestureProcessObserver.Companion.getInstance().getNavGestureProgressState());
        if (gearStr == null) {
            gearStr = DEFAULT_GESTURE_SIDE_WIDTH_GEAR;
        }
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isFoldDevice() && DeviceUtil.shouldUseLargeFoldConfig(this.context.getResources())) ? getNavGestureProgressValueArray(this.context, NAV_PROGRESS_DIRECTION_IN_LEFT)[gearStr[2]] : getNavGestureProgressValueArray(this.context, NAV_PROGRESS_DIRECTION_OUT_LEFT)[gearStr[0]];
    }

    public int getNavGestureRightGearValue() {
        int[] gearStr = parseGearStr(NavGestureProcessObserver.Companion.getInstance().getNavGestureProgressState());
        if (gearStr == null) {
            gearStr = DEFAULT_GESTURE_SIDE_WIDTH_GEAR;
        }
        FeatureOption.INSTANCE.getClass();
        return (FeatureOption.isFoldDevice() && DeviceUtil.shouldUseLargeFoldConfig(this.context.getResources())) ? getNavGestureProgressValueArray(this.context, NAV_PROGRESS_DIRECTION_IN_RIGHT)[gearStr[3]] : getNavGestureProgressValueArray(this.context, NAV_PROGRESS_DIRECTION_OUT_RIGHT)[gearStr[1]];
    }
}
