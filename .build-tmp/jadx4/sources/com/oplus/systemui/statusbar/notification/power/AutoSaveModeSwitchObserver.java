package com.oplus.systemui.statusbar.notification.power;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.OplusLockIconView$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AutoSaveModeSwitchObserver extends AbstractObserver {
    public static final String AUTO_SAVE_POWER_SWITCH_STRING = "power_save_open_level_switch";
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AutoSaveModeSwitchObserver$$ExternalSyntheticLambda0());
    public boolean isAutoSaveModeOn;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final AutoSaveModeSwitchObserver getInstance() {
            return (AutoSaveModeSwitchObserver) AutoSaveModeSwitchObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return AUTO_SAVE_POWER_SWITCH_STRING;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            this.isAutoSaveModeOn = AbsSettingsValueProxy.Companion.getSystemIntValue(context, getKey(), 0, 0) == 1;
            if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onChange isAutoSaveModeOn = ", "Oplus_PowerUI-->", "SystemUi--Common", this.isAutoSaveModeOn);
            }
        }
    }

    public final void setAutoSaveModeOn(boolean z) {
        this.isAutoSaveModeOn = z;
    }
}
