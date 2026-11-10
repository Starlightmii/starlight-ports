package com.oplus.systemui.qs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Trace;
import android.os.UserManager;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayController$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.customize.IQSCustomizerController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.customize.OplusQSCustomizerController;
import com.oplus.systemui.qs.widget.MoreButtonPopupWindow;
import com.oplus.systemui.qs.widget.OplusFgsManagerController;
import com.oplus.systemui.qs.widget.OplusQSClock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.clock.ui.ClockSecondsViewBinder;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplusos.systemui.common.receiver.DragStateReceiver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSFooterViewController extends ViewController implements QSNode {
    public final ActivityStarter mActivityStarter;
    public final ClockSecondsViewBinder mClockSecondsViewBinder;
    public int mCollapseHeight;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass1 mConfigurationListener;
    public MoreButtonPopupWindow mCouiPopup;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final OplusQSFooterViewController$$ExternalSyntheticLambda0 mDragStateReceiver;
    public int mExpandHeight;
    public boolean mExpanded;
    public float mExpansion;
    public int mFgsNumPkg;
    public final OplusQSFooterViewController$.ExternalSyntheticLambda1 mFgsNumberListener;
    public float mGapTop;
    public boolean mIsLastLand;
    public boolean mIsQsSettingsHasOpenState;
    public boolean mListening;
    public final MetricsLogger mMetricsLogger;
    public final NotificationDependOnCommon mNotificationDependOnCommon;
    public final AnonymousClass2 mOnUserInfoChangedListener;
    public final OplusFgsManagerController mOplusFgsManagerController;
    public final OplusQSActivityStarter mOplusQSActivityStarter;
    public final QSPanelController mQsPanelController;
    public final 4 mQsSettingsHasOpenObserver;
    public final OplusSeparateNotificationAndQSState mSeparateNotificationAndQSState;
    public final AnonymousClass3 mSettingsOnClickListener;
    public final ShadeViewController mShadeViewController;
    public boolean mShowFgs;
    public final UserInfoController mUserInfoController;
    public final UserSwitcherInteractor mUserInteractor;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterViewController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusQSFooterViewController this$0;

        public final void onConfigChanged(Configuration configuration) {
            IQSCustomizerController iQSCustomizerController;
            OplusQSFooterViewController oplusQSFooterViewController = this.this$0;
            boolean z = configuration.orientation == 2;
            if (!oplusQSFooterViewController.mIsLastLand && z && (iQSCustomizerController = oplusQSFooterViewController.mQsPanelController.mQsCustomizerController) != null) {
                ((OplusQSCustomizerController) iQSCustomizerController).hideCustomizer();
            }
            oplusQSFooterViewController.mIsLastLand = z;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterViewController$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements UserInfoController.OnUserInfoChangedListener {
        public /* synthetic */ OplusQSFooterViewController this$0;

        public final void onUserInfoChanged(String str, Drawable drawable, String str2) {
            ((OplusQSFooterImpl) ((ViewController) this.this$0).mView).onUserInfoChanged(drawable);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterViewController$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements View.OnClickListener {
        public /* synthetic */ OplusQSFooterViewController this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean zIsCurrentUserSetup = this.this$0.mDeviceProvisionedController.isCurrentUserSetup();
            OplusQSFooterViewController oplusQSFooterViewController = this.this$0;
            if (!zIsCurrentUserSetup) {
                oplusQSFooterViewController.mActivityStarter.postQSRunnableDismissingKeyguard(new ClipboardOverlayController$.ExternalSyntheticLambda12());
                return;
            }
            oplusQSFooterViewController.mMetricsLogger.action(oplusQSFooterViewController.mExpanded ? 406 : 490);
            this.this$0.mOplusQSActivityStarter.onAnimationButtonClicked((ImageView) ((OplusQSFooterImpl) ((ViewController) this.this$0).mView).findViewById(2131365158), new Intent("android.settings.SETTINGS"), OplusQSActivityStarter.QSTransitionAnimator.SETTINGS);
            QsStatisticUtil.collectShortcutsCenterEvent(this.this$0.getContext(), "settings_click");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterViewController$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass5(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    OplusQSFooterViewController oplusQSFooterViewController = (OplusQSFooterViewController) this.this$0;
                    oplusQSFooterViewController.mIsQsSettingsHasOpenState = oplusQSFooterViewController.getQSSettingsState();
                    break;
                default:
                    OplusQSFooterViewController oplusQSFooterViewController2 = ((4) this.this$0).this$0;
                    oplusQSFooterViewController2.mIsQsSettingsHasOpenState = oplusQSFooterViewController2.getQSSettingsState();
                    break;
            }
        }
    }

    public OplusQSFooterViewController(OplusQSFooterImpl oplusQSFooterImpl, UserManager userManager, UserInfoController userInfoController, ActivityStarter activityStarter, DeviceProvisionedController deviceProvisionedController, UserTracker userTracker, QSPanelController qSPanelController, TunerService tunerService, MetricsLogger metricsLogger, ConfigurationController configurationController, OplusFgsManagerController oplusFgsManagerController, ShadeViewController shadeViewController, UserSwitcherInteractor userSwitcherInteractor, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, NotificationDependOnCommon notificationDependOnCommon, OplusQSActivityStarter oplusQSActivityStarter, ShadeControllerImpl shadeControllerImpl, ShadeInteractor shadeInteractor, ClockSecondsViewBinder clockSecondsViewBinder) {
        super(oplusQSFooterImpl);
        this.mFgsNumPkg = 0;
        this.mIsQsSettingsHasOpenState = false;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mConfigurationListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnUserInfoChangedListener = anonymousClass2;
        OplusQSFooterViewController$$ExternalSyntheticLambda0 oplusQSFooterViewController$$ExternalSyntheticLambda0 = new OplusQSFooterViewController$$ExternalSyntheticLambda0();
        oplusQSFooterViewController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mDragStateReceiver = oplusQSFooterViewController$$ExternalSyntheticLambda0;
        OplusQSFooterViewController$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSFooterViewController$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mFgsNumberListener = externalSyntheticLambda1;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSettingsOnClickListener = anonymousClass3;
        this.mQsSettingsHasOpenObserver = new 4(this, new Handler());
        this.mUserInfoController = userInfoController;
        this.mActivityStarter = activityStarter;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mQsPanelController = qSPanelController;
        this.mMetricsLogger = metricsLogger;
        this.mConfigurationController = configurationController;
        this.mOplusFgsManagerController = oplusFgsManagerController;
        this.mUserInteractor = userSwitcherInteractor;
        this.mShadeViewController = shadeViewController;
        this.mOplusQSActivityStarter = oplusQSActivityStarter;
        this.mSeparateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.mNotificationDependOnCommon = notificationDependOnCommon;
        this.mClockSecondsViewBinder = clockSecondsViewBinder;
        this.mIsLastLand = oplusQSFooterImpl.getContext().getResources().getConfiguration().orientation == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getQSSettingsState() {
        return "true".equals(Settings.Secure.getStringForUser(getContext().getContentResolver(), "qs_settings_has_open", -2));
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m("QSFooterController: ", "    mListening: ", printWriter), this.mListening, printWriter, "    mCollapseHeight: "), this.mCollapseHeight, printWriter, "    mExpandHeight: "), this.mExpandHeight, printWriter, "    mExpandHeight: ");
        sbM.append(this.mGapTop);
        printWriter.println(sbM.toString());
        if (((ViewController) this).mView != null) {
            printWriter.println("    translationY: " + ((OplusQSFooterImpl) ((ViewController) this).mView).getTranslationY());
            OplusLockIconView$.ExternalSyntheticOutline0.m(new StringBuilder("    couiCurrY: "), ((OplusQSFooterImpl) ((ViewController) this).mView).getSpringOverScroller() == null ? 0 : ((OplusQSFooterImpl) ((ViewController) this).mView).getSpringOverScroller().getCOUICurrY(), printWriter);
            ((OplusQSFooterImpl) ((ViewController) this).mView).dump(printWriter, strArr);
        }
    }

    public View getAnimatorView() {
        return getClockView();
    }

    public View getClockView() {
        return ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131364766);
    }

    public int getCollapseHeight() {
        return this.mCollapseHeight;
    }

    public View getDateView() {
        return ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131364311);
    }

    public int getExpandHeight() {
        return this.mExpandHeight;
    }

    public boolean getExpanded() {
        return this.mExpanded && this.mExpansion > 0.0f;
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public View getSettingView() {
        return ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131365156);
    }

    public final float getTopGap(float f) {
        return this.mGapTop;
    }

    public View getView() {
        return ((ViewController) this).mView;
    }

    public final void onInit() {
        OplusQSFooterImpl oplusQSFooterImpl = (OplusQSFooterImpl) ((ViewController) this).mView;
        OplusFgsManagerController oplusFgsManagerController = this.mOplusFgsManagerController;
        oplusQSFooterImpl.setOplusFgsManagerController(oplusFgsManagerController);
        ((OplusQSFooterImpl) ((ViewController) this).mView).setUserInterator(this.mUserInteractor);
        oplusFgsManagerController.init();
        Resources resources = ((OplusQSFooterImpl) ((ViewController) this).mView).getResources();
        this.mCollapseHeight = resources.getDimensionPixelSize(2131171866);
        this.mExpandHeight = resources.getDimensionPixelSize(2131171875);
        this.mGapTop = resources.getDimensionPixelSize(2131171876);
        OplusQSFooterImpl oplusQSFooterImpl2 = (OplusQSFooterImpl) ((ViewController) this).mView;
        OplusQSFooterViewController$$ExternalSyntheticLambda5 oplusQSFooterViewController$$ExternalSyntheticLambda5 = new OplusQSFooterViewController$$ExternalSyntheticLambda5();
        oplusQSFooterViewController$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQSFooterImpl2.setConfigurationChangeListener(oplusQSFooterViewController$$ExternalSyntheticLambda5);
    }

    public final void onQsColorStateChanged(int i) {
        LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterViewController", "onQsColorStateChanged updateTextColor qsColorState: " + i);
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        OplusQSFooterViewController$.ExternalSyntheticLambda6 externalSyntheticLambda6 = new OplusQSFooterViewController$.ExternalSyntheticLambda6();
        externalSyntheticLambda6.f$0 = this;
        externalSyntheticLambda6.f$1 = i;
        VarHandle.storeStoreFence();
        mainHandler.post(externalSyntheticLambda6);
    }

    public final void onViewAttached() {
        Trace.traceBegin(8L, "OplusQSFooterViewController onViewAttached");
        Context context = getContext();
        Clock clock = (OplusQSClock) ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131364766);
        this.mClockSecondsViewBinder.bindQsClock(clock);
        OplusQSFooterViewController$$ExternalSyntheticLambda2 oplusQSFooterViewController$$ExternalSyntheticLambda2 = new OplusQSFooterViewController$$ExternalSyntheticLambda2(0);
        oplusQSFooterViewController$$ExternalSyntheticLambda2.f$0 = this;
        oplusQSFooterViewController$$ExternalSyntheticLambda2.f$1 = context;
        VarHandle.storeStoreFence();
        clock.setOnClickListener(oplusQSFooterViewController$$ExternalSyntheticLambda2);
        OplusQSDateView oplusQSDateView = (OplusQSDateView) ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131364311);
        OplusQSFooterViewController$$ExternalSyntheticLambda2 oplusQSFooterViewController$$ExternalSyntheticLambda3 = new OplusQSFooterViewController$$ExternalSyntheticLambda2(1);
        oplusQSFooterViewController$$ExternalSyntheticLambda3.f$0 = this;
        oplusQSFooterViewController$$ExternalSyntheticLambda3.f$1 = context;
        VarHandle.storeStoreFence();
        oplusQSDateView.setOnClickListener(oplusQSFooterViewController$$ExternalSyntheticLambda3);
        ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131365156).setOnClickListener(this.mSettingsOnClickListener);
        context.getResources();
        View viewFindViewById = ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131364003);
        OplusQSFooterViewController$$ExternalSyntheticLambda4 oplusQSFooterViewController$$ExternalSyntheticLambda4 = new OplusQSFooterViewController$$ExternalSyntheticLambda4();
        oplusQSFooterViewController$$ExternalSyntheticLambda4.f$0 = this;
        oplusQSFooterViewController$$ExternalSyntheticLambda4.f$1 = context;
        oplusQSFooterViewController$$ExternalSyntheticLambda4.f$2 = viewFindViewById;
        VarHandle.storeStoreFence();
        viewFindViewById.setOnClickListener(oplusQSFooterViewController$$ExternalSyntheticLambda4);
        this.mConfigurationController.addCallback(this.mConfigurationListener);
        setListening(true);
        this.mOplusFgsManagerController.addOnNumberOfPackagesChangedListener(this.mFgsNumberListener);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("qs_settings_has_open"), false, this.mQsSettingsHasOpenObserver);
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        AnonymousClass5 anonymousClass5 = new AnonymousClass5(0);
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        workHandler.post(anonymousClass5);
        Trace.traceEnd(8L);
    }

    public final void onViewDetached() {
        Context context = getContext();
        setListening(false);
        this.mConfigurationController.removeCallback(this.mConfigurationListener);
        this.mOplusFgsManagerController.removeOnNumberOfPackagesChangedListener(this.mFgsNumberListener);
        context.getContentResolver().unregisterContentObserver(this.mQsSettingsHasOpenObserver);
    }

    public void setClockBaseline(int i) {
        ((OplusQSFooterImpl) ((ViewController) this).mView).setClockBaseline(i);
    }

    public /* bridge */ /* synthetic */ void setContainer(ExpandableNode.OnStateListener onStateListener) {
        super.setContainer(onStateListener);
    }

    public void setCustomizing(boolean z) {
        setVisibility(z ? 4 : 0);
    }

    public void setExpanded(boolean z) {
        if (this.mExpanded == z) {
            return;
        }
        this.mExpanded = z;
        ((OplusQSFooterImpl) ((ViewController) this).mView).setExpanded(z);
    }

    public void setExpansion(float f) {
        this.mExpansion = f;
        ((OplusQSFooterImpl) ((ViewController) this).mView).setExpansion(f);
    }

    public void setKeyguardShowing(boolean z) {
        OplusQSFooterImpl oplusQSFooterImpl = (OplusQSFooterImpl) ((ViewController) this).mView;
        oplusQSFooterImpl.setExpansion(oplusQSFooterImpl.mExpansionAmount);
    }

    public void setListening(boolean z) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        OplusQSFooterViewController$$ExternalSyntheticLambda0 oplusQSFooterViewController$$ExternalSyntheticLambda0 = this.mDragStateReceiver;
        AnonymousClass2 anonymousClass2 = this.mOnUserInfoChangedListener;
        UserInfoControllerImpl userInfoControllerImpl = this.mUserInfoController;
        if (z) {
            userInfoControllerImpl.addCallback(anonymousClass2);
            DragStateReceiver.getInstance().addListener(oplusQSFooterViewController$$ExternalSyntheticLambda0);
        } else {
            userInfoControllerImpl.removeCallback(anonymousClass2);
            DragStateReceiver.getInstance().removeListener(oplusQSFooterViewController$$ExternalSyntheticLambda0);
        }
        ((OplusQSFooterImpl) ((ViewController) this).mView).setListening(z);
    }

    public /* bridge */ /* synthetic */ float setTranslationY(float f, int i, Function1 function1) {
        return super.setTranslationY(f, i, function1);
    }

    public void setVisibility(int i) {
        ((OplusQSFooterImpl) ((ViewController) this).mView).setVisibility(i);
    }

    public final void updateViewState(float f, float f2) {
        ViewGroup viewGroup;
        if (TemporarilyInfoManager.Companion.getInstance().isShowing || (viewGroup = (ViewGroup) ((OplusQSFooterImpl) ((ViewController) this).mView).findViewById(2131365157)) == null || viewGroup.getChildCount() <= 0) {
            return;
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setTransitionAlpha(f);
        }
    }
}
