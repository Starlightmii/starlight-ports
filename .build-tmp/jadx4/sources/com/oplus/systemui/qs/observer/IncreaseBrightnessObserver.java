package com.oplus.systemui.qs.observer;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IncreaseBrightnessObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String INCREASE_BRIGHTNESS = "increase_brightness_range";
    private static final String TAG = "IncreaseBrightnessObserver";
    private static volatile IncreaseBrightnessObserver sInstance;
    public boolean isIncreaseBrightnessEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final IncreaseBrightnessObserver getInstance() {
            if (IncreaseBrightnessObserver.sInstance == null) {
                synchronized (IncreaseBrightnessObserver.class) {
                    if (IncreaseBrightnessObserver.sInstance == null) {
                        IncreaseBrightnessObserver.sInstance = new IncreaseBrightnessObserver();
                    }
                }
            }
            return IncreaseBrightnessObserver.sInstance;
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return INCREASE_BRIGHTNESS;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "IncreaseBrightnessObserver-->onChange");
        }
        this.isIncreaseBrightnessEnabled = AbsSettingsValueProxy.Companion.getSystemIntValue(context, INCREASE_BRIGHTNESS, 0) == 1;
        super.onChange(z);
    }
}
