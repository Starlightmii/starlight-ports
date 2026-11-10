package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.QRCodeScannerTile;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQRCodeScannerTile extends QRCodeScannerTile {
    private final QRCodeScannerController qrCodeScannerController;

    public OplusQRCodeScannerTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, QRCodeScannerController qRCodeScannerController) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, qRCodeScannerController);
        this.qrCodeScannerController = qRCodeScannerController;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131234424);
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131955182);
    }

    public void handleClick(Expandable expandable) {
        Intent intent = this.qrCodeScannerController.getIntent();
        if (intent == null) {
            throw new IncompatibleClassChangeError();
        }
        QSTileEx tileEx = getTileEx();
        QSTileEx.Companion companion = QSTileEx.Companion;
        tileEx.postStartActivityDismissingKeyguard(expandable, intent, false);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.State state, Object obj) {
        String string = ((QSTileImpl) this).mContext.getString(2131955182);
        state.label = string;
        state.contentDescription = string;
        state.icon = QSTileImpl.ResourceIcon.get(2131234424);
        state.state = 1;
        state.secondaryLabel = ((QSTileImpl) this).mContext.getString(2131955181);
    }

    public final boolean isAvailable() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion() && this.qrCodeScannerController.isCameraAvailable();
    }

    public QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.handlesLongClick = true;
        state.toggleable = false;
        return state;
    }
}
