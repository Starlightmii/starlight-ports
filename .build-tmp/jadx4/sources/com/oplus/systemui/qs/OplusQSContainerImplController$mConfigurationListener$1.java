package com.oplus.systemui.qs;

import android.content.res.Configuration;
import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController;
import com.oplus.systemui.qs.fake.OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController$mConfigurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ OplusQSContainerImplController this$0;

    public final void onConfigChanged(Configuration configuration) {
        OplusQSContainerImplController oplusQSContainerImplController = this.this$0;
        OplusQSContainerImpl oplusQSContainerImpl = (OplusQSContainerImpl) ((ViewController) oplusQSContainerImplController).mView;
        oplusQSContainerImpl.updateResources$8();
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSContainerImpl", new StringBuilder("onConfigChanged mHasDispatchConfig= "), oplusQSContainerImpl.mHasDispatchConfig);
        if (oplusQSContainerImpl.mHasDispatchConfig && oplusQSContainerImpl.getVisibility() == 0 && oplusQSContainerImpl.mInflateConfiguration.diffPublicOnly(oplusQSContainerImpl.getResources().getConfiguration()) != 0) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSContainerImpl", "onConfigChanged => dispatchConfigurationChanged()");
            oplusQSContainerImpl.dispatchConfigurationChanged(oplusQSContainerImpl.getResources().getConfiguration());
        }
        oplusQSContainerImpl.mHasDispatchConfig = false;
        OplusQSFakeStatusController oplusQSFakeStatusController = oplusQSContainerImplController.oplusQSFakeStatusController;
        View view = oplusQSFakeStatusController.mView;
        OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1 = new OplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1(0);
        oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1.this$0 = oplusQSFakeStatusController;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(view, oplusQSFakeStatusController$updateResource$$inlined$doOnPreDraw$1);
    }
}
