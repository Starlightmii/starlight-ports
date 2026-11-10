package com.oplus.systemui.plugins.qs;

import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileViewBinder$collectThemeApplied$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ List<IQsComponent> $nodes;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileViewBinder$collectThemeApplied$2(List list, Continuation continuation) {
        super(2, continuation);
        this.$nodes = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SepQSTileViewBinder$collectThemeApplied$2 sepQSTileViewBinder$collectThemeApplied$2 = new SepQSTileViewBinder$collectThemeApplied$2(this.$nodes, continuation);
        sepQSTileViewBinder$collectThemeApplied$2.Z$0 = ((Boolean) obj).booleanValue();
        return sepQSTileViewBinder$collectThemeApplied$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((SepQSTileViewBinder$collectThemeApplied$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        Iterator<T> it = this.$nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).onThemeApplied(z);
        }
        return Unit.INSTANCE;
    }
}
