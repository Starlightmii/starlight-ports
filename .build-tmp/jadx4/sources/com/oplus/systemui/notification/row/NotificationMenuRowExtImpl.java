package com.oplus.systemui.notification.row;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.math.MathUtils;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.NotificationMenuRowExt;
import com.android.systemui.statusbar.notification.row.NotificationSnooze;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.navigationbar.gesture.VibrationHelper;
import com.oplus.systemui.notification.blur.NotificationPlatFormBlurParamsManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.swipe.OplusSwipeHelperExImpl;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomMenuRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.MaskBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.FlavorZeroFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowExtImpl implements NotificationMenuRowExt {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationMenuRowExtImpl";
    private final Lazy animatorDismiss$delegate;
    private final Context context;
    private NotificationMenuRowPlugin.MenuItem deleteItem;
    private final Lazy materialColorManager$delegate;
    private final Lazy menuItemAnimator$delegate;
    private NotificationMenuRowPlugin menuRow;
    private final NotificationMenuRowItemViewParam menuRowItemViewParam;
    private NotificationMenuRowMetaBallController metaBallController;
    private final Lazy notificationMaterialColorEx$delegate;
    private ArrayList<NotificationMenuRowPlugin.MenuItem> rightMenuItems;
    private NotificationMenuRowPlugin.MenuItem settingsItem;
    public boolean showingDelete;
    private final Lazy statusBarStateController$delegate;
    private final Lazy viewBlurManager$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Integer $r8$lambda$lNWbyGxQqUgPPvcuHCPEElu3Y3I(NotificationMenuRowExtImpl notificationMenuRowExtImpl) {
        ExpandableView menuRowParent = notificationMenuRowExtImpl.getMenuRowParent();
        if (menuRowParent != null) {
            return Integer.valueOf(menuRowParent.getActualHeight());
        }
        return null;
    }

    public static NotificationMenuRowItemAnimator $r8$lambda$w8L7FI5m8wA1KbM6MJKmx_RIQ6I(NotificationMenuRowExtImpl notificationMenuRowExtImpl) {
        NotificationMenuRowExtImpl$menuItemAnimator$2$1 notificationMenuRowExtImpl$menuItemAnimator$2$1 = new NotificationMenuRowExtImpl$menuItemAnimator$2$1();
        notificationMenuRowExtImpl$menuItemAnimator$2$1.this$0 = notificationMenuRowExtImpl;
        VarHandle.storeStoreFence();
        return new NotificationMenuRowItemAnimator(notificationMenuRowExtImpl$menuItemAnimator$2$1, notificationMenuRowExtImpl.context);
    }

    public NotificationMenuRowExtImpl(Context context) {
        this.context = context;
        final int i = 0;
        NotificationMenuRowExtImpl$$ExternalSyntheticLambda1 notificationMenuRowExtImpl$$ExternalSyntheticLambda1 = new NotificationMenuRowExtImpl$$ExternalSyntheticLambda1(i);
        notificationMenuRowExtImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.menuRowItemViewParam = new NotificationMenuRowItemViewParam(context, notificationMenuRowExtImpl$$ExternalSyntheticLambda1);
        this.statusBarStateController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.row.NotificationMenuRowExtImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                    case 1:
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 2:
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 3:
                        return (NotificationMaterialColorEx) DependencyEx.sDependency.getDependency(NotificationMaterialColorEx.class);
                    default:
                        return new NotificationMenuRowItemAnimatorDismiss();
                }
            }
        });
        final int i2 = 1;
        this.viewBlurManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.row.NotificationMenuRowExtImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                    case 1:
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 2:
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 3:
                        return (NotificationMaterialColorEx) DependencyEx.sDependency.getDependency(NotificationMaterialColorEx.class);
                    default:
                        return new NotificationMenuRowItemAnimatorDismiss();
                }
            }
        });
        final int i3 = 2;
        this.materialColorManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.row.NotificationMenuRowExtImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                    case 1:
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 2:
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 3:
                        return (NotificationMaterialColorEx) DependencyEx.sDependency.getDependency(NotificationMaterialColorEx.class);
                    default:
                        return new NotificationMenuRowItemAnimatorDismiss();
                }
            }
        });
        final int i4 = 3;
        this.notificationMaterialColorEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.row.NotificationMenuRowExtImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                    case 1:
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 2:
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 3:
                        return (NotificationMaterialColorEx) DependencyEx.sDependency.getDependency(NotificationMaterialColorEx.class);
                    default:
                        return new NotificationMenuRowItemAnimatorDismiss();
                }
            }
        });
        NotificationMenuRowExtImpl$$ExternalSyntheticLambda1 notificationMenuRowExtImpl$$ExternalSyntheticLambda2 = new NotificationMenuRowExtImpl$$ExternalSyntheticLambda1(i2);
        notificationMenuRowExtImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.menuItemAnimator$delegate = LazyKt__LazyJVMKt.lazy(notificationMenuRowExtImpl$$ExternalSyntheticLambda2);
        final int i5 = 4;
        this.animatorDismiss$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.row.NotificationMenuRowExtImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
                    case 1:
                        return (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
                    case 2:
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 3:
                        return (NotificationMaterialColorEx) DependencyEx.sDependency.getDependency(NotificationMaterialColorEx.class);
                    default:
                        return new NotificationMenuRowItemAnimatorDismiss();
                }
            }
        });
    }

    private final NotificationMenuRowItemAnimatorDismiss getAnimatorDismiss() {
        return (NotificationMenuRowItemAnimatorDismiss) this.animatorDismiss$delegate.getValue();
    }

    private final MaterialColorManager getMaterialColorManager() {
        return (MaterialColorManager) this.materialColorManager$delegate.getValue();
    }

    private final NotificationMenuRowItemAnimator getMenuItemAnimator() {
        return (NotificationMenuRowItemAnimator) this.menuItemAnimator$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ExpandableView getMenuRowParent() {
        NotificationMenuRow notificationMenuRow = this.menuRow;
        if (notificationMenuRow instanceof NotificationMenuRow) {
            return notificationMenuRow.getParent();
        }
        if (notificationMenuRow instanceof OplusCustomMenuRow) {
            return ((OplusCustomMenuRow) notificationMenuRow).getParent();
        }
        return null;
    }

    private final NotificationMenuRow getMenuRowParentAsNotificationMenuRow() {
        NotificationMenuRow notificationMenuRow = this.menuRow;
        if (notificationMenuRow instanceof NotificationMenuRow) {
            return notificationMenuRow;
        }
        return null;
    }

    private final NotificationMaterialColorEx getNotificationMaterialColorEx() {
        return (NotificationMaterialColorEx) this.notificationMaterialColorEx$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSHorizMarginForIcon() {
        return this.menuRowItemViewParam.getIconMargin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSHorizMarginForIconExtra() {
        return this.menuRowItemViewParam.getIconMarginExtra();
    }

    private final Size getSIconSize() {
        return this.menuRowItemViewParam.getIconSize(false);
    }

    private final StatusBarStateController getStatusBarStateController() {
        return (StatusBarStateController) this.statusBarStateController$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewBlurManager getViewBlurManager() {
        return (ViewBlurManager) this.viewBlurManager$delegate.getValue();
    }

    public final int calLeftOffset(int i) {
        return ((getSIconSize().getWidth() + getSHorizMarginForIcon()) * i) + getSHorizMarginForIconExtra();
    }

    public final int calRightOffset(int i, int i2) {
        return ((i - ((i2 + 1) * getSIconSize().getWidth())) - (getSHorizMarginForIcon() * i2)) - getSHorizMarginForIconExtra();
    }

    public NotificationMenuRow.NotificationMenuItem createConversationItemExt(Context context) {
        if (!FlavorZeroFeatureOption.isFlavorZeroDevice()) {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (!FlavorTwoFeatureOption.isFlavorTwoDeviceCN()) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (!FlavorOneFeatureOption.isFlavorOneDevice() && !FlavorZeroFeatureOption.isFlavorZeroLightOsDevice() && !FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                    return null;
                }
            }
        }
        return NotificationMenuRowItemCreator.createOplusItem(context, 2131954896, -1, -1, 2);
    }

    public NotificationMenuRow.NotificationMenuItem createInfoItem(Context context) {
        if (!FlavorZeroFeatureOption.isFlavorZeroDevice()) {
            FlavorTwoFeatureOption.INSTANCE.getClass();
            if (!FlavorTwoFeatureOption.isFlavorTwoDeviceCN()) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (!FlavorOneFeatureOption.isFlavorOneDevice() && !FlavorZeroFeatureOption.isFlavorZeroLightOsDevice() && !FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                    return null;
                }
            }
        }
        return NotificationMenuRowItemCreator.createOplusItem(context, 2131954896, -1, -1, 1);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x009b  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f9  */
    public void createMenuViewsExt(boolean z, NotificationMenuRowPlugin notificationMenuRowPlugin, ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList, Context context, boolean z2, boolean z3) {
        NotificationMenuRowProvider oplusCustomRowMenuRowProvider;
        NotificationMenuRow.NotificationMenuItem notificationMenuItemCreateImageItem;
        NotificationMenuRowPlugin.MenuItem menuItemCreateImageItem;
        ExpandableNotificationRow parent;
        NotificationEntry entry;
        StatusBarNotification sbn;
        arrayList.remove(notificationMenuRowPlugin.getLongpressMenuItem(context));
        this.rightMenuItems = arrayList;
        this.menuRow = notificationMenuRowPlugin;
        ExpandableNotificationRow menuRowParent = getMenuRowParent();
        NotificationMenuRow.NotificationMenuItem notificationMenuItem = null;
        if (menuRowParent instanceof ExpandableNotificationRow) {
            oplusCustomRowMenuRowProvider = new ExpandableNotificationRowMenuRowProvider(menuRowParent);
        } else {
            oplusCustomRowMenuRowProvider = menuRowParent instanceof OplusCustomRow ? new OplusCustomRowMenuRowProvider((OplusCustomRow) menuRowParent) : null;
        }
        if (oplusCustomRowMenuRowProvider != null) {
            this.metaBallController = new NotificationMenuRowMetaBallController(oplusCustomRowMenuRowProvider, context);
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            NotificationCenterHelper notificationCenterHelper = (NotificationCenterHelper) DependencyEx.sDependency.getDependency(NotificationCenterHelper.class);
            NotificationMenuRow menuRowParentAsNotificationMenuRow = getMenuRowParentAsNotificationMenuRow();
            if (!notificationCenterHelper.isNoPopmenuByPkg(context, (menuRowParentAsNotificationMenuRow == null || (parent = menuRowParentAsNotificationMenuRow.getParent()) == null || (entry = parent.getEntry()) == null || (sbn = entry.getSbn()) == null) ? null : sbn.getPackageName())) {
                if (z2) {
                    if (FlavorZeroFeatureOption.isFlavorZeroDevice() || FlavorZeroFeatureOption.isFlavorZeroLightOsDevice()) {
                        menuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954970, 2131235723, 0);
                    } else {
                        FlavorTwoFeatureOption.INSTANCE.getClass();
                        if (FlavorTwoFeatureOption.isFlavorTwoDeviceCN()) {
                            menuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954970, 2131235723, 0);
                        } else {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (FlavorOneFeatureOption.isFlavorOneDevice() || FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                                menuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954970, 2131235723, 0);
                            } else {
                                menuItemCreateImageItem = null;
                            }
                        }
                    }
                    if (menuItemCreateImageItem != null) {
                        arrayList.addFirst(menuItemCreateImageItem);
                    } else {
                        menuItemCreateImageItem = null;
                    }
                    this.settingsItem = menuItemCreateImageItem;
                }
                this.showingDelete = z3;
                if (z3) {
                    if (NotificationMenuRowItemResourceHelperKt.shouldShowDeleteAnimation(context)) {
                        NotificationMenuRowExtImpl$$ExternalSyntheticLambda1 notificationMenuRowExtImpl$$ExternalSyntheticLambda1 = new NotificationMenuRowExtImpl$$ExternalSyntheticLambda1(2);
                        notificationMenuRowExtImpl$$ExternalSyntheticLambda1.f$0 = context;
                        VarHandle.storeStoreFence();
                        notificationMenuItemCreateImageItem = NotificationMenuRowItemCreator.createLottieItem(context, 2131954895, notificationMenuRowExtImpl$$ExternalSyntheticLambda1, 3);
                    } else if (FlavorZeroFeatureOption.isFlavorZeroDevice() || FlavorZeroFeatureOption.isFlavorZeroLightOsDevice()) {
                        notificationMenuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954895, 2131233815, 3);
                    } else {
                        FlavorTwoFeatureOption.INSTANCE.getClass();
                        if (FlavorTwoFeatureOption.isFlavorTwoDeviceCN()) {
                            notificationMenuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954895, 2131233815, 3);
                        } else {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (FlavorOneFeatureOption.isFlavorOneDevice() || FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
                                notificationMenuItemCreateImageItem = NotificationMenuRowItemCreator.createImageItem(context, 2131954895, 2131233815, 3);
                            } else {
                                notificationMenuItemCreateImageItem = null;
                            }
                        }
                    }
                    if (notificationMenuItemCreateImageItem != null) {
                        arrayList.addFirst(notificationMenuItemCreateImageItem);
                        notificationMenuItem = notificationMenuItemCreateImageItem;
                    }
                    this.deleteItem = notificationMenuItem;
                    return;
                }
                return;
            }
        }
        arrayList.clear();
    }

    public final Context getContext() {
        return this.context;
    }

    public float getDismissThreshold(View view, ViewGroup viewGroup, int i) {
        if (view == null) {
            return 0.0f;
        }
        float width = view.getWidth();
        return MathUtils.clamp(((OplusSwipeHelperExImpl) ((OplusSwipeHelperEx) DependencyEx.sDependency.getDependency(OplusSwipeHelperEx.class))).getSwipeFarEnoughFraction(width) * width, (0.1f * width) + getSpaceForMenu(viewGroup, i), width);
    }

    public Size getIconSize(int i) {
        return getSIconSize();
    }

    public final Drawable getMenuItemBackground(Resources resources, View view) {
        ViewBlurProxy.BlurType blurType;
        Drawable drawable = resources.getDrawable(2131235695, this.context.getTheme());
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                if (ScrimUtil.isBAnimLevel() || (ScrimUtil.isBPAnimLevel() && getViewBlurManager().isKeyguardMaterialColorState())) {
                    int currentButtonMaterialColor = getMaterialColorManager().getCurrentButtonMaterialColor();
                    if (currentButtonMaterialColor != 0) {
                        drawable.setTint(currentButtonMaterialColor);
                    }
                    drawable.getClass();
                    return drawable;
                }
                drawable.getClass();
                ViewBlurProxy viewBlurProxyRequireBlurProxyForView = getViewBlurManager().requireBlurProxyForView(view, ViewBlurManager.CardType.MENU);
                if (ScrimUtil.isBPAnimLevel()) {
                    blurType = ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
                } else {
                    blurType = GaussBlurUtils.isGaussBlurDisabled() ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
                }
                viewBlurProxyRequireBlurProxyForView.setBlurType(blurType);
                if (Intrinsics.areEqual(viewBlurProxyRequireBlurProxyForView.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                    NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
                    notificationPlatFormBlurParamsManager.updateGradientStrokeLineParamsForNotificationSize(this.context, viewBlurProxyRequireBlurProxyForView.getBlurConfig().getGradientStrokeLineParam(), view.getWidth(), view.getHeight(), (NotificationBlurMode) getNotificationMaterialColorEx().getBlurMode().getValue());
                    notificationPlatFormBlurParamsManager.updateInnerShadowParamsForNotificationSize(this.context, viewBlurProxyRequireBlurProxyForView.getBlurConfig().getInnerShadowParams(), view.getWidth(), view.getHeight(), (NotificationBlurMode) getNotificationMaterialColorEx().getBlurMode().getValue());
                }
                viewBlurProxyRequireBlurProxyForView.applyBlurConfig();
                return new MaskBlurDrawable(viewBlurProxyRequireBlurProxyForView, drawable, 0);
            }
        }
        drawable.setTintList(null);
        return drawable;
    }

    public final boolean getNewFlowHideShelf(int i) {
        if (i == 1) {
            return this.context.getResources().getBoolean(2131034281);
        }
        return i == -1;
    }

    public boolean getShowOnLeft(View view, float f) {
        return view != null && view.getLayoutDirection() == 1;
    }

    public int getSpaceForMenu(ViewGroup viewGroup, int i) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return 0;
        }
        return (viewGroup.getChildCount() * (getSIconSize().getWidth() + getSHorizMarginForIcon())) + getSHorizMarginForIconExtra();
    }

    public NotificationSnooze inflaterNotificationSnooze(Context context) {
        return LayoutInflater.from(context).inflate(2131559224, (ViewGroup) null, false);
    }

    public boolean isHeadsUpAnimatingAway(ExpandableNotificationRow expandableNotificationRow) {
        if (expandableNotificationRow != null) {
            return expandableNotificationRow.mHeadsupDisappearRunning;
        }
        return false;
    }

    public boolean isParentSnapped(ExpandableNotificationRow expandableNotificationRow) {
        return (expandableNotificationRow != null ? expandableNotificationRow.getTranslation() : 0.0f) < 0.0f;
    }

    public /* bridge */ /* synthetic */ boolean isShowNotificationSnooze(NotificationMenuRow notificationMenuRow) {
        return super.isShowNotificationSnooze(notificationMenuRow);
    }

    public boolean isSingleLineType(ExpandableNotificationRow expandableNotificationRow) {
        NotificationContentView privateLayout = expandableNotificationRow.getPrivateLayout();
        return privateLayout != null && privateLayout.getVisibleType() == 3;
    }

    public boolean isSwipeToEnd(float f, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        View menuView = notificationMenuRowPlugin.getMenuView();
        if (menuView != null) {
            return NotificationMenuRowItemCreator.isSwipeToEnd(f, menuView);
        }
        return false;
    }

    public boolean isSysUIHighPriority(boolean z, NotificationMenuRow notificationMenuRow, NotificationEntry notificationEntry) {
        OplusNotificationEntryEx ex;
        if (notificationEntry == null || (ex = notificationEntry.getEx()) == null || !((OplusNotificationEntryExImpl) ex).isSysUIHighPriority) {
            return false;
        }
        notificationMenuRow.populateMenuViews();
        if (z) {
            notificationMenuRow.resetState(false);
            return true;
        }
        notificationMenuRow.mIconsPlaced = false;
        notificationMenuRow.setMenuLocation();
        if (!notificationMenuRow.mIsUserTouching) {
            notificationMenuRow.onSnapOpen();
        }
        return true;
    }

    public final void onConfigChange() {
        ExpandableView menuRowParent = getMenuRowParent();
        if (menuRowParent != null) {
            if (!menuRowParent.isLaidOut() || menuRowParent.isLayoutRequested()) {
                NotificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1 notificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1 = new NotificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1();
                notificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1.this$0 = this;
                VarHandle.storeStoreFence();
                menuRowParent.addOnLayoutChangeListener(notificationMenuRowExtImpl$onConfigChange$$inlined$doOnLayout$1);
            } else {
                NotificationMenuRowMetaBallController notificationMenuRowMetaBallController = this.metaBallController;
                if (notificationMenuRowMetaBallController != null) {
                    notificationMenuRowMetaBallController.updateMetaBallPosition(notificationMenuRowMetaBallController.getLastTranslationX());
                }
            }
        }
        NotificationMenuRowItemAnimator menuItemAnimator = getMenuItemAnimator();
        if (menuItemAnimator != null) {
            menuItemAnimator.onConfigChange();
        }
    }

    public final void onDismissRow() {
        View menuView;
        NotificationMenuRowPlugin.MenuItem menuItem;
        View menuView2;
        NotificationMenuRowPlugin.MenuItem menuItem2 = this.deleteItem;
        if (menuItem2 == null || (menuView = menuItem2.getMenuView()) == null || (menuItem = this.settingsItem) == null || (menuView2 = menuItem.getMenuView()) == null) {
            return;
        }
        NotificationMenuRowItemAnimator menuItemAnimator = getMenuItemAnimator();
        if (menuItemAnimator != null) {
            menuItemAnimator.setDismiss();
        }
        getAnimatorDismiss().startDismissRowAnimator(menuView, menuView2);
    }

    public void onParentHeightUpdate(ExpandableView expandableView, ViewGroup viewGroup) {
        super.onParentHeightUpdate(expandableView, viewGroup);
        int actualHeight = expandableView.getActualHeight();
        Size iconSize = this.menuRowItemViewParam.getIconSize(true);
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.menuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.isMenuVisible()) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams.width != iconSize.getWidth() || layoutParams.height != iconSize.getHeight()) {
                    layoutParams.width = iconSize.getWidth();
                    layoutParams.height = iconSize.getHeight();
                    childAt.setLayoutParams(layoutParams);
                }
            }
        }
        viewGroup.setTranslationY(actualHeight < expandableView.getResources().getDimensionPixelSize(2131170724) ? (actualHeight - iconSize.getHeight()) / 2.0f : expandableView.getResources().getDimensionPixelSize(2131170726));
    }

    public void performHapticFeedback(View view) {
        VibrationHelper.getInstance(this.context).onPerformHapticFeedback();
    }

    public void resetState(FrameLayout frameLayout, Map<View, ? extends NotificationMenuRowPlugin.MenuItem> map) {
        getMenuItemAnimator().reset();
        NotificationMenuRowPlugin.MenuItem menuItem = this.deleteItem;
        View menuView = menuItem != null ? menuItem.getMenuView() : null;
        IOplusMenuRowItemView iOplusMenuRowItemView = menuView instanceof IOplusMenuRowItemView ? (IOplusMenuRowItemView) menuView : null;
        if (iOplusMenuRowItemView != null) {
            iOplusMenuRowItemView.resetState();
        }
        ExpandableView menuRowParent = getMenuRowParent();
        float translationX = menuRowParent != null ? menuRowParent.getTranslationX() : 0.0f;
        NotificationMenuRowMetaBallController notificationMenuRowMetaBallController = this.metaBallController;
        if (notificationMenuRowMetaBallController != null) {
            notificationMenuRowMetaBallController.updateMetaBallPosition(translationX);
        }
        NotificationMenuRowMetaBallController notificationMenuRowMetaBallController2 = this.metaBallController;
        if (notificationMenuRowMetaBallController2 != null) {
            notificationMenuRowMetaBallController2.clearAllMetaBalls();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setMenuChildrenTranslation(float f, float f2, ViewGroup viewGroup, View view, Map<View, ? extends NotificationMenuRowPlugin.MenuItem> map, int i) {
        NotificationBackgroundViewExt ext;
        ViewBlurProxy viewBlurProxy;
        Drawable blurDrawable;
        MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable;
        NotificationBackgroundViewExt ext2;
        ViewBlurProxy viewBlurProxy2;
        long j;
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        float childCount = (viewGroup.getChildCount() * (getSIconSize().getWidth() + getSHorizMarginForIcon())) + getSHorizMarginForIconExtra();
        getMenuItemAnimator().onParentTranslationUpdated(view, f, viewGroup, f2);
        ExpandableView expandableView = (ExpandableView) view;
        ExpandableViewState viewState = expandableView.getViewState();
        float f3 = 0.0f;
        float notificationStackedProgress = (viewState == null || (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) == null) ? 0.0f : oplusExpandableViewStateEx.getNotificationStackedProgress();
        if (Math.abs(f) >= childCount || notificationStackedProgress != 0.0f) {
            f3 = 0.0f;
            long jCurrentTimeMillis = System.currentTimeMillis();
            NotificationMenuRowMetaBallController notificationMenuRowMetaBallController = this.metaBallController;
            if (notificationMenuRowMetaBallController != null) {
                notificationMenuRowMetaBallController.updateMetaBallPositionNoMeta(f);
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (jCurrentTimeMillis2 > 70 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowExtImpl metaBall cost(noMeta) translation=" + f + " positionMs=" + jCurrentTimeMillis2));
            }
        } else {
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList = this.rightMenuItems;
            if (arrayList != null) {
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i2 = 0;
                j = 70;
                while (i2 < size) {
                    NotificationMenuRowPlugin.MenuItem menuItem = arrayList.get(i2);
                    i2++;
                    View menuView = menuItem.getMenuView();
                    if (menuView != null) {
                        arrayList2.add(menuView);
                    }
                }
                int size2 = arrayList2.size();
                int i3 = 0;
                int i4 = 0;
                while (i4 < size2) {
                    Object obj = arrayList2.get(i4);
                    i4++;
                    int i5 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    View view2 = (View) obj;
                    NotificationMenuRowMetaBallController notificationMenuRowMetaBallController2 = this.metaBallController;
                    if (notificationMenuRowMetaBallController2 != null) {
                        notificationMenuRowMetaBallController2.addMetaBallData(i3, view2);
                    }
                    i3 = i5;
                }
            } else {
                j = 70;
            }
            long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
            long jCurrentTimeMillis5 = System.currentTimeMillis();
            NotificationMenuRowMetaBallController notificationMenuRowMetaBallController3 = this.metaBallController;
            if (notificationMenuRowMetaBallController3 != null) {
                notificationMenuRowMetaBallController3.updateMetaBallPosition(f);
            }
            long jCurrentTimeMillis6 = System.currentTimeMillis() - jCurrentTimeMillis5;
            if ((jCurrentTimeMillis4 > j || jCurrentTimeMillis6 > j) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat("NotificationMenuRowExtImpl metaBall cost translation=" + f + " bindMs=" + jCurrentTimeMillis4 + " positionMs=" + jCurrentTimeMillis6));
            }
        }
        if (view instanceof ExpandableNotificationRow) {
            ViewBlurProxy viewBlurProxy3 = ((ActivatableNotificationView) ((ExpandableNotificationRow) view)).mBackgroundNormal.getExt().getViewBlurProxy();
            Drawable blurDrawable2 = viewBlurProxy3 != null ? viewBlurProxy3.getBlurDrawable(null) : null;
            PlatformBlurDrawable platformBlurDrawable = blurDrawable2 instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable2 : null;
            if (platformBlurDrawable != null) {
                platformBlurDrawable.setEnableBlurMaskColor(f == f3);
            }
        }
        if (f == f3) {
            resetState((FrameLayout) viewGroup, map);
            NotificationMenuRowPlugin notificationMenuRowPlugin = this.menuRow;
            if (notificationMenuRowPlugin == null || !notificationMenuRowPlugin.isMenuVisible()) {
                return;
            }
            if (expandableView instanceof ExpandableNotificationRow) {
                NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) ((ExpandableNotificationRow) view)).mBackgroundNormal;
                MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable2 = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable((notificationBackgroundView == null || (ext2 = notificationBackgroundView.getExt()) == null || (viewBlurProxy2 = ext2.getViewBlurProxy()) == null) ? null : viewBlurProxy2.getBlurDrawable(null));
                if (metaBallBlurDrawableTryGetMetaBallBlurDrawable2 != null) {
                    NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableTryGetMetaBallBlurDrawable2, 0, "ExpandableNotificationRow setMenuChildrenTranslation0");
                }
            } else if (expandableView instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
                if (!NotificationBackgroundCustomRowHandler.INSTANCE.needDrawBgColorByMetaBall(this.context, oplusCustomRow.getEntry())) {
                    NotificationBackgroundView mBackgroundNormal = oplusCustomRow.getMBackgroundNormal();
                    MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable3 = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable((mBackgroundNormal == null || (ext = mBackgroundNormal.getExt()) == null || (viewBlurProxy = ext.getViewBlurProxy()) == null) ? null : viewBlurProxy.getBlurDrawable(null));
                    if (metaBallBlurDrawableTryGetMetaBallBlurDrawable3 != null) {
                        NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableTryGetMetaBallBlurDrawable3, 0, "OplusCustomRow setMenuChildrenTranslation0");
                    }
                }
            }
            ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList3 = this.rightMenuItems;
            if (arrayList3 != null) {
                ArrayList arrayList4 = new ArrayList();
                int size3 = arrayList3.size();
                int i6 = 0;
                while (i6 < size3) {
                    NotificationMenuRowPlugin.MenuItem menuItem2 = arrayList3.get(i6);
                    i6++;
                    View menuView2 = menuItem2.getMenuView();
                    if (menuView2 != null) {
                        arrayList4.add(menuView2);
                    }
                }
                int size4 = arrayList4.size();
                int i7 = 0;
                while (i7 < size4) {
                    Object obj2 = arrayList4.get(i7);
                    i7++;
                    ViewBlurProxy blurProxyForView = getViewBlurManager().getBlurProxyForView((View) obj2);
                    if (blurProxyForView != null && (blurDrawable = blurProxyForView.getBlurDrawable(null)) != null && (metaBallBlurDrawableTryGetMetaBallBlurDrawable = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(blurDrawable)) != null) {
                        NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableTryGetMetaBallBlurDrawable, 0, "menuView setMenuChildrenTranslation0");
                    }
                }
            }
        }
    }

    public void setMenuViewBackground(NotificationMenuRowPlugin.MenuItem menuItem) {
        View menuView = menuItem.getMenuView();
        menuView.setTag(NotificationMenuRowLog.TAG);
        if (!menuView.isAttachedToWindow()) {
            NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1 notificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1 = new NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1();
            notificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1.$this_doOnAttach = menuView;
            notificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1.this$0 = this;
            notificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1.$this_apply$inlined = menuView;
            VarHandle.storeStoreFence();
            menuView.addOnAttachStateChangeListener(notificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1);
            return;
        }
        Drawable menuItemBackground = getMenuItemBackground(menuView.getResources(), menuView);
        menuView.setBackground(menuItemBackground);
        ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
        NotificationMenuRowLog.e(companion.simpleNameAndHash(menuView) + " doOnAttach, setBackground = " + companion.simpleNameAndHash(menuItemBackground));
        if (menuView.isAttachedToWindow()) {
            menuView.addOnAttachStateChangeListener(new NotificationMenuRowExtImpl$setMenuViewBackground$lambda$13$$inlined$doOnAttach$1(menuView, menuView, this));
            return;
        }
        NotificationMenuRowLog.e(companion.simpleNameAndHash(menuView) + " doOnDetach, post viewBlurManager.releaseBlurProxyForView");
        getViewBlurManager().releaseBlurProxyForView(menuView, true, "menu detach");
    }

    public final void updateMenuBlur() {
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.menuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.isMenuVisible()) {
            ExpandableView menuRowParent = getMenuRowParent();
            NotificationMenuRowPlugin notificationMenuRowPlugin2 = this.menuRow;
            View menuView = notificationMenuRowPlugin2 != null ? notificationMenuRowPlugin2.getMenuView() : null;
            ViewGroup viewGroup = menuView instanceof ViewGroup ? (ViewGroup) menuView : null;
            if (menuRowParent != null && viewGroup != null) {
                onParentHeightUpdate(menuRowParent, viewGroup);
            }
        }
        ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList = this.rightMenuItems;
        int i = 0;
        if (arrayList != null) {
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                NotificationMenuRowPlugin.MenuItem menuItem = arrayList.get(i2);
                i2++;
                View menuView2 = menuItem.getMenuView();
                if (menuView2 != null && menuView2.isVisibleToUser()) {
                    menuView2.setBackground(getMenuItemBackground(menuView2.getResources(), menuView2));
                }
            }
        }
        MaterialColorMode materialColorMode = (MaterialColorMode) getNotificationMaterialColorEx().getCurrentMaterialColor().getValue();
        ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList2 = this.rightMenuItems;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            while (i < size2) {
                NotificationMenuRowPlugin.MenuItem menuItem2 = arrayList2.get(i);
                i++;
                View menuView3 = menuItem2.getMenuView();
                if (menuView3 != null && menuView3.isVisibleToUser()) {
                    if (menuView3 instanceof NotificationMenuRowItemImageView) {
                        OplusCustomMenuRow.Companion.setSettingImageDrawableTint(this.context, ((NotificationMenuRowItemImageView) menuView3).getImageView(), materialColorMode);
                    } else if (menuView3 instanceof NotificationMenuRowItemLottieView) {
                        ((NotificationMenuRowItemLottieView) menuView3).getLottieView().setAnimation(NotificationMenuRowItemResourceHelperKt.getDeleteItemLottieRes(this.context, materialColorMode));
                    } else if (menuView3 instanceof ImageView) {
                        OplusCustomMenuRow.Companion.setSettingImageDrawableTint(this.context, (ImageView) menuView3, materialColorMode);
                    }
                }
            }
        }
    }

    public final void updateResources() {
        getMenuItemAnimator().reset();
    }

    public /* bridge */ /* synthetic */ void updateSizeIfNeeded(Resources resources, boolean z) {
        super.updateSizeIfNeeded(resources, z);
    }
}
