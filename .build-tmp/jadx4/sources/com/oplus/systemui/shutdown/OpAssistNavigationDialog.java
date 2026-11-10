package com.oplus.systemui.shutdown;

import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.airbnb.lottie.LottieAnimationView;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.keyguard.domain.quickaffordance.util.AppInfoUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OpAssistNavigationDialog implements ConfigurationController.ConfigurationListener {
    public LayoutInflater layoutInflater;
    public ActivityStarter mActivityStarter;
    public LottieAnimationView mAnimationViewAssist;
    public LottieAnimationView mAnimationViewPow;
    public TextView mBottomContent;
    public CardView mCardBottom;
    public Context mContext;
    public int mCurrentPage;
    public ImageView mImageView;
    public boolean mIsShowing;
    public Button mLeftButton;
    public View mMainView;
    public Button mRightButton;
    public ScrollView mScrollView;
    public TextView mTopContent;
    public TextView mTopTitle;
    public WindowManager.LayoutParams mWindowLayoutParams;
    public WindowManager mWindowManager;

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.OpAssistNavigationDialog$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnSystemUiVisibilityChangeListener {
        public /* synthetic */ OpAssistNavigationDialog this$0;

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public final void onSystemUiVisibilityChange(int i) {
            if (i == 0 || i == 2) {
                OpAssistNavigationDialog opAssistNavigationDialog = this.this$0;
                WindowManager.LayoutParams layoutParams = opAssistNavigationDialog.mWindowLayoutParams;
                if (layoutParams != null) {
                    opAssistNavigationDialog.mWindowManager.updateViewLayout(opAssistNavigationDialog.mMainView, layoutParams);
                }
                View view = this.this$0.mMainView;
                if (view != null) {
                    view.setSystemUiVisibility(5122);
                    this.this$0.mMainView.requestLayout();
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shutdown.OpAssistNavigationDialog$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements View.OnClickListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OpAssistNavigationDialog this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            OpAssistNavigationDialog opAssistNavigationDialog;
            int i;
            switch (this.$r8$classId) {
                case 0:
                    OpAssistNavigationDialog opAssistNavigationDialog2 = this.this$0;
                    opAssistNavigationDialog2.mCurrentPage++;
                    if (OpAssistNavigationDialog.isSystemAppGeminiExperience(opAssistNavigationDialog2.mContext) || this.this$0.isNeedToShowYandexAssistant() || (i = (opAssistNavigationDialog = this.this$0).mCurrentPage) >= 2) {
                        this.this$0.dismissDialog();
                    } else {
                        opAssistNavigationDialog.updateDialog(i);
                    }
                    break;
                default:
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.SubSettings"));
                    intent.putExtra(":settings:show_fragment", "com.oplus.settings.feature.convenient.PowerButtonSettingsFragment");
                    intent.addFlags(268435456);
                    this.this$0.mContext.startActivity(intent);
                    OpAssistNavigationDialog opAssistNavigationDialog3 = this.this$0;
                    opAssistNavigationDialog3.mCurrentPage = 2;
                    opAssistNavigationDialog3.dismissDialog();
                    break;
            }
        }
    }

    private String getCurrentAssistant() {
        RoleManager roleManager = (RoleManager) this.mContext.getSystemService("role");
        if (roleManager != null) {
            List roleHoldersAsUser = roleManager.getRoleHoldersAsUser("android.app.role.ASSISTANT", Process.myUserHandle());
            if (!roleHoldersAsUser.isEmpty()) {
                return (String) roleHoldersAsUser.getFirst();
            }
        }
        return null;
    }

    public static boolean isSystemAppGeminiExperience(Context context) {
        return AppInfoUtil.isSystemApp(context) && CustomizeFeatureOption.sIsGeminiExperience;
    }

    public final void dismissDialog() {
        WindowManager windowManager;
        View view = this.mMainView;
        if (view == null || (windowManager = this.mWindowManager) == null || !this.mIsShowing) {
            return;
        }
        this.mIsShowing = false;
        windowManager.removeViewImmediate(view);
        LottieAnimationView lottieAnimationView = this.mAnimationViewPow;
        if (lottieAnimationView != null) {
            if (lottieAnimationView.isAnimating()) {
                this.mAnimationViewPow.cancelAnimation();
            }
            this.mAnimationViewPow = null;
        }
        LottieAnimationView lottieAnimationView2 = this.mAnimationViewAssist;
        if (lottieAnimationView2 != null) {
            if (lottieAnimationView2.isAnimating()) {
                this.mAnimationViewAssist.cancelAnimation();
            }
            this.mAnimationViewAssist = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c  */
    public final boolean isNeedToShowYandexAssistant() {
        boolean z;
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isRealme) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isRuRegionMark() && CustomizeFeatureOption.sIsYandexExperience) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        StringBuilder sb = new StringBuilder("CustomizeFeatureOption.isYandexExperience(): ");
        sb.append(CustomizeFeatureOption.sIsYandexExperience);
        sb.append(" FeatureOption.INSTANCE.isRuRegionMark() : ");
        FeatureOption.INSTANCE.getClass();
        sb.append(FeatureOption.isRuRegionMark());
        sb.append(" BrandUtils.INSTANCE.isRealme(): ");
        LogUtil.d(LogUtil.TAG_GLOBALACTIONS, "OpAssistNavDialog", KeyguardLogger$.ExternalSyntheticOutline0.m(sb, BrandUtils.isRealme, " isRealmeSystemAppYandexExperience: ", z));
        if (z || CustomizeFeatureOption.sIsDefaultAssistantFeatureEnable) {
            String currentAssistant = getCurrentAssistant();
            if ("com.yandex.searchapp".equals(currentAssistant) || "com.yandex.browser".equals(currentAssistant)) {
                return true;
            }
        }
        return false;
    }

    public final void updateDialog(int i) {
        Drawable drawable;
        if (isNeedToShowYandexAssistant()) {
            this.mImageView.setVisibility(0);
            this.mTopTitle.setVisibility(0);
            this.mAnimationViewAssist.setVisibility(0);
            this.mAnimationViewPow.setVisibility(0);
            this.mAnimationViewPow.playAnimation();
            this.mAnimationViewAssist.playAnimation();
            ImageView imageView = this.mImageView;
            String currentAssistant = getCurrentAssistant();
            PackageManager packageManager = this.mContext.getPackageManager();
            if (packageManager != null) {
                try {
                    drawable = packageManager.getApplicationInfo(currentAssistant, 0).loadIcon(packageManager);
                } catch (PackageManager.NameNotFoundException unused) {
                    drawable = this.mContext.getDrawable(2131237615);
                }
                imageView.setImageDrawable(drawable);
            }
            this.mTopTitle.setText(2131957457);
            this.mTopContent.setText(2131954281);
        } else if (CustomizeFeatureOption.sIsSupportGeminiGone && isSystemAppGeminiExperience(this.mContext)) {
            this.mImageView.setVisibility(8);
            this.mTopContent.setText(2131954280);
            this.mTopTitle.setVisibility(0);
            this.mAnimationViewAssist.setVisibility(0);
            this.mAnimationViewPow.setVisibility(0);
            this.mAnimationViewPow.playAnimation();
            this.mAnimationViewAssist.playAnimation();
        } else if (isSystemAppGeminiExperience(this.mContext)) {
            this.mImageView.setImageDrawable(this.mContext.getResources().getDrawable(2131233589));
            this.mImageView.setVisibility(0);
            this.mTopContent.setText(2131954280);
            this.mTopTitle.setVisibility(0);
            this.mAnimationViewAssist.setVisibility(0);
            this.mAnimationViewPow.setVisibility(0);
            this.mAnimationViewPow.playAnimation();
            this.mAnimationViewAssist.playAnimation();
        } else if (i == 0) {
            this.mTopContent.setText(2131954283);
            this.mLeftButton.setVisibility(8);
            this.mImageView.setVisibility(4);
            this.mRightButton.setText(2131955292);
            this.mAnimationViewAssist.setVisibility(8);
            this.mAnimationViewPow.setVisibility(0);
            if (this.mAnimationViewAssist.isAnimating()) {
                this.mAnimationViewAssist.cancelAnimation();
            }
            this.mAnimationViewPow.playAnimation();
        } else if (i == 1) {
            this.mTopContent.setText(2131954282);
            this.mImageView.setVisibility(0);
            this.mRightButton.setText(2131954768);
            this.mAnimationViewAssist.setVisibility(0);
            this.mAnimationViewPow.setVisibility(8);
            if (this.mAnimationViewPow.isAnimating()) {
                this.mAnimationViewPow.cancelAnimation();
            }
            this.mAnimationViewAssist.playAnimation();
        }
        Context context = this.mContext;
        List list = OpUtils.PACKAGE_LISTENERS;
        UserInfo userInfo = ((UserManager) context.getSystemService("user")).getUserInfo(KeyguardUpdateMonitor.getCurrentUser());
        boolean zIsPrimary = userInfo == null ? false : userInfo.isPrimary();
        Button button = this.mLeftButton;
        if (!zIsPrimary) {
            button.setVisibility(8);
        } else {
            button.setVisibility(0);
            this.mLeftButton.setSelected(true);
        }
    }
}
