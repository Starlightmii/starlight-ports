package com.oplus.systemui.qs;

import android.os.Trace;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.qs.deprecated.QSPanel;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSContainerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ OplusQSContainerImpl f$0;

    @Override // java.lang.Runnable
    public final void run() {
        OplusQSContainerImpl oplusQSContainerImpl = this.f$0;
        int i = OplusQSContainerImpl.$r8$clinit;
        Trace.traceBegin(8L, "dispatchConfigurationChanged");
        int iDiffPublicOnly = oplusQSContainerImpl.mInflateConfiguration.diffPublicOnly(oplusQSContainerImpl.getResources().getConfiguration());
        QSPanel.QSTileLayout qSTileLayout = oplusQSContainerImpl.mQSPanel.mTileLayout;
        boolean zNeedUpdateResources = qSTileLayout != null ? qSTileLayout.needUpdateResources() : false;
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSContainerImpl", KeyguardLogger$.ExternalSyntheticOutline0.m("mDispatchConfigRunnable diff= ", iDiffPublicOnly, ", needUpdateResources = ", ", mIsMediaModeChangeInDiffConfig=", zNeedUpdateResources), oplusQSContainerImpl.mIsMediaModeChangeInDiffConfig);
        if (oplusQSContainerImpl.mIsMediaModeChangeInDiffConfig || iDiffPublicOnly != 0 || zNeedUpdateResources) {
            OplusNotificationLogger.logMediaPanel("OplusQSContainerImpl", "dispatchConfigurationChangedRun: diff=" + iDiffPublicOnly);
            oplusQSContainerImpl.dispatchConfigurationChanged(oplusQSContainerImpl.getResources().getConfiguration());
            oplusQSContainerImpl.mHasDispatchConfig = true;
        } else {
            QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
            LogUtil.i(LogUtil.TAG_QS, "QSFragmentHelper", "QSFragmentHelper mLastAccentColorConfigChanged=" + qSFragmentHelper.mLastAccentColorConfigChanged + ", mLastActiveColor= " + Integer.toHexString(qSFragmentHelper.mLastActiveColor));
            if (qSFragmentHelper.mLastAccentColorConfigChanged) {
                qSFragmentHelper.mLastAccentColorConfigChanged = false;
                OplusNotificationLogger.logMediaPanel("OplusQSContainerImpl", "dispatchConfigurationChangedRun: diff=" + iDiffPublicOnly);
                oplusQSContainerImpl.dispatchConfigurationChanged(oplusQSContainerImpl.getResources().getConfiguration());
                oplusQSContainerImpl.mHasDispatchConfig = true;
            }
        }
        Trace.traceEnd(8L);
    }
}
