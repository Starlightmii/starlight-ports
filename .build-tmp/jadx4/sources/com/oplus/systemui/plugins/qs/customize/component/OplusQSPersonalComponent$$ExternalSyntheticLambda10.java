package com.oplus.systemui.plugins.qs.customize.component;

import android.content.Context;
import android.view.ViewGroup;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EmptyViewHolder;
import com.oplusos.systemui.common.model.Loc;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSPersonalComponent$$ExternalSyntheticLambda10 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSPersonalComponent f$0;

    public /* synthetic */ OplusQSPersonalComponent$$ExternalSyntheticLambda10(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        OplusQSPersonalComponent oplusQSPersonalComponent = this.f$0;
        switch (i) {
            case 0:
                OplusQSPersonalComponent.Companion companion = OplusQSPersonalComponent.Companion;
                Context context = oplusQSPersonalComponent.getContext();
                OplusQSPersonalComponent$$ExternalSyntheticLambda10 oplusQSPersonalComponent$$ExternalSyntheticLambda10 = new OplusQSPersonalComponent$$ExternalSyntheticLambda10(1);
                oplusQSPersonalComponent$$ExternalSyntheticLambda10.f$0 = oplusQSPersonalComponent;
                VarHandle.storeStoreFence();
                return new EmptyViewHolder(context, (ViewGroup) obj, true, oplusQSPersonalComponent$$ExternalSyntheticLambda10);
            default:
                return OplusQSPersonalComponent.m130$r8$lambda$GSky4fhWxTK8zbZWwuYXMl7WdU(oplusQSPersonalComponent, (Loc) obj);
        }
    }
}
