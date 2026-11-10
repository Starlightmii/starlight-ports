package com.oplus.systemui.statusbar.notification.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.statusbar.notification.smallapp.SmallAppUtil;
import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallAppHelper$mMultiUserListener$1 implements IMultiUserListener {
    public /* synthetic */ SmallAppHelper this$0;

    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onSwitched(int i) {
        Context context;
        ContentResolver contentResolver;
        SmallAppHelper smallAppHelper = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SmallAppHelper.Companion.getClass();
            OplusLockIconView$.ExternalSyntheticOutline0.m("on user switched update small app info, sRegistered： ", "SmallAppHelper-->", "SystemUi--Notification", SmallAppHelper.isRegister);
        }
        SmallAppHelper.Companion.getClass();
        if (SmallAppHelper.isRegister) {
            ContentObserver contentObserver = smallAppHelper.mObserver;
            if (contentObserver != null && (context = smallAppHelper.mUserContext) != null && (contentResolver = context.getContentResolver()) != null) {
                contentResolver.unregisterContentObserver(contentObserver);
            }
            SmallAppHelper.isRegister = false;
        }
        smallAppHelper.initHelper();
        SmallAppUtil.initSmallAppInfo(smallAppHelper.mUserContext);
    }
}
