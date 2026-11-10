package com.oplus.systemui.statusbar.notification.row;

import _COROUTINE.CoroutineDebuggingKt$;
import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.service.notification.StatusBarNotification;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.LogWindowBase;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.util.DumpUtilsKt;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.blur.NotificationPlatFormBlurParamsManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.material.HeadsUpShadowEdgeController;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.notification.row.stacking.KeyguardStackingBackgroundControl;
import com.oplus.systemui.statusbar.notification.OplusRoundImageDrawable;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.MultiPlatformBlurHelper;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBackgroundViewExtImp extends NotificationBackgroundViewExt {
    public static final Companion Companion = new Companion();
    private final RectF aodBounds;
    public float appearTranslation;
    public int clickAlpha;
    private Drawable clickEffectDrawable;
    public int clipBottom;
    public float clipBottomRadius;
    public int clipLeft;
    public int clipRight;
    public int clipTop;
    public float clipTopRadius;
    private NotificationEntry entry;
    private final KeyguardStackingBackgroundControl keyguardStackingBackgroundControl;
    public int lastBlurEffectHeight;
    public int lastBlurEffectWidth;
    private final Paint layerPaint;
    private final Lazy materialColorDrawable$delegate;
    public final MaterialColorManager materialColorManager;
    private final String nameForNormal;
    public final NotificationAodTransition notificationAodTransition;
    private NotificationBlurMode notificationBlurMode;
    private final Lazy notificationMaterialColorEx$delegate;
    private Drawable oplusColorBg;
    private OplusRoundImageDrawable oplusMcsBg;
    public final OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor;
    public final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    public final boolean oplusStyle;
    private Drawable panoramicAodDrawable;
    private HeadsUpShadowEdgeController shadowEdgeController;
    public int stackedNotificationMaskColor;
    public float stackedProgress;
    private Drawable tempDrawable;
    public final ViewBlurManager viewBlurManager;
    private ViewBlurProxy viewBlurProxy;
    private Drawable zoomAppIcon;
    public final int zoomAppIconHeight;
    public final int zoomAppIconWidth;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp$dump$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ IndentingPrintWriter $pw;
        public /* synthetic */ NotificationBackgroundViewExtImp this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.$pw.println("viewBlurProxy: " + this.this$0.getViewBlurProxy());
            NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = this.this$0;
            if (((NotificationBackgroundViewExt) notificationBackgroundViewExtImp).bgView != null) {
                this.$pw.println("bgView.actualHeight: " + notificationBackgroundViewExtImp.getBgView().getActualHeight());
                this.$pw.println("bgView.actualWidth: " + this.this$0.getBgView().getActualWidth());
                this.$pw.println("clipLeft: " + this.this$0.getClipLeft());
                this.$pw.println("clipRight: " + this.this$0.getClipRight());
                this.$pw.println("appearTranslation: " + this.this$0.getAppearTranslation());
                this.$pw.println("bgView.mClipTopAmount: " + this.this$0.getBgView().mClipTopAmount);
                this.$pw.println("bgView.mClipBottomAmount: " + this.this$0.getBgView().mClipBottomAmount);
                ExpandHelper$.ExternalSyntheticOutline0.m("bgView.mExpandAnimationRunning: ", this.this$0.getBgView().mExpandAnimationRunning, this.$pw);
                this.$pw.println("mKeyguardStackingBackgroundControl.targetProgress: " + this.this$0.getKeyguardStackingBackgroundControl().getTargetProgress());
                Drawable panoramicAodDrawable = this.this$0.getPanoramicAodDrawable();
                if (panoramicAodDrawable != null) {
                    this.$pw.println("has aod drawable,alpha=" + panoramicAodDrawable.getAlpha());
                }
                this.$pw.println("notificationBlurMode:" + this.this$0.notificationBlurMode);
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$BlTD-Wm-etZMMJD5VgreLhtr3q4, reason: not valid java name */
    public static Unit m450$r8$lambda$BlTDWmetZMMJD5VgreLhtr3q4(NotificationBackgroundViewExtImp notificationBackgroundViewExtImp) {
        NotificationEntry notificationEntry;
        View row;
        if (notificationBackgroundViewExtImp.oplusPanoramicAodRepository.getPanoramicAodProgress() == 1.0f && (notificationEntry = notificationBackgroundViewExtImp.entry) != null && (row = notificationEntry.getRow()) != null) {
            notificationBackgroundViewExtImp.notificationAodTransition.notifyDataChanged(row, "aodStateError");
        }
        return Unit.INSTANCE;
    }

    public NotificationBackgroundViewExtImp(Context context) {
        super(context);
        this.nameForNormal = context.getResources().getString(2131954822);
        this.aodBounds = new RectF();
        this.layerPaint = new Paint();
        OplusPanoramicAodRepository oplusPanoramicAodRepository = (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.notificationAodTransition = (NotificationAodTransition) DependencyEx.sDependency.getDependency(NotificationAodTransition.class);
        this.notificationMaterialColorEx$delegate = LazyKt__LazyJVMKt.lazy(new NotificationBackgroundViewExtImp$$ExternalSyntheticLambda1());
        NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2 notificationBackgroundViewExtImp$$ExternalSyntheticLambda2 = new NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2(0);
        notificationBackgroundViewExtImp$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStackingBackgroundControl = new KeyguardStackingBackgroundControl(context, oplusPanoramicAodRepository, notificationBackgroundViewExtImp$$ExternalSyntheticLambda2);
        this.oplusStyle = true;
        this.viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
        this.materialColorManager = (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
        this.oplusNotificationOverlayInteractor = (OplusNotificationOverlayInteractor) DependencyEx.sDependency.getDependency(OplusNotificationOverlayInteractor.class);
        this.lastBlurEffectWidth = -1;
        this.lastBlurEffectHeight = -1;
        this.zoomAppIconWidth = context.getResources().getDimensionPixelSize(2131170809);
        this.zoomAppIconHeight = context.getResources().getDimensionPixelSize(2131170808);
        NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2 notificationBackgroundViewExtImp$$ExternalSyntheticLambda3 = new NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2(1);
        notificationBackgroundViewExtImp$$ExternalSyntheticLambda3.f$0 = context;
        VarHandle.storeStoreFence();
        this.materialColorDrawable$delegate = LazyKt__LazyJVMKt.lazy(notificationBackgroundViewExtImp$$ExternalSyntheticLambda3);
    }

    private final Drawable getMaterialColorDrawable() {
        return (Drawable) this.materialColorDrawable$delegate.getValue();
    }

    private final NotificationMaterialColorEx getNotificationMaterialColorEx() {
        return (NotificationMaterialColorEx) this.notificationMaterialColorEx$delegate.getValue();
    }

    private final void setIconAlpha(float f) {
        Drawable drawable = this.zoomAppIcon;
        if (drawable == null || drawable == null) {
            return;
        }
        drawable.setAlpha(MathKt.roundToInt(f * 255.0f));
    }

    private final void setZoomRadiusForOplusDrawable(Drawable drawable) {
        if (getBgView().getParent() instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow parent = getBgView().getParent();
            if (parent.mRowEx.getIsZoomAnimating() && (drawable instanceof BackgroundBlurDrawable)) {
                ((BackgroundBlurDrawable) drawable).setCornerRadius(parent.mRowEx.getZoomTopRadius(), parent.mRowEx.getZoomTopRadius(), parent.mRowEx.getZoomBottomRadius(), parent.mRowEx.getZoomBottomRadius());
            }
        }
    }

    public void calculateAndDrawIcon(NotificationBackgroundView notificationBackgroundView, Canvas canvas) {
        if (this.zoomAppIcon == null) {
            return;
        }
        NotificationBackgroundView bgView = getBgView();
        int width = (int) ((notificationBackgroundView.getWidth() - notificationBackgroundView.getActualWidth()) / 2.0f);
        int actualWidth = notificationBackgroundView.getActualWidth() + width;
        int actualHeight = notificationBackgroundView.getActualHeight();
        if (bgView.mBottomIsRounded && bgView.mBottomAmountClips && !bgView.mExpandAnimationRunning) {
            actualHeight -= bgView.mClipBottomAmount;
        }
        setIconBounds(notificationBackgroundView, width, 0, actualWidth, actualHeight);
        drawIcon(canvas);
    }

    /* JADX WARN: Code duplicated, block: B:120:0x024b  */
    /* JADX WARN: Code duplicated, block: B:20:0x0047  */
    /* JADX WARN: Code duplicated, block: B:26:0x0056  */
    /* JADX WARN: Code duplicated, block: B:31:0x0062  */
    /* JADX WARN: Code duplicated, block: B:90:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:92:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:93:0x01cf  */
    public void draw(Canvas canvas, Drawable drawable) {
        ViewBlurProxy viewBlurProxy;
        Drawable blurDrawable;
        Drawable drawable2;
        boolean z;
        Integer numValueOf;
        int iAbs;
        boolean z2;
        ExpandableView expandableView;
        ExpandableNotificationRow row;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        HeadsUpShadowEdgeController headsUpShadowEdgeController;
        ExpandableNotificationRow row2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2;
        NotificationEntry notificationEntry;
        StatusBarNotification sbn;
        Notification notification;
        BlurConfig blurConfig;
        BlurConfig blurConfig2;
        Drawable panoramicAodDrawable;
        Function0 onDrawListener = getOnDrawListener();
        if (onDrawListener != null) {
            onDrawListener.invoke();
        }
        if (ScrimUtil.isBPAnimLevel() && (getBgView().getParent() instanceof OplusCustomRow)) {
            drawable2 = drawable;
        } else if (noColorized()) {
            viewBlurProxy = getViewBlurProxy();
            if (viewBlurProxy != null || (blurDrawable = viewBlurProxy.getBlurDrawable(drawable)) == null) {
                drawable2 = drawable;
            } else {
                drawable2 = blurDrawable;
            }
        } else {
            ViewBlurProxy viewBlurProxy2 = getViewBlurProxy();
            if (Intrinsics.areEqual(viewBlurProxy2 != null ? viewBlurProxy2.getBlurType() : null, ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                FeatureOption.INSTANCE.getClass();
                if (!FeatureOption.isExpRegion()) {
                    viewBlurProxy = getViewBlurProxy();
                    if (viewBlurProxy != null) {
                    }
                }
            }
            drawable2 = drawable;
        }
        if (drawable2 == null) {
            return;
        }
        if (getPanoramicAodDrawable() != null) {
            if (noColorized() && !(getBgView().getParent() instanceof OplusCustomRow)) {
                ViewBlurProxy viewBlurProxy3 = getViewBlurProxy();
                z = Intrinsics.areEqual(viewBlurProxy3 != null ? viewBlurProxy3.getBlurType() : null, ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) ? false : true;
            }
        }
        if (z) {
            this.aodBounds.set(0.0f, 0.0f, getBgView().getWidth(), getBgView().getActualHeight());
            numValueOf = Integer.valueOf(canvas.saveLayer(this.aodBounds, this.layerPaint));
        } else {
            numValueOf = null;
        }
        Drawable drawable3 = this.tempDrawable;
        if (drawable3 != null) {
            NotificationBackgroundView bgView = getBgView();
            drawable3.setBounds(0, 0, bgView.getWidth(), bgView.getActualHeight());
            drawable3.draw(canvas);
        }
        NotificationBackgroundViewExtImp$$ExternalSyntheticLambda0 notificationBackgroundViewExtImp$$ExternalSyntheticLambda0 = new NotificationBackgroundViewExtImp$$ExternalSyntheticLambda0();
        notificationBackgroundViewExtImp$$ExternalSyntheticLambda0.f$0 = z;
        notificationBackgroundViewExtImp$$ExternalSyntheticLambda0.f$1 = this;
        notificationBackgroundViewExtImp$$ExternalSyntheticLambda0.f$2 = drawable2;
        VarHandle.storeStoreFence();
        View bgView2 = getBgView();
        OplusRoundImageDrawable oplusRoundImageDrawable = this.oplusMcsBg;
        if (oplusRoundImageDrawable != null) {
            oplusRoundImageDrawable.setTranslateX((this.clipLeft + this.clipRight) - getBgView().getWidth());
        }
        if (drawable2.equals(drawable)) {
            notificationBackgroundViewExtImp$$ExternalSyntheticLambda0.invoke();
            setDrawableMaterialColor();
            super.draw(canvas, drawable);
            if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Rect bounds = drawable2.getBounds();
                String name = DebugToolKt.getName(getBgView(), false);
                StringBuilder sb = new StringBuilder("[draw] ");
                sb.append(drawable2);
                sb.append(" bounds=");
                sb.append(bounds);
                sb.append(" for ");
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(name, "NotificationBackgroundViewExt-->", "SystemUi--Common", sb);
            }
            z = z;
            expandableView = null;
        } else {
            int iAbs2 = this.clipBottom - ((int) Math.abs(this.appearTranslation));
            float f = this.appearTranslation;
            if (f > 0.0f) {
                iAbs2 = (int) (iAbs2 - f);
            }
            if (iAbs2 < 0) {
                iAbs2 = 0;
            }
            if (OneHandedObserver.Companion.getInstance().getOneHandedEnable()) {
                iAbs = ((NotificationBackgroundView) bgView2).mClipTopAmount;
            } else {
                iAbs = (int) Math.abs(this.appearTranslation);
                iAbs2 += iAbs;
            }
            setZoomRadiusForOplusDrawable(drawable2);
            ViewBlurProxy viewBlurProxy4 = getViewBlurProxy();
            if (!Intrinsics.areEqual(viewBlurProxy4 != null ? viewBlurProxy4.getBlurType() : null, ViewBlurProxy.BlurType.BlurTypeStatic.INSTANCE)) {
                ViewBlurProxy viewBlurProxy5 = getViewBlurProxy();
                if (!Intrinsics.areEqual(viewBlurProxy5 != null ? viewBlurProxy5.getBlurType() : null, ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
                    drawable2.setBounds(this.clipLeft, iAbs, this.clipRight, iAbs2);
                } else if (((NotificationBackgroundView) bgView2).mExpandAnimationRunning) {
                    int width = (int) ((bgView2.getWidth() - bgView2.getActualWidth()) / 2.0f);
                    drawable2.setBounds(width, iAbs, bgView2.getActualWidth() + width, iAbs2);
                } else {
                    z2 = false;
                    drawable2.setBounds(0, 0, bgView2.getWidth(), bgView2.getActualHeight());
                }
                z2 = false;
            } else if (((NotificationBackgroundView) bgView2).mExpandAnimationRunning) {
                int width2 = (int) ((bgView2.getWidth() - bgView2.getActualWidth()) / 2.0f);
                drawable2.setBounds(width2, iAbs, bgView2.getActualWidth() + width2, iAbs2);
                z2 = false;
            } else {
                z2 = false;
                drawable2.setBounds(0, 0, bgView2.getWidth(), bgView2.getActualHeight());
            }
            if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Rect bounds2 = drawable2.getBounds();
                String name2 = DebugToolKt.getName(getBgView(), z2);
                StringBuilder sb2 = new StringBuilder("[draw] ");
                sb2.append(drawable2);
                sb2.append(" bounds=");
                sb2.append(bounds2);
                sb2.append(" for ");
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(name2, "NotificationBackgroundViewExt-->", "SystemUi--Common", sb2);
            }
            if (noColorized()) {
                expandableView = null;
            } else {
                ViewBlurProxy viewBlurProxy6 = getViewBlurProxy();
                Drawable blurDrawable2 = viewBlurProxy6 != null ? viewBlurProxy6.getBlurDrawable(null) : null;
                if (!(blurDrawable2 instanceof PlatformBlurDrawable) || (notificationEntry = this.entry) == null || (sbn = notificationEntry.getSbn()) == null || (notification = sbn.getNotification()) == null) {
                    expandableView = null;
                } else {
                    int i = notification.color;
                    ViewBlurProxy viewBlurProxy7 = getViewBlurProxy();
                    if (viewBlurProxy7 == null || (blurConfig2 = viewBlurProxy7.getBlurConfig()) == null || i != blurConfig2.getBlurColor()) {
                        ViewBlurProxy viewBlurProxy8 = getViewBlurProxy();
                        if (viewBlurProxy8 == null || (blurConfig = viewBlurProxy8.getBlurConfig()) == null) {
                            expandableView = null;
                        } else {
                            ((PlatformBlurDrawable) blurDrawable2).setOverlayColor(blurConfig, i);
                            expandableView = null;
                            blurConfig.setInnerShadowParams(null);
                            blurConfig.setGradientStrokeLineParam(null);
                        }
                        ViewBlurProxy viewBlurProxy9 = getViewBlurProxy();
                        if (viewBlurProxy9 != null) {
                            viewBlurProxy9.applyBlurConfig();
                        }
                    } else {
                        expandableView = null;
                    }
                }
            }
            notificationBackgroundViewExtImp$$ExternalSyntheticLambda0.invoke();
            int iWidth = drawable2.getBounds().width();
            int iHeight = drawable2.getBounds().height();
            ViewBlurProxy viewBlurProxy10 = getViewBlurProxy();
            if (viewBlurProxy10 != null) {
                if (iWidth <= 0 || iHeight <= 0) {
                    logOnActualSizeSkip(iWidth, iHeight, "invalid size");
                } else if ((iWidth != this.lastBlurEffectWidth || iHeight != this.lastBlurEffectHeight) && Intrinsics.areEqual(viewBlurProxy10.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
                    int cardType = viewBlurProxy10.getBlurConfig().getCardType();
                    ViewBlurManager.CardType cardType2 = ViewBlurManager.CardType.NOTIFICATION;
                    if (cardType == 0) {
                        NotificationEntry notificationEntry2 = this.entry;
                        if (notificationEntry2 != null && (row2 = notificationEntry2.getRow()) != null && (oplusExpandableNotificationRowEx2 = row2.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx2).isHeadsUpView()) {
                            logOnActualSizeSkip(iWidth, iHeight, "headsUp notification");
                        } else if (getBgView().getVisibility() == 0) {
                            this.lastBlurEffectWidth = iWidth;
                            this.lastBlurEffectHeight = iHeight;
                            NotificationBlurMode notificationBlurMode = this.notificationBlurMode;
                            if (notificationBlurMode != null) {
                                NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
                                float f2 = iWidth;
                                float f3 = iHeight;
                                notificationPlatFormBlurParamsManager.updateGradientStrokeLineParamsForNotificationSize(getContext(), viewBlurProxy10.getBlurConfig().getGradientStrokeLineParam(), f2, f3, notificationBlurMode);
                                notificationPlatFormBlurParamsManager.updateInnerShadowParamsForNotificationSize(getContext(), viewBlurProxy10.getBlurConfig().getInnerShadowParams(), f2, f3, notificationBlurMode);
                            }
                            ExpandableView expandableView2 = drawable2 instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) drawable2 : expandableView;
                            if (expandableView2 != null) {
                                expandableView2.getBlurDrawable().setInnerShadowParams(viewBlurProxy10.getBlurConfig().getInnerShadowParams());
                                expandableView2.getBlurDrawable().setGradientStrokeLineParams(viewBlurProxy10.getBlurConfig().getGradientStrokeLineParam());
                            }
                        }
                    }
                }
            }
            drawable2.draw(canvas);
            if (ScrimUtil.isBPAnimLevel() && noColorized() && !(getBgView().getParent() instanceof OplusCustomRow)) {
                MaterialColorManager materialColorManager = this.materialColorManager;
                if (Color.alpha(materialColorManager.getCurrentMaterialColor()) > 0) {
                    Drawable materialColorDrawable = getMaterialColorDrawable();
                    if (materialColorDrawable != null) {
                        materialColorDrawable.setTint(materialColorManager.getCurrentMaterialColor());
                    }
                    Drawable materialColorDrawable2 = getMaterialColorDrawable();
                    if (materialColorDrawable2 != null) {
                        materialColorDrawable2.setBounds(drawable2.getBounds());
                    }
                    Drawable materialColorDrawable3 = getMaterialColorDrawable();
                    if (materialColorDrawable3 != null) {
                        materialColorDrawable3.draw(canvas);
                    }
                }
            }
            LogWindowBase.Companion.printViewBoundary(LogWindowBase.TestPaintBoundary.NbvDraw, canvas, new Rect(this.clipLeft, iAbs, this.clipRight, iAbs2));
            drawClickEffectIfNeed(bgView2.getLeft(), iAbs, bgView2.getRight(), iAbs2, canvas);
            NotificationEntry notificationEntry3 = this.entry;
            if (notificationEntry3 != null && (row = notificationEntry3.getRow()) != null && (oplusExpandableNotificationRowEx = row.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isHeadsUpView() && (headsUpShadowEdgeController = this.shadowEdgeController) != null) {
                headsUpShadowEdgeController.draw(canvas, bgView2, drawable2.getBounds());
            }
        }
        getBgView();
        if (getStackedNotificationMaskColor() != 0) {
            Drawable drawable4 = this.oplusColorBg;
            if (drawable4 != null) {
                drawable4.setBounds(drawable2.getBounds());
                drawable4.draw(canvas);
            }
            if (!drawable2.equals(drawable) && noColorized()) {
                this.keyguardStackingBackgroundControl.draw(canvas, drawable2.getBounds());
            }
        }
        if (z && (panoramicAodDrawable = getPanoramicAodDrawable()) != null) {
            if (drawable2.getBounds().width() == 0) {
                NotificationBackgroundView bgView3 = getBgView();
                panoramicAodDrawable.setBounds(0, 0, bgView3.getWidth(), bgView3.getActualHeight());
            } else {
                panoramicAodDrawable.setBounds(drawable2.getBounds());
            }
            if (getStackedProgress() == 1.0f && this.oplusPanoramicAodRepository.getPanoramicAodProgress() != 0.0f) {
                ExpandableView parent = getBgView().getParent();
                ExpandableView expandableView3 = parent instanceof ExpandableView ? parent : expandableView;
                if (expandableView3 != null && !this.oplusNotificationOverlayInteractor.isOverlayShowing(expandableView3, true)) {
                    panoramicAodDrawable.getBounds().inset(-1, -1);
                }
            }
            panoramicAodDrawable.draw(canvas);
        }
        if (numValueOf != null) {
            canvas.restoreToCount(numValueOf.intValue());
        }
    }

    public void drawClickEffectIfNeed(int i, int i2, int i3, int i4, Canvas canvas) {
        Drawable drawable;
        OplusRoundImageDrawable oplusRoundImageDrawable = this.oplusMcsBg;
        if (oplusRoundImageDrawable != null) {
            oplusRoundImageDrawable.setNightMode(getNotificationMaterialColorEx().getCurrentMaterialColor().getValue() != MaterialColorMode.NORMAL_LIGHT);
            oplusRoundImageDrawable.setInitSize(getBgView().getWidth(), getBgView().getActualHeight());
            oplusRoundImageDrawable.setBounds(i, i2, i3, i4);
            if (canvas != null) {
                oplusRoundImageDrawable.draw(canvas);
            }
        }
        Drawable drawable2 = this.clickEffectDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(i, i2, i3, i4);
            drawable2.setAlpha(this.clickAlpha);
            if (canvas == null || (drawable = this.clickEffectDrawable) == null) {
                return;
            }
            drawable.draw(canvas);
        }
    }

    public void drawIcon(Canvas canvas) {
        Drawable drawable = this.zoomAppIcon;
        if (drawable == null || drawable == null) {
            return;
        }
        drawable.draw(canvas);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriterAsIndenting = DumpUtilsKt.asIndenting(printWriter);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$pw = indentingPrintWriterAsIndenting;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        DumpUtilsKt.withIncreasedIndent(indentingPrintWriterAsIndenting, anonymousClass1);
    }

    public final float getAppearTranslation() {
        return this.appearTranslation;
    }

    public int getClickEffectAlpha() {
        return this.clickAlpha;
    }

    public final int getClipBottom() {
        return this.clipBottom;
    }

    public final float getClipBottomRadius() {
        return this.clipBottomRadius;
    }

    public final int getClipLeft() {
        return this.clipLeft;
    }

    public final int getClipRight() {
        return this.clipRight;
    }

    public final int getClipTop() {
        return this.clipTop;
    }

    public final float getClipTopRadius() {
        return this.clipTopRadius;
    }

    public boolean getGlobalVisibleRect(Rect rect, Point point) {
        int width = getBgView().getWidth();
        int actualHeight = getBgView().getActualHeight();
        if (width <= 0 || actualHeight <= 0) {
            return false;
        }
        rect.set(0, 0, width, actualHeight);
        if (point != null) {
            point.set(-getBgView().getScrollX(), -getBgView().getScrollY());
        }
        return getBgView().getParent() == null || getBgView().getParent().getChildVisibleRect(getBgView(), rect, point);
    }

    public final KeyguardStackingBackgroundControl getKeyguardStackingBackgroundControl() {
        return this.keyguardStackingBackgroundControl;
    }

    public final Drawable getOplusColorBg() {
        return this.oplusColorBg;
    }

    public boolean getOplusStyle() {
        return this.oplusStyle;
    }

    public final Drawable getPanoramicAodDrawable() {
        if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 0.0f) {
            return null;
        }
        return this.panoramicAodDrawable;
    }

    public int getStackedNotificationMaskColor() {
        return this.stackedNotificationMaskColor;
    }

    public float getStackedProgress() {
        return this.stackedProgress;
    }

    public final int getStackedType() {
        return this.keyguardStackingBackgroundControl.getTargetType();
    }

    public final Drawable getTempDrawable() {
        return this.tempDrawable;
    }

    public ViewBlurProxy getViewBlurProxy() {
        return this.viewBlurProxy;
    }

    public final void init() {
        Drawable drawableMutate;
        Drawable drawable = getContext().getDrawable(2131235714);
        Drawable drawable2 = this.clickEffectDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        getBgView().unscheduleDrawable(this.clickEffectDrawable);
        if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
            drawableMutate = null;
        } else {
            drawableMutate.setCallback(getBgView());
            if (drawableMutate instanceof RippleDrawable) {
                ((RippleDrawable) drawableMutate).setForceSoftware(true);
            }
            Drawable drawable3 = this.clickEffectDrawable;
            if ((drawable3 instanceof LayerDrawable) && !(((LayerDrawable) drawable3).getDrawable(0) instanceof BackgroundBlurDrawable)) {
                Drawable drawable4 = ((LayerDrawable) this.clickEffectDrawable).getDrawable(0);
                GradientDrawable gradientDrawable = drawable4 instanceof GradientDrawable ? (GradientDrawable) drawable4 : null;
                if (gradientDrawable != null) {
                    gradientDrawable.setCornerRadii(getBgView().mCornerRadii);
                }
            }
        }
        this.clickEffectDrawable = drawableMutate;
        getBgView().invalidate();
        Drawable smoothDrawable = SmoothRoundExKt.getSmoothDrawable(getContext(), 2131102886);
        this.oplusColorBg = smoothDrawable;
        if (smoothDrawable != null) {
            smoothDrawable.setTint(getStackedNotificationMaskColor());
        }
        setViewBlurProxy(this.viewBlurManager.getBlurProxyForView(getBgView()));
        this.keyguardStackingBackgroundControl.setViewBlurProxy(getViewBlurProxy());
        this.keyguardStackingBackgroundControl.setBackgroundView(getBgView());
        if (this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 0.0f) {
            this.panoramicAodDrawable = null;
        }
    }

    public final void logOnActualSizeSkip(int i, int i2, String str) {
        String name = ((NotificationBackgroundViewExt) this).bgView != null ? DebugToolKt.getName(getBgView(), false) : "uninitialized";
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "[onActualSizeChanged] skip: ", str, ", w=", ", h=");
            sbM.append(i2);
            sbM.append(", view=");
            sbM.append(name);
            Log.d("SystemUi--Notification", "NotificationBackgroundViewExt-->".concat(sbM.toString()));
        }
    }

    public final boolean noColorized() {
        NotificationEntry notificationEntry;
        ExpandableNotificationRow row;
        NotificationEntry notificationEntry2;
        StatusBarNotification sbn;
        Notification notification;
        StatusBarNotification sbn2;
        StatusBarNotification sbn3;
        Notification notification2;
        NotificationEntry notificationEntry3 = this.entry;
        boolean z = false;
        if (notificationEntry3 != null && (sbn3 = notificationEntry3.getSbn()) != null && (notification2 = sbn3.getNotification()) != null && notification2.isColorized()) {
            z = true;
        }
        if (z && (notificationEntry = this.entry) != null && (row = notificationEntry.getRow()) != null && row.getCurrentBackgroundTint() == 0 && (notificationEntry2 = this.entry) != null && (sbn = notificationEntry2.getSbn()) != null && (notification = sbn.getNotification()) != null && notification.color == 0) {
            NotificationEntry notificationEntry4 = this.entry;
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "set background color error packageName:", (notificationEntry4 == null || (sbn2 = notificationEntry4.getSbn()) == null) ? null : sbn2.getPackageName(), LogUtil.TAG_COMMON, "NotificationBackgroundViewExt");
        }
        return !z;
    }

    public void notifyBlurParamChanged(NotificationBlurMode notificationBlurMode) {
        String str;
        ViewBlurProxy viewBlurProxy = getViewBlurProxy();
        if (viewBlurProxy == null) {
            return;
        }
        if (!ScrimUtil.isMiddleOrHighAnimLevel(getContext())) {
            this.notificationBlurMode = null;
            return;
        }
        int cardType = viewBlurProxy.getBlurConfig().getCardType();
        ViewBlurManager.CardType cardType2 = ViewBlurManager.CardType.NOTIFICATION;
        if (cardType != 0) {
            return;
        }
        if (notificationBlurMode.getNoBlur()) {
            this.notificationBlurMode = null;
            return;
        }
        boolean normalDebug = LogUtil.getNormalDebug();
        OplusPanoramicAodRepository oplusPanoramicAodRepository = this.oplusPanoramicAodRepository;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            NotificationBlurMode notificationBlurMode2 = this.notificationBlurMode;
            float panoramicAodProgress = oplusPanoramicAodRepository.getPanoramicAodProgress();
            NotificationEntry notificationEntry = this.entry;
            Log.d("SystemUi--Notification", "NotificationBackgroundViewExt-->".concat("notifyBlurParamChanged:old=" + notificationBlurMode2 + " new=" + notificationBlurMode + " panoramicAodProgress=" + panoramicAodProgress + " " + (notificationEntry != null ? notificationEntry.getKey() : null)));
        }
        if (notificationBlurMode == NotificationBlurMode.HEADS_UP_MATERIAL_LIGHT || notificationBlurMode == NotificationBlurMode.HEADS_UP_MATERIAL_DARK) {
            this.notificationBlurMode = notificationBlurMode;
            this.viewBlurManager.refreshMotionMixConfig(viewBlurProxy, notificationBlurMode);
            HeadsUpShadowEdgeController headsUpShadowEdgeController = this.shadowEdgeController;
            if (headsUpShadowEdgeController != null) {
                headsUpShadowEdgeController.refreshUiStyle(notificationBlurMode.isHeadsUpBlurDark());
            }
            viewBlurProxy.getView().postInvalidateOnAnimation();
            return;
        }
        NotificationBlurMode notificationBlurMode3 = this.notificationBlurMode;
        if (notificationBlurMode3 != notificationBlurMode) {
            NotificationBlurMode notificationBlurMode4 = NotificationBlurMode.WALLPAPER_A;
            boolean z = true;
            boolean z2 = notificationBlurMode == notificationBlurMode4 || notificationBlurMode3 == notificationBlurMode4;
            NotificationBlurMode notificationBlurMode5 = NotificationBlurMode.WALLPAPER_B;
            boolean z3 = notificationBlurMode == notificationBlurMode5 || notificationBlurMode3 == notificationBlurMode5;
            boolean z4 = notificationBlurMode3 != null && notificationBlurMode3.getLight() == notificationBlurMode.getLight();
            NotificationBlurMode notificationBlurMode6 = this.notificationBlurMode;
            boolean z5 = notificationBlurMode6 != null && notificationBlurMode6.getShade() == notificationBlurMode.getShade();
            this.notificationBlurMode = notificationBlurMode;
            if (!z4 || !z5 || z2 || z3) {
                if (!z4) {
                    updatePlatformMixConfig(viewBlurProxy, notificationBlurMode, "nightModeChange", true);
                    BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
                    NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
                    blurConfig.setInnerShadowParams(notificationPlatFormBlurParamsManager.getInnerShadowParams(cardType2, notificationBlurMode));
                    viewBlurProxy.getBlurConfig().setGradientStrokeLineParam(notificationPlatFormBlurParamsManager.getGradientStrokeLineParams(cardType2, notificationBlurMode));
                    this.lastBlurEffectWidth = -1;
                    this.lastBlurEffectHeight = -1;
                    if (oplusPanoramicAodRepository.getPanoramicAodProgress() == 1.0f) {
                        notificationPlatFormBlurParamsManager.applyAodProgressToNotificationBlurParams(viewBlurProxy.getBlurConfig().getGradientStrokeLineParam(), viewBlurProxy.getBlurConfig().getInnerShadowParams(), 1.0f, notificationBlurMode);
                    }
                } else if (!z5 || z2 || z3) {
                    viewBlurProxy.getBlurConfig().setGradientStrokeLineParam(NotificationPlatFormBlurParamsManager.INSTANCE.getGradientStrokeLineParams(cardType2, notificationBlurMode));
                    this.lastBlurEffectWidth = -1;
                    this.lastBlurEffectHeight = -1;
                    if (z2) {
                        str = "isWallpaperRegionA";
                    } else {
                        str = z3 ? "isWallpaperRegionB" : "shadeStatusChange";
                    }
                    if (!z2 && !z3) {
                        z = false;
                    }
                    updatePlatformMixConfig(viewBlurProxy, notificationBlurMode, str, z);
                    if (notificationBlurMode.getShade()) {
                        this.keyguardStackingBackgroundControl.updateKeyguardStackingBackgroundEnable(false);
                    }
                }
                viewBlurProxy.applyBlurConfig();
            }
        }
    }

    public final void notifyMaterialColorChanged() {
        if (ScrimUtil.isBAnimLevel() || (this.viewBlurManager.isSinkFeatureNonShadeState() && ScrimUtil.isBPAnimLevel())) {
            setDrawableMaterialColor();
        } else if (ScrimUtil.isBPAnimLevel()) {
            getBgView().invalidate();
        }
    }

    public final void onHeadsUpStateChanged(boolean z) {
        BlurConfig blurConfig;
        HeadsUpShadowEdgeController headsUpShadowEdgeController = this.shadowEdgeController;
        if (!z) {
            if (headsUpShadowEdgeController != null) {
                headsUpShadowEdgeController.release();
            }
            this.shadowEdgeController = null;
            return;
        }
        if (headsUpShadowEdgeController == null) {
            this.shadowEdgeController = HeadsUpShadowEdgeController.Companion.create(getContext());
        }
        HeadsUpShadowEdgeController headsUpShadowEdgeController2 = this.shadowEdgeController;
        if (headsUpShadowEdgeController2 != null) {
            NotificationBackgroundView bgView = getBgView();
            Context context = getContext();
            ViewBlurProxy blurProxyForView = this.viewBlurManager.getBlurProxyForView(getBgView());
            float cornerRadius = (blurProxyForView == null || (blurConfig = blurProxyForView.getBlurConfig()) == null) ? 0.0f : blurConfig.getCornerRadius();
            NotificationMaterialColorEx notificationMaterialColorEx = getNotificationMaterialColorEx();
            NotificationEntry notificationEntry = this.entry;
            HeadsUpShadowEdgeController.show$default(headsUpShadowEdgeController2, bgView, context, cornerRadius, null, notificationMaterialColorEx.getBlurModeForRow(notificationEntry != null ? notificationEntry.getRow() : null, false).isHeadsUpBlurDark(), 8);
        }
    }

    public final void onVisibilityAggregated(boolean z) {
        ViewBlurProxy viewBlurProxy = getViewBlurProxy();
        if (viewBlurProxy != null) {
            viewBlurProxy.onVisibilityAggregated(z);
        }
    }

    public final void setAppearTranslation(float f) {
        this.appearTranslation = f;
    }

    public void setClickEffectAlpha(int i) {
        if ((i < 0 ? 0 : i) != this.clickAlpha) {
            this.clickAlpha = i;
            getBgView().invalidate();
        }
    }

    public final void setClipBottom(int i) {
        this.clipBottom = i;
    }

    public final void setClipBottomRadius(float f) {
        this.clipBottomRadius = f;
    }

    public final void setClipLeft(int i) {
        this.clipLeft = i;
    }

    public final void setClipRight(int i) {
        this.clipRight = i;
    }

    public final void setClipTop(int i) {
        this.clipTop = i;
    }

    public final void setClipTopRadius(float f) {
        this.clipTopRadius = f;
    }

    public final void setDrawableMaterialColor() {
        NotificationEntry notificationEntry;
        ExpandableNotificationRow row;
        ExpandableNotificationRow row2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        boolean z = (ScrimUtil.isBAnimLevel() || (this.viewBlurManager.isSinkFeatureNonShadeState() && ScrimUtil.isBPAnimLevel())) && !(getBgView().getParent() instanceof OplusCustomRow);
        boolean zNoColorized = noColorized();
        if (!zNoColorized || !z) {
            if (zNoColorized || !z || (notificationEntry = this.entry) == null || (row = notificationEntry.getRow()) == null) {
                return;
            }
            int currentBackgroundTint = row.getCurrentBackgroundTint();
            if (getBgView().mTintColor == 0 || currentBackgroundTint == getBgView().mTintColor) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(currentBackgroundTint, "NotificationBackgroundViewExt-->", "SystemUi--Notification", ConstraintSet$.ExternalSyntheticOutline0.m(getBgView().mTintColor, "reSetRowTint;key: ", row.getLoggingKey(), ", tintColor:", ", backgroundTint:"));
            }
            getBgView().setTint(currentBackgroundTint);
            return;
        }
        NotificationEntry notificationEntry2 = this.entry;
        if (notificationEntry2 != null && (row2 = notificationEntry2.getRow()) != null && (oplusExpandableNotificationRowEx = row2.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isHeadsUpView()) {
            getBgView().setTint(getContext().getColor(2131102886));
            return;
        }
        int currentMaterialColor = this.materialColorManager.getCurrentMaterialColor();
        if (currentMaterialColor == getBgView().mTintColor) {
            return;
        }
        if (currentMaterialColor != 0) {
            getBgView().setTint(currentMaterialColor);
        } else {
            getBgView().setTint(getContext().getColor(2131102886));
        }
    }

    public boolean setExpandAnimationSize(ExpandableNotificationRow expandableNotificationRow, NotificationBackgroundView notificationBackgroundView, LaunchAnimationParameters launchAnimationParameters, int i) {
        if (!expandableNotificationRow.mRowEx.getIsZoomAnimating() || expandableNotificationRow.getScaleX() == 0.0f) {
            return false;
        }
        int width = (int) (launchAnimationParameters.getWidth() / expandableNotificationRow.getScaleX());
        notificationBackgroundView.mExpandAnimationHeight = i;
        notificationBackgroundView.mExpandAnimationWidth = width;
        notificationBackgroundView.invalidate();
        setIconAlpha(launchAnimationParameters.getIconalpha());
        return true;
    }

    public void setIcon(Drawable drawable) {
        this.zoomAppIcon = drawable;
        setIconAlpha(0.0f);
    }

    public void setIconBounds(NotificationBackgroundView notificationBackgroundView, int i, int i2, int i3, int i4) {
        if (this.zoomAppIcon != null) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int scaleX = (int) ((this.zoomAppIconWidth * 1.0f) / (notificationBackgroundView.getParent() instanceof View ? ((View) notificationBackgroundView.getParent()).getScaleX() : 1.0f));
            Drawable drawable = this.zoomAppIcon;
            if (drawable != null) {
                int iM = (int) (ListPopupWindow$.ExternalSyntheticOutline0.m(i5, scaleX, 2, i) * 1.0f);
                int i7 = this.zoomAppIconHeight;
                drawable.setBounds(iM, ListPopupWindow$.ExternalSyntheticOutline0.m(i6, i7, 2, i2), (int) ((((i5 + scaleX) / 2) + i) * 1.0f), ((i6 + i7) / 2) + i2);
            }
        }
    }

    public void setOplusMcsBg(NotificationEntry notificationEntry) {
        OplusRoundImageDrawable oplusMcsBg;
        this.entry = notificationEntry;
        OplusNotificationEntryEx ex = notificationEntry.getEx();
        if (!(ex instanceof OplusNotificationEntryExImpl) || (oplusMcsBg = ((OplusNotificationEntryExImpl) ex).getOplusMcsBg()) == null) {
            return;
        }
        oplusMcsBg.setCornerRadii(getBgView().mCornerRadii);
        this.oplusMcsBg = oplusMcsBg;
        getBgView().invalidate();
    }

    public final void setPanoramicAodDrawable(Drawable drawable) {
        this.panoramicAodDrawable = drawable;
    }

    public void setStackedNotificationMaskColor(int i) {
        View row;
        int i2 = this.stackedNotificationMaskColor;
        if (i2 == i) {
            return;
        }
        if (i2 == 0 && this.oplusPanoramicAodRepository.getPanoramicAodProgress() == 1.0f) {
            this.stackedNotificationMaskColor = i;
            NotificationEntry notificationEntry = this.entry;
            if (notificationEntry != null && (row = notificationEntry.getRow()) != null) {
                this.notificationAodTransition.notifyDataChanged(row, "stackColor changed");
            }
        }
        this.stackedNotificationMaskColor = i;
        Drawable drawable = this.oplusColorBg;
        if (drawable != null) {
            drawable.setTint(i);
        }
        getBgView().invalidate();
    }

    public void setStackedProgress(float f) {
        if (this.stackedProgress == f) {
            return;
        }
        this.stackedProgress = f;
        this.keyguardStackingBackgroundControl.setTargetProgress(f);
    }

    public final void setTempDrawable(Drawable drawable) {
        this.tempDrawable = drawable;
    }

    public void setViewBlurProxy(ViewBlurProxy viewBlurProxy) {
        this.viewBlurProxy = viewBlurProxy;
    }

    public final boolean shouldInterceptInvalidate() {
        return !(getBgView().mClipTopAmount + getBgView().mClipBottomAmount < getBgView().getActualHeight() || getBgView().mExpandAnimationRunning);
    }

    public String toString() {
        return "NotificationBackgroundViewExtImp(nameForNormal=" + this.nameForNormal + ", " + getViewBlurProxy() + ")";
    }

    public final void updatePlatformMixConfig(ViewBlurProxy viewBlurProxy, NotificationBlurMode notificationBlurMode, String str, boolean z) {
        BlurConfig blurConfig;
        BlurConfig blurConfig2 = viewBlurProxy.getBlurConfig();
        NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
        NotificationEntry notificationEntry = this.entry;
        String strM = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(str, " ", notificationEntry != null ? notificationEntry.getKey() : null);
        ViewBlurManager viewBlurManager = this.viewBlurManager;
        blurConfig2.setPlatformMixConfig(notificationPlatFormBlurParamsManager.normalCardPlatformMixConfig(notificationBlurMode, viewBlurManager.useMotionBlur(), strM));
        if (z) {
            ViewBlurProxy.updateMultiPlatformMixConfig$default(viewBlurProxy, 0.0f, 7);
            ExpandableView parent = getBgView().getParent();
            ExpandableView expandableView = parent instanceof ExpandableView ? parent : null;
            if (expandableView != null) {
                OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor = this.oplusNotificationOverlayInteractor;
                if (oplusNotificationOverlayInteractor.isOverlayShowing(expandableView, true)) {
                    ViewBlurProxy overlayBackgroundViewBlurProxy = oplusNotificationOverlayInteractor.getOverlayBackgroundViewBlurProxy();
                    if (overlayBackgroundViewBlurProxy != null) {
                        ViewBlurProxy.updateMultiPlatformMixConfig$default(overlayBackgroundViewBlurProxy, 0.0f, 7);
                    }
                    if (overlayBackgroundViewBlurProxy != null && (blurConfig = overlayBackgroundViewBlurProxy.getBlurConfig()) != null) {
                        NotificationEntry notificationEntry2 = this.entry;
                        blurConfig.setPlatformMixConfig(notificationPlatFormBlurParamsManager.normalCardPlatformMixConfig(notificationBlurMode, viewBlurManager.useMotionBlur(), CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(str, " ", notificationEntry2 != null ? notificationEntry2.getKey() : null)));
                    }
                    if (overlayBackgroundViewBlurProxy != null) {
                        overlayBackgroundViewBlurProxy.applyBlurConfig();
                    }
                }
            }
        }
    }

    public final void updateRadius(float f, float f2) {
        ViewBlurProxy viewBlurProxy;
        BlurConfig blurConfig;
        BlurConfig blurConfig2;
        ViewBlurProxy viewBlurProxy2 = getViewBlurProxy();
        Float fValueOf = (viewBlurProxy2 == null || (blurConfig2 = viewBlurProxy2.getBlurConfig()) == null) ? null : Float.valueOf(blurConfig2.getLeftTopCornerRadius());
        ViewBlurProxy viewBlurProxy3 = getViewBlurProxy();
        Float fValueOf2 = (viewBlurProxy3 == null || (blurConfig = viewBlurProxy3.getBlurConfig()) == null) ? null : Float.valueOf(blurConfig.getLeftBottomCornerRadius());
        if (Intrinsics.areEqual(f, fValueOf) && Intrinsics.areEqual(f2, fValueOf2)) {
            return;
        }
        if (f == 0.0f && f2 == 0.0f) {
            return;
        }
        if ((Math.max(Math.abs(f - (fValueOf != null ? fValueOf.floatValue() : 0.0f)), Math.abs(f2 - (fValueOf2 != null ? fValueOf2.floatValue() : 0.0f))) >= 0.5f || f == 0.0f || f2 == 0.0f) && (viewBlurProxy = getViewBlurProxy()) != null) {
            BlurConfig blurConfig3 = viewBlurProxy.getBlurConfig();
            blurConfig3.setLeftTopCornerRadius(f);
            blurConfig3.setRightTopCornerRadius(f);
            blurConfig3.setLeftBottomCornerRadius(f2);
            blurConfig3.setRightBottomCornerRadius(f2);
            blurConfig3.getGradientStrokeCornerParam().setRadius(f);
            HeadsUpShadowEdgeController headsUpShadowEdgeController = this.shadowEdgeController;
            if (headsUpShadowEdgeController != null) {
                headsUpShadowEdgeController.refresh(f);
            }
            MultiPlatformBlurHelper multiPlatformBlurHelper = viewBlurProxy.getMultiPlatformBlurHelper();
            NotificationBackgroundViewExtImp$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new NotificationBackgroundViewExtImp$.ExternalSyntheticLambda4();
            externalSyntheticLambda4.f$0 = f;
            externalSyntheticLambda4.f$1 = f2;
            VarHandle.storeStoreFence();
            multiPlatformBlurHelper.forEachStackBlurConfig(externalSyntheticLambda4);
            Drawable blurDrawable = viewBlurProxy.getBlurDrawable(null);
            PlatformBlurDrawable platformBlurDrawable = blurDrawable instanceof PlatformBlurDrawable ? (PlatformBlurDrawable) blurDrawable : null;
            BlurDrawable blurDrawable2 = platformBlurDrawable != null ? platformBlurDrawable.getBlurDrawable() : null;
            if (blurDrawable2 != null) {
                blurDrawable2.setCornerParams(viewBlurProxy.getBlurConfig().getGradientStrokeCornerParam());
            } else {
                viewBlurProxy.applyBlurConfig();
            }
        }
    }

    public boolean verifyDrawablePlus(Drawable drawable) {
        ViewBlurProxy viewBlurProxy = getViewBlurProxy();
        return viewBlurProxy != null && viewBlurProxy.verifyDrawable(drawable);
    }
}
