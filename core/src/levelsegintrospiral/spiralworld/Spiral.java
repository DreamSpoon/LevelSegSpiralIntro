package levelsegintrospiral.spiralworld;

import com.badlogic.gdx.math.Vector2;

import levelsegintrospiral.tools.MapInfo;

public class Spiral {
	private static final double MIN_DRAW_RADIUS = 1f;
	private static final double MIN_THETA = 1f;

	private boolean isRightHanded;
	private double spiralScale;
	private double spiralExp;

	public Spiral(boolean isRightHanded, double spiralScale, double spiralExp) {
		this.isRightHanded = isRightHanded;
		this.spiralScale = spiralScale;
		this.spiralExp = spiralExp;
	}

	/*
	 * angle is in radians.
	 */
	public Vector2 getPositionForTheta(double theta) {
		// x * -1 if this is a right handed spiral
		if(isRightHanded) {
			return new Vector2((float) (spiralScale*Math.exp(spiralExp*theta)*Math.cos(theta) * -1f),
					(float) (spiralScale*Math.exp(spiralExp*theta)*Math.sin(theta)));
		}
		else {
			return new Vector2((float) (spiralScale*Math.exp(spiralExp*theta)*Math.cos(theta)),
					(float) (spiralScale*Math.exp(spiralExp*theta)*Math.sin(theta)));
		}
	}

	public Vector2 getUpVecForTheta(double theta) {
		Vector2 pos1 = getPositionForTheta(theta);
		Vector2 pos2 = getPositionForTheta(theta + Math.PI*2f);
		return pos2.sub(pos1).setLength(1f);
	}

	public float getScaleForTheta(double theta) {
		Vector2 pos1 = getPositionForTheta(theta);
		Vector2 pos2 = getPositionForTheta(theta + Math.PI*2f);
		return pos2.sub(pos1).len() / MapInfo.MAX_SLICE_HEIGHT_MAIN / MapInfo.TILE_HEIGHT;
	}

	public boolean isRightHanded() {
		return isRightHanded;
	}

	public Arc[] getArcsInView(Vector2 viewPos, double viewRadius) {
		// calculate the angle of the cone that starts at center of the spiral and extends out to the view circle  
		double viewCircleDelta = Math.atan2(viewRadius, viewPos.len()) * 5f;
		double viewCircleDirection = viewPos.angleRad();
		// angleRad may return a negative value, so ensure view circle dir is positive
		if(viewCircleDirection < 0)
			viewCircleDirection += Math.PI*2f;
		// if spiral is right handed then flip the x, but in an angle way....
		if(isRightHanded) {
			if(viewCircleDirection >= 0 && viewCircleDirection <= Math.PI)
				viewCircleDirection = Math.PI - viewCircleDirection;
			else
				viewCircleDirection = Math.PI*3f - viewCircleDirection;
		}

		// if closer than min dist then return minimum arc 
		if(viewPos.len() - viewRadius < MIN_DRAW_RADIUS) {
			double maxDist = viewPos.len() + viewRadius;
			int shell;
			for(shell=0; shell<1024; shell++) {
				if(getPositionForTheta(viewCircleDirection + shell*Math.PI*2f).len2() > maxDist*maxDist)
					break;
			}
			return new Arc[] { new Arc(MIN_THETA, viewCircleDirection + shell*Math.PI*2f) };
		}
		// calculate the arc(s)
		else {
			// get the first arc's theta and the last arc's theta
			double minDist = viewPos.len() - viewRadius;
			double maxDist = viewPos.len() + viewRadius;
			int i;
			for(i=0; i<1024; i++) {
				if(getPositionForTheta(viewCircleDirection + i*Math.PI*2f).len2() > minDist*minDist)
					break;
			}
			int startI = i-2;
			for(i++; i<1024; i++) {
				if(getPositionForTheta(viewCircleDirection + i*Math.PI*2f).len2() > maxDist*maxDist)
					break;
			}
			int endI = i+1;

			// calculate the individual arcs
			Arc[] arcs = new Arc[endI - startI];
			for(int j=startI; j<endI; j++) {
				arcs[j-startI] = new Arc(viewCircleDirection + j*Math.PI*2f - viewCircleDelta/2f,
						viewCircleDirection + j*Math.PI*2f + viewCircleDelta/2f);
			}
			return arcs;
		}
	}

	public void setExp(double spiralExp) {
		this.spiralExp = spiralExp;
	}

	public double getExp() {
		return spiralExp;
	}
}
