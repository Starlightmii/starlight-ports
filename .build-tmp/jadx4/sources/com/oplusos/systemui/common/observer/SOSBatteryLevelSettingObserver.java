package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SOSBatteryLevelSettingObserver extends AbstractObserver {
    public static volatile SOSBatteryLevelSettingObserver sInstance;
    public int mSOSBatteryLowLevel;

    public static SOSBatteryLevelSettingObserver getInstance() {
        if (sInstance == null) {
            synchronized (SOSBatteryLevelSettingObserver.class) {
                try {
                    if (sInstance == null) {
                        SOSBatteryLevelSettingObserver sOSBatteryLevelSettingObserver = new SOSBatteryLevelSettingObserver();
                        sOSBatteryLevelSettingObserver.mSOSBatteryLowLevel = 15;
                        sInstance = sOSBatteryLevelSettingObserver;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "sos_low_battery_notification_level";
    }

    public int getSosLowBatteryLevel() {
        return this.mSOSBatteryLowLevel;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            this.mSOSBatteryLowLevel = MultiUserContentHelper.getGlobalIntValue(context, "sos_low_battery_notification_level", 15);
            ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mSOSBatteryLowLevel, LogUtil.TAG_NOTIFICATION, "SOSBatteryLevelSettingObserver", new StringBuilder("onChange mSOSBatteryLowLevel = "));
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void register(Context context, boolean z) {
        super.register(context, z);
        if (context != null) {
            this.mSOSBatteryLowLevel = MultiUserContentHelper.getGlobalIntValue(context, "sos_low_battery_notification_level", 15);
        }
    }
}
