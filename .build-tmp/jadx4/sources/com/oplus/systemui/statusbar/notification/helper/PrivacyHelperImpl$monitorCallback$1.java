package com.oplus.systemui.statusbar.notification.helper;

import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.statusbar.notification.helper.PrivacyHelperImpl.AppLockBlockNotificationObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyHelperImpl$monitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ PrivacyHelperImpl this$0;

    public final void onSystemReady() {
        PrivacyHelperImpl privacyHelperImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "PrivacyHelper-->onSystemReady to register provider ");
        }
        privacyHelperImpl.new AppLockBlockNotificationObserver().registerForUserSwitch(privacyHelperImpl.context, false);
        PrivacyHelperImpl$bgHandler$1 privacyHelperImpl$bgHandler$1 = privacyHelperImpl.bgHandler;
        PrivacyHelperImpl$bgHandler$1$handleMessage$2 privacyHelperImpl$bgHandler$1$handleMessage$2 = new PrivacyHelperImpl$bgHandler$1$handleMessage$2(2);
        privacyHelperImpl$bgHandler$1$handleMessage$2.this$0 = privacyHelperImpl;
        VarHandle.storeStoreFence();
        privacyHelperImpl$bgHandler$1.postDelayed(privacyHelperImpl$bgHandler$1$handleMessage$2, 500L);
        AonAuthManager aonAuthManager = (AonAuthManager) privacyHelperImpl.aonAuthManager.get();
        PrivacyHelperImpl$monitorCallback$1$onSystemReady$3 privacyHelperImpl$monitorCallback$1$onSystemReady$3 = new PrivacyHelperImpl$monitorCallback$1$onSystemReady$3();
        privacyHelperImpl$monitorCallback$1$onSystemReady$3.this$0 = privacyHelperImpl;
        VarHandle.storeStoreFence();
        aonAuthManager.addAonAuthStateListener(privacyHelperImpl$monitorCallback$1$onSystemReady$3);
        SensitiveNotificationProtectionControllerImpl sensitiveNotificationProtectionControllerImpl = (SensitiveNotificationProtectionController) privacyHelperImpl.sensitiveNotificationProtectionController.get();
        PrivacyHelperImpl$bgHandler$1$handleMessage$2 privacyHelperImpl$bgHandler$1$handleMessage$3 = new PrivacyHelperImpl$bgHandler$1$handleMessage$2(3);
        privacyHelperImpl$bgHandler$1$handleMessage$3.this$0 = privacyHelperImpl;
        VarHandle.storeStoreFence();
        sensitiveNotificationProtectionControllerImpl.registerSensitiveStateListener(privacyHelperImpl$bgHandler$1$handleMessage$3);
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), (CoroutineContext) null, (CoroutineStart) null, new PrivacyHelperImpl$monitorCallback$1$onSystemReady$5(privacyHelperImpl, null), 3);
    }
}
