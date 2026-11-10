package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import androidx.car.app.serialization.Bundler$;
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
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CleanTile extends QSTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "clear";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CleanTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237136);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956556);
    }

    public void handleClick(Expandable expandable) {
        Intent intent = new Intent();
        intent.setAction(((QSTileImpl) this).mContext.getString(2131955405));
        intent.addFlags(268468224);
        intent.putExtra("enter_from", ((QSTileImpl) this).mContext.getPackageName());
        QSTileEx tileEx = getTileEx();
        QSTileEx.Companion companion = QSTileEx.Companion;
        tileEx.postStartActivityDismissingKeyguard(expandable, intent, false);
    }

    public void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131956556);
        ((QSTile.AdapterState) booleanState).value = true;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237136);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131956556);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = 1;
    }

    public final boolean isAvailable() {
        boolean zIsPhonecleanAvailable = isPhonecleanAvailable(((QSTileImpl) this).mContext);
        String str = ((QSTileImpl) this).TAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("CleanTile isAvailable: ", "SystemUi--Statusbar", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), zIsPhonecleanAvailable);
        }
        return zIsPhonecleanAvailable;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    public final boolean isPhonecleanAvailable(Context context) {
        if (context.getString(2131955406).length() > 0) {
            Intent intent = new Intent();
            BrandUtils.INSTANCE.getClass();
            if (BrandUtils.isOneplus) {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isExpRegion()) {
                    intent.setPackage(context.getString(2131955407));
                } else {
                    intent.setPackage(context.getString(2131955406));
                }
            } else {
                intent.setPackage(context.getString(2131955406));
            }
            intent.setAction(context.getString(2131955405));
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("CleanTile resolveInfoList: " + listQueryIntentActivities);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            if (listQueryIntentActivities.size() > 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m316newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = false;
        return booleanState;
    }
}
