package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import android.provider.Settings;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.security.SecurityLockModeManager;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.observer.PanelPullEnableObserver;
import com.oplus.systemui.statusbar.observer.StatusBarExpandableObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.FocusModeSettingsObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PanelPullHelper {
    public final Context mContext;
    public Lazy mNotificationDependOnKeyguard;
    public final PanelPullEnableObserver mPanelEnableStateObserver;
    public SecurityLockModeManager mSecurityLockModeManager;
    public Lazy mStatusBar;

    public PanelPullHelper(Context context) {
        PanelPullEnableObserver panelPullEnableObserver = new PanelPullEnableObserver();
        this.mPanelEnableStateObserver = panelPullEnableObserver;
        this.mContext = context;
        panelPullEnableObserver.registerForUserSwitch(context, false);
        panelPullEnableObserver.setDisablePullStatusBar(AbsSettingsValueProxy.getSystemIntValue(context, "oplus_customize_status_bar_enable_when_lock", 1) == 0);
    }

    private boolean getDisablePullStatusBarOnShade() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFeaturesDisplaying()) {
            boolean z = Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, -2) != 0;
            if (!z) {
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("Notification-isUserSetup:", LogUtil.TAG_NOTIFICATION, "Notification--PanelEnabledHelper", z);
                return true;
            }
        }
        boolean z2 = this.mPanelEnableStateObserver.mDisablePullStatusBar;
        boolean zIsKeyguardSecure = ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).isKeyguardSecure();
        boolean zHasSecurityKeyguard = ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).hasSecurityKeyguard();
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NOTIFICATION, "Notification--PanelEnabledHelper", TextFieldImplKt$.ExternalSyntheticOutline0.m("Notification--getDisablePullStatusBar:", ",mStatusBar.isKeyguardSecure():", ",hasKeyguardView:", z2, zIsKeyguardSecure), zHasSecurityKeyguard);
        return z2 && zIsKeyguardSecure && zHasSecurityKeyguard;
    }

    public final boolean getDisablePullStatusBar(Boolean bool) {
        if (!((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.mNotificationDependOnKeyguard.get())).isKeyguardShowing()) {
            return false;
        }
        if ((bool.booleanValue() || !getDisablePullStatusBarOnShade()) && !StatusBarExpandableObserver.getInstance().mIsStatusBarExpandDisableMode && !FocusModeSettingsObserver.getsInstance().mIsFocusMode && !Util.isChildrenOrStudyCenterMode()) {
            LockDeadUtil.INSTANCE.getClass();
            if (!LockDeadUtil.isLockDeadState && this.mSecurityLockModeManager.securityLockMode != 1 && !OplusSceneModeObserver.Companion.getInstance(this.mContext).isInBrickMode) {
                return false;
            }
        }
        return true;
    }
}
