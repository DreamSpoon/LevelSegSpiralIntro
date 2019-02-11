package levelsegintrospiral.spiralworld;

public abstract class LerpFunc {
	public abstract double lerp(double input);

	/*
	 * Lerp from begin to end, using the position of v between min and max to do so.
	 * The lerp can be linear, sine, etc. by passing the lerp func to the method.
	 */
	public static double lerp(LerpFunc lerpFunc, double input, double minOutput, double maxOutput, double minInput, double maxInput) {
		// get the lerp deltas
		double outputDelta = maxOutput - minOutput;
		double inputDelta = maxInput - minInput;
		// clamp the incoming input value
		if(input < minInput)
			input = minInput;
		else if (input > maxInput)
			input = maxInput;
		// return lerp
		return minOutput + outputDelta * lerpFunc.lerp((input-minInput) / inputDelta);
	}
}
