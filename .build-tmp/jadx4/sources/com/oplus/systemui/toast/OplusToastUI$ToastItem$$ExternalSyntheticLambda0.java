package com.oplus.systemui.toast;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import androidx.appcompat.widget.ListPopupWindow$;
import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToastUI$ToastItem$$ExternalSyntheticLambda0 implements Supplier {
    public /* synthetic */ OplusToastUI.ToastItem f$0;

    @Override // java.util.function.Supplier
    public final Object get() {
        OplusToastUI.ToastItem toastItem = this.f$0;
        UserHandle userHandleForUid = UserHandle.getUserHandleForUid(toastItem.mUid);
        OplusToastUI oplusToastUI = toastItem.this$0;
        Context contextCreateContextAsUser = oplusToastUI.mContext.createContextAsUser(userHandleForUid, 1);
        DisplayManager displayManager = (DisplayManager) oplusToastUI.mContext.getSystemService(DisplayManager.class);
        int i = toastItem.mDisplayId;
        Display display = displayManager.getDisplay(i);
        if (display != null || (display = displayManager.getDisplay(0)) != null) {
            Context contextCreateDisplayContext = contextCreateContextAsUser.createDisplayContext(display);
            toastItem.mDisplayContext = contextCreateDisplayContext;
            return oplusToastUI.mToastFactory.createToast(contextCreateDisplayContext, toastItem.mText, toastItem.mPackageName, userHandleForUid.getIdentifier(), oplusToastUI.mOrientation, oplusToastUI.mUiModeHelper);
        }
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "showToast error display is null displayId:", " for ");
        sbM.append(toastItem.mPackageName);
        Log.e("OplusToastUI", sbM.toString());
        return null;
    }
}
