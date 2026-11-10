package com.oplus.systemui.plugins.qs.customize.view.animation.drag;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.animator.IDeformableView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.DropAnim;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.IMultiAnimView;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MoveAnim;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.PressAnim;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.model.SpanSizeKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragAnimationHelper {
    public static final Companion Companion = new Companion();
    public static final String TAG = "DragAnimationHelper";
    private CopyViewProxy copyViewProxy;
    private RecyclerView.ViewHolder curViewHolder;
    public final Object data;
    private final IElevationAdjust elevationAdjust;
    private Integer fromWidth;
    public boolean isDeformationEnded;
    public boolean isDragAnimRunning;
    public boolean isMoveAnimEnded;
    private MoveAnim moveAnim;
    private Integer offsetX;
    private final Function0 onAnimationEnd;
    private final Function0 onAnimationStart;
    private PressAnim pressAnim;
    private final ViewGroup rootView;
    private final Function1 viewHolderProvider;

    /* JADX INFO: renamed from: $r8$lambda$3iF__SUHhag-lUtsTRh2A84hBnM, reason: not valid java name */
    public static Unit m159$r8$lambda$3iF__SUHhaglUtsTRh2A84hBnM(DragAnimationHelper dragAnimationHelper) {
        dragAnimationHelper.pressAnim = null;
        dragAnimationHelper.onDropAnimationComplete();
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$WDzp11t6xfkLtGcuQauuzDvJ56Q(DragAnimationHelper dragAnimationHelper) {
        MoveAnim moveAnim = dragAnimationHelper.moveAnim;
        if (moveAnim != null) {
            moveAnim.bindViewHolder((RecyclerView.ViewHolder) null);
        }
        dragAnimationHelper.moveAnim = null;
        dragAnimationHelper.onDropAnimationComplete();
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$a7riqXvXswgXZ22RLpDqsqPJfgU(DragAnimationHelper dragAnimationHelper) {
        dragAnimationHelper.pressAnim = null;
        dragAnimationHelper.onDropAnimationComplete();
        return Unit.INSTANCE;
    }

    public DragAnimationHelper(ViewGroup viewGroup, Object obj, IElevationAdjust iElevationAdjust, Function1 function1, Function0 function0, Function0 function2) {
        this.rootView = viewGroup;
        this.data = obj;
        this.elevationAdjust = iElevationAdjust;
        this.viewHolderProvider = function1;
        this.onAnimationStart = function0;
        this.onAnimationEnd = function2;
    }

    public static final COUISpringAnimation animateTo(float f, float f2) {
        return Companion.animateTo(f, f2);
    }

    private final RecyclerView.ViewHolder getCopyViewHolder() {
        CopyViewProxy copyViewProxy = this.copyViewProxy;
        if (copyViewProxy != null) {
            return copyViewProxy.getCopyViewHolder();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void completeAnimation(View view) {
        IDeformableView iDeformableView = view instanceof IDeformableView ? (IDeformableView) view : null;
        if (iDeformableView != null) {
            ((DeformationFrameLayout) iDeformableView).endAnim();
        }
        MoveAnim moveAnim = this.moveAnim;
        if (moveAnim != null) {
            moveAnim.bindViewHolder((RecyclerView.ViewHolder) null);
        }
        this.moveAnim = null;
        onDropAnimationComplete();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "Animation completed", null, 8);
    }

    public final Object getData() {
        return this.data;
    }

    public final void onDeformationEnd(View view) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onDeformationEnd: isMoveAnimEnded: ", this.isMoveAnimEnded), null, 8);
        this.isDeformationEnded = true;
        if (this.isMoveAnimEnded) {
            completeAnimation(view);
        }
    }

    public final void onDropAnimationComplete() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDropAnimationComplete moveAnim:" + this.moveAnim + ", pressAnim:" + this.pressAnim, null, 8);
        if (this.moveAnim == null && this.pressAnim == null) {
            removeCopyViewAndReset();
            this.elevationAdjust.resetElevation(this.curViewHolder);
            RecyclerView.ViewHolder viewHolder = this.curViewHolder;
            KeyEvent.Callback callback = viewHolder != null ? viewHolder.itemView : null;
            IMultiAnimView iMultiAnimView = callback instanceof IMultiAnimView ? (IMultiAnimView) callback : null;
            if (iMultiAnimView != null) {
                iMultiAnimView.setShouldBlockTranslationAndScaleChange(false, TAG);
            }
            this.curViewHolder = null;
            this.isDragAnimRunning = false;
            this.onAnimationEnd.invoke();
        }
    }

    public final void onMoveAnimEnd(View view) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onMoveAnimEnd: isDeformationEnded: ", this.isDeformationEnded), null, 8);
        this.isMoveAnimEnded = true;
        if (this.isDeformationEnded) {
            completeAnimation(view);
        }
    }

    public final void removeCopyViewAndReset() {
        View view;
        View view2;
        RecyclerView.ViewHolder viewHolder = this.curViewHolder;
        if (viewHolder != null && (view2 = viewHolder.itemView) != null) {
            view2.setVisibility(0);
        }
        RecyclerView.ViewHolder copyViewHolder = getCopyViewHolder();
        if (copyViewHolder != null && (view = copyViewHolder.itemView) != null) {
            view.setVisibility(8);
        }
        ViewGroup viewGroup = this.rootView;
        RecyclerView.ViewHolder copyViewHolder2 = getCopyViewHolder();
        viewGroup.removeView(copyViewHolder2 != null ? copyViewHolder2.itemView : null);
        this.copyViewProxy = null;
    }

    public final void resetPressAnim() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "resetPressAnim", null, 8);
        PressAnim pressAnim = this.pressAnim;
        if (pressAnim != null) {
            DragAnimationHelper$$ExternalSyntheticLambda0 dragAnimationHelper$$ExternalSyntheticLambda0 = new DragAnimationHelper$$ExternalSyntheticLambda0(2);
            dragAnimationHelper$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            pressAnim.resetPressAnim(dragAnimationHelper$$ExternalSyntheticLambda0);
        }
    }

    public final void startDragAnim(Function0 function0, float f, float f2, Function0 function1) {
        SpanSize spanSize;
        RecyclerView.ViewHolder hostViewHolder;
        View view;
        boolean z = false;
        if (this.copyViewProxy == null) {
            this.copyViewProxy = (CopyViewProxy) function0.invoke();
            RecyclerView.ViewHolder copyViewHolder = getCopyViewHolder();
            if (copyViewHolder != null) {
                this.elevationAdjust.resetElevation(this.curViewHolder);
                this.rootView.addView(copyViewHolder.itemView);
                copyViewHolder.itemView.setVisibility(0);
                CopyViewProxy copyViewProxy = this.copyViewProxy;
                if (copyViewProxy != null && (hostViewHolder = copyViewProxy.getHostViewHolder()) != null && (view = hostViewHolder.itemView) != null) {
                    view.setVisibility(4);
                }
                View view2 = copyViewHolder.itemView;
                if (!view2.isAttachedToWindow()) {
                    DragAnimationHelper$startDragAnim$lambda$2$.inlined.doOnAttach.1 r0 = new DragAnimationHelper$startDragAnim$lambda$2$.inlined.doOnAttach.1();
                    r0.$this_doOnAttach = view2;
                    r0.$dragViewReady$inlined = function1;
                    VarHandle.storeStoreFence();
                    view2.addOnAttachStateChangeListener(r0);
                } else if (function1 != null) {
                    function1.invoke();
                }
            } else if (function1 != null) {
                function1.invoke();
            }
        } else if (function1 != null) {
            function1.invoke();
        }
        this.isDeformationEnded = false;
        this.isMoveAnimEnded = false;
        RecyclerView.ViewHolder viewHolder = this.curViewHolder;
        View view3 = viewHolder != null ? viewHolder.itemView : null;
        DeformationFrameLayout deformationFrameLayout = view3 instanceof DeformationFrameLayout ? (DeformationFrameLayout) view3 : null;
        if (deformationFrameLayout != null) {
            deformationFrameLayout.endAnim();
        }
        if (this.moveAnim == null) {
            this.moveAnim = new MoveAnim(MoveAnim.Type.DRAG);
        }
        MoveAnim moveAnim = this.moveAnim;
        if (moveAnim != null) {
            moveAnim.cancel();
        }
        if (getCopyViewHolder() == null) {
            RecyclerView.ViewHolder viewHolder2 = this.curViewHolder;
            if (viewHolder2 != null) {
                KeyEvent.Callback callback = viewHolder2.itemView;
                IMultiAnimView iMultiAnimView = callback instanceof IMultiAnimView ? (IMultiAnimView) callback : null;
                if (iMultiAnimView != null) {
                    iMultiAnimView.setShouldBlockTranslationAndScaleChange(true, TAG);
                }
                this.elevationAdjust.setElevation(viewHolder2);
                MoveAnim moveAnim2 = this.moveAnim;
                if (moveAnim2 != null) {
                    moveAnim2.bindViewHolder(viewHolder2);
                }
            }
        } else {
            RecyclerView.ViewHolder copyViewHolder2 = getCopyViewHolder();
            if (copyViewHolder2 != null) {
                PressAnim pressAnim = this.pressAnim;
                if (pressAnim != null) {
                    pressAnim.bindViewHolder(copyViewHolder2);
                }
                MoveAnim moveAnim3 = this.moveAnim;
                if (moveAnim3 != null) {
                    moveAnim3.bindViewHolder(copyViewHolder2);
                }
            }
        }
        PressAnim pressAnim2 = this.pressAnim;
        if (pressAnim2 != null) {
            pressAnim2.startDragAnim();
        }
        MoveAnim moveAnim4 = this.moveAnim;
        if (moveAnim4 != null) {
            RecyclerView.ViewHolder copyViewHolder3 = getCopyViewHolder();
            if (copyViewHolder3 == null) {
                copyViewHolder3 = this.curViewHolder;
            }
            KeyEvent.Callback callback2 = copyViewHolder3 != null ? copyViewHolder3.itemView : null;
            IDeformableView iDeformableView = callback2 instanceof IDeformableView ? (IDeformableView) callback2 : null;
            if (iDeformableView != null && (spanSize = iDeformableView.getSpanSize()) != null && SpanSizeKt.isOneXOne(spanSize)) {
                z = true;
            }
            moveAnim4.startDragAnim(f, f2, z);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "startDragAnim", null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startDropAnim() {
        int[] intArray;
        RecyclerView.ViewHolder hostViewHolder;
        View view;
        RecyclerView.ViewHolder hostViewHolder2;
        RecyclerView.ViewHolder hostViewHolder3;
        RecyclerView.ViewHolder hostViewHolder4;
        View view2;
        View view3;
        RecyclerView.ViewHolder hostViewHolder5;
        RecyclerView.ViewHolder copyViewHolder = getCopyViewHolder();
        KeyEvent.Callback callback = copyViewHolder != null ? copyViewHolder.itemView : null;
        IDeformableView iDeformableView = callback instanceof IDeformableView ? (IDeformableView) callback : null;
        SpanSize spanSize = iDeformableView != null ? iDeformableView.getSpanSize() : null;
        CopyViewProxy copyViewProxy = this.copyViewProxy;
        KeyEvent.Callback callback2 = (copyViewProxy == null || (hostViewHolder5 = copyViewProxy.getHostViewHolder()) == null) ? null : hostViewHolder5.itemView;
        IDeformableView iDeformableView2 = callback2 instanceof IDeformableView ? (IDeformableView) callback2 : null;
        SpanSize spanSize2 = iDeformableView2 != null ? iDeformableView2.getSpanSize() : null;
        if (!Intrinsics.areEqual(spanSize, spanSize2)) {
            RecyclerView.ViewHolder copyViewHolder2 = getCopyViewHolder();
            this.fromWidth = (copyViewHolder2 == null || (view3 = copyViewHolder2.itemView) == null) ? null : Integer.valueOf(view3.getMeasuredWidth());
        }
        CopyViewProxy copyViewProxy2 = this.copyViewProxy;
        Integer numValueOf = (copyViewProxy2 == null || (hostViewHolder4 = copyViewProxy2.getHostViewHolder()) == null || (view2 = hostViewHolder4.itemView) == null) ? null : Integer.valueOf(view2.getMeasuredWidth());
        Integer num = this.fromWidth;
        if (num != null) {
            int iIntValue = num.intValue();
            if (numValueOf != null) {
                this.offsetX = Integer.valueOf((numValueOf.intValue() - iIntValue) / 2);
            }
        }
        int i = 1;
        if (getCopyViewHolder() != null) {
            CopyViewProxy copyViewProxy3 = this.copyViewProxy;
            Class<?> cls = (copyViewProxy3 == null || (hostViewHolder3 = copyViewProxy3.getHostViewHolder()) == null) ? null : hostViewHolder3.getClass();
            RecyclerView.ViewHolder copyViewHolder3 = getCopyViewHolder();
            if (Intrinsics.areEqual(cls, copyViewHolder3 != null ? copyViewHolder3.getClass() : null)) {
                CopyViewProxy copyViewProxy4 = this.copyViewProxy;
                if (copyViewProxy4 == null || (hostViewHolder2 = copyViewProxy4.getHostViewHolder()) == null) {
                    hostViewHolder2 = null;
                } else {
                    this.elevationAdjust.setElevation(hostViewHolder2);
                    PressAnim pressAnim = this.pressAnim;
                    if (pressAnim != null) {
                        pressAnim.bindViewHolder(hostViewHolder2);
                    }
                    MoveAnim moveAnim = this.moveAnim;
                    if (moveAnim != null) {
                        moveAnim.bindViewHolder(hostViewHolder2);
                    }
                }
                this.curViewHolder = hostViewHolder2;
                removeCopyViewAndReset();
                RecyclerView.ViewHolder viewHolder = this.curViewHolder;
                KeyEvent.Callback callback3 = viewHolder != null ? viewHolder.itemView : null;
                IMultiAnimView iMultiAnimView = callback3 instanceof IMultiAnimView ? (IMultiAnimView) callback3 : null;
                if (iMultiAnimView != null) {
                    iMultiAnimView.setShouldBlockTranslationAndScaleChange(true, TAG);
                }
            }
        }
        PressAnim pressAnim2 = this.pressAnim;
        int i2 = 0;
        if (pressAnim2 != null) {
            DragAnimationHelper$$ExternalSyntheticLambda0 dragAnimationHelper$$ExternalSyntheticLambda0 = new DragAnimationHelper$$ExternalSyntheticLambda0(i2);
            dragAnimationHelper$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            pressAnim2.endDragAnim(dragAnimationHelper$$ExternalSyntheticLambda0);
        }
        MoveAnim moveAnim2 = this.moveAnim;
        if (moveAnim2 == null || (intArray = moveAnim2.getIntArray()) == null) {
            intArray = new int[2];
        }
        CopyViewProxy copyViewProxy5 = this.copyViewProxy;
        if (copyViewProxy5 == null || (hostViewHolder = copyViewProxy5.getHostViewHolder()) == null) {
            hostViewHolder = this.curViewHolder;
        }
        if (hostViewHolder != null && (view = hostViewHolder.itemView) != 0) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(intArray[0], intArray[1], "startDropAnim, ", ", "), null, 8);
            if (!Intrinsics.areEqual(spanSize2, spanSize)) {
                DropAnim.Companion companion = DropAnim.Companion;
                if (Intrinsics.areEqual(spanSize2, companion.getSIZE_1X1())) {
                    if (spanSize != null) {
                        int left = view.getLeft();
                        Integer num2 = this.fromWidth;
                        view.setRight(left + (num2 != null ? num2.intValue() : view.getWidth()));
                        IDeformableView iDeformableView3 = view instanceof IDeformableView ? (IDeformableView) view : null;
                        if (iDeformableView3 != null) {
                            DragAnimationHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new DragAnimationHelper$.ExternalSyntheticLambda1(0);
                            externalSyntheticLambda1.f$0 = this;
                            externalSyntheticLambda1.f$1 = view;
                            VarHandle.storeStoreFence();
                            iDeformableView3.executeSizeTransformAnimation(spanSize, externalSyntheticLambda1);
                        }
                    }
                    Integer num3 = this.offsetX;
                    if (num3 != null) {
                        int iIntValue2 = num3.intValue();
                        MoveAnim moveAnim3 = this.moveAnim;
                        if (moveAnim3 != null) {
                            DragAnimationHelper$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new DragAnimationHelper$.ExternalSyntheticLambda2(0);
                            externalSyntheticLambda2.f$0 = this;
                            externalSyntheticLambda2.f$1 = view;
                            VarHandle.storeStoreFence();
                            moveAnim3.endDragAnim(externalSyntheticLambda2, intArray[0] + iIntValue2, intArray[1]);
                            return;
                        }
                        return;
                    }
                } else if (Intrinsics.areEqual(spanSize2, companion.getSIZE_2X1())) {
                    if (spanSize != null) {
                        IDeformableView iDeformableView4 = view instanceof IDeformableView ? (IDeformableView) view : null;
                        if (iDeformableView4 != null) {
                            DragAnimationHelper$.ExternalSyntheticLambda1 externalSyntheticLambda3 = new DragAnimationHelper$.ExternalSyntheticLambda1(1);
                            externalSyntheticLambda3.f$0 = this;
                            externalSyntheticLambda3.f$1 = view;
                            VarHandle.storeStoreFence();
                            iDeformableView4.executeSizeTransformAnimation(spanSize, externalSyntheticLambda3);
                        }
                    }
                    Integer num4 = this.offsetX;
                    if (num4 != null) {
                        int iIntValue3 = num4.intValue();
                        MoveAnim moveAnim4 = this.moveAnim;
                        if (moveAnim4 != null) {
                            moveAnim4.updateTranslation(Float.valueOf(view.getTranslationX() - iIntValue3), (Float) null);
                            moveAnim4.updateStartLocation(view.getTranslationX() + intArray[0], view.getTranslationY() + intArray[1]);
                            DragAnimationHelper$.ExternalSyntheticLambda2 externalSyntheticLambda4 = new DragAnimationHelper$.ExternalSyntheticLambda2(1);
                            externalSyntheticLambda4.f$0 = this;
                            externalSyntheticLambda4.f$1 = view;
                            VarHandle.storeStoreFence();
                            moveAnim4.endDragAnim(externalSyntheticLambda4, intArray[0], intArray[1]);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        MoveAnim moveAnim5 = this.moveAnim;
        if (moveAnim5 != null) {
            DragAnimationHelper$$ExternalSyntheticLambda0 dragAnimationHelper$$ExternalSyntheticLambda1 = new DragAnimationHelper$$ExternalSyntheticLambda0(i);
            dragAnimationHelper$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            moveAnim5.endDragAnim(dragAnimationHelper$$ExternalSyntheticLambda1, intArray[0], intArray[1]);
        }
    }

    public final void startPressAnim() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "startPressAnim", null, 8);
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.viewHolderProvider.invoke(this.data);
        if (viewHolder == null) {
            return;
        }
        this.isDragAnimRunning = true;
        this.onAnimationStart.invoke();
        this.curViewHolder = viewHolder;
        if (this.pressAnim == null) {
            this.pressAnim = new PressAnim();
        }
        PressAnim pressAnim = this.pressAnim;
        if (pressAnim != null) {
            pressAnim.cancel();
        }
        PressAnim pressAnim2 = this.pressAnim;
        if (pressAnim2 != null) {
            pressAnim2.bindViewHolder(viewHolder);
        }
        PressAnim pressAnim3 = this.pressAnim;
        if (pressAnim3 != null) {
            pressAnim3.startPressAnim();
        }
    }

    public final void updateLocation(float f, float f2) {
        MoveAnim moveAnim = this.moveAnim;
        if (moveAnim != null) {
            moveAnim.updateLocation(f, f2);
        }
    }
}
