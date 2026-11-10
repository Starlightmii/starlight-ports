package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.widget.Button;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "lockscreen";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LockTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237182);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mHost.getContext().getString(2131956560);
    }

    public void handleClick(Expandable expandable) {
        try {
            ((PowerManager) ((QSTileImpl) this).mContext.getSystemService("power")).goToSleep(SystemClock.uptimeMillis());
        } catch (Exception unused) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, ((QSTileImpl) this).TAG, "doLock error");
        }
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956560);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237182);
        ((QSTile.State) booleanState).state = 1;
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956560);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m323newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
