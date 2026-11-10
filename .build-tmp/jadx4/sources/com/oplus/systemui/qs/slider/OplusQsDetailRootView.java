package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsDetailRootView extends LaunchableFrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsDetailRootView";
    private ViewController.OnConfigurationChangedListener configListener;
    public View hookToggleView;
    public boolean isLandscape;
    private OrientationChangedListener listener;
    private final OplusQsDetailLayoutHelper locationHelper;
    private View portraitSpaceB;
    private View portraitSpaceD;
    private View portraitSpaceF;
    public View rootContainer;
    public OplusQsDetailToggleSliderLayout slider;
    public RelativeLayout temporarilyTipsContainer;
    public int temporarilyTipsMaxLine;
    public OplusQsDetailTilesContainer tilesContainer;
    public TextView titleTextView;

    public OplusQsDetailRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.locationHelper = new OplusQsDetailLayoutHelper(context);
        this.temporarilyTipsMaxLine = 2;
    }

    public final View getHookToggleView() {
        View view = this.hookToggleView;
        if (view != null) {
            return view;
        }
        return null;
    }

    public final OrientationChangedListener getListener() {
        return this.listener;
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    public final OplusQsDetailToggleSliderLayout getSlider() {
        OplusQsDetailToggleSliderLayout oplusQsDetailToggleSliderLayout = this.slider;
        if (oplusQsDetailToggleSliderLayout != null) {
            return oplusQsDetailToggleSliderLayout;
        }
        return null;
    }

    public final RelativeLayout getTemporarilyTipsContainer() {
        RelativeLayout relativeLayout = this.temporarilyTipsContainer;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        return null;
    }

    public final int getTemporarilyTipsMaxLine() {
        return this.temporarilyTipsMaxLine;
    }

    public final OplusQsDetailTilesContainer getTilesContainer() {
        OplusQsDetailTilesContainer oplusQsDetailTilesContainer = this.tilesContainer;
        if (oplusQsDetailTilesContainer != null) {
            return oplusQsDetailTilesContainer;
        }
        return null;
    }

    public final TextView getTitleTextView() {
        TextView textView = this.titleTextView;
        if (textView != null) {
            return textView;
        }
        return null;
    }

    public final boolean handleConfigurationChanged(Configuration configuration) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsDetailRootView-->".concat("handleConfigurationChanged() " + (configuration != null ? Integer.valueOf(configuration.orientation) : null)));
        }
        if (configuration == null) {
            return false;
        }
        boolean z = configuration.orientation == 2;
        boolean z2 = this.isLandscape != z;
        if (z2) {
            this.isLandscape = z;
        }
        return z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void loadViewByOrientation(int i, View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("loadViewByOrientation() isLandscape = ", "OplusQsDetailRootView-->", "SystemUi--QuickSettings", this.isLandscape);
        }
        this.locationHelper.prepareLayoutInfo();
        removeAllViews();
        View viewInflate = LayoutInflater.from(getContext()).inflate(2131559337, (ViewGroup) null);
        this.rootContainer = viewInflate;
        if (viewInflate == null) {
            viewInflate = null;
        }
        setTemporarilyTipsContainer((RelativeLayout) viewInflate.findViewById(2131364738));
        View view2 = this.rootContainer;
        if (view2 == null) {
            view2 = null;
        }
        setTitleTextView((TextView) view2.findViewById(2131364740));
        getTitleTextView().setMinWidth((int) getResources().getDimension(2131171738));
        getTitleTextView().setTextColor(OplusQsDetailColorUtil.INSTANCE.getCurrentColor(getContext()));
        getTitleTextView().setOnClickListener(inflateLayout.1.INSTANCE);
        getTitleTextView().setImportantForAccessibility(2);
        View view3 = this.rootContainer;
        if (view3 == null) {
            view3 = null;
        }
        setSlider((OplusQsDetailToggleSliderLayout) view3.findViewById(2131364734));
        getSlider().generateToggleViewWithLayoutId(i, view);
        View toggleView = getSlider().getToggleView();
        if (toggleView != null) {
            toggleView.setVisibility(4);
        }
        setHookToggleView(view);
        getHookToggleView().setImportantForAccessibility(2);
        view.setOnClickListener(inflateLayout.2.INSTANCE);
        View view4 = this.rootContainer;
        if (view4 == null) {
            view4 = null;
        }
        setTilesContainer(view4.findViewById(2131364739));
        getTilesContainer().setLocationHelper(this.locationHelper);
        boolean z = this.locationHelper.isLandscapeLayout;
        View view5 = this.rootContainer;
        if (z) {
            if (view5 == null) {
                view5 = null;
            }
            view5.findViewById(2131364733).addView(view, 1);
        } else {
            if (view5 == null) {
                view5 = null;
            }
            this.portraitSpaceB = view5.findViewById(2131364735);
            View view6 = this.rootContainer;
            if (view6 == null) {
                view6 = null;
            }
            this.portraitSpaceD = view6.findViewById(2131364736);
            View view7 = this.rootContainer;
            if (view7 == null) {
                view7 = null;
            }
            this.portraitSpaceF = view7.findViewById(2131364737);
            View view8 = this.rootContainer;
            if (view8 == null) {
                view8 = null;
            }
            ((LinearLayoutCompat) view8).addView(view, 1);
        }
        View view9 = this.rootContainer;
        if (view9 == null) {
            view9 = null;
        }
        addView(view9);
        OplusQsDetailLayoutHelper oplusQsDetailLayoutHelper = this.locationHelper;
        if (oplusQsDetailLayoutHelper.isLandscapeLayout) {
            this.temporarilyTipsMaxLine = 2;
            getSlider().setSliderHeight(this.locationHelper.getLandscapeSliderHeight());
        } else {
            PortraitHeightInfo portraitHeightInfoPortraitCalculateLayout = oplusQsDetailLayoutHelper.portraitCalculateLayout();
            ViewGroup.LayoutParams layoutParams = getTemporarilyTipsContainer().getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            if (marginLayoutParams != null) {
                marginLayoutParams.height = portraitHeightInfoPortraitCalculateLayout.getTemporarilyContainerHeight();
            }
            View view10 = this.portraitSpaceB;
            ViewGroup.LayoutParams layoutParams2 = view10 != null ? view10.getLayoutParams() : null;
            ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
            if (marginLayoutParams2 != null) {
                marginLayoutParams2.height = portraitHeightInfoPortraitCalculateLayout.getSpaceBHeight();
            }
            View view11 = this.portraitSpaceD;
            ViewGroup.LayoutParams layoutParams3 = view11 != null ? view11.getLayoutParams() : null;
            ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            if (marginLayoutParams3 != null) {
                marginLayoutParams3.height = portraitHeightInfoPortraitCalculateLayout.getSpaceDHeight();
            }
            ViewGroup.LayoutParams layoutParams4 = getSlider().getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams4 = layoutParams4 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams4 : null;
            if (marginLayoutParams4 != null) {
                marginLayoutParams4.height = portraitHeightInfoPortraitCalculateLayout.getSliderHeight();
            }
            this.temporarilyTipsMaxLine = this.locationHelper.getPortraitTemporarilyTipsMaxLine(portraitHeightInfoPortraitCalculateLayout.getTemporarilyContainerHeight());
            getSlider().setSliderHeight(portraitHeightInfoPortraitCalculateLayout.getSliderHeight());
        }
        OplusQSBrightnessVolumeDialogController.onShow.1 r4 = this.listener;
        if (r4 != null) {
            r4.this$0.setExpandSliderTouchDelegate(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.FrameLayout*/.onConfigurationChanged(configuration);
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.configListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
    }

    public final void setHookToggleView(View view) {
        this.hookToggleView = view;
    }

    public final void setListener(OrientationChangedListener orientationChangedListener) {
        this.listener = orientationChangedListener;
    }

    public final void setOnConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.configListener = onConfigurationChangedListener;
    }

    public final void setSlider(OplusQsDetailToggleSliderLayout oplusQsDetailToggleSliderLayout) {
        this.slider = oplusQsDetailToggleSliderLayout;
    }

    public final void setTemporarilyTipsContainer(RelativeLayout relativeLayout) {
        this.temporarilyTipsContainer = relativeLayout;
    }

    public final void setTilesContainer(OplusQsDetailTilesContainer oplusQsDetailTilesContainer) {
        this.tilesContainer = oplusQsDetailTilesContainer;
    }

    public final void setTitleTextView(TextView textView) {
        this.titleTextView = textView;
    }

    public final void showTitle(String str) {
        getTitleTextView().setText(str);
    }
}
