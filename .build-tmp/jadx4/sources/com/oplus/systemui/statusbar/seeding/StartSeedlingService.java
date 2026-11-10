package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.settings.UserTracker;
import com.oplus.dispatch.OCDHandler;
import com.oplus.systemui.media.seedling.MediaSeedling;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSeedlingService {
    private static final String ACTION_SEEDLING_SERVICE = "com.oplus.seedlingservice.action.SEEDLING_SERVICE";
    public static final Companion Companion = new Companion();
    private static final String KEY_GUARD_OCCLUDE_STATE = "isKeyguardOccluded";
    private static final String KEY_GUARD_SHOWING_STATE = "isKeyguardShowing";
    private static final String PACKAGE_NAME = "com.android.systemui";
    public static final String SEEDLING_EVENT = "seedling_event";
    private static final String TAG = "StartSeedlingService";
    private final Lazy clientMessenger$delegate;
    private StartSeedlingService$conn$1 conn;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback;
    private Context lastContext;
    private List<Bundle> mData;
    private Messenger serverMessenger;
    private final StartSeedlingService$userCallback$1 userCallback;
    private Map<String, Function1> serverEventMap = new LinkedHashMap();
    private final StartSeedlingService$workHandler$1 workHandler = new OCDHandler() { // from class: com.oplus.systemui.statusbar.seeding.StartSeedlingService$workHandler$1
        {
            super("StartSeedlingService");
        }

        public final void handleMessage(Message message) {
            Function1 function1;
            if (message.what == 2) {
                boolean zAreEqual = Intrinsics.areEqual(message.getData().getString("flashlightEvent"), "flashlightEvent");
                boolean zAreEqual2 = Intrinsics.areEqual(message.getData().getString(MediaSeedling.MEDIA_EVENT), MediaSeedling.MEDIA_EVENT);
                if (zAreEqual) {
                    Function1 function2 = (Function1) this.this$0.serverEventMap.get("flashlightEvent");
                    if (function2 != null) {
                        function2.invoke(message);
                        return;
                    }
                    return;
                }
                if (!zAreEqual2 || (function1 = (Function1) this.this$0.serverEventMap.get(MediaSeedling.MEDIA_EVENT)) == null) {
                    return;
                }
                function1.invoke(message);
            }
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.StartSeedlingService$sendMessage$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Bundle $data;
        public /* synthetic */ boolean $isKeyguardChange;
        public /* synthetic */ StartSeedlingService this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Object obj;
            Unit unit;
            Message messageObtain = Message.obtain();
            if (this.$isKeyguardChange) {
                messageObtain.what = 3;
            } else {
                messageObtain.what = 1;
            }
            messageObtain.setData(this.$data);
            messageObtain.replyTo = this.this$0.getClientMessenger();
            try {
                Messenger messenger = this.this$0.serverMessenger;
                if (messenger != null) {
                    messenger.send(messageObtain);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                obj = Result.constructor-impl(unit);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (obj instanceof Result.Failure) {
                LogUtil.e(LogUtil.TAG_COMMON, StartSeedlingService.TAG, "send message error", Result.exceptionOrNull-impl(obj));
            }
        }
    }

    public static Messenger $r8$lambda$exkaPk76R5jEdkagbJyYr5AFgjs(StartSeedlingService startSeedlingService) {
        return new Messenger((Handler) startSeedlingService.workHandler);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.oplus.systemui.statusbar.seeding.StartSeedlingService$workHandler$1] */
    public StartSeedlingService(UserTracker userTracker, Executor executor, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        StartSeedlingService$$ExternalSyntheticLambda0 startSeedlingService$$ExternalSyntheticLambda0 = new StartSeedlingService$$ExternalSyntheticLambda0();
        startSeedlingService$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.clientMessenger$delegate = LazyKt__LazyJVMKt.lazy(startSeedlingService$$ExternalSyntheticLambda0);
        this.mData = new ArrayList();
        StartSeedlingService$keyguardUpdateMonitorCallback$1 startSeedlingService$keyguardUpdateMonitorCallback$1 = new StartSeedlingService$keyguardUpdateMonitorCallback$1();
        startSeedlingService$keyguardUpdateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback = startSeedlingService$keyguardUpdateMonitorCallback$1;
        StartSeedlingService$conn$1 startSeedlingService$conn$1 = new StartSeedlingService$conn$1();
        startSeedlingService$conn$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.conn = startSeedlingService$conn$1;
        StartSeedlingService$userCallback$1 startSeedlingService$userCallback$1 = new StartSeedlingService$userCallback$1();
        startSeedlingService$userCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userCallback = startSeedlingService$userCallback$1;
        userTracker.addCallback(startSeedlingService$userCallback$1, executor);
    }

    public static void bindService$default(Context context, StartSeedlingService startSeedlingService) {
        StartSeedlingService$workHandler$1 startSeedlingService$workHandler$1 = startSeedlingService.workHandler;
        StartSeedlingService$bindService$1 startSeedlingService$bindService$1 = new StartSeedlingService$bindService$1(0);
        startSeedlingService$bindService$1.$context = context;
        startSeedlingService$bindService$1.this$0 = startSeedlingService;
        VarHandle.storeStoreFence();
        startSeedlingService$workHandler$1.post(startSeedlingService$bindService$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Messenger getClientMessenger() {
        return (Messenger) this.clientMessenger$delegate.getValue();
    }

    public final void registerServerEventListener(String str, Function1 function1) {
        this.serverEventMap.put(str, function1);
    }

    public final void sendDestroySeedling(Bundle bundle) {
        StartSeedlingService$workHandler$1 startSeedlingService$workHandler$1 = this.workHandler;
        StartSeedlingService$bindService$1 startSeedlingService$bindService$1 = new StartSeedlingService$bindService$1(1);
        startSeedlingService$bindService$1.$context = bundle;
        startSeedlingService$bindService$1.this$0 = this;
        VarHandle.storeStoreFence();
        startSeedlingService$workHandler$1.post(startSeedlingService$bindService$1);
    }

    public final void sendMessage(Bundle bundle, boolean z) {
        StartSeedlingService$workHandler$1 startSeedlingService$workHandler$1 = this.workHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$isKeyguardChange = z;
        anonymousClass1.$data = bundle;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        startSeedlingService$workHandler$1.post(anonymousClass1);
    }

    public final void updateData(Context context, String str, Bundle bundle) {
        bundle.putString("seedling_event", str);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StartSeedlingService-->".concat("updateData: serverMessenger=" + this.serverMessenger));
        }
        if (this.serverMessenger != null) {
            sendMessage(bundle, false);
            return;
        }
        this.mData.add(bundle);
        Context context2 = this.lastContext;
        if (context2 != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "StartSeedlingService-->".concat("updateData: lastContext=" + this.lastContext + " mData= " + this.mData));
            }
            bindService$default(context2, this);
            return;
        }
        UserManager userManager = (UserManager) context.getSystemService("user");
        UserHandle userHandle = userManager.isSystemUser() ? UserHandle.SYSTEM : UserHandle.CURRENT;
        userHandle.getClass();
        Context contextCreateContextAsUser = context.createContextAsUser(userHandle, 1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StartSeedlingService-->".concat("updateData: isSystemUser=" + userManager.isSystemUser() + ", userContext=" + contextCreateContextAsUser));
        }
        bindService$default(contextCreateContextAsUser, this);
        this.lastContext = contextCreateContextAsUser;
    }
}
