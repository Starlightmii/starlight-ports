package com.oplus.systemui.qs.customize;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.oplus.systemui.qs.OplusQSBottomImpl;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.OplusQSTileMediaContainer;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.mydevice.MyDevicePanel;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.util.ScrimVisibleUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSDetailClipper {
    public static final Interpolator CUSTOM_INTERPOLATOR;
    public AnonymousClass1 mAlphaAnimate;
    public ValueAnimator mAlphaInAnimator;
    public AnonymousClass2 mAlphaInOnEnd;
    public ValueAnimator mAlphaOutAnimator;
    public AnonymousClass2 mAlphaOutOnEnd;
    public View mBackground;
    public View mDetail;
    public ValueAnimator mFadeInAnimator;
    public ValueAnimator mFadeOutAnimator;
    public AnonymousClass2 mFadeOutOnEnd;
    public OplusQSFooterImpl mFooter;
    public View mHeader;
    public boolean mIsRunning;
    public boolean mIsScaleMode;
    public View mMyDevice;
    public NotificationStackScrollLayout mNotificationScrollLayout;
    public OplusQSBottomImpl mQSBottom;
    public QSPanel mQSPanel;
    public OplusQSTileMediaContainer mTileMedia;
    public AnonymousClass2 mVisibleOnStart;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSDetailClipper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            ScrimVisibleUtil.Companion.getInstance().setRunning(false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            ScrimVisibleUtil.Companion.getInstance().setRunning(true);
        }
    }

    static {
        new PathInterpolator(0.1f, 0.0f, 0.1f, 1.0f);
        new PathInterpolator(0.1f, 0.0f, 0.9f, 1.0f);
        CUSTOM_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    }

    public final void cancelAnimator() {
        ValueAnimator valueAnimator = this.mFadeInAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mAlphaInAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.mFadeOutAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        ValueAnimator valueAnimator4 = this.mAlphaOutAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.cancel();
        }
    }

    public final void resetStatus() {
        this.mQSBottom.setAlpha(1.0f);
        this.mTileMedia.setAlpha(1.0f);
        View view = this.mMyDevice;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        this.mQSPanel.setAlpha(1.0f);
        this.mFooter.setAlpha(1.0f);
        this.mBackground.setAlpha(1.0f);
        this.mHeader.setAlpha(1.0f);
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationScrollLayout;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.setAlpha(1.0f);
        }
        this.mDetail.setScaleX(1.0f);
        this.mDetail.setScaleY(1.0f);
        NotificationStackScrollLayout notificationScrollLayout = QSFragmentHelper.getInstance().getNotificationScrollLayout();
        if (notificationScrollLayout != null) {
            notificationScrollLayout.setVisibility(0);
        }
    }

    public void setIsScaleMode(boolean z) {
        this.mIsScaleMode = z;
        if (this.mIsRunning) {
            resetStatus();
        }
    }

    public void setMyDevicePanel(MyDevicePanel myDevicePanel) {
        this.mMyDevice = myDevicePanel;
    }

    public void setTransparent(boolean z) {
        if (!z) {
            resetStatus();
            return;
        }
        LogUtil.d(LogUtil.TAG_QS, "OplusQSDetailClipper", "setTransparent: show detail");
        this.mQSBottom.setAlpha(0.0f);
        this.mTileMedia.setAlpha(0.0f);
        View view = this.mMyDevice;
        if (view != null) {
            view.setAlpha(0.0f);
        }
        this.mQSPanel.setAlpha(0.0f);
        this.mFooter.setAlpha(0.0f);
        this.mHeader.setAlpha(0.0f);
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationScrollLayout;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.setAlpha(0.0f);
        }
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
    public final void updateFadePosition(float f) {
        float f2 = 1.0f - f;
        this.mQSBottom.setAlpha(f2);
        this.mQSPanel.setAlpha(f2);
        this.mTileMedia.setAlpha(f2);
        View view = this.mMyDevice;
        if (view != null) {
            view.setAlpha(f2);
        }
        NotificationStackScrollLayout notificationStackScrollLayout = this.mNotificationScrollLayout;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.setAlpha(f2);
        }
        if (this.mIsScaleMode) {
            this.mFooter.setAlpha(f2);
            this.mHeader.setAlpha(f2);
        }
        LogUtil.d(LogUtil.TAG_QS, "OplusQSDetailClipper", "updateFadePosition:" + f);
    }
}
