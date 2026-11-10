package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import com.oplus.systemui.scenario.host.impl.ui.composable.LocalViewsMapKt;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda31 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ConcurrentHashMap f$0;
    public /* synthetic */ String f$1;
    public /* synthetic */ int f$2;

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ConcurrentHashMap concurrentHashMap = this.f$0;
                String str = this.f$1;
                int i = this.f$2;
                ScenarioCapsuleViewBinder$.ExternalSyntheticLambda54 externalSyntheticLambda54 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda54();
                externalSyntheticLambda54.f$0 = i;
                VarHandle.storeStoreFence();
                return (TextSwitcher) LocalViewsMapKt.getOrCreateView(concurrentHashMap, (Context) obj, str, externalSyntheticLambda54);
            default:
                ConcurrentHashMap concurrentHashMap2 = this.f$0;
                String str2 = this.f$1;
                int i2 = this.f$2;
                Context context = (Context) obj;
                ScenarioCapsuleViewBinder$.ExternalSyntheticLambda56 externalSyntheticLambda56 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda56();
                externalSyntheticLambda56.f$0 = context;
                externalSyntheticLambda56.f$1 = i2;
                VarHandle.storeStoreFence();
                return (ImageView) LocalViewsMapKt.getOrCreateView(concurrentHashMap2, context, str2, externalSyntheticLambda56);
        }
    }
}
