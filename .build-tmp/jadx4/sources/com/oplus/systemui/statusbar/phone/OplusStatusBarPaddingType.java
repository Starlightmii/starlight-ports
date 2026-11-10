package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusStatusBarPaddingType {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusStatusBarPaddingType";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CutoutHolePadding extends OplusStatusBarPaddingType {
        public final int cutoutDistance;
        public final int holePadding;

        public CutoutHolePadding(Context context, int i) {
            this.holePadding = i;
            this.cutoutDistance = context.getResources().getDimensionPixelSize(2131171158);
        }

        public final int getCutoutDistance() {
            return this.cutoutDistance;
        }

        @Override // com.oplus.systemui.statusbar.phone.OplusStatusBarPaddingType
        public int getPadding() {
            return this.cutoutDistance + this.holePadding;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DisplayCornerPadding extends OplusStatusBarPaddingType {
        private final Context context;
        public final int cornerPosition;
        public final float percentScreen;
        public final int statusBarHeight;
        private final Pair<Integer, Integer> verticalPadding;

        public DisplayCornerPadding(Context context, int i, Pair<Integer, Integer> pair, int i2, float f) {
            this.context = context;
            this.statusBarHeight = i;
            this.verticalPadding = pair;
            this.cornerPosition = i2;
            this.percentScreen = f;
        }

        public final int getCornerSafeDistance(Context context) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                return context.getResources().getDimensionPixelSize(2131171165);
            }
            return (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceUtil.shouldUseLargeFoldConfig(context.getResources())) ? context.getResources().getDimensionPixelSize(2131171163) : context.getResources().getDimensionPixelSize(2131171164);
        }

        @Override // com.oplus.systemui.statusbar.phone.OplusStatusBarPaddingType
        public int getPadding() {
            int i;
            String str;
            double d;
            double d2;
            Context context = this.context;
            int cornerSafeDistance = getCornerSafeDistance(context);
            Pair<Integer, Integer> pair = this.verticalPadding;
            int iIntValue = (((this.statusBarHeight - ((Number) pair.getFirst()).intValue()) - ((Number) pair.getSecond()).intValue()) / 2) + ((Number) pair.getFirst()).intValue();
            int i2 = this.cornerPosition;
            int cornerRadius = OplusStatusBarUtils.getCornerRadius(context, i2, this.percentScreen);
            if (cornerRadius <= 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(cornerRadius, "getStatusBarCornerPadding radius <= 0 , return x2 ,radius :", "OplusStatusBarPaddingType-->", "SystemUi--Statusbar");
                    return cornerSafeDistance;
                }
            } else if (iIntValue <= cornerRadius) {
                int i3 = cornerRadius - iIntValue;
                int iSqrt = 0;
                if (i3 <= 0 || cornerRadius <= 0) {
                    i = iIntValue;
                    str = LogUtil.TAG_STATUSBAR;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                        Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->Side lengths must be positive");
                    }
                } else {
                    if (i3 > cornerRadius) {
                        d = i3;
                        d2 = cornerRadius;
                    } else if (cornerRadius > i3) {
                        d = cornerRadius;
                        d2 = i3;
                    } else {
                        i = iIntValue;
                        str = LogUtil.TAG_STATUSBAR;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                            Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->The two sides entered cannot be equal (cannot be a right angle side and a hypotenuse at the same time)");
                        }
                    }
                    if (d <= d2) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            Log.d("SystemUi--Statusbar", "OplusStatusBarUtils-->The hypotenuse must be longer than the right angle side");
                        }
                        i = iIntValue;
                        str = LogUtil.TAG_STATUSBAR;
                    } else {
                        i = iIntValue;
                        str = LogUtil.TAG_STATUSBAR;
                        double dPow = Math.pow(d, 2.0d) - Math.pow(d2, 2.0d);
                        if (dPow < 0.0d) {
                            dPow = 0.0d;
                        }
                        iSqrt = (int) Math.sqrt(dPow);
                    }
                }
                if (iSqrt != 0) {
                    int i4 = cornerRadius - iSqrt;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getStatusBarCornerPadding x1 :", i4, cornerSafeDistance, " ,x2 :", " ,n1 :");
                        RectList$.ExternalSyntheticOutline0.m(i3, iSqrt, " ,n2 :", " ,topDistance: ", sbM);
                        RectList$.ExternalSyntheticOutline0.m(i, i2, " ,cornerPosition: ", ", radius :", sbM);
                        sbM.append(cornerRadius);
                        sbM.append(",paddingTopBottomPair: ");
                        sbM.append(pair);
                        Log.d("SystemUi--Statusbar", "OplusStatusBarPaddingType-->".concat(sbM.toString()));
                    }
                    return i4 + cornerSafeDistance;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("return n2 == 0 ,cornerPosition: ", i2, cornerRadius, ", radius :", ",paddingTopBottomPair: ");
                    sbM2.append(pair);
                    Log.d("SystemUi--Statusbar", "OplusStatusBarPaddingType-->".concat(sbM2.toString()));
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("getStatusBarCornerPadding return x2, x2 :", cornerSafeDistance, iIntValue, ",topDistance: ", " ,cornerPosition :");
                RectList$.ExternalSyntheticOutline0.m(i2, cornerRadius, ", radius :", " ,paddingTopBottomPair: ", sbM3);
                sbM3.append(pair);
                Log.d("SystemUi--Statusbar", "OplusStatusBarPaddingType-->".concat(sbM3.toString()));
                return cornerSafeDistance;
            }
            return cornerSafeDistance;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NavbarInsetsPadding extends OplusStatusBarPaddingType {
        public final int fixedNavBar;

        public NavbarInsetsPadding(Context context) {
            this.fixedNavBar = context.getResources().getDimensionPixelSize(2131171159);
        }

        public final int getFixedNavBar() {
            return this.fixedNavBar;
        }

        @Override // com.oplus.systemui.statusbar.phone.OplusStatusBarPaddingType
        public int getPadding() {
            return this.fixedNavBar;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SafeInsetPadding extends OplusStatusBarPaddingType {
        public final int cornerPadding;
        public final int cutOutPadding;
        public final int fixedOffSet;

        public SafeInsetPadding(Context context, int i, int i2) {
            this.cutOutPadding = i;
            this.cornerPadding = i2;
            this.fixedOffSet = context.getResources().getDimensionPixelSize(2131171160);
        }

        @Override // com.oplus.systemui.statusbar.phone.OplusStatusBarPaddingType
        public int getPadding() {
            int i = this.cutOutPadding;
            int i2 = this.cornerPadding;
            return i > i2 ? i + this.fixedOffSet : i2;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UnknownPadding extends OplusStatusBarPaddingType {
        public static final UnknownPadding INSTANCE = new UnknownPadding();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof UnknownPadding);
        }

        @Override // com.oplus.systemui.statusbar.phone.OplusStatusBarPaddingType
        public int getPadding() {
            return 0;
        }

        public final int hashCode() {
            return 1322024584;
        }

        public String toString() {
            return "UnknownPadding";
        }
    }

    public abstract int getPadding();
}
