package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CustomAttribute {
    private static final String TAG = "TransitionLayout";
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod = false;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    private static int clamp(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static int hsvToRgb(float f, float f2, float f3) {
        float f4 = f * 6.0f;
        int i = (int) f4;
        float f5 = f4 - ((float) i);
        float f6 = f3 * 255.0f;
        int i2 = (int) (((1.0f - f2) * f6) + 0.5f);
        int i3 = (int) (((1.0f - (f5 * f2)) * f6) + 0.5f);
        int i4 = (int) (((1.0f - ((1.0f - f5) * f2)) * f6) + 0.5f);
        int i5 = (int) (f6 + 0.5f);
        if (i == 0) {
            return ((i5 << 16) + (i4 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 1) {
            return ((i3 << 16) + (i5 << 8) + i2) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 2) {
            return ((i2 << 16) + (i5 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 3) {
            return ((i2 << 16) + (i3 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i == 4) {
            return ((i4 << 16) + (i2 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
        }
        if (i != 5) {
            return 0;
        }
        return ((i5 << 16) + (i2 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
    }

    public AttributeType getType() {
        return this.mType;
    }

    public boolean isContinuous() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    public void setFloatValue(float f) {
        this.mFloatValue = f;
    }

    public void setColorValue(int i) {
        this.mColorValue = i;
    }

    public void setIntValue(int i) {
        this.mIntegerValue = i;
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public int numberOfInterpolatedValues() {
        int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
        return (i == 4 || i == 5) ? 4 : 1;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                return this.mBooleanValue ? 1.0f : 0.0f;
            case STRING_TYPE:
                throw new RuntimeException("Cannot interpolate String");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case INT_TYPE:
                return (float) this.mIntegerValue;
            case FLOAT_TYPE:
                return this.mFloatValue;
            case DIMENSION_TYPE:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case BOOLEAN_TYPE:
                fArr[0] = this.mBooleanValue ? 1.0f : 0.0f;
                return;
            case STRING_TYPE:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                int i = this.mColorValue;
                float pow = (float) Math.pow((double) (((float) ((i >> 16) & 255)) / 255.0f), 2.2d);
                float pow2 = (float) Math.pow((double) (((float) ((i >> 8) & 255)) / 255.0f), 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((double) (((float) (i & 255)) / 255.0f), 2.2d);
                fArr[3] = ((float) ((i >> 24) & 255)) / 255.0f;
                return;
            case INT_TYPE:
                fArr[0] = (float) this.mIntegerValue;
                return;
            case FLOAT_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            case DIMENSION_TYPE:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        boolean z = true;
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = (int) fArr[0];
                return;
            case 2:
                if (((double) fArr[0]) <= 0.5d) {
                    z = false;
                }
                this.mBooleanValue = z;
                return;
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
            case 5:
                int hsvToRgb = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mColorValue = hsvToRgb;
                this.mColorValue = (clamp((int) (fArr[3] * 255.0f)) << 24) | (hsvToRgb & ViewCompat.MEASURED_SIZE_MASK);
                return;
            case 7:
                this.mFloatValue = fArr[0];
                return;
            case 8:
                this.mFloatValue = fArr[0];
                return;
            default:
                return;
        }
    }

    public boolean diff(CustomAttribute customAttribute) {
        if (customAttribute == null || this.mType != customAttribute.mType) {
            return false;
        }
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 6:
                if (this.mIntegerValue == customAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 2:
                if (this.mBooleanValue == customAttribute.mBooleanValue) {
                    return true;
                }
                return false;
            case 3:
                if (this.mIntegerValue == customAttribute.mIntegerValue) {
                    return true;
                }
                return false;
            case 4:
            case 5:
                if (this.mColorValue == customAttribute.mColorValue) {
                    return true;
                }
                return false;
            case 7:
                if (this.mFloatValue == customAttribute.mFloatValue) {
                    return true;
                }
                return false;
            case 8:
                if (this.mFloatValue == customAttribute.mFloatValue) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public CustomAttribute(String str, AttributeType attributeType) {
        this.mName = str;
        this.mType = attributeType;
    }

    public CustomAttribute(String str, AttributeType attributeType, Object obj, boolean z) {
        this.mName = str;
        this.mType = attributeType;
        this.mMethod = z;
        setValue(obj);
    }

    public CustomAttribute(CustomAttribute customAttribute, Object obj) {
        this.mName = customAttribute.mName;
        this.mType = customAttribute.mType;
        setValue(obj);
    }

    public void setValue(Object obj) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
            case 1:
            case 6:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case 2:
                this.mBooleanValue = ((Boolean) obj).booleanValue();
                return;
            case 3:
                this.mStringValue = (String) obj;
                return;
            case 4:
            case 5:
                this.mColorValue = ((Integer) obj).intValue();
                return;
            case 7:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case 8:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            default:
                return;
        }
    }

    public static HashMap<String, CustomAttribute> extractAttributes(HashMap<String, CustomAttribute> hashMap, Object obj) {
        HashMap<String, CustomAttribute> hashMap2 = new HashMap<>();
        Class<?> cls = obj.getClass();
        for (String next : hashMap.keySet()) {
            try {
                hashMap2.put(next, new CustomAttribute(hashMap.get(next), cls.getMethod("getMap" + next, new Class[0]).invoke(obj, new Object[0])));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return hashMap2;
    }

    public static void setAttributes(Object obj, HashMap<String, CustomAttribute> hashMap) {
        Class<?> cls = obj.getClass();
        for (String next : hashMap.keySet()) {
            CustomAttribute customAttribute = hashMap.get(next);
            String str = !customAttribute.mMethod ? "set" + next : next;
            try {
                switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[customAttribute.mType.ordinal()]) {
                    case 1:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mIntegerValue)});
                        break;
                    case 2:
                        cls.getMethod(str, new Class[]{Boolean.TYPE}).invoke(obj, new Object[]{Boolean.valueOf(customAttribute.mBooleanValue)});
                        break;
                    case 3:
                        cls.getMethod(str, new Class[]{CharSequence.class}).invoke(obj, new Object[]{customAttribute.mStringValue});
                        break;
                    case 4:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mColorValue)});
                        break;
                    case 6:
                        cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(customAttribute.mIntegerValue)});
                        break;
                    case 7:
                        cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(customAttribute.mFloatValue)});
                        break;
                    case 8:
                        cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(customAttribute.mFloatValue)});
                        break;
                }
            } catch (NoSuchMethodException e) {
                Utils.loge(TAG, e.getMessage());
                Utils.loge(TAG, " Custom Attribute \"" + next + "\" not found on " + cls.getName());
                Utils.loge(TAG, cls.getName() + " must have a method " + str);
            } catch (IllegalAccessException e2) {
                Utils.loge(TAG, " Custom Attribute \"" + next + "\" not found on " + cls.getName());
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                Utils.loge(TAG, " Custom Attribute \"" + next + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public void applyCustom(Object obj) {
        Class<?> cls = obj.getClass();
        String str = this.mName;
        String str2 = !this.mMethod ? "set" + str : str;
        try {
            switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()]) {
                case 1:
                case 6:
                    cls.getMethod(str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(this.mIntegerValue)});
                    return;
                case 2:
                    cls.getMethod(str2, new Class[]{Boolean.TYPE}).invoke(obj, new Object[]{Boolean.valueOf(this.mBooleanValue)});
                    return;
                case 3:
                    cls.getMethod(str2, new Class[]{CharSequence.class}).invoke(obj, new Object[]{this.mStringValue});
                    return;
                case 4:
                    cls.getMethod(str2, new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(this.mColorValue)});
                    return;
                case 7:
                    cls.getMethod(str2, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                case 8:
                    cls.getMethod(str2, new Class[]{Float.TYPE}).invoke(obj, new Object[]{Float.valueOf(this.mFloatValue)});
                    return;
                default:
                    return;
            }
        } catch (NoSuchMethodException e) {
            Utils.loge(TAG, e.getMessage());
            Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
            Utils.loge(TAG, cls.getName() + " must have a method " + str2);
        } catch (IllegalAccessException e2) {
            Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            Utils.loge(TAG, " Custom Attribute \"" + str + "\" not found on " + cls.getName());
            e3.printStackTrace();
        }
    }

    public void setInterpolatedValue(Object obj, float[] fArr) {
        Object obj2 = obj;
        Class<?> cls = obj.getClass();
        String str = "set" + this.mName;
        try {
            int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$motion$CustomAttribute$AttributeType[this.mType.ordinal()];
            boolean z = true;
            if (i == 2) {
                Method method = cls.getMethod(str, new Class[]{Boolean.TYPE});
                Object[] objArr = new Object[1];
                if (fArr[0] <= 0.5f) {
                    z = false;
                }
                objArr[0] = Boolean.valueOf(z);
                method.invoke(obj2, objArr);
            } else if (i == 3) {
                throw new RuntimeException("unable to interpolate strings " + this.mName);
            } else if (i == 4) {
                Method method2 = cls.getMethod(str, new Class[]{Integer.TYPE});
                int clamp = clamp((int) (((float) Math.pow((double) fArr[0], 0.45454545454545453d)) * 255.0f));
                method2.invoke(obj2, new Object[]{Integer.valueOf((clamp((int) (((float) Math.pow((double) fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp << 16) | clamp((int) (((float) Math.pow((double) fArr[2], 0.45454545454545453d)) * 255.0f)))});
            } else if (i == 6) {
                cls.getMethod(str, new Class[]{Integer.TYPE}).invoke(obj2, new Object[]{Integer.valueOf((int) fArr[0])});
            } else if (i == 7) {
                cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj2, new Object[]{Float.valueOf(fArr[0])});
            } else if (i == 8) {
                cls.getMethod(str, new Class[]{Float.TYPE}).invoke(obj2, new Object[]{Float.valueOf(fArr[0])});
            }
        } catch (NoSuchMethodException e) {
            Utils.loge(TAG, "no method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            Utils.loge(TAG, "cannot access method " + str + " on View \"" + obj.getClass().getName() + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }
}
