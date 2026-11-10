package com.oplus.systemui.qs.base.widget.recyclerview.callback;

import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PagerCallbackWrapper extends PagerCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PagerCallbackWrapper";
    private final CopyOnWriteArraySet<PagerCallback> pagerCallbacks = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void addPagerCallback(PagerCallback pagerCallback) {
        this.pagerCallbacks.add(pagerCallback);
    }

    public final boolean isEmpty() {
        return this.pagerCallbacks.isEmpty();
    }

    public final void notifyCallbacks(Function1 function1) {
        for (PagerCallback pagerCallback : CollectionsKt___CollectionsKt.toList(this.pagerCallbacks)) {
            pagerCallback.getClass();
            function1.invoke(pagerCallback);
        }
    }

    public final void onPageScrollStateChanged(int i) {
        PagerCallbackWrapper$$ExternalSyntheticLambda0 pagerCallbackWrapper$$ExternalSyntheticLambda0 = new PagerCallbackWrapper$$ExternalSyntheticLambda0(2);
        pagerCallbackWrapper$$ExternalSyntheticLambda0.f$0 = i;
        VarHandle.storeStoreFence();
        notifyCallbacks(pagerCallbackWrapper$$ExternalSyntheticLambda0);
    }

    public final void onPageScrolled(int i, float f, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(i2, "PagerCallbackWrapper-->", "SystemUi--QuickSettings", NormalizedTouchData$.ExternalSyntheticOutline0.m(f, i, "[onPageScrolled] position=", ", positionOffset=", ", positionOffsetPixels="));
        }
        PagerCallbackWrapper$$ExternalSyntheticLambda2 pagerCallbackWrapper$$ExternalSyntheticLambda2 = new PagerCallbackWrapper$$ExternalSyntheticLambda2();
        pagerCallbackWrapper$$ExternalSyntheticLambda2.f$0 = i;
        pagerCallbackWrapper$$ExternalSyntheticLambda2.f$1 = f;
        pagerCallbackWrapper$$ExternalSyntheticLambda2.f$2 = i2;
        VarHandle.storeStoreFence();
        notifyCallbacks(pagerCallbackWrapper$$ExternalSyntheticLambda2);
    }

    public final void onPageSelected(int i) {
        PagerCallbackWrapper$$ExternalSyntheticLambda0 pagerCallbackWrapper$$ExternalSyntheticLambda0 = new PagerCallbackWrapper$$ExternalSyntheticLambda0(1);
        pagerCallbackWrapper$$ExternalSyntheticLambda0.f$0 = i;
        VarHandle.storeStoreFence();
        notifyCallbacks(pagerCallbackWrapper$$ExternalSyntheticLambda0);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.PagerCallback
    public final void onPageSizeChanged(int i) {
        PagerCallbackWrapper$$ExternalSyntheticLambda0 pagerCallbackWrapper$$ExternalSyntheticLambda0 = new PagerCallbackWrapper$$ExternalSyntheticLambda0(0);
        pagerCallbackWrapper$$ExternalSyntheticLambda0.f$0 = i;
        VarHandle.storeStoreFence();
        notifyCallbacks(pagerCallbackWrapper$$ExternalSyntheticLambda0);
    }

    public final void removePagerCallback(PagerCallback pagerCallback) {
        this.pagerCallbacks.remove(pagerCallback);
    }
}
