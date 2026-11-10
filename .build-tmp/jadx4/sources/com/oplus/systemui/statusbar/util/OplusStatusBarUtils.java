package com.oplus.systemui.statusbar.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.DeadSystemRuntimeException;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.RoundedCorner;
import android.view.RoundedCorners;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.common.settings.others.ThemeBundleUtils;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.keyguard.helper.ScreenResolutionHelper;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.transition.OplusAnimationController;
import com.oplus.util.OplusDarkModeUtil;
import com.oplus.zoom.common.ZoomUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.RomVersionUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarUtils {
    private static final String ROUND_CORNER_PROPERTY_OPLUS = "ro.oplus.display.rc.size";
    private static final String ROUND_SECONDARY_CORNER_PROPERTY_OPLUS = "ro.oplus.display.secondary.rc.size";
    private static final String RO_OPLUS_SCREEN_HOLE_POSITION = "ro.oplus.display.screenhole.positon";
    private static final String RO_OPLUS_SECONDARY_SCREEN_HOLE_POSITION = "ro.oplus.display.secondary.screenhole.position";
    private static final String TAG = "OplusStatusBarUtils";
    private static final Lazy propertiesCornerList$delegate;
    private static final Lazy secondaryPropertiesCornerList$delegate;
    public static final OplusStatusBarUtils INSTANCE = new OplusStatusBarUtils();
    private static final ArrayList<Integer> propertiesHoleList = new ArrayList<>();
    private static final ArrayList<Integer> secondaryPropertiesHoleList = new ArrayList<>();
    private static final HolePosition holePosition = HolePosition.INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HolePosition implements ConfigurationController.ConfigurationListener {
        public static final HolePosition INSTANCE = new HolePosition();
        public static boolean isHoleCenter = false;
        public static boolean isHoleRight = false;
        public static boolean isInit = false;
        public static int lastHoleLeft = 0;
        public static int screenSize = 1080;

        public static final void dump(PrintWriter printWriter) {
            if (printWriter != null) {
                printWriter.println(OplusStatusBarUtils.TAG);
            }
            if (printWriter != null) {
                INSTANCE.getClass();
                boolean zIsHoleCenter = isHoleCenter();
                boolean zIsHoleRight = isHoleRight();
                int i = lastHoleLeft;
                int i2 = screenSize;
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m(" isHoleCenter:", " isHoleRight:", " lastHoleLeft:", zIsHoleCenter, zIsHoleRight);
                sbM.append(i);
                sbM.append(" screenSize:");
                sbM.append(i2);
                printWriter.println(sbM.toString());
            }
            if (printWriter != null) {
                printWriter.println();
            }
        }

        public static boolean isHoleCenter() {
            if (!isInit) {
                isInit = true;
                updateHolePosition(null);
                ConfigurationController configController = OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getConfigController();
                if (configController != null) {
                    configController.addCallback(INSTANCE);
                }
            }
            if (OplusStatusBarUtils.getScreenHoleParam(0) != lastHoleLeft) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->isHoleCenter lastHoleLeft has changed");
                }
                updateHolePosition(null);
            }
            return isHoleCenter;
        }

        public static boolean isHoleRight() {
            if (OplusStatusBarUtils.getScreenHoleParam(0) != lastHoleLeft) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->isHoleRight lastHoleLeft has changed");
                }
                updateHolePosition(null);
            }
            return isHoleRight;
        }

        public static final void updateHolePosition(Rect rect) {
            int stableDisplayWidthSize;
            Display defaultDisplay;
            boolean z = OplusStatusBarUtils.getScreenHoleHeight() > 0;
            lastHoleLeft = OplusStatusBarUtils.getScreenHoleParam(0);
            if (!z) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->it is not holeScreen");
                }
                isHoleCenter = false;
                isHoleRight = false;
                return;
            }
            Context context = OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getContext();
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled()) {
                stableDisplayWidthSize = ScreenSizeHelper.Companion.getInstance(context).getStableDisplayWidthSize(context);
            } else if (rect == null || rect.width() <= 0) {
                DisplayInfo displayInfo = new DisplayInfo();
                WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
                if (windowManager != null && (defaultDisplay = windowManager.getDefaultDisplay()) != null) {
                    defaultDisplay.getDisplayInfo(displayInfo);
                }
                stableDisplayWidthSize = displayInfo.getNaturalWidth();
            } else {
                stableDisplayWidthSize = rect.width();
            }
            screenSize = stableDisplayWidthSize;
            int i = lastHoleLeft;
            int screenHoleWidth = OplusStatusBarUtils.getScreenHoleWidth();
            int i2 = screenSize;
            isHoleRight = i - (i2 / 2) > 5;
            isHoleCenter = Math.abs(((i2 - screenHoleWidth) / 2) - i) <= 5;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                int i3 = screenSize;
                INSTANCE.getClass();
                boolean zIsHoleRight = isHoleRight();
                boolean zIsHoleCenter = isHoleCenter();
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("updateHolePosition: screenSize = ", i3, screenHoleWidth, ", holeWidth = ", ", holeLeft = ");
                ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i, ", isHoleRight = ", ", isHoleCenter = ", sbM, zIsHoleRight);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusStatusBarUtils-->", "SystemUi--Statusbar", sbM, zIsHoleCenter);
            }
        }

        public void onConfigChanged(Configuration configuration) {
            updateHolePosition(null);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HolePositionType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ HolePositionType[] $VALUES;
        public static final HolePositionType HOLE_CENTER_TYPE;
        public static final HolePositionType HOLE_LEFT_TYPE;
        public static final HolePositionType HOLE_RIGHT_TYPE;
        public static final HolePositionType NO_HOLE_TYPE;

        static {
            HolePositionType holePositionType = new HolePositionType("HOLE_LEFT_TYPE", 0);
            HOLE_LEFT_TYPE = holePositionType;
            HolePositionType holePositionType2 = new HolePositionType("HOLE_RIGHT_TYPE", 1);
            HOLE_RIGHT_TYPE = holePositionType2;
            HolePositionType holePositionType3 = new HolePositionType("HOLE_CENTER_TYPE", 2);
            HOLE_CENTER_TYPE = holePositionType3;
            HolePositionType holePositionType4 = new HolePositionType("NO_HOLE_TYPE", 3);
            NO_HOLE_TYPE = holePositionType4;
            HolePositionType[] holePositionTypeArr = {holePositionType, holePositionType2, holePositionType3, holePositionType4};
            $VALUES = holePositionTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(holePositionTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static HolePositionType valueOf(String str) {
            return (HolePositionType) Enum.valueOf(HolePositionType.class, str);
        }

        public static HolePositionType[] values() {
            return (HolePositionType[]) $VALUES.clone();
        }
    }

    static {
        final int i = 0;
        propertiesCornerList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.util.OplusStatusBarUtils$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        ArrayList arrayList = new ArrayList();
                        OplusStatusBarUtils.initializeCornerList("ro.oplus.display.rc.size", arrayList);
                        return arrayList;
                    default:
                        ArrayList arrayList2 = new ArrayList();
                        OplusStatusBarUtils.initializeCornerList("ro.oplus.display.secondary.rc.size", arrayList2);
                        return arrayList2;
                }
            }
        });
        final int i2 = 1;
        secondaryPropertiesCornerList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.util.OplusStatusBarUtils$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        ArrayList arrayList = new ArrayList();
                        OplusStatusBarUtils.initializeCornerList("ro.oplus.display.rc.size", arrayList);
                        return arrayList;
                    default:
                        ArrayList arrayList2 = new ArrayList();
                        OplusStatusBarUtils.initializeCornerList("ro.oplus.display.secondary.rc.size", arrayList2);
                        return arrayList2;
                }
            }
        });
    }

    public static final void dump(PrintWriter printWriter) {
        HolePosition.dump(printWriter);
    }

    public static final String getContextDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return " context:" + context + " density " + displayMetrics.density + " densityDpi  " + displayMetrics.densityDpi + " scaledDensity  " + displayMetrics.scaledDensity;
    }

    public static final int getCornerRadius(Context context, int i, float f) {
        int iFloatValue;
        FeatureOption.INSTANCE.getClass();
        ArrayList<Integer> propertiesCornerList = (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled() || DeviceStateController.getInstance().isOpenOrHalfOpen()) ? INSTANCE.getPropertiesCornerList() : INSTANCE.getSecondaryPropertiesCornerList();
        if (propertiesCornerList.size() >= 4) {
            iFloatValue = (int) (propertiesCornerList.get(i).floatValue() * f);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                StringBuilder sb = new StringBuilder("old product resultRadius:");
                sb.append(iFloatValue);
                sb.append(",cornerList:");
                sb.append(propertiesCornerList);
                sb.append(",percentScreen: ");
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f, "OplusStatusBarUtils-->", "SystemUi--Statusbar");
            }
        } else {
            iFloatValue = 0;
        }
        if (iFloatValue != 0) {
            return iFloatValue;
        }
        DisplayInfo displayInfo = new DisplayInfo();
        context.getDisplay().getDisplayInfo(displayInfo);
        RoundedCorners roundedCorners = displayInfo.roundedCorners;
        RoundedCorner roundedCorner = roundedCorners != null ? roundedCorners.getRoundedCorner(i) : null;
        int radius = roundedCorner != null ? roundedCorner.getRadius() : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->".concat("new product roundedCorner:" + roundedCorner + ",resultRadius:" + radius));
        }
        return radius;
    }

    public static final StringBuilder getImageViewStringBuilder(StringBuilder sb, ImageView imageView) {
        Resources resources;
        DisplayMetrics displayMetrics;
        Resources resources2;
        DisplayMetrics displayMetrics2;
        Resources resources3;
        DisplayMetrics displayMetrics3;
        Drawable drawable;
        Drawable drawable2;
        sb.append("size:[");
        Integer numValueOf = null;
        sb.append((imageView == null || (drawable2 = imageView.getDrawable()) == null) ? null : Integer.valueOf(drawable2.getIntrinsicWidth()));
        sb.append(OplusPreventModeData.SEPARATOR);
        sb.append((imageView == null || (drawable = imageView.getDrawable()) == null) ? null : Integer.valueOf(drawable.getIntrinsicHeight()));
        sb.append("]");
        sb.append(" scaledDensity:");
        sb.append((imageView == null || (resources3 = imageView.getResources()) == null || (displayMetrics3 = resources3.getDisplayMetrics()) == null) ? null : Float.valueOf(displayMetrics3.scaledDensity));
        sb.append(" density:");
        sb.append((imageView == null || (resources2 = imageView.getResources()) == null || (displayMetrics2 = resources2.getDisplayMetrics()) == null) ? null : Float.valueOf(displayMetrics2.density));
        sb.append(" densityDpi:");
        if (imageView != null && (resources = imageView.getResources()) != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            numValueOf = Integer.valueOf(displayMetrics.densityDpi);
        }
        sb.append(numValueOf);
        return sb;
    }

    private final ArrayList<Integer> getPropertiesCornerList() {
        return (ArrayList) propertiesCornerList$delegate.getValue();
    }

    public static final String getResourceName(int i, Context context) {
        if (i == 0) {
            return "(null)";
        }
        try {
            String resourceName = context.getResources().getResourceName(i);
            resourceName.getClass();
            return resourceName;
        } catch (Resources.NotFoundException unused) {
            return "(unknown)";
        }
    }

    public static final int getScreenHoleHeight(Context context) {
        return getScreenHoleParam(context, 3) - getScreenHoleParam(context, 1);
    }

    public static final int getScreenHoleParam(ArrayList arrayList, String str, int i) {
        if (arrayList.size() >= 4) {
            Integer num = (Integer) CollectionsKt___CollectionsKt.getOrNull(arrayList, i);
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
        synchronized (arrayList) {
            if (arrayList.size() < 4) {
                INSTANCE.getClass();
                loadScreenHoleData(str, arrayList);
            }
        }
        Integer num2 = (Integer) CollectionsKt___CollectionsKt.getOrNull(arrayList, i);
        if (num2 != null) {
            return num2.intValue();
        }
        return -1;
    }

    public static final Rect getScreenHoleRect(Context context) {
        return new Rect(getScreenHoleParam(context, 0), getScreenHoleParam(context, 1), getScreenHoleParam(context, 2), getScreenHoleParam(context, 3));
    }

    public static final int getScreenHoleWidth(Context context) {
        return getScreenHoleParam(context, 2) - getScreenHoleParam(context, 0);
    }

    private final ArrayList<Integer> getSecondaryPropertiesCornerList() {
        return (ArrayList) secondaryPropertiesCornerList$delegate.getValue();
    }

    public static final int getStatusBarHeight(Context context) {
        return SystemBarUtils.getStatusBarHeight(context);
    }

    public static final int holePosition() {
        holePosition.getClass();
        if (HolePosition.isHoleCenter()) {
            return 2;
        }
        if (HolePosition.isHoleRight()) {
            return 3;
        }
        return getScreenHoleHeight() > 0 ? 1 : 0;
    }

    public static final void initializeCornerList(String str, ArrayList arrayList) {
        String str2 = SystemProperties.get(str, "");
        if (str2.length() > 0) {
            List listSplit$default = StringsKt__StringsKt.split$default(str2, new char[]{','}, 6);
            if (listSplit$default.size() >= 4) {
                try {
                    Iterator it = listSplit$default.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Integer.valueOf(Integer.parseInt((String) it.next())));
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        String str3 = LogUtil.TAG + LogUtil.TAG_STATUSBAR;
                        StringBuilder sb = new StringBuilder();
                        sb.append(TAG);
                        sb.append(LogUtil.SEPARATOR);
                        sb.append("lazy initialized cornerList: " + arrayList + " for property: " + str);
                        Log.d(str3, sb.toString());
                    }
                } catch (NumberFormatException unused) {
                    LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "initializeCornerList, error for property: ".concat(str));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x006b  */
    public static final boolean isAdaptiveStatusBar(Context context) {
        boolean z;
        ArrayList<Integer> arrayList;
        String str;
        if (getScreenHoleHeight(context) > 0) {
            ArrayList<Integer> arrayList2 = propertiesHoleList;
            FeatureOption featureOption = FeatureOption.INSTANCE;
            featureOption.getClass();
            boolean zIsFoldDevice = FeatureOption.isFoldDevice();
            String str2 = RO_OPLUS_SCREEN_HOLE_POSITION;
            if (!zIsFoldDevice || FeatureOption.isFoldRemapDisabled()) {
                arrayList = arrayList2;
                str = RO_OPLUS_SCREEN_HOLE_POSITION;
            } else {
                arrayList = secondaryPropertiesHoleList;
                str = RO_OPLUS_SECONDARY_SCREEN_HOLE_POSITION;
            }
            boolean z2 = getScreenHoleParam(arrayList, str, 0) >= 300;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                featureOption.getClass();
                if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
                    arrayList2 = secondaryPropertiesHoleList;
                    str2 = RO_OPLUS_SECONDARY_SCREEN_HOLE_POSITION;
                }
                Log.d("SystemUi--Statusbar", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("isAdaptiveCenterHole: centerHole=", getScreenHoleParam(arrayList2, str2, 0), "  getSmallScreenHoleParam(LEFT)=", "OplusStatusBarUtils-->", z2));
            }
            if (z2) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        boolean zIsEarScreen = NotificationFeatureOption.isEarScreen();
        boolean z3 = !zIsEarScreen;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("isAdaptiveStatusBar: notCenterHoleScreen = ", ", fullScreen = ", "OplusStatusBarUtils-->", z, z3));
        }
        return z || !zIsEarScreen;
    }

    public static final boolean isBooleanMedaValid(Context context, String str, String str2) {
        boolean z = false;
        try {
            z = context.getPackageManager().getApplicationInfo(str2, 128).metaData.getBoolean(str, false);
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                return z;
            }
            Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->".concat("isPackageProcessAlive " + str2 + " " + z));
            return z;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "isBooleanMedaValid " + str2 + " " + e);
            return z;
        }
    }

    public static final boolean isCenterHole() {
        holePosition.getClass();
        return HolePosition.isHoleCenter();
    }

    public static final boolean isHoleRight() {
        holePosition.getClass();
        return HolePosition.isHoleRight();
    }

    public static final HolePositionType isHoleType(Context context) {
        HolePositionType holePositionType;
        int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(context).getStableDisplayWidth();
        int i = stableDisplayWidth / 2;
        int screenHoleParam = getScreenHoleParam(context, 0);
        int screenHoleParam2 = getScreenHoleParam(context, 2);
        if (screenHoleParam < 0 || screenHoleParam2 < 0 || screenHoleParam >= screenHoleParam2) {
            return HolePositionType.NO_HOLE_TYPE;
        }
        if (screenHoleParam >= i || screenHoleParam2 <= i) {
            holePositionType = screenHoleParam2 <= i ? HolePositionType.HOLE_LEFT_TYPE : HolePositionType.HOLE_RIGHT_TYPE;
        } else {
            holePositionType = HolePositionType.HOLE_CENTER_TYPE;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("isHoleType stableDisplayWidth: ", stableDisplayWidth, screenHoleParam, ", holeLeftPosition ", ",holeRightPosition ");
            sbM.append(screenHoleParam2);
            sbM.append(" ,holeType ");
            sbM.append(holePositionType);
            Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->".concat(sbM.toString()));
        }
        return holePositionType;
    }

    public static final boolean isLight(Context context) {
        float[] fArr = new float[3];
        Color.colorToHSV(context.getColor(2131102929), fArr);
        return fArr[2] >= 0.85f && fArr[1] <= 0.2f;
    }

    public static final boolean isPackageProcessAlive(Context context, String str) {
        try {
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
            while (it.hasNext()) {
                String[] strArr = it.next().pkgList;
                if (strArr != null && strArr.length != 0) {
                    Iterator it2 = ArrayIteratorKt.iterator(strArr);
                    while (it2.hasNext()) {
                        if (((String) it2.next()).equals(str)) {
                            return true;
                        }
                    }
                }
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "isPackageProcessAlive pkgList is isEmpty");
            }
            return false;
        } catch (DeadSystemRuntimeException e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "isPackageProcessAlive " + str + " " + e);
            return false;
        }
    }

    public static void loadScreenHoleData(String str, ArrayList arrayList) {
        try {
            String str2 = SystemProperties.get(str, "");
            List listSplit$default = StringsKt__StringsKt.split$default(str2, new char[]{':', ','}, 6);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : listSplit$default) {
                if (((String) obj).length() > 0) {
                    arrayList2.add(obj);
                }
            }
            if (arrayList2.size() >= 4) {
                arrayList.clear();
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull((String) obj2);
                    if (intOrNull != null) {
                        arrayList.add(Integer.valueOf(intOrNull.intValue()));
                    }
                }
            }
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "loadScreenHoleData: " + str + " = " + str2 + ", holeList = " + arrayList, null, 8);
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "loadScreenHoleData: NPE when parsing", e);
            arrayList.clear();
        }
    }

    public static final void setDarkStatusIcon(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        ThemeBundleUtils.Companion.getClass();
        boolean z = ThemeBundleUtils.sDarkMode;
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return;
        }
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(!z ? systemUiVisibility | 8208 : systemUiVisibility & (-8209));
    }

    public static final void setFpQLStatusBarTransparentAndBlackFont(Activity activity) {
        Window window = activity.getWindow();
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(COUIContextUtil.getAttrColor(activity, 2130969252));
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int romVersionCode = RomVersionUtil.INSTANCE.getRomVersionCode();
        if (romVersionCode >= 6 || romVersionCode == 0) {
            window.addFlags(Integer.MIN_VALUE);
            decorView.setSystemUiVisibility(OplusDarkModeUtil.isNightMode(activity) ? systemUiVisibility & (-8209) : systemUiVisibility | OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
        }
    }

    public static final void setStatusBarVisibility(Activity activity, boolean z) {
        int i;
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE);
        window.setStatusBarColor(0);
        window.addFlags(Integer.MIN_VALUE);
        boolean z2 = (activity.getResources().getConfiguration().uiMode & 48) == 32;
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (z2) {
            i = systemUiVisibility & (-8209);
        } else {
            i = (z ? systemUiVisibility | 256 : systemUiVisibility | OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) | 16;
            window.setNavigationBarColor(ThemeBundleUtils.Companion.getNavBarBackground());
        }
        decorView.setSystemUiVisibility(i);
    }

    public static final void updateHolePosition(Rect rect) {
        HolePosition.updateHolePosition(rect);
    }

    public final boolean isGlobalThemeApplied(Context context) {
        return context.getResources().getBoolean(2131034330);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getScreenHoleHeight() {
        return getScreenHoleParam(3) - getScreenHoleParam(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getScreenHoleWidth() {
        return getScreenHoleParam(2) - getScreenHoleParam(0);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0027  */
    public static final int getScreenHoleParam(Context context, int i) {
        String str;
        ArrayList<Integer> arrayList = propertiesHoleList;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            Uri uri = Util.ROM_UPDATE_CONTENT_URI;
            if (AbsSettingsValueProxy.getGlobalIntValue(context, ZoomUtil.FOLD_MODE, -1) != 1) {
                arrayList = secondaryPropertiesHoleList;
                str = RO_OPLUS_SECONDARY_SCREEN_HOLE_POSITION;
            } else {
                str = RO_OPLUS_SCREEN_HOLE_POSITION;
            }
        } else {
            str = RO_OPLUS_SCREEN_HOLE_POSITION;
        }
        return getScreenHoleParam(arrayList, str, i);
    }

    public static final int getScreenHoleParam(int i) {
        String str;
        ArrayList<Integer> arrayList = propertiesHoleList;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && !DeviceStateController.getInstance().isOpenOrHalfOpen()) {
            arrayList = secondaryPropertiesHoleList;
            str = RO_OPLUS_SECONDARY_SCREEN_HOLE_POSITION;
        } else {
            str = RO_OPLUS_SCREEN_HOLE_POSITION;
        }
        return getScreenHoleParam(arrayList, str, i);
    }
}
