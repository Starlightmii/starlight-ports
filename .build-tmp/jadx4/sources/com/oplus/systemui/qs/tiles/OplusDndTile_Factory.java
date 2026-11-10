package com.oplus.systemui.qs.tiles;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusDndTile_Factory implements Factory {
    public static OplusDndTile newInstance(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ZenModeController zenModeController, SharedPreferences sharedPreferences, SecureSettings secureSettings, DialogTransitionAnimator dialogTransitionAnimator, OplusQSDetailInteractor.Factory factory, SelectedUserInteractor selectedUserInteractor) {
        return new OplusDndTile(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, zenModeController, sharedPreferences, secureSettings, dialogTransitionAnimator, factory, selectedUserInteractor);
    }
}
