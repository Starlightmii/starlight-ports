package com.oplus.systemui.plugins.qs.addtile;

import androidx.compose.runtime.OpaqueKey$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.view.dialog.QsAddTileViewManager;
import com.oplus.systemui.plugins.qs.customize.viewmodel.AllTilesState;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AddTileComponent$1$onViewAttached$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AddTileComponent this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.addtile.AddTileComponent$1$onViewAttached$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ AddTileComponent this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.addtile.AddTileComponent$1$onViewAttached$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00441 implements FlowCollector {
            public /* synthetic */ AddTileComponent this$0;

            public final Object emit(Object obj, Continuation continuation) {
                if (obj instanceof AllTilesState.Loading) {
                    LogUtil.i$default(LogUtil.TAG_QS, "AddTileComponent", "[collectFlow] allSelectable not ready", null, 8);
                } else {
                    LogUtil.i$default(LogUtil.TAG_QS, "AddTileComponent", OpaqueKey$.ExternalSyntheticOutline0.m(obj, "[collectFlow] "), null, 8);
                    List<? extends Object> list = (List) obj;
                    QsAddTileViewManager qsAddTileViewManager = this.this$0.qsAddTileViewMgr;
                    if (qsAddTileViewManager != null) {
                        qsAddTileViewManager.updateTileAdapterDataWhenFlow(list);
                    }
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AddTileComponent addTileComponent, Continuation continuation) {
            super(2, continuation);
            this.this$0 = addTileComponent;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow allSelectable = this.this$0.editViewModel.getAllSelectable();
                AddTileComponent addTileComponent = this.this$0;
                C00441 c00441 = new C00441();
                c00441.this$0 = addTileComponent;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (FlowExtKt.collectTraced(allSelectable, "AddTileComponent-collect-allSelectable", c00441, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddTileComponent$1$onViewAttached$1(AddTileComponent addTileComponent, Continuation continuation) {
        super(3, continuation);
        this.this$0 = addTileComponent;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AddTileComponent$1$onViewAttached$1 addTileComponent$1$onViewAttached$1 = new AddTileComponent$1$onViewAttached$1(this.this$0, (Continuation) obj3);
        addTileComponent$1$onViewAttached$1.L$0 = (LifecycleOwner) obj;
        return addTileComponent$1$onViewAttached$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
