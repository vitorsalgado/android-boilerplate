package com.example.swiper;

import java.util.Locale;

final class LinearRegression {
	private final double mAlpha;
	private final double mBeta;
	private final double mR2;

	LinearRegression(float[] x, float[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("array lengths are not equal");
		}

		final int mN = x.length;

		// first pass
		double sumx = 0.0;
		double sumy = 0.0;

		for (float aX : x) {
			sumx += aX;
		}

		for (int i = 0; i < mN; i++) {
			sumy += y[i];
		}

		final double xbar = sumx / mN;
		final double ybar = sumy / mN;

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
		double ssr = 0.0;      // regression sum of squares

		for (float aX : x) {
			double fit = mBeta * aX + mAlpha;
			ssr += (fit - ybar) * (fit - ybar);
		}

		mR2 = ssr / yybar;
	}

	double intercept() {
		return mAlpha;
	}

	double slope() {
		return mBeta;
	}

	private double r2() {
		return mR2;
	}

	@Override
	public String toString() {
		String s = "";
		Locale locale = Locale.getDefault();
		s += String.format(locale, "%.2f mN + %.2f", slope(), intercept());

		return s + "  (R^2 = " + String.format(locale, "%.3f", r2()) + ")";
	}

}
