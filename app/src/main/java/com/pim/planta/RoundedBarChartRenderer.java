package com.pim.planta;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RoundedBarChartRenderer extends BarChartRenderer {

    private final float radius;

    public RoundedBarChartRenderer(BarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler, float radius) {
        super(chart, animator, viewPortHandler);
        this.radius = radius;
    }

    @Override
    protected void drawDataSet(Canvas c, IBarDataSet dataSet, int index) {
        // Pintura
        Paint renderPaint = mRenderPaint;
        float phaseY = mAnimator.getPhaseY();

        for (int i = 0; i < dataSet.getEntryCount(); i++) {
            BarEntry entry = dataSet.getEntryForIndex(i);

            // Calcula las coordenadas de la barra
            float left = entry.getX() - 0.25f; // Asegúrate de ajustar el tamaño de las barras
            float top = entry.getY() > 0 ? 0 : entry.getY(); // Para valores negativos
            float right = entry.getX() + 0.25f; // Asegúrate de ajustar el tamaño de las barras
            float bottom = entry.getY() * phaseY;

            RectF barRect = new RectF(left, top, right, bottom);

            // Dibuja la barra con esquinas redondeadas
            c.drawRoundRect(barRect, radius, radius, renderPaint);  // Redondeo
        }
    }
}
