package org.nem.core.crypto.ed25519.arithmetic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Pool of Ed25519 group elements.
 */
public class Ed25519GroupElementPool {
	private static long POOL_SIZE = 1_000_000;
	private static final Ed25519GroupElement[] POOL = new Ed25519GroupElement[(int)POOL_SIZE];
	private static final AtomicLong POSITION = new AtomicLong(0);

	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			POOL[i] = new Ed25519GroupElement();
		}
	}

	public static Ed25519GroupElement getElement(
			final CoordinateSystem coordinateSystem,
			final Ed25519FieldElement X,
			final Ed25519FieldElement Y,
			final Ed25519FieldElement Z,
			final Ed25519FieldElement T) {
		final long next = POSITION.incrementAndGet();
		final Ed25519GroupElement groupElement = POOL[(int)(next % POOL_SIZE)];
		groupElement.resetPrecomputations();
		groupElement.setCoordinateSystem(coordinateSystem);
		System.arraycopy(X.getRaw(), 0, groupElement.getX().getRaw(), 0, 10);
		System.arraycopy(Y.getRaw(), 0, groupElement.getY().getRaw(), 0, 10);
		System.arraycopy(Z.getRaw(), 0, groupElement.getZ().getRaw(), 0, 10);
		if (null != T) {
			System.arraycopy(T.getRaw(), 0, groupElement.getT().getRaw(), 0, 10);
		}

		return groupElement;
	}
}
