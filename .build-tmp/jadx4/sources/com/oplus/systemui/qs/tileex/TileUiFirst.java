package com.oplus.systemui.qs.tileex;

import android.os.Handler;
import android.os.Message;
import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface TileUiFirst {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Default implements TileUiFirst {
        public static final Default INSTANCE = new Default();

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ boolean handleClick(QSTile qSTile, Message message, Handler handler) {
            return super.handleClick(qSTile, message, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void handleDetailToggle(QSTile qSTile, Message message, Handler handler) {
            super.handleDetailToggle(qSTile, message, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void handleFwStateChange(QSTile qSTile, Message message, Handler handler) {
            super.handleFwStateChange(qSTile, message, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void handleRefreshState(QSTile qSTile, Message message, Handler handler) {
            super.handleRefreshState(qSTile, message, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void handleSetListening(QSTile qSTile, boolean z, Handler handler) {
            super.handleSetListening(qSTile, z, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void handleUiFirstTimeout(QSTile qSTile, Message message, Handler handler) {
            super.handleUiFirstTimeout(qSTile, message, handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ boolean isUiFirstRunning(Handler handler) {
            return super.isUiFirstRunning(handler);
        }

        @Override // com.oplus.systemui.qs.tileex.TileUiFirst
        public /* bridge */ /* synthetic */ void onTileUnavailable(QSTile qSTile, Handler handler) {
            super.onTileUnavailable(qSTile, handler);
        }
    }

    default boolean handleClick(QSTile qSTile, Message message, Handler handler) {
        return false;
    }

    default void handleDetailToggle(QSTile qSTile, Message message, Handler handler) {
    }

    default void handleFwStateChange(QSTile qSTile, Message message, Handler handler) {
    }

    default void handleRefreshState(QSTile qSTile, Message message, Handler handler) {
    }

    default void handleSetListening(QSTile qSTile, boolean z, Handler handler) {
    }

    default void handleUiFirstTimeout(QSTile qSTile, Message message, Handler handler) {
    }

    default boolean isUiFirstRunning(Handler handler) {
        return false;
    }

    default void onTileUnavailable(QSTile qSTile, Handler handler) {
    }
}
