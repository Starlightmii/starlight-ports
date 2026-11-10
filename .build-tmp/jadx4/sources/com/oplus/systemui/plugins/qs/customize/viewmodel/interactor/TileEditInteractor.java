package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.plugins.qs.Dumpable;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.DeformAlgHelper;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.TileCheckHelper;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IStepStatus;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.LocationAlgSession;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.StepStatus;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileEditInteractor implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileEditInteractor";
    private GridInfoEntity gridInfo;
    private Job job;
    private final LocationAlgSession locationAlgSession = new LocationAlgSession();
    private final CoroutineScope mainScope;
    private Pair<? extends List<TileConfig>, ? extends List<TileConfig>> originTiles;
    private final TileConfigRepoSwitcher repoSwitcherInteractor;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    private ISepTileConfigRepository tileConfigRepoInEdit;
    private StateFlow tileConfigs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$checkTiles$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return TileEditInteractor.this.checkTiles(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$dragEnd$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02401 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C02401(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileEditInteractor.this.dragEnd(false, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$enterEditMode$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileEditInteractor.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x00f1, code lost:
        
            if (com.android.app.tracing.coroutines.flow.FlowExtKt.collectLatestTraced(r1, r5, r8) == r0) goto L35;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 247
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$exitEditMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02411 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C02411(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileEditInteractor.this.exitEditMode(this);
        }
    }

    public TileEditInteractor(TileConfigRepoSwitcher tileConfigRepoSwitcher, CoroutineScope coroutineScope, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
        this.repoSwitcherInteractor = tileConfigRepoSwitcher;
        this.mainScope = coroutineScope;
        this.separateQsTileLogger = separateQsTileListLogBuffer;
    }

    public final Object addTile(String str, int i, Loc loc, SpanSize spanSize, Continuation continuation) {
        Object objAddTileWithLoc;
        SeparateQsTileListLogBuffer separateQsTileListLogBuffer = this.separateQsTileLogger;
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "[addTileWithLoc] ", str, ", targetPageIndex=", ", repo=");
        sbM.append(iSepTileConfigRepository);
        separateQsTileListLogBuffer.logTile(TAG, sbM.toString());
        ISepTileConfigRepository iSepTileConfigRepository2 = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository2 == null || (objAddTileWithLoc = iSepTileConfigRepository2.addTileWithLoc(str, i, loc, spanSize, false, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objAddTileWithLoc;
    }

    public final Object addTileNonLoc(String str, int i, Continuation continuation) {
        Object objAddTilesNonLoc;
        SeparateQsTileListLogBuffer separateQsTileListLogBuffer = this.separateQsTileLogger;
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "[addTileNonLoc] ", str, ", position=", ", repo=");
        sbM.append(iSepTileConfigRepository);
        separateQsTileListLogBuffer.logTile(TAG, sbM.toString());
        ISepTileConfigRepository iSepTileConfigRepository2 = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository2 == null || (objAddTilesNonLoc = iSepTileConfigRepository2.addTilesNonLoc(CollectionsKt__CollectionsJVMKt.listOf(str), i, false, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objAddTilesNonLoc;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object checkTiles(ContinuationImpl continuationImpl) {
        AnonymousClass1 anonymousClass1;
        Pair<? extends List<TileConfig>, ? extends List<TileConfig>> pair;
        Pair pair2;
        GridInfoEntity gridInfoEntity;
        if (continuationImpl instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuationImpl;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuationImpl);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuationImpl);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            StateFlow stateFlow = this.tileConfigs;
            if (stateFlow != null && (pair2 = (Pair) stateFlow.getValue()) != null && (gridInfoEntity = this.gridInfo) != null) {
                booleanRef.element = TileCheckHelper.INSTANCE.checkTiles(CollectionsKt___CollectionsKt.toMutableList((Collection) pair2.getFirst()), gridInfoEntity);
            }
            if (!booleanRef.element && (pair = this.originTiles) != null) {
                StateFlow stateFlow2 = this.tileConfigs;
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "[checkTiles] fail setTileConfigs tiles: " + (stateFlow2 != null ? (Pair) stateFlow2.getValue() : null), null, 8);
                ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
                if (iSepTileConfigRepository != null) {
                    List list = (List) pair.getFirst();
                    List list2 = (List) pair.getSecond();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(booleanRef);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(pair);
                    anonymousClass1.I$0 = 0;
                    anonymousClass1.label = 1;
                    if (iSepTileConfigRepository.setTileConfigs(list, list2, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    public final Object clipTileSize(String str, SpanSize spanSize, boolean z, Continuation continuation) {
        Object objClipSize;
        TileConfigRepoSwitcher tileConfigRepoSwitcher = this.repoSwitcherInteractor;
        ISepTileConfigRepository editTileConfigRepo = z ? tileConfigRepoSwitcher.getEditTileConfigRepo() : tileConfigRepoSwitcher.getSrcTileConfigRepo();
        return (editTileConfigRepo == null || (objClipSize = editTileConfigRepo.clipSize(str, spanSize, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objClipSize;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x009b  */
    public final Object deform(int i, SpanSize spanSize, Continuation continuation) {
        Pair pair;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileEditInteractor-->".concat("[deform] draggingIndex=" + i + ", size=" + spanSize));
        }
        IStepStatus iStepStatusLast = this.locationAlgSession.last();
        TileConfig tileConfig = iStepStatusLast != null ? (TileConfig) iStepStatusLast.getTiles().get(iStepStatusLast.getIndex()) : null;
        if (Intrinsics.areEqual(tileConfig != null ? tileConfig.getSize() : null, spanSize)) {
            this.locationAlgSession.pop();
            ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
            if (iSepTileConfigRepository != null) {
                Object tileConfigs = iSepTileConfigRepository.setTileConfigs(iStepStatusLast.getTiles(), null, continuation);
                return tileConfigs == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tileConfigs : Unit.INSTANCE;
            }
        } else {
            StateFlow stateFlow = this.tileConfigs;
            List list = (stateFlow == null || (pair = (Pair) stateFlow.getValue()) == null) ? null : (List) pair.getFirst();
            if (list != null) {
                if (iStepStatusLast == null || iStepStatusLast.getIndex() != i) {
                    this.locationAlgSession.push(new StepStatus(list, i));
                } else {
                    if (!Intrinsics.areEqual(tileConfig != null ? tileConfig.getSize() : null, spanSize)) {
                        this.locationAlgSession.push(new StepStatus(list, i));
                    }
                }
            }
            ISepTileConfigRepository iSepTileConfigRepository2 = this.tileConfigRepoInEdit;
            if (iSepTileConfigRepository2 != null) {
                Object objDeform = iSepTileConfigRepository2.deform(i, spanSize, false, continuation);
                return objDeform == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDeform : Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public final Object drag(int i, Loc loc, Continuation continuation) {
        Pair pair;
        List list;
        Object objDragItem;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileEditInteractor-->".concat("[drag] draggingIndex=" + i + ", toLoc=" + loc));
        }
        IStepStatus iStepStatusLast = this.locationAlgSession.last();
        StateFlow stateFlow = this.tileConfigs;
        if (stateFlow == null || (pair = (Pair) stateFlow.getValue()) == null || (list = (List) pair.getFirst()) == null) {
            return Unit.INSTANCE;
        }
        if (iStepStatusLast == null || iStepStatusLast.getIndex() != i || !Intrinsics.areEqual(((TileConfig) iStepStatusLast.getTiles().get(iStepStatusLast.getIndex())).getLocation(), loc)) {
            this.locationAlgSession.push(new StepStatus(list, i));
        }
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository == null || (objDragItem = iSepTileConfigRepository.dragItem(i, loc, false, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objDragItem;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object dragEnd(boolean z, Continuation continuation) {
        C02401 c02401;
        Pair pair;
        List list;
        Object next;
        ISepTileConfigRepository iSepTileConfigRepository;
        if (continuation instanceof C02401) {
            c02401 = (C02401) continuation;
            int i = c02401.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02401.label = i - Integer.MIN_VALUE;
            } else {
                c02401 = new C02401(continuation);
            }
        } else {
            c02401 = new C02401(continuation);
        }
        Object obj = c02401.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02401.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[dragEnd] isPersonal=", "TileEditInteractor-->", "SystemUi--QuickSettings", z);
            }
            LocationAlgSession locationAlgSession = this.locationAlgSession;
            if (!z) {
                locationAlgSession.stopDrag();
                return Unit.INSTANCE;
            }
            IStepStatus iStepStatusFirst = locationAlgSession.first();
            if (iStepStatusFirst == null) {
                return Unit.INSTANCE;
            }
            StateFlow stateFlow = this.tileConfigs;
            if (stateFlow == null || (pair = (Pair) stateFlow.getValue()) == null || (list = (List) pair.getFirst()) == null) {
                return Unit.INSTANCE;
            }
            TileConfig tileConfig = (TileConfig) iStepStatusFirst.getTiles().get(iStepStatusFirst.getIndex());
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((TileConfig) next).getTileSpec(), tileConfig.getTileSpec()));
            TileConfig tileConfig2 = (TileConfig) next;
            if (tileConfig2 == null) {
                return Unit.INSTANCE;
            }
            List tiles = (Intrinsics.areEqual(tileConfig.getLocation(), tileConfig2.getLocation()) && Intrinsics.areEqual(tileConfig.getSize(), tileConfig2.getSize())) ? iStepStatusFirst.getTiles() : null;
            if (tiles != null && (iSepTileConfigRepository = this.tileConfigRepoInEdit) != null) {
                c02401.L$0 = SpillingKt.nullOutSpilledVariable(iStepStatusFirst);
                c02401.L$1 = SpillingKt.nullOutSpilledVariable(list);
                c02401.L$2 = SpillingKt.nullOutSpilledVariable(tileConfig);
                c02401.L$3 = SpillingKt.nullOutSpilledVariable(tileConfig2);
                c02401.L$4 = SpillingKt.nullOutSpilledVariable(tiles);
                c02401.L$5 = SpillingKt.nullOutSpilledVariable(tiles);
                c02401.Z$0 = z;
                c02401.I$0 = 0;
                c02401.label = 1;
                if (iSepTileConfigRepository.setTileConfigs(tiles, null, c02401) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.locationAlgSession.stopDrag();
        return Unit.INSTANCE;
    }

    public final void dragStart(int i) {
        Pair pair;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "[dragStart] draggingIndex=", "TileEditInteractor-->", "SystemUi--QuickSettings");
        }
        StateFlow stateFlow = this.tileConfigs;
        if (stateFlow == null || (pair = (Pair) stateFlow.getValue()) == null) {
            return;
        }
        this.locationAlgSession.startDrag((List) pair.getFirst(), i);
    }

    @Override // com.oplus.systemui.plugins.qs.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("  TileEditInteractor:");
        printWriter.println("  stepStackList: " + this.locationAlgSession.getStackList());
        StateFlow stateFlow = this.tileConfigs;
        printWriter.println("  currentTiles: " + (stateFlow != null ? (Pair) stateFlow.getValue() : null));
        printWriter.println("  originTiles: " + this.originTiles);
    }

    public final Object enterEditMode(Continuation continuation) {
        this.job = CoroutineTracingKt.launchTraced$default(this.mainScope, "TileEditInteractor-launch-enterEditMode", Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass2(null), 4);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0072  */
    /* JADX WARN: Code duplicated, block: B:34:0x0075  */
    /* JADX WARN: Code duplicated, block: B:36:0x0078  */
    /* JADX WARN: Code duplicated, block: B:41:0x0087  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007e, code lost:
    
        if (r8.persistAllToSrcRepo(r0) == r1) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object exitEditMode(kotlin.coroutines.Continuation r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor.C02411
            if (r0 == 0) goto L13
            r0 = r8
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$exitEditMode$1 r0 = (com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor.C02411) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$exitEditMode$1 r0 = new com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor$exitEditMode$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L3e
            if (r2 == r5) goto L3a
            if (r2 == r4) goto L36
            if (r2 != r3) goto L30
            kotlin.ResultKt.throwOnFailure(r8)
            goto L81
        L30:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            return r6
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6c
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L63
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r8 == 0) goto L56
            java.lang.String r8 = "QuickSettings"
            boolean r8 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r8)
            if (r8 == 0) goto L56
            java.lang.String r8 = "SystemUi--QuickSettings"
            java.lang.String r2 = "TileEditInteractor-->[exitEditMode]"
            android.util.Log.d(r8, r2)
        L56:
            com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository r8 = r7.tileConfigRepoInEdit
            if (r8 == 0) goto L63
            r0.label = r5
            java.lang.Object r8 = r8.removeEmptyPage(r0)
            if (r8 != r1) goto L63
            goto L80
        L63:
            r0.label = r4
            java.lang.Object r8 = r7.checkTiles(r0)
            if (r8 != r1) goto L6c
            goto L80
        L6c:
            com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository r8 = r7.tileConfigRepoInEdit
            boolean r2 = r8 instanceof com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository
            if (r2 == 0) goto L75
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository r8 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepMemTileConfigRepository) r8
            goto L76
        L75:
            r8 = r6
        L76:
            if (r8 == 0) goto L81
            r0.label = r3
            java.lang.Object r8 = r8.persistAllToSrcRepo(r0)
            if (r8 != r1) goto L81
        L80:
            return r1
        L81:
            r7.tileConfigRepoInEdit = r6
            kotlinx.coroutines.Job r8 = r7.job
            if (r8 == 0) goto L8c
            kotlinx.coroutines.Job$Key r0 = kotlinx.coroutines.Job.Key
            r8.cancel(r6)
        L8c:
            r7.tileConfigs = r6
            r7.gridInfo = r6
            r7.originTiles = r6
            com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgHelper.clearPageUsesInfos()
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor.exitEditMode(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<SpanSize> getActualSupportSize(int i) {
        Pair pair;
        List list;
        List mutableList;
        GridInfoEntity gridInfoEntity;
        StateFlow stateFlow = this.tileConfigs;
        if (stateFlow == null || (pair = (Pair) stateFlow.getValue()) == null || (list = (List) pair.getFirst()) == null || (mutableList = CollectionsKt___CollectionsKt.toMutableList(list)) == null || (gridInfoEntity = this.gridInfo) == null) {
            return null;
        }
        return DeformAlgHelper.INSTANCE.getActualSupportSize(mutableList, (TileConfig) mutableList.get(i), gridInfoEntity);
    }

    public final Object moveTile(String str, int i, Loc loc, Continuation continuation) {
        Object objMoveTile;
        SeparateQsTileListLogBuffer separateQsTileListLogBuffer = this.separateQsTileLogger;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "[moveTile] ", str, ", dstPosition=", ", dstLoc=");
        sbM.append(loc);
        separateQsTileListLogBuffer.logTile(sbM.toString());
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository == null || (objMoveTile = iSepTileConfigRepository.moveTile(CollectionsKt__CollectionsJVMKt.listOf(str), i, loc, false, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objMoveTile;
    }

    public final Object onCellNumChanged(int i, int i2, Continuation continuation) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[onCellNumChanged] ", i, i2, " * ", "TileEditInteractor-->"));
        }
        Object objChangeGridInfo = this.repoSwitcherInteractor.getSrcTileConfigRepo().changeGridInfo(i, i2, continuation);
        return objChangeGridInfo == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objChangeGridInfo : Unit.INSTANCE;
    }

    public final Object removeTiles(Collection<String> collection, IntentTarget intentTarget, Continuation continuation) {
        Object objRemoveTiles;
        this.separateQsTileLogger.logTile(TAG, "[removeTiles] " + collection + ", target=" + intentTarget + ", repo=" + this.tileConfigRepoInEdit);
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository == null || (objRemoveTiles = iSepTileConfigRepository.removeTiles(collection, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objRemoveTiles;
    }

    public final Object swapTile(String str, String str2, Continuation continuation) {
        Object objSwapTile;
        this.separateQsTileLogger.logTile("[swapTile] specOfPersonalArea=" + str + ", specOfOthersArea=" + str2);
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        return (iSepTileConfigRepository == null || (objSwapTile = iSepTileConfigRepository.swapTile(str, str2, false, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objSwapTile;
    }

    public final Object drag(int i, int i2, Continuation continuation) {
        ISepTileConfigRepository iSepTileConfigRepository = this.tileConfigRepoInEdit;
        if (iSepTileConfigRepository != null) {
            Object objDragItem = iSepTileConfigRepository.dragItem(i, i2, false, continuation);
            return objDragItem == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDragItem : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
