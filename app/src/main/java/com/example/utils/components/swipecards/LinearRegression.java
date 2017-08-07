package com.example.utils.components.swipecards;

import android.annotation.SuppressLint;

class LinearRegression {
    private final int mN;
    private final double mAlpha;
    private final double mBeta;
    private final double mR2;
    private final double mSvar;
    private final double mSvar0;
    private final double mSvar1;

    /**
     * Performs a linear regression on the data points <tt>(y[i], x[i])</tt>.
     *
     * @param x the values of the predictor variable
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(float[] x, float[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        mN = x.length;

        // first pass
        double sumx = 0.0;
        double sumy = 0.0;
        double sumx2 = 0.0;

        for (int i = 0; i < mN; i++) {
            sumx += x[i];
        }

        for (int i = 0; i < mN; i++) {
            sumx2 += x[i] * x[i];
        }

        for (int i = 0; i < mN; i++) {
            sumy += y[i];
        }

        double xbar = sumx / mN;
        double ybar = sumy / mN;

        // second pass: compute summary statistics
        double xxbar = 0.0;
        double yybar = 0.0;
        double xybar = 0.0;

        for (int i = 0; i < mN; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        mBeta = xybar / xxbar;
        mAlpha = ybar - mBeta * xbar;

        // more statistical analysis
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < mN; i++) {
            double fit = mBeta * x[i] + mAlpha;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }

        int degreesOfFreedom = mN - 2;
        mR2 = ssr / yybar;
        mSvar = rss / degreesOfFreedom;
        mSvar1 = mSvar / xxbar;
        mSvar0 = mSvar / mN + xbar * xbar * mSvar1;
    }

    /**
     * Returns the <em>y</em>-intercept &mAlpha; of the best of the best-fit line <em>y</em> = &mAlpha; + &mBeta; <em>x</em>.
     *
     * @return the <em>y</em>-intercept &mAlpha; of the best-fit line <em>y = &mAlpha; + &mBeta; x</em>
     */
    public double intercept() {
        return mAlpha;
    }

    /**
     * Returns the slope &mBeta; of the best of the best-fit line <em>y</em> = &mAlpha; + &mBeta; <em>x</em>.
     *
     * @return the slope &mBeta; of the best-fit line <em>y</em> = &mAlpha; + &mBeta; <em>x</em>
     */
    public double slope() {
        return mBeta;
    }

    /**
     * Returns the coefficient of determination <em>R</em><sup>2</sup>.
     *
     * @return the coefficient of determination <em>R</em><sup>2</sup>, which is a real number between 0 and 1
     */
    public double r2() {
        return mR2;
    }

    /**
     * Returns the standard error of the estimate for the intercept.
     *
     * @return the standard error of the estimate for the intercept
     */
    public double interceptStdErr() {
        return Math.sqrt(mSvar0);
    }

    /**
     * Returns the standard error of the estimate for the slope.
     *
     * @return the standard error of the estimate for the slope
     */
    public double slopeStdErr() {
        return Math.sqrt(mSvar1);
    }

    /**
     * Returns the expected response <tt>y</tt> given the value of the predictor
     * variable <tt>x</tt>.
     *
     * @param x the value of the predictor variable
     * @return the expected response <tt>y</tt> given the value of the predictor
     * variable <tt>x</tt>
     */
    public double predict(double x) {
        return mBeta * x + mAlpha;
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     * including the best-fit line and the coefficient of determination <em>R</em><sup>2</sup>
     */
    @SuppressLint("DefaultLocale")
    public String toString() {
        String s = "";
        s += String.format("%.2f mN + %.2f", slope(), intercept());
        return s + "  (R^2 = " + String.format("%.3f", r2()) + ")";
    }

}