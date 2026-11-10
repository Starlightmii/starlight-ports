package com.oplus.systemui.shutdown;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.plugins.ActivityStarter;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OpAssistNavigationDialog$$ExternalSyntheticLambda0 implements ActivityStarter.OnDismissAction {
    public /* synthetic */ OpAssistNavigationDialog f$0;
    public /* synthetic */ AssistManagerImpl.beforeStartAssistInternal.3 f$1;

    public final boolean onDismiss() {
        OpAssistNavigationDialog opAssistNavigationDialog = this.f$0;
        AssistManagerImpl.beforeStartAssistInternal.3 r11 = this.f$1;
        if (!opAssistNavigationDialog.mIsShowing) {
            if (opAssistNavigationDialog.mCurrentPage == 2) {
                opAssistNavigationDialog.mCurrentPage = 0;
            }
            opAssistNavigationDialog.layoutInflater = (LayoutInflater) opAssistNavigationDialog.mContext.getSystemService("layout_inflater");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2008, 16777504, -3);
            opAssistNavigationDialog.mWindowLayoutParams = layoutParams;
            layoutParams.type = 2008;
            layoutParams.format = -3;
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isTablet()) {
                opAssistNavigationDialog.mWindowLayoutParams.screenOrientation = 1;
            }
            opAssistNavigationDialog.mWindowLayoutParams.setTitle("OpAssistNavigationDialog");
            opAssistNavigationDialog.mWindowLayoutParams.layoutInDisplayCutoutMode = 3;
            if (OpAssistNavigationDialog.isSystemAppGeminiExperience(opAssistNavigationDialog.mContext) || opAssistNavigationDialog.isNeedToShowYandexAssistant()) {
                boolean zIsTablet = FeatureOption.isTablet();
                LayoutInflater layoutInflater = opAssistNavigationDialog.layoutInflater;
                if (zIsTablet) {
                    opAssistNavigationDialog.mMainView = layoutInflater.inflate(2131559248, (ViewGroup) null);
                } else {
                    opAssistNavigationDialog.mMainView = layoutInflater.inflate(2131559247, (ViewGroup) null);
                }
            } else {
                opAssistNavigationDialog.mMainView = opAssistNavigationDialog.layoutInflater.inflate(2131559246, (ViewGroup) null);
            }
            View view = opAssistNavigationDialog.mMainView;
            if (view != null) {
                view.setSystemUiVisibility(5122);
                View view2 = opAssistNavigationDialog.mMainView;
                OpAssistNavigationDialog.AnonymousClass1 anonymousClass1 = new OpAssistNavigationDialog.AnonymousClass1();
                anonymousClass1.this$0 = opAssistNavigationDialog;
                VarHandle.storeStoreFence();
                view2.setOnSystemUiVisibilityChangeListener(anonymousClass1);
                opAssistNavigationDialog.mImageView = (ImageView) opAssistNavigationDialog.mMainView.findViewById(2131363247);
                opAssistNavigationDialog.mTopContent = (TextView) opAssistNavigationDialog.mMainView.findViewById(2131365712);
                opAssistNavigationDialog.mLeftButton = (Button) opAssistNavigationDialog.mMainView.findViewById(2131363698);
                opAssistNavigationDialog.mRightButton = (Button) opAssistNavigationDialog.mMainView.findViewById(2131364912);
                if (OpAssistNavigationDialog.isSystemAppGeminiExperience(opAssistNavigationDialog.mContext) || opAssistNavigationDialog.isNeedToShowYandexAssistant()) {
                    opAssistNavigationDialog.mTopTitle = (TextView) opAssistNavigationDialog.mMainView.findViewById(2131365715);
                    opAssistNavigationDialog.mBottomContent = (TextView) opAssistNavigationDialog.mMainView.findViewById(2131362297);
                    opAssistNavigationDialog.mCardBottom = opAssistNavigationDialog.mMainView.findViewById(2131362479);
                    opAssistNavigationDialog.mScrollView = (ScrollView) opAssistNavigationDialog.mMainView.findViewById(2131365076);
                    opAssistNavigationDialog.mTopContent.setMovementMethod(ScrollingMovementMethod.getInstance());
                    opAssistNavigationDialog.mBottomContent.setMovementMethod(ScrollingMovementMethod.getInstance());
                } else {
                    opAssistNavigationDialog.mMainView.findViewById(2131362066).getLayoutParams().height = opAssistNavigationDialog.mContext.getResources().getDimensionPixelOffset(2131170865);
                }
                opAssistNavigationDialog.mAnimationViewPow = opAssistNavigationDialog.mMainView.findViewById(2131362067);
                opAssistNavigationDialog.mAnimationViewAssist = opAssistNavigationDialog.mMainView.findViewById(2131362065);
                opAssistNavigationDialog.mAnimationViewPow.setRepeatCount(-1);
                opAssistNavigationDialog.mAnimationViewAssist.setRepeatCount(-1);
                boolean z = OpAssistNavigationDialog.isSystemAppGeminiExperience(opAssistNavigationDialog.mContext) || opAssistNavigationDialog.isNeedToShowYandexAssistant();
                boolean zIsTablet2 = FeatureOption.isTablet();
                boolean zIsOplusVolumeKeyInRight = FeatureOption.isOplusVolumeKeyInRight();
                if (!z) {
                    LottieAnimationView lottieAnimationView = opAssistNavigationDialog.mAnimationViewPow;
                    if (zIsTablet2) {
                        if (zIsOplusVolumeKeyInRight) {
                            lottieAnimationView.setAnimation(2131886234);
                        } else {
                            lottieAnimationView.setAnimation(2131886235);
                        }
                        opAssistNavigationDialog.mAnimationViewAssist.setAnimation(2131886088);
                    } else {
                        if (zIsOplusVolumeKeyInRight) {
                            lottieAnimationView.setAnimation(2131886232);
                        } else {
                            lottieAnimationView.setAnimation(2131886229);
                        }
                        opAssistNavigationDialog.mAnimationViewAssist.setAnimation(2131886084);
                    }
                } else if (zIsTablet2) {
                    int dimension = (int) opAssistNavigationDialog.mContext.getResources().getDimension(2131170871);
                    LottieAnimationView lottieAnimationView2 = opAssistNavigationDialog.mAnimationViewPow;
                    OpAssistNavigationDialog$$ExternalSyntheticLambda2 opAssistNavigationDialog$$ExternalSyntheticLambda2 = new OpAssistNavigationDialog$$ExternalSyntheticLambda2();
                    opAssistNavigationDialog$$ExternalSyntheticLambda2.f$0 = opAssistNavigationDialog;
                    opAssistNavigationDialog$$ExternalSyntheticLambda2.f$1 = lottieAnimationView2;
                    opAssistNavigationDialog$$ExternalSyntheticLambda2.f$2 = dimension;
                    VarHandle.storeStoreFence();
                    lottieAnimationView2.post(opAssistNavigationDialog$$ExternalSyntheticLambda2);
                    opAssistNavigationDialog.mAnimationViewPow.setAnimation(2131886231);
                    opAssistNavigationDialog.mAnimationViewAssist.setAnimation(2131886086);
                } else {
                    LottieAnimationView lottieAnimationView3 = opAssistNavigationDialog.mAnimationViewPow;
                    OpAssistNavigationDialog$$ExternalSyntheticLambda2 opAssistNavigationDialog$$ExternalSyntheticLambda3 = new OpAssistNavigationDialog$$ExternalSyntheticLambda2();
                    opAssistNavigationDialog$$ExternalSyntheticLambda3.f$0 = opAssistNavigationDialog;
                    opAssistNavigationDialog$$ExternalSyntheticLambda3.f$1 = lottieAnimationView3;
                    opAssistNavigationDialog$$ExternalSyntheticLambda3.f$2 = 0;
                    VarHandle.storeStoreFence();
                    lottieAnimationView3.post(opAssistNavigationDialog$$ExternalSyntheticLambda3);
                    LottieAnimationView lottieAnimationView4 = opAssistNavigationDialog.mAnimationViewPow;
                    if (zIsOplusVolumeKeyInRight) {
                        lottieAnimationView4.setAnimation(2131886233);
                        opAssistNavigationDialog.mAnimationViewAssist.setAnimation(2131886087);
                    } else {
                        lottieAnimationView4.setAnimation(2131886230);
                        opAssistNavigationDialog.mAnimationViewAssist.setAnimation(2131886085);
                    }
                    ScrollView scrollView = opAssistNavigationDialog.mScrollView;
                    OpAssistNavigationDialog$$ExternalSyntheticLambda1 opAssistNavigationDialog$$ExternalSyntheticLambda1 = new OpAssistNavigationDialog$$ExternalSyntheticLambda1();
                    opAssistNavigationDialog$$ExternalSyntheticLambda1.f$0 = opAssistNavigationDialog;
                    VarHandle.storeStoreFence();
                    scrollView.setOnTouchListener(opAssistNavigationDialog$$ExternalSyntheticLambda1);
                }
                Button button = opAssistNavigationDialog.mRightButton;
                OpAssistNavigationDialog.AnonymousClass2 anonymousClass2 = new OpAssistNavigationDialog.AnonymousClass2(0);
                anonymousClass2.this$0 = opAssistNavigationDialog;
                VarHandle.storeStoreFence();
                button.setOnClickListener(anonymousClass2);
                Button button2 = opAssistNavigationDialog.mLeftButton;
                OpAssistNavigationDialog.AnonymousClass2 anonymousClass3 = new OpAssistNavigationDialog.AnonymousClass2(1);
                anonymousClass3.this$0 = opAssistNavigationDialog;
                VarHandle.storeStoreFence();
                button2.setOnClickListener(anonymousClass3);
            }
            opAssistNavigationDialog.updateDialog(opAssistNavigationDialog.mCurrentPage);
            opAssistNavigationDialog.mWindowManager.addView(opAssistNavigationDialog.mMainView, opAssistNavigationDialog.mWindowLayoutParams);
            opAssistNavigationDialog.mIsShowing = true;
            r11.run();
        }
        return false;
    }
}
