package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.OperateTile;
import java.io.PrintWriter;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepSuperPowerTileConfigRepository implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepSuperPowerTileConfigRepository";
    private final MutableSharedFlow changeEvents = SharedFlowKt.MutableSharedFlow$default(0, 10, (BufferOverflow) null, 5);
    private final CoroutineScope mainScope;
    private final SepDefaultTileSpecRepository sepDefaultTileSpecRepo;
    private final SepStaticTileConfigRepository staticTileConfigRepo;
    public StateFlow superPowerSaveTileUiConfigs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepSuperPowerTileConfigRepository$loadSuperPowerSaveTilesFromDefault$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepSuperPowerTileConfigRepository.this.loadSuperPowerSaveTilesFromDefault(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepSuperPowerTileConfigRepository$superPowerSaveTileConfigs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C02151(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepSuperPowerTileConfigRepository.this.superPowerSaveTileConfigs(this);
        }
    }

    public SepSuperPowerTileConfigRepository(SepDefaultTileSpecRepository sepDefaultTileSpecRepository, SepStaticTileConfigRepository sepStaticTileConfigRepository, CoroutineScope coroutineScope) {
        this.sepDefaultTileSpecRepo = sepDefaultTileSpecRepository;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
        this.mainScope = coroutineScope;
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("  superPowerSaveModeTileSpecs: " + this.sepDefaultTileSpecRepo.getSuperPowerSaveModeTileSpecs());
        StateFlow stateFlow = this.superPowerSaveTileUiConfigs;
        if (stateFlow == null) {
            stateFlow = null;
        }
        printWriter.println("  superPowerSaveTileUiConfigs: " + stateFlow);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:29:0x0110  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0110 -> B:30:0x0114). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x01d5 -> B:42:0x01dc). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object loadSuperPowerSaveTilesFromDefault(kotlin.coroutines.jvm.internal.ContinuationImpl r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepSuperPowerTileConfigRepository.loadSuperPowerSaveTilesFromDefault(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object operateTiles(List<? extends TileSpecOperate> list, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new OperateTile(list, true), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0093 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x0094 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object superPowerSaveTileConfigs(Continuation continuation) throws Throwable {
        C02151 c02151;
        SepSuperPowerTileConfigRepository sepSuperPowerTileConfigRepository;
        StateFlow stateFlow;
        if (continuation instanceof C02151) {
            c02151 = (C02151) continuation;
            int i = c02151.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02151.label = i - Integer.MIN_VALUE;
            } else {
                c02151 = new C02151(continuation);
            }
        } else {
            c02151 = new C02151(continuation);
        }
        Object objLoadSuperPowerSaveTilesFromDefault = c02151.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02151.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objLoadSuperPowerSaveTilesFromDefault);
            if (this.superPowerSaveTileUiConfigs == null) {
                c02151.label = 1;
                objLoadSuperPowerSaveTilesFromDefault = loadSuperPowerSaveTilesFromDefault(c02151);
                if (objLoadSuperPowerSaveTilesFromDefault != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            stateFlow = this.superPowerSaveTileUiConfigs;
            if (stateFlow == null) {
                return null;
            }
            return stateFlow;
        }
        if (i2 == 1) {
            ResultKt.throwOnFailure(objLoadSuperPowerSaveTilesFromDefault);
        } else {
            if (i2 != 2) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            sepSuperPowerTileConfigRepository = (SepSuperPowerTileConfigRepository) c02151.L$2;
            ResultKt.throwOnFailure(objLoadSuperPowerSaveTilesFromDefault);
        }
        sepSuperPowerTileConfigRepository.superPowerSaveTileUiConfigs = FlowExtKt.traceAs((StateFlow) objLoadSuperPowerSaveTilesFromDefault, "SepSuperPowerTileConfigRepository-superPowerSaveTileUiConfigs");
        stateFlow = this.superPowerSaveTileUiConfigs;
        if (stateFlow == null) {
            return null;
        }
        return stateFlow;
        Pair pair = (Pair) objLoadSuperPowerSaveTilesFromDefault;
        GridInfoEntity gridInfoEntity = new GridInfoEntity(0, 4, 4);
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.scan(this.changeEvents, pair, new superPowerSaveTileConfigs.2(gridInfoEntity, (Continuation) null)));
        CoroutineScope coroutineScope = this.mainScope;
        c02151.L$0 = SpillingKt.nullOutSpilledVariable(pair);
        c02151.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
        c02151.L$2 = this;
        c02151.label = 2;
        objLoadSuperPowerSaveTilesFromDefault = FlowKt.stateIn(flowDistinctUntilChanged, coroutineScope, c02151);
        if (objLoadSuperPowerSaveTilesFromDefault != coroutine_suspended) {
            sepSuperPowerTileConfigRepository = this;
            sepSuperPowerTileConfigRepository.superPowerSaveTileUiConfigs = FlowExtKt.traceAs((StateFlow) objLoadSuperPowerSaveTilesFromDefault, "SepSuperPowerTileConfigRepository-superPowerSaveTileUiConfigs");
            stateFlow = this.superPowerSaveTileUiConfigs;
            if (stateFlow == null) {
                return null;
            }
            return stateFlow;
        }
        return coroutine_suspended;
    }
}
