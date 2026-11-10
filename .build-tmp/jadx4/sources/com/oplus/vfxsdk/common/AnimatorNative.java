package com.oplus.vfxsdk.common;

import com.oplus.vfxsdk.common.parameter.IPop;
import com.oplus.vfxsdk.common.parameter.IUpdate;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatorNative extends AbsAnimator {
    public static final Companion Companion = new Companion();
    public static final String TAG = "VFX:AnimatorNative";
    private AnimatorNative animator;
    private HashMap<String, ChannelData> builtinAnimMap;
    private final IPop pop;
    private IUpdate update;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AnimatorNative(COEData cOEData, int i) {
        super(cOEData, i);
        this.animator = this;
        this.builtinAnimMap = new HashMap<>();
        this.update = new AnimatorNative$update$1();
        this.pop = new AnimatorNative$pop$1();
    }

    public final HashMap<String, ChannelData> getBuiltinAnimMap() {
        return this.builtinAnimMap;
    }

    public final IPop getPop() {
        return this.pop;
    }

    public final IUpdate getUpdate() {
        return this.update;
    }

    @Override // com.oplus.vfxsdk.common.AbsAnimator
    public HashMap<String, Animator> initAnimators(IUpdate iUpdate, IPop iPop) {
        return super.initAnimators(this.update, this.pop);
    }

    public final void initBuiltin(Animator animator, AnimLine animLine, IUpdate iUpdate) {
        String str = COEParse.Companion.getBuiltinAnimLineMap().get(animLine.getKey());
        if (str == null) {
            return;
        }
        ChannelData channelData = this.builtinAnimMap.containsKey(str) ? this.builtinAnimMap.get(str) : new ChannelData();
        channelData.getClass();
        channelData.setName(str);
        channelData.setType(animLine.getType());
        IKey iKey = (IKey) ArraysKt___ArraysKt.getOrNull(animLine.getAnimKeys(), 0);
        channelData.addChannel(Float.valueOf(iKey != null ? iKey.getValue() : 0.0f), animLine.getIndex());
        animLine.setChannelData(channelData);
        int iHashCode = animLine.hashCode();
        initBuiltin.1 r2 = new initBuiltin.1();
        r2.$channelData = channelData;
        r2.$animeLine = animLine;
        VarHandle.storeStoreFence();
        animator.setAnimLineUpdate(iHashCode, r2);
        animator.setAnimLinePop(animLine.hashCode(), new initBuiltin.2());
        this.builtinAnimMap.put(channelData.getName(), channelData);
        channelData.setUpdate(iUpdate);
    }

    public final void initBuiltinAnimMap(HashMap<String, AnimatorValue> map, IUpdate iUpdate) {
        Animator animator;
        for (Map.Entry<String, AnimatorValue> entry : map.entrySet()) {
            HashMap<String, Animator> animators = getAnimators();
            if (animators == null || (animator = animators.get(entry.getKey())) == null) {
                return;
            }
            for (AnimLine animLine : entry.getValue().getAnimLines()) {
                initBuiltin(animator, animLine, iUpdate);
            }
        }
    }

    public void setAnimator(AnimatorNative animatorNative) {
        this.animator = animatorNative;
    }

    public final void setUpdate(IUpdate iUpdate) {
        this.update = iUpdate;
    }

    @Override // com.oplus.vfxsdk.common.AbsAnimator, com.oplus.vfxsdk.common.IAnimator
    public AnimatorNative getAnimator() {
        return this.animator;
    }
}
