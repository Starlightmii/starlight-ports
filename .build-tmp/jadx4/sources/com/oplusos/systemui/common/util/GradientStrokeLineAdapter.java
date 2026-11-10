package com.oplusos.systemui.common.util;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.CornerType;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.posteffect.params.OpticsParams;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplusos.systemui.common.adapter.MixColorTileInnerShadowAdapter;
import com.oplusos.systemui.common.adapter.MixColorTileOpticsAdapter;
import com.oplusos.systemui.common.adapter.MixColorTileStrokeLineAdapter;
import com.oplusos.systemui.common.blurability.BlurConfig;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GradientStrokeLineAdapter {
    private static final String TAG = "GradientStrokeLineAdapter";
    private static final Lazy commonInactiveTemplate$delegate;
    private static final Lazy commonInactiveTemplateDark$delegate;
    private static final Lazy detailPanelTemplate$delegate;
    private static final Lazy detailPanelTemplateDark$delegate;
    private static final Lazy highlightIconActiveTemplate$delegate;
    private static final Lazy highlightIconActiveTemplateDark$delegate;
    private static final Lazy highlightInActiveTemplate$delegate;
    private static final Lazy highlightInActiveTemplateDark$delegate;
    private static final Lazy mediaOutPutStrokeParamsTemplate$delegate;
    private static final Lazy mediaOutPutStrokeParamsTemplateDark$delegate;
    private static final Lazy pluginTemplate$delegate;
    private static final Lazy pluginTemplateDark$delegate;
    private static final Lazy quickEntranceTemplate$delegate;
    private static final Lazy quickEntranceTemplateDark$delegate;
    private static final Lazy seekBarBGDialogTemplate$delegate;
    private static final Lazy seekBarBGDialogTemplateDark$delegate;
    private static final Lazy seekBarBGTemplate$delegate;
    private static final Lazy seekBarBGTemplateDark$delegate;
    private static final Lazy seekBarProgressDialogTemplate$delegate;
    private static final Lazy seekBarProgressDialogTemplateDark$delegate;
    private static final Lazy seekBarProgressTemplate$delegate;
    private static final Lazy seekBarProgressTemplateDark$delegate;
    private static final Lazy sepEditModeRectButtonTemplate$delegate;
    private static final Lazy sepEditModeRectButtonTemplateDark$delegate;
    private static final Lazy tileActiveTemplate$delegate;
    private static final Lazy tileActiveTemplateDark$delegate;
    private static final Lazy tileInActiveTemplate$delegate;
    private static final Lazy tileInActiveTemplateDark$delegate;
    public static final GradientStrokeLineAdapter INSTANCE = new GradientStrokeLineAdapter();
    private static final LineParams lineParamsTemplate = new LineParams(6, 0.0f, 0.2f, 0.2f, 0.0f, 0.0f);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CacheType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ CacheType[] $VALUES;
        public static final CacheType DEFAULT_RADIUS;
        public static final CacheType ONE_X_ONE_RADIUS;
        public static final CacheType ONE_X_TWO_OPLUS_RADIUS;
        public static final CacheType ONE_X_TWO_OPPO_RADIUS;
        public static final CacheType TWO_X_ONE_OPLUS_RADIUS;
        public static final CacheType TWO_X_ONE_OPPO_RADIUS;
        public static final CacheType TWO_X_TWO_RADIUS;

        static {
            CacheType cacheType = new CacheType("ONE_X_ONE_RADIUS", 0);
            ONE_X_ONE_RADIUS = cacheType;
            CacheType cacheType2 = new CacheType("TWO_X_TWO_RADIUS", 1);
            TWO_X_TWO_RADIUS = cacheType2;
            CacheType cacheType3 = new CacheType("TWO_X_ONE_OPPO_RADIUS", 2);
            TWO_X_ONE_OPPO_RADIUS = cacheType3;
            CacheType cacheType4 = new CacheType("TWO_X_ONE_OPLUS_RADIUS", 3);
            TWO_X_ONE_OPLUS_RADIUS = cacheType4;
            CacheType cacheType5 = new CacheType("ONE_X_TWO_OPPO_RADIUS", 4);
            ONE_X_TWO_OPPO_RADIUS = cacheType5;
            CacheType cacheType6 = new CacheType("ONE_X_TWO_OPLUS_RADIUS", 5);
            ONE_X_TWO_OPLUS_RADIUS = cacheType6;
            CacheType cacheType7 = new CacheType("DEFAULT_RADIUS", 6);
            DEFAULT_RADIUS = cacheType7;
            CacheType[] cacheTypeArr = {cacheType, cacheType2, cacheType3, cacheType4, cacheType5, cacheType6, cacheType7};
            $VALUES = cacheTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(cacheTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CacheType valueOf(String str) {
            return (CacheType) Enum.valueOf(CacheType.class, str);
        }

        public static CacheType[] values() {
            return (CacheType[]) $VALUES.clone();
        }
    }

    static {
        final int i = 0;
        tileInActiveTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i2 = 2;
        tileActiveTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i3 = 12;
        highlightIconActiveTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i4 = 13;
        seekBarBGTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i5 = 14;
        seekBarBGDialogTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i6 = 15;
        seekBarBGDialogTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i7 = 16;
        detailPanelTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i7) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i8 = 17;
        quickEntranceTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i8) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i9 = 18;
        tileInActiveTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i9) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i10 = 19;
        tileActiveTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i10) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i11 = 11;
        highlightIconActiveTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i11) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i12 = 20;
        seekBarBGTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i12) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i13 = 21;
        detailPanelTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i13) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i14 = 22;
        mediaOutPutStrokeParamsTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i14) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i15 = 23;
        mediaOutPutStrokeParamsTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i15) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i16 = 24;
        quickEntranceTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i16) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i17 = 25;
        highlightInActiveTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i17) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i18 = 26;
        highlightInActiveTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i18) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i19 = 27;
        commonInactiveTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i19) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i20 = 1;
        sepEditModeRectButtonTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i20) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i21 = 3;
        sepEditModeRectButtonTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i21) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i22 = 4;
        commonInactiveTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i22) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i23 = 5;
        seekBarProgressTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i23) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i24 = 6;
        seekBarProgressTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i24) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i25 = 7;
        seekBarProgressDialogTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i25) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i26 = 8;
        seekBarProgressDialogTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i26) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i27 = 9;
        pluginTemplate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i27) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
        final int i28 = 10;
        pluginTemplateDark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.util.GradientStrokeLineAdapter$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i28) {
                    case 0:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 1:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonInnerShadowParams());
                    case 2:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveInnerShadowParams());
                    case 3:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepEditModeRectButtonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSepEditModeRectButtonDarkInnerShadowParams());
                    case 4:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonDarkInnerShadowParams());
                    case 5:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveInnerShadowParams());
                    case 6:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarActiveDarkInnerShadowParams());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGDarkInnerShadowParams());
                    case 8:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarPGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekbarPGInnerShadowParams());
                    case 9:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginInnerShadowParams());
                    case 10:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getQSPluginDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getQSPluginDarkInnerShadowParams());
                    case 11:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveDarkParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveDarkInnerShadowParams());
                    case 12:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightIconActiveParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightIconActiveInnerShadowParams());
                    case 13:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveInnerShadowParams());
                    case 14:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogSeekBarBGInnerShadowParams());
                    case 15:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogSeekbarBGOpticsParamsDark(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarBGInActiveDarkInnerShadowParams());
                    case 16:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogInnerShadowParams());
                    case 17:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
                    case 18:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 19:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getActiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSeekbarInActiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSeekBarInActiveDarkInnerShadowParams());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getDialogDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getDialogDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getDialogDarkInnerShadowParams());
                    case 22:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveInnerShadowParams());
                    case 23:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getMediaOutPutDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getTileInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getInactiveDarkInnerShadowParams());
                    case 24:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSimpleHeaderDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
                    case 25:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveInnerShadowParams());
                    case 26:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getHighlightInactiveDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getHighlightInactiveDarkInnerShadowParams());
                    default:
                        return new GradientStrokeLineAdapter.StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getCommonOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getCommonInnerShadowParams());
                }
            }
        });
    }

    public static final void adaptStrokeCornerParams(BlurConfig blurConfig, float f, Float f2, boolean z) {
        float fFloatValue;
        CornerParams gradientStrokeCornerParam = blurConfig.getGradientStrokeCornerParam();
        gradientStrokeCornerParam.setType(z ? CornerType.FULL : CornerType.G2);
        gradientStrokeCornerParam.setRadius(f);
        if (f2 != null) {
            fFloatValue = f2.floatValue();
        } else {
            fFloatValue = z ? 1.0f : 3.0f;
        }
        gradientStrokeCornerParam.setWeight(fFloatValue);
    }

    public static final void adaptStrokeLineParams(BlurConfig blurConfig, StrokeParamsTemplate strokeParamsTemplate) {
        blurConfig.setGradientStrokeLineParam(strokeParamsTemplate != null ? strokeParamsTemplate.getStrokeParams() : null);
        blurConfig.setOpticsParams(strokeParamsTemplate != null ? strokeParamsTemplate.getOpticsParams() : null);
        blurConfig.setInnerShadowParams(strokeParamsTemplate != null ? strokeParamsTemplate.getInnerShadowParams() : null);
    }

    public static final void clearGradientStrokeParams(BlurConfig blurConfig) {
        blurConfig.setGradientStrokeLineParam(null);
        blurConfig.setOpticsParams(null);
        blurConfig.setInnerShadowParams(null);
        CornerParams gradientStrokeCornerParam = blurConfig.getGradientStrokeCornerParam();
        gradientStrokeCornerParam.setType(CornerType.FULL);
        gradientStrokeCornerParam.setRadius(0.0f);
        gradientStrokeCornerParam.setWeight(1.0f);
    }

    public static final StrokeParamsTemplate getCommonInactiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getCommonInactiveTemplateDark() : INSTANCE.getCommonInactiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getMediaOutPutStrokeParamsTemplate(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getMediaOutPutStrokeParamsTemplateDark() : INSTANCE.getMediaOutPutStrokeParamsTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getNotificationBlurLightConfig(boolean z, boolean z2) {
        if (!z) {
            return null;
        }
        GradientStrokeLineAdapter gradientStrokeLineAdapter = INSTANCE;
        return z2 ? gradientStrokeLineAdapter.getQuickEntranceTemplateDark() : gradientStrokeLineAdapter.getQuickEntranceTemplate();
    }

    public static final StrokeParamsTemplate getQSDetailPanelBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getDetailPanelTemplateDark() : INSTANCE.getDetailPanelTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQSHighlightInActiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getHighlightInActiveTemplateDark() : INSTANCE.getHighlightInActiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQSParamsTemplate(boolean z) {
        return z ? INSTANCE.getTileInActiveTemplateDark() : INSTANCE.getTileInActiveTemplate();
    }

    public static final StrokeParamsTemplate getQSPluginBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getPluginTemplateDark() : INSTANCE.getPluginTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQSTileActiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getTileActiveTemplateDark() : INSTANCE.getTileActiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQSTileInActiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getTileInActiveTemplateDark() : INSTANCE.getTileInActiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQsQuickEntranceBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getQuickEntranceTemplateDark() : INSTANCE.getQuickEntranceTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQsSeekBarBGBlurLightConfig(boolean z, boolean z2, boolean z3) {
        if (!z2) {
            return null;
        }
        if (z3) {
            return z ? INSTANCE.getSeekBarBGDialogTemplateDark() : INSTANCE.getSeekBarBGDialogTemplate();
        }
        return z ? INSTANCE.getSeekBarBGTemplateDark() : INSTANCE.getSeekBarBGTemplate();
    }

    public static final StrokeParamsTemplate getQsSeekBarPGBlurLightConfig(boolean z, boolean z2, boolean z3) {
        if (!z2) {
            return null;
        }
        if (z3) {
            return z ? INSTANCE.getSeekBarProgressDialogTemplateDark() : INSTANCE.getSeekBarProgressDialogTemplate();
        }
        return z ? INSTANCE.getSeekBarProgressTemplateDark() : INSTANCE.getSeekBarProgressTemplate();
    }

    public static final StrokeParamsTemplate getQsSepHighlightIconActiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getHighlightIconActiveTemplateDark() : INSTANCE.getHighlightIconActiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getQsSepHighlightIconInactiveBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getTileInActiveTemplateDark() : INSTANCE.getTileInActiveTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getSepEditModeRectButtonBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getSepEditModeRectButtonTemplateDark() : INSTANCE.getSepEditModeRectButtonTemplate();
        }
        return null;
    }

    public static final StrokeParamsTemplate getSepScenarioCapsuleBlurLightConfig(boolean z, boolean z2) {
        if (z2) {
            return z ? INSTANCE.getSepScenarioCapsuleTemplateDark() : INSTANCE.getSepScenarioCapsuleTemplate();
        }
        return null;
    }

    public final void adaptGradientStrokeParams(BlurConfig blurConfig, float f, Float f2, int i, int i2, boolean z, StrokeParamsTemplate strokeParamsTemplate) {
        boolean z2 = false;
        boolean z3 = strokeParamsTemplate != null;
        if (i > 0 && i2 > 0) {
            z2 = true;
        }
        if (!z3 || !z2) {
            clearGradientStrokeParams(blurConfig);
        } else {
            adaptStrokeCornerParams(blurConfig, f, f2, z);
            adaptStrokeLineParams(blurConfig, strokeParamsTemplate);
        }
    }

    public final StrokeParamsTemplate getCommonInactiveTemplate() {
        return (StrokeParamsTemplate) commonInactiveTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getCommonInactiveTemplateDark() {
        return (StrokeParamsTemplate) commonInactiveTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getDetailPanelTemplate() {
        return (StrokeParamsTemplate) detailPanelTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getDetailPanelTemplateDark() {
        return (StrokeParamsTemplate) detailPanelTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getHighlightIconActiveTemplate() {
        return (StrokeParamsTemplate) highlightIconActiveTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getHighlightIconActiveTemplateDark() {
        return (StrokeParamsTemplate) highlightIconActiveTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getHighlightInActiveTemplate() {
        return (StrokeParamsTemplate) highlightInActiveTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getHighlightInActiveTemplateDark() {
        return (StrokeParamsTemplate) highlightInActiveTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getMediaOutPutStrokeParamsTemplateDark() {
        return (StrokeParamsTemplate) mediaOutPutStrokeParamsTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getPluginTemplate() {
        return (StrokeParamsTemplate) pluginTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getPluginTemplateDark() {
        return (StrokeParamsTemplate) pluginTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getQuickEntranceTemplate() {
        return (StrokeParamsTemplate) quickEntranceTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getQuickEntranceTemplateDark() {
        return (StrokeParamsTemplate) quickEntranceTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarBGDialogTemplate() {
        return (StrokeParamsTemplate) seekBarBGDialogTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarBGDialogTemplateDark() {
        return (StrokeParamsTemplate) seekBarBGDialogTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarBGTemplate() {
        return (StrokeParamsTemplate) seekBarBGTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarBGTemplateDark() {
        return (StrokeParamsTemplate) seekBarBGTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarProgressDialogTemplate() {
        return (StrokeParamsTemplate) seekBarProgressDialogTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarProgressDialogTemplateDark() {
        return (StrokeParamsTemplate) seekBarProgressDialogTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarProgressTemplate() {
        return (StrokeParamsTemplate) seekBarProgressTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getSeekBarProgressTemplateDark() {
        return (StrokeParamsTemplate) seekBarProgressTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSepEditModeRectButtonTemplate() {
        return (StrokeParamsTemplate) sepEditModeRectButtonTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getSepEditModeRectButtonTemplateDark() {
        return (StrokeParamsTemplate) sepEditModeRectButtonTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getSepScenarioCapsuleTemplate() {
        return new StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepScenarioCapsuleOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderInnerShadowParams());
    }

    public final StrokeParamsTemplate getSepScenarioCapsuleTemplateDark() {
        return new StrokeParamsTemplate(MixColorTileStrokeLineAdapter.INSTANCE.getInactiveDarkStrokeLineParams(), MixColorTileOpticsAdapter.INSTANCE.getSepScenarioCapsuleDarkOpticsParams(), MixColorTileInnerShadowAdapter.INSTANCE.getSimpleHeaderDarkInnerShadowParams());
    }

    public final StrokeParamsTemplate getTileActiveTemplate() {
        return (StrokeParamsTemplate) tileActiveTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getTileActiveTemplateDark() {
        return (StrokeParamsTemplate) tileActiveTemplateDark$delegate.getValue();
    }

    public final StrokeParamsTemplate getTileInActiveTemplate() {
        return (StrokeParamsTemplate) tileInActiveTemplate$delegate.getValue();
    }

    public final StrokeParamsTemplate getTileInActiveTemplateDark() {
        return (StrokeParamsTemplate) tileInActiveTemplateDark$delegate.getValue();
    }

    public final void updateRatioAndSides(GradientStrokeLineParams gradientStrokeLineParams, int i, int i2, float f) {
        CacheType cacheType;
        if (i > i2) {
            float f2 = i2 / 2.0f;
            if (f < f2) {
                cacheType = CacheType.TWO_X_ONE_OPPO_RADIUS;
            } else {
                cacheType = f >= f2 ? CacheType.TWO_X_ONE_OPLUS_RADIUS : CacheType.DEFAULT_RADIUS;
            }
        } else if (i < i2) {
            float f3 = i / 2.0f;
            if (f < f3) {
                cacheType = CacheType.ONE_X_TWO_OPPO_RADIUS;
            } else {
                cacheType = f >= f3 ? CacheType.ONE_X_TWO_OPLUS_RADIUS : CacheType.DEFAULT_RADIUS;
            }
        } else {
            float f4 = i2 / 2.0f;
            if (f >= f4) {
                cacheType = CacheType.ONE_X_ONE_RADIUS;
            } else {
                cacheType = f < f4 ? CacheType.TWO_X_TWO_RADIUS : CacheType.DEFAULT_RADIUS;
            }
        }
        int i3 = i > i2 ? i2 : i;
        float f5 = ((((i * i2) / 2) - ((i3 * i3) / 2)) * 1.0f) / i3;
        float f6 = i >= i2 ? f5 + (i2 / 2) : (i2 / 2) - f5;
        float f7 = (i > i2 ? i2 : i) / 2.0f;
        if (f <= f7) {
            f7 = f;
        }
        if (cacheType != CacheType.ONE_X_ONE_RADIUS) {
            f6 -= f7 / 2.0f;
        }
        float f8 = i + i2;
        float f9 = ((f6 * 0.5f) / f8) + 0.5f;
        int iOrdinal = cacheType.ordinal();
        float f10 = ((i - ((iOrdinal == 1 || iOrdinal == 2) ? f7 / 2.0f : 2.0f * f7)) * 0.5f) / f8;
        float f11 = ((4.0f * f7) * 0.5f) / f8;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("calculateChangeRatio: w/h->");
            sb.append(i);
            sb.append('/');
            sb.append(i2);
            sb.append(", r->");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f, ", correctRadius->", f7, ", ratio->");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sb, f9, ", fade->", f10, ", fade2->");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f11, "GradientStrokeLineAdapter-->", "SystemUi--QuickSettings");
        }
        LineParams lineParams = lineParamsTemplate;
        gradientStrokeLineParams.setStrokeLineVerticalNearSolid(lineParams.getFadeToSides1());
        gradientStrokeLineParams.setStrokeLineVerticalNearFade(lineParams.getFadeToSides2());
        gradientStrokeLineParams.setStrokeLineTransverseNearSolid(lineParams.getFadeToSides1());
        gradientStrokeLineParams.setStrokeLineTransverseNearFade(lineParams.getFadeToSides2());
        gradientStrokeLineParams.setRatio(f9);
    }

    public static final StrokeParamsTemplate getNotificationBlurLightConfig(boolean z) {
        return getNotificationBlurLightConfig(true, z);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StrokeParamsTemplate {
        public final float alphaLT;
        public final float alphaRB;
        public final int color;
        private final InnerShadowParams innerShadowParams;
        public final int lineWidth;
        private final OpticsParams opticsParams;
        public final float ratio;
        private final GradientStrokeLineParams strokeParams;

        public StrokeParamsTemplate(int i, float f, float f2, float f3, int i2, GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams, OpticsParams opticsParams) {
            this.color = i;
            this.ratio = f;
            this.alphaLT = f2;
            this.alphaRB = f3;
            this.lineWidth = i2;
            this.strokeParams = gradientStrokeLineParams;
            this.innerShadowParams = innerShadowParams;
            this.opticsParams = opticsParams;
        }

        public final GradientStrokeLineParams component6() {
            return this.strokeParams;
        }

        public final InnerShadowParams component7() {
            return this.innerShadowParams;
        }

        public final OpticsParams component8() {
            return this.opticsParams;
        }

        public final StrokeParamsTemplate copy(int i, float f, float f2, float f3, int i2, GradientStrokeLineParams gradientStrokeLineParams, InnerShadowParams innerShadowParams, OpticsParams opticsParams) {
            return new StrokeParamsTemplate(i, f, f2, f3, i2, gradientStrokeLineParams, innerShadowParams, opticsParams);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StrokeParamsTemplate)) {
                return false;
            }
            StrokeParamsTemplate strokeParamsTemplate = (StrokeParamsTemplate) obj;
            return this.color == strokeParamsTemplate.color && Float.compare(this.ratio, strokeParamsTemplate.ratio) == 0 && Float.compare(this.alphaLT, strokeParamsTemplate.alphaLT) == 0 && Float.compare(this.alphaRB, strokeParamsTemplate.alphaRB) == 0 && this.lineWidth == strokeParamsTemplate.lineWidth && Intrinsics.areEqual(this.strokeParams, strokeParamsTemplate.strokeParams) && Intrinsics.areEqual(this.innerShadowParams, strokeParamsTemplate.innerShadowParams) && Intrinsics.areEqual(this.opticsParams, strokeParamsTemplate.opticsParams);
        }

        public final float getAlphaLT() {
            return this.alphaLT;
        }

        public final float getAlphaRB() {
            return this.alphaRB;
        }

        public final int getColor() {
            return this.color;
        }

        public final InnerShadowParams getInnerShadowParams() {
            return this.innerShadowParams;
        }

        public final int getLineWidth() {
            return this.lineWidth;
        }

        public final OpticsParams getOpticsParams() {
            return this.opticsParams;
        }

        public final float getRatio() {
            return this.ratio;
        }

        public final GradientStrokeLineParams getStrokeParams() {
            return this.strokeParams;
        }

        public final int hashCode() {
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.lineWidth, ChangeSize$.ExternalSyntheticOutline0.m(this.alphaRB, ChangeSize$.ExternalSyntheticOutline0.m(this.alphaLT, ChangeSize$.ExternalSyntheticOutline0.m(this.ratio, Integer.hashCode(this.color) * 31, 31), 31), 31), 31);
            GradientStrokeLineParams gradientStrokeLineParams = this.strokeParams;
            int iHashCode = (iM + (gradientStrokeLineParams == null ? 0 : gradientStrokeLineParams.hashCode())) * 31;
            InnerShadowParams innerShadowParams = this.innerShadowParams;
            int iHashCode2 = (iHashCode + (innerShadowParams == null ? 0 : innerShadowParams.hashCode())) * 31;
            OpticsParams opticsParams = this.opticsParams;
            return iHashCode2 + (opticsParams != null ? opticsParams.hashCode() : 0);
        }

        public String toString() {
            return "StrokeParamsTemplate(color=" + this.color + ", ratio=" + this.ratio + ", alphaLT=" + this.alphaLT + ", alphaRB=" + this.alphaRB + ", lineWidth=" + this.lineWidth + ", strokeParams=" + this.strokeParams + ", innerShadowParams=" + this.innerShadowParams + ", opticsParams=" + this.opticsParams + ')';
        }

        public /* synthetic */ StrokeParamsTemplate(GradientStrokeLineParams gradientStrokeLineParams, OpticsParams opticsParams, InnerShadowParams innerShadowParams) {
            this(-1, 0.0f, 0.0f, 0.0f, 0, gradientStrokeLineParams, innerShadowParams, opticsParams);
        }
    }

    public static final StrokeParamsTemplate getMediaOutPutStrokeParamsTemplate(boolean z) {
        return getMediaOutPutStrokeParamsTemplate(z, true);
    }

    public final StrokeParamsTemplate getMediaOutPutStrokeParamsTemplate() {
        return (StrokeParamsTemplate) mediaOutPutStrokeParamsTemplate$delegate.getValue();
    }
}
