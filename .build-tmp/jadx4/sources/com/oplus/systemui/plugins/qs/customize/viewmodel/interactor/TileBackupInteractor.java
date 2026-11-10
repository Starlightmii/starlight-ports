package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.google.gson.Gson;
import com.oplus.systemui.plugins.qs.customize.data.model.BackupData;
import com.oplus.systemui.plugins.qs.customize.data.model.BackupTile;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileBackupInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileBackupInteractor";
    private final CoroutineScope bgScope;
    public int gridCol;
    public int gridRow;
    private Job job;
    public SepUserTileConfigRepository mainUserTileConfigRepository;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final List<TileConfig> currentTileConfig = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00571 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ TileBackupInteractor this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00581 extends SuspendLambda implements Function2 {
                /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ TileBackupInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00581(TileBackupInteractor tileBackupInteractor, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = tileBackupInteractor;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00581 c00581 = new C00581(this.this$0, continuation);
                    c00581.L$0 = obj;
                    return c00581;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((Pair) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Pair pair = (Pair) this.L$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    TileBackupInteractor.access$updateTileConfigs(this.this$0, CollectionsKt___CollectionsKt.plus((Collection) pair.getFirst(), (Iterable) pair.getSecond()));
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00571(TileBackupInteractor tileBackupInteractor, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileBackupInteractor;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00571(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
            
                if (com.android.app.tracing.coroutines.flow.FlowExtKt.collectLatestTraced(r6, r1, r5) == r0) goto L23;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r5.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    if (r1 == 0) goto L1d
                    if (r1 == r3) goto L19
                    if (r1 != r2) goto L13
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L4a
                L13:
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
                    return r4
                L19:
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L30
                L1d:
                    kotlin.ResultKt.throwOnFailure(r6)
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor r6 = r5.this$0
                    com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository r6 = r6.mainUserTileConfigRepository
                    if (r6 != 0) goto L27
                    r6 = r4
                L27:
                    r5.label = r3
                    java.lang.Object r6 = r6.tileConfigs(r5)
                    if (r6 != r0) goto L30
                    goto L49
                L30:
                    boolean r1 = r6 instanceof kotlinx.coroutines.flow.StateFlow
                    if (r1 == 0) goto L37
                    kotlinx.coroutines.flow.StateFlow r6 = (kotlinx.coroutines.flow.StateFlow) r6
                    goto L38
                L37:
                    r6 = r4
                L38:
                    if (r6 == 0) goto L4a
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$1$1 r1 = new com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$1$1
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor r3 = r5.this$0
                    r1.<init>(r3, r4)
                    r5.label = r2
                    java.lang.Object r5 = com.android.app.tracing.coroutines.flow.FlowExtKt.collectLatestTraced(r6, r1, r5)
                    if (r5 != r0) goto L4a
                L49:
                    return r0
                L4a:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor.AnonymousClass1.C00571.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ TileBackupInteractor this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$2$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00591 extends SuspendLambda implements Function2 {
                /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ TileBackupInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00591(TileBackupInteractor tileBackupInteractor, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = tileBackupInteractor;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    C00591 c00591 = new C00591(this.this$0, continuation);
                    c00591.L$0 = obj;
                    return c00591;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((GridInfoEntity) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    GridInfoEntity gridInfoEntity = (GridInfoEntity) this.L$0;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.gridCol = gridInfoEntity.getGridCol();
                    this.this$0.gridRow = gridInfoEntity.getGridRow();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(TileBackupInteractor tileBackupInteractor, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileBackupInteractor;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
            
                if (com.android.app.tracing.coroutines.flow.FlowExtKt.collectLatestTraced(r6, r1, r5) == r0) goto L23;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                /*
                    r5 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r5.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    if (r1 == 0) goto L1d
                    if (r1 == r3) goto L19
                    if (r1 != r2) goto L13
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L4a
                L13:
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
                    return r4
                L19:
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L30
                L1d:
                    kotlin.ResultKt.throwOnFailure(r6)
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor r6 = r5.this$0
                    com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository r6 = r6.mainUserTileConfigRepository
                    if (r6 != 0) goto L27
                    r6 = r4
                L27:
                    r5.label = r3
                    java.lang.Object r6 = r6.gridInfo(r5)
                    if (r6 != r0) goto L30
                    goto L49
                L30:
                    boolean r1 = r6 instanceof kotlinx.coroutines.flow.StateFlow
                    if (r1 == 0) goto L37
                    kotlinx.coroutines.flow.StateFlow r6 = (kotlinx.coroutines.flow.StateFlow) r6
                    goto L38
                L37:
                    r6 = r4
                L38:
                    if (r6 == 0) goto L4a
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$2$1 r1 = new com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor$start$1$2$1
                    com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor r3 = r5.this$0
                    r1.<init>(r3, r4)
                    r5.label = r2
                    java.lang.Object r5 = com.android.app.tracing.coroutines.flow.FlowExtKt.collectLatestTraced(r6, r1, r5)
                    if (r5 != r0) goto L4a
                L49:
                    return r0
                L4a:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileBackupInteractor.AnonymousClass1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = TileBackupInteractor.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
            CoroutineTracingKt.launchTraced$default(coroutineScope, "TileBackupInteractor-start-inner1", (CoroutineContext) null, (CoroutineStart) null, new C00571(TileBackupInteractor.this, null), 6);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "TileBackupInteractor-start-inner2", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(TileBackupInteractor.this, null), 6);
            return Unit.INSTANCE;
        }
    }

    public TileBackupInteractor(CoroutineScope coroutineScope) {
        this.bgScope = coroutineScope;
    }

    public static final void access$updateTileConfigs(TileBackupInteractor tileBackupInteractor, List list) {
        synchronized (tileBackupInteractor) {
            try {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "TileBackupInteractor-->".concat("updateTileConfigs: old=" + tileBackupInteractor.currentTileConfig.size() + " new=" + list.size()));
                }
                tileBackupInteractor.currentTileConfig.clear();
                tileBackupInteractor.currentTileConfig.addAll(list);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final synchronized List<TileConfig> getTileConfigs() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.currentTileConfig);
        return arrayList;
    }

    public final String backup() {
        SepUserTileConfigRepository sepUserTileConfigRepository = this.mainUserTileConfigRepository;
        if (sepUserTileConfigRepository == null) {
            sepUserTileConfigRepository = null;
        }
        List<TileConfig> tileConfigs = sepUserTileConfigRepository.defaultTilesMatched ? getTileConfigs() : CollectionsKt__CollectionsKt.emptyList();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(tileConfigs.size(), "backup: "), null, 8);
        List<TileConfig> list = tileConfigs;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (TileConfig tileConfig : list) {
            arrayList.add(new BackupTile(tileConfig.getTileSpec(), tileConfig.getLocation().getPageIndex(), tileConfig.getLocation().getStartCol(), tileConfig.getLocation().getStartRow(), tileConfig.getSize().getColSpan(), tileConfig.getSize().getRowSpan()));
        }
        return new Gson().toJson(new BackupData(1, this.gridCol, this.gridRow, arrayList));
    }

    public final void destroy() {
        if (!this.initialized.compareAndSet(true, false)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "destroy already!");
            return;
        }
        Job job = this.job;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
    }

    public final void start(SepTileConfigRepository sepTileConfigRepository) {
        if (!this.initialized.compareAndSet(false, true)) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "start already!");
        } else {
            this.mainUserTileConfigRepository = sepTileConfigRepository.ensureUserTileConfigRepository(0);
            this.job = CoroutineTracingKt.launchTraced$default(this.bgScope, "TileBackupInteractor-start", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 6);
        }
    }
}
