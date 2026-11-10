package com.oplus.systemui.qs.observer;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VisualComfortSwitchObserver extends AbstractObserver {
    private static final String TAG = "VisualComfortSwitchObserver";
    public boolean visualComfortSwitchOpen;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new VisualComfortSwitchObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final VisualComfortSwitchObserver getInstance() {
            return (VisualComfortSwitchObserver) VisualComfortSwitchObserver.instance$delegate.getValue();
        }
    }

    public static final VisualComfortSwitchObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_VISUAL_COMFORT_SWITCH;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    public final boolean getVisualComfortSwitchOpen() {
        return this.visualComfortSwitchOpen;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.visualComfortSwitchOpen = CommonSettingsValueProxy.Companion.isVisualComfortSwitchOpen(context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onChange: selfChange = ", ", visualComfortSwitchOpen = ", "VisualComfortSwitchObserver-->", z, this.visualComfortSwitchOpen));
        }
        super.onChange(z);
    }
}
