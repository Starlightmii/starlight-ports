package com.oplus.systemui.notification.flashlight;

import android.content.Context;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.capsule.FlavorOneCapsuleController;
import com.oplus.systemui.capsule.StateMonitor;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplus.systemui.statusbar.notification.flashlight.seeding.FlashlightSeedling;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightSeedlingChecker {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FlashlightSeedlingChecker";
    private final Context context;
    private final FlashlightSeedling flashlightSeedling;
    private final SeedlingPluginManager seedlingPluginManager;
    public boolean userUnlocked;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FlashlightSeedlingChecker(Context context, FlashlightSeedling flashlightSeedling, SeedlingPluginManager seedlingPluginManager) {
        this.context = context;
        this.flashlightSeedling = flashlightSeedling;
        this.seedlingPluginManager = seedlingPluginManager;
    }

    public final boolean triggerFlashlightSeedling(boolean z, String str) {
        boolean z2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("triggerFlashlightSeedling: flashlightOn = ", "FlashlightSeedlingChecker-->", "SystemUi--Notification", z);
        }
        if (this.seedlingPluginManager.isPluginAvailable || FlashlightTip.INSTANCE.getSupportLiteLiveAlert()) {
            if (this.userUnlocked || !FlashlightTip.INSTANCE.getSupportLiteLiveAlert()) {
                z2 = false;
            } else {
                MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
                Context currentUserContext = companion.getCurrentUserContext(this.context);
                boolean zIsCurrentUserUnlocked = currentUserContext != null ? companion.isCurrentUserUnlocked(currentUserContext) : true;
                this.userUnlocked = zIsCurrentUserUnlocked;
                z2 = !zIsCurrentUserUnlocked;
            }
            if (!z2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "FlashlightSeedlingChecker-->triggerFlashlightSeedling: updateFlashlightSeeding");
                }
                int i = z ? 1 : 2;
                FlavorOneFeatureOption.INSTANCE.getClass();
                boolean zIsThreeFlashSupport = FlavorOneFeatureOption.isThreeFlashSupport();
                FlashlightSeedling flashlightSeedling = this.flashlightSeedling;
                Context context = this.context;
                if (zIsThreeFlashSupport) {
                    flashlightSeedling.updateFlashlightTitleSeeding(context, str, i);
                    return true;
                }
                flashlightSeedling.updateFlashlightSeeding(context, i, 0);
                if (!FlavorOneFeatureOption.isSupportMiniCapsule() || ((FlavorOneCapsuleController) DependencyEx.sDependency.getDependency(FlavorOneCapsuleController.class)) == null || !StateMonitor.Companion.getInstance().mMiniCapsuleSwitchEnable) {
                    return true;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "FlashlightSeedlingChecker-->triggerFlashlightSeedling: liteLiveAlertFilter");
                return false;
            }
        }
        return false;
    }
}
