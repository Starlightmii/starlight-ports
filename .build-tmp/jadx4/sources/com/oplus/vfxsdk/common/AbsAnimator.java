package com.oplus.vfxsdk.common;

import android.animation.TimeInterpolator;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.animation.PathInterpolator;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.vfxsdk.common.api.ILinkProxy;
import com.oplus.vfxsdk.common.parameter.AbsParameter;
import com.oplus.vfxsdk.common.parameter.ICallback;
import com.oplus.vfxsdk.common.parameter.IParameter;
import com.oplus.vfxsdk.common.parameter.IPop;
import com.oplus.vfxsdk.common.parameter.IUpdate;
import com.oplus.vfxsdk.common.parameter.Param;
import com.oplus.vfxsdk.common.parameter.ParameterFloat;
import com.oplus.vfxsdk.common.parameter.ParameterFloatArray;
import com.oplus.vfxsdk.common.parameter.ParameterInt;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt___SequencesKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbsAnimator implements IAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "VFX:AbsAnimator";
    private Function0 animatorEndCb;
    private Function0 animatorStartCb;
    private final COEData coeData;
    private ILinkProxy iLinkProxy;
    public final int layerIndex;
    public int parameterCount;
    public int stateAnimatorStartedCount;
    private ArrayList<StateAnimParam> stateAnims;
    private AnimatorType animatorType = AnimatorType.Animator;
    private HashMap<String, Animator> animatorMap = new HashMap<>();
    private HashMap<Integer, HashMap<String, ChannelData>> uniformMapList = new HashMap<>();
    private ArrayList<Map<String, IParameter>> defaultPassParam = new ArrayList<>();
    private final AnimatorHandler animatorHandler = new AnimatorHandler();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getTAG() {
            return AbsAnimator.TAG;
        }

        public final void setAnimLinePop(AnimLine animLine, IParameter iParameter) {
            setAnimLinePop.1 r0 = new setAnimLinePop.1();
            r0.$parameter = iParameter;
            VarHandle.storeStoreFence();
            animLine.setPop(r0);
        }

        public final void setAnimLineUpdate(AnimLine animLine, IParameter iParameter) {
            setAnimLineUpdate.1 r0 = new setAnimLineUpdate.1();
            r0.$parameter = iParameter;
            VarHandle.storeStoreFence();
            animLine.setUpdate(r0);
        }
    }

    public AbsAnimator(COEData cOEData, int i) {
        this.coeData = cOEData;
        this.layerIndex = i;
    }

    public static void parseUniform(Uniform uniform, int i, HashMap map) {
        ChannelData channelData = new ChannelData();
        channelData.setName(uniform.getName());
        channelData.setType(uniform.getType());
        channelData.setPassId(i);
        if (!Intrinsics.areEqual(uniform.getName(), "iResolution")) {
            switch (uniform.getType().ordinal()) {
                case 0:
                    channelData.setV(new float[]{uniform.getX(), uniform.getY()});
                    break;
                case 1:
                    channelData.setV(new float[]{uniform.getX(), uniform.getY(), uniform.getZ()});
                    break;
                case 2:
                case 3:
                    channelData.setV(new float[]{uniform.getX(), uniform.getY(), uniform.getZ(), uniform.getW()});
                    break;
                case 4:
                    channelData.setV((Integer) uniform.getValue());
                    break;
                case 5:
                    channelData.setV((Float) uniform.getValue());
                    break;
                case 6:
                    channelData.setV((Float) uniform.getValue());
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                case 8:
                case 9:
                    break;
                default:
                    MotionLayout$.ExternalSyntheticOutline0.m("Unsupported uniform type: ", uniform.getType().name(), TAG);
                    break;
            }
        }
        map.put(uniform.getName(), channelData);
    }

    public static void setParamValue(AbsParameter absParameter, Object[] objArr) {
        float fFloatValue;
        if (absParameter instanceof ParameterFloat) {
            ((ParameterFloat) absParameter).updateValue((Float) objArr[0]);
            return;
        }
        if (absParameter instanceof ParameterInt) {
            ((ParameterInt) absParameter).updateValue((Integer) objArr[0]);
            return;
        }
        if (absParameter instanceof ParameterFloatArray) {
            ParameterFloatArray parameterFloatArray = (ParameterFloatArray) absParameter;
            ArrayList arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                if (obj instanceof Integer) {
                    fFloatValue = ((Number) obj).intValue();
                } else {
                    if (!(obj instanceof Float)) {
                        Path$.ExternalSyntheticBUOutline0.m("cannot convert");
                        return;
                    }
                    fFloatValue = ((Number) obj).floatValue();
                }
                arrayList.add(Float.valueOf(fFloatValue));
            }
            parameterFloatArray.updateValue(CollectionsKt___CollectionsKt.toFloatArray(arrayList));
        }
    }

    public final void bindAnimLine(AnimLine animLine, IUpdate iUpdate, IPop iPop) {
        String name = animLine.getName();
        Iterator<Map<String, IParameter>> it = this.defaultPassParam.iterator();
        boolean z = false;
        while (it.hasNext()) {
            for (Map.Entry<String, IParameter> entry : it.next().entrySet()) {
                String key = entry.getKey();
                IParameter value = entry.getValue();
                if (Intrinsics.areEqual(key, name)) {
                    Companion companion = Companion;
                    companion.setAnimLineUpdate(animLine, value);
                    companion.setAnimLinePop(animLine, value);
                    z = true;
                    break;
                }
            }
            if (z) {
                break;
            }
        }
        ChannelData uniformData = getUniformData(animLine.getName());
        if (uniformData != null) {
            animLine.setChannelData(uniformData);
        }
        animLine.setUpdate(iUpdate);
        animLine.setPop(iPop);
    }

    public final boolean equalValue(IParameter iParameter, UniformValue uniformValue) {
        Object[] values = uniformValue.getValues();
        if (iParameter instanceof ParameterFloat) {
            if (values.length != 0) {
                Object obj = values[0];
                if ((obj instanceof Number) && ((Number) ((ParameterFloat) iParameter).getValue()).floatValue() == ((Number) obj).floatValue()) {
                    return true;
                }
            }
        } else if (iParameter instanceof ParameterInt) {
            if (values.length != 0) {
                Object obj2 = values[0];
                if ((obj2 instanceof Number) && ((Number) ((ParameterInt) iParameter).getValue()).intValue() == ((Number) obj2).intValue()) {
                    return true;
                }
            }
        } else if (iParameter instanceof ParameterFloatArray) {
            ParameterFloatArray parameterFloatArray = (ParameterFloatArray) iParameter;
            if (values.length == ((float[]) parameterFloatArray.getValue()).length) {
                if (values.length == 0 && ((float[]) parameterFloatArray.getValue()).length == 0) {
                    return true;
                }
                try {
                    int length = values.length;
                    for (int i = 0; i < length; i++) {
                        Object obj3 = values[i];
                        if (!(obj3 instanceof Number)) {
                            throw new IllegalArgumentException("Unsupported type: " + Reflection.getOrCreateKotlinClass(obj3.getClass()).getSimpleName());
                        }
                        if (((float[]) ((ParameterFloatArray) iParameter).getValue())[i] == ((Number) obj3).floatValue()) {
                        }
                    }
                    return true;
                } catch (Exception e) {
                    throw new IllegalArgumentException(FileProvider$.ExternalSyntheticOutline0.m("Error converting target values: ", e.getMessage()), e);
                }
            }
        }
        return false;
    }

    public final void findAnimatorByKey(String str, Function1 function1) {
        this.animatorType = AnimatorType.Animator;
        HashMap<String, Animator> animators = getAnimators();
        if (animators == null || !animators.containsKey(str)) {
            return;
        }
        Animator animator = animators.get(str);
        animator.getClass();
        function1.invoke(animator);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public HashMap<String, PassParams[]> getAllTrigers() {
        Layer layer;
        Layer[] layers = this.coeData.getLayers();
        if (layers == null || (layer = (Layer) ArraysKt___ArraysKt.getOrNull(layers, this.layerIndex)) == null) {
            return null;
        }
        return layer.getParams();
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public abstract /* synthetic */ AbsAnimator getAnimator();

    public final AnimatorHandler getAnimatorHandler() {
        return this.animatorHandler;
    }

    public final HashMap<String, Animator> getAnimatorMap() {
        return this.animatorMap;
    }

    public final AnimatorType getAnimatorType() {
        return this.animatorType;
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public HashMap<String, Animator> getAnimators() {
        return this.animatorMap;
    }

    public final COEData getCoeData() {
        return this.coeData;
    }

    public final ArrayList<Map<String, IParameter>> getDefaultPassParam() {
        return this.defaultPassParam;
    }

    public final int getLayerIndex() {
        return this.layerIndex;
    }

    public final int getParameterCount() {
        return this.parameterCount;
    }

    public final int getStateAnimatorStartedCount() {
        return this.stateAnimatorStartedCount;
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public PassParams[] getTriger(String str) {
        Layer layer;
        HashMap<String, PassParams[]> params;
        Layer[] layers = this.coeData.getLayers();
        if (layers == null || (layer = (Layer) ArraysKt___ArraysKt.getOrNull(layers, this.layerIndex)) == null || (params = layer.getParams()) == null) {
            return null;
        }
        return params.get(str);
    }

    public ChannelData getUniformData(final String str) {
        return (ChannelData) SequencesKt___SequencesKt.firstOrNull(SequencesKt___SequencesKt.mapNotNull(CollectionsKt___CollectionsKt.asSequence(this.uniformMapList.entrySet()), new Function1() { // from class: com.oplus.vfxsdk.common.AbsAnimator.getUniformData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final ChannelData invoke(Map.Entry<Integer, HashMap<String, ChannelData>> entry) {
                return entry.getValue().get(str);
            }
        }));
    }

    public final HashMap<Integer, HashMap<String, ChannelData>> getUniformMaps() {
        return this.uniformMapList;
    }

    public final void initAnimator(String str, AnimatorValue animatorValue, IUpdate iUpdate, IPop iPop) {
        for (AnimLine animLine : animatorValue.getAnimLines()) {
            bindAnimLine(animLine, iUpdate, iPop);
        }
        Animator animator = new Animator(animatorValue, this.coeData.getExpressions(), this.iLinkProxy);
        animator.init();
        this.animatorMap.put(str, animator);
    }

    public HashMap<String, Animator> initAnimators(IUpdate iUpdate, IPop iPop) {
        HashMap<String, AnimatorValue> animParams;
        Layer layer = (Layer) ArraysKt___ArraysKt.getOrNull(this.coeData.getLayers(), this.layerIndex);
        if (layer != null && (animParams = layer.getAnimParams()) != null) {
            for (Map.Entry<String, AnimatorValue> entry : animParams.entrySet()) {
                initAnimator(entry.getKey(), entry.getValue(), iUpdate, iPop);
            }
        }
        return this.animatorMap;
    }

    public final ArrayList<Map<String, IParameter>> initStateParams(ICallback iCallback, TimeInterpolator timeInterpolator, long j) {
        HashMap<String, PassParams[]> params;
        PassParams[] passParamsArr;
        int i;
        Iterator it;
        float fFloatValue;
        Layer layer = (Layer) ArraysKt___ArraysKt.getOrNull(this.coeData.getLayers(), this.layerIndex);
        if (layer != null && (params = layer.getParams()) != null && (passParamsArr = params.get("default")) != null) {
            Iterator it2 = ArraysKt___ArraysKt.withIndex(passParamsArr).iterator();
            while (it2.hasNext()) {
                IndexedValue indexedValue = (IndexedValue) it2.next();
                int i2 = indexedValue.index;
                PassParams passParams = (PassParams) indexedValue.value;
                initStateParams.1.1.update.1 r4 = new initStateParams.1.1.update.1();
                r4.$cb = iCallback;
                r4.$index = i2;
                VarHandle.storeStoreFence();
                HashMap map = new HashMap();
                UniformValue[] uniformPrams = passParams.getUniformPrams();
                int length = uniformPrams.length;
                int i3 = 0;
                int i4 = 0;
                while (i4 < length) {
                    UniformValue uniformValue = uniformPrams[i4];
                    int iOrdinal = uniformValue.getType().ordinal();
                    boolean z = true;
                    if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                        i = i3;
                        float[] bezier = uniformValue.getBezier();
                        String name = uniformValue.getName();
                        Object[] values = uniformValue.getValues();
                        ArrayList arrayList = new ArrayList(values.length);
                        int length2 = values.length;
                        int i5 = i;
                        while (i5 < length2) {
                            boolean z2 = z;
                            Object obj = values[i5];
                            Iterator it3 = it2;
                            if (obj instanceof Integer) {
                                fFloatValue = ((Number) obj).intValue();
                            } else {
                                if (!(obj instanceof Float)) {
                                    Path$.ExternalSyntheticBUOutline0.m(FileProvider$.ExternalSyntheticOutline0.m("Invalid value type for parameter ", uniformValue.getName()));
                                    return null;
                                }
                                fFloatValue = ((Number) obj).floatValue();
                            }
                            arrayList.add(Float.valueOf(fFloatValue));
                            i5++;
                            z = z2;
                            it2 = it3;
                        }
                        it = it2;
                        map.put(uniformValue.getName(), new ParameterFloatArray(new Param(name, CollectionsKt___CollectionsKt.toFloatArray(arrayList), new PathInterpolator(bezier[i], bezier[z ? 1 : 0], bezier[2], bezier[3]), j), r4));
                    } else {
                        if (iOrdinal == 4) {
                            i = i3;
                            map.put(uniformValue.getName(), new ParameterInt(new Param(uniformValue.getName(), (Integer) uniformValue.getValues()[i], timeInterpolator, j), r4));
                        } else if (iOrdinal != 6) {
                            it = it2;
                            i = i3;
                        } else {
                            float[] bezier2 = uniformValue.getBezier();
                            i = i3;
                            map.put(uniformValue.getName(), new ParameterFloat(new Param(uniformValue.getName(), (Float) uniformValue.getValues()[i3], new PathInterpolator(bezier2[i], bezier2[1], bezier2[2], bezier2[3]), uniformValue.getDuration()), r4));
                        }
                        it = it2;
                    }
                    i4++;
                    it2 = it;
                    i3 = i;
                }
                this.parameterCount = map.size() + this.parameterCount;
                this.defaultPassParam.add(map);
                it2 = it2;
            }
        }
        return this.defaultPassParam;
    }

    public final void initUniformMap() {
        PostProcessorData[] postProcessor;
        RendPass[] render;
        Layer layer = (Layer) ArraysKt___ArraysKt.getOrNull(this.coeData.getLayers(), this.layerIndex);
        if (layer != null && (render = layer.getRender()) != null) {
            int length = render.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                RendPass rendPass = render[i];
                int i3 = i2 + 1;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                this.uniformMapList.put(Integer.valueOf(i2), linkedHashMap);
                Iterator<T> it = rendPass.getUniforms().keySet().iterator();
                while (it.hasNext()) {
                    Uniform uniform = rendPass.getUniforms().get((String) it.next());
                    if (uniform != null) {
                        parseUniform(uniform, i2, linkedHashMap);
                    }
                }
                Effect[] effects = rendPass.getEffects();
                if (effects != null) {
                    for (Effect effect : effects) {
                        Iterator<Map.Entry<String, Uniform>> it2 = effect.getUniforms().entrySet().iterator();
                        while (it2.hasNext()) {
                            parseUniform(it2.next().getValue(), 0, linkedHashMap);
                        }
                    }
                }
                i++;
                i2 = i3;
            }
        }
        if (layer == null || (postProcessor = layer.getPostProcessor()) == null) {
            return;
        }
        for (PostProcessorData postProcessorData : postProcessor) {
            HashMap<String, ChannelData> map = this.uniformMapList.get(0);
            for (Map.Entry entry : postProcessorData.getProperties().entrySet()) {
                Uniform uniform2 = (Uniform) entry.getValue();
                if (map != null) {
                    parseUniform(uniform2, 0, map);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:38:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:40:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:45:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:47:0x0106  */
    /* JADX WARN: Code duplicated, block: B:48:0x010e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0112  */
    /* JADX WARN: Code duplicated, block: B:57:0x013a  */
    /* JADX WARN: Code duplicated, block: B:59:0x0141 A[LOOP:3: B:58:0x013f->B:59:0x0141, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x014e  */
    /* JADX WARN: Code duplicated, block: B:96:0x0126 A[SYNTHETIC] */
    @Override // com.oplus.vfxsdk.common.IAnimator
    public void onTriger(String str, boolean z, Function0 function0) {
        HashMap<String, PassParams[]> params;
        PassParams[] passParamsArr;
        Iterator it;
        int i;
        AbsParameter absParameter;
        Object obj;
        ArrayList arrayList;
        int length;
        int i2;
        float[] floatArray;
        float[] fArr;
        int length2;
        float[] fArr2;
        int i3;
        Object obj2;
        float fFloatValue;
        Object obj3;
        Object objValueOf;
        if (this.defaultPassParam.isEmpty()) {
            Log.w(Utils.TAG, "onTriger defaultPassParam is null");
        } else {
            HashMap<String, Animator> animators = getAnimators();
            if (animators != null) {
                Iterator<Map.Entry<String, Animator>> it2 = animators.entrySet().iterator();
                while (it2.hasNext()) {
                    it2.next().getValue().stop();
                }
            }
            this.animatorHandler.stop();
        }
        ArrayList arrayList2 = new ArrayList();
        Layer layer = (Layer) ArraysKt___ArraysKt.getOrNull(this.coeData.getLayers(), this.layerIndex);
        int i4 = 0;
        if (layer != null && (params = layer.getParams()) != null && (passParamsArr = params.get(str)) != null) {
            Iterator it3 = ArraysKt___ArraysKt.withIndex(passParamsArr).iterator();
            while (it3.hasNext()) {
                IndexedValue indexedValue = (IndexedValue) it3.next();
                int i5 = indexedValue.index;
                UniformValue[] uniformPrams = ((PassParams) indexedValue.value).getUniformPrams();
                int length3 = uniformPrams.length;
                int i6 = i4;
                while (i6 < length3) {
                    UniformValue uniformValue = uniformPrams[i6];
                    Map map = (Map) CollectionsKt___CollectionsKt.getOrNull(this.defaultPassParam, i5);
                    if (map == null || (absParameter = (IParameter) map.get(uniformValue.getName())) == null || equalValue(absParameter, uniformValue)) {
                        it = it3;
                        i = i4;
                    } else {
                        Object value = absParameter.getValue();
                        Object[] values = uniformValue.getValues();
                        if (value instanceof Integer) {
                            Object obj4 = values[i4];
                            if (obj4 instanceof Integer) {
                                objValueOf = Integer.valueOf(((Integer) obj4).intValue() - ((Number) value).intValue());
                            } else {
                                if (value instanceof Float) {
                                    obj3 = values[i4];
                                    if (obj3 instanceof Float) {
                                        objValueOf = Float.valueOf(((Float) obj3).floatValue() - ((Number) value).floatValue());
                                    }
                                }
                                if (value instanceof float[]) {
                                    i = i4;
                                    arrayList = new ArrayList(values.length);
                                    length = values.length;
                                    i2 = i;
                                    while (i2 < length) {
                                        Iterator it4 = it3;
                                        obj2 = values[i2];
                                        int i7 = length;
                                        if (obj2 instanceof Integer) {
                                            fFloatValue = ((Number) obj2).intValue();
                                        } else {
                                            if (obj2 instanceof Float) {
                                                Path$.ExternalSyntheticBUOutline0.m("cannot convert");
                                                return;
                                            }
                                            fFloatValue = ((Number) obj2).floatValue();
                                        }
                                        arrayList.add(Float.valueOf(fFloatValue));
                                        i2++;
                                        it3 = it4;
                                        length = i7;
                                    }
                                    it = it3;
                                    floatArray = CollectionsKt___CollectionsKt.toFloatArray(arrayList);
                                    fArr = (float[]) value;
                                    if (fArr.length != floatArray.length) {
                                        length2 = fArr.length;
                                        fArr2 = new float[length2];
                                        for (i3 = i; i3 < length2; i3++) {
                                            fArr2[i3] = floatArray[i3] - fArr[i3];
                                        }
                                        obj = fArr2;
                                    }
                                } else {
                                    it = it3;
                                    i = i4;
                                }
                                obj = null;
                            }
                            it = it3;
                            i = i4;
                            obj = objValueOf;
                        } else {
                            if (value instanceof Float) {
                                obj3 = values[i4];
                                if (obj3 instanceof Float) {
                                    objValueOf = Float.valueOf(((Float) obj3).floatValue() - ((Number) value).floatValue());
                                    it = it3;
                                    i = i4;
                                    obj = objValueOf;
                                }
                            }
                            if (value instanceof float[]) {
                                i = i4;
                                arrayList = new ArrayList(values.length);
                                length = values.length;
                                i2 = i;
                                while (i2 < length) {
                                    Iterator it5 = it3;
                                    obj2 = values[i2];
                                    int i8 = length;
                                    if (obj2 instanceof Integer) {
                                        fFloatValue = ((Number) obj2).intValue();
                                    } else {
                                        if (obj2 instanceof Float) {
                                            Path$.ExternalSyntheticBUOutline0.m("cannot convert");
                                            return;
                                        }
                                        fFloatValue = ((Number) obj2).floatValue();
                                    }
                                    arrayList.add(Float.valueOf(fFloatValue));
                                    i2++;
                                    it3 = it5;
                                    length = i8;
                                }
                                it = it3;
                                floatArray = CollectionsKt___CollectionsKt.toFloatArray(arrayList);
                                fArr = (float[]) value;
                                if (fArr.length != floatArray.length) {
                                    length2 = fArr.length;
                                    fArr2 = new float[length2];
                                    while (i3 < length2) {
                                        fArr2[i3] = floatArray[i3] - fArr[i3];
                                    }
                                    obj = fArr2;
                                }
                            } else {
                                it = it3;
                                i = i4;
                            }
                            obj = null;
                        }
                        if (obj != null) {
                            arrayList2.add(new StateAnimParam(absParameter, uniformValue, absParameter.copyValue(), obj, false));
                        }
                    }
                    i6++;
                    i4 = i;
                    it3 = it;
                }
            }
        }
        int i9 = i4;
        if (z) {
            int size = arrayList2.size();
            int i10 = i9;
            while (i10 < size) {
                Object obj5 = arrayList2.get(i10);
                i10++;
                StateAnimParam stateAnimParam = (StateAnimParam) obj5;
                setParamValue(stateAnimParam.getDefaultParameter(), stateAnimParam.getUniformValue().getValues());
            }
            if (function0 != null) {
                return;
            }
            return;
        }
        if (arrayList2.isEmpty()) {
            if (function0 != null) {
            }
            ConstraintSet$.ExternalSyntheticOutline0.m("state:", str, " animParameters empty", TAG);
            return;
        }
        int size2 = arrayList2.size();
        int i11 = i9;
        while (i11 < size2) {
            Object obj6 = arrayList2.get(i11);
            i11++;
            StateAnimParam stateAnimParam2 = (StateAnimParam) obj6;
            if (stateAnimParam2.getUniformValue().getDuration() < 10) {
                setParamValue(stateAnimParam2.getDefaultParameter(), stateAnimParam2.getUniformValue().getValues());
                stateAnimParam2.setDone(true);
            } else if (Intrinsics.areEqual(stateAnimParam2.getUniformValue().getIpol(), "bezier")) {
                stateAnimParam2.getDefaultParameter().getParam().setInterpolator(new PathInterpolator(stateAnimParam2.getUniformValue().getBezier()[i9], stateAnimParam2.getUniformValue().getBezier()[1], stateAnimParam2.getUniformValue().getBezier()[2], stateAnimParam2.getUniformValue().getBezier()[3]));
            } else if (Intrinsics.areEqual(stateAnimParam2.getUniformValue().getIpol(), "spring")) {
                stateAnimParam2.getDefaultParameter().getParam().setInterpolator(AbsAnimatorKt.createSpringAnimation(stateAnimParam2.getUniformValue().getSpring()[i9], stateAnimParam2.getUniformValue().getSpring()[1], stateAnimParam2.getUniformValue().getSpring()[2]));
            }
        }
        AnimatorHandler animatorHandler = this.animatorHandler;
        onTriger.3 r4 = new onTriger.3();
        r4.$animParameters = arrayList2;
        r4.this$0 = this;
        r4.$endCb = function0;
        VarHandle.storeStoreFence();
        animatorHandler.registerFrameListener(r4);
        this.animatorHandler.play();
        Function0 function1 = this.animatorStartCb;
        if (function1 != null) {
        }
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void pauseAnim(String str) {
        findAnimatorByKey(str, pauseAnim.1.INSTANCE);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void playAnim(String str) {
        findAnimatorByKey(str, playAnim.1.INSTANCE);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void playAnimTo(String str, float f) {
        findAnimatorByKey(str, new playAnimTo.1(f));
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void restartAnim(String str) {
        findAnimatorByKey(str, restartAnim.1.INSTANCE);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void seekAnimNext(String str) {
        findAnimatorByKey(str, seekAnimNext.1.INSTANCE);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void seekAnimTo(String str, float f) {
        findAnimatorByKey(str, new seekAnimTo.1(f));
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void seekAnimToEnd(String str) {
        findAnimatorByKey(str, seekAnimToEnd.1.INSTANCE);
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void setAnimEndListener(String str, Function0 function0) {
        findAnimatorByKey(str, new setAnimEndListener.1(function0));
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void setAnimMode(String str, Animator.AnimMode animMode) {
        findAnimatorByKey(str, new setAnimMode.1(animMode));
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void setAnimUpdateListener(String str, Function1 function1) {
        findAnimatorByKey(str, new setAnimUpdateListener.1(function1));
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void setAnimatorEndCb(Function0 function0) {
        this.animatorEndCb = function0;
    }

    public final void setAnimatorMap(HashMap<String, Animator> map) {
        this.animatorMap = map;
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void setAnimatorStartCb(Function0 function0) {
        this.animatorStartCb = function0;
    }

    public final void setAnimatorType(AnimatorType animatorType) {
        this.animatorType = animatorType;
    }

    public final void setDefaultPassParam(ArrayList<Map<String, IParameter>> arrayList) {
        this.defaultPassParam = arrayList;
    }

    public final void setLinkProxy(ILinkProxy iLinkProxy) {
        this.iLinkProxy = iLinkProxy;
    }

    public final void setParameterCount(int i) {
        this.parameterCount = i;
    }

    public final void setStateAnimatorStartedCount(int i) {
        this.stateAnimatorStartedCount = i;
    }

    @Override // com.oplus.vfxsdk.common.IAnimator
    public void stopAnim(String str) {
        findAnimatorByKey(str, stopAnim.1.INSTANCE);
    }

    public ChannelData getUniformData(int i, String str) {
        HashMap<String, ChannelData> map = this.uniformMapList.get(Integer.valueOf(i));
        if (map != null) {
            return map.get(str);
        }
        return null;
    }
}
