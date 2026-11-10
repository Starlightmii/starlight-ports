package com.oplus.systemui.statusbar.phone.fragment;

import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.deviceentry.data.repository.DeviceEntryFaceAuthRepositoryImpl$;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.statusbar.controller.SystemPromptController;
import com.oplus.systemui.statusbar.seeding.CapsuleContainerStateListener;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CollapsedStatusBarViewModelEx implements CapsuleContainerStateListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CollapsedStatusBarViewModelEx";
    private final MutableStateFlow _shouldShowPromptView;
    public boolean hasPrompt;
    public boolean hasSystemCapsule;
    private final StateFlow miniCapsuleShowing;
    private final SeedlingPluginManager seedlingPluginManager;
    private final StateFlow shouldShowPromptView;
    private final SystemPromptController systemPromptController;
    private final CollapsedStatusBarViewModelEx$systemPromptListener$1 systemPromptListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CollapsedStatusBarViewModelEx(SystemPromptController systemPromptController, OplusFlavorManager oplusFlavorManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SeedlingPluginManager seedlingPluginManager) {
        this.systemPromptController = systemPromptController;
        this.seedlingPluginManager = seedlingPluginManager;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._shouldShowPromptView = MutableStateFlow;
        Flow flowOnEach = FlowKt.onEach(MutableStateFlow, new CollapsedStatusBarViewModelEx$shouldShowPromptView$1(2, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.shouldShowPromptView = FlowKt.stateIn(flowOnEach, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        this.miniCapsuleShowing = FlowKt.stateIn(FlowKt.onEach(FlowConflatedKt.conflatedCallbackFlow(new CollapsedStatusBarViewModelEx$miniCapsuleShowing$1(oplusFlavorManager, null)), new CollapsedStatusBarViewModelEx$miniCapsuleShowing$2(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), bool);
        CollapsedStatusBarViewModelEx$systemPromptListener$1 collapsedStatusBarViewModelEx$systemPromptListener$1 = new CollapsedStatusBarViewModelEx$systemPromptListener$1();
        collapsedStatusBarViewModelEx$systemPromptListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.systemPromptListener = collapsedStatusBarViewModelEx$systemPromptListener$1;
        systemPromptController.addCallback(collapsedStatusBarViewModelEx$systemPromptListener$1);
        seedlingPluginManager.setCapsuleListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHasPrompt(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("setHasPrompt: value=", " old=", "CollapsedStatusBarViewModelEx-->", z, this.hasPrompt));
        }
        if (this.hasPrompt != z) {
            this.hasPrompt = z;
            this._shouldShowPromptView.setValue(Boolean.valueOf(z && !this.hasSystemCapsule));
        }
    }

    private final void setHasSystemCapsule(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("setSystemCapsule: value=", " old=", "CollapsedStatusBarViewModelEx-->", z, this.hasSystemCapsule));
        }
        if (this.hasSystemCapsule != z) {
            this.hasSystemCapsule = z;
            this._shouldShowPromptView.setValue(Boolean.valueOf(this.hasPrompt && !z));
        }
    }

    public final void dump(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.println(TAG);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    hasPrompt: ", this.hasPrompt);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    hasSystemCapsule: ", this.hasSystemCapsule);
        }
        if (printWriter != null) {
            DeviceEntryFaceAuthRepositoryImpl$.ExternalSyntheticOutline0.m("    shouldShowPromptView: ", this.shouldShowPromptView.getValue(), printWriter);
        }
    }

    public final StateFlow getMiniCapsuleShowing() {
        return this.miniCapsuleShowing;
    }

    public final StateFlow getShouldShowPromptView() {
        return this.shouldShowPromptView;
    }

    public final void onSystemCapsuleChanged(boolean z) {
        setHasSystemCapsule(z);
    }
}
