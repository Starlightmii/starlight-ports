package com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.util.SeparateModeHelper;
import com.coui.appcompat.dialog.COUIAlertDialogBuilder;
import com.coui.appcompat.poplist.COUIPopupListWindow;
import com.oplus.systemui.notification.dialog.SingleChoiceDialogBuilder;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelHelper;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuImpl implements NotificationPopupMenu {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationPopupMenu";
    private final Context context;
    private AlertDialog dialog;
    private final KeyguardViewMediator keyguardViewMediator;
    private View menuAnchorView;
    public int menuContentWidth;
    private final Lazy menuPositionHelper$delegate;
    private final NotificationPopupMenuOperation notificationPopupMenuOperation;
    private COUIPopupListWindow popupMenu;
    private final Lazy popupMenuItemCreator$delegate = LazyKt__LazyJVMKt.lazy(new NotificationPopupMenuImpl$$ExternalSyntheticLambda0());
    private final SettingsPanelHelper settingsPanelHelper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuImpl$prepare$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NotificationPopupMenuImpl.this.prepare(null, null, null, null, this);
        }
    }

    public static Unit $r8$lambda$5B04lfYxcXYqGKZS3kwaOQL5zjc(NotificationPopupMenuImpl notificationPopupMenuImpl, ExpandableNotificationRow expandableNotificationRow) {
        notificationPopupMenuImpl.notificationPopupMenuOperation.doOperation(NotificationPopupMenuOperation.Operation.NOTIFICATION_SETTING, expandableNotificationRow);
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, "close_click_category");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$6dt-WbUy784a5smwwURAC9fzvhw, reason: not valid java name */
    public static Unit m455$r8$lambda$6dtWbUy784a5smwwURAC9fzvhw(NotificationPopupMenuImpl notificationPopupMenuImpl) {
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$7BgK599ohEzRcwextv0-AezvLUI, reason: not valid java name */
    public static Unit m456$r8$lambda$7BgK599ohEzRcwextv0AezvLUI(Ref.BooleanRef booleanRef, NotificationPopupMenuImpl$.ExternalSyntheticLambda4 externalSyntheticLambda4, NotificationPopupMenuImpl notificationPopupMenuImpl, ExpandableNotificationRow expandableNotificationRow) {
        booleanRef.element = true;
        externalSyntheticLambda4.invoke();
        notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, notificationPopupMenuImpl.notificationPopupMenuOperation.isCloseChannel() ? "close_channel" : "close_all");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$7gsnwwJnd9GgWxB9MO5-1c8EU0A, reason: not valid java name */
    public static NotificationPopupMenuPositionHelper m457$r8$lambda$7gsnwwJnd9GgWxB9MO51c8EU0A(NotificationPopupMenuImpl notificationPopupMenuImpl) {
        return new NotificationPopupMenuPositionHelper(notificationPopupMenuImpl.context);
    }

    public static Unit $r8$lambda$D7YZjWS6jjELuXJ4TpFjALvAGzA(NotificationPopupMenuImpl notificationPopupMenuImpl) {
        notificationPopupMenuImpl.dialog = null;
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$dXuDI3IchrlHI_07zK6aOn4SiEQ(NotificationPopupMenuImpl notificationPopupMenuImpl) {
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$oRMeZsnX8lXjXR1JeVACyk42ZoE(NotificationPopupMenuImpl notificationPopupMenuImpl, int i) {
        notificationPopupMenuImpl.notificationPopupMenuOperation.setCloseChannel(i == 0);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$qjnmqJKAlYQo231OL9bpjV8KGjM(NotificationPopupMenuImpl notificationPopupMenuImpl, NotificationPopupMenu.MenuItem menuItem, ExpandableNotificationRow expandableNotificationRow) {
        notificationPopupMenuImpl.notificationPopupMenuOperation.doOperation(menuItem.getMenuOperation(), expandableNotificationRow);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$syyUZTFJxL9xoN3rNdMk8YOnzAQ(NotificationPopupMenuImpl notificationPopupMenuImpl) {
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$wvz8RCf5LgEXikBYHCeE-Bmj9TA, reason: not valid java name */
    public static Unit m458$r8$lambda$wvz8RCf5LgEXikBYHCeEBmj9TA(Ref.BooleanRef booleanRef, NotificationPopupMenuImpl notificationPopupMenuImpl, ExpandableNotificationRow expandableNotificationRow) {
        booleanRef.element = true;
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, "close_undo");
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$yoC3zLuvi98J4wDh7QgkIXreejg(Ref.BooleanRef booleanRef, NotificationPopupMenuImpl notificationPopupMenuImpl, ExpandableNotificationRow expandableNotificationRow) {
        booleanRef.element = true;
        AlertDialog alertDialog = notificationPopupMenuImpl.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, "close_undo");
        return Unit.INSTANCE;
    }

    public NotificationPopupMenuImpl(Context context, NotificationPopupMenuOperation notificationPopupMenuOperation, KeyguardViewMediator keyguardViewMediator, SettingsPanelHelper settingsPanelHelper) {
        this.context = context;
        this.notificationPopupMenuOperation = notificationPopupMenuOperation;
        this.keyguardViewMediator = keyguardViewMediator;
        this.settingsPanelHelper = settingsPanelHelper;
        this.menuContentWidth = context.getResources().getDimensionPixelOffset(2131170760);
        NotificationPopupMenuImpl$$ExternalSyntheticLambda1 notificationPopupMenuImpl$$ExternalSyntheticLambda1 = new NotificationPopupMenuImpl$$ExternalSyntheticLambda1(0);
        notificationPopupMenuImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.menuPositionHelper$delegate = LazyKt__LazyJVMKt.lazy(notificationPopupMenuImpl$$ExternalSyntheticLambda1);
    }

    public static final void access$onClick(NotificationPopupMenuImpl notificationPopupMenuImpl, NotificationPopupMenu.MenuItem menuItem, ExpandableNotificationRow expandableNotificationRow) {
        Object obj;
        AlertDialog alertDialogShowDialog;
        ApplicationInfo applicationInfo;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationPopupMenu-->".concat("onClick: " + menuItem.getMenuOperation()));
        }
        NotificationPopupMenuImpl$$ExternalSyntheticLambda1 notificationPopupMenuImpl$$ExternalSyntheticLambda1 = new NotificationPopupMenuImpl$$ExternalSyntheticLambda1(2);
        notificationPopupMenuImpl$$ExternalSyntheticLambda1.f$0 = notificationPopupMenuImpl;
        VarHandle.storeStoreFence();
        NotificationPopupMenuImpl$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda4(0);
        externalSyntheticLambda4.f$0 = notificationPopupMenuImpl;
        externalSyntheticLambda4.f$1 = menuItem;
        externalSyntheticLambda4.f$2 = expandableNotificationRow;
        VarHandle.storeStoreFence();
        StatusBarNotification sbn = expandableNotificationRow.getEntry().getSbn();
        AlertDialog alertDialogCreate = null;
        try {
            PackageManager packageManagerForUser = sbn.getPackageManagerForUser(notificationPopupMenuImpl.context);
            obj = Result.constructor-impl((packageManagerForUser == null || (applicationInfo = packageManagerForUser.getApplicationInfo(sbn.getPackageName(), 795136)) == null) ? null : NotificationUtil.loadHeaderAppName(sbn, packageManagerForUser, applicationInfo));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            obj = "";
        }
        String packageName = (String) obj;
        if (packageName == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getAppName: get app name fail, use package name: ", sbn.getPackageName(), "NotificationPopupMenu-->", "SystemUi--Notification");
            }
            packageName = sbn.getPackageName();
        }
        int i = 1;
        switch (menuItem.getMenuOperation().ordinal()) {
            case 0:
            case 1:
                externalSyntheticLambda4.invoke();
                notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, menuItem.getMenuOperation() == NotificationPopupMenuOperation.Operation.NOTIFICATION_TOP ? "conversation_favorite" : "conversateion_unfavorite");
                break;
            case 2:
                FeatureOption.INSTANCE.getClass();
                int i2 = FeatureOption.isExpRegion() ? 2131954919 : 2131954918;
                String string = notificationPopupMenuImpl.context.getResources().getString(2131954920);
                String string2 = notificationPopupMenuImpl.context.getResources().getString(i2, packageName);
                String string3 = notificationPopupMenuImpl.context.getResources().getString(2131954909);
                Function0 externalSyntheticLambda5 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda5(0);
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda5).f$0 = externalSyntheticLambda4;
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda5).f$1 = notificationPopupMenuImpl;
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda5).f$2 = expandableNotificationRow;
                VarHandle.storeStoreFence();
                NotificationPopupMenuImpl$$ExternalSyntheticLambda1 notificationPopupMenuImpl$$ExternalSyntheticLambda2 = new NotificationPopupMenuImpl$$ExternalSyntheticLambda1(3);
                notificationPopupMenuImpl$$ExternalSyntheticLambda2.f$0 = notificationPopupMenuImpl;
                VarHandle.storeStoreFence();
                notificationPopupMenuImpl.dialog = notificationPopupMenuImpl.showDialog(string, string2, string3, externalSyntheticLambda5, notificationPopupMenuImpl$$ExternalSyntheticLambda1, notificationPopupMenuImpl$$ExternalSyntheticLambda2);
                break;
            case 3:
                externalSyntheticLambda4.invoke();
                notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, "zoom_open");
                break;
            case 4:
                NotificationPopupMenuImpl$.ExternalSyntheticLambda5 externalSyntheticLambda6 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda5(2);
                externalSyntheticLambda6.f$0 = externalSyntheticLambda4;
                externalSyntheticLambda6.f$1 = notificationPopupMenuImpl;
                externalSyntheticLambda6.f$2 = expandableNotificationRow;
                VarHandle.storeStoreFence();
                NotificationPopupMenuImpl$$ExternalSyntheticLambda1 notificationPopupMenuImpl$$ExternalSyntheticLambda3 = new NotificationPopupMenuImpl$$ExternalSyntheticLambda1(i);
                notificationPopupMenuImpl$$ExternalSyntheticLambda3.f$0 = notificationPopupMenuImpl;
                VarHandle.storeStoreFence();
                COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(notificationPopupMenuImpl.context), 2132017606);
                cOUIAlertDialogBuilder.setTitle(cOUIAlertDialogBuilder.getContext().getResources().getString(2131954973));
                View viewCreateSettingPanel = notificationPopupMenuImpl.settingsPanelHelper.createSettingPanel(expandableNotificationRow, notificationPopupMenuImpl$$ExternalSyntheticLambda1);
                if (viewCreateSettingPanel != null) {
                    showSingleChoiceDialog.1.1 r5 = new showSingleChoiceDialog.1.1(1);
                    r5.$onOperationClick = externalSyntheticLambda6;
                    VarHandle.storeStoreFence();
                    cOUIAlertDialogBuilder.setPositiveButton(2131954998, r5);
                    showSingleChoiceDialog.1.1 r0 = new showSingleChoiceDialog.1.1(2);
                    r0.$onOperationClick = notificationPopupMenuImpl$$ExternalSyntheticLambda3;
                    VarHandle.storeStoreFence();
                    cOUIAlertDialogBuilder.setNegativeButton(2131952564, r0);
                    cOUIAlertDialogBuilder.setView(viewCreateSettingPanel);
                    cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
                    showDialog.1.3 r1 = new showDialog.1.3(1);
                    r1.$onDismiss = notificationPopupMenuImpl$$ExternalSyntheticLambda1;
                    VarHandle.storeStoreFence();
                    cOUIAlertDialogBuilder.setOnDismissListener(r1);
                    cOUIAlertDialogBuilder.setWindowType(2017);
                    alertDialogCreate = cOUIAlertDialogBuilder.create();
                    alertDialogCreate.show();
                    cOUIAlertDialogBuilder.updateViewAfterShown();
                }
                notificationPopupMenuImpl.dialog = alertDialogCreate;
                break;
            case 5:
                String string4 = notificationPopupMenuImpl.context.getResources().getString(2131954912);
                String string5 = notificationPopupMenuImpl.context.getResources().getString(2131954911, packageName);
                String string6 = notificationPopupMenuImpl.context.getResources().getString(2131954905);
                Function0 externalSyntheticLambda7 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda5(1);
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda7).f$0 = externalSyntheticLambda4;
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda7).f$1 = notificationPopupMenuImpl;
                ((NotificationPopupMenuImpl$.ExternalSyntheticLambda5) externalSyntheticLambda7).f$2 = expandableNotificationRow;
                VarHandle.storeStoreFence();
                NotificationPopupMenuImpl$$ExternalSyntheticLambda1 notificationPopupMenuImpl$$ExternalSyntheticLambda4 = new NotificationPopupMenuImpl$$ExternalSyntheticLambda1(4);
                notificationPopupMenuImpl$$ExternalSyntheticLambda4.f$0 = notificationPopupMenuImpl;
                VarHandle.storeStoreFence();
                notificationPopupMenuImpl.dialog = notificationPopupMenuImpl.showDialog(string4, string5, string6, externalSyntheticLambda7, notificationPopupMenuImpl$$ExternalSyntheticLambda1, notificationPopupMenuImpl$$ExternalSyntheticLambda4);
                break;
            case 6:
                externalSyntheticLambda4.invoke();
                notificationPopupMenuImpl.recordPopupMenuOperation(expandableNotificationRow, "settings");
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                FeatureOption.INSTANCE.getClass();
                boolean z = (FeatureOption.isExpRegion() || expandableNotificationRow.mIsSummaryWithChildren) ? false : true;
                notificationPopupMenuImpl.notificationPopupMenuOperation.setCloseChannel(z);
                if (z) {
                    Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    String string7 = notificationPopupMenuImpl.context.getResources().getString(2131954917);
                    String string8 = notificationPopupMenuImpl.context.getResources().getString(2131954908);
                    CharSequence[] textArray = notificationPopupMenuImpl.context.getResources().getTextArray(2130903260);
                    CharSequence[] textArray2 = notificationPopupMenuImpl.context.getResources().getTextArray(2130903259);
                    Function1 externalSyntheticLambda13 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda13();
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda13) externalSyntheticLambda13).f$0 = notificationPopupMenuImpl;
                    VarHandle.storeStoreFence();
                    Function0 externalSyntheticLambda14 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda14(0);
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda14) externalSyntheticLambda14).f$0 = notificationPopupMenuImpl;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda14) externalSyntheticLambda14).f$1 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    NotificationPopupMenuImpl$.ExternalSyntheticLambda11 externalSyntheticLambda11 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda11(1);
                    externalSyntheticLambda11.f$0 = booleanRef2;
                    externalSyntheticLambda11.f$1 = externalSyntheticLambda4;
                    externalSyntheticLambda11.f$2 = notificationPopupMenuImpl;
                    externalSyntheticLambda11.f$3 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    NotificationPopupMenuImpl$.ExternalSyntheticLambda12 externalSyntheticLambda12 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda12(1);
                    externalSyntheticLambda12.f$0 = notificationPopupMenuImpl$$ExternalSyntheticLambda1;
                    externalSyntheticLambda12.f$1 = booleanRef2;
                    externalSyntheticLambda12.f$2 = notificationPopupMenuImpl;
                    externalSyntheticLambda12.f$3 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    NotificationPopupMenuImpl$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda3(1);
                    externalSyntheticLambda3.f$0 = booleanRef2;
                    externalSyntheticLambda3.f$1 = notificationPopupMenuImpl;
                    externalSyntheticLambda3.f$2 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    NotificationPopupMenuImpl$.ExternalSyntheticLambda14 externalSyntheticLambda15 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda14(1);
                    externalSyntheticLambda15.f$0 = notificationPopupMenuImpl;
                    externalSyntheticLambda15.f$1 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    SingleChoiceDialogBuilder singleChoiceDialogBuilder = new SingleChoiceDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(notificationPopupMenuImpl.context), 2132017606);
                    singleChoiceDialogBuilder.setAppName(packageName);
                    singleChoiceDialogBuilder.setTitle(string7);
                    singleChoiceDialogBuilder.setBlurBackgroundDrawable(true);
                    singleChoiceDialogBuilder.setSingleChoiceItemsWithLink(textArray, textArray2, externalSyntheticLambda13, externalSyntheticLambda14);
                    showSingleChoiceDialog.1.1 r2 = new showSingleChoiceDialog.1.1(0);
                    r2.$onOperationClick = externalSyntheticLambda11;
                    VarHandle.storeStoreFence();
                    singleChoiceDialogBuilder.setPositiveButton(string8, r2);
                    showSingleChoiceDialog.1.1 r3 = new showSingleChoiceDialog.1.1(3);
                    r3.$onOperationClick = externalSyntheticLambda3;
                    VarHandle.storeStoreFence();
                    singleChoiceDialogBuilder.setNegativeButton(2131952564, r3);
                    showDialog.1.3 r4 = new showDialog.1.3(2);
                    r4.$onDismiss = externalSyntheticLambda12;
                    VarHandle.storeStoreFence();
                    singleChoiceDialogBuilder.setOnDismissListener(r4);
                    singleChoiceDialogBuilder.setWindowType(2017);
                    alertDialogShowDialog = singleChoiceDialogBuilder.create();
                    DialogInterface.OnShowListener onShowListener = new showSingleChoiceDialog.2.1.1();
                    ((showSingleChoiceDialog.2.1.1) onShowListener).$onShow = externalSyntheticLambda15;
                    VarHandle.storeStoreFence();
                    alertDialogShowDialog.setOnShowListener(onShowListener);
                    alertDialogShowDialog.show();
                    singleChoiceDialogBuilder.updateViewAfterShown();
                } else {
                    String string9 = notificationPopupMenuImpl.context.getResources().getString(2131954917);
                    String string10 = notificationPopupMenuImpl.context.getResources().getString(2131954916, packageName);
                    String string11 = notificationPopupMenuImpl.context.getResources().getString(2131954908);
                    Function0 externalSyntheticLambda16 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda11(0);
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda11) externalSyntheticLambda16).f$0 = booleanRef;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda11) externalSyntheticLambda16).f$1 = externalSyntheticLambda4;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda11) externalSyntheticLambda16).f$2 = notificationPopupMenuImpl;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda11) externalSyntheticLambda16).f$3 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    Function0 externalSyntheticLambda17 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda12(0);
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda12) externalSyntheticLambda17).f$0 = notificationPopupMenuImpl$$ExternalSyntheticLambda1;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda12) externalSyntheticLambda17).f$1 = booleanRef;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda12) externalSyntheticLambda17).f$2 = notificationPopupMenuImpl;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda12) externalSyntheticLambda17).f$3 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    Function0 externalSyntheticLambda8 = new NotificationPopupMenuImpl$.ExternalSyntheticLambda3(0);
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda3) externalSyntheticLambda8).f$0 = booleanRef;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda3) externalSyntheticLambda8).f$1 = notificationPopupMenuImpl;
                    ((NotificationPopupMenuImpl$.ExternalSyntheticLambda3) externalSyntheticLambda8).f$2 = expandableNotificationRow;
                    VarHandle.storeStoreFence();
                    alertDialogShowDialog = notificationPopupMenuImpl.showDialog(string9, string10, string11, externalSyntheticLambda16, externalSyntheticLambda17, externalSyntheticLambda8);
                }
                notificationPopupMenuImpl.dialog = alertDialogShowDialog;
                break;
            default:
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                break;
        }
    }

    private final NotificationPopupMenuPositionHelper getMenuPositionHelper() {
        return (NotificationPopupMenuPositionHelper) this.menuPositionHelper$delegate.getValue();
    }

    private final NotificationPopupMenuItemCreator getPopupMenuItemCreator() {
        return (NotificationPopupMenuItemCreator) this.popupMenuItemCreator$delegate.getValue();
    }

    public final void dismissMenu(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationPopupMenu-->dismissMenu");
        }
        this.menuAnchorView = null;
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.cancel();
        }
        COUIPopupListWindow cOUIPopupListWindow = this.popupMenu;
        if (z) {
            if (cOUIPopupListWindow != null) {
                cOUIPopupListWindow.dismiss();
            }
        } else if (cOUIPopupListWindow != null) {
            cOUIPopupListWindow.forceDismiss();
        }
        this.dialog = null;
        this.popupMenu = null;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenu
    public void onConfigChanged(Configuration configuration) {
        this.menuContentWidth = this.context.getResources().getDimensionPixelOffset(2131170760);
        getMenuPositionHelper().onConfigChanged(configuration);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenu
    public Object prepare(ExpandableNotificationRow expandableNotificationRow, Rect rect, ViewGroup viewGroup, PopupWindow.OnDismissListener onDismissListener, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        COUIPopupListWindow cOUIPopupListWindow;
        Rect rect2;
        View view;
        Object obj;
        ViewGroup viewGroup2;
        COUIPopupListWindow cOUIPopupListWindow2;
        NotificationPopupMenuImpl notificationPopupMenuImpl;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("prepare:entry=", ex != null ? ex.getEncryptionKey() : null, "NotificationPopupMenu-->", "SystemUi--Notification");
            }
            cOUIPopupListWindow = new COUIPopupListWindow(this.context);
            cOUIPopupListWindow.setWindowLayoutType(2020);
            cOUIPopupListWindow.setInputMethodMode(2);
            cOUIPopupListWindow.setNeedOffsetWhenSetWindowType(SeparateModeHelper.INSTANCE.getSeparateMode());
            cOUIPopupListWindow.setDismissWhenLayoutChange(false);
            cOUIPopupListWindow.setEnableAddExtraWidth(false);
            if (!ScrimUtil.isLowGaussianLevel(this.context)) {
                cOUIPopupListWindow.setUseBackgroundBlur(true);
            }
            View view2 = new View(this.context);
            NotificationPopupMenuItemCreatorImpl popupMenuItemCreator = getPopupMenuItemCreator();
            Context context = this.context;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(expandableNotificationRow);
            anonymousClass1.L$1 = rect;
            anonymousClass1.L$2 = viewGroup;
            anonymousClass1.L$3 = onDismissListener;
            anonymousClass1.L$4 = cOUIPopupListWindow;
            anonymousClass1.L$5 = cOUIPopupListWindow;
            anonymousClass1.L$6 = view2;
            anonymousClass1.L$7 = this;
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            Object objCreateMenuList = popupMenuItemCreator.createMenuList(context, expandableNotificationRow, anonymousClass1);
            if (objCreateMenuList == coroutine_suspended) {
                return coroutine_suspended;
            }
            rect2 = rect;
            view = view2;
            obj = objCreateMenuList;
            viewGroup2 = viewGroup;
            cOUIPopupListWindow2 = cOUIPopupListWindow;
            notificationPopupMenuImpl = this;
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            notificationPopupMenuImpl = (NotificationPopupMenuImpl) anonymousClass1.L$7;
            view = (View) anonymousClass1.L$6;
            cOUIPopupListWindow2 = (COUIPopupListWindow) anonymousClass1.L$5;
            COUIPopupListWindow cOUIPopupListWindow3 = (COUIPopupListWindow) anonymousClass1.L$4;
            PopupWindow.OnDismissListener onDismissListener2 = (PopupWindow.OnDismissListener) anonymousClass1.L$3;
            viewGroup2 = (ViewGroup) anonymousClass1.L$2;
            rect2 = (Rect) anonymousClass1.L$1;
            ResultKt.throwOnFailure(obj2);
            obj = obj2;
            cOUIPopupListWindow = cOUIPopupListWindow3;
            onDismissListener = onDismissListener2;
        }
        cOUIPopupListWindow2.setItemList((List) obj);
        cOUIPopupListWindow2.setOnDismissListener(onDismissListener);
        getMenuPositionHelper().updateAnchorPosition(viewGroup2, view, rect2);
        view.setAlpha(0.0f);
        this.menuAnchorView = view;
        notificationPopupMenuImpl.popupMenu = cOUIPopupListWindow;
        return Unit.INSTANCE;
    }

    public final void recordPopupMenuOperation(ExpandableNotificationRow expandableNotificationRow, String str) {
        if (expandableNotificationRow.mIsHeadsUp) {
            NotificationStatisticUtil.collectNotificationMenuEvent(this.context, expandableNotificationRow.getEntry(), str, "", 1000);
        } else {
            NotificationStatisticUtil.collectNotificationMenuEvent(this.context, expandableNotificationRow.getEntry(), str, "", ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState());
        }
    }

    public final AlertDialog showDialog(String str, String str2, String str3, Function0 function0, Function0 function1, Function0 function2) {
        COUIAlertDialogBuilder cOUIAlertDialogBuilder = new COUIAlertDialogBuilder(OplusThemeApplyUtil.toAppContextTheme(this.context), 2132017606);
        cOUIAlertDialogBuilder.setTitle(str);
        cOUIAlertDialogBuilder.setMessage(str2);
        cOUIAlertDialogBuilder.setBlurBackgroundDrawable(true);
        showDialog.1.1 r3 = new showDialog.1.1(0);
        r3.$onOperationClick = function0;
        VarHandle.storeStoreFence();
        cOUIAlertDialogBuilder.setPositiveButton(str3, r3);
        showDialog.1.1 r4 = new showDialog.1.1(1);
        r4.$onOperationClick = function2;
        VarHandle.storeStoreFence();
        cOUIAlertDialogBuilder.setNegativeButton(2131952564, r4);
        showDialog.1.3 r2 = new showDialog.1.3(0);
        r2.$onDismiss = function1;
        VarHandle.storeStoreFence();
        cOUIAlertDialogBuilder.setOnDismissListener(r2);
        cOUIAlertDialogBuilder.setWindowType(2017);
        return cOUIAlertDialogBuilder.show();
    }

    @Override // com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenu
    public void showMenu(ExpandableNotificationRow expandableNotificationRow, Rect rect, ViewGroup viewGroup, KeyguardDismissUtil keyguardDismissUtil, Function3 function3) {
        View view = this.menuAnchorView;
        COUIPopupListWindow cOUIPopupListWindow = this.popupMenu;
        if (view == null || cOUIPopupListWindow == null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "showMenu, error!  anchorView=" + view + ", popupMenu=" + cOUIPopupListWindow);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusNotificationEntryEx ex = expandableNotificationRow.getEntry().getEx();
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("showMenu: ", ex != null ? ex.getEncryptionKey() : null, "NotificationPopupMenu-->", "SystemUi--Notification");
        }
        showMenu.2.1 r2 = new showMenu.2.1();
        r2.$this_apply = cOUIPopupListWindow;
        r2.this$0 = this;
        r2.$onMenuItemClick = function3;
        r2.$row = expandableNotificationRow;
        r2.$keyguardDismissUtil = keyguardDismissUtil;
        VarHandle.storeStoreFence();
        cOUIPopupListWindow.setOnItemClickListener(r2);
        cOUIPopupListWindow.setMenuWidth(this.menuContentWidth);
        try {
            ReflectionUtils.setValue(COUIPopupListWindow.class, cOUIPopupListWindow, "mPopupMaxWidth", Integer.valueOf(this.menuContentWidth));
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (getMenuPositionHelper().showAtAbove(rect, viewGroup.getHeight())) {
            cOUIPopupListWindow.show(view, true);
        } else {
            cOUIPopupListWindow.show(view, false);
        }
    }
}
