package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.globalactions.GlobalActionsComponent;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusShutDownTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    private static final String SHUT_DOWN_SETTING_ACTION = "oplus.intent.action.settings.AI_POWER";
    private static final String SHUT_DOWN_SETTING_PACKAGE = "com.android.settings";
    public static final String TILE_SPEC = "shutdown";
    private final GlobalActionsComponent globalActionsComponent;
    private final Runnable shutDownRunnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusShutDownTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, GlobalActionsComponent globalActionsComponent) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.globalActionsComponent = globalActionsComponent;
        OplusShutDownTile$shutDownRunnable$1 oplusShutDownTile$shutDownRunnable$1 = new OplusShutDownTile$shutDownRunnable$1();
        oplusShutDownTile$shutDownRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.shutDownRunnable = oplusShutDownTile$shutDownRunnable$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131236025);
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent(SHUT_DOWN_SETTING_ACTION);
        intent.setFlags(268435456);
        intent.setPackage(SHUT_DOWN_SETTING_PACKAGE);
        return intent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955243);
    }

    public void handleClick(Expandable expandable) {
        ((QSTileImpl) this).mUiHandler.post(this.shutDownRunnable);
    }

    public void handleLongClick(Expandable expandable) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            ((QSTileImpl) this).mUiHandler.post(this.shutDownRunnable);
        } else {
            super.handleLongClick(expandable);
        }
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131955243);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131236025);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131955243);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = 1;
    }

    public final boolean isAvailable() {
        return true;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m339newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        ((QSTile.State) booleanState).state = 1;
        return booleanState;
    }
}
