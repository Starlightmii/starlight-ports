package com.oplus.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import androidx.car.app.serialization.Bundler$;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiSystemTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "multisystem";
    private final KeyguardRepository keyguardRepository;
    private final PanelInteractor panelInteractor;
    private final Runnable userSwitchRunnable;
    private final UserSwitcherInteractor userSwitcherInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MultiSystemTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardRepository keyguardRepository, UserSwitcherInteractor userSwitcherInteractor, PanelInteractor panelInteractor) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.keyguardRepository = keyguardRepository;
        this.userSwitcherInteractor = userSwitcherInteractor;
        this.panelInteractor = panelInteractor;
        MultiSystemTile$userSwitchRunnable$1 multiSystemTile$userSwitchRunnable$1 = new MultiSystemTile$userSwitchRunnable$1();
        multiSystemTile$userSwitchRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userSwitchRunnable = multiSystemTile$userSwitchRunnable$1;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237191);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 156;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956562);
    }

    public void handleClick(Expandable expandable) {
        if (((QSTile.State) getState()).state == 0) {
            return;
        }
        this.panelInteractor.forceCollapsePanels();
        ((QSTileImpl) this).mClickHandler.postDelayed(this.userSwitchRunnable, 500L);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean zIsKeyguardShowing = this.keyguardRepository.isKeyguardShowing();
        ((QSTile.State) booleanState).icon = zIsKeyguardShowing ? QSTileImpl.ResourceIcon.get(2131237192) : QSTileImpl.ResourceIcon.get(2131237191);
        ((QSTile.State) booleanState).state = !zIsKeyguardShowing ? 1 : 0;
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956562);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956562);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
    }

    public final boolean isAvailable() {
        int userId = getHost().getUserId();
        boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(userId);
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("isAvailable: isInMultiSystem=" + zIsMultiSystemUserId + ", currentUser=" + userId);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        return zIsMultiSystemUserId;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m324newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
