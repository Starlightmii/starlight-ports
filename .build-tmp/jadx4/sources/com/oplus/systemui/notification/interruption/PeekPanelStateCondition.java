package com.oplus.systemui.notification.interruption;

import android.net.Uri;
import com.android.systemui.model.SysUiState;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.observer.GlobalBannerSwitchObserver;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekPanelStateCondition extends VisualInterruptionCondition {
    private final Lazy notificationDependOnKeyguard;
    private final NotificationPanelViewController notificationPanelViewController;
    private String reason;
    private final StatusBarStateController statusBarStateController;
    private final SysUiState sysUiState;

    public PeekPanelStateCondition(SysUiState sysUiState, StatusBarStateController statusBarStateController, NotificationPanelViewController notificationPanelViewController, Lazy lazy) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "peek panel state");
        this.sysUiState = sysUiState;
        this.statusBarStateController = statusBarStateController;
        this.notificationPanelViewController = notificationPanelViewController;
        this.notificationDependOnKeyguard = lazy;
        this.reason = "peek panel state";
    }

    public String getReason() {
        return this.reason;
    }

    public final boolean shouldSuppress() {
        if (this.statusBarStateController.isDozing()) {
            this.reason = "suppress by isDozing";
            return true;
        }
        if (this.notificationPanelViewController.mExpandingFromHeadsUp) {
            this.reason = "suppress by isExpandingFromHeadsUp";
            return true;
        }
        if ((this.sysUiState.getFlags() & 2048) != 0) {
            this.reason = "suppress by qsExpand";
            return true;
        }
        if ((this.sysUiState.getFlags() & 4) != 0) {
            this.reason = "suppress by panel expand";
            return true;
        }
        if (!GlobalBannerSwitchObserver.INSTANCE.getGlobalBannerSwitchEnabled()) {
            this.reason = "suppress by globalBannerSwitch is disabled";
            return true;
        }
        ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get())).getClass();
        LockDeadUtil.INSTANCE.getClass();
        if (LockDeadUtil.isLockDeadState) {
            this.reason = "suppress by os policy : lockDeadState";
            return true;
        }
        Uri uri = Util.ROM_UPDATE_CONTENT_URI;
        if (ChildrenModeObserver.getInstance().mIsChildrenMode) {
            this.reason = "suppress by os policy : isChildrenMode";
            return true;
        }
        if (!((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get())).isKeyguardShowing()) {
            return false;
        }
        this.reason = "suppress by os policy : isKeyguardShowing";
        return true;
    }
}
