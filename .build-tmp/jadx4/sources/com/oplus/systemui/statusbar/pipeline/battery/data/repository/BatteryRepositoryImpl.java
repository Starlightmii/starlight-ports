package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.content.Context;
import android.provider.Settings;
import com.android.systemui.Dumpable;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.statusbar.observer.BatteryStyleObserver;
import com.oplus.systemui.statusbar.observer.PowerSavingModeObserver;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryStyleModel;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel;
import com.oplus.systemui.utils.OplusThemeController;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
public final class BatteryRepositoryImpl implements BatteryRepository, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String SPEED_MODE_KEY = "speed_charge_state";
    private static final String TAG = "BatteryRepository";
    private final StateFlow batteryLevel;
    private final StateFlow batteryStyle;
    private final Executor bgExecutor;
    private final StateFlow chargeState;
    private final ConfigurationController configurationController;
    private final Context context;
    private final OplusFlavorManager flavorManager;
    private final StateFlow isSavingMode;
    private final OplusThemeController oplusThemeController;
    private final StateFlow speedModeTurnedOn;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepositoryImpl$isSavingMode$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = BatteryRepositoryImpl.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BatteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1 batteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1 = new BatteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1();
                batteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1.$$this$conflatedCallbackFlow = producerScope;
                VarHandle.storeStoreFence();
                PowerSavingModeObserver.Companion companion = PowerSavingModeObserver.Companion;
                companion.getInstance().register(BatteryRepositoryImpl.this.context, false);
                companion.getInstance().addListener(batteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1);
                producerScope.trySend-JP2dKIU(Boxing.boxBoolean(companion.getInstance().batterySavingMode));
                BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 = new BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0(2);
                batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0.f$0 = batteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(batteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, batteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public BatteryRepositoryImpl(Context context, CoroutineScope coroutineScope, UserTracker userTracker, OplusFlavorManager oplusFlavorManager, ConfigurationController configurationController, OplusThemeController oplusThemeController, Executor executor, DumpManager dumpManager) {
        this.context = context;
        this.userTracker = userTracker;
        this.flavorManager = oplusFlavorManager;
        this.configurationController = configurationController;
        this.oplusThemeController = oplusThemeController;
        this.bgExecutor = executor;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new BatteryRepositoryImpl$batteryLevel$1(this, null)));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.batteryLevel = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
        this.chargeState = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new BatteryRepositoryImpl$chargeState$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new ChargeStateModel());
        Flow flowDistinctUntilChanged2 = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new BatteryRepositoryImpl$batteryStyle$1(this, coroutineScope, null)));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        FlavorTwoFeatureOption.INSTANCE.getClass();
        this.batteryStyle = FlowKt.stateIn(flowDistinctUntilChanged2, coroutineScope, sharingStartedWhileSubscribed$default, new BatteryStyleModel(FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 3 : 1));
        Flow flowDistinctUntilChanged3 = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(null)));
        SharingStarted sharingStartedWhileSubscribed$default2 = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.isSavingMode = FlowKt.stateIn(flowDistinctUntilChanged3, coroutineScope, sharingStartedWhileSubscribed$default2, bool);
        this.speedModeTurnedOn = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new BatteryRepositoryImpl$speedModeTurnedOn$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        dumpManager.registerCriticalDumpable(this);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("BatteryRepository:");
        printWriter.println("BatteryController state:");
        printWriter.println("  batteryLevel=" + getBatteryLevel().getValue());
        printWriter.println("  chargeState=" + getChargeState().getValue());
        printWriter.println("  batteryStyle=" + getBatteryStyle().getValue());
        printWriter.println("  isSavingMode=" + isSavingMode().getValue());
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("  speedModeTurnedOn=", getSpeedModeTurnedOn().getValue(), printWriter);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository
    public StateFlow getBatteryLevel() {
        return this.batteryLevel;
    }

    public final int getBatteryStyle(int i) {
        return Settings.System.getIntForUser(this.context.getContentResolver(), BatteryStyleObserver.Companion.getDISPLAY_BATTERY_STYLE(), this.context.getResources().getInteger(2131427477), i);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository
    public StateFlow getChargeState() {
        return this.chargeState;
    }

    public final int getDisplayPowerPercent(int i, int i2) {
        int intForUser = Settings.System.getIntForUser(this.context.getContentResolver(), "display_power_percent", 0, i);
        if (intForUser != 1 || i2 == 1) {
            return intForUser;
        }
        Settings.System.putIntForUser(this.context.getContentResolver(), "display_power_percent", 2, i);
        return 2;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository
    public StateFlow getSpeedModeTurnedOn() {
        return this.speedModeTurnedOn;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository
    public StateFlow isSavingMode() {
        return this.isSavingMode;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository
    public StateFlow getBatteryStyle() {
        return this.batteryStyle;
    }
}
