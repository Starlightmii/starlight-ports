package com.oplus.systemui.statusbar.notification.power;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FluidCloudStateObserver extends AbstractObserver {
    private static final String FLUID_CLOUD_NOT_SUPPORT = "fluid_cloud_not_support";
    private static final String TAG = "FluidCloudStateObserver";
    public int mCurrentFluidCloudState;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new FluidCloudStateObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final FluidCloudStateObserver getInstance() {
            return (FluidCloudStateObserver) FluidCloudStateObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return FLUID_CLOUD_NOT_SUPPORT;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            this.mCurrentFluidCloudState = AbsSettingsValueProxy.Companion.getGlobalIntValue(context, getKey(), 0);
            if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mCurrentFluidCloudState, "onChange mCurrentFluidCloudState = ", "FluidCloudStateObserver-->", "SystemUi--Common");
            }
        }
    }
}
