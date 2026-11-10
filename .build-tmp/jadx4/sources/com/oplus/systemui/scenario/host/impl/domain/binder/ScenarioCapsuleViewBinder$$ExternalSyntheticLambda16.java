package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.content.Context;
import android.widget.ImageView;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import com.oplus.systemui.scenario.host.impl.ui.composable.LocalViewsMapKt;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return LocalViewsMapKt.getOrCreateView((ConcurrentHashMap) obj2, (Context) obj, "CapsuleBackground", new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda51());
            case 1:
                ((ImageView) obj).setImageResource(((Number) ((Function0) obj2).invoke()).intValue());
                return Unit.INSTANCE;
            default:
                ((PrivacyScenarioCapsuleViewModel) obj2).onCapsuleClick(null);
                return Unit.INSTANCE;
        }
    }
}
