package com.oplus.zoom.common.zoomparameter;

import android.graphics.Rect;
import com.oplus.zoom.common.ZoomPositionInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ZoomParameter {
    public static final int SCREEN_LARGE = 3;
    public static final int SCREEN_MIDDLE = 2;
    public static final int SCREEN_SMALL = 1;

    ZoomPositionInfo getDefaultMiniPositionInfo(boolean z, boolean z2);

    Rect getDefaultMiniZoomRectForDisplayChange(Rect rect, int i, ZoomPositionInfo zoomPositionInfo);

    float getDefaultMiniZoomScale(boolean z, boolean z2);

    Rect getDefaultMiniZoomScaleRect(boolean z, boolean z2);

    ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z, boolean z2);

    Rect getDefaultSuperMiniZoomScaleRect(boolean z, boolean z2);

    Rect getDefaultZoomBound(boolean z, boolean z2);

    ZoomPositionInfo getDefaultZoomPositionInfo(boolean z, boolean z2);

    ZoomPositionInfo getDefaultZoomPositionInfoInHandle(boolean z, boolean z2, boolean z3);

    float getDefaultZoomScale(boolean z, boolean z2);

    Rect getDefaultZoomScaleRect(boolean z, boolean z2);

    int getLeftRightLimitInMini(boolean z);

    int getLeftRightLimitInSuperMini(boolean z);

    float getMaxMiniZoomScale(boolean z, boolean z2);

    float getMaxZoomScale(boolean z, boolean z2);

    float getMinMiniZoomScale(boolean z, boolean z2);

    int getScreenType();

    float getSuperMiniZoomScale(boolean z);

    int getTopBottomLimitInMini(boolean z);

    int getTopBottomLimitInSuperMini(boolean z);

    int getTopLimitInZoom(boolean z);

    int getZoomCornerRadius(boolean z);

    void initZoomDefaultInfo();
}
