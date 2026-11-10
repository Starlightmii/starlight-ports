package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelPluginInteractor$special$$inlined$map$1 implements Flow {
    public /* synthetic */ StateFlow $this_unsafeTransform$inlined;
    public /* synthetic */ PanelPluginInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor$special$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ PanelPluginInteractor this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            int I$1;
            int I$2;
            int I$3;
            int I$4;
            Object L$0;
            Object L$1;
            Object L$10;
            Object L$11;
            Object L$12;
            Object L$13;
            Object L$14;
            Object L$15;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            Object L$7;
            Object L$8;
            Object L$9;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:19:0x00f2  */
        /* JADX WARN: Code duplicated, block: B:22:0x015c  */
        /* JADX WARN: Code duplicated, block: B:7:0x0019  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x015c -> B:23:0x0176). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object emit(java.lang.Object r29, kotlin.coroutines.Continuation r30) {
            /*
                Method dump skipped, instruction units count: 626
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        StateFlow stateFlow = this.$this_unsafeTransform$inlined;
        PanelPluginInteractor panelPluginInteractor = this.this$0;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.this$0 = panelPluginInteractor;
        VarHandle.storeStoreFence();
        Object objCollect = stateFlow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
