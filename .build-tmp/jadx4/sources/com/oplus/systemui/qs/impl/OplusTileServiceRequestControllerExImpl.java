package com.oplus.systemui.qs.impl;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.external.TileData;
import com.android.systemui.qs.external.TileServiceRequestController;
import com.android.systemui.qs.tile.OplusTileServiceRequestControllerEx;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.oplus.systemui.qs.tileimpl.OplusQSIconViewImpl;
import com.oplus.systemui.qs.tileimpl.OplusQSTileViewImpl;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileServiceRequestControllerExImpl extends OplusTileServiceRequestControllerEx {
    /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View, android.widget.LinearLayout, com.oplus.systemui.qs.base.tile.OplusQSTileBaseView, com.oplus.systemui.qs.tileimpl.OplusQSTileViewImpl] */
    public Dialog createTileRequestDialog(Context context, TileData tileData, TileServiceRequestController.SingleShotConsumer singleShotConsumer, Function2 function2) {
        Drawable drawableLoadDrawable;
        createTileRequestDialog.dialogClickListener.1 r0 = new createTileRequestDialog.dialogClickListener.1();
        r0.$dialogResponse = singleShotConsumer;
        VarHandle.storeStoreFence();
        COUIAlertDialogBuilder title = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(context)).setTitle(context.getString(2131955701, tileData.getAppName()));
        ?? oplusQSTileViewImpl = new OplusQSTileViewImpl(context, new OplusQSIconViewImpl(context), true);
        oplusQSTileViewImpl.setGravity(49);
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).label = tileData.getLabel();
        ((QSTile.State) booleanState).handlesLongClick = false;
        Icon icon = tileData.getIcon();
        ((QSTile.State) booleanState).icon = (icon == null || (drawableLoadDrawable = icon.loadDrawable(context)) == null) ? QSTileImpl.ResourceIcon.get(2131232501) : new QSTileImpl.DrawableIcon(drawableLoadDrawable);
        oplusQSTileViewImpl.onStateChanged(booleanState);
        createTileView.1 r3 = new createTileView.1();
        r3.$tile = oplusQSTileViewImpl;
        r3.$context = context;
        VarHandle.storeStoreFence();
        oplusQSTileViewImpl.post(r3);
        AlertDialog alertDialogCreate = title.setView((View) oplusQSTileViewImpl).setPositiveButton(2131955697, r0).setNegativeButton(2131955699, r0).create();
        if (alertDialogCreate.getWindow() == null) {
            return super.createTileRequestDialog(context, tileData, singleShotConsumer, function2);
        }
        SystemUIDialog.setWindowOnTop(alertDialogCreate, ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing);
        SystemUIDialog.setShowForAllUsers(alertDialogCreate, true);
        createTileRequestDialog.1 r7 = new createTileRequestDialog.1();
        r7.$dialogResponse = singleShotConsumer;
        VarHandle.storeStoreFence();
        SystemUIDialog.registerDismissListener(alertDialogCreate, r7);
        return alertDialogCreate;
    }
}
