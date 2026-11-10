package com.oplus.systemui.notification.envelope.view;

import _COROUTINE.CoroutineDebuggingKt$;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.slice.widget.RowView$;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplus.confinemode.OplusConfineModeManager;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.notification.envelope.WeChatEnvelopeHandler;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class EnvelopeViewManager {
    public final AnonymousClass5 mCallBack;
    public String mContent;
    public final Context mCurrentContext;
    public boolean mEncrypted;
    public FrameLayout mEnvelopeFrame;
    Lazy mEnvelopeHelper;
    public final Handler mHandler;
    public EnvelopeHeadUpView mHeadUpView;
    public EnvelopeHeadUpView mHeadUpViewLand;
    Lazy mHeadsUpHelper;
    public final ValueAnimator mInAlphaAnim;
    public boolean mInAnimatorRunning;
    public final AnimatorSet mInAnimatorSet;
    public final ValueAnimator mInTranslateAnim;
    public boolean mInitSuccess;
    public int mInitialDisplayDensity;
    public boolean mIsChangingDensity;
    public final Object mLock;
    public final NotificationDependOnKeyguard mNotificationDependOnKeyguard;
    public Context mOriginContext;
    public final ValueAnimator mOutAlphaAnim;
    public boolean mOutAnimatorRunning;
    public final AnimatorSet mOutAnimatorSet;
    public final ValueAnimator mOutLandTranslateAnim;
    public final ValueAnimator mOutTranslateAnim;
    public final String mPackageName;
    public PendingIntent mPendingIntent;
    public boolean mRegistered;
    public final AnonymousClass2 mRemoveHeadUpViewRunnable;
    public final Resources mResources;
    public final AnonymousClass2 mShowHeadUpViewRunnable;
    public String mTitle;
    public final AnonymousClass2 mUpdateHeadUpViewRunnable;
    public final WindowManager mWindowManager;
    public final WindowManager.LayoutParams mWindowParams;
    public final AnonymousClass1 mReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.notification.envelope.view.EnvelopeViewManager.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                LogUtil.d(LogUtil.TAG_COMMON, "EnvelopeViewManager", "receive ACTION_SCREEN_OFF start");
                EnvelopeViewManager.this.removeHeadUpViewImmediately();
                LogUtil.d(LogUtil.TAG_COMMON, "EnvelopeViewManager", "receive ACTION_SCREEN_OFF end");
            } else if ("oplus.intent.config.density.change".equals(action)) {
                EnvelopeViewManager.this.onDensityChanged();
            }
        }
    };
    public boolean mEnvelopeHeadUpVisible = false;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.envelope.view.EnvelopeViewManager$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ EnvelopeViewManager this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ((HeadsUpHelper) this.this$0.mHeadsUpHelper.get()).setEnvelopeShow(true);
                    EnvelopeViewManager envelopeViewManager = this.this$0;
                    synchronized (envelopeViewManager.mLock) {
                        try {
                            if (!envelopeViewManager.mEnvelopeHeadUpVisible) {
                                envelopeViewManager.mEnvelopeHeadUpVisible = true;
                                int i = envelopeViewManager.mCurrentContext.getResources().getConfiguration().orientation;
                                EnvelopeHeadUpView envelopeHeadUpView = envelopeViewManager.mHeadUpView;
                                if (i == 1) {
                                    envelopeHeadUpView.setVisibility(0);
                                    envelopeViewManager.mHeadUpViewLand.setVisibility(8);
                                } else {
                                    envelopeHeadUpView.setVisibility(8);
                                    envelopeViewManager.mHeadUpViewLand.setVisibility(0);
                                }
                                envelopeViewManager.applyViewAndParams(i);
                                envelopeViewManager.mWindowManager.addView(envelopeViewManager.mEnvelopeFrame, envelopeViewManager.mWindowParams);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                        break;
                    }
                    EnvelopeViewManager envelopeViewManager2 = this.this$0;
                    if (envelopeViewManager2.mInAnimatorRunning) {
                        envelopeViewManager2.mInAnimatorSet.cancel();
                    }
                    envelopeViewManager2.mInAnimatorSet.start();
                    return;
                case 1:
                    ((HeadsUpHelper) this.this$0.mHeadsUpHelper.get()).setEnvelopeShow(false);
                    EnvelopeViewManager envelopeViewManager3 = this.this$0;
                    if (envelopeViewManager3.mOutAnimatorRunning) {
                        envelopeViewManager3.mOutAnimatorSet.cancel();
                    }
                    envelopeViewManager3.mOutAnimatorSet.play(envelopeViewManager3.mOutTranslateAnim).with(envelopeViewManager3.mOutAlphaAnim);
                    envelopeViewManager3.mOutAnimatorSet.start();
                    return;
                default:
                    synchronized (this.this$0.mLock) {
                        try {
                            int i2 = this.this$0.mCurrentContext.getResources().getConfiguration().orientation;
                            EnvelopeViewManager envelopeViewManager4 = this.this$0;
                            if (envelopeViewManager4.mEnvelopeHeadUpVisible) {
                                envelopeViewManager4.mHandler.removeCallbacks(envelopeViewManager4.mRemoveHeadUpViewRunnable);
                                EnvelopeViewManager envelopeViewManager5 = this.this$0;
                                envelopeViewManager5.mHandler.postDelayed(envelopeViewManager5.mRemoveHeadUpViewRunnable, 5000L);
                                this.this$0.applyViewAndParams(i2);
                                EnvelopeViewManager envelopeViewManager6 = this.this$0;
                                envelopeViewManager6.mWindowManager.updateViewLayout(envelopeViewManager6.mEnvelopeFrame, envelopeViewManager6.mWindowParams);
                                EnvelopeViewManager envelopeViewManager7 = this.this$0;
                                EnvelopeHeadUpView envelopeHeadUpView2 = envelopeViewManager7.mHeadUpView;
                                if (i2 == 1) {
                                    envelopeHeadUpView2.setVisibility(0);
                                    envelopeViewManager7.mHeadUpViewLand.setVisibility(8);
                                } else {
                                    envelopeHeadUpView2.setVisibility(8);
                                    envelopeViewManager7.mHeadUpViewLand.setVisibility(0);
                                }
                                EnvelopeViewManager envelopeViewManager8 = this.this$0;
                                if (envelopeViewManager8.mInAnimatorRunning) {
                                    envelopeViewManager8.mInAnimatorSet.cancel();
                                }
                                envelopeViewManager8.mInAnimatorSet.start();
                            }
                        } catch (Throwable th2) {
                            throw th2;
                        }
                        break;
                    }
                    return;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.envelope.view.EnvelopeViewManager$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements EnvelopeHeadUpView.CallBack {
        public /* synthetic */ EnvelopeViewManager this$0;
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.envelope.view.EnvelopeViewManager$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements Animator.AnimatorListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ EnvelopeViewManager this$0;

        public /* synthetic */ AnonymousClass6(int i) {
            this.$r8$classId = i;
        }

        private final void onAnimationCancel$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$10(Animator animator) {
        }

        private final void onAnimationCancel$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$11(Animator animator) {
        }

        private final void onAnimationCancel$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$6(Animator animator) {
        }

        private final void onAnimationCancel$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$7(Animator animator) {
        }

        private final void onAnimationCancel$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$9(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$10(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$11(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$12(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$6(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$7(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$8(Animator animator) {
        }

        private final void onAnimationRepeat$com$oplus$systemui$notification$envelope$view$EnvelopeViewManager$9(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 3:
                    this.this$0.mOutAnimatorRunning = false;
                    break;
                case 5:
                    this.this$0.mInAnimatorRunning = false;
                    break;
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mEnvelopeFrame.setTranslationY(0.0f);
                    break;
                case 1:
                    this.this$0.mEnvelopeFrame.setTranslationX(0.0f);
                    break;
                case 2:
                    this.this$0.mEnvelopeFrame.setAlpha(0.0f);
                    break;
                case 3:
                    EnvelopeViewManager envelopeViewManager = this.this$0;
                    envelopeViewManager.mOutAnimatorRunning = false;
                    envelopeViewManager.mEnvelopeFrame.setVisibility(8);
                    Handler handler = this.this$0.mHandler;
                    EnvelopeViewManager$12$$ExternalSyntheticLambda0 envelopeViewManager$12$$ExternalSyntheticLambda0 = new EnvelopeViewManager$12$$ExternalSyntheticLambda0();
                    envelopeViewManager$12$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    handler.post(envelopeViewManager$12$$ExternalSyntheticLambda0);
                    break;
                case 4:
                    this.this$0.mEnvelopeFrame.setAlpha(1.0f);
                    break;
                case 5:
                    this.this$0.mInAnimatorRunning = false;
                    break;
                default:
                    this.this$0.mEnvelopeFrame.setTranslationY(-100.0f);
                    break;
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
            int i = this.$r8$classId;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mEnvelopeFrame.setTranslationY(-100.0f);
                    break;
                case 1:
                    this.this$0.mEnvelopeFrame.setTranslationX(0.0f);
                    break;
                case 2:
                    this.this$0.mEnvelopeFrame.setAlpha(1.0f);
                    break;
                case 3:
                    this.this$0.mOutAnimatorRunning = true;
                    break;
                case 4:
                    this.this$0.mEnvelopeFrame.setAlpha(0.0f);
                    break;
                case 5:
                    EnvelopeViewManager envelopeViewManager = this.this$0;
                    envelopeViewManager.mInAnimatorRunning = true;
                    envelopeViewManager.mEnvelopeFrame.setVisibility(0);
                    break;
                default:
                    this.this$0.mEnvelopeFrame.setTranslationY(0.0f);
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.notification.envelope.view.EnvelopeViewManager$1] */
    public EnvelopeViewManager(Context context, Handler handler, NotificationDependOnKeyguard notificationDependOnKeyguard) {
        int i = 0;
        Object obj = new Object();
        this.mLock = obj;
        this.mInAnimatorRunning = false;
        this.mOutAnimatorRunning = false;
        this.mEncrypted = false;
        this.mRegistered = false;
        this.mIsChangingDensity = false;
        this.mInitialDisplayDensity = 0;
        int i2 = 1;
        this.mInitSuccess = true;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(i);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mShowHeadUpViewRunnable = anonymousClass2;
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(i2);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mRemoveHeadUpViewRunnable = anonymousClass3;
        int i3 = 2;
        AnonymousClass2 anonymousClass4 = new AnonymousClass2(i3);
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateHeadUpViewRunnable = anonymousClass4;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallBack = anonymousClass5;
        this.mCurrentContext = context;
        this.mOriginContext = context;
        this.mResources = context.getResources();
        this.mPackageName = context.getPackageName();
        this.mHandler = handler;
        this.mNotificationDependOnKeyguard = notificationDependOnKeyguard;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.mWindowManager = windowManager;
        synchronized (obj) {
            windowManager.getDefaultDisplay().getRealMetrics(new DisplayMetrics());
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mWindowParams = layoutParams;
            layoutParams.type = 2017;
            layoutParams.format = 1;
            layoutParams.flags = 25429800;
            layoutParams.setTitle("OSEnvelopeHeadUpView");
            applyOriginContext();
        }
        if (!this.mInitSuccess) {
            LogUtil.w(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--initAnimation skip,because init fail");
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(-100.0f, 0.0f);
        this.mInTranslateAnim = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(200L);
        this.mInTranslateAnim.setInterpolator(new AccelerateInterpolator());
        ValueAnimator valueAnimator = this.mInTranslateAnim;
        EnvelopeViewManager$$ExternalSyntheticLambda0 envelopeViewManager$$ExternalSyntheticLambda0 = new EnvelopeViewManager$$ExternalSyntheticLambda0(0);
        envelopeViewManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator.addUpdateListener(envelopeViewManager$$ExternalSyntheticLambda0);
        ValueAnimator valueAnimator2 = this.mInTranslateAnim;
        AnonymousClass6 anonymousClass6 = new AnonymousClass6(i);
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator2.addListener(anonymousClass6);
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mInAlphaAnim = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(200L);
        this.mInAlphaAnim.setInterpolator(new AccelerateInterpolator());
        ValueAnimator valueAnimator3 = this.mInAlphaAnim;
        EnvelopeViewManager$$ExternalSyntheticLambda0 envelopeViewManager$$ExternalSyntheticLambda1 = new EnvelopeViewManager$$ExternalSyntheticLambda0(1);
        envelopeViewManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator3.addUpdateListener(envelopeViewManager$$ExternalSyntheticLambda1);
        ValueAnimator valueAnimator4 = this.mInAlphaAnim;
        AnonymousClass6 anonymousClass7 = new AnonymousClass6(4);
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator4.addListener(anonymousClass7);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mInAnimatorSet = animatorSet;
        animatorSet.play(this.mInTranslateAnim).with(this.mInAlphaAnim);
        AnimatorSet animatorSet2 = this.mInAnimatorSet;
        AnonymousClass6 anonymousClass8 = new AnonymousClass6(5);
        anonymousClass8.this$0 = this;
        VarHandle.storeStoreFence();
        animatorSet2.addListener(anonymousClass8);
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, -100.0f);
        this.mOutTranslateAnim = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.setDuration(200L);
        this.mOutTranslateAnim.setInterpolator(new AccelerateInterpolator());
        ValueAnimator valueAnimator5 = this.mOutTranslateAnim;
        EnvelopeViewManager$$ExternalSyntheticLambda0 envelopeViewManager$$ExternalSyntheticLambda2 = new EnvelopeViewManager$$ExternalSyntheticLambda0(2);
        envelopeViewManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator5.addUpdateListener(envelopeViewManager$$ExternalSyntheticLambda2);
        ValueAnimator valueAnimator6 = this.mOutTranslateAnim;
        AnonymousClass6 anonymousClass9 = new AnonymousClass6(6);
        anonymousClass9.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator6.addListener(anonymousClass9);
        ValueAnimator valueAnimatorOfFloat4 = ValueAnimator.ofFloat(0.0f, 500.0f);
        this.mOutLandTranslateAnim = valueAnimatorOfFloat4;
        valueAnimatorOfFloat4.setDuration(200L);
        this.mOutLandTranslateAnim.setInterpolator(new AccelerateInterpolator());
        ValueAnimator valueAnimator7 = this.mOutLandTranslateAnim;
        EnvelopeViewManager$$ExternalSyntheticLambda0 envelopeViewManager$$ExternalSyntheticLambda3 = new EnvelopeViewManager$$ExternalSyntheticLambda0(3);
        envelopeViewManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator7.addUpdateListener(envelopeViewManager$$ExternalSyntheticLambda3);
        ValueAnimator valueAnimator8 = this.mOutLandTranslateAnim;
        AnonymousClass6 anonymousClass10 = new AnonymousClass6(i2);
        anonymousClass10.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator8.addListener(anonymousClass10);
        ValueAnimator valueAnimatorOfFloat5 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.mOutAlphaAnim = valueAnimatorOfFloat5;
        valueAnimatorOfFloat5.setDuration(200L);
        this.mOutAlphaAnim.setInterpolator(new AccelerateInterpolator());
        ValueAnimator valueAnimator9 = this.mOutAlphaAnim;
        EnvelopeViewManager$$ExternalSyntheticLambda0 envelopeViewManager$$ExternalSyntheticLambda4 = new EnvelopeViewManager$$ExternalSyntheticLambda0(4);
        envelopeViewManager$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator9.addUpdateListener(envelopeViewManager$$ExternalSyntheticLambda4);
        ValueAnimator valueAnimator10 = this.mOutAlphaAnim;
        AnonymousClass6 anonymousClass11 = new AnonymousClass6(i3);
        anonymousClass11.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimator10.addListener(anonymousClass11);
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.mOutAnimatorSet = animatorSet3;
        animatorSet3.play(this.mOutTranslateAnim).with(this.mOutAlphaAnim);
        AnimatorSet animatorSet4 = this.mOutAnimatorSet;
        AnonymousClass6 anonymousClass12 = new AnonymousClass6(3);
        anonymousClass12.this$0 = this;
        VarHandle.storeStoreFence();
        animatorSet4.addListener(anonymousClass12);
    }

    public final void applyOriginContext() {
        this.mInitialDisplayDensity = KeyguardUtils.getInitialDisplayDensity();
        Configuration configuration = new Configuration(this.mCurrentContext.getResources().getConfiguration());
        configuration.densityDpi = this.mInitialDisplayDensity;
        this.mOriginContext = this.mCurrentContext.createConfigurationContext(configuration);
        Resources resources = this.mResources;
        String str = this.mPackageName;
        int identifier = resources.getIdentifier("notification_envelope_frame", "layout", str);
        boolean z = identifier != 0;
        this.mInitSuccess = z;
        if (!z) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--applyOriginContext init fail");
            return;
        }
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mOriginContext).inflate(identifier, (ViewGroup) null);
        this.mEnvelopeFrame = frameLayout;
        EnvelopeHeadUpView envelopeHeadUpView = (EnvelopeHeadUpView) frameLayout.findViewById(this.mResources.getIdentifier("envelope_head_up_view", PictorialAppInfo.ID, str));
        this.mHeadUpView = envelopeHeadUpView;
        AnonymousClass5 anonymousClass5 = this.mCallBack;
        envelopeHeadUpView.setCallBack(anonymousClass5);
        EnvelopeHeadUpView envelopeHeadUpView2 = (EnvelopeHeadUpView) this.mEnvelopeFrame.findViewById(this.mResources.getIdentifier("envelope_head_up_view_land", PictorialAppInfo.ID, str));
        this.mHeadUpViewLand = envelopeHeadUpView2;
        envelopeHeadUpView2.setCallBack(anonymousClass5);
        this.mIsChangingDensity = false;
    }

    public final void applyViewAndParams(int i) {
        String str = this.mPackageName;
        if (i == 1) {
            TextView textView = (TextView) this.mHeadUpView.findViewById(this.mResources.getIdentifier("title", PictorialAppInfo.ID, str));
            TextView textView2 = (TextView) this.mHeadUpView.findViewById(this.mResources.getIdentifier("content", PictorialAppInfo.ID, str));
            textView.setVisibility(0);
            textView.setText(this.mTitle);
            if (this.mEncrypted) {
                textView.setVisibility(8);
            }
            textView2.setText(this.mContent);
            WindowManager.LayoutParams layoutParams = this.mWindowParams;
            layoutParams.width = -2;
            layoutParams.height = this.mOriginContext.getResources().getDimensionPixelSize(2131168167);
            WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
            layoutParams2.x = 0;
            layoutParams2.y = this.mOriginContext.getResources().getDimensionPixelSize(2131168169);
            this.mWindowParams.gravity = 49;
            return;
        }
        TextView textView3 = (TextView) this.mHeadUpViewLand.findViewById(this.mResources.getIdentifier("title", PictorialAppInfo.ID, str));
        TextView textView4 = (TextView) this.mHeadUpViewLand.findViewById(this.mResources.getIdentifier("content", PictorialAppInfo.ID, str));
        textView3.setVisibility(0);
        textView3.setText(this.mTitle);
        textView4.setText(this.mContent);
        if (this.mEncrypted) {
            textView3.setVisibility(8);
        }
        WindowManager.LayoutParams layoutParams3 = this.mWindowParams;
        layoutParams3.width = -2;
        layoutParams3.height = this.mOriginContext.getResources().getDimensionPixelSize(2131168167);
        WindowManager.LayoutParams layoutParams4 = this.mWindowParams;
        layoutParams4.x = 0;
        layoutParams4.y = this.mOriginContext.getResources().getDimensionPixelSize(2131168170);
        this.mWindowParams.gravity = 49;
    }

    public final void onDensityChanged() {
        this.mIsChangingDensity = true;
        Handler handler = this.mHandler;
        AnonymousClass2 anonymousClass2 = this.mRemoveHeadUpViewRunnable;
        if (handler.hasCallbacks(anonymousClass2)) {
            handler.removeCallbacks(anonymousClass2);
        }
        ((HeadsUpHelper) this.mHeadsUpHelper.get()).setEnvelopeShow(false);
        this.mEnvelopeFrame.setVisibility(8);
        removeWindow();
        applyOriginContext();
    }

    public final void onReceiveEnvelope(Notification notification, int i, boolean z, boolean z2) {
        if (!this.mInitSuccess) {
            LogUtil.w(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--onReceiveEnvelope skip,because init fail");
            return;
        }
        if (((HeadsUpHelper) this.mHeadsUpHelper.get()).mInCallUIFloatingWindowObserver.getShowing()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for call");
            return;
        }
        if (!((PowerManager) this.mCurrentContext.getSystemService("power")).isInteractive()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for screen off");
            return;
        }
        if (((NotificationDependOnKeyguardImpl) this.mNotificationDependOnKeyguard).isKeyguardShowing()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for keyguard");
            return;
        }
        if (Util.isChildrenOrStudyCenterMode()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for children mode");
            return;
        }
        if (this.mIsChangingDensity) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for density changed");
            return;
        }
        if (OplusConfineModeManager.getInstance().getConfineMode() == 8) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for game focus mode");
            return;
        }
        if (!this.mInitSuccess) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "Envelop--suppress for init fail");
            return;
        }
        if (this.mInitialDisplayDensity != KeyguardUtils.getInitialDisplayDensity()) {
            onDensityChanged();
        }
        String string = notification.extras.getCharSequence("android.title").toString();
        String string2 = notification.extras.getCharSequence("android.text").toString();
        if (i == 1) {
            if (z2 || WeChatEnvelopeHandler.isWechatEncrypted(string2)) {
                StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("Envelope--onReceiveEnvelope: needEncrypted:", ", WechatEncrypted:", z2);
                sbM.append(WeChatEnvelopeHandler.isWechatEncrypted(string2));
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", sbM.toString());
                string = this.mCurrentContext.getString(2131957252);
                string2 = this.mCurrentContext.getString(2131957251);
                z2 = true;
            } else {
                string2 = z ? CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(WeChatEnvelopeHandler.extractUserName(string2), ": ", WeChatEnvelopeHandler.extractContent(string2)) : WeChatEnvelopeHandler.extractContent(string2);
            }
        }
        PendingIntent pendingIntent = notification.contentIntent;
        this.mPendingIntent = pendingIntent;
        synchronized (this.mLock) {
            this.mEncrypted = z2;
            this.mTitle = string;
            this.mContent = string2;
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "EnvelopeViewManager", "[showHeadUpView] mPendingIntent = " + pendingIntent);
            boolean z3 = this.mEnvelopeHeadUpVisible;
            Handler handler = this.mHandler;
            if (z3) {
                handler.post(this.mUpdateHeadUpViewRunnable);
            } else {
                handler.post(this.mShowHeadUpViewRunnable);
                this.mHandler.postDelayed(this.mRemoveHeadUpViewRunnable, 5000L);
            }
        }
    }

    public final void registerReceiver() {
        synchronized (this.mLock) {
            try {
                if (!this.mRegistered && this.mInitSuccess) {
                    this.mRegistered = true;
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    intentFilter.addAction("oplus.intent.config.density.change");
                    ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(this.mReceiver, intentFilter);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeHeadUpViewImmediately() {
        Handler handler = this.mHandler;
        AnonymousClass2 anonymousClass2 = this.mRemoveHeadUpViewRunnable;
        if (handler.hasCallbacks(anonymousClass2)) {
            handler.removeCallbacks(anonymousClass2);
        }
        handler.post(anonymousClass2);
    }

    public final void removeWindow() {
        synchronized (this.mLock) {
            try {
                if (this.mEnvelopeHeadUpVisible) {
                    this.mEnvelopeHeadUpVisible = false;
                    this.mWindowManager.removeView(this.mEnvelopeFrame);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
