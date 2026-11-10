package com.oplus.systemui.plugins.qs.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SeparateQSModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final LogBuffer providePanelAnimationLogBuffer(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.createLogBuffer("SeparateQSLog", 1000);
        }

        public final SepQSHostAdapter provideSepQSTileHost(OplusSeparateQSPlugin.IStub iStub, TileConfigRepoSwitcher tileConfigRepoSwitcher, CoroutineScope coroutineScope, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
            return new SepQSHostAdapter(iStub.bindQSHost(), tileConfigRepoSwitcher, coroutineScope, separateQsTileListLogBuffer);
        }

        public final LogBuffer provideSeparateQSTileListLogBuffer(OplusSeparateQSPlugin.IStub iStub) {
            return iStub.createLogBuffer("SeparateQSTileListLog", 1000);
        }
    }
}
