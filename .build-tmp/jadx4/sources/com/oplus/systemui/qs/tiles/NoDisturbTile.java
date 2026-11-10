package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.UserSettingObserver;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NoDisturbTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "nodisturb";
    private final NoDisturbTile$setting$1 setting;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.qs.tiles.NoDisturbTile$setting$1] */
    public NoDisturbTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, final SecureSettings secureSettings) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        final QSTileImpl.H h = ((QSTileImpl) this).mHandler;
        final int userId = qSHost.getUserContext().getUserId();
        this.setting = new UserSettingObserver(secureSettings, h, userId) { // from class: com.oplus.systemui.qs.tiles.NoDisturbTile$setting$1
            public final void handleValueChanged(int i, boolean z) {
                LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "handleValueChanged: value = "), null, 8);
                this.refreshState((Object) null);
            }
        };
    }

    private final void setNoDisturb(int i) {
        setValue(i);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237199);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956564);
    }

    public void handleClick(Expandable expandable) {
        setNoDisturb((getValue() == 1 ? 1 : 0) ^ 1);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        setListening(false);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        setListening(true);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131956564);
        boolean z = getValue() == 1;
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(z ? 2131237198 : 2131237199);
        ((QSTile.State) booleanState).labelDesc = (((QSTile.State) booleanState).state == 2 && QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS()) ? ((QSTileImpl) this).mContext.getString(2131955246) : "";
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956564);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        setUserId(i);
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m325newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
