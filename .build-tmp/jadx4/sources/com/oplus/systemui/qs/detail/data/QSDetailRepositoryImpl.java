package com.oplus.systemui.qs.detail.data;

import android.content.Context;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailRepositoryImpl implements QSDetailRepository {
    private final AtomicBoolean _isDarkTheme;
    private final MutableSharedFlow _switchState;
    private Context _viewContext;
    private final ConfigurationController configurationController;
    private final Context context;
    private final AtomicBoolean isFirstConfigStart;
    private final CoroutineScope scope;
    private final Flow switchState;
    private final Flow updateConfigEvent;

    public QSDetailRepositoryImpl(Context context, CoroutineScope coroutineScope, ConfigurationController configurationController) {
        this.context = context;
        this.scope = coroutineScope;
        this.configurationController = configurationController;
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2);
        this._switchState = mutableSharedFlowMutableSharedFlow$default;
        this.switchState = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        this.isFirstConfigStart = new AtomicBoolean(true);
        this.updateConfigEvent = FlowKt.shareIn(FlowKt.onStart(FlowKt.onEach(FlowConflatedKt.conflatedCallbackFlow(new QSDetailRepositoryImpl$updateConfigEvent$1(this, null)), new QSDetailRepositoryImpl$updateConfigEvent$2(this, null)), new QSDetailRepositoryImpl$updateConfigEvent$3(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), 1);
        AtomicBoolean atomicBoolean = new AtomicBoolean(QSDetailRepositoryKt.isQSDetailDarkTheme(context));
        this._isDarkTheme = atomicBoolean;
        this._viewContext = QsColorUtil.createDarkColorContext(context, atomicBoolean.get());
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Flow getSwitchState() {
        return this.switchState;
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Context getThemeContext() {
        return this._viewContext;
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Flow getUpdateConfigEvent() {
        return this.updateConfigEvent;
    }

    public final boolean isDarkTheme() {
        return this._isDarkTheme.get();
    }

    public final boolean updateSwitchState(boolean z) {
        return this._switchState.tryEmit(Boolean.valueOf(z));
    }

    @Override // com.oplus.systemui.qs.detail.data.QSDetailRepository
    public Context updateThemeContext() {
        boolean zIsQSDetailDarkTheme = QSDetailRepositoryKt.isQSDetailDarkTheme(this.context);
        if (this._isDarkTheme.compareAndSet(!zIsQSDetailDarkTheme, zIsQSDetailDarkTheme)) {
            LogUtil.i$default(LogUtil.TAG_QS, "QSDetailRepository", KeyguardLogger$.ExternalSyntheticOutline0.m("updateViewContext: darkTheme=", zIsQSDetailDarkTheme), null, 8);
        }
        Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(this.context, zIsQSDetailDarkTheme);
        this._viewContext = contextCreateDarkColorContext;
        return contextCreateDarkColorContext;
    }
}
