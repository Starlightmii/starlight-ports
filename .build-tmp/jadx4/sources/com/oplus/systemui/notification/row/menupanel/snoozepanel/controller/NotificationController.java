package com.oplus.systemui.notification.row.menupanel.snoozepanel.controller;

import android.app.NotificationChannel;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.SnoozeCriterion;
import android.util.KeyValueListParser;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationSnooze;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.ButtonModel;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.ButtonTemplate;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelHelper;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.SettingsPanelModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationController implements BaseController {
    public static final String ACTION_SNOOZE = "snooze";
    private static final String ACTION_SNOOZE_SELECT_ = "snooze_select_";
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationController";
    private final Handler backgroundHandler;
    private final Context context;
    private final KeyValueListParser parser = new KeyValueListParser(',');
    private ExpandableNotificationRow row;
    private NotificationSnooze.NotificationSnoozeOption selectOption;
    private final SettingsPanelHelper settingsPanelHelper;
    private NotificationChannel singleNotificationChannel;
    private NotificationController$snoozeOptionsObserver$1 snoozeOptionsObserver;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.NotificationController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ NotificationController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            NotificationController.access$initSnoozeOption(this.this$0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.NotificationController$snoozeOptionsObserver$1] */
    public NotificationController(Context context, SettingsPanelHelper settingsPanelHelper, final Handler handler) {
        this.context = context;
        this.settingsPanelHelper = settingsPanelHelper;
        this.backgroundHandler = handler;
        this.snoozeOptionsObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.NotificationController$snoozeOptionsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                NotificationController.access$initSnoozeOption(this.this$0);
            }
        };
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                contentResolver.registerContentObserver(Settings.Global.getUriFor("notification_snooze_options"), false, this.snoozeOptionsObserver, -1);
            }
        } catch (Exception e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("registerContentObserver Exception: ", Log.getStackTraceString(e), "NotificationController-->", "SystemUi--Notification");
            }
        }
    }

    public static final void access$initSnoozeOption(NotificationController notificationController) {
        try {
            notificationController.parser.setString(Settings.Global.getString(notificationController.context.getContentResolver(), "notification_snooze_options"));
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Bad snooze constants");
        }
    }

    public final void reset() {
        this.singleNotificationChannel = null;
        this.row = null;
        this.selectOption = null;
    }

    @Override // com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.BaseController
    public void resolveClickEvent(String str) {
        List<ButtonModel> actions;
        Runnable action;
        Object objValueOf;
        NotificationListContainer notificationListContainer;
        NotificationSwipeActionHelper swipeActionHelper;
        if (!Intrinsics.areEqual(str, ACTION_SNOOZE)) {
            SettingsPanelModel settingsModel = this.settingsPanelHelper.getSettingsModel();
            if (settingsModel == null || (actions = settingsModel.getActions()) == null) {
                return;
            }
            for (ButtonModel buttonModel : actions) {
                if (Intrinsics.areEqual(buttonModel.getTemplate().getClickAction(), str) && (action = buttonModel.getTemplate().getAction()) != null) {
                    action.run();
                }
            }
            return;
        }
        NotificationSnooze.NotificationSnoozeOption notificationSnoozeOption = this.selectOption;
        if (notificationSnoozeOption != null) {
            ExpandableNotificationRow expandableNotificationRow = this.row;
            if (expandableNotificationRow != null && (notificationListContainer = ((NotificationStackScrollLayoutController) this.settingsPanelHelper.getStackScrollLayoutControllerLazy().get()).getNotificationListContainer()) != null && (swipeActionHelper = notificationListContainer.getSwipeActionHelper()) != null) {
                swipeActionHelper.snooze(expandableNotificationRow.getEntry().getSbn(), notificationSnoozeOption);
            }
            objValueOf = Integer.valueOf(notificationSnoozeOption.getMinutesToSnoozeFor());
        } else {
            objValueOf = "null option";
        }
        this.settingsPanelHelper.dismissSettingsPanel("snooze for " + objValueOf);
    }

    @Override // com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.BaseController
    public void resolveSettingsModel(SettingsPanelModel settingsPanelModel) {
        List listEmptyList;
        NotificationEntry entry;
        settingsPanelModel.clearActions();
        settingsPanelModel.resolveModel();
        if (settingsPanelModel.getContentType() != 7) {
            settingsPanelModel.setHintString("");
            return;
        }
        Resources resources = this.context.getResources();
        int i = this.parser.getInt("default", resources.getInteger(2131427407));
        int[] intArray = this.parser.getIntArray("options_array", resources.getIntArray(2130903152));
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= intArray.length || i2 >= NotificationSnooze.getAccessibilityActions().length) {
                break;
            }
            int i3 = intArray[i2];
            boolean z2 = i3 >= 60;
            String strIcuMessageFormat = PluralMessageFormaterKt.icuMessageFormat(resources, z2 ? 2131956485 : 2131956486, z2 ? i3 / 60 : i3);
            NotificationSnooze.NotificationSnoozeOption notificationSnoozeOption = new NotificationSnooze.NotificationSnoozeOption((SnoozeCriterion) null, i3, strIcuMessageFormat, strIcuMessageFormat, new AccessibilityNodeInfo.AccessibilityAction(NotificationSnooze.getAccessibilityActions()[i2], strIcuMessageFormat));
            if (i2 != 0 && i3 != i) {
                z = false;
            }
            resolveSnoozeOptionButton(settingsPanelModel, notificationSnoozeOption, z);
            i2++;
        }
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow == null || (entry = expandableNotificationRow.getEntry()) == null || (listEmptyList = entry.getSnoozeCriteria()) == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        int iMin = Math.min(1, listEmptyList.size());
        for (int i4 = 0; i4 < iMin; i4++) {
            SnoozeCriterion snoozeCriterion = (SnoozeCriterion) listEmptyList.get(i4);
            resolveSnoozeOptionButton(settingsPanelModel, new NotificationSnooze.NotificationSnoozeOption(snoozeCriterion, 0, snoozeCriterion.getExplanation(), snoozeCriterion.getConfirmation(), new AccessibilityNodeInfo.AccessibilityAction(2131361962, snoozeCriterion.getExplanation())), false);
        }
        settingsPanelModel.setAppModifiable(false);
        settingsPanelModel.setLargeSpaceMargin(true);
        settingsPanelModel.getButtonCommon().setClickAction(ACTION_SNOOZE);
    }

    public final void resolveSnoozeOptionButton(SettingsPanelModel settingsPanelModel, NotificationSnooze.NotificationSnoozeOption notificationSnoozeOption, boolean z) {
        ButtonModel buttonModel = new ButtonModel();
        buttonModel.getTemplate().setText(notificationSnoozeOption.getDescription());
        buttonModel.getTemplate().setClickAction(ACTION_SNOOZE_SELECT_ + notificationSnoozeOption.getMinutesToSnoozeFor());
        ButtonTemplate template = buttonModel.getTemplate();
        resolveSnoozeOptionButton.1 r2 = new resolveSnoozeOptionButton.1();
        r2.$settingsModel = settingsPanelModel;
        r2.$buttonModel = buttonModel;
        r2.this$0 = this;
        r2.$option = notificationSnoozeOption;
        VarHandle.storeStoreFence();
        template.setAction(r2);
        ButtonTemplate template2 = buttonModel.getTemplate();
        NotificationController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationController$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        template2.setDoClickAction(externalSyntheticLambda0);
        if (z) {
            settingsPanelModel.resetActionsCheckedState();
            this.selectOption = notificationSnoozeOption;
        }
        buttonModel.getTemplate().setChecked(z);
        settingsPanelModel.getActions().add(buttonModel);
    }

    @Override // com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.BaseController
    public void setRow(ExpandableNotificationRow expandableNotificationRow) {
        this.row = expandableNotificationRow;
    }
}
