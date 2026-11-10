package com.oplus.systemui.qs.base.widget.recyclerview;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPagerStaggerSnapHelper f$0;

    public /* synthetic */ OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        final OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper = this.f$0;
        switch (i) {
            case 0:
                final int i2 = 0;
                COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatPropertyCompat(oplusPagerStaggerSnapHelper, i2) { // from class: com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$1
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ OplusPagerStaggerSnapHelper this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super("offsetX");
                        this.$r8$classId = i2;
                        switch (i2) {
                            case 1:
                                this.this$0 = oplusPagerStaggerSnapHelper;
                                super("offsetY");
                                break;
                            default:
                                this.this$0 = oplusPagerStaggerSnapHelper;
                                break;
                        }
                    }

                    public final float getValue(Object obj) {
                        int i3;
                        switch (this.$r8$classId) {
                            case 0:
                                i3 = ((OplusPagerStaggerSnapHelper) obj).offsetX;
                                break;
                            default:
                                i3 = ((OplusPagerStaggerSnapHelper) obj).offsetY;
                                break;
                        }
                        return i3;
                    }

                    public final void setValue(Object obj, float f) {
                        int i3 = this.$r8$classId;
                        OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper2 = this.this$0;
                        switch (i3) {
                            case 0:
                                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper3 = (OplusPagerStaggerSnapHelper) obj;
                                RecyclerView recyclerView = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                                if (recyclerView != null) {
                                    recyclerView.scrollBy((int) (f - oplusPagerStaggerSnapHelper3.offsetX), 0);
                                }
                                int i4 = oplusPagerStaggerSnapHelper3.offsetX;
                                oplusPagerStaggerSnapHelper3.offsetX = i4 + ((int) (f - i4));
                                break;
                            default:
                                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper4 = (OplusPagerStaggerSnapHelper) obj;
                                RecyclerView recyclerView2 = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                                if (recyclerView2 != null) {
                                    recyclerView2.scrollBy(0, (int) (f - oplusPagerStaggerSnapHelper4.offsetY));
                                }
                                int i5 = oplusPagerStaggerSnapHelper4.offsetY;
                                oplusPagerStaggerSnapHelper4.offsetY = i5 + ((int) (f - i5));
                                break;
                        }
                    }
                }, oplusPagerStaggerSnapHelper);
                COUISpringForce cOUISpringForce = new COUISpringForce();
                OplusPagerStaggerSnapHelper.Companion companion = OplusPagerStaggerSnapHelper.Companion;
                cOUISpringForce.setBounce(companion.getSpringConfig().bounce());
                cOUISpringForce.setResponse(companion.getSpringConfig().response());
                cOUISpringAnimation.setSpring(cOUISpringForce);
                OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2 oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2 = new OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2(0);
                oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2.this$0 = oplusPagerStaggerSnapHelper;
                VarHandle.storeStoreFence();
                cOUISpringAnimation.addEndListener(oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2);
                return cOUISpringAnimation;
            default:
                final int i3 = 1;
                COUISpringAnimation cOUISpringAnimation2 = new COUISpringAnimation(new FloatPropertyCompat(oplusPagerStaggerSnapHelper, i3) { // from class: com.oplus.systemui.qs.base.widget.recyclerview.OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$1
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ OplusPagerStaggerSnapHelper this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super("offsetX");
                        this.$r8$classId = i3;
                        switch (i3) {
                            case 1:
                                this.this$0 = oplusPagerStaggerSnapHelper;
                                super("offsetY");
                                break;
                            default:
                                this.this$0 = oplusPagerStaggerSnapHelper;
                                break;
                        }
                    }

                    public final float getValue(Object obj) {
                        int i4;
                        switch (this.$r8$classId) {
                            case 0:
                                i4 = ((OplusPagerStaggerSnapHelper) obj).offsetX;
                                break;
                            default:
                                i4 = ((OplusPagerStaggerSnapHelper) obj).offsetY;
                                break;
                        }
                        return i4;
                    }

                    public final void setValue(Object obj, float f) {
                        int i4 = this.$r8$classId;
                        OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper2 = this.this$0;
                        switch (i4) {
                            case 0:
                                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper3 = (OplusPagerStaggerSnapHelper) obj;
                                RecyclerView recyclerView = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                                if (recyclerView != null) {
                                    recyclerView.scrollBy((int) (f - oplusPagerStaggerSnapHelper3.offsetX), 0);
                                }
                                int i5 = oplusPagerStaggerSnapHelper3.offsetX;
                                oplusPagerStaggerSnapHelper3.offsetX = i5 + ((int) (f - i5));
                                break;
                            default:
                                OplusPagerStaggerSnapHelper oplusPagerStaggerSnapHelper4 = (OplusPagerStaggerSnapHelper) obj;
                                RecyclerView recyclerView2 = oplusPagerStaggerSnapHelper2.attachedRecyclerView;
                                if (recyclerView2 != null) {
                                    recyclerView2.scrollBy(0, (int) (f - oplusPagerStaggerSnapHelper4.offsetY));
                                }
                                int i6 = oplusPagerStaggerSnapHelper4.offsetY;
                                oplusPagerStaggerSnapHelper4.offsetY = i6 + ((int) (f - i6));
                                break;
                        }
                    }
                }, oplusPagerStaggerSnapHelper);
                COUISpringForce cOUISpringForce2 = new COUISpringForce();
                OplusPagerStaggerSnapHelper.Companion companion2 = OplusPagerStaggerSnapHelper.Companion;
                cOUISpringForce2.setBounce(companion2.getSpringConfig().bounce());
                cOUISpringForce2.setResponse(companion2.getSpringConfig().response());
                cOUISpringAnimation2.setSpring(cOUISpringForce2);
                OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2 oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$3 = new OplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$2(1);
                oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$3.this$0 = oplusPagerStaggerSnapHelper;
                VarHandle.storeStoreFence();
                cOUISpringAnimation2.addEndListener(oplusPagerStaggerSnapHelper$offsetXSpringAnimation$2$2$3);
                return cOUISpringAnimation2;
        }
    }
}
