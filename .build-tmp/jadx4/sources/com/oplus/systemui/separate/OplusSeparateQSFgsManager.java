package com.oplus.systemui.separate;

import android.content.Context;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.plugins.qs.QSFgs;
import com.oplus.systemui.qs.widget.OplusFgsManagerController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.LazyKt__LazyJVMKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSFgsManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSeparateQSPrivacyManager";
    private final Executor backgroundExecutor;
    private final Context context;
    private final OplusFgsManagerController fgsManagerController;
    private final OplusFgsManagerController.OnNumberOfPackagesChangedListener fgsNumberCallback;
    private QSFgsImpl qsFgsImpl;
    public int qsNumPackages;
    private final CopyOnWriteArrayList<QSFgs.CallBack> qsFgsCallBacks = new CopyOnWriteArrayList<>();
    private List<OplusFgsManagerController.RunningApp> qsRunningApps = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSFgsImpl implements QSFgs {
        private final Context context;

        public QSFgsImpl(Context context) {
            this.context = context;
        }

        public void addCallBack(QSFgs.CallBack callBack) {
            OplusSeparateQSFgsManager oplusSeparateQSFgsManager = OplusSeparateQSFgsManager.this;
            oplusSeparateQSFgsManager.qsFgsCallBacks.add(callBack);
            callBack.onNumberOfPackagesChanged(oplusSeparateQSFgsManager.qsNumPackages, false);
            callBack.onRunningAppsChanged(OplusSeparateQSFgsManager.access$convertRunningApp(oplusSeparateQSFgsManager, oplusSeparateQSFgsManager.qsRunningApps));
        }

        public final Context getContext() {
            return this.context;
        }

        public List<QSFgs.QSRunningApp> getRunningApps() {
            OplusSeparateQSFgsManager oplusSeparateQSFgsManager = OplusSeparateQSFgsManager.this;
            List<OplusFgsManagerController.RunningApp> runningApps = oplusSeparateQSFgsManager.fgsManagerController.getRunningApps();
            LogUtil.i$default(LogUtil.TAG_QS, OplusSeparateQSFgsManager.TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(runningApps.size(), "getRunningApps runningApps "), null, 8);
            return OplusSeparateQSFgsManager.access$convertRunningApp(oplusSeparateQSFgsManager, runningApps);
        }

        public void removeCallBack(QSFgs.CallBack callBack) {
            OplusSeparateQSFgsManager.this.qsFgsCallBacks.remove(callBack);
        }

        public void setHasDialog(boolean z) {
            OplusSeparateQSFgsManager.this.fgsManagerController.setHasDialog(z);
        }

        public final void setShowPoPWindows() {
        }
    }

    public OplusSeparateQSFgsManager(Context context, Executor executor, OplusFgsManagerController oplusFgsManagerController) {
        this.context = context;
        this.backgroundExecutor = executor;
        this.fgsManagerController = oplusFgsManagerController;
        OplusSeparateQSFgsManager$fgsNumberCallback$1 oplusSeparateQSFgsManager$fgsNumberCallback$1 = new OplusSeparateQSFgsManager$fgsNumberCallback$1();
        oplusSeparateQSFgsManager$fgsNumberCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.fgsNumberCallback = oplusSeparateQSFgsManager$fgsNumberCallback$1;
        oplusFgsManagerController.addOnNumberOfPackagesChangedListener(oplusSeparateQSFgsManager$fgsNumberCallback$1);
    }

    public static final List access$convertRunningApp(OplusSeparateQSFgsManager oplusSeparateQSFgsManager, List list) {
        QSFgs.QSUIControl qSUIControl;
        oplusSeparateQSFgsManager.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            OplusFgsManagerController.RunningApp runningApp = (OplusFgsManagerController.RunningApp) it.next();
            runningApp.getUiControl();
            int userId = runningApp.getUserId();
            String packageName = runningApp.getPackageName();
            long timeStarted = runningApp.getTimeStarted();
            int iOrdinal = runningApp.getUiControl().ordinal();
            if (iOrdinal == 0) {
                qSUIControl = QSFgs.QSUIControl.NORMAL;
            } else if (iOrdinal == 1) {
                qSUIControl = QSFgs.QSUIControl.HIDE_BUTTON;
            } else {
                if (iOrdinal != 2) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return null;
                }
                qSUIControl = QSFgs.QSUIControl.HIDE_ENTRY;
            }
            arrayList.add(new QSFgs.QSRunningApp(userId, packageName, timeStarted, qSUIControl, runningApp.getAppLabel(), runningApp.getIcon(), runningApp.getStopped()));
        }
        return arrayList;
    }

    public final QSFgs bindQSFgs() {
        if (this.qsFgsImpl == null) {
            this.qsFgsImpl = new QSFgsImpl(this.context);
            this.fgsManagerController.init();
        }
        return this.qsFgsImpl;
    }
}
