package com.oplus.systemui.separate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.v4.content.FileProvider$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginLifecycleManager;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.qs.IQSPanelPluginScanner;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelPluginScanner implements PluginListener, IQSPanelPluginScanner, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusPanelPluginScanner";
    private final MutableStateFlow _dateFlow;
    private final Map<String, QsPanelPlugin> allPanelPlugins = new LinkedHashMap();
    private final BroadcastDispatcher broadcastDispatcher;
    private final StateFlow dataFlow;
    private final DumpManager dumpManager;
    public boolean isPluginListenerRegistered;
    private final Executor mainExecutor;
    private final IObserverListener observerListener;
    private final PluginManager pluginManager;
    private final OplusPanelPluginScanner$receiver$1 receiver;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final UserManager userManager;
    private final UserTracker userTracker;
    private final UserTracker.Callback userTrackerCallback;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelPluginScanner$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusPanelPluginScanner$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01031 implements FlowCollector {
            public /* synthetic */ OplusPanelPluginScanner this$0;

            public final Object emit(Object obj, Continuation continuation) {
                LogUtil.i$default(LogUtil.TAG_QS, OplusPanelPluginScanner.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("separate mode change: ", ((Boolean) obj).booleanValue()), null, 8);
                this.this$0.updatePluginState$1();
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusPanelPluginScanner.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow enableSeparateNotificationAndQS = OplusPanelPluginScanner.this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS();
                OplusPanelPluginScanner oplusPanelPluginScanner = OplusPanelPluginScanner.this;
                C01031 c01031 = new C01031();
                c01031.this$0 = oplusPanelPluginScanner;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (enableSeparateNotificationAndQS.collect(c01031, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.separate.OplusPanelPluginScanner$receiver$1] */
    public OplusPanelPluginScanner(DumpManager dumpManager, UserManager userManager, PluginManager pluginManager, BroadcastDispatcher broadcastDispatcher, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, CoroutineScope coroutineScope, UserTracker userTracker, Executor executor) {
        this.dumpManager = dumpManager;
        this.userManager = userManager;
        this.pluginManager = pluginManager;
        this.broadcastDispatcher = broadcastDispatcher;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.userTracker = userTracker;
        this.mainExecutor = executor;
        ?? r3 = new BroadcastReceiver() { // from class: com.oplus.systemui.separate.OplusPanelPluginScanner$receiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                LogUtil.i$default(LogUtil.TAG_DEVICE, "OplusPanelPluginScanner", FileProvider$.ExternalSyntheticOutline0.m("receive action: ", intent != null ? intent.getAction() : null), null, 8);
                if ("android.intent.action.USER_UNLOCKED".equals(intent != null ? intent.getAction() : null)) {
                    this.this$0.updatePluginState$1();
                }
            }
        };
        this.receiver = r3;
        OplusPanelPluginScanner$userTrackerCallback$1 oplusPanelPluginScanner$userTrackerCallback$1 = new OplusPanelPluginScanner$userTrackerCallback$1();
        oplusPanelPluginScanner$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = oplusPanelPluginScanner$userTrackerCallback$1;
        OplusPanelPluginScanner$observerListener$1 oplusPanelPluginScanner$observerListener$1 = new OplusPanelPluginScanner$observerListener$1();
        oplusPanelPluginScanner$observerListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.observerListener = oplusPanelPluginScanner$observerListener$1;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt__CollectionsKt.emptyList());
        this._dateFlow = MutableStateFlow;
        this.dataFlow = MutableStateFlow;
        boolean zIsUserUnlocked = userManager.isUserUnlocked();
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isUserUnlocked = ", zIsUserUnlocked), null, 8);
        if (zIsUserUnlocked) {
            updatePluginState$1();
        } else {
            BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, (BroadcastReceiver) r3, new IntentFilter("android.intent.action.USER_UNLOCKED"), (Executor) null, UserHandle.ALL, 0, 48);
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSuperPowerModeSupport) {
            SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(oplusPanelPluginScanner$observerListener$1);
        }
        userTracker.addCallback(oplusPanelPluginScanner$userTrackerCallback$1, executor);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    public final void addPluginListener$1() {
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("addPluginListener isAdd: ", this.isPluginListenerRegistered), null, 8);
        if (this.isPluginListenerRegistered) {
            return;
        }
        this.pluginManager.addPluginListener(this, QsPanelPlugin.class, true);
        this.dumpManager.registerDumpable(TAG, this);
        this.isPluginListenerRegistered = true;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
    }

    public StateFlow getDataFlow() {
        return this.dataFlow;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x002a  */
    public final boolean isSupportMyDevicePlugin$1() {
        boolean z;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice()) {
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel() || !((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue()) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isSupportMyDevicePlugin = ", z), null, 8);
        return z;
    }

    public void onPluginConnected(QsPanelPlugin qsPanelPlugin, Context context) {
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, "[onPluginConnected] spec=" + qsPanelPlugin.getSpec() + " plugin:" + qsPanelPlugin, null, 8);
        this.allPanelPlugins.put(qsPanelPlugin.getSpec(), qsPanelPlugin);
        this._dateFlow.setValue(CollectionsKt___CollectionsKt.toList(this.allPanelPlugins.values()));
    }

    public void onPluginUnloaded(QsPanelPlugin qsPanelPlugin, PluginLifecycleManager<QsPanelPlugin> pluginLifecycleManager) {
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, "[onPluginUnloaded] spec=" + qsPanelPlugin.getSpec() + " plugin:" + qsPanelPlugin, null, 8);
        super.onPluginUnloaded(qsPanelPlugin, pluginLifecycleManager);
        if (this.allPanelPlugins.remove(qsPanelPlugin.getSpec()) != null) {
            this._dateFlow.setValue(CollectionsKt___CollectionsKt.toList(this.allPanelPlugins.values()));
        }
    }

    public final void removePluginListener() {
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("removePluginListener isAdd: ", this.isPluginListenerRegistered), null, 8);
        if (this.isPluginListenerRegistered) {
            this.pluginManager.removePluginListener(this);
            this.dumpManager.unregisterDumpable(TAG);
            this.isPluginListenerRegistered = false;
        }
    }

    public final void updatePluginState$1() {
        LogUtil.i$default(LogUtil.TAG_DEVICE, TAG, "updatePluginState", null, 8);
        if (isSupportMyDevicePlugin$1()) {
            addPluginListener$1();
        } else {
            removePluginListener();
        }
    }

    public final /* bridge */ /* synthetic */ void onPluginUnloaded(Plugin plugin, PluginLifecycleManager pluginLifecycleManager) {
        onPluginUnloaded((QsPanelPlugin) plugin, (PluginLifecycleManager<QsPanelPlugin>) pluginLifecycleManager);
    }
}
