package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.util.Log;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileConfigRepoSwitcher$startSetListeningTileConfigChange$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ TileConfigRepoSwitcher this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ TileConfigRepoSwitcher this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TileConfigRepoSwitcher tileConfigRepoSwitcher, Continuation continuation) {
            super(2, continuation);
            this.this$0 = tileConfigRepoSwitcher;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((Pair) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (((Number) this.this$0._scene.getValue()).intValue() != 2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileConfigRepoSwitcher-->TileConfigChange:resetLandscapeRepoTask");
                }
                this.this$0.mainHandler.removeCallbacks(this.this$0.resetLandscapeRepoTask);
                this.this$0.resetLandscapeRepoTask.run();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileConfigRepoSwitcher$startSetListeningTileConfigChange$1(TileConfigRepoSwitcher tileConfigRepoSwitcher, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tileConfigRepoSwitcher;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new TileConfigRepoSwitcher$startSetListeningTileConfigChange$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowTransformLatest = FlowKt.transformLatest(this.this$0._userId, new TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, null));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, null);
            this.label = 1;
            if (FlowExtKt.collectLatestTraced(flowTransformLatest, anonymousClass2, this) == coroutine_suspended) {
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
