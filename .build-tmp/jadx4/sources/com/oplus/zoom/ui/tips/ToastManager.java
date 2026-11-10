package com.oplus.zoom.ui.tips;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.android.wm.shell.R;
import com.oplus.splitscreen.SplitToggleHelper;
import com.oplus.zoom.ui.common.UiLogUtils;
import com.oplus.zoom.ui.common.ZoomSharedPrefHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ToastManager {
    private static final String TAG = "Tips";
    private Context mContext;

    public ToastManager(Context context) {
        this.mContext = context;
    }

    public void showCompatibilityToast(int i, int i2, String str, String str2, Bundle bundle) {
        if (str == null) {
            UiLogUtils.w("Tips", "Failed to show toast: target is null");
            return;
        }
        UiLogUtils.d("Tips", "target:  " + str + ", type =" + i);
        if (i != 2 || SplitToggleHelper.getInstance().mIsTabletDevice || ZoomSharedPrefHelper.getInt(this.mContext, str, 0) == i) {
            return;
        }
        Context context = this.mContext;
        Toast.makeText(context, context.getString(R.string.zoom_unsupport_application), 1).show();
        ZoomSharedPrefHelper.putInt(this.mContext, str, i);
    }
}
