package com.oplus.systemui.plugins.qs.customize.component;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusQSTilesComponent$collectFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusQSTilesComponent this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$collectFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusQSTilesComponent this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$collectFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00501 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusQSTilesComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$collectFlow$1$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public /* synthetic */ OplusQSTilesComponent this$0;

                public /* synthetic */ AnonymousClass2(int i) {
                    this.$r8$classId = i;
                }

                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            TileEditState tileEditState = (TileEditState) obj;
                            OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
                            if (tileEditState instanceof TileEditState.Normal) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->".concat("[collect-uiStateFlow] " + tileEditState));
                                }
                                oplusQSTilesComponent.otherAddedTiles.clear();
                                List list = oplusQSTilesComponent.otherAddedTiles;
                                TileEditState.Normal normal = (TileEditState.Normal) tileEditState;
                                boolean z = normal.isEditMode;
                                list.addAll(normal.getOtherAddedTiles());
                                oplusQSTilesComponent.setNeedItemAnimator(z || oplusQSTilesComponent.getTileAdapter().isEditMode);
                                oplusQSTilesComponent.getTileAdapter().setTileNameEnabled(normal.getTileNameEnabled());
                                oplusQSTilesComponent.getTileAdapter().updateEditMode$1(z);
                                oplusQSTilesComponent.getTileAdapter().setResizeEnabled(false);
                                oplusQSTilesComponent.bindData(normal.getOtherAddedTiles());
                            }
                            break;
                        default:
                            TileEditEffectWrapper tileEditEffectWrapper = (TileEditEffectWrapper) obj;
                            TileEditEffect effect = tileEditEffectWrapper.getEffect();
                            TileEditEffect.TransformToSpecEffect transformToSpecEffect = effect instanceof TileEditEffect.TransformToSpecEffect ? (TileEditEffect.TransformToSpecEffect) effect : null;
                            if (transformToSpecEffect != null) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->".concat("[collect-uiEffectFlow] " + tileEditEffectWrapper));
                                }
                                OplusQSTilesComponent.access$scrollToTarget(this.this$0, transformToSpecEffect.getTargetUniqueId(), transformToSpecEffect.getHighlight());
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00501(OplusQSTilesComponent oplusQSTilesComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSTilesComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00501(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow uiStateFlow = this.this$0.editViewModel.getUiStateFlow();
                    OplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 = new OplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1(0);
                    oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1.$this_unsafeTransform$inlined = uiStateFlow;
                    VarHandle.storeStoreFence();
                    OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
                    anonymousClass2.this$0 = oplusQSTilesComponent;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (FlowExtKt.collectTraced(oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1, "OplusQSTilesComponent-collect-uiStateFlow", anonymousClass2, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSTilesComponent$collectFlow$1$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusQSTilesComponent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OplusQSTilesComponent oplusQSTilesComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSTilesComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                int i2 = 1;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SharedFlow uiEffectFlow = this.this$0.editViewModel.getUiEffectFlow();
                    OplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 = new OplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1(1);
                    oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1.$this_unsafeTransform$inlined = uiEffectFlow;
                    VarHandle.storeStoreFence();
                    OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
                    C00501.AnonymousClass2 anonymousClass2 = new C00501.AnonymousClass2(i2);
                    anonymousClass2.this$0 = oplusQSTilesComponent;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (FlowExtKt.collectTraced(oplusQSTilesComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1, "OplusQSTilesComponent-collect-uiEffectFlow", anonymousClass2, this) == coroutine_suspended) {
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
        public AnonymousClass1(OplusQSTilesComponent oplusQSTilesComponent, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusQSTilesComponent;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSTilesComponent-launch-uiStateFlow", (CoroutineContext) null, (CoroutineStart) null, new C00501(this.this$0, null), 6);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSTilesComponent-launch-uiEffectFlow", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusQSTilesComponent$collectFlow$1(OplusQSTilesComponent oplusQSTilesComponent, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusQSTilesComponent;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusQSTilesComponent$collectFlow$1 oplusQSTilesComponent$collectFlow$1 = new OplusQSTilesComponent$collectFlow$1(this.this$0, (Continuation) obj3);
        oplusQSTilesComponent$collectFlow$1.L$0 = (LifecycleOwner) obj;
        return oplusQSTilesComponent$collectFlow$1.invokeSuspend(Unit.INSTANCE);
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
