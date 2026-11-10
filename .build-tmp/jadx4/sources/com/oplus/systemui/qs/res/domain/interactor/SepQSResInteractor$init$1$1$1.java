package com.oplus.systemui.qs.res.domain.interactor;

import com.android.systemui.kairos.BuildScopeKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$init$1$1$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SepQSResInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
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
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startMediaPanelBackgroundCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
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
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startMediaPanelThemeBackgroundCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startMediaPanelOutlineCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass4(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startCustomizerBackgroundCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass5(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startQuickEntranceBackgroundCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass6(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startStrokeEnableCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass7(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startDetailPanelBackgroundCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass8 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SepQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sepQSResInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass8(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSResInteractor sepQSResInteractor = this.this$0;
                this.label = 1;
                if (SepQSResInteractor.access$startSpotLightEnableCollection(sepQSResInteractor, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.SepQSResInteractor$init$1$1$1$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass9 implements Function0 {
        public static final AnonymousClass9 INSTANCE = new AnonymousClass9();

        public final /* bridge */ /* synthetic */ Object invoke() {
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSResInteractor$init$1$1$1(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepQSResInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SepQSResInteractor$init$1$1$1 sepQSResInteractor$init$1$1$1 = new SepQSResInteractor$init$1$1$1(this.this$0, continuation);
        sepQSResInteractor$init$1$1$1.L$0 = obj;
        return sepQSResInteractor$init$1$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass7(this.this$0, null), 3);
            BuildersKt.launch$default(producerScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass8(this.this$0, null), 3);
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.label = 1;
            if (BuildScopeKt.awaitClose(AnonymousClass9.INSTANCE, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }
}
