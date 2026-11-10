package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.fragment.app.Fragment$5$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.OplusSystemUIApplicationUtils;
import com.android.systemui.assist.ui.DisplayUtils;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.utils.DisplaySizeUtils;
import com.oplus.systemui.navigationbar.utils.NavBarScreenSizeHelper;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SideGestureConfiguration extends SideGestureConfigurationEx {
    public static int sPreDistance;
    public static int sValidDistance;
    public final float mAttrSizeScaleForSubDisplay;
    public final Context mContext;
    public final String mDisplayIdTag;
    public final InvalidRegionControl mInvalidRegionControl;
    public final boolean mIsOnSubDisplayId;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mSideGestureAreaWidthLeft;
    public int mSideGestureAreaWidthRight;
    public final float mUiSizeScaleForSubDisplay;
    public int mBottomGestureAreaHeight = 0;
    public int mBottomGestureAreaHeightInMistouch = 0;
    public final Executor mBgExecutor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);

    public SideGestureConfiguration(Context context, Rect rect, int i) {
        this.mDisplayIdTag = "display_id_";
        this.mIsOnSubDisplayId = false;
        this.mContext = context;
        this.mDisplayIdTag = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "display_id_");
        if (CustomizeFeatureOption.sIsSupportMiniApp) {
            ConfigurationOnSubDisplay.Companion companion = ConfigurationOnSubDisplay.Companion;
            companion.getInstance(context).initData();
            companion.getInstance(context).getClass();
            boolean zIsSubDisplayId = ConfigurationOnSubDisplay.isSubDisplayId(i);
            this.mIsOnSubDisplayId = zIsSubDisplayId;
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("SideGestureConfiguration init, mIsOnSubDisplay:", LogUtil.TAG_NAVBAR, "SideGestureConfiguration", zIsSubDisplayId);
        }
        boolean z = this.mIsOnSubDisplayId;
        this.mAttrSizeScaleForSubDisplay = z ? 2.5f : 1.0f;
        this.mUiSizeScaleForSubDisplay = z ? 1.5f : 1.0f;
        setScreenSize(rect);
        if (OplusSystemUIApplicationUtils.isMainSystemUiThread()) {
            this.mInvalidRegionControl = new InvalidRegionControl(context, this, i);
            return;
        }
        LogUtil.e(LogUtil.TAG_NAVBAR, "SideGestureConfiguration", "non-system foreground user, callers = " + Log.getStackTraceString(new Throwable()));
    }

    private void setScreenSize(Rect rect) {
        this.mScreenWidth = Math.min(rect.width(), rect.height());
        this.mScreenHeight = Math.max(rect.width(), rect.height());
        DeviceStateController deviceStateController = DeviceStateController.getInstance();
        int i = this.mScreenWidth;
        int i2 = this.mScreenHeight;
        deviceStateController.getClass();
        if (DeviceStateController.isLargeScreenInFoldDevice(i, i2)) {
            NavBarFeatureOption.INSTANCE.getClass();
            if (NavBarFeatureOption.isUnfoldWidthSmallHigh()) {
                return;
            }
            int i3 = this.mScreenHeight;
            int i4 = this.mScreenWidth;
            int i5 = i3 ^ i4;
            int i6 = i4 ^ i5;
            this.mScreenWidth = i6;
            this.mScreenHeight = i5 ^ i6;
        }
    }

    public final int boundToRange(int i, int i2, int i3) {
        return Math.max(i2, Math.min(i, i3));
    }

    public final void checkSideGestureAreaWidth(int i) {
        if (this.mIsOnSubDisplayId) {
            return;
        }
        updateSideGestureAreaWidth();
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append(" checkSideGestureAreaWidth: mSideGestureAreaWidthLeft=");
        sb.append(this.mSideGestureAreaWidthLeft);
        sb.append(" mSideGestureAreaWidthRight=");
        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", Fragment$5$.ExternalSyntheticOutline0.m(sb, this.mSideGestureAreaWidthRight, i, "rotation = "));
        InvalidRegionControl invalidRegionControl = this.mInvalidRegionControl;
        if (invalidRegionControl != null) {
            invalidRegionControl.notifyTouchBoundsChange(this.mSideGestureAreaWidthLeft, this.mSideGestureAreaWidthRight, this.mBottomGestureAreaHeight, i);
        }
    }

    public final void dump(PrintWriter printWriter) {
        InvalidRegionControl invalidRegionControl = this.mInvalidRegionControl;
        if (invalidRegionControl != null) {
            invalidRegionControl.dump(printWriter);
        }
    }

    public int getAnimCancelDistance() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 8.8f / this.mUiSizeScaleForSubDisplay);
    }

    public int getAnimRadius() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 104.5f / this.mUiSizeScaleForSubDisplay);
    }

    public int getAnimSwitchMaxDistanceX() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 38.6f / this.mUiSizeScaleForSubDisplay);
    }

    public int getBackDistance() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 100.0f / this.mAttrSizeScaleForSubDisplay);
    }

    public int getBezierControlOffset1() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 56.0f / this.mUiSizeScaleForSubDisplay);
    }

    public int getBezierControlOffsetSwitch2() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 39.0f / this.mUiSizeScaleForSubDisplay);
    }

    public int getBottomGestureAreaHeight() {
        return this.mBottomGestureAreaHeight;
    }

    public final int getCancelDistance(int i, int i2, boolean z) {
        int i3;
        int iAbs = Math.abs(i - sPreDistance);
        Context context = this.mContext;
        float f = this.mAttrSizeScaleForSubDisplay;
        int iConvertDpToPixel = DisplaySizeUtils.convertDpToPixel(context, 10.0f / f);
        String str = this.mDisplayIdTag;
        if (iAbs >= iConvertDpToPixel) {
            if ((z && i > sPreDistance) || (!z && (i < (i3 = sPreDistance) || i3 == 0))) {
                sValidDistance = i;
            }
            sPreDistance = i;
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, "sPreDistance =  ");
            sbM.append(DisplaySizeUtils.convertPixelToDp(this.mContext, sPreDistance));
            sbM.append("dp   sValidDistance = ");
            sbM.append(DisplaySizeUtils.convertPixelToDp(this.mContext, sValidDistance));
            sbM.append("dp");
            LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureConfiguration", sbM.toString());
        }
        if (z) {
            if (sValidDistance <= getBackDistance() && i <= getBackDistance()) {
                return getMinCancelDistance();
            }
            LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureConfiguration", Fragment$5$.ExternalSyntheticOutline0.m(Bundler$.ExternalSyntheticOutline0.m(str, " getCancelDistance left sValidDistance = "), sValidDistance, i, " x point = "));
            return sValidDistance - DisplaySizeUtils.convertDpToPixel(this.mContext, 65.0f / f);
        }
        if (sValidDistance >= i2 - getBackDistance() && i >= i2 - getBackDistance()) {
            return i2 - getMinCancelDistance();
        }
        LogUtil.d(LogUtil.TAG_NAVBAR, "SideGestureConfiguration", Fragment$5$.ExternalSyntheticOutline0.m(Bundler$.ExternalSyntheticOutline0.m(str, " getCancelDistance right sValidDistanceDp = "), sValidDistance, i, " x.Point = "));
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 65.0f / f) + sValidDistance;
    }

    public int getIconSize() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, 18.0f);
    }

    public int getMinCancelDistance() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, this.mIsOnSubDisplayId ? ConfigurationOnSubDisplay.Companion.getInstance(this.mContext).getSubscreenCancelDistance() : 45.0f);
    }

    public int getRubberbandMaxDistance() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, ((Float) NavBarOliveLinkConnector.INSTANCE.getRUBBER_BAND_MAX_DISTANCE_X().get()).floatValue() / this.mUiSizeScaleForSubDisplay);
    }

    public int getRubberbandMaxDistanceY() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, ((Float) NavBarOliveLinkConnector.INSTANCE.getRUBBER_BAND_MAX_DISTANCE_Y().get()).floatValue() / this.mUiSizeScaleForSubDisplay);
    }

    public final int getScreenHeight(int i) {
        if (i == 0 || i == 2) {
            return this.mScreenHeight;
        }
        return (i == 1 || i == 3) ? this.mScreenWidth : this.mScreenHeight;
    }

    public final int getScreenWidth(int i) {
        if (i == 0 || i == 2) {
            return this.mScreenWidth;
        }
        return (i == 1 || i == 3) ? this.mScreenHeight : this.mScreenWidth;
    }

    public int getSideGestureAreaWidthLeft() {
        return this.mSideGestureAreaWidthLeft;
    }

    public int getSideGestureAreaWidthRight() {
        return this.mSideGestureAreaWidthRight;
    }

    public final int getWindowHeight(int i) {
        return (int) ((getScreenHeight(i) * 1.0f) + getAnimRadius());
    }

    public int getWindowWidth() {
        return DisplaySizeUtils.convertDpToPixel(this.mContext, ((Float) NavBarOliveLinkConnector.INSTANCE.getRUBBER_BAND_MAX_DISTANCE_X().get()).floatValue() / this.mUiSizeScaleForSubDisplay);
    }

    public final void onSideBarSceneRegionChanged(Region region) {
        InvalidRegionControl invalidRegionControl;
        if (this.mIsOnSubDisplayId || (invalidRegionControl = this.mInvalidRegionControl) == null || region == null) {
            return;
        }
        invalidRegionControl.updateSideBarSceneRegion(region);
    }

    public final void resetGestureRemoteData() {
        sValidDistance = 0;
        sPreDistance = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append(" resetGestureRemoteData preDistance = ");
        sb.append(sPreDistance);
        sb.append("   validDistance = ");
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(sValidDistance, LogUtil.TAG_NAVBAR, "SideGestureConfiguration", sb);
    }

    public final void updateGestureAreaRatio(float f) {
        if (NavBarUtils.isGestureSideMode()) {
            this.mBottomGestureAreaHeightInMistouch = (int) (f >= 0.0f ? 0.5f + f : f - 0.5f);
            StringBuilder sb = new StringBuilder();
            sb.append(this.mDisplayIdTag);
            sb.append(" updateGestureAreaRatio newSize: ");
            sb.append(f);
            sb.append(" ,mBottomGestureAreaHeightInMistouch: ");
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mBottomGestureAreaHeightInMistouch, LogUtil.TAG_NAVBAR, "NoBackGesture", sb);
        }
        updateGestureBottomArea();
    }

    public final void updateGestureAreaSize(int i) {
        updateGestureBottomArea();
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append(" updateGestureAreaSize: mBottomGestureAreaHeight = ");
        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", Fragment$5$.ExternalSyntheticOutline0.m(sb, this.mBottomGestureAreaHeight, i, ", rotation = "));
        updateSideGestureAreaWidth();
        InvalidRegionControl invalidRegionControl = this.mInvalidRegionControl;
        if (invalidRegionControl != null) {
            invalidRegionControl.notifyTouchBoundsChange(this.mSideGestureAreaWidthLeft, this.mSideGestureAreaWidthRight, this.mBottomGestureAreaHeight, i);
        }
    }

    public final void updateGestureBottomArea() {
        int i;
        if (!NavBarUtils.isGestureSideMode() || (i = this.mBottomGestureAreaHeightInMistouch) == 0) {
            this.mBottomGestureAreaHeight = this.mContext.getResources().getDimensionPixelSize(2131165705);
        } else {
            this.mBottomGestureAreaHeight = i;
        }
        if (this.mIsOnSubDisplayId) {
            this.mBottomGestureAreaHeight = ConfigurationOnSubDisplay.Companion.getInstance(this.mContext).getSubBottomGestureAreaHeight(this.mContext);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDisplayIdTag);
        sb.append(" updateGestureBottomArea: mBottomGestureAreaHeight = ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mBottomGestureAreaHeight, LogUtil.TAG_NAVBAR, "NoBackGesture", sb);
    }

    public final void updateScreenSize(Rect rect) {
        Context context = this.mContext;
        if (this.mIsOnSubDisplayId) {
            ConfigurationOnSubDisplay.Companion companion = ConfigurationOnSubDisplay.Companion;
            this.mScreenWidth = companion.getInstance(context).getSubScreenWidth();
            this.mScreenHeight = companion.getInstance(this.mContext).getSubScreenHeight();
            StringBuilder sb = new StringBuilder();
            sb.append(this.mDisplayIdTag);
            sb.append(" on sub display, physics sScreenWidth:");
            sb.append(this.mScreenWidth);
            sb.append(",physics sScreenHeight:");
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mScreenHeight, LogUtil.TAG_NAVBAR, "NoBackGesture", sb);
            return;
        }
        NavBarScreenSizeHelper.getInstance(context).updateScreenSize(this.mContext);
        this.mScreenWidth = Math.min(rect.width(), rect.height());
        this.mScreenHeight = Math.max(rect.width(), rect.height());
        DeviceStateController deviceStateController = DeviceStateController.getInstance();
        int i = this.mScreenWidth;
        int i2 = this.mScreenHeight;
        deviceStateController.getClass();
        if (DeviceStateController.isLargeScreenInFoldDevice(i, i2)) {
            NavBarFeatureOption.INSTANCE.getClass();
            if (NavBarFeatureOption.isUnfoldWidthSmallHigh()) {
                return;
            }
            int i3 = this.mScreenHeight;
            int i4 = this.mScreenWidth;
            int i5 = i3 ^ i4;
            int i6 = i4 ^ i5;
            this.mScreenWidth = i6;
            this.mScreenHeight = i5 ^ i6;
        }
    }

    public final void updateSideGestureAreaWidth() {
        if (this.mIsOnSubDisplayId) {
            this.mSideGestureAreaWidthLeft = this.mContext.getResources().getDimensionPixelSize(2131173168);
            this.mSideGestureAreaWidthRight = this.mContext.getResources().getDimensionPixelSize(2131173168);
        } else {
            this.mSideGestureAreaWidthLeft = DisplayUtils.convertDpToPx(((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNavBarGestureGearEx().getNavGestureLeftGearValue(), this.mContext);
            int iConvertDpToPx = DisplayUtils.convertDpToPx(((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNavBarGestureGearEx().getNavGestureRightGearValue(), this.mContext);
            this.mSideGestureAreaWidthRight = iConvertDpToPx;
            int i = iConvertDpToPx | (this.mSideGestureAreaWidthLeft << 16);
            Executor executor = this.mBgExecutor;
            SideGestureConfiguration$$ExternalSyntheticLambda0 sideGestureConfiguration$$ExternalSyntheticLambda0 = new SideGestureConfiguration$$ExternalSyntheticLambda0();
            sideGestureConfiguration$$ExternalSyntheticLambda0.f$0 = this;
            sideGestureConfiguration$$ExternalSyntheticLambda0.f$1 = i;
            VarHandle.storeStoreFence();
            executor.execute(sideGestureConfiguration$$ExternalSyntheticLambda0);
        }
        LogUtil.i(LogUtil.TAG_NAVBAR, "NoBackGesture", this.mDisplayIdTag + " mSideGestureAreaWidthLeft = " + this.mSideGestureAreaWidthLeft + "px , = " + DisplaySizeUtils.convertPixelToDp(this.mContext, this.mSideGestureAreaWidthLeft) + "dp, mSideGestureAreaWidthRight = " + this.mSideGestureAreaWidthRight + "px , = " + DisplaySizeUtils.convertPixelToDp(this.mContext, this.mSideGestureAreaWidthRight) + "dp");
    }
}
