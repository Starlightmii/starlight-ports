package com.oplusos.systemui.aod.mediapanel.viewstate;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.systemui.aod.mediapanel.AodMediaControlPanel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaViewStateHolderExpanded extends AodMediaViewStateHolderBase {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AodMediaViewStateHolderExpanded";
    public int actionButtonSize;
    public int actionOplusDrawableSize;
    public int actionOplusHeight;
    public int actionOplusMarginTop;
    public int actionOplusWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getContainerHeight(Context context) {
            return context.getResources().getDimensionPixelSize(2131165525) + context.getResources().getDimensionPixelSize(2131165526) + context.getResources().getDimensionPixelSize(2131165493) + context.getResources().getDimensionPixelSize(2131165491) + context.getResources().getDimensionPixelSize(2131165516) + context.getResources().getDimensionPixelSize(2131165526) + context.getResources().getDimensionPixelSize(2131165525);
        }
    }

    public AodMediaViewStateHolderExpanded(Context context, ConfigurationController configurationController) {
        super(context, configurationController);
        initDimen(getContext());
        initProp(getContext());
    }

    @Override // com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderBase
    public void applyViewState(AodMediaControlPanel aodMediaControlPanel) {
        super.applyViewState(aodMediaControlPanel);
        forceLayoutViewGroup(aodMediaControlPanel);
    }

    @Override // com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderBase
    public void initDimen(Context context) {
        super.initDimen(context);
        setPanelWidth(context.getResources().getDimensionPixelSize(2131165523));
        setAlbumArtWidth(context.getResources().getDimensionPixelSize(2131165516));
        setAlbumArHeight(context.getResources().getDimensionPixelSize(2131165516));
        setPanelMarginVertical(context.getResources().getDimensionPixelSize(2131165526));
        setAppIconMarginBottom(context.getResources().getDimensionPixelSize(2131165520));
        this.actionButtonSize = context.getResources().getDimensionPixelSize(2131165456);
        this.actionOplusDrawableSize = context.getResources().getDimensionPixelSize(2131165492);
        this.actionOplusWidth = context.getResources().getDimensionPixelSize(2131165494);
        this.actionOplusHeight = context.getResources().getDimensionPixelSize(2131165493);
        this.actionOplusMarginTop = context.getResources().getDimensionPixelSize(2131165491);
        setAodBrightNess(1.0f);
    }

    @Override // com.oplusos.systemui.aod.mediapanel.viewstate.AodMediaViewStateHolderBase
    public void initProp(Context context) {
        super.initProp(context);
        for (ViewState viewState : getButtonContainer()) {
            viewState.setProp("PROP_VISIBLE", 0.0f);
            viewState.setProp("PROP_WIDTH", this.actionButtonSize);
            viewState.setProp("PROP_HEIGHT", this.actionButtonSize);
            viewState.setProp("PROP_ALPHA", 1.0f);
            getListAllState().add(viewState);
        }
        for (ViewState viewState2 : getRecommendationIvContainer()) {
            viewState2.setProp("PROP_VISIBLE", 0.0f);
            viewState2.setProp("PROP_WIDTH", this.actionOplusDrawableSize);
            viewState2.setProp("PROP_HEIGHT", this.actionOplusDrawableSize);
            viewState2.setProp("PROP_ALPHA", 1.0f);
            getListAllState().add(viewState2);
        }
        for (ViewState viewState3 : getRecommendationContainer()) {
            viewState3.setProp("PROP_VISIBLE", 0.0f);
            viewState3.setProp("PROP_WIDTH", this.actionOplusWidth);
            viewState3.setProp("PROP_HEIGHT", this.actionOplusHeight);
            if (viewState3.getResId() == 2131361891) {
                viewState3.setProp("PROP_MARGIN_TOP", this.actionOplusMarginTop);
            }
            viewState3.setProp("PROP_ALPHA", 1.0f);
            getListAllState().add(viewState3);
        }
    }
}
