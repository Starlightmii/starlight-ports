package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.content.Context;
import android.os.DeadSystemException;
import android.os.DeadSystemRuntimeException;
import android.telephony.TelephonyManager;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.FullMobileConnectionRepository;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.pipeline.mobile.data.ims.ImsListenerProxy;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.CapabilityTypeModel;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionRepositoryImpl implements OplusMobileConnectionRepository {
    public static final Companion Companion = new Companion();
    private static final String KEY_5G_CELL_COUNT_SLOT = "settings_oplus_5ga_carrier_nums_slot_";
    private static final String KEY_VONR_SWITCH_CHANGE_SLOT = "vonr_switch_change_slot_";
    public static final String TAG = "OplusMobileConnectionRepository";
    private final StateFlow capabilityType;
    private final Context context;
    private final ImsListenerProxy imsListenerProxy;
    private final StateFlow isVoNrEnabled;
    private final String logTag;
    private final TableLogBuffer mobileConnectionLogger;
    private final StateFlow oplusFeatureChanged;
    private final StateFlow s5GCellCount;
    public final int subId;
    private final TelephonyManager telephonyManager;
    private final StateFlow voNrSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Factory {
        private final CoroutineDispatcher bgDispatcher;
        private final Context context;
        private final ImsListenerProxy imsListenerProxy;
        private final TableLogBufferFactory logFactory;
        private final CoroutineScope scope;
        private final TelephonyManager telephonyManager;

        public Factory(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, TelephonyManager telephonyManager, ImsListenerProxy imsListenerProxy, TableLogBufferFactory tableLogBufferFactory) {
            this.context = context;
            this.scope = coroutineScope;
            this.bgDispatcher = coroutineDispatcher;
            this.telephonyManager = telephonyManager;
            this.imsListenerProxy = imsListenerProxy;
            this.logFactory = tableLogBufferFactory;
        }

        public final OplusMobileConnectionRepositoryImpl build(int i, Flow flow) {
            return new OplusMobileConnectionRepositoryImpl(this.context, i, this.logFactory.getOrCreate(FullMobileConnectionRepository.Factory.Companion.tableBufferLogName(i), 100), this.scope, this.bgDispatcher, this.telephonyManager, this.imsListenerProxy, flow);
        }

        public final void initImsListener() {
            this.imsListenerProxy.init();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$isVoNrEnabled$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function5 {
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(5, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            ((Boolean) obj).booleanValue();
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            AnonymousClass1 anonymousClass1 = OplusMobileConnectionRepositoryImpl.this.new AnonymousClass1((Continuation) obj5);
            anonymousClass1.Z$0 = zBooleanValue;
            anonymousClass1.L$0 = (SubscriptionModel) obj3;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            SubscriptionModel subscriptionModel = (SubscriptionModel) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            FeatureOption.INSTANCE.getClass();
            FeatureOption.isExpRegion();
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, "isSupportShowVo5G phoneId: " + (subscriptionModel != null ? Boxing.boxInt(subscriptionModel.getPhoneId()) : null), null, 8);
            return Boxing.boxBoolean(OplusMobileConnectionRepositoryImpl.this.getVoNrEnable() && z);
        }
    }

    public OplusMobileConnectionRepositoryImpl(Context context, int i, TableLogBuffer tableLogBuffer, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, TelephonyManager telephonyManager, ImsListenerProxy imsListenerProxy, Flow flow) {
        this.context = context;
        this.subId = i;
        this.mobileConnectionLogger = tableLogBuffer;
        this.telephonyManager = telephonyManager;
        this.imsListenerProxy = imsListenerProxy;
        this.logTag = ListPopupWindow$.ExternalSyntheticOutline0.m(i, TAG);
        Flow flowTransformLatest = FlowKt.transformLatest(flow, new OplusMobileConnectionRepositoryImpl$special$$inlined$flatMapLatest$1(null, coroutineDispatcher, this));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.capabilityType = FlowKt.stateIn(flowTransformLatest, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), new CapabilityTypeModel());
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1(2, null)));
        SharingStarted sharingStartedWhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3);
        Boolean bool = Boolean.FALSE;
        this.oplusFeatureChanged = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, sharingStartedWhileSubscribed$default, bool);
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(flow, new OplusMobileConnectionRepositoryImpl$special$$inlined$flatMapLatest$2(null, coroutineDispatcher, this))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.voNrSettings = stateFlowStateIn;
        this.isVoNrEnabled = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.flowOn(FlowKt.combine(getOplusFeatureChanged(), stateFlowStateIn, flow, getCapabilityType(), new AnonymousClass1(null)), coroutineDispatcher)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.s5GCellCount = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(flow, new OplusMobileConnectionRepositoryImpl$special$$inlined$flatMapLatest$3(null, coroutineDispatcher, this))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), 0);
    }

    public static /* synthetic */ void getCapabilityType$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getVoNrEnable() {
        int i = this.subId;
        boolean z = false;
        try {
            TelephonyManager telephonyManagerCreateForSubscriptionId = this.telephonyManager.createForSubscriptionId(i);
            if (telephonyManagerCreateForSubscriptionId != null && telephonyManagerCreateForSubscriptionId.isVoNrEnabled()) {
                z = true;
            }
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "getVoNrEnable: " + z, null, 8);
            return z;
        } catch (DeadSystemException unused) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG + i, "unable to get vonr state: DeadSystemException");
            return z;
        } catch (IllegalStateException unused2) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG + i, "unable to get vonr state");
            return z;
        } catch (DeadSystemRuntimeException unused3) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG + i, "unable to get vonr state: DeadSystemRuntimeException");
            return z;
        }
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepository
    public StateFlow getCapabilityType() {
        return this.capabilityType;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepository
    public StateFlow getOplusFeatureChanged() {
        return this.oplusFeatureChanged;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepository
    public StateFlow getS5GCellCount() {
        return this.s5GCellCount;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepository
    public StateFlow isVoNrEnabled() {
        return this.isVoNrEnabled;
    }
}
