package org.nem.core.crypto.ed25519.arithmetic;

import org.nem.core.utils.HexEncoder;

import java.math.BigInteger;

/**
 * Represents the underlying group for Ed25519.
 */
public class Ed25519Group {

	/**
	 * 2^252 + 27742317777372353535851937790883648493
	 */
	public static final BigInteger GROUP_ORDER = BigInteger.ONE.shiftLeft(252).add(new BigInteger("27742317777372353535851937790883648493"));

	/**
	 * <pre>{@code
	 * (x, 4/5); x > 0
	 * }</pre>
	 */
	public static final Ed25519GroupElement BASE_POINT = getBasePoint();

	// different representations of zero
	public static final Ed25519GroupElement ZERO_P3 = new Ed25519GroupElement(
			CoordinateSystem.P3,
			Ed25519Field.ZERO,
			Ed25519Field.ONE,
			Ed25519Field.ONE,
			Ed25519Field.ZERO);
	public static final Ed25519GroupElement ZERO_P2 = new Ed25519GroupElement(
			CoordinateSystem.P2,
			Ed25519Field.ZERO,
			Ed25519Field.ONE,
			Ed25519Field.ONE,
			null);
	public static final Ed25519GroupElement ZERO_PRECOMPUTED = new Ed25519GroupElement(
			CoordinateSystem.PRECOMPUTED,
			Ed25519Field.ONE,
			Ed25519Field.ONE,
			Ed25519Field.ZERO,
			null);

	private static Ed25519GroupElement getBasePoint() {
		final byte[] rawEncodedGroupElement = HexEncoder.getBytes("5866666666666666666666666666666666666666666666666666666666666666");
		final Ed25519GroupElement tmp = new Ed25519EncodedGroupElement(rawEncodedGroupElement).decode();
		final Ed25519GroupElement basePoint = new Ed25519GroupElement(
				tmp.getCoordinateSystem(),
				tmp.getX(),
				tmp.getY(),
				tmp.getZ(),
				tmp.getT());
		basePoint.precomputeForScalarMultiplication();
		basePoint.precomputeForDoubleScalarMultiplication();
		return basePoint;
	}
}
