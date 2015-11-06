package org.nem.core.crypto.ed25519.arithmetic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Pool of Ed25519 field elements.
 */
public class Ed25519FieldElementPool {
	private static long POOL_SIZE = 1_000_000;
	private static final Ed25519FieldElement[] POOL = new Ed25519FieldElement[(int)POOL_SIZE];
	private static final AtomicLong POSITION = new AtomicLong(0);

	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			POOL[i] = new Ed25519FieldElement(new int[10]);
		}

	}

	public static Ed25519FieldElement getElement() {
		return POOL[(int)(POSITION.incrementAndGet() % POOL_SIZE)];
	}
}
