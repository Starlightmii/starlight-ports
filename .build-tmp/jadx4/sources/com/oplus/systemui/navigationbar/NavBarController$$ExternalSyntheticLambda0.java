package com.oplus.systemui.navigationbar;

import com.android.systemui.navigationbar.NavigationBarController;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarInflaterView;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.OplusNavigationBarView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NavBarController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        int i2 = 0;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                NavigationBarController navigationBarController = ((NavBarController) obj).mNavigationBarController;
                while (i2 < navigationBarController.getNavigationBars().size()) {
                    NavigationBar navigationBar = (NavigationBar) navigationBarController.getNavigationBars().valueAt(i2);
                    if (navigationBar == null) {
                        LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onNavStateChange: navbar is null");
                    } else {
                        OplusNavigationBarView oplusNavigationBarView = (OplusNavigationBarView) navigationBar.getView();
                        if (oplusNavigationBarView != null) {
                            LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "onNavStateChange: update");
                            oplusNavigationBarView.updateViewVisible$1();
                        }
                    }
                    i2++;
                }
                break;
            case 1:
                NavigationBarController navigationBarController2 = ((NavBarController) obj).mNavigationBarController;
                while (i2 < navigationBarController2.getNavigationBars().size()) {
                    NavigationBar navigationBar2 = (NavigationBar) navigationBarController2.getNavigationBars().valueAt(i2);
                    if (navigationBar2 == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onVirtualKeyCombinationTypeChange: navbar is null");
                    } else {
                        OplusNavigationBarView oplusNavigationBarView2 = (OplusNavigationBarView) navigationBar2.getView();
                        if (oplusNavigationBarView2 != null) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onVirtualKeyCombinationTypeChange: update");
                            NavigationBarInflaterView navigationBarInflaterView = ((NavigationBarView) oplusNavigationBarView2).mNavigationInflaterView;
                            if (navigationBarInflaterView != null) {
                                navigationBarInflaterView.onLikelyDefaultLayoutChange();
                            }
                        }
                    }
                    i2++;
                }
                break;
            case 2:
                NavigationBarController navigationBarController3 = ((NavBarController) obj).mNavigationBarController;
                while (i2 < navigationBarController3.getNavigationBars().size()) {
                    NavigationBar navigationBar3 = (NavigationBar) navigationBarController3.getNavigationBars().valueAt(i2);
                    if (navigationBar3 == null) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onUpGestureKeysModeChange: navbar is null");
                    } else {
                        OplusNavigationBarView oplusNavigationBarView3 = (OplusNavigationBarView) navigationBar3.getView();
                        if (oplusNavigationBarView3 != null) {
                            LogUtil.d(LogUtil.TAG_NAVBAR, "NavBarController", "onUpGestureKeysModeChange: update");
                            oplusNavigationBarView3.updateSwipeUpGestureBarMode();
                        }
                    }
                    i2++;
                }
                break;
            default:
                ((NavigationBarController) obj).updateNavbarForTaskbar();
                break;
        }
    }
}
