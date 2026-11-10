package com.oplus.systemui.qs.tiles;

import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.DeviceControlsTile;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDeviceControlsTile extends DeviceControlsTile {
    private final ControlsComponent controlsComponent;

    public OplusDeviceControlsTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ControlsComponent controlsComponent, PanelInteractor panelInteractor) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, controlsComponent, panelInteractor);
        this.controlsComponent = controlsComponent;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237145);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.State state, Object obj) {
        state.label = getTileLabel();
        state.functionName.setMainText(getTileLabel());
        state.contentDescription = state.label;
        if (!this.controlsComponent.featureEnabled || !getHasControlsApps().get()) {
            state.state = 0;
            state.icon = QSTileImpl.ResourceIcon.get(2131237146);
            return;
        }
        if (this.controlsComponent.getVisibility() == ControlsComponent.Visibility.AVAILABLE) {
            SelectedItem.StructureItem preferredSelection = ((ControlsController) this.controlsComponent.getControlsController().get()).getPreferredSelection();
            state.state = ((preferredSelection instanceof SelectedItem.StructureItem) && preferredSelection.getStructure().getControls().isEmpty()) ? 1 : 2;
            CharSequence name = preferredSelection.getName();
            if (Intrinsics.areEqual(name, getTileLabel())) {
                name = null;
            }
            state.secondaryLabel = name;
            state.icon = QSTileImpl.ResourceIcon.get(2131237144);
        } else {
            state.state = 1;
            state.secondaryLabel = ((QSTileImpl) this).mContext.getText(2131952820);
            state.icon = QSTileImpl.ResourceIcon.get(2131237145);
        }
        state.stateDescription = state.secondaryLabel;
    }

    public final boolean isAvailable() {
        return this.controlsComponent.getControlsController().isPresent();
    }

    public QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.state = 0;
        state.toggleable = true;
        return state;
    }
}
