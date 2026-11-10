package com.oplus.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.qs.external.InnerCustomTileConnection;
import com.oplus.systemui.qs.utils.QsUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InputMethodTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "inputmethod";
    private InnerCustomTileConnection customTileConnection;
    public final ComponentName mComponentName;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public InputMethodTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, Context context, InnerCustomTileConnection.Factory factory) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        ComponentName componentFromSpec = CustomTile.getComponentFromSpec("custom(com.android.settings/com.oplus.settings.feature.display.video.InputMethodTile)");
        this.mComponentName = componentFromSpec;
        this.customTileConnection = factory.create(TILE_SPEC, componentFromSpec, context.getUser());
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237498);
    }

    public Intent getLongClickIntent() {
        Intent intentResolveIntent = QsUtil.resolveIntent(((QSTileImpl) this).mContext, new Intent("android.service.quicksettings.action.QS_TILE_PREFERENCES").setPackage(this.mComponentName.getPackageName()));
        if (intentResolveIntent == null) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mComponentName.getPackageName(), null));
        }
        intentResolveIntent.putExtra("android.intent.extra.COMPONENT_NAME", this.mComponentName);
        return intentResolveIntent;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getResources().getString(2131953594);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        this.customTileConnection.onClick();
    }

    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        this.customTileConnection.setListening(z);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).state = 1;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237498);
        String string = ((QSTileImpl) this).mContext.getResources().getString(2131953594);
        ((QSTile.State) booleanState).label = string;
        ((QSTile.State) booleanState).contentDescription = string;
    }

    public final boolean isAvailable() {
        return this.customTileConnection.checkComponentState();
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m321newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
