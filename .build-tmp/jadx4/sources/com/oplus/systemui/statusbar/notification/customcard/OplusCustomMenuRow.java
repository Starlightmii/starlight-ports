package com.oplus.systemui.statusbar.notification.customcard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.NotificationMenuRowExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.notification.row.NotificationMenuRowExtImpl;
import com.oplus.systemui.notification.row.NotificationMenuRowItemCreator;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationColorPalette;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomMenuRow implements NotificationMenuRowPlugin, View.OnClickListener, OplusCustomRow.LayoutListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCustomMenuRow";
    private static StatusBarStateController statusBarStateController;
    public float alpha;
    private final Context context;
    private CustomRowEntry entry;
    public boolean iconsPlaced;
    private NotificationMenuRowPlugin.MenuItem infoItem;
    public boolean mMenuSnapped;
    public boolean mMenuSnappedOnLeft;
    public final boolean mShouldShowMenu;
    public boolean mSnappingToDismiss;
    private FrameLayout menuContainer;
    private NotificationMenuRowPlugin.OnMenuEventListener menuListener;
    public boolean onLeft;
    private OplusCustomRow oplusCustomRow;
    private OplusCustomRow parent;
    public boolean snapping;
    public float translation;
    private ArrayList<String> whiteList;
    private ArrayList<NotificationMenuRowPlugin.MenuItem> leftMenuItems = new ArrayList<>();
    private ArrayList<NotificationMenuRowPlugin.MenuItem> rightMenuItems = new ArrayList<>();
    private Map<View, NotificationMenuRowPlugin.MenuItem> menuItemsByView = new LinkedHashMap();
    private final int[] iconLocation = new int[2];
    private final int[] parentLocation = new int[2];
    public int horizSpaceForIcon = -1;
    public final int iconPadding = -1;
    public final NotificationMenuRowExt mNotificationMenuRowExt = (NotificationMenuRowExt) DependencyEx.sDependency.createNewInstance(NotificationMenuRowExt.class);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final StatusBarStateController getStatusBarStateController() {
            if (OplusCustomMenuRow.statusBarStateController == null) {
                OplusCustomMenuRow.statusBarStateController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
            }
            return OplusCustomMenuRow.statusBarStateController;
        }

        public final NotificationMenuRow.NotificationMenuItem createInfoItem(Context context) {
            return new NotificationMenuRow.NotificationMenuItem(context, context.getResources().getString(2131955119), LayoutInflater.from(context).inflate(2131559201, (ViewGroup) null, false), 2131234511);
        }

        public final void setSettingImageDrawableTint(Context context, ImageView imageView, MaterialColorMode materialColorMode) {
            int color;
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.clearColorFilter();
            }
            if (SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
                color = context.getColor(2131102937);
            } else {
                ScrimControllerEx.Companion.getClass();
                color = (ScrimControllerEx.isThirdTheme || ScrimUtil.isLowGaussianLevel(context)) ? context.getColor(2131102937) : NotificationColorPalette.Companion.from(materialColorMode).getSettingIconMaterialColor();
            }
            imageView.setImageTintList(ColorStateList.valueOf(color));
        }
    }

    public OplusCustomMenuRow(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.whiteList = arrayList;
        this.mShouldShowMenu = context.getResources().getBoolean(2131034228);
    }

    private final float getDismissThreshold() {
        return this.mNotificationMenuRowExt.getDismissThreshold(this.parent, this.menuContainer, this.horizSpaceForIcon);
    }

    private final float getMaximumSwipeDistance() {
        return this.horizSpaceForIcon * 0.2f;
    }

    private final float getMinimumSwipeDistance() {
        OplusCustomRow parent = getParent();
        return this.horizSpaceForIcon * ((parent == null || !parent.getCanViewBeDismissed()) ? 0.15f : 0.25f);
    }

    private final float getSnapBackThreshold() {
        return getSpaceForMenu() - getMaximumSwipeDistance();
    }

    public static final void setSettingImageDrawableTint(Context context, ImageView imageView, MaterialColorMode materialColorMode) {
        Companion.setSettingImageDrawableTint(context, imageView, materialColorMode);
    }

    public final boolean canBeDismissed() {
        OplusCustomRow parent = getParent();
        return parent != null && parent.getCanViewBeDismissed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void createMenu(ViewGroup viewGroup) {
        this.parent = viewGroup instanceof OplusCustomRow ? (OplusCustomRow) viewGroup : null;
        Resources resources = this.context.getResources();
        this.horizSpaceForIcon = resources.getDimensionPixelSize(2131165242);
        resources.getDimensionPixelSize(2131165244);
        this.leftMenuItems.clear();
        this.rightMenuItems.clear();
        this.infoItem = Companion.createInfoItem(this.context);
        OplusCustomRow oplusCustomRow = this.parent;
        boolean z = oplusCustomRow != null ? oplusCustomRow.isShowSettingMenu : false;
        boolean z2 = oplusCustomRow != null ? oplusCustomRow.isShowDeleteMenu : false;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("createMenuViews: isShowSettingMenu = ", ", isShowDeleteMenu = ", z, z2), null, 8);
        this.mNotificationMenuRowExt.createMenuViewsExt(true, this, this.rightMenuItems, this.context, z, z2);
        this.leftMenuItems.addAll(this.rightMenuItems);
        populateMenuViews$1();
        resetState$1(false);
    }

    public final NotificationMenuRowExt getExt() {
        return this.mNotificationMenuRowExt;
    }

    public NotificationMenuRowPlugin.MenuItem getLongpressMenuItem(Context context) {
        return this.infoItem;
    }

    public ArrayList<NotificationMenuRowPlugin.MenuItem> getMenuItems(Context context) {
        return this.onLeft ? this.leftMenuItems : this.rightMenuItems;
    }

    public int getMenuSnapTarget() {
        boolean z = this.onLeft;
        int spaceForMenu = getSpaceForMenu();
        return z ? spaceForMenu : -spaceForMenu;
    }

    public View getMenuView() {
        return this.menuContainer;
    }

    public final OplusCustomRow getParent() {
        return this.parent;
    }

    public Point getRevealAnimationOrigin() {
        NotificationMenuRowPlugin.MenuItem menuItem = this.infoItem;
        if (menuItem != null && this.parent != null) {
            menuItem.getClass();
            if (menuItem.getMenuView() != null) {
                NotificationMenuRowPlugin.MenuItem menuItem2 = this.infoItem;
                menuItem2.getClass();
                View menuView = menuItem2.getMenuView();
                menuView.getClass();
                int width = (menuView.getWidth() / 2) + menuView.getPaddingLeft() + menuView.getLeft();
                int height = (menuView.getHeight() / 2) + menuView.getPaddingTop() + menuView.getTop();
                if (this.onLeft) {
                    return new Point(width, height);
                }
                LaunchableView launchableView = this.parent;
                return new Point((launchableView != null ? launchableView.getRight() : 0) - width, height);
            }
        }
        return new Point(0, 0);
    }

    public NotificationMenuRowPlugin.MenuItem getSnoozeMenuItem(Context context) {
        return null;
    }

    public final int getSpaceForMenu() {
        FrameLayout frameLayout;
        if (isImmersiveState() || (frameLayout = this.menuContainer) == null) {
            return 0;
        }
        return this.mNotificationMenuRowExt.getSpaceForMenu(frameLayout, this.horizSpaceForIcon);
    }

    public final float getTranslation() {
        return this.translation;
    }

    public final boolean isImmersiveState() {
        OplusExpandableViewEx oplusExpandableViewEx;
        OplusCustomRow oplusCustomRow = this.parent;
        return Intrinsics.areEqual((oplusCustomRow == null || (oplusExpandableViewEx = ((ExpandableView) oplusCustomRow).mExpandableViewEx) == null) ? null : Float.valueOf(oplusExpandableViewEx.getImmersiveProgress()), 1.0f);
    }

    public final boolean isMenuVisible() {
        return this.alpha > 0.0f;
    }

    public final boolean isSnappedAndOnSameSide() {
        return this.mMenuSnapped && isMenuVisible() && this.mMenuSnappedOnLeft == this.onLeft;
    }

    public final boolean isSwipeToEnd(float f, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        View menuView = notificationMenuRowPlugin.getMenuView();
        if (menuView != null) {
            return NotificationMenuRowItemCreator.isSwipeToEnd(f, menuView);
        }
        return false;
    }

    public final boolean isSwipedEnoughToShowMenu() {
        float minimumSwipeDistance = getMinimumSwipeDistance();
        float translation = getTranslation();
        if (!isMenuVisible()) {
            return false;
        }
        if (this.onLeft) {
            return translation > minimumSwipeDistance;
        }
        return translation < (-minimumSwipeDistance);
    }

    public final boolean isTowardsMenu(float f) {
        if (!isMenuVisible()) {
            return false;
        }
        boolean z = this.onLeft;
        if (!z || f > 0.0f) {
            return !z && f >= 0.0f;
        }
        return true;
    }

    public final boolean isWithinSnapMenuThreshold() {
        float translation = getTranslation();
        float snapBackThreshold = getSnapBackThreshold();
        float dismissThreshold = getDismissThreshold();
        if (this.onLeft) {
            return translation > snapBackThreshold && translation < dismissThreshold;
        }
        return translation < (-snapBackThreshold) && translation > (-dismissThreshold);
    }

    public NotificationMenuRowPlugin.MenuItem menuItemToExposeOnSnap() {
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.menuListener == null || this.parent == null) {
            return;
        }
        view.getLocationOnScreen(this.iconLocation);
        LaunchableView launchableView = this.parent;
        launchableView.getClass();
        launchableView.getLocationOnScreen(this.parentLocation);
        int i = this.horizSpaceForIcon / 2;
        int height = view.getHeight() / 2;
        int[] iArr = this.iconLocation;
        int i2 = iArr[0];
        int[] iArr2 = this.parentLocation;
        int i3 = (i2 - iArr2[0]) + i;
        int i4 = (iArr[1] - iArr2[1]) + height;
        if (this.menuItemsByView.containsKey(view)) {
            NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener = this.menuListener;
            onMenuEventListener.getClass();
            onMenuEventListener.onMenuClicked(this.parent, i3, i4, this.menuItemsByView.get(view));
        }
    }

    public final void onConfigurationChanged() {
        OplusCustomRow oplusCustomRow = this.parent;
        if (oplusCustomRow != null) {
            oplusCustomRow.setLayoutListener(this);
        }
    }

    public final void onDismiss() {
        this.mMenuSnapped = false;
    }

    public final void onLayout() {
        this.iconsPlaced = false;
        setMenuLocation$1();
        OplusCustomRow oplusCustomRow = this.parent;
        if (oplusCustomRow != null) {
            oplusCustomRow.removeListener();
        }
    }

    public final void onNotificationUpdated() {
    }

    public final void onParentHeightUpdate() {
        FrameLayout frameLayout;
        OplusCustomRow oplusCustomRow = this.parent;
        if (oplusCustomRow == null || (frameLayout = this.menuContainer) == null) {
            return;
        }
        this.mNotificationMenuRowExt.onParentHeightUpdate(oplusCustomRow, frameLayout);
    }

    public final void onParentTranslationUpdate(float f) {
        FrameLayout frameLayout;
        LaunchableView parent = getParent();
        if (parent == null || (frameLayout = this.menuContainer) == null || isImmersiveState()) {
            return;
        }
        if (!getMenuItems(this.context).isEmpty() || frameLayout.getVisibility() == 0) {
            this.translation = f;
            this.mNotificationMenuRowExt.setMenuChildrenTranslation(f, getDismissThreshold(), frameLayout, parent, this.menuItemsByView, this.horizSpaceForIcon);
            if (isMenuVisible() || isSwipeToEnd(this.translation, this) || f == 0.0f) {
                return;
            }
            setMenuAlpha(1.0f);
            ((NotificationMenuRowExtImpl) this.mNotificationMenuRowExt).updateMenuBlur();
        }
    }

    public final void onSnapClosed() {
        this.mMenuSnapped = false;
        this.snapping = true;
    }

    public final void onSnapOpen() {
        this.mMenuSnapped = true;
        this.mMenuSnappedOnLeft = this.onLeft;
        NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener = this.menuListener;
        if (onMenuEventListener != null) {
            onMenuEventListener.onMenuShown(getParent());
        }
    }

    public final void onTouchEnd() {
    }

    public final void onTouchMove(float f) {
        this.snapping = false;
        if (!isTowardsMenu(f)) {
            float f2 = this.translation;
            int i = this.iconPadding;
            boolean z = f2 > ((float) i);
            boolean z2 = f2 < ((float) (-i));
            boolean z3 = this.onLeft;
            if ((z3 && z2) || (!z3 && z)) {
                this.mMenuSnapped = false;
                setMenuAlpha(0.0f);
                setMenuLocation$1();
            }
        }
        if (isSwipeToEnd(f, this)) {
            return;
        }
        this.mNotificationMenuRowExt.getClass();
        if (canBeDismissed()) {
            float dismissThreshold = getDismissThreshold();
            this.mSnappingToDismiss = f < (-dismissThreshold) || f > dismissThreshold;
        }
    }

    public final void onTouchStart() {
        this.snapping = false;
        this.iconsPlaced = false;
        setMenuLocation$1();
        this.mSnappingToDismiss = false;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void populateMenuViews$1() {
        FrameLayout frameLayout = this.menuContainer;
        if (frameLayout != null) {
            frameLayout.getClass();
            frameLayout.removeAllViews();
            this.menuItemsByView.clear();
        } else {
            FrameLayout frameLayout2 = new FrameLayout(this.context);
            frameLayout2.setClipChildren(false);
            this.menuContainer = frameLayout2;
        }
        ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList = this.onLeft ? this.leftMenuItems : this.rightMenuItems;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            NotificationMenuRowPlugin.MenuItem menuItem = arrayList.get(i);
            FrameLayout frameLayout3 = this.menuContainer;
            frameLayout3.getClass();
            View menuView = menuItem.getMenuView();
            if (menuView != null) {
                menuView.setAlpha(this.alpha);
                frameLayout3.addView(menuView);
                menuView.setOnClickListener(this);
                ViewGroup.LayoutParams layoutParams = menuView.getLayoutParams();
                FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
                if (layoutParams2 != null) {
                    Size iconSize = this.mNotificationMenuRowExt.getIconSize(this.horizSpaceForIcon);
                    layoutParams2.width = iconSize.getWidth();
                    layoutParams2.height = iconSize.getHeight();
                    menuView.setLayoutParams(layoutParams2);
                }
                this.mNotificationMenuRowExt.setMenuViewBackground(menuItem);
            }
            Map<View, NotificationMenuRowPlugin.MenuItem> map = this.menuItemsByView;
            menuView.getClass();
            map.put(menuView, menuItem);
        }
    }

    public final void resetMenu() {
        resetState$1(true);
    }

    public final void resetState$1(boolean z) {
        NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener;
        setMenuAlpha(0.0f);
        this.iconsPlaced = false;
        this.snapping = false;
        this.mMenuSnapped = false;
        setMenuLocation$1();
        FrameLayout frameLayout = this.menuContainer;
        if (frameLayout != null) {
            this.mNotificationMenuRowExt.resetState(frameLayout, this.menuItemsByView);
        }
        if (!z || (onMenuEventListener = this.menuListener) == null) {
            return;
        }
        onMenuEventListener.onMenuReset(this.parent);
    }

    public final void setAppName(String str, List list) {
        Resources resources = this.context.getResources();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            NotificationMenuRowPlugin.MenuItem menuItem = (NotificationMenuRowPlugin.MenuItem) list.get(i);
            String str2 = String.format(resources.getString(2131955117), Arrays.copyOf(new Object[]{str, menuItem.getContentDescription()}, 2));
            View menuView = menuItem.getMenuView();
            if (menuView != null) {
                menuView.setContentDescription(str2);
            }
        }
    }

    public final void setCustomContainer(OplusCustomRow oplusCustomRow) {
        this.oplusCustomRow = oplusCustomRow;
    }

    public final void setEntry(CustomRowEntry customRowEntry) {
        this.entry = customRowEntry;
    }

    public final void setMenuAlpha(float f) {
        this.alpha = f;
        FrameLayout frameLayout = this.menuContainer;
        if (frameLayout == null) {
            return;
        }
        if (f == 0.0f) {
            if (frameLayout != null) {
                frameLayout.setVisibility(4);
            }
        } else if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        FrameLayout frameLayout2 = this.menuContainer;
        frameLayout2.getClass();
        int childCount = frameLayout2.getChildCount();
        for (int i = 0; i < childCount; i++) {
            FrameLayout frameLayout3 = this.menuContainer;
            frameLayout3.getClass();
            frameLayout3.getChildAt(i).setAlpha(f);
        }
    }

    public void setMenuClickListener(NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener) {
        this.menuListener = onMenuEventListener;
    }

    public void setMenuItems(ArrayList<NotificationMenuRowPlugin.MenuItem> arrayList) {
    }

    public final void setMenuLocation$1() {
        FrameLayout frameLayout;
        LaunchableView parent = getParent();
        if (parent == null) {
            return;
        }
        boolean showOnLeft = this.mNotificationMenuRowExt.getShowOnLeft(parent, this.translation);
        if ((this.iconsPlaced && showOnLeft == this.onLeft) || this.snapping || (frameLayout = this.menuContainer) == null || !frameLayout.isAttachedToWindow()) {
            return;
        }
        boolean z = this.onLeft;
        this.onLeft = showOnLeft;
        if (z != showOnLeft) {
            populateMenuViews$1();
        }
        FrameLayout frameLayout2 = this.menuContainer;
        frameLayout2.getClass();
        int childCount = frameLayout2.getChildCount();
        if (childCount > 1) {
            FrameLayout frameLayout3 = this.menuContainer;
            View childAt = frameLayout3 != null ? frameLayout3.getChildAt(0) : null;
            float elevation = childAt != null ? childAt.getElevation() : 0.0f;
            if (childAt != null) {
                childAt.setElevation(elevation + 1.0f);
            }
            if (childAt != null) {
                childAt.setOutlineSpotShadowColor(0);
            }
            if (childAt != null) {
                childAt.setOutlineAmbientShadowColor(0);
            }
        }
        for (int i = 0; i < childCount; i++) {
            FrameLayout frameLayout4 = this.menuContainer;
            frameLayout4.getClass();
            View childAt2 = frameLayout4.getChildAt(i);
            NotificationMenuRowExt notificationMenuRowExt = this.mNotificationMenuRowExt;
            parent.getWidth();
            float fCalLeftOffset = ((NotificationMenuRowExtImpl) notificationMenuRowExt).calLeftOffset(i);
            float fCalRightOffset = ((NotificationMenuRowExtImpl) this.mNotificationMenuRowExt).calRightOffset(parent.getWidth(), i);
            if (!showOnLeft) {
                fCalLeftOffset = fCalRightOffset;
            }
            childAt2.setX(fCalLeftOffset);
        }
        this.iconsPlaced = true;
    }

    public final boolean shouldShowGutsOnSnapOpen() {
        return false;
    }

    public final boolean shouldShowMenu() {
        return this.mShouldShowMenu;
    }

    public final boolean shouldSnapBack() {
        float translation = getTranslation();
        float snapBackThreshold = getSnapBackThreshold();
        if (this.onLeft) {
            return translation < snapBackThreshold;
        }
        return translation > (-snapBackThreshold);
    }

    public final void updateWhiteListData(ArrayList<String> arrayList) {
        this.whiteList = arrayList;
    }

    public void setAppName(String str) {
        if (str == null) {
            return;
        }
        setAppName(str, this.leftMenuItems);
        setAppName(str, this.rightMenuItems);
    }
}
