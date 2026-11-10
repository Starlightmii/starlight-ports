package com.oplus.systemui.separate;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewKt$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.coui.appcompat.button.COUIButton;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.coui.appcompat.panel.COUIBottomSheetDialog;
import com.coui.appcompat.panel.COUIPanelContentLayout;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpUtils;
import com.oplus.systemui.separate.guide.OplusQsGuideEventExternalTransfer;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsCenterModeSelectDialogController {
    private static final String GUIDE_DIALOG_HAS_DISPLAYED = "guide_dialog_has_displayed";
    private static final String QS_EXPAND_TIMES = "qs_expand_times";
    private static final String SEPARATE_NOTI_AND_QS_ENABLE = "separate_noti_and_qs_enable";
    private static final String SEPARATE_NOTI_AND_QS_ENABLE_DEFAULT = "separate_noti_and_qs_enable_default";
    private static final String TAG = "QsCenterModeSelectDialogController";
    private final Handler bgHandler;
    private Context colorContext;
    private final ConfigurationController configurationController;
    private COUIButton confirmButton;
    private final Context context;
    private TextView descriptionText;
    private final OplusQsGuideEventExternalTransfer guideEventTransfer;
    public int guideMode;
    public boolean isChinaModel;
    public boolean isCollecting;
    public boolean isFoldedLarge;
    public boolean isModeChange;
    private final StateFlow isShadeCollapsed;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private COUIBottomSheetDialog mBottomSheetDialog;
    private final ConfigurationController.ConfigurationListener mConfigurationListener;
    public boolean mNightMode;
    private AppCompatImageView meldModeGuidePic;
    private LinearLayout meldPartLayout;
    private RadioButton meldRadioButton;
    private TextView meldText;
    public int qsExpandTimes;
    private final OplusSeparateNotificationAndQSState qsState;
    private final CoroutineScope scope;
    private AppCompatImageView separateModeGuidePic;
    private LinearLayout separatePartLayout;
    private RadioButton separateRadioButton;
    private TextView separateText;
    private final QsCenterModeSelectDialogController$settingsObserver$1 settingsObserver;
    private final ShadeInteractor shadeInteractor;
    private final StatusBarStateController statusBarStateController;
    private final UserTracker.Callback userCallback;
    private final UserTracker userTracker;
    public static final Companion Companion = new Companion();
    public static final int FIRST_API_LEVEL = SystemProperties.getInt("ro.product.first_api_level", -1);
    public static final boolean IS_AUTO_TEST = SystemProperties.getBoolean("oplus.autotest.skipguide", false);
    public boolean isNeedUpdate = true;
    public boolean isGuideDialogHasShown = true;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.separate.QsCenterModeSelectDialogController$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ QsCenterModeSelectDialogController this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(QsCenterModeSelectDialogController.GUIDE_DIALOG_HAS_DISPLAYED), false, this.this$0.settingsObserver);
                    QsCenterModeSelectDialogController qsCenterModeSelectDialogController = this.this$0;
                    qsCenterModeSelectDialogController.isGuideDialogHasShown = qsCenterModeSelectDialogController.getDialogDisplayedBySettings();
                    LogUtil.i$default(LogUtil.TAG_QS, QsCenterModeSelectDialogController.TAG, OplusLockIconView$.ExternalSyntheticOutline0.m(" init : hasShown = ", " ", this.this$0.isGuideDialogHasShown), null, 8);
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isAgingVersion() || QsCenterModeSelectDialogController.IS_AUTO_TEST || FeatureOption.isFactoryMode()) {
                        LogUtil.i$default(LogUtil.TAG_QS, QsCenterModeSelectDialogController.TAG, " init return is auto test ", null, 8);
                        this.this$0.setDialogDisplayedBySettings(1);
                    } else if (!this.this$0.isGuideDialogHasShown) {
                        int i = QsCenterModeSelectDialogController.FIRST_API_LEVEL;
                        boolean z = i <= 34;
                        boolean notShowGuideDialog = QSFeatureOption.INSTANCE.getNotShowGuideDialog();
                        boolean separateModeBySettings = this.this$0.getSeparateModeBySettings();
                        this.this$0.isChinaModel = !FeatureOption.isExpRegion();
                        QsCenterModeSelectDialogController qsCenterModeSelectDialogController2 = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            boolean z2 = qsCenterModeSelectDialogController2.isChinaModel;
                            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("init,isAlreadySeparateMode: ", ",  needSetSeparateMode: ", ",  isChinaModel: ", separateModeBySettings, notShowGuideDialog);
                            sbM.append(z2);
                            sbM.append(", FIRST_API_LEVEL: ");
                            sbM.append(i);
                            Log.d("SystemUi--QuickSettings", "QsCenterModeSelectDialogController-->".concat(sbM.toString()));
                        }
                        if (separateModeBySettings) {
                            this.this$0.setDialogDisplayedBySettings(1);
                            this.this$0.setDefaultSeparateModeBySettings(true);
                            this.this$0.guideEventTransfer.notifyModeSelectGuideDialogDismiss();
                        } else if (notShowGuideDialog && z) {
                            this.this$0.readyToShowGuideDialog();
                        } else if (!notShowGuideDialog && z) {
                            this.this$0.readyToShowGuideDialog();
                        } else {
                            this.this$0.setSeparateModeBySettings(true);
                            this.this$0.setDialogDisplayedBySettings(1);
                            this.this$0.setDefaultSeparateModeBySettings(true);
                            this.this$0.guideEventTransfer.notifyModeSelectGuideDialogDismiss();
                        }
                    }
                    break;
                case 1:
                    LogUtil.i$default(LogUtil.TAG_QS, QsCenterModeSelectDialogController.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" handleGuideComplete： ", this.this$0.isGuideDialogHasShown), null, 8);
                    QsCenterModeSelectDialogController qsCenterModeSelectDialogController3 = this.this$0;
                    if (!qsCenterModeSelectDialogController3.isGuideDialogHasShown) {
                        qsCenterModeSelectDialogController3.setQsExpandTimes(0);
                        QsCenterModeSelectDialogController qsCenterModeSelectDialogController4 = this.this$0;
                        qsCenterModeSelectDialogController4.isGuideDialogHasShown = true;
                        qsCenterModeSelectDialogController4.setDialogDisplayedBySettings(1);
                        boolean separateModeBySettings2 = this.this$0.getSeparateModeBySettings();
                        QsCenterModeSelectDialogController qsCenterModeSelectDialogController5 = this.this$0;
                        if (!separateModeBySettings2) {
                            qsCenterModeSelectDialogController5.setSupportGuideBySettings(false);
                        } else {
                            qsCenterModeSelectDialogController5.guideEventTransfer.notifyModeSelectGuideDialogDismiss();
                        }
                        break;
                    }
                    break;
                case 2:
                    this.this$0.setDefaultSeparateModeBySettings(true);
                    break;
                case 3:
                    QsCenterModeSelectDialogController.access$handleDialogDismiss(this.this$0);
                    break;
                case 4:
                    boolean dialogDisplayedBySettings = this.this$0.getDialogDisplayedBySettings();
                    QsCenterModeSelectDialogController qsCenterModeSelectDialogController6 = this.this$0;
                    if (dialogDisplayedBySettings != qsCenterModeSelectDialogController6.isGuideDialogHasShown) {
                        qsCenterModeSelectDialogController6.isGuideDialogHasShown = dialogDisplayedBySettings;
                    }
                    LogUtil.i$default(LogUtil.TAG_QS, QsCenterModeSelectDialogController.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" settings change : ", dialogDisplayedBySettings, ", user= ", qsCenterModeSelectDialogController6.userTracker.getUserId()), null, 8);
                    if (!dialogDisplayedBySettings) {
                        this.this$0.readyToShowGuideDialog();
                    } else {
                        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
                        QsCenterModeSelectDialogController qsCenterModeSelectDialogController7 = this.this$0;
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(3);
                        anonymousClass2.this$0 = qsCenterModeSelectDialogController7;
                        VarHandle.storeStoreFence();
                        mainHandler.post(anonymousClass2);
                    }
                    break;
                default:
                    this.this$0.showGuideDialog();
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.oplus.systemui.separate.QsCenterModeSelectDialogController$settingsObserver$1] */
    public QsCenterModeSelectDialogController(Context context, ConfigurationController configurationController, OplusQsGuideEventExternalTransfer oplusQsGuideEventExternalTransfer, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, KeyguardUpdateMonitor keyguardUpdateMonitor, UserTracker userTracker, ShadeInteractor shadeInteractor, StatusBarStateController statusBarStateController, Handler handler, CoroutineScope coroutineScope) {
        this.context = context;
        this.configurationController = configurationController;
        this.guideEventTransfer = oplusQsGuideEventExternalTransfer;
        this.qsState = oplusSeparateNotificationAndQSState;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.userTracker = userTracker;
        this.shadeInteractor = shadeInteractor;
        this.statusBarStateController = statusBarStateController;
        this.bgHandler = handler;
        this.scope = coroutineScope;
        this.qsExpandTimes = context.getSharedPreferences("qs_guide_sp", 0).getInt(QS_EXPAND_TIMES, 0);
        this.isShadeCollapsed = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.combine(shadeInteractor.isUserInteracting(), shadeInteractor.isAnyFullyCollapsed(), new QsCenterModeSelectDialogController$isShadeCollapsed$1(3, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), Boolean.FALSE);
        QsCenterModeSelectDialogController$mConfigurationListener$1 qsCenterModeSelectDialogController$mConfigurationListener$1 = new QsCenterModeSelectDialogController$mConfigurationListener$1();
        qsCenterModeSelectDialogController$mConfigurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mConfigurationListener = qsCenterModeSelectDialogController$mConfigurationListener$1;
        this.settingsObserver = new ContentObserver() { // from class: com.oplus.systemui.separate.QsCenterModeSelectDialogController$settingsObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                Handler handler2 = this.this$0.bgHandler;
                QsCenterModeSelectDialogController qsCenterModeSelectDialogController = this.this$0;
                QsCenterModeSelectDialogController.AnonymousClass2 anonymousClass2 = new QsCenterModeSelectDialogController.AnonymousClass2(4);
                anonymousClass2.this$0 = qsCenterModeSelectDialogController;
                VarHandle.storeStoreFence();
                handler2.post(anonymousClass2);
            }
        };
        QsCenterModeSelectDialogController$userCallback$1 qsCenterModeSelectDialogController$userCallback$1 = new QsCenterModeSelectDialogController$userCallback$1();
        qsCenterModeSelectDialogController$userCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userCallback = qsCenterModeSelectDialogController$userCallback$1;
    }

    public static final void access$handleButtonClick(QsCenterModeSelectDialogController qsCenterModeSelectDialogController) {
        int i = 1;
        boolean z = qsCenterModeSelectDialogController.guideMode == 1;
        Handler handler = qsCenterModeSelectDialogController.bgHandler;
        changeQsMode.1 r4 = new changeQsMode.1(0);
        r4.this$0 = qsCenterModeSelectDialogController;
        r4.$separateMode = z;
        VarHandle.storeStoreFence();
        handler.post(r4);
        if (qsCenterModeSelectDialogController.mBottomSheetDialog != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, " handleButtonClick", null, 8);
            COUIBottomSheetDialog cOUIBottomSheetDialog = qsCenterModeSelectDialogController.mBottomSheetDialog;
            if (cOUIBottomSheetDialog != null) {
                cOUIBottomSheetDialog.dismiss();
            }
            qsCenterModeSelectDialogController.mBottomSheetDialog = null;
            Handler handler2 = qsCenterModeSelectDialogController.bgHandler;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(i);
            anonymousClass2.this$0 = qsCenterModeSelectDialogController;
            VarHandle.storeStoreFence();
            handler2.post(anonymousClass2);
        }
    }

    public static final void access$handleDialogDismiss(QsCenterModeSelectDialogController qsCenterModeSelectDialogController) {
        if (qsCenterModeSelectDialogController.mBottomSheetDialog != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(qsCenterModeSelectDialogController.qsExpandTimes, qsCenterModeSelectDialogController.guideMode, " handleDialogDismiss：time = ", ", mode = "), null, 8);
            COUIBottomSheetDialog cOUIBottomSheetDialog = qsCenterModeSelectDialogController.mBottomSheetDialog;
            if (cOUIBottomSheetDialog != null) {
                cOUIBottomSheetDialog.dismiss();
            }
            qsCenterModeSelectDialogController.mBottomSheetDialog = null;
            int i = 1;
            if (qsCenterModeSelectDialogController.qsExpandTimes >= 9 || qsCenterModeSelectDialogController.guideMode == 1) {
                Handler handler = qsCenterModeSelectDialogController.bgHandler;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(i);
                anonymousClass2.this$0 = qsCenterModeSelectDialogController;
                VarHandle.storeStoreFence();
                handler.post(anonymousClass2);
            }
        }
    }

    public static final void access$handleModeClick(QsCenterModeSelectDialogController qsCenterModeSelectDialogController, int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, " handleModeClick: mode = "), null, 8);
        if (qsCenterModeSelectDialogController.guideMode != i) {
            qsCenterModeSelectDialogController.guideMode = i;
            qsCenterModeSelectDialogController.setQSCenterMode(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSeparateModeBySettings() {
        return "true".equals(Settings.System.getStringForUser(this.context.getContentResolver(), SEPARATE_NOTI_AND_QS_ENABLE, this.context.getUserId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDefaultSeparateModeBySettings(boolean z) {
        Settings.System.putStringForUser(this.context.getContentResolver(), SEPARATE_NOTI_AND_QS_ENABLE_DEFAULT, z ? "true" : "false", this.context.getUserId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDialogDisplayedBySettings(int i) {
        Settings.Secure.putInt(this.context.getContentResolver(), GUIDE_DIALOG_HAS_DISPLAYED, i);
    }

    private final void setQSCenterMode(int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "setOsCenterMode mode: ", "QsCenterModeSelectDialogController-->", "SystemUi--QuickSettings");
        }
        if (i == 0) {
            RadioButton radioButton = this.separateRadioButton;
            if (radioButton != null) {
                radioButton.setChecked(false);
            }
            RadioButton radioButton2 = this.meldRadioButton;
            if (radioButton2 != null) {
                radioButton2.setChecked(true);
            }
            TextView textView = this.meldText;
            if (textView != null) {
                textView.setTextColor(COUIContextUtil.getAttrColor(this.colorContext, 2130969315));
            }
            TextView textView2 = this.descriptionText;
            if (textView2 != null) {
                textView2.setText(2131955603);
            }
            TextView textView3 = this.separateText;
            if (textView3 != null) {
                textView3.setTextColor(ActivityCompat.getColorStateList(2131103273, this.context));
                return;
            }
            return;
        }
        if (i != 1) {
            return;
        }
        RadioButton radioButton3 = this.separateRadioButton;
        if (radioButton3 != null) {
            radioButton3.setChecked(true);
        }
        RadioButton radioButton4 = this.meldRadioButton;
        if (radioButton4 != null) {
            radioButton4.setChecked(false);
        }
        TextView textView4 = this.separateText;
        if (textView4 != null) {
            textView4.setTextColor(COUIContextUtil.getAttrColor(this.colorContext, 2130969315));
        }
        TextView textView5 = this.descriptionText;
        if (textView5 != null) {
            textView5.setText(2131955612);
        }
        TextView textView6 = this.meldText;
        if (textView6 != null) {
            textView6.setTextColor(ActivityCompat.getColorStateList(2131103273, this.context));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setQsExpandTimes(int i) {
        if (this.qsExpandTimes != i) {
            this.qsExpandTimes = i;
            Handler handler = this.bgHandler;
            qsExpandTimes.1 r1 = new qsExpandTimes.1();
            r1.$value = i;
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(r1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeparateModeBySettings(boolean z) {
        Settings.System.putStringForUser(this.context.getContentResolver(), SEPARATE_NOTI_AND_QS_ENABLE, z ? "true" : "false", this.context.getUserId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSupportGuideBySettings(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" setSupportGuideBySettings： ", z), null, 8);
        Settings.System.putIntForUser(this.context.getContentResolver(), OplusQsGuideSpUtils.SUPPORT_SEPARATE_GUIDE, z ? 1 : 0, this.context.getUserId());
    }

    public static void updateContentAndSize(AppCompatImageView appCompatImageView, int i, int i2, int i3) {
        appCompatImageView.setImageResource(i);
        ViewGroup.LayoutParams layoutParams = appCompatImageView.getLayoutParams();
        if (layoutParams == null) {
            ViewKt$.ExternalSyntheticBUOutline0.m("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            return;
        }
        layoutParams.width = appCompatImageView.getContext().getResources().getDimensionPixelSize(i2);
        layoutParams.height = appCompatImageView.getContext().getResources().getDimensionPixelSize(i3);
        appCompatImageView.setLayoutParams(layoutParams);
    }

    public final boolean getDialogDisplayedBySettings() {
        return Settings.Secure.getInt(this.context.getContentResolver(), GUIDE_DIALOG_HAS_DISPLAYED, 0) == 1;
    }

    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QsCenterModeSelectDialogController-->start init ");
        }
        Handler handler = this.bgHandler;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass2);
    }

    public final boolean isDeviceProvisioned(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0;
    }

    public final boolean isDialogShowing() {
        COUIBottomSheetDialog cOUIBottomSheetDialog = this.mBottomSheetDialog;
        return cOUIBottomSheetDialog != null && cOUIBottomSheetDialog.isShowing();
    }

    public final boolean isPhoneLandscape() {
        FeatureOption.INSTANCE.getClass();
        return ((!FeatureOption.isFoldDevice() || !DeviceUtil.shouldUseLargeFoldConfig(this.context.getResources())) && !FeatureOption.isTablet()) && DeviceUtil.isLandscape(this.context);
    }

    public final void reShowDialog() {
        if (this.mBottomSheetDialog == null || !isDialogShowing()) {
            return;
        }
        COUIBottomSheetDialog cOUIBottomSheetDialog = this.mBottomSheetDialog;
        if (cOUIBottomSheetDialog != null) {
            cOUIBottomSheetDialog.dismiss();
        }
        this.mBottomSheetDialog = null;
        showGuideDialog();
    }

    public final void readyToShowGuideDialog() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " readyToShowGuideDialog: qsExpandTimes=" + this.qsExpandTimes + ", isCollecting=" + this.isCollecting + " ", null, 8);
        this.guideMode = 1;
        if (this.isCollecting) {
            return;
        }
        this.isCollecting = true;
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new readyToShowGuideDialog.1(this, (Continuation) null), 3);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new readyToShowGuideDialog.2(this, (Continuation) null), 3);
        BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new readyToShowGuideDialog.3(this, (Continuation) null), 3);
    }

    public final void separateModeChange(boolean z) {
        if (this.isGuideDialogHasShown || !this.userTracker.getUserInfo().isMain()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                boolean z2 = this.isGuideDialogHasShown;
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("QsCenterModeSelectDialogController-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m(" separateModeChange: ", " , hasShow = ", ", isMain = ", z, z2), this.userTracker.getUserInfo().isMain());
                return;
            }
            return;
        }
        if (!z || isDialogShowing()) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "separateModeChange true, guide complete", null, 8);
        Handler handler = this.bgHandler;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass2);
    }

    public final void shadeCollapsedStateChange(boolean z) {
        if (this.isGuideDialogHasShown || !this.userTracker.getUserInfo().isMain()) {
            if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" Shade Collapsed ,return : hasShow = ", ", isMain = ", "QsCenterModeSelectDialogController-->", this.isGuideDialogHasShown, this.userTracker.getUserInfo().isMain()));
                return;
            }
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("Shade Collapsed change : ", z), null, 8);
        if (z) {
            this.isNeedUpdate = true;
            if (isDialogShowing() && this.isModeChange) {
                setSeparateModeBySettings(this.guideMode == 1);
                this.isModeChange = false;
            }
        }
    }

    public final void showGuideDialog() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        COUIPanelContentLayout dragableLinearLayout;
        ImageView dragView;
        BottomSheetBehavior behavior;
        ViewTreeObserver viewTreeObserver;
        if (this.mBottomSheetDialog == null && isDeviceProvisioned(this.context)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "showGuideDialog ", null, 8);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "QsCenterModeSelectDialogController-->initModeSelectDialog");
            }
            this.isFoldedLarge = DeviceUtil.shouldUseLargeFoldConfig(this.context.getResources());
            boolean z = (this.context.getResources().getConfiguration().uiMode & 48) == 32;
            this.mNightMode = z;
            this.colorContext = new ContextThemeWrapper(this.context, z ? 2132019096 : 2132019095);
            COUIThemeOverlay.getInstance().applyThemeOverlays(this.colorContext);
            View viewInflate = LayoutInflater.from(this.colorContext).inflate(2131559504, (ViewGroup) null);
            Context context = this.colorContext;
            this.mBottomSheetDialog = context != null ? new COUIBottomSheetDialog(context, 2132017821) : null;
            View viewFindViewById = viewInflate.findViewById(2131365147);
            this.separateRadioButton = viewFindViewById instanceof RadioButton ? (RadioButton) viewFindViewById : null;
            View viewFindViewById2 = viewInflate.findViewById(2131363932);
            this.meldRadioButton = viewFindViewById2 instanceof RadioButton ? (RadioButton) viewFindViewById2 : null;
            View viewFindViewById3 = viewInflate.findViewById(2131364720);
            this.separateText = viewFindViewById3 instanceof TextView ? (TextView) viewFindViewById3 : null;
            View viewFindViewById4 = viewInflate.findViewById(2131363275);
            TextView textView = viewFindViewById4 instanceof TextView ? (TextView) viewFindViewById4 : null;
            this.descriptionText = textView;
            if (textView != null && (viewTreeObserver = textView.getViewTreeObserver()) != null) {
                initModeSelectDialog.3 r8 = new initModeSelectDialog.3();
                r8.this$0 = this;
                VarHandle.storeStoreFence();
                viewTreeObserver.addOnGlobalLayoutListener(r8);
            }
            View viewFindViewById5 = viewInflate.findViewById(2131364718);
            this.meldText = viewFindViewById5 instanceof TextView ? (TextView) viewFindViewById5 : null;
            AppCompatImageView appCompatImageViewFindViewById = viewInflate.findViewById(2131364719);
            this.separateModeGuidePic = appCompatImageViewFindViewById instanceof AppCompatImageView ? appCompatImageViewFindViewById : null;
            AppCompatImageView appCompatImageViewFindViewById2 = viewInflate.findViewById(2131364717);
            this.meldModeGuidePic = appCompatImageViewFindViewById2 instanceof AppCompatImageView ? appCompatImageViewFindViewById2 : null;
            COUIButton cOUIButtonFindViewById = viewInflate.findViewById(2131363274);
            this.confirmButton = cOUIButtonFindViewById instanceof COUIButton ? cOUIButtonFindViewById : null;
            this.separatePartLayout = (LinearLayout) viewInflate.findViewById(2131365146);
            this.meldPartLayout = (LinearLayout) viewInflate.findViewById(2131363931);
            COUIBottomSheetDialog cOUIBottomSheetDialog = this.mBottomSheetDialog;
            Window window = cOUIBottomSheetDialog != null ? cOUIBottomSheetDialog.getWindow() : null;
            if (window != null) {
                window.setType(2008);
            }
            if (window != null) {
                window.addFlags(131072);
            }
            WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
            if (attributes != null) {
                attributes.setTitle("QsCenterModeSelectDialog");
                attributes.privateFlags |= 16;
                window.setAttributes(attributes);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog2 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog2 != null) {
                cOUIBottomSheetDialog2.setContentView(viewInflate);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "QsCenterModeSelectDialogController-->initModeGuidePic");
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                AppCompatImageView appCompatImageView = this.separateModeGuidePic;
                if (appCompatImageView != null) {
                    updateContentAndSize(appCompatImageView, 2131236317, 2131171583, 2131171580);
                }
                AppCompatImageView appCompatImageView2 = this.meldModeGuidePic;
                if (appCompatImageView2 != null) {
                    updateContentAndSize(appCompatImageView2, 2131236314, 2131171583, 2131171580);
                }
            } else if (FeatureOption.isFoldDevice() && this.isFoldedLarge) {
                AppCompatImageView appCompatImageView3 = this.separateModeGuidePic;
                if (appCompatImageView3 != null) {
                    updateContentAndSize(appCompatImageView3, 2131236315, 2131171581, 2131171578);
                }
                AppCompatImageView appCompatImageView4 = this.meldModeGuidePic;
                if (appCompatImageView4 != null) {
                    updateContentAndSize(appCompatImageView4, 2131236312, 2131171581, 2131171578);
                }
            } else {
                AppCompatImageView appCompatImageView5 = this.separateModeGuidePic;
                if (appCompatImageView5 != null) {
                    updateContentAndSize(appCompatImageView5, 2131236316, 2131171582, 2131171579);
                }
                AppCompatImageView appCompatImageView6 = this.meldModeGuidePic;
                if (appCompatImageView6 != null) {
                    updateContentAndSize(appCompatImageView6, 2131236313, 2131171582, 2131171579);
                }
            }
            setQSCenterMode(this.guideMode);
            Handler handler = this.bgHandler;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2);
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass2);
            RadioButton radioButton = this.separateRadioButton;
            if (radioButton != null) {
                showGuideDialog.1 r1 = new showGuideDialog.1(0);
                r1.this$0 = this;
                VarHandle.storeStoreFence();
                radioButton.setOnClickListener(r1);
            }
            RadioButton radioButton2 = this.meldRadioButton;
            if (radioButton2 != null) {
                showGuideDialog.1 r2 = new showGuideDialog.1(1);
                r2.this$0 = this;
                VarHandle.storeStoreFence();
                radioButton2.setOnClickListener(r2);
            }
            LinearLayout linearLayout = this.separatePartLayout;
            if (linearLayout != null) {
                showGuideDialog.1 r3 = new showGuideDialog.1(2);
                r3.this$0 = this;
                VarHandle.storeStoreFence();
                linearLayout.setOnClickListener(r3);
            }
            LinearLayout linearLayout2 = this.meldPartLayout;
            if (linearLayout2 != null) {
                showGuideDialog.1 r4 = new showGuideDialog.1(3);
                r4.this$0 = this;
                VarHandle.storeStoreFence();
                linearLayout2.setOnClickListener(r4);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog3 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog3 != null) {
                cOUIBottomSheetDialog3.setCanceledOnTouchOutside(false);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog4 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog4 != null && (behavior = cOUIBottomSheetDialog4.getBehavior()) != null) {
                behavior.setDraggable(false);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog5 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog5 != null && (dragableLinearLayout = cOUIBottomSheetDialog5.getDragableLinearLayout()) != null && (dragView = dragableLinearLayout.getDragView()) != null) {
                dragView.setVisibility(8);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog6 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog6 != null) {
                cOUIBottomSheetDialog6.setOutSideViewTouchListener(showGuideDialog.5.INSTANCE);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog7 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog7 != null && (onBackInvokedDispatcher = cOUIBottomSheetDialog7.getOnBackInvokedDispatcher()) != null) {
                showGuideDialog.6 r5 = new showGuideDialog.6();
                r5.this$0 = this;
                VarHandle.storeStoreFence();
                onBackInvokedDispatcher.registerOnBackInvokedCallback(0, r5);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog8 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog8 != null) {
                showGuideDialog.7 r6 = new showGuideDialog.7();
                r6.this$0 = this;
                VarHandle.storeStoreFence();
                cOUIBottomSheetDialog8.setOnDismissListener(r6);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog9 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog9 != null) {
                showGuideDialog.8 r7 = new showGuideDialog.8();
                r7.this$0 = this;
                VarHandle.storeStoreFence();
                cOUIBottomSheetDialog9.setOnShowListener(r7);
            }
            COUIButton cOUIButton = this.confirmButton;
            if (cOUIButton != null) {
                showGuideDialog.1 r9 = new showGuideDialog.1(4);
                r9.this$0 = this;
                VarHandle.storeStoreFence();
                cOUIButton.setOnClickListener(r9);
            }
            COUIBottomSheetDialog cOUIBottomSheetDialog10 = this.mBottomSheetDialog;
            if (cOUIBottomSheetDialog10 != null) {
                cOUIBottomSheetDialog10.show();
            }
        }
    }

    public final void updateShadeExpand(boolean z) {
        if (this.isGuideDialogHasShown || !this.userTracker.getUserInfo().isMain()) {
            if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" updateShadeExpand: qs Expand , hasShow = ", ", isMain = ", "QsCenterModeSelectDialogController-->", this.isGuideDialogHasShown, this.userTracker.getUserInfo().isMain()));
                return;
            }
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, OplusLockIconView$.ExternalSyntheticOutline0.m(" updateShadeExpand: ", " ", z), null, 8);
        if (z) {
            boolean zIsDialogShowing = isDialogShowing();
            boolean z2 = this.isNeedUpdate;
            int i = this.qsExpandTimes;
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateShadeExpand ,qs Expand: isNeedUpdate=", ", isDialogShowing=", ", qsExpandTimes=", z2, zIsDialogShowing);
            sbM.append(i);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
            if (!this.isNeedUpdate || zIsDialogShowing || this.keyguardUpdateMonitor.mKeyguardShowing) {
                return;
            }
            this.isNeedUpdate = false;
            int i2 = this.qsExpandTimes;
            if (1 <= i2 && i2 < 10) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(i2, "qsExpandTimes no match, need add 1 : ", " "), null, 8);
                setQsExpandTimes(this.qsExpandTimes + 1);
                return;
            }
            if (this.isGuideDialogHasShown) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: false, isGuideDialogHasShown", null, 8);
                return;
            }
            if (isDialogShowing()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: false, isDialogShowing", null, 8);
                return;
            }
            if (this.keyguardUpdateMonitor.mKeyguardShowing) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: false, isKeyguardShowing", null, 8);
                return;
            }
            if (isPhoneLandscape()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: false, isPhoneLandscape", null, 8);
                return;
            }
            if (!isDeviceProvisioned(this.context)) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: false , not isDeviceProvisioned", null, 8);
                return;
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "shouldShowDialog: true", null, 8);
            int i3 = this.qsExpandTimes;
            if (i3 == 0) {
                setQsExpandTimes(i3 + 1);
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.qsExpandTimes, "ShowDialog, "), null, 8);
            Handler mainHandler = SystemUIThreadUtil.getMainHandler();
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(5);
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            mainHandler.post(anonymousClass2);
        }
    }
}
