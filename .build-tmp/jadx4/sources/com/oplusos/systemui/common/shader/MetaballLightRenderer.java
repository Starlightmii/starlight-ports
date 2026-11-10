package com.oplusos.systemui.common.shader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import com.oplus.transition.OplusAnimationController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaballLightRenderer {
    private static final Companion Companion = new Companion();

    @Deprecated
    public static final String TAG = "MetaballLightRenderer";
    public long alphaAnimDurationMs;
    public long alphaAnimStartElapsed;
    private AlphaAnimation alphaAnimation;
    private Function0 alphaEndAction;
    public float alphaEndMultiplier;
    private final MetaballLightRenderer$alphaFrameCallback$1 alphaFrameCallback;
    public float alphaMultiplier;
    public float alphaStartMultiplier;
    public long animationStartElapsed;
    public final Choreographer choreographer;
    private final Path clipPath;
    private final RectF clipRect;
    private final MetaballLightConfig config;
    private final Context context;
    private final MetaballLightRenderer$frameCallback$1 frameCallback;
    public float frozenTimeSec;
    public boolean hasLoggedShaderParams;
    private final View hostView;
    public boolean isAnimating;
    public boolean isVisible;
    private RuntimeShader shader;
    private final Paint shaderPaint;
    private final Lazy shaderSource$delegate;
    public float textureHeight;
    private Shader textureShader;
    public float textureWidth;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AlphaAnimation {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AlphaAnimation[] $VALUES;
        public static final AlphaAnimation FADE_IN;
        public static final AlphaAnimation FADE_OUT;
        public static final AlphaAnimation NONE;

        static {
            AlphaAnimation alphaAnimation = new AlphaAnimation("NONE", 0);
            NONE = alphaAnimation;
            AlphaAnimation alphaAnimation2 = new AlphaAnimation("FADE_IN", 1);
            FADE_IN = alphaAnimation2;
            AlphaAnimation alphaAnimation3 = new AlphaAnimation("FADE_OUT", 2);
            FADE_OUT = alphaAnimation3;
            AlphaAnimation[] alphaAnimationArr = {alphaAnimation, alphaAnimation2, alphaAnimation3};
            $VALUES = alphaAnimationArr;
            $ENTRIES = EnumEntriesKt.enumEntries(alphaAnimationArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AlphaAnimation valueOf(String str) {
            return (AlphaAnimation) Enum.valueOf(AlphaAnimation.class, str);
        }

        public static AlphaAnimation[] values() {
            return (AlphaAnimation[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$6wH59fr__VH483BFsBLy0-C1LLQ, reason: not valid java name */
    public static String m622$r8$lambda$6wH59fr__VH483BFsBLy0C1LLQ(MetaballLightRenderer metaballLightRenderer) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(metaballLightRenderer.context.getResources().openRawResource(metaballLightRenderer.config.getShaderResId()), Charsets.UTF_8), OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
            return text;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th2;
            }
        }
    }

    public MetaballLightRenderer(Context context, View view, MetaballLightConfig metaballLightConfig) {
        this.context = context;
        this.hostView = view;
        this.config = metaballLightConfig;
        Paint paint = new Paint(5);
        paint.setFilterBitmap(true);
        this.shaderPaint = paint;
        this.clipPath = new Path();
        this.clipRect = new RectF();
        this.choreographer = Choreographer.getInstance();
        MetaballLightRenderer$$ExternalSyntheticLambda1 metaballLightRenderer$$ExternalSyntheticLambda1 = new MetaballLightRenderer$$ExternalSyntheticLambda1(0);
        metaballLightRenderer$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.shaderSource$delegate = LazyKt__LazyJVMKt.lazy(metaballLightRenderer$$ExternalSyntheticLambda1);
        this.frozenTimeSec = metaballLightConfig.getFinalTimeSec();
        this.alphaAnimation = AlphaAnimation.NONE;
        this.alphaStartMultiplier = 1.0f;
        this.alphaEndMultiplier = 1.0f;
        this.alphaMultiplier = 1.0f;
        this.textureWidth = 1.0f;
        this.textureHeight = 1.0f;
        MetaballLightRenderer$frameCallback$1 metaballLightRenderer$frameCallback$1 = new MetaballLightRenderer$frameCallback$1();
        metaballLightRenderer$frameCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.frameCallback = metaballLightRenderer$frameCallback$1;
        MetaballLightRenderer$alphaFrameCallback$1 metaballLightRenderer$alphaFrameCallback$1 = new MetaballLightRenderer$alphaFrameCallback$1();
        metaballLightRenderer$alphaFrameCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.alphaFrameCallback = metaballLightRenderer$alphaFrameCallback$1;
    }

    public static final void access$finishAlphaAnimation(MetaballLightRenderer metaballLightRenderer) {
        Function0 function0 = metaballLightRenderer.alphaEndAction;
        metaballLightRenderer.alphaMultiplier = metaballLightRenderer.alphaEndMultiplier;
        metaballLightRenderer.cancelAlphaAnimation();
        metaballLightRenderer.hostView.invalidate();
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final String getShaderSource() {
        return (String) this.shaderSource$delegate.getValue();
    }

    public final Shader buildTextureShader() {
        Resources resources = this.context.getResources();
        int textureResId = this.config.getTextureResId();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, textureResId, options);
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        }
        this.textureWidth = bitmapDecodeResource.getWidth();
        this.textureHeight = bitmapDecodeResource.getHeight();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        return new BitmapShader(bitmapDecodeResource, tileMode, tileMode);
    }

    public final void cancelAllAnimations() {
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = false;
        this.alphaMultiplier = 1.0f;
        this.hostView.invalidate();
    }

    public final void cancelAlphaAnimation() {
        this.alphaAnimation = AlphaAnimation.NONE;
        this.alphaEndAction = null;
        this.choreographer.removeFrameCallback(this.alphaFrameCallback);
    }

    public final void draw(Canvas canvas, Rect rect, float[] fArr) {
        Object obj;
        if (!this.isVisible || rect.isEmpty()) {
            return;
        }
        RuntimeShader runtimeShader = this.shader;
        if (runtimeShader == null) {
            try {
                RuntimeShader runtimeShader2 = new RuntimeShader(getShaderSource());
                this.textureShader = buildTextureShader();
                this.shaderPaint.setShader(runtimeShader2);
                this.shader = runtimeShader2;
                obj = Result.constructor-impl(runtimeShader2);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Log.e(TAG, "ensureShader failed", th2);
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            runtimeShader = (RuntimeShader) obj;
        }
        if (runtimeShader == null) {
            return;
        }
        float fResolveTimeSec = resolveTimeSec();
        Shader shader = this.textureShader;
        if (shader != null) {
            MetaballLightCenter metaballLightCenterCalculate = ((MetaballBottomOffsetCenterProvider) this.config.getCenterProvider()).calculate(rect, fResolveTimeSec, this.config);
            runtimeShader.setInputShader("u_texture", shader);
            runtimeShader.setFloatUniform("u_textureSize", this.textureWidth, this.textureHeight);
            runtimeShader.setFloatUniform("u_radius", this.config.getShadeRadius());
            if (!this.hasLoggedShaderParams) {
                this.hasLoggedShaderParams = true;
                Log.d(TAG, "updateShader: bounds(left=" + rect.left + ", top=" + rect.top + ", right=" + rect.right + ", bottom=" + rect.bottom + ", width=" + rect.width() + ", height=" + rect.height() + "), textureSize=(" + this.textureWidth + ", " + this.textureHeight + "), radius=" + this.config.getShadeRadius() + ", center=(" + metaballLightCenterCalculate.getX() + ", " + metaballLightCenterCalculate.getY() + "), centerXRatio=" + this.config.getCenterXRatio() + ", centerYOffsetPx=" + this.config.getCenterYOffsetPx() + ", alpha=" + this.config.getShadeAlpha() + ", rotationSpeed=" + this.config.getRotationSpeed() + ", timeSec=" + fResolveTimeSec + ", centerProvider=" + this.config.getCenterProvider().getClass().getSimpleName());
            }
            runtimeShader.setFloatUniform("u_centerPx", metaballLightCenterCalculate.getX(), metaballLightCenterCalculate.getY());
            runtimeShader.setFloatUniform("a_alpha", this.config.getShadeAlpha() * this.alphaMultiplier);
            runtimeShader.setFloatUniform("u_rotationSpeed", this.config.getRotationSpeed());
            runtimeShader.setFloatUniform("u_time", fResolveTimeSec);
            this.shaderPaint.setShader(runtimeShader);
        }
        int iSave = canvas.save();
        if (fArr.length >= 8) {
            for (float f : fArr) {
                if (f > 0.0f) {
                    this.clipPath.reset();
                    this.clipRect.set(rect);
                    this.clipPath.addRoundRect(this.clipRect, fArr, Path.Direction.CW);
                    canvas.clipPath(this.clipPath);
                }
            }
            canvas.clipRect(rect);
        } else {
            canvas.clipRect(rect);
        }
        canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, this.shaderPaint);
        canvas.restoreToCount(iSave);
    }

    public final void fadeInFirstFrame() {
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = true;
        this.isAnimating = false;
        this.frozenTimeSec = 0.0f;
        this.alphaMultiplier = 0.0f;
        this.hostView.invalidate();
        startAlphaAnimation(AlphaAnimation.FADE_IN, 200L, 0.0f, 1.0f, null);
    }

    public final void fadeInThenPlay() {
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = true;
        this.isAnimating = false;
        this.frozenTimeSec = 0.0f;
        this.alphaMultiplier = 0.0f;
        this.hostView.invalidate();
        AlphaAnimation alphaAnimation = AlphaAnimation.FADE_IN;
        MetaballLightRenderer$$ExternalSyntheticLambda1 metaballLightRenderer$$ExternalSyntheticLambda1 = new MetaballLightRenderer$$ExternalSyntheticLambda1(1);
        metaballLightRenderer$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        startAlphaAnimation(alphaAnimation, 200L, 0.0f, 1.0f, metaballLightRenderer$$ExternalSyntheticLambda1);
    }

    public final void fadeOutThenHide(long j, Function0 function0) {
        float fResolveTimeSec = resolveTimeSec();
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = true;
        this.isAnimating = false;
        this.frozenTimeSec = fResolveTimeSec;
        AlphaAnimation alphaAnimation = AlphaAnimation.FADE_OUT;
        float f = this.alphaMultiplier;
        MetaballLightRenderer$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new MetaballLightRenderer$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = function0;
        VarHandle.storeStoreFence();
        startAlphaAnimation(alphaAnimation, j, f, 0.0f, externalSyntheticLambda0);
    }

    public final float resolveTimeSec() {
        if (!this.isAnimating) {
            return this.frozenTimeSec;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.animationStartElapsed;
        long playDurationMs = this.config.getPlayDurationMs();
        if (playDurationMs < 0) {
            playDurationMs = 0;
        }
        if (jElapsedRealtime < playDurationMs) {
            return jElapsedRealtime / 1000.0f;
        }
        this.frozenTimeSec = this.config.getFinalTimeSec();
        stopAnimation();
        return this.frozenTimeSec;
    }

    public final void scheduleAlphaFrame() {
        if (this.alphaAnimation == AlphaAnimation.NONE || !this.isVisible) {
            return;
        }
        this.choreographer.removeFrameCallback(this.alphaFrameCallback);
        this.choreographer.postFrameCallback(this.alphaFrameCallback);
    }

    public final void scheduleNextFrame() {
        if (this.isAnimating && this.isVisible) {
            this.choreographer.removeFrameCallback(this.frameCallback);
            this.choreographer.postFrameCallback(this.frameCallback);
        }
    }

    public final void showFinalFrame() {
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = true;
        this.isAnimating = false;
        this.alphaMultiplier = 1.0f;
        this.frozenTimeSec = this.config.getFinalTimeSec();
        this.hostView.invalidate();
    }

    public final void showFirstFrame() {
        stopAnimation();
        cancelAlphaAnimation();
        this.isVisible = true;
        this.isAnimating = false;
        this.alphaMultiplier = 1.0f;
        this.frozenTimeSec = 0.0f;
        this.hostView.invalidate();
    }

    public final void startAlphaAnimation(AlphaAnimation alphaAnimation, long j, float f, float f2, Function0 function0) {
        this.alphaAnimation = alphaAnimation;
        this.alphaAnimStartElapsed = SystemClock.elapsedRealtime();
        this.alphaAnimDurationMs = j;
        this.alphaStartMultiplier = f;
        this.alphaEndMultiplier = f2;
        this.alphaEndAction = function0;
        scheduleAlphaFrame();
    }

    public final void startPlay() {
        cancelAlphaAnimation();
        this.isVisible = true;
        this.animationStartElapsed = SystemClock.elapsedRealtime();
        this.frozenTimeSec = this.config.getFinalTimeSec();
        this.alphaMultiplier = 1.0f;
        this.isAnimating = true;
        this.hasLoggedShaderParams = false;
        this.hostView.invalidate();
        scheduleNextFrame();
    }

    public final void stopAnimation() {
        this.isAnimating = false;
        this.choreographer.removeFrameCallback(this.frameCallback);
    }
}
