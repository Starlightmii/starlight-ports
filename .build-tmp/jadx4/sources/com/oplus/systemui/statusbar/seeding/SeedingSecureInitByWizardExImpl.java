package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedingSecureInitByWizardExImpl {
    public static final Companion Companion = new Companion();
    private static final String ENTRY_TYPE_LOCK_SCREEN_KEY = "fluid_seeding_entry_lockscreen";
    private static final String ENTRY_TYPE_NOTIFICATION_STATUS_BAR_KEY = "fluid_seeding_entry_bar";
    private static final String FLUID_SEEDING_ENABLE_WIZARD = "fluid_seeding_enable_wizard";
    private static final String FLUID_SEEDING_SETTINGS_MASTER = "fluid_seeding_enable";
    private static final String SEEDLING_GRANTED_STATE = "fluid_seeding_agreement";
    public static final String TAG = "SeedingSecureInitByWizardEx";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SeedingSecureInitByWizardExImpl(Context context) {
        this.context = context;
    }

    public final void checkIsInitSeedingSecureByWizard() {
        Context context;
        NotificationFeatureOption.INSTANCE.getClass();
        if (!NotificationFeatureOption.isSeedlingExpRegion || (context = this.context) == null) {
            return;
        }
        boolean z = Settings.Secure.getIntForUser(context.getContentResolver(), FLUID_SEEDING_ENABLE_WIZARD, 0, -2) == 1;
        int intForUser = Settings.Secure.getIntForUser(context.getContentResolver(), FLUID_SEEDING_SETTINGS_MASTER, 2, -2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("Check if need to sync smart suggestions switch.fluidEnableByWizard: ", intForUser, " fluidValue: ", "SeedingSecureInitByWizardEx-->", z));
        }
        if (intForUser == 2 && z) {
            Settings.Secure.putIntForUser(context.getContentResolver(), FLUID_SEEDING_SETTINGS_MASTER, 1, -2);
            Settings.Secure.putIntForUser(context.getContentResolver(), SEEDLING_GRANTED_STATE, 1, -2);
            Settings.Secure.putIntForUser(context.getContentResolver(), ENTRY_TYPE_NOTIFICATION_STATUS_BAR_KEY, 1, -2);
            Settings.Secure.putIntForUser(context.getContentResolver(), ENTRY_TYPE_LOCK_SCREEN_KEY, 1, -2);
        }
    }
}
