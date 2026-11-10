package com.oplus.systemui.statusbar.notification.customcard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.ViewGroupKt;
import com.android.app.animation.Interpolators;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.SwipeHelper;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.animation.RowAppearAnimationEx;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableOutlineViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SwipeableView;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import com.oplus.systemui.notification.material.NotificationSpotLightParamsKind;
import com.oplus.systemui.notification.row.NotificationBackgroundCustomRowHandler;
import com.oplus.systemui.notification.row.NotificationMenuRowExtImpl;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowGuideTipsController;
import com.oplus.systemui.notification.row.overlay.NotificationOverlayBackgroundView;
import com.oplus.systemui.statusbar.notification.animation.RowAppearAnimationExImpl;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuController;
import com.oplus.systemui.statusbar.notification.customcard.popupmenu.OplusCustomPopupMenuControllerImpl;
import com.oplus.systemui.statusbar.notification.helper.NotificationApplyDelayHelperImpl;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl;
import com.oplus.systemui.util.NotificationUtil;
import com.oplus.wrapper.os.Trace;
import com.oplusos.systemui.common.BaseViewBinder;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRow extends ExpandableOutlineView implements SwipeableView, LaunchableView {
    private static final String TAG = "OplusCustomRow";
    private static final String TAG_CARD_VIEW_LAYOUT_ANIMATOR_SUPPORTED = "cardViewLayoutAnimatorSupport";
    public boolean _isExpandAnimationRunning;
    public float appearAnimationFraction;
    private ValueAnimator appearAnimator;
    public boolean canViewBeDismissed;
    public float currentTranslationY;
    public final OplusCustomRowControllerManager customManager;
    public float defaultCornerRadius;
    private final LaunchableViewDelegate delegate;
    private CustomRowEntry entry;
    private final Lazy guideTipsController$delegate;
    private final Drawable immersiveCustomDrawable;
    public final int immersiveEndBottomLayerColor;
    public final int immersiveEndBottomLayerSpecialColor;
    public final int immersiveEndTopLayerColor;
    public final int immersiveEndTopLayerSpecialColor;
    private ImmersiveViewEntry immersiveViewEntry;
    public boolean initTranslationY;
    public boolean isLaunchAnimationRunning;
    public boolean isShowDeleteMenu;
    public boolean isShowSettingMenu;
    public final ILockScreenExtStateController lockScreenExtStateController;
    private String mAppName;
    private NotificationBackgroundView mBackgroundNormal;
    private LayoutListener mLayoutListener;
    private final NotificationMenuRowPlugin mMenuRow;
    public int mNormalColor;
    private NotificationOverlayBackgroundView mOverlayBackgroundView;
    private SpringAnimation mSpringTranslateAnim;
    private Animator mTranslateAnim;
    private final List<View> mTranslateableViews;
    private final Handler mainHandler;
    private OplusCustomMenuRow menuRow;
    private final Lazy notificationApplyDelayHelper$delegate;
    public int notificationLaunchHeight;
    public int orientation;
    private final Lazy rowAppearAnimationEx$delegate;
    public boolean skipNotificationSpotLightForCapture;
    private final Path tmpCustomViewPath;
    private FloatProperty<View> translationContent;
    public final ViewBlurManager viewBlurManager;
    public static final Companion Companion = new Companion();
    private static final SourceType BASE_VALUE = SourceType.Companion.from("BaseValue");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isOplusMediaCustomRow(String str) {
            return Intrinsics.areEqual(str, OplusCustomRowControllerManager.MEDIA_CONTROLLER_SERVICE_ID) || Intrinsics.areEqual(str, OplusCustomRowControllerManager.MEDIA_CONTROLLER_SERVICE_ID_NEW);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface LayoutListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusCustomRowViewState extends ExpandableViewState {
        /* JADX WARN: Multi-variable type inference failed */
        public void animateTo(View view, AnimationProperties animationProperties) {
            if (view instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
                if (oplusCustomRow._isExpandAnimationRunning) {
                    return;
                }
                ImmersiveViewEntry immersiveViewEntry = oplusCustomRow.getImmersiveViewEntry();
                if (immersiveViewEntry != null) {
                    immersiveViewEntry.getViewState().animateTo(immersiveViewEntry, animationProperties, null, null);
                }
                super.animateTo(view, animationProperties);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void applyToView(View view) {
            if (view instanceof OplusCustomRow) {
                OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
                if (oplusCustomRow._isExpandAnimationRunning) {
                    return;
                }
                ImmersiveViewEntry immersiveViewEntry = oplusCustomRow.getImmersiveViewEntry();
                if (immersiveViewEntry != null) {
                    immersiveViewEntry.getViewState().applyTo(immersiveViewEntry);
                }
                super.applyToView(view);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow$dispatchDraw$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider {
        public /* synthetic */ OplusCustomRow this$0;

        @Override // com.oplus.systemui.notification.material.NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider
        public final NotificationSpotLightDelegate.ClipSpec getClipSpec() {
            OplusCustomRow oplusCustomRow = this.this$0;
            OplusExpandableOutlineViewEx oplusExpandableOutlineViewEx = ((ExpandableOutlineView) oplusCustomRow).mOutlineViewEx;
            OplusExpandableOutlineViewExImpl oplusExpandableOutlineViewExImpl = oplusExpandableOutlineViewEx instanceof OplusExpandableOutlineViewExImpl ? (OplusExpandableOutlineViewExImpl) oplusExpandableOutlineViewEx : null;
            if (oplusExpandableOutlineViewExImpl != null) {
                return oplusExpandableOutlineViewExImpl.getSpotLightClipSpec(oplusCustomRow);
            }
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit $r8$lambda$NmJYklb0fncjT_z8F30BiKXzm44(OplusCustomRow oplusCustomRow, int i) {
        super/*android.widget.FrameLayout*/.setVisibility(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusCustomRow(Context context, AttributeSet attributeSet) {
        OplusCustomMenuRow oplusCustomMenuRow;
        super(context, attributeSet);
        this.appearAnimationFraction = -1.0f;
        final int i = 0;
        this.rowAppearAnimationEx$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        OplusCustomRow.Companion companion = OplusCustomRow.Companion;
                        return (RowAppearAnimationEx) DependencyEx.sDependency.getDependency(RowAppearAnimationEx.class);
                    default:
                        OplusCustomRow.Companion companion2 = OplusCustomRow.Companion;
                        return (NotificationApplyDelayHelper) DependencyEx.sDependency.getDependency(NotificationApplyDelayHelper.class);
                }
            }
        });
        final int i2 = 1;
        this.notificationApplyDelayHelper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        OplusCustomRow.Companion companion = OplusCustomRow.Companion;
                        return (RowAppearAnimationEx) DependencyEx.sDependency.getDependency(RowAppearAnimationEx.class);
                    default:
                        OplusCustomRow.Companion companion2 = OplusCustomRow.Companion;
                        return (NotificationApplyDelayHelper) DependencyEx.sDependency.getDependency(NotificationApplyDelayHelper.class);
                }
            }
        });
        this.translationContent = new OplusCustomRow$translationContent$1("translate");
        this.mTranslateableViews = new ArrayList();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.tmpCustomViewPath = new Path();
        CustomRowEntry customRowEntry = this.entry;
        this.canViewBeDismissed = customRowEntry != null ? customRowEntry.isShowDeleteMenu() : false;
        OplusCustomRow$$ExternalSyntheticLambda2 oplusCustomRow$$ExternalSyntheticLambda2 = new OplusCustomRow$$ExternalSyntheticLambda2();
        oplusCustomRow$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.delegate = new LaunchableViewDelegate(this, oplusCustomRow$$ExternalSyntheticLambda2);
        this.viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
        this.customManager = (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
        this.lockScreenExtStateController = (ILockScreenExtStateController) DependencyEx.sDependency.getDependency(ILockScreenExtStateController.class);
        OplusCustomRow$$ExternalSyntheticLambda3 oplusCustomRow$$ExternalSyntheticLambda3 = new OplusCustomRow$$ExternalSyntheticLambda3();
        oplusCustomRow$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.guideTipsController$delegate = LazyKt__LazyJVMKt.lazy(oplusCustomRow$$ExternalSyntheticLambda3);
        this.defaultCornerRadius = getResources().getDimensionPixelSize(2131170626);
        this.mAppName = "Breeno Suggestions";
        ArrayList arrayList = new ArrayList();
        if (this.menuRow == null) {
            this.menuRow = context != null ? new OplusCustomMenuRow(context, arrayList) : null;
        }
        OplusCustomMenuRow oplusCustomMenuRow2 = this.menuRow;
        if ((oplusCustomMenuRow2 != null ? oplusCustomMenuRow2.getMenuView() : null) != null && (oplusCustomMenuRow = this.menuRow) != null) {
            oplusCustomMenuRow.setAppName(this.mAppName);
        }
        this.immersiveEndTopLayerColor = getContext().getColor(2131101855);
        this.immersiveEndBottomLayerColor = getContext().getColor(2131101854);
        this.immersiveEndTopLayerSpecialColor = getContext().getColor(2131101857);
        this.immersiveEndBottomLayerSpecialColor = getContext().getColor(2131101856);
        Drawable smoothDrawable = SmoothRoundExKt.getSmoothDrawable(getContext(), 2131104174);
        smoothDrawable.getClass();
        this.immersiveCustomDrawable = smoothDrawable;
        setClipChildren(false);
        setClipToPadding(false);
        NotificationSpotLightDelegate.Companion.markLazyParamsKind(this, NotificationSpotLightParamsKind.NOTIFICATION_SEEDLING_CARD);
    }

    private final int getContentIntrinsicHeight() {
        int height = getContentLayout().getHeight();
        return getGuideTipsController().shouldUseGuideTipsIntrinsicHeight() ? getGuideTipsController().computeContentIntrinsicHeight(height) : height;
    }

    private final Map<Integer, Pair<Integer, Integer>> getImmersiveColorMap() {
        return MapsKt__MapsJVMKt.mapOf(TuplesKt.to(1, new Pair(Integer.valueOf(this.immersiveEndTopLayerSpecialColor), Integer.valueOf(this.immersiveEndBottomLayerSpecialColor))));
    }

    private final NotificationApplyDelayHelper getNotificationApplyDelayHelper() {
        return (NotificationApplyDelayHelper) this.notificationApplyDelayHelper$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RowAppearAnimationEx getRowAppearAnimationEx() {
        return (RowAppearAnimationEx) this.rowAppearAnimationEx$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyLaunchAnimationParams(LaunchAnimationParameters launchAnimationParameters) {
        int top;
        if (launchAnimationParameters == null) {
            setTranslationX(0.0f);
            getViewState().setXTranslation(0.0f);
            float dimension = getResources().getDimension(2131170626);
            NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
            if (notificationBackgroundView != null) {
                notificationBackgroundView.setRadius(dimension, dimension, true);
            }
            OplusPhysicsPropertyAnimatorExtKt.setFinalValue(this, OplusPhysicsPropertyAnimator.Y_TRANSLATION, getViewState().getYTranslation());
            return;
        }
        if (!launchAnimationParameters.getVisible() && getVisibility() == 0) {
            setVisibility(4);
            return;
        }
        PathInterpolator pathInterpolator = (PathInterpolator) Interpolators.FAST_OUT_SLOW_IN;
        setTranslationZ(MathUtils.lerp(launchAnimationParameters.getStartTranslationZ(), this.notificationLaunchHeight, pathInterpolator.getInterpolation(launchAnimationParameters.getProgress(50L))));
        setExtraWidthForClipping(launchAnimationParameters.getWidth() - getWidth());
        if (launchAnimationParameters.getStartRoundedTopClipping() > 0) {
            float interpolation = pathInterpolator.getInterpolation(launchAnimationParameters.getProgress(100L));
            int startNotificationTop = launchAnimationParameters.getStartNotificationTop();
            top = Math.min((int) MathUtils.lerp(startNotificationTop, launchAnimationParameters.getTop(), interpolation), startNotificationTop);
        } else {
            top = launchAnimationParameters.getTop();
        }
        int bottom = launchAnimationParameters.getBottom() - top;
        setFinalActualHeight(bottom);
        int notificationParentTop = top - launchAnimationParameters.getNotificationParentTop();
        int startClipTopAmount = launchAnimationParameters.getStartClipTopAmount();
        int iLerp = (int) MathUtils.lerp(startClipTopAmount, 0, launchAnimationParameters.getProgress());
        if (startClipTopAmount != 0) {
            setClipTopAmount(iLerp);
        }
        setTranslationY(notificationParentTop);
        setTranslationX(launchAnimationParameters.getCenterX() - (((getWidth() / 2.0f) + getLocationOnScreen()[0]) - getTranslationX()));
        if (getTopRoundness() == getBottomRoundness()) {
            getRoundableState().setRoundness(getTopRoundness(), false);
        } else {
            getRoundableState().setTopRoundness(getTopRoundness(), false);
            getRoundableState().setBottomRoundness(getBottomRoundness(), false);
        }
        NotificationBackgroundView notificationBackgroundView2 = this.mBackgroundNormal;
        if (notificationBackgroundView2 != null) {
            int width = launchAnimationParameters.getWidth();
            notificationBackgroundView2.mExpandAnimationHeight = bottom;
            notificationBackgroundView2.mExpandAnimationWidth = width;
            notificationBackgroundView2.invalidate();
        }
        NotificationBackgroundView notificationBackgroundView3 = this.mBackgroundNormal;
        if (notificationBackgroundView3 != null) {
            notificationBackgroundView3.setRadius(launchAnimationParameters.getTopCornerRadius(), launchAnimationParameters.getBottomCornerRadius(), true);
        }
        invalidateOutline();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void attachGuideTipsViewForController(View view) {
        addView(view);
        this.mTranslateableViews.add(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Path buildCustomViewRoundedPath(Path path) {
        View contentLayout = getContentLayout();
        if (contentLayout == this || contentLayout.getWidth() <= 0 || contentLayout.getHeight() <= 0) {
            path.reset();
            return null;
        }
        boolean z = getResources().getBoolean(2131034133);
        Pair pair = TuplesKt.to(Float.valueOf(z ? getMaxRadius() : getTopCornerRadius()), Float.valueOf(z ? getMaxRadius() : getBottomCornerRadius()));
        float fFloatValue = ((Number) pair.component1()).floatValue();
        float fFloatValue2 = ((Number) pair.component2()).floatValue();
        path.reset();
        getRoundedRectPath(contentLayout.getLeft(), contentLayout.getTop(), contentLayout.getRight(), contentLayout.getBottom(), fFloatValue, fFloatValue2, path);
        return path;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0018  */
    /* JADX WARN: Code duplicated, block: B:15:0x0023 A[RETURN] */
    public boolean childNeedsClipping(View view) {
        if (!(view instanceof NotificationBackgroundView)) {
            if (view != null) {
                CustomRowEntry customRowEntry = this.entry;
                if (!view.equals(customRowEntry != null ? customRowEntry.getCustomView() : null)) {
                    if (!getGuideTipsController().isGuideTipsChild(view)) {
                        return false;
                    }
                }
            } else if (!getGuideTipsController().isGuideTipsChild(view)) {
                return false;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomRow-->".concat("childNeedsClipping, child = " + view));
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void closeMenuView() {
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if ((oplusCustomMenuRow != null ? oplusCustomMenuRow.getMenuView() : null) != null) {
            NotificationMenuRowPlugin provider = getProvider();
            removeView(provider != null ? provider.getMenuView() : null);
        }
    }

    public ExpandableViewState createExpandableViewState() {
        return new OplusCustomRowViewState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NotificationMenuRowPlugin createMenu() {
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null && oplusCustomMenuRow.getMenuView() == null) {
            oplusCustomMenuRow.createMenu(this);
            oplusCustomMenuRow.setAppName(this.mAppName);
            addView(oplusCustomMenuRow.getMenuView(), 0, new FrameLayout.LayoutParams(-1, -1));
        }
        List<View> list = this.mTranslateableViews;
        OplusCustomMenuRow oplusCustomMenuRow2 = this.menuRow;
        TypeIntrinsics.asMutableCollection(list).remove(oplusCustomMenuRow2 != null ? oplusCustomMenuRow2.getMenuView() : null);
        return this.menuRow;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void dispatchDraw(Canvas canvas) {
        super/*android.widget.FrameLayout*/.dispatchDraw(canvas);
        if (this.skipNotificationSpotLightForCapture) {
            return;
        }
        NotificationSpotLightDelegate.Companion companion = NotificationSpotLightDelegate.Companion;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        companion.draw(this, canvas, anonymousClass1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void doLongClick(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomRow-->doLongClick");
        }
        Companion companion = Companion;
        CustomRowEntry customRowEntry = this.entry;
        if (companion.isOplusMediaCustomRow(customRowEntry != null ? customRowEntry.getServiceId() : null)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomRow-->media controller not show popupMenu");
                return;
            }
            return;
        }
        OplusCustomPopupMenuController popopMenuController = this.customManager.getPopopMenuController();
        if (((OplusCustomPopupMenuControllerImpl) popopMenuController).isPopupMenuShowing) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCustomRow-->onLongPress--Panel isShowing!");
                return;
            }
            return;
        }
        performHapticFeedback(0);
        View rootView = getRootView();
        if (rootView != null) {
            NotificationUtil.sendCancelMotionEvent((ViewGroup) rootView, "doLongClick");
        }
        doLongClick.r.1 r2 = new doLongClick.r.1(0);
        r2.this$0 = this;
        r2.$popupMenuController = popopMenuController;
        r2.$statisticsEvent = str;
        VarHandle.storeStoreFence();
        if (((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState() == 1) {
            NotificationSwipeHelper swipeHepler = this.customManager.getSwipeHepler();
            if (swipeHepler != null) {
                swipeHepler.resetExposedMenuView(true, true);
            }
            ILockScreenExtStateController.tryGotoShadeLockState$default(this.lockScreenExtStateController, "LongPressRowOnLockScreen", 0.0f, r2, 20);
            return;
        }
        if (!ScrimUtil.isMiddleOrHighAnimLevel(getContext()) && !ScrimUtil.isBPAnimLevel()) {
            r2.run();
            return;
        }
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        if (oplusExpandableViewEx != null) {
            oplusExpandableViewEx.executeRunnableAnimationFinish(r2);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriterAsIndenting = DumpUtilsKt.asIndenting(printWriter);
        super.dump(printWriter, strArr);
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.dump(indentingPrintWriterAsIndenting, strArr);
        }
    }

    public /* bridge */ /* synthetic */ float getBottomCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getBottomRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundness();
    }

    public /* bridge */ /* synthetic */ Set getBottomRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundnessSources();
    }

    public final boolean getCanViewBeDismissed() {
        return this.canViewBeDismissed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View getContentLayout() {
        for (View view : ViewGroupKt.getChildren(this)) {
            CustomRowEntry customRowEntry = this.entry;
            if (!Intrinsics.areEqual(view, customRowEntry != null ? customRowEntry.getCustomView() : null)) {
                if (!(view instanceof NotificationBackgroundView)) {
                    OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
                    if (Intrinsics.areEqual(view, oplusCustomMenuRow != null ? oplusCustomMenuRow.getMenuView() : null) || getGuideTipsController().isGuideTipsChild(view)) {
                    }
                }
            }
            return view;
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getContentLayoutMeasuredHeight() {
        int measuredHeight = getContentLayout().getMeasuredHeight();
        return measuredHeight > 0 ? measuredHeight : getMeasuredHeight();
    }

    public final void getContentViewClipPath(Path path) {
        path.reset();
        buildCustomViewRoundedPath(path);
    }

    public Path getCustomClipPath(View view) {
        if (getGuideTipsController().isTipsViewVisible()) {
            CustomRowEntry customRowEntry = this.entry;
            if (Intrinsics.areEqual(view, customRowEntry != null ? customRowEntry.getCustomView() : null)) {
                return buildCustomViewRoundedPath(this.tmpCustomViewPath);
            }
        }
        return getClipPath(false);
    }

    public final CustomRowEntry getEntry() {
        return this.entry;
    }

    public final OplusCustomRowGuideTipsController getGuideTipsController() {
        return (OplusCustomRowGuideTipsController) this.guideTipsController$delegate.getValue();
    }

    public final ImmersiveViewEntry getImmersiveViewEntry() {
        return this.immersiveViewEntry;
    }

    public int getIntrinsicHeight() {
        return getContentIntrinsicHeight();
    }

    public final NotificationBackgroundView getMBackgroundNormal() {
        return this.mBackgroundNormal;
    }

    public final NotificationOverlayBackgroundView getMOverlayBackgroundView() {
        return this.mOverlayBackgroundView;
    }

    public /* bridge */ /* synthetic */ float getMaxRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getMaxRadius();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<NotificationMenuRowPlugin.MenuItem> getMenuItems() {
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null) {
            return notificationMenuRowPlugin.getMenuItems(getContext());
        }
        return null;
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super.getPaddingForLaunchAnimation();
    }

    public final NotificationMenuRowPlugin getProvider() {
        return this.menuRow;
    }

    public final boolean getSkipNotificationSpotLightForCapture() {
        return this.skipNotificationSpotLightForCapture;
    }

    public /* bridge */ /* synthetic */ float getTopCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getTopRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundness();
    }

    public /* bridge */ /* synthetic */ Set getTopRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundnessSources();
    }

    public final Animator getTranslateViewAnimator(float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        Animator animator = this.mTranslateAnim;
        if (animator != null) {
            animator.cancel();
        }
        SpringAnimation springAnimation = this.mSpringTranslateAnim;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        PhysicsAnimator.Companion companion = PhysicsAnimator.Companion;
        if (companion.getInstance(this).isRunning()) {
            companion.getInstance(this).cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.translationContent, f);
        if (animatorUpdateListener != null) {
            objectAnimatorOfFloat.addUpdateListener(animatorUpdateListener);
        }
        objectAnimatorOfFloat.addListener(new getTranslateViewAnimator.1(this, f));
        this.mTranslateAnim = objectAnimatorOfFloat;
        return objectAnimatorOfFloat;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float getTranslation() {
        if (((ExpandableOutlineView) this).mDismissUsingRowTranslationX) {
            return getTranslationX();
        }
        if (this.mTranslateableViews.isEmpty()) {
            return 0.0f;
        }
        return this.mTranslateableViews.get(0).getTranslationX();
    }

    public final FloatProperty<View> getTranslationContent() {
        return this.translationContent;
    }

    public /* bridge */ /* synthetic */ float[] getUpdatedRadii() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getUpdatedRadii();
    }

    public final boolean hasFinishedInitialization() {
        return true;
    }

    public final boolean isExpandAnimationRunning() {
        return this._isExpandAnimationRunning;
    }

    public final boolean isRemoved() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final BaseViewBinder makeEx() {
        final Context context = getContext();
        OplusExpandableOutlineViewEx oplusExpandableOutlineViewEx = new OplusExpandableOutlineViewExImpl(context) { // from class: com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow$oplusExpandableOutlineViewExImpl$1
            public final boolean oplusStyle;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context);
                context.getClass();
                this.oplusStyle = true;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
            public /* bridge */ /* synthetic */ void bind(View view) {
                super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.FrameLayout, com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow] */
            @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
            public NotificationBackgroundView getNotificationBackgroundView() {
                ?? r1 = this.this$0;
                NotificationBackgroundView mBackgroundNormal = r1.getMBackgroundNormal();
                return mBackgroundNormal == null ? r1.findViewById(2131362768) : mBackgroundNormal;
            }

            @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
            public boolean getOplusStyle() {
                return this.oplusStyle;
            }

            public final boolean shouldUseSmoothClip() {
                return true;
            }

            public final void syncClipCanvasToBgView(float f, int i, int i2, float f2, int i3, int i4) {
                OplusCustomRow oplusCustomRow = this.this$0;
                NotificationOverlayBackgroundView mOverlayBackgroundView = oplusCustomRow.getMOverlayBackgroundView();
                if (mOverlayBackgroundView != null) {
                    mOverlayBackgroundView.invalidate();
                }
                NotificationBackgroundView mBackgroundNormal = oplusCustomRow.getMBackgroundNormal();
                NotificationBackgroundViewExt ext = mBackgroundNormal != null ? mBackgroundNormal.getExt() : null;
                NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
                if (notificationBackgroundViewExtImp != null) {
                    boolean z = false;
                    boolean z2 = notificationBackgroundViewExtImp.getClipLeft() == i && notificationBackgroundViewExtImp.getClipRight() == i3 && notificationBackgroundViewExtImp.getClipTop() == i2 && notificationBackgroundViewExtImp.getClipBottom() == i4;
                    if (notificationBackgroundViewExtImp.getClipTopRadius() == f && notificationBackgroundViewExtImp.getClipBottomRadius() == f2) {
                        z = true;
                    }
                    if (z2 && z) {
                        return;
                    }
                    notificationBackgroundViewExtImp.setClipLeft(i);
                    notificationBackgroundViewExtImp.setClipRight(i3);
                    notificationBackgroundViewExtImp.setClipTop(i2);
                    notificationBackgroundViewExtImp.setClipBottom(i4);
                    notificationBackgroundViewExtImp.setClipTopRadius(f);
                    notificationBackgroundViewExtImp.setClipBottomRadius(f2);
                    if (((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState() == 1) {
                        notificationBackgroundViewExtImp.updateRadius(f, f2);
                    } else {
                        String str = OplusExpandableViewStateEx.Companion != null ? "clip_width_touch_scale" : null;
                        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) oplusCustomRow).mExpandableViewEx;
                        if (!Intrinsics.areEqual(str, oplusExpandableViewEx != null ? oplusExpandableViewEx.getCurrentClipWidthSource() : null)) {
                            NotificationBackgroundView mBackgroundNormal2 = oplusCustomRow.getMBackgroundNormal();
                            if (mBackgroundNormal2 != null) {
                                i4 = mBackgroundNormal2.getActualHeight();
                            }
                            notificationBackgroundViewExtImp.setClipBottom(i4);
                        }
                        float f3 = oplusCustomRow.defaultCornerRadius;
                        notificationBackgroundViewExtImp.updateRadius(f3, f3);
                    }
                    NotificationBackgroundView mBackgroundNormal3 = oplusCustomRow.getMBackgroundNormal();
                    if (mBackgroundNormal3 != null) {
                        mBackgroundNormal3.invalidate();
                    }
                }
            }
        };
        oplusExpandableOutlineViewEx.bind(this);
        return oplusExpandableOutlineViewEx;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.FrameLayout*/.onAttachedToWindow();
        updateBaseRoundness();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        int i = configuration != null ? configuration.orientation : this.orientation;
        if (this.orientation != i) {
            resetTranslation();
            this.orientation = i;
        }
        resetTranslation();
        NotificationMenuRowPlugin notificationMenuRowPlugin = this.mMenuRow;
        if (notificationMenuRowPlugin != null && notificationMenuRowPlugin.getMenuView() != null) {
            this.mMenuRow.onConfigurationChanged();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomRow-->onConfigurationChanged");
        }
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        View menuView = oplusCustomMenuRow != null ? oplusCustomMenuRow.getMenuView() : null;
        if (menuView != null) {
            int iIndexOfChild = indexOfChild(menuView);
            removeView(menuView);
            OplusCustomMenuRow oplusCustomMenuRow2 = this.menuRow;
            if (oplusCustomMenuRow2 != null) {
                oplusCustomMenuRow2.createMenu(this);
            }
            OplusCustomMenuRow oplusCustomMenuRow3 = this.menuRow;
            if (oplusCustomMenuRow3 != null) {
                oplusCustomMenuRow3.setAppName(this.mAppName);
            }
            OplusCustomMenuRow oplusCustomMenuRow4 = this.menuRow;
            addView(oplusCustomMenuRow4 != null ? oplusCustomMenuRow4.getMenuView() : null, iIndexOfChild, new FrameLayout.LayoutParams(-1, -1));
        }
        this.defaultCornerRadius = getResources().getDimensionPixelSize(2131170626);
        updateBaseRoundness();
        updateResource(true);
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        setImmersiveProgress(oplusExpandableViewEx != null ? oplusExpandableViewEx.getImmersiveProgress() : 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        super/*android.widget.FrameLayout*/.onFinishInflate();
        this.mBackgroundNormal = findViewById(2131362768);
        updateResource(true);
        setAllChildViewInTranslateable();
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null) {
            oplusCustomMenuRow.setCustomContainer(this);
        }
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (!KeyEvent.isConfirmKey(i)) {
            return false;
        }
        doLongClick(null);
        return true;
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        LayoutListener layoutListener = this.mLayoutListener;
        if (layoutListener != null) {
            ((OplusCustomMenuRow) layoutListener).onLayout();
        }
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null && oplusCustomMenuRow.getMenuView() != null) {
            oplusCustomMenuRow.onParentHeightUpdate();
        }
        int contentIntrinsicHeight = getContentIntrinsicHeight();
        super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.onLayout(z, i, i2, i3, i4);
        if (contentIntrinsicHeight != getContentIntrinsicHeight()) {
            if (contentIntrinsicHeight != 0 || getActualHeight() > 0) {
                notifyHeightChanged("OCR: onLayout", Intrinsics.areEqual(getContentLayout().getTag(), TAG_CARD_VIEW_LAYOUT_ANIMATOR_SUPPORTED));
                if (getGuideTipsController().shouldSyncActualHeightFromLayout()) {
                    setActualHeight(getContentIntrinsicHeight(), true);
                }
            }
        }
    }

    public final void onMeasure(int i, int i2) {
        long j = Trace.TRACE_TAG_VIEW;
        Trace.traceBegin(j, "OplusCustomRow#onMeasure");
        super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.onMeasure(i, i2);
        getGuideTipsController().onMeasureAfterSuper(this.mBackgroundNormal);
        Trace.traceEnd(j);
    }

    public void performAddAnimation(long j, long j2, boolean z, boolean z2, Runnable runnable) {
        if (!shouldSkipAppearAnimation(z, z2)) {
            startScaleAlphaAppearAnimation(true, j, j2, z, null, runnable, null);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public long performRemoveAnimation(long j, long j2, float f, boolean z, boolean z2, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        boolean zShouldSkipAppearAnimation = shouldSkipAppearAnimation(z, z2);
        CustomRowEntry customRowEntry = this.entry;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("performRemoveAnimation: key=", customRowEntry != null ? customRowEntry.getKey() : null, " skip=", zShouldSkipAppearAnimation, " delay=");
        sbM.append(j2);
        DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, " duration=", j, " headsUp=");
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, z, " cycling=", z2), null, 8);
        performRemoveAnimation.finish.1 r8 = new performRemoveAnimation.finish.1();
        r8.$onFinishedRunnable = runnable2;
        VarHandle.storeStoreFence();
        if (!zShouldSkipAppearAnimation) {
            startScaleAlphaAppearAnimation(false, j2, j, z, runnable, r8, animatorListenerAdapter);
            return 0L;
        }
        if (runnable != null) {
            runnable.run();
        }
        r8.run();
        return 0L;
    }

    public final void removeListener() {
        this.mLayoutListener = null;
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        NotificationSwipeHelper swipeHepler;
        Companion companion = Companion;
        CustomRowEntry customRowEntry = this.entry;
        if (companion.isOplusMediaCustomRow(customRowEntry != null ? customRowEntry.getServiceId() : null) && (swipeHepler = this.customManager.getSwipeHepler()) != null) {
            ((SwipeHelper) swipeHepler).requestDisallowInterceptTouchEvent = z;
        }
        super/*android.widget.FrameLayout*/.requestDisallowInterceptTouchEvent(z);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resetTranslation() {
        Animator animator = this.mTranslateAnim;
        if (animator != null) {
            animator.cancel();
        }
        SpringAnimation springAnimation = this.mSpringTranslateAnim;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        if (((ExpandableOutlineView) this).mDismissUsingRowTranslationX) {
            setTranslationX(0.0f);
        } else {
            PhysicsAnimator.Companion companion = PhysicsAnimator.Companion;
            if (companion.getInstance(this).isRunning()) {
                companion.getInstance(this).cancel();
            }
            if (!this.mTranslateableViews.isEmpty()) {
                Iterator<T> it = this.mTranslateableViews.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setTranslationX(0.0f);
                }
            }
        }
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null) {
            oplusCustomMenuRow.resetState$1(true);
        }
    }

    public ExpandableViewState resetViewState() {
        SeedlingImmersiveViewState viewState;
        ImmersiveViewEntry immersiveViewEntry = this.immersiveViewEntry;
        if (immersiveViewEntry != null && (viewState = immersiveViewEntry.getViewState()) != null) {
            viewState.setImmersiveState(0);
            viewState.setFromExtState(2);
            viewState.setToExtState(0);
            OplusExpandableViewStateEx oplusExpandableViewStateEx = ((ExpandableViewState) viewState).mExpandableViewStateEx;
            if (oplusExpandableViewStateEx != null) {
                oplusExpandableViewStateEx.setAnimationCornerRadius(this.defaultCornerRadius);
            }
        }
        return super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.resetViewState();
    }

    public final void setActualHeight(int i, boolean z) {
        super.setActualHeight(i, z);
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.setActualHeight(i);
        }
        getGuideTipsController().setActualHeight(i, getContentIntrinsicHeight());
    }

    public void setActualHeightAnimating(boolean z) {
        super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.setActualHeightAnimating(z);
        getGuideTipsController().setActualHeightAnimating(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setAllChildViewInTranslateable() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (!this.mTranslateableViews.contains(childAt)) {
                List<View> list = this.mTranslateableViews;
                childAt.getClass();
                list.add(childAt);
            }
        }
        List<View> list2 = this.mTranslateableViews;
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView == null) {
            notificationBackgroundView = null;
        }
        TypeIntrinsics.asMutableCollection(list2).remove(notificationBackgroundView);
        List<View> list3 = this.mTranslateableViews;
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        TypeIntrinsics.asMutableCollection(list3).remove(oplusCustomMenuRow != null ? oplusCustomMenuRow.getMenuView() : null);
    }

    public final void setCanViewBeDismissed(boolean z) {
        this.canViewBeDismissed = z;
    }

    public void setClipBottomAmount(int i) {
        if (this._isExpandAnimationRunning) {
            return;
        }
        super.setClipBottomAmount(i);
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.setBottomAmountClips(i == 0);
        }
    }

    public void setClipTopAmount(int i) {
        super.setClipTopAmount(i);
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.setClipTopAmount(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setDismissUsingRowTranslationXInner(boolean z) {
        if (z != ((ExpandableOutlineView) this).mDismissUsingRowTranslationX) {
            float translation = getTranslation();
            if (translation != 0.0f) {
                setTranslation(0.0f);
            }
            ((ExpandableOutlineView) this).mDismissUsingRowTranslationX = z;
            if (translation != 0.0f) {
                setTranslation(translation);
            }
            invalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setEntry(CustomRowEntry customRowEntry) {
        NotificationBackgroundViewExt ext;
        ViewBlurProxy viewBlurProxy;
        NotificationBackgroundCustomRowHandler notificationBackgroundCustomRowHandler = NotificationBackgroundCustomRowHandler.INSTANCE;
        if (!Intrinsics.areEqual(notificationBackgroundCustomRowHandler.getContentBgColor(this.entry), notificationBackgroundCustomRowHandler.getContentBgColor(customRowEntry))) {
            Context context = getContext();
            NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
            Drawable blurDrawable = null;
            if (notificationBackgroundView != null && (ext = notificationBackgroundView.getExt()) != null && (viewBlurProxy = ext.getViewBlurProxy()) != null) {
                blurDrawable = viewBlurProxy.getBlurDrawable(null);
            }
            notificationBackgroundCustomRowHandler.updateDrawableMetaColor(context, customRowEntry, NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(blurDrawable));
        }
        this.entry = customRowEntry;
        boolean zIsOplusMediaCustomRow = Companion.isOplusMediaCustomRow(customRowEntry.getServiceId());
        this.isShowSettingMenu = customRowEntry.isShowSettingMenu() && !zIsOplusMediaCustomRow;
        boolean z = customRowEntry.isShowDeleteMenu() && !zIsOplusMediaCustomRow;
        this.isShowDeleteMenu = z;
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        if (oplusExpandableViewEx != null) {
            oplusExpandableViewEx.setHasMetaBall(this.isShowSettingMenu || z);
        }
        this.immersiveViewEntry = new ImmersiveViewEntry(customRowEntry, new SeedlingImmersiveViewState(1));
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null) {
            oplusCustomMenuRow.setEntry(customRowEntry);
        }
        CustomRowEntry customRowEntry2 = this.entry;
        this.canViewBeDismissed = customRowEntry2 != null ? customRowEntry2.isShowDeleteMenu() : false;
        this.immersiveCustomDrawable.clearColorFilter();
        CustomRowEntry customRowEntry3 = this.entry;
        if (customRowEntry3 == null || LockScreenSeedingInteractor.Companion.getBgType(customRowEntry3) != 1) {
            this.immersiveCustomDrawable.setTint(getContext().getColor(2131101853));
        } else {
            this.immersiveCustomDrawable.setTint(getContext().getColor(2131101470));
        }
        OplusExpandableViewEx oplusExpandableViewEx2 = ((ExpandableView) this).mExpandableViewEx;
        setImmersiveProgress(oplusExpandableViewEx2 != null ? oplusExpandableViewEx2.getImmersiveProgress() : 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setExpandAnimationRunning(boolean z) {
        this._isExpandAnimationRunning = z;
        if (z) {
            getViewState().cancelAnimations(this);
            this.notificationLaunchHeight = Math.max(1, getContext().getResources().getDimensionPixelSize(2131174023)) * 4;
        } else {
            int state = ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState();
            Companion companion = Companion;
            CustomRowEntry customRowEntry = this.entry;
            setVisibility((companion.isOplusMediaCustomRow(customRowEntry != null ? customRowEntry.getServiceId() : null) && state == 0) ? 8 : 0);
            setExtraWidthForClipping(0.0f);
        }
        updateClipping$1();
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.setExpandAnimationRunning(z);
        }
    }

    public final void setImmersiveProgress(float f) {
        NotificationBackgroundViewExt ext;
        ViewBlurProxy viewBlurProxy;
        MixColor mixColorCopy;
        Drawable customBackground;
        Drawable customBackground2;
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry customRowEntry = this.entry;
            Log.d("SystemUi--Notification", "OplusCustomRow-->".concat((customRowEntry != null ? customRowEntry.getKey() : null) + " immersiveProgress=" + f));
        }
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        NotificationBackgroundViewExt ext2 = notificationBackgroundView != null ? notificationBackgroundView.getExt() : null;
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext2 instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext2 : null;
        if (notificationBackgroundViewExtImp != null) {
            if (f == 0.0f) {
                notificationBackgroundViewExtImp.setTempDrawable(null);
                NotificationBackgroundView notificationBackgroundView2 = this.mBackgroundNormal;
                if (notificationBackgroundView2 != null && (customBackground2 = notificationBackgroundView2.getCustomBackground()) != null) {
                    customBackground2.setAlpha(255);
                }
            } else {
                this.immersiveCustomDrawable.setAlpha((int) (f * 255.0f));
                notificationBackgroundViewExtImp.setTempDrawable(this.immersiveCustomDrawable);
                NotificationBackgroundView notificationBackgroundView3 = this.mBackgroundNormal;
                if (notificationBackgroundView3 != null && (customBackground = notificationBackgroundView3.getCustomBackground()) != null) {
                    customBackground.setAlpha((int) ((1.0f - f) * 255.0f));
                }
            }
        }
        NotificationBackgroundView notificationBackgroundView4 = this.mBackgroundNormal;
        if (notificationBackgroundView4 == null || (ext = notificationBackgroundView4.getExt()) == null || (viewBlurProxy = ext.getViewBlurProxy()) == null) {
            return;
        }
        BlurMixConfig blurMixConfigCustomCardPlatformMixConfig = this.viewBlurManager.customCardPlatformMixConfig();
        BlurMixConfig.BlurMixMulti blurMixMulti = blurMixConfigCustomCardPlatformMixConfig instanceof BlurMixConfig.BlurMixMulti ? (BlurMixConfig.BlurMixMulti) blurMixConfigCustomCardPlatformMixConfig : null;
        if (blurMixMulti == null) {
            return;
        }
        Map<Integer, Pair<Integer, Integer>> immersiveColorMap = getImmersiveColorMap();
        CustomRowEntry customRowEntry2 = this.entry;
        Pair<Integer, Integer> pair = immersiveColorMap.get(customRowEntry2 != null ? Integer.valueOf(LockScreenSeedingInteractor.Companion.getBgType(customRowEntry2)) : null);
        if (pair == null) {
            pair = new Pair<>(Integer.valueOf(this.immersiveEndTopLayerColor), Integer.valueOf(this.immersiveEndBottomLayerColor));
        }
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair.component2()).intValue();
        MixColor foregroundMixColor = blurMixMulti.getForegroundMixColor();
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf = Integer.valueOf(foregroundMixColor.getTopLayerColor());
        Integer numValueOf2 = Integer.valueOf(iIntValue);
        argbEvaluatorCompat.getClass();
        int iIntValue3 = ArgbEvaluatorCompat.evaluate(f, numValueOf, numValueOf2).intValue();
        ArgbEvaluatorCompat argbEvaluatorCompat2 = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf3 = Integer.valueOf(foregroundMixColor.getBottomLayerColor());
        Integer numValueOf4 = Integer.valueOf(iIntValue2);
        argbEvaluatorCompat2.getClass();
        MixColor mixColorCopy2 = foregroundMixColor.copy(foregroundMixColor.mode, iIntValue3, ArgbEvaluatorCompat.evaluate(f, numValueOf3, numValueOf4).intValue());
        CustomRowEntry customRowEntry3 = this.entry;
        if (customRowEntry3 == null || LockScreenSeedingInteractor.Companion.getBgType(customRowEntry3) != 1) {
            MixColor backgroundMixColor = blurMixMulti.getBackgroundMixColor();
            ArgbEvaluatorCompat argbEvaluatorCompat3 = ArgbEvaluatorCompat.getInstance();
            Integer numValueOf5 = Integer.valueOf(backgroundMixColor.getTopLayerColor());
            argbEvaluatorCompat3.getClass();
            int iIntValue4 = ArgbEvaluatorCompat.evaluate(f, numValueOf5, 0).intValue();
            ArgbEvaluatorCompat argbEvaluatorCompat4 = ArgbEvaluatorCompat.getInstance();
            Integer numValueOf6 = Integer.valueOf(backgroundMixColor.getBottomLayerColor());
            argbEvaluatorCompat4.getClass();
            mixColorCopy = backgroundMixColor.copy(backgroundMixColor.mode, iIntValue4, ArgbEvaluatorCompat.evaluate(f, numValueOf6, 0).intValue());
        } else {
            mixColorCopy = blurMixMulti.getBackgroundMixColor();
        }
        viewBlurProxy.getBlurConfig().setPlatformMixConfig(blurMixMulti.copy(mixColorCopy2, mixColorCopy));
        viewBlurProxy.applyBlurConfig();
    }

    public final void setLaunchAnimationRunning(boolean z) {
        this.isLaunchAnimationRunning = z;
    }

    public final void setLayoutListener(LayoutListener layoutListener) {
        this.mLayoutListener = layoutListener;
    }

    public final void setMBackgroundNormal(NotificationBackgroundView notificationBackgroundView) {
        this.mBackgroundNormal = notificationBackgroundView;
    }

    public final void setMOverlayBackgroundView(NotificationOverlayBackgroundView notificationOverlayBackgroundView) {
        this.mOverlayBackgroundView = notificationOverlayBackgroundView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setMeasuredHeightForGuideTips(int i) {
        setMeasuredDimension(getMeasuredWidth(), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setNewSeedlingView(View view) {
        int i;
        int childCount = getChildCount();
        if (childCount < 0) {
            i = -1;
            break;
        }
        i = 0;
        while (true) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
                if (!childAt.equals(oplusCustomMenuRow != null ? oplusCustomMenuRow.getMenuView() : null) && !childAt.equals(this.mBackgroundNormal) && !getGuideTipsController().isGuideTipsChild(childAt)) {
                    break;
                }
            }
            if (i == childCount) {
                i = -1;
                break;
            }
            i++;
        }
        if (i >= 0) {
            View childAt2 = getChildAt(i);
            if (this.mTranslateableViews.contains(childAt2)) {
                this.mTranslateableViews.remove(childAt2);
            }
            removeView(childAt2);
        }
        if (view.getParent() != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
        addView(view, i);
        this.mTranslateableViews.add(view);
    }

    public void setShouldBlockVisibilityChanges(boolean z) {
        this.delegate.setShouldBlockVisibilityChanges(z);
    }

    public final void setSkipNotificationSpotLightForCapture(boolean z) {
        this.skipNotificationSpotLightForCapture = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTranslation(float f) {
        invalidate();
        if (((ExpandableOutlineView) this).mDismissUsingRowTranslationX) {
            setTranslationX(f);
        } else {
            setTranslationX(0.0f);
            if (!this.mTranslateableViews.isEmpty()) {
                Iterator<T> it = this.mTranslateableViews.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setTranslationX(f);
                }
            }
            invalidateOutline();
        }
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow == null || oplusCustomMenuRow.getMenuView() == null) {
            return;
        }
        oplusCustomMenuRow.onParentTranslationUpdate(f);
    }

    public final void setTranslationContent(FloatProperty<View> floatProperty) {
        this.translationContent = floatProperty;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTranslationY(float f) {
        super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.setTranslationY(f);
        if (ScrimUtil.isLowGaussianLevel(getContext()) || getVisibility() != 0 || Math.abs(this.currentTranslationY - f) <= 10.0f) {
            return;
        }
        this.currentTranslationY = f;
        NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
        if (notificationBackgroundView != null) {
            notificationBackgroundView.invalidate();
        }
        if (this.initTranslationY) {
            updateResource(false);
        } else {
            updateResource(true);
            this.initTranslationY = true;
        }
    }

    public void setVisibility(int i) {
        this.delegate.setVisibility(i);
    }

    public final boolean shouldClipToActualHeight() {
        return !this._isExpandAnimationRunning;
    }

    public final boolean shouldSkipAppearAnimation(boolean z, boolean z2) {
        return z || z2 || this._isExpandAnimationRunning || this.isLaunchAnimationRunning || ((NotificationApplyDelayHelperImpl) getNotificationApplyDelayHelper()).animating;
    }

    public final void startScaleAlphaAppearAnimation(boolean z, long j, long j2, boolean z2, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter) {
        ValueAnimator valueAnimator = this.appearAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.appearAnimator = null;
        if (this.appearAnimationFraction < 0.0f) {
            this.appearAnimationFraction = z ? 0.0f : 1.0f;
        }
        float f = z ? 1.0f : 0.0f;
        ((RowAppearAnimationExImpl) getRowAppearAnimationEx()).getClass();
        if (!z2) {
            j2 = 600;
        }
        long jAbs = (long) (Math.abs(this.appearAnimationFraction - f) * j2);
        if (jAbs < 0) {
            jAbs = 0;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.appearAnimationFraction, f);
        valueAnimatorOfFloat.setInterpolator(Interpolators.LINEAR);
        valueAnimatorOfFloat.setDuration(jAbs);
        startScaleAlphaAppearAnimation.animator.1.1 r8 = new startScaleAlphaAppearAnimation.animator.1.1();
        r8.this$0 = this;
        r8.$isAppearing = z;
        r8.$isHeadsUpAnimation = z2;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r8);
        valueAnimatorOfFloat.addListener(new startScaleAlphaAppearAnimation.animator.1.2(runnable, animatorListenerAdapter, this, runnable2));
        this.appearAnimator = valueAnimatorOfFloat;
        getRowAppearAnimationEx().doAppearAnimation(z, z2, this.appearAnimationFraction, this);
        Choreographer choreographer = Choreographer.getInstance();
        startScaleAlphaAppearAnimation.1 r9 = new startScaleAlphaAppearAnimation.1();
        r9.this$0 = this;
        r9.$cachedAnimator = valueAnimatorOfFloat;
        VarHandle.storeStoreFence();
        choreographer.postFrameCallbackDelayed(r9, j);
    }

    public final void updateBaseRoundness() {
        float maxRadius = this.defaultCornerRadius / getMaxRadius();
        SourceType sourceType = BASE_VALUE;
        requestRoundness(maxRadius, maxRadius, sourceType, false);
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
        if (oplusExpandableViewEx == null || oplusExpandableViewEx.getAnimationCornerRadius() <= this.defaultCornerRadius || oplusExpandableViewEx.getAnimationCornerRadius() == getTopCornerRadius()) {
            return;
        }
        getRoundableState().setMaxRadius(oplusExpandableViewEx.getAnimationCornerRadius());
        requestRoundness(1.0f, 1.0f, sourceType, false);
    }

    public /* bridge */ /* synthetic */ void updateRadii(float f, float f2, float[] fArr) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.updateRadii(f, f2, fArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResource(boolean z) {
        NotificationBackgroundViewExt ext;
        if (z) {
            this.mNormalColor = getContext().getColor(2131101470);
            NotificationBackgroundView notificationBackgroundView = this.mBackgroundNormal;
            if (notificationBackgroundView != null) {
                notificationBackgroundView.setCustomBackground(SmoothRoundExKt.getSmoothLayerDrawable(getContext(), 2131101470));
            }
            NotificationBackgroundView notificationBackgroundView2 = this.mBackgroundNormal;
            if (notificationBackgroundView2 != null && (ext = notificationBackgroundView2.getExt()) != null) {
                ext.init();
            }
            NotificationBackgroundView notificationBackgroundView3 = this.mBackgroundNormal;
            if (notificationBackgroundView3 != null) {
                notificationBackgroundView3.setTint(this.mNormalColor);
            }
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) this).mExpandableViewEx;
            setImmersiveProgress(oplusExpandableViewEx != null ? oplusExpandableViewEx.getImmersiveProgress() : 0.0f);
        }
        OplusCustomMenuRow oplusCustomMenuRow = this.menuRow;
        if (oplusCustomMenuRow != null) {
            ((NotificationMenuRowExtImpl) oplusCustomMenuRow.mNotificationMenuRowExt).updateResources();
        }
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType, boolean z) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType, z);
    }
}
