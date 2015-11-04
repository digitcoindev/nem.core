package org.nem.core.crypto.ed25519.arithmetic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Poll of Ed25519 field elements.
 */
public class Ed25519FieldElementPool {
	private static int POOL_SIZE = 10000;
	private static final Ed25519FieldElement[] POOL = new Ed25519FieldElement[POOL_SIZE];
	private static final AtomicInteger POSITION = new AtomicInteger(0);

	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			POOL[i] = new Ed25519FieldElement(new int[10]);
		}

	}

	public static Ed25519FieldElement next() {
		return POOL[POSITION.incrementAndGet() % POOL_SIZE];
	}
}
