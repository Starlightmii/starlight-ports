package com.oplus.zoom.common.zoomparameter;

import android.content.Context;
import android.graphics.Rect;
import com.android.internal.policy.SystemBarUtils;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.common.ZoomUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class LargeScreenZoomParameter implements ZoomParameter {
    public static final int LARGE_CORNER_ZOOM_DP = 22;
    public static final float LARGE_LAND_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float LARGE_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int LARGE_LAND_SCREEN_LAND_APP_ZOOM_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_LAND_SCREEN_LAND_APP_ZOOM_TOP_OFFSET_DP = 48;
    public static final int LARGE_LAND_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_LAND_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 48;
    public static final float LARGE_LAND_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float LARGE_LAND_SCREEN_PORT_APP_ZOOM_RATIO = 0.5625f;
    public static final int LARGE_LAND_SCREEN_PORT_APP_ZOOM_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_LAND_SCREEN_PORT_APP_ZOOM_TOP_OFFSET_DP = 48;
    public static final int LARGE_LAND_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 25;
    public static final int LARGE_LAND_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 49;
    public static final int LARGE_LAND_SCREEN_ZOOM_TOP_LIMIT_DP = 25;
    public static final float LARGE_PORT_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float LARGE_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int LARGE_PORT_SCREEN_LAND_APP_ZOOM_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_PORT_SCREEN_LAND_APP_ZOOM_TOP_OFFSET_DP = 48;
    public static final int LARGE_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_PORT_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 48;
    public static final float LARGE_PORT_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float LARGE_PORT_SCREEN_PORT_APP_ZOOM_RATIO = 0.5625f;
    public static final int LARGE_PORT_SCREEN_PORT_APP_ZOOM_RIGHT_OFFSET_DP = 24;
    public static final int LARGE_PORT_SCREEN_PORT_APP_ZOOM_TOP_OFFSET_DP = 48;
    public static final int LARGE_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 25;
    public static final int LARGE_PORT_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 49;
    public static final int LARGE_PORT_SCREEN_ZOOM_TOP_LIMIT_DP = 35;
    public static final int LARGE_SCREEN_BASE_WIDTH_DP = 432;
    private Context mContext;
    private int mDisplayId;
    private Rect mDefaultSuperMiniBoundPortScreenPortApp = new Rect();
    private Rect mDefaultSuperMiniBoundLandScreenPortApp = new Rect();
    private Rect mDefaultSuperMiniBoundPortScreenLandApp = new Rect();
    private Rect mDefaultSuperMiniBoundLandScreenLandApp = new Rect();
    private Rect mSuperMiniScaleRectPortScreenPortApp = new Rect();
    private Rect mSuperMiniScaleRectPortScreenLandApp = new Rect();
    private Rect mSuperMiniScaleRectLandScreenPortApp = new Rect();
    private Rect mSuperMiniScaleRectLandScreenLandApp = new Rect();
    private int mScreenType = 3;
    private Rect mDefaultBoundPortScreenPortApp = new Rect();
    private Rect mDefaultBoundLandScreenPortApp = new Rect();
    private Rect mDefaultBoundPortScreenLandApp = new Rect();
    private Rect mDefaultBoundLandScreenLandApp = new Rect();
    private Rect mZoomScaleRectPortScreenPortApp = new Rect();
    private Rect mZoomScaleRectPortScreenLandApp = new Rect();
    private Rect mZoomScaleRectLandScreenPortApp = new Rect();
    private Rect mZoomScaleRectLandScreenLandApp = new Rect();
    private Rect mZoomScaleRectPortScreenPortAppLeft = new Rect();
    private Rect mZoomScaleRectPortScreenPortAppRight = new Rect();
    private Rect mZoomScaleRectPortScreenLandAppLeft = new Rect();
    private Rect mZoomScaleRectPortScreenLandAppRight = new Rect();
    private Rect mZoomScaleRectLandScreenPortAppLeft = new Rect();
    private Rect mZoomScaleRectLandScreenPortAppRight = new Rect();
    private Rect mZoomScaleRectLandScreenLandAppLeft = new Rect();
    private Rect mZoomScaleRectLandScreenLandAppRight = new Rect();
    private Rect mMiniScaleRectPortScreenPortApp = new Rect();
    private Rect mMiniScaleRectPortScreenLandApp = new Rect();
    private Rect mMiniScaleRectLandScreenPortApp = new Rect();
    private Rect mMiniScaleRectLandScreenLandApp = new Rect();
    private ScreenGridUtils mScreenGrid = new ScreenGridUtils(this.mScreenType);

    public LargeScreenZoomParameter(Context context, int i) {
        this.mContext = context;
        this.mDisplayId = i;
    }

    private Rect getDefaultZoomScaleRectInLeft(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mZoomScaleRectPortScreenLandAppLeft : this.mZoomScaleRectPortScreenPortAppLeft;
        }
        return z ? this.mZoomScaleRectLandScreenLandAppLeft : this.mZoomScaleRectLandScreenPortAppLeft;
    }

    private Rect getDefaultZoomScaleRectInRight(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mZoomScaleRectPortScreenLandAppRight : this.mZoomScaleRectPortScreenPortAppRight;
        }
        return z ? this.mZoomScaleRectLandScreenLandAppRight : this.mZoomScaleRectLandScreenPortAppRight;
    }

    private float getDensity() {
        return ZoomDisplayController.getInstance().getDensity();
    }

    private int getRotation() {
        return ZoomDisplayController.getInstance().getRotation();
    }

    private int getScreenHeight() {
        return ZoomDisplayController.getInstance().getScreenHeight();
    }

    private int getScreenWidth() {
        return ZoomDisplayController.getInstance().getScreenWidth();
    }

    private void initDefaultMiniZoomScaleRect() {
        int topBottomLimitInMini = getTopBottomLimitInMini(isPortScreen());
        int leftRightLimitInMini = getLeftRightLimitInMini(isPortScreen());
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        getDensity();
        int i = screenWidth - leftRightLimitInMini;
        this.mMiniScaleRectPortScreenPortApp.set(i - ((int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sLargePortScreenPortAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sLargePortScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectPortScreenLandApp.set(i - ((int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sLargePortScreenLandAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sLargePortScreenLandAppMiniScale)) + topBottomLimitInMini);
        int i2 = screenHeight - leftRightLimitInMini;
        this.mMiniScaleRectLandScreenPortApp.set(i2 - ((int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sLargeLandScreenPortAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sLargeLandScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectLandScreenLandApp.set(i2 - ((int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sLargeLandScreenLandAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sLargeLandScreenLandAppMiniScale)) + topBottomLimitInMini);
    }

    private void initDefaultSuperMiniZoomScaleRect() {
        int topBottomLimitInSuperMini = getTopBottomLimitInSuperMini(isPortScreen());
        int leftRightLimitInSuperMini = getLeftRightLimitInSuperMini(isPortScreen());
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        getDensity();
        int i = screenWidth - leftRightLimitInSuperMini;
        this.mSuperMiniScaleRectPortScreenPortApp.set(i - ((int) (this.mDefaultBoundPortScreenPortApp.width() * 0.2f)), topBottomLimitInSuperMini, i, ((int) (this.mDefaultBoundPortScreenPortApp.height() * 0.2f)) + topBottomLimitInSuperMini);
        this.mSuperMiniScaleRectPortScreenLandApp.set(i - ((int) (this.mDefaultBoundPortScreenLandApp.width() * 0.2f)), topBottomLimitInSuperMini, i, ((int) (this.mDefaultBoundPortScreenLandApp.height() * 0.2f)) + topBottomLimitInSuperMini);
        int i2 = screenHeight - leftRightLimitInSuperMini;
        this.mSuperMiniScaleRectLandScreenPortApp.set(i2 - ((int) (this.mDefaultBoundLandScreenPortApp.width() * 0.2f)), topBottomLimitInSuperMini, i2, ((int) (this.mDefaultBoundLandScreenPortApp.height() * 0.2f)) + topBottomLimitInSuperMini);
        this.mSuperMiniScaleRectLandScreenLandApp.set(i2 - ((int) (this.mDefaultBoundLandScreenLandApp.width() * 0.2f)), topBottomLimitInSuperMini, i2, ((int) (this.mDefaultBoundLandScreenLandApp.height() * 0.2f)) + topBottomLimitInSuperMini);
    }

    private void initDefaultZoomBound() {
        if (isPortScreen()) {
            getScreenWidth();
        } else {
            getScreenHeight();
        }
        if (isPortScreen()) {
            getScreenHeight();
        } else {
            getScreenWidth();
        }
        float density = getDensity();
        int iDip2px = ZoomUtil.dip2px(density, 432.0f);
        this.mDefaultBoundPortScreenPortApp.set(0, 0, iDip2px, (int) (iDip2px / 0.5625f));
        int iDip2px2 = ZoomUtil.dip2px(density, 432.0f);
        this.mDefaultBoundLandScreenPortApp.set(0, 0, iDip2px2, (int) (iDip2px2 / 0.5625f));
        int iDip2px3 = ZoomUtil.dip2px(density, 432.0f);
        this.mDefaultBoundPortScreenLandApp.set(0, 0, (int) (iDip2px3 * 1.7777778f), iDip2px3);
        int iDip2px4 = ZoomUtil.dip2px(density, 432.0f);
        this.mDefaultBoundLandScreenLandApp.set(0, 0, (int) (iDip2px4 * 1.7777778f), iDip2px4);
    }

    private void initDefaultZoomScaleRect() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        int statusBarHeight = SystemBarUtils.getStatusBarHeight(this.mContext);
        float density = getDensity();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sLargePortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sLargePortScreenPortAppZoomScale);
        int iDip2px = (screenWidth - iWidth) - ZoomUtil.dip2px(density, 24.0f);
        int iDip2px2 = ZoomUtil.dip2px(density, 48.0f);
        this.mZoomScaleRectPortScreenPortApp.set(iDip2px, iDip2px2, iWidth + iDip2px, iHeight + iDip2px2);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sLargePortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sLargePortScreenLandAppZoomScale);
        int iDip2px3 = (screenWidth - iWidth2) - ZoomUtil.dip2px(density, 24.0f);
        int iDip2px4 = ZoomUtil.dip2px(density, 48.0f);
        this.mZoomScaleRectPortScreenLandApp.set(iDip2px3, iDip2px4, iWidth2 + iDip2px3, iHeight2 + iDip2px4);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sLargeLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sLargeLandScreenPortAppZoomScale);
        int iDip2px5 = (screenHeight - iWidth3) - ZoomUtil.dip2px(density, 24.0f);
        int iDip2px6 = ZoomUtil.dip2px(density, 48.0f);
        this.mZoomScaleRectLandScreenPortApp.set(iDip2px5, iDip2px6, iWidth3 + iDip2px5, iHeight3 + iDip2px6);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sLargeLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sLargeLandScreenLandAppZoomScale);
        int iDip2px7 = (screenHeight - iWidth4) - ZoomUtil.dip2px(density, 24.0f);
        int iDip2px8 = ZoomUtil.dip2px(density, 48.0f);
        this.mZoomScaleRectLandScreenLandApp.set(iDip2px7, Math.max(iDip2px8, statusBarHeight), iWidth4 + iDip2px7, iHeight4 + iDip2px8);
    }

    private void initDefaultZoomScaleRectInHandle() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        float density = getDensity();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sLargePortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sLargePortScreenPortAppZoomScale);
        int iDip2px = ZoomUtil.dip2px(density, 24.0f);
        int iDip2px2 = ZoomUtil.dip2px(density, 48.0f);
        int i = iHeight + iDip2px2;
        this.mZoomScaleRectPortScreenPortAppLeft.set(iDip2px, iDip2px2, iDip2px + iWidth, i);
        this.mZoomScaleRectPortScreenPortAppRight.set((screenWidth - iWidth) - iDip2px, iDip2px2, screenWidth - iDip2px, i);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sLargePortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sLargePortScreenLandAppZoomScale);
        int iDip2px3 = ZoomUtil.dip2px(density, 24.0f);
        int iDip2px4 = ZoomUtil.dip2px(density, 48.0f);
        int i2 = iHeight2 + iDip2px4;
        this.mZoomScaleRectPortScreenLandAppLeft.set(iDip2px3, iDip2px4, iDip2px3 + iWidth2, i2);
        this.mZoomScaleRectPortScreenLandAppRight.set((screenWidth - iWidth2) - iDip2px3, iDip2px4, screenWidth - iDip2px3, i2);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sLargeLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sLargeLandScreenPortAppZoomScale);
        int iDip2px5 = ZoomUtil.dip2px(density, 24.0f);
        int iDip2px6 = ZoomUtil.dip2px(density, 48.0f);
        int i3 = iHeight3 + iDip2px6;
        this.mZoomScaleRectLandScreenPortAppLeft.set(iDip2px5, iDip2px6, iDip2px5 + iWidth3, i3);
        this.mZoomScaleRectLandScreenPortAppRight.set((screenHeight - iWidth3) - iDip2px5, iDip2px6, screenHeight - iDip2px5, i3);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sLargeLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sLargeLandScreenLandAppZoomScale);
        int i4 = screenHeight - iWidth4;
        int iDip2px7 = i4 - ZoomUtil.dip2px(density, 24.0f);
        int iDip2px8 = ZoomUtil.dip2px(density, 48.0f);
        int i5 = iHeight4 + iDip2px8;
        this.mZoomScaleRectLandScreenLandAppLeft.set(iDip2px7, iDip2px8, iWidth4 + iDip2px7, i5);
        this.mZoomScaleRectLandScreenLandAppRight.set(i4 - iDip2px7, iDip2px8, screenHeight - iDip2px7, i5);
    }

    private boolean isPortScreen() {
        return getRotation() == 0 || getRotation() == 2;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public ZoomPositionInfo getDefaultMiniPositionInfo(boolean z, boolean z2) {
        return new ZoomPositionInfo(getDefaultMiniZoomScaleRect(z, z2), getDefaultMiniZoomScale(z, z2));
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public Rect getDefaultMiniZoomRectForDisplayChange(Rect rect, int i, ZoomPositionInfo zoomPositionInfo) {
        int topBottomLimitInMini;
        int screenHeight;
        int topBottomLimitInMini2;
        int screenWidth = 0;
        Rect defaultMiniZoomScaleRect = getDefaultMiniZoomScaleRect(rect.width() > rect.height(), isPortScreen());
        int iWidth = defaultMiniZoomScaleRect.width();
        int iHeight = defaultMiniZoomScaleRect.height();
        if (i == 1) {
            screenWidth = getLeftRightLimitInMini(isPortScreen());
            topBottomLimitInMini = getTopBottomLimitInMini(isPortScreen());
        } else {
            if (i == 2) {
                screenWidth = getLeftRightLimitInMini(isPortScreen());
                screenHeight = getScreenHeight() - iHeight;
                topBottomLimitInMini2 = getTopBottomLimitInMini(isPortScreen());
            } else if (i == 3) {
                screenWidth = (getScreenWidth() - iWidth) - getLeftRightLimitInMini(isPortScreen());
                topBottomLimitInMini = getTopBottomLimitInMini(isPortScreen());
            } else if (i == 4) {
                screenWidth = (getScreenWidth() - iWidth) - getLeftRightLimitInMini(isPortScreen());
                screenHeight = getScreenHeight() - iHeight;
                topBottomLimitInMini2 = getTopBottomLimitInMini(isPortScreen());
            } else {
                topBottomLimitInMini = 0;
            }
            topBottomLimitInMini = screenHeight - topBottomLimitInMini2;
        }
        return new Rect(screenWidth, topBottomLimitInMini, iWidth + screenWidth, iHeight + topBottomLimitInMini);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getDefaultMiniZoomScale(boolean z, boolean z2) {
        return this.mScreenGrid.getDefaultMiniZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public Rect getDefaultMiniZoomScaleRect(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mMiniScaleRectPortScreenLandApp : this.mMiniScaleRectPortScreenPortApp;
        }
        return z ? this.mMiniScaleRectLandScreenLandApp : this.mMiniScaleRectLandScreenPortApp;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z, boolean z2) {
        return new ZoomPositionInfo(getDefaultSuperMiniZoomScaleRect(z, z2), getSuperMiniZoomScale(z));
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public Rect getDefaultSuperMiniZoomScaleRect(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mSuperMiniScaleRectPortScreenLandApp : this.mSuperMiniScaleRectPortScreenPortApp;
        }
        return z ? this.mSuperMiniScaleRectLandScreenLandApp : this.mSuperMiniScaleRectLandScreenPortApp;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public Rect getDefaultZoomBound(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mDefaultBoundPortScreenLandApp : this.mDefaultBoundPortScreenPortApp;
        }
        return z ? this.mDefaultBoundLandScreenLandApp : this.mDefaultBoundLandScreenPortApp;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public ZoomPositionInfo getDefaultZoomPositionInfo(boolean z, boolean z2) {
        return new ZoomPositionInfo(getDefaultZoomScaleRect(z, z2), getDefaultZoomScale(z, z2));
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public ZoomPositionInfo getDefaultZoomPositionInfoInHandle(boolean z, boolean z2, boolean z3) {
        return new ZoomPositionInfo(z3 ? getDefaultZoomScaleRectInLeft(z, z2) : getDefaultZoomScaleRectInRight(z, z2), getDefaultZoomScale(z, z2));
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getDefaultZoomScale(boolean z, boolean z2) {
        return this.mScreenGrid.getDefaultZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public Rect getDefaultZoomScaleRect(boolean z, boolean z2) {
        if (z2) {
            return z ? this.mZoomScaleRectPortScreenLandApp : this.mZoomScaleRectPortScreenPortApp;
        }
        return z ? this.mZoomScaleRectLandScreenLandApp : this.mZoomScaleRectLandScreenPortApp;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getLeftRightLimitInMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 24.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getLeftRightLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 25.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMaxMiniZoomScale(boolean z, boolean z2) {
        return this.mScreenGrid.getMaxMiniZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMaxZoomScale(boolean z, boolean z2) {
        return this.mScreenGrid.getMaxZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMinMiniZoomScale(boolean z, boolean z2) {
        return this.mScreenGrid.getMinMiniZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getScreenType() {
        return this.mScreenType;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getSuperMiniZoomScale(boolean z) {
        isPortScreen();
        return 0.2f;
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopBottomLimitInMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 48.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopBottomLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 49.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopLimitInZoom(boolean z) {
        return SystemBarUtils.getStatusBarHeight(this.mContext);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getZoomCornerRadius(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 22.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public void initZoomDefaultInfo() {
        initDefaultZoomBound();
        initDefaultZoomScaleRect();
        initDefaultZoomScaleRectInHandle();
        initDefaultMiniZoomScaleRect();
        initDefaultSuperMiniZoomScaleRect();
    }
}
