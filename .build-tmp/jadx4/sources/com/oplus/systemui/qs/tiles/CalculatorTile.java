package com.oplus.systemui.qs.tiles;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
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
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CalculatorTile extends QSTileImpl {
    private static final String CALCULATOR_ACTION = "oplus.intent.action.CALCULATOR";
    private static final String CALCULATOR_ACTIVITY = "com.android.calculator2.CalculatorTile";
    public static final Companion Companion = new Companion();
    private static final String KEY_ZOOM_LAUNCH_FLAG = "android:activity.mZoomLaunchFlags";
    public static final String TILE_SPEC = "calculator";
    private final KeyguardStateController keyguard;
    private final PanelInteractor panelInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isIntentAvailable(Context context, Intent intent) {
            return context.getPackageManager().queryIntentActivities(intent, 1).size() > 0;
        }
    }

    public CalculatorTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardStateController keyguardStateController, PanelInteractor panelInteractor) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.keyguard = keyguardStateController;
        this.panelInteractor = panelInteractor;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237130);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956553);
    }

    public void handleClick(Expandable expandable) {
        Intent intent = new Intent();
        intent.setAction(CALCULATOR_ACTION);
        if (!Companion.isIntentAvailable(((QSTileImpl) this).mContext, intent)) {
            intent.setAction(null);
            intent.setComponent(new ComponentName(((QSTileImpl) this).mContext, ((QSTileImpl) this).mContext.getString(2131952540)));
        }
        intent.putExtra("source", "from_control_center");
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguard;
        if (!keyguardStateControllerImpl.mShowing) {
            intent.putExtra("start_mode", "start_mode_float_window");
            intent.setFlags(268435456);
        } else {
            if (keyguardStateControllerImpl.mSecure) {
                intent.putExtra("comeFrom", "mFromScreen");
                intent.setFlags(880836608);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, ((QSTileImpl) this).TAG, "openCalculator  comeFrom  mFromScreen", null, 8);
                Bundle bundle = ActivityOptions.makeCustomAnimation(((QSTileImpl) this).mContext, 0, 0).toBundle();
                if (bundle != null) {
                    bundle.putInt(KEY_ZOOM_LAUNCH_FLAG, 8);
                }
                ((QSTileImpl) this).mContext.startActivityAsUser(intent, bundle, UserHandle.CURRENT);
                this.panelInteractor.collapsePanels();
                return;
            }
            intent.addFlags(872415232);
            intent.putExtra("comeFrom", "mFromDesktop").getClass();
        }
        QSTileEx tileEx = getTileEx();
        QSTileEx.Companion companion = QSTileEx.Companion;
        tileEx.postStartActivityDismissingKeyguard(expandable, intent, false);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956553);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237130);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956553);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = 1;
    }

    public final boolean isAvailable() {
        Context context = ((QSTileImpl) this).mContext;
        return AppInfoUtil.isPackageExist(context, context.getString(2131952540));
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m314newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
