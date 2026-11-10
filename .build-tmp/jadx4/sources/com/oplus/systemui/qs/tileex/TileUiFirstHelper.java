package com.oplus.systemui.qs.tileex;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import androidx.compose.material.ripple.Ripple_androidKt$;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tile.QSTileEx;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileUiFirstHelper implements TileUiFirst {
    public static final TileUiFirstHelper INSTANCE = new TileUiFirstHelper();

    public static String getLogTag(QSTile qSTile) {
        return FileProvider$.ExternalSyntheticOutline0.m("TileUiFirst_", qSTile.getTileSpec());
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public boolean handleClick(QSTile qSTile, Message message, Handler handler) {
        QSTileEx.UiState uiState;
        Object obj = qSTile.getState().uiState;
        QSTileEx.UiState uiState2 = QSTileEx.UiState.ON;
        if (obj == uiState2) {
            uiState = QSTileEx.UiState.OFF;
        } else {
            uiState = obj == QSTileEx.UiState.OFF ? uiState2 : null;
        }
        if (uiState == null) {
            int i = qSTile.getState().state;
            if (i != 1) {
                uiState2 = i != 2 ? null : QSTileEx.UiState.OFF;
            }
        } else {
            uiState2 = uiState;
        }
        return handleUserOperation(qSTile, uiState2, handler);
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void handleDetailToggle(QSTile qSTile, Message message, Handler handler) {
        handleUserOperation(qSTile, message.obj, handler);
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void handleFwStateChange(QSTile qSTile, Message message, Handler handler) {
        Object obj = qSTile.getState().fwState;
        Object obj2 = qSTile.getState().uiState;
        boolean z = qSTile.getState().pendingClick;
        LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "handleFwStateChange: fwState=" + obj + ", uiState=" + obj2 + ", pendingClick=" + z, null, 8);
        if (!z || Intrinsics.areEqual(obj2, obj)) {
            setUiFirstRunning(qSTile, false, handler);
        } else {
            qSTile.getState().pendingClick = false;
            setUiFirstRunning(qSTile, qSTile.handleUserOperation(obj2), handler);
        }
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void handleRefreshState(QSTile qSTile, Message message, Handler handler) {
        Object obj = qSTile.getState().uiState;
        if (obj == null || obj.equals(message.obj)) {
            return;
        }
        if (message.obj != null) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "handleRefreshState: change " + message.obj + " to " + obj + " when UiFirst", null, 8);
        }
        message.obj = obj;
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void handleSetListening(QSTile qSTile, boolean z, Handler handler) {
        if (z || !isUiFirstRunning(handler)) {
            return;
        }
        Object obj = qSTile.getState().uiState;
        Object obj2 = qSTile.getState().fwState;
        boolean z2 = qSTile.getState().pendingClick;
        LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "handleListening: uiState=" + obj + ", fwState=" + obj2 + ", pendingClick=" + z2, null, 8);
        if (z2 && obj != null && !obj.equals(obj2)) {
            qSTile.handleUserOperation(obj);
        }
        setUiFirstRunning(qSTile, false, handler);
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void handleUiFirstTimeout(QSTile qSTile, Message message, Handler handler) {
        LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "handleUiFirstTimeout: ", null, 8);
        setUiFirstRunning(qSTile, false, handler);
    }

    public final boolean handleUserOperation(QSTile qSTile, Object obj, Handler handler) {
        int i = qSTile.getState().state;
        QSTileEx.Companion.getClass();
        if (i != 2 && i != 1) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), ActionsConstraints$.ExternalSyntheticOutline0.m(i, "handleUserOperation: tile.state(", ") illegal, ignore"), null, 8);
            return false;
        }
        if (obj == null) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "handleUserOperation: nextUiState is null, ignore", null, 8);
            return false;
        }
        if (isUiFirstRunning(handler)) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), Ripple_androidKt$.ExternalSyntheticOutline0.m(obj, "handleUserOperation: pending nextUiState=", " when UiFirst running"), null, 8);
            qSTile.getState().uiState = obj;
            qSTile.getState().pendingClick = true;
            setUiFirstRunning(qSTile, true, handler);
            return true;
        }
        if (!qSTile.handleUserOperation(obj)) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), Ripple_androidKt$.ExternalSyntheticOutline0.m(obj, "handleUserOperation: abandon nextUiState=", " when operation not allowed"), null, 8);
            return true;
        }
        LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), Ripple_androidKt$.ExternalSyntheticOutline0.m(obj, "handleUserOperation: perform nextUiState=", " "), null, 8);
        qSTile.getState().uiState = obj;
        setUiFirstRunning(qSTile, true, handler);
        return true;
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public boolean isUiFirstRunning(Handler handler) {
        return handler.hasMessages(1001);
    }

    @Override // com.oplus.systemui.qs.tileex.TileUiFirst
    public void onTileUnavailable(QSTile qSTile, Handler handler) {
        if (isUiFirstRunning(handler)) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "onTileUnavailable: stop UiFirst", null, 8);
            setUiFirstRunning(qSTile, false, handler);
        }
    }

    public final void setUiFirstRunning(QSTile qSTile, boolean z, Handler handler) {
        boolean zIsUiFirstRunning = isUiFirstRunning(handler);
        if (z && !zIsUiFirstRunning) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "set UiFirst start", null, 8);
        } else if (!z && zIsUiFirstRunning) {
            LogUtil.i$default(LogUtil.TAG_QS, getLogTag(qSTile), "set UiFirst end", null, 8);
        }
        if (zIsUiFirstRunning) {
            handler.removeMessages(1001);
        }
        if (z) {
            handler.sendEmptyMessageDelayed(1001, 10000L);
        } else {
            qSTile.getState().pendingClick = false;
            qSTile.getState().uiState = null;
        }
        qSTile.refreshState();
    }
}
