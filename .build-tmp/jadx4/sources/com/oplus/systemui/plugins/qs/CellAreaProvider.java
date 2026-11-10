package com.oplus.systemui.plugins.qs;

import android.view.View;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellAreaProvider implements ICellAreaProvider {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CellAreaProvider";
    private final Function0 cellAreaChangeListener;
    private final CellAreaConfig cellAreaConfig;
    private final CopyOnWriteArraySet<CellConfigUpdateListener> configListeners;
    public boolean isEditMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CellAreaProvider(View view) {
        this.cellAreaConfig = new CellAreaConfig(view.getContext(), this);
        CellAreaProvider$$ExternalSyntheticLambda0 cellAreaProvider$$ExternalSyntheticLambda0 = new CellAreaProvider$$ExternalSyntheticLambda0();
        cellAreaProvider$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.cellAreaChangeListener = cellAreaProvider$$ExternalSyntheticLambda0;
        this.configListeners = new CopyOnWriteArraySet<>();
    }

    public final void addConfigUpdateListener(CellConfigUpdateListener cellConfigUpdateListener) {
        this.configListeners.add(cellConfigUpdateListener);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("CellAreaProvider isEditMode:" + this.isEditMode + ", isLandscape:" + CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion));
        this.cellAreaConfig.dump(printWriter, strArr);
    }

    @Override // com.oplus.systemui.plugins.qs.ICellAreaProvider
    public Function0 getCellAreaChangeListener() {
        return this.cellAreaChangeListener;
    }

    public final CellAreaConfig getCellAreaConfig() {
        return this.cellAreaConfig;
    }

    public final void notifyConfigUpdate() {
        Iterator<T> it = this.configListeners.iterator();
        while (it.hasNext()) {
            OplusQSRootViewComponent oplusQSRootViewComponent = ((OplusQSRootViewComponent$cellConfigUpdateListener$1) ((CellConfigUpdateListener) it.next())).this$0;
            OplusQSRootViewComponent.access$updateTextSize(oplusQSRootViewComponent, ((Boolean) OplusQSSpecialModeProvider.Companion.getInstance().isDefaultFont().getValue()).booleanValue());
            TemporarilyInfoManager.Companion.getInstance().setSeparateExtraSpace(oplusQSRootViewComponent.getCellAreaProvider().getCellAreaConfig().getC2Height() + oplusQSRootViewComponent.getCellAreaProvider().getCellAreaConfig().getC2Height());
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQSRootViewComponent", "onConfigUpdate", null, 8);
        }
    }

    public final void removeConfigUpdateListener(CellConfigUpdateListener cellConfigUpdateListener) {
        this.configListeners.remove(cellConfigUpdateListener);
    }

    public final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            this.isEditMode = z;
            this.cellAreaConfig.setEditMode(z);
            this.cellAreaConfig.update();
        }
    }

    public final void updateCellAreaConfig() {
        this.cellAreaConfig.update();
    }
}
