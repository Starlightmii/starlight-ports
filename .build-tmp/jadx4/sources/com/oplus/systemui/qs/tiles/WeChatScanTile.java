package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplusos.systemui.common.utils.AppInfoUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WeChatScanTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "weixinscan";
    private final String extraKey;
    private final String wechatScanAction;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public WeChatScanTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.wechatScanAction = ((QSTileImpl) this).mContext.getString(2131957256);
        this.extraKey = ((QSTileImpl) this).mContext.getString(2131957254);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237241);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 119;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956575);
    }

    public void handleClick(Expandable expandable) {
        Intent intent = new Intent();
        intent.setAction(this.wechatScanAction);
        Bundle bundle = new Bundle();
        bundle.putBoolean(this.extraKey, true);
        intent.putExtras(bundle);
        intent.addFlags(335544320);
        QSTileEx tileEx = getTileEx();
        QSTileEx.Companion companion = QSTileEx.Companion;
        tileEx.postStartActivityDismissingKeyguard(expandable, intent, false);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956575);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237241);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956575);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = 1;
    }

    public final boolean isAvailable() {
        Context context = ((QSTileImpl) this).mContext;
        return AppInfoUtil.isPackageExist(context, context.getString(2131957255));
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m349newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
