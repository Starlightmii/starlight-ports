package com.oplusos.systemui.aod.mediapanel.viewstate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout;
import com.oplus.systemui.aod.utils.AodUtils;
import com.oplus.systemui.notification.mediapanel.widget.AodMediaPanelView;
import com.oplusos.systemui.aod.mediapanel.AodMediaControlPanel;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AodMediaViewStateHolderBase {
    private final ViewState action0IvViewState;
    private final ViewState action0ViewState;
    private final ViewState action1IvViewState;
    private final ViewState action1ViewState;
    private final ViewState action2IvViewState;
    private final ViewState action2ViewState;
    private final ViewState action3IvViewState;
    private final ViewState action3ViewState;
    private final ViewState actionLikeViewState;
    private final ViewState actionNextViewState;
    private final ViewState actionPlayViewState;
    private final ViewState actionPreViewState;
    public int albumArHeight;
    private final ViewState albumArtState;
    public int albumArtWidth;
    public int alumArtMarginBottom;
    public int alumArtMarginTop;
    public float aodBrightNess;
    public int appIconMarginBottom;
    private final ViewState appIconState;
    private final ViewState artistTextState;
    private final List<ViewState> buttonContainer;
    private final configChangeListener.1 configChangeListener;
    public Context context;
    private final ArrayList<ViewState> listAllState;
    private final List<ViewState> listMain;
    public int panelMarginVertical;
    public int panelWidth;
    private final ViewState playerContainer = new ViewState(2131363927);
    private final List<ViewState> recommendationContainer;
    private final List<ViewState> recommendationIvContainer;
    public boolean shouldReloadRes;
    public int titleMarginTop;
    private final ViewState titleTextState;

    public AodMediaViewStateHolderBase(Context context, ConfigurationController configurationController) {
        this.context = AodUtils.getFixContext(context);
        ViewState viewState = new ViewState(2131362003);
        this.albumArtState = viewState;
        ViewState viewState2 = new ViewState(2131363381);
        this.appIconState = viewState2;
        ViewState viewState3 = new ViewState(2131363345);
        this.titleTextState = viewState3;
        ViewState viewState4 = new ViewState(2131363335);
        this.artistTextState = viewState4;
        this.listMain = CollectionsKt__CollectionsKt.listOf(new ViewState[]{viewState, viewState2, viewState3, viewState4});
        this.listAllState = new ArrayList<>();
        ViewState viewState5 = new ViewState(2131361891);
        this.action0ViewState = viewState5;
        ViewState viewState6 = new ViewState(2131361894);
        this.action1ViewState = viewState6;
        ViewState viewState7 = new ViewState(2131361897);
        this.action2ViewState = viewState7;
        ViewState viewState8 = new ViewState(2131361900);
        this.action3ViewState = viewState8;
        this.recommendationContainer = CollectionsKt__CollectionsKt.listOf(new ViewState[]{viewState5, viewState6, viewState7, viewState8});
        ViewState viewState9 = new ViewState(2131361892);
        this.action0IvViewState = viewState9;
        ViewState viewState10 = new ViewState(2131361895);
        this.action1IvViewState = viewState10;
        ViewState viewState11 = new ViewState(2131361898);
        this.action2IvViewState = viewState11;
        ViewState viewState12 = new ViewState(2131361901);
        this.action3IvViewState = viewState12;
        this.recommendationIvContainer = CollectionsKt__CollectionsKt.listOf(new ViewState[]{viewState9, viewState10, viewState11, viewState12});
        ViewState viewState13 = new ViewState(2131361908);
        this.actionPlayViewState = viewState13;
        ViewState viewState14 = new ViewState(2131361907);
        this.actionNextViewState = viewState14;
        ViewState viewState15 = new ViewState(2131361909);
        this.actionPreViewState = viewState15;
        ViewState viewState16 = new ViewState(2131363729);
        this.actionLikeViewState = viewState16;
        this.buttonContainer = CollectionsKt__CollectionsKt.listOf(new ViewState[]{viewState13, viewState14, viewState15, viewState16});
        configChangeListener.1 r5 = new configChangeListener.1();
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        this.configChangeListener = r5;
        configurationController.addCallback(r5);
    }

    public final void addAodClockView(AodClockLayout aodClockLayout) {
        this.playerContainer.setExtraView(aodClockLayout);
    }

    public void applyViewState(AodMediaControlPanel aodMediaControlPanel) {
        Iterator<T> it = this.listMain.iterator();
        while (it.hasNext()) {
            ((ViewState) it.next()).applyStateToView(aodMediaControlPanel);
        }
        Iterator<T> it2 = this.buttonContainer.iterator();
        while (it2.hasNext()) {
            ((ViewState) it2.next()).applyStateToView(aodMediaControlPanel);
        }
        Iterator<T> it3 = this.recommendationIvContainer.iterator();
        while (it3.hasNext()) {
            ((ViewState) it3.next()).applyStateToView(aodMediaControlPanel);
        }
        Iterator<T> it4 = this.recommendationContainer.iterator();
        while (it4.hasNext()) {
            ((ViewState) it4.next()).applyStateToView(aodMediaControlPanel);
        }
    }

    public final void forceLayoutViewGroup(AodMediaControlPanel aodMediaControlPanel) {
        AodMediaPanelView playerView;
        AodMediaPanelView playerView2;
        if (aodMediaControlPanel != null && (playerView2 = aodMediaControlPanel.getPlayerView()) != null) {
            playerView2.forceLayout();
        }
        if (((aodMediaControlPanel == null || (playerView = aodMediaControlPanel.getPlayerView()) == null) ? null : playerView.getParent()) instanceof ViewGroup) {
            AodMediaPanelView playerView3 = aodMediaControlPanel.getPlayerView();
            ((ViewGroup) (playerView3 != null ? playerView3.getParent() : null)).forceLayout();
        }
    }

    public final ViewState getAction0IvViewState() {
        return this.action0IvViewState;
    }

    public final ViewState getAction0ViewState() {
        return this.action0ViewState;
    }

    public final ViewState getAction1IvViewState() {
        return this.action1IvViewState;
    }

    public final ViewState getAction1ViewState() {
        return this.action1ViewState;
    }

    public final ViewState getAction2IvViewState() {
        return this.action2IvViewState;
    }

    public final ViewState getAction2ViewState() {
        return this.action2ViewState;
    }

    public final ViewState getAction3IvViewState() {
        return this.action3IvViewState;
    }

    public final ViewState getAction3ViewState() {
        return this.action3ViewState;
    }

    public final ViewState getActionLikeViewState() {
        return this.actionLikeViewState;
    }

    public final ViewState getActionNextViewState() {
        return this.actionNextViewState;
    }

    public final ViewState getActionPlayViewState() {
        return this.actionPlayViewState;
    }

    public final ViewState getActionPreViewState() {
        return this.actionPreViewState;
    }

    public final int getAlbumArHeight() {
        return this.albumArHeight;
    }

    public final ViewState getAlbumArtState() {
        return this.albumArtState;
    }

    public final int getAlbumArtWidth() {
        return this.albumArtWidth;
    }

    public final int getAlumArtMarginBottom() {
        return this.alumArtMarginBottom;
    }

    public final int getAlumArtMarginTop() {
        return this.alumArtMarginTop;
    }

    public final float getAodBrightNess() {
        return this.aodBrightNess;
    }

    public final int getAppIconMarginBottom() {
        return this.appIconMarginBottom;
    }

    public final ViewState getAppIconState() {
        return this.appIconState;
    }

    public final ViewState getArtistTextState() {
        return this.artistTextState;
    }

    public final List<ViewState> getButtonContainer() {
        return this.buttonContainer;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ArrayList<ViewState> getListAllState() {
        return this.listAllState;
    }

    public final List<ViewState> getListMain() {
        return this.listMain;
    }

    public final int getPanelMarginVertical() {
        return this.panelMarginVertical;
    }

    public final int getPanelWidth() {
        return this.panelWidth;
    }

    public final ViewState getPlayerContainer() {
        return this.playerContainer;
    }

    public final List<ViewState> getRecommendationContainer() {
        return this.recommendationContainer;
    }

    public final List<ViewState> getRecommendationIvContainer() {
        return this.recommendationIvContainer;
    }

    public final boolean getShouldReloadRes() {
        return this.shouldReloadRes;
    }

    public final ArrayList<ViewState> getStateList() {
        return this.listAllState;
    }

    public final int getTitleMarginTop() {
        return this.titleMarginTop;
    }

    public final ViewState getTitleTextState() {
        return this.titleTextState;
    }

    public void initDimen(Context context) {
    }

    public void initProp(Context context) {
        this.albumArtState.setProp("PROP_WIDTH", this.albumArtWidth).setProp("PROP_HEIGHT", this.albumArHeight).setProp("PROP_MARGIN_TOP", this.alumArtMarginTop).setProp("PROP_MARGIN_BOTTOM", this.alumArtMarginBottom);
        this.playerContainer.setProp("PROP_WIDTH", this.panelWidth).setProp("PROP_BRIGHT_NESS", this.aodBrightNess).setProp("PROP_MARGIN_TOP", this.panelMarginVertical).setProp("PROP_MARGIN_BOTTOM", this.panelMarginVertical);
        this.appIconState.setProp("PROP_MARGIN_BOTTOM", this.appIconMarginBottom);
        this.titleTextState.setProp("PROP_MARGIN_TOP", this.titleMarginTop);
        this.listAllState.add(this.playerContainer);
        this.listAllState.addAll(this.listMain);
    }

    public final void removeAodClickLayout() {
        this.playerContainer.setExtraView((View) null);
    }

    public final void setAlbumArHeight(int i) {
        this.albumArHeight = i;
    }

    public final void setAlbumArtWidth(int i) {
        this.albumArtWidth = i;
    }

    public final void setAlumArtMarginBottom(int i) {
        this.alumArtMarginBottom = i;
    }

    public final void setAlumArtMarginTop(int i) {
        this.alumArtMarginTop = i;
    }

    public final void setAodBrightNess(float f) {
        this.aodBrightNess = f;
    }

    public final void setAppIconMarginBottom(int i) {
        this.appIconMarginBottom = i;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setPanelMarginVertical(int i) {
        this.panelMarginVertical = i;
    }

    public final void setPanelWidth(int i) {
        this.panelWidth = i;
    }

    public final void setShouldReloadRes(boolean z) {
        this.shouldReloadRes = z;
    }

    public final void setTitleMarginTop(int i) {
        this.titleMarginTop = i;
    }

    public final void transitionStateTo(AodMediaControlPanel aodMediaControlPanel, AodMediaViewStateHolderBase aodMediaViewStateHolderBase, float f) {
        ArrayList<ViewState> arrayList = this.listAllState;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            ViewState viewState = arrayList.get(i);
            i++;
            ViewState viewState2 = viewState;
            ArrayList<ViewState> arrayList2 = aodMediaViewStateHolderBase.listAllState;
            int size2 = arrayList2.size();
            int i2 = 0;
            while (i2 < size2) {
                ViewState viewState3 = arrayList2.get(i2);
                i2++;
                ViewState viewState4 = viewState3;
                if (viewState2.isSameElement(viewState4)) {
                    viewState2.transitionStateTo(aodMediaControlPanel, viewState4, f);
                }
            }
        }
    }
}
