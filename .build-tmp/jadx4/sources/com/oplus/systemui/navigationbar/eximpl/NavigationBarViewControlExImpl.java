package com.oplus.systemui.navigationbar.eximpl;

import android.app.LocaleManager;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.DisplayInfo;
import android.view.RoundedCorner;
import android.view.RoundedCorners;
import android.view.View;
import android.widget.FrameLayout;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.core.os.LocaleListCompat;
import androidx.percentlayout.widget.PercentFrameLayout;
import com.android.systemui.navigationbar.NavigationBarViewControlEx;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.NearestTouchFrame;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.systemui.navigationbar.gesture.olivelink.NavBarOliveLinkConnector;
import com.oplus.systemui.navigationbar.gesture.proxy.NavBarSettingsValueProxy;
import com.oplus.systemui.navigationbar.positionview.PositionSwitchView;
import com.oplus.systemui.navigationbar.positionview.PositionSwitchViewController;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.observer.VirtualKeyCombinationTypeObserver;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarViewControlExImpl extends NavigationBarViewControlEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NavigationBarViewControlExImpl";
    private final Context context;
    private PositionSwitchViewController mPositionSwitchViewController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NavigationBarViewControlExImpl(Context context) {
        super(context);
        this.context = context;
    }

    public static int dpToPx(int i, Context context) {
        return (int) (i * context.getResources().getDisplayMetrics().density);
    }

    public String getAccessibilityPinningToast() {
        Context context = this.context;
        Object systemService = context.getSystemService("locale");
        if (systemService != null) {
            LocaleListCompat.wrap(((LocaleManager) systemService).getApplicationLocales());
        } else {
            LocaleListCompat.getEmptyLocaleList();
        }
        return context.getString(2131956013);
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getFoldOpenLayout(boolean z) {
        return z ? 2131559185 : 2131559187;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:51:0x011f  */
    public int getNavBarPaddingP2(Context context, int i, int i2, int i3) {
        RoundedCorner roundedCorner;
        int radius;
        String str;
        int i4;
        int iSqrt;
        int i5;
        double d;
        double d2;
        DisplayInfo displayInfo = new DisplayInfo();
        context.getDisplay().getDisplayInfo(displayInfo);
        RoundedCorners roundedCorners = displayInfo.roundedCorners;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getNavBarPaddingP2  navBarHeight:", i, i2, " ,maxDrawableWith:", " ,maxDrawableHeight:");
        sbM.append(i3);
        sbM.append(" ,roundedCorners:");
        sbM.append(roundedCorners);
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, sbM.toString(), null, 8);
        RoundedCorners roundedCorners2 = displayInfo.roundedCorners;
        if (roundedCorners2 == null || (roundedCorner = roundedCorners2.getRoundedCorner(2)) == null || (radius = roundedCorner.getRadius()) <= 0) {
            return 0;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131170546);
        int iDpToPx = (dpToPx(i, context) - dpToPx(i3, context)) / 2;
        int i6 = radius - iDpToPx;
        OplusNavBarUtils oplusNavBarUtils = OplusNavBarUtils.INSTANCE;
        if (i6 > 0 && radius > 0) {
            if (i6 > radius) {
                d = i6;
                str = LogUtil.TAG_NAVBAR;
                i4 = 0;
                d2 = radius;
            } else {
                str = LogUtil.TAG_NAVBAR;
                i4 = 0;
                if (radius > i6) {
                    d = radius;
                    d2 = i6;
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    Log.d("SystemUi--NavBar", "OplusNavBarUtils-->The two sides entered cannot be equal (cannot be a right angle side and a hypotenuse at the same time)");
                }
            }
            if (d > d2) {
                double dPow = Math.pow(d, 2.0d) - Math.pow(d2, 2.0d);
                if (dPow < 0.0d) {
                    dPow = 0.0d;
                }
                iSqrt = (int) Math.sqrt(dPow);
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                Log.d("SystemUi--NavBar", "OplusNavBarUtils-->The hypotenuse must be longer than the right angle side");
            }
            if (iSqrt == 0) {
                if (!LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
                    Log.d("SystemUi--NavBar", "NavigationBarViewControlExImpl-->n2 == 0");
                    return i4;
                }
            }
            int i7 = radius - iSqrt;
            NavBarOliveLinkConnector navBarOliveLinkConnector = NavBarOliveLinkConnector.INSTANCE;
            int iMax = (int) Math.max(dpToPx(navBarOliveLinkConnector.getNAVBAR_PADDING_X1().get().intValue(), context) + i7, dpToPx(navBarOliveLinkConnector.getNAVBAR_PADDING_X().get().intValue(), context));
            int iDpToPx2 = (dimensionPixelSize - dpToPx(i2, context)) / 2;
            i5 = iMax - iDpToPx2;
            if (i5 > radius) {
                i5 = radius;
            }
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("result radius:", radius, dimensionPixelSize, " ,parentWidth:", " ,drawableBottomPadding:");
            RectList$.ExternalSyntheticOutline0.m(iDpToPx, i6, " ,n1:", " ,n2:", sbM2);
            RectList$.ExternalSyntheticOutline0.m(iSqrt, i7, " ,x2:", " ,x:", sbM2);
            RectList$.ExternalSyntheticOutline0.m(iMax, iDpToPx2, " ,p1:", " ,p2:", sbM2);
            sbM2.append(i5);
            LogUtil.i$default(str, TAG, sbM2.toString(), null, 8);
            return i5;
        }
        str = LogUtil.TAG_NAVBAR;
        i4 = 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str)) {
            Log.d("SystemUi--NavBar", "OplusNavBarUtils-->Side lengths must be positive");
        }
        iSqrt = i4;
        if (iSqrt == 0) {
            return !LogUtil.getNormalDebug() ? i4 : i4;
        }
        int i8 = radius - iSqrt;
        NavBarOliveLinkConnector navBarOliveLinkConnector2 = NavBarOliveLinkConnector.INSTANCE;
        int iMax2 = (int) Math.max(dpToPx(navBarOliveLinkConnector2.getNAVBAR_PADDING_X1().get().intValue(), context) + i8, dpToPx(navBarOliveLinkConnector2.getNAVBAR_PADDING_X().get().intValue(), context));
        int iDpToPx3 = (dimensionPixelSize - dpToPx(i2, context)) / 2;
        i5 = iMax2 - iDpToPx3;
        if (i5 > radius) {
            i5 = radius;
        }
        StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("result radius:", radius, dimensionPixelSize, " ,parentWidth:", " ,drawableBottomPadding:");
        RectList$.ExternalSyntheticOutline0.m(iDpToPx, i6, " ,n1:", " ,n2:", sbM3);
        RectList$.ExternalSyntheticOutline0.m(iSqrt, i8, " ,x2:", " ,x:", sbM3);
        RectList$.ExternalSyntheticOutline0.m(iMax2, iDpToPx3, " ,p1:", " ,p2:", sbM3);
        sbM3.append(i5);
        LogUtil.i$default(str, TAG, sbM3.toString(), null, 8);
        return i5;
    }

    public int getNavbarLayout() {
        VirtualKeyCombinationTypeObserver virtualKeyCombinationTypeObserver = VirtualKeyCombinationTypeObserver.INSTANCE;
        if (virtualKeyCombinationTypeObserver.getVirtualKeyCombinationType() == 0) {
            return OplusNavBarUtils.isFoldOpenNavigationLayout(this.context) ? 2131955100 : 2131955099;
        }
        if (virtualKeyCombinationTypeObserver.getVirtualKeyCombinationType() == 1) {
            return OplusNavBarUtils.isFoldOpenNavigationLayout(this.context) ? 2131955102 : 2131955101;
        }
        return 2131955099;
    }

    public void getSwitchViewRegion(Region region) {
        int width;
        int height;
        PositionSwitchViewController positionSwitchViewController = this.mPositionSwitchViewController;
        if (positionSwitchViewController == null || positionSwitchViewController.mIsDisableSwitchView) {
            return;
        }
        Context context = positionSwitchViewController.mContext;
        if (context == null) {
            LogUtil.w(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "getSwitchViewRegion mContext is null");
            return;
        }
        if (!OplusNavBarUtils.isFoldOpenNavigationLayout(context)) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "getSwitchViewRegion return FoldOpenNavigationLayout");
            return;
        }
        Rect rect = new Rect();
        int[] iArr = new int[2];
        boolean z = NavBarSettingsValueProxy.getNavButtonsSwitchDirect(positionSwitchViewController.mNavButtons.getContext()) == 0;
        if (z) {
            positionSwitchViewController.mRightSwitchView.getLocationInWindow(iArr);
            width = positionSwitchViewController.mRightSwitchView.getWidth();
            height = positionSwitchViewController.mRightSwitchView.getHeight();
        } else {
            positionSwitchViewController.mLeftSwitchView.getLocationInWindow(iArr);
            width = positionSwitchViewController.mLeftSwitchView.getWidth();
            height = positionSwitchViewController.mLeftSwitchView.getHeight();
        }
        if (z) {
            int i = iArr[0];
            int i2 = iArr[1];
            rect.set(i - width, i2 - height, i, i2);
        } else {
            int i3 = iArr[0];
            int i4 = iArr[1];
            rect.set(i3, i4, width + i3, height + i4);
        }
        region.op(rect, Region.Op.UNION);
        LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "getSwitchViewRegion " + region + " bounds: " + rect);
    }

    public int getVirtualKeyCombinationType() {
        return VirtualKeyCombinationTypeObserver.INSTANCE.getVirtualKeyCombinationType();
    }

    public void inflatePositionSwitchView(View view, NavigationBarView navigationBarView, boolean z) {
        if (view == null || !(view instanceof PercentFrameLayout)) {
            return;
        }
        PercentFrameLayout percentFrameLayout = (PercentFrameLayout) view;
        removePositionsSwitchControllerListener(navigationBarView);
        Context context = this.context;
        PositionSwitchViewController positionSwitchViewController = new PositionSwitchViewController();
        positionSwitchViewController.mIsAnimRunning = false;
        positionSwitchViewController.mIsDisableSwitchView = false;
        positionSwitchViewController.mContext = context;
        PositionSwitchView positionSwitchView = (PositionSwitchView) percentFrameLayout.findViewById(2131365528);
        positionSwitchViewController.mLeftSwitchView = positionSwitchView;
        PositionSwitchView positionSwitchView2 = (PositionSwitchView) percentFrameLayout.findViewById(2131365529);
        positionSwitchViewController.mRightSwitchView = positionSwitchView2;
        NearestTouchFrame nearestTouchFrameFindViewById = percentFrameLayout.findViewById(2131364120);
        positionSwitchViewController.mNavButtons = nearestTouchFrameFindViewById;
        positionSwitchViewController.mIsDisableSwitchView = z;
        if (positionSwitchView != null) {
            positionSwitchView.setOnClickListener(positionSwitchViewController);
        }
        if (positionSwitchView2 != null) {
            positionSwitchView2.setOnClickListener(positionSwitchViewController);
        }
        boolean z2 = NavBarSettingsValueProxy.getNavButtonsSwitchDirect(nearestTouchFrameFindViewById.getContext()) == 0;
        if (positionSwitchViewController.mIsDisableSwitchView) {
            if (positionSwitchView != null && positionSwitchView2 != null) {
                positionSwitchView.setVisibility(8);
                positionSwitchView2.setVisibility(8);
            }
        } else if (positionSwitchView != null && positionSwitchView2 != null) {
            positionSwitchView.setVisibility(z2 ? 8 : 0);
            positionSwitchView2.setVisibility(z2 ? 0 : 8);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) nearestTouchFrameFindViewById.getLayoutParams();
        layoutParams.gravity = z2 ? 8388611 : 8388613;
        nearestTouchFrameFindViewById.setLayoutParams(layoutParams);
        VarHandle.storeStoreFence();
        this.mPositionSwitchViewController = positionSwitchViewController;
        if (navigationBarView == null || navigationBarView.getBarTransitions() == null) {
            return;
        }
        NavigationBarTransitions barTransitions = navigationBarView.getBarTransitions();
        barTransitions.mDarkIntensityListeners.add(this.mPositionSwitchViewController);
        float currentDarkIntensity = barTransitions.mLightTransitionsController.getCurrentDarkIntensity();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("inflatePositionSwitchView: add listener, currentDarkIntensity = ", currentDarkIntensity, "NavigationBarViewControlExImpl-->", "SystemUi--NavBar");
        }
        PositionSwitchViewController positionSwitchViewController2 = this.mPositionSwitchViewController;
        if (positionSwitchViewController2 != null) {
            positionSwitchViewController2.onDarkIntensity(currentDarkIntensity);
        }
    }

    public boolean isGetFoldOpenNavigationLayout(Context context) {
        return OplusNavBarUtils.isFoldOpenNavigationLayout(context);
    }

    public boolean isLargeScreenGravitySpacer(Context context) {
        return context.getResources().getBoolean(2131034316);
    }

    public boolean isSupportHotZoneAvoid(Context context) {
        return NavBarUtils.isSupportHotZoneAvoid(context);
    }

    public final void onDirectChanged(int i) {
        PositionSwitchViewController positionSwitchViewController = this.mPositionSwitchViewController;
        if (positionSwitchViewController != null) {
            if (positionSwitchViewController.mIsAnimRunning) {
                LogUtil.d(LogUtil.TAG_NAVBAR, "PositionSwitchViewController", "onDirectChanged return for the anim is running");
                return;
            }
            if (i == 0 && positionSwitchViewController.mLeftSwitchView.getVisibility() == 0) {
                positionSwitchViewController.startAnimation(positionSwitchViewController.mLeftSwitchView, positionSwitchViewController.mRightSwitchView, positionSwitchViewController.mNavButtons, true);
            } else if (i == 1 && positionSwitchViewController.mRightSwitchView.getVisibility() == 0) {
                positionSwitchViewController.startAnimation(positionSwitchViewController.mLeftSwitchView, positionSwitchViewController.mRightSwitchView, positionSwitchViewController.mNavButtons, false);
            }
        }
    }

    public void removePositionsSwitchControllerListener(NavigationBarView navigationBarView) {
        NavigationBarTransitions barTransitions;
        PositionSwitchViewController positionSwitchViewController = this.mPositionSwitchViewController;
        if (positionSwitchViewController != null && navigationBarView != null && (barTransitions = navigationBarView.getBarTransitions()) != null) {
            barTransitions.mDarkIntensityListeners.remove(positionSwitchViewController);
        }
        this.mPositionSwitchViewController = null;
    }

    public final void updatePositionSwitchView(boolean z) {
        PositionSwitchViewController positionSwitchViewController = this.mPositionSwitchViewController;
        if (positionSwitchViewController == null || positionSwitchViewController.mIsDisableSwitchView == z) {
            return;
        }
        positionSwitchViewController.mIsDisableSwitchView = z;
        if (z) {
            positionSwitchViewController.mLeftSwitchView.setVisibility(8);
            positionSwitchViewController.mRightSwitchView.setVisibility(8);
        } else {
            boolean z2 = NavBarSettingsValueProxy.getNavButtonsSwitchDirect(positionSwitchViewController.mNavButtons.getContext()) == 0;
            positionSwitchViewController.mLeftSwitchView.setVisibility(z2 ? 8 : 0);
            positionSwitchViewController.mRightSwitchView.setVisibility(z2 ? 0 : 8);
        }
    }
}
