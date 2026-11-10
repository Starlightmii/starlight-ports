package com.oplus.systemui.qs.pipeline.data.adapter;

import com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredBroadcastRepository;
import com.android.systemui.qs.pipeline.data.repository.QSSettingsRestoredRepository;
import com.oplus.systemui.qs.pipeline.data.repository.StandardQSSettingsRestoredRepository;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSettingsRestoredRepositoryAdapter implements QSSettingsRestoredRepository {
    private final Flow restoreData;

    public QSSettingsRestoredRepositoryAdapter(CoroutineScope coroutineScope, CoroutineContext coroutineContext, QSSettingsRestoredBroadcastRepository qSSettingsRestoredBroadcastRepository, StandardQSSettingsRestoredRepository standardQSSettingsRestoredRepository) {
        this.restoreData = FlowKt.shareIn(FlowKt.buffer$default(10, 2, FlowKt.flowOn(FlowKt.merge(new Flow[]{qSSettingsRestoredBroadcastRepository.getRestoreData(), standardQSSettingsRestoredRepository.getRestoreData()}), coroutineContext)), coroutineScope, SharingStarted.Companion.getEagerly(), 0);
    }

    public Flow getRestoreData() {
        return this.restoreData;
    }
}
