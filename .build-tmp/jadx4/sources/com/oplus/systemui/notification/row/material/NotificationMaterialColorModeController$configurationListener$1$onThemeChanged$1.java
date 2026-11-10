package com.oplus.systemui.notification.row.material;

import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationMaterialColorModeController$configurationListener$1$onThemeChanged$1 extends SuspendLambda implements Function2 {
    boolean Z$0;
    int label;
    final /* synthetic */ NotificationMaterialColorModeController this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$configurationListener$1$onThemeChanged$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $isWallpaperDimmingNotSupported;
        int label;
        final /* synthetic */ NotificationMaterialColorModeController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, NotificationMaterialColorModeController notificationMaterialColorModeController, Continuation continuation) {
            super(2, continuation);
            this.$isWallpaperDimmingNotSupported = z;
            this.this$0 = notificationMaterialColorModeController;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$isWallpaperDimmingNotSupported, this.this$0, continuation);
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
            if (this.$isWallpaperDimmingNotSupported != ((Boolean) this.this$0._isWallpaperDimmingNotSupported.getValue()).booleanValue()) {
                this.this$0._isWallpaperDimmingNotSupported.setValue(Boxing.boxBoolean(this.$isWallpaperDimmingNotSupported));
            }
            NotificationMaterialColorModeController notificationMaterialColorModeController = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("onUiModeChanged: isWallpaperDimmingNotSupported=", notificationMaterialColorModeController._isWallpaperDimmingNotSupported.getValue(), "MaterialColorController-->", "SystemUi--Notification");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationMaterialColorModeController$configurationListener$1$onThemeChanged$1(NotificationMaterialColorModeController notificationMaterialColorModeController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationMaterialColorModeController;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationMaterialColorModeController$configurationListener$1$onThemeChanged$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean zAccess$isWallpaperDimmingNotSupported = NotificationMaterialColorModeController.access$isWallpaperDimmingNotSupported(this.this$0);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(zAccess$isWallpaperDimmingNotSupported, this.this$0, null);
            this.Z$0 = zAccess$isWallpaperDimmingNotSupported;
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
