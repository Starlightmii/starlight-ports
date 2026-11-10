package com.oplus.systemui.notification.row.oplusgroup;

import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AppIconGroupColorManager$getOrCreateTask$deferred$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationEntry $entry;
    final /* synthetic */ String $key;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ AppIconGroupColorManager this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.oplusgroup.AppIconGroupColorManager$getOrCreateTask$deferred$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ AppIconGroupColorManager.AppIconColorConfig $config;
        final /* synthetic */ String $key;
        int label;
        final /* synthetic */ AppIconGroupColorManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AppIconGroupColorManager appIconGroupColorManager, String str, AppIconGroupColorManager.AppIconColorConfig appIconColorConfig, Continuation continuation) {
            super(2, continuation);
            this.this$0 = appIconGroupColorManager;
            this.$key = str;
            this.$config = appIconColorConfig;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$key, this.$config, continuation);
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
            this.this$0.configMap.put(this.$key, this.$config);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.oplusgroup.AppIconGroupColorManager$getOrCreateTask$deferred$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $key;
        int label;
        final /* synthetic */ AppIconGroupColorManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(AppIconGroupColorManager appIconGroupColorManager, String str, Continuation continuation) {
            super(2, continuation);
            this.this$0 = appIconGroupColorManager;
            this.$key = str;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.this$0, this.$key, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return this.this$0.taskMap.remove(this.$key);
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppIconGroupColorManager$getOrCreateTask$deferred$1(AppIconGroupColorManager appIconGroupColorManager, NotificationEntry notificationEntry, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = appIconGroupColorManager;
        this.$entry = notificationEntry;
        this.$key = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AppIconGroupColorManager$getOrCreateTask$deferred$1(this.this$0, this.$entry, this.$key, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0150 A[RETURN] */
    public final Object invokeSuspend(Object obj) throws Throwable {
        AppIconGroupColorManager.AppIconColorConfig appIconColorConfig;
        String str;
        MainCoroutineDispatcher main;
        AnonymousClass3 anonymousClass3;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                NotificationResolverEx notificationResolverEx = (NotificationResolverEx) this.this$0.notificationResolverEx.get();
                StatusBarNotification sbn = this.$entry.getSbn();
                this.label = 1;
                obj = notificationResolverEx.getPackageIconForNotification(sbn, this);
                if (obj == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return null;
                }
                if (i != 3) {
                    if (i == 4) {
                        AppIconGroupColorManager.AppIconColorConfig appIconColorConfig2 = (AppIconGroupColorManager.AppIconColorConfig) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        return appIconColorConfig2;
                    }
                    if (i != 5) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    Throwable th = (Throwable) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    throw th;
                }
                appIconColorConfig = (AppIconGroupColorManager.AppIconColorConfig) this.L$2;
                ResultKt.throwOnFailure(obj);
            }
            str = this.$key;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "AppIconGroupColorManager-->".concat("getOrCreateTask: complete key=" + str + " lightColor=" + Integer.toHexString(appIconColorConfig.getLightColor()) + " darkColor=" + Integer.toHexString(appIconColorConfig.getDarkColor()) + " seedColor=" + Integer.toHexString(appIconColorConfig.getSeedColor())));
            }
            main = Dispatchers.getMain();
            anonymousClass3 = new AnonymousClass3(this.this$0, this.$key, null);
            this.L$0 = appIconColorConfig;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 4;
            if (BuildersKt.withContext(main, anonymousClass3, this) != coroutine_suspended) {
                return coroutine_suspended;
            }
            return appIconColorConfig;
            Drawable drawable = (Drawable) obj;
            if (drawable == null) {
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                AnonymousClass3 anonymousClass4 = new AnonymousClass3(this.this$0, this.$key, null);
                this.label = 2;
                if (BuildersKt.withContext(main2, anonymousClass4, this) != coroutine_suspended) {
                    return null;
                }
            } else {
                Triple<Integer, Integer, Integer> expandedPillBgColor = GroupIconManager.Companion.getExpandedPillBgColor(this.this$0.context, drawable);
                AppIconGroupColorManager.AppIconColorConfig appIconColorConfig3 = new AppIconGroupColorManager.AppIconColorConfig(((Number) expandedPillBgColor.getFirst()).intValue(), ((Number) expandedPillBgColor.getSecond()).intValue(), ((Number) expandedPillBgColor.getThird()).intValue());
                MainCoroutineDispatcher main3 = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$key, appIconColorConfig3, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(drawable);
                this.L$1 = SpillingKt.nullOutSpilledVariable(expandedPillBgColor);
                this.L$2 = appIconColorConfig3;
                this.label = 3;
                if (BuildersKt.withContext(main3, anonymousClass1, this) != coroutine_suspended) {
                    appIconColorConfig = appIconColorConfig3;
                    str = this.$key;
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--Notification", "AppIconGroupColorManager-->".concat("getOrCreateTask: complete key=" + str + " lightColor=" + Integer.toHexString(appIconColorConfig.getLightColor()) + " darkColor=" + Integer.toHexString(appIconColorConfig.getDarkColor()) + " seedColor=" + Integer.toHexString(appIconColorConfig.getSeedColor())));
                    }
                    main = Dispatchers.getMain();
                    anonymousClass3 = new AnonymousClass3(this.this$0, this.$key, null);
                    this.L$0 = appIconColorConfig;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.label = 4;
                    if (BuildersKt.withContext(main, anonymousClass3, this) != coroutine_suspended) {
                        return appIconColorConfig;
                    }
                }
            }
        } catch (Throwable th2) {
            MainCoroutineDispatcher main4 = Dispatchers.getMain();
            AnonymousClass3 anonymousClass5 = new AnonymousClass3(this.this$0, this.$key, null);
            this.L$0 = th2;
            this.L$1 = null;
            this.L$2 = null;
            this.label = 5;
            if (BuildersKt.withContext(main4, anonymousClass5, this) != coroutine_suspended) {
                throw th2;
            }
        }
        return coroutine_suspended;
    }
}
