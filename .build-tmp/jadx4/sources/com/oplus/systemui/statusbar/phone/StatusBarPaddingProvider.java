package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Trace;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.ui.spatial.RectList$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.android.systemui.statusbar.policy.CallbackController;
import com.oplus.systemui.keyguard.helper.ScreenResolutionHelper;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$holeRectListener$1;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarPaddingProvider implements CallbackController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "StatusBarPaddingProvider";
    public static volatile int lastRotation;
    public int baseScreenWidth;
    private final Context context;
    public int screenHoleBottom;
    public int screenHoleHeight;
    public int screenHoleTop;
    public int seedlingHeight;
    public int statusBarHeight;
    public float percentScreen = 1.0f;
    private final List<IScreenHoleChanged> listeners = new ArrayList();
    private RectF holeRect = new RectF();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Pair<Integer, Integer> cornerCutoutMargins(DisplayCutout displayCutout, Display display) {
            return statusBarCornerCutoutMargins(displayCutout, display, 0, 0);
        }

        public final android.util.Pair<Integer, Integer> paddingNeededForCutoutAndRoundedCorner(DisplayCutout displayCutout, android.util.Pair<Integer, Integer> pair, int i, Context context) {
            if (displayCutout == null) {
                return new android.util.Pair<>(Integer.valueOf(i), Integer.valueOf(i));
            }
            int safeInsetLeft = displayCutout.getSafeInsetLeft();
            int safeInsetRight = displayCutout.getSafeInsetRight();
            if (pair != null) {
                Object obj = pair.first;
                obj.getClass();
                int iIntValue = ((Number) obj).intValue();
                if (safeInsetLeft < iIntValue) {
                    safeInsetLeft = iIntValue;
                }
                Object obj2 = pair.second;
                obj2.getClass();
                int iIntValue2 = ((Number) obj2).intValue();
                if (safeInsetRight < iIntValue2) {
                    safeInsetRight = iIntValue2;
                }
            }
            if (ListPopupWindow$.ExternalSyntheticOutline0.m(context) != 1) {
            }
            context.getResources().getConfiguration().windowConfiguration.getRotation();
            if (safeInsetLeft < i) {
                safeInsetLeft = i;
            }
            Integer numValueOf = Integer.valueOf(safeInsetLeft);
            if (safeInsetRight >= i) {
                i = safeInsetRight;
            }
            return new android.util.Pair<>(numValueOf, Integer.valueOf(i));
        }

        public final Pair<Integer, Integer> statusBarCornerCutoutMargins(DisplayCutout displayCutout, Display display, int i, int i2) {
            if (displayCutout == null) {
                return null;
            }
            Point point = new Point();
            display.getRealSize(point);
            Rect rect = new Rect();
            if (i == 0) {
                ScreenDecorations.DisplayCutoutView.boundsFromDirection(48, rect, displayCutout);
            } else if (i == 1) {
                ScreenDecorations.DisplayCutoutView.boundsFromDirection(3, rect, displayCutout);
            } else {
                if (i == 2) {
                    return null;
                }
                if (i == 3) {
                    ScreenDecorations.DisplayCutoutView.boundsFromDirection(5, rect, displayCutout);
                }
            }
            if (i2 >= 0 && rect.top > i2) {
                return null;
            }
            if (rect.left <= 0) {
                return new Pair<>(Integer.valueOf(rect.right), 0);
            }
            if (rect.right >= point.x) {
                return new Pair<>(0, Integer.valueOf(point.x - rect.left));
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IScreenHoleChanged {
        void onRectChanged(RectF rectF);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OplusStatusBarUtils.HolePositionType.values().length];
            try {
                OplusStatusBarUtils.HolePositionType holePositionType = OplusStatusBarUtils.HolePositionType.HOLE_LEFT_TYPE;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                OplusStatusBarUtils.HolePositionType holePositionType2 = OplusStatusBarUtils.HolePositionType.HOLE_LEFT_TYPE;
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StatusBarPaddingProvider(Context context) {
        this.context = context;
    }

    public static final Pair<Integer, Integer> cornerCutoutMargins(DisplayCutout displayCutout, Display display) {
        return Companion.cornerCutoutMargins(displayCutout, display);
    }

    public static final android.util.Pair<Integer, Integer> paddingNeededForCutoutAndRoundedCorner(DisplayCutout displayCutout, android.util.Pair<Integer, Integer> pair, int i, Context context) {
        return Companion.paddingNeededForCutoutAndRoundedCorner(displayCutout, pair, i, context);
    }

    public static final Pair<Integer, Integer> statusBarCornerCutoutMargins(DisplayCutout displayCutout, Display display, int i, int i2) {
        return Companion.statusBarCornerCutoutMargins(displayCutout, display, i, i2);
    }

    public void addCallback(IScreenHoleChanged iScreenHoleChanged) {
        if (this.listeners.contains(iScreenHoleChanged)) {
            return;
        }
        this.listeners.add(iScreenHoleChanged);
        ((SeedlingPluginManager$holeRectListener$1) iScreenHoleChanged).onRectChanged(this.holeRect);
    }

    public final void dump(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.println(TAG);
        }
        if (printWriter != null) {
            float f = this.percentScreen;
            int i = this.baseScreenWidth;
            int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(this.context).getStableDisplayWidth();
            int i2 = this.screenHoleHeight;
            int i3 = this.screenHoleTop;
            int i4 = this.screenHoleBottom;
            StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, i, "percentScreen: ", " baseScreenWidth:", " stableWidth: ");
            RectList$.ExternalSyntheticOutline0.m(stableDisplayWidth, i2, " screenHoleHeight: ", " screenHoleTop: ", sbM);
            sbM.append(i3);
            sbM.append(" screenHoleBottom:");
            sbM.append(i4);
            printWriter.println(sbM.toString());
        }
        if (printWriter != null) {
            printWriter.println();
        }
    }

    public final Rect getHomeStatusBarLeftRightPadding(String str, Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2) {
        updateResources$12();
        boolean zIsLandscape = Util.isLandscape(this.context);
        int rotation = this.context.getResources().getConfiguration().windowConfiguration.getRotation();
        Pair pairUpdatePaddingTopBottom = updatePaddingTopBottom(rotation, zIsLandscape);
        Pair pairUpdatePaddingLeftRight = updatePaddingLeftRight(str, pair, rotation, pairUpdatePaddingTopBottom, this.holeRect, pair2);
        Object first = pairUpdatePaddingLeftRight.getFirst();
        Object first2 = pairUpdatePaddingTopBottom.getFirst();
        Object second = pairUpdatePaddingLeftRight.getSecond();
        Object second2 = pairUpdatePaddingTopBottom.getSecond();
        int i = this.screenHoleHeight;
        int i2 = this.screenHoleBottom;
        int i3 = this.statusBarHeight;
        int i4 = this.seedlingHeight;
        StatusBarFeatureOption.INSTANCE.getClass();
        boolean zIsScreenResolutionSwitchSupport = StatusBarFeatureOption.isScreenResolutionSwitchSupport();
        float f = this.percentScreen;
        int i5 = this.baseScreenWidth;
        int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(this.context).getStableDisplayWidth();
        StringBuilder sb = new StringBuilder("getHomeStatusBarPadding: tagStr:");
        sb.append(str);
        sb.append(", (");
        sb.append(first);
        sb.append(", ");
        sb.append(first2);
        sb.append(", ");
        sb.append(second);
        sb.append(", ");
        sb.append(second2);
        sb.append("), screenHoleHeight: ");
        sb.append(i);
        sb.append(" isLandscape=");
        PromptIconState$.ExternalSyntheticOutline0.m(rotation, ", rotation: ", " screenHoleBottom: ", sb, zIsLandscape);
        RectList$.ExternalSyntheticOutline0.m(i2, i3, " statusBarHeight: ", " seedlingHeight: ", sb);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i4, " switchSupport: ", " percentScreen:", sb, zIsScreenResolutionSwitchSupport);
        ChildHelper$.ExternalSyntheticOutline0.m(f, i5, " baseScreenWidth:", " stableDisplayWidth:", sb);
        sb.append(stableDisplayWidth);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sb.toString(), null, 8);
        return new Rect(((Number) pairUpdatePaddingLeftRight.getFirst()).intValue(), ((Number) pairUpdatePaddingTopBottom.getFirst()).intValue(), ((Number) pairUpdatePaddingLeftRight.getSecond()).intValue(), ((Number) pairUpdatePaddingTopBottom.getSecond()).intValue());
    }

    public final int getLastRotation() {
        return lastRotation;
    }

    public final void updateKeyguardStatusBarPadding(View view, Pair<Integer, Integer> pair) {
        Pair pair2;
        updateResources$12();
        boolean zIsLandscape = Util.isLandscape(this.context);
        int rotation = this.context.getResources().getConfiguration().windowConfiguration.getRotation();
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(2131170982);
        boolean z = ListPopupWindow$.ExternalSyntheticOutline0.m(this.context) == 1;
        lastRotation = rotation;
        Pair pairUpdatePaddingTopBottom = updatePaddingTopBottom(rotation, zIsLandscape);
        Pair pairUpdatePaddingLeftRight = updatePaddingLeftRight(view.toString(), pair, rotation, pairUpdatePaddingTopBottom, this.holeRect, TuplesKt.to(0, 0));
        int i = this.screenHoleHeight;
        int i2 = this.screenHoleBottom;
        int i3 = this.statusBarHeight;
        int i4 = this.seedlingHeight;
        StatusBarFeatureOption.INSTANCE.getClass();
        boolean zIsScreenResolutionSwitchSupport = StatusBarFeatureOption.isScreenResolutionSwitchSupport();
        float f = this.percentScreen;
        int i5 = this.baseScreenWidth;
        int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(this.context).getStableDisplayWidth();
        RectF rectF = this.holeRect;
        boolean z2 = z;
        StringBuilder sb = new StringBuilder("updateKeyguardStatusBarPadding: View:");
        sb.append(view);
        sb.append(", paddingLeftRightPair: ");
        sb.append(pairUpdatePaddingLeftRight);
        sb.append(", paddingTopBottomPair: ");
        sb.append(pairUpdatePaddingTopBottom);
        sb.append(", screenHoleHeight: ");
        sb.append(i);
        sb.append(" isLandscape=");
        PromptIconState$.ExternalSyntheticOutline0.m(rotation, ", rotation: ", ", screenHoleBottom: ", sb, zIsLandscape);
        RectList$.ExternalSyntheticOutline0.m(i2, i3, " statusBarHeight: ", " seedlingHeight: ", sb);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i4, " switchSupport: ", " percentScreen:", sb, zIsScreenResolutionSwitchSupport);
        ChildHelper$.ExternalSyntheticOutline0.m(f, i5, " baseScreenWidth:", " stableDisplayWidth:", sb);
        sb.append(stableDisplayWidth);
        sb.append(" holeRect: ");
        sb.append(rectF);
        sb.append(" ,insets: ");
        sb.append(pair);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sb.toString(), null, 8);
        int iIntValue = ((Number) pairUpdatePaddingLeftRight.component1()).intValue();
        int iIntValue2 = ((Number) pairUpdatePaddingLeftRight.component2()).intValue();
        if (z2) {
            int i6 = iIntValue2 - dimensionPixelSize;
            pair2 = TuplesKt.to(Integer.valueOf(iIntValue), Integer.valueOf(i6 < 0 ? 0 : i6));
        } else {
            int i7 = iIntValue - dimensionPixelSize;
            pair2 = TuplesKt.to(Integer.valueOf(i7 < 0 ? 0 : i7), Integer.valueOf(iIntValue2));
        }
        view.setPadding(((Number) pair2.component1()).intValue(), ((Number) pairUpdatePaddingTopBottom.getFirst()).intValue(), ((Number) pair2.component2()).intValue(), ((Number) pairUpdatePaddingTopBottom.getSecond()).intValue());
    }

    public final Pair updatePaddingLeftRight(String str, Pair pair, int i, Pair pair2, RectF rectF, Pair pair3) {
        Pair pair4;
        int i2 = this.context.getResources().getDisplayMetrics().widthPixels;
        boolean z = ((Number) pair.getFirst()).intValue() > 0;
        boolean z2 = ((Number) pair.getSecond()).intValue() > 0;
        OplusStatusBarUtils.HolePositionType holePositionTypeIsHoleType = OplusStatusBarUtils.isHoleType(this.context);
        if (i == 0) {
            int iOrdinal = holePositionTypeIsHoleType.ordinal();
            Context context = this.context;
            if (iOrdinal != 0) {
                int i3 = this.statusBarHeight;
                pair4 = iOrdinal != 1 ? TuplesKt.to(new OplusStatusBarPaddingType.DisplayCornerPadding(context, i3, pair2, 0, this.percentScreen), new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 1, this.percentScreen)) : TuplesKt.to(new OplusStatusBarPaddingType.DisplayCornerPadding(context, i3, pair2, 0, this.percentScreen), new OplusStatusBarPaddingType.CutoutHolePadding(this.context, i2 - ((int) rectF.left)));
            } else {
                pair4 = TuplesKt.to(new OplusStatusBarPaddingType.CutoutHolePadding(context, (int) rectF.right), new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 1, this.percentScreen));
            }
        } else if (i == 1) {
            Context context2 = this.context;
            Object navbarInsetsPadding = z2 ? new OplusStatusBarPaddingType.NavbarInsetsPadding(context2) : new OplusStatusBarPaddingType.DisplayCornerPadding(context2, this.statusBarHeight, pair2, 2, this.percentScreen);
            OplusStatusBarPaddingType.DisplayCornerPadding displayCornerPadding = new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 1, this.percentScreen);
            int i4 = WhenMappings.$EnumSwitchMapping$0[holePositionTypeIsHoleType.ordinal()];
            Context context3 = this.context;
            pair4 = i4 == 2 ? TuplesKt.to(new OplusStatusBarPaddingType.CutoutHolePadding(context3, (int) rectF.bottom), navbarInsetsPadding) : TuplesKt.to(new OplusStatusBarPaddingType.SafeInsetPadding(context3, ((Number) pair3.getFirst()).intValue(), displayCornerPadding.getPadding()), navbarInsetsPadding);
        } else if (i == 2) {
            pair4 = TuplesKt.to(new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 2, this.percentScreen), new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 3, this.percentScreen));
        } else if (i != 3) {
            OplusStatusBarPaddingType.UnknownPadding unknownPadding = OplusStatusBarPaddingType.UnknownPadding.INSTANCE;
            pair4 = TuplesKt.to(unknownPadding, unknownPadding);
        } else {
            Context context4 = this.context;
            Object navbarInsetsPadding2 = z ? new OplusStatusBarPaddingType.NavbarInsetsPadding(context4) : new OplusStatusBarPaddingType.DisplayCornerPadding(context4, this.statusBarHeight, pair2, 3, this.percentScreen);
            OplusStatusBarPaddingType.DisplayCornerPadding displayCornerPadding2 = new OplusStatusBarPaddingType.DisplayCornerPadding(this.context, this.statusBarHeight, pair2, 0, this.percentScreen);
            int i5 = WhenMappings.$EnumSwitchMapping$0[holePositionTypeIsHoleType.ordinal()];
            Context context5 = this.context;
            pair4 = i5 == 1 ? TuplesKt.to(navbarInsetsPadding2, new OplusStatusBarPaddingType.CutoutHolePadding(context5, (int) rectF.bottom)) : TuplesKt.to(navbarInsetsPadding2, new OplusStatusBarPaddingType.SafeInsetPadding(context5, ((Number) pair3.getSecond()).intValue(), displayCornerPadding2.getPadding()));
        }
        Pair pair5 = TuplesKt.to(Integer.valueOf(((OplusStatusBarPaddingType) pair4.getFirst()).getPadding()), Integer.valueOf(((OplusStatusBarPaddingType) pair4.getSecond()).getPadding()));
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("logPaddingResult tagStr:", str, ", ", holePositionTypeIsHoleType.name(), " ,rotation: ");
        sbM.append(i);
        sbM.append(", HolePadding: paddingResult: ");
        sbM.append(pair5);
        sbM.append(", screenWidth: ");
        sbM.append(i2);
        sbM.append(" , holeRect: ");
        sbM.append(rectF);
        sbM.append(", hasLeftInset: ");
        String strM = KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, z, " ,hasRightInset: ", z2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarPaddingProvider-->".concat(strM));
        }
        return pair5;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001a  */
    public final Pair updatePaddingTopBottom(int i, boolean z) {
        int i2;
        int i3 = this.screenHoleHeight;
        int i4 = 0;
        int i5 = i3 != 0 ? this.screenHoleTop : 0;
        int i6 = this.screenHoleBottom;
        int i7 = this.statusBarHeight;
        int i8 = (i6 >= i7 || i6 <= 0) ? 0 : i7 - i6;
        if (!z && i3 <= 0) {
            i2 = 0;
        } else if (z && (i == 1 || i == 3)) {
            i2 = (i7 - this.seedlingHeight) / 2;
            if (i2 > 0) {
                i4 = i2;
            } else {
                i2 = 0;
            }
        } else {
            i2 = i5;
            i4 = i8;
        }
        int i9 = (i7 - i4) - i2;
        int i10 = this.seedlingHeight;
        if (i9 < i10) {
            int i11 = (i10 / 2) - (i9 / 2);
            int i12 = i4 > i2 ? i2 : i4;
            if (i11 <= i12) {
                i4 -= i11;
                i2 -= i11;
            } else {
                i4 -= i12;
                i2 -= i12;
            }
        }
        return new Pair(Integer.valueOf(i2), Integer.valueOf(i4));
    }

    public final void updateResources$12() {
        Trace.beginSection("StatusBarPaddingProvider#updateResources");
        this.context.getResources().getDimensionPixelSize(2131171162);
        this.context.getResources().getDimensionPixelSize(2131171161);
        this.screenHoleHeight = OplusStatusBarUtils.getScreenHoleHeight(this.context);
        this.screenHoleTop = OplusStatusBarUtils.getScreenHoleParam(this.context, 1);
        this.screenHoleBottom = OplusStatusBarUtils.getScreenHoleParam(this.context, 3);
        this.statusBarHeight = SystemBarUtils.getStatusBarHeight(this.context);
        this.seedlingHeight = this.context.getResources().getDimensionPixelSize(2131165923);
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.isScreenResolutionSwitchSupport()) {
            boolean zIsLandscape = Util.isLandscape(this.context);
            Context context = this.context;
            this.baseScreenWidth = zIsLandscape ? context.getResources().getDisplayMetrics().heightPixels : context.getResources().getDisplayMetrics().widthPixels;
            int stableDisplayWidth = ScreenResolutionHelper.Companion.getInstance(this.context).getStableDisplayWidth();
            if (stableDisplayWidth > 0) {
                this.percentScreen = this.baseScreenWidth / stableDisplayWidth;
            }
            float f = this.percentScreen;
            if (f > 1.5f || f < 0.5f) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "screen percent get error, reset to 1f, percent:" + f);
                this.percentScreen = 1.0f;
            }
            float f2 = this.screenHoleHeight;
            float f3 = this.percentScreen;
            this.screenHoleHeight = (int) (f2 * f3);
            this.screenHoleTop = (int) (this.screenHoleTop * f3);
            this.screenHoleBottom = (int) (this.screenHoleBottom * f3);
        }
        this.holeRect.set(OplusStatusBarUtils.getScreenHoleParam(this.context, 0) * this.percentScreen, OplusStatusBarUtils.getScreenHoleParam(this.context, 1) * this.percentScreen, OplusStatusBarUtils.getScreenHoleParam(this.context, 2) * this.percentScreen, OplusStatusBarUtils.getScreenHoleParam(this.context, 3) * this.percentScreen);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((SeedlingPluginManager$holeRectListener$1) ((IScreenHoleChanged) it.next())).onRectChanged(this.holeRect);
        }
        Trace.endSection();
    }

    public void removeCallback(IScreenHoleChanged iScreenHoleChanged) {
        this.listeners.remove(iScreenHoleChanged);
    }
}
