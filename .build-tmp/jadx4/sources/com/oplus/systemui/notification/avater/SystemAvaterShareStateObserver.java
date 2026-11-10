package com.oplus.systemui.notification.avater;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemAvaterShareStateObserver extends AbstractObserver {
    public static volatile SystemAvaterShareStateObserver sInstance;
    public String TAG;
    public int mShareState;

    public static SystemAvaterShareStateObserver getInstance() {
        if (sInstance == null) {
            synchronized (SystemAvaterShareStateObserver.class) {
                try {
                    if (sInstance == null) {
                        SystemAvaterShareStateObserver systemAvaterShareStateObserver = new SystemAvaterShareStateObserver();
                        systemAvaterShareStateObserver.TAG = "SystemAvaterShareStateObserver";
                        sInstance = systemAvaterShareStateObserver;
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
        return "system_clone_share_notification";
    }

    public int getShareState() {
        return this.mShareState;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        LogUtil.d(LogUtil.TAG_NOTIFICATION, this.TAG, " onChange(boolean selfChange) start");
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mShareState = AbsSettingsValueProxy.getGlobalIntValue(context, "system_clone_share_notification", 0);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onRegister(Context context) {
        super.onRegister(context);
        this.mShareState = AbsSettingsValueProxy.getGlobalIntValue(context, "system_clone_share_notification", 0);
    }
}
