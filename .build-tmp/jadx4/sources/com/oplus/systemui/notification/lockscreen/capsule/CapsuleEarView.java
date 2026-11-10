package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.PathParser;
import androidx.appcompat.widget.AppCompatImageView;
import com.android.systemui.DependencyEx;
import com.android.systemui.util.kotlin.UtilsKt;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleBlurHelper;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleScrimAlphaHelper;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ResourceUtils;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleEarView extends AppCompatImageView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleEarView";
    private final Paint clearPaint;
    private final Paint defaultBgPaint;
    private Rect drawBound;
    private final Matrix drawableMatrix;
    private Rect earConfigBound;
    private Path earPath;
    private Path inversePath;
    public final boolean isLeftEar;
    private MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback;
    private final Lazy materialColorManager$delegate;
    private final Lazy oplusPanoramicAodRepository$delegate;
    private final Lazy originContext$delegate;
    private final Paint scrimPaint;
    private String vectorPathData;
    public final ViewBlurManager viewBlurManager;
    private ViewBlurProxy viewBlurProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CapsuleEarView(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.isLeftEar = z;
        ViewBlurManager viewBlurManager = (ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class);
        this.viewBlurManager = viewBlurManager;
        final int i = 0;
        this.materialColorManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        CapsuleEarView.Companion companion = CapsuleEarView.Companion;
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 1:
                        CapsuleEarView.Companion companion2 = CapsuleEarView.Companion;
                        return (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
                    default:
                        CapsuleEarView.Companion companion3 = CapsuleEarView.Companion;
                        return (Context) DependencyEx.sDependency.getDependency(Context.class);
                }
            }
        });
        final int i2 = 1;
        this.oplusPanoramicAodRepository$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        CapsuleEarView.Companion companion = CapsuleEarView.Companion;
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 1:
                        CapsuleEarView.Companion companion2 = CapsuleEarView.Companion;
                        return (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
                    default:
                        CapsuleEarView.Companion companion3 = CapsuleEarView.Companion;
                        return (Context) DependencyEx.sDependency.getDependency(Context.class);
                }
            }
        });
        final int i3 = 2;
        this.originContext$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarView$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        CapsuleEarView.Companion companion = CapsuleEarView.Companion;
                        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
                    case 1:
                        CapsuleEarView.Companion companion2 = CapsuleEarView.Companion;
                        return (OplusPanoramicAodRepository) DependencyEx.sDependency.getDependency(OplusPanoramicAodRepository.class);
                    default:
                        CapsuleEarView.Companion companion3 = CapsuleEarView.Companion;
                        return (Context) DependencyEx.sDependency.getDependency(Context.class);
                }
            }
        });
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        this.earConfigBound = new Rect(0, 0, capsuleConfig.getEarWidth(), capsuleConfig.getItemHeight());
        this.drawBound = new Rect(0, 0, capsuleConfig.getEarWidth(), capsuleConfig.getItemHeight());
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        paint.setColor(-1);
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.clearPaint = paint;
        Paint paint2 = new Paint();
        paint2.setColor(-16777216);
        paint2.setStyle(style);
        paint2.setAlpha(0);
        this.scrimPaint = paint2;
        Paint paint3 = new Paint();
        paint3.setColor(getOriginContext().getColor(2131102886));
        paint3.setStyle(style);
        this.defaultBgPaint = paint3;
        this.drawableMatrix = new Matrix();
        ViewBlurProxy viewBlurProxyRequireBlurProxyForView$default = ViewBlurManager.requireBlurProxyForView$default(viewBlurManager, this, ViewBlurManager.CardType.CAPSULE_EAR, 0.0f, null, 8);
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setRadiusWeight(null);
        viewBlurProxyRequireBlurProxyForView$default.getBlurConfig().setCornerRadius(capsuleConfig.getDefaultRadius());
        viewBlurProxyRequireBlurProxyForView$default.setBlurType(ScrimUtil.isMiddleOrHighAnimLevel(getContext()) ? ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE : ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
        this.viewBlurProxy = viewBlurProxyRequireBlurProxyForView$default;
        viewBlurProxyRequireBlurProxyForView$default.applyBlurConfig();
        this.vectorPathData = ResourceUtils.Companion.getVectorDrawablePathData(z ? 2131235918 : 2131235919, context);
        paint3.setColor(getBackgroundColor(getOriginContext()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void access$updateBackgroundDrawableTint(CapsuleEarView capsuleEarView) {
        capsuleEarView.defaultBgPaint.setColor(capsuleEarView.getBackgroundColor(capsuleEarView.getOriginContext()));
        capsuleEarView.invalidate();
    }

    private final MaterialColorManager getMaterialColorManager() {
        return (MaterialColorManager) this.materialColorManager$delegate.getValue();
    }

    private final OplusPanoramicAodRepository getOplusPanoramicAodRepository() {
        return (OplusPanoramicAodRepository) this.oplusPanoramicAodRepository$delegate.getValue();
    }

    private final Context getOriginContext() {
        return (Context) this.originContext$delegate.getValue();
    }

    public final int getBackgroundColor(Context context) {
        return getMaterialColorManager().getCurrentCapsuleMaterialColor() != 0 ? getMaterialColorManager().getCurrentCapsuleMaterialColor() : context.getColor(2131102886);
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInTouchArea(float f, float f2) {
        float px = UtilsKt.toPx(8, getContext());
        int[] iArr = new int[2];
        getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        Pair pair = TuplesKt.to(Integer.valueOf(getWidth() + i), Integer.valueOf(getHeight() + i2));
        return f >= ((float) i) - px && f <= ((float) ((Number) pair.component1()).intValue()) + px && f2 >= ((float) i2) - px && f2 <= ((float) ((Number) pair.component2()).intValue()) + px;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        if (!ScrimUtil.isMiddleOrHighAnimLevel(getContext())) {
            if (this.materialColorChangeCallback == null) {
                CapsuleEarView$registerMaterialColorChangeCallback$1 capsuleEarView$registerMaterialColorChangeCallback$1 = new CapsuleEarView$registerMaterialColorChangeCallback$1();
                capsuleEarView$registerMaterialColorChangeCallback$1.this$0 = this;
                VarHandle.storeStoreFence();
                this.materialColorChangeCallback = capsuleEarView$registerMaterialColorChangeCallback$1;
            }
            MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback = this.materialColorChangeCallback;
            if (materialColorChangeCallback != null) {
                getMaterialColorManager().addMaterialColorCallback(materialColorChangeCallback);
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "registerMaterialColorChangeCallback: register", null, 8);
        }
        super/*android.widget.ImageView*/.onAttachedToWindow();
        reloadResource();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.widget.ImageView*/.onDetachedFromWindow();
        this.viewBlurManager.releaseBlurProxyForView(this, true, "capsule ear remove");
        MaterialColorManager.MaterialColorChangeCallback materialColorChangeCallback = this.materialColorChangeCallback;
        if (materialColorChangeCallback != null) {
            getMaterialColorManager().removeMaterialColorCallback(materialColorChangeCallback);
        }
        this.materialColorChangeCallback = null;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "unRegisterMaterialColorChangeCallback: unRegister", null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        ViewBlurProxy viewBlurProxy;
        if (this.earPath == null) {
            updatePathTransform(this.vectorPathData);
            if (this.earPath == null) {
                super/*android.widget.ImageView*/.onDraw(canvas);
                return;
            }
        }
        Path path = this.inversePath;
        if (path == null) {
            super/*android.widget.ImageView*/.onDraw(canvas);
            return;
        }
        if (CapsuleBlurHelper.INSTANCE.userNormalDrawable(getContext())) {
            ViewBlurProxy viewBlurProxy2 = this.viewBlurProxy;
            ViewBlurProxy.BlurType blurType = viewBlurProxy2 != null ? viewBlurProxy2.getBlurType() : null;
            ViewBlurProxy.BlurType.BlurTypeNon blurTypeNon = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
            if (!Intrinsics.areEqual(blurType, blurTypeNon) && (viewBlurProxy = this.viewBlurProxy) != null) {
                viewBlurProxy.setBlurType(blurTypeNon);
            }
        }
        int iSaveLayer = canvas.saveLayer(new RectF(this.drawBound), null);
        ViewBlurProxy viewBlurProxy3 = this.viewBlurProxy;
        Drawable blurDrawable = viewBlurProxy3 != null ? viewBlurProxy3.getBlurDrawable(null) : null;
        Path path2 = this.earPath;
        if (blurDrawable != null) {
            if (path2 != null) {
                canvas.clipPath(path2);
            }
            blurDrawable.setBounds(this.drawBound);
            blurDrawable.draw(canvas);
        } else if (path2 != null) {
            canvas.drawPath(path2, this.defaultBgPaint);
        } else {
            super/*android.widget.ImageView*/.onDraw(canvas);
        }
        Path path3 = this.earPath;
        if (path3 != null) {
            canvas.drawPath(path3, this.scrimPaint);
        }
        if (blurDrawable != null) {
            canvas.drawPath(path, this.clearPaint);
        }
        canvas.restoreToCount(iSaveLayer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onVisibilityAggregated(boolean z) {
        super/*android.widget.ImageView*/.onVisibilityAggregated(z);
        ViewBlurProxy viewBlurProxy = this.viewBlurProxy;
        if (viewBlurProxy != null) {
            viewBlurProxy.onVisibilityAggregated(z);
        }
    }

    public final void reloadResource() {
        CapsuleConfig capsuleConfig = CapsuleConfig.INSTANCE;
        this.earConfigBound = new Rect(0, 0, capsuleConfig.getEarWidth(), capsuleConfig.getItemHeight());
        this.drawBound = new Rect(0, 0, capsuleConfig.getEarWidth(), capsuleConfig.getItemHeight());
        this.defaultBgPaint.setColor(getBackgroundColor(getOriginContext()));
        updatePathTransform(this.vectorPathData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setScrimAlpha(float f) {
        int iComputeScrimAlphaBase = (int) (CapsuleScrimAlphaHelper.INSTANCE.computeScrimAlphaBase(CapsuleScrimAlphaHelper.ScrimAlphaType.EAR, f) * f);
        if (iComputeScrimAlphaBase != this.scrimPaint.getAlpha()) {
            this.scrimPaint.setAlpha(iComputeScrimAlphaBase);
            invalidate();
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00f5  */
    public final void updatePathTransform(String str) {
        float f;
        if (str == null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updatePathTransform: pathData is null", null, 8);
            return;
        }
        String str2 = this.vectorPathData;
        Path pathCreatePathFromPathData = (str2 == null || str2.length() == 0) ? null : PathParser.createPathFromPathData(str2);
        if (pathCreatePathFromPathData == null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updatePathTransform: get path failed", null, 8);
            return;
        }
        RectF rectF = new RectF();
        pathCreatePathFromPathData.computeBounds(rectF, true);
        if (rectF.height() <= 0.0f || rectF.width() <= 0.0f) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "updatePathTransform: unexpected path size", null, 8);
            return;
        }
        float fMin = Math.min(this.earConfigBound.width() / rectF.width(), this.earConfigBound.height() / rectF.height());
        this.drawableMatrix.reset();
        this.drawableMatrix.setScale(fMin, fMin);
        RectF rectF2 = new RectF();
        rectF2.set(rectF);
        this.drawableMatrix.mapRect(rectF2);
        float fCenterX = this.earConfigBound.centerX() - rectF2.centerX();
        float fCenterY = this.earConfigBound.centerY() - rectF2.centerY();
        Rect rect = this.earConfigBound;
        int i = rect.left;
        int i2 = (int) (rect.top + fCenterY);
        int i3 = rect.right;
        int i4 = rect.bottom;
        int i5 = (int) (i4 + fCenterY);
        if (i5 <= i4) {
            i4 = i5;
        }
        this.drawBound = new Rect(i, i2, i3, i4);
        this.drawableMatrix.postTranslate(fCenterX, fCenterY);
        if (this.earPath == null) {
            this.earPath = new Path();
        }
        Path path = this.earPath;
        if (path != null) {
            path.reset();
        }
        pathCreatePathFromPathData.transform(this.drawableMatrix, this.earPath);
        Path path2 = this.earPath;
        if (path2 != null) {
            RectF rectF3 = new RectF();
            path2.computeBounds(rectF3, true);
            if (rectF3.height() <= 0.0f || rectF3.width() <= 0.0f) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "adapterEarPathInsideClip: unexpected path size", null, 8);
            } else {
                if (this.isLeftEar) {
                    f = 0.5f - rectF3.left;
                    if (f < 0.0f) {
                        f = 0.0f;
                    }
                } else {
                    float fWidth = (this.earConfigBound.width() - 0.5f) - rectF3.right;
                    if (fWidth > 0.0f) {
                        f = 0.0f;
                    } else {
                        f = fWidth;
                    }
                }
                if (f != 0.0f) {
                    path2.offset(f, 0.0f);
                }
            }
        }
        Path path3 = this.earPath;
        if (path3 != null) {
            Path path4 = this.inversePath;
            if (path4 == null) {
                path4 = new Path();
                this.inversePath = path4;
            }
            path4.reset();
            path4.addRect(new RectF(this.drawBound), Path.Direction.CW);
            path4.op(path3, Path.Op.DIFFERENCE);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleEarView-->".concat("updatePathTransform: drawBound=" + this.drawBound));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean verifyDrawable(Drawable drawable) {
        ViewBlurProxy viewBlurProxy;
        return super/*android.widget.ImageView*/.verifyDrawable(drawable) || ((viewBlurProxy = this.viewBlurProxy) != null && viewBlurProxy.verifyDrawable(drawable));
    }

    public CapsuleEarView(Context context, AttributeSet attributeSet) {
        this(4, context, attributeSet, false);
    }

    public /* synthetic */ CapsuleEarView(int i, Context context, AttributeSet attributeSet, boolean z) {
        this(context, (i & 2) != 0 ? null : attributeSet, (i & 4) != 0 ? false : z);
    }

    public CapsuleEarView(Context context) {
        this(6, context, null, false);
    }
}
