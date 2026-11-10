package com.oplus.systemui.statusbar.notification.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.dispatch.DispatchQueue;
import com.oplus.dispatch.OCDHandler;
import com.oplus.dispatch.OCDMessageQueue;
import com.oplus.dispatch.QueueAttr;
import com.oplus.dispatch.QueueManager;
import com.oplus.synergynotificationsdk.SynergyNotificationManager;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.notification.INotificationCenter;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.android.HandlerDispatcher;
import kotlinx.coroutines.android.HandlerDispatcherKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCenterConnectImpl implements NotificationCenterConnect {
    private static final String NAME_CLASS = "com.oplus.notification.NotificationCenterService";
    private static final String NAME_PACKAGE = "com.oplus.notificationmanager";
    private static final Lazy workCoroutineDispatcher$delegate;
    private static final Lazy workHandler$delegate;
    private final Runnable binderRunnable;
    private final Context context;
    public final long disconnectDelayMs;
    private INotificationCenter iNotificationCenter;
    private final ManagerConnection mManagerConnection;
    private final Runnable unBinderRunnable;
    private Context userContext;
    private List<Function1> waitQueue = new CopyOnWriteArrayList();
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationCenterConnect";
    public static final OCDMessageQueue workThread = QueueManager.messageQueueCreate(TAG, QueueAttr.QUEUE_SERIAL_TIME, (DispatchQueue) null);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final HandlerDispatcher getWorkCoroutineDispatcher() {
            return (HandlerDispatcher) NotificationCenterConnectImpl.workCoroutineDispatcher$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final OCDHandler getWorkHandler() {
            return (OCDHandler) NotificationCenterConnectImpl.workHandler$delegate.getValue();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnectImpl$runAction$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$callback = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationCenterConnectImpl.this.new AnonymousClass1(this.$callback, continuation);
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
            NotificationCenterConnectImpl notificationCenterConnectImpl = NotificationCenterConnectImpl.this;
            Unit unit = Unit.INSTANCE;
            notificationCenterConnectImpl.runAndWaitResult(unit, this.$callback);
            return unit;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnectImpl$runAndWaitResult$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02861 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $callback;
        final /* synthetic */ Object $normalValue;
        int I$0;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02861(Object obj, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$normalValue = obj;
            this.$callback = function1;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationCenterConnectImpl.this.new C02861(this.$normalValue, this.$callback, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) throws Throwable {
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            NotificationCenterConnectImpl.access$resetUnbindTime(NotificationCenterConnectImpl.this);
            INotificationCenter iNotificationCenter = NotificationCenterConnectImpl.this.iNotificationCenter;
            Object obj3 = this.$normalValue;
            NotificationCenterConnectImpl notificationCenterConnectImpl = NotificationCenterConnectImpl.this;
            Function1 function1 = this.$callback;
            if (iNotificationCenter == null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationCenterConnect-->runWithSuspend: need start");
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(iNotificationCenter);
                this.I$0 = 0;
                this.label = 1;
                Object objAccess$runWithRestart = NotificationCenterConnectImpl.access$runWithRestart(notificationCenterConnectImpl, function1, obj3, this);
                return objAccess$runWithRestart == coroutine_suspended ? coroutine_suspended : objAccess$runWithRestart;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->runAndWaitResult: do it");
            }
            try {
                obj2 = Result.constructor-impl(function1.invoke(iNotificationCenter));
            } catch (Throwable th) {
                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj2);
            if (th2 != null) {
                LogUtil.e(LogUtil.TAG_NOTIFICATION, NotificationCenterConnectImpl.TAG, "runAndWaitResult: ", th2);
            }
            return obj2 instanceof Result.Failure ? obj3 : obj2;
        }
    }

    static {
        final int i = 0;
        workHandler$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnectImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        NotificationCenterConnectImpl.Companion companion = NotificationCenterConnectImpl.Companion;
                        return new OCDHandler(NotificationCenterConnectImpl.workThread);
                    default:
                        return HandlerDispatcherKt.from(NotificationCenterConnectImpl.Companion.getWorkHandler(), "NotificationCenterConnect");
                }
            }
        });
        final int i2 = 1;
        workCoroutineDispatcher$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnectImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        NotificationCenterConnectImpl.Companion companion = NotificationCenterConnectImpl.Companion;
                        return new OCDHandler(NotificationCenterConnectImpl.workThread);
                    default:
                        return HandlerDispatcherKt.from(NotificationCenterConnectImpl.Companion.getWorkHandler(), "NotificationCenterConnect");
                }
            }
        });
    }

    public NotificationCenterConnectImpl(Context context, long j) {
        this.context = context;
        this.disconnectDelayMs = j;
        ManagerConnection managerConnection = new ManagerConnection();
        managerConnection.this$0 = this;
        VarHandle.storeStoreFence();
        this.mManagerConnection = managerConnection;
        NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$4 = new NotificationCenterConnectImpl$doWaitWork$1$4(3);
        notificationCenterConnectImpl$doWaitWork$1$4.this$0 = this;
        VarHandle.storeStoreFence();
        this.binderRunnable = notificationCenterConnectImpl$doWaitWork$1$4;
        NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$5 = new NotificationCenterConnectImpl$doWaitWork$1$4(4);
        notificationCenterConnectImpl$doWaitWork$1$5.this$0 = this;
        VarHandle.storeStoreFence();
        this.unBinderRunnable = notificationCenterConnectImpl$doWaitWork$1$5;
        this.userContext = context;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(context.getUserId(), "init: user:", "NotificationCenterConnect-->", "SystemUi--Notification");
        }
    }

    public static final boolean access$bind(NotificationCenterConnectImpl notificationCenterConnectImpl) {
        notificationCenterConnectImpl.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCenterConnect-->bind:");
        }
        if (notificationCenterConnectImpl.iNotificationCenter != null) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                return true;
            }
            Log.d("SystemUi--Notification", "NotificationCenterConnect-->no need to start service");
            return true;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(NAME_PACKAGE, NAME_CLASS));
        try {
            boolean zBindService = notificationCenterConnectImpl.userContext.bindService(intent, notificationCenterConnectImpl.mManagerConnection, Context.BindServiceFlags.of(68719476737L));
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                return zBindService;
            }
            Log.d("SystemUi--Notification", "NotificationCenterConnect-->".concat("bind result = " + zBindService));
            return zBindService;
        } catch (Exception e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "unable to start service ", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
            return false;
        }
    }

    public static final void access$bindInHandler(NotificationCenterConnectImpl notificationCenterConnectImpl) {
        Companion companion = Companion;
        if (companion.getWorkHandler().hasCallbacks(notificationCenterConnectImpl.binderRunnable)) {
            return;
        }
        companion.getWorkHandler().postDelayed(notificationCenterConnectImpl.binderRunnable, 500L);
    }

    public static final void access$connectNotificationCenter(NotificationCenterConnectImpl notificationCenterConnectImpl, IBinder iBinder) {
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(notificationCenterConnectImpl.mManagerConnection, 0);
            } catch (RemoteException e) {
                OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "connectNotificationCenter RemoteException:", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
            }
        }
    }

    public static final void access$doWaitWork(NotificationCenterConnectImpl notificationCenterConnectImpl) {
        Object obj;
        INotificationCenter iNotificationCenter = notificationCenterConnectImpl.iNotificationCenter;
        if (iNotificationCenter == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->doWaitWork: skip");
                return;
            }
            return;
        }
        if (notificationCenterConnectImpl.waitQueue.isEmpty()) {
            return;
        }
        int i = 0;
        Function1 function1Remove = notificationCenterConnectImpl.waitQueue.remove(0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCenterConnect-->doWaitWork: find one");
        }
        try {
            function1Remove.invoke(iNotificationCenter);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, TAG, "doWaitWork: ", th2);
        }
        if (notificationCenterConnectImpl.waitQueue.isEmpty()) {
            return;
        }
        OCDHandler workHandler = Companion.getWorkHandler();
        NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$4 = new NotificationCenterConnectImpl$doWaitWork$1$4(i);
        notificationCenterConnectImpl$doWaitWork$1$4.this$0 = notificationCenterConnectImpl;
        VarHandle.storeStoreFence();
        workHandler.post(notificationCenterConnectImpl$doWaitWork$1$4);
    }

    public static final void access$resetUnbindTime(NotificationCenterConnectImpl notificationCenterConnectImpl) {
        notificationCenterConnectImpl.getClass();
        Companion companion = Companion;
        companion.getWorkHandler().removeCallbacks(notificationCenterConnectImpl.unBinderRunnable);
        companion.getWorkHandler().postDelayed(notificationCenterConnectImpl.unBinderRunnable, notificationCenterConnectImpl.disconnectDelayMs);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0070  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:60:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$runWithRestart(NotificationCenterConnectImpl notificationCenterConnectImpl, Function1 function1, Object obj, ContinuationImpl continuationImpl) throws Throwable {
        NotificationCenterConnectImpl$runWithRestart$1 notificationCenterConnectImpl$runWithRestart$1;
        NotificationCenterConnectImpl notificationCenterConnectImpl2;
        Ref.ObjectRef objectRef;
        Throwable th;
        Function1 function2;
        Function1 function3;
        Function1 function4;
        notificationCenterConnectImpl.getClass();
        if (continuationImpl instanceof NotificationCenterConnectImpl$runWithRestart$1) {
            notificationCenterConnectImpl$runWithRestart$1 = (NotificationCenterConnectImpl$runWithRestart$1) continuationImpl;
            int i = notificationCenterConnectImpl$runWithRestart$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                notificationCenterConnectImpl$runWithRestart$1.label = i - Integer.MIN_VALUE;
            } else {
                notificationCenterConnectImpl$runWithRestart$1 = new NotificationCenterConnectImpl$runWithRestart$1(notificationCenterConnectImpl, continuationImpl);
            }
        } else {
            notificationCenterConnectImpl$runWithRestart$1 = new NotificationCenterConnectImpl$runWithRestart$1(notificationCenterConnectImpl, continuationImpl);
        }
        Object objWithTimeout = notificationCenterConnectImpl$runWithRestart$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = notificationCenterConnectImpl$runWithRestart$1.label;
        if (i2 == 0) {
            Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(objWithTimeout);
            try {
                long timeOutMs = notificationCenterConnectImpl.getTimeOutMs();
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
                try {
                    NotificationCenterConnectImpl$runWithRestart$2 notificationCenterConnectImpl$runWithRestart$2 = new NotificationCenterConnectImpl$runWithRestart$2(objectRefM, notificationCenterConnectImpl2, obj, function1, null);
                    notificationCenterConnectImpl$runWithRestart$1.L$0 = SpillingKt.nullOutSpilledVariable(function1);
                    notificationCenterConnectImpl$runWithRestart$1.L$1 = obj;
                    notificationCenterConnectImpl$runWithRestart$1.L$2 = objectRefM;
                    notificationCenterConnectImpl$runWithRestart$1.label = 1;
                    objWithTimeout = TimeoutKt.withTimeout(timeOutMs, notificationCenterConnectImpl$runWithRestart$2, notificationCenterConnectImpl$runWithRestart$1);
                    if (objWithTimeout == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRefM;
                    function4 = (Function1) objectRef.element;
                    if (function4 != null) {
                        Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function4));
                    }
                    return objWithTimeout;
                } catch (TimeoutCancellationException unused) {
                    objectRef = objectRefM;
                    obj = obj;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterConnect-->runWithSuspend: time out");
                    }
                    function3 = (Function1) objectRef.element;
                    if (function3 != null) {
                        Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function3));
                    }
                    return obj;
                } catch (Throwable th2) {
                    th = th2;
                    th = th;
                    objectRef = objectRefM;
                    function2 = (Function1) objectRef.element;
                    if (function2 != null) {
                        throw th;
                    }
                    Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function2));
                    throw th;
                }
            } catch (TimeoutCancellationException unused2) {
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
                objectRef = objectRefM;
            } catch (Throwable th3) {
                th = th3;
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            objectRef = (Ref.ObjectRef) notificationCenterConnectImpl$runWithRestart$1.L$2;
            obj = notificationCenterConnectImpl$runWithRestart$1.L$1;
            try {
                ResultKt.throwOnFailure(objWithTimeout);
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
                function4 = (Function1) objectRef.element;
                if (function4 != null) {
                    Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function4));
                }
                return objWithTimeout;
            } catch (TimeoutCancellationException unused3) {
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
                try {
                    if (LogUtil.getNormalDebug()) {
                        Log.d("SystemUi--Notification", "NotificationCenterConnect-->runWithSuspend: time out");
                    }
                    function3 = (Function1) objectRef.element;
                    if (function3 != null) {
                        Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function3));
                    }
                    return obj;
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                notificationCenterConnectImpl2 = notificationCenterConnectImpl;
            }
        }
        function2 = (Function1) objectRef.element;
        if (function2 != null) {
            throw th;
        }
        Boxing.boxBoolean(notificationCenterConnectImpl2.waitQueue.remove(function2));
        throw th;
    }

    public static final void access$unBind(NotificationCenterConnectImpl notificationCenterConnectImpl) {
        INotificationCenter iNotificationCenter = notificationCenterConnectImpl.iNotificationCenter;
        if (iNotificationCenter == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->unBind:skip");
                return;
            }
            return;
        }
        try {
            notificationCenterConnectImpl.disConnectNotificationCenter(iNotificationCenter.asBinder());
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->unBind:");
            }
            notificationCenterConnectImpl.userContext.unbindService(notificationCenterConnectImpl.mManagerConnection);
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.constructor-impl(ResultKt.createFailure(th2));
        }
        notificationCenterConnectImpl.iNotificationCenter = null;
    }

    private final long getTimeOutMs() {
        NotificationFeatureOption.INSTANCE.getClass();
        return NotificationFeatureOption.isNotificationLightOs ? 3000L : 1000L;
    }

    public static void runOnWorkThread(Runnable runnable) {
        OCDHandler workHandler = Companion.getWorkHandler();
        NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$4 = new NotificationCenterConnectImpl$doWaitWork$1$4(5);
        notificationCenterConnectImpl$doWaitWork$1$4.this$0 = runnable;
        VarHandle.storeStoreFence();
        workHandler.post(notificationCenterConnectImpl$doWaitWork$1$4);
    }

    public final void disConnectNotificationCenter(IBinder iBinder) {
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this.mManagerConnection, 0);
            } catch (NoSuchElementException e) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("disConnectNotificationCenter NoSuchElementException:", e.getMessage(), "NotificationCenterConnect-->", "SystemUi--Notification");
                }
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnect
    public void onUserChanged(int i, Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(context.getUserId(), "onUserChanging: newUser =", "NotificationCenterConnect-->", "SystemUi--Notification");
        }
        onUserChanged.2 r0 = new onUserChanged.2();
        r0.this$0 = this;
        r0.$userContext = context;
        VarHandle.storeStoreFence();
        runOnWorkThread(r0);
        SynergyNotificationManager.Companion.getInstance().onUserChanged(i, context);
    }

    @Override // com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnect
    public void runAction(Function1 function1) {
        BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1(function1, null), 2);
    }

    @Override // com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnect, com.oplus.systemui.notification.notificationcenter.NotificationServiceCaller
    public <T> T runAndWaitResult(T t, Function1 function1) {
        if (ThreadUtils.isMainThread()) {
            Log.w(TAG, "runAndWaitResult: you can't call runAndWaitResult in main thread");
        }
        return (T) BuildersKt.runBlocking(Companion.getWorkCoroutineDispatcher(), new C02861(t, function1, null));
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ManagerConnection implements ServiceConnection, IBinder.DeathRecipient {
        public /* synthetic */ NotificationCenterConnectImpl this$0;

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied(IBinder iBinder) {
            NotificationCenterConnectImpl notificationCenterConnectImpl = this.this$0;
            int i = 1;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("binderDied;need unbind = ", "NotificationCenterConnect-->", "SystemUi--Notification", notificationCenterConnectImpl.iNotificationCenter != null);
            }
            NotificationCenterConnectImpl notificationCenterConnectImpl2 = this.this$0;
            Companion companion = NotificationCenterConnectImpl.Companion;
            notificationCenterConnectImpl2.disConnectNotificationCenter(iBinder);
            NotificationCenterConnectImpl notificationCenterConnectImpl3 = this.this$0;
            NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$4 = new NotificationCenterConnectImpl$doWaitWork$1$4(i);
            notificationCenterConnectImpl$doWaitWork$1$4.this$0 = notificationCenterConnectImpl3;
            VarHandle.storeStoreFence();
            NotificationCenterConnectImpl.runOnWorkThread(notificationCenterConnectImpl$doWaitWork$1$4);
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            super.onBindingDied(componentName);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->".concat("onBindingDied: " + componentName));
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->".concat("onServiceConnected:: componentName = " + componentName + ", iBinder = " + iBinder));
            }
            this.this$0.iNotificationCenter = INotificationCenter.Stub.asInterface(iBinder);
            NotificationCenterConnectImpl.access$connectNotificationCenter(this.this$0, iBinder);
            NotificationCenterConnectImpl.access$resetUnbindTime(this.this$0);
            SystemClock.elapsedRealtime();
            NotificationCenterConnectImpl notificationCenterConnectImpl = this.this$0;
            NotificationCenterConnectImpl$doWaitWork$1$4 notificationCenterConnectImpl$doWaitWork$1$4 = new NotificationCenterConnectImpl$doWaitWork$1$4(2);
            notificationCenterConnectImpl$doWaitWork$1$4.this$0 = notificationCenterConnectImpl;
            VarHandle.storeStoreFence();
            NotificationCenterConnectImpl.runOnWorkThread(notificationCenterConnectImpl$doWaitWork$1$4);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->".concat("onServiceDisconnected:: componentName = " + componentName));
            }
            this.this$0.iNotificationCenter = null;
            if (this.this$0.waitQueue.isEmpty()) {
                return;
            }
            NotificationCenterConnectImpl.access$bindInHandler(this.this$0);
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
        }
    }
}
