package com.oplus.systemui.qs.pipeline.data.event;

import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BroadcastModifyTileEvent implements TileModifyEvent {
    public static final Companion Companion = new Companion();
    private static final String HIDE_EXTRA = "hide";
    private static final String HIDE_TILES_SETTING = "op_sysui_qs_tiles_hide";
    private static final String INTENT_HIDE_TILE = "com.oplus.systemui.qs.hide_tile";
    private static final String POS_EXTRA = "position";
    private static final String TAG = "BroadcastModifyTileEvent";
    private static final String TITLE_EXTRA = "tile";
    private final CoroutineContext bgCoroutineContext;
    private final BroadcastDispatcher broadcastDispatcher;
    private final CopyOnWriteArrayList<TileSpec> hideTilesList = new CopyOnWriteArrayList<>();
    private final SecureSettings secureSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BroadcastModifyTileEvent(CoroutineContext coroutineContext, SecureSettings secureSettings, BroadcastDispatcher broadcastDispatcher) {
        this.bgCoroutineContext = coroutineContext;
        this.secureSettings = secureSettings;
        this.broadcastDispatcher = broadcastDispatcher;
    }

    public static final List access$loadHideTiles(BroadcastModifyTileEvent broadcastModifyTileEvent, int i) {
        TilesSettingConverter tilesSettingConverter = TilesSettingConverter.INSTANCE;
        String stringForUser = broadcastModifyTileEvent.secureSettings.getStringForUser(HIDE_TILES_SETTING, i);
        if (stringForUser == null) {
            stringForUser = "";
        }
        return tilesSettingConverter.toTilesList(stringForUser);
    }

    @Override // com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent
    public Flow modifyTilesSignal(int i) {
        return (CustomizeFeatureOption.sIsTmoSignalViewLayout || CustomizeFeatureOption.sIsSprintSignalViewLayout) ? FlowKt.flowOn(FlowKt.onStart(FlowKt.onEach(BroadcastDispatcher.broadcastFlow$default(this.broadcastDispatcher, new IntentFilter(INTENT_HIDE_TILE), UserHandle.of(i), 0, new BroadcastModifyTileEvent$.ExternalSyntheticLambda0(), 12), new modifyTilesSignal.2(this, i, (Continuation) null)), new modifyTilesSignal.3(this, i, (Continuation) null)), this.bgCoroutineContext) : FlowKt.emptyFlow();
    }
}
