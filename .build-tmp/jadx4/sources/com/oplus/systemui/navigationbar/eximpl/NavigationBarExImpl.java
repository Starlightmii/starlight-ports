package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.navigationbar.NavigationBarEx;
import com.android.systemui.navigationbar.otherbusiness.circlesearch.OplusCircleToSearchManagerEx;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarFrame;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.oplus.app.OplusSplitScreenObserver;
import com.oplus.splitscreen.OplusSplitScreenManager;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplus.systemui.navigationbar.utils.DisplaySizeUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.volume.manager.VolumeAnimStateManager;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarExImpl extends NavigationBarEx {
    public static final Companion Companion = new Companion();
    private final NavigationBar bar;
    private final Map<NavigationBarEx.OnVolumeAnimStateChangeListener, VolumeAnimStateManager.VolumeAnimStateChangeListener> listenerMap;
    private final IObserverListener mChildModeListener;
    private final OplusSplitScreenObserver mSplitScreenObserver;
    private final IObserverListener mStudyCenterModeListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NavigationBarExImpl(NavigationBar navigationBar) {
        super(navigationBar);
        this.bar = navigationBar;
        this.listenerMap = new LinkedHashMap();
        NavigationBarExImpl$mChildModeListener$1 navigationBarExImpl$mChildModeListener$1 = new NavigationBarExImpl$mChildModeListener$1(0);
        navigationBarExImpl$mChildModeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mChildModeListener = navigationBarExImpl$mChildModeListener$1;
        NavigationBarExImpl$mChildModeListener$1 navigationBarExImpl$mChildModeListener$2 = new NavigationBarExImpl$mChildModeListener$1(1);
        navigationBarExImpl$mChildModeListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStudyCenterModeListener = navigationBarExImpl$mChildModeListener$2;
        this.mSplitScreenObserver = new OplusSplitScreenObserver() { // from class: com.oplus.systemui.navigationbar.eximpl.NavigationBarExImpl$mSplitScreenObserver$1
            public void onStateChanged(String str, Bundle bundle) {
                NavigationBarView view;
                ButtonDispatcher homeHandle;
                if ("splitScreenModeChange".equals(str)) {
                    boolean z = bundle.getBoolean("isInSplitScreenMode", false);
                    NavigationBar bar = this.this$0.getBar();
                    View currentView = (bar == null || (view = bar.getView()) == null || (homeHandle = view.getHomeHandle()) == null) ? null : homeHandle.getCurrentView();
                    if (currentView != null && (currentView instanceof OplusNavigationHandle)) {
                        OplusNavigationHandle oplusNavigationHandle = (OplusNavigationHandle) currentView;
                        oplusNavigationHandle.setInSplitScreenMode(z);
                        oplusNavigationHandle.setSplitScreenChange(true);
                    }
                    LogUtil.i$default(LogUtil.TAG_NAVBAR, "NavigationBar", KeyguardLogger$.ExternalSyntheticOutline0.m("onStateChanged() mInSplitScreenMode = ", z), null, 8);
                    NavigationBar bar2 = this.this$0.getBar();
                    if (bar2 != null) {
                        Runnable runnable = bar2.updateSplitScreenRegionDarkRunnable;
                        Handler handler = bar2.mRSampleHandler;
                        if (handler.hasCallbacks(runnable)) {
                            handler.removeCallbacks(runnable);
                        }
                        bar2.getBarTransitions().getLightTransitionsController().setSplitScreenChange(true);
                        LogUtil.d(LogUtil.TAG_NAVBAR, "NavigationBar", "onSplitScreenModeChange updateSplitScreenRegionDarkRunnable");
                        handler.postDelayed(runnable, 0L);
                    }
                }
            }
        };
    }

    public static final void access$onChildrenModeChanged(NavigationBarExImpl navigationBarExImpl) {
        NavigationBar navigationBar;
        if (!NavBarUtils.isVirtualKeyMode() || (navigationBar = navigationBarExImpl.bar) == null) {
            return;
        }
        navigationBar.updateAccessibilityStateFlags();
    }

    public static final void access$onStudyCenterModeChanged(NavigationBarExImpl navigationBarExImpl) {
        NavigationBar navigationBar;
        if (!NavBarUtils.isVirtualKeyMode() || (navigationBar = navigationBarExImpl.bar) == null) {
            return;
        }
        navigationBar.updateAccessibilityStateFlags();
    }

    public final void addChildrenModeObserver() {
        ChildrenModeObserver.getInstance().addListener(this.mChildModeListener);
    }

    public void addVolumeAnimStateChangeListener(NavigationBarEx.OnVolumeAnimStateChangeListener onVolumeAnimStateChangeListener) {
        NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1 navigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1 = new NavigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1();
        navigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1.$listener = onVolumeAnimStateChangeListener;
        VarHandle.storeStoreFence();
        this.listenerMap.put(onVolumeAnimStateChangeListener, navigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1);
        VolumeAnimStateManager.INSTANCE.addStateChangeListener(navigationBarExImpl$addVolumeAnimStateChangeListener$volumeListener$1);
    }

    public final NavigationBar getBar() {
        return this.bar;
    }

    public final void registerSplitScreenListener() {
        OplusSplitScreenManager.getInstance().registerSplitScreenObserver(this.mSplitScreenObserver);
    }

    public final void removeChildrenModeObserver() {
        ChildrenModeObserver.getInstance().removeListener(this.mChildModeListener);
    }

    public void removeVolumeAnimStateChangeListener(NavigationBarEx.OnVolumeAnimStateChangeListener onVolumeAnimStateChangeListener) {
        VolumeAnimStateManager.VolumeAnimStateChangeListener volumeAnimStateChangeListenerRemove = this.listenerMap.remove(onVolumeAnimStateChangeListener);
        if (volumeAnimStateChangeListenerRemove != null) {
            VolumeAnimStateManager.INSTANCE.removeStateChangeListener(volumeAnimStateChangeListenerRemove);
        }
    }

    public final void unRegisterSplitScreenListener() {
        OplusSplitScreenManager.getInstance().unregisterSplitScreenObserver(this.mSplitScreenObserver);
    }

    public boolean updateInsetsTouchability(Context context, OplusCircleToSearchManagerEx oplusCircleToSearchManagerEx, NavigationBarView navigationBarView, ViewTreeObserver.InternalInsetsInfo internalInsetsInfo, Region region) {
        List<ButtonDispatcher> listListOf = CollectionsKt__CollectionsKt.listOf(new ButtonDispatcher[]{navigationBarView.getBackButton(), navigationBarView.getHomeButton(), navigationBarView.getRecentsButton()});
        if (!(listListOf instanceof Collection) || !listListOf.isEmpty()) {
            for (ButtonDispatcher buttonDispatcher : listListOf) {
                if (buttonDispatcher == null || buttonDispatcher.getVisibility() != 0) {
                    internalInsetsInfo.setTouchableInsets(3);
                    internalInsetsInfo.touchableRegion.set(region);
                    return true;
                }
            }
        }
        if (oplusCircleToSearchManagerEx.updateInsetsTouchability(internalInsetsInfo, region)) {
            return true;
        }
        if (!NavBarUtils.isSupportHotZoneAvoid(context)) {
            return false;
        }
        internalInsetsInfo.setTouchableInsets(3);
        int iConvertDpToPixel = DisplaySizeUtils.convertDpToPixel(context, 48.0f);
        internalInsetsInfo.touchableRegion.set(new Region(navigationBarView.getLeft() + iConvertDpToPixel, navigationBarView.getTop(), navigationBarView.getRight() - iConvertDpToPixel, navigationBarView.getBottom()));
        return true;
    }

    public void userSwitchedNavigationBarHeightInsets(NavigationBarView navigationBarView, NavigationBarFrame navigationBarFrame, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        if (navigationBarFrame == null || layoutParams == null || navigationBarView == null || windowManager == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(navigationBarView.getNavBarHeight(), "userSwitchedNavigationBarHeightInsets ::", "NavigationBar-->", "SystemUi--NavBar");
        }
        if (navigationBarView.isAttachedToWindow()) {
            windowManager.updateViewLayout(navigationBarFrame, layoutParams);
        }
    }
}
