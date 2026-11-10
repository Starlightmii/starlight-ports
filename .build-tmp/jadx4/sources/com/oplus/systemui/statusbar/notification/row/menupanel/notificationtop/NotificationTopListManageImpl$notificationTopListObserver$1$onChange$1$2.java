package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.content.Context;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationTopListManageImpl$notificationTopListObserver$1$onChange$1$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int label;
    final /* synthetic */ NotificationTopListManageImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl$notificationTopListObserver$1$onChange$1$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ NotificationTopListManageImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(NotificationTopListManageImpl notificationTopListManageImpl, Continuation continuation) {
            super(2, continuation);
            this.this$0 = notificationTopListManageImpl;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            NotificationTopListManageImpl.NotificationTopListListener notificationTopListListener = this.this$0.notificationTopListListener;
            if (notificationTopListListener != null) {
                NotificationTopCoordinator notificationTopCoordinator = ((NotificationTopCoordinator$notificationTopListListener$1) notificationTopListListener).this$0;
                NotificationTopListManage notificationTopListManage = notificationTopCoordinator.notificationTopListManage;
                Set<String> notificationTopDiffSet = notificationTopListManage != null ? ((NotificationTopListManageImpl) notificationTopListManage).getNotificationTopDiffSet() : null;
                NotificationTopCoordinator.access$resolveNotificationTopIcon(notificationTopCoordinator, notificationTopDiffSet);
                NotificationTopCoordinator.access$updateNotificationUI(notificationTopCoordinator, notificationTopDiffSet);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationTopListManageImpl$notificationTopListObserver$1$onChange$1$2(Context context, NotificationTopListManageImpl notificationTopListManageImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationTopListManageImpl;
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationTopListManageImpl$notificationTopListObserver$1$onChange$1$2(this.$context, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationTopListManageImpl.access$updateNotificationTopSet(this.this$0, this.$context);
            NotificationTopListManageImpl.access$updateNotifTopDiffSet(this.this$0);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
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
