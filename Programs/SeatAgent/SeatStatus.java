package kr.ac.kaist.iot.service.n1.seat;

public class SeatStatus {
	private boolean isOccupied = false;

	private boolean assumeOccupied = false;
	private long lastAssumptionAt = 0L;

	public SeatStatus() {

	}

	public boolean update(int[] readValues, long thresholdMinValue,
			long thresholdMaxValue, long thresholdTime) {
		boolean tempOccupied = false;

		for (int i = 0; i < readValues.length; i++) {
			if (readValues[i] >= thresholdMinValue
					&& readValues[i] <= thresholdMaxValue) {
				tempOccupied = true;
				break;
			}
		}

		long now = System.currentTimeMillis();
		if (assumeOccupied != tempOccupied) {
			// New assumption made, update last assumption time
			assumeOccupied = tempOccupied;
			lastAssumptionAt = now;
			return false;
		} else {
			if (isOccupied != assumeOccupied
					&& (now - lastAssumptionAt) > thresholdTime) {
				// update occupied
				isOccupied = assumeOccupied;
				lastAssumptionAt = now;
				return true;
			} else {
				return false;
			}
		}

	}

	public boolean isOccupied() {
		return this.isOccupied;
	}

	@Override
	public String toString() {
		return isOccupied() ? "Occupied" : "Empty";
	}
}
