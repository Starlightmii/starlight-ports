package com.oplus.systemui.statusbar.notification.livealert.data.interactor;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.statusbar.notification.livealert.data.model.LiveAlertAction;
import com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter;
import com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilterByPlugin;
import com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilters;
import com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertNotificationsRepository;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsInteractor implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String REASON = "LiveAlert";
    private static final String SYSTEM_UI_SEEDLING_CARD_AUTHORITY = "com.oplus.card.server.systemui.provider";
    private static final String TAG = "OplusLiveAlertNotificationsInteractor";
    private Job collectJob;
    private final Context context;
    private OplusLiveAlertFilter filterByPlugin;
    private final Lazy filterLazy;
    private final OplusLiveAlertNotificationsInteractor$hunFilter$1 hunFilter;
    private final SharedFlow loadPlugin;
    private final OplusLiveAlertNotificationsInteractor$preGroupFilter$1 preGroupFilter = new NotifFilter() { // from class: com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$preGroupFilter$1
        {
            super("LiveAlert");
        }

        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return this.this$0.isLiveAlert(notificationEntry, false);
        }
    };
    private final OplusLiveAlertNotificationsRepository repository;
    private final CoroutineScope scope;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$start$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$start$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ OplusLiveAlertNotificationsInteractor this$0;

            public final Object emit(Object obj, Continuation continuation) {
                OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor = this.this$0;
                oplusLiveAlertNotificationsInteractor.preGroupFilter.invalidateList(OplusLiveAlertNotificationsInteractor.REASON);
                OplusLiveAlertNotificationsInteractor.access$notifyLiveAlerts(oplusLiveAlertNotificationsInteractor, (LiveAlertAction) obj);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusLiveAlertNotificationsInteractor.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow liveAlerts = OplusLiveAlertNotificationsInteractor.this.repository.getLiveAlerts();
                OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor = OplusLiveAlertNotificationsInteractor.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = oplusLiveAlertNotificationsInteractor;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (liveAlerts.collect(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$preGroupFilter$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$hunFilter$1] */
    public OplusLiveAlertNotificationsInteractor(Context context, OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository, Lazy lazy, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, CoroutineScope coroutineScope) {
        this.context = context;
        this.repository = oplusLiveAlertNotificationsRepository;
        this.filterLazy = lazy;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.scope = coroutineScope;
        final Set of = SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK);
        this.hunFilter = new VisualInterruptionFilter(of) { // from class: com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor$hunFilter$1
            public final boolean shouldSuppress(NotificationEntry notificationEntry) {
                OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor = this.this$0;
                return oplusLiveAlertNotificationsInteractor.repository.hasStatusBarLiveAlert(notificationEntry) || oplusLiveAlertNotificationsInteractor.interceptorHun(notificationEntry);
            }
        };
        this.loadPlugin = oplusLiveAlertNotificationsRepository.getLoadPlugin();
    }

    public static final void access$notifyLiveAlerts(OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor, LiveAlertAction liveAlertAction) {
        oplusLiveAlertNotificationsInteractor.getClass();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "notifyLiveAlerts: " + liveAlertAction, null, 8);
        try {
            String method = liveAlertAction.getMethod();
            if (method != null) {
                ContentResolver contentResolver = oplusLiveAlertNotificationsInteractor.context.getContentResolver();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(KEY_NOTIFICATIONS, liveAlertAction.getSbnList());
                contentResolver.call(SYSTEM_UI_SEEDLING_CARD_AUTHORITY, method, "", bundle);
            }
        } catch (IllegalArgumentException e) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "notifyLiveAlerts: call error: ", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    public void attach(NotifPipeline notifPipeline) {
        SeedlingPluginManager.Companion.getClass();
        if (SeedlingPluginManager.Companion.isFluidEntrySupported()) {
            this.repository.attach(notifPipeline);
            this.visualInterruptionDecisionProvider.addFilterForLiveAlert(this.hunFilter);
            notifPipeline.addPreGroupFilter(this.preGroupFilter);
        }
    }

    public final void dismissLiveAlert(String str) {
        this.repository.dismissLiveAlert(str);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.repository.dump(printWriter, strArr);
    }

    public final StatusBarNotification getLiveAlertNotification(String str) {
        return this.repository.getLiveAlertNotification(str);
    }

    public final SharedFlow getLoadPlugin() {
        return this.loadPlugin;
    }

    public final boolean interceptorHun(NotificationEntry notificationEntry) {
        return this.repository.interceptorHun(notificationEntry);
    }

    public final boolean isLiveAlert(NotificationEntry notificationEntry, boolean z) {
        return this.repository.isLiveAlert(notificationEntry, z);
    }

    public final void start(SeedlingPlugin seedlingPlugin) {
        SeedlingPluginManager.Companion.getClass();
        if (SeedlingPluginManager.Companion.isFluidEntrySupported()) {
            OplusLiveAlertFilterByPlugin oplusLiveAlertFilterByPlugin = new OplusLiveAlertFilterByPlugin(seedlingPlugin);
            ((OplusLiveAlertFilters) this.filterLazy.get()).addFilter(oplusLiveAlertFilterByPlugin);
            this.filterByPlugin = oplusLiveAlertFilterByPlugin;
            this.repository.setFilter((OplusLiveAlertFilters) this.filterLazy.get());
            this.repository.setPlugin(seedlingPlugin);
            this.collectJob = BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
        }
    }

    public final void stop() {
        Job job = this.collectJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        OplusLiveAlertFilter oplusLiveAlertFilter = this.filterByPlugin;
        if (oplusLiveAlertFilter != null) {
            ((OplusLiveAlertFilters) this.filterLazy.get()).removeFilter(oplusLiveAlertFilter);
        }
        this.repository.setFilter(null);
        this.repository.setPlugin(null);
    }

    public final void updateLiveAlerts() {
        this.repository.reInitLiveAlert();
    }
}
