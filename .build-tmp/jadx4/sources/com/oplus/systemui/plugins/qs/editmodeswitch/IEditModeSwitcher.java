package com.oplus.systemui.plugins.qs.editmodeswitch;

import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IEditModeSwitcher {
    public static final String ADDING_TRANSITION = "adding_transition";
    public static final String ADDING_TRANSITION_POSITION = "adding_transition_position";
    public static final String ADDING_TRANSITION_TILE_ALPHA = "adding_transition_tile_alpha";
    public static final String ADDING_TRANSITION_TILE_STROKE_ALPHA = "adding_transition_tile_stroke_alpha";
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String DELETE_ICON_ALPHA_TRANSITION = "remove_icon_alpha_transition";
    public static final String DELETE_ICON_SCALE_TRANSITION = "remove_icon_scale_transition";
    public static final String DRAG_DISABLE_TRANSITION = "drag_disable_transition";
    public static final String DRAG_LABEL_TRANSITION = "drag_label_transition";
    public static final String DRAG_TRANSITION = "drag_transition";
    public static final String MAIN_TRANSITION = "enter_exit_edit_mode_main_transition";
    public static final String PARENT_DISPLACEMENT_TRANSITION = "parent_translation_transition";
    public static final String SAVE_ADD_BUTTON_TRANSITION = "add_save_icon_alpha_transition";
    public static final String STATUS_BAR_AND_QUICK_ENTRANCE_TRANSITION = "status_bar_alpha_transition";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String ADDING_TRANSITION = "adding_transition";
        public static final String ADDING_TRANSITION_POSITION = "adding_transition_position";
        public static final String ADDING_TRANSITION_TILE_ALPHA = "adding_transition_tile_alpha";
        public static final String ADDING_TRANSITION_TILE_STROKE_ALPHA = "adding_transition_tile_stroke_alpha";
        public static final String DELETE_ICON_ALPHA_TRANSITION = "remove_icon_alpha_transition";
        public static final String DELETE_ICON_SCALE_TRANSITION = "remove_icon_scale_transition";
        public static final String DRAG_DISABLE_TRANSITION = "drag_disable_transition";
        public static final String DRAG_LABEL_TRANSITION = "drag_label_transition";
        public static final String DRAG_TRANSITION = "drag_transition";
        public static final String MAIN_TRANSITION = "enter_exit_edit_mode_main_transition";
        public static final String PARENT_DISPLACEMENT_TRANSITION = "parent_translation_transition";
        public static final String SAVE_ADD_BUTTON_TRANSITION = "add_save_icon_alpha_transition";
        public static final String STATUS_BAR_AND_QUICK_ENTRANCE_TRANSITION = "status_bar_alpha_transition";
        private static final Map<String, Spring> customTransitionConfigMap;

        static {
            OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
            customTransitionConfigMap = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("enter_exit_edit_mode_main_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "enter_exit_edit_mode_main_transition", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("add_save_icon_alpha_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "add_save_icon_alpha_transition", 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("status_bar_alpha_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "status_bar_alpha_transition", 0.0f, 0.2f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("remove_icon_scale_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "remove_icon_scale_transition", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("remove_icon_alpha_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "remove_icon_alpha_transition", 0.0f, 0.2f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("parent_translation_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "parent_translation_transition", 0.0f, 0.4f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("drag_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "drag_transition", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("drag_label_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "drag_label_transition", 0.0f, 0.2f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("adding_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "adding_transition", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("adding_transition_tile_stroke_alpha", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "adding_transition_tile_stroke_alpha", 0.0f, 0.35f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("adding_transition_tile_alpha", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "adding_transition_tile_alpha", 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("adding_transition_position", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "adding_transition_position", 0.0f, 0.35f, 0.0f, 0.0f, 0.0f, null, null, null, 1008)), TuplesKt.to("drag_disable_transition", OliveLinkConnector.safeSpring$default(oliveLinkConnector, OliveLinkConnector.SEP_QS_SERVER_NAME, "drag_transition", 0.0f, 0.3f, 0.0f, 0.0f, 0.0f, null, null, null, 1008))});
        }

        public final SpringTransition createTransition(final String str) {
            final Spring springConfig = getSpringConfig(str);
            return new SpringTransition(str) { // from class: com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher$Companion$createTransition$1
                @Override // com.oplus.systemui.qs.base.springtransition.SpringTransition
                public final COUISpringAnimation createSpringAnimation() {
                    COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
                    Spring spring = springConfig;
                    if (spring != null) {
                        COUISpringForce cOUISpringForce = new COUISpringForce();
                        cOUISpringForce.setBounce(spring.bounce());
                        cOUISpringForce.setResponse(spring.response());
                        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
                        cOUISpringAnimation.setSpring(cOUISpringForce);
                        cOUISpringAnimation.setStartVelocity(spring.velocity());
                        cOUISpringAnimation.setStartValue(0.0f);
                    }
                    return cOUISpringAnimation;
                }
            };
        }

        public final Spring getSpringConfig(String str) {
            Map<String, Spring> map = customTransitionConfigMap;
            Spring spring = map.get(str);
            return spring == null ? map.get("enter_exit_edit_mode_main_transition") : spring;
        }
    }

    default void bindTargetProperties(Set<? extends SpringTransition> set) {
    }

    default SpringTransition[] createSpringTransition(String str) {
        return new SpringTransition[0];
    }
}
