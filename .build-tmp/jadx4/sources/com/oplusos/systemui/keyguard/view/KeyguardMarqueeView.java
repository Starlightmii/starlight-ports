package com.oplusos.systemui.keyguard.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.R;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import com.oplusos.systemui.common.observer.TalkbackObserver;
import com.oplusos.systemui.common.util.TextShadowUtils;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class KeyguardMarqueeView extends SurfaceView implements SurfaceHolder.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardMarqueeView";
    private final Lazy arrowBitmap$delegate;
    private Paint arrowPaint;
    private final AttributeSet attrs;
    private String content;
    private final Context context;
    public final int defStyleAttr;
    private Paint endGradientPaint;
    public float fadingLength;
    private Handler handler;
    public boolean hasMore;
    public volatile boolean isRealRun;
    public volatile boolean isRunning;
    private final Lazy lastConfig$delegate;
    private Matrix matrix;
    public int maxWidth;
    private Typeface mediumTypeface;
    public float moreMargin;
    public float moreWidth;
    public int speed;
    private Paint startGradientPaint;
    public int startX;
    public int startX2;
    public int stepX;
    public volatile boolean supportMarqueue;
    public boolean surfaceCreated;
    private SurfaceHolder surfaceHolder;
    public int textColor;
    private TextPaint textPaint;
    public float textSize;
    public final int textSpace;
    public int textWidth;
    private HandlerThread thread;
    private Runnable updateRunable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$Hwp0XckZv1yn9rXkO1-Gn8N2IYA, reason: not valid java name */
    public static Bitmap m623$r8$lambda$Hwp0XckZv1yn9rXkO1Gn8N2IYA(KeyguardMarqueeView keyguardMarqueeView) {
        Drawable drawable = keyguardMarqueeView.context.getDrawable(2131233975);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof VectorDrawable)) {
            return null;
        }
        VectorDrawable vectorDrawable = (VectorDrawable) drawable;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public KeyguardMarqueeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.startGradientPaint = new Paint();
        this.endGradientPaint = new Paint();
        this.textPaint = new TextPaint(1);
        this.arrowPaint = new Paint(1);
        this.textColor = -1;
        this.textSize = 24.0f;
        this.speed = 30;
        this.content = "";
        this.supportMarqueue = true;
        this.stepX = 2;
        this.thread = OplusThreadFactoryImpl.INSTANCE.buildNewThread("KeyguardMarqueeThread", -8);
        this.handler = new Handler(this.thread.getLooper());
        this.textSpace = 80;
        KeyguardMarqueeView$$ExternalSyntheticLambda0 keyguardMarqueeView$$ExternalSyntheticLambda0 = new KeyguardMarqueeView$$ExternalSyntheticLambda0();
        keyguardMarqueeView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.arrowBitmap$delegate = LazyKt__LazyJVMKt.lazy(keyguardMarqueeView$$ExternalSyntheticLambda0);
        this.matrix = new Matrix();
        this.mediumTypeface = Typeface.create(Typeface.DEFAULT, 500, false);
        this.lastConfig$delegate = LazyKt__LazyJVMKt.lazy(new KeyguardMarqueeView$$ExternalSyntheticLambda1());
        updateDimen();
        SurfaceHolder holder = getHolder();
        this.surfaceHolder = holder;
        if (holder != null) {
            holder.addCallback(this);
        }
        setZOrderOnTop(true);
        getHolder().setFormat(-2);
        KeyguardMarqueeView$init$1 keyguardMarqueeView$init$1 = new KeyguardMarqueeView$init$1();
        keyguardMarqueeView$init$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateRunable = keyguardMarqueeView$init$1;
        Paint paint = this.startGradientPaint;
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        paint.setXfermode(new PorterDuffXfermode(mode));
        this.endGradientPaint.setXfermode(new PorterDuffXfermode(mode));
        setLayerType(1, null);
    }

    public static final void access$update(KeyguardMarqueeView keyguardMarqueeView) {
        Runnable runnable;
        float f;
        float f2;
        Bitmap arrowBitmap;
        Bitmap arrowBitmap2;
        Bitmap arrowBitmap3;
        if (keyguardMarqueeView.surfaceHolder == null || (runnable = keyguardMarqueeView.updateRunable) == null || !keyguardMarqueeView.isRunning || TextUtils.isEmpty(keyguardMarqueeView.content) || !keyguardMarqueeView.surfaceCreated) {
            return;
        }
        keyguardMarqueeView.isRealRun = true;
        SurfaceHolder surfaceHolder = keyguardMarqueeView.surfaceHolder;
        Canvas canvasLockCanvas = surfaceHolder != null ? surfaceHolder.lockCanvas() : null;
        if (canvasLockCanvas == null) {
            KgdLogUtil.w(TAG, "update canvas null, maybe surface not created");
            keyguardMarqueeView.handler.postDelayed(runnable, 200L);
            return;
        }
        canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (keyguardMarqueeView.canMarqueue()) {
            boolean zIsRtl = DeviceUtil.isRtl();
            int i = keyguardMarqueeView.maxWidth;
            int height = (int) ((canvasLockCanvas.getHeight() / 2) - ((keyguardMarqueeView.textPaint.ascent() + keyguardMarqueeView.textPaint.descent()) / 2.0f));
            int textContentWidth = keyguardMarqueeView.getTextContentWidth();
            if (keyguardMarqueeView.hasMore && (arrowBitmap = keyguardMarqueeView.getArrowBitmap()) != null) {
                Bitmap arrowBitmap4 = keyguardMarqueeView.getArrowBitmap();
                float height2 = arrowBitmap4 != null ? ((canvasLockCanvas.getHeight() - arrowBitmap4.getHeight()) * 1.0f) / 2.0f : 0.0f;
                if (zIsRtl) {
                    keyguardMarqueeView.matrix.reset();
                    keyguardMarqueeView.matrix.postRotate(180.0f, arrowBitmap.getWidth() / 2.0f, arrowBitmap.getHeight() / 2.0f);
                    keyguardMarqueeView.matrix.postTranslate(0.0f, height2);
                    canvasLockCanvas.drawBitmap(arrowBitmap, keyguardMarqueeView.matrix, keyguardMarqueeView.arrowPaint);
                } else {
                    canvasLockCanvas.drawBitmap(arrowBitmap, i - keyguardMarqueeView.moreWidth, height2, keyguardMarqueeView.arrowPaint);
                }
            }
            if (keyguardMarqueeView.canMarqueue()) {
                boolean zIsRtl2 = DeviceUtil.isRtl();
                float f3 = (zIsRtl2 && keyguardMarqueeView.hasMore) ? keyguardMarqueeView.moreWidth + keyguardMarqueeView.moreMargin : 0.0f;
                float f4 = (zIsRtl2 && keyguardMarqueeView.hasMore) ? keyguardMarqueeView.fadingLength + keyguardMarqueeView.moreWidth + keyguardMarqueeView.moreMargin : keyguardMarqueeView.fadingLength;
                int i2 = keyguardMarqueeView.textColor;
                Shader.TileMode tileMode = Shader.TileMode.MIRROR;
                LinearGradient linearGradient = new LinearGradient(f3, 0.0f, f4, 0.0f, 0, i2, tileMode);
                if (zIsRtl2 || !keyguardMarqueeView.hasMore) {
                    f = keyguardMarqueeView.maxWidth;
                    f2 = keyguardMarqueeView.fadingLength;
                } else {
                    f = (keyguardMarqueeView.maxWidth - keyguardMarqueeView.fadingLength) - keyguardMarqueeView.moreWidth;
                    f2 = keyguardMarqueeView.moreMargin;
                }
                LinearGradient linearGradient2 = new LinearGradient(f - f2, 0.0f, ((zIsRtl2 || !keyguardMarqueeView.hasMore) ? keyguardMarqueeView.maxWidth : (keyguardMarqueeView.maxWidth - keyguardMarqueeView.moreWidth) - keyguardMarqueeView.moreMargin) * 1.0f, 0.0f, keyguardMarqueeView.textColor, 0, tileMode);
                keyguardMarqueeView.startGradientPaint.setShader(linearGradient);
                keyguardMarqueeView.endGradientPaint.setShader(linearGradient2);
            } else {
                keyguardMarqueeView.startGradientPaint.setShader(null);
                keyguardMarqueeView.endGradientPaint.setShader(null);
            }
            if (!keyguardMarqueeView.supportMarqueue) {
                if (zIsRtl) {
                    keyguardMarqueeView.startX = (-keyguardMarqueeView.textWidth) + keyguardMarqueeView.maxWidth;
                    keyguardMarqueeView.startX2 = 0;
                } else {
                    keyguardMarqueeView.startX = 0;
                    keyguardMarqueeView.startX2 = 0;
                }
            }
            int i3 = keyguardMarqueeView.startX;
            if (!zIsRtl ? i3 <= (-keyguardMarqueeView.textWidth) : i3 >= textContentWidth) {
                int i4 = keyguardMarqueeView.stepX;
                if (zIsRtl) {
                    keyguardMarqueeView.startX = i3 + i4;
                } else {
                    keyguardMarqueeView.startX = i3 - i4;
                }
                if (keyguardMarqueeView.hasMore) {
                    float f5 = keyguardMarqueeView.moreWidth;
                    float f6 = keyguardMarqueeView.moreMargin;
                    if (zIsRtl) {
                        canvasLockCanvas.clipRect(f5 + f6, 0.0f, i, canvasLockCanvas.getHeight());
                    } else {
                        canvasLockCanvas.clipRect(0.0f, 0.0f, (i - f5) - f6, canvasLockCanvas.getHeight());
                    }
                }
                float f7 = height;
                canvasLockCanvas.drawText(keyguardMarqueeView.content, keyguardMarqueeView.startX, f7, keyguardMarqueeView.textPaint);
                int i5 = keyguardMarqueeView.startX;
                int i6 = keyguardMarqueeView.textWidth;
                int i7 = keyguardMarqueeView.textSpace;
                if (zIsRtl) {
                    int i8 = (i5 - i6) - i7;
                    if (i8 > (-i6)) {
                        keyguardMarqueeView.startX2 = i8;
                        canvasLockCanvas.drawText(keyguardMarqueeView.content, i8, f7, keyguardMarqueeView.textPaint);
                    }
                } else {
                    int i9 = i5 + i6 + i7;
                    if (i9 < textContentWidth) {
                        keyguardMarqueeView.startX2 = i9;
                        canvasLockCanvas.drawText(keyguardMarqueeView.content, i9, f7, keyguardMarqueeView.textPaint);
                    }
                }
                keyguardMarqueeView.drawGrident(canvasLockCanvas);
                if (keyguardMarqueeView.supportMarqueue) {
                    keyguardMarqueeView.handler.postDelayed(runnable, keyguardMarqueeView.speed);
                }
            } else {
                int i10 = keyguardMarqueeView.startX2;
                if (zIsRtl) {
                    int i11 = -keyguardMarqueeView.textWidth;
                    if (i10 > i11) {
                        int i12 = i10 + keyguardMarqueeView.stepX;
                        keyguardMarqueeView.startX = i12;
                        if (i12 >= textContentWidth) {
                            keyguardMarqueeView.startX = i11;
                        }
                    } else {
                        keyguardMarqueeView.startX = i11;
                        keyguardMarqueeView.startX2 = 0;
                    }
                } else if (i10 > 0) {
                    int i13 = i10 - keyguardMarqueeView.stepX;
                    keyguardMarqueeView.startX = i13;
                    if (i13 <= (-keyguardMarqueeView.textWidth)) {
                        keyguardMarqueeView.startX = textContentWidth;
                    }
                } else {
                    keyguardMarqueeView.startX = textContentWidth;
                    keyguardMarqueeView.startX2 = 0;
                }
                if (keyguardMarqueeView.hasMore) {
                    float f8 = keyguardMarqueeView.moreWidth;
                    float f9 = keyguardMarqueeView.moreMargin;
                    if (zIsRtl) {
                        canvasLockCanvas.clipRect(f8 + f9, 0.0f, i, canvasLockCanvas.getHeight());
                    } else {
                        canvasLockCanvas.clipRect(0.0f, 0.0f, (i - f8) - f9, canvasLockCanvas.getHeight());
                    }
                }
                canvasLockCanvas.drawText(keyguardMarqueeView.content, keyguardMarqueeView.startX, height, keyguardMarqueeView.textPaint);
                keyguardMarqueeView.drawGrident(canvasLockCanvas);
                if (keyguardMarqueeView.supportMarqueue) {
                    keyguardMarqueeView.handler.postDelayed(runnable, keyguardMarqueeView.speed);
                }
            }
        } else {
            boolean zIsRtl3 = DeviceUtil.isRtl();
            int i14 = keyguardMarqueeView.maxWidth;
            int height3 = (int) ((canvasLockCanvas.getHeight() / 2) - ((keyguardMarqueeView.textPaint.ascent() + keyguardMarqueeView.textPaint.descent()) / 2.0f));
            Bitmap arrowBitmap5 = keyguardMarqueeView.getArrowBitmap();
            float height4 = arrowBitmap5 != null ? ((canvasLockCanvas.getHeight() - arrowBitmap5.getHeight()) * 1.0f) / 2.0f : 0.0f;
            int i15 = keyguardMarqueeView.textWidth;
            if (zIsRtl3) {
                float f10 = i14 - i15;
                float f11 = keyguardMarqueeView.moreWidth;
                float f12 = keyguardMarqueeView.moreMargin;
                float f13 = ((f10 - f11) - f12) / 2.0f;
                canvasLockCanvas.drawText(keyguardMarqueeView.content, f11 + f13 + f12, height3, keyguardMarqueeView.textPaint);
                if (keyguardMarqueeView.hasMore && (arrowBitmap2 = keyguardMarqueeView.getArrowBitmap()) != null) {
                    keyguardMarqueeView.matrix.reset();
                    keyguardMarqueeView.matrix.postRotate(180.0f, arrowBitmap2.getWidth() / 2.0f, arrowBitmap2.getHeight() / 2.0f);
                    keyguardMarqueeView.matrix.postTranslate(f13, height4);
                    canvasLockCanvas.drawBitmap(arrowBitmap2, keyguardMarqueeView.matrix, keyguardMarqueeView.arrowPaint);
                }
            } else {
                float f14 = (i14 - i15) / 2.0f;
                if (keyguardMarqueeView.hasMore) {
                    f14 -= (keyguardMarqueeView.moreWidth + keyguardMarqueeView.moreMargin) / 2.0f;
                }
                float f15 = f14 >= 0.0f ? f14 : 0.0f;
                canvasLockCanvas.drawText(keyguardMarqueeView.content, f15, height3, keyguardMarqueeView.textPaint);
                if (keyguardMarqueeView.hasMore && (arrowBitmap3 = keyguardMarqueeView.getArrowBitmap()) != null) {
                    canvasLockCanvas.drawBitmap(arrowBitmap3, f15 + keyguardMarqueeView.textWidth + keyguardMarqueeView.moreMargin, height4, keyguardMarqueeView.arrowPaint);
                }
            }
        }
        SurfaceHolder surfaceHolder2 = keyguardMarqueeView.surfaceHolder;
        if (surfaceHolder2 != null) {
            surfaceHolder2.unlockCanvasAndPost(canvasLockCanvas);
        }
    }

    private final Bitmap getArrowBitmap() {
        return (Bitmap) this.arrowBitmap$delegate.getValue();
    }

    private final Configuration getLastConfig() {
        return (Configuration) this.lastConfig$delegate.getValue();
    }

    private final int getMaxWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels - (getResources().getDimensionPixelSize(2131168670) * 2);
    }

    private final int getTextContentWidth() {
        boolean z = this.hasMore;
        int i = this.maxWidth;
        return z ? (int) ((i - this.moreWidth) - this.moreMargin) : i;
    }

    public final boolean canMarqueue() {
        if (TalkbackObserver.Companion.isTalkbackEnabled(this.context)) {
            return false;
        }
        boolean z = this.hasMore;
        String str = this.content;
        if (z) {
            return !TextUtils.isEmpty(str) && ((float) this.textWidth) >= (((float) this.maxWidth) - this.moreMargin) - this.moreWidth;
        }
        return !TextUtils.isEmpty(str) && this.textWidth >= this.maxWidth;
    }

    public final void drawGrident(Canvas canvas) {
        float f;
        float f2;
        boolean zIsRtl = DeviceUtil.isRtl();
        int maxWidth = getMaxWidth();
        if (this.supportMarqueue || zIsRtl) {
            canvas.drawRect((zIsRtl && this.hasMore) ? this.moreWidth + this.moreMargin : 0.0f, 0.0f, (zIsRtl && this.hasMore) ? this.fadingLength + this.moreWidth + this.moreMargin : this.fadingLength, canvas.getHeight(), this.startGradientPaint);
        }
        if (this.supportMarqueue || !zIsRtl) {
            if (zIsRtl || !this.hasMore) {
                f = maxWidth;
                f2 = this.fadingLength;
            } else {
                f = (maxWidth - this.fadingLength) - this.moreWidth;
                f2 = this.moreMargin;
            }
            canvas.drawRect(f - f2, 0.0f, ((zIsRtl || !this.hasMore) ? maxWidth : (maxWidth - this.moreWidth) - this.moreMargin) * 1.0f, canvas.getHeight(), this.endGradientPaint);
        }
    }

    public final String getText() {
        return this.content;
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (TextUtils.isEmpty(this.content)) {
            if (getWidth() == 0 || getHeight() == 0) {
                setVisibility(8);
            }
        }
    }

    public final void onConfigChanged(Configuration configuration) {
        KgdLogUtil.i(TAG, "onConfigChanged");
        if (configuration != null) {
            int iDiff = getLastConfig().diff(configuration);
            if ((33554432 & iDiff) != 0 || (iDiff & 16777216) != 0) {
                this.textPaint.setTypeface(this.mediumTypeface);
            }
            getLastConfig().setTo(configuration);
        }
        updateDimen();
        boolean zIsRtl = DeviceUtil.isRtl();
        getTextContentWidth();
        if (zIsRtl) {
            this.startX = (-this.textWidth) + this.maxWidth;
            this.startX2 = 0;
        } else {
            this.startX = 0;
            this.startX2 = 0;
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
    }

    public final void refreshNoMarqueue(String str) {
        this.isRunning = true;
        this.supportMarqueue = false;
        Runnable runnable = this.updateRunable;
        if (runnable == null || !this.surfaceCreated) {
            return;
        }
        this.handler.removeCallbacks(runnable);
        this.handler.post(runnable);
    }

    public final void refreshTextMarqueeIfNeed() {
        Runnable runnable;
        KgdLogUtil.i(TAG, "refreshTextMarqueeIfNeed");
        if (!this.isRealRun) {
            KgdLogUtil.i(TAG, "refreshTextMarqueeIfNeed return isRealRun false");
            return;
        }
        if (canMarqueue()) {
            if (this.supportMarqueue || (runnable = this.updateRunable) == null) {
                return;
            }
            this.handler.removeCallbacks(runnable);
            this.handler.post(runnable);
            return;
        }
        Runnable runnable2 = this.updateRunable;
        if (runnable2 != null) {
            this.handler.removeCallbacks(runnable2);
            this.handler.post(runnable2);
        }
    }

    public final void setHasMore(boolean z) {
        this.hasMore = z;
    }

    public final void setInverseColorShadow(boolean z) {
        TextShadowUtils.setInverseColorTextShadow(this.textPaint, z);
    }

    public final void setInverseColorTextShadow(boolean z) {
        TextShadowUtils.setInverseColorTextShadow(this.textPaint, z);
    }

    public final void setText(String str) {
        if (TextUtils.isEmpty(str)) {
            setVisibility(8);
            stopMarqueue("setText_empty");
        } else {
            this.textPaint.setTextSize(this.textSize);
            this.textPaint.setColor(this.textColor);
            this.textWidth = (int) this.textPaint.measureText(str);
            this.textPaint.setTextAlign(Paint.Align.LEFT);
        }
        if (!Intrinsics.areEqual(this.content, str)) {
            refreshTextMarqueeIfNeed();
        }
        this.content = str;
        setContentDescription(str);
    }

    public final void setTextColor(int i) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "setTextColor-textColor=", TAG);
        this.textColor = i;
        this.textPaint.setColor(i);
        this.arrowPaint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        refreshTextMarqueeIfNeed();
    }

    public final void startMarqueue(String str) {
        if (TextUtils.isEmpty(this.content)) {
            this.isRunning = false;
            KgdLogUtil.i(TAG, "startMarqueue false,content empty");
            return;
        }
        this.isRunning = true;
        this.supportMarqueue = true;
        Runnable runnable = this.updateRunable;
        if (runnable == null || !this.surfaceCreated) {
            return;
        }
        this.handler.removeCallbacks(runnable);
        this.handler.post(runnable);
    }

    public final void stopMarqueue(String str) {
        this.isRunning = false;
        Runnable runnable = this.updateRunable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        Runnable runnable;
        KgdLogUtil.i(TAG, "surfaceChanged");
        if (canMarqueue() || (runnable = this.updateRunable) == null) {
            return;
        }
        this.handler.removeCallbacks(runnable);
        this.handler.post(runnable);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Runnable runnable;
        this.surfaceCreated = true;
        KgdLogUtil.i(TAG, "surfaceCreated");
        if (this.isRealRun || !this.isRunning || (runnable = this.updateRunable) == null) {
            return;
        }
        this.handler.removeCallbacks(runnable);
        this.handler.post(runnable);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        KgdLogUtil.i(TAG, "surfaceDestroyed");
        surfaceHolder.removeCallback(this);
        Runnable runnable = this.updateRunable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
    }

    public final void updateDimen() {
        float dimension = getResources().getDimension(2131168667);
        float dimension2 = getResources().getDimension(2131168669);
        this.moreMargin = getResources().getDimension(2131168793);
        this.moreWidth = getResources().getDimension(2131168794);
        this.maxWidth = getMaxWidth();
        this.textPaint.setTypeface(this.mediumTypeface);
        TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(this.attrs, R.styleable.KeyguardMarqueeView, this.defStyleAttr, 0);
        this.textSize = typedArrayObtainStyledAttributes.getDimension(3, dimension2);
        this.speed = typedArrayObtainStyledAttributes.getInt(1, 30);
        this.stepX = typedArrayObtainStyledAttributes.getInt(2, 2);
        this.fadingLength = typedArrayObtainStyledAttributes.getDimension(0, dimension);
        typedArrayObtainStyledAttributes.recycle();
    }

    public KeyguardMarqueeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ KeyguardMarqueeView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public KeyguardMarqueeView(Context context) {
        this(context, null, 0, 6, null);
    }
}
