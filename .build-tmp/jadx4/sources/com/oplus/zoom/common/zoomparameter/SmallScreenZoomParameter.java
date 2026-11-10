package com.oplus.zoom.common.zoomparameter;

import android.content.Context;
import android.graphics.Rect;
import com.android.internal.policy.SystemBarUtils;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.common.ZoomUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SmallScreenZoomParameter implements ZoomParameter {
    public static final int SMALL_CORNER_ZOOM_DP = 22;
    public static final float SMALL_LAND_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final int SMALL_LAND_SCREEN_LAND_APP_ZOOM_LEFT_OFFSET_DP = 40;
    public static final float SMALL_LAND_SCREEN_LAND_APP_ZOOM_RATIO = 1.6666666f;
    public static final int SMALL_LAND_SCREEN_LAND_APP_ZOOM_TOP_OFFSET_DP = 0;
    public static final int SMALL_LAND_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 16;
    public static final int SMALL_LAND_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 40;
    public static final float SMALL_LAND_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final int SMALL_LAND_SCREEN_PORT_APP_ZOOM_LEFT_OFFSET_DP = 40;
    public static final float SMALL_LAND_SCREEN_PORT_APP_ZOOM_RATIO = 0.6923077f;
    public static final int SMALL_LAND_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 17;
    public static final int SMALL_LAND_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 41;
    public static final float SMALL_PORT_SCREEN_LAND_APP_SUPER_MINI_SCALE = 0.2f;
    public static final float SMALL_PORT_SCREEN_LAND_APP_ZOOM_RATIO = 1.6666666f;
    public static final int SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP = 20;
    public static final int SMALL_PORT_SCREEN_MINI_TOP_BOTTOM_OFFSET_DP = 40;
    public static final float SMALL_PORT_SCREEN_PORT_APP_SUPER_MINI_SCALE = 0.2f;
    public static final int SMALL_PORT_SCREEN_PORT_APP_ZOOM_BOTTOM_OFFSET_DP = 144;
    public static final float SMALL_PORT_SCREEN_PORT_APP_ZOOM_RATIO = 0.6f;
    public static final int SMALL_PORT_SCREEN_PORT_APP_ZOOM_TOP_OFFSET_DP = 256;
    public static final int SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP = 21;
    public static final int SMALL_PORT_SCREEN_SUPER_MINI_TOP_BOTTOM_OFFSET_DP = 41;
    public static final int SMALL_SCREEN_BASE_WIDTH_DP = 360;
    private Context mContext;
    private int mDisplayId;
    private int mScreenType = 1;
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
    private Rect mDefaultSuperMiniBoundPortScreenPortApp = new Rect();
    private Rect mDefaultSuperMiniBoundLandScreenPortApp = new Rect();
    private Rect mDefaultSuperMiniBoundPortScreenLandApp = new Rect();
    private Rect mDefaultSuperMiniBoundLandScreenLandApp = new Rect();
    private Rect mSuperMiniScaleRectPortScreenPortApp = new Rect();
    private Rect mSuperMiniScaleRectPortScreenLandApp = new Rect();
    private Rect mSuperMiniScaleRectLandScreenPortApp = new Rect();
    private Rect mSuperMiniScaleRectLandScreenLandApp = new Rect();
    private ScreenGridUtils mScreenGrid = new ScreenGridUtils(this.mScreenType);

    public SmallScreenZoomParameter(Context context, int i) {
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

    private Rect getDefaultZoomScaleRectLandScreenPortApp() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int statusBarHeight = SystemBarUtils.getStatusBarHeight(this.mContext);
        int iHeight = this.mZoomScaleRectLandScreenPortApp.height();
        int i = this.mZoomScaleRectLandScreenPortApp.left;
        int i2 = (screenWidth - iHeight) / 2;
        int iMax = Math.max(i2, statusBarHeight);
        if (!ZoomDisplayController.getInstance().isStatusbarHide()) {
            i2 = iMax;
        }
        this.mZoomScaleRectLandScreenPortApp.offsetTo(i, i2);
        return this.mZoomScaleRectLandScreenPortApp;
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
        this.mMiniScaleRectPortScreenPortApp.set(i - ((int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sSmallPortScreenPortAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sSmallPortScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectPortScreenLandApp.set(i - ((int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sSmallPortScreenLandAppMiniScale)), topBottomLimitInMini, i, ((int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sSmallPortScreenLandAppMiniScale)) + topBottomLimitInMini);
        int i2 = screenHeight - leftRightLimitInMini;
        this.mMiniScaleRectLandScreenPortApp.set(i2 - ((int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sSmallLandScreenPortAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sSmallLandScreenPortAppMiniScale)) + topBottomLimitInMini);
        this.mMiniScaleRectLandScreenLandApp.set(i2 - ((int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sSmallLandScreenLandAppMiniScale)), topBottomLimitInMini, i2, ((int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sSmallLandScreenLandAppMiniScale)) + topBottomLimitInMini);
    }

    private void initDefaultSuperMiniZoomScaleRect() {
        getTopBottomLimitInSuperMini(isPortScreen());
        int leftRightLimitInSuperMini = getLeftRightLimitInSuperMini(isPortScreen());
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        getDensity();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * 0.2f);
        int i = screenWidth - leftRightLimitInSuperMini;
        int i2 = screenHeight / 2;
        int iHeight = ((int) (this.mDefaultBoundPortScreenPortApp.height() * 0.2f)) / 2;
        this.mSuperMiniScaleRectPortScreenPortApp.set(i - iWidth, i2 - iHeight, i, iHeight + i2);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * 0.2f);
        int iHeight2 = ((int) (this.mDefaultBoundPortScreenLandApp.height() * 0.2f)) / 2;
        this.mSuperMiniScaleRectPortScreenLandApp.set(i - iWidth2, i2 - iHeight2, i, iHeight2 + i2);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * 0.2f);
        int iHeight3 = ((int) (this.mDefaultBoundLandScreenPortApp.height() * 0.2f)) / 2;
        this.mSuperMiniScaleRectLandScreenPortApp.set(i - iWidth3, i2 - iHeight3, i, iHeight3 + i2);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * 0.2f);
        int iHeight4 = ((int) (this.mDefaultBoundLandScreenLandApp.height() * 0.2f)) / 2;
        this.mSuperMiniScaleRectLandScreenLandApp.set(i - iWidth4, i2 - iHeight4, i, i2 + iHeight4);
    }

    private void initDefaultZoomBound() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        getDensity();
        int iMin = Math.min(screenWidth, screenHeight);
        this.mDefaultBoundPortScreenPortApp.set(0, 0, iMin, (int) (iMin / 0.6f));
        int iMin2 = Math.min(screenWidth, screenHeight);
        this.mDefaultBoundLandScreenPortApp.set(0, 0, iMin2, (int) (iMin2 / 0.6923077f));
        int iMin3 = Math.min(screenWidth, screenHeight);
        this.mDefaultBoundPortScreenLandApp.set(0, 0, (int) (iMin3 * 1.6666666f), iMin3);
        int iMin4 = Math.min(screenWidth, screenHeight);
        this.mDefaultBoundLandScreenLandApp.set(0, 0, (int) (iMin4 * 1.6666666f), iMin4);
    }

    private void initDefaultZoomScaleRect() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        int statusBarHeight = SystemBarUtils.getStatusBarHeight(this.mContext);
        float density = getDensity();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sSmallPortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sSmallPortScreenPortAppZoomScale);
        int i = (screenWidth - iWidth) / 2;
        int iDip2px = (screenHeight - ZoomUtil.dip2px(density, 144.0f)) - iHeight;
        this.mZoomScaleRectPortScreenPortApp.set(i, iDip2px, iWidth + i, iHeight + iDip2px);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sSmallPortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sSmallPortScreenLandAppZoomScale);
        int i2 = (screenWidth - iWidth2) / 2;
        int i3 = (screenHeight - iHeight2) / 2;
        this.mZoomScaleRectPortScreenLandApp.set(i2, i3, iWidth2 + i2, iHeight2 + i3);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sSmallLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sSmallLandScreenPortAppZoomScale);
        int iDip2px2 = ZoomUtil.dip2px(density, 40.0f);
        int iMax = Math.max((screenWidth - iHeight3) / 2, statusBarHeight);
        this.mZoomScaleRectLandScreenPortApp.set(iDip2px2, iMax, iWidth3 + iDip2px2, iHeight3 + iMax);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sSmallLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sSmallLandScreenLandAppZoomScale);
        int iDip2px3 = ZoomUtil.dip2px(density, 40.0f);
        int i4 = (screenWidth - iHeight4) / 2;
        this.mZoomScaleRectLandScreenLandApp.set(iDip2px3, i4, iWidth4 + iDip2px3, iHeight4 + i4);
    }

    private void initDefaultZoomScaleRectInHandle() {
        int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
        int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
        float density = getDensity();
        int iWidth = (int) (this.mDefaultBoundPortScreenPortApp.width() * ScreenGridUtils.sSmallPortScreenPortAppZoomScale);
        int iHeight = (int) (this.mDefaultBoundPortScreenPortApp.height() * ScreenGridUtils.sSmallPortScreenPortAppZoomScale);
        int iDip2px = ZoomUtil.dip2px(density, 256.0f);
        int i = (screenHeight - iHeight) / 2;
        int i2 = iHeight + i;
        this.mZoomScaleRectPortScreenPortAppLeft.set(iDip2px, i, iDip2px + iWidth, i2);
        this.mZoomScaleRectPortScreenPortAppRight.set((screenWidth - iWidth) - iDip2px, i, screenWidth - iDip2px, i2);
        int iWidth2 = (int) (this.mDefaultBoundPortScreenLandApp.width() * ScreenGridUtils.sSmallPortScreenLandAppZoomScale);
        int iHeight2 = (int) (this.mDefaultBoundPortScreenLandApp.height() * ScreenGridUtils.sSmallPortScreenLandAppZoomScale);
        int i3 = screenWidth - iWidth2;
        int i4 = i3 / 2;
        int i5 = (screenHeight - iHeight2) / 2;
        int i6 = iHeight2 + i5;
        this.mZoomScaleRectPortScreenLandAppLeft.set(i4, i5, iWidth2 + i4, i6);
        this.mZoomScaleRectPortScreenLandAppRight.set(i3 - i4, i5, screenWidth - i4, i6);
        int iWidth3 = (int) (this.mDefaultBoundLandScreenPortApp.width() * ScreenGridUtils.sSmallLandScreenPortAppZoomScale);
        int iHeight3 = (int) (this.mDefaultBoundLandScreenPortApp.height() * ScreenGridUtils.sSmallLandScreenPortAppZoomScale);
        int iDip2px2 = ZoomUtil.dip2px(density, 40.0f);
        int i7 = (screenWidth - iHeight3) / 2;
        int i8 = iHeight3 + i7;
        this.mZoomScaleRectLandScreenPortAppLeft.set(iDip2px2, i7, iDip2px2 + iWidth3, i8);
        this.mZoomScaleRectLandScreenPortAppRight.set((screenHeight - iWidth3) - iDip2px2, i7, screenHeight - iDip2px2, i8);
        int iWidth4 = (int) (this.mDefaultBoundLandScreenLandApp.width() * ScreenGridUtils.sSmallLandScreenLandAppZoomScale);
        int iHeight4 = (int) (this.mDefaultBoundLandScreenLandApp.height() * ScreenGridUtils.sSmallLandScreenLandAppZoomScale);
        int iDip2px3 = ZoomUtil.dip2px(density, 40.0f);
        int i9 = (screenWidth - iHeight4) / 2;
        int i10 = iHeight4 + i9;
        this.mZoomScaleRectLandScreenLandAppLeft.set(iDip2px3, i9, iDip2px3 + iWidth4, i10);
        this.mZoomScaleRectLandScreenLandAppRight.set((screenHeight - iWidth4) - iDip2px3, i9, screenHeight - iDip2px3, i10);
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
        return z ? this.mZoomScaleRectLandScreenLandApp : getDefaultZoomScaleRectLandScreenPortApp();
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getLeftRightLimitInMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), z ? 20 : 16);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getLeftRightLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), z ? 21 : 17);
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
        return ZoomUtil.dip2px(getDensity(), 40.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopBottomLimitInSuperMini(boolean z) {
        return ZoomUtil.dip2px(getDensity(), 41.0f);
    }

    @Override // com.oplus.zoom.common.zoomparameter.ZoomParameter
    public int getTopLimitInZoom(boolean z) {
        return (z || !ZoomDisplayController.getInstance().isStatusbarHide()) ? SystemBarUtils.getStatusBarHeight(this.mContext) : getDefaultZoomScaleRectLandScreenPortApp().top;
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
