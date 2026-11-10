package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import android.os.Handler;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualViewController_Factory implements Factory {
    public InstanceFactory contextProvider;
    public Provider guideStateProvider;
    public InstanceFactory handlerProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public OplusQsGuideSpiritualViewController m227get() {
        return new OplusQsGuideSpiritualViewController((Context) this.contextProvider.get(), (OplusQsGuideStateProvider) this.guideStateProvider.get(), (Handler) this.handlerProvider.get());
    }
}
