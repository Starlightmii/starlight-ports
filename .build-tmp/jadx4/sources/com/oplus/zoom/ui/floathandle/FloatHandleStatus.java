package com.oplus.zoom.ui.floathandle;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.SystemProperties;
import com.android.wm.shell.R;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomUtil;
import com.oplus.zoom.ui.common.UiLogUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleStatus {
    private static final float FLOAT_HANDLE_HEIGHT = 60.0f;
    private static final float FLOAT_HANDLE_WIDTH = 65.0f;
    public static final int LEFT_SIDE = 1;
    public static final int MODE_FULL = 2;
    public static final int MODE_HALF = 4;
    public static final int MODE_NONE = 1;
    public static final int MODE_UNSET = 8;
    public static final int RIGHT_SIDE = 2;
    private static final String TAG = "FloatHandle";
    private int mContainerHeight;
    private Context mContext;
    private int mDistanceToRebound;
    private int mMaxDistanceToScreenInHalfHidden;
    private int mMaxDistanceToScreenInNone;
    private int mMovingEdgeLimit;
    private int mVelocityOfFling = SystemProperties.getInt("debug.zoom.flingX", 1000);
    private int mDistanceOfFling = SystemProperties.getInt("debug.zoom.distanceX", 100);
    private FloatHandleInfo mFloatHandleInfo = new FloatHandleInfo();
    private FloatViewState mFloatViewState = new FloatViewState(0);

    public FloatHandleStatus(Context context) {
        this.mContext = context;
    }

    private void loadResource() {
        this.mMaxDistanceToScreenInHalfHidden = this.mContext.getResources().getDimensionPixelSize(R.dimen.zoom_float_handle_distance_in_half);
        this.mMaxDistanceToScreenInNone = this.mContext.getResources().getDimensionPixelSize(R.dimen.zoom_float_handle_view_width);
        this.mContainerHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.zoom_float_handle_view_height);
        this.mDistanceToRebound = this.mContext.getResources().getDimensionPixelSize(R.dimen.distance_to_rebound);
        this.mMovingEdgeLimit = this.mContext.getResources().getDimensionPixelSize(R.dimen.LIMIT_EDGE_OF_MOVE_FLOAT_HANDLE);
        this.mDistanceOfFling = this.mContext.getResources().getDimensionPixelOffset(R.dimen.LIMIT_EDGE_OF_FLING_HANDLE);
    }

    public void addFloatHandleInfo(FloatHandleInfo floatHandleInfo) {
        this.mFloatHandleInfo.copy(floatHandleInfo);
        int i = floatHandleInfo.side;
        float f = floatHandleInfo.scaleY;
        if (i == 0) {
            if (f != -1.0f) {
                this.mFloatViewState.mScaleLeft = this.mFloatHandleInfo.scaleY;
            }
            this.mFloatViewState.side = 1;
        } else {
            if (f != -1.0f) {
                this.mFloatViewState.mScaleRight = this.mFloatHandleInfo.scaleY;
            }
            this.mFloatViewState.side = 2;
        }
        loadResource();
    }

    public void clearRestoreInfo() {
        FloatHandleInfo floatHandleInfo = this.mFloatHandleInfo;
        floatHandleInfo.zoomPositionInfo = null;
        floatHandleInfo.zoomState = 0;
    }

    public int getContainerHeight() {
        return this.mContainerHeight;
    }

    public String getCurrentLockPkg() {
        return this.mFloatViewState.mPackageName;
    }

    public int getCurrentUserId() {
        return this.mFloatViewState.mUserId;
    }

    public int getDistanceOfFling() {
        return this.mDistanceOfFling;
    }

    public int getDistanceToRebound() {
        return this.mDistanceToRebound;
    }

    public FloatHandleInfo getFloatHandleInfo() {
        return this.mFloatHandleInfo;
    }

    public Rect getFloatHandleRegion(Rect rect, float f) {
        int i;
        int i2;
        int screenHeight;
        int i3;
        int iDip2px = ZoomUtil.dip2px(this.mContext, FLOAT_HANDLE_WIDTH);
        int iDip2px2 = ZoomUtil.dip2px(this.mContext, FLOAT_HANDLE_HEIGHT);
        FloatViewState floatViewState = this.mFloatViewState;
        if (floatViewState.side == 1) {
            i2 = -iDip2px;
            screenHeight = ((int) (floatViewState.mScaleLeft * getScreenHeight())) - (iDip2px2 / 2);
            i = iDip2px2 + screenHeight;
            i3 = 0;
        } else {
            int screenHeight2 = (int) (floatViewState.mScaleRight * getScreenHeight());
            int screenWidth = getScreenWidth();
            int i4 = screenHeight2 - (iDip2px2 / 2);
            int i5 = iDip2px + screenWidth;
            i = iDip2px2 + i4;
            i2 = screenWidth;
            screenHeight = i4;
            i3 = i5;
        }
        return new Rect(i2, screenHeight, i3, i);
    }

    public int getMaxDistanceToScreenInHalfHidden() {
        return this.mMaxDistanceToScreenInHalfHidden;
    }

    public int getMaxDistanceToScreenInNone() {
        return this.mMaxDistanceToScreenInNone;
    }

    public int getMovingEdgeLimit() {
        return this.mMovingEdgeLimit;
    }

    public float getScale(int i) {
        FloatViewState floatViewState = this.mFloatViewState;
        return i == 1 ? floatViewState.mScaleLeft : floatViewState.mScaleRight;
    }

    public int getScreenHeight() {
        return ZoomDisplayController.getInstance().getScreenHeight();
    }

    public int getScreenWidth() {
        return ZoomDisplayController.getInstance().getScreenWidth();
    }

    public int getVelocityOfFling() {
        return this.mVelocityOfFling;
    }

    public boolean isFloatHandleShow() {
        UiLogUtils.i("FloatHandle", "isFloatHandleShow mPackageName = " + this.mFloatViewState.mPackageName);
        return this.mFloatViewState.mPackageName != null;
    }

    public void onConfigurationChange(Configuration configuration) {
        loadResource();
    }

    public void setCurrentLockPkg(String str) {
        this.mFloatViewState.mPackageName = str;
    }

    public void setCurrentUserId(int i) {
        this.mFloatViewState.mUserId = i;
    }

    public void updateFloatHandlePosition(int i, int i2, int i3) {
        float screenHeight = getScreenHeight();
        FloatViewState floatViewState = this.mFloatViewState;
        if (i == 1) {
            floatViewState.mScaleLeft = i3 / screenHeight;
        } else {
            floatViewState.mScaleRight = i3 / screenHeight;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class FloatViewState {
        String mPackageName;
        float mScaleLeft;
        float mScaleRight;
        int mUserId;
        int side;

        private FloatViewState() {
            this.mScaleLeft = 0.5f;
            this.mScaleRight = 0.5f;
            this.side = 1;
        }

        public /* synthetic */ FloatViewState(int i) {
            this();
        }
    }
}
