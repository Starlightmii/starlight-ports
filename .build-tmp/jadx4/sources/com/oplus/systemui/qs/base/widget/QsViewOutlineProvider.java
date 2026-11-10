package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.oplus.systemui.qs.base.tile.OplusQsDeformRadiusParam;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.view.SmoothCornersGradientDrawable;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QsViewOutlineProvider extends ViewOutlineProvider {
    private final View hostView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract class QsViewBaseOutlineProvider extends QsViewOutlineProvider implements View.OnAttachStateChangeListener {
        private List<BiConsumer<QsViewOutlineProvider, Boolean>> attributeReferences;
        private final Runnable invalidateOutlineRunnable;
        private final QsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1 layoutChangeListener;
        private final Function<Context, Object> outlineShapeProvider;
        private final AtomicReference<Object> outlineShapeReference;

        public QsViewBaseOutlineProvider(View view, Function<Context, Object> function) {
            super(view);
            this.outlineShapeProvider = function;
            QsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1 qsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1 = new QsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1();
            qsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1.$hostView = view;
            qsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.invalidateOutlineRunnable = qsViewOutlineProvider$QsViewBaseOutlineProvider$invalidateOutlineRunnable$1;
            this.outlineShapeReference = new AtomicReference<>(function.apply(getContext()));
            QsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1 qsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1 = new QsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1();
            qsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1.this$0 = this;
            VarHandle.storeStoreFence();
            this.layoutChangeListener = qsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1;
            view.addOnLayoutChangeListener(qsViewOutlineProvider$QsViewBaseOutlineProvider$layoutChangeListener$1);
        }

        private final Context getContext() {
            return getHostView().getContext();
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public synchronized void attachAttribute(BiConsumer<QsViewOutlineProvider, Boolean> biConsumer) {
            try {
                List copyOnWriteArrayList = this.attributeReferences;
                if (copyOnWriteArrayList == null) {
                    copyOnWriteArrayList = new CopyOnWriteArrayList();
                    this.attributeReferences = copyOnWriteArrayList;
                }
                if (copyOnWriteArrayList.isEmpty()) {
                    getHostView().addOnAttachStateChangeListener(this);
                }
                if (copyOnWriteArrayList.add(biConsumer) && getHostView().isAttachedToWindow()) {
                    biConsumer.accept(this, Boolean.TRUE);
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public synchronized void detachAttribute(BiConsumer<QsViewOutlineProvider, Boolean> biConsumer) {
            try {
                List<BiConsumer<QsViewOutlineProvider, Boolean>> list = this.attributeReferences;
                if (list == null) {
                    return;
                }
                if (list.remove(biConsumer)) {
                    biConsumer.accept(this, Boolean.FALSE);
                }
                if (list.isEmpty()) {
                    getHostView().removeOnAttachStateChangeListener(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            view.setClipToOutline(true);
        }

        public final AtomicReference<Object> getOutlineShapeReference() {
            return this.outlineShapeReference;
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public final void invalidateOutline() {
            Object objApply = this.outlineShapeProvider.apply(getContext());
            if (Intrinsics.areEqual(objApply, this.outlineShapeReference.getAndSet(objApply)) || !getHostView().isAttachedToWindow()) {
                return;
            }
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                this.invalidateOutlineRunnable.run();
            } else {
                getHostView().post(this.invalidateOutlineRunnable);
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            List<BiConsumer<QsViewOutlineProvider, Boolean>> list = this.attributeReferences;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    ((BiConsumer) it.next()).accept(this, Boolean.TRUE);
                }
            }
            getHostView().removeOnLayoutChangeListener(this.layoutChangeListener);
            getHostView().addOnLayoutChangeListener(this.layoutChangeListener);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            getHostView().removeOnAttachStateChangeListener(this);
            List<BiConsumer<QsViewOutlineProvider, Boolean>> list = this.attributeReferences;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    ((BiConsumer) it.next()).accept(this, Boolean.FALSE);
                }
            }
            getHostView().removeOnLayoutChangeListener(this.layoutChangeListener);
        }

        public final void refreshSmoothRound(String str, float f, OplusRoundParams oplusRoundParams, boolean z) {
            Drawable foreground;
            Drawable background = getHostView().getBackground();
            if (background != null) {
                refreshSmoothRound$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(background, str, f, oplusRoundParams, z);
            }
            if (!z || (foreground = getHostView().getForeground()) == null) {
                return;
            }
            refreshSmoothRound$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(foreground, str, f, oplusRoundParams, true);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QsViewRoundRectOutlineProvider extends QsViewBaseOutlineProvider {
        public static final Companion Companion = new Companion();
        private OplusQsDeformRadiusParam deformParam;
        public boolean isCircleShape;
        private Path path;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public QsViewRoundRectOutlineProvider(View view, Function<Context, Float> function) {
            super(view, function);
            this.path = new Path();
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider.QsViewBaseOutlineProvider, android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            super.getOutline(view, outline);
            Float f = (Float) getOutlineShapeReference().get();
            OplusRoundParams roundParams = getRoundParams(getHostView());
            if (roundParams != null && (f.floatValue() < view.getHeight() / 2.0f || roundParams.isCircle || this.deformParam != null)) {
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
                return;
            }
            this.path.rewind();
            Path path = this.path;
            float width = view.getWidth();
            float height = view.getHeight();
            f.getClass();
            path.addRoundRect(0.0f, 0.0f, width, height, f.floatValue(), f.floatValue(), Path.Direction.CCW);
            outline.setPath(this.path);
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public OplusRoundParams getRoundParams(View view) {
            Float f = (Float) getOutlineShapeReference().get();
            OplusQsDeformRadiusParam oplusQsDeformRadiusParam = this.deformParam;
            if (oplusQsDeformRadiusParam != null) {
                boolean z = Math.abs((((float) view.getHeight()) / 2.0f) - oplusQsDeformRadiusParam.getSmoothRadius()) < 10.0f;
                OplusRoundParams normalRoundParams = OplusQsSmoothRoundUtil.getNormalRoundParams();
                normalRoundParams.setRadius(oplusQsDeformRadiusParam.getSmoothRadius());
                normalRoundParams.setWeight(z ? 0.0f : oplusQsDeformRadiusParam.getWeight());
                return normalRoundParams;
            }
            if (this.isCircleShape || (f.floatValue() >= view.getHeight() / 2.0f && view.getHeight() > 0)) {
                return OplusQsSmoothRoundUtil.getCircleRoundParams();
            }
            OplusRoundParams normalRoundParams2 = OplusQsSmoothRoundUtil.getNormalRoundParams();
            normalRoundParams2.setRadius(f.floatValue());
            normalRoundParams2.setWeight(1.0f);
            return normalRoundParams2;
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public final void refreshSmoothRound() {
            Float f = (Float) getOutlineShapeReference().get();
            OplusRoundParams roundParams = getRoundParams(getHostView());
            f.getClass();
            refreshSmoothRound("roundRect", f.floatValue(), roundParams, this.deformParam != null);
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public void setCircleShape(boolean z) {
            this.isCircleShape = z;
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewOutlineProvider
        public void setDeformRadiusParam(OplusQsDeformRadiusParam oplusQsDeformRadiusParam) {
            this.deformParam = oplusQsDeformRadiusParam;
            refreshSmoothRound();
        }
    }

    public QsViewOutlineProvider(View view) {
        this.hostView = view;
    }

    public abstract void attachAttribute(BiConsumer<QsViewOutlineProvider, Boolean> biConsumer);

    public abstract void detachAttribute(BiConsumer<QsViewOutlineProvider, Boolean> biConsumer);

    public final View getHostView() {
        return this.hostView;
    }

    public abstract OplusRoundParams getRoundParams(View view);

    public abstract void invalidateOutline();

    public abstract void refreshSmoothRound();

    public final void refreshSmoothRound$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Drawable drawable, String str, float f, OplusRoundParams oplusRoundParams, boolean z) {
        if (drawable instanceof SmoothCornersGradientDrawable) {
            SmoothCornersGradientDrawable smoothCornersGradientDrawable = (SmoothCornersGradientDrawable) drawable;
            if (oplusRoundParams == null) {
                if (smoothCornersGradientDrawable.getRadius() == 0.0f && smoothCornersGradientDrawable.getWeight() == 0.0f && smoothCornersGradientDrawable.getRoundType() != 1) {
                    return;
                }
                smoothCornersGradientDrawable.setSmoothRound(0.0f, 0.0f);
                smoothCornersGradientDrawable.setRoundCircleType(false);
                return;
            }
            if (oplusRoundParams.isCircle) {
                if (smoothCornersGradientDrawable.getRoundType() != 1) {
                    smoothCornersGradientDrawable.setSmoothRound(oplusRoundParams.getRadius(), oplusRoundParams.getWeight());
                    smoothCornersGradientDrawable.setRoundCircleType(true);
                    return;
                }
                return;
            }
            if (z) {
                smoothCornersGradientDrawable.setSmoothRound(oplusRoundParams.getRadius(), oplusRoundParams.getWeight());
                smoothCornersGradientDrawable.setRoundCircleType(false);
            } else if (f < this.hostView.getHeight() / 2.0f) {
                if (smoothCornersGradientDrawable.getRadius() == oplusRoundParams.getRadius() && smoothCornersGradientDrawable.getWeight() == oplusRoundParams.getWeight() && smoothCornersGradientDrawable.getRoundType() != 1) {
                    return;
                }
                smoothCornersGradientDrawable.setSmoothRound(oplusRoundParams.getRadius(), oplusRoundParams.getWeight());
                smoothCornersGradientDrawable.setRoundCircleType(false);
            }
        }
    }

    public abstract void setCircleShape(boolean z);

    public abstract void setDeformRadiusParam(OplusQsDeformRadiusParam oplusQsDeformRadiusParam);
}
