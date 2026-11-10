package com.oplus.systemui.qs.tiles;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.service.notification.ScheduleCalendar;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.qs.deprecated.tileimpl.DndTile;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplus.systemui.statusbar.notification.helper.DndAlertHelper;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.utils.DndInfoUtil;
import com.oplusos.systemui.common.utils.ValidRuleInfo;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusDndTile extends DndTile {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Adapter mAdapter;
    public final ColorDndDetailAdapter mDetailAdapter;
    public final OplusQSDetailInteractor mDetailInteractor;
    public LinearLayout mDetailTitle;
    Lazy mDndAlertHelper;
    public ImageView mDndEmptyIcon;
    public ListView mDndList;
    public View mEmpty;
    public final Handler mHandler;
    public LinearLayout mHeaderContainer;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public View mVisible;
    public final AnonymousClass1 mZenModeCallback;
    public TextView mZenModeDuration;
    public final int[] mZenModeDurationTime;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tiles.OplusDndTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ZenModeController.Callback {
        public /* synthetic */ OplusDndTile this$0;

        public final void onConfigChanged(ZenModeConfig zenModeConfig) {
            OplusDndTile oplusDndTile = this.this$0;
            oplusDndTile.refreshState((Object) null);
            if (oplusDndTile.mDetailInteractor.isShowing()) {
                oplusDndTile.mDetailAdapter.updatePanel();
            }
        }

        public final void onZenChanged(int i) {
            OplusDndTile oplusDndTile = this.this$0;
            Integer numValueOf = Integer.valueOf(i);
            int i2 = OplusDndTile.$r8$clinit;
            oplusDndTile.refreshState(numValueOf);
            if (oplusDndTile.mDetailInteractor.isShowing()) {
                oplusDndTile.mDetailAdapter.updatePanel();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ColorDndDetailAdapter implements DetailAdapter {
        public RelativeLayout mDetail;
        public /* synthetic */ OplusDndTile this$0;

        public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
            this.mDetail = (RelativeLayout) LayoutInflater.from(context).inflate(2131559339, viewGroup, z);
            OplusDndTile oplusDndTile = this.this$0;
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(2131559341, viewGroup, z);
            oplusDndTile.mDetailTitle = linearLayout;
            oplusDndTile.mZenModeDuration = (TextView) linearLayout.findViewById(2131366137);
            LinearLayout linearLayout2 = new LinearLayout(context);
            oplusDndTile.mHeaderContainer = linearLayout2;
            linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            oplusDndTile.mHeaderContainer.addView(oplusDndTile.mDetailTitle);
            ListView listView = (ListView) this.mDetail.findViewById(2131362923);
            oplusDndTile.mDndList = listView;
            listView.setChoiceMode(1);
            oplusDndTile.mDndList.addHeaderView(oplusDndTile.mHeaderContainer);
            ListAdapter adapter = new Adapter(oplusDndTile);
            oplusDndTile.mAdapter = adapter;
            oplusDndTile.mDndList.setAdapter(adapter);
            Adapter adapter2 = oplusDndTile.mAdapter;
            OplusDndTile$ColorDndDetailAdapter$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusDndTile$ColorDndDetailAdapter$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            externalSyntheticLambda0.f$1 = context;
            VarHandle.storeStoreFence();
            adapter2.setOnItemClickListener(externalSyntheticLambda0);
            oplusDndTile.mEmpty = this.mDetail.findViewById(2131362919);
            ImageView imageView = (ImageView) this.mDetail.findViewById(2131362920);
            oplusDndTile.mDndEmptyIcon = imageView;
            imageView.setImageResource(OplusDarkModeUtil.isNightMode(context) ? 2131236013 : 2131236014);
            oplusDndTile.mVisible = this.mDetail.findViewById(2131362925);
            updatePanel();
            if (oplusDndTile.mDetailInteractor.isDarkTheme()) {
                oplusDndTile.mDndEmptyIcon.setImageResource(2131236013);
            }
            return this.mDetail;
        }

        public int getMetricsCategory() {
            return 149;
        }

        public Intent getSettingsIntent() {
            int i = OplusDndTile.$r8$clinit;
            return DndTile.ZEN_SETTINGS;
        }

        public String getTag() {
            return OplusQSHighlightTileViewLabelTextHandler.DND;
        }

        public CharSequence getTitle() {
            OplusDndTile oplusDndTile = this.this$0;
            int i = OplusDndTile.$r8$clinit;
            return ((QSTileImpl) oplusDndTile).mContext.getString(2131956559);
        }

        public Boolean getToggleState() {
            OplusDndTile oplusDndTile = this.this$0;
            int i = OplusDndTile.$r8$clinit;
            return Boolean.valueOf(((QSTile.AdapterState) ((QSTileImpl) oplusDndTile).mState).value);
        }

        public final boolean hasFooterDivider() {
            OplusDndTile oplusDndTile = this.this$0;
            int i = OplusDndTile.$r8$clinit;
            return DeviceUtil.isLandscape(((QSTileImpl) oplusDndTile).mContext);
        }

        public void setToggleState(boolean z) {
            OplusDndTile oplusDndTile = this.this$0;
            Boolean boolValueOf = Boolean.valueOf(z);
            int i = OplusDndTile.$r8$clinit;
            oplusDndTile.onDetailToggle(boolValueOf);
        }

        public final void updatePanel() {
            OplusDndTile oplusDndTile = this.this$0;
            int i = OplusDndTile.$r8$clinit;
            if (((DndTile) oplusDndTile).mController.getZen() == 0) {
                oplusDndTile.mVisible.setVisibility(4);
                oplusDndTile.mEmpty.setVisibility(0);
                return;
            }
            if (oplusDndTile.mAdapter != null) {
                Handler handler = oplusDndTile.mHandler;
                1 r3 = new 1();
                r3.this$1 = this;
                VarHandle.storeStoreFence();
                handler.postDelayed(r3, 100L);
            }
            oplusDndTile.mEmpty.setVisibility(8);
            oplusDndTile.mVisible.setVisibility(0);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mgetSecondDialogTitle, reason: not valid java name */
    public static String m332$$Nest$mgetSecondDialogTitle(OplusDndTile oplusDndTile) {
        ZenModeController zenModeController = ((DndTile) oplusDndTile).mController;
        if (zenModeController == null) {
            return null;
        }
        ZenModeConfig.ZenRule zenRule = zenModeController.getConfig() != null ? zenModeController.getConfig().manualRule : null;
        String notificationContent = "";
        long jTryParseCountdownConditionId = -1;
        if (zenRule != null) {
            String str = zenRule.enabler;
            DndInfoUtil dndInfoUtil = DndInfoUtil.getInstance();
            ZenModeConfig config = zenModeController.getConfig();
            dndInfoUtil.getClass();
            ValidRuleInfo validRule = DndInfoUtil.getValidRule(config);
            if (TextUtils.isEmpty(str) || AppInfoUtil.BLACK_DND_PACKAGES.contains(str)) {
                Uri uri = zenRule.conditionId;
                if (uri != null && !Uri.EMPTY.equals(uri)) {
                    jTryParseCountdownConditionId = ZenModeConfig.tryParseCountdownConditionId(zenRule.conditionId);
                    if (jTryParseCountdownConditionId > 0) {
                        notificationContent = oplusDndTile.getSummary();
                    }
                } else if (validRule != null && !"zen_mode_manual_duration".equals(zenRule.name)) {
                    notificationContent = ((DndAlertHelper) oplusDndTile.mDndAlertHelper.get()).formatRemainingTimeWithCalendar(validRule.endTime, validRule.name);
                }
            } else {
                String appNameByPkgName = AppInfoUtil.getAppNameByPkgName(((QSTileImpl) oplusDndTile).mContext, str);
                if (!TextUtils.isEmpty(appNameByPkgName)) {
                    notificationContent = ((QSTileImpl) oplusDndTile).mContext.getString(2131957473, appNameByPkgName);
                }
            }
        }
        for (ZenModeConfig.ZenRule zenRule2 : zenModeController.getConfig().automaticRules.values()) {
            long automaticRuleEndTime = parseAutomaticRuleEndTime(((QSTileImpl) oplusDndTile).mContext, zenRule2.conditionId);
            if (zenRule2.isActive() && automaticRuleEndTime > jTryParseCountdownConditionId && automaticRuleEndTime > 0) {
                notificationContent = ((DndAlertHelper) oplusDndTile.mDndAlertHelper.get()).getNotificationContent();
            }
        }
        return notificationContent;
    }

    public OplusDndTile(QSHost qSHost, QsEventLogger qsEventLogger, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ZenModeController zenModeController, SharedPreferences sharedPreferences, SecureSettings secureSettings, DialogTransitionAnimator dialogTransitionAnimator, OplusQSDetailInteractor.Factory factory, SelectedUserInteractor selectedUserInteractor) {
        super(qSHost, qsEventLogger, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger, zenModeController, sharedPreferences, secureSettings, dialogTransitionAnimator);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mZenModeCallback = anonymousClass1;
        this.mZenModeDurationTime = new int[]{1, 2, 8};
        this.mHandler = handler;
        ColorDndDetailAdapter colorDndDetailAdapter = new ColorDndDetailAdapter();
        colorDndDetailAdapter.this$0 = this;
        VarHandle.storeStoreFence();
        this.mDetailAdapter = colorDndDetailAdapter;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mDetailInteractor = factory.create(colorDndDetailAdapter);
    }

    private String getSummary() {
        Uri uri;
        ZenModeController zenModeController = ((DndTile) this).mController;
        ZenModeConfig.ZenRule zenRule = zenModeController.getConfig() != null ? zenModeController.getConfig().manualRule : null;
        if (zenRule == null || (uri = zenRule.conditionId) == null) {
            return "";
        }
        long jTryParseCountdownConditionId = ZenModeConfig.tryParseCountdownConditionId(uri);
        return jTryParseCountdownConditionId > 0 ? ((QSTileImpl) this).mContext.getString(2131957474, DndInfoUtil.getInstance().getFormatEndTime(((QSTileImpl) this).mContext, jTryParseCountdownConditionId)) : "";
    }

    public static long parseAutomaticRuleEndTime(Context context, Uri uri) {
        if (!ZenModeConfig.isValidScheduleConditionId(uri)) {
            return -1L;
        }
        ScheduleCalendar scheduleCalendar = ZenModeConfig.toScheduleCalendar(uri);
        long nextChangeTime = scheduleCalendar.getNextChangeTime(System.currentTimeMillis());
        if (scheduleCalendar.exitAtAlarm()) {
            AlarmManager.AlarmClockInfo nextAlarmClock = ((AlarmManager) context.getSystemService("alarm")).getNextAlarmClock(context.getUserId());
            long triggerTime = nextAlarmClock != null ? nextAlarmClock.getTriggerTime() : 0L;
            scheduleCalendar.maybeSetNextAlarm(System.currentTimeMillis(), triggerTime);
            if (scheduleCalendar.shouldExitForAlarm(nextChangeTime)) {
                return triggerTime;
            }
        }
        return nextChangeTime;
    }

    public QSTile.Icon getDefaultIcon() {
        return QSTileImpl.ResourceIcon.get(2131237149);
    }

    public CharSequence getTileLabel() {
        return ((QSTileImpl) this).mContext.getString(2131956559);
    }

    public final void handleClick(Expandable expandable) {
        boolean z = ((QSTile.AdapterState) ((QSTileImpl) this).mState).value;
        ZenModeControllerImpl zenModeControllerImpl = ((DndTile) this).mController;
        if (z) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick: setZenModeOff");
            zenModeControllerImpl.setZen(((QSTileImpl) this).TAG, (Uri) null, 0);
            refreshState((Object) null);
            return;
        }
        boolean z2 = ((QSTileImpl) this).mContext.getSharedPreferences("preference_dnd_first_notice", 0).getBoolean("key_donot_show_anymore", false);
        Context context = ((QSTileImpl) this).mContext;
        if (z2) {
            Settings.Secure.putIntForUser(context.getContentResolver(), "preference_dnd_first_notice", 1, -2);
            ((QSTileImpl) this).mContext.getSharedPreferences("preference_dnd_first_notice", 0).edit().putBoolean("key_donot_show_anymore", false).apply();
        } else if (Settings.Secure.getIntForUser(context.getContentResolver(), "preference_dnd_first_notice", 0, -2) != 0) {
            LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick: setZenModeOn");
            zenModeControllerImpl.setZen(((QSTileImpl) this).TAG, (Uri) null, 1);
            refreshState((Object) null);
            return;
        }
        LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleClick: showDndDialog");
        Intent intent = new Intent("android.settings.DND_SECURITY");
        intent.setPackage(((QSTileImpl) this).mContext.getPackageName());
        intent.addFlags(268435456);
        ((QSTileImpl) this).mActivityStarter.postStartActivityDismissingKeyguard(intent, 0);
    }

    public final void handleDestroy() {
        super.handleDestroy();
        ((DndTile) this).mController.removeCallback(this.mZenModeCallback);
    }

    public final void handleInitialize() {
        ((DndTile) this).mController.addCallback(this.mZenModeCallback);
    }

    public final void handleSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.show(expandable != null ? expandable.getView() : null);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    public final void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        String notificationContent;
        long jTryParseCountdownConditionId;
        ZenModeController zenModeController = ((DndTile) this).mController;
        if (zenModeController == null) {
            return;
        }
        int iIntValue = obj instanceof Integer ? ((Integer) obj).intValue() : zenModeController.getZen();
        boolean z = iIntValue != 0;
        boolean z2 = ((QSTile.AdapterState) booleanState).value != z;
        ((QSTile.State) booleanState).dualTarget = true;
        ((QSTile.AdapterState) booleanState).value = z;
        ((QSTile.State) booleanState).state = z ? 2 : 1;
        ((QSTile.State) booleanState).label = getTileLabel();
        if (iIntValue != 0) {
            ZenModeConfig.ZenRule zenRule = zenModeController.getConfig() != null ? zenModeController.getConfig().manualRule : null;
            if (zenRule != null) {
                String str = zenRule.enabler;
                if (AppInfoUtil.BLACK_DND_PACKAGES.contains(str)) {
                    notificationContent = "";
                } else {
                    notificationContent = AppInfoUtil.getAppNameByPkgName(((QSTileImpl) this).mContext, str);
                }
            } else {
                notificationContent = "";
            }
            if (zenRule != null) {
                Uri uri = zenRule.conditionId;
                if (uri != null) {
                    jTryParseCountdownConditionId = ZenModeConfig.tryParseCountdownConditionId(uri);
                    if (jTryParseCountdownConditionId > 0) {
                        notificationContent = getSummary();
                    }
                }
            } else {
                jTryParseCountdownConditionId = -1;
            }
            for (ZenModeConfig.ZenRule zenRule2 : zenModeController.getConfig().automaticRules.values()) {
                long automaticRuleEndTime = parseAutomaticRuleEndTime(((QSTileImpl) this).mContext, zenRule2.conditionId);
                if (zenRule2.isActive() && automaticRuleEndTime > jTryParseCountdownConditionId && automaticRuleEndTime > 0) {
                    notificationContent = ((DndAlertHelper) this.mDndAlertHelper.get()).getNotificationContent();
                }
            }
        } else {
            notificationContent = "";
        }
        ((QSTile.State) booleanState).secondaryLabel = notificationContent;
        ((QSTile.State) booleanState).functionName.setMainText(((QSTile.State) booleanState).label);
        String notificationContent2 = ((DndAlertHelper) this.mDndAlertHelper.get()).getNotificationContent();
        ((QSTile.State) booleanState).functionName.setSubText(((QSTile.AdapterState) booleanState).value ? notificationContent2 : "");
        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("handleUpdateState:subTextContent=", notificationContent2, LogUtil.TAG_QS, ((QSTileImpl) this).TAG);
        if (((OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class)).enableSeparateNotificationAndQS()) {
            ((QSTile.State) booleanState).labelDesc = ((QSTileImpl) this).mContext.getString(2131955246);
        } else {
            ((QSTile.State) booleanState).labelDesc = "";
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(((QSTile.AdapterState) booleanState).value ? 2131237148 : 2131237149);
        checkIfRestrictionEnforcedByAdminOnly("no_adjust_volume", booleanState);
        if (iIntValue != 1) {
            Context context = ((QSTileImpl) this).mContext;
            if (iIntValue == 2) {
                ((QSTile.State) booleanState).contentDescription = context.getString(2131951926) + ", " + ((QSTileImpl) this).mContext.getString(2131951928) + ", " + ((Object) ((QSTile.State) booleanState).secondaryLabel);
            } else if (iIntValue != 3) {
                ((QSTile.State) booleanState).contentDescription = context.getString(2131951926);
            } else {
                ((QSTile.State) booleanState).contentDescription = context.getString(2131951926) + ", " + ((QSTileImpl) this).mContext.getString(2131951927) + ", " + ((Object) ((QSTile.State) booleanState).secondaryLabel);
            }
        } else {
            ((QSTile.State) booleanState).contentDescription = ((QSTileImpl) this).mContext.getString(2131951926) + ", " + ((Object) ((QSTile.State) booleanState).secondaryLabel);
        }
        if (z2) {
            this.mDetailInteractor.updateSwitchState(((QSTile.AdapterState) booleanState).value);
        }
        ((QSTile.State) booleanState).dualLabelContentDescription = ((QSTileImpl) this).mContext.getResources().getString(2131951935, getTileLabel());
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Switch.class.getName();
    }

    public final boolean handleUserOperation(Object obj) {
        LogUtil.i(LogUtil.TAG_QS, ((QSTileImpl) this).TAG, "handleUserOperation: operation=" + obj);
        boolean zEquals = Boolean.TRUE.equals(obj);
        ZenModeControllerImpl zenModeControllerImpl = ((DndTile) this).mController;
        if (zEquals) {
            zenModeControllerImpl.setZen(((QSTileImpl) this).TAG, (Uri) null, 1);
        } else {
            zenModeControllerImpl.setZen(((QSTileImpl) this).TAG, (Uri) null, 0);
        }
        return true;
    }

    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public final QSTile.BooleanState m333newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        ((QSTile.State) booleanState).lottiePrefix = QSConstant.DND_LOTTIE_PREFIX;
        ((QSTile.State) booleanState).lottieSupport = 17;
        ((QSTile.State) booleanState).toggleable = true;
        ((QSTile.State) booleanState).colorfulConfig = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_VIOLET_COLOR;
        return booleanState;
    }

    public final void preloadForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.prepareDetailView();
    }

    public final void releasePreloadedForSecondaryClick(Expandable expandable) {
        this.mDetailInteractor.releaseDetailView();
    }
}
