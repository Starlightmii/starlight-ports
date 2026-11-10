package com.oplus.systemui.plugins.qs.quickentrance.dialog;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.function.Function;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFgsDialogController implements DetailAdapter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSFgsDialogController";
    private final IActivityManager activityManager = ActivityManager.getService();
    private final OplusQSFgsListAdapter appListAdapter;
    private final Context context;
    private Function<Boolean, Boolean> dismissAction;
    private final QSFgs qsFgs;
    private final QSQuickEntrance quickEntrance;
    private final OplusSeparateQSPlugin.IStub stub;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void handleClick(QSFgs.QSRunningApp qSRunningApp);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSFgsDialogController(Context context, QSFgs qSFgs, QSQuickEntrance qSQuickEntrance, OplusSeparateQSPlugin.IStub iStub) {
        this.context = context;
        this.qsFgs = qSFgs;
        this.quickEntrance = qSQuickEntrance;
        this.stub = iStub;
        OplusQSFgsDialogController$appListAdapter$1 oplusQSFgsDialogController$appListAdapter$1 = new OplusQSFgsDialogController$appListAdapter$1();
        oplusQSFgsDialogController$appListAdapter$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.appListAdapter = new OplusQSFgsListAdapter(oplusQSFgsDialogController$appListAdapter$1);
    }

    public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
        View viewInflate = LayoutInflater.from(context).inflate(2131559342, viewGroup, z);
        COUIRecyclerView cOUIRecyclerViewRequireViewById = viewInflate.requireViewById(2131363119);
        cOUIRecyclerViewRequireViewById.setLayoutManager(new LinearLayoutManager(context));
        cOUIRecyclerViewRequireViewById.setAdapter(this.appListAdapter);
        return viewInflate;
    }

    public int getMetricsCategory() {
        return 404;
    }

    public String getTag() {
        return "fgs_manager";
    }

    public CharSequence getTitle() {
        return this.context.getResources().getString(2131955704);
    }

    public final void onDismiss() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->OnDismiss");
        }
        this.dismissAction = null;
        this.qsFgs.setHasDialog(false);
    }

    public final void onShow() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->onShow");
        }
        this.qsFgs.setHasDialog(true);
    }

    public final void showDialog(View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->showDialog");
        }
        Function<Boolean, Boolean> function = this.dismissAction;
        if (function != null) {
            function.apply(Boolean.FALSE);
        }
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new showDialog.2(this, view, (Continuation) null), 3);
    }

    public final void stopPackage(int i, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->".concat("stopPackage: " + i + ", " + str));
        }
        try {
            this.activityManager.stopAppForUser(str, i);
        } catch (RemoteException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "stopPackage exception: ", e.getMessage(), LogUtil.TAG_QS, TAG);
        }
    }

    public final void updateAppItemsLocked(List<QSFgs.QSRunningApp> list) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->".concat("updateAppItemsLocked: dialog: " + this.dismissAction + ", size: " + list.size()));
        }
        if (this.dismissAction == null) {
            return;
        }
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().getImmediate()), (CoroutineContext) null, (CoroutineStart) null, new updateAppItemsLocked.2(this, list, (Continuation) null), 3);
    }
}
