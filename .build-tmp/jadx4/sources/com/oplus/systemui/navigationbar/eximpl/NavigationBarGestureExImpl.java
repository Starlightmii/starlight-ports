package com.oplus.systemui.navigationbar.eximpl;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.ViewConfiguration;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.Dependency;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationBarGestureEx;
import com.android.systemui.navigationbar.SideGestureConfigurationEx;
import com.android.systemui.navigationbar.SideGestureDetectorEx;
import com.android.systemui.navigationbar.SideGestureViewManagerEx;
import com.android.systemui.plugins.FalsingManager;
import com.oplus.systemui.navigationbar.gesture.manager.GestureUIThreadManager;
import com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureConfiguration;
import com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector;
import com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureManager;
import com.oplus.systemui.navigationbar.gesture.sidegesture.view.SideGestureViewManager;
import com.oplus.systemui.navigationbar.observer.OplusGameModeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeSideGestureMistouchPreventionObserver;
import com.oplus.systemui.navigationbar.utils.NavBarScreenSizeHelper;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarGestureExImpl extends NavigationBarGestureEx {
    public static final Companion Companion = new Companion();
    public static final String TAG = "NavigationBarGestureExImpl";
    public boolean oplusFlagAllowBackGestureIgnoreVisibility;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.eximpl.NavigationBarGestureExImpl$updateOplusFlagAllowBackGestureIgnoreVisibility$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ NavigationBarGestureExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            ((SysUiState) Dependency.sDependency.getDependencyInner(SysUiState.class)).setFlag(131072L, this.this$0.oplusFlagAllowBackGestureIgnoreVisibility).commitUpdate();
        }
    }

    public SideGestureConfigurationEx createSideGestureConfiguration(Context context, Rect rect, int i) {
        return new SideGestureConfiguration(context, rect, i);
    }

    public SideGestureDetectorEx createSideGestureDetector(Context context, Display display, List<ComponentName> list, Executor executor, LauncherProxyService launcherProxyService, FalsingManager falsingManager, Handler handler, ViewConfiguration viewConfiguration, SideGestureConfigurationEx sideGestureConfigurationEx) {
        return new SideGestureDetector(context, display, list, executor, falsingManager, handler, sideGestureConfigurationEx);
    }

    public SideGestureViewManagerEx createSideGestureViewManager(Context context, Display display, SideGestureConfigurationEx sideGestureConfigurationEx) {
        return new SideGestureViewManager(context, display, sideGestureConfigurationEx);
    }

    public Handler getGestureUIHandler() {
        return GestureUIThreadManager.INSTANCE.getGestureUIHandler();
    }

    public HandlerThread getGestureUiThread() {
        return GestureUIThreadManager.INSTANCE.getGestureUiThread();
    }

    public Rect getScreenRect(Context context) {
        Rect screenRect;
        NavBarScreenSizeHelper companion = NavBarScreenSizeHelper.Companion.getInstance(context);
        return (companion == null || (screenRect = companion.getScreenRect(context)) == null) ? new Rect() : screenRect;
    }

    public void initSideGestureManager(Context context) {
        if (SideGestureManager.sInstance == null) {
            synchronized (SideGestureManager.class) {
                try {
                    if (SideGestureManager.sInstance == null) {
                        SideGestureManager.sInstance = new SideGestureManager(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public boolean isTablet(Context context) {
        NavBarScreenSizeHelper companion = NavBarScreenSizeHelper.Companion.getInstance(context);
        if (companion != null) {
            return companion.isTablet(context);
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:4:0x000b  */
    public final boolean oplusAllowSystemGestureIgnoringBarVisibility() {
        boolean z;
        SwipeSideGestureMistouchPreventionObserver.INSTANCE.getClass();
        if (SwipeSideGestureMistouchPreventionObserver.mSwipeSideGestureMistouchPrevention == 0) {
            z = true;
        } else {
            OplusGameModeObserver.INSTANCE.getClass();
            if (OplusGameModeObserver.oplusGameModeObserverState == 1 && SwipeSideGestureMistouchPreventionObserver.mSwipeSideGestureMistouchPrevention == 1) {
                z = true;
            } else {
                z = false;
            }
        }
        LogUtil.i$default(LogUtil.TAG_NAVBAR, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("oplusAllowSystemGestureIgnoringBarVisibility isOplusFlagAllowBackGestureIgnoreVisibilityState = ", " isGestureMisTouchPreventionEnable = ", this.oplusFlagAllowBackGestureIgnoreVisibility, z), null, 8);
        return this.oplusFlagAllowBackGestureIgnoreVisibility || !z;
    }

    public final void refreshNavBarIcons(int i) {
        refreshNavBarIcons.1 r0 = new refreshNavBarIcons.1();
        r0.$oplusFlags = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public void registerGameModeListener(IObserverListener iObserverListener) {
        OplusGameModeObserver.INSTANCE.addListener(iObserverListener);
    }

    public void unRegisterGameModeListener(IObserverListener iObserverListener) {
        OplusGameModeObserver.INSTANCE.removeListener(iObserverListener);
    }

    public final void updateOplusFlagAllowBackGestureIgnoreVisibility(int i) {
        boolean z = (i & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) != 0;
        if (z != this.oplusFlagAllowBackGestureIgnoreVisibility) {
            this.oplusFlagAllowBackGestureIgnoreVisibility = z;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(anonymousClass1);
        }
    }
}
