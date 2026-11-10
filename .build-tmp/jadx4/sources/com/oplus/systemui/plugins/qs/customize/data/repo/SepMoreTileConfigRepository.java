package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.data.model.TilePreviewsData;
import com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileTypeConfig;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepMoreTileConfigRepository {
    public static final Companion Companion = new Companion();
    private static final String PREFIX = "custom(";
    public static final String TAG = "SepPendingAddTileSpecRepository";
    private final CoroutineDispatcher bgDispatcher;
    private final Lazy connectivityTileSpecs$delegate;
    private final Lazy displayTileSpecs$delegate;
    private final Mutex mutex = MutexKt.Mutex(false);
    public List previewConfigs;
    private final Lazy recommendTileSpecs$delegate;
    private final SepStaticTileConfigRepository staticTileRepository;
    public Map stockTileList;
    private final Lazy toolsTilesSpec$delegate;
    private final Map<SepTileType, String> typeToResources;
    public Map typeToTileSpecs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getPreviewConfig$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMoreTileConfigRepository.this.getPreviewConfig(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getTypeToTileSpecs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02071 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C02071(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMoreTileConfigRepository.this.getTypeToTileSpecs(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$loadPreviewConfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02081 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C02081(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMoreTileConfigRepository.this.loadPreviewConfig(this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$parseTileSpec$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02091 extends ContinuationImpl {
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
        int label;
        /* synthetic */ Object result;

        public C02091(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepMoreTileConfigRepository.this.parseTileSpec(null, this);
        }
    }

    public SepMoreTileConfigRepository(ContextInstance contextInstance, SepStaticTileConfigRepository sepStaticTileConfigRepository, CoroutineDispatcher coroutineDispatcher) {
        this.staticTileRepository = sepStaticTileConfigRepository;
        this.bgDispatcher = coroutineDispatcher;
        this.typeToResources = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(SepTileType.RECOMMEND, contextInstance.getSysUiContext().getResources().getString(2131956235)), TuplesKt.to(SepTileType.CONNECTIVITY, contextInstance.getSysUiContext().getResources().getString(2131956231)), TuplesKt.to(SepTileType.DISPLAY, contextInstance.getSysUiContext().getResources().getString(2131956233)), TuplesKt.to(SepTileType.TOOLS, contextInstance.getSysUiContext().getResources().getString(2131956236))});
        SepMoreTileConfigRepository$$ExternalSyntheticLambda0 sepMoreTileConfigRepository$$ExternalSyntheticLambda0 = new SepMoreTileConfigRepository$$ExternalSyntheticLambda0(0);
        sepMoreTileConfigRepository$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.recommendTileSpecs$delegate = LazyKt__LazyJVMKt.lazy(sepMoreTileConfigRepository$$ExternalSyntheticLambda0);
        SepMoreTileConfigRepository$$ExternalSyntheticLambda0 sepMoreTileConfigRepository$$ExternalSyntheticLambda1 = new SepMoreTileConfigRepository$$ExternalSyntheticLambda0(1);
        sepMoreTileConfigRepository$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.connectivityTileSpecs$delegate = LazyKt__LazyJVMKt.lazy(sepMoreTileConfigRepository$$ExternalSyntheticLambda1);
        SepMoreTileConfigRepository$$ExternalSyntheticLambda0 sepMoreTileConfigRepository$$ExternalSyntheticLambda2 = new SepMoreTileConfigRepository$$ExternalSyntheticLambda0(2);
        sepMoreTileConfigRepository$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.displayTileSpecs$delegate = LazyKt__LazyJVMKt.lazy(sepMoreTileConfigRepository$$ExternalSyntheticLambda2);
        SepMoreTileConfigRepository$$ExternalSyntheticLambda0 sepMoreTileConfigRepository$$ExternalSyntheticLambda3 = new SepMoreTileConfigRepository$$ExternalSyntheticLambda0(3);
        sepMoreTileConfigRepository$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.toolsTilesSpec$delegate = LazyKt__LazyJVMKt.lazy(sepMoreTileConfigRepository$$ExternalSyntheticLambda3);
    }

    public static final List access$toTileData(SepMoreTileConfigRepository sepMoreTileConfigRepository, List list) {
        sepMoreTileConfigRepository.getClass();
        List<TileStaticConfig> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        for (TileStaticConfig tileStaticConfig : list2) {
            String tileSpec = tileStaticConfig.getTileSpec();
            List<SpanSize> supportSizes = tileStaticConfig.getSupportSizes();
            SpanCache spanCache = SpanCache.INSTANCE;
            arrayList.add(new TilePreviewsData(tileSpec, (String) null, (QSTile.Icon) null, supportSizes.contains(spanCache.spanSize(1, 1)) ? spanCache.spanSize(1, 1) : tileStaticConfig.getDefaultSize(), (String) null, 46));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TileStaticConfig> getConnectivityTileSpecs() {
        return (List) this.connectivityTileSpecs$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TileStaticConfig> getDisplayTileSpecs() {
        return (List) this.displayTileSpecs$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TileStaticConfig> getOtherSystemTilesSpec() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Map map = this.typeToTileSpecs;
        if (map == null) {
            map = null;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() != SepTileType.RECOMMEND) {
                linkedHashSet.addAll((Collection) entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedHashSet, 10));
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileStaticConfig) it.next()).getTileSpec());
        }
        Set set = CollectionsKt___CollectionsKt.toSet(arrayList);
        Map map2 = this.stockTileList;
        Collection collectionValues = (map2 != null ? map2 : null).values();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : collectionValues) {
            TileStaticConfig tileStaticConfig = (TileStaticConfig) obj;
            if (StringsKt__StringsKt.contains(tileStaticConfig.getTileSpec(), PREFIX, false) && !set.contains(tileStaticConfig.getTileSpec())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TileStaticConfig> getRecommendTileSpecs() {
        return (List) this.recommendTileSpecs$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<TileStaticConfig> getToolsTilesSpec() {
        return (List) this.toolsTilesSpec$delegate.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        if (loadPreviewConfig(r0) == r1) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPreviewConfig(kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getPreviewConfig$1 r0 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getPreviewConfig$1 r0 = new com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getPreviewConfig$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L46
            if (r2 == r6) goto L3e
            if (r2 == r5) goto L36
            if (r2 != r4) goto L30
            kotlin.ResultKt.throwOnFailure(r8)
            goto L76
        L30:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            return r3
        L36:
            java.lang.Object r2 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository r2 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L67
        L3e:
            java.lang.Object r2 = r0.L$0
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository r2 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L46:
            kotlin.ResultKt.throwOnFailure(r8)
            com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository r8 = r7.staticTileRepository
            r0.L$0 = r7
            r0.label = r6
            java.lang.Object r8 = r8.getTileConfigs(r0)
            if (r8 != r1) goto L56
            goto L75
        L56:
            r2 = r7
        L57:
            java.util.Map r8 = (java.util.Map) r8
            r2.stockTileList = r8
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r8 = r7.getTypeToTileSpecs(r0)
            if (r8 != r1) goto L66
            goto L75
        L66:
            r2 = r7
        L67:
            java.util.Map r8 = (java.util.Map) r8
            r2.typeToTileSpecs = r8
            r0.L$0 = r3
            r0.label = r4
            java.lang.Object r8 = r7.loadPreviewConfig(r0)
            if (r8 != r1) goto L76
        L75:
            return r1
        L76:
            java.util.List r7 = r7.previewConfigs
            if (r7 != 0) goto L7b
            return r3
        L7b:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.getPreviewConfig(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final SepTileType getTypeBySpec(String str) {
        SepTileType tileType;
        List list = this.previewConfigs;
        Object obj = null;
        if (list == null) {
            list = null;
        }
        loop0: for (Object obj2 : list) {
            List<TilePreviewsData> tileList = ((TileTypeConfig) obj2).getTileList();
            if (!(tileList instanceof Collection) || !tileList.isEmpty()) {
                Iterator<T> it = tileList.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((TilePreviewsData) it.next()).getTileSpec(), str)) {
                        obj = obj2;
                        break loop0;
                    }
                }
            }
        }
        TileTypeConfig tileTypeConfig = (TileTypeConfig) obj;
        return (tileTypeConfig == null || (tileType = tileTypeConfig.getTileType()) == null) ? SepTileType.OTHERS : tileType;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0069  */
    /* JADX WARN: Code duplicated, block: B:19:0x009f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00a0 -> B:21:0x00a1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object getTypeToTileSpecs(kotlin.coroutines.jvm.internal.ContinuationImpl r12) throws java.lang.Throwable {
        /*
            r11 = this;
            boolean r0 = r12 instanceof com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.C02071
            if (r0 == 0) goto L13
            r0 = r12
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getTypeToTileSpecs$1 r0 = (com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.C02071) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getTypeToTileSpecs$1 r0 = new com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository$getTypeToTileSpecs$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L4d
            if (r2 != r4) goto L46
            int r2 = r0.I$0
            java.lang.Object r5 = r0.L$6
            java.lang.Object r6 = r0.L$5
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r0.L$4
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r7 = r0.L$3
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r7 = r0.L$2
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r0.L$1
            java.util.Map r8 = (java.util.Map) r8
            java.lang.Object r9 = r0.L$0
            java.util.Map r9 = (java.util.Map) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto La1
        L46:
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r11)
            r11 = 0
            return r11
        L4d:
            kotlin.ResultKt.throwOnFailure(r12)
            java.util.LinkedHashMap r12 = new java.util.LinkedHashMap
            r12.<init>()
            java.util.Map<com.oplus.systemui.plugins.qs.customize.data.SepTileType, java.lang.String> r2 = r11.typeToResources
            java.util.Set r5 = r2.entrySet()
            java.util.Iterator r5 = r5.iterator()
            r6 = r12
            r8 = r2
            r2 = r3
            r7 = r5
        L63:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto La6
            java.lang.Object r12 = r7.next()
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12
            java.lang.Object r5 = r12.getKey()
            java.lang.Object r9 = r12.getValue()
            java.lang.String r9 = (java.lang.String) r9
            r0.L$0 = r6
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$1 = r10
            r0.L$2 = r7
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$3 = r10
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$4 = r12
            r0.L$5 = r6
            r0.L$6 = r5
            r0.I$0 = r2
            r0.I$1 = r3
            r0.label = r4
            java.lang.Object r12 = r11.parseTileSpec(r9, r0)
            if (r12 != r1) goto La0
            return r1
        La0:
            r9 = r6
        La1:
            r6.put(r5, r12)
            r6 = r9
            goto L63
        La6:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.getTypeToTileSpecs(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object loadPreviewConfig(ContinuationImpl continuationImpl) throws Throwable {
        C02081 c02081;
        Mutex mutex;
        int i;
        Mutex mutex2;
        if (continuationImpl instanceof C02081) {
            c02081 = (C02081) continuationImpl;
            int i2 = c02081.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02081.label = i2 - Integer.MIN_VALUE;
            } else {
                c02081 = new C02081(continuationImpl);
            }
        } else {
            c02081 = new C02081(continuationImpl);
        }
        Object obj = c02081.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02081.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.previewConfigs == null) {
                    mutex = this.mutex;
                    c02081.L$0 = mutex;
                    c02081.I$0 = 0;
                    c02081.label = 1;
                    if (mutex.lock((Object) null, c02081) != coroutine_suspended) {
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
                this = (SepMoreTileConfigRepository) c02081.L$1;
                mutex2 = (Mutex) c02081.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    this.previewConfigs = (List) obj;
                    mutex = mutex2;
                    mutex.unlock((Object) null);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock((Object) null);
                    throw th;
                }
            }
            i = c02081.I$0;
            Mutex mutex3 = (Mutex) c02081.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex3;
            if (this.previewConfigs == null) {
                c02081.L$0 = mutex;
                c02081.L$1 = this;
                c02081.I$0 = i;
                c02081.I$1 = 0;
                c02081.label = 2;
                Object objWithContext = BuildersKt.withContext(this.bgDispatcher, new SepMoreTileConfigRepository$loadDataFromXMLByLock$2(this, null), c02081);
                if (objWithContext != coroutine_suspended) {
                    mutex2 = mutex;
                    obj = objWithContext;
                    this.previewConfigs = (List) obj;
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

    /* JADX WARN: Code duplicated, block: B:17:0x0089  */
    /* JADX WARN: Code duplicated, block: B:19:0x00c4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00c5 -> B:21:0x00c9). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object parseTileSpec(java.lang.String r17, kotlin.coroutines.jvm.internal.ContinuationImpl r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository.parseTileSpec(java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
