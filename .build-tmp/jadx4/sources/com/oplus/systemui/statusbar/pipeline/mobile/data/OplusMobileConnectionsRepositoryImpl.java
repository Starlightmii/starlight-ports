package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.R;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.OplusOSTelephonyManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SystemSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.observer.DualSimStateObserver;
import com.oplus.systemui.statusbar.phone.policy.OplusPolicyObserver;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.statusbar.util.NetworkServiceProxy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionsRepositoryImpl implements OplusMobileConnectionsRepository, Dumpable {
    private static final String ACTION_SERVICE_STATE = "android.intent.action.SERVICE_STATE";
    public static final Companion Companion = new Companion();
    private static final String KEY_DATA_CHANNEL_CONTROL_CFG = "force_data_channel_control";
    private static final String OPLUS_SIM_VSIM_TYPE = "OPLUS_SIM_VSIM_TYPE";
    private static final String TAG = "OplusMobileConnectionsRepository";
    private final CoroutineDispatcher bgDispatcher;
    private final Context context;
    private final StateFlow is5GNewCall;
    private final StateFlow isCMCCDataPlan;
    private final StateFlow isDualPsSim;
    private final StateFlow isSatelliteForbid;
    private final MobileInputLogger logger;
    private final MobileConnectionsRepository mobileConnectionsRepo;
    private final TableLogBuffer mobileSummaryLog;
    private final OplusMobileConnectionRepositoryImpl.Factory oplusMobileConnectionRepositoryFactory;
    private final StateFlow simState;
    private final String slotMobile;
    private final TelephonyManager telephonyManager;
    private ConcurrentHashMap<Integer, OplusMobileConnectionRepository> subIdRepositoryCache = new ConcurrentHashMap<>();
    private final ArrayList<String> blockListIcons = new ArrayList<>();

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01561 implements FlowCollector {
            public /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C01571 implements Predicate {
                public /* synthetic */ List $modelList;
                public /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean z;
                    Integer num = (Integer) ((Map.Entry) obj).getKey();
                    OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = this.this$0;
                    int iIntValue = num.intValue();
                    List list = this.$modelList;
                    oplusMobileConnectionsRepositoryImpl.getClass();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        if (((SubscriptionModel) it.next()).getSubscriptionId() == iIntValue) {
                            z = true;
                            return !z;
                        }
                    }
                    z = false;
                    return !z;
                }
            }

            public final Object emit(Object obj, Continuation continuation) {
                List list = (List) obj;
                OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = this.this$0;
                Set setEntrySet = oplusMobileConnectionsRepositoryImpl.subIdRepositoryCache.entrySet();
                C01571 c01571 = new C01571();
                c01571.this$0 = oplusMobileConnectionsRepositoryImpl;
                c01571.$modelList = list;
                VarHandle.storeStoreFence();
                setEntrySet.removeIf(c01571);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusMobileConnectionsRepository-->".concat("filter " + list + ", " + oplusMobileConnectionsRepositoryImpl.subIdRepositoryCache));
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusMobileConnectionsRepositoryImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow subscriptions = OplusMobileConnectionsRepositoryImpl.this.mobileConnectionsRepo.getSubscriptions();
                OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = OplusMobileConnectionsRepositoryImpl.this;
                C01561 c01561 = new C01561();
                c01561.this$0 = oplusMobileConnectionsRepositoryImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (subscriptions.collect(c01561, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$getRepoForSubId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03001 implements Function {
        public /* synthetic */ int $subId;
        public /* synthetic */ Flow $subscriptionModel;
        public /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return OplusMobileConnectionsRepositoryImpl.access$createRepositoryForSubId(this.this$0, this.$subId, this.$subscriptionModel);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$is5GNewCall$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03011 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C03011(Continuation continuation) {
            super(2, continuation);
        }

        public static final boolean invokeSuspend$isNewCall(OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl) {
            boolean z = Settings.System.getIntForUser(oplusMobileConnectionsRepositoryImpl.context.getContentResolver(), OplusMobileConnectionsRepositoryImpl.KEY_DATA_CHANNEL_CONTROL_CFG, 0, -2) == 1;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionsRepositoryImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("int5GNewCall: ", z), null, 8);
            return z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C03011 c03011 = OplusMobileConnectionsRepositoryImpl.this.new C03011(continuation);
            c03011.L$0 = obj;
            return c03011;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final Handler handler = new Handler(Looper.getMainLooper());
                final OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = OplusMobileConnectionsRepositoryImpl.this;
                ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$newCallObserver$1
                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z, Uri uri) {
                        onChange(z);
                        producerScope.trySend-JP2dKIU(Boolean.valueOf(OplusMobileConnectionsRepositoryImpl.C03011.invokeSuspend$isNewCall(oplusMobileConnectionsRepositoryImpl)));
                    }
                };
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionsRepositoryImpl.TAG, "isNewCallObserver registerObserver", null, 8);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(OplusMobileConnectionsRepositoryImpl.KEY_DATA_CHANNEL_CONTROL_CFG), contentObserver, -1);
                producerScope.trySend-JP2dKIU(Boxing.boxBoolean(invokeSuspend$isNewCall(OplusMobileConnectionsRepositoryImpl.this)));
                OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0 oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0 = new OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0(0);
                oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0.f$0 = contentObserver;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(contentObserver);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$isDualPsSim$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03021 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C03021(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C03021 c03021 = OplusMobileConnectionsRepositoryImpl.this.new C03021(continuation);
            c03021.L$0 = obj;
            return c03021;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1 oplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1 = new OplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1();
                oplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1.$$this$conflatedCallbackFlow = producerScope;
                VarHandle.storeStoreFence();
                DualSimStateObserver.Companion companion = DualSimStateObserver.Companion;
                companion.getInstance().register(OplusMobileConnectionsRepositoryImpl.this.context, false);
                companion.getInstance().addListener(oplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionsRepositoryImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" init:", companion.getInstance().isDualSimOpen()), null, 8);
                producerScope.trySend-JP2dKIU(Boxing.boxBoolean(companion.getInstance().isDualSimOpen()));
                OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0 oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0 = new OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0(i2);
                oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0.f$0 = oplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(oplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, oplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03031 extends SuspendLambda implements Function2 {
        final /* synthetic */ OplusPolicyObserver $oplusPolicyObserver;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ OplusMobileConnectionsRepositoryImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03031(OplusPolicyObserver oplusPolicyObserver, OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl, Continuation continuation) {
            super(2, continuation);
            this.$oplusPolicyObserver = oplusPolicyObserver;
            this.this$0 = oplusMobileConnectionsRepositoryImpl;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C03031 c03031 = new C03031(this.$oplusPolicyObserver, this.this$0, continuation);
            c03031.L$0 = obj;
            return c03031;
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
                OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl = this.this$0;
                OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1 oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1 = new OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1();
                oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1.this$0 = oplusMobileConnectionsRepositoryImpl;
                oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1.$$this$conflatedCallbackFlow = producerScope;
                VarHandle.storeStoreFence();
                this.$oplusPolicyObserver.addCallback(oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1);
                producerScope.trySend-JP2dKIU(Boxing.boxBoolean(this.$oplusPolicyObserver.getBlockList().contains(this.this$0.slotMobile)));
                OplusPolicyObserver oplusPolicyObserver = this.$oplusPolicyObserver;
                OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0 oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0 = new OplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0();
                oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0.f$0 = oplusPolicyObserver;
                oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0.f$1 = oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$callback$1);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, oplusMobileConnectionsRepositoryImpl$isSatelliteForbid$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    public OplusMobileConnectionsRepositoryImpl(Context context, CoroutineScope coroutineScope, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher, OplusMobileConnectionRepositoryImpl.Factory factory, MobileConnectionsRepository mobileConnectionsRepository, TelephonyManager telephonyManager, OplusPolicyObserver oplusPolicyObserver, MobileInputLogger mobileInputLogger, TableLogBuffer tableLogBuffer, SystemSettings systemSettings, SelectedUserInteractor selectedUserInteractor, DumpManager dumpManager) {
        MutableStateFlow mutableStateFlowStateIn;
        this.context = context;
        this.bgDispatcher = coroutineDispatcher;
        this.oplusMobileConnectionRepositoryFactory = factory;
        this.mobileConnectionsRepo = mobileConnectionsRepository;
        this.telephonyManager = telephonyManager;
        this.logger = mobileInputLogger;
        this.mobileSummaryLog = tableLogBuffer;
        this.slotMobile = context.getString(R.string.write_fail_reason_cancelled);
        dumpManager.registerDumpable(this);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        factory.initImsListener();
        Flow flowCatch = FlowKt.catch(FlowConflatedKt.conflatedCallbackFlow(new OplusMobileConnectionsRepositoryImpl$simState$1(broadcastDispatcher, this, null)), new OplusMobileConnectionsRepositoryImpl$simState$2(3, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.simState = FlowKt.stateIn(flowCatch, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new SimStateModel());
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isDualSimConcurrently()) {
            mutableStateFlowStateIn = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new C03021(null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), Boolean.valueOf(DualSimStateObserver.Companion.getInstance().isDualSimOpen()));
        } else {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "isDualSimConcurrently not support", null, 8);
            mutableStateFlowStateIn = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        }
        this.isDualPsSim = mutableStateFlowStateIn;
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new C03031(oplusPolicyObserver, this, null)));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.isSatelliteForbid = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        this.is5GNewCall = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new C03011(null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.isCMCCDataPlan = FlowKt.stateIn(FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new OplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1(null, systemSettings)), systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
    }

    public static final OplusMobileConnectionRepositoryImpl access$createRepositoryForSubId(OplusMobileConnectionsRepositoryImpl oplusMobileConnectionsRepositoryImpl, int i, Flow flow) {
        return oplusMobileConnectionsRepositoryImpl.oplusMobileConnectionRepositoryFactory.build(i, flow);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("simState: ", getSimState().getValue(), printWriter);
        printWriter.println("subIdRepositoryCache: " + this.subIdRepositoryCache);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public OplusMobileConnectionRepository getRepoForSubId(int i, Flow flow) {
        ConcurrentHashMap<Integer, OplusMobileConnectionRepository> concurrentHashMap = this.subIdRepositoryCache;
        Integer numValueOf = Integer.valueOf(i);
        C03001 c03001 = new C03001();
        c03001.this$0 = this;
        c03001.$subId = i;
        c03001.$subscriptionModel = flow;
        VarHandle.storeStoreFence();
        return concurrentHashMap.computeIfAbsent(numValueOf, c03001);
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public StateFlow getSimState() {
        return this.simState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public StateFlow is5GNewCall() {
        return this.is5GNewCall;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public StateFlow isCMCCDataPlan() {
        return this.isCMCCDataPlan;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public StateFlow isDualPsSim() {
        return this.isDualPsSim;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepository
    public StateFlow isSatelliteForbid() {
        return this.isSatelliteForbid;
    }

    public final void updateSimState(SimStateModel simStateModel, int i) {
        if (i >= 0 && i < 2) {
            simStateModel.getSimInserted()[i] = OplusOSTelephonyManager.getDefault(this.context).hasIccCardGemini(i);
            simStateModel.getSimForbidden()[i] = NetworkServiceProxy.isSlotForbidden(i, this.context);
            return;
        }
        simStateModel.getSimInserted()[0] = OplusOSTelephonyManager.getDefault(this.context).hasIccCardGemini(0);
        simStateModel.getSimInserted()[1] = OplusOSTelephonyManager.getDefault(this.context).hasIccCardGemini(1);
        simStateModel.getSimForbidden()[0] = NetworkServiceProxy.isSlotForbidden(0, this.context);
        simStateModel.getSimForbidden()[1] = NetworkServiceProxy.isSlotForbidden(1, this.context);
        int simCount = this.telephonyManager.getSimCount();
        int i2 = simCount <= 2 ? simCount : 2;
        for (int i3 = 0; i3 < i2; i3++) {
            int simState = this.telephonyManager.getSimState(i3);
            if (simState == 5 || simState == 10) {
                simStateModel.getSimLoaded()[i3] = true;
                simStateModel.getSimStateIMSI()[i3] = true;
            }
        }
    }
}
