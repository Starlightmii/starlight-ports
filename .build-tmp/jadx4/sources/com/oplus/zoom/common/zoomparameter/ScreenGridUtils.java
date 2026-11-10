package com.oplus.zoom.common.zoomparameter;

import com.oplus.splitscreen.SplitToggleHelper;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ScreenGridUtils {
    public static final float LARGE_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final float LARGE_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int LARGE_SCREEN_BASE_WIDTH_DP = 432;
    public static final int LARGE_SCREEN_GRID_NUMBER = 6;
    public static final int LARGE_SYSTEM_GRID_MARGIN = 40;
    public static final int LARGE_SYSTEM_GRID_NUMBER = 12;
    public static final int LARGE_SYSTEM_GRID_SPACING = 12;
    public static final float MIDDLE_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final float MIDDLE_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int MIDDLE_SCREEN_BASE_WIDTH_DP = 360;
    public static final int MIDDLE_SCREEN_GRID_NUMBER = 4;
    public static final int MIDDLE_SYSTEM_GRID_MARGIN = 24;
    public static final int MIDDLE_SYSTEM_GRID_NUMBER = 8;
    public static final int MIDDLE_SYSTEM_GRID_SPACING = 8;
    public static final float SMALL_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.6666666f;
    public static final float SMALL_LAND_SCREEN_PORT_APP_ZOOM_RATIO = 0.6923077f;
    private static final int SMALL_LAND_SCREEN_TOP_AND_BOTTOM_MARGIN = 24;
    public static final int SMALL_PORT_SCREEN_GRID_NUMBER = 4;
    public static final float SMALL_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.6666666f;
    public static final int SMALL_SYSTEM_GRID_MARGIN = 16;
    public static final int SMALL_SYSTEM_GRID_NUMBER = 4;
    public static final int SMALL_SYSTEM_GRID_SPACING = 8;
    public static float sLargeLandScreenLandAppMaxMiniScale;
    public static float sLargeLandScreenLandAppMaxZoomScale;
    public static float sLargeLandScreenLandAppMiniScale;
    public static float sLargeLandScreenLandAppZoomScale;
    public static float sLargeLandScreenPortAppMaxMiniScale;
    public static float sLargeLandScreenPortAppMaxZoomScale;
    public static float sLargeLandScreenPortAppMiniScale;
    public static float sLargeLandScreenPortAppZoomScale;
    public static float sLargePortScreenLandAppMaxMiniScale;
    public static float sLargePortScreenLandAppMaxZoomScale;
    public static float sLargePortScreenLandAppMiniScale;
    public static float sLargePortScreenLandAppZoomScale;
    public static float sLargePortScreenPortAppMaxMiniScale;
    public static float sLargePortScreenPortAppMaxZoomScale;
    public static float sLargePortScreenPortAppMiniScale;
    public static float sLargePortScreenPortAppZoomScale;
    public static float sMiddleLandScreenLandAppMaxMiniScale;
    public static float sMiddleLandScreenLandAppMaxZoomScale;
    public static float sMiddleLandScreenLandAppMiniScale;
    public static float sMiddleLandScreenLandAppZoomScale;
    public static float sMiddleLandScreenPortAppMaxMiniScale;
    public static float sMiddleLandScreenPortAppMaxZoomScale;
    public static float sMiddleLandScreenPortAppMiniScale;
    public static float sMiddleLandScreenPortAppZoomScale;
    public static float sMiddlePortScreenLandAppMaxMiniScale;
    public static float sMiddlePortScreenLandAppMaxZoomScale;
    public static float sMiddlePortScreenLandAppMiniScale;
    public static float sMiddlePortScreenLandAppZoomScale;
    public static float sMiddlePortScreenPortAppMaxMiniScale;
    public static float sMiddlePortScreenPortAppMaxZoomScale;
    public static float sMiddlePortScreenPortAppMiniScale;
    public static float sMiddlePortScreenPortAppZoomScale;
    public static float sSmallLandScreenLandAppMaxMiniScale;
    public static float sSmallLandScreenLandAppMaxZoomScale;
    public static float sSmallLandScreenLandAppMiniScale;
    public static float sSmallLandScreenLandAppZoomScale;
    public static float sSmallLandScreenPortAppMaxMiniScale;
    public static float sSmallLandScreenPortAppMaxZoomScale;
    public static float sSmallLandScreenPortAppMiniScale;
    public static float sSmallLandScreenPortAppZoomScale;
    public static float sSmallPortScreenLandAppMaxMiniScale;
    public static float sSmallPortScreenLandAppMaxZoomScale;
    public static float sSmallPortScreenLandAppMiniScale;
    public static float sSmallPortScreenLandAppZoomScale;
    public static float sSmallPortScreenPortAppMaxMiniScale;
    public static float sSmallPortScreenPortAppMaxZoomScale;
    public static float sSmallPortScreenPortAppMiniScale;
    public static float sSmallPortScreenPortAppZoomScale;
    private int mScreenType;
    public final int sSmallScreenBaseWidthDp = ZoomUtil.px2dip(getDensity(), Math.min(getScreenWidth(), getScreenHeight()));
    private int mScreenWidth = Math.min(getScreenWidth(), getScreenHeight());
    private int mScreenHeight = Math.max(getScreenWidth(), getScreenHeight());

    public ScreenGridUtils(int i) {
        this.mScreenType = i;
        if (i == 1) {
            computeSmallScreenZoomScale();
        } else if (i == 2) {
            computeMiddleScreenZoomScale();
        } else {
            if (i != 3) {
                return;
            }
            computeLargeScreenZoomScale();
        }
    }

    private void computeLargeScreenZoomScale() {
        float fPx2dip = ((((ZoomUtil.px2dip(getDensity(), this.mScreenWidth) - 212) / 12) * 6) + 60) / 432.0f;
        sLargePortScreenPortAppZoomScale = 0.9f * fPx2dip;
        sLargePortScreenLandAppZoomScale = (fPx2dip * 1.35f) / 1.7777778f;
        float fPx2dip2 = ((((ZoomUtil.px2dip(getDensity(), this.mScreenHeight) - 212) / 12) * 6) + 60) / 432.0f;
        float f = 0.6f * fPx2dip2;
        sLargeLandScreenPortAppZoomScale = f;
        float f2 = (fPx2dip2 * 1.0f) / 1.7777778f;
        sLargeLandScreenLandAppZoomScale = f2;
        float f3 = sLargePortScreenPortAppZoomScale;
        sLargePortScreenPortAppMiniScale = 0.4f * f3;
        float f4 = sLargePortScreenLandAppZoomScale;
        sLargePortScreenLandAppMiniScale = f4 * 0.5f;
        sLargeLandScreenPortAppMiniScale = 0.45f * f;
        sLargeLandScreenLandAppMiniScale = 0.5f * f2;
        sLargePortScreenPortAppMaxZoomScale = f3 * 1.4f;
        sLargePortScreenLandAppMaxZoomScale = f4 * 1.4f;
        sLargeLandScreenPortAppMaxZoomScale = 1.25f * f;
        sLargeLandScreenLandAppMaxZoomScale = 1.4f * f2;
        sLargePortScreenPortAppMaxMiniScale = f3 * 0.56f;
        sLargePortScreenLandAppMaxMiniScale = f4 * 0.65f;
        sLargeLandScreenPortAppMaxMiniScale = f * 0.56f;
        sLargeLandScreenLandAppMaxMiniScale = f2 * 0.7f;
    }

    private void computeMiddleScreenZoomScale() {
        int iPx2dip = ZoomUtil.px2dip(getDensity(), this.mScreenWidth);
        SplitToggleHelper.getInstance().getClass();
        if (SplitToggleHelper.isWhiteSwanDevice()) {
            iPx2dip = ZoomUtil.px2dip(getDensity(), this.mScreenHeight);
        }
        float f = ((((iPx2dip - 104) / 8) * 4) + 24) / 360.0f;
        sMiddlePortScreenPortAppZoomScale = f * 0.9f;
        sMiddlePortScreenLandAppZoomScale = (f * 1.35f) / 1.7777778f;
        int iPx2dip2 = ZoomUtil.px2dip(getDensity(), this.mScreenHeight);
        SplitToggleHelper.getInstance().getClass();
        if (SplitToggleHelper.isWhiteSwanDevice()) {
            iPx2dip2 = ZoomUtil.px2dip(getDensity(), this.mScreenWidth);
        }
        float f2 = ((((iPx2dip2 - 104) / 8) * 4) + 24) / 360.0f;
        float f3 = 0.9f * f2;
        sMiddleLandScreenPortAppZoomScale = f3;
        float f4 = (f2 * 1.35f) / 1.7777778f;
        sMiddleLandScreenLandAppZoomScale = f4;
        float f5 = sMiddlePortScreenPortAppZoomScale;
        sMiddlePortScreenPortAppMiniScale = f5 * 0.4f;
        float f6 = sMiddlePortScreenLandAppZoomScale;
        sMiddlePortScreenLandAppMiniScale = f6 * 0.4f;
        sMiddleLandScreenPortAppMiniScale = f3 * 0.4f;
        sMiddleLandScreenLandAppMiniScale = 0.4f * f4;
        sMiddlePortScreenPortAppMaxZoomScale = f5 * 1.3f;
        sMiddlePortScreenLandAppMaxZoomScale = f6 * 1.4f;
        sMiddleLandScreenPortAppMaxZoomScale = 1.3f * f3;
        sMiddleLandScreenLandAppMaxZoomScale = 1.4f * f4;
        sMiddlePortScreenPortAppMaxMiniScale = f5 * 0.5f;
        sMiddlePortScreenLandAppMaxMiniScale = f6 * 0.56f;
        sMiddleLandScreenPortAppMaxMiniScale = f3 * 0.5f;
        sMiddleLandScreenLandAppMaxMiniScale = f4 * 0.56f;
    }

    private void computeSmallScreenZoomScale() {
        int iPx2dip = ZoomUtil.px2dip(getDensity(), this.mScreenWidth);
        float f = ((((iPx2dip - 56) / 4) * 4) + 24) / this.sSmallScreenBaseWidthDp;
        sSmallPortScreenPortAppZoomScale = 0.8f * f;
        sSmallPortScreenLandAppZoomScale = (f * 0.9f) / 1.6666666f;
        float fPx2dip = (((ZoomUtil.px2dip(getDensity(), this.mScreenHeight) - 56) / 4) * 2) + 8;
        int i = this.sSmallScreenBaseWidthDp;
        float f2 = iPx2dip - 48.0f;
        float f3 = (0.6923077f * f2) / i;
        sSmallLandScreenPortAppZoomScale = f3;
        float f4 = ((fPx2dip / i) * 1.0f) / 1.6666666f;
        sSmallLandScreenLandAppZoomScale = f4;
        float f5 = sSmallPortScreenPortAppZoomScale;
        sSmallPortScreenPortAppMiniScale = 0.45f * f5;
        float f6 = sSmallPortScreenLandAppZoomScale;
        sSmallPortScreenLandAppMiniScale = f6 * 0.56f;
        sSmallLandScreenPortAppMiniScale = 0.6f * f3;
        sSmallLandScreenLandAppMiniScale = 0.5f * f4;
        sSmallPortScreenPortAppMaxZoomScale = f2 / i;
        sSmallPortScreenLandAppMaxZoomScale = (f2 / 1.6666666f) / i;
        sSmallLandScreenPortAppMaxZoomScale = f3;
        sSmallLandScreenLandAppMaxZoomScale = 1.25f * f4;
        sSmallPortScreenPortAppMaxMiniScale = f5 * 0.56f;
        sSmallPortScreenLandAppMaxMiniScale = f6 * 0.7f;
        sSmallLandScreenPortAppMaxMiniScale = f3 * 0.7f;
        sSmallLandScreenLandAppMaxMiniScale = f4 * 0.65f;
    }

    private float getDensity() {
        return ZoomDisplayController.getInstance().getDensity();
    }

    private int getScreenHeight() {
        return ZoomDisplayController.getInstance().getScreenHeight();
    }

    private int getScreenWidth() {
        return ZoomDisplayController.getInstance().getScreenWidth();
    }

    public float getDefaultMiniZoomScale(boolean z, boolean z2) {
        int i = this.mScreenType;
        if (i == 1) {
            if (z2) {
                return z ? sSmallPortScreenLandAppMiniScale : sSmallPortScreenPortAppMiniScale;
            }
            return z ? sSmallLandScreenLandAppMiniScale : sSmallLandScreenPortAppMiniScale;
        }
        if (i == 2) {
            if (z2) {
                return z ? sMiddlePortScreenLandAppMiniScale : sMiddlePortScreenPortAppMiniScale;
            }
            return z ? sMiddleLandScreenLandAppMiniScale : sMiddleLandScreenPortAppMiniScale;
        }
        if (i != 3) {
            return 1.0f;
        }
        if (z2) {
            return z ? sLargePortScreenLandAppMiniScale : sLargePortScreenPortAppMiniScale;
        }
        return z ? sLargeLandScreenLandAppMiniScale : sLargeLandScreenPortAppMiniScale;
    }

    public float getDefaultZoomScale(boolean z, boolean z2) {
        int i = this.mScreenType;
        if (i == 1) {
            if (z2) {
                return z ? sSmallPortScreenLandAppZoomScale : sSmallPortScreenPortAppZoomScale;
            }
            return z ? sSmallLandScreenLandAppZoomScale : sSmallLandScreenPortAppZoomScale;
        }
        if (i == 2) {
            if (z2) {
                return z ? sMiddlePortScreenLandAppZoomScale : sMiddlePortScreenPortAppZoomScale;
            }
            return z ? sMiddleLandScreenLandAppZoomScale : sMiddleLandScreenPortAppZoomScale;
        }
        if (i != 3) {
            return 1.0f;
        }
        if (z2) {
            return z ? sLargePortScreenLandAppZoomScale : sLargePortScreenPortAppZoomScale;
        }
        return z ? sLargeLandScreenLandAppZoomScale : sLargeLandScreenPortAppZoomScale;
    }

    public float getMaxMiniZoomScale(boolean z, boolean z2) {
        int i = this.mScreenType;
        if (i == 1) {
            if (z2) {
                return z ? sSmallPortScreenLandAppMaxMiniScale : sSmallPortScreenPortAppMaxMiniScale;
            }
            return z ? sSmallLandScreenLandAppMaxMiniScale : sSmallLandScreenPortAppMaxMiniScale;
        }
        if (i == 2) {
            if (z2) {
                return z ? sMiddlePortScreenLandAppMaxMiniScale : sMiddlePortScreenPortAppMaxMiniScale;
            }
            return z ? sMiddleLandScreenLandAppMaxMiniScale : sMiddleLandScreenPortAppMaxMiniScale;
        }
        if (i != 3) {
            return 1.0f;
        }
        if (z2) {
            return z ? sLargePortScreenLandAppMaxMiniScale : sLargePortScreenPortAppMaxMiniScale;
        }
        return z ? sLargeLandScreenLandAppMaxMiniScale : sLargeLandScreenPortAppMaxMiniScale;
    }

    public float getMaxZoomScale(boolean z, boolean z2) {
        int i = this.mScreenType;
        if (i == 1) {
            if (z2) {
                return z ? sSmallPortScreenLandAppMaxZoomScale : sSmallPortScreenPortAppMaxZoomScale;
            }
            return z ? sSmallLandScreenLandAppMaxZoomScale : sSmallLandScreenPortAppMaxZoomScale;
        }
        if (i == 2) {
            if (z2) {
                return z ? sMiddlePortScreenLandAppMaxZoomScale : sMiddlePortScreenPortAppMaxZoomScale;
            }
            return z ? sMiddleLandScreenLandAppMaxZoomScale : sMiddleLandScreenPortAppMaxZoomScale;
        }
        if (i != 3) {
            return 1.0f;
        }
        if (z2) {
            return z ? sLargePortScreenLandAppMaxZoomScale : sLargePortScreenPortAppMaxZoomScale;
        }
        return z ? sLargeLandScreenLandAppMaxZoomScale : sLargeLandScreenPortAppMaxZoomScale;
    }

    public float getMinMiniZoomScale(boolean z, boolean z2) {
        int i = this.mScreenType;
        if (i == 1) {
            if (z2) {
                return z ? sSmallPortScreenLandAppMiniScale : sSmallPortScreenPortAppMiniScale;
            }
            return z ? sSmallLandScreenLandAppMiniScale : sSmallLandScreenPortAppMiniScale;
        }
        if (i == 2) {
            if (z2) {
                return z ? sMiddlePortScreenLandAppMiniScale : sMiddlePortScreenPortAppMiniScale;
            }
            return z ? sMiddleLandScreenLandAppMiniScale : sMiddleLandScreenPortAppMiniScale;
        }
        if (i != 3) {
            return 1.0f;
        }
        if (z2) {
            return z ? sLargePortScreenLandAppMiniScale : sLargePortScreenPortAppMiniScale;
        }
        return z ? sLargeLandScreenLandAppMiniScale : sLargeLandScreenPortAppMiniScale;
    }
}
