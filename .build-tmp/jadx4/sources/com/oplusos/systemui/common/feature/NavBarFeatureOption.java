package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarFeatureOption {
    private static final String DEFAULT_NAV_STATE_SIDE_GESTURE = "sidegesture";
    private static final String DEFAULT_NAV_STATE_VIRTUAL_KEY = "virtualkey";
    private static final String TOUCHPAD_TWO_FINGER = "oplus.software.touchpad_two_finger";
    private static final String UNFOLD_WIDTH_SMALL_HIGH = "oplus.software.feature_unfold_width_small_high";
    private static Context applicationContext;
    private static final Lazy isDefaultNavVirtualKey$delegate;
    private static final Lazy isTouchpadTwoFinger$delegate;
    private static final Lazy isUnfoldWidthSmallHigh$delegate;
    private static String sDefaultNavState;
    public static boolean sEnableHideGesture;
    public static boolean sIsHideGestureBarByOta;
    public static boolean sIsSupportTaskbar;
    public static final NavBarFeatureOption INSTANCE = new NavBarFeatureOption();
    private static final String DEFAULT_NAV_STATE_STRING = "com.android.systemui.default_nav_state";
    private static final String IS_ENABLE_GESTURE_HIDE = "com.android.systemui.enable_gesture_hide";
    private static final String IS_SUPPORT_TASKBAR_KEY = "com.android.launcher.TASKBAR_ENABLE";
    private static final String IS_HIDE_GESTURE_BAR_OTA = "com.android.systemui.hide_gesture_bar_by_ota";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{DEFAULT_NAV_STATE_STRING, IS_ENABLE_GESTURE_HIDE, IS_SUPPORT_TASKBAR_KEY, IS_HIDE_GESTURE_BAR_OTA});

    public static boolean $r8$lambda$7zeteXHEYZXyNBn3ruIY8KdxiUs() {
        String str = sDefaultNavState;
        if (Intrinsics.areEqual(str, DEFAULT_NAV_STATE_VIRTUAL_KEY)) {
            return true;
        }
        Intrinsics.areEqual(str, DEFAULT_NAV_STATE_SIDE_GESTURE);
        return false;
    }

    static {
        final int i = 0;
        isDefaultNavVirtualKey$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NavBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return Boolean.valueOf(NavBarFeatureOption.$r8$lambda$7zeteXHEYZXyNBn3ruIY8KdxiUs());
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.feature_unfold_width_small_high");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.touchpad_two_finger");
                }
            }
        });
        final int i2 = 1;
        isUnfoldWidthSmallHigh$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NavBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return Boolean.valueOf(NavBarFeatureOption.$r8$lambda$7zeteXHEYZXyNBn3ruIY8KdxiUs());
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.feature_unfold_width_small_high");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.touchpad_two_finger");
                }
            }
        });
        final int i3 = 2;
        isTouchpadTwoFinger$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NavBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return Boolean.valueOf(NavBarFeatureOption.$r8$lambda$7zeteXHEYZXyNBn3ruIY8KdxiUs());
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.feature_unfold_width_small_high");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.touchpad_two_finger");
                }
            }
        });
    }

    public static boolean isDefaultNavVirtualKey() {
        return ((Boolean) isDefaultNavVirtualKey$delegate.getValue()).booleanValue();
    }

    public static boolean isTouchpadTwoFinger() {
        return ((Boolean) isTouchpadTwoFinger$delegate.getValue()).booleanValue();
    }

    public static boolean isUnfoldWidthSmallHigh() {
        return ((Boolean) isUnfoldWidthSmallHigh$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final void loadAppFeature(Context context) {
        applicationContext = context;
        sDefaultNavState = AppFeatureProviderUtils.getString(context.getContentResolver(), DEFAULT_NAV_STATE_STRING, DEFAULT_NAV_STATE_VIRTUAL_KEY);
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        sEnableHideGesture = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, IS_ENABLE_GESTURE_HIDE);
        sIsSupportTaskbar = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, IS_SUPPORT_TASKBAR_KEY);
        sIsHideGestureBarByOta = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, IS_HIDE_GESTURE_BAR_OTA);
    }
}
