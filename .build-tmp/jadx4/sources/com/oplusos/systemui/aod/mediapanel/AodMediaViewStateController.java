package com.oplusos.systemui.aod.mediapanel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.PowerManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout;
import com.oplus.systemui.aod.controller.WorkshopAodController;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderCollapse;
import com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderExpanded;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaViewStateController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AodMediaViewState";
    public boolean animating;
    private final configChangeListener.1 configChangeListener;
    public Context context;
    public int curMode;
    public volatile int curState;
    public boolean rehearsing;
    public boolean shown;
    private final AodMediaViewStateHolderCollapse viewStateCollapse;
    private final AodMediaViewStateHolderExpanded viewStateExpanded;
    private PowerManager.WakeLock wakeLock;
    private final AodMediaViewStateAnimation animator = new AodMediaViewStateAnimation(this, this);
    private final CapsuleProgressAnimation progressAnimator = new CapsuleProgressAnimation(this, this);
    private final Set<AodMediaViewStateListener> _listeners = new CopyOnWriteArraySet();
    public int aodLayoutDirection = 1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AodMediaViewStateListener {
    }

    public AodMediaViewStateController(Context context, ConfigurationController configurationController, AodMediaViewStateHolderCollapse aodMediaViewStateHolderCollapse, AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded) {
        this.viewStateCollapse = aodMediaViewStateHolderCollapse;
        this.viewStateExpanded = aodMediaViewStateHolderExpanded;
        this.context = AodUtils.getFixContext(context);
        configChangeListener.1 r4 = new configChangeListener.1();
        r4.this$0 = this;
        VarHandle.storeStoreFence();
        this.configChangeListener = r4;
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) this.context.getSystemService("power")).newWakeLock(1, "AodService:wakeLock");
        this.wakeLock = wakeLockNewWakeLock;
        if (wakeLockNewWakeLock != null) {
            wakeLockNewWakeLock.setReferenceCounted(false);
        }
        configurationController.addCallback(r4);
    }

    public final void addAodClockView(AodClockLayout aodClockLayout) {
        this.viewStateExpanded.addAodClockView(aodClockLayout);
        this.viewStateCollapse.addAodClockView(aodClockLayout);
    }

    public final void addListener(AodMediaViewStateListener aodMediaViewStateListener) {
        this._listeners.add(aodMediaViewStateListener);
        ((WorkshopAodController) aodMediaViewStateListener).onStateChanged(this.curState);
    }

    public final void addTransListener(AnimatorListenerAdapter animatorListenerAdapter) {
        if (animatorListenerAdapter != null) {
            ArrayList<Animator.AnimatorListener> listeners = this.animator.getListeners();
            if (listeners == null || !listeners.contains(animatorListenerAdapter)) {
                this.animator.addListener(animatorListenerAdapter);
            }
        }
    }

    public final void apply(AodMediaControlPanel aodMediaControlPanel, int i) {
        if ((aodMediaControlPanel != null ? aodMediaControlPanel.getPlayerView() : null) == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "apply: playerView == null, return!!!");
        }
        if (i == 1) {
            this.viewStateExpanded.applyViewState(aodMediaControlPanel);
        } else {
            this.viewStateCollapse.applyViewState(aodMediaControlPanel);
        }
    }

    public final void cancelCapsuleProgressAnim(String str) {
        if (this.progressAnimator.getAnimating()) {
            OplusNotificationLogger.logAodMediaPanelNormal(TAG, "cancelCapsuleProgressAnim, cause=" + str);
            this.progressAnimator.cancel();
        }
    }

    public final void cancelTransTo() {
        if (this.animating && this.animator.isRunning()) {
            this.animator.cancel();
        }
    }

    public final boolean getAnimating() {
        return this.animating;
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getCurMode() {
        return this.curMode;
    }

    public final int getCurState() {
        return this.curState;
    }

    public final boolean getRehearsing() {
        return this.rehearsing;
    }

    public final boolean getShown() {
        return this.shown;
    }

    public final void removeAodClickLayout() {
        this.viewStateExpanded.removeAodClickLayout();
        this.viewStateCollapse.removeAodClickLayout();
    }

    public final void removeListener(AodMediaViewStateListener aodMediaViewStateListener) {
        this._listeners.remove(aodMediaViewStateListener);
    }

    public final void removeTransListener(AnimatorListenerAdapter animatorListenerAdapter) {
        ArrayList<Animator.AnimatorListener> listeners;
        if (animatorListenerAdapter == null || (listeners = this.animator.getListeners()) == null || !listeners.contains(animatorListenerAdapter)) {
            return;
        }
        this.animator.removeListener(animatorListenerAdapter);
    }

    public final void requestWakeLockInMode() {
        boolean z = this.curMode == 2;
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (z) {
            if (wakeLock != null) {
                wakeLock.acquire(5000L);
            }
            KgdLogUtil.i(TAG, "acquire wakelock");
        } else {
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
            PowerManager.WakeLock wakeLock2 = this.wakeLock;
            if (wakeLock2 != null) {
                wakeLock2.release();
            }
            KgdLogUtil.i(TAG, "release wakelock");
        }
    }

    public final void setAnimating(boolean z) {
        this.animating = z;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setCurMode(int i) {
        this.curMode = i;
    }

    public final void setCurState(int i) {
        boolean z = this.curState != i;
        this.curState = i;
        if (z) {
            Iterator<T> it = this._listeners.iterator();
            while (it.hasNext()) {
                ((AodMediaViewStateListener) it.next()).onStateChanged(i);
            }
        }
    }

    public final void setLayoutDirection(Object obj) {
        if (obj instanceof Integer) {
            int iIntValue = ((Number) obj).intValue();
            this.aodLayoutDirection = iIntValue;
            OplusNotificationLogger.logAodMediaPanel(TAG, "setLayoutDirection = " + iIntValue);
        }
    }

    public final void setRehearsing(boolean z) {
        this.rehearsing = z;
    }

    public final void setShown(boolean z) {
        this.shown = z;
    }

    public final void shouldReloadResIfNeed() {
        AodMediaViewStateHolderCollapse aodMediaViewStateHolderCollapse = this.viewStateCollapse;
        if (aodMediaViewStateHolderCollapse.shouldReloadRes) {
            aodMediaViewStateHolderCollapse.shouldReloadRes = false;
            aodMediaViewStateHolderCollapse.initDimen(aodMediaViewStateHolderCollapse.context);
            aodMediaViewStateHolderCollapse.initProp(aodMediaViewStateHolderCollapse.context);
        }
        AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded = this.viewStateExpanded;
        if (aodMediaViewStateHolderExpanded.shouldReloadRes) {
            aodMediaViewStateHolderExpanded.shouldReloadRes = false;
            aodMediaViewStateHolderExpanded.initDimen(aodMediaViewStateHolderExpanded.context);
            aodMediaViewStateHolderExpanded.initProp(aodMediaViewStateHolderExpanded.context);
        }
    }

    public final void startCapsuleProgressAnim(AodMediaControlPanel aodMediaControlPanel, long j, Runnable runnable, String str) {
        if (this.progressAnimator.getAnimating()) {
            return;
        }
        OplusNotificationLogger.logAodMediaPanelNormal(TAG, "startCapsuleProgressAnim, cause=" + str);
        this.progressAnimator.start(aodMediaControlPanel, j, runnable);
    }

    public final void transitionTo(AodMediaControlPanel aodMediaControlPanel, int i, int i2) {
        AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded;
        AodMediaViewStateHolderExpanded aodMediaViewStateHolderExpanded2;
        if ((aodMediaControlPanel != null ? aodMediaControlPanel.getPlayerView() : null) == null) {
            OplusNotificationLogger.logAodMediaPanel(TAG, "transitionTo: playerView == null, return!!!");
            return;
        }
        if (i == 0) {
            aodMediaViewStateHolderExpanded = this.viewStateCollapse;
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m("not a legal view state");
                return;
            }
            aodMediaViewStateHolderExpanded = this.viewStateExpanded;
        }
        if (i2 == 0) {
            aodMediaViewStateHolderExpanded2 = this.viewStateCollapse;
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m("not a legal view state");
                return;
            }
            aodMediaViewStateHolderExpanded2 = this.viewStateExpanded;
        }
        this.animator.animateTo(aodMediaControlPanel, aodMediaViewStateHolderExpanded, aodMediaViewStateHolderExpanded2);
    }
}
