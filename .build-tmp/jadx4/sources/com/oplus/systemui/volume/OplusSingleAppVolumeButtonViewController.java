package com.oplus.systemui.volume;

import android.app.ActivityManager;
import android.app.StatusBarManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.volume.anim.SingleAppVolumeButtonAnimationController;
import com.oplus.systemui.volume.repository.SingleAppVolumeButtonConfigRepository;
import com.oplus.systemui.volume.repository.SingleAppVolumeButtonInfoRepository;
import com.oplus.systemui.volume.touch.SingleAppVolumeButtonTouchHandler;
import com.oplus.systemui.volume.utils.MultiVolumeKeyStateListener;
import com.oplus.systemui.volume.utils.ScreenUtils;
import com.oplus.systemui.volume.view.SingleAppVolumeButtonContainer;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSingleAppVolumeButtonViewController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SingleAppVolumeButtonVC";
    private SingleAppVolumeButtonAnimationController animationController;
    private ImageButton buttonView;
    private SingleAppVolumeButtonCallback callback;
    private final SingleAppVolumeButtonConfigRepository config;
    private SingleAppVolumeButtonContainer containerView;
    private final Context context;
    private SingleAppVolumeButtonInfoRepository.Edge currentEdge;
    public Rect draggableBounds;
    private final SingleAppVolumeButtonInfoRepository infoRepository;
    public boolean isInitialized;
    public boolean isVisible;
    public int screenWidth;
    private final Lazy statusBarManager$delegate;
    public Rect stickableBounds;
    private SingleAppVolumeButtonTouchHandler touchHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface SingleAppVolumeButtonCallback {
    }

    public static Unit $r8$lambda$8lhrL_QuKpH8he6bKK9EDTZvKQQ(OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController) {
        SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = oplusSingleAppVolumeButtonViewController.callback;
        if (singleAppVolumeButtonCallback != null) {
            ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0.rescheduleTimeoutH();
        }
        return Unit.INSTANCE;
    }

    public static StatusBarManager $r8$lambda$C9AjzqSJWZrKyctfsJjxyrkwj8Y(OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController) {
        return (StatusBarManager) oplusSingleAppVolumeButtonViewController.context.getSystemService("statusbar");
    }

    public static Unit $r8$lambda$lk58g7dlyhsdvI37NX4GgVMUqnE(OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController, float f, float f2) {
        oplusSingleAppVolumeButtonViewController.updateCurrentEdge();
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = oplusSingleAppVolumeButtonViewController.containerView;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.notifyPositionChanged(oplusSingleAppVolumeButtonViewController.currentEdge, f, f2);
        }
        SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = oplusSingleAppVolumeButtonViewController.callback;
        if (singleAppVolumeButtonCallback != null) {
            ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0.rescheduleTimeoutH();
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$y7UOe4Vd7fHtlsTA2nPL8yghXZw(OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController) {
        oplusSingleAppVolumeButtonViewController.isVisible = false;
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = oplusSingleAppVolumeButtonViewController.containerView;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.setVisibility(8);
        }
        SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = oplusSingleAppVolumeButtonViewController.callback;
        if (singleAppVolumeButtonCallback != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0;
            oplusVolumeDialogImpl.mOplusVolumeDialogView.endGfxSceneIfActive(oplusVolumeDialogImpl.mContext, 301);
            oplusVolumeDialogImpl.mOplusVolumeDialogView.dismissH(1);
            oplusVolumeDialogImpl.setHasTopUi(false);
        }
        return Unit.INSTANCE;
    }

    public OplusSingleAppVolumeButtonViewController(Context context) {
        this.context = context;
        this.config = new SingleAppVolumeButtonConfigRepository(context);
        this.infoRepository = new SingleAppVolumeButtonInfoRepository(context);
        OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 = new OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0(2);
        oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarManager$delegate = LazyKt__LazyJVMKt.lazy(oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0);
        this.currentEdge = SingleAppVolumeButtonInfoRepository.Edge.LEFT;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001b  */
    public static final void access$performSnapToEdge(OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController, float f, float f2) {
        SingleAppVolumeButtonInfoRepository.Edge edge;
        float leftEdgeX;
        ImageButton imageButton = oplusSingleAppVolumeButtonViewController.buttonView;
        if (imageButton != null) {
            float x = imageButton.getX();
            oplusSingleAppVolumeButtonViewController.updateCurrentEdge();
            SingleAppVolumeButtonInfoRepository.Edge edge2 = oplusSingleAppVolumeButtonViewController.currentEdge;
            SingleAppVolumeButtonInfoRepository.Edge edge3 = SingleAppVolumeButtonInfoRepository.Edge.LEFT;
            SingleAppVolumeButtonConfigRepository singleAppVolumeButtonConfigRepository = oplusSingleAppVolumeButtonViewController.config;
            if (edge2 == edge3) {
                if (f < singleAppVolumeButtonConfigRepository.getEscapeVelocity()) {
                    edge = edge3;
                } else {
                    edge = SingleAppVolumeButtonInfoRepository.Edge.RIGHT;
                }
            } else if (f < (-singleAppVolumeButtonConfigRepository.getEscapeVelocity())) {
                edge = edge3;
            } else {
                edge = SingleAppVolumeButtonInfoRepository.Edge.RIGHT;
            }
            int iOrdinal = edge.ordinal();
            if (iOrdinal == 0) {
                leftEdgeX = oplusSingleAppVolumeButtonViewController.getLeftEdgeX();
            } else {
                if (iOrdinal != 1) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                }
                leftEdgeX = oplusSingleAppVolumeButtonViewController.getRightEdgeX();
            }
            float flingFriction = oplusSingleAppVolumeButtonViewController.config.getFlingFriction() * (leftEdgeX - x) * 4.2f;
            Triple triple = new Triple(Float.valueOf(leftEdgeX), Float.valueOf(edge == edge3 ? Math.min(flingFriction, f) : Math.max(flingFriction, f)), edge);
            float fFloatValue = ((Number) triple.component1()).floatValue();
            float fFloatValue2 = ((Number) triple.component2()).floatValue();
            SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = oplusSingleAppVolumeButtonViewController.animationController;
            if (singleAppVolumeButtonAnimationController != null) {
                Rect rect = oplusSingleAppVolumeButtonViewController.stickableBounds;
                Rect rect2 = rect == null ? null : rect;
                Rect rect3 = oplusSingleAppVolumeButtonViewController.draggableBounds;
                Rect rect4 = rect3 == null ? null : rect3;
                OplusSingleAppVolumeButtonViewController$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new OplusSingleAppVolumeButtonViewController$.ExternalSyntheticLambda3();
                externalSyntheticLambda3.f$0 = oplusSingleAppVolumeButtonViewController;
                VarHandle.storeStoreFence();
                singleAppVolumeButtonAnimationController.flingThenSpring(fFloatValue, fFloatValue2, f2, rect2, rect4, externalSyntheticLambda3);
            }
        }
    }

    private final float getFlyInStartX() {
        int iOrdinal = this.currentEdge.ordinal();
        if (iOrdinal == 0) {
            return (-this.config.getButtonSize()) * 2.0f;
        }
        if (iOrdinal == 1) {
            return this.screenWidth + this.config.getButtonSize();
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return 0.0f;
    }

    private final float getFlyOutTargetX() {
        return getFlyInStartX();
    }

    private final float getLeftEdgeX() {
        return this.config.getEdgeMargin();
    }

    private final float getRightEdgeX() {
        return (this.screenWidth - this.config.getButtonSize()) - this.config.getEdgeMargin();
    }

    private final StatusBarManager getStatusBarManager() {
        return (StatusBarManager) this.statusBarManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatusBarExpandable(boolean z) {
        try {
            getStatusBarManager().disable(z ? 0 : OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS);
        } catch (Exception e) {
            LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "Failed to set status bar expandable: " + e);
        }
    }

    public final void destroy() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->destroy()");
        }
        SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = this.animationController;
        if (singleAppVolumeButtonAnimationController != null) {
            singleAppVolumeButtonAnimationController.cancelAll();
        }
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = this.touchHandler;
        if (singleAppVolumeButtonTouchHandler != null) {
            singleAppVolumeButtonTouchHandler.release();
        }
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = this.containerView;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.setVisibility(8);
        }
        this.animationController = null;
        this.touchHandler = null;
        this.isInitialized = false;
    }

    public final FrameLayout getContainerView() {
        return this.containerView;
    }

    public final ViewTreeObserver.OnComputeInternalInsetsListener getInsetsListener() {
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = this.containerView;
        if (singleAppVolumeButtonContainer != null) {
            return singleAppVolumeButtonContainer.getInsetsListener();
        }
        return null;
    }

    public final ImageButton getView() {
        return this.buttonView;
    }

    public final void hide() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->hide()");
        }
        if (!this.isVisible) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->Already hidden");
                return;
            }
            return;
        }
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = this.touchHandler;
        if (singleAppVolumeButtonTouchHandler != null) {
            singleAppVolumeButtonTouchHandler.setEnabled(false);
        }
        MultiVolumeKeyStateListener.Companion.getInstance(this.context).setKeyEventState(false);
        OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 = new OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0(1);
        oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        if (this.buttonView != null) {
            savePosition();
            float flyOutTargetX = getFlyOutTargetX();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("performFlyOut: targetX=", flyOutTargetX, "SingleAppVolumeButtonVC-->", "SystemUi--Volume");
            }
            SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = this.animationController;
            if (singleAppVolumeButtonAnimationController != null) {
                singleAppVolumeButtonAnimationController.flyOut(flyOutTargetX, oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0);
            }
        }
    }

    public final void hideImmediately(boolean z) {
        if (this.isVisible) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->hideImmediately()");
            }
            SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = this.animationController;
            if (singleAppVolumeButtonAnimationController != null) {
                singleAppVolumeButtonAnimationController.cancelAll();
            }
            savePosition();
            this.isVisible = false;
            if (z) {
                MultiVolumeKeyStateListener.Companion.getInstance(this.context).setKeyEventState(false);
            }
        }
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = this.touchHandler;
        if (singleAppVolumeButtonTouchHandler != null) {
            singleAppVolumeButtonTouchHandler.setEnabled(false);
        }
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = this.containerView;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.setVisibility(8);
        }
    }

    public final void init() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->init()");
        }
        ImageButton imageButton = (ImageButton) LayoutInflater.from(this.context).inflate(2131559395, (ViewGroup) null);
        imageButton.setLayoutParams(new FrameLayout.LayoutParams(this.config.getButtonSize(), this.config.getButtonSize()));
        SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = new SingleAppVolumeButtonAnimationController(imageButton, this.config.getSpringStiffness(), this.config.getSpringDamping(), this.config.getFlingFriction());
        this.animationController = singleAppVolumeButtonAnimationController;
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = new SingleAppVolumeButtonTouchHandler(this.config.getTouchSlop(), this.config.getVelocityTimeUnit(), singleAppVolumeButtonAnimationController);
        OplusSingleAppVolumeButtonViewController$createDragCallback$1 oplusSingleAppVolumeButtonViewController$createDragCallback$1 = new OplusSingleAppVolumeButtonViewController$createDragCallback$1();
        oplusSingleAppVolumeButtonViewController$createDragCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        singleAppVolumeButtonTouchHandler.setDragCallback(oplusSingleAppVolumeButtonViewController$createDragCallback$1);
        this.touchHandler = singleAppVolumeButtonTouchHandler;
        OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2 oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2 = new OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2();
        oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        imageButton.setOnTouchListener(oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2);
        OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3 oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3 = new OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3();
        oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3.this$0 = this;
        VarHandle.storeStoreFence();
        imageButton.setOnClickListener(oplusSingleAppVolumeButtonViewController$init$imageButtonView$1$3);
        this.buttonView = imageButton;
        this.containerView = new SingleAppVolumeButtonContainer(this.context, imageButton, this.config.getGestureExclusionPadding());
        onConfigurationChanged();
        this.isInitialized = true;
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = this.containerView;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.setVisibility(8);
        }
    }

    public final boolean isShowing() {
        return this.isVisible && this.isInitialized;
    }

    public final void onConfigurationChanged() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->onConfigurationChanged");
        }
        this.draggableBounds = ScreenUtils.getDraggableBounds(this.context, this.config.getButtonSize());
        this.stickableBounds = ScreenUtils.getStickableBounds(this.context, this.config.getButtonSize(), (int) this.config.getEdgeMargin());
        this.screenWidth = ScreenUtils.getScreenWidth(this.context);
        SingleAppVolumeButtonConfigRepository singleAppVolumeButtonConfigRepository = this.config;
        singleAppVolumeButtonConfigRepository.edgeMargin = singleAppVolumeButtonConfigRepository.calculateEdgeMargin();
    }

    public final void onUserSwitched() {
        int currentUser = ActivityManager.getCurrentUser();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(currentUser, "onUserSwitched, userId=", "SingleAppVolumeButtonVC-->", "SystemUi--Volume");
        }
        this.infoRepository.onUserSwitched(this.context.createContextAsUser(UserHandle.of(currentUser), 0));
    }

    public final void savePosition() {
        updateCurrentEdge();
        ImageButton imageButton = this.buttonView;
        if (imageButton != null) {
            Rect rect = this.stickableBounds;
            if (rect == null) {
                rect = null;
            }
            int iHeight = rect.height();
            if (iHeight <= 0) {
                LogUtil.w$default(8, LogUtil.TAG_VOLUME, TAG, "savePosition: invalid stickableBounds height");
                return;
            }
            float y = imageButton.getY();
            Rect rect2 = this.stickableBounds;
            float fCoerceIn = RangesKt___RangesKt.coerceIn((y - (rect2 != null ? rect2 : null).top) / iHeight, 0.0f, 1.0f);
            this.infoRepository.save(fCoerceIn, this.currentEdge);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                float x = imageButton.getX();
                float y2 = imageButton.getY();
                SingleAppVolumeButtonInfoRepository.Edge edge = this.currentEdge;
                int i = this.screenWidth;
                StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("savePosition: x=", x, ", y=", y2, ", percentY=");
                sbM.append(fCoerceIn);
                sbM.append(", edge=");
                sbM.append(edge);
                sbM.append(", screenWidth=");
                MobileInputLogger$.ExternalSyntheticOutline0.m(i, "SingleAppVolumeButtonVC-->", "SystemUi--Volume", sbM);
            }
        }
    }

    public final void setCallback(SingleAppVolumeButtonCallback singleAppVolumeButtonCallback) {
        this.callback = singleAppVolumeButtonCallback;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void show() {
        float leftEdgeX;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->show()");
        }
        if (!this.isInitialized) {
            LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "Not initialized, call init() first");
            return;
        }
        if (this.isVisible) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->Already showing");
                return;
            }
            return;
        }
        SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = this.callback;
        if (singleAppVolumeButtonCallback != null) {
            OplusVolumeDialogImpl oplusVolumeDialogImpl = ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0;
            oplusVolumeDialogImpl.setHasTopUi(true);
            oplusVolumeDialogImpl.mOplusVolumeDialogView.beforeShowSingleAppVolumeButton(oplusVolumeDialogImpl.mSingleAppVolumeButtonViewController.getInsetsListener());
        }
        this.isVisible = true;
        float fLoadPositionY = this.infoRepository.loadPositionY();
        SingleAppVolumeButtonInfoRepository.Edge edgeLoadAttachedEdge = this.infoRepository.loadAttachedEdge();
        this.currentEdge = edgeLoadAttachedEdge;
        ImageButton imageButton = this.buttonView;
        if (imageButton != null) {
            int iOrdinal = edgeLoadAttachedEdge.ordinal();
            if (iOrdinal == 0) {
                leftEdgeX = getLeftEdgeX();
            } else {
                if (iOrdinal != 1) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                }
                leftEdgeX = getRightEdgeX();
            }
            imageButton.setX(leftEdgeX);
        }
        ImageButton imageButton2 = this.buttonView;
        if (imageButton2 != null) {
            Rect rect = this.stickableBounds;
            if (rect == null) {
                rect = null;
            }
            float fHeight = rect.height() * fLoadPositionY;
            Rect rect2 = this.stickableBounds;
            if (rect2 == null) {
                rect2 = null;
            }
            imageButton2.setY(fHeight + rect2.top);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            ImageButton imageButton3 = this.buttonView;
            Float fValueOf = imageButton3 != null ? Float.valueOf(imageButton3.getX()) : null;
            ImageButton imageButton4 = this.buttonView;
            Float fValueOf2 = imageButton4 != null ? Float.valueOf(imageButton4.getY()) : null;
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->".concat("loadPosition: x=" + fValueOf + ", y=" + fValueOf2 + ", percentY=" + fLoadPositionY + ", edge=" + this.currentEdge));
        }
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = this.touchHandler;
        if (singleAppVolumeButtonTouchHandler != null) {
            singleAppVolumeButtonTouchHandler.setEnabled(true);
        }
        ImageButton imageButton5 = this.buttonView;
        if (imageButton5 != null) {
            imageButton5.setAlpha(1.0f);
        }
        SingleAppVolumeButtonContainer singleAppVolumeButtonContainer = this.containerView;
        int i = 0;
        if (singleAppVolumeButtonContainer != null) {
            singleAppVolumeButtonContainer.setVisibility(0);
        }
        ImageButton imageButton6 = this.buttonView;
        if (imageButton6 != null) {
            float flyInStartX = getFlyInStartX();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("performFlyIn: startX=", flyInStartX, ", targetX=", imageButton6.getX(), ", targetY="), imageButton6.getY(), "SingleAppVolumeButtonVC-->", "SystemUi--Volume");
            }
            SingleAppVolumeButtonContainer singleAppVolumeButtonContainer2 = this.containerView;
            if (singleAppVolumeButtonContainer2 != null) {
                singleAppVolumeButtonContainer2.notifyPositionChanged(this.currentEdge, imageButton6.getX(), imageButton6.getY());
            }
            SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = this.animationController;
            if (singleAppVolumeButtonAnimationController != null) {
                float x = imageButton6.getX();
                float y = imageButton6.getY();
                OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0 = new OplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0(i);
                oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                singleAppVolumeButtonAnimationController.flyIn(flyInStartX, x, y, oplusSingleAppVolumeButtonViewController$$ExternalSyntheticLambda0);
            }
        }
    }

    public final void updateCurrentEdge() {
        float f = this.screenWidth / 2.0f;
        ImageButton imageButton = this.buttonView;
        if (imageButton != null) {
            this.currentEdge = (((float) this.config.getButtonSize()) / 2.0f) + imageButton.getX() < f ? SingleAppVolumeButtonInfoRepository.Edge.LEFT : SingleAppVolumeButtonInfoRepository.Edge.RIGHT;
        }
    }
}
