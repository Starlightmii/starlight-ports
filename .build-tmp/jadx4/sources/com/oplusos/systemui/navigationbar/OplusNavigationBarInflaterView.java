package com.oplusos.systemui.navigationbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.android.systemui.navigationbar.NavigationBarViewControlEx;
import com.android.systemui.navigationbar.UtilsStaticToolsEx;
import com.android.systemui.navigationbar.views.NavigationBarInflaterView;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.shared.system.QuickStepContract;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNavigationBarInflaterView extends NavigationBarInflaterView {
    public boolean mIsFoldOpen;
    public boolean mIsFoldOpenLayout;
    public boolean mIsGesturalMode;
    public NavigationBarViewControlEx mNavBarViewControlEx;
    public OplusNavigationBarView mOplusNavigationBarView;
    public UtilsStaticToolsEx mUtilsStaticToolsEx;

    public OplusNavigationBarInflaterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsFoldOpen = false;
        this.mIsFoldOpenLayout = false;
        this.mIsGesturalMode = false;
    }

    private NavigationBarViewControlEx getNavigationBarViewControlEx() {
        if (this.mNavBarViewControlEx == null) {
            this.mNavBarViewControlEx = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNarBarViewControlEx();
        }
        return this.mNavBarViewControlEx;
    }

    private UtilsStaticToolsEx getUtilsStaticToolsEx() {
        if (this.mUtilsStaticToolsEx == null) {
            this.mUtilsStaticToolsEx = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getUtilsStaticToolsExImpl();
        }
        return this.mUtilsStaticToolsEx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addGravitySpacer(LinearLayout linearLayout) {
        if (getNavigationBarViewControlEx().isGetFoldOpenNavigationLayout(((FrameLayout) this).mContext)) {
            linearLayout.addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 0.0f));
            return;
        }
        if (!getNavigationBarViewControlEx().isLargeScreenGravitySpacer(((FrameLayout) this).mContext)) {
            linearLayout.addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 86.0f));
            return;
        }
        OplusNavigationBarView oplusNavigationBarView = this.mOplusNavigationBarView;
        if (oplusNavigationBarView == null || !(oplusNavigationBarView.getCurrentRotation() == 0 || this.mOplusNavigationBarView.getCurrentRotation() == 2)) {
            linearLayout.addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 72.0f));
        } else {
            linearLayout.addView(new Space(((FrameLayout) this).mContext), new LinearLayout.LayoutParams(0, 0, 106.0f));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String getDefaultLayout() {
        int navbarLayout = getNavigationBarViewControlEx().getNavbarLayout();
        if (QuickStepContract.isGesturalMode(((NavigationBarInflaterView) this).mNavBarMode)) {
            navbarLayout = 2131952705;
        }
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", "getDefaultLayout(): navbarLayout = " + ((FrameLayout) this).mContext.getString(navbarLayout));
        return ((FrameLayout) this).mContext.getString(navbarLayout);
    }

    public boolean getIsFoldOpen() {
        return this.mIsFoldOpen;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void inflateChildren() {
        removeAllViews();
        boolean zIsGetFoldOpenNavigationLayout = getNavigationBarViewControlEx().isGetFoldOpenNavigationLayout(((FrameLayout) this).mContext);
        this.mIsFoldOpen = zIsGetFoldOpenNavigationLayout;
        boolean zIsGesturalMode = QuickStepContract.isGesturalMode(((NavigationBarInflaterView) this).mNavBarMode);
        this.mIsGesturalMode = zIsGesturalMode;
        boolean z = zIsGetFoldOpenNavigationLayout && !zIsGesturalMode;
        this.mIsFoldOpenLayout = z;
        FrameLayout frameLayout = (FrameLayout) ((NavigationBarInflaterView) this).mLayoutInflater.inflate(z ? getNavigationBarViewControlEx().getFoldOpenLayout(true) : 2131559184, (ViewGroup) this, false);
        ((NavigationBarInflaterView) this).mHorizontal = frameLayout;
        addView(frameLayout);
        FrameLayout frameLayout2 = (FrameLayout) ((NavigationBarInflaterView) this).mLayoutInflater.inflate(this.mIsFoldOpenLayout ? getNavigationBarViewControlEx().getFoldOpenLayout(false) : 2131559186, (ViewGroup) this, false);
        ((NavigationBarInflaterView) this).mVertical = frameLayout2;
        addView(frameLayout2);
        updateAlternativeOrder();
        OplusNavigationBarView oplusNavigationBarView = this.mOplusNavigationBarView;
        if (oplusNavigationBarView != null) {
            oplusNavigationBarView.setIsFoldOpenLayout(this.mIsFoldOpenLayout);
        }
        LogUtil.i(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", "inflateChildren:, mIsFoldOpen = " + this.mIsFoldOpen + "  ,mIsGesturalMode:" + this.mIsGesturalMode + " ,mOplusNavigationBarView:" + this.mOplusNavigationBarView);
    }

    public final void onLikelyDefaultLayoutChange() {
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", "onLikelyDefaultLayoutChange()");
        if (getUtilsStaticToolsEx().getNavButtonsPosition() == 1) {
            if (!Objects.equals(((NavigationBarInflaterView) this).mCurrentLayout, getDefaultLayout())) {
                resizeLayout();
            }
        } else {
            super.onLikelyDefaultLayoutChange();
        }
        super.onLikelyDefaultLayoutChange();
    }

    public final void onNavigationModeChanged(int i) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "b/182478748 NavigationBarInflaterView.onNavModeChanged: mode=", LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView");
        ((NavigationBarInflaterView) this).mNavBarMode = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.widget.FrameLayout, com.android.systemui.navigationbar.views.NavigationBarView, com.oplusos.systemui.navigationbar.OplusNavigationBarView] */
    public final void resizeLayout() {
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", "updateFoldLayout()");
        setVisibility(getUtilsStaticToolsEx().isHideNavBarGestureMode() ? 8 : 0);
        inflateChildren();
        clearViews();
        ?? r0 = this.mOplusNavigationBarView;
        if (r0 != 0) {
            ((NavigationBarView) r0).mHorizontal = r0.findViewById(2131363370);
            ((NavigationBarView) r0).mVertical = r0.findViewById(2131365954);
            r0.updateCurrentView();
        }
        inflateLayout(getDefaultLayout());
        LogUtil.i(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", "resizeLayout:, mIsFoldOpen = " + this.mIsFoldOpen + "  ,mIsGesturalMode:" + this.mIsGesturalMode + " ,mOplusNavigationBarView:" + this.mOplusNavigationBarView);
        OplusNavigationBarView oplusNavigationBarView = this.mOplusNavigationBarView;
        if (oplusNavigationBarView == null || this.mIsFoldOpen || this.mIsGesturalMode) {
            return;
        }
        oplusNavigationBarView.setNavBarPadding();
    }

    public void setNavBarPadding(int i) {
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setNavBarPadding:", ", mIsFoldOpen = ");
        sbM.append(this.mIsFoldOpen);
        sbM.append("  ,mIsGesturalMode:");
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_NAVBAR, "OplusNavigationBarInflaterView", sbM, this.mIsGesturalMode);
        if (this.mIsFoldOpen || this.mIsGesturalMode) {
            return;
        }
        FrameLayout frameLayout = ((NavigationBarInflaterView) this).mHorizontal;
        if (frameLayout != null) {
            frameLayout.setPadding(i, 0, i, 0);
        }
        FrameLayout frameLayout2 = ((NavigationBarInflaterView) this).mVertical;
        if (frameLayout2 != null) {
            frameLayout2.setPadding(0, i, 0, i);
        }
    }

    public void setParentView(OplusNavigationBarView oplusNavigationBarView) {
        this.mOplusNavigationBarView = oplusNavigationBarView;
        oplusNavigationBarView.setIsFoldOpenLayout(this.mIsFoldOpenLayout);
    }

    public final void updateButtonDispatchersCurrentView() {
        super.updateButtonDispatchersCurrentView();
        OplusNavigationBarView oplusNavigationBarView = this.mOplusNavigationBarView;
        if (oplusNavigationBarView != null) {
            ((NavigationBarView) oplusNavigationBarView).mEdgeBackGestureHandler.setHomeButton((QuickStepContract.isGesturalMode(((NavigationBarView) oplusNavigationBarView).mNavBarMode) ? oplusNavigationBarView.getHomeHandle() : oplusNavigationBarView.getHomeButton()).getCurrentView());
        }
    }
}
