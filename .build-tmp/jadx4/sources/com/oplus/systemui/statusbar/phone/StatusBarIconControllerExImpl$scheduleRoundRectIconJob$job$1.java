package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import com.android.systemui.statusbar.StatusBarIconView;
import com.oplus.systemui.statusbar.notification.util.RoundRectDrawableUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $appContext;
    final /* synthetic */ StatusBarIconView $iconView;
    final /* synthetic */ Drawable $safeDrawable;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ StatusBarIconView $iconView;
        final /* synthetic */ Ref.ObjectRef<Drawable> $pending;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Ref.ObjectRef objectRef, StatusBarIconView statusBarIconView, Continuation continuation) {
            super(2, continuation);
            this.$pending = objectRef;
            this.$iconView = statusBarIconView;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$pending, this.$iconView, continuation);
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
            Drawable drawable = (Drawable) this.$pending.element;
            if (drawable != null) {
                this.$iconView.setImageDrawable(drawable);
            }
            this.$pending.element = null;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1(Drawable drawable, Context context, StatusBarIconView statusBarIconView, Continuation continuation) {
        super(2, continuation);
        this.$safeDrawable = drawable;
        this.$appContext = context;
        this.$iconView = statusBarIconView;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1 statusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1 = new StatusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1(this.$safeDrawable, this.$appContext, this.$iconView, continuation);
        statusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1.L$0 = obj;
        return statusBarIconControllerExImpl$scheduleRoundRectIconJob$job$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) throws Throwable {
        Ref.ObjectRef objectRef;
        Throwable th;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            objectRef = (Ref.ObjectRef) this.L$1;
            try {
                ResultKt.throwOnFailure(obj);
                RoundRectDrawableUtil.recycleOwnedRoundRectDrawable((Drawable) objectRef.element, this.$safeDrawable);
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
                RoundRectDrawableUtil.recycleOwnedRoundRectDrawable((Drawable) objectRef.element, this.$safeDrawable);
                throw th;
            }
        }
        Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
        try {
            CoroutineScopeKt.ensureActive(coroutineScope);
            Drawable drawable = this.$safeDrawable;
            StatusBarIconControllerExImpl.Companion companion = StatusBarIconControllerExImpl.Companion;
            float sIconRadiusFraction = companion.getSIconRadiusFraction();
            int sNotificationRoundIconSize = companion.getSNotificationRoundIconSize();
            int sNotificationRoundIconSize2 = companion.getSNotificationRoundIconSize();
            Context context = this.$appContext;
            context.getClass();
            objectRefM.element = RoundRectDrawableUtil.getRoundRectDrawable(drawable, sIconRadiusFraction, sNotificationRoundIconSize, sNotificationRoundIconSize2, context);
            MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(objectRefM, this.$iconView, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.L$1 = objectRefM;
            this.label = 1;
            if (BuildersKt.withContext(immediate, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRefM;
            RoundRectDrawableUtil.recycleOwnedRoundRectDrawable((Drawable) objectRef.element, this.$safeDrawable);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            objectRef = objectRefM;
            th = th3;
            RoundRectDrawableUtil.recycleOwnedRoundRectDrawable((Drawable) objectRef.element, this.$safeDrawable);
            throw th;
        }
    }
}
