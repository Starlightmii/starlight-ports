package com.oplus.zoom.common;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.SparseArray;
import com.oplus.zoom.common.zoomparameter.LargeScreenZoomParameter;
import com.oplus.zoom.common.zoomparameter.MiddleScreenZoomParameter;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplus.zoom.common.zoomparameter.ZoomParameter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomParameterHelper {
    private static final float FOLD_SMALLSCREEN_THRESOLD_RATIO = 0.6f;
    public static final int HOT_EDGE_OF_SHOW_FLOAT_HANDLE = 40;
    public static final int MIDDLE_SCREEN_HEIGHT_THRESHOLD_DP = 900;
    public static final int MIDDLE_SCREEN_WIDTH_THRESHOLD_DP = 840;
    public static final int SMALL_SCREEN_HEIGHT_THRESHOLD_DP = 480;
    public static final int SMALL_SCREEN_WIDTH_THRESHOLD_DP = 600;
    private static final String TAG = "ZoomParameterHelper";
    public static final int ZOOM_IME_SPACE = 24;
    public static final int ZOOM_SHADOW_RADIUS_WITHOUT_FOCUS = 6;
    public static final int ZOOM_SHADOW_RADIUS_WITH_FOCUS = 10;
    private static volatile ZoomParameterHelper sInstance;
    private Context mContext;
    private PerDisplay mDefaultDisplay;
    private ZoomDisplayController mDisplayController;
    private final SparseArray<PerDisplay> mZoomParameters = new SparseArray<>();
    private final boolean mIsFlipDevice = ZoomUtil.isFlipDevice();
    private final boolean mIsFoldDevice = ZoomUtil.isFoldDevice();
    private final boolean mIsTabletDevice = ZoomUtil.isTabletDevice();
    private ZoomDisplayController.DisplayChangedListener mDisplayListener = new ZoomDisplayController.DisplayChangedListener() { // from class: com.oplus.zoom.common.ZoomParameterHelper.1
        @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
        public void onDisplayAdded(int i) {
            PerDisplay perDisplay = ZoomParameterHelper.this.new PerDisplay(i);
            ZoomParameterHelper.this.mZoomParameters.put(i, perDisplay);
            if (i == 0) {
                ZoomParameterHelper.this.mDefaultDisplay = perDisplay;
            }
        }

        @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
        public void onDisplayChanged(int i, int i2, Configuration configuration) {
            PerDisplay perDisplay;
            if ((i2 & 2) == 0 || (perDisplay = (PerDisplay) ZoomParameterHelper.this.mZoomParameters.get(i)) == null) {
                return;
            }
            perDisplay.initZoomDefaultInfo();
        }

        @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
        public void onDisplayRemove(int i) {
            if (((PerDisplay) ZoomParameterHelper.this.mZoomParameters.get(i)) == null) {
                return;
            }
            ZoomParameterHelper.this.mZoomParameters.remove(i);
        }
    };

    public static ZoomParameterHelper getInstance() {
        if (sInstance == null) {
            synchronized (ZoomParameterHelper.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomParameterHelper();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public ZoomPositionInfo getDefaultMiniPositionInfo(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new ZoomPositionInfo() : perDisplay.getDefaultMiniPositionInfo(z);
    }

    public Rect getDefaultMiniZoomRectForDisplayChange(Rect rect, int i, ZoomPositionInfo zoomPositionInfo) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new Rect() : perDisplay.getDefaultMiniZoomRectForDisplayChange(rect, i, zoomPositionInfo);
    }

    public ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new ZoomPositionInfo() : perDisplay.getDefaultSuperMiniPositionInfo(z);
    }

    public Rect getDefaultZoomBound(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new Rect() : perDisplay.getDefaultZoomBound(z);
    }

    public ZoomPositionInfo getDefaultZoomPositionInfo(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new ZoomPositionInfo() : perDisplay.getDefaultZoomPositionInfo(z);
    }

    public ZoomPositionInfo getDefaultZoomPositionInfoInHandle(boolean z, boolean z2) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        return perDisplay == null ? new ZoomPositionInfo() : perDisplay.getDefaultZoomPositionInfoInHandle(z, z2);
    }

    public float getDefaultZoomScale(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 1.0f;
        }
        return perDisplay.getDefaultZoomScale(z);
    }

    public int getLeftRightLimitInMini() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getLeftRightLimitInMini();
    }

    public int getLeftRightLimitInSuperMini() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getLeftRightLimitInSuperMini();
    }

    public float getMaxMiniZoomScale(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 1.0f;
        }
        return perDisplay.getMaxMiniZoomScale(z);
    }

    public float getMaxZoomScale(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 1.0f;
        }
        return perDisplay.getMaxZoomScale(z);
    }

    public float getMinMiniZoomScale(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 1.0f;
        }
        return perDisplay.getMinMiniZoomScale(z);
    }

    public int getRotation() {
        return this.mDisplayController.getRotation();
    }

    public int getScreenType() {
        return this.mDefaultDisplay.getScreenType();
    }

    public float getSuperMiniZoomScale(boolean z) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0.2f;
        }
        return perDisplay.getSuperMiniZoomScale(z);
    }

    public int getTopBottomLimitInMini() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getTopBottomLimitInMini();
    }

    public int getTopBottomLimitInSuperMini() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getTopBottomLimitInSuperMini();
    }

    public int getTopLimitInZoom() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getTopLimitInZoom();
    }

    public int getZoomCornerRadius() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return 0;
        }
        return perDisplay.getZoomCornerRadius();
    }

    public void init(Context context, ZoomDisplayController zoomDisplayController) {
        this.mContext = context;
        this.mDisplayController = zoomDisplayController;
        zoomDisplayController.registerDisplayChangedListener(this.mDisplayListener);
    }

    public boolean isPortScreen(int i) {
        return i == 0 || i == 2;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class PerDisplay {
        private final int mDisplayId;
        private ZoomParameter mParameterStore;
        private int mScreenType;

        public PerDisplay(int i) {
            this.mDisplayId = i;
            initZoomDefaultInfo();
        }

        private int computeScreenType() {
            int i;
            int screenWidth = isPortScreen() ? getScreenWidth() : getScreenHeight();
            int screenHeight = isPortScreen() ? getScreenHeight() : getScreenWidth();
            float density = getDensity();
            int iMin = Math.min(screenWidth, screenHeight);
            int iMax = Math.max(screenWidth, screenHeight);
            int iDip2px = ZoomUtil.dip2px(density, 600.0f);
            ZoomUtil.dip2px(density, 480.0f);
            int iDip2px2 = ZoomUtil.dip2px(density, 840.0f);
            int iDip2px3 = ZoomUtil.dip2px(density, 900.0f);
            if (iMin < iDip2px) {
                i = 1;
            } else {
                i = (iMin < iDip2px || iMin >= iDip2px2 || iMax >= iDip2px3) ? 3 : 2;
            }
            this.mScreenType = i;
            LogD.d(ZoomParameterHelper.TAG, "computeScreenType screenType = " + i);
            return i;
        }

        private float getDensity() {
            return ZoomParameterHelper.this.mDisplayController.getDensity();
        }

        private int getRotation() {
            return ZoomParameterHelper.this.mDisplayController.getRotation();
        }

        private int getScreenHeight() {
            return ZoomParameterHelper.this.mDisplayController.getScreenHeight();
        }

        private int getScreenWidth() {
            return ZoomParameterHelper.this.mDisplayController.getScreenWidth();
        }

        private ZoomParameter getZoomParameterStore(int i) {
            if (i == 1) {
                return new SmallScreenZoomParameter(ZoomParameterHelper.this.mContext, this.mDisplayId);
            }
            ZoomParameterHelper zoomParameterHelper = ZoomParameterHelper.this;
            return i == 2 ? new MiddleScreenZoomParameter(zoomParameterHelper.mContext, this.mDisplayId) : new LargeScreenZoomParameter(zoomParameterHelper.mContext, this.mDisplayId);
        }

        private boolean isPortScreen() {
            return getRotation() == 0 || getRotation() == 2;
        }

        public ZoomPositionInfo getDefaultMiniPositionInfo(boolean z) {
            return getDefaultMiniPositionInfo(z, isPortScreen());
        }

        public Rect getDefaultMiniZoomRectForDisplayChange(Rect rect, int i, ZoomPositionInfo zoomPositionInfo) {
            return this.mParameterStore.getDefaultMiniZoomRectForDisplayChange(rect, i, zoomPositionInfo);
        }

        public ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z) {
            return getDefaultSuperMiniPositionInfo(z, isPortScreen());
        }

        public Rect getDefaultZoomBound(boolean z) {
            return getDefaultZoomBound(z, isPortScreen());
        }

        public ZoomPositionInfo getDefaultZoomPositionInfo(boolean z) {
            return getDefaultZoomPositionInfo(z, isPortScreen());
        }

        public ZoomPositionInfo getDefaultZoomPositionInfoInHandle(boolean z, boolean z2) {
            return this.mParameterStore.getDefaultZoomPositionInfoInHandle(z, isPortScreen(), z2);
        }

        public float getDefaultZoomScale(boolean z) {
            return this.mParameterStore.getDefaultZoomScale(z, isPortScreen());
        }

        public int getLeftRightLimitInMini() {
            return this.mParameterStore.getLeftRightLimitInMini(isPortScreen());
        }

        public int getLeftRightLimitInSuperMini() {
            return this.mParameterStore.getLeftRightLimitInSuperMini(isPortScreen());
        }

        public float getMaxMiniZoomScale(boolean z) {
            return this.mParameterStore.getMaxMiniZoomScale(z, isPortScreen());
        }

        public float getMaxZoomScale(boolean z) {
            return this.mParameterStore.getMaxZoomScale(z, isPortScreen());
        }

        public float getMinMiniZoomScale(boolean z) {
            return this.mParameterStore.getMinMiniZoomScale(z, isPortScreen());
        }

        public int getScreenType() {
            return this.mScreenType;
        }

        public float getSuperMiniZoomScale(boolean z) {
            return this.mParameterStore.getSuperMiniZoomScale(z);
        }

        public int getTopBottomLimitInMini() {
            return this.mParameterStore.getTopBottomLimitInMini(isPortScreen());
        }

        public int getTopBottomLimitInSuperMini() {
            return this.mParameterStore.getTopBottomLimitInSuperMini(isPortScreen());
        }

        public int getTopLimitInZoom() {
            return this.mParameterStore.getTopLimitInZoom(isPortScreen());
        }

        public int getZoomCornerRadius() {
            return this.mParameterStore.getZoomCornerRadius(isPortScreen());
        }

        public void initZoomDefaultInfo() {
            LogD.d(ZoomParameterHelper.TAG, "initZoomDefaultInfo");
            ZoomParameter zoomParameterStore = getZoomParameterStore(computeScreenType());
            this.mParameterStore = zoomParameterStore;
            zoomParameterStore.initZoomDefaultInfo();
        }

        public ZoomPositionInfo getDefaultMiniPositionInfo(boolean z, boolean z2) {
            return this.mParameterStore.getDefaultMiniPositionInfo(z, z2);
        }

        public ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z, boolean z2) {
            return this.mParameterStore.getDefaultSuperMiniPositionInfo(z, z2);
        }

        public Rect getDefaultZoomBound(boolean z, boolean z2) {
            return this.mParameterStore.getDefaultZoomBound(z, z2);
        }

        public ZoomPositionInfo getDefaultZoomPositionInfo(boolean z, boolean z2) {
            return this.mParameterStore.getDefaultZoomPositionInfo(z, z2);
        }
    }

    public ZoomPositionInfo getDefaultMiniPositionInfo(boolean z, boolean z2) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return new ZoomPositionInfo();
        }
        return perDisplay.getDefaultMiniPositionInfo(z, z2);
    }

    public ZoomPositionInfo getDefaultSuperMiniPositionInfo(boolean z, boolean z2) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return new ZoomPositionInfo();
        }
        return perDisplay.getDefaultSuperMiniPositionInfo(z, z2);
    }

    public Rect getDefaultZoomBound(boolean z, boolean z2) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return new Rect();
        }
        return perDisplay.getDefaultZoomBound(z, z2);
    }

    public ZoomPositionInfo getDefaultZoomPositionInfo(boolean z, boolean z2) {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay == null) {
            return new ZoomPositionInfo();
        }
        return perDisplay.getDefaultZoomPositionInfo(z, z2);
    }
}
