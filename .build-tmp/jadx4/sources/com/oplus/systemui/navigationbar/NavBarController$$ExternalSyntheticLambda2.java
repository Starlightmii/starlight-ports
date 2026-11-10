package com.oplus.systemui.navigationbar;

import com.android.systemui.DependencyEx;
import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.OplusNavigationBarView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NavBarController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NavBarController f$0;
    public /* synthetic */ int f$1;

    public /* synthetic */ NavBarController$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                NavBarController navBarController = this.f$0;
                int i2 = this.f$1;
                NavigationBarController navigationBarController = navBarController.mNavigationBarController;
                while (i < navigationBarController.getNavigationBars().size()) {
                    NavigationBar navigationBar = (NavigationBar) navigationBarController.getNavigationBars().valueAt(i);
                    if (navigationBar == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onUpGestureBarTypeChange: navbar is null");
                    } else {
                        OplusNavigationBarView oplusNavigationBarView = (OplusNavigationBarView) navigationBar.getView();
                        if (oplusNavigationBarView != null) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onUpGestureBarTypeChange: update");
                            oplusNavigationBarView.updateSwipeUpGestureBarVisible(i2);
                        }
                    }
                    i++;
                }
                break;
            case 1:
                NavBarController navBarController2 = this.f$0;
                int i3 = this.f$1;
                NavigationBarController navigationBarController2 = navBarController2.mNavigationBarController;
                while (i < navigationBarController2.getNavigationBars().size()) {
                    NavigationBar navigationBar2 = (NavigationBar) navigationBarController2.getNavigationBars().valueAt(i);
                    if (navigationBar2 == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onNavButtonsPositionChange: navbar is null");
                    } else {
                        OplusNavigationBarView oplusNavigationBarView2 = (OplusNavigationBarView) navigationBar2.getView();
                        if (oplusNavigationBarView2 != null) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onNavButtonsPositionChange: update, direct = " + i3);
                            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "navButtonsPositionChange(): direct = " + i3);
                            oplusNavigationBarView2.getInflaterView().resizeLayout();
                        }
                    }
                    i++;
                }
                break;
            case 2:
                NavBarController navBarController3 = this.f$0;
                int i4 = this.f$1;
                NavigationBarController navigationBarController3 = navBarController3.mNavigationBarController;
                for (int i5 = 0; i5 < navigationBarController3.getNavigationBars().size(); i5++) {
                    if (navigationBarController3.getNavigationBars().valueAt(i5) == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onSideGestureBarTypeChange: navbarValue is null");
                    } else {
                        NavigationBar navigationBar3 = (NavigationBar) navigationBarController3.getNavigationBars().valueAt(i5);
                        if (navigationBar3 == null) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onSideGestureBarTypeChange: navbar is null");
                        } else {
                            OplusNavigationBarView oplusNavigationBarView3 = (OplusNavigationBarView) navigationBar3.getView();
                            if (oplusNavigationBarView3 != null) {
                                LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onSideGestureBarTypeChange: update");
                                oplusNavigationBarView3.updateSideGestureBarVisible(i4);
                                if (NavBarUtils.isGestureSideMode()) {
                                    if (i4 == 0) {
                                        navigationBar3.setNavigationBarLumaSamplingEnabled(true);
                                    } else {
                                        navigationBar3.setNavigationBarLumaSamplingEnabled(false);
                                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "View is hidden, stopSampling");
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                break;
            default:
                NavBarController navBarController4 = this.f$0;
                int i6 = this.f$1;
                NavigationBarController navigationBarController4 = navBarController4.mNavigationBarController;
                while (i < navigationBarController4.getNavigationBars().size()) {
                    NavigationBar navigationBar4 = (NavigationBar) navigationBarController4.getNavigationBars().valueAt(i);
                    if (navigationBar4 == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onNavButtonsSwitchDirectChange: navbar is null");
                    } else if (((OplusNavigationBarView) navigationBar4.getView()) != null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onNavButtonsSwitchDirectChange: update");
                        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "navButtonsSwitchDirectChange(): direct = " + i6);
                        ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNarBarViewControlEx().onDirectChanged(i6);
                    }
                    i++;
                }
                break;
        }
    }
}
