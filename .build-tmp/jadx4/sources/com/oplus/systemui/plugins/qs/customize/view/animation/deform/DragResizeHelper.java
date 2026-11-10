package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.core.view.OneShotPreDrawListener;
import com.coui.appcompat.animation.COUISpringDragHelper;
import com.oplus.systemui.plugins.qs.customize.view.deformation.DeformationFrameLayout$dragResizeHelperCallback$1;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragResizeHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DragResizeHelper";
    private IDeformationAnimationController animationController;
    private final ViewGroup cellHostView;
    public ViewGroup currHostView;
    private final CapsuleLeftAnimationProperty currInnerViewLeftAnimationProperty;
    public final int dragHotRectSize;
    public int lastHeight;
    public int lastWidth;
    private final IDragResizeHelperCallback resizeCallback;
    private final Lazy springDragHelper$delegate;
    private final Map<SpanSize, Size> validSizeMap = new LinkedHashMap();
    private final Map<SpanSize, ViewGroup> allSupportHost = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$egfJxzeFRJqSff8ewir8fq--sC4, reason: not valid java name */
    public static Unit m158$r8$lambda$egfJxzeFRJqSff8ewir8fqsC4(DragResizeHelper dragResizeHelper, ViewGroup viewGroup) {
        ViewGroup viewGroup2 = dragResizeHelper.currHostView;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        if (!Intrinsics.areEqual(viewGroup, viewGroup2)) {
            ((DeformationFrameLayout$dragResizeHelperCallback$1) dragResizeHelper.resizeCallback).recycle(viewGroup);
        }
        return Unit.INSTANCE;
    }

    public DragResizeHelper(ViewGroup viewGroup, int i, IDragResizeHelperCallback iDragResizeHelperCallback) {
        this.cellHostView = viewGroup;
        this.dragHotRectSize = i;
        this.resizeCallback = iDragResizeHelperCallback;
        DragResizeHelper$$ExternalSyntheticLambda0 dragResizeHelper$$ExternalSyntheticLambda0 = new DragResizeHelper$$ExternalSyntheticLambda0();
        dragResizeHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.springDragHelper$delegate = LazyKt__LazyJVMKt.lazy(dragResizeHelper$$ExternalSyntheticLambda0);
        this.currInnerViewLeftAnimationProperty = new CapsuleLeftAnimationProperty();
    }

    public static final void access$onEnd(DragResizeHelper dragResizeHelper) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            ViewGroup viewGroup = dragResizeHelper.currHostView;
            if (viewGroup == null) {
                viewGroup = null;
            }
            Log.d("SystemUi--QuickSettings", "DragResizeHelper-->".concat("[onEnd] currHostView=" + viewGroup));
        }
        dragResizeHelper.cellHostView.suppressLayout(false);
        dragResizeHelper.cellHostView.setElevation(0.0f);
        IDeformationAnimationController iDeformationAnimationController = dragResizeHelper.animationController;
        if (iDeformationAnimationController != null) {
            DragResizeHelper$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new DragResizeHelper$.ExternalSyntheticLambda1();
            externalSyntheticLambda1.f$0 = dragResizeHelper;
            VarHandle.storeStoreFence();
            iDeformationAnimationController.stop(externalSyntheticLambda1);
        }
        ((DeformationFrameLayout$dragResizeHelperCallback$1) dragResizeHelper.resizeCallback).setDeforming(false);
    }

    public static final void access$onSizeChange(DragResizeHelper dragResizeHelper, int i, int i2) {
        Object next;
        Object next2;
        Object next3;
        int i3;
        ViewGroup viewGroup = dragResizeHelper.currHostView;
        Object next4 = null;
        if (viewGroup == null) {
            viewGroup = null;
        }
        viewGroup.setLeftTopRightBottom(viewGroup.getLeft(), viewGroup.getTop(), viewGroup.getLeft() + i, viewGroup.getTop() + i2);
        IDeformationAnimationController iDeformationAnimationController = dragResizeHelper.animationController;
        if (iDeformationAnimationController != null) {
            IDeformationAnimationController.ComputeResult computeResultCalculateAnimationStartAndEndView = iDeformationAnimationController.calculateAnimationStartAndEndView(dragResizeHelper.lastWidth, dragResizeHelper.lastHeight, i, i2);
            dragResizeHelper.currInnerViewLeftAnimationProperty.offsetLeftAndRight(computeResultCalculateAnimationStartAndEndView.getEndHost(), computeResultCalculateAnimationStartAndEndView.getXProgressFromCurr());
            iDeformationAnimationController.updateFrame(computeResultCalculateAnimationStartAndEndView);
            Iterator<T> it = dragResizeHelper.validSizeMap.values().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int width = ((Size) next).getWidth();
                    do {
                        Object next5 = it.next();
                        int width2 = ((Size) next5).getWidth();
                        if (width > width2) {
                            next = next5;
                            width = width2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            Size size = (Size) next;
            int i4 = 0;
            int width3 = size != null ? size.getWidth() : 0;
            Iterator<T> it2 = dragResizeHelper.validSizeMap.values().iterator();
            if (it2.hasNext()) {
                next2 = it2.next();
                if (it2.hasNext()) {
                    int width4 = ((Size) next2).getWidth();
                    do {
                        Object next6 = it2.next();
                        int width5 = ((Size) next6).getWidth();
                        if (width4 < width5) {
                            next2 = next6;
                            width4 = width5;
                        }
                    } while (it2.hasNext());
                }
            } else {
                next2 = null;
            }
            Size size2 = (Size) next2;
            int width6 = size2 != null ? size2.getWidth() : 0;
            Iterator<T> it3 = dragResizeHelper.validSizeMap.values().iterator();
            if (it3.hasNext()) {
                next3 = it3.next();
                if (it3.hasNext()) {
                    int height = ((Size) next3).getHeight();
                    do {
                        Object next7 = it3.next();
                        int height2 = ((Size) next7).getHeight();
                        if (height > height2) {
                            next3 = next7;
                            height = height2;
                        }
                    } while (it3.hasNext());
                }
            } else {
                next3 = null;
            }
            Size size3 = (Size) next3;
            int height3 = size3 != null ? size3.getHeight() : 0;
            Iterator<T> it4 = dragResizeHelper.validSizeMap.values().iterator();
            if (it4.hasNext()) {
                next4 = it4.next();
                if (it4.hasNext()) {
                    int height4 = ((Size) next4).getHeight();
                    do {
                        Object next8 = it4.next();
                        int height5 = ((Size) next8).getHeight();
                        if (height4 < height5) {
                            next4 = next8;
                            height4 = height5;
                        }
                    } while (it4.hasNext());
                }
            }
            Size size4 = (Size) next4;
            int height6 = size4 != null ? size4.getHeight() : 0;
            boolean z = i <= width3;
            boolean z2 = i >= width6;
            if (z) {
                i3 = i - width3;
            } else {
                i3 = z2 ? i - width6 : 0;
            }
            boolean z3 = i2 <= height3;
            boolean z4 = i2 >= height6;
            if (z3) {
                i4 = i2 - height3;
            } else if (z4) {
                i4 = i2 - height6;
            }
            if (z || z2 || z3 || z4) {
                iDeformationAnimationController.squeezeOrStretch(i3, i4, i);
            }
        }
        ((DeformationFrameLayout$dragResizeHelperCallback$1) dragResizeHelper.resizeCallback).onSizeChanged(i, i2);
        dragResizeHelper.lastWidth = i;
        dragResizeHelper.lastHeight = i2;
    }

    private final COUISpringDragHelper getSpringDragHelper() {
        return (COUISpringDragHelper) this.springDragHelper$delegate.getValue();
    }

    private final void setStableSizeList(Map<SpanSize, Size> map) {
        this.validSizeMap.clear();
        this.validSizeMap.putAll(map);
        Collection<Size> collectionValues = map.values();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionValues, 10));
        for (Size size : collectionValues) {
            arrayList.add(new COUISpringDragHelper.Vec2(size.getWidth(), size.getHeight()));
        }
        getSpringDragHelper().setStableList(new ArrayList(arrayList));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "DragResizeHelper-->".concat("setValidSize -- " + arrayList));
        }
    }

    public final IDeformationAnimationController getAnimationController() {
        return this.animationController;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public final boolean onActionDown(MotionEvent motionEvent, ViewGroup viewGroup, Function0 function0) {
        ViewGroup viewGroup2;
        this.currHostView = viewGroup;
        boolean zOnTouchEvent = onTouchEvent(motionEvent);
        if (zOnTouchEvent && !((DeformationFrameLayout$dragResizeHelperCallback$1) this.resizeCallback).isDeforming) {
            this.allSupportHost.clear();
            setStableSizeList((Map) function0.invoke());
            IDeformationAnimationController iDeformationAnimationController = this.animationController;
            if (iDeformationAnimationController != null) {
                Map<SpanSize, Size> map = this.validSizeMap;
                LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(map.size()));
                Iterator<T> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object key = entry.getKey();
                    ViewGroup viewGroup3 = (ViewGroup) ((DeformationFrameLayout$dragResizeHelperCallback$1) this.resizeCallback).getInnerViewAttachedData((SpanSize) entry.getKey());
                    this.allSupportHost.put(entry.getKey(), viewGroup3);
                    linkedHashMap.put(key, viewGroup3);
                }
                viewGroup2 = viewGroup;
                iDeformationAnimationController.prepare(viewGroup2, linkedHashMap, this.validSizeMap, viewGroup.isLayoutRtl(), true);
            } else {
                viewGroup2 = viewGroup;
            }
            this.lastWidth = viewGroup2.getWidth();
            this.lastHeight = viewGroup2.getHeight();
            this.cellHostView.setElevation(1.0f);
            this.cellHostView.suppressLayout(true);
            this.currInnerViewLeftAnimationProperty.setCurrHostView(viewGroup2);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry<SpanSize, ViewGroup> entry2 : this.allSupportHost.entrySet()) {
                SpanSize key2 = entry2.getKey();
                ViewGroup value = entry2.getValue();
                Integer innerViewLeft = ((DeformationFrameLayout$dragResizeHelperCallback$1) this.resizeCallback).getInnerViewLeft(key2);
                if (innerViewLeft != null) {
                    linkedHashMap2.put(value, Integer.valueOf(innerViewLeft.intValue()));
                }
            }
            this.currInnerViewLeftAnimationProperty.prepare(linkedHashMap2);
            ((DeformationFrameLayout$dragResizeHelperCallback$1) this.resizeCallback).setDeforming(true);
        }
        return zOnTouchEvent;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        COUISpringDragHelper.Vec2 attachPosition;
        SpanSize key;
        ViewGroup viewGroup;
        int action = motionEvent.getAction();
        if (action == 0) {
            float x = motionEvent.getX();
            ViewGroup viewGroup2 = this.currHostView;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            int width = viewGroup2.getWidth();
            int i = this.dragHotRectSize;
            if (x >= width - i) {
                float y = motionEvent.getY();
                ViewGroup viewGroup3 = this.currHostView;
                if (viewGroup3 == null) {
                    viewGroup3 = null;
                }
                if (y >= viewGroup3.getHeight() - i) {
                    COUISpringDragHelper springDragHelper = getSpringDragHelper();
                    ViewGroup viewGroup4 = this.currHostView;
                    if (viewGroup4 == null) {
                        viewGroup4 = null;
                    }
                    float width2 = viewGroup4.getWidth();
                    ViewGroup viewGroup5 = this.currHostView;
                    float height = (viewGroup5 != null ? viewGroup5 : null).getHeight();
                    springDragHelper.mStartX = width2;
                    springDragHelper.mStartY = height;
                    springDragHelper.mCurX = width2;
                    springDragHelper.mCurY = height;
                    getSpringDragHelper().springDrag(motionEvent);
                    return true;
                }
            }
            return false;
        }
        if ((action == 1 || action == 3) && (attachPosition = getSpringDragHelper().getAttachPosition()) != null) {
            Iterator<Map.Entry<SpanSize, Size>> it = this.validSizeMap.entrySet().iterator();
            do {
                if (!it.hasNext()) {
                    key = null;
                    break;
                }
                Map.Entry<SpanSize, Size> next = it.next();
                key = (next.getValue().getWidth() == ((int) attachPosition.getX()) && next.getValue().getHeight() == ((int) attachPosition.getY())) ? next.getKey() : null;
            } while (key == null);
            if (key != null && (viewGroup = this.allSupportHost.get(key)) != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    ViewGroup viewGroup6 = this.currHostView;
                    if (viewGroup6 == null) {
                        viewGroup6 = null;
                    }
                    Log.d("SystemUi--QuickSettings", "DragResizeHelper-->".concat("[onFlingStarted] newHostView=" + viewGroup + ", lastHostView=" + viewGroup6));
                }
                ViewGroup viewGroup7 = this.currHostView;
                if (viewGroup7 == null) {
                    viewGroup7 = null;
                }
                int left = viewGroup7.getLeft();
                ViewGroup viewGroup8 = this.currHostView;
                if (viewGroup8 == null) {
                    viewGroup8 = null;
                }
                int top = viewGroup8.getTop();
                ViewGroup viewGroup9 = this.currHostView;
                if (viewGroup9 == null) {
                    viewGroup9 = null;
                }
                int right = viewGroup9.getRight();
                ViewGroup viewGroup10 = this.currHostView;
                if (viewGroup10 == null) {
                    viewGroup10 = null;
                }
                Rect rect = new Rect(left, top, right, viewGroup10.getBottom());
                ViewGroup viewGroup11 = this.currHostView;
                if (viewGroup11 == null) {
                    viewGroup11 = null;
                }
                this.currHostView = viewGroup;
                this.currInnerViewLeftAnimationProperty.setCurrHostView(viewGroup);
                IDragResizeHelperCallback iDragResizeHelperCallback = this.resizeCallback;
                ViewGroup viewGroup12 = this.currHostView;
                if (viewGroup12 == null) {
                    viewGroup12 = null;
                }
                ((DeformationFrameLayout$dragResizeHelperCallback$1) iDragResizeHelperCallback).updateInnerView(viewGroup12, key);
                ViewGroup viewGroup13 = this.currHostView;
                if (viewGroup13 == null) {
                    viewGroup13 = null;
                }
                if (!Intrinsics.areEqual(viewGroup11, viewGroup13)) {
                    IDeformationAnimationController iDeformationAnimationController = this.animationController;
                    if (iDeformationAnimationController != null) {
                        ViewGroup viewGroup14 = this.currHostView;
                        if (viewGroup14 == null) {
                            viewGroup14 = null;
                        }
                        iDeformationAnimationController.onFlingStarted(viewGroup14, key);
                    }
                    ViewGroup viewGroup15 = this.currHostView;
                    ViewGroup viewGroup16 = viewGroup15 != null ? viewGroup15 : null;
                    DragResizeHelper$onFlingStarted$.inlined.doOnPreDraw.1 r0 = new DragResizeHelper$onFlingStarted$.inlined.doOnPreDraw.1();
                    r0.this$0 = this;
                    r0.$lastHostViewBounds$inlined = rect;
                    VarHandle.storeStoreFence();
                    OneShotPreDrawListener.add(viewGroup16, r0);
                    this.cellHostView.suppressLayout(false);
                }
            }
        }
        getSpringDragHelper().springDrag(motionEvent);
        return true;
    }

    public final void setAnimationController(IDeformationAnimationController iDeformationAnimationController) {
        this.animationController = iDeformationAnimationController;
    }
}
