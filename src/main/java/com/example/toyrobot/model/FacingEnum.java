package com.example.toyrobot.model;

/**
 * Facing enumeration: NORTH, EAST, SOUTH, WEST
 * 
 * @author <a href="mailto:mokhlisse_badre@yahoo.fr">Badre-Edine Mokhlisse</a>
 *
 */
public enum FacingEnum {

	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	private int index;

	private FacingEnum(int index) {
		this.index = index;
	}

	/**
	 * get FacingEnum by code
	 * 
	 * @param index
	 * @return
	 */
	private FacingEnum getByCode(int index) {
		for (FacingEnum facing : FacingEnum.values()) {
			if (index == facing.index) {
				return facing;
			}
		}

		throw new IllegalArgumentException("facingEnum index(" + index + ") out of range{0,1,2,3}");
	}

	/**
	 * flip a facing direction to right. For example north.right() should be
	 * east
	 * 
	 * @return
	 */
	public FacingEnum right() {
		int next = next(index);
		return getByCode(next);
	}

	/**
	 * flip a facing direction to left. For example north.left() should be west
	 * 
	 * @return
	 */
	public FacingEnum left() {
		int prev = prev(index);
		return getByCode(prev);
	}

	private int next(int i) {
		return i == 3 ? 0 : ++i;
	}

	private int prev(int i) {
		return i == 0 ? 3 : --i;
	}
}
