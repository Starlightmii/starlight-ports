package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FocusModeSettingsObserver extends AbstractObserver {
    public static volatile FocusModeSettingsObserver sInstance;
    public volatile boolean mIsFocusMode;

    public static FocusModeSettingsObserver getsInstance() {
        if (sInstance == null) {
            synchronized (FocusModeSettingsObserver.class) {
                try {
                    if (sInstance == null) {
                        FocusModeSettingsObserver focusModeSettingsObserver = new FocusModeSettingsObserver();
                        focusModeSettingsObserver.mIsFocusMode = false;
                        sInstance = focusModeSettingsObserver;
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
        return CommonSettingsValueProxy.KEY_FOCUS_MODE_SWITCH;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mIsFocusMode = CommonSettingsValueProxy.getFocusModeState(context, CommonSettingsValueProxy.KEY_FOCUS_MODE_SWITCH) == 1;
        super.onChange(z);
    }
}
