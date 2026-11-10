package com.oplus.systemui.statusbar.phone;

import android.content.res.Configuration;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.systemui.statusbar.data.repository.StatusBarConfigurationController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.statusbar.icon.domain.OplusNotificationIconAreaInteractor;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSideContainerController extends ViewController implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StartSideContainerController";
    private final View.OnApplyWindowInsetsListener applyWindowInsetsListener;
    private final StatusBarConfigurationController configurationController;
    private DisplayCutout displayCutout;
    private final View endSideContainer;
    private final View endSideContent;
    public int iconSize;
    private final View.OnLayoutChangeListener layoutListener;
    private NotificationIconContainer notificationIconContainer;
    private final OplusNotificationIconAreaInteractor oplusNotificationIconAreaInteractor;
    private final View startSideContainer;
    private StatusIconContainer systemIconContainer;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StartSideContainerController(View view, View view2, View view3, StatusBarConfigurationController statusBarConfigurationController, OplusNotificationIconAreaInteractor oplusNotificationIconAreaInteractor) {
        super(view);
        this.startSideContainer = view;
        this.endSideContainer = view2;
        this.endSideContent = view3;
        this.configurationController = statusBarConfigurationController;
        this.oplusNotificationIconAreaInteractor = oplusNotificationIconAreaInteractor;
        this.iconSize = getContext().getResources().getDimensionPixelSize(2131173087);
        StartSideContainerController$layoutListener$1 startSideContainerController$layoutListener$1 = new StartSideContainerController$layoutListener$1();
        startSideContainerController$layoutListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutListener = startSideContainerController$layoutListener$1;
        StartSideContainerController$applyWindowInsetsListener$1 startSideContainerController$applyWindowInsetsListener$1 = new StartSideContainerController$applyWindowInsetsListener$1();
        startSideContainerController$applyWindowInsetsListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.applyWindowInsetsListener = startSideContainerController$applyWindowInsetsListener$1;
    }

    public static final int access$endSideContainerRealWidth(StartSideContainerController startSideContainerController) {
        return (startSideContainerController.endSideContainer.getWidth() - startSideContainerController.endSideContainer.getPaddingEnd()) - startSideContainerController.endSideContainer.getPaddingStart();
    }

    public static final void access$updateNotificationsWidth(StartSideContainerController startSideContainerController, int i) {
        NotificationIconContainer notificationIconContainer = startSideContainerController.notificationIconContainer;
        if (notificationIconContainer != null) {
            int i2 = startSideContainerController.iconSize * i;
            startSideContainerController.oplusNotificationIconAreaInteractor.getMaxIconSize().setValue(Integer.valueOf(i));
            if (i2 != notificationIconContainer.getWidth()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    int width = notificationIconContainer.getWidth();
                    int i3 = startSideContainerController.iconSize;
                    int childCount = notificationIconContainer.getChildCount();
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("OnLayoutChangeListener actualWidth:", i2, width, ", width:", ", iconSize: ");
                    RectList$.ExternalSyntheticOutline0.m(i3, i, ", mMaxStaticIcons: ", " child: ", sbM);
                    MobileInputLogger$.ExternalSyntheticOutline0.m(childCount, "StartSideContainerController-->", "SystemUi--Statusbar", sbM);
                }
                ViewGroup.LayoutParams layoutParams = notificationIconContainer.getLayoutParams();
                layoutParams.width = i2;
                notificationIconContainer.setLayoutParams(layoutParams);
                notificationIconContainer.getParent().requestLayout();
                notificationIconContainer.updateState();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getNotVisibleSystemIconWidth() {
        Sequence<View> children;
        boolean zIsLayoutRtl = this.endSideContainer.isLayoutRtl();
        StatusIconContainer statusIconContainer = this.systemIconContainer;
        int translationX = 0;
        int width = statusIconContainer != null ? statusIconContainer.getWidth() : 0;
        StatusIconContainer statusIconContainer2 = this.systemIconContainer;
        if (statusIconContainer2 != null && (children = ConvenienceExtensionsKt.getChildren(statusIconContainer2)) != null) {
            for (View view : children) {
                if (!zIsLayoutRtl && view.getTranslationX() < translationX) {
                    translationX = (int) view.getTranslationX();
                } else if (zIsLayoutRtl && ((int) view.getTranslationX()) - width > translationX) {
                    translationX = ((int) view.getTranslationX()) - width;
                }
            }
        }
        return Math.abs(translationX);
    }

    public void onConfigChanged(Configuration configuration) {
        if (updateDisplayParameters()) {
            updateStartSideContainerLayout();
        }
        this.iconSize = getContext().getResources().getDimensionPixelSize(2131173087);
    }

    public final void onViewAttached() {
        this.systemIconContainer = this.endSideContent.findViewById(2131365358);
        ConfigurationControllerImpl configurationControllerImpl = this.configurationController;
        configurationControllerImpl.getClass();
        configurationControllerImpl.addCallback(this);
        ((ViewController) this).mView.setOnApplyWindowInsetsListener(this.applyWindowInsetsListener);
        if (updateDisplayParameters()) {
            updateStartSideContainerLayout();
        }
    }

    public final void onViewDetached() {
        ConfigurationControllerImpl configurationControllerImpl = this.configurationController;
        configurationControllerImpl.getClass();
        configurationControllerImpl.removeCallback(this);
        this.endSideContent.removeOnLayoutChangeListener(this.layoutListener);
    }

    public final void setNotificationIConContainer(NotificationIconContainer notificationIconContainer) {
        this.notificationIconContainer = notificationIconContainer;
    }

    public final boolean updateDisplayParameters() {
        if (Intrinsics.areEqual(((ViewController) this).mView.getRootWindowInsets().getDisplayCutout(), this.displayCutout)) {
            return false;
        }
        this.displayCutout = ((ViewController) this).mView.getRootWindowInsets().getDisplayCutout();
        return true;
    }

    public final void updateStartSideContainerLayout() {
        boolean zIsAdaptiveStatusBar = OplusStatusBarUtils.isAdaptiveStatusBar(getContext());
        View view = ((ViewController) this).mView;
        if (zIsAdaptiveStatusBar) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.weight = 0.0f;
            layoutParams.width = -2;
            this.endSideContent.addOnLayoutChangeListener(this.layoutListener);
            return;
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams2.weight = 1.0f;
        layoutParams2.width = 0;
        this.endSideContent.removeOnLayoutChangeListener(this.layoutListener);
    }
}
