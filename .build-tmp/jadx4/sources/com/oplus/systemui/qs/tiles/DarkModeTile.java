package com.oplus.systemui.qs.tiles;

import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DarkModeTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "darkmode";
    private final ConfigurationController configurationController;
    private final DarkModeTile$configurationListener$1 configurationListener;
    private final KeyguardRepository keyguardRepository;
    private final NotificationPanelViewController notifiPanelController;
    private final Runnable refreshRunnable;
    private final Lazy separateNotificationAndQSEx;
    private final OplusSeparateQSManager separateQSManager;
    public final ComponentName tileComponent;
    private final UiModeManager uiModeManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DarkModeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, OplusSeparateQSManager oplusSeparateQSManager, NotificationPanelViewController notificationPanelViewController, Lazy lazy, KeyguardRepository keyguardRepository, ConfigurationController configurationController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.separateQSManager = oplusSeparateQSManager;
        this.notifiPanelController = notificationPanelViewController;
        this.separateNotificationAndQSEx = lazy;
        this.keyguardRepository = keyguardRepository;
        this.configurationController = configurationController;
        Object systemService = ((QSTileImpl) this).mContext.getSystemService("uimode");
        this.uiModeManager = systemService instanceof UiModeManager ? (UiModeManager) systemService : null;
        this.tileComponent = CustomTile.getComponentFromSpec("custom(com.android.settings/com.oplus.settings.feature.display.darkmode.DarkModeTileService)");
        DarkModeTile$configurationListener$1 darkModeTile$configurationListener$1 = new DarkModeTile$configurationListener$1();
        darkModeTile$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = darkModeTile$configurationListener$1;
        DarkModeTile$refreshRunnable$1 darkModeTile$refreshRunnable$1 = new DarkModeTile$refreshRunnable$1();
        darkModeTile$refreshRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.refreshRunnable = darkModeTile$refreshRunnable$1;
    }

    private final void setSystemDarkMode(boolean z) {
        UiModeManager uiModeManager = this.uiModeManager;
        if (uiModeManager != null) {
            uiModeManager.setNightModeActivated(z);
        }
    }

    public void addCallback(QSTile.Callback callback) {
        refreshState((Object) null);
        super.addCallback(callback);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131233237);
    }

    public Intent getLongClickIntent() {
        Intent intentResolveIntent = QsUtil.resolveIntent(((QSTileImpl) this).mContext, new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES").setPackage(this.tileComponent.getPackageName()));
        if (intentResolveIntent == null) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.tileComponent.getPackageName(), null));
        }
        intentResolveIntent.putExtra("android.intent.extra.COMPONENT_NAME", this.tileComponent);
        return intentResolveIntent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131952938);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0 || QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_DARK_MODE)) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("handleClick: darkModeTile state: ", ((QSTile.AdapterState) getState()).value), null, 8);
        setSystemDarkMode(!((QSTile.AdapterState) getState()).value);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        this.configurationController.removeCallback(this.configurationListener);
    }

    public final void handleInitialize() {
        this.configurationController.addCallback(this.configurationListener);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.AdapterState) booleanState).value = ((QSTileImpl) this).mContext.getResources().getConfiguration().isNightModeActive();
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mContext.getString(2131952938);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131233238 : 2131233237);
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m317newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLACK_COLOR;
        ((QSTile.State) booleanState).toggleable = true;
        return booleanState;
    }
}
