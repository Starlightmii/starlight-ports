package com.oplus.systemui.volume.utils;

import android.content.Context;
import com.oplus.systemui.volume.utils.material.VolumeBlurManager;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumePanelTiming {
    public static final OplusVolumePanelTiming INSTANCE = new OplusVolumePanelTiming();

    public static final long getPanelHideDurationMs(Context context, int i) {
        if (i < 1) {
            i = 1;
        }
        int i2 = i - 1;
        if (i2 < 0) {
            i2 = 0;
        }
        return resolvePanelHideItemDurationMs(context, 0) + (((long) i2) * 15);
    }

    public static final int resolveAnimTotalTime(Context context) {
        if (VolumeBlurManager.INSTANCE.isGaussianBlurDisable(context)) {
            return 650;
        }
        if (!VolumeLayoutUtil.isLandscape(context)) {
            return ZoomParameterHelper.MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            return ZoomParameterHelper.MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP;
        }
        if (!FeatureOption.isLargeScreenFolding() || DeviceStateController.getInstance().isFolding()) {
            return 1100;
        }
        return ZoomParameterHelper.MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP;
    }

    public static final long resolveDismissFallbackTimeoutMs(Context context, int i, float f) {
        float fMax = Math.max(VolumeAnimUtil.DURATION_ROW_HIDE, getPanelHideDurationMs(context, i));
        if (f < 1.0f) {
            f = 1.0f;
        }
        return ((long) (fMax * f)) + 200;
    }

    public static final long resolveExpandItemDurationMs(Context context, int i) {
        long jResolveAnimTotalTime = ((long) resolveAnimTotalTime(context)) - (((long) i) * 100);
        if (jResolveAnimTotalTime < 0) {
            return 0L;
        }
        return jResolveAnimTotalTime;
    }

    public static final long resolveInterruptPanelHideDurationMs(Context context, int i, float f) {
        long panelHideDurationMs = (long) (getPanelHideDurationMs(context, i) * f);
        if (panelHideDurationMs < 300) {
            return 300L;
        }
        return panelHideDurationMs;
    }

    public static final long resolvePanelCollapseItemDurationMs(Context context, int i, boolean z, float f) {
        long jResolvePanelHideItemDurationMs = resolvePanelHideItemDurationMs(context, i);
        if (!z) {
            if (jResolvePanelHideItemDurationMs < 0) {
                return 0L;
            }
            return jResolvePanelHideItemDurationMs;
        }
        long j = (long) (jResolvePanelHideItemDurationMs * f);
        if (j < 300) {
            j = 300;
        }
        if (j < 0) {
            return 0L;
        }
        return j;
    }

    public static final long resolvePanelExpandReferenceDurationMs(Context context) {
        return getPanelHideDurationMs(context, 1) + 200;
    }

    public static final long resolvePanelHideItemDurationMs(Context context, int i) {
        return Math.max(100L, (((long) (resolveAnimTotalTime(context) - (i * 100))) * 2) / 3);
    }
}
