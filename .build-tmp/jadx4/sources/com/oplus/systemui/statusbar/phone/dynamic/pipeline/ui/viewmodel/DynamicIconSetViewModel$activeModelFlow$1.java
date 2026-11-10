package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class DynamicIconSetViewModel$activeModelFlow$1 extends SuspendLambda implements Function3 {
    long J$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ DynamicIconSetViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIconSetViewModel$activeModelFlow$1(DynamicIconSetViewModel dynamicIconSetViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = dynamicIconSetViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DynamicIconSetViewModel$activeModelFlow$1 dynamicIconSetViewModel$activeModelFlow$1 = new DynamicIconSetViewModel$activeModelFlow$1(this.this$0, (Continuation) obj3);
        dynamicIconSetViewModel$activeModelFlow$1.L$0 = (FlowCollector) obj;
        dynamicIconSetViewModel$activeModelFlow$1.L$1 = (Set) obj2;
        return dynamicIconSetViewModel$activeModelFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x004e  */
    /* JADX WARN: Code duplicated, block: B:18:0x0086  */
    /* JADX WARN: Code duplicated, block: B:23:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:28:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:31:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:34:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:39:0x011f  */
    /* JADX WARN: Code duplicated, block: B:40:0x0130  */
    /* JADX WARN: Path cross not found for [B:18:0x0086, B:21:0x00a0], limit reached: 41 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00fb -> B:37:0x0111). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x010e -> B:37:0x0111). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconSetViewModel$activeModelFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
