package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilderKt;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSDetailContainerBinder$collectBackgroundState$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $container;
    final /* synthetic */ TileDrawableWrapper $drawableWrapper;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSDetailContainerBinder$collectBackgroundState$2(TileDrawableWrapper tileDrawableWrapper, View view, Continuation continuation) {
        super(3, continuation);
        this.$drawableWrapper = tileDrawableWrapper;
        this.$container = view;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        QSDetailContainerBinder$collectBackgroundState$2 qSDetailContainerBinder$collectBackgroundState$2 = new QSDetailContainerBinder$collectBackgroundState$2(this.$drawableWrapper, this.$container, (Continuation) obj3);
        qSDetailContainerBinder$collectBackgroundState$2.L$0 = (QSDrawableBuilder) obj;
        qSDetailContainerBinder$collectBackgroundState$2.L$1 = (CornerOutlineProvider) obj2;
        return qSDetailContainerBinder$collectBackgroundState$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        QSDrawableBuilder qSDrawableBuilder = (QSDrawableBuilder) this.L$0;
        CornerOutlineProvider cornerOutlineProvider = (CornerOutlineProvider) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.$drawableWrapper.setDrawable(QSDrawableBuilderKt.dialogBuild(qSDrawableBuilder, this.$container, true));
        this.$drawableWrapper.setPathProvider(cornerOutlineProvider);
        return Unit.INSTANCE;
    }
}
