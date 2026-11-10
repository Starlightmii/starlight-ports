package com.oplus.systemui.shade;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.NotificationShadeWindowViewControllerEx;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerStore;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.material.NotificationSpotLightTouchBridge;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.keyguard.OplusLockIconViewController;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationShadeWindowViewControllerExImpl extends NotificationShadeWindowViewControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationShadeWindowViewControllerExImpl";
    private final Lazy capsuleContainerControllerImpl;
    private final Lazy centralSurfaces;
    private final Context context;
    private MotionEvent downEvent;
    public float hoverLastX = Float.NaN;
    public float hoverLastY = Float.NaN;
    public long hoverResetOffLastTime;
    public boolean isMoveStopNeedResetOffTime;
    public boolean isScrolling;
    private final Lazy keyguardUpdateMediator;
    private final Lazy keyguardViewMediator;
    public long lastMoveTime;
    public float lastMoveX;
    public float lastMoveY;
    public long moveResetOffLastTime;
    private final Lazy notificationPanelViewController;
    private final Lazy notificationStackScrollLayoutController;
    private final Lazy oplusKeyguardBottomAreaController;
    private final Lazy oplusLockIconViewController;
    private final Lazy scrimController;
    private final Lazy shadeController;
    private final Lazy shadeWindowController;
    private final Lazy statusBarKeyguardViewManager;
    private final Lazy statusBarStateController;
    private final Lazy statusBarWindowControllerStore;
    public boolean touchActive;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationShadeWindowViewControllerExImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, Lazy lazy13, Lazy lazy14) {
        this.context = context;
        this.shadeWindowController = lazy;
        this.statusBarWindowControllerStore = lazy2;
        this.keyguardUpdateMediator = lazy3;
        this.keyguardViewMediator = lazy4;
        this.statusBarKeyguardViewManager = lazy5;
        this.shadeController = lazy6;
        this.statusBarStateController = lazy7;
        this.scrimController = lazy8;
        this.notificationPanelViewController = lazy9;
        this.notificationStackScrollLayoutController = lazy10;
        this.oplusKeyguardBottomAreaController = lazy11;
        this.oplusLockIconViewController = lazy12;
        this.centralSurfaces = lazy13;
        this.capsuleContainerControllerImpl = lazy14;
    }

    public void forwardSpotLightTouch(MotionEvent motionEvent) {
        ViewGroup view = ((NotificationPanelViewController) this.notificationPanelViewController.get()).getView();
        view.getClass();
        NotificationSpotLightTouchBridge.forwardPanelDispatchTouch(view, motionEvent, (NotificationStackScrollLayoutController) this.notificationStackScrollLayoutController.get(), (NotificationCapsuleContainerControllerImpl) this.capsuleContainerControllerImpl.get(), (SysuiStatusBarStateController) this.statusBarStateController.get());
    }

    public void handleHoverSlideNoLock(MotionEvent motionEvent) {
        if (((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() != 1) {
            this.hoverLastX = Float.NaN;
            this.hoverLastY = Float.NaN;
            return;
        }
        if (motionEvent.getActionMasked() != 7) {
            if (motionEvent.getActionMasked() == 10) {
                this.hoverLastX = Float.NaN;
                this.hoverLastY = Float.NaN;
                return;
            }
            return;
        }
        if (motionEvent.isFromSource(2)) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = this.hoverLastX;
            float f2 = this.hoverLastY;
            this.hoverLastX = rawX;
            this.hoverLastY = rawY;
            if (Float.isNaN(f) || Float.isNaN(f2)) {
                return;
            }
            if (Math.abs(rawX - f) > 5.0f || Math.abs(rawY - f2) > 5.0f) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.hoverResetOffLastTime > 5000) {
                    ((CentralSurfaces) this.centralSurfaces.get()).userActivity();
                    this.hoverResetOffLastTime = jCurrentTimeMillis;
                    LogUtil.i$default(TAG, "reset sceenoff time---hover moving", null, null, 12);
                }
            }
        }
    }

    public void handleSlideNoLock(MotionEvent motionEvent) {
        if (((SysuiStatusBarStateController) this.statusBarStateController.get()).getState() != 1) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            this.isScrolling = false;
            this.lastMoveX = motionEvent.getRawX();
            this.lastMoveY = motionEvent.getRawY();
            MotionEvent motionEvent2 = this.downEvent;
            if (motionEvent2 != null) {
                motionEvent2.recycle();
            }
            this.downEvent = MotionEvent.obtain(motionEvent);
            this.touchActive = true;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "NotificationShadeWindowViewControllerExImpl-->reset sceenoff time---down");
                return;
            }
            return;
        }
        if (motionEvent.getActionMasked() != 2) {
            if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                this.touchActive = false;
                MotionEvent motionEvent3 = this.downEvent;
                if (motionEvent3 != null) {
                    motionEvent3.recycle();
                }
                this.downEvent = null;
                this.isScrolling = false;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "NotificationShadeWindowViewControllerExImpl-->reset sceenoff time---up");
                }
                ((CentralSurfaces) this.centralSurfaces.get()).userActivity();
                return;
            }
            return;
        }
        if (this.touchActive && this.downEvent != null && !this.isScrolling) {
            float rawX = motionEvent.getRawX();
            MotionEvent motionEvent4 = this.downEvent;
            float fAbs = Math.abs(rawX - (motionEvent4 != null ? motionEvent4.getRawX() : 0.0f));
            float rawY = motionEvent.getRawY();
            MotionEvent motionEvent5 = this.downEvent;
            float fAbs2 = Math.abs(rawY - (motionEvent5 != null ? motionEvent5.getRawY() : 0.0f));
            if (fAbs > 20.0f || fAbs2 > 20.0f) {
                this.isScrolling = true;
            }
        }
        if (this.touchActive && this.isScrolling) {
            float fAbs3 = Math.abs(motionEvent.getRawX() - this.lastMoveX);
            float fAbs4 = Math.abs(motionEvent.getRawY() - this.lastMoveY);
            if (fAbs3 > 5.0f || fAbs4 > 5.0f) {
                if (System.currentTimeMillis() - this.moveResetOffLastTime > 5000) {
                    ((CentralSurfaces) this.centralSurfaces.get()).userActivity();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        Log.d("SystemUi--Keyguard", "NotificationShadeWindowViewControllerExImpl-->reset sceenoff time---moving...");
                    }
                    this.moveResetOffLastTime = System.currentTimeMillis();
                }
                this.isMoveStopNeedResetOffTime = true;
                this.lastMoveTime = System.currentTimeMillis();
            } else if (this.isMoveStopNeedResetOffTime && System.currentTimeMillis() - this.lastMoveTime > 1000) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "NotificationShadeWindowViewControllerExImpl-->reset sceenoff time---moving to stop");
                }
                ((CentralSurfaces) this.centralSurfaces.get()).userActivity();
                this.isMoveStopNeedResetOffTime = false;
            }
        }
        this.lastMoveX = motionEvent.getRawX();
        this.lastMoveY = motionEvent.getRawY();
    }

    public boolean isTouchCancelledEnd(MotionEvent motionEvent, boolean z) {
        if (z) {
            return motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1;
        }
        return false;
    }

    public void setLockIconView(NotificationShadeWindowView notificationShadeWindowView) {
        OplusLockIconViewController oplusLockIconViewController = (OplusLockIconViewController) this.oplusLockIconViewController.get();
        if (oplusLockIconViewController != null) {
            View viewFindViewById = notificationShadeWindowView.findViewById(2131363783);
            viewFindViewById.getClass();
            oplusLockIconViewController.setLockIconView((OplusLockIconView) viewFindViewById);
        }
    }

    public boolean tryResetShadeWindowActiveWithTouch(MotionEvent motionEvent) {
        NotificationShadeWindowState currentShadeWindowState;
        NotificationShadeWindowViewController base = getBase();
        if (base == null) {
            return super.tryResetShadeWindowActiveWithTouch(motionEvent);
        }
        if (motionEvent.getAction() == 0 && base.getView().getVisibility() == 0 && ((KeyguardUpdateMonitor) this.keyguardUpdateMediator.get()).mDeviceInteractive && !((KeyguardViewMediator) this.keyguardViewMediator.get()).isShowingAndNotOccluded() && motionEvent.getRawY() > ((StatusBarWindowController) ((StatusBarWindowControllerStore) this.statusBarWindowControllerStore.get()).getDefaultDisplay()).getStatusBarHeight() && Math.abs(System.currentTimeMillis() - base.getView().getEx().getWindowViewStartVisibleTime()) > 1000 && (currentShadeWindowState = ((NotificationShadeWindowController) this.shadeWindowController.get()).getCurrentShadeWindowState()) != null) {
            if (currentShadeWindowState.bouncerShowing && !((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManager.get()).mPrimaryBouncerInteractor.isInTransit() && !base.getView().getEx().isKeyguardBouncerContainerVisible()) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "tryResetShadeWindowActiveWithTouch: Abnormal bouncer showing state", null, 8);
                StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) this.statusBarKeyguardViewManager.get();
                if (statusBarKeyguardViewManager.mKeyguardStateController.mShowing) {
                    statusBarKeyguardViewManager.reset(true);
                    return true;
                }
                statusBarKeyguardViewManager.hideBouncer(false);
                statusBarKeyguardViewManager.updateStates();
                statusBarKeyguardViewManager.mStatusBarStateController.setLeaveOpenOnKeyguardHide(false);
                return true;
            }
            if (!currentShadeWindowState.keyguardShowing && !((NotificationPanelViewController) this.notificationPanelViewController.get()).isCollapsing() && ((NotificationPanelViewController) this.notificationPanelViewController.get()).getBarState() == 0) {
                for (View view : ViewGroupKt.getChildren(base.getView())) {
                    if (!(view instanceof ScrimView) && view.getVisibility() == 0) {
                        if (view.getId() == 2131363587 && (view instanceof ViewGroup)) {
                            Iterator it = ViewGroupKt.getChildren((ViewGroup) view).iterator();
                            while (it.hasNext()) {
                                if (((View) it.next()).getVisibility() == 0) {
                                }
                            }
                        }
                    }
                }
                boolean zLeaveOpenOnKeyguardHide = ((SysuiStatusBarStateController) this.statusBarStateController.get()).leaveOpenOnKeyguardHide();
                boolean z = currentShadeWindowState.scrimsVisibility != 0;
                if (zLeaveOpenOnKeyguardHide || z) {
                    ((SysuiStatusBarStateController) this.statusBarStateController.get()).setLeaveOpenOnKeyguardHide(false);
                    ((ShadeController) this.shadeController.get()).instantCollapseShade();
                    if (zLeaveOpenOnKeyguardHide) {
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "tryResetShadeWindowActiveWithTouch: Abnormal leave open on keyguard hide", null, 8);
                    }
                    if (z) {
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "tryResetShadeWindowActiveWithTouch: Abnormal scrims visibility state", null, 8);
                        ((ScrimController) this.scrimController.get()).getExt().forceResetScrimVisibility();
                    }
                    return true;
                }
                if (currentShadeWindowState.panelVisible && ((NotificationPanelViewController) this.notificationPanelViewController.get()).getView().getVisibility() != 0) {
                    NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) this.notificationPanelViewController.get();
                    if (notificationPanelViewController.mHeightAnimator == null || notificationPanelViewController.mIsSpringBackAnimation) {
                        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "tryResetShadeWindowActiveWithTouch: Abnormal panel visible state", null, 8);
                        ((ShadeController) this.shadeController.get()).makeExpandedInvisible();
                        return true;
                    }
                }
                StringBuilder sb = new StringBuilder();
                int size = currentShadeWindowState.getAsStringList().size();
                int iMin = Math.min(NotificationShadeWindowState.TABLE_HEADERS.size(), size);
                int i = 0;
                while (i < iMin) {
                    if (i != 0) {
                        sb.append(OplusPreventModeData.SEPARATOR);
                    }
                    sb.append((String) NotificationShadeWindowState.TABLE_HEADERS.get(i));
                    sb.append("=");
                    sb.append((String) currentShadeWindowState.getAsStringList().get(i));
                    i++;
                }
                while (i < size) {
                    sb.append(OplusPreventModeData.SEPARATOR);
                    sb.append((String) currentShadeWindowState.getAsStringList().get(i));
                    i++;
                }
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "tryResetShadeWindowActiveWithTouch: Abnormal showing shade with windowState(" + ((Object) sb) + ")", null, 8);
                return false;
            }
        }
        return false;
    }
}
