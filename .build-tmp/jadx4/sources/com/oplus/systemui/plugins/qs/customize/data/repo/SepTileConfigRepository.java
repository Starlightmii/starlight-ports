package com.oplus.systemui.plugins.qs.customize.data.repo;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.SepTileSpecRepository;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigRepository implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepTileConfigRepository";
    private final CoroutineDispatcher bgDispatcher;
    private final SepEditDBTileRepository dbTileRepository;
    private final SepTileSpecRepository innerTileSpecRepository;
    private final SepUserTileConfigRepository.Factory sepUserTileRepository;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    private final Flow specialModeState;
    private final MutableStateFlow superPowerState;
    private final SepSuperPowerTileConfigRepository superPowerTileConfigRepository;
    private final ConcurrentHashMap<Integer, SepUserTileConfigRepository> userTileRepositories = new ConcurrentHashMap<>();
    private final SepWellBeingTileConfigRepository wellBeingTileConfigRepository;
    private final MutableStateFlow willBeingState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository$ensureUserTileConfigRepository$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Function {
        public /* synthetic */ int $userId;
        public /* synthetic */ SepTileConfigRepository this$0;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((SepUserTileConfigRepository_Factory_Impl) this.this$0.sepUserTileRepository).create(this.$userId, SharedFlowKt.MutableSharedFlow$default(0, 10, (BufferOverflow) null, 5));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository$tileConfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02161 extends ContinuationImpl {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C02161(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepTileConfigRepository.this.tileConfig(0, this);
        }
    }

    public SepTileConfigRepository(SepUserTileConfigRepository.Factory factory, SepSuperPowerTileConfigRepository sepSuperPowerTileConfigRepository, SepWellBeingTileConfigRepository sepWellBeingTileConfigRepository, SepEditDBTileRepository sepEditDBTileRepository, CoroutineDispatcher coroutineDispatcher, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
        this.sepUserTileRepository = factory;
        this.superPowerTileConfigRepository = sepSuperPowerTileConfigRepository;
        this.wellBeingTileConfigRepository = sepWellBeingTileConfigRepository;
        this.dbTileRepository = sepEditDBTileRepository;
        this.bgDispatcher = coroutineDispatcher;
        this.separateQsTileLogger = separateQsTileListLogBuffer;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow mutableStateFlowTraceAs = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(bool), "SepTileConfigRepository-superPowerState");
        this.superPowerState = mutableStateFlowTraceAs;
        MutableStateFlow mutableStateFlowTraceAs2 = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow(bool), "SepTileConfigRepository-willBeingState");
        this.willBeingState = mutableStateFlowTraceAs2;
        this.specialModeState = FlowExtKt.traceAs(FlowKt.distinctUntilChanged(FlowKt.combine(mutableStateFlowTraceAs, mutableStateFlowTraceAs2, SepTileConfigRepository$specialModeState$3.INSTANCE)), "SepTileConfigRepository-specialModeState");
        SepTileConfigRepository$innerTileSpecRepository$1 sepTileConfigRepository$innerTileSpecRepository$1 = new SepTileConfigRepository$innerTileSpecRepository$1();
        sepTileConfigRepository$innerTileSpecRepository$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.innerTileSpecRepository = sepTileConfigRepository$innerTileSpecRepository$1;
    }

    public static final Object access$removeUserTileConfigRepository(int i, SepTileConfigRepository sepTileConfigRepository, Continuation continuation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "removeUserTileConfigRepository: "), null, 8);
        sepTileConfigRepository.userTileRepositories.remove(Boxing.boxInt(i));
        Object objWithContext = BuildersKt.withContext(sepTileConfigRepository.bgDispatcher, new removeUserTileConfigRepository.2(i, sepTileConfigRepository, (Continuation) null), continuation);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SepTileConfigRepository:");
        Iterator<Map.Entry<Integer, SepUserTileConfigRepository>> it = this.userTileRepositories.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().dump(printWriter, strArr);
        }
    }

    public final SepUserTileConfigRepository ensureUserTileConfigRepository(int i) {
        ConcurrentHashMap<Integer, SepUserTileConfigRepository> concurrentHashMap = this.userTileRepositories;
        Integer numValueOf = Integer.valueOf(i);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$userId = i;
        VarHandle.storeStoreFence();
        return concurrentHashMap.computeIfAbsent(numValueOf, anonymousClass1);
    }

    public final SepTileSpecRepository getInnerTileSpecRepository() {
        return this.innerTileSpecRepository;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object tileConfig(int i, Continuation continuation) {
        C02161 c02161;
        if (continuation instanceof C02161) {
            c02161 = (C02161) continuation;
            int i2 = c02161.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02161.label = i2 - Integer.MIN_VALUE;
            } else {
                c02161 = new C02161(continuation);
            }
        } else {
            c02161 = new C02161(continuation);
        }
        Object objTileConfigs = c02161.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02161.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objTileConfigs);
            SepUserTileConfigRepository sepUserTileConfigRepositoryEnsureUserTileConfigRepository = ensureUserTileConfigRepository(i);
            c02161.I$0 = i;
            c02161.label = 1;
            objTileConfigs = sepUserTileConfigRepositoryEnsureUserTileConfigRepository.tileConfigs(c02161);
            if (objTileConfigs == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(objTileConfigs);
        }
        return FlowExtKt.traceAs(FlowKt.transformLatest(this.specialModeState, new SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1(null, this, (StateFlow) objTileConfigs)), "SepTileConfigRepository-specialModeState");
    }
}
