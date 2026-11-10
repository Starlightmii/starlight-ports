package com.oplus.systemui.qs;

import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.ViewController;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplus.systemui.keyguard.familymode.SystemFamilyModeObserver;
import com.oplus.systemui.keyguard.underagemode.UnderageModeObserver;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.qs.widget.ConstraintSetExt;
import com.oplus.systemui.qs.widget.OplusFgsManagerController;
import com.oplus.systemui.qs.widget.OplusQSCarrierText;
import com.oplus.systemui.qs.widget.OplusQSClock;
import com.oplus.systemui.qs.widget.OplusQSDateView;
import com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch;
import com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch$$ExternalSyntheticLambda1;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSFooterImpl extends FrameLayout implements Dumpable {
    public static final Interpolator DATE_X_EXPAND = new PathInterpolator(0.5f, 0.0f, 1.0f, 1.0f);
    public static final Interpolator DATE_Y_EXPAND = new PathInterpolator(0.0f, 0.0f, 0.5f, 1.0f);
    public static int sClockWidth = 0;
    public static boolean sIsSettingsAppDisabled = false;
    public static final AnonymousClass1 sSettingDisableStatePolicyObserver = new AnonymousClass1();
    public int mClockBaseline;
    public OplusQSClock mClockView;
    public ViewController.OnConfigurationChangedListener mConfigListener;
    public boolean mDateExpaned;
    public TouchAnimator mDateXExpandTranslationAnimator;
    public TouchAnimator mDateYExpandTranslationAnimator;
    public View.OnClickListener mExpandClickListener;
    public boolean mExpanded;
    public float mExpansionAmount;
    public boolean mIsInSuperPowerSaveMode;
    public boolean mIsInWellBeingAssistantMode;
    public TouchAnimator mLandAllAnimator;
    public Configuration mLastConfiguration;
    public boolean mLastShowUserSwitcher;
    public int mLayoutDirection;
    public boolean mListening;
    public View mMore;
    public OplusQSFooterBackgroundProxy mMoreButtonBackgroundProxy;
    public ImageView mMoreIcon;
    public ImageView mMultiUserAvatar;
    public OplusQSMultiUserSwitch mMultiUserSwitch;
    public OplusQSFooterBackgroundProxy mMultiUserSwitchBackgroundProxy;
    public OplusFgsManagerController mOplusFgsManagerController;
    public OplusQSCarrierText mOplusQSCarrier;
    public TouchAnimator mPortAndFoldAnimator;
    public TouchAnimator mPortraitExpandAndCarrierInfoAlphaAnimator;
    public OplusQSDateView mQsDateView;
    public boolean mReloadedUserInfo;
    public Scroller mScroller;
    public FrameLayout mSettingsButton;
    public OplusQSFooterBackgroundProxy mSettingsButtonBackgroundProxy;
    public ImageView mSettingsButtonIcon;
    public ConstraintLayout mSettingsContainer;
    public boolean mShowUserSwitcher;
    public SpringOverScroller mSpringOverScroller;
    public final ConstraintSet mTmpConstraintSet;
    public final UserInfoController mUserInfoController;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends TouchAnimator.ListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQSFooterImpl this$0;
        public /* synthetic */ boolean val$showUserSwitcher;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        public final void onAnimationAtStart() {
            switch (this.$r8$classId) {
                case 0:
                    OplusQSFooterImpl oplusQSFooterImpl = this.this$0;
                    boolean z = this.val$showUserSwitcher;
                    Interpolator interpolator = OplusQSFooterImpl.DATE_X_EXPAND;
                    oplusQSFooterImpl.updateActionViews(z);
                    break;
                default:
                    OplusQSFooterImpl oplusQSFooterImpl2 = this.this$0;
                    boolean z2 = this.val$showUserSwitcher;
                    Interpolator interpolator2 = OplusQSFooterImpl.DATE_X_EXPAND;
                    oplusQSFooterImpl2.updateActionViews(z2);
                    break;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0094  */
    /* JADX INFO: renamed from: $r8$lambda$14sFidUiKQIOZGF1QIEUE-tDyiY, reason: not valid java name */
    public static void m240$r8$lambda$14sFidUiKQIOZGF1QIEUEtDyiY(OplusQSFooterImpl oplusQSFooterImpl) {
        boolean z;
        boolean z2;
        boolean z3 = false;
        if (oplusQSFooterImpl.mIsInSuperPowerSaveMode) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher return false reason : mIsInSuperPowerSaveMode");
        } else if (SystemFamilyModeObserver.Companion.getInstance().mFamilyMode == 1) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher return false reason : isFamilyOpen");
        } else if (UnderageModeObserver.Companion.getInstance().minorsMode == 1) {
            LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher return false reason : isKeyguardUnderageOpen ");
        } else {
            try {
                int currentUser = KeyguardUpdateMonitor.getCurrentUser();
                if (currentUser == OplusMultiUserManager.getStudyUserId(((FrameLayout) oplusQSFooterImpl).mContext)) {
                    LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher: current User is StudyUser, ignore");
                } else {
                    if (currentUser == 888) {
                        z = false;
                    } else {
                        z = SystemProperties.getBoolean("persist.sys.assert.panic.multi.user.entrance", false) ? true : !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.multiuser_entry_disabled");
                    }
                    if (z) {
                        boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(currentUser);
                        if (zIsMultiSystemUserId) {
                            z2 = false;
                        } else {
                            OplusQSMultiUserSwitch oplusQSMultiUserSwitch = oplusQSFooterImpl.mMultiUserSwitch;
                            oplusQSMultiUserSwitch.getClass();
                            OplusQSMultiUserSwitch$$ExternalSyntheticLambda1 oplusQSMultiUserSwitch$$ExternalSyntheticLambda1 = new OplusQSMultiUserSwitch$$ExternalSyntheticLambda1();
                            oplusQSMultiUserSwitch$$ExternalSyntheticLambda1.f$0 = oplusQSMultiUserSwitch;
                            VarHandle.storeStoreFence();
                            if (((Boolean) DejankUtils.whitelistIpcs(oplusQSMultiUserSwitch$$ExternalSyntheticLambda1)).booleanValue()) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                        }
                        LogUtil.d(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher: isInMultiSystem: " + zIsMultiSystemUserId + ", show: " + z2);
                        z3 = z2;
                    }
                }
            } catch (Exception e) {
                LogUtil.w(LogUtil.TAG_QS, "OplusQSFooterImpl", "showUserSwitcher: exception : " + e);
            }
        }
        oplusQSFooterImpl.mShowUserSwitcher = z3;
        OplusQSFooterImpl$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSFooterImpl$.ExternalSyntheticLambda1(1);
        externalSyntheticLambda1.f$0 = oplusQSFooterImpl;
        VarHandle.storeStoreFence();
        oplusQSFooterImpl.post(externalSyntheticLambda1);
    }

    public static /* synthetic */ void $r8$lambda$53o7pt4YWERqfXGw70K9I4u6WbM(OplusQSFooterImpl oplusQSFooterImpl) {
        oplusQSFooterImpl.mTmpConstraintSet.clone(oplusQSFooterImpl.mSettingsContainer);
        if (DeviceUtil.isLandscape(((FrameLayout) oplusQSFooterImpl).mContext)) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isSupportMyDevice()) {
                oplusQSFooterImpl.updateQsMoreButton(((FrameLayout) oplusQSFooterImpl).mContext.getResources(), OplusQSSmallSpaceController.isSmallSpace(((FrameLayout) oplusQSFooterImpl).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((FrameLayout) oplusQSFooterImpl).mContext));
            }
        }
        oplusQSFooterImpl.mTmpConstraintSet.applyTo(oplusQSFooterImpl.mSettingsContainer);
    }

    public OplusQSFooterImpl(Context context, AttributeSet attributeSet, UserInfoController userInfoController) {
        super(context, attributeSet);
        this.mClockBaseline = 0;
        this.mTmpConstraintSet = new ConstraintSet();
        this.mUserInfoController = userInfoController;
        this.mLayoutDirection = getLayoutDirection();
    }

    private float getFontScale() {
        return ((OplusQSFontHelper.getInstance(((FrameLayout) this).mContext).getAdapterTextSizeSp(2) / 13.0f) + MathUtils.min(Resources.getSystem().getConfiguration().fontScale, 1.6f)) - 1.0f;
    }

    @Override // android.view.View
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.mSpringOverScroller);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0076  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void createAndUpdateExpandAnimators(boolean z) {
        float f;
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mMore, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        builder.addFloat(this.mMultiUserSwitch, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        anonymousClass2.val$showUserSwitcher = z;
        VarHandle.storeStoreFence();
        this.mLandAllAnimator = builder.setListener(anonymousClass2).build();
        TouchAnimator.Builder builder2 = new TouchAnimator.Builder();
        Resources resources = getResources();
        float dimension = resources.getDimension(2131171861);
        float dimension2 = resources.getDimension(2131171867);
        float dimension3 = resources.getDimension(2131171868);
        float dimension4 = resources.getDimension(2131171870);
        if (this.mClockView.getWidth() != 0) {
            sClockWidth = this.mClockView.getWidth();
        }
        float f2 = sClockWidth + dimension4;
        if (DeviceUtil.isRtl()) {
            f = 2.0f;
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isTablet()) {
                f = 2.1f;
            } else {
                f = 2.0f;
            }
        }
        this.mClockView.setPivotX(DeviceUtil.isRtl() ? sClockWidth : 0.0f);
        this.mQsDateView.setPivotX(DeviceUtil.isRtl() ? sClockWidth : 0.0f);
        float f3 = (-f2) + dimension2;
        float dimension5 = resources.getDimension(2131171858);
        float dimensionPixelSize = resources.getDimensionPixelSize(2131171902);
        float f4 = -(dimension5 + dimensionPixelSize);
        float width = this.mSettingsButton.getWidth() + dimension5;
        float height = (this.mSettingsButton.getHeight() / 2.0f) - this.mClockBaseline;
        if (LogUtil.getNormalDebug()) {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("clockX=", dimension, " clockTranslationY=", height, " dateExpandX=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, dimension2, " dateExpandY=", dimension3, " datePaddingStart=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, dimension4, " dateXTranslation=", f3, " settingExpandX=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, dimension5, " settingXTranslation=", f4, " settingWidth=");
            sbM.append(dimensionPixelSize);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusQSFooterImpl", sbM.toString());
        }
        int i = 1;
        builder2.addFloat(this.mClockView, MultiAnimViewDelegate.PROPERTY_SCALE_X, new float[]{getFontScale(), Math.min((f + getFontScale()) - 1.0f, 2.2f)});
        builder2.addFloat(this.mClockView, MultiAnimViewDelegate.PROPERTY_SCALE_Y, new float[]{getFontScale(), Math.min((f + getFontScale()) - 1.0f, 2.2f)});
        builder2.addFloat(this.mClockView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{0.0f, height});
        OplusQSClock oplusQSClock = this.mClockView;
        if (DeviceUtil.isRtl()) {
            dimension = -dimension;
        }
        builder2.addFloat(oplusQSClock, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, dimension});
        builder2.addFloat(this.mSettingsButton, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, DeviceUtil.isRtl() ? width : f4});
        OplusQSMultiUserSwitch oplusQSMultiUserSwitch = this.mMultiUserSwitch;
        if (DeviceUtil.isRtl()) {
            f4 = width;
        }
        builder2.addFloat(oplusQSMultiUserSwitch, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, f4});
        this.mPortAndFoldAnimator = builder2.build();
        TouchAnimator.Builder builder3 = new TouchAnimator.Builder();
        builder3.addFloat(this.mMore, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        builder3.addFloat(this.mMultiUserSwitch, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        builder3.addFloat(this.mOplusQSCarrier, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(i);
        anonymousClass3.this$0 = this;
        anonymousClass3.val$showUserSwitcher = z;
        VarHandle.storeStoreFence();
        this.mPortraitExpandAndCarrierInfoAlphaAnimator = builder3.setListener(anonymousClass3).setStartDelay(0.556f).build();
        TouchAnimator.Builder builder4 = new TouchAnimator.Builder();
        builder4.addFloat(this.mQsDateView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, DeviceUtil.isRtl() ? f2 : f3});
        this.mDateXExpandTranslationAnimator = builder4.setInterpolator(DATE_X_EXPAND).build();
        TouchAnimator.Builder builder5 = new TouchAnimator.Builder();
        builder5.addFloat(this.mQsDateView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_Y, new float[]{0.0f, dimension3});
        this.mDateYExpandTranslationAnimator = builder5.setInterpolator(DATE_Y_EXPAND).build();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m("OplusQSFooterImpl: ", "    mExpanded: ", printWriter), this.mExpanded, printWriter, "    mExpansionAmount: "), this.mExpansionAmount, printWriter, "    translationX of mClockView: ");
        Clock clock = this.mClockView;
        StringBuilder sbM2 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM, clock == null ? 0.0f : clock.getTranslationX(), printWriter, "    translationY of mClockView: ");
        Clock clock2 = this.mClockView;
        StringBuilder sbM3 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM2, clock2 == null ? 0.0f : clock2.getTranslationY(), printWriter, "    translationX of mQsDateView: ");
        OplusQSDateView oplusQSDateView = this.mQsDateView;
        StringBuilder sbM4 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM3, oplusQSDateView == null ? 0.0f : oplusQSDateView.getTranslationX(), printWriter, "    translationY of mQsDateView: ");
        OplusQSDateView oplusQSDateView2 = this.mQsDateView;
        StringBuilder sbM5 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM4, oplusQSDateView2 == null ? 0.0f : oplusQSDateView2.getTranslationY(), printWriter, "    width of mSettingsButton: ");
        FrameLayout frameLayout = this.mSettingsButton;
        StringBuilder sbM6 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM5, frameLayout == null ? 0 : frameLayout.getWidth(), printWriter, "    translationX of mSettingsButton: ");
        FrameLayout frameLayout2 = this.mSettingsButton;
        StringBuilder sbM7 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM6, frameLayout2 == null ? 0.0f : frameLayout2.getTranslationX(), printWriter, "    width of mMultiUserSwitch: ");
        LaunchableFrameLayout launchableFrameLayout = this.mMultiUserSwitch;
        StringBuilder sbM8 = OplusLockIconView$.ExternalSyntheticOutline0.m(sbM7, launchableFrameLayout != null ? launchableFrameLayout.getWidth() : 0, printWriter, "    translationX of mMultiUserSwitch: ");
        LaunchableFrameLayout launchableFrameLayout2 = this.mMultiUserSwitch;
        OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(OplusLockIconView$.ExternalSyntheticOutline0.m(sbM8, launchableFrameLayout2 != null ? launchableFrameLayout2.getTranslationX() : 0.0f, printWriter, "    mShowUserSwitcher: "), this.mShowUserSwitcher, printWriter, "    mIsInSuperPowerSaveMode : "), this.mIsInSuperPowerSaveMode, printWriter);
    }

    public boolean getExpanded() {
        return this.mExpanded;
    }

    public Scroller getScroller() {
        return this.mScroller;
    }

    public SpringOverScroller getSpringOverScroller() {
        return this.mSpringOverScroller;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        Trace.traceBegin(8L, "$TAG-onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        updateDeviceState();
        StringBuilder sb = new StringBuilder("[onConfigurationChanged]: mExpanded: ");
        sb.append(this.mExpanded);
        sb.append(", newConfig: ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(configuration.orientation, LogUtil.TAG_QS, "OplusQSFooterImpl", sb);
        this.mLastConfiguration = configuration;
        updateResources$11();
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
        Trace.traceEnd(8L);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SystemFamilyModeObserver.Companion.getInstance().unregister(((FrameLayout) this).mContext);
    }

    /* JADX WARN: Type inference failed for: r0v32, types: [android.widget.TextView, com.oplus.systemui.qs.widget.OplusQSCarrierText] */
    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", "initViews");
        OplusDevicepolicyManager oplusDevicepolicyManager = OplusDevicepolicyManager.getInstance();
        if (oplusDevicepolicyManager != null) {
            AnonymousClass1 anonymousClass1 = sSettingDisableStatePolicyObserver;
            oplusDevicepolicyManager.unregisterOplusDevicePolicyObserver(anonymousClass1);
            oplusDevicepolicyManager.registerOplusDevicepolicyObserver("setting_application_disable_state", anonymousClass1);
            sIsSettingsAppDisabled = oplusDevicepolicyManager.getBoolean("setting_application_disable_state", 1, false);
        }
        SystemFamilyModeObserver.Companion.getInstance().register(((FrameLayout) this).mContext, false);
        updateDeviceState();
        this.mSettingsContainer = findViewById(2131365157);
        this.mMore = findViewById(2131364003);
        this.mMoreIcon = (ImageView) findViewById(2131364004);
        this.mSettingsButtonIcon = (ImageView) findViewById(2131365158);
        this.mSettingsButton = (FrameLayout) findViewById(2131365156);
        this.mMultiUserSwitch = (OplusQSMultiUserSwitch) findViewById(2131364109);
        this.mMultiUserAvatar = (ImageView) findViewById(2131364107);
        this.mClockView = (OplusQSClock) findViewById(2131364766);
        this.mQsDateView = (OplusQSDateView) findViewById(2131364311);
        ?? r0 = (OplusQSCarrierText) findViewById(2131364765);
        this.mOplusQSCarrier = r0;
        r0.setMaxWidth(getResources().getDimensionPixelSize(2131171860));
        Clock clock = this.mClockView;
        OplusQSFooterImpl$$ExternalSyntheticLambda0 oplusQSFooterImpl$$ExternalSyntheticLambda0 = new OplusQSFooterImpl$$ExternalSyntheticLambda0();
        oplusQSFooterImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        clock.addOnLayoutChangeListener(oplusQSFooterImpl$$ExternalSyntheticLambda0);
        float dimension = getResources().getDimension(2131171166);
        this.mMoreButtonBackgroundProxy = new OplusQSFooterBackgroundProxy(this.mMore, null, dimension);
        this.mSettingsButtonBackgroundProxy = new OplusQSFooterBackgroundProxy(this.mSettingsButton, null, dimension);
        this.mMultiUserSwitchBackgroundProxy = new OplusQSFooterBackgroundProxy(this.mMultiUserSwitch, null, dimension);
        this.mSpringOverScroller = new SpringOverScroller(((FrameLayout) this).mContext);
        this.mScroller = new Scroller(((FrameLayout) this).mContext);
        View view = this.mMore;
        if (view != null) {
            view.bringToFront();
        }
        FrameLayout frameLayout = this.mSettingsButton;
        if (frameLayout != null) {
            frameLayout.bringToFront();
        }
        LaunchableFrameLayout launchableFrameLayout = this.mMultiUserSwitch;
        if (launchableFrameLayout != null) {
            launchableFrameLayout.bringToFront();
        }
        createAndUpdateExpandAnimators(false);
        updateResources$11();
        setImportantForAccessibility(1);
        refreshFooterButtonBackground();
        refreshFooterButtonIconColor();
        updateTextColor(QsColorUtil.getQsColorState());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        if (this.mLayoutDirection != i) {
            this.mLayoutDirection = i;
            updateResources$11();
        }
    }

    public final void onUserInfoChanged(Drawable drawable) {
        UserInfo userInfo;
        if (drawable == null) {
            if (this.mReloadedUserInfo) {
                return;
            }
            this.mUserInfoController.reloadUserInfo();
            this.mReloadedUserInfo = true;
            LogUtil.d(LogUtil.TAG_QS, "OplusQSFooterImpl", "failed to get user info, try again");
            return;
        }
        ColorFilter colorFilter = drawable.getColorFilter();
        Drawable drawableMutate = drawable.getConstantState().newDrawable(((FrameLayout) this).mContext.getResources()).mutate();
        try {
            userInfo = ((UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class)).getUserInfo();
        } catch (NoSuchElementException unused) {
            LogUtil.e(LogUtil.TAG_QS, "OplusQSFooterImpl", "onUserInfoChanged NoSuchElementException");
            userInfo = null;
        }
        if (userInfo != null && userInfo.isGuest() && !(drawableMutate instanceof UserIconDrawable)) {
            drawableMutate.setColorFilter(((FrameLayout) this).mContext.getResources().getColor(2131103759, null), PorterDuff.Mode.XOR);
        } else if ((drawableMutate instanceof UserIconDrawable) || !(colorFilter instanceof PorterDuffColorFilter)) {
            drawableMutate.setColorFilter(-1, PorterDuff.Mode.DST_IN);
        } else {
            drawableMutate.setColorFilter(((PorterDuffColorFilter) colorFilter).getColor(), PorterDuff.Mode.DST_IN);
        }
        drawableMutate.setBounds(0, 0, drawableMutate.getIntrinsicWidth(), drawableMutate.getIntrinsicHeight());
        ThreadManager threadManager = ThreadManager.getInstance();
        OplusQSFooterImpl$$ExternalSyntheticLambda4 oplusQSFooterImpl$$ExternalSyntheticLambda4 = new OplusQSFooterImpl$$ExternalSyntheticLambda4();
        oplusQSFooterImpl$$ExternalSyntheticLambda4.f$0 = this;
        oplusQSFooterImpl$$ExternalSyntheticLambda4.f$1 = drawableMutate;
        VarHandle.storeStoreFence();
        threadManager.postInThread(oplusQSFooterImpl$$ExternalSyntheticLambda4);
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        View.OnClickListener onClickListener;
        if (i != 262144 || (onClickListener = this.mExpandClickListener) == null) {
            return super.performAccessibilityAction(i, bundle);
        }
        onClickListener.onClick(null);
        return true;
    }

    public final void refreshFooterButtonBackground() {
        QsColorUtil.INSTANCE.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            return;
        }
        OplusQSFooterBackgroundProxy oplusQSFooterBackgroundProxy = this.mMoreButtonBackgroundProxy;
        if (oplusQSFooterBackgroundProxy != null) {
            oplusQSFooterBackgroundProxy.refreshViewBackground();
        }
        OplusQSFooterBackgroundProxy oplusQSFooterBackgroundProxy2 = this.mSettingsButtonBackgroundProxy;
        if (oplusQSFooterBackgroundProxy2 != null) {
            oplusQSFooterBackgroundProxy2.refreshViewBackground();
        }
        OplusQSFooterBackgroundProxy oplusQSFooterBackgroundProxy3 = this.mMultiUserSwitchBackgroundProxy;
        if (oplusQSFooterBackgroundProxy3 != null) {
            oplusQSFooterBackgroundProxy3.refreshViewBackground();
        }
    }

    public final void refreshFooterButtonIconColor() {
        QsColorUtil.INSTANCE.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            return;
        }
        StateFlow stateFlow = StdQSResPool.tileIconColorState;
        if (stateFlow.getValue() != null) {
            ColorStateList colorStateList = ((TileIconColorState) stateFlow.getValue()).getColorStateList(null);
            this.mMoreIcon.setImageTintList(colorStateList);
            this.mSettingsButtonIcon.setImageTintList(colorStateList);
            this.mMultiUserAvatar.setImageTintList(colorStateList);
        }
    }

    public void setClockBaseline(int i) {
        this.mClockBaseline = i;
    }

    public void setConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigListener = onConfigurationChangedListener;
    }

    public void setExpandClickListener(View.OnClickListener onClickListener) {
        this.mExpandClickListener = onClickListener;
    }

    public void setExpanded(boolean z) {
        if (this.mExpanded != z) {
            TaskbarDelegate$1$.ExternalSyntheticOutline0.m("setExpanded expanded =", LogUtil.TAG_QS, "OplusQSFooterImpl", z);
            this.mExpanded = z;
            updateUi();
        }
    }

    public void setExpansion(float f) {
        this.mExpansionAmount = f;
        updateExpand(f);
    }

    public void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        LogUtil.d(LogUtil.TAG_QS, "OplusQSFooterImpl", KeyguardLogger$.ExternalSyntheticOutline0.m(new StringBuilder("setListening mListening="), this.mListening, " listening=", z));
        this.mListening = z;
        if (z) {
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            this.mIsInSuperPowerSaveMode = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
            updateEverything();
        }
        this.mOplusQSCarrier.setListening(z);
    }

    public void setOplusFgsManagerController(OplusFgsManagerController oplusFgsManagerController) {
        this.mOplusFgsManagerController = oplusFgsManagerController;
    }

    public void setShowPoPWindows(boolean z) {
        this.mTmpConstraintSet.clone(this.mSettingsContainer);
        this.mTmpConstraintSet.applyTo(this.mSettingsContainer);
    }

    public void setUserInterator(UserSwitcherInteractor userSwitcherInteractor) {
        this.mMultiUserSwitch.setUserInteractor(userSwitcherInteractor);
    }

    public final void updateActionViews(boolean z) {
        boolean z2 = OplusQSSmallSpaceController.isSmallSpace(((FrameLayout) this).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((FrameLayout) this).mContext);
        Resources resources = ((FrameLayout) this).mContext.getResources();
        this.mTmpConstraintSet.clone(this.mSettingsContainer);
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        this.mIsInSuperPowerSaveMode = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
        boolean zUpdateQsClockView = updateQsClockView() | updateQsMoreButton(resources, z2);
        int dimensionPixelSize = z2 ? resources.getDimensionPixelSize(2131171903) : resources.getDimensionPixelSize(2131171902);
        boolean zUpdateConstrainHeight = ConstraintSetExt.updateConstrainHeight(this.mTmpConstraintSet, this.mSettingsButton.getId(), dimensionPixelSize) | ConstraintSetExt.updateConstrainWidth(this.mTmpConstraintSet, this.mSettingsButton.getId(), dimensionPixelSize) | ConstraintSetExt.updateVisibility(this.mTmpConstraintSet, this.mSettingsButton.getId(), (this.mIsInSuperPowerSaveMode || sIsSettingsAppDisabled || this.mIsInWellBeingAssistantMode) ? 4 : 0);
        this.mSettingsButton.setClickable((this.mIsInSuperPowerSaveMode || this.mIsInWellBeingAssistantMode) ? false : true);
        boolean zUpdateEndSideConnect = ((!DeviceUtil.isLandscape(((FrameLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources())) ? ConstraintSetExt.updateEndSideConnect(this.mTmpConstraintSet, this.mSettingsButton.getId(), 0, 7, 0) | zUpdateConstrainHeight : ConstraintSetExt.updateEndSideConnect(this.mTmpConstraintSet, this.mSettingsButton.getId(), 2131364003, 6, Integer.valueOf(getResources().getDimensionPixelSize(2131171858))) | zUpdateConstrainHeight | ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mSettingsButton.getId(), 0.0f) | ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mSettingsButton.getId(), 0.0f)) | zUpdateQsClockView;
        int dimensionPixelSize2 = z2 ? resources.getDimensionPixelSize(2131171903) : resources.getDimensionPixelSize(2131171902);
        boolean zUpdateConstrainHeight2 = ConstraintSetExt.updateConstrainHeight(this.mTmpConstraintSet, this.mMultiUserSwitch.getId(), dimensionPixelSize2) | ConstraintSetExt.updateConstrainWidth(this.mTmpConstraintSet, this.mMultiUserSwitch.getId(), dimensionPixelSize2);
        int i = (this.mExpanded && z && !this.mIsInSuperPowerSaveMode) ? 0 : 8;
        StringBuilder sb = new StringBuilder("updateQsMultiUserSwitch mExpanded=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", showUserSwitcher= ", ", mIsInSuperPowerSaveMode= ", sb, this.mExpanded, z);
        LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, this.mIsInSuperPowerSaveMode, ", shouldScaleDown=", z2));
        boolean zUpdateVisibility = ConstraintSetExt.updateVisibility(this.mTmpConstraintSet, this.mMultiUserSwitch.getId(), i) | zUpdateConstrainHeight2 | ConstraintSetExt.updateEndSideConnect(this.mTmpConstraintSet, 2131364109, 2131365156, 6, Integer.valueOf(getResources().getDimensionPixelSize(2131171858)));
        if (DeviceUtil.isLandscape(((FrameLayout) this).mContext) && !DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
            zUpdateVisibility = zUpdateVisibility | ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mMultiUserSwitch.getId(), 0.0f) | ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mMultiUserSwitch.getId(), 0.0f);
        }
        if (zUpdateVisibility | zUpdateEndSideConnect) {
            this.mTmpConstraintSet.applyTo(this.mSettingsContainer);
        }
        if (LargeScreenUtils.shouldUseSplitNotificationShade(((FrameLayout) this).mContext.getResources()) || this.mExpansionAmount != 0.0f) {
            return;
        }
        QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
        qSFragmentHelper.onFractionUpdated(qSFragmentHelper.mConverFraction, false);
    }

    public final void updateDeviceState() {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        this.mIsInSuperPowerSaveMode = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSFooterImpl", new StringBuilder("updateDeviceState mIsInSuperPowerSaveMode= "), this.mIsInSuperPowerSaveMode);
        WellBeingAssistantRepositoryImpl.Companion.getClass();
        this.mIsInWellBeingAssistantMode = WellBeingAssistantRepositoryImpl.isWellBeingAssistantState;
    }

    public final void updateEverything() {
        Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
        OplusQSFooterImpl$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSFooterImpl$.ExternalSyntheticLambda1(0);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda1);
    }

    public final void updateExpand(float f) {
        Configuration configuration;
        if (Float.isNaN(f)) {
            return;
        }
        if (DeviceUtil.isLandscape(((FrameLayout) this).mContext)) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet() && !DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
                if (this.mDateExpaned) {
                    this.mDateXExpandTranslationAnimator.setPosition(0.0f);
                    this.mDateYExpandTranslationAnimator.setPosition(0.0f);
                    this.mDateExpaned = false;
                }
                this.mLandAllAnimator.setPosition(f);
                return;
            }
        }
        if (LogUtil.getNormalDebug()) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet() && (configuration = this.mLastConfiguration) != null && configuration.orientation == 2) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusQSFooterImpl", "updateExpand in landscape fraction=" + f);
            }
        }
        this.mPortAndFoldAnimator.setPosition(f);
        this.mPortraitExpandAndCarrierInfoAlphaAnimator.setPosition(f);
        this.mDateXExpandTranslationAnimator.setPosition(f);
        this.mDateYExpandTranslationAnimator.setPosition(f);
        this.mDateExpaned = f == 1.0f;
    }

    public final boolean updateQsCarrierText() {
        boolean zUpdateVisibility = ConstraintSetExt.updateVisibility(this.mTmpConstraintSet, this.mOplusQSCarrier.getId(), (this.mExpanded && !this.mIsInSuperPowerSaveMode && !this.mIsInWellBeingAssistantMode) & ((!DeviceUtil.isLandscape(((FrameLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources())) && !QSFeatureOption.INSTANCE.isVirtualCommConsumerDevice(((FrameLayout) this).mContext)) ? 0 : 4);
        if (!DeviceUtil.isLandscape(((FrameLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
            return zUpdateVisibility;
        }
        return ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mOplusQSCarrier.getId(), 0.0f) | zUpdateVisibility | ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mOplusQSCarrier.getId(), 0.0f);
    }

    public final boolean updateQsClockView() {
        boolean zUpdateTranslationX = (!DeviceUtil.isLandscape(((FrameLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources())) ? false : ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mClockView.getId(), 0.0f) | ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mClockView.getId(), 0.0f) | ConstraintSetExt.updateScaleX(this.mTmpConstraintSet, this.mClockView.getId(), getFontScale()) | ConstraintSetExt.updateScaleY(this.mTmpConstraintSet, this.mClockView.getId(), getFontScale());
        StringBuilder sb = new StringBuilder("updateQsClockView isLandscape = ");
        sb.append(DeviceUtil.isLandscape(((FrameLayout) this).mContext));
        sb.append(", shouldUseLargeFoldConfig = ");
        sb.append(DeviceUtil.shouldUseLargeFoldConfig(getResources()));
        sb.append(", updated = ");
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "OplusQSFooterImpl", sb, zUpdateTranslationX);
        return zUpdateTranslationX;
    }

    public final void updateQsDateView() {
        boolean zIsLandscape = DeviceUtil.isLandscape(((FrameLayout) this).mContext);
        boolean zShouldUseLargeFoldConfig = DeviceUtil.shouldUseLargeFoldConfig(getResources());
        boolean zUpdateTranslationX = false;
        if (zIsLandscape && !zShouldUseLargeFoldConfig) {
            this.mDateExpaned = false;
            zUpdateTranslationX = ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mQsDateView.getId(), 0.0f) | ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mQsDateView.getId(), 0.0f);
        }
        boolean zUpdateConstrainWidth = zUpdateTranslationX | ConstraintSetExt.updateConstrainWidth(this.mTmpConstraintSet, this.mQsDateView.getId(), getResources().getDimensionPixelSize(2131171872));
        int dimensionPixelSize = ((FrameLayout) this).mContext.getResources().getDimensionPixelSize(2131171869);
        if (!zIsLandscape || zShouldUseLargeFoldConfig) {
            ConstraintSetExt.updateEndSideConnect(this.mTmpConstraintSet, this.mQsDateView.getId(), -1, 6, Integer.valueOf(dimensionPixelSize));
        } else {
            boolean z = this.mShowUserSwitcher;
            ConstraintSet constraintSet = this.mTmpConstraintSet;
            OplusQSDateView oplusQSDateView = this.mQsDateView;
            if (z) {
                ConstraintSetExt.updateEndSideConnect(constraintSet, oplusQSDateView.getId(), 2131364109, 6, Integer.valueOf(dimensionPixelSize));
            } else {
                ConstraintSetExt.updateEndSideConnect(constraintSet, oplusQSDateView.getId(), 2131365156, 6, Integer.valueOf(dimensionPixelSize));
            }
        }
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_QS, "OplusQSFooterImpl", TextFieldImplKt$.ExternalSyntheticOutline0.m("updateQsDateView isLandscape = ", ", shouldUseLargeFoldConfig = ", ", updated = ", zIsLandscape, zShouldUseLargeFoldConfig), zUpdateConstrainWidth);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0038  */
    public final boolean updateQsMoreButton(Resources resources, boolean z) {
        boolean z2;
        FeatureOption.INSTANCE.getClass();
        boolean zIsSupportMyDevice = FeatureOption.isSupportMyDevice();
        boolean z3 = true;
        boolean z4 = !LargeScreenUtils.shouldUseSplitNotificationShade(((FrameLayout) this).mContext.getResources()) || FeatureOption.isTablet();
        boolean zIsFgsShow = this.mOplusFgsManagerController.isFgsShow();
        if (OsBinderCacheUtils.getCurrentUserId() == 0) {
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isMediaShowSupport) {
                z2 = false;
            } else {
                z2 = true;
            }
        } else {
            z2 = false;
        }
        if (!zIsSupportMyDevice && !z4 && !zIsFgsShow && !z2) {
            z3 = false;
        }
        int dimensionPixelSize = z ? resources.getDimensionPixelSize(2131171903) : resources.getDimensionPixelSize(2131171902);
        boolean zUpdateConstrainHeight = ConstraintSetExt.updateConstrainHeight(this.mTmpConstraintSet, this.mMore.getId(), dimensionPixelSize) | ConstraintSetExt.updateConstrainWidth(this.mTmpConstraintSet, this.mMore.getId(), dimensionPixelSize) | ConstraintSetExt.updateVisibility(this.mTmpConstraintSet, this.mMore.getId(), (!this.mExpanded || !z3 || this.mIsInSuperPowerSaveMode || this.mIsInWellBeingAssistantMode) ? 8 : 0);
        if (!DeviceUtil.isLandscape(((FrameLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources())) {
            return zUpdateConstrainHeight;
        }
        return ConstraintSetExt.updateTranslationY(this.mTmpConstraintSet, this.mMore.getId(), 0.0f) | zUpdateConstrainHeight | ConstraintSetExt.updateTranslationX(this.mTmpConstraintSet, this.mMore.getId(), 0.0f);
    }

    public final void updateResources$11() {
        if (!LargeScreenUtils.shouldUseSplitNotificationShade(((FrameLayout) this).mContext.getResources()) && this.mExpansionAmount == 0.0f) {
            QSFragmentHelper qSFragmentHelper = QSFragmentHelper.getInstance();
            qSFragmentHelper.onFractionUpdated(qSFragmentHelper.mConverFraction, false);
        }
        Resources resources = ((FrameLayout) this).mContext.getResources();
        this.mTmpConstraintSet.clone(this.mSettingsContainer);
        ((FrameLayout.LayoutParams) this.mSettingsContainer.getLayoutParams()).height = resources.getDimensionPixelSize(2131171875);
        int dimensionPixelSize = resources.getDimensionPixelSize(2131171904);
        this.mSettingsContainer.setPaddingRelative(dimensionPixelSize, 0, dimensionPixelSize, 0);
        updateQsClockView();
        updateQsDateView();
        updateQsCarrierText();
        updateEverything();
        this.mTmpConstraintSet.applyTo(this.mSettingsContainer);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateTextColor(int i) {
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updateTextColor qsColorState: ", " mContext:");
        sbM.append(((FrameLayout) this).mContext);
        LogUtil.i(LogUtil.TAG_QS, "OplusQSFooterImpl", sbM.toString());
        boolean zIsTextNeedUseLightColorWhenLight = QsColorUtil.isTextNeedUseLightColorWhenLight(((FrameLayout) this).mContext, i);
        OplusQSDateView oplusQSDateView = this.mQsDateView;
        if (!zIsTextNeedUseLightColorWhenLight) {
            oplusQSDateView.setTextColor(((FrameLayout) this).mContext.getColor(2131103705));
            this.mClockView.setTextColor(((FrameLayout) this).mContext.getColor(2131103705));
            this.mOplusQSCarrier.setTextColor(((FrameLayout) this).mContext.getColor(2131103705));
            this.mMoreIcon.setImageTintList(ColorStateList.valueOf(((FrameLayout) this).mContext.getColor(2131103704)));
            this.mSettingsButtonIcon.setImageTintList(ColorStateList.valueOf(((FrameLayout) this).mContext.getColor(2131103728)));
            return;
        }
        int i2 = QsColorUtil.CARRIER_TEXT_LIGHT_COLOR;
        oplusQSDateView.setTextColor(i2);
        this.mClockView.setTextColor(i2);
        this.mOplusQSCarrier.setTextColor(i2);
        this.mMoreIcon.setImageTintList(ColorStateList.valueOf(i2));
        this.mSettingsButtonIcon.setImageTintList(ColorStateList.valueOf(i2));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateUi() {
        StringBuilder sb = new StringBuilder("updateUi showUserSwitcher = ");
        sb.append(this.mShowUserSwitcher);
        sb.append(" mExpanded = ");
        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSFooterImpl", sb, this.mExpanded);
        setClickable(false);
        updateActionViews(this.mShowUserSwitcher);
        View view = this.mMore;
        if (view != null) {
            view.bringToFront();
        }
        FrameLayout frameLayout = this.mSettingsButton;
        if (frameLayout != null) {
            frameLayout.bringToFront();
        }
        LaunchableFrameLayout launchableFrameLayout = this.mMultiUserSwitch;
        if (launchableFrameLayout != null) {
            launchableFrameLayout.bringToFront();
        }
        updateDeviceState();
        this.mSettingsButton.setVisibility((this.mIsInSuperPowerSaveMode || sIsSettingsAppDisabled || this.mIsInWellBeingAssistantMode) ? 4 : 0);
        this.mSettingsButton.setClickable((this.mIsInSuperPowerSaveMode || this.mIsInWellBeingAssistantMode) ? false : true);
        boolean z = this.mExpanded;
        LaunchableFrameLayout launchableFrameLayout2 = this.mMultiUserSwitch;
        if (launchableFrameLayout2 != null) {
            launchableFrameLayout2.setClickable(z);
        }
        this.mMore.setClickable(z);
        if (sIsSettingsAppDisabled) {
            this.mSettingsButton.setClickable(false);
        }
        Clock clock = this.mClockView;
        clock.setClickable((clock.getVisibility() != 0 || this.mIsInSuperPowerSaveMode || this.mIsInWellBeingAssistantMode) ? false : true);
        this.mQsDateView.setClickable((this.mIsInSuperPowerSaveMode || this.mIsInWellBeingAssistantMode) ? false : true);
        this.mQsDateView.setSelected(true);
        this.mTmpConstraintSet.clone(this.mSettingsContainer);
        if (updateQsCarrierText()) {
            this.mTmpConstraintSet.applyTo(this.mSettingsContainer);
        }
        AppCompatTextView appCompatTextView = this.mOplusQSCarrier;
        appCompatTextView.setClickable(appCompatTextView.getVisibility() == 0);
        createAndUpdateExpandAnimators(this.mShowUserSwitcher);
        updateExpand(this.mExpansionAmount);
        if (this.mLastShowUserSwitcher != this.mShowUserSwitcher) {
            updateQsDateView();
            this.mTmpConstraintSet.applyTo(this.mSettingsContainer);
            this.mLastShowUserSwitcher = this.mShowUserSwitcher;
        }
    }

    public OplusQSFooterImpl(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (UserInfoController) DependencyEx.sDependency.getDependency(UserInfoController.class));
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSFooterImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusDevicepolicyManager.OplusDevicePolicyObserver {
        public final void onOplusDevicePolicyUpdate(String str, List list) {
            OplusQSFooterImpl.sIsSettingsAppDisabled = OplusDevicepolicyManager.getInstance().getBoolean("setting_application_disable_state", 1, false);
            ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSFooterImpl", new StringBuilder("onOplusDevicePolicyUpdate sIsSettingsAppDisabled: "), OplusQSFooterImpl.sIsSettingsAppDisabled);
        }

        public final void onOplusDevicePolicyUpdate(String str, String str2) {
            OplusQSFooterImpl.sIsSettingsAppDisabled = OplusDevicepolicyManager.getInstance().getBoolean("setting_application_disable_state", 1, false);
            ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "OplusQSFooterImpl", new StringBuilder("onOplusDevicePolicyUpdate sIsSettingsAppDisabled: "), OplusQSFooterImpl.sIsSettingsAppDisabled);
        }
    }
}
