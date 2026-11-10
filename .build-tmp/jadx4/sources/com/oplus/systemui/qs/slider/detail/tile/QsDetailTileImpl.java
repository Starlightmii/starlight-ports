package com.oplus.systemui.qs.slider.detail.tile;

import android.content.Context;
import android.content.Intent;
import android.util.ArraySet;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QsBgThread;
import com.oplusos.systemui.common.util.TileHandlerThread;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QsDetailTileImpl implements QsDetailTile {
    public static final Companion Companion = new Companion();
    private final ArraySet<QSTile.Callback> callbacks;
    private final H clickHandler;
    private final Context context;
    private final H handler;
    public boolean listening;
    private final OplusQSActivityStarter oplusQSActivityStarter;
    public int readyState;
    public QSTile.State state;
    private final String tag;
    public QSTile.State tmpState;

    public QsDetailTileImpl(Context context, OplusQSActivityStarter oplusQSActivityStarter) {
        this.context = context;
        this.oplusQSActivityStarter = oplusQSActivityStarter;
        String strConcat = "Tile.".concat(getClass().getSimpleName());
        this.tag = strConcat;
        this.callbacks = new ArraySet<>();
        this.clickHandler = new H(this, QsBgThread.INSTANCE.getThreadLooper());
        this.handler = new H(this, TileHandlerThread.Companion.getInstance().getLooper());
        LogUtil.i$default(LogUtil.TAG_QS, strConcat, "init: this = ".concat(getClass().getSimpleName()), null, 8);
        resetStates();
    }

    public static final void access$handleAddCallback(QsDetailTileImpl qsDetailTileImpl, QSTile.Callback callback) {
        qsDetailTileImpl.callbacks.add(callback);
        if (qsDetailTileImpl.readyState == 2) {
            callback.onStateChanged(qsDetailTileImpl.getState());
        }
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final void addCallback(QSTile.Callback callback) {
        this.handler.obtainMessage(1, callback).sendToTarget();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final void click(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, ListPopupWindow$.ExternalSyntheticOutline0.m(getState().state, "click: state = "), null, 8);
        this.clickHandler.obtainMessage(2, new WeakReference(expandable)).sendToTarget();
    }

    public final void destroy() {
        this.handler.sendEmptyMessage(7);
    }

    public final H getClickHandler() {
        return this.clickHandler;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public abstract /* synthetic */ int getClickTipsType();

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public abstract /* synthetic */ int getDetailType();

    public final H getHandler() {
        return this.handler;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public abstract /* synthetic */ Intent getLongClickIntent();

    public final OplusQSActivityStarter getOplusQSActivityStarter() {
        return this.oplusQSActivityStarter;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getSecondaryLabel(QSTile.State state) {
        if (!(state instanceof QSTile.BooleanState)) {
            return null;
        }
        return this.context.getString(((QSTile.AdapterState) ((QSTile.BooleanState) state)).value ? 2131956739 : 2131956738);
    }

    public final QSTile.State getState() {
        QSTile.State state = this.state;
        if (state != null) {
            return state;
        }
        return null;
    }

    public final String getTag() {
        return this.tag;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public abstract /* synthetic */ String getTileLabel();

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public abstract /* synthetic */ String getTileSpec();

    public final QSTile.State getTmpState() {
        QSTile.State state = this.tmpState;
        if (state != null) {
            return state;
        }
        return null;
    }

    public abstract void handleClick(Expandable expandable);

    public void handleClickInternal(Expandable expandable) {
        if (getState().state == 0 || getState().state == 101) {
            LogUtil.i$default(LogUtil.TAG_QS, this.tag, "handleClickInternal: click not available!", null, 8);
        } else {
            handleClick(expandable);
        }
    }

    public void handleLongClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "handleLongClick: ", null, 8);
        Intent longClickIntent = getLongClickIntent();
        if (longClickIntent == null) {
            return;
        }
        this.oplusQSActivityStarter.postStartActivityDismissingKeyguard(expandable, longClickIntent, false);
    }

    public final void handleRefreshState(Object obj) {
        if (this.listening) {
            handleUpdateState(getTmpState(), obj);
            getTmpState().secondaryLabel = getSecondaryLabel(getTmpState());
            boolean zCopyTo = getTmpState().copyTo(getState());
            if (this.readyState == 1) {
                this.readyState = 2;
                zCopyTo = true;
            }
            String str = this.tag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                sbM.append("handleRefreshState: changed = " + zCopyTo + " state = " + getState().state);
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
            if (zCopyTo) {
                handleStateChanged$1();
            }
        }
    }

    public final void handleRemoveCallbacks$1() {
        this.callbacks.clear();
    }

    public void handleSecondaryClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "handleSecondaryClick: ", null, 8);
        handleClickInternal(expandable);
    }

    public abstract void handleSetListening(boolean z);

    public final void handleSetListeningInternal(boolean z) {
        if (this.listening == z) {
            return;
        }
        this.listening = z;
        if (!z) {
            handleSetListening(false);
            return;
        }
        handleSetListening(true);
        refreshState(null);
        if (this.readyState == 0) {
            this.readyState = 1;
        }
    }

    public void handleStateChanged$1() {
        if (this.callbacks.isEmpty()) {
            return;
        }
        int size = this.callbacks.size();
        for (int i = 0; i < size; i++) {
            this.callbacks.valueAt(i).onStateChanged(getState());
        }
    }

    public abstract void handleUpdateState(QSTile.State state, Object obj);

    public final void initialize$1() {
        this.handler.sendEmptyMessage(12);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public boolean isAvailable() {
        return true;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final void longClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "longClicked: ", null, 8);
        this.clickHandler.obtainMessage(4, new WeakReference(expandable)).sendToTarget();
    }

    /* JADX INFO: renamed from: newTileState */
    public abstract QSTile.State mo309newTileState();

    public final void refreshState(Object obj) {
        this.handler.obtainMessage(5, obj).sendToTarget();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final void removeCallback(QSTile.Callback callback) {
        this.handler.obtainMessage(9, callback).sendToTarget();
    }

    public final void removeCallbacks() {
        this.handler.sendEmptyMessage(8);
    }

    public final void resetStates() {
        setState(mo309newTileState());
        setTmpState(mo309newTileState());
        getState().spec = getTileSpec();
        getTmpState().spec = getTileSpec();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final void secondaryClick(Expandable expandable) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, ListPopupWindow$.ExternalSyntheticOutline0.m(getState().state, "secondaryClick: state = "), null, 8);
        this.clickHandler.obtainMessage(3, new WeakReference(expandable)).sendToTarget();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public void setListening(boolean z) {
        this.handler.obtainMessage(10, z ? 1 : 0, 0).sendToTarget();
    }

    public final void setState(QSTile.State state) {
        this.state = state;
    }

    public final void setTmpState(QSTile.State state) {
        this.tmpState = state;
    }
}
