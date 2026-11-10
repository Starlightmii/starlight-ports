package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSMultiUserSwitch extends LaunchableFrameLayout implements View.OnClickListener, IQSSpotLightConfig {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final KeyguardStateController mKeyguardStateController;
    public long mShowUserSwitchDialogRequestTime;
    public 3 mShowUserSwitchDialogRunnable;
    public final int[] mTmpInt2;
    public UserSwitcherInteractor mUserInteractor;
    public final UserManager mUserManager;
    public 2 mUserStateChangedListener;
    public final UserTracker mUserTracker;
    public UserTracker.Callback mUserTrackerCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSMultiUserSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTmpInt2 = new int[2];
        this.mUserTracker = (UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mUserTrackerCallback = anonymousClass1;
        this.mUserStateChangedListener = null;
        this.mShowUserSwitchDialogRequestTime = 0L;
        this.mShowUserSwitchDialogRunnable = null;
        this.mUserManager = UserManager.get(getContext());
        this.mKeyguardStateController = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void dispatchDraw(Canvas canvas) {
        super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super/*android.widget.FrameLayout*/.dispatchTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    public int getSpotLightType() {
        return 1;
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        2 r3;
        super/*android.widget.FrameLayout*/.onAttachedToWindow();
        this.mUserTracker.addCallback(this.mUserTrackerCallback, ((FrameLayout) this).mContext.getMainExecutor());
        UserSwitcherInteractor userSwitcherInteractor = this.mUserInteractor;
        if (userSwitcherInteractor == null || (r3 = this.mUserStateChangedListener) == null) {
            return;
        }
        userSwitcherInteractor.addCallback(r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.widget.FrameLayout, com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch, java.lang.Object] */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.mUserInteractor != null) {
            if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_USERSWITCH)) {
                LogUtil.d(LogUtil.TAG_QS, "ColorMultiUserSwitch", "onClick: fastClick");
                return;
            }
            ?? childAt = getChildCount() > 0 ? getChildAt(0) : this;
            childAt.getLocationInWindow(this.mTmpInt2);
            int[] iArr = this.mTmpInt2;
            iArr[0] = (childAt.getWidth() / 2) + iArr[0];
            int[] iArr2 = this.mTmpInt2;
            iArr2[1] = (childAt.getHeight() / 2) + iArr2[1];
            LogUtil.d(LogUtil.TAG_QS, "ColorMultiUserSwitch", "MultiUser button clicked keyguard state: " + this.mKeyguardStateController.mShowing);
            QsStatisticUtil.collectShortcutsCenterEvent(getContext(), "user_click");
            if (!this.mKeyguardStateController.mShowing) {
                UserSwitcherInteractor userSwitcherInteractor = this.mUserInteractor;
                if (userSwitcherInteractor != null) {
                    userSwitcherInteractor.showUserSwitcher((Expandable) null, (Context) null, view);
                    return;
                }
                return;
            }
            ActivityStarter activityStarter = (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class);
            OplusQSMultiUserSwitch$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSMultiUserSwitch$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            activityStarter.postQSRunnableDismissingKeyguard(externalSyntheticLambda0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        2 r2;
        super/*android.widget.FrameLayout*/.onDetachedFromWindow();
        this.mUserTracker.removeCallback(this.mUserTrackerCallback);
        UserSwitcherInteractor userSwitcherInteractor = this.mUserInteractor;
        if (userSwitcherInteractor == null || (r2 = this.mUserStateChangedListener) == null) {
            return;
        }
        userSwitcherInteractor.removeCallback(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.widget.FrameLayout*/.onFinishInflate();
        setOnClickListener(this);
        refreshContentDescription();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super/*android.widget.FrameLayout*/.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super/*android.widget.FrameLayout*/.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0023  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshContentDescription() {
        String str;
        UserInfo userInfo;
        if (this.mUserInteractor == null || !this.mUserManager.isUserSwitcherEnabled()) {
            str = null;
        } else {
            try {
                userInfo = this.mUserTracker.getUserInfo();
            } catch (NoSuchElementException unused) {
                LogUtil.e(LogUtil.TAG_QS, "ColorMultiUserSwitch", "getUserInfo NoSuchElementException");
                userInfo = null;
            }
            if (userInfo != null) {
                str = userInfo.name;
            } else {
                str = null;
            }
        }
        String string = TextUtils.isEmpty(str) ? null : ((FrameLayout) this).mContext.getString(2131951942, str);
        if (TextUtils.equals(getContentDescription(), string)) {
            return;
        }
        setContentDescription(string);
    }

    public void setUserInteractor(UserSwitcherInteractor userSwitcherInteractor) {
        2 r0;
        this.mUserInteractor = userSwitcherInteractor;
        if (userSwitcherInteractor != null && (r0 = this.mUserStateChangedListener) != null) {
            userSwitcherInteractor.addCallback(r0);
        }
        refreshContentDescription();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateSpotLightPath(Path path, Object obj) {
        int width = getWidth();
        int height = getHeight();
        float fMin = Math.min(width, height) / 2.0f;
        path.addRoundRect(0.0f, 0.0f, width, height, fMin, fMin, Path.Direction.CW);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusQSMultiUserSwitch$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements UserTracker.Callback {
        public /* synthetic */ OplusQSMultiUserSwitch this$0;

        public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
            super.onBeforeUserSwitching(i, runnable);
        }

        public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
            super.onProfilesChanged(list);
        }

        public final void onUserChanged(int i, Context context) {
            super.onUserChanged(i, context);
            OplusQSMultiUserSwitch oplusQSMultiUserSwitch = this.this$0;
            int i2 = OplusQSMultiUserSwitch.$r8$clinit;
            oplusQSMultiUserSwitch.refreshContentDescription();
        }

        public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
            super.onUserChanging(i, context);
        }

        public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
            super.onUserChanging(i, context, runnable);
        }
    }
}
