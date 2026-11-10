package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OpBreathModeObserver extends AbstractObserver {
    public static boolean mIsInBrickMode;
    public static final OpBreathModeObserver INSTANCE = new OpBreathModeObserver();
    private static final String mKey = "op_breath_mode_status";

    public final boolean getBreathModeState() {
        return mIsInBrickMode;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return mKey;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        mIsInBrickMode = CommonSettingsValueProxy.Companion.getBreathModeState(context, "op_breath_mode_status") == 1;
        super.onChange(z);
    }
}
