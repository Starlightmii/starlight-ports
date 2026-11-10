package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.res.ColorStateList;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModel;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbstractOplusStatusBarMobileViewBinder$Binding {
    private final ImageView dataActivity;
    private final ImageView iconView;
    private final ViewGroup mobileComboReal;
    private final ViewGroup mobileGroupView;
    private final OplusMobileIconViewModel oplusViewModel;
    private final Space roamingSpace;
    private final ViewGroup viewGroup;
    private final LocationBasedMobileViewModel viewModel;
    private final ImageView volteView;

    public AbstractOplusStatusBarMobileViewBinder$Binding(AbstractOplusStatusBarMobileViewBinder abstractOplusStatusBarMobileViewBinder, ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, OplusMobileIconViewModel oplusMobileIconViewModel) {
        this.viewGroup = viewGroup;
        this.viewModel = locationBasedMobileViewModel;
        this.oplusViewModel = oplusMobileIconViewModel;
        this.mobileGroupView = (ViewGroup) viewGroup.requireViewById(2131363971);
        this.mobileComboReal = (ViewGroup) viewGroup.requireViewById(2131363969);
        this.iconView = (ImageView) viewGroup.requireViewById(2131363980);
        this.dataActivity = (ImageView) viewGroup.findViewById(2131362782);
        this.volteView = (ImageView) viewGroup.requireViewById(2131363992);
        this.roamingSpace = (Space) viewGroup.findViewById(2131363977);
    }

    public abstract void bindSubclassFlows(CoroutineScope coroutineScope);

    public final ImageView getDataActivity() {
        return this.dataActivity;
    }

    public final ImageView getIconView() {
        return this.iconView;
    }

    public final ViewGroup getMobileComboReal() {
        return this.mobileComboReal;
    }

    public final ViewGroup getMobileGroupView() {
        return this.mobileGroupView;
    }

    public final OplusMobileIconViewModel getOplusViewModel() {
        return this.oplusViewModel;
    }

    public final Space getRoamingSpace() {
        return this.roamingSpace;
    }

    public final ViewGroup getViewGroup() {
        return this.viewGroup;
    }

    public final LocationBasedMobileViewModel getViewModel() {
        return this.viewModel;
    }

    public final ImageView getVolteView() {
        return this.volteView;
    }

    public void initViews(int i) {
        updateSignalIcon((SignalIconModel) this.oplusViewModel.getIcon().getValue());
        updateDataActivity(((Number) this.oplusViewModel.getMobileActivityResId().getValue()).intValue());
        updateTint(i);
    }

    public final void updateDataActivity(int i) {
        if (i <= 0) {
            i = 0;
        }
        ImageView imageView = this.dataActivity;
        if (i <= 0) {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        } else {
            if (imageView != null) {
                imageView.setImageResource(i);
            }
            ImageView imageView2 = this.dataActivity;
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
        }
    }

    public void updateSignalIcon(SignalIconModel signalIconModel) {
        if (!(signalIconModel instanceof SignalIconModel.CellularTypeIconModel.Cellular)) {
            if (signalIconModel instanceof SignalIconModel.Satellite) {
                IconViewBinder.INSTANCE.bind(((SignalIconModel.Satellite) signalIconModel).getIcon(), this.iconView);
            }
        } else {
            int signalDrawableState = ((SignalIconModel.CellularTypeIconModel.Cellular) signalIconModel).toSignalDrawableState();
            if (signalDrawableState > 0) {
                this.iconView.setImageResource(signalDrawableState);
            }
        }
    }

    public void updateTint(int i) {
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        this.iconView.setImageTintList(colorStateListValueOf);
        ImageView imageView = this.dataActivity;
        if (imageView != null) {
            imageView.setImageTintList(colorStateListValueOf);
        }
        this.volteView.setImageTintList(colorStateListValueOf);
    }
}
