package com.oplus.systemui.plugins.qs.quickentrance.popupwindow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSFgs;
import com.android.systemui.plugins.qs.QSQuickEntrance;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.IComponentHostAbility;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.OplusQSRootViewComponent;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.quickentrance.dialog.OplusQSFgsDialogController;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSMoreButtonPopupWindowController implements QSQuickEntrance.CallBack {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSMoreButtonPopupWindowController";
    private final CoroutineDispatcher bgDispatcher;
    private final Context context;
    private final OplusQSMoreButtonPopupWindowController$defaultCallback$1 defaultCallback;
    private final TileEditViewModel editViewModel;
    private final OplusQSFgsDialogController fgsDialogController;
    public int fgsNumPkg;
    private IComponentHostAbility hostAbility;
    public boolean isFgsNew;
    private final CoroutineScope mainScope;
    private WeakReference<View> moreBtn = new WeakReference<>(null);
    private OplusQSMoreButtonPopupWindow popupWindow;
    private final QSQuickEntrance qsQSQuickEntrance;
    private final OplusQSRootView rootView;
    private final OplusSeparateQSPlugin.IStub stub;
    private String userDescription;
    private Drawable userIcon;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void handleClick(View view, OplusQSMoreButtonPopupWindow.Type type);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$d1nSPOJnXEq_sGZIwQxIIRII8eA(OplusQSMoreButtonPopupWindowController oplusQSMoreButtonPopupWindowController) {
        if (oplusQSMoreButtonPopupWindowController.rootView.isAttachedToWindow()) {
            OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
            if (!((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue() && !((Boolean) companion.getInstance().getSuperPowerSaveMode().getValue()).booleanValue()) {
                SeparateQSStatisicUtil.collectEditTitleTypeEvent(oplusQSMoreButtonPopupWindowController.context, "edit_tile_click", false);
                IComponentHostAbility iComponentHostAbility = oplusQSMoreButtonPopupWindowController.hostAbility;
                if (iComponentHostAbility != null) {
                    ((OplusQSRootViewComponent) iComponentHostAbility).enterEditMode(true);
                }
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public OplusQSMoreButtonPopupWindowController(Context context, OplusQSFgsDialogController oplusQSFgsDialogController, QSQuickEntrance qSQuickEntrance, OplusSeparateQSPlugin.IStub iStub, TileEditViewModel tileEditViewModel, OplusQSRootView oplusQSRootView, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.fgsDialogController = oplusQSFgsDialogController;
        this.qsQSQuickEntrance = qSQuickEntrance;
        this.stub = iStub;
        this.editViewModel = tileEditViewModel;
        this.rootView = oplusQSRootView;
        this.mainScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        OplusQSMoreButtonPopupWindowController$defaultCallback$1 oplusQSMoreButtonPopupWindowController$defaultCallback$1 = new OplusQSMoreButtonPopupWindowController$defaultCallback$1();
        oplusQSMoreButtonPopupWindowController$defaultCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.defaultCallback = oplusQSMoreButtonPopupWindowController$defaultCallback$1;
        this.userDescription = "";
    }

    public final IComponentHostAbility getHostAbility() {
        return this.hostAbility;
    }

    public final boolean hideCouiPopup() {
        this.qsQSQuickEntrance.removeCallBack(this);
        OplusQSMoreButtonPopupWindow oplusQSMoreButtonPopupWindow = this.popupWindow;
        if (oplusQSMoreButtonPopupWindow == null || !oplusQSMoreButtonPopupWindow.isShowing()) {
            return false;
        }
        OplusQSMoreButtonPopupWindow oplusQSMoreButtonPopupWindow2 = this.popupWindow;
        if (oplusQSMoreButtonPopupWindow2 != null) {
            oplusQSMoreButtonPopupWindow2.dismiss();
        }
        return true;
    }

    public void onUserInfoChanged(String str, Drawable drawable) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSMoreButtonPopupWindowController-->".concat("icon: " + drawable));
        }
        this.userDescription = str;
        this.userIcon = drawable;
    }

    public final void setHostAbility(IComponentHostAbility iComponentHostAbility) {
        this.hostAbility = iComponentHostAbility;
    }

    public final void showEdit$1() {
        QSQuickEntrance qSQuickEntrance = this.qsQSQuickEntrance;
        OplusQSMoreButtonPopupWindowController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSMoreButtonPopupWindowController$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        qSQuickEntrance.handleEditClick(externalSyntheticLambda0);
    }

    public final void showPopupWindow(Context context, View view) {
        this.moreBtn = new WeakReference<>(view);
        hideCouiPopup();
        CoroutineTracingKt.launchTraced$default(this.mainScope, "OplusQSMoreButtonPopupWindowController-launch-showPopupWindow", this.bgDispatcher, (CoroutineStart) null, new showPopupWindow.1(this, context, view, (Continuation) null), 4);
    }

    public final void updateAppItemsLocked(List<QSFgs.QSRunningApp> list) {
        this.fgsDialogController.updateAppItemsLocked(list);
    }
}
