package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationHeaderViewWrapperExImp$updateIconForMcs$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Drawable $it;
    int label;
    final /* synthetic */ OplusNotificationHeaderViewWrapperExImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusNotificationHeaderViewWrapperExImp$updateIconForMcs$1$1$1(OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp, Drawable drawable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusNotificationHeaderViewWrapperExImp;
        this.$it = drawable;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusNotificationHeaderViewWrapperExImp$updateIconForMcs$1$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher coroutineDispatcher = Dispatchers.getDefault();
            drawableWithBadge.1 r1 = new drawableWithBadge.1(this.this$0, this.$it, (Continuation) null);
            this.label = 1;
            obj = BuildersKt.withContext(coroutineDispatcher, r1, this);
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
        OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp = this.this$0;
        oplusNotificationHeaderViewWrapperExImp.applyExpandedCanvasIconWithBadge((Drawable) obj, oplusNotificationHeaderViewWrapperExImp.resolveHeaderIconContentSizePx(), this.this$0.getContext().getResources().getDimensionPixelSize(2131170675), null);
        ImageView mRightIcon = this.this$0.getMRightIcon();
        if (mRightIcon != null) {
            mRightIcon.setVisibility(8);
        }
        return Unit.INSTANCE;
    }
}
