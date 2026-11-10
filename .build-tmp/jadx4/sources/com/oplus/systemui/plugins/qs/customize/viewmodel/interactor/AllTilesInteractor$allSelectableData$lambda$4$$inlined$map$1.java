package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.ResourcePreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.SeekBarPreviewData;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.TilePreviewData;
import com.oplus.systemui.plugins.qs.customize.viewmodel.AllTilesState;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.DialogPreviewConfig;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AllTilesInteractor$allSelectableData$lambda$4$$inlined$map$1 implements Flow {
    public /* synthetic */ AllTilesState $state$inlined;
    public /* synthetic */ Flow $this_unsafeTransform$inlined;
    public /* synthetic */ AllTilesInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor$allSelectableData$lambda$4$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ AllTilesState $state$inlined;
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ AllTilesInteractor this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor$allSelectableData$lambda$4$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0019  */
        public final Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            AllTilesInteractor allTilesInteractor = this.this$0;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                int i = anonymousClass1.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label = i - Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
            Object obj2 = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = anonymousClass1.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                List list = (List) obj;
                List<DialogPreviewConfig> dialogPreviewConfig = ((AllTilesState.Normal) this.$state$inlined).getDialogPreviewConfig();
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(dialogPreviewConfig, 10));
                for (DialogPreviewConfig dialogPreviewConfigCopy : dialogPreviewConfig) {
                    if (Intrinsics.areEqual(dialogPreviewConfigCopy.getTileType(), allTilesInteractor.contextInstance.getSysUiContext().getResources().getString(2131955235))) {
                        List mutableList = CollectionsKt___CollectionsKt.toMutableList(dialogPreviewConfigCopy.getTiles());
                        mutableList.addAll(list);
                        dialogPreviewConfigCopy = dialogPreviewConfigCopy.copy(dialogPreviewConfigCopy.tileType, mutableList);
                    }
                    arrayList.add(dialogPreviewConfigCopy);
                }
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj3 = arrayList.get(i3);
                    i3++;
                    if (!((DialogPreviewConfig) obj3).getTiles().isEmpty()) {
                        arrayList2.add(obj3);
                    }
                }
                allTilesInteractor.getClass();
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                int size2 = arrayList2.size();
                int i4 = 0;
                while (i4 < size2) {
                    Object obj4 = arrayList2.get(i4);
                    i4++;
                    DialogPreviewConfig dialogPreviewConfig2 = (DialogPreviewConfig) obj4;
                    List<GridData> tiles = dialogPreviewConfig2.getTiles();
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj5 : tiles) {
                        GridData gridData = (GridData) obj5;
                        BasePreviewData basePreviewData = (BasePreviewData) gridData.getData();
                        if (basePreviewData instanceof ResourcePreviewData) {
                            if (((ResourcePreviewData) gridData.getData()).getLabel().length() > 0) {
                                arrayList4.add(obj5);
                            }
                        } else if (!(basePreviewData instanceof SeekBarPreviewData)) {
                            TilePreviewData tilePreviewData = (TilePreviewData) gridData.getData();
                            if (tilePreviewData.getLabel().length() > 0 && tilePreviewData.getIcon() != null) {
                                arrayList4.add(obj5);
                            }
                        } else if (((SeekBarPreviewData) gridData.getData()).getLabel().length() > 0) {
                            arrayList4.add(obj5);
                        }
                    }
                    arrayList3.add(dialogPreviewConfig2.copy(dialogPreviewConfig2.tileType, arrayList4));
                }
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flowCollector.emit(arrayList3, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        AllTilesInteractor allTilesInteractor = this.this$0;
        AllTilesState allTilesState = this.$state$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.this$0 = allTilesInteractor;
        anonymousClass2.$state$inlined = allTilesState;
        VarHandle.storeStoreFence();
        Object objCollect = flow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
