package com.oplus.systemui.navigationbar;

import android.content.om.IOverlayManager;
import android.os.Process;
import android.os.RemoteException;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NavBarController$$ExternalSyntheticLambda8 implements Runnable {
    public /* synthetic */ NavBarController f$0;
    public /* synthetic */ IOverlayManager f$1;
    public /* synthetic */ String f$2;

    @Override // java.lang.Runnable
    public final void run() {
        NavBarController navBarController = this.f$0;
        IOverlayManager iOverlayManager = this.f$1;
        String str = this.f$2;
        int threadPriority = Process.getThreadPriority(Process.myTid());
        Process.setThreadPriority(-10);
        str.hashCode();
        String str2 = !str.equals("system_nav_3buttons") ? "com.android.internal.systemui.navbar.gestural" : "com.android.internal.systemui.navbar.threebutton";
        try {
            LogUtil.i(LogUtil.TAG_NAVBAR, "NavBarController", "setCurrentSystemNavigationMode: overlayPackage: " + str2 + " ,key: " + str);
            if (navBarController.isOverlayInfoAvailable(-2, str2)) {
                iOverlayManager.setEnabledExclusiveInCategory(str2, -2);
            }
        } catch (RemoteException e) {
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("Failed to enable overlayPackage: ", str2, " ,key: ", str, ", exception: ");
            sbM.append(e);
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", sbM.toString());
        } catch (Exception e2) {
            StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("Failed to enable overlayPackage: ", str2, " ,key: ", str, ", exception: ");
            sbM2.append(e2);
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", sbM2.toString());
        }
        try {
            int multiSystemUserId = OplusMultiUserManager.getInstance().getMultiSystemUserId();
            if (multiSystemUserId != -10000) {
                int currentUser = KeyguardUpdateMonitor.getCurrentUser();
                boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(currentUser);
                boolean z = currentUser == 0;
                if (zIsMultiSystemUserId) {
                    if (navBarController.isOverlayInfoAvailable(0, str2)) {
                        navBarController.mOverlayManager.setEnabledExclusiveInCategory(str2, 0);
                    }
                } else if (z && navBarController.isOverlayInfoAvailable(multiSystemUserId, str2)) {
                    navBarController.mOverlayManager.setEnabledExclusiveInCategory(str2, multiSystemUserId);
                }
            }
        } catch (RemoteException e3) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", "Failed to enable for multi system user: " + str2 + ", exception: " + e3);
        } catch (Exception e4) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "NavBarController", "Failed to enable for multi system user: " + str2 + ", exception: " + e4);
        }
        Process.setThreadPriority(threadPriority);
    }
}
