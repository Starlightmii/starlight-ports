package com.oplus.systemui.statusbar;

import android.app.OplusActivityManager;
import android.content.Context;
import android.os.RemoteException;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.CommonProcessObserverEx;
import com.oplus.wrapper.app.IProcessObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommonProcessObserver extends CommonProcessObserverEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CommonProcessObserverExImpl";
    private final Context context;
    public boolean isRegister;
    private final CopyOnWriteArrayList<CommonProcessObserverEx.ICommonProcessObserver> observerList = new CopyOnWriteArrayList<>();
    private final Lazy mOam$delegate = LazyKt__LazyJVMKt.lazy(new CommonProcessObserver$$ExternalSyntheticLambda0());
    private final IProcessObserver mIProcessObserver = new IProcessObserver.Stub() { // from class: com.oplus.systemui.statusbar.CommonProcessObserver$mIProcessObserver$1
        public void onForegroundActivitiesChanged(int i, int i2, boolean z) {
            Iterator it = this.this$0.observerList.iterator();
            while (it.hasNext()) {
                ((CommonProcessObserverEx.ICommonProcessObserver) it.next()).getClass();
            }
        }

        public void onForegroundServicesChanged(int i, int i2, int i3) {
            Iterator it = this.this$0.observerList.iterator();
            while (it.hasNext()) {
                ((CommonProcessObserverEx.ICommonProcessObserver) it.next()).getClass();
            }
        }

        public void onProcessDied(int i, int i2) {
            String nameForUid = this.this$0.getContext().getPackageManager().getNameForUid(i2);
            Iterator it = this.this$0.observerList.iterator();
            while (it.hasNext()) {
                ((CommonProcessObserverEx.ICommonProcessObserver) it.next()).onProcessDied(i, i2, nameForUid);
            }
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CommonProcessObserver(Context context) {
        this.context = context;
    }

    private final OplusActivityManager getMOam() {
        return (OplusActivityManager) this.mOam$delegate.getValue();
    }

    public void addProcessObserver(CommonProcessObserverEx.ICommonProcessObserver iCommonProcessObserver) {
        if (this.observerList.contains(iCommonProcessObserver)) {
            return;
        }
        this.observerList.add(iCommonProcessObserver);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.observerList.size(), "addProcessObserver "), null, 8);
        if (this.isRegister) {
            return;
        }
        try {
            getMOam().registerProcessObserver(this.mIProcessObserver, 8);
            this.isRegister = true;
        } catch (RemoteException e) {
            LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "registerProcessObserver exception: ", e);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public void removeProcessObserver(CommonProcessObserverEx.ICommonProcessObserver iCommonProcessObserver) {
        if (this.observerList.contains(iCommonProcessObserver)) {
            this.observerList.remove(iCommonProcessObserver);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.observerList.size(), "removeProcessObserver ", "CommonProcessObserverExImpl-->", "SystemUi--Statusbar");
        }
        if (this.observerList.isEmpty() && this.isRegister) {
            try {
                getMOam().unregisterProcessObserver(this.mIProcessObserver);
                this.isRegister = false;
            } catch (RemoteException e) {
                LogUtil.e(LogUtil.TAG_STATUSBAR, TAG, "unregisterProcessObserver exception: ", e);
            }
        }
    }
}
