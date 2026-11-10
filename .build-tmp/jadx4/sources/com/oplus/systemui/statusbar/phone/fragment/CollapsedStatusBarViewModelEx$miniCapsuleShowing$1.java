package com.oplus.systemui.statusbar.phone.fragment;

import com.android.systemui.kairos.BuildScopeKt;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class CollapsedStatusBarViewModelEx$miniCapsuleShowing$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ OplusFlavorManager $flavorManager;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollapsedStatusBarViewModelEx$miniCapsuleShowing$1(OplusFlavorManager oplusFlavorManager, Continuation continuation) {
        super(2, continuation);
        this.$flavorManager = oplusFlavorManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        CollapsedStatusBarViewModelEx$miniCapsuleShowing$1 collapsedStatusBarViewModelEx$miniCapsuleShowing$1 = new CollapsedStatusBarViewModelEx$miniCapsuleShowing$1(this.$flavorManager, continuation);
        collapsedStatusBarViewModelEx$miniCapsuleShowing$1.L$0 = obj;
        return collapsedStatusBarViewModelEx$miniCapsuleShowing$1;
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
            CollapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1 collapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1 = new CollapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1();
            this.$flavorManager.getMiniCapsuleManager().registerCapsuleShowListener(collapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1);
            OplusFlavorManager oplusFlavorManager = this.$flavorManager;
            CollapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0 collapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0 = new CollapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0();
            collapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0.f$0 = oplusFlavorManager;
            collapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0.f$1 = collapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(collapsedStatusBarViewModelEx$miniCapsuleShowing$1$miniCapsuleShowListener$1);
            this.label = 1;
            if (BuildScopeKt.awaitClose(collapsedStatusBarViewModelEx$miniCapsuleShowing$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
