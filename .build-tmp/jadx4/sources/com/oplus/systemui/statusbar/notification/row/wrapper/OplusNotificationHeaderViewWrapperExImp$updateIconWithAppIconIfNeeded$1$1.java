package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import com.android.internal.widget.CachingIconView;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationHeaderViewWrapperExImp$updateIconWithAppIconIfNeeded$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationHeaderViewWrapper $this_apply;
    int label;
    final /* synthetic */ OplusNotificationHeaderViewWrapperExImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusNotificationHeaderViewWrapperExImp$updateIconWithAppIconIfNeeded$1$1(NotificationHeaderViewWrapper notificationHeaderViewWrapper, OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp, Continuation continuation) {
        super(2, continuation);
        this.$this_apply = notificationHeaderViewWrapper;
        this.this$0 = oplusNotificationHeaderViewWrapperExImp;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusNotificationHeaderViewWrapperExImp$updateIconWithAppIconIfNeeded$1$1(this.$this_apply, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationResolverEx notificationResolverEx = (NotificationResolverEx) DependencyEx.sDependency.getDependency(NotificationResolverEx.class);
            StatusBarNotification sbn = ((NotificationViewWrapper) this.$this_apply).mRow.getEntry().getSbn();
            this.label = 1;
            obj = notificationResolverEx.getPackageIconForNotification(sbn, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            NotificationHeaderViewWrapper notificationHeaderViewWrapper = this.$this_apply;
            OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp = this.this$0;
            CachingIconView cachingIconView = notificationHeaderViewWrapper.mIcon;
            if (cachingIconView != null) {
                cachingIconView.setImageDrawable(drawable);
            }
            OplusNotificationHeaderViewWrapperExImp.access$updateRightIconWithAppIconIfNeeded(oplusNotificationHeaderViewWrapperExImp, drawable);
        }
        return Unit.INSTANCE;
    }
}
