package com.oplus.systemui.statusbar.notification.dependence;

import android.content.Context;
import android.util.Log;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;
import com.android.systemui.statusbar.window.data.repository.StatusBarWindowStatePerDisplayRepository;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDependOnCommonImpl implements NotificationDependOnCommon {
    private static final Companion Companion = new Companion();
    private static final String TAG = "NotificationDependOnCom";
    private final Context context;
    private final PerDisplayRepository displaySubComponentRepository;
    private final Set<StatusBarWindowStateListener> listeners = new CopyOnWriteArraySet();
    public SelectedUserInteractor selectedUserInteractor;
    private final ShadeDisplaysRepository shadeDisplaysRepository;
    private final ShadeInteractor shadeInteractor;
    public int windowState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationDependOnCommonImpl(Context context, ShadeInteractor shadeInteractor, ShadeDisplaysRepository shadeDisplaysRepository, PerDisplayRepository perDisplayRepository) {
        this.context = context;
        this.shadeInteractor = shadeInteractor;
        this.shadeDisplaysRepository = shadeDisplaysRepository;
        this.displaySubComponentRepository = perDisplayRepository;
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon
    public void addListener(StatusBarWindowStateListener statusBarWindowStateListener) {
        this.listeners.add(statusBarWindowStateListener);
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon
    public Context getApplicationContext() {
        return this.context;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon
    public int getCurrentUser() {
        return getSelectedUserInteractor().getSelectedUserId();
    }

    public final SelectedUserInteractor getSelectedUserInteractor() {
        SelectedUserInteractor selectedUserInteractor = this.selectedUserInteractor;
        if (selectedUserInteractor != null) {
            return selectedUserInteractor;
        }
        return null;
    }

    public final ShadeDisplaysRepository getShadeDisplaysRepository() {
        return this.shadeDisplaysRepository;
    }

    public final ShadeInteractor getShadeInteractor() {
        return this.shadeInteractor;
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon
    public int getWindowState() {
        return this.windowState;
    }

    public final boolean isFullyCollapsed() {
        return !((Boolean) this.shadeInteractor.isAnyExpanded().getValue()).booleanValue();
    }

    @Override // com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon
    public void removeListener(StatusBarWindowStateListener statusBarWindowStateListener) {
        this.listeners.remove(statusBarWindowStateListener);
    }

    public final void setSelectedUserInteractor(SelectedUserInteractor selectedUserInteractor) {
        this.selectedUserInteractor = selectedUserInteractor;
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommonImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationDependOnCommonImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow displayId = NotificationDependOnCommonImpl.this.getShadeDisplaysRepository().getDisplayId();
                NotificationDependOnCommonImpl notificationDependOnCommonImpl = NotificationDependOnCommonImpl.this;
                C01221 c01221 = new C01221();
                c01221.this$0 = notificationDependOnCommonImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (displayId.collect(c01221, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommonImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01221 implements FlowCollector {
            public /* synthetic */ NotificationDependOnCommonImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommonImpl$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class AnonymousClass2 implements FlowCollector {
                public /* synthetic */ NotificationDependOnCommonImpl this$0;

                /* JADX WARN: Code duplicated, block: B:14:0x0039 A[PHI: r0
                  0x0039: PHI (r0v2 int) = (r0v1 int), (r0v3 int) binds: [B:10:0x0033, B:12:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
                public final Object emit(Object obj, Continuation continuation) {
                    StatusBarWindowState statusBarWindowState = (StatusBarWindowState) obj;
                    NotificationDependOnCommonImpl notificationDependOnCommonImpl = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationDependOnCom-->".concat("onStatusBarWindowStateChanged:" + statusBarWindowState));
                    }
                    int iOrdinal = statusBarWindowState.ordinal();
                    int i = 0;
                    if (iOrdinal != 0) {
                        int i2 = 1;
                        if (iOrdinal != 1) {
                            i2 = 2;
                            if (iOrdinal == 2) {
                                i = i2;
                            }
                        } else {
                            i = i2;
                        }
                    }
                    notificationDependOnCommonImpl.windowState = i;
                    Iterator it = notificationDependOnCommonImpl.listeners.iterator();
                    while (it.hasNext()) {
                        ((StatusBarWindowStateListener) it.next()).onStatusBarWindowStateChanged(notificationDependOnCommonImpl.windowState);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            public final Object emit(int i, Continuation continuation) {
                NotificationDependOnCommonImpl$1$1$emit$1 notificationDependOnCommonImpl$1$1$emit$1;
                NotificationDependOnCommonImpl notificationDependOnCommonImpl = this.this$0;
                if (continuation instanceof NotificationDependOnCommonImpl$1$1$emit$1) {
                    notificationDependOnCommonImpl$1$1$emit$1 = (NotificationDependOnCommonImpl$1$1$emit$1) continuation;
                    int i2 = notificationDependOnCommonImpl$1$1$emit$1.label;
                    if ((i2 & Integer.MIN_VALUE) != 0) {
                        notificationDependOnCommonImpl$1$1$emit$1.label = i2 - Integer.MIN_VALUE;
                    } else {
                        notificationDependOnCommonImpl$1$1$emit$1 = new NotificationDependOnCommonImpl$1$1$emit$1(this, continuation);
                    }
                } else {
                    notificationDependOnCommonImpl$1$1$emit$1 = new NotificationDependOnCommonImpl$1$1$emit$1(this, continuation);
                }
                Object obj = notificationDependOnCommonImpl$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i3 = notificationDependOnCommonImpl$1$1$emit$1.label;
                if (i3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "displayId changed:", "NotificationDependOnCom-->", "SystemUi--Notification");
                    }
                    StatusBarWindowStatePerDisplayRepository statusBarWindowStateRepository = ((ReferenceSysUIDisplaySubcomponent) notificationDependOnCommonImpl.displaySubComponentRepository.getOrDefault(i)).getStatusBarWindowStateRepository();
                    StateFlow windowState = statusBarWindowStateRepository.getWindowState();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                    anonymousClass2.this$0 = notificationDependOnCommonImpl;
                    VarHandle.storeStoreFence();
                    notificationDependOnCommonImpl$1$1$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(statusBarWindowStateRepository);
                    notificationDependOnCommonImpl$1$1$emit$1.I$0 = i;
                    notificationDependOnCommonImpl$1$1$emit$1.label = 1;
                    if (windowState.collect(anonymousClass2, notificationDependOnCommonImpl$1$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i3 != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Regex$.ExternalSyntheticBUOutline0.m$1();
                return null;
            }

            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Number) obj).intValue(), continuation);
            }
        }
    }
}
