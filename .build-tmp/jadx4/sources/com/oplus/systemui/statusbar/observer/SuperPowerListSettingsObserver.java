package com.oplus.systemui.statusbar.observer;

import android.net.Uri;
import android.os.Handler;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper$$ExternalSyntheticLambda0;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerListSettingsObserver extends AbstractObserver {
    public static volatile SuperPowerListSettingsObserver sInstance;

    public static SuperPowerListSettingsObserver getInstance() {
        if (sInstance == null) {
            synchronized (SuperPowerListSettingsObserver.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SuperPowerListSettingsObserver();
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
        return CommonSettingsValueProxy.SUPER_POWER_SAVE_DESKTOP_APP_LIST;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        if (getContext() == null) {
            return;
        }
        SuperPowerNotificationHelper.getInstance(getContext()).updateSuperPowerList();
        SuperPowerNotificationHelper superPowerNotificationHelper = SuperPowerNotificationHelper.getInstance(getContext());
        superPowerNotificationHelper.getClass();
        if (ThreadManager.getInstance().getMainHandler() != null) {
            Handler mainHandler = ThreadManager.getInstance().getMainHandler();
            SuperPowerNotificationHelper$$ExternalSyntheticLambda0 superPowerNotificationHelper$$ExternalSyntheticLambda0 = new SuperPowerNotificationHelper$$ExternalSyntheticLambda0();
            superPowerNotificationHelper$$ExternalSyntheticLambda0.f$0 = superPowerNotificationHelper;
            VarHandle.storeStoreFence();
            mainHandler.post(superPowerNotificationHelper$$ExternalSyntheticLambda0);
        }
        super.onChange(z);
    }
}
