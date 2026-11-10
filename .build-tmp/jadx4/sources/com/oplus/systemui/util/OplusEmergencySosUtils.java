package com.oplus.systemui.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telecom.TelecomManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusEmergencySosUtils {
    private static final String PERSONAL_SAFETY_FEATURE_NAME = "com.google.android.feature.PERSONAL_SAFETY";

    public static final Intent createEmergencyDialerIntent(Context context, String str) {
        FeatureOption.INSTANCE.getClass();
        int i = 1;
        if (!FeatureOption.isExpRegion()) {
            if (Intrinsics.areEqual(str, "from_power_page")) {
                String string = context.getString(2131956495);
                Intent intent = new Intent("oplus.intent.action.LAUNCH_SOS_HELPER");
                intent.setPackage(string);
                intent.putExtra("start_type", str);
                return intent;
            }
            Intent intentCreateLaunchEmergencyDialerIntent = ((TelecomManager) context.getSystemService("telecom")).createLaunchEmergencyDialerIntent(null);
            intentCreateLaunchEmergencyDialerIntent.setFlags(343932928);
            if (Intrinsics.areEqual(str, "from_power_page")) {
                i = 2;
            } else if (!Intrinsics.areEqual(str, "from_key_guard")) {
                i = 0;
            }
            intentCreateLaunchEmergencyDialerIntent.putExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", i);
            return intentCreateLaunchEmergencyDialerIntent;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PERSONAL_SAFETY_FEATURE_NAME)) {
            try {
                if (packageManager.getPackageInfo(context.getString(2131952710), 0) != null) {
                    Intent intentCreateLaunchEmergencyDialerIntent2 = ((TelecomManager) context.getSystemService("telecom")).createLaunchEmergencyDialerIntent(null);
                    intentCreateLaunchEmergencyDialerIntent2.setFlags(343932928);
                    if (Intrinsics.areEqual(str, "from_power_page")) {
                        i = 2;
                    } else if (!Intrinsics.areEqual(str, "from_key_guard")) {
                        i = 0;
                    }
                    intentCreateLaunchEmergencyDialerIntent2.putExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", i);
                    return intentCreateLaunchEmergencyDialerIntent2;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        String string2 = context.getString(2131956495);
        Intent intent2 = new Intent("oplus.intent.action.LAUNCH_SOS_HELPER");
        intent2.setPackage(string2);
        intent2.putExtra("start_type", str);
        return intent2;
    }
}
