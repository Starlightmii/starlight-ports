package com.oplus.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.keyguard.smartlock.OplusSmartlockController$mSmartLockCallback$1$;
import com.oplus.systemui.notification.envelope.EnvelopeHelper;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EnvelopeTile extends QSTileImpl {
    private static final String ACTION_ENVELOPE = "oplus.intent.action.ENVELOP_SETTINGS";
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "envelope";
    private Lazy mEnvelopeHelper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EnvelopeTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237206);
    }

    public Intent getLongClickIntent() {
        return new Intent(ACTION_ENVELOPE);
    }

    public final Lazy getMEnvelopeHelper() {
        return this.mEnvelopeHelper;
    }

    public int getMetricsCategory() {
        return 268;
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131953160);
    }

    public void handleClick(Expandable expandable) {
        Lazy lazy = this.mEnvelopeHelper;
        if ((lazy != null ? (EnvelopeHelper) lazy.get() : null) == null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, ((QSTileImpl) this).TAG, "EnvelopeManager is null", null, 8);
            return;
        }
        int i = !((QSTile.AdapterState) ((QSTileImpl) this).mState).value ? 1 : 0;
        AbsSettingsValueProxy.setSecureIntValue(((QSTileImpl) this).mContext, "envelope_assistant_enable", i);
        Context context = ((QSTileImpl) this).mContext;
        HashMap mapM = OplusSmartlockController$mSmartLockCallback$1$.ExternalSyntheticOutline0.m("envelope_option", "envelope_tile_option");
        mapM.put("option_status", String.valueOf(i));
        if (LogUtil.getNormalDebug()) {
            for (Map.Entry entry : mapM.entrySet()) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeStatisticUtil", "key =" + ((String) entry.getKey()) + " value = " + ((String) entry.getValue()));
            }
        }
        OplusTrackProxyUtil.onCommon(context, "20082", "envelope_settings_status", mapM);
    }

    public final void handleDestroy() {
        EnvelopeHelper envelopeHelper;
        super.handleDestroy();
        Lazy lazy = this.mEnvelopeHelper;
        if (lazy == null || (envelopeHelper = (EnvelopeHelper) lazy.get()) == null) {
            return;
        }
        envelopeHelper.mEnvelopeStateChangeCallbackList.remove(this);
    }

    public final void handleSetListening(boolean z) {
        EnvelopeHelper envelopeHelper;
        EnvelopeHelper envelopeHelper2;
        super.handleSetListening(z);
        Lazy lazy = this.mEnvelopeHelper;
        if ((lazy != null ? (EnvelopeHelper) lazy.get() : null) == null) {
            String str = ((QSTileImpl) this).TAG;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->EnvelopeManager is null", "SystemUi--Common");
                return;
            }
            return;
        }
        Lazy lazy2 = this.mEnvelopeHelper;
        if (z) {
            if (lazy2 == null || (envelopeHelper2 = (EnvelopeHelper) lazy2.get()) == null) {
                return;
            }
            envelopeHelper2.mEnvelopeStateChangeCallbackList.add(this);
            return;
        }
        if (lazy2 == null || (envelopeHelper = (EnvelopeHelper) lazy2.get()) == null) {
            return;
        }
        envelopeHelper.mEnvelopeStateChangeCallbackList.remove(this);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        EnvelopeHelper envelopeHelper;
        Lazy lazy = this.mEnvelopeHelper;
        if ((lazy != null ? (EnvelopeHelper) lazy.get() : null) == null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, ((QSTileImpl) this).TAG, "EnvelopeManager is null", null, 8);
            return;
        }
        Lazy lazy2 = this.mEnvelopeHelper;
        boolean z = false;
        if (lazy2 != null && (envelopeHelper = (EnvelopeHelper) lazy2.get()) != null && envelopeHelper.getEnvelopeAssistantStatus() == 1) {
            z = true;
        }
        ((QSTile.State) booleanState).label = ((QSTileImpl) this).mHost.getContext().getString(2131955128);
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).icon = z ? QSTileImpl.ResourceIcon.get(2131237205) : QSTileImpl.ResourceIcon.get(2131237206);
        ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131953160);
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
        ((QSTile.State) booleanState).state = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
    }

    public final boolean isAvailable() {
        QSFeatureOption.INSTANCE.getClass();
        return QSFeatureOption.isEnvelopSupport;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState m318newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_RED_COLOR;
        return booleanState;
    }

    public final void setMEnvelopeHelper(Lazy lazy) {
        this.mEnvelopeHelper = lazy;
    }
}
