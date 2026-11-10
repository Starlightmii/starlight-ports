package com.oplus.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.common.data.repository.PackageChangeRepository;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.tileex.CustomTileEx;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysInlineServiceRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SysInlineServiceRepository";
    private final CoroutineScope backgroundScope;
    private final AtomicReference<List<TileSpec>> currentCustomTileSpecs = new AtomicReference<>(CollectionsKt__CollectionsKt.emptyList());
    private final CustomTileEx customTileEx;
    private final PackageChangeRepository packageChangeRepository;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.SysInlineServiceRepository$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CurrentTilesInteractor $currentTilesInteractor;
        int label;
        final /* synthetic */ SysInlineServiceRepository this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.SysInlineServiceRepository$init$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00871 extends SuspendLambda implements Function2 {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SysInlineServiceRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00871(SysInlineServiceRepository sysInlineServiceRepository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = sysInlineServiceRepository;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00871 c00871 = new C00871(this.this$0, continuation);
                c00871.L$0 = obj;
                return c00871;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((List) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                List list = (List) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (((TileModel) obj2).getSpec() instanceof TileSpec.CustomTileSpec) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj3 = arrayList.get(i);
                    i++;
                    arrayList2.add(((TileModel) obj3).getSpec());
                }
                this.this$0.currentCustomTileSpecs.set(arrayList2);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CurrentTilesInteractor currentTilesInteractor, SysInlineServiceRepository sysInlineServiceRepository, Continuation continuation) {
            super(2, continuation);
            this.$currentTilesInteractor = currentTilesInteractor;
            this.this$0 = sysInlineServiceRepository;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$currentTilesInteractor, this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow currentTiles = this.$currentTilesInteractor.getCurrentTiles();
                C00871 c00871 = new C00871(this.this$0, null);
                this.label = 1;
                if (FlowKt.collectLatest(currentTiles, c00871, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.SysInlineServiceRepository$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ CurrentTilesInteractor $currentTilesInteractor;
        int label;
        final /* synthetic */ SysInlineServiceRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CurrentTilesInteractor currentTilesInteractor, SysInlineServiceRepository sysInlineServiceRepository, Continuation continuation) {
            super(2, continuation);
            this.$currentTilesInteractor = currentTilesInteractor;
            this.this$0 = sysInlineServiceRepository;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$currentTilesInteractor, this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            FlowKt.launchIn(FlowKt.transformLatest(this.$currentTilesInteractor.getUserId(), new SysInlineServiceRepository$init$2$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, null)), this.this$0.backgroundScope);
            return Unit.INSTANCE;
        }
    }

    public SysInlineServiceRepository(CoroutineScope coroutineScope, PackageChangeRepository packageChangeRepository, CustomTileEx customTileEx) {
        this.backgroundScope = coroutineScope;
        this.packageChangeRepository = packageChangeRepository;
        this.customTileEx = customTileEx;
    }

    public final void init(CurrentTilesInteractor currentTilesInteractor) {
        BuildersKt.launch$default(this.backgroundScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(currentTilesInteractor, this, null), 3);
        BuildersKt.launch$default(this.backgroundScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(currentTilesInteractor, this, null), 3);
    }
}
