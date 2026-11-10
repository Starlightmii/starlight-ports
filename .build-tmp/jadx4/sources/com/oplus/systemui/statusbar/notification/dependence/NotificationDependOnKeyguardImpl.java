package com.oplus.systemui.statusbar.notification.dependence;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.utils.KeyguardUtils;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDependOnKeyguardImpl implements NotificationDependOnKeyguard {
    private final Lazy clockAlpha$delegate;
    private final dagger.Lazy keyguardStateController;
    private final dagger.Lazy notificationDependOnCommon;
    private final dagger.Lazy oplusKeyguardClockViewModel;
    private final Lazy oplusKeyguardDependencyEx$delegate = LazyKt__LazyJVMKt.lazy(new NotificationDependOnKeyguardImpl$$ExternalSyntheticLambda0());
    private final dagger.Lazy statusBarKeyguardViewManager;

    public NotificationDependOnKeyguardImpl(dagger.Lazy lazy, dagger.Lazy lazy2, dagger.Lazy lazy3, dagger.Lazy lazy4) {
        this.oplusKeyguardClockViewModel = lazy;
        this.statusBarKeyguardViewManager = lazy2;
        this.keyguardStateController = lazy3;
        this.notificationDependOnCommon = lazy4;
        NotificationDependOnKeyguardImpl$$ExternalSyntheticLambda1 notificationDependOnKeyguardImpl$$ExternalSyntheticLambda1 = new NotificationDependOnKeyguardImpl$$ExternalSyntheticLambda1();
        notificationDependOnKeyguardImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.clockAlpha$delegate = LazyKt__LazyJVMKt.lazy(notificationDependOnKeyguardImpl$$ExternalSyntheticLambda1);
    }

    private final OplusKeyguardDependencyEx getOplusKeyguardDependencyEx() {
        return (OplusKeyguardDependencyEx) this.oplusKeyguardDependencyEx$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard
    public Flow getClockAlpha() {
        return (Flow) this.clockAlpha$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard
    public int getWallpaperType() {
        return -1;
    }

    public final boolean hasSecurityKeyguard() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        return keyguardStateControllerImpl.mSecure && !keyguardStateControllerImpl.mCanDismissLockScreen;
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard
    public boolean isDefaultKeyguardInSettings(Context context) {
        String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "oplus_customize_unlock_change_pkg", ((NotificationDependOnCommon) this.notificationDependOnCommon.get()).getCurrentUser());
        if (TextUtils.isEmpty(stringForUser) || "com.android.systemui".equals(stringForUser)) {
            return true;
        }
        KeyguardUtils.Companion.isCustomLockStyle(context);
        return false;
    }

    public final boolean isKeyguardSecure() {
        return ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManager.get()).isSecure();
    }

    public final boolean isKeyguardShowing() {
        return ((KeyguardStateController) this.keyguardStateController.get()).mShowing;
    }
}
