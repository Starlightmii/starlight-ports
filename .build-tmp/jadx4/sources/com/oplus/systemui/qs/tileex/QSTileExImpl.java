package com.oplus.systemui.qs.tileex;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import androidx.compose.material.ripple.Ripple_androidKt$;
import androidx.compose.runtime.OpaqueKey$;
import com.android.settingslib.RestrictedLockUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.tile.QSTileEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.external.OplusCustomTileStub;
import com.oplus.systemui.qs.tileimpl.OplusQSHighlightTileViewImpl;
import com.oplus.systemui.qs.tileimpl.OplusQSIconViewImpl;
import com.oplus.systemui.qs.tileimpl.OplusQSTileBaseViewImpl;
import com.oplus.systemui.qs.tileimpl.OplusQSTileViewImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.function.Supplier;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class QSTileExImpl implements QSTileEx, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String KEY_OPLUS_AUDIO_GET_MUTE_PHONE = "OPLUS_AUDIO_GET_MUTE_PHONE";
    private final String TAG = getClass().getSimpleName();
    private final Lazy audioManager$delegate;
    private final Lazy componentName$delegate;
    private final QSTileExParams params;
    private final Lazy uiFirstInteractor$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public QSTileExImpl(QSTileExParams qSTileExParams) {
        this.params = qSTileExParams;
        QSTileExImpl$$ExternalSyntheticLambda0 qSTileExImpl$$ExternalSyntheticLambda0 = new QSTileExImpl$$ExternalSyntheticLambda0(0);
        qSTileExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.uiFirstInteractor$delegate = LazyKt__LazyJVMKt.lazy(qSTileExImpl$$ExternalSyntheticLambda0);
        QSTileExImpl$$ExternalSyntheticLambda0 qSTileExImpl$$ExternalSyntheticLambda1 = new QSTileExImpl$$ExternalSyntheticLambda0(1);
        qSTileExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.componentName$delegate = LazyKt__LazyJVMKt.lazy(qSTileExImpl$$ExternalSyntheticLambda1);
        QSTileExImpl$$ExternalSyntheticLambda0 qSTileExImpl$$ExternalSyntheticLambda2 = new QSTileExImpl$$ExternalSyntheticLambda0(2);
        qSTileExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.audioManager$delegate = LazyKt__LazyJVMKt.lazy(qSTileExImpl$$ExternalSyntheticLambda2);
        qSTileExParams.getDumpManager().registerDumpable(getClass().getSimpleName(), this);
    }

    public static void requestUxForTileThread() {
        OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.CONTROL_PANEL_SWITCH);
    }

    public Supplier<QSTile.Icon> buildTileIconSupplier(Icon icon, Icon icon2, CustomTile customTile) {
        return OplusCustomTileStub.buildTileIconSupplier(getContext(), icon, icon2, customTile);
    }

    public final QSTileView createTileView(Context context, QSTile qSTile, boolean z) {
        OplusQSIconViewImpl oplusQSIconViewImpl = new OplusQSIconViewImpl(context);
        if (getQsHost().indexOf(qSTile.getTileSpec()) == 0 || getQsHost().indexOf(qSTile.getTileSpec()) == 1) {
            return new OplusQSHighlightTileViewImpl(context, oplusQSIconViewImpl, z);
        }
        return z ? new OplusQSTileBaseViewImpl(context, oplusQSIconViewImpl, true) : new OplusQSTileViewImpl(context, oplusQSIconViewImpl, false);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusCustomTileStub.INSTANCE.dump(printWriter);
    }

    public final ActivityStarter getActivityStarter() {
        return this.params.getActivityStarter();
    }

    public final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager$delegate.getValue();
    }

    public final ComponentName getComponentName() {
        return (ComponentName) this.componentName$delegate.getValue();
    }

    public final Context getContext() {
        return getQsHost().getContext();
    }

    public final RestrictedLockUtils.EnforcedAdmin getEnforcedAdmin(QSTile qSTile) {
        QSTileImpl qSTileImpl = qSTile instanceof QSTileImpl ? (QSTileImpl) qSTile : null;
        if (qSTileImpl != null) {
            return qSTileImpl.getEnforcedAdmin();
        }
        return null;
    }

    public final OplusQSActivityStarter getOplusQSActivityStarter() {
        return this.params.getOplusQSActivityStarter();
    }

    public final PanelInteractor getPanelInteractor() {
        return this.params.getPanelInteractor();
    }

    public final QSHost getQsHost() {
        return (QSHost) this.params.getQsHostLazy().get();
    }

    public final SuperPowerSaveRepository getSuperPowerRepo() {
        return this.params.getSuperPowerRepo();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final TileUiFirst getUiFirstInteractor() {
        return (TileUiFirst) this.uiFirstInteractor$delegate.getValue();
    }

    public final WellBeingAssistantRepository getWellBeingRepo() {
        return this.params.getWellBeingRepo();
    }

    public boolean handleClick(QSTile qSTile, Message message, Handler handler) {
        if (!isClickForbidden()) {
            return !qSTile.getState().disabledByPolicy && getUiFirstInteractor().handleClick(qSTile, message, handler);
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isVersionSellMode()) {
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleTileClick: click is forbidden", null, 8);
            return true;
        }
        Toast.makeText(getContext(), 2131955263, 0).show();
        LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleTileClick: click is forbidden in sell mode", null, 8);
        return true;
    }

    public boolean handleDestroy(QSTile qSTile, Message message, Handler handler) {
        return false;
    }

    public boolean handleDetailToggle(QSTile qSTile, Message message, Handler handler) {
        LogUtil.i$default(LogUtil.TAG_QS, this.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(message.obj, "handleDetailToggle: operation="), null, 8);
        if (getUiFirstInteractor().isUiFirstRunning(handler)) {
            getUiFirstInteractor().handleDetailToggle(qSTile, message, handler);
            return true;
        }
        qSTile.handleUserOperation(message.obj);
        return true;
    }

    public boolean handleFwStateChange(QSTile qSTile, Message message, Handler handler) {
        getUiFirstInteractor().handleFwStateChange(qSTile, message, handler);
        return true;
    }

    public boolean handleLongClick(QSTile qSTile, Message message, Handler handler) {
        if (qSTile.getState().dualTarget || !(getSuperPowerRepo().getInSuperPowerSaveMode() || getWellBeingRepo().getInWellBeingAssistantMode())) {
            if (!isClickForbidden()) {
                return false;
            }
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleTileLongClick: click is forbidden", null, 8);
            return true;
        }
        Toast.makeText(getContext(), 2131955248, 0).show();
        getPanelInteractor().collapsePanels();
        LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleTileLongClick: not support for inSuperPowerSaveMode or inWellBeingAssistantMode", null, 8);
        return true;
    }

    public boolean handleRefreshState(QSTile qSTile, Message message, Handler handler) {
        getUiFirstInteractor().handleRefreshState(qSTile, message, handler);
        return false;
    }

    public boolean handleSecondaryClick(QSTile qSTile, Message message, Handler handler) {
        if (qSTile.getState().disabledByPolicy) {
            getActivityStarter().postStartActivityDismissingKeyguard(RestrictedLockUtils.getShowAdminSupportDetailsIntent$1(getEnforcedAdmin(qSTile)), 0);
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleSecondaryClick: disabled by policy", null, 8);
            return true;
        }
        if (!isClickForbidden()) {
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "handleSecondaryClick: click is forbidden", null, 8);
        return true;
    }

    public void handleSetListening(QSTile qSTile, boolean z, Handler handler) {
        getUiFirstInteractor().handleSetListening(qSTile, z, handler);
    }

    public boolean handleUiFirstTimeOut(QSTile qSTile, Message message, Handler handler) {
        getUiFirstInteractor().handleUiFirstTimeout(qSTile, message, handler);
        return true;
    }

    public boolean isClickForbidden() {
        return false;
    }

    public final boolean isDisableVolumeMute$1() {
        return StringsKt__StringsJVMKt.equals("true", (String) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new QSTileExImpl$isDisableVolumeMute$result$1(this, null)), true);
    }

    public boolean isTileDisable() {
        return false;
    }

    public TileUiFirst newUiFirstInteractor() {
        return TileUiFirst.Default.INSTANCE;
    }

    public void onClick() {
        requestUxForTileThread();
    }

    public void onDetailToggle(QSTile qSTile, Object obj, Handler handler) {
        requestUxForTileThread();
        handler.obtainMessage(1002, obj).sendToTarget();
    }

    public void onFwStateChanged(QSTile qSTile, Object obj, Handler handler) {
        boolean zIsValidUiState = QSTileEx.Companion.isValidUiState(obj);
        boolean zAreEqual = Intrinsics.areEqual(obj, qSTile.getState().fwState);
        if (!zIsValidUiState) {
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, Ripple_androidKt$.ExternalSyntheticOutline0.m(obj, "onFwStateChanged: fw error, ", " is invalid"), null, 8);
        } else if (!zAreEqual) {
            qSTile.getState().fwState = obj;
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, OpaqueKey$.ExternalSyntheticOutline0.m(obj, "onFwStateChanged: fwState change to "), null, 8);
        }
        if (!(zIsValidUiState && zAreEqual) && getUiFirstInteractor().isUiFirstRunning(handler)) {
            handler.sendEmptyMessage(1000);
        } else {
            qSTile.refreshState();
        }
    }

    public final boolean onInterceptTileMessage(QSTile qSTile, Message message, Handler handler) {
        int i = message.what;
        if (i == 2) {
            return handleClick(qSTile, message, handler);
        }
        if (i == 3) {
            return handleSecondaryClick(qSTile, message, handler);
        }
        if (i == 4) {
            return handleLongClick(qSTile, message, handler);
        }
        if (i == 5) {
            return handleRefreshState(qSTile, message, handler);
        }
        if (i == 7) {
            return handleDestroy(qSTile, message, handler);
        }
        switch (i) {
            case 1000:
                return handleFwStateChange(qSTile, message, handler);
            case 1001:
                return handleUiFirstTimeOut(qSTile, message, handler);
            case 1002:
                return handleDetailToggle(qSTile, message, handler);
            default:
                return false;
        }
    }

    public final void onLongClick() {
        requestUxForTileThread();
    }

    public void onSecondaryClick() {
        requestUxForTileThread();
    }

    public void postHandleUpdateState(QSTile qSTile, QSTile.State state, Handler handler) {
        if (isTileDisable()) {
            resetIconForDisable(state);
            tintIconForDisable(state);
        }
        QSTileEx.Companion companion = QSTileEx.Companion;
        int i = state.state;
        companion.getClass();
        if (i == 2 || i == 1) {
            return;
        }
        getUiFirstInteractor().onTileUnavailable(qSTile, handler);
    }

    public void postStartActivityDismissingKeyguard(Expandable expandable, Intent intent, boolean z) {
        getOplusQSActivityStarter().postStartActivityDismissingKeyguard(expandable, intent, z);
    }

    public void preHandleUpdateSate(QSTile qSTile, QSTile.State state) {
        state.functionName.setMainText(qSTile.getTileLabel());
    }

    public void resetIconForDisable(QSTile.State state) {
        state.state = 0;
    }

    public Drawable tintDrawable(Drawable drawable, int i, ComponentName componentName) {
        return OplusCustomTileStub.tintDrawable(getContext(), drawable, i, componentName);
    }

    public void tintIconForDisable(QSTile.State state) {
        QSTile.Icon icon = state.icon;
        if (icon == null) {
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "resetIconForDisable: icon is null, ignore", null, 8);
            return;
        }
        Drawable drawable = icon.getDrawable(getContext());
        if (drawable == null) {
            LogUtil.i$default(LogUtil.TAG_QS, this.TAG, "resetIconForDisable: drawable is null, ignore", null, 8);
        } else {
            drawable.setTint(getContext().getColor(2131103749));
            state.icon = new QSTileImpl.DrawableIcon(drawable);
        }
    }

    public void postStartActivityDismissingKeyguard(Expandable expandable, PendingIntent pendingIntent, boolean z) {
        getOplusQSActivityStarter().postStartActivityDismissingKeyguard(expandable, pendingIntent, z);
    }
}
