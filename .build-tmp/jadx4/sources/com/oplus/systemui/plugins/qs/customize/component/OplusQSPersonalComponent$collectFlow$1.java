package com.oplus.systemui.plugins.qs.customize.component;

import android.os.SystemProperties;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.recyclerview.widget.COUIRecyclerView;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.view.EditTileAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditState;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusQSPersonalComponent$collectFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusQSPersonalComponent this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$collectFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusQSPersonalComponent this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$collectFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00491 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusQSPersonalComponent this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$collectFlow$1$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public /* synthetic */ OplusQSPersonalComponent this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$collectFlow$1$1$1$2$3, reason: invalid class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class AnonymousClass3 implements Runnable {
                    public /* synthetic */ OplusQSPersonalComponent this$0;

                    @Override // java.lang.Runnable
                    public final void run() {
                        OplusQSPersonalComponent oplusQSPersonalComponent = this.this$0;
                        oplusQSPersonalComponent.setNeedItemAnimator(oplusQSPersonalComponent.isNeedItemAnimator || oplusQSPersonalComponent.getTileAdapter().isEditMode);
                    }
                }

                public /* synthetic */ AnonymousClass2(int i) {
                    this.$r8$classId = i;
                }

                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            TileEditState tileEditState = (TileEditState) obj;
                            OplusQSPersonalComponent oplusQSPersonalComponent = this.this$0;
                            if (tileEditState instanceof TileEditState.Normal) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    Log.d("SystemUi--QuickSettings", "OplusQSPersonalComponent-->".concat("[collect-uiStateFlow] " + tileEditState));
                                }
                                oplusQSPersonalComponent.setNeedItemAnimator(oplusQSPersonalComponent.getTileAdapter().isEditMode);
                                List list = CollectionsKt___CollectionsKt.toList(oplusQSPersonalComponent.getTileAdapter().getDataList());
                                List<Loc> list2 = CollectionsKt___CollectionsKt.toList(oplusQSPersonalComponent.getCustomLayoutManager().getLayoutConfig().getSpecifyLoc());
                                TileEditState.Normal normal = (TileEditState.Normal) tileEditState;
                                boolean z = normal.isEditMode;
                                List<Loc> allPersonalTilesLoc = normal.getAllPersonalTilesLoc();
                                if (!z && !allPersonalTilesLoc.isEmpty()) {
                                    List<GridData> allPersonalTiles = normal.getAllPersonalTiles();
                                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allPersonalTiles, 10));
                                    Iterator<T> it = allPersonalTiles.iterator();
                                    while (it.hasNext()) {
                                        arrayList.add(Integer.valueOf(((GridData) it.next()).getSize().getRowSpan()));
                                    }
                                    List<Loc> list3 = allPersonalTilesLoc;
                                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                                    Iterator<T> it2 = list3.iterator();
                                    while (it2.hasNext()) {
                                        arrayList2.add(Integer.valueOf(((Loc) it2.next()).getStartRow()));
                                    }
                                    TreeSet treeSet = new TreeSet();
                                    for (Pair pair : CollectionsKt___CollectionsKt.zip(arrayList2, arrayList)) {
                                        treeSet.addAll(CollectionsKt___CollectionsKt.toList(new IntRange(((Number) pair.getFirst()).intValue(), (((Number) pair.getSecond()).intValue() + ((Number) pair.getFirst()).intValue()) - 1, 1)));
                                    }
                                    if (treeSet.size() == 4) {
                                        oplusQSPersonalComponent.saveLatestNoEditModeLocList(allPersonalTilesLoc);
                                    } else {
                                        Iterable<IndexedValue> iterableWithIndex = CollectionsKt___CollectionsKt.withIndex(CollectionsKt___CollectionsKt.sorted(treeSet));
                                        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterableWithIndex, 10));
                                        if (iMapCapacity < 16) {
                                            iMapCapacity = 16;
                                        }
                                        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                                        for (IndexedValue indexedValue : iterableWithIndex) {
                                            Pair pair2 = TuplesKt.to(indexedValue.getValue(), Integer.valueOf(indexedValue.getIndex()));
                                            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
                                        }
                                        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                                        for (Loc loc : list3) {
                                            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(loc.getStartRow()));
                                            if (num != null) {
                                                int iIntValue = num.intValue();
                                                Loc loc2 = iIntValue == loc.getStartRow() ? loc : LocCache.INSTANCE.loc(loc.getPageIndex(), loc.getStartCol(), iIntValue);
                                                if (loc2 != null) {
                                                    loc = loc2;
                                                }
                                            }
                                            arrayList3.add(loc);
                                        }
                                        allPersonalTilesLoc = CollectionsKt___CollectionsKt.distinct(arrayList3);
                                        oplusQSPersonalComponent.saveLatestNoEditModeLocList(allPersonalTilesLoc);
                                    }
                                }
                                oplusQSPersonalComponent.getCustomLayoutManager().setLoc(allPersonalTilesLoc);
                                oplusQSPersonalComponent.getTileAdapter().submitList(normal.getAllPersonalTiles(), list2, allPersonalTilesLoc);
                                boolean z2 = false;
                                oplusQSPersonalComponent.getTileAdapter().setTileNameEnabled(false);
                                oplusQSPersonalComponent.getTileAdapter().updateEditMode$1(z);
                                EditTileAdapter tileAdapter = oplusQSPersonalComponent.getTileAdapter();
                                if (z) {
                                    OplusQSPersonalComponent.Companion.getClass();
                                    if (SystemProperties.getBoolean("debug.qs.tile.resize", false)) {
                                        z2 = true;
                                    }
                                }
                                tileAdapter.setResizeEnabled(z2);
                                OplusQSPersonalComponent.access$refreshEmptyCell(oplusQSPersonalComponent, list2, allPersonalTilesLoc, list, oplusQSPersonalComponent.getTileAdapter().getDataList());
                                if (!oplusQSPersonalComponent.getTileAdapter().isEditMode) {
                                    COUIRecyclerView cOUIRecyclerView = oplusQSPersonalComponent.recyclerView;
                                    OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1 = new OplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1(1);
                                    oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1.this$0 = oplusQSPersonalComponent;
                                    VarHandle.storeStoreFence();
                                    cOUIRecyclerView.addOnLayoutChangeListener(oplusQSPersonalComponent$updateBorderSize$$inlined$doOnLayout$1);
                                    OplusQSPersonalComponent.access$updateContainerRowWithReallyUsed(oplusQSPersonalComponent);
                                }
                                COUIRecyclerView cOUIRecyclerView2 = oplusQSPersonalComponent.recyclerView;
                                AnonymousClass3 anonymousClass3 = new AnonymousClass3();
                                anonymousClass3.this$0 = oplusQSPersonalComponent;
                                VarHandle.storeStoreFence();
                                cOUIRecyclerView2.post(anonymousClass3);
                                oplusQSPersonalComponent.updateRecyclerViewVisible(oplusQSPersonalComponent.getCustomLayoutManager().getLayoutConfig().getRowCount());
                            }
                            break;
                        default:
                            TileEditEffectWrapper tileEditEffectWrapper = (TileEditEffectWrapper) obj;
                            TileEditEffect effect = tileEditEffectWrapper.getEffect();
                            TileEditEffect.TransformToSpecEffect transformToSpecEffect = effect instanceof TileEditEffect.TransformToSpecEffect ? (TileEditEffect.TransformToSpecEffect) effect : null;
                            if (transformToSpecEffect != null) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    Log.d("SystemUi--QuickSettings", "OplusQSPersonalComponent-->".concat("[collect-uiEffectFlow] " + tileEditEffectWrapper));
                                }
                                OplusQSPersonalComponent.access$scrollToTarget(this.this$0, transformToSpecEffect.getTargetUniqueId(), transformToSpecEffect.getHighlight());
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00491(OplusQSPersonalComponent oplusQSPersonalComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSPersonalComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00491(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow uiStateFlow = this.this$0.editViewModel.getUiStateFlow();
                    OplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 = new OplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1(0);
                    oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1.$this_unsafeTransform$inlined = uiStateFlow;
                    VarHandle.storeStoreFence();
                    OplusQSPersonalComponent oplusQSPersonalComponent = this.this$0;
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
                    anonymousClass2.this$0 = oplusQSPersonalComponent;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (FlowExtKt.collectTraced(oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1, "OplusQSPersonalComponent-collect-uiStateFlow", anonymousClass2, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.component.OplusQSPersonalComponent$collectFlow$1$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusQSPersonalComponent this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(OplusQSPersonalComponent oplusQSPersonalComponent, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQSPersonalComponent;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                int i2 = 1;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SharedFlow uiEffectFlow = this.this$0.editViewModel.getUiEffectFlow();
                    OplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1 = new OplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1(1);
                    oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1.$this_unsafeTransform$inlined = uiEffectFlow;
                    VarHandle.storeStoreFence();
                    OplusQSPersonalComponent oplusQSPersonalComponent = this.this$0;
                    C00491.AnonymousClass2 anonymousClass2 = new C00491.AnonymousClass2(i2);
                    anonymousClass2.this$0 = oplusQSPersonalComponent;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (FlowExtKt.collectTraced(oplusQSPersonalComponent$collectFlow$1$1$1$invokeSuspend$$inlined$filter$1, "OplusQSPersonalComponent-collect-uiEffectFlow", anonymousClass2, this) == coroutine_suspended) {
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
        public AnonymousClass1(OplusQSPersonalComponent oplusQSPersonalComponent, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusQSPersonalComponent;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
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
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSPersonalComponent-launch-uiStateFlow", (CoroutineContext) null, (CoroutineStart) null, new C00491(this.this$0, null), 6);
            CoroutineTracingKt.launchTraced$default(coroutineScope, "OplusQSPersonalComponent-launch-uiEffectFlow", (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.this$0, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusQSPersonalComponent$collectFlow$1(OplusQSPersonalComponent oplusQSPersonalComponent, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusQSPersonalComponent;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusQSPersonalComponent$collectFlow$1 oplusQSPersonalComponent$collectFlow$1 = new OplusQSPersonalComponent$collectFlow$1(this.this$0, (Continuation) obj3);
        oplusQSPersonalComponent$collectFlow$1.L$0 = (LifecycleOwner) obj;
        return oplusQSPersonalComponent$collectFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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
