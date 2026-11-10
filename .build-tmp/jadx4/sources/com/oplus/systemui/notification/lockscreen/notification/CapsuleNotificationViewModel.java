package com.oplus.systemui.notification.lockscreen.notification;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.oplus.systemui.notification.lockscreen.base.CapsuleBaseViewModel;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationViewModel extends CapsuleBaseViewModel {
    private final CapsuleNotificationDataController controller;
    private final CoroutineScope mainScope;
    private final NotifPipeline pipeline;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationViewModel$refreshCapsuleData$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<PipelineEntry> $entries;
        final /* synthetic */ Function1 $onUpdate;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List list, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$entries = list;
            this.$onUpdate = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CapsuleNotificationViewModel.this.new AnonymousClass1(this.$entries, this.$onUpdate, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationViewModel.this.controller;
                List<PipelineEntry> list = this.$entries;
                Function1 function1 = this.$onUpdate;
                this.label = 1;
                CapsuleNotificationDataController.Companion companion = CapsuleNotificationDataController.Companion;
                if (capsuleNotificationDataController.refreshCapsuleData(list, CapsuleNotificationDataController.RefreshReason.NOTIFICATION_CHANGE, function1, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationViewModel$refreshCapsuleDataByMedia$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01931 extends SuspendLambda implements Function2 {
        int label;

        public C01931(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CapsuleNotificationViewModel.this.new C01931(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationViewModel.this.controller;
                CapsuleNotificationViewModel capsuleNotificationViewModel = CapsuleNotificationViewModel.this;
                CapsuleNotificationViewModel$refreshCapsuleDataByMedia$1$$ExternalSyntheticLambda0 capsuleNotificationViewModel$refreshCapsuleDataByMedia$1$$ExternalSyntheticLambda0 = new CapsuleNotificationViewModel$refreshCapsuleDataByMedia$1$$ExternalSyntheticLambda0();
                capsuleNotificationViewModel$refreshCapsuleDataByMedia$1$$ExternalSyntheticLambda0.f$0 = capsuleNotificationViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (capsuleNotificationDataController.refreshCapsuleDataByMedia(capsuleNotificationViewModel$refreshCapsuleDataByMedia$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public CapsuleNotificationViewModel(CoroutineScope coroutineScope, NotifPipeline notifPipeline, CapsuleNotificationDataController capsuleNotificationDataController) {
        super(notifPipeline, null);
        this.mainScope = coroutineScope;
        this.pipeline = notifPipeline;
        this.controller = capsuleNotificationDataController;
    }

    @Override // com.oplus.systemui.notification.lockscreen.base.CapsuleBaseViewModel
    public void refreshCapsuleData(List<? extends PipelineEntry> list, Function1 function1) {
        BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(list, function1, null), 3);
    }

    public final void refreshCapsuleDataByMedia() {
        BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new C01931(null), 3);
    }
}
