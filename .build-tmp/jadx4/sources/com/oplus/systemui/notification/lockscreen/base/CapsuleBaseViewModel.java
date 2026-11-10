package com.oplus.systemui.notification.lockscreen.base;

import androidx.lifecycle.ViewModel;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleBaseViewModel extends ViewModel implements OnBeforeRenderListListener {
    private final StateFlow dataFlow;
    private final MutableStateFlow dataFlowHolder;
    private final NotifPipeline pipeline;
    private final Lazy tag$delegate;

    /* JADX INFO: renamed from: $r8$lambda$-tTrrDU-dx8-cY363amU14V6lO8, reason: not valid java name */
    public static Unit m72$r8$lambda$tTrrDUdx8cY363amU14V6lO8(CapsuleBaseViewModel capsuleBaseViewModel, Object obj) {
        capsuleBaseViewModel.dataFlowHolder.setValue(obj);
        return Unit.INSTANCE;
    }

    public CapsuleBaseViewModel(NotifPipeline notifPipeline, Object obj) {
        this.pipeline = notifPipeline;
        CapsuleBaseViewModel$$ExternalSyntheticLambda0 capsuleBaseViewModel$$ExternalSyntheticLambda0 = new CapsuleBaseViewModel$$ExternalSyntheticLambda0();
        capsuleBaseViewModel$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.tag$delegate = LazyKt__LazyJVMKt.lazy(capsuleBaseViewModel$$ExternalSyntheticLambda0);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(obj);
        this.dataFlowHolder = MutableStateFlow;
        this.dataFlow = FlowKt.asStateFlow(MutableStateFlow);
        notifPipeline.addOnBeforeRenderListListener(this);
    }

    public final StateFlow getDataFlow() {
        return this.dataFlow;
    }

    public final MutableStateFlow getDataFlowHolder() {
        return this.dataFlowHolder;
    }

    public final String getTag() {
        return (String) this.tag$delegate.getValue();
    }

    public void onBeforeRenderList(List<PipelineEntry> list) {
        List<? extends PipelineEntry> mutableList = CollectionsKt___CollectionsKt.toMutableList(list);
        System.currentTimeMillis();
        CapsuleBaseViewModel$$ExternalSyntheticLambda1 capsuleBaseViewModel$$ExternalSyntheticLambda1 = new CapsuleBaseViewModel$$ExternalSyntheticLambda1();
        capsuleBaseViewModel$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        refreshCapsuleData(mutableList, capsuleBaseViewModel$$ExternalSyntheticLambda1);
        System.currentTimeMillis();
    }

    public abstract void refreshCapsuleData(List<? extends PipelineEntry> list, Function1 function1);
}
