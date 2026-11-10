package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import android.provider.Settings;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IntStateProxy {
    private final Context context;
    public final boolean defaultValue;
    private final String key;

    public IntStateProxy(Context context, String str, boolean z) {
        this.context = context;
        this.key = str;
        this.defaultValue = z;
    }

    public final boolean getDefaultValue() {
        return this.defaultValue;
    }

    public final String getKey() {
        return this.key;
    }

    public final boolean getValue(Object obj, KProperty kProperty) {
        return Settings.Secure.getIntForUser(this.context.getContentResolver(), this.key, this.defaultValue ? 1 : 0, -2) == 1;
    }

    public final void setValue(Object obj, KProperty kProperty, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IntStateProxy-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m(this.context.getUserId(), "(", ")set ", kProperty.getName(), ": "), z);
        }
        Settings.Secure.putIntForUser(this.context.getContentResolver(), this.key, z ? 1 : 0, -2);
    }
}
