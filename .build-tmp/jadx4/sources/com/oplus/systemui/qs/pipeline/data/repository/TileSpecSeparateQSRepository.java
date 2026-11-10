package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.qs.SepTileSpecRepository;
import com.android.systemui.plugins.qs.TileSpecOperate;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecRepository;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.wrapper.content.Intent;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecSeparateQSRepository implements TileSpecRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileSpecSeparateQSRepository";
    private final StateFlow isAvailable;
    private final SharedFlow removeTilesFlow;
    private final MutableSharedFlow removedTilesEvents;
    private final CoroutineScope scope;
    private final MutableStateFlow sepTileSpecRepositoryFlow = StateFlowKt.MutableStateFlow(new SepTileSpecRepositoryStub());
    private final SuperPowerSaveRepository superPowerSaveRepository;
    private final ReceiveChannel tilesUpgradePath;
    private final WellBeingAssistantRepository wellBeingAssistantRepository;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BroadcastDispatcher $broadcastDispatcher;
        int label;
        final /* synthetic */ TileSpecSeparateQSRepository this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass3 implements FlowCollector {
            public /* synthetic */ TileSpecSeparateQSRepository this$0;

            public final Object emit(Object obj, Continuation continuation) {
                int iIntValue = ((Number) obj).intValue();
                LogUtil.i$default(LogUtil.TAG_QS, TileSpecSeparateQSRepository.TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(iIntValue, "user ", " removed!"), null, 8);
                Object objRemoveUser = ((SepTileSpecRepository) this.this$0.sepTileSpecRepositoryFlow.getValue()).removeUser(iIntValue, continuation);
                return objRemoveUser == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveUser : Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BroadcastDispatcher broadcastDispatcher, TileSpecSeparateQSRepository tileSpecSeparateQSRepository, Continuation continuation) {
            super(2, continuation);
            this.$broadcastDispatcher = broadcastDispatcher;
            this.this$0 = tileSpecSeparateQSRepository;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$broadcastDispatcher, this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowBroadcastFlow$default = BroadcastDispatcher.broadcastFlow$default(this.$broadcastDispatcher, new IntentFilter(Intent.ACTION_USER_REMOVED), (UserHandle) null, 0, new TileSpecSeparateQSRepository$1$$ExternalSyntheticLambda0(), 14);
                TileSpecSeparateQSRepository tileSpecSeparateQSRepository = this.this$0;
                AnonymousClass3 anonymousClass3 = new AnonymousClass3();
                anonymousClass3.this$0 = tileSpecSeparateQSRepository;
                VarHandle.storeStoreFence();
                this.label = 1;
                TileSpecSeparateQSRepository$1$invokeSuspend$$inlined$filterNot$1$2 tileSpecSeparateQSRepository$1$invokeSuspend$$inlined$filterNot$1$2 = new TileSpecSeparateQSRepository$1$invokeSuspend$$inlined$filterNot$1$2();
                tileSpecSeparateQSRepository$1$invokeSuspend$$inlined$filterNot$1$2.$this_unsafeFlow = anonymousClass3;
                VarHandle.storeStoreFence();
                Object objCollect = flowBroadcastFlow$default.collect(tileSpecSeparateQSRepository$1$invokeSuspend$$inlined$filterNot$1$2, this);
                if (objCollect != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollect = Unit.INSTANCE;
                }
                if (objCollect == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$removeTiles$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02631 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02631(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileSpecSeparateQSRepository.this.removeTiles(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$setSepTileSpecRepository$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02641 extends SuspendLambda implements Function2 {
        final /* synthetic */ SepTileSpecRepository $repository;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$setSepTileSpecRepository$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00811 extends SuspendLambda implements Function2 {
            final /* synthetic */ SepTileSpecRepository $repository;
            int label;
            final /* synthetic */ TileSpecSeparateQSRepository this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$setSepTileSpecRepository$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00821 extends SuspendLambda implements Function2 {
                final /* synthetic */ SepTileSpecRepository $repository;
                /* synthetic */ boolean Z$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00821(SepTileSpecRepository sepTileSpecRepository, Continuation continuation) {
                    super(2, continuation);
                    this.$repository = sepTileSpecRepository;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00821 c00821 = new C00821(this.$repository, continuation);
                    c00821.Z$0 = ((Boolean) obj).booleanValue();
                    return c00821;
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((C00821) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    boolean z = this.Z$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    SepTileSpecRepository sepTileSpecRepository = this.$repository;
                    if (sepTileSpecRepository != null) {
                        sepTileSpecRepository.setSuperPowerState(z);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00811(TileSpecSeparateQSRepository tileSpecSeparateQSRepository, SepTileSpecRepository sepTileSpecRepository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileSpecSeparateQSRepository;
                this.$repository = sepTileSpecRepository;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00811(this.this$0, this.$repository, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow superPowerSaveMode = this.this$0.superPowerSaveRepository.getSuperPowerSaveMode();
                    C00821 c00821 = new C00821(this.$repository, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(superPowerSaveMode, c00821, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$setSepTileSpecRepository$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ SepTileSpecRepository $repository;
            int label;
            final /* synthetic */ TileSpecSeparateQSRepository this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$setSepTileSpecRepository$1$2$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00831 extends SuspendLambda implements Function2 {
                final /* synthetic */ SepTileSpecRepository $repository;
                /* synthetic */ boolean Z$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00831(SepTileSpecRepository sepTileSpecRepository, Continuation continuation) {
                    super(2, continuation);
                    this.$repository = sepTileSpecRepository;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00831 c00831 = new C00831(this.$repository, continuation);
                    c00831.Z$0 = ((Boolean) obj).booleanValue();
                    return c00831;
                }

                public final Object invoke(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    return ((C00831) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    boolean z = this.Z$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    SepTileSpecRepository sepTileSpecRepository = this.$repository;
                    if (sepTileSpecRepository != null) {
                        sepTileSpecRepository.setWillBeingState(z);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(TileSpecSeparateQSRepository tileSpecSeparateQSRepository, SepTileSpecRepository sepTileSpecRepository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileSpecSeparateQSRepository;
                this.$repository = sepTileSpecRepository;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, this.$repository, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow wellBeingAssistantMode = this.this$0.wellBeingAssistantRepository.getWellBeingAssistantMode();
                    C00831 c00831 = new C00831(this.$repository, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(wellBeingAssistantMode, c00831, this) == coroutine_suspended) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02641(SepTileSpecRepository sepTileSpecRepository, Continuation continuation) {
            super(2, continuation);
            this.$repository = sepTileSpecRepository;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C02641 c02641 = TileSpecSeparateQSRepository.this.new C02641(this.$repository, continuation);
            c02641.L$0 = obj;
            return c02641;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00811(TileSpecSeparateQSRepository.this, this.$repository, null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(TileSpecSeparateQSRepository.this, this.$repository, null), 3);
            return Unit.INSTANCE;
        }
    }

    public TileSpecSeparateQSRepository(OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, SuperPowerSaveRepository superPowerSaveRepository, WellBeingAssistantRepository wellBeingAssistantRepository, CoroutineScope coroutineScope, BroadcastDispatcher broadcastDispatcher) {
        this.superPowerSaveRepository = superPowerSaveRepository;
        this.wellBeingAssistantRepository = wellBeingAssistantRepository;
        this.scope = coroutineScope;
        this.isAvailable = oplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS();
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 1, (BufferOverflow) null, 5);
        this.removedTilesEvents = mutableSharedFlowMutableSharedFlow$default;
        this.removeTilesFlow = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(broadcastDispatcher, this, null), 3);
        this.tilesUpgradePath = ChannelKt.Channel$default(0, 7, (BufferOverflow) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$processSepTileSpecsFlow(TileSpecSeparateQSRepository tileSpecSeparateQSRepository, int i, SepTileSpecRepository sepTileSpecRepository, ContinuationImpl continuationImpl) {
        TileSpecSeparateQSRepository$processSepTileSpecsFlow$1 tileSpecSeparateQSRepository$processSepTileSpecsFlow$1;
        tileSpecSeparateQSRepository.getClass();
        if (continuationImpl instanceof TileSpecSeparateQSRepository$processSepTileSpecsFlow$1) {
            tileSpecSeparateQSRepository$processSepTileSpecsFlow$1 = (TileSpecSeparateQSRepository$processSepTileSpecsFlow$1) continuationImpl;
            int i2 = tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.label = i2 - Integer.MIN_VALUE;
            } else {
                tileSpecSeparateQSRepository$processSepTileSpecsFlow$1 = new TileSpecSeparateQSRepository$processSepTileSpecsFlow$1(tileSpecSeparateQSRepository, continuationImpl);
            }
        } else {
            tileSpecSeparateQSRepository$processSepTileSpecsFlow$1 = new TileSpecSeparateQSRepository$processSepTileSpecsFlow$1(tileSpecSeparateQSRepository, continuationImpl);
        }
        Object objTilesSpecs = tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objTilesSpecs);
            tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.L$0 = SpillingKt.nullOutSpilledVariable(sepTileSpecRepository);
            tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.I$0 = i;
            tileSpecSeparateQSRepository$processSepTileSpecsFlow$1.label = 1;
            objTilesSpecs = sepTileSpecRepository.tilesSpecs(i, tileSpecSeparateQSRepository$processSepTileSpecsFlow$1);
            if (objTilesSpecs == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(objTilesSpecs);
        }
        TileSpecSeparateQSRepository$processSepTileSpecsFlow$$inlined$map$1 tileSpecSeparateQSRepository$processSepTileSpecsFlow$$inlined$map$1 = new TileSpecSeparateQSRepository$processSepTileSpecsFlow$$inlined$map$1();
        tileSpecSeparateQSRepository$processSepTileSpecsFlow$$inlined$map$1.$this_unsafeTransform$inlined = (Flow) objTilesSpecs;
        VarHandle.storeStoreFence();
        return tileSpecSeparateQSRepository$processSepTileSpecsFlow$$inlined$map$1;
    }

    public Object addTile(int i, TileSpec tileSpec, int i2, Continuation continuation) {
        Object objAddTile;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileSpecSeparateQSRepository-->".concat("addTile: " + i + " " + tileSpec));
        }
        return (!(tileSpec instanceof TileSpec.Invalid) && (objAddTile = ((SepTileSpecRepository) this.sepTileSpecRepositoryFlow.getValue()).addTile(i, tileSpec.getSpec(), i2, continuation)) == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAddTile : Unit.INSTANCE;
    }

    public final SharedFlow getRemoveTilesFlow() {
        return this.removeTilesFlow;
    }

    public ReceiveChannel getTilesUpgradePath() {
        return this.tilesUpgradePath;
    }

    public StateFlow isAvailable() {
        return this.isAvailable;
    }

    public Object modifyTiles(int i, List<? extends TileSpec> list, List<? extends TileSpecOperate> list2, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileSpecSeparateQSRepository-->".concat("modifyTiles: tiles=" + list + ", operates=" + list2 + " for Use(" + i + ")"));
        }
        Object objOperateTiles = ((SepTileSpecRepository) this.sepTileSpecRepositoryFlow.getValue()).operateTiles(i, list2, continuation);
        return objOperateTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objOperateTiles : Unit.INSTANCE;
    }

    public Object prependDefault(int i, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "prependDefault: ", "TileSpecSeparateQSRepository-->", "SystemUi--QuickSettings");
        }
        return Unit.INSTANCE;
    }

    public Object reconcileRestore(RestoreData restoreData, Set<? extends TileSpec> set, Continuation continuation) {
        boolean z = restoreData.getSepRestoredTiles() == null;
        String sepRestoredTiles = restoreData.getSepRestoredTiles();
        if (sepRestoredTiles == null) {
            sepRestoredTiles = TilesSettingConverter.INSTANCE.toSpecString(restoreData.getRestoredTiles());
        }
        String str = sepRestoredTiles;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileSpecSeparateQSRepository-->".concat("reconcileRestore: restoreFromQsSettings=" + z + ", autoAdded=" + set));
        }
        SepTileSpecRepository sepTileSpecRepository = (SepTileSpecRepository) this.sepTileSpecRepositoryFlow.getValue();
        int userId = restoreData.getUserId();
        Set<? extends TileSpec> set2 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        Iterator<T> it = set2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileSpec) it.next()).getSpec());
        }
        Set set3 = CollectionsKt___CollectionsKt.toSet(arrayList);
        Set restoredAutoAddedTiles = restoreData.getRestoredAutoAddedTiles();
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(restoredAutoAddedTiles, 10));
        Iterator it2 = restoredAutoAddedTiles.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((TileSpec) it2.next()).getSpec());
        }
        Object objReconcileRestore = sepTileSpecRepository.reconcileRestore(userId, str, z, set3, CollectionsKt___CollectionsKt.toSet(arrayList2), continuation);
        return objReconcileRestore == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objReconcileRestore : Unit.INSTANCE;
    }

    public Object removePackage(String str, int i, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileSpecSeparateQSRepository-->".concat("removePackage: packageName=" + str + " userId=" + i));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d7, code lost:
    
        if (((com.android.systemui.plugins.qs.SepTileSpecRepository) r8.sepTileSpecRepositoryFlow.getValue()).removeTiles(r9, r2, r0) == r1) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object removeTiles(int r9, java.util.Collection<? extends com.android.systemui.qs.pipeline.shared.TileSpec> r10, kotlin.coroutines.Continuation r11) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository.removeTiles(int, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object resetToDefault(int i, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "resetToDefault: ", "TileSpecSeparateQSRepository-->", "SystemUi--QuickSettings");
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public final void setSepTileSpecRepository(SepTileSpecRepository sepTileSpecRepository) {
        this.sepTileSpecRepositoryFlow.setValue(sepTileSpecRepository == null ? new SepTileSpecRepositoryStub() : sepTileSpecRepository);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new C02641(sepTileSpecRepository, null), 3);
    }

    public Object setTiles(int i, List<? extends TileSpec> list, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("setTiles: ", i, list.size(), " ", "TileSpecSeparateQSRepository-->"));
        }
        SepTileSpecRepository sepTileSpecRepository = (SepTileSpecRepository) this.sepTileSpecRepositoryFlow.getValue();
        List<? extends TileSpec> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileSpec) it.next()).getSpec());
        }
        Object tiles = sepTileSpecRepository.setTiles(i, arrayList, continuation);
        return tiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tiles : Unit.INSTANCE;
    }

    public Object tilesSpecs(int i, Continuation continuation) {
        return FlowKt.transformLatest(this.sepTileSpecRepositoryFlow, new TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1(null, this, i));
    }
}
