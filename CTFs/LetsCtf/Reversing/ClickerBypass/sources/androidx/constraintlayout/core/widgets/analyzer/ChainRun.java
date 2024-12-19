package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            j = j + ((long) widgetRun.start.margin) + widgetRun.getWrapDimension() + ((long) widgetRun.end.margin);
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cd, code lost:
        if (r3.dimension.resolved != false) goto L_0x00cf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r23) {
        /*
            r22 = this;
            r0 = r22
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x040f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x040f
        L_0x0010:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            boolean r2 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x0021
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r2 = r2 - r4
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0032:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004a
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004b
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r5 = r6
        L_0x004b:
            int r8 = r4 + -1
            r9 = r8
        L_0x004e:
            if (r9 < 0) goto L_0x0064
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0063
            int r9 = r9 + -1
            goto L_0x004e
        L_0x0063:
            r6 = r9
        L_0x0064:
            r9 = 0
        L_0x0065:
            r11 = 2
            r12 = 1
            if (r9 >= r11) goto L_0x0104
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x0070:
            if (r13 >= r4) goto L_0x00f6
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r3 = r0.widgets
            java.lang.Object r3 = r3.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            int r11 = r11.getVisibility()
            if (r11 != r7) goto L_0x0084
            goto L_0x00ef
        L_0x0084:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x008f
            if (r13 < r5) goto L_0x008f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r3.start
            int r11 = r11.margin
            int r14 = r14 + r11
        L_0x008f:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r3.dimension
            int r11 = r11.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = r3.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 == r10) goto L_0x009b
            r7 = r12
            goto L_0x009c
        L_0x009b:
            r7 = 0
        L_0x009c:
            if (r7 == 0) goto L_0x00bc
            int r10 = r0.orientation
            if (r10 != 0) goto L_0x00ad
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r10 = r10.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            boolean r10 = r10.resolved
            if (r10 != 0) goto L_0x00ad
            return
        L_0x00ad:
            int r10 = r0.orientation
            if (r10 != r12) goto L_0x00d0
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r10 = r10.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            boolean r10 = r10.resolved
            if (r10 != 0) goto L_0x00d0
            return
        L_0x00bc:
            int r10 = r3.matchConstraintsType
            if (r10 != r12) goto L_0x00c9
            if (r9 != 0) goto L_0x00c9
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r3.dimension
            int r11 = r7.wrapValue
            int r15 = r15 + 1
            goto L_0x00cf
        L_0x00c9:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r3.dimension
            boolean r10 = r10.resolved
            if (r10 == 0) goto L_0x00d0
        L_0x00cf:
            r7 = r12
        L_0x00d0:
            if (r7 != 0) goto L_0x00e4
            int r15 = r15 + 1
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r3.widget
            float[] r7 = r7.mWeight
            int r10 = r0.orientation
            r7 = r7[r10]
            r10 = 0
            int r11 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x00e5
            float r17 = r17 + r7
            goto L_0x00e5
        L_0x00e4:
            int r14 = r14 + r11
        L_0x00e5:
            if (r13 >= r8) goto L_0x00ef
            if (r13 >= r6) goto L_0x00ef
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x00ef:
            int r13 = r13 + 1
            r7 = 8
            r11 = 2
            goto L_0x0070
        L_0x00f6:
            if (r14 < r2) goto L_0x0101
            if (r15 != 0) goto L_0x00fb
            goto L_0x0101
        L_0x00fb:
            int r9 = r9 + 1
            r7 = 8
            goto L_0x0065
        L_0x0101:
            r3 = r16
            goto L_0x0109
        L_0x0104:
            r3 = 0
            r14 = 0
            r15 = 0
            r17 = 0
        L_0x0109:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.start
            int r7 = r7.value
            if (r1 == 0) goto L_0x0113
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.end
            int r7 = r7.value
        L_0x0113:
            r9 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r2) goto L_0x012a
            r10 = 1073741824(0x40000000, float:2.0)
            if (r1 == 0) goto L_0x0123
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 + r10
            goto L_0x012a
        L_0x0123:
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 - r10
        L_0x012a:
            if (r15 <= 0) goto L_0x0212
            int r10 = r2 - r14
            float r10 = (float) r10
            float r11 = (float) r15
            float r11 = r10 / r11
            float r11 = r11 + r9
            int r11 = (int) r11
            r13 = 0
            r16 = 0
        L_0x0137:
            if (r13 >= r4) goto L_0x01c7
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r12 = r0.widgets
            java.lang.Object r12 = r12.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r12 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r12
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r12.widget
            int r9 = r9.getVisibility()
            r18 = r11
            r11 = 8
            if (r9 != r11) goto L_0x014e
            goto L_0x01b2
        L_0x014e:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r12.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r11) goto L_0x01b2
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r12.dimension
            boolean r9 = r9.resolved
            if (r9 != 0) goto L_0x01b2
            r9 = 0
            int r11 = (r17 > r9 ? 1 : (r17 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x016f
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r12.widget
            float[] r11 = r11.mWeight
            int r9 = r0.orientation
            r9 = r11[r9]
            float r9 = r9 * r10
            float r9 = r9 / r17
            r11 = 1056964608(0x3f000000, float:0.5)
            float r9 = r9 + r11
            int r9 = (int) r9
            goto L_0x0171
        L_0x016f:
            r9 = r18
        L_0x0171:
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r12.widget
            int r11 = r11.mMatchConstraintMaxWidth
            r19 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r10 = r10.mMatchConstraintMinWidth
            goto L_0x018a
        L_0x0180:
            r19 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r11 = r10.mMatchConstraintMaxHeight
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r10 = r10.mMatchConstraintMinHeight
        L_0x018a:
            r20 = r14
            int r14 = r12.matchConstraintsType
            r21 = r7
            r7 = 1
            if (r14 != r7) goto L_0x019c
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r12.dimension
            int r7 = r7.wrapValue
            int r7 = java.lang.Math.min(r9, r7)
            goto L_0x019d
        L_0x019c:
            r7 = r9
        L_0x019d:
            int r7 = java.lang.Math.max(r10, r7)
            if (r11 <= 0) goto L_0x01a7
            int r7 = java.lang.Math.min(r11, r7)
        L_0x01a7:
            if (r7 == r9) goto L_0x01ac
            int r16 = r16 + 1
            r9 = r7
        L_0x01ac:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r12.dimension
            r7.resolve(r9)
            goto L_0x01b8
        L_0x01b2:
            r21 = r7
            r19 = r10
            r20 = r14
        L_0x01b8:
            int r13 = r13 + 1
            r11 = r18
            r10 = r19
            r14 = r20
            r7 = r21
            r9 = 1056964608(0x3f000000, float:0.5)
            r12 = 1
            goto L_0x0137
        L_0x01c7:
            r21 = r7
            r20 = r14
            if (r16 <= 0) goto L_0x0203
            int r15 = r15 - r16
            r7 = 0
            r9 = 0
        L_0x01d1:
            if (r7 >= r4) goto L_0x0201
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r7)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r10.widget
            int r11 = r11.getVisibility()
            r12 = 8
            if (r11 != r12) goto L_0x01e6
            goto L_0x01fe
        L_0x01e6:
            if (r7 <= 0) goto L_0x01ef
            if (r7 < r5) goto L_0x01ef
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r10.start
            int r11 = r11.margin
            int r9 = r9 + r11
        L_0x01ef:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r10.dimension
            int r11 = r11.value
            int r9 = r9 + r11
            if (r7 >= r8) goto L_0x01fe
            if (r7 >= r6) goto L_0x01fe
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r10.end
            int r10 = r10.margin
            int r10 = -r10
            int r9 = r9 + r10
        L_0x01fe:
            int r7 = r7 + 1
            goto L_0x01d1
        L_0x0201:
            r14 = r9
            goto L_0x0205
        L_0x0203:
            r14 = r20
        L_0x0205:
            int r7 = r0.chainStyle
            r9 = 2
            if (r7 != r9) goto L_0x0210
            if (r16 != 0) goto L_0x0210
            r7 = 0
            r0.chainStyle = r7
            goto L_0x0218
        L_0x0210:
            r7 = 0
            goto L_0x0218
        L_0x0212:
            r21 = r7
            r20 = r14
            r7 = 0
            r9 = 2
        L_0x0218:
            if (r14 <= r2) goto L_0x021c
            r0.chainStyle = r9
        L_0x021c:
            if (r3 <= 0) goto L_0x0224
            if (r15 != 0) goto L_0x0224
            if (r5 != r6) goto L_0x0224
            r0.chainStyle = r9
        L_0x0224:
            int r9 = r0.chainStyle
            r10 = 1
            if (r9 != r10) goto L_0x02c7
            if (r3 <= r10) goto L_0x022f
            int r2 = r2 - r14
            int r3 = r3 - r10
            int r2 = r2 / r3
            goto L_0x0236
        L_0x022f:
            if (r3 != r10) goto L_0x0235
            int r2 = r2 - r14
            r3 = 2
            int r2 = r2 / r3
            goto L_0x0236
        L_0x0235:
            r2 = r7
        L_0x0236:
            if (r15 <= 0) goto L_0x0239
            r2 = r7
        L_0x0239:
            r3 = r7
            r7 = r21
        L_0x023c:
            if (r3 >= r4) goto L_0x040f
            if (r1 == 0) goto L_0x0245
            int r9 = r3 + 1
            int r9 = r4 - r9
            goto L_0x0246
        L_0x0245:
            r9 = r3
        L_0x0246:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r9 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r10 = r10.getVisibility()
            r11 = 8
            if (r10 != r11) goto L_0x0263
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            r9.resolve(r7)
            goto L_0x02c3
        L_0x0263:
            if (r3 <= 0) goto L_0x026a
            if (r1 == 0) goto L_0x0269
            int r7 = r7 - r2
            goto L_0x026a
        L_0x0269:
            int r7 = r7 + r2
        L_0x026a:
            if (r3 <= 0) goto L_0x027b
            if (r3 < r5) goto L_0x027b
            if (r1 == 0) goto L_0x0276
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 - r10
            goto L_0x027b
        L_0x0276:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 + r10
        L_0x027b:
            if (r1 == 0) goto L_0x0283
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
            goto L_0x0288
        L_0x0283:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
        L_0x0288:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x029b
            int r11 = r9.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x029b
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.wrapValue
        L_0x029b:
            if (r1 == 0) goto L_0x029f
            int r7 = r7 - r10
            goto L_0x02a0
        L_0x029f:
            int r7 = r7 + r10
        L_0x02a0:
            if (r1 == 0) goto L_0x02a8
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            goto L_0x02ad
        L_0x02a8:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
        L_0x02ad:
            r10 = 1
            r9.resolved = r10
            if (r3 >= r8) goto L_0x02c3
            if (r3 >= r6) goto L_0x02c3
            if (r1 == 0) goto L_0x02bd
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 - r9
            goto L_0x02c3
        L_0x02bd:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 + r9
        L_0x02c3:
            int r3 = r3 + 1
            goto L_0x023c
        L_0x02c7:
            if (r9 != 0) goto L_0x035d
            int r2 = r2 - r14
            r9 = 1
            int r3 = r3 + r9
            int r2 = r2 / r3
            if (r15 <= 0) goto L_0x02d0
            r2 = r7
        L_0x02d0:
            r3 = r7
            r7 = r21
        L_0x02d3:
            if (r3 >= r4) goto L_0x040f
            if (r1 == 0) goto L_0x02dc
            int r9 = r3 + 1
            int r9 = r4 - r9
            goto L_0x02dd
        L_0x02dc:
            r9 = r3
        L_0x02dd:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r9 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r10 = r10.getVisibility()
            r11 = 8
            if (r10 != r11) goto L_0x02fa
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            r9.resolve(r7)
            goto L_0x0359
        L_0x02fa:
            if (r1 == 0) goto L_0x02fe
            int r7 = r7 - r2
            goto L_0x02ff
        L_0x02fe:
            int r7 = r7 + r2
        L_0x02ff:
            if (r3 <= 0) goto L_0x0310
            if (r3 < r5) goto L_0x0310
            if (r1 == 0) goto L_0x030b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 - r10
            goto L_0x0310
        L_0x030b:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 + r10
        L_0x0310:
            if (r1 == 0) goto L_0x0318
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
            goto L_0x031d
        L_0x0318:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
        L_0x031d:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0334
            int r11 = r9.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x0334
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r9.dimension
            int r11 = r11.wrapValue
            int r10 = java.lang.Math.min(r10, r11)
        L_0x0334:
            if (r1 == 0) goto L_0x0338
            int r7 = r7 - r10
            goto L_0x0339
        L_0x0338:
            int r7 = r7 + r10
        L_0x0339:
            if (r1 == 0) goto L_0x0341
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            goto L_0x0346
        L_0x0341:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
        L_0x0346:
            if (r3 >= r8) goto L_0x0359
            if (r3 >= r6) goto L_0x0359
            if (r1 == 0) goto L_0x0353
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 - r9
            goto L_0x0359
        L_0x0353:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 + r9
        L_0x0359:
            int r3 = r3 + 1
            goto L_0x02d3
        L_0x035d:
            r3 = 2
            if (r9 != r3) goto L_0x040f
            int r3 = r0.orientation
            if (r3 != 0) goto L_0x036b
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getHorizontalBiasPercent()
            goto L_0x0371
        L_0x036b:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getVerticalBiasPercent()
        L_0x0371:
            if (r1 == 0) goto L_0x0377
            r9 = 1065353216(0x3f800000, float:1.0)
            float r3 = r9 - r3
        L_0x0377:
            int r2 = r2 - r14
            float r2 = (float) r2
            float r2 = r2 * r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            int r2 = (int) r2
            if (r2 < 0) goto L_0x0382
            if (r15 <= 0) goto L_0x0383
        L_0x0382:
            r2 = r7
        L_0x0383:
            if (r1 == 0) goto L_0x0388
            int r2 = r21 - r2
            goto L_0x038a
        L_0x0388:
            int r2 = r21 + r2
        L_0x038a:
            r3 = r7
        L_0x038b:
            if (r3 >= r4) goto L_0x040f
            if (r1 == 0) goto L_0x0394
            int r7 = r3 + 1
            int r7 = r4 - r7
            goto L_0x0395
        L_0x0394:
            r7 = r3
        L_0x0395:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r7 = r9.get(r7)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x03b3
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            r7.resolve(r2)
            r12 = 1
            goto L_0x040b
        L_0x03b3:
            if (r3 <= 0) goto L_0x03c4
            if (r3 < r5) goto L_0x03c4
            if (r1 == 0) goto L_0x03bf
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r2 = r2 - r9
            goto L_0x03c4
        L_0x03bf:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r2 = r2 + r9
        L_0x03c4:
            if (r1 == 0) goto L_0x03cc
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r2)
            goto L_0x03d1
        L_0x03cc:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
        L_0x03d1:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r7.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x03e5
            int r11 = r7.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x03e6
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.wrapValue
            goto L_0x03e6
        L_0x03e5:
            r12 = 1
        L_0x03e6:
            if (r1 == 0) goto L_0x03ea
            int r2 = r2 - r9
            goto L_0x03eb
        L_0x03ea:
            int r2 = r2 + r9
        L_0x03eb:
            if (r1 == 0) goto L_0x03f3
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
            goto L_0x03f8
        L_0x03f3:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r2)
        L_0x03f8:
            if (r3 >= r8) goto L_0x040b
            if (r3 >= r6) goto L_0x040b
            if (r1 == 0) goto L_0x0405
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r2 = r2 - r7
            goto L_0x040b
        L_0x0405:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r2 = r2 + r7
        L_0x040b:
            int r3 = r3 + 1
            goto L_0x038b
        L_0x040f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = this.widgets.get(0).widget;
            ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
