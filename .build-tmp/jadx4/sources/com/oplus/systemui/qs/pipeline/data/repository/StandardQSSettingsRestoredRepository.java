package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredRepository;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.pipeline.data.event.QSBackupRestoreProxy;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StandardQSSettingsRestoredRepository implements QSSettingsRestoredRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StandardQSSettingsRestoredRepository";
    private final Flow restoreData;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StandardQSSettingsRestoredRepository(CoroutineScope coroutineScope, CoroutineContext coroutineContext, QSBackupRestoreProxy qSBackupRestoreProxy, QSPipelineLoggerEx qSPipelineLoggerEx) {
        this.restoreData = FlowKt.onEach(FlowKt.shareIn(FlowKt.buffer$default(10, 2, FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new StandardQSSettingsRestoredRepository$restoreData$1(qSBackupRestoreProxy, null)), coroutineContext)), coroutineScope, SharingStarted.Companion.getEagerly(), 0), new StandardQSSettingsRestoredRepository$restoreData$2(2, qSPipelineLoggerEx, QSPipelineLoggerEx.class, "logSettingsRestored", "logSettingsRestored(Lcom/android/systemui/qs/pipeline/data/model/RestoreData;)V", 4));
    }

    public Flow getRestoreData() {
        return this.restoreData;
    }
}
