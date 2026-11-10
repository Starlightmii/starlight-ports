package com.oplus.systemui.navigationbar.gesture.olivelink;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarOliveLinkConnector {
    public static final NavBarOliveLinkConnector INSTANCE = new NavBarOliveLinkConnector();
    private static final Lazy NAVBAR_PADDING_X$delegate;
    private static final Lazy NAVBAR_PADDING_X1$delegate;
    private static final Lazy RUBBER_BAND_MAX_DISTANCE_X$delegate;
    private static final Lazy RUBBER_BAND_MAX_DISTANCE_Y$delegate;
    private static final Lazy RUBBER_BAND_RATIO_X$delegate;
    private static final Lazy RUBBER_BAND_RATIO_Y$delegate;
    public static final String SEP_NAV_BAR_NAME = "nav_bar";
    private static final String TAG = "NavBarOliveLinkConnector";

    static {
        final int i = 0;
        RUBBER_BAND_MAX_DISTANCE_X$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
        final int i2 = 1;
        RUBBER_BAND_MAX_DISTANCE_Y$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
        final int i3 = 2;
        RUBBER_BAND_RATIO_X$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
        final int i4 = 3;
        RUBBER_BAND_RATIO_Y$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
        final int i5 = 4;
        NAVBAR_PADDING_X1$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
        final int i6 = 5;
        NAVBAR_PADDING_X$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向偏移最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(55.0f), 16);
                    case 1:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向最大距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(250.0f), 16);
                    case 2:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势横向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 3:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "侧边手势竖向橡皮筋曲率", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, Float.valueOf(0.55f), 16);
                    case 4:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标到圆角边的水平距离", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 12, 16);
                    default:
                        return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, "R角-图标距离屏幕边水平距离最小值", NavBarOliveLinkConnector.SEP_NAV_BAR_NAME, 24, 16);
                }
            }
        });
    }

    public final Scalar getNAVBAR_PADDING_X() {
        return (Scalar) NAVBAR_PADDING_X$delegate.getValue();
    }

    public final Scalar getNAVBAR_PADDING_X1() {
        return (Scalar) NAVBAR_PADDING_X1$delegate.getValue();
    }

    public final Scalar getRUBBER_BAND_MAX_DISTANCE_X() {
        return (Scalar) RUBBER_BAND_MAX_DISTANCE_X$delegate.getValue();
    }

    public final Scalar getRUBBER_BAND_MAX_DISTANCE_Y() {
        return (Scalar) RUBBER_BAND_MAX_DISTANCE_Y$delegate.getValue();
    }

    public final Scalar getRUBBER_BAND_RATIO_X() {
        return (Scalar) RUBBER_BAND_RATIO_X$delegate.getValue();
    }

    public final Scalar getRUBBER_BAND_RATIO_Y() {
        return (Scalar) RUBBER_BAND_RATIO_Y$delegate.getValue();
    }

    public final void start(Context context, String str) {
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, FileProvider$.ExternalSyntheticOutline0.m("[start] for ", str), null, 8);
        OliveLinkConnector.INSTANCE.start(context, str);
    }

    public final void stop(String str) {
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, FileProvider$.ExternalSyntheticOutline0.m("[stop] for ", str), null, 8);
        OliveLinkConnector.INSTANCE.stop(str);
    }
}
