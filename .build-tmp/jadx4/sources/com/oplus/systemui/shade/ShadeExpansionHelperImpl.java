package com.oplus.systemui.shade;

import android.os.SystemProperties;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.concurrent.Executors;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeExpansionHelperImpl {
    public static final Companion Companion = new Companion();
    public static final String PROP_EXPAND_STATUS = "persist.sys.statusbar.status";
    public static final String TAG = "ShadeExpansionHelperImpl";
    public static final String VAL_EXPANDED_PANEL_ONLY = "1";
    public static final String VAL_EXPANDED_PANEL_QS_BOTH = "2";
    public static final String VAL_INVALID = "-1";
    public static final String VAL_NORMAL = "0";
    private final ExecutorCoroutineDispatcher executor;
    public boolean panelExpanded;
    private String prevPropValue;
    public boolean qsExpanded;
    private final Lazy shadeInteractorLazy;
    private final CoroutineScope singleCoroutineScope;

    public ShadeExpansionHelperImpl(Lazy lazy) {
        this.shadeInteractorLazy = lazy;
        ExecutorCoroutineDispatcher executorCoroutineDispatcherFrom = ExecutorsKt.from(Executors.newSingleThreadExecutor());
        this.executor = executorCoroutineDispatcherFrom;
        this.singleCoroutineScope = CoroutineScopeKt.CoroutineScope(executorCoroutineDispatcherFrom);
        this.prevPropValue = VAL_INVALID;
    }

    public static final void access$updateExpandedState(ShadeExpansionHelperImpl shadeExpansionHelperImpl) {
        String str;
        if (shadeExpansionHelperImpl.qsExpanded) {
            str = VAL_EXPANDED_PANEL_QS_BOTH;
        } else {
            str = shadeExpansionHelperImpl.panelExpanded ? "1" : "0";
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            boolean z = shadeExpansionHelperImpl.panelExpanded;
            boolean z2 = shadeExpansionHelperImpl.qsExpanded;
            String str2 = shadeExpansionHelperImpl.prevPropValue;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateExpandedState called ... panelExpanded = ", " , qsExpanded = ", " , val = ", z, z2);
            sbM.append(str);
            sbM.append(" , prevVal = ");
            sbM.append(str2);
            Log.d("SystemUi--QuickSettings", "ShadeExpansionHelperImpl-->".concat(sbM.toString()));
        }
        if (str.equals(shadeExpansionHelperImpl.prevPropValue)) {
            return;
        }
        shadeExpansionHelperImpl.prevPropValue = str;
        SystemProperties.set(PROP_EXPAND_STATUS, str);
    }

    public final void init() {
        BuildersKt.launch$default(this.singleCoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new init.1(this, (Continuation) null), 3);
    }
}
