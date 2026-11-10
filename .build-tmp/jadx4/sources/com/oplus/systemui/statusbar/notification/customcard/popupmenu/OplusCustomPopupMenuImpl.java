package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.custom.CustomRowMenuData;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.coui.appcompat.poplist.COUIPopupListWindow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.helper.SmallAppHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomPopupMenuImpl implements OplusCustomPopupMenu {
    public static final String ACTION_CUSTOM_NOTIFICATION_ACTION_CLOCE_ONCE_CLICK = "oppo.intent.action.NOTIFICATION_CUSTOM_ACTION_CLOCE_ONCE_CLICK";
    public static final String ACTION_CUSTOM_NOTIFICATION_ACTION_SETTING_CLICK = "oppo.intent.action.NOTIFICATION_CUSTOM_ACTION_SETTING_CLICK";
    public static final Companion Companion = new Companion();
    private static final String EXTRA_APP_NAME = "app_name";
    private static final String EXTRA_CHANNEL_ID = "channel_id";
    private static final String EXTRA_IS_SMALL_APP = "small_app";
    private static final String EXTRA_PKG_NAME = "pkg_name";
    private static final String EXTRA_UID = "uid";
    private static final String TAG = "OplusCustomPopupMenuImpl";
    private AlertDialog alertDialog;
    private final Context context;
    private final KeyguardViewMediator keyguardViewMediator;
    private View menuAnchorView;
    public int menuContentWidth;
    private COUIPopupListWindow popupMenu;
    private final Lazy positionHelper$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static OplusCustomPopupMenuPositionHelper $r8$lambda$xcAtqrUxVU_pohmstXjeCOtxNOk(OplusCustomPopupMenuImpl oplusCustomPopupMenuImpl) {
        return new OplusCustomPopupMenuPositionHelper(oplusCustomPopupMenuImpl.context);
    }

    public OplusCustomPopupMenuImpl(Context context, KeyguardViewMediator keyguardViewMediator) {
        this.context = context;
        this.keyguardViewMediator = keyguardViewMediator;
        this.menuContentWidth = context.getResources().getDimensionPixelOffset(2131170760);
        OplusCustomPopupMenuImpl$$ExternalSyntheticLambda0 oplusCustomPopupMenuImpl$$ExternalSyntheticLambda0 = new OplusCustomPopupMenuImpl$$ExternalSyntheticLambda0();
        oplusCustomPopupMenuImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.positionHelper$delegate = LazyKt__LazyJVMKt.lazy(oplusCustomPopupMenuImpl$$ExternalSyntheticLambda0);
    }

    public static final void access$onClick(OplusCustomPopupMenuImpl oplusCustomPopupMenuImpl, CustomRowEntry customRowEntry, StatusBarNotification statusBarNotification, Function0 function0, int i, String str) throws PendingIntent.CanceledException {
        String multiAppName;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomPopupMenuImpl-->".concat("onClick: position = " + i + ", statisticsEvent=" + str));
        }
        List popupMenuList = customRowEntry.getPopupMenuList();
        if (popupMenuList != null) {
            PendingIntent itemClickIntent = ((CustomRowMenuData) popupMenuList.get(i - 1)).getItemClickIntent();
            if (statusBarNotification == null) {
                if (str != null) {
                    Intent intent = itemClickIntent.getIntent();
                    intent.putExtra("actionType", OplusCustomRowControllerManager.ACTION_TYPE_LEFT_SWIPE);
                    itemClickIntent = PendingIntent.getBroadcast(oplusCustomPopupMenuImpl.context, (int) System.currentTimeMillis(), intent, 67108864);
                }
                itemClickIntent.send();
                return;
            }
            Intent intent2 = itemClickIntent.getIntent();
            if (ACTION_CUSTOM_NOTIFICATION_ACTION_SETTING_CLICK.equals(intent2.getAction())) {
                String channelId = statusBarNotification.getNotification().getChannelId();
                if (channelId == null) {
                    channelId = "miscellaneous";
                }
                SmallAppHelper.Companion companion = SmallAppHelper.Companion;
                boolean zIsSmallApp = companion.isSmallApp(statusBarNotification);
                String smallAppPkg = zIsSmallApp ? companion.getSmallAppPkg(statusBarNotification) : statusBarNotification.getPackageName();
                if (zIsSmallApp) {
                    multiAppName = companion.getSmallAppName(statusBarNotification);
                } else {
                    Context context = oplusCustomPopupMenuImpl.context;
                    String packageName = statusBarNotification.getPackageName();
                    int uid = statusBarNotification.getUid();
                    Set set = AppInfoUtil.BLACK_DND_PACKAGES;
                    multiAppName = OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(UserHandle.getUserId(uid))) ? OplusMultiAppUtils.getMultiAppName(Integer.valueOf(UserHandle.getUserId(uid)), packageName) : AppInfoUtil.getAppNameByPkgName(context, packageName);
                }
                intent2.putExtra(EXTRA_CHANNEL_ID, channelId);
                intent2.putExtra(EXTRA_IS_SMALL_APP, zIsSmallApp);
                intent2.putExtra(EXTRA_PKG_NAME, smallAppPkg);
                intent2.putExtra("app_name", multiAppName);
                intent2.putExtra(EXTRA_UID, statusBarNotification.getUid());
            } else if (ACTION_CUSTOM_NOTIFICATION_ACTION_CLOCE_ONCE_CLICK.equals(intent2.getAction()) && function0 != null) {
                function0.invoke();
            }
            if (str != null) {
                intent2.putExtra("actionType", OplusCustomRowControllerManager.ACTION_TYPE_LEFT_SWIPE);
            }
            PendingIntent.getBroadcast(oplusCustomPopupMenuImpl.context, (int) System.currentTimeMillis(), intent2, 67108864).send();
        }
    }

    private final OplusCustomPopupMenuPositionHelper getPositionHelper() {
        return (OplusCustomPopupMenuPositionHelper) this.positionHelper$delegate.getValue();
    }

    public final void dismissMenu() {
        this.menuAnchorView = null;
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.cancel();
        }
        this.alertDialog = null;
        COUIPopupListWindow cOUIPopupListWindow = this.popupMenu;
        if (cOUIPopupListWindow != null) {
            cOUIPopupListWindow.dismiss();
        }
        this.popupMenu = null;
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenu
    public void onConfigChanged(Configuration configuration) {
        getPositionHelper().onConfigChanged(configuration);
        this.menuContentWidth = this.context.getResources().getDimensionPixelOffset(2131170760);
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenu
    public Object prepare(ViewGroup viewGroup, Rect rect, ViewGroup viewGroup2, CustomRowEntry customRowEntry, PopupWindow.OnDismissListener onDismissListener, Continuation continuation) {
        COUIPopupListWindow cOUIPopupListWindow = new COUIPopupListWindow(this.context);
        cOUIPopupListWindow.setWindowLayoutType(2020);
        cOUIPopupListWindow.setInputMethodMode(2);
        cOUIPopupListWindow.setDismissWhenLayoutChange(false);
        cOUIPopupListWindow.setEnableAddExtraWidth(false);
        if (!ScrimUtil.isLowGaussianLevel(this.context)) {
            cOUIPopupListWindow.setUseBackgroundBlur(true);
        }
        List listCreateMenuList = OplusCustomPopupMenuItemCreator.Companion.getInstance().createMenuList(this.context, customRowEntry);
        if (listCreateMenuList.isEmpty()) {
            return Boxing.boxBoolean(false);
        }
        cOUIPopupListWindow.setItemList(listCreateMenuList);
        View view = new View(this.context);
        getPositionHelper().updateAnchorPosition(viewGroup2, view, rect);
        view.setAlpha(0.0f);
        this.menuAnchorView = view;
        cOUIPopupListWindow.setOnDismissListener(onDismissListener);
        this.popupMenu = cOUIPopupListWindow;
        return Boxing.boxBoolean(true);
    }

    @Override // com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenu
    public Object showMenu(ViewGroup viewGroup, Rect rect, ViewGroup viewGroup2, CustomRowEntry customRowEntry, StatusBarNotification statusBarNotification, Function0 function0, KeyguardDismissUtil keyguardDismissUtil, Function2 function2, String str, Continuation continuation) {
        View view = this.menuAnchorView;
        COUIPopupListWindow cOUIPopupListWindow = this.popupMenu;
        if (view == null || cOUIPopupListWindow == null) {
            return Unit.INSTANCE;
        }
        showMenu.2.1 r1 = new showMenu.2.1();
        r1.$this_apply = cOUIPopupListWindow;
        r1.this$0 = this;
        r1.$onMenuItemClick = function2;
        r1.$keyguardDismissUtil = keyguardDismissUtil;
        r1.$customView = viewGroup;
        r1.$rowEntry = customRowEntry;
        r1.$notification = statusBarNotification;
        r1.$cancelNotificationAction = function0;
        r1.$statisticsEvent = str;
        VarHandle.storeStoreFence();
        cOUIPopupListWindow.setOnItemClickListener(r1);
        cOUIPopupListWindow.setMenuWidth(this.menuContentWidth);
        try {
            OplusCustomPopupMenuPositionHelper.Companion.setValue(COUIPopupListWindow.class, cOUIPopupListWindow, "mPopupMaxWidth", Boxing.boxInt(this.menuContentWidth));
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (getPositionHelper().showAtAbove(rect, viewGroup2.getHeight() / 2)) {
            cOUIPopupListWindow.show(view, true);
        } else {
            cOUIPopupListWindow.show(view, false);
        }
        return Unit.INSTANCE;
    }
}
