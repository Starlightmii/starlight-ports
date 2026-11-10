package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WirelessReverseEnabledObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String TAG = "WirelessReverseEnabledObserver";
    private static volatile WirelessReverseEnabledObserver sInstance;
    private final MutableStateFlow _reverseChargeEnabled;
    public volatile boolean isWirelessReverseEnabled = true;
    private final StateFlow reverseChargeEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final WirelessReverseEnabledObserver getInstance() {
            WirelessReverseEnabledObserver wirelessReverseEnabledObserver;
            WirelessReverseEnabledObserver wirelessReverseEnabledObserver2 = WirelessReverseEnabledObserver.sInstance;
            if (wirelessReverseEnabledObserver2 != null) {
                return wirelessReverseEnabledObserver2;
            }
            synchronized (this) {
                wirelessReverseEnabledObserver = WirelessReverseEnabledObserver.sInstance;
                if (wirelessReverseEnabledObserver == null) {
                    wirelessReverseEnabledObserver = new WirelessReverseEnabledObserver();
                    WirelessReverseEnabledObserver.sInstance = wirelessReverseEnabledObserver;
                }
            }
            return wirelessReverseEnabledObserver;
        }
    }

    public WirelessReverseEnabledObserver() {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._reverseChargeEnabled = MutableStateFlow;
        this.reverseChargeEnabled = FlowKt.asStateFlow(MutableStateFlow);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_WIRELESS_REVERSE;
    }

    public final StateFlow getReverseChargeEnabled() {
        return this.reverseChargeEnabled;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            this.isWirelessReverseEnabled = CommonSettingsValueProxy.Companion.getWirelessReverseEnableState(context) == 0;
            this._reverseChargeEnabled.setValue(Boolean.valueOf(this.isWirelessReverseEnabled));
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.isWirelessReverseEnabled = CommonSettingsValueProxy.Companion.getWirelessReverseEnableState(context) == 0;
        this._reverseChargeEnabled.setValue(Boolean.valueOf(this.isWirelessReverseEnabled));
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onRegister: ", this.isWirelessReverseEnabled), null, 8);
    }
}
