package com.oplus.systemui.toast;

import android.R;
import android.animation.Animator;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.plugins.ToastPlugin;
import com.android.systemui.toast.OplusToastDefaultAnimation;
import com.oplus.systemui.common.helper.ResponsiveUIModelHelper;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemUIToast implements ToastPlugin.Toast {
    public final Context mContext;
    public int mDefaultGravity;
    public int mDefaultY;
    public final boolean mHasIcon;
    public final ImageView mIconView;
    public final Animator mInAnimator;
    public final LayoutInflater mLayoutInflater;
    public final Animator mOutAnimator;
    public final ToastPlugin.Toast mPluginToast;
    public int mSubDefaultY;
    public int mSubIconSize;
    public final CharSequence mText;
    public final TextView mTextView;
    public final View mToastView;
    public final LinearLayout mToastcontainer;
    public final ResponsiveUIModelHelper mUiModelHelper;

    public OplusSystemUIToast(Context context, CharSequence charSequence, ToastPlugin.Toast toast, String str, int i, int i2, ResponsiveUIModelHelper responsiveUIModelHelper) {
        ApplicationInfo applicationInfoAsUser;
        View view;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mText = charSequence;
        this.mPluginToast = toast;
        this.mUiModelHelper = responsiveUIModelHelper;
        if (!isPluginToast$1() || toast.getView() == null) {
            Drawable badgedIcon = null;
            try {
                applicationInfoAsUser = context.getPackageManager().getApplicationInfoAsUser(str, 0, i);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("OplusSystemUIToast", "Package name not found package=" + str + " user=" + i);
                applicationInfoAsUser = null;
            }
            View viewInflate = this.mLayoutInflater.inflate(2131559950, (ViewGroup) null);
            this.mTextView = (TextView) viewInflate.findViewById(2131365614);
            this.mIconView = (ImageView) viewInflate.findViewById(2131363381);
            this.mToastcontainer = (LinearLayout) viewInflate.findViewById(2131365689);
            this.mTextView.setTextSize(OplusQSFontHelper.getInstance(this.mContext).getAdapterTextSizeSp(7));
            this.mHasIcon = false;
            if (applicationInfoAsUser == null || applicationInfoAsUser.targetSdkVersion >= 31) {
                Context context2 = this.mContext;
                if ((context2.getApplicationContext() instanceof Application) || i != 0) {
                    try {
                        PackageManager packageManager = context2.getPackageManager();
                        ApplicationInfo applicationInfoAsUser2 = packageManager.getApplicationInfoAsUser(str, PackageManager.ApplicationInfoFlags.of(128L), i);
                        if (applicationInfoAsUser2 == null || packageManager.getLaunchIntentForPackage(applicationInfoAsUser2.packageName) == null) {
                            LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusSystemUIToast", str + " toast icon is null, appInfo: " + applicationInfoAsUser2);
                        } else {
                            badgedIcon = IconDrawableFactory.newInstance(context2).getBadgedIcon(applicationInfoAsUser2, UserHandle.getUserId(applicationInfoAsUser2.uid));
                        }
                    } catch (PackageManager.NameNotFoundException unused2) {
                        Log.e("OplusSystemUIToast", "Couldn't find application info for packageName=" + str + " userId=" + i);
                    }
                } else {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusSystemUIToast", str + " toast icon is null, user id is system");
                }
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "OplusSystemUIToast", str + " toast icon is " + badgedIcon);
                if (badgedIcon == null) {
                    this.mIconView.setVisibility(8);
                } else {
                    this.mHasIcon = true;
                    this.mIconView.setImageDrawable(badgedIcon);
                    if (applicationInfoAsUser == null) {
                        Log.d("OplusSystemUIToast", "No appInfo for pkg=" + str + " usr=" + i);
                    } else if (applicationInfoAsUser.labelRes != 0) {
                        try {
                            this.mIconView.setContentDescription(this.mContext.getPackageManager().getResourcesForApplication(applicationInfoAsUser, new Configuration(this.mContext.getResources().getConfiguration())).getString(applicationInfoAsUser.labelRes));
                        } catch (PackageManager.NameNotFoundException unused3) {
                            Log.d("OplusSystemUIToast", "Cannot find application resources for icon label.");
                        }
                    }
                }
            } else {
                this.mTextView.setMaxLines(Integer.MAX_VALUE);
                viewInflate.findViewById(2131363381).setVisibility(8);
            }
            setTextViewMaxSize();
            view = viewInflate;
        } else {
            view = toast.getView();
        }
        this.mToastView = view;
        ToastPlugin.Toast toast2 = this.mPluginToast;
        this.mInAnimator = (!isPluginToast$1() || toast2.getInAnimation() == null) ? OplusToastDefaultAnimation.Companion.toastIn(getView()) : toast2.getInAnimation();
        ToastPlugin.Toast toast3 = this.mPluginToast;
        this.mOutAnimator = (!isPluginToast$1() || toast3.getOutAnimation() == null) ? OplusToastDefaultAnimation.Companion.toastOut(getView()) : toast3.getOutAnimation();
        onOrientationChange(i2);
    }

    private void setTextParams(boolean z) {
        if (z) {
            this.mSubDefaultY = this.mContext.getResources().getDimensionPixelSize(2131171212);
            if (this.mHasIcon && this.mIconView != null) {
                int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(2131171200);
                int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(2131171193);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
                layoutParams.setMarginsRelative(0, 0, dimensionPixelSize, 0);
                layoutParams.width = dimensionPixelSize2;
                layoutParams.height = dimensionPixelSize2;
                this.mIconView.setLayoutParams(layoutParams);
            }
            LinearLayout linearLayout = this.mToastcontainer;
            if (linearLayout != null) {
                linearLayout.setMinimumHeight(this.mContext.getResources().getDimensionPixelSize(2131171197));
            }
            this.mTextView.setTextColor(this.mContext.getColor(2131103116));
        }
    }

    public Integer getGravity() {
        if (isPluginToast$1()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getGravity() != null) {
                return toast.getGravity();
            }
        }
        return Integer.valueOf(this.mDefaultGravity);
    }

    public Integer getHorizontalMargin() {
        if (isPluginToast$1()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getHorizontalMargin() != null) {
                return toast.getHorizontalMargin();
            }
        }
        return 0;
    }

    public Animator getInAnimation() {
        return this.mInAnimator;
    }

    public Animator getOutAnimation() {
        return this.mOutAnimator;
    }

    public Integer getVerticalMargin() {
        if (isPluginToast$1()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getVerticalMargin() != null) {
                return toast.getVerticalMargin();
            }
        }
        return 0;
    }

    public View getView() {
        return this.mToastView;
    }

    public Integer getXOffset() {
        if (isPluginToast$1()) {
            ToastPlugin.Toast toast = this.mPluginToast;
            if (toast.getXOffset() != null) {
                return toast.getXOffset();
            }
        }
        return 0;
    }

    public Integer getYOffset() {
        int i;
        if (DeviceStateController.getInstance().isOpenOrHalfOpen()) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isFoldRemapDisabled()) {
                return Integer.valueOf(this.mContext.getResources().getDimensionPixelSize(2131171233));
            }
        }
        FeatureOption.INSTANCE.getClass();
        boolean zIsTablet = FeatureOption.isTablet();
        Context context = this.mContext;
        if (zIsTablet) {
            return Integer.valueOf(context.getResources().getDimensionPixelSize(2131171232));
        }
        return (!OplusSubDisplayUtils.Companion.getInstance(context).getBaseSubDisplayShow() || (i = this.mSubDefaultY) <= 0) ? Integer.valueOf(this.mDefaultY) : Integer.valueOf(i);
    }

    public final boolean isPluginToast$1() {
        return this.mPluginToast != null;
    }

    public final void onOrientationChange(int i) {
        ToastPlugin.Toast toast = this.mPluginToast;
        if (toast != null) {
            toast.onOrientationChange(i);
        }
        Context context = this.mContext;
        if (2 == i) {
            this.mDefaultY = context.getResources().getDimensionPixelSize(2131171231);
        } else {
            this.mDefaultY = context.getResources().getDimensionPixelSize(17105863) - this.mContext.getResources().getDimensionPixelSize(2131171230);
        }
        this.mDefaultGravity = this.mContext.getResources().getInteger(R.integer.device_idle_idle_factor);
    }

    public final void setTextViewMaxSize() {
        int dimensionPixelSize;
        if (this.mTextView == null) {
            return;
        }
        boolean baseSubDisplayShow = OplusSubDisplayUtils.Companion.getInstance(this.mContext).getBaseSubDisplayShow();
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(2131171220);
        int dimensionPixelSize3 = this.mContext.getResources().getDimensionPixelSize(2131171219);
        int dimensionPixelSize4 = this.mContext.getResources().getDimensionPixelSize(2131171218) + this.mContext.getResources().getDimensionPixelSize(2131171211);
        this.mSubIconSize = this.mContext.getResources().getDimensionPixelSize(2131171218) + this.mContext.getResources().getDimensionPixelSize(2131171193);
        int dimensionPixelSize5 = this.mContext.getResources().getDimensionPixelSize(2131171196);
        int toastMaxWidth = this.mUiModelHelper.getToastMaxWidth();
        boolean z = this.mHasIcon;
        if (z) {
            if (baseSubDisplayShow) {
                dimensionPixelSize = ((toastMaxWidth - (this.mContext.getResources().getDimensionPixelSize(2131171201) * 2)) - (dimensionPixelSize5 * 2)) - this.mSubIconSize;
            } else {
                toastMaxWidth = (toastMaxWidth - dimensionPixelSize2) - dimensionPixelSize4;
                dimensionPixelSize = toastMaxWidth - dimensionPixelSize3;
            }
        } else if (baseSubDisplayShow) {
            dimensionPixelSize = (toastMaxWidth - (this.mContext.getResources().getDimensionPixelSize(2131171198) * 2)) - (dimensionPixelSize5 * 2);
        } else {
            dimensionPixelSize3 *= 2;
            dimensionPixelSize = toastMaxWidth - dimensionPixelSize3;
        }
        if (dimensionPixelSize <= 0) {
            dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(2131171226);
        }
        this.mTextView.setMaxWidth(dimensionPixelSize);
        this.mTextView.setText(this.mText);
        TextView textView = this.mTextView;
        if (z) {
            textView.measure(View.MeasureSpec.makeMeasureSpec(Integer.MIN_VALUE, textView.getMaxWidth()), 0);
            if (textView.getLineCount() >= 2) {
                textView.setMaxWidth(dimensionPixelSize);
            }
        }
        setTextParams(baseSubDisplayShow);
    }
}
