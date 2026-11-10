package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpeedChassistMainObserver extends AbstractObserver {
    public static boolean mIsChassistEnable;
    public static final SpeedChassistMainObserver INSTANCE = new SpeedChassistMainObserver();
    private static final String mKey = CommonSettingsValueProxy.KEY_SPEEDCHASSIST_MAIN_TYPE;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return mKey;
    }

    public final boolean getSpeedChassistEnableStatus() {
        return mIsChassistEnable;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        mIsChassistEnable = CommonSettingsValueProxy.Companion.getSpeedChassistMainState(context, mKey) == 2;
        super.onChange(z);
    }
}
