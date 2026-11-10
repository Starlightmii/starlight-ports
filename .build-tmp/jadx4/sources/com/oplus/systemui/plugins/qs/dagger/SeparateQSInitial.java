package com.oplus.systemui.plugins.qs.dagger;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSInitial {
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SeparateQSInitial$$ExternalSyntheticLambda0());
    private SeparateQSComponent daggerSeparateQSComponent;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SeparateQSInitial getInstance() {
            return (SeparateQSInitial) SeparateQSInitial.instance$delegate.getValue();
        }
    }

    public final SeparateQSComponent getSeparateQSComponent(ContextInstance contextInstance, OplusSeparateQSPlugin.IStub iStub) {
        if (this.daggerSeparateQSComponent == null) {
            DaggerSeparateQSComponent$Builder daggerSeparateQSComponent$Builder = new DaggerSeparateQSComponent$Builder();
            contextInstance.getClass();
            daggerSeparateQSComponent$Builder.contextInstance = contextInstance;
            iStub.getClass();
            daggerSeparateQSComponent$Builder.pluginStub = iStub;
            this.daggerSeparateQSComponent = daggerSeparateQSComponent$Builder.build();
        }
        return this.daggerSeparateQSComponent;
    }
}
