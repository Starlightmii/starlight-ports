package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.helper.LowBatteryWarningDelayHelper;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveRepositoryImpl implements SuperPowerSaveRepository {
    private static final String ACTION_SUPER_POWER_READY = "oplus.intent.action.superpowersave.ready";
    public static final Companion Companion = new Companion();
    private static final String SYS_PROPERTY_BOOT_COMPLETED = "sys.boot_completed";
    private static final String TAG = "SuperPowerSaveRepositoryImpl";
    private final StateFlow isRelatedTileAvailable;
    public final boolean isSysBootCompleted;
    private final StateFlow superPowerSaveMode;
    private final Flow superPowerSaveModeDeprecated;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static boolean isSuperPowerSaveModel() {
            QSFeatureOption.INSTANCE.getClass();
            return QSFeatureOption.isSuperPowerModeSupport && SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Executor $backgroundExecutor;
        final /* synthetic */ BroadcastDispatcher $broadcastDispatcher;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BroadcastDispatcher broadcastDispatcher, Executor executor, Continuation continuation) {
            super(2, continuation);
            this.$broadcastDispatcher = broadcastDispatcher;
            this.$backgroundExecutor = executor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = SuperPowerSaveRepositoryImpl.this.new AnonymousClass1(this.$broadcastDispatcher, this.$backgroundExecutor, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0, types: [android.content.BroadcastReceiver, com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1, java.lang.Object] */
        public final Object invokeSuspend(Object obj) {
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ?? r6 = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$superPowerReadyReceiver$1
                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        if (LowBatteryWarningDelayHelper.ACTION_SUPER_POWER_READY_NOTIFY.equals(intent.getAction())) {
                            LogUtil.i$default(LogUtil.TAG_QS, "SuperPowerSaveRepositoryImpl", "onReceive: super power ready", null, 8);
                            producerScope.trySend-JP2dKIU(Boolean.TRUE);
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter("oplus.intent.action.superpowersave.ready");
                LogUtil.i$default(LogUtil.TAG_QS, SuperPowerSaveRepositoryImpl.TAG, "registerReceiver: isSysBootCompleted= " + SuperPowerSaveRepositoryImpl.this.isSysBootCompleted + ",   " + ((Object) r6), null, 8);
                if (!SuperPowerSaveRepositoryImpl.this.isSysBootCompleted) {
                    BroadcastDispatcher.registerReceiver$default(this.$broadcastDispatcher, (BroadcastReceiver) r6, intentFilter, this.$backgroundExecutor, (UserHandle) null, 2, 32);
                }
                BroadcastDispatcher broadcastDispatcher = this.$broadcastDispatcher;
                SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0 superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0 = new SuperPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0();
                superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0.f$0 = r6;
                superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0.f$1 = broadcastDispatcher;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable((Object) r6);
                this.L$2 = SpillingKt.nullOutSpilledVariable(intentFilter);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, superPowerSaveRepositoryImpl$isRelatedTileAvailable$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public SuperPowerSaveRepositoryImpl(BroadcastDispatcher broadcastDispatcher, CoroutineContext coroutineContext, Executor executor, CoroutineScope coroutineScope) {
        Companion.getClass();
        boolean zEquals = "1".equals(SystemProperties.get(SYS_PROPERTY_BOOT_COMPLETED));
        this.isSysBootCompleted = zEquals;
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1(2, null));
        SuperPowerSaveRepositoryImpl$special$$inlined$map$1 superPowerSaveRepositoryImpl$special$$inlined$map$1 = new SuperPowerSaveRepositoryImpl$special$$inlined$map$1();
        superPowerSaveRepositoryImpl$special$$inlined$map$1.$this_unsafeTransform$inlined = flowConflatedCallbackFlow;
        VarHandle.storeStoreFence();
        this.superPowerSaveModeDeprecated = FlowKt.flowOn(FlowKt.onEach(FlowKt.distinctUntilChanged(superPowerSaveRepositoryImpl$special$$inlined$map$1), new SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$3(2, null)), coroutineContext);
        Flow superPowerSaveModeDeprecated = getSuperPowerSaveModeDeprecated();
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.superPowerSaveMode = FlowKt.stateIn(superPowerSaveModeDeprecated, coroutineScope, companion.getEagerly(), Boolean.valueOf(Companion.isSuperPowerSaveModel()));
        this.isRelatedTileAvailable = FlowKt.stateIn(FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(broadcastDispatcher, executor, null)), coroutineContext), coroutineScope, companion.getEagerly(), Boolean.valueOf(zEquals));
    }

    @Deprecated
    public static /* synthetic */ void getSuperPowerSaveModeDeprecated$annotations() {
    }

    public /* bridge */ /* synthetic */ boolean getInSuperPowerSaveMode() {
        return super.getInSuperPowerSaveMode();
    }

    public StateFlow getSuperPowerSaveMode() {
        return this.superPowerSaveMode;
    }

    public Flow getSuperPowerSaveModeDeprecated() {
        return this.superPowerSaveModeDeprecated;
    }

    public StateFlow isRelatedTileAvailable() {
        return this.isRelatedTileAvailable;
    }
}
