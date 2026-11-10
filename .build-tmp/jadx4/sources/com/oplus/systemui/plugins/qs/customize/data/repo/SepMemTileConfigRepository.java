package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForAB;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepMemTileConfigRepository implements ISepTileConfigRepository, ISepTileConfigCtrlA, ISepTileConfigCtrlB, ISepTileConfigCtrlAB {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepMemTileConfigRepository";
    public final /* synthetic */ SepTileConfigCtrlADefault $$delegate_0;
    public final /* synthetic */ SepTileConfigCtrlBDefault $$delegate_1;
    public final /* synthetic */ SepTileConfigCtrlABDefault $$delegate_2;
    public StateFlow _tileConfigs;
    private final MutableSharedFlow changeEvents;
    private final GridInfoEntity initGridInfoEntity;
    private final Pair<List<TileConfig>, List<TileConfig>> initTileUiConfigs;
    private final CoroutineScope mainScope;
    private final Mutex mutex = MutexKt.Mutex(false);
    private final SepUserTileConfigRepository sepUserTileConfigRepository;
    private final SepStaticTileConfigRepository staticTileConfigRepo;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository$initFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMemTileConfigRepository.this.initFlow(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository$persistAllToSrcRepo$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends BaseActionForAB {
        public AnonymousClass2() {
            super(true);
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForAB
        public final Object applyAB(List list, GridInfoEntity gridInfoEntity, List list2, Continuation continuation) {
            SepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1 sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1;
            if (continuation instanceof SepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1) {
                sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1 = (SepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1) continuation;
                int i = sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.label = i - Integer.MIN_VALUE;
                } else {
                    sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1 = new SepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1(this, continuation);
                }
            } else {
                sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1 = new SepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1(this, continuation);
            }
            Object obj = sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.label;
            if (i2 != 0) {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                Pair pair = (Pair) sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$3;
                ResultKt.throwOnFailure(obj);
                return pair;
            }
            ResultKt.throwOnFailure(obj);
            Pair pair2 = new Pair(list, list2);
            SepUserTileConfigRepository sepUserTileConfigRepository = SepMemTileConfigRepository.this.sepUserTileConfigRepository;
            List<TileConfig> list3 = (List) pair2.getFirst();
            List<TileConfig> list4 = (List) pair2.getSecond();
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$0 = SpillingKt.nullOutSpilledVariable(list);
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$2 = SpillingKt.nullOutSpilledVariable(list2);
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$3 = pair2;
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.L$4 = SpillingKt.nullOutSpilledVariable(pair2);
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.I$0 = 0;
            sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1.label = 1;
            return sepUserTileConfigRepository.setTileConfigs(list3, list4, sepMemTileConfigRepository$persistAllToSrcRepo$2$applyAB$1) == coroutine_suspended ? coroutine_suspended : pair2;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository$tileConfigs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02061 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C02061(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMemTileConfigRepository.this.tileConfigs(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SepMemTileConfigRepository(SepUserTileConfigRepository sepUserTileConfigRepository, Pair<? extends List<TileConfig>, ? extends List<TileConfig>> pair, GridInfoEntity gridInfoEntity, CoroutineScope coroutineScope, SepStaticTileConfigRepository sepStaticTileConfigRepository, MutableSharedFlow mutableSharedFlow) {
        this.$$delegate_0 = new SepTileConfigCtrlADefault(mutableSharedFlow, sepStaticTileConfigRepository);
        this.$$delegate_1 = new SepTileConfigCtrlBDefault(mutableSharedFlow, sepStaticTileConfigRepository);
        this.$$delegate_2 = new SepTileConfigCtrlABDefault(mutableSharedFlow, sepStaticTileConfigRepository);
        this.sepUserTileConfigRepository = sepUserTileConfigRepository;
        this.initTileUiConfigs = pair;
        this.initGridInfoEntity = gridInfoEntity;
        this.mainScope = coroutineScope;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
        this.changeEvents = mutableSharedFlow;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x010a, code lost:
    
        if (r0.addTileWithLoc(r10, r5, r9, r8, r1, r3) == r4) goto L32;
     */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addTileWithLoc(java.lang.String r17, int r18, com.oplusos.systemui.common.model.Loc r19, com.oplusos.systemui.common.model.SpanSize r20, boolean r21, kotlin.coroutines.Continuation r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository.addTileWithLoc(java.lang.String, int, com.oplusos.systemui.common.model.Loc, com.oplusos.systemui.common.model.SpanSize, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:30:0x0123  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0123 -> B:31:0x012e). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    public java.lang.Object addTilesNonLoc(java.util.Collection<java.lang.String> r18, int r19, boolean r20, kotlin.coroutines.Continuation r21) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository.addTilesNonLoc(java.util.Collection, int, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object changeGridInfo(int i, int i2, Continuation continuation) {
        return this.$$delegate_0.changeGridInfo(i, i2, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object clipSize(String str, SpanSize spanSize, Continuation continuation) {
        return this.$$delegate_0.clipSize(str, spanSize, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object deform(int i, SpanSize spanSize, boolean z, Continuation continuation) {
        return this.$$delegate_0.deform(i, spanSize, z, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    public Object dragItem(int i, int i2, boolean z, Continuation continuation) {
        return this.$$delegate_1.dragItem(i, i2, z, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigFlow
    public Object gridInfo(Continuation continuation) {
        return StateFlowKt.MutableStateFlow(this.initGridInfoEntity);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object initFlow(ContinuationImpl continuationImpl) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Mutex mutex;
        int i;
        Mutex mutex2;
        if (continuationImpl instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuationImpl;
            int i2 = anonymousClass1.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i2 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuationImpl);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuationImpl);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (this._tileConfigs == null) {
                    mutex = this.mutex;
                    anonymousClass1.L$0 = mutex;
                    anonymousClass1.I$0 = 0;
                    anonymousClass1.label = 1;
                    if (mutex.lock((Object) null, anonymousClass1) != coroutine_suspended) {
                        i = 0;
                    }
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            if (i3 != 1) {
                if (i3 != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                this = (SepMemTileConfigRepository) anonymousClass1.L$1;
                mutex2 = (Mutex) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    this._tileConfigs = FlowExtKt.traceAs((StateFlow) obj, "SepMemTileConfigRepository-tileConfigs");
                    mutex = mutex2;
                    mutex.unlock((Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock((Object) null);
                    throw th;
                }
            }
            i = anonymousClass1.I$0;
            Mutex mutex3 = (Mutex) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex3;
            if (this._tileConfigs == null) {
                Flow flowOnEach = FlowKt.onEach(FlowKt.scan(this.changeEvents, this.initTileUiConfigs, new SepMemTileConfigRepository$initFlow$2$1(this, null)), new SepMemTileConfigRepository$initFlow$2$2(2, null));
                CoroutineScope coroutineScope = this.mainScope;
                anonymousClass1.L$0 = mutex;
                anonymousClass1.L$1 = this;
                anonymousClass1.I$0 = i;
                anonymousClass1.I$1 = 0;
                anonymousClass1.label = 2;
                Object objStateIn = FlowKt.stateIn(flowOnEach, coroutineScope, anonymousClass1);
                if (objStateIn != coroutine_suspended) {
                    mutex2 = mutex;
                    obj = objStateIn;
                    this._tileConfigs = FlowExtKt.traceAs((StateFlow) obj, "SepMemTileConfigRepository-tileConfigs");
                    mutex = mutex2;
                }
                return coroutine_suspended;
            }
            mutex.unlock((Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock((Object) null);
            throw th;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object moveTile(List<String> list, int i, Loc loc, boolean z, Continuation continuation) {
        return this.$$delegate_2.moveTile(list, i, loc, z, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object operateTiles(List<? extends TileSpecOperate> list, Continuation continuation) {
        return this.$$delegate_2.operateTiles(list, continuation);
    }

    public final Object persistAllToSrcRepo(Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new AnonymousClass2(), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object prependDefault(Continuation continuation) {
        return this.$$delegate_2.prependDefault(continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object reconcileRestore(String str, boolean z, Set<String> set, Set<String> set2, Continuation continuation) {
        return this.$$delegate_2.reconcileRestore(str, z, set, set2, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object removeEmptyPage(Continuation continuation) {
        return this.$$delegate_0.removeEmptyPage(continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object removeTiles(Collection<String> collection, Continuation continuation) {
        return this.$$delegate_2.removeTiles(collection, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object restoreTileConfig(List<TileConfig> list, List<TileConfig> list2, boolean z, Continuation continuation) {
        return this.$$delegate_2.restoreTileConfig(list, list2, z, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object setTileConfigs(List<TileConfig> list, List<TileConfig> list2, Continuation continuation) {
        return this.$$delegate_2.setTileConfigs(list, list2, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object setTiles(List<String> list, Continuation continuation) {
        return this.$$delegate_2.setTiles(list, continuation);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object swapTile(String str, String str2, boolean z, Continuation continuation) {
        return this.$$delegate_2.swapTile(str, str2, z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigFlow
    public Object tileConfigs(Continuation continuation) {
        C02061 c02061;
        if (continuation instanceof C02061) {
            c02061 = (C02061) continuation;
            int i = c02061.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02061.label = i - Integer.MIN_VALUE;
            } else {
                c02061 = new C02061(continuation);
            }
        } else {
            c02061 = new C02061(continuation);
        }
        Object obj = c02061.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02061.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            c02061.label = 1;
            if (initFlow(c02061) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        StateFlow stateFlow = this._tileConfigs;
        if (stateFlow == null) {
            return null;
        }
        return stateFlow;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository, com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    public Object dragItem(int i, Loc loc, boolean z, Continuation continuation) {
        return this.$$delegate_0.dragItem(i, loc, z, continuation);
    }
}
