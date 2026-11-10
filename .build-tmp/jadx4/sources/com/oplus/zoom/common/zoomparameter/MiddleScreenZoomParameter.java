package com.oplus.zoom.common.zoomparameter;

import android.content.Context;
import android.graphics.Rect;
import com.android.internal.policy.SystemBarUtils;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.common.ZoomUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MiddleScreenZoomParameter implements ZoomParameter {
    public static final int MIDDLE_CORNER_ZOOM_DP = 22;
    public static final float MIDDLE_LAND_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float MIDDLE_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int MIDDLE_LAND_SCREEN_LAND_APP_ZOOM_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_LAND_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_LAND_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 40;
    public static final float MIDDLE_LAND_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float MIDDLE_LAND_SCREEN_PORT_APP_ZOOM_RATIO = 0.5625f;
    public static final int MIDDLE_LAND_SCREEN_PORT_APP_ZOOM_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_LAND_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 17;
    public static final int MIDDLE_LAND_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 41;
    public static final int MIDDLE_LAND_SCREEN_ZOOM_TOP_LIMIT_DP = 25;
    public static final float MIDDLE_PORT_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float MIDDLE_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.7777778f;
    public static final int MIDDLE_PORT_SCREEN_LAND_APP_ZOOM_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_PORT_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 40;
    public static final float MIDDLE_PORT_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float MIDDLE_PORT_SCREEN_PORT_APP_ZOOM_RATIO = 0.5625f;
    public static final int MIDDLE_PORT_SCREEN_PORT_APP_ZOOM_RIGHT_OFFSET_DP = 16;
    public static final int MIDDLE_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 17;
    public static final int MIDDLE_PORT_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 41;
    public static final int MIDDLE_PORT_SCREEN_ZOOM_TOP_LIMIT_DP = 35;
    public static final int MIDDLE_SCREEN_BASE_WIDTH_DP = 360;
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
    private int mScreenType = 2;
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
    private ScreenGridUtils mSceenGrid = new ScreenGridUtils(this.mScreenType);

    public MiddleScreenZoomParameter(Context context, int i) {
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
        this.mMiniScaleRectPortScreenPortApp.set(i - ((int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sMiddlePortScreenPortAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sMiddlePortScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectPortScreenLandApp.set(i - ((int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sMiddlePortScreenLandAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sMiddlePortScreenLandAppMiniScale)) + topBottomLimitInMini);
        int i2 = screenHeight - leftRightLimitInMini;
        this.mMiniScaleRectLandScreenPortApp.set(i2 - ((int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sMiddleLandScreenPortAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sMiddleLandScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectLandScreenLandApp.set(i2 - ((int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sMiddleLandScreenLandAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sMiddleLandScreenLandAppMiniScale)) + topBottomLimitInMini);
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
        int iDip2px = ZoomUtil.dip2px(density, 360.0f);
        this.mDefaultBoundPortScreenPortApp.set(0, 0, iDip2px, (int) (iDip2px / 0.5625f));
        int iDip2px2 = ZoomUtil.dip2px(density, 360.0f);
        this.mDefaultBoundLandScreenPortApp.set(0, 0, iDip2px2, (int) (iDip2px2 / 0.5625f));
        int iDip2px3 = ZoomUtil.dip2px(density, 360.0f);
        this.mDefaultBoundPortScreenLandApp.set(0, 0, (int) (iDip2px3 * 1.7777778f), iDip2px3);
        int iDip2px4 = ZoomUtil.dip2px(density, 360.0f);
        this.mDefaultBoundLandScreenLandApp.set(0, 0, (int) (iDip2px4 * 1.7777778f), iDip2px4);
    }

    private void initDefaultZoomScaleRect() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        int statusBarHeight = SystemBarUtils.getStatusBarHeight(this.mContext);
        float density = getDensity();
        ZoomUtil.isSupportPocketStudio();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sMiddlePortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sMiddlePortScreenPortAppZoomScale);
        int iDip2px = (screenWidth - iWidth) - ZoomUtil.dip2px(density, 16.0f);
        int i = (screenHeight - iHeight) / 2;
        this.mZoomScaleRectPortScreenPortApp.set(iDip2px, i, iWidth + iDip2px, iHeight + i);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sMiddlePortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sMiddlePortScreenLandAppZoomScale);
        int iDip2px2 = (screenWidth - iWidth2) - ZoomUtil.dip2px(density, 16.0f);
        int i2 = (screenHeight - iHeight2) / 2;
        this.mZoomScaleRectPortScreenLandApp.set(iDip2px2, i2, iWidth2 + iDip2px2, iHeight2 + i2);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sMiddleLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sMiddleLandScreenPortAppZoomScale);
        int iDip2px3 = (screenHeight - iWidth3) - ZoomUtil.dip2px(density, 16.0f);
        int i3 = (screenWidth - iHeight3) / 2;
        this.mZoomScaleRectLandScreenPortApp.set(iDip2px3, Math.max(i3, statusBarHeight), iWidth3 + iDip2px3, iHeight3 + i3);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sMiddleLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sMiddleLandScreenLandAppZoomScale);
        int iDip2px4 = (screenHeight - iWidth4) - ZoomUtil.dip2px(density, 16.0f);
        int i4 = (screenWidth - iHeight4) / 2;
        this.mZoomScaleRectLandScreenLandApp.set(iDip2px4, i4, iWidth4 + iDip2px4, iHeight4 + i4);
    }

    private void initDefaultZoomScaleRectInHandle() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        float density = getDensity();
        ZoomUtil.isSupportPocketStudio();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sMiddlePortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sMiddlePortScreenPortAppZoomScale);
        int iDip2px = ZoomUtil.dip2px(density, 16.0f);
        int i = (screenHeight - iHeight) / 2;
        int i2 = iHeight + i;
        this.mZoomScaleRectPortScreenPortAppLeft.set(iDip2px, i, iDip2px + iWidth, i2);
        this.mZoomScaleRectPortScreenPortAppRight.set((screenWidth - iWidth) - iDip2px, i, screenWidth - iDip2px, i2);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sMiddlePortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sMiddlePortScreenLandAppZoomScale);
        int iDip2px2 = ZoomUtil.dip2px(density, 16.0f);
        int i3 = (screenHeight - iHeight2) / 2;
        int i4 = iHeight2 + i3;
        this.mZoomScaleRectPortScreenLandAppLeft.set(iDip2px2, i3, iDip2px2 + iWidth2, i4);
        this.mZoomScaleRectPortScreenLandAppRight.set((screenWidth - iWidth2) - iDip2px2, i3, screenWidth - iDip2px2, i4);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sMiddleLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sMiddleLandScreenPortAppZoomScale);
        int iDip2px3 = ZoomUtil.dip2px(density, 16.0f);
        int i5 = (screenWidth - iHeight3) / 2;
        int i6 = iHeight3 + i5;
        this.mZoomScaleRectLandScreenPortAppLeft.set(iDip2px3, i5, iDip2px3 + iWidth3, i6);
        this.mZoomScaleRectLandScreenPortAppRight.set((screenHeight - iWidth3) - iDip2px3, i5, screenHeight - iDip2px3, i6);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sMiddleLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sMiddleLandScreenLandAppZoomScale);
        int iDip2px4 = ZoomUtil.dip2px(density, 16.0f);
        int i7 = (screenWidth - iHeight4) / 2;
        int i8 = iHeight4 + i7;
        this.mZoomScaleRectLandScreenLandAppLeft.set(iDip2px4, i7, iDip2px4 + iWidth4, i8);
        this.mZoomScaleRectLandScreenLandAppRight.set((screenHeight - iWidth4) - iDip2px4, i7, screenHeight - iDip2px4, i8);
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
        return this.mSceenGrid.getDefaultMiniZoomScale(z, z2);
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
        return this.mSceenGrid.getDefaultZoomScale(z, z2);
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
        return ZoomUtil.dip2px(getDensity(), 16.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getLeftRightLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 17.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMaxMiniZoomScale(boolean z, boolean z2) {
        return this.mSceenGrid.getMaxMiniZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMaxZoomScale(boolean z, boolean z2) {
        return this.mSceenGrid.getMaxZoomScale(z, z2);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public float getMinMiniZoomScale(boolean z, boolean z2) {
        return this.mSceenGrid.getMinMiniZoomScale(z, z2);
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
        return ZoomUtil.dip2px(getDensity(), 40.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopBottomLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 41.0f);
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
