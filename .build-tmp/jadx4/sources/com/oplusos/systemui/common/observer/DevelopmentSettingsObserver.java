package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DevelopmentSettingsObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static volatile DevelopmentSettingsObserver sInstance;
    public volatile boolean isDevelopmentSettingsEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final DevelopmentSettingsObserver getInstance() {
            if (DevelopmentSettingsObserver.sInstance == null) {
                synchronized (DevelopmentSettingsObserver.class) {
                    if (DevelopmentSettingsObserver.sInstance == null) {
                        DevelopmentSettingsObserver.sInstance = new DevelopmentSettingsObserver();
                    }
                }
            }
            DevelopmentSettingsObserver developmentSettingsObserver = DevelopmentSettingsObserver.sInstance;
            developmentSettingsObserver.getClass();
            return developmentSettingsObserver;
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_DEVELOPMENT_SETTINGS;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            this.isDevelopmentSettingsEnabled = CommonSettingsValueProxy.Companion.getDevelopmentSettingsState(context) == 1;
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.isDevelopmentSettingsEnabled = CommonSettingsValueProxy.Companion.getDevelopmentSettingsState(context) == 1;
    }
}
