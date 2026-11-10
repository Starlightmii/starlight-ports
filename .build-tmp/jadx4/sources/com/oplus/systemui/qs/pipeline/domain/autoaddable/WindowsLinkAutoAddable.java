package com.oplus.systemui.qs.pipeline.domain.autoaddable;

import android.content.Context;
import com.android.systemui.Prefs;
import com.android.systemui.qs.pipeline.data.repository.DefaultTilesRepository;
import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.helper.QSTileFilterHelper;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WindowsLinkAutoAddable implements AutoAddable {
    public static final Companion Companion = new Companion();
    private static final Lazy FIRST_API_LEVEL$delegate = LazyKt__LazyJVMKt.lazy(new WindowsLinkAutoAddable$$ExternalSyntheticLambda1());
    private final Context context;
    public final boolean isDefaultTile;
    private final Lazy needTryAdd$delegate;
    private final TileSpec spec;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final int getFIRST_API_LEVEL() {
            return ((Number) WindowsLinkAutoAddable.FIRST_API_LEVEL$delegate.getValue()).intValue();
        }
    }

    public static boolean $r8$lambda$9XzlD2_NuFhWfccZgcSMbdT7loU(WindowsLinkAutoAddable windowsLinkAutoAddable) {
        return windowsLinkAutoAddable.isDefaultTile && !Prefs.getBoolean(windowsLinkAutoAddable.context, "QsHandledUpgrade", false) && Companion.getFIRST_API_LEVEL() < 34;
    }

    public WindowsLinkAutoAddable(Context context, DefaultTilesRepository defaultTilesRepository) {
        this.context = context;
        TileSpec tileSpecCreate = TileSpec.Companion.create(QSTileFilterHelper.DISABLE_WINDOW_TILE);
        this.spec = tileSpecCreate;
        this.isDefaultTile = defaultTilesRepository.getDefaultTiles(false).contains(tileSpecCreate);
        WindowsLinkAutoAddable$$ExternalSyntheticLambda0 windowsLinkAutoAddable$$ExternalSyntheticLambda0 = new WindowsLinkAutoAddable$$ExternalSyntheticLambda0();
        windowsLinkAutoAddable$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.needTryAdd$delegate = LazyKt__LazyJVMKt.lazy(windowsLinkAutoAddable$$ExternalSyntheticLambda0);
    }

    private final boolean getNeedTryAdd() {
        return ((Boolean) this.needTryAdd$delegate.getValue()).booleanValue();
    }

    public Flow autoAddSignal(int i) {
        if (getNeedTryAdd()) {
            return FlowKt.flowOf(new AutoAddSignal.Add(this.spec, 0, 6));
        }
        return !this.isDefaultTile ? FlowKt.flowOf(new AutoAddSignal.RemoveTracking(this.spec)) : FlowKt.emptyFlow();
    }

    public AutoAddTracking getAutoAddTracking() {
        return this.isDefaultTile ? new AutoAddTracking.IfNotAdded(this.spec) : AutoAddTracking.Always.INSTANCE;
    }

    public String getDescription() {
        return "WindowsLinkAutoAddable (" + getAutoAddTracking() + ")";
    }
}
