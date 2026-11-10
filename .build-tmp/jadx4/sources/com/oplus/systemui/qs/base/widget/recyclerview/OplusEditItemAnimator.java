package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusEditItemAnimator extends AbsOplusItemAnimator {
    private static final String ADD_ANIMATOR_EMPTY_ALPHA = "add_animator_empty_alpha";
    private static final String ADD_ANIMATOR_TILE_ITEM_ALPHA = "add_animator_tile_item_alpha";
    private static final String ADD_ANIMATOR_TILE_ITEM_SCALE = "add_animator_tile_item_scale";
    private static final String CHANGE_ANIMATOR_TILE_ITEM_SCALE = "change_animator_tile_item_scale";
    private static final String CHANGE_ANIMATOR_TILE_ITEM_SCALE_SECOND = "change_animator_tile_item_scale_second";
    public static final Companion Companion = new Companion();
    private static final String MOVE_ANIMATOR_TILE_ITEM_TRANSLATION = "move_animator_tile_item_translation";
    private static final String MOVE_LAST_ANIMATOR_TILE_ITEM_IN_ALPHA = "move_last_animator_tile_item_in_alpha";
    private static final String MOVE_LAST_ANIMATOR_TILE_ITEM_IN_SCALE = "move_last_animator_tile_item_in_scale";
    private static final String MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_ALPHA = "move_last_animator_tile_item_out_alpha";
    private static final String MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_SCALE = "move_last_animator_tile_item_out_scale";
    private static final String REMOVE_ANIMATOR_EMPTY_ALPHA = "remove_animator_empty_alpha";
    private static final String REMOVE_ANIMATOR_TILE_ITEM_ALPHA = "remove_animator_tile_item_alpha";
    private static final String REMOVE_ANIMATOR_TILE_ITEM_SCALE = "remove_animator_tile_item_scale";
    private static final Map<String, OplusItemSpringAnimationHolder$AnimationConfig> animationConfigMap;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final OplusItemSpringAnimationHolder$AnimationConfig access$getAnimationConfig(Companion companion, String str) {
            companion.getClass();
            return (OplusItemSpringAnimationHolder$AnimationConfig) OplusEditItemAnimator.animationConfigMap.get(str);
        }

        public static Pair addConfig(String str, float f, float f2, Float f3) {
            return new Pair(str, new OplusItemSpringAnimationHolder$AnimationConfig(f3, f2, OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, OliveLinkConnector.SEP_QS_SERVER_NAME, str, 0.0f, f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)));
        }
    }

    static {
        Pair pairAddConfig = Companion.addConfig(MOVE_ANIMATOR_TILE_ITEM_TRANSLATION, 0.35f, 0.12f, null);
        Float fValueOf = Float.valueOf(0.8f);
        Pair pairAddConfig2 = Companion.addConfig(ADD_ANIMATOR_TILE_ITEM_SCALE, 0.4f, 1.0f, fValueOf);
        Float fValueOf2 = Float.valueOf(0.0f);
        animationConfigMap = MapsKt__MapsKt.mapOf(new Pair[]{pairAddConfig, pairAddConfig2, Companion.addConfig(ADD_ANIMATOR_TILE_ITEM_ALPHA, 0.4f, 1.0f, fValueOf2), Companion.addConfig(ADD_ANIMATOR_EMPTY_ALPHA, 0.2f, 1.0f, fValueOf), Companion.addConfig(REMOVE_ANIMATOR_TILE_ITEM_SCALE, 0.25f, 0.8f, null), Companion.addConfig(REMOVE_ANIMATOR_TILE_ITEM_ALPHA, 0.25f, 0.0f, null), Companion.addConfig(REMOVE_ANIMATOR_EMPTY_ALPHA, 0.4f, 0.0f, null), Companion.addConfig(CHANGE_ANIMATOR_TILE_ITEM_SCALE, 0.35f, 1.08f, Float.valueOf(1.0f)), Companion.addConfig(CHANGE_ANIMATOR_TILE_ITEM_SCALE_SECOND, 0.37f, 1.0f, Float.valueOf(1.08f)), Companion.addConfig(MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_SCALE, 0.35f, 0.8f, null), Companion.addConfig(MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_ALPHA, 0.35f, 0.0f, null), Companion.addConfig(MOVE_LAST_ANIMATOR_TILE_ITEM_IN_SCALE, 0.35f, 1.0f, fValueOf), Companion.addConfig(MOVE_LAST_ANIMATOR_TILE_ITEM_IN_ALPHA, 0.35f, 1.0f, fValueOf2)});
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.AbsOplusItemAnimator
    public OplusItemSpringAnimationHolder createAddAnimationHolder(View view) {
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.createSpringAnimationHolder(view);
        Companion companion = Companion;
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createAlphaAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, ADD_ANIMATOR_TILE_ITEM_ALPHA)});
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, ADD_ANIMATOR_TILE_ITEM_SCALE)});
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, ADD_ANIMATOR_TILE_ITEM_SCALE)});
        return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.AbsOplusItemAnimator
    public OplusItemSpringAnimationHolder createChangeAnimationHolder(View view) {
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.createSpringAnimationHolder(view);
        Companion companion = Companion;
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, CHANGE_ANIMATOR_TILE_ITEM_SCALE), Companion.access$getAnimationConfig(companion, CHANGE_ANIMATOR_TILE_ITEM_SCALE_SECOND)});
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, CHANGE_ANIMATOR_TILE_ITEM_SCALE), Companion.access$getAnimationConfig(companion, CHANGE_ANIMATOR_TILE_ITEM_SCALE_SECOND)});
        return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.AbsOplusItemAnimator
    public OplusItemSpringAnimationHolder createMoveAnimationHolder(View view, boolean z, boolean z2, int i, int i2) {
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.createSpringAnimationHolder(view);
        if (z) {
            Companion companion = Companion;
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createAlphaAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, MOVE_LAST_ANIMATOR_TILE_ITEM_IN_ALPHA)});
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, MOVE_LAST_ANIMATOR_TILE_ITEM_IN_SCALE)});
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, MOVE_LAST_ANIMATOR_TILE_ITEM_IN_SCALE)});
            return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
        }
        if (z2) {
            Companion companion2 = Companion;
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createAlphaAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion2, MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_ALPHA)});
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion2, MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_SCALE)});
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion2, MOVE_LAST_ANIMATOR_TILE_ITEM_OUT_SCALE)});
            return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
        }
        if (i != 0) {
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createTranslationXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(Companion, MOVE_ANIMATOR_TILE_ITEM_TRANSLATION)});
        }
        if (i2 != 0) {
            oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createTranslationYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(Companion, MOVE_ANIMATOR_TILE_ITEM_TRANSLATION)});
        }
        return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.AbsOplusItemAnimator
    public OplusItemSpringAnimationHolder createRemoveAnimationHolder(View view) {
        OplusItemSpringAnimationHolder oplusItemSpringAnimationHolderCreateSpringAnimationHolder = OplusItemSpringAnimationHolder.Companion.createSpringAnimationHolder(view);
        Companion companion = Companion;
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createAlphaAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, REMOVE_ANIMATOR_TILE_ITEM_ALPHA)});
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleXAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, REMOVE_ANIMATOR_TILE_ITEM_SCALE)});
        oplusItemSpringAnimationHolderCreateSpringAnimationHolder.createScaleYAnimation(new OplusItemSpringAnimationHolder$AnimationConfig[]{Companion.access$getAnimationConfig(companion, REMOVE_ANIMATOR_TILE_ITEM_SCALE)});
        return oplusItemSpringAnimationHolderCreateSpringAnimationHolder;
    }
}
