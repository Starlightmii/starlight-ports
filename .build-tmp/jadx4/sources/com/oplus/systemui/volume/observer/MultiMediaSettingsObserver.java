package com.oplus.systemui.volume.observer;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.wrapper.os.UserHandle;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiMediaSettingsObserver extends AbstractObserver implements IVolumeSettingsObserver {
    public static final String TAG = "MultiMediaSettingsObserver";
    public volatile boolean isMultiMediaSettingsEnabled;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new MultiMediaSettingsObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final MultiMediaSettingsObserver getInstance() {
            return (MultiMediaSettingsObserver) MultiMediaSettingsObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return VolumeSettingsValueProxy.SETTINGS_MULTI_MEDIA_SWITCH;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            this.isMultiMediaSettingsEnabled = VolumeSettingsValueProxy.Companion.getMultiMediaSettingsState(context) == 1;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("onChange isMultiMediaSettingsEnabled = ", "MultiMediaSettingsObserver-->", "SystemUi--Volume", this.isMultiMediaSettingsEnabled);
            }
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        VolumeSettingsValueProxy.Companion companion = VolumeSettingsValueProxy.Companion;
        int multiMediaSettingsState = companion.getMultiMediaSettingsState(context);
        int i = 0;
        this.isMultiMediaSettingsEnabled = multiMediaSettingsState == 1;
        if (multiMediaSettingsState == -1) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                this.isMultiMediaSettingsEnabled = true;
                i = 1;
            }
            companion.setMultiMediaSettingsState(context, i);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onRegister isMultiMediaSettingsEnabled = ", "MultiMediaSettingsObserver-->", "SystemUi--Volume", this.isMultiMediaSettingsEnabled);
        }
    }

    @Override // com.oplus.systemui.volume.observer.IVolumeSettingsObserver
    public void registerObserver(Context context, IObserverListener iObserverListener) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.INSTANCE.getSFlavorOneMultiMediaDevice()) {
            register(context, UserHandle.USER_ALL, false);
            addListener(iObserverListener);
        }
    }
}
