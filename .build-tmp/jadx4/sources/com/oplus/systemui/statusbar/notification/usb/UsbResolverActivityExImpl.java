package com.oplus.systemui.statusbar.notification.usb;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.statusbar.notification.usb.UsbResolverActivityEx;
import com.oplus.systemui.usb.OplusUsbAOASkipPackages;
import com.oplus.systemui.usb.OplusUsbBackend;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UsbResolverActivityExImpl extends UsbResolverActivityEx {
    public ResolveInfo getInfo() {
        return getMInfo();
    }

    public Intent getIntentInInfo() {
        return getMIntentInInfo();
    }

    public void setOcarComponent(ResolveInfo resolveInfo, Intent intent) {
        if (OplusUsbAOASkipPackages.INSTANCE.shouldSkipAOADialog(resolveInfo.getComponentInfo().packageName)) {
            setMInfo(resolveInfo);
            Log.d("UsbResolverActivity", "action of target: " + intent.getAction());
            setMIntentInInfo(new Intent(intent));
            Intent mIntentInInfo = getMIntentInInfo();
            if (mIntentInInfo != null) {
                mIntentInInfo.addFlags(50331648);
            }
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            Intent mIntentInInfo2 = getMIntentInInfo();
            if (mIntentInInfo2 != null) {
                mIntentInInfo2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            }
        }
    }

    public boolean shouldBlockDialog(Context context) {
        return OplusUsbBackend.getInstance(context).shouldBlockService();
    }

    public boolean startOcarActivity(Activity activity, int i) {
        String str;
        ActivityInfo activityInfo;
        Intent mIntentInInfo = getMIntentInInfo();
        if (mIntentInInfo == null) {
            return false;
        }
        try {
            ResolveInfo mInfo = getMInfo();
            if (mInfo == null || (activityInfo = mInfo.activityInfo) == null || (str = activityInfo.packageName) == null) {
                str = "unknown";
            }
            Log.d("UsbResolverActivity", "startActivity for skip AOA package: ".concat(str));
            activity.startActivityAsUser(mIntentInInfo, null, UserHandle.of(i));
            return true;
        } catch (ActivityNotFoundException e) {
            Log.e("UsbResolverActivity", "startActivity failed for skip AOA package", e);
            return true;
        }
    }
}
