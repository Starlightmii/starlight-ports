package com.oplus.systemui.qs.scenario.data.repository;

import android.util.Log;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.privacy.dialog.OplusQSPrivacyDialogController;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPrivacyRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSPrivacyRepository";
    private final Lazy privacyDialogController;
    private final StateFlow privacyElementList;
    private final StateFlow privacyItems;
    private final CoroutineScope scope;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSPrivacyRepository(PrivacyItemController privacyItemController, CoroutineScope coroutineScope, Lazy lazy) {
        this.scope = coroutineScope;
        this.privacyDialogController = lazy;
        Flow flowConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new OplusQSPrivacyRepository$privacyItems$1(privacyItemController, null));
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(flowConflatedCallbackFlow, coroutineScope, companion.getEagerly(), CollectionsKt__CollectionsKt.emptyList());
        this.privacyItems = stateFlowStateIn;
        OplusQSPrivacyRepository$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSPrivacyRepository$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.privacyElementList = FlowKt.stateIn(FlowKt.mapLatest(FlowKt.distinctUntilChanged(stateFlowStateIn, externalSyntheticLambda0), new privacyElementList.2(this, (Continuation) null)), coroutineScope, companion.getEagerly(), CollectionsKt__CollectionsKt.emptyList());
    }

    public static final List access$resolvePrivacyElementList(OplusQSPrivacyRepository oplusQSPrivacyRepository, List list) {
        oplusQSPrivacyRepository.getClass();
        if (list.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List privacyDialogElementList = ((OplusQSPrivacyDialogController) oplusQSPrivacyRepository.privacyDialogController.get()).getPrivacyDialogElementList();
        if (list.size() != privacyDialogElementList.size() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSPrivacyRepository-->".concat("resolvePrivacyElementList: count mismatch pic=" + list + ", pm=" + privacyDialogElementList));
        }
        return privacyDialogElementList;
    }

    public final StateFlow getPrivacyElementList() {
        return this.privacyElementList;
    }

    public final StateFlow getPrivacyItems() {
        return this.privacyItems;
    }
}
